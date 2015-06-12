import org.apache.catalina.startup.Catalina;

public class StartTomcat {
   @sc.obj.MainSettings(produceScript = true, execName = "startSCTomcat", debug = false)
   public static void main(String[] args) throws Exception {
       Catalina catalina = new Catalina();
       catalina.load(args);
       catalina.start();
       catalina.await();
   }
}
