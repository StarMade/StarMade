/*    */ package org.jaxen.util;
/*    */ 
/*    */ import java.util.AbstractList;
/*    */ 
/*    */ public class SingletonList extends AbstractList
/*    */ {
/*    */   private final Object element;
/*    */ 
/*    */   public SingletonList(Object element)
/*    */   {
/* 74 */     this.element = element;
/*    */   }
/*    */ 
/*    */   public int size()
/*    */   {
/* 83 */     return 1;
/*    */   }
/*    */ 
/*    */   public Object get(int index)
/*    */   {
/* 95 */     if (index == 0) {
/* 96 */       return this.element;
/*    */     }
/* 98 */     throw new IndexOutOfBoundsException(index + " != 0");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.SingletonList
 * JD-Core Version:    0.6.2
 */