import org.apache.catalina.startup.Catalina;

public class StartTomcat {
   @sc.obj.MainSettings(produceScript = true, execName = "startSCTomcat", debug = false, 
                        defaultArgs = "-config ./conf/server.xml start")
   public static void main(String[] args) throws Exception {
      Catalina catalina = new Catalina();
      catalina.setParentClassLoader(Thread.currentThread().getContextClassLoader());
      catalina.load(args);
      catalina.start();

      if (catalina.getServer() != null) {
         // Currently StrataCode assumes the 'main' method starts the server and returns once
         // it has started.  Tomcat appears to mark it's threads as Daemon so we need another
         // non-daemon thread here to wait for it.
         new Thread(new Runnable() {
            public void run() {
               catalina.await();
            }
         }).start();
      }
   }
}
