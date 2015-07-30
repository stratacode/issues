import org.apache.catalina.startup.Catalina;

public class StartTomcat {
   @sc.obj.MainSettings(produceScript = true, execName = "startSCTomcat", debug = false, 
                        defaultArgs = "-config ./conf/server.xml")
   public static void main(String[] args) throws Exception {
       Catalina catalina = new Catalina();
       catalina.setParentClassLoader(Thread.currentThread().getContextClassLoader());
       catalina.load(args);
       catalina.start();
       catalina.await();
   }
}
