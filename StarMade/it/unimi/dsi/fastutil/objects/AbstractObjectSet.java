/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public abstract class AbstractObjectSet<K> extends AbstractObjectCollection<K>
/*    */   implements Cloneable, ObjectSet<K>
/*    */ {
/*    */   public abstract ObjectIterator<K> iterator();
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 50 */     if (o == this) return true;
/* 51 */     if (!(o instanceof Set)) return false;
/* 52 */     Set s = (Set)o;
/* 53 */     if (s.size() != size()) return false;
/* 54 */     return containsAll(s);
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 64 */     int h = 0; int n = size();
/* 65 */     ObjectIterator i = iterator();
/*    */ 
/* 67 */     while (n-- != 0) {
/* 68 */       Object k = i.next();
/* 69 */       h += (k == null ? 0 : k.hashCode());
/*    */     }
/* 71 */     return h;
/*    */   }
/*    */   public boolean remove(Object k) {
/* 74 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectSet
 * JD-Core Version:    0.6.2
 */