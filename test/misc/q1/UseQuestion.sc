import sc.obj.MainSettings;

public class UseQuestion {
   object question1 extends BQuestion {
     questions = new String[]{"a", "b", "c"};
     foo += 1;

   }

   BQuestion currentQuestion;

   String text := "The correct answer: " + currentQuestion.answer;

   boolean bar, baz;
   int foo := bar ? 0 : (baz ? 1 : 2);

   @MainSettings(produceScript=true, test=true)
   public static void main(String[]args) {
      UseQuestion q = new UseQuestion();
      q.currentQuestion = q.question1;
      System.out.println("*** foo: " + q.foo);
      q.baz = true;
      System.out.println("*** foo: " + q.foo);
      q.bar = true;
      System.out.println("*** foo: " + q.foo);
   }
}
