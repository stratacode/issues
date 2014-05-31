/**
 * A Question consists of the question text, four answer choices, and
 * the answer detail.
 */
@Entity
@Table(name="question")
public class Question implements Serializable {
   static int NO_ANSWER = -1;
   static int displayLength = 18;

   @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
   long id;

   @Version 
   int version;

   @Bindable
   @Column(length=500)
   String question;

   @Bindable
   String[] answerChoices = new String[4];

   @Bindable   
   int answerIndex = NO_ANSWER;

   @Bindable
   @Column(length=500)
   String answerDetail;

   /**
    * Returns the answer choice at the given index.
    */
   @BindSettings(reverseMethod="setAnswerChoice")
   public String getAnswerChoice(int index) {
      return answerChoices[index];
   }

   /**
    * Sets the answer choice at the given index to the given value.
    */
   public int setAnswerChoice(String value, int index) {
      if (!StringUtil.equalStrings(value, answerChoices[index])) {
	 answerChoices[index] = value;
	 // Openjpa doesn't detect that this property has changed if
	 // you simply set one of the array elements.  We must reset
	 // the property itself.
	 // TODO: this might not be necessary if answerChoices is a List
	 String[] ac = new String[] 
	    { answerChoices[0], answerChoices[1], answerChoices[2], answerChoices[3] };
	 answerChoices = ac;
      }
      return index;
   }

   String getAnswer() {
      return answerChoices[answerIndex];
   }

   /**
    * Returns the String representation of this question which can be
    * used by the UI.
    */
   public String toString() {
      if (question == null)
	 return "New question";
      return StringUtil.ellipsis(question, displayLength, true);
   }
}
