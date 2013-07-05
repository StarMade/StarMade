/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.StringWriter;
/*    */ import java.io.Writer;
/*    */ import org.dom4j.CDATA;
/*    */ import org.dom4j.Visitor;
/*    */ 
/*    */ public abstract class AbstractCDATA extends AbstractCharacterData
/*    */   implements CDATA
/*    */ {
/*    */   public short getNodeType()
/*    */   {
/* 32 */     return 4;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 36 */     return super.toString() + " [CDATA: \"" + getText() + "\"]";
/*    */   }
/*    */ 
/*    */   public String asXML() {
/* 40 */     StringWriter writer = new StringWriter();
/*    */     try
/*    */     {
/* 43 */       write(writer);
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/*    */     }
/* 48 */     return writer.toString();
/*    */   }
/*    */ 
/*    */   public void write(Writer writer) throws IOException {
/* 52 */     writer.write("<![CDATA[");
/*    */ 
/* 54 */     if (getText() != null) {
/* 55 */       writer.write(getText());
/*    */     }
/*    */ 
/* 58 */     writer.write("]]>");
/*    */   }
/*    */ 
/*    */   public void accept(Visitor visitor) {
/* 62 */     visitor.visit(this);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractCDATA
 * JD-Core Version:    0.6.2
 */