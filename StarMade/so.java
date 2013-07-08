/*  1:   */import javax.swing.SwingUtilities;
/*  2:   */
/* 80:   */final class so
/* 81:   */  implements Runnable
/* 82:   */{
/* 83:   */  so(sm paramsm) {}
/* 84:   */  
/* 85:   */  public final void run()
/* 86:   */  {
/* 87:87 */    SwingUtilities.invokeLater(new sp(this));
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     so
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */