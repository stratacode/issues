class page {

   class body {

       class editorMixin extends EditorFrame {

           String toString() {
              return "super:" + super.toString() + 
                      " this: " + page.this + ":" + body.this + ":" + "editorMixin";
           }
       }

       void init() {
          editorMixin emix = new editorMixin();
          emix.init();
          System.out.println(emix.toString());
          assertTrue(emix.toString().equals("super:EditorFrame this: page:page:body:editorMixin"));
       }

      String toString() {
         return page.this + ":body";
      }
   }

   void init() {
      body body = new body();
      body.init();
   }

   String toString() {
      return "page";
   }

   @Test
   void doTest() {
      page page = new page();
      page.init();
      System.out.println("*** Test passed");
   }
}
