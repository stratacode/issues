import java.util.Iterator;

public class JPACatalogManager extends BaseCatalogManager {
   static EntityManagerFactory factory;
   EntityManager entityManager;

   public void init() {
      super.init();
      if (factory == null) {
         synchronized (BaseCatalogManager.class) {
            if (factory == null)
               factory = Persistence.createEntityManagerFactory("simpleCatalog");
         }
      }
      entityManager = factory.createEntityManager();
   }

   public SimpleCategory getCategoryById(int id) {
     return entityManager.find(SimpleCategory.class, id);
   }

   public Object beginTransaction() {
      super.beginTransaction();
      EntityTransaction tx = entityManager.getTransaction();
      tx.begin();
      return tx;
   }
   public void commitTransaction(Object tx) {
      ((EntityTransaction) tx).commit();
      super.commitTransaction(tx);
   }

   public void rollbackTransaction(Object tx) {
      EntityTransaction etx = (EntityTransaction) tx;
      if (etx.isActive())
         etx.rollback();
   }
   public SimpleCategory newCategory() {
      return new SimpleCategory();
   }

   public void createCategory(SimpleCategory cat) {
      entityManager.persist(cat);
   }

   static class RunnerThread extends Thread {
      JPACatalogManager mgr; 
      int instanceIndex;
      String[] args;
      int numQueried;
      public void run() {
         for (int i = 0; i < numIterationsPerThread; i++) {
            mgr = new JPACatalogManager();
            mgr.instanceIndex = instanceIndex;
            mgr.sequenceNumber = i;
            mgr.init();
            mgr.runTest(args);
            numQueried += mgr.numQueried;
         }
      }
   }

   @sc.obj.MainSettings(produceScript=true, execName="run", maxMemory=2048)
   public static void main(String[] args) {
      long startTime = System.currentTimeMillis();
      RunnerThread[] threads = new RunnerThread[numThreads];
      for (int i = 0; i < numThreads; i++) {
         RunnerThread rt;
         
         rt = threads[i] = new RunnerThread();
         rt.args = args;
         rt.instanceIndex = i;
         rt.start();
      }
      try {
         int numQueried = 0;
         for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            numQueried += threads[i].numQueried;
         }
         System.out.println("**** TOTAL time: " + (System.currentTimeMillis()-startTime) + " millis");
         System.out.println("**** TOTAL time: " + (System.currentTimeMillis()-startTime) + " millis.  Total queried:" + numQueried);
      }
      catch (InterruptedException exc) {
         System.out.println("**** Interrupted!");
      }
   }
}
