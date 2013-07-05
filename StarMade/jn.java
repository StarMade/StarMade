import java.io.IOException;
import java.util.LinkedList;
import org.schema.game.common.controller.SegmentController;

final class jn
  implements Runnable
{
  jn(jm paramjm)
  {
  }

  public final void run()
  {
    while (true)
      try
      {
        mw localmw1 = (mw)this.a.a.a(); Object localObject1 = this.a; if (localmw1.f()) synchronized ((localObject1 = ((jm)localObject1).a).a) { ((je)localObject1).a.add(localmw1); ((je)localObject1).a.notify(); } localmw2.a().getSegmentProvider().e(localmw2);
      } catch (InterruptedException localInterruptedException) {
      } catch (IOException localIOException) {
        localIOException.printStackTrace();
      }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jn
 * JD-Core Version:    0.6.2
 */