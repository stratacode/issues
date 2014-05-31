import sc.util.FileUtil;
import java.util.ArrayList;

@sc.js.JSSettings(replaceWith="jv_Object", jsLibFiles="js/javasys.js")
public dynamic class MarkDownPage {

   @sc.obj.HTMLSettings(returnsHTML=true)
   public static String markDown(String input) {
      String markDownCMD = System.getProperty("markDown.cmd");
      if (markDownCMD == null) {
          markDownCMD = System.getenv("markDown.cmd");
          if (markDownCMD == null)
             System.err.println("*** Can't find markDown.cmd in system properties or environment");
          return input;
      }

      ArrayList<String> args = new ArrayList<String>();
      args.add(markDownCMD);
      return FileUtil.execCommand(args, input);
   }
}
