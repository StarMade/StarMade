/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ final class so
/*    */   implements Runnable
/*    */ {
/*    */   so(sm paramsm)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void run()
/*    */   {
/* 87 */     SwingUtilities.invokeLater(new sp(this));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     so
 * JD-Core Version:    0.6.2
 */