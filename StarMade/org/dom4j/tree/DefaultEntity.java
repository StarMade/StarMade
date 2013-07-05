/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class DefaultEntity extends FlyweightEntity
/*    */ {
/*    */   private Element parent;
/*    */ 
/*    */   public DefaultEntity(String name)
/*    */   {
/* 33 */     super(name);
/*    */   }
/*    */ 
/*    */   public DefaultEntity(String name, String text)
/*    */   {
/* 45 */     super(name, text);
/*    */   }
/*    */ 
/*    */   public DefaultEntity(Element parent, String name, String text)
/*    */   {
/* 59 */     super(name, text);
/* 60 */     this.parent = parent;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 64 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public void setText(String text) {
/* 68 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public Element getParent() {
/* 72 */     return this.parent;
/*    */   }
/*    */ 
/*    */   public void setParent(Element parent) {
/* 76 */     this.parent = parent;
/*    */   }
/*    */ 
/*    */   public boolean supportsParent() {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean isReadOnly() {
/* 84 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultEntity
 * JD-Core Version:    0.6.2
 */