public class Question {
  public String[] questions = {"a", "b"};
  public String[] boundQuestions;
  public int foo = 9;

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

  String bar = currentQuestion == null ? null : currentQuestion.questions[0];
  int fum = currentQuestion == null ? null : currentQuestion.questions[0].length();
  String baz = innerQs == null ? null : innerQs[0].innerQuestions[0] + "bar";
  String yaz = innerQs == null ? null : innerQs[0].qx;

  String bbar = currentQuestion == null ? null : currentQuestion.boundQuestions[0];
  int ffum = currentQuestion == null ? null : currentQuestion.boundQuestions[0].length();
  String bbaz = currentQuestion == null ? null : currentQuestion.boundQuestions[0] + "bar";
  String byaz = boundInnerQs == null ? null : boundInnerQs[0].qx;
  String bdaz = boundInnerQs == null ? null : boundInnerQs[0].innerQuestions[0];
  String bcaz = boundInnerQs == null ? null : boundInnerQs[0].boundInnerQuestions[0];

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
