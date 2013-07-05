/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public abstract class AbstractLongComparator
/*    */   implements LongComparator
/*    */ {
/*    */   public int compare(Long ok1, Long ok2)
/*    */   {
/* 57 */     return compare(ok1.longValue(), ok2.longValue());
/*    */   }
/*    */ 
/*    */   public abstract int compare(long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongComparator
 * JD-Core Version:    0.6.2
 */