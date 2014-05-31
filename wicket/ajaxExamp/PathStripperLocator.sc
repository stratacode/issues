
// From: http://cwiki.apache.org/WICKET/control-where-html-files-are-loaded-from.html#ControlwhereHTMLfilesareloadedfrom-InWicket1.3
public class PathStripperLocator extends ResourceStreamLocator {

    public PathStripperLocator() {
    }

    public IResourceStream locate(final Class clazz, final String path) {
        IResourceStream located = super.locate(clazz, trimFolders(path));
        if (located != null) {
            return located;
        }
        return super.locate(clazz, path);
    }

    private String trimFolders(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }
}

