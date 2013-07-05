/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ public class FloatComparators
/*    */ {
/* 51 */   public static final FloatComparator NATURAL_COMPARATOR = new AbstractFloatComparator() {
/*    */     public final int compare(float a, float b) {
/* 53 */       return a == b ? 0 : a < b ? -1 : 1;
/*    */     }
/* 51 */   };
/*    */ 
/* 58 */   public static final FloatComparator OPPOSITE_COMPARATOR = new AbstractFloatComparator() {
/*    */     public final int compare(float a, float b) {
/* 60 */       return b == a ? 0 : b < a ? -1 : 1;
/*    */     }
/* 58 */   };
/*    */ 
/*    */   public static FloatComparator oppositeComparator(FloatComparator c)
/*    */   {
/* 69 */     return new AbstractFloatComparator() {
/* 70 */       private final FloatComparator comparator = this.val$c;
/*    */ 
/* 72 */       public final int compare(float a, float b) { return -this.comparator.compare(a, b); }
/*    */ 
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatComparators
 * JD-Core Version:    0.6.2
 */