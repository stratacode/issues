
import sc.util.FileUtil;

public class ResourceStreamLocator extends org.apache.wicket.util.resource.locator.ResourceStreamLocator {

   String[] rootDirs;
   String[] prefixes;

   public ResourceStreamLocator() {
   }

   public ResourceStreamLocator(String[] rootDirs, String[] prefixes) {
      this.rootDirs = rootDirs;
      this.prefixes = prefixes;
   }

   public IResourceStream locate(Class theClass, String path) {
      if (rootDirs != null && prefixes != null) {
         for (int i = 0; i < rootDirs.length; i++) {
            String prefix = prefixes[i].replace(".", FileUtil.FILE_SEPARATOR);
            if (prefix == null || path.startsWith(prefix)) {
               IResourceStream s = super.locate(theClass, path.substring(prefix.length()));
               if (s != null)
                  return s;
            }
         }
       }
       return super.locate(theClass, path);
    }
}
