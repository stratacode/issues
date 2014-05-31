
BaseCatalogManager {
   // Individual test parameters.  Look in cfg/x/BaseCatalogManager for overrides for a specific test run
   numThreads = 1;    // Number of test threads to start
   queriesPerTransaction = 1;    //  Number of nodes to visit before committing the transaction 
   numQueries = 1;              // Number of queries for each iteration (session)
   numIterationsPerThread = 1;   // Number of sessions for each thread
}
