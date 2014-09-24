import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import sc.gwt.CListBox;
import sc.gwt.CTextBox;

import sc.gwt.GWTModule;

public gwt.main extends lib, meta, jreStub {

   public void start() {
      sc.lang.DefaultAnnotationProcessor moduleProc = new sc.gwt.lang.GWTModuleAnnotation();
      sc.layer.LayeredSystem system = getLayeredSystem();

      moduleProc.typeGroupName = "gwtModules";
      moduleProc.validOnField = false;
      moduleProc.needsCompiledClass = true;
      String[] is = new String[] {"com.google.gwt.core.client.EntryPoint"};
      moduleProc.appendInterfaces = is;
      registerAnnotationProcessor("sc.gwt.GWTModule", moduleProc);

      system.addPostBuildCommand(sc.layer.BuildPhase.Process, this, "ant", "javac");

      // respect the -c option - if no main command is specified, we'll compile and not run the app
      if (system.options.runClass != null) {
         String[] mainArgs = new String[] { "-startupUrl", "index.html", "-war", "web",
            "[<% java.util.List<sc.layer.TypeGroupMember> gwtModules = buildInfo.getTypeGroupMembers(\"gwtModules\"); " +
            "  boolean first = true; " +
            "  for (sc.layer.TypeGroupMember gwtModule:gwtModules) { " +
            "     if (!first) { %> <% } first = false; %><%= gwtModule.typeName %><% " +
            "  } %>]"
         };
         system.buildInfo.addMainCommand(this, "com.google.gwt.dev.DevMode", "StrataCodeGWT", mainArgs);
      }

      // Turn off compilation since we need to use gwt to do this. 
      system.options.noCompile = true;

      // Need to run from the build dir (now that we do not extend jetty)
      system.options.runFromBuildDir = true;

      String rtSrcDir = system.getStrataCodeRuntimePath(true, true);
      if (rtSrcDir != null)
         system.addSystemClassDir(rtSrcDir);
      else
         System.err.println("*** Warning: You must add Java argument: -Dsc.core.src.path='<path to scrt-core-src.jar>' to run StrataCode applications from the interpreter");

      // Also tell the system to add any layer specific "src" dirs into the system class loader.  We load DevMode with that class loader and it finds the src.
      system.includeSrcInClassPath = true;
   }
}
