public spring.std extends sys.std {
/*
   configFileProcessor {
      srcPathTypes = {"config"};
   }
*/

   resourceFileProcessor {
      srcPathTypes = {null, "resource"};
   }

   object validatorPkg extends MvnRepositoryPackage {
      url = "mvn://javax.validation/validation-api/1.1.0.Final";
   }
}
