/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public class IntComparators
/*    */ {
/* 51 */   public static final IntComparator NATURAL_COMPARATOR = new AbstractIntComparator() {
/*    */     public final int compare(int a, int b) {
/* 53 */       return a == b ? 0 : a < b ? -1 : 1;
/*    */     }
/* 51 */   };
/*    */ 
/* 58 */   public static final IntComparator OPPOSITE_COMPARATOR = new AbstractIntComparator() {
/*    */     public final int compare(int a, int b) {
/* 60 */       return b == a ? 0 : b < a ? -1 : 1;
/*    */     }
/* 58 */   };
/*    */ 
/*    */   public static IntComparator oppositeComparator(IntComparator c)
/*    */   {
/* 69 */     return new AbstractIntComparator() {
/* 70 */       private final IntComparator comparator = this.val$c;
/*    */ 
/* 72 */       public final int compare(int a, int b) { return -this.comparator.compare(a, b); }
/*    */ 
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntComparators
 * JD-Core Version:    0.6.2
 */