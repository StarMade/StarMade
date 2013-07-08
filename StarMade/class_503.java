import org.schema.game.common.facedit.ElementEditorFrame;

public final class class_503
  implements Runnable
{
  public final void run()
  {
    try
    {
      new ElementEditorFrame().setVisible(true);
      return;
    }
    catch (Exception localException2)
    {
      Exception localException1;
      (localException1 = localException2).printStackTrace();
      class_29.a12(localException1);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_503
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */