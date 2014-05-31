
BaseCatalogManager {
   numThreads = 10;

   createsPerTransaction = 1000;

   // Number of queries for each iteration (session)
   numQueries = 100; // 1: 2000000

   // Number of sessions for each thread
   numIterationsPerThread = 500; // 1: 1

   numTransactionsPerTimeCheck = 100; // As we shrink the transaction size, up this value for recording
}
