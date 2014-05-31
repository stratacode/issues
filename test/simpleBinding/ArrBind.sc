
import sc.bind.BindSettings;

public class ArrBind {
   public int b1 = 3;
   public int[] b2 = {1, 2, 3, 4};

   public String[] strArray = {"a", "b", "c", "d"};
   public String strSel := strArray[b1];

   public String bstrSel :=: strArray[b1];

   public int b3 := b2[b1];

   public String bstr2 := anyMethod(strArray[b1]);

   public String bstr3 :=: anyMethod(strArray[b1]);

   public String[] nstrArr = {"a"};
   public int nstrLen := nstrArr.length;

   public String[] typeNames = {"a"};
   public int numCols := typeNames.length <= 1 ? 1 : typeNames.length <= 4 ? 2 : typeNames.length <= 9 ? 3 : 4;

   @BindSettings(reverseMethod="reverseMethod")
   public String anyMethod(String x) {
      if (x == null)
         return null;
      return x + " ANY";
   }

   public String reverseMethod(String x) {
      if (x == null)
         return null;
      return x.substring(0,x.length()-4); 
   }

   @MainSettings(test=true)
   public static void main(String[]args) {
      ArrBind bd = new ArrBind();

      System.out.println("d ANY=" + bd.bstr2);

      System.out.println("4=" + bd.b3);
      System.out.println("d=" + bd.strSel);
      bd.b1 = 2;
      System.out.println("3=" + bd.b3);
      System.out.println("c=" + bd.strSel);
      System.out.println("c=" + bd.bstrSel);
      bd.strArray[2] = "ccc";
      System.out.println("ccc=" + bd.strSel);
      System.out.println("ccc=" + bd.bstrSel);

      bd.bstrSel = "ccccc";

      System.out.println("ccccc=" + bd.strArray[bd.b1]);
      System.out.println("ccccc=" + bd.bstrSel);
      System.out.println("ccccc=" + bd.strSel);

      bd.b1 = 3;

      System.out.println("d=" + bd.strArray[bd.b1]);
      System.out.println("d=" + bd.bstrSel);
      System.out.println("d=" + bd.strSel);

      bd.bstrSel = "dd";

      System.out.println("dd ANY=" + bd.bstr2);

      System.out.println("dd=" + bd.strArray[bd.b1]);
      System.out.println("dd=" + bd.bstrSel);
      System.out.println("dd=" + bd.strSel);

      bd.bstrSel = null;
      System.out.println("null=" + bd.strArray[bd.b1]);

      bd.bstr3 = null;

      System.out.println("null=" + bd.choice);
      bd.question = null;
      System.out.println("null=" + bd.choice);
      bd.question = new InnerC();
      System.out.println("c1=" + bd.choice);
      bd.choiceIndex = 1;
      System.out.println("c2=" + bd.choice);
      System.out.println("c2=" + bd.choice2);

      bd.question = null;
      System.out.println("null=" + bd.choice2);
      bd.choiceIndex = 0;
      bd.question = new InnerC();
      System.out.println("c1=" + bd.choice2);
      bd.choiceIndex = 1;
      System.out.println("c2=" + bd.choice2);

      System.out.println("nstrLen 1=" + bd.nstrLen);
      bd.nstrArr = new String[] {"a", "b", "c"};
      System.out.println("nstrLen 3=" + bd.nstrLen);

      System.out.println("numCols 1=" + bd.numCols);
      String[] nval = new String[] {"a", "b", "c"}; 
      bd.typeNames = nval;
      System.out.println("numCols 2=" + bd.numCols);

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(bd);
      sc.bind.Bind.removeBindings(bd);
      sc.bind.Bind.removeBindings(ArrBind.class);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(bd);

   }

   public InnerC question = null;


   int choiceIndex = 0;
   String choice :=: question.choices[choiceIndex];

   String choice2 :=: question.getAnswerChoice(choiceIndex);

   public static class InnerC {
      String[] choices = {"c1", "c2", "c3"};

      @BindSettings(reverseMethod="setAnswerChoice")
      public String getAnswerChoice(int index) {
         return choices[index];
      }

      public void setAnswerChoice(String value, int index) {
         choices[index] = value;
      }
   }
}
