/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.QName;
/*    */ 
/*    */ /** @deprecated */
/*    */ public class ElementQNameIterator extends FilterIterator
/*    */ {
/*    */   private QName qName;
/*    */ 
/*    */   public ElementQNameIterator(Iterator proxy, QName qName)
/*    */   {
/* 31 */     super(proxy);
/* 32 */     this.qName = qName;
/*    */   }
/*    */ 
/*    */   protected boolean matches(Object object)
/*    */   {
/* 45 */     if ((object instanceof Element)) {
/* 46 */       Element element = (Element)object;
/*    */ 
/* 48 */       return this.qName.equals(element.getQName());
/*    */     }
/*    */ 
/* 51 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ElementQNameIterator
 * JD-Core Version:    0.6.2
 */