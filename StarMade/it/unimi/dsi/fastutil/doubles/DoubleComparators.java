/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public class DoubleComparators
/*    */ {
/* 51 */   public static final DoubleComparator NATURAL_COMPARATOR = new AbstractDoubleComparator() {
/*    */     public final int compare(double a, double b) {
/* 53 */       return a == b ? 0 : a < b ? -1 : 1;
/*    */     }
/* 51 */   };
/*    */ 
/* 58 */   public static final DoubleComparator OPPOSITE_COMPARATOR = new AbstractDoubleComparator() {
/*    */     public final int compare(double a, double b) {
/* 60 */       return b == a ? 0 : b < a ? -1 : 1;
/*    */     }
/* 58 */   };
/*    */ 
/*    */   public static DoubleComparator oppositeComparator(DoubleComparator c)
/*    */   {
/* 69 */     return new AbstractDoubleComparator() {
/* 70 */       private final DoubleComparator comparator = this.val$c;
/*    */ 
/* 72 */       public final int compare(double a, double b) { return -this.comparator.compare(a, b); }
/*    */ 
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleComparators
 * JD-Core Version:    0.6.2
 */