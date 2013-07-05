/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public abstract class AbstractCharSet extends AbstractCharCollection
/*    */   implements Cloneable, CharSet
/*    */ {
/*    */   public abstract CharIterator iterator();
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
/* 66 */     CharIterator i = iterator();
/*    */ 
/* 68 */     while (n-- != 0) {
/* 69 */       char k = i.nextChar();
/* 70 */       h += k;
/*    */     }
/* 72 */     return h;
/*    */   }
/*    */   public boolean remove(char k) {
/* 75 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public boolean rem(char k)
/*    */   {
/* 83 */     return remove(k);
/*    */   }
/*    */ 
/*    */   public boolean remove(Object o) {
/* 87 */     return remove(((Character)o).charValue());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharSet
 * JD-Core Version:    0.6.2
 */