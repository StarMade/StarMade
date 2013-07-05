/*    */ import org.schema.game.common.updater.Launcher;
/*    */ 
/*    */ public final class sf
/*    */   implements Runnable
/*    */ {
/*    */   public final void run()
/*    */   {
/*    */     try
/*    */     {
/* 50 */       new Launcher()
/* 51 */         .setVisible(true);
/*    */       return;
/*    */     }
/*    */     catch (Exception localException2)
/*    */     {
/*    */       Exception localException1;
/* 52 */       (
/* 53 */         localException1 = 
/* 55 */         localException2).printStackTrace();
/* 54 */       d.a(localException1);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sf
 * JD-Core Version:    0.6.2
 */