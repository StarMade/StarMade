/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import org.dom4j.Comment;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.Visitor;
/*    */ 
/*    */ public abstract class AbstractComment extends AbstractCharacterData
/*    */   implements Comment
/*    */ {
/*    */   public short getNodeType()
/*    */   {
/* 32 */     return 8;
/*    */   }
/*    */ 
/*    */   public String getPath(Element context) {
/* 36 */     Element parent = getParent();
/*    */ 
/* 38 */     return (parent != null) && (parent != context) ? parent.getPath(context) + "/comment()" : "comment()";
/*    */   }
/*    */ 
/*    */   public String getUniquePath(Element context)
/*    */   {
/* 43 */     Element parent = getParent();
/*    */ 
/* 45 */     return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/comment()" : "comment()";
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 50 */     return super.toString() + " [Comment: \"" + getText() + "\"]";
/*    */   }
/*    */ 
/*    */   public String asXML() {
/* 54 */     return "<!--" + getText() + "-->";
/*    */   }
/*    */ 
/*    */   public void write(Writer writer) throws IOException {
/* 58 */     writer.write("<!--");
/* 59 */     writer.write(getText());
/* 60 */     writer.write("-->");
/*    */   }
/*    */ 
/*    */   public void accept(Visitor visitor) {
/* 64 */     visitor.visit(this);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractComment
 * JD-Core Version:    0.6.2
 */