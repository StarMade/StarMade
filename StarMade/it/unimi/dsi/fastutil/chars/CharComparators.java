/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ public class CharComparators
/*    */ {
/* 51 */   public static final CharComparator NATURAL_COMPARATOR = new AbstractCharComparator() {
/*    */     public final int compare(char a, char b) {
/* 53 */       return a == b ? 0 : a < b ? -1 : 1;
/*    */     }
/* 51 */   };
/*    */ 
/* 58 */   public static final CharComparator OPPOSITE_COMPARATOR = new AbstractCharComparator() {
/*    */     public final int compare(char a, char b) {
/* 60 */       return b == a ? 0 : b < a ? -1 : 1;
/*    */     }
/* 58 */   };
/*    */ 
/*    */   public static CharComparator oppositeComparator(CharComparator c)
/*    */   {
/* 69 */     return new AbstractCharComparator() {
/* 70 */       private final CharComparator comparator = this.val$c;
/*    */ 
/* 72 */       public final int compare(char a, char b) { return -this.comparator.compare(a, b); }
/*    */ 
/*    */     };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharComparators
 * JD-Core Version:    0.6.2
 */