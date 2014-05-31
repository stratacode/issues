public class ReposCatService extends GenericService {
   private MutableRepository repos;

   public void setRepos(MutableRepository r) {
      repos = r;
   }
   public MutableRepository getRepos() {
      return repos;
   }

   private TransactionManager txMgr;
   public void setTransactionManager(TransactionManager tm) {
      txMgr = tm;
   }
   public TransactionManager getTransactionManager() {
      return txMgr;
   }

   private boolean createDatabase;
   public boolean getCreateDatabase() {
      return createDatabase;
   }
   public void setCreateDatabase(boolean cdb) {
      createDatabase = cdb;
   }

   private class RunnerThread extends Thread {
      ReposCatalogManager mgr; 
      int instanceIndex;
      int numQueried = 0;
      public void run() {
         for (int i = 0; i < BaseCatalogManager.numIterationsPerThread; i++) {
            mgr = new ReposCatalogManager();
            mgr.setTransactionManager(txMgr);
            mgr.setRepos(repos);
            mgr.instanceIndex = instanceIndex;
            mgr.sequenceNumber = i;
            mgr.init();

            if (createDatabase) {
               synchronized (ReposCatService.this) {
                  if (createDatabase) {
                     createDatabase = false;
                     System.out.println("**** Populating database!");
                     mgr.runTest(new String[] {"-c"});
                     continue;
                  }
               }
            }

            mgr.runTest(new String[0]);
            numQueried += mgr.numQueried;
         }
      }
   }

   public void doStartService() {
      System.out.println("*** ReposCatService Starting with repository: " + repos);
      RunnerThread[] threads = new RunnerThread[BaseCatalogManager.numThreads];
      long startTime = System.currentTimeMillis();
      for (int i = 0; i < BaseCatalogManager.numThreads; i++) {
         RunnerThread rt;
         
         rt = threads[i] = new RunnerThread();
         rt.instanceIndex = i;
         rt.start();
      }

      try {
         int numQueried = 0;
         for (int i = 0; i < BaseCatalogManager.numThreads; i++) {
            threads[i].join();
            numQueried += threads[i].numQueried;
         }
         System.out.println("**** TOTAL time: " + (System.currentTimeMillis()-startTime) + " millis.  Total queried:" + numQueried);
      }
      catch (InterruptedException exc) {
         System.out.println("**** Interrupted!");
      }
      System.out.println("** before exit");
      System.exit(0);
      System.out.println("** after exit??");
   }
}
