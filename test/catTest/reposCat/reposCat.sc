import atg.repository.Repository;
import atg.repository.MutableRepository;
import atg.repository.MutableRepositoryItem;
import atg.repository.RepositoryItemDescriptor;
import atg.repository.Query;
import atg.repository.RepositoryItem;
import atg.repository.RepositoryView;
import atg.repository.NamedQueryView;
import atg.repository.ParameterSupportView;
import atg.repository.RepositoryException;
import atg.nucleus.GenericService;
import atg.dtm.TransactionDemarcation;

import javax.transaction.TransactionManager;
import java.util.Iterator;

test.catTest.reposCat extends jpaCat {
   buildDir="/atg/home/locallib";
   classPath=sc.util.FileUtil.listFiles(getRelativeFile("./lib"),".*\\.jar");

   disabled = true;
}
