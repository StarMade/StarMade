/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.HashCommon;
/*    */ import java.util.Set;
/*    */ 
/*    */ public abstract class AbstractFloatSet extends AbstractFloatCollection
/*    */   implements Cloneable, FloatSet
/*    */ {
/*    */   public abstract FloatIterator iterator();
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
/* 66 */     FloatIterator i = iterator();
/*    */ 
/* 68 */     while (n-- != 0) {
/* 69 */       float k = i.nextFloat();
/* 70 */       h += HashCommon.float2int(k);
/*    */     }
/* 72 */     return h;
/*    */   }
/*    */   public boolean remove(float k) {
/* 75 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public boolean rem(float k)
/*    */   {
/* 83 */     return remove(k);
/*    */   }
/*    */ 
/*    */   public boolean remove(Object o) {
/* 87 */     return remove(((Float)o).floatValue());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatSet
 * JD-Core Version:    0.6.2
 */