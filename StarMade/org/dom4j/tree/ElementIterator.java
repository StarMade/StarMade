/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ /** @deprecated */
/*    */ public class ElementIterator extends FilterIterator
/*    */ {
/*    */   public ElementIterator(Iterator proxy)
/*    */   {
/* 27 */     super(proxy);
/*    */   }
/*    */ 
/*    */   protected boolean matches(Object element)
/*    */   {
/* 40 */     return element instanceof Element;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ElementIterator
 * JD-Core Version:    0.6.2
 */