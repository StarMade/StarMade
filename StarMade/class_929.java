import org.schema.schine.graphicsengine.shader.ErrorDialogException;

final class class_929
  implements Runnable
{
  class_929(class_933 paramclass_933) {}
  
  public final void run()
  {
    try
    {
      Thread.sleep(5000L);
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
    if (!class_933.a6(this.field_1155)) {
      class_933.a2(new ErrorDialogException("Sound initialization failed. Please report the bug, restart the game, and turn sound off in options for a temporal workaround."));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_929
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */