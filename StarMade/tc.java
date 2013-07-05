/*    */ import java.util.HashMap;
/*    */ 
/*    */ public final class tc extends ti
/*    */ {
/*    */   private long a;
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public tc(wk paramwk)
/*    */   {
/* 19 */     super(paramwk);
/*    */   }
/*    */ 
/*    */   public final boolean c()
/*    */   {
/* 30 */     this.a = System.currentTimeMillis();
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   public final boolean b()
/*    */   {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   public final boolean d()
/*    */   {
/* 43 */     vY localvY = (vY)a();
/* 44 */     q localq = new q();
/* 45 */     for (lE locallE : localvY.a().b().values()) {
/* 46 */       localq.a(locallE.a(), localvY.a());
/* 47 */       if (localq.a() < 6.0F) {
/* 48 */         ((sL)a().a()).a(new q(locallE.a()));
/*    */ 
/* 50 */         localvY.a(locallE);
/*    */ 
/* 54 */         a(new tv());
/* 55 */         return true;
/*    */       }
/*    */     }
/*    */ 
/* 59 */     if (System.currentTimeMillis() - this.a > 60000L) {
/* 60 */       a(new tq());
/* 61 */       return true;
/*    */     }
/*    */ 
/* 64 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tc
 * JD-Core Version:    0.6.2
 */