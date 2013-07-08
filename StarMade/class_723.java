import java.io.IOException;
import java.util.LinkedList;
import org.schema.game.common.controller.SegmentController;

final class class_723
  implements Runnable
{
  class_723(class_729 paramclass_729) {}
  
  public final void run()
  {
    try
    {
      for (;;)
      {
        label0:
        class_672 localclass_6721 = (class_672)this.field_996.field_1006.a1();
        Object localObject1 = this.field_996;
        if (localclass_6721.f()) {
          synchronized ((localObject1 = ((class_729)localObject1).field_1006).field_1128)
          {
            ((class_901)localObject1).field_1128.add(localclass_6721);
            ((class_901)localObject1).field_1128.notify();
          }
        }
        localclass_6722.a15().getSegmentProvider().e1(localclass_6722);
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      break label0;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_723
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */