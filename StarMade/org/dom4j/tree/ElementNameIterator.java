/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ /** @deprecated */
/*    */ public class ElementNameIterator extends FilterIterator
/*    */ {
/*    */   private String name;
/*    */ 
/*    */   public ElementNameIterator(Iterator proxy, String name)
/*    */   {
/* 30 */     super(proxy);
/* 31 */     this.name = name;
/*    */   }
/*    */ 
/*    */   protected boolean matches(Object object)
/*    */   {
/* 44 */     if ((object instanceof Element)) {
/* 45 */       Element element = (Element)object;
/*    */ 
/* 47 */       return this.name.equals(element.getName());
/*    */     }
/*    */ 
/* 50 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ElementNameIterator
 * JD-Core Version:    0.6.2
 */