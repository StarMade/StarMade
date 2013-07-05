/*    */ import javax.swing.JEditorPane;
/*    */ import javax.swing.text.DefaultCaret;
/*    */ import javax.swing.text.Document;
/*    */ import javax.swing.text.html.HTMLEditorKit;
/*    */ import javax.swing.text.html.StyleSheet;
/*    */ 
/*    */ public final class se extends JEditorPane
/*    */ {
/*    */   private static final long serialVersionUID = 6241837723420103078L;
/*    */   private HTMLEditorKit a;
/*    */ 
/*    */   public se()
/*    */   {
/* 32 */     setEditable(false);
/*    */ 
/* 37 */     this.a = new HTMLEditorKit();
/* 38 */     setEditorKit(this.a);
/*    */ 
/* 41 */     (
/* 42 */       localObject = this.a.getStyleSheet())
/* 42 */       .addRule("body {color:#bfbfbf; font-family:Verdana,Arial,sans-serif; margin: 4px; background-color : #292929; }");
/* 43 */     ((StyleSheet)localObject).addRule("h1 {color: #fffeff;}");
/* 44 */     ((StyleSheet)localObject).addRule("h2 {color: #fffeff;}");
/* 45 */     ((StyleSheet)localObject).addRule("pre {font : 10px monaco; color : #3b3b3b; background-color : black; }");
/*    */ 
/* 50 */     Object localObject = this.a.createDefaultDocument();
/* 51 */     setDocument((Document)localObject);
/*    */ 
/* 53 */     ((DefaultCaret)getCaret())
/* 54 */       .setUpdatePolicy(1);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     se
 * JD-Core Version:    0.6.2
 */