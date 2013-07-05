/*    */ package org.jaxen.javabean;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ElementIterator
/*    */   implements Iterator
/*    */ {
/*    */   private Element parent;
/*    */   private String name;
/*    */   private Iterator iterator;
/*    */ 
/*    */   public ElementIterator(Element parent, String name, Iterator iterator)
/*    */   {
/* 16 */     this.parent = parent;
/* 17 */     this.name = name;
/* 18 */     this.iterator = iterator;
/*    */   }
/*    */ 
/*    */   public boolean hasNext()
/*    */   {
/* 23 */     return this.iterator.hasNext();
/*    */   }
/*    */ 
/*    */   public Object next()
/*    */   {
/* 28 */     return new Element(this.parent, this.name, this.iterator.next());
/*    */   }
/*    */ 
/*    */   public void remove()
/*    */   {
/* 35 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.ElementIterator
 * JD-Core Version:    0.6.2
 */