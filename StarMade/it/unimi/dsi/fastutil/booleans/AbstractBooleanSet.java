/*    */ package it.unimi.dsi.fastutil.booleans;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public abstract class AbstractBooleanSet extends AbstractBooleanCollection
/*    */   implements Cloneable, BooleanSet
/*    */ {
/*    */   public abstract BooleanIterator iterator();
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
/* 66 */     BooleanIterator i = iterator();
/*    */ 
/* 68 */     while (n-- != 0) {
/* 69 */       boolean k = i.nextBoolean();
/* 70 */       h += (k ? 1231 : 1237);
/*    */     }
/* 72 */     return h;
/*    */   }
/*    */   public boolean remove(boolean k) {
/* 75 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public boolean rem(boolean k)
/*    */   {
/* 83 */     return remove(k);
/*    */   }
/*    */ 
/*    */   public boolean remove(Object o) {
/* 87 */     return remove(((Boolean)o).booleanValue());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanSet
 * JD-Core Version:    0.6.2
 */