/*  1:   */import javax.swing.JEditorPane;
/*  2:   */import javax.swing.text.DefaultCaret;
/*  3:   */import javax.swing.text.Document;
/*  4:   */import javax.swing.text.html.HTMLEditorKit;
/*  5:   */import javax.swing.text.html.StyleSheet;
/*  6:   */
/* 24:   */public final class se
/* 25:   */  extends JEditorPane
/* 26:   */{
/* 27:   */  private static final long serialVersionUID = 6241837723420103078L;
/* 28:   */  private HTMLEditorKit a;
/* 29:   */  
/* 30:   */  public se()
/* 31:   */  {
/* 32:32 */    setEditable(false);
/* 33:   */    
/* 37:37 */    this.a = new HTMLEditorKit();
/* 38:38 */    setEditorKit(this.a);
/* 39:   */    
/* 41:41 */    (
/* 42:42 */      localObject = this.a.getStyleSheet()).addRule("body {color:#bfbfbf; font-family:Verdana,Arial,sans-serif; margin: 4px; background-color : #292929; }");
/* 43:43 */    ((StyleSheet)localObject).addRule("h1 {color: #fffeff;}");
/* 44:44 */    ((StyleSheet)localObject).addRule("h2 {color: #fffeff;}");
/* 45:45 */    ((StyleSheet)localObject).addRule("pre {font : 10px monaco; color : #3b3b3b; background-color : black; }");
/* 46:   */    
/* 50:50 */    Object localObject = this.a.createDefaultDocument();
/* 51:51 */    setDocument((Document)localObject);
/* 52:   */    
/* 53:53 */    ((DefaultCaret)getCaret())
/* 54:54 */      .setUpdatePolicy(1);
/* 55:   */  }
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     se
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */