/*    */ package org.dom4j.util;
/*    */ 
/*    */ import org.dom4j.QName;
/*    */ import org.dom4j.tree.DefaultAttribute;
/*    */ 
/*    */ public class UserDataAttribute extends DefaultAttribute
/*    */ {
/*    */   private Object data;
/*    */ 
/*    */   public UserDataAttribute(QName qname)
/*    */   {
/* 30 */     super(qname);
/*    */   }
/*    */ 
/*    */   public UserDataAttribute(QName qname, String text) {
/* 34 */     super(qname, text);
/*    */   }
/*    */ 
/*    */   public Object getData() {
/* 38 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(Object data) {
/* 42 */     this.data = data;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.UserDataAttribute
 * JD-Core Version:    0.6.2
 */