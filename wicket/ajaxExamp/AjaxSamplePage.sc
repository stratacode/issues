
public class AjaxSamplePage extends WebPage
{
  String labelText = "First Item";
  public String getLabelText(){return labelText;}
  public void setLabelText(String labelText){this.labelText = labelText;}

  public AjaxSamplePage(){
    final Label label = new Label("firstName", new PropertyModel(this, "labelText"));
    add(label);

    TextArea textArea = new TextArea("firstValue");
    add(textArea);

    IBehavior behavior =  new AjaxEventBehavior("onKeyUp"){
	protected void onEvent( AjaxRequestTarget target ) {
          setLabelText("Hmmm"); 
	  label.setOutputMarkupId(true);
        }
      }; 
 
    textArea.add(
      behavior
    );
  }
}
