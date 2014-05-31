public class BQuestion {
  String[] questions;
  String[] boundQuestions;
  int foo = 9;

  static class InnerQ {
     String qx;
     String[] innerQuestions;
     String[] boundInnerQuestions;
     boolean hasBoundInnerQuestions := boundInnerQuestions != null;
  }

  InnerQ[] innerQs;
  InnerQ[] boundInnerQs;

  Question currentQuestion;
  
  boolean hasQuestion := currentQuestion != null;
  boolean hasBoundQuestions := boundQuestions != null && boundQuestions[0] != null;
  boolean hasInnerQs := boundInnerQs != null && boundInnerQs[0] != null;

  String bar := currentQuestion.questions[0];
  int fum := currentQuestion.questions[0].length();
  String baz := innerQs[0].innerQuestions[0] + "bar";
  String yaz := innerQs[0].qx;

  String bbar := currentQuestion.boundQuestions[0];
  int ffum := currentQuestion.boundQuestions[0].length();
  String bbaz := currentQuestion.boundQuestions[0] + "bar";
  String byaz := boundInnerQs[0].qx;
  String bdaz := boundInnerQs[0].innerQuestions[0];
  String bcaz := boundInnerQs[0].boundInnerQuestions[0];

  public String getAnswer() {
      return "foo";
  }

  public void testM() {
    currentQuestion.questions[0] = "foo";
    innerQs[0].innerQuestions[0] = "bar";
    currentQuestion.boundQuestions[0] = "foo";
    boundInnerQs[0].innerQuestions[0] = "foo";
    boundInnerQs[0].boundInnerQuestions[0] = "foo";
  }
}
