/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public final class td extends ti
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private long a;
/*    */ 
/*    */   public td(wk paramwk)
/*    */   {
/* 17 */     super(paramwk);
/*    */   }
/*    */ 
/*    */   public final boolean c()
/*    */   {
/* 22 */     this.a = System.currentTimeMillis();
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   public final boolean b()
/*    */   {
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   public final boolean d()
/*    */   {
/* 34 */     int i = 1;
/* 35 */     String str = "";
/* 36 */     for (int j = 0; j < ((vY)a()).a().size(); j++) {
/* 37 */       if (((vY)a()).a(j)) {
/* 38 */         i = 0;
/* 39 */         str = str + (String)((vY)a()).a().get(j) + "; ";
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 44 */     if (i != 0) {
/* 45 */       ((vY)a()).a().a().a((vY)a());
/*    */ 
/* 47 */       a().d();
/*    */     } else {
/* 49 */       System.err.println("[AI] DISBANDING: Waiting for all to unload. Still loaded: " + str);
/* 50 */       if (System.currentTimeMillis() - this.a > 240000L) {
/* 51 */         a(new tG());
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 56 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     td
 * JD-Core Version:    0.6.2
 */