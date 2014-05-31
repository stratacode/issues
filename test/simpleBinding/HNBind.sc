import java.util.List;
import sc.util.ArrayList;

public class HNBind {

   public static class Question {
      String question = "!!";
      Question(String q) {
        question = q;
      }
      public String toString() {
          return question;
      }
   }

   public static class InnerD {
      Integer a2 = 4;
      String s2 = "bar";

      List<Question> questions;
   }

   public static class InnerC {
      Integer a1 = 3;
      String s1 = "foo";

      InnerD id;

      InnerD[] ids;

      Integer a3 := id == null ? 0 : id.a2;
      String s3 := id.s2;
   }

   InnerC ref1;
   Integer refA1 := ref1 == null ? 0 : ref1.a1;
   String refS1 := ref1.s1;

   Integer refA2 := ref1 == null ? 0 : ref1.a3;
   String refS2 := ref1.s3;

   String refS3 := ref1.id.s2;
   Integer refA3 := ref1 == null || ref1.id == null ? 0 : ref1.id.a2;

   int questionIndex = 0;
   Question currentQuestion := ref1.id.questions.get(questionIndex);

   private List qlist;
   public void setQuestionList(List questions) {
      System.out.println("*** new question list: " + questions);
      qlist = questions;
   }
   public List getQuestionList() {
      return qlist;
   }

   questionList := ref1.id.questions;

   @MainSettings(test=true)
   public static void main(String[] args) {
      HNBind hn = new HNBind();
      System.out.println("*** 0= " + hn.refA1);
      System.out.println("*** null: " + hn.refS1);

      System.out.println("*** 0= " + hn.refA2);
      System.out.println("*** null: " + hn.refS2);

      System.out.println("*** 0= " + hn.refA3);
      System.out.println("*** null: " + hn.refS3);

      System.out.println("***cq null: " + hn.currentQuestion);

      hn.ref1 = new InnerC();

      System.out.println("*** 3= " + hn.refA1);
      System.out.println("*** foo: " + hn.refS1);

      System.out.println("*** 0= " + hn.refA2);
      System.out.println("*** null: " + hn.refS2);

      System.out.println("*** 0= " + hn.refA3);
      System.out.println("*** null: " + hn.refS3);

      System.out.println("***cq null: " + hn.currentQuestion);

      hn.ref1.id = new InnerD();

      System.out.println("*** 3= " + hn.refA1);
      System.out.println("*** foo: " + hn.refS1);

      System.out.println("*** 4= " + hn.refA2);
      System.out.println("*** bar: " + hn.refS2);

      System.out.println("*** 4= " + hn.refA3);
      System.out.println("*** bar: " + hn.refS3);

      System.out.println("***cq null: " + hn.currentQuestion);

      ArrayList<Question> qs = new ArrayList<Question>();
      qs.add(new Question("q1"));
      qs.add(new Question("q2"));

      hn.ref1.id.questions = qs;

      qs.add(new Question("q33"));
      qs.add(new Question("q44"));

      System.out.println("***cq q1: " + hn.currentQuestion);
      System.out.println("***cq g1: " + hn.currentQuestion.question);

      hn.questionIndex = 1;

      System.out.println("***cq q2: " + hn.currentQuestion);
      System.out.println("***cq q2: " + hn.currentQuestion.question);

      InnerD newD = new InnerD();
      qs = new ArrayList<Question>();
      qs.add(new Question("q3"));
      qs.add(new Question("q4"));
      newD.questions = qs;
      hn.ref1.id = newD;

      System.out.println("***cq q4: " + hn.currentQuestion);
      System.out.println("***cq g4: " + hn.currentQuestion.question);

      hn.questionIndex = 0;

      System.out.println("***cq q3: " + hn.currentQuestion);
      System.out.println("***cq q3: " + hn.currentQuestion.question);

      hn.ref1 = new InnerC();

      hn.ref1.id = new InnerD();

      qs = new ArrayList<Question>();
      qs.add(new Question("q5"));
      qs.add(new Question("q6"));
      hn.ref1.id.questions = qs;

      System.out.println("***cq q5: " + hn.currentQuestion);
      System.out.println("***cq q5: " + hn.currentQuestion.question);

      hn.questionIndex = 1;

      System.out.println("***cq q6: " + hn.currentQuestion);
      System.out.println("***cq q6: " + hn.currentQuestion.question);

      //System.out.println("All active bindings");
      //sc.bind.Bind.printBindings(hn);
      //sc.bind.Bind.printBindings(hn.ref1);
      sc.bind.Bind.removeBindings(hn);
      sc.bind.Bind.removeBindings(hn.ref1);
      System.out.println("Should be empty now");
      sc.bind.Bind.printBindings(hn);
      sc.bind.Bind.printBindings(hn.ref1);
   }
}
