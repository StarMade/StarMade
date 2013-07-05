/*    */ package org.dom4j.util;
/*    */ 
/*    */ import org.dom4j.DocumentFactory;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.QName;
/*    */ import org.dom4j.tree.DefaultElement;
/*    */ 
/*    */ public class UserDataElement extends DefaultElement
/*    */ {
/*    */   private Object data;
/*    */ 
/*    */   public UserDataElement(String name)
/*    */   {
/* 31 */     super(name);
/*    */   }
/*    */ 
/*    */   public UserDataElement(QName qname) {
/* 35 */     super(qname);
/*    */   }
/*    */ 
/*    */   public Object getData() {
/* 39 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(Object data) {
/* 43 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 47 */     return super.toString() + " userData: " + this.data;
/*    */   }
/*    */ 
/*    */   public Object clone() {
/* 51 */     UserDataElement answer = (UserDataElement)super.clone();
/*    */ 
/* 53 */     if (answer != this) {
/* 54 */       answer.data = getCopyOfUserData();
/*    */     }
/*    */ 
/* 57 */     return answer;
/*    */   }
/*    */ 
/*    */   protected Object getCopyOfUserData()
/*    */   {
/* 71 */     return this.data;
/*    */   }
/*    */ 
/*    */   protected Element createElement(String name) {
/* 75 */     Element answer = getDocumentFactory().createElement(name);
/* 76 */     answer.setData(getCopyOfUserData());
/*    */ 
/* 78 */     return answer;
/*    */   }
/*    */ 
/*    */   protected Element createElement(QName qName) {
/* 82 */     Element answer = getDocumentFactory().createElement(qName);
/* 83 */     answer.setData(getCopyOfUserData());
/*    */ 
/* 85 */     return answer;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.UserDataElement
 * JD-Core Version:    0.6.2
 */