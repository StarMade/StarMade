/*    */ import java.awt.event.KeyAdapter;
/*    */ import java.awt.event.KeyEvent;
/*    */ 
/*    */ final class qp extends KeyAdapter
/*    */ {
/*    */   qp(qo paramqo)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void keyTyped(KeyEvent paramKeyEvent)
/*    */   {
/* 37 */     if ((paramKeyEvent.getKeyCode() == 10) || (paramKeyEvent.getKeyChar() == '\n'))
/* 38 */       qo.a(this.a);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qp
 * JD-Core Version:    0.6.2
 */