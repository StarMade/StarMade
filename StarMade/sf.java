/*  1:   */import org.schema.game.common.updater.Launcher;
/*  2:   */
/* 44:   */public final class sf
/* 45:   */  implements Runnable
/* 46:   */{
/* 47:   */  public final void run()
/* 48:   */  {
/* 49:   */    try
/* 50:   */    {
/* 51:51 */      new Launcher().setVisible(true); return;
/* 52:   */    } catch (Exception localException2) { Exception localException1;
/* 53:53 */      (localException1 = 
/* 54:   */      
/* 55:55 */        localException2).printStackTrace();d.a(localException1);
/* 56:   */    }
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */