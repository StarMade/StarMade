/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ public class ShortComparators
/*    */ {
/* 51 */   public static final ShortComparator NATURAL_COMPARATOR = new AbstractShortComparator() {
/*    */     public final int compare(short a, short b) {
/* 53 */       return a == b ? 0 : a < b ? -1 : 1;
/*    */     }
/* 51 */   };
/*    */ 
/* 58 */   public static final ShortComparator OPPOSITE_COMPARATOR = new AbstractShortComparator() {
/*    */     public final int compare(short a, short b) {
/* 60 */       return b == a ? 0 : b < a ? -1 : 1;
/*    */     }
/* 58 */   };
/*    */ 
/*    */   public static ShortComparator oppositeComparator(ShortComparator c)
/*    */   {
/* 69 */     return new AbstractShortComparator() {
/* 70 */       private final ShortComparator comparator = this.val$c;
/*    */ 
/* 72 */       public final int compare(short a, short b) { return -this.comparator.compare(a, b); }
/*    */ 
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortComparators
 * JD-Core Version:    0.6.2
 */