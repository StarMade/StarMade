/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ 
/*    */ public final class f extends ArrayList
/*    */ {
/*    */   private static final long serialVersionUID = 5564392963896845136L;
/*    */ 
/*    */   public final boolean a(Comparable paramComparable)
/*    */   {
/* 33 */     if (paramComparable == null) {
/* 34 */       throw new NullPointerException("tried to add null " + paramComparable);
/*    */     }
/* 36 */     if (size() == 0)
/* 37 */       return super.add(paramComparable);
/*    */     int i;
/* 44 */     if ((
/* 44 */       i = Collections.binarySearch(this, paramComparable)) >= 0)
/*    */     {
/* 45 */       super.add(i + 1, paramComparable);
/*    */ 
/* 47 */       return true;
/*    */     }
/*    */ 
/* 51 */     super.add(-i - 1, paramComparable);
/*    */ 
/* 53 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     f
 * JD-Core Version:    0.6.2
 */