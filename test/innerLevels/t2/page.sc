class page {

   class body {

       class editorMixin extends EditorFrame {

           String getString() {
              return "super:" + super.getString() + 
                      " this: " + page.this.getString() + ":" + body.this.getString() + ":" + "editorMixin";
           }
       }

       void init() {
          editorMixin emix = new editorMixin();
          emix.init();
          System.out.println(emix.getString());
          assertTrue(emix.getString().equals("super:EditorFrame this: page:page:body:editorMixin"));
       }

      String getString() {
         return page.this.getString() + ":body";
      }
   }

   void init() {
      body body = new body();
      body.init();
   }

   String getString() {
      return "page";
   }

   @Test
   void doTest() {
      page page = new page();
      page.init();
      System.out.println("*** Test passed");
   }
}
