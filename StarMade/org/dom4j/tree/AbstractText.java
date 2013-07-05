/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import org.dom4j.Text;
/*    */ import org.dom4j.Visitor;
/*    */ 
/*    */ public abstract class AbstractText extends AbstractCharacterData
/*    */   implements Text
/*    */ {
/*    */   public short getNodeType()
/*    */   {
/* 30 */     return 3;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 34 */     return super.toString() + " [Text: \"" + getText() + "\"]";
/*    */   }
/*    */ 
/*    */   public String asXML() {
/* 38 */     return getText();
/*    */   }
/*    */ 
/*    */   public void write(Writer writer) throws IOException {
/* 42 */     writer.write(getText());
/*    */   }
/*    */ 
/*    */   public void accept(Visitor visitor) {
/* 46 */     visitor.visit(this);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractText
 * JD-Core Version:    0.6.2
 */