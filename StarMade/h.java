/*    */ import java.util.HashMap;
/*    */ 
/*    */ public final class h
/*    */ {
/*  6 */   public static HashMap a = new HashMap();
/*    */ 
/*    */   public static void a(String paramString) {
/*  9 */     a.put(paramString, Long.valueOf(System.currentTimeMillis()));
/*    */   }
/*    */   public static void b(String paramString) {
/* 12 */     a.put(paramString, Long.valueOf(System.currentTimeMillis() - ((Long)a.get(paramString)).longValue()));
/*    */   }
/*    */ 
/*    */   public static long a(String paramString) {
/* 16 */     if ((
/* 16 */       paramString = (Long)a.get(paramString)) != null)
/* 16 */       return paramString.longValue(); return -1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     h
 * JD-Core Version:    0.6.2
 */