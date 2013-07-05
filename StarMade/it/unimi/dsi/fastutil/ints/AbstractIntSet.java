/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public abstract class AbstractIntSet extends AbstractIntCollection
/*    */   implements Cloneable, IntSet
/*    */ {
/*    */   public abstract IntIterator iterator();
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 51 */     if (o == this) return true;
/* 52 */     if (!(o instanceof Set)) return false;
/* 53 */     Set s = (Set)o;
/* 54 */     if (s.size() != size()) return false;
/* 55 */     return containsAll(s);
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 65 */     int h = 0; int n = size();
/* 66 */     IntIterator i = iterator();
/*    */ 
/* 68 */     while (n-- != 0) {
/* 69 */       int k = i.nextInt();
/* 70 */       h += k;
/*    */     }
/* 72 */     return h;
/*    */   }
/*    */   public boolean remove(int k) {
/* 75 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public boolean rem(int k)
/*    */   {
/* 83 */     return remove(k);
/*    */   }
/*    */ 
/*    */   public boolean remove(Object o) {
/* 87 */     return remove(((Integer)o).intValue());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntSet
 * JD-Core Version:    0.6.2
 */