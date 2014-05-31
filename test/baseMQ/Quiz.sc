/**
 * A Quiz has a name and a list of Questions.
 */
@Entity
@Table(name="quiz")
public class Quiz implements Serializable {
   // Primary key is the quiz name
   @Bindable
   @Id 
   String name;

   // Define a one to many relationship between a quiz and its
   // questions.  The questions will automatically be persisted when the
   // quiz is persisted.
   @Bindable
   @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) 
   List<Question> questions = new ArrayList<Question>();
}
