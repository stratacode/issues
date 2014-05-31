
jpa.hibernate extends basejpa {
   classPath=sc.util.FileUtil.listFiles(getRelativeFile("./lib"),".*\\.jar");

   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.Program;
}
