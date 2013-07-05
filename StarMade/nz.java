/*    */ import org.schema.game.common.facedit.ElementEditorFrame;
/*    */ 
/*    */ public final class nz
/*    */   implements Runnable
/*    */ {
/*    */   public final void run()
/*    */   {
/*    */     try
/*    */     {
/* 69 */       new ElementEditorFrame()
/* 70 */         .setVisible(true);
/*    */       return;
/*    */     }
/*    */     catch (Exception localException2)
/*    */     {
/*    */       Exception localException1;
/* 71 */       (
/* 72 */         localException1 = 
/* 74 */         localException2).printStackTrace();
/* 73 */       d.a(localException1);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nz
 * JD-Core Version:    0.6.2
 */