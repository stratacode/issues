@sc.js.JSSettings(replaceWith="jv_Object", jsLibFiles="js/javasys.js")
dynamic object templateSnippets {
   String simpleMethodCall = "<%= util.header(\"Getting Started\",\"gettingStarted\") %>";
   String templateMethodCall = "<%= util.header(%>Getting Started<%,\"gettingStarted\") %>";
   String templateNestedMethodCall = "<%= util.header(%>Getting <%=\"Start\"%>ed<%,\"gettingStarted\") %>";
   String simpleMethodDecl = "<%! public String header(String title, String fromFile) { %>\n" +
                             "   Template language text and operators which get evaluated and returned from this method\n" +
                             "<% } %>\n" +
                             "<%! public String footer() { %>\n" +
                             "   template text - same file, different method\n" +
                             "<% } %>\n";
   String simpleMethodDecl2 = "<%! public String header(String title, String fromFile) { %>\n" +
                             "   Template language text and operators which get evaluated and returned from this method\n" +
                             "<% } public String footer() { %>\n" +
                             "   template text - same file, different method\n" +
                             "<% } %>\n";
   String simpleExtends = "<%! class foo extends bar { %>\n" +
                          "   This text is part of the template\n" +
                          "<% } %>";
   String simpleVariableDecl = "<% String myStr = %>Now in this template<%;%>";
   String baseObjectTemplate = "<%! String firstName, lastName; %>\n" +
                               "Name: <%= lastName %>, <%= firstName %>";
   String helloTemplate = "Hello World";
   String helloTemplate1 = "<%! class Hello {\n" +
                           "   String message = \"Hello World\";\n" + 
                           "} %>\n" + 
                           "<%= message %>";
   String helloTemplate2 = "<%! Hello {\n" +
                           "   message = \"Hello from another Layer\";\n" + 
                           "} %>\n" + 
                           "<%= super.output() %>";

   String importTemplate = "<%@ import java.util.List; %>";
   String declarationsOp = "<%!";
   String javaOp = "<%";
   String closeOp ="%>";
   String annotationsOp = "<%@";
   String expressionOp = "<%=";
}
