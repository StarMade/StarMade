/*  1:   */import java.io.IOException;
/*  2:   */import java.util.LinkedList;
/*  3:   */import org.schema.game.common.controller.SegmentController;
/*  4:   */
/* 30:   */final class jn
/* 31:   */  implements Runnable
/* 32:   */{
/* 33:   */  jn(jm paramjm) {}
/* 34:   */  
/* 35:   */  public final void run()
/* 36:   */  {
/* 37:   */    try
/* 38:   */    {
/* 39:   */      for (;;)
/* 40:   */      {
/* 41:   */        label0:
/* 42:42 */        mw localmw1 = (mw)this.a.a.a(); Object localObject1 = this.a; if (localmw1.f()) synchronized ((localObject1 = ((jm)localObject1).a).a) { ((je)localObject1).a.add(localmw1);((je)localObject1).a.notify(); } localmw2.a().getSegmentProvider().e(localmw2);
/* 43:43 */      } } catch (InterruptedException localInterruptedException) { break label0; } catch (IOException localIOException) { 
/* 44:   */      
/* 45:45 */        localIOException;
/* 46:   */    }
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */