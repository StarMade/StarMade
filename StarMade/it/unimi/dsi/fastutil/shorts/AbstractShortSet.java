/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public abstract class AbstractShortSet extends AbstractShortCollection
/*    */   implements Cloneable, ShortSet
/*    */ {
/*    */   public abstract ShortIterator iterator();
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
/* 66 */     ShortIterator i = iterator();
/*    */ 
/* 68 */     while (n-- != 0) {
/* 69 */       short k = i.nextShort();
/* 70 */       h += k;
/*    */     }
/* 72 */     return h;
/*    */   }
/*    */   public boolean remove(short k) {
/* 75 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public boolean rem(short k)
/*    */   {
/* 83 */     return remove(k);
/*    */   }
/*    */ 
/*    */   public boolean remove(Object o) {
/* 87 */     return remove(((Short)o).shortValue());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortSet
 * JD-Core Version:    0.6.2
 */