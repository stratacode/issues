import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public abstract class BaseCatalogManager {
   // NOTE: When you change treeDepth or numChildren, you need to rebuild the database.  
   // Run with the "-c" option using openjpa or hibernate after dropping the tables.
   static final int treeDepth = 4; // 5 levels: 111110 nodes.  
   static final int numChildren = 10;

   // Individual test parameters.  Look in cfg/x/BaseCatalogManager for overrides for a specific test run
   static final int numThreads = 5;    // Number of test threads to start
   static final int queriesPerTransaction = 50;    //  Number of nodes to visit before committing the transaction 
   static final int numQueries = 200;              // Number of queries for each iteration (session)
   static final int numIterationsPerThread = 50;   // Number of sessions for each thread
   static final int numTransactionsPerTimeCheck = 100; // As we shrink the transaction size, up this value for recording
   static final boolean replaySameSession = false;    // If true, each thread uses the same random seed for more cache hits
   static final int createsPerTransaction = 1000;   // With -c, specifies the transaction size
   static final boolean useManyToMany = true;

   static final boolean debug = false;

   Random rand; 

   public int instanceIndex = -1;
   public int sequenceNumber = 1;

   public void init() {
      if (replaySameSession)
         sequenceNumber = 1;
      rand = new Random(instanceIndex * 17 + sequenceNumber * 43);
   }

   public abstract SimpleCategory getCategoryById(int id);
   public Object beginTransaction() {
      if (lastTxStart == 0)
         lastTxStart = System.currentTimeMillis();
      return null;
   }

   int txCount = 0;
   public void commitTransaction(Object tx) {
      if ((txCount++ % numTransactionsPerTimeCheck) == 0) {
         lastTxStart = System.currentTimeMillis();
      }
   }

   public abstract void rollbackTransaction(Object tx);
   public abstract SimpleCategory newCategory();
   public abstract void createCategory(SimpleCategory cat);

   public static Object initTestLock = new Object();
   private static boolean testsInited = false;

   public void runTest(String[] args) {

      synchronized (initTestLock) {
         if (!testsInited) {
            testsInited = true;

            for (int i = 0; i < args.length; i++) {
               if (args[i].length() == 0)
                  System.out.println("Invalid empty option");
               if (args[i].charAt(0) == '-') {
                  if (args[i].length() == 1)
                     System.out.println("Invalid option: " + args[i]);

                  String opt = args[i].substring(1);
                  if (opt.length() > 2)
                     System.out.println("Invalid option: " + opt);
                  switch (opt.charAt(0)) {
                     case 'c':
                        int beforeCt = numCreated;
                        long startTime = System.currentTimeMillis();
                        createTree();
                        long endTime = System.currentTimeMillis();
                        System.out.println("*** created: " + (numCreated - beforeCt) + " in: " + (endTime-startTime) + " millis");
                        break;
                  }
               }
            }
         }
      }

      long startTime = System.currentTimeMillis();
      queryTree(numQueries, treeDepth, numChildren);
      System.out.println("*** queried: " + numQueried + " categories in: " + timeStr(startTime) + " for thread: " + instanceIndex);
   }

   protected String timeStr(long startTime) {
      long endTime = System.currentTimeMillis();
      return (endTime - startTime) + " millis";
   }

   long lastTxStart;

   public void createTree() {
      Object tx = beginTransaction();
      boolean committed = false;
      try {
         tx = createCategoryTree(tx, treeDepth, numChildren, null);
         commitTransaction(tx);
         committed = true;
      }
      finally {
         if (!committed)
            rollbackTransaction(tx);
      }
   }

   int numCreated = 0;
   int numQueried = 0;

   public Object createCategoryTree(Object tx, int depth, int num, SimpleCategory currentParent) {
      int dix = treeDepth - depth;
      for (int j = 0; j < num; j++) {
         SimpleCategory cat = newCategory();
         cat.setId(numCreated++);
         cat.setDepth(dix);
         cat.setChildIndex(j);
         cat.setName("Category: " + cat.getId() + " level: " + dix + " child: " + j);
         cat.setDescription("Description for: " + cat.getName());
         cat.setLongDescription("Some even longer description for: " + cat.getName());
         if (currentParent != null)
            currentParent.addChildCategory(cat);
         createCategory(cat);
         if (depth > 0)
            tx = createCategoryTree(tx, depth-1, num, cat);

         if ((numCreated % createsPerTransaction) == 0) {
            System.out.println("**** committing: " + createsPerTransaction + " (total: " + numCreated + " for: " + instanceIndex);
            commitTransaction(tx);
            tx = beginTransaction();
         }
      }
      return tx;
   }

   public void queryTree(int numQueries, int maxDepth, int maxNum) {
      Object tx = beginTransaction();

      SimpleCategory cat = getCategoryById(0);
      numQueried++;
      if (cat == null) {
         throw new IllegalArgumentException("Can't find root category with id=0");
      }
      assertTrue(cat.getDepth() == 0);
      assertTrue(cat.getChildIndex() == 0);
      assertTrue(cat.getParentCategory() == null);

      for (int i = 0; i < numQueries; i++) {
         int d = Math.abs(rand.nextInt()) % maxDepth + 1;
         testQuery(cat, i, d, maxNum, d);

         if ((i % queriesPerTransaction) == 0) {
            commitTransaction(tx);
            tx = beginTransaction();
         }
      }
      commitTransaction(tx);
   }

   public void testQuery(SimpleCategory parent, int ix, int depth, int maxNum, int startDepth) {
      int child = Math.abs(rand.nextInt()) % maxNum;

      int x = 0;
      if (parent.getChildren() == null) {
         throw new IllegalArgumentException("children are null for parent: " + parent.getId() + " name: " + parent.getName());
      }
      Iterator<SimpleCategory> children = !useManyToMany ? parent.getChildrenIterator() :
                                                           parent.getFixedChildCategoriesIterator();
      int childrenCt = 0;
      SimpleCategory ci;
      while (children.hasNext()) {
         ci = children.next();
         numQueried++;
         if (x++ == child) {
            if (depth > 0)
               testQuery(ci, ix, depth-1, maxNum, startDepth);
            else {
               int childDepth = parent.getDepth() + 1;
               // Make sure we got the right one!
               assertTrue(ci.getDepth() == childDepth);
               // Set is not ordered
               //assertTrue(ci.getChildIndex() == x - 1);
               assertTrue(ci.getName().indexOf("level: " + childDepth) != -1);
               //assertTrue(ci.getName().indexOf("child: " + (x-1)) != -1);
               //

               if (debug)
                  System.out.println("*** matched: " + ci.getId() + " childIndex:" + x + " at depth: " + startDepth + " for ct: " + ix + " parent: " + parent.getId()); 
            }
            return;
         }
         childrenCt++;
      }
      if (depth == 0) {
         assert (childrenCt == 0);
         // Make sure we got the right one!
         assertTrue(parent.getDepth() == startDepth);
         // Set is not ordered
         //assertTrue(ci.getChildIndex() == x - 1);
         assertTrue(parent.getName().indexOf("level: " + startDepth) != -1);
         if (debug)
            System.out.println("*** matched leaf node: " + parent.getId() + " at depth: " + startDepth + " for ct: " + ix); 
      }
      else
         System.out.println("*** depth is not zero for node: " + parent.getId() + " children: " + parent.getChildren() + " index: " + ix + " childIndex: " + child);
   }

   public void assertTrue(boolean t) {
      if (!t)
         throw new IllegalArgumentException("Test failed!");
   }
}
