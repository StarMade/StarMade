/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.Entity;
/*    */ import org.dom4j.Visitor;
/*    */ 
/*    */ public abstract class AbstractEntity extends AbstractNode
/*    */   implements Entity
/*    */ {
/*    */   public short getNodeType()
/*    */   {
/* 31 */     return 5;
/*    */   }
/*    */ 
/*    */   public String getPath(Element context)
/*    */   {
/* 36 */     Element parent = getParent();
/*    */ 
/* 38 */     return (parent != null) && (parent != context) ? parent.getPath(context) + "/text()" : "text()";
/*    */   }
/*    */ 
/*    */   public String getUniquePath(Element context)
/*    */   {
/* 44 */     Element parent = getParent();
/*    */ 
/* 46 */     return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/text()" : "text()";
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 51 */     return super.toString() + " [Entity: &" + getName() + ";]";
/*    */   }
/*    */ 
/*    */   public String getStringValue() {
/* 55 */     return "&" + getName() + ";";
/*    */   }
/*    */ 
/*    */   public String asXML() {
/* 59 */     return "&" + getName() + ";";
/*    */   }
/*    */ 
/*    */   public void write(Writer writer) throws IOException {
/* 63 */     writer.write("&");
/* 64 */     writer.write(getName());
/* 65 */     writer.write(";");
/*    */   }
/*    */ 
/*    */   public void accept(Visitor visitor) {
/* 69 */     visitor.visit(this);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractEntity
 * JD-Core Version:    0.6.2
 */