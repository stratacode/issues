import java.util.Iterator;
import javax.transaction.TransactionManager;
import atg.dtm.TransactionDemarcationException;

public class ReposCatalogManager extends BaseCatalogManager {
   private MutableRepository repos;
   private TransactionManager txMgr;

   public void setRepos(MutableRepository r) {
      repos = r;
   }
   public MutableRepository getRepos() {
      return repos;
   }

   public void setTransactionManager(TransactionManager mgr) {
      txMgr = mgr;
   }

   public void init() {
      super.init();
      if (repos == null)
         throw new IllegalArgumentException("**** Error - no repository!");
      if (txMgr == null)
         throw new IllegalArgumentException("**** Error - no transaction manager!");
   }

   static final int treeDepth = 3;
   static final int numChildren = 3;

   public SimpleCategory getCategoryById(int id) {
      try {
         RepositoryItemDescriptor catDesc = repos.getItemDescriptor("simpleCategory");
         NamedQueryView view = (NamedQueryView) catDesc.getRepositoryView();
         Query q = view.getNamedQuery("byDepthAndIndex");
         RepositoryItem[] items = ((ParameterSupportView)view).executeQuery(q, new Object[] { new Integer(0), new Integer(0) });
         if (items == null || items.length == 0) {
            System.out.println("**** No root item");
            return null;
         }
         else if (items.length != 1) {
            System.out.println("**** Too many roots");
         }
         return new ReposItemCategory((MutableRepositoryItem)items[0]);

         //return new ReposItemCategory((MutableRepositoryItem)repos.getItem(String.valueOf(id), "simpleCategory"));
      }
      catch (RepositoryException exc) {
         System.err.println("*** repository exception" + exc);
      }
      return null;
   }

   public Object beginTransaction() {
      super.beginTransaction();
      TransactionDemarcation td = new TransactionDemarcation();
      try {
         td.begin(txMgr, td.REQUIRED);
      }
      catch (TransactionDemarcationException exc) {
         System.err.println("*** Can't start tx" + exc);
         return null;
      }
      return td;
   }
   public void commitTransaction(Object tx) {
      TransactionDemarcation td = (TransactionDemarcation) tx;
      try {
         td.end();
         super.commitTransaction(tx);
      }
      catch (TransactionDemarcationException exc) {
         System.err.println("*** Can't end tx" + exc);
      }
   }

   public void rollbackTransaction(Object tx) {
      commitTransaction(tx);
   }

   public SimpleCategory newCategory() {
      try {
         return new ReposItemCategory(repos.createItem("simpleCategory"));
      }
      catch (RepositoryException exc) {
         System.err.println("*** repository exception" + exc);
      }
      return null;
   }

   public void createCategory(SimpleCategory cat) {
      try {
         repos.addItem(((ReposItemCategory) cat).getItem());
      }
      catch (RepositoryException exc) {
         System.err.println("*** " + exc);
      }
   }

}
