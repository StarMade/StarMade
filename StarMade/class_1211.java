import javax.swing.JEditorPane;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public final class class_1211
  extends JEditorPane
{
  private static final long serialVersionUID = 6241837723420103078L;
  private HTMLEditorKit field_1426;
  
  public class_1211()
  {
    setEditable(false);
    this.field_1426 = new HTMLEditorKit();
    setEditorKit(this.field_1426);
    (localObject = this.field_1426.getStyleSheet()).addRule("body {color:#bfbfbf; font-family:Verdana,Arial,sans-serif; margin: 4px; background-color : #292929; }");
    ((StyleSheet)localObject).addRule("h1 {color: #fffeff;}");
    ((StyleSheet)localObject).addRule("h2 {color: #fffeff;}");
    ((StyleSheet)localObject).addRule("pre {font : 10px monaco; color : #3b3b3b; background-color : black; }");
    Object localObject = this.field_1426.createDefaultDocument();
    setDocument((Document)localObject);
    ((DefaultCaret)getCaret()).setUpdatePolicy(1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1211
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */