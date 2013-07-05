/*    */ package org.jaxen.javabean;
/*    */ 
/*    */ public class Element
/*    */ {
/*    */   private Element parent;
/*    */   private String name;
/*    */   private Object object;
/*    */ 
/*    */   public Element(Element parent, String name, Object object)
/*    */   {
/* 13 */     this.parent = parent;
/* 14 */     this.name = name;
/* 15 */     this.object = object;
/*    */   }
/*    */ 
/*    */   public Element getParent()
/*    */   {
/* 20 */     return this.parent;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 25 */     return this.name;
/*    */   }
/*    */ 
/*    */   public Object getObject()
/*    */   {
/* 30 */     return this.object;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.Element
 * JD-Core Version:    0.6.2
 */