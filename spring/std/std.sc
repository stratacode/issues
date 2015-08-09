
public spring.std extends sys.std, html.core {
/*
   configFileProcessor {
      srcPathTypes = {"config"};
   }
*/

   resourceFileProcessor {
      srcPathTypes = {null, "resource"};
   }

   configFileProcessor {
      srcPathTypes = {"config"};
   }

   // Need to treat xml files as resources - so don't process them as web files
   webFileProcessor {
      srcPathTypes = {"web"};
   }

   object validatorPkg extends MvnRepositoryPackage {
      url = "mvn://javax.validation/validation-api/1.1.0.Final";
   }
}
