/*   1:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*   2:    */
/* 689:    */final class xo
/* 690:    */  implements Runnable
/* 691:    */{
/* 692:    */  xo(xm paramxm) {}
/* 693:    */  
/* 694:    */  public final void run()
/* 695:    */  {
/* 696:    */    try
/* 697:    */    {
/* 698:698 */      Thread.sleep(5000L);
/* 699:699 */    } catch (InterruptedException localInterruptedException) { 
/* 700:    */      
/* 701:701 */        localInterruptedException;
/* 702:    */    }
/* 703:    */    
/* 704:702 */    if (!xm.a(this.a)) {
/* 705:703 */      xm.a(new ErrorDialogException("Sound initialization failed. Please report the bug, restart the game, and turn sound off in options for a temporal workaround."));
/* 706:    */    }
/* 707:    */  }
/* 708:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */