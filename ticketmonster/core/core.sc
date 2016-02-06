ticketmonster.core {
   object ticketMonsterPkg extends RepositoryPackage {
      packageName = "ticketmonster";
      type = "git";
      url = "git@github.com:jboss-developer/ticket-monster.git";

      // No src for this package - only subpackages
      definesSrc = false;
      definesClasses = false;

      object demoPkg extends MvnRepositoryPackage {
         url = "demo"; 

         srcPaths = {"src/main/java", "src/main/resources"};
         webPaths = {"src/main/webapp"};

         // The POM file assumes we have installed JBoss EAP so marks 
         // dependencies as 'provided'.  We'll just suck them in here but
         // perhaps they should be included in some base layer we extend?
         includeProvided = true;

         // Need to use the jboss repositories defined in this POM
         useRepositories = true;
      }
   }
}
