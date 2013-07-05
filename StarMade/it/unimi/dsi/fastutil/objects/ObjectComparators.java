/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class ObjectComparators
/*    */ {
/* 51 */   public static final Comparator NATURAL_COMPARATOR = new Comparator() {
/*    */     public final int compare(Object a, Object b) {
/* 53 */       return ((Comparable)a).compareTo(b);
/*    */     }
/* 51 */   };
/*    */ 
/* 58 */   public static final Comparator OPPOSITE_COMPARATOR = new Comparator() {
/*    */     public final int compare(Object a, Object b) {
/* 60 */       return ((Comparable)b).compareTo(a);
/*    */     }
/* 58 */   };
/*    */ 
/*    */   public static <K> Comparator<K> oppositeComparator(Comparator<K> c)
/*    */   {
/* 69 */     return new Comparator() {
/* 70 */       private final Comparator<K> comparator = this.val$c;
/*    */ 
/* 72 */       public final int compare(K a, K b) { return -this.comparator.compare(a, b); }
/*    */ 
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectComparators
 * JD-Core Version:    0.6.2
 */