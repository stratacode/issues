package sc.opengl;

opengl.lib {
   compiledOnly = true;
   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.UI;

   {
      sc.repos.RepositoryPackage pkg = addRepositoryPackage("javaOpenGL", "scp", "vsgit@stratacode.com:/home/vsgit/opengl", false);
      String openGLDir = sc.util.FileUtil.concat(pkg.installedRoot, "lib") + "/";
      classPath=openGLDir + "jogl.all.jar:" + openGLDir + "nativewindow.all.jar:" + 
                openGLDir + "gluegen-rt.jar:" + openGLDir + "newt.all.jar";
      /*String path = System.getProperty("java.library.path");
      if (path == null) path = "";
      else path += ":";
      path += openGLDir;
      System.setProperty("java.library.path", path); */
      sc.layer.LayerUtil.addLibraryPath(openGLDir);
      System.out.println(" new path: " + System.getProperty("java.library.path"));
   }
}
