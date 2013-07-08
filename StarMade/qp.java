/*  1:   */import java.awt.event.KeyAdapter;
/*  2:   */import java.awt.event.KeyEvent;
/*  3:   */
/* 30:   */final class qp
/* 31:   */  extends KeyAdapter
/* 32:   */{
/* 33:   */  qp(qo paramqo) {}
/* 34:   */  
/* 35:   */  public final void keyTyped(KeyEvent paramKeyEvent)
/* 36:   */  {
/* 37:37 */    if ((paramKeyEvent.getKeyCode() == 10) || (paramKeyEvent.getKeyChar() == '\n')) {
/* 38:38 */      qo.a(this.a);
/* 39:   */    }
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */