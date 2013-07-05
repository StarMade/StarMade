/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public class LongComparators
/*    */ {
/* 51 */   public static final LongComparator NATURAL_COMPARATOR = new AbstractLongComparator() {
/*    */     public final int compare(long a, long b) {
/* 53 */       return a == b ? 0 : a < b ? -1 : 1;
/*    */     }
/* 51 */   };
/*    */ 
/* 58 */   public static final LongComparator OPPOSITE_COMPARATOR = new AbstractLongComparator() {
/*    */     public final int compare(long a, long b) {
/* 60 */       return b == a ? 0 : b < a ? -1 : 1;
/*    */     }
/* 58 */   };
/*    */ 
/*    */   public static LongComparator oppositeComparator(LongComparator c)
/*    */   {
/* 69 */     return new AbstractLongComparator() {
/* 70 */       private final LongComparator comparator = this.val$c;
/*    */ 
/* 72 */       public final int compare(long a, long b) { return -this.comparator.compare(a, b); }
/*    */ 
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongComparators
 * JD-Core Version:    0.6.2
 */