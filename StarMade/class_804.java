import java.io.IOException;
import org.schema.game.common.crashreporter.CrashReporter;

public final class class_804
  implements Runnable
{
  public class_804(CrashReporter paramCrashReporter) {}
  
  public final void run()
  {
    try
    {
      CrashReporter.a4(this.field_1060);
      return;
    }
    catch (IOException localIOException2)
    {
      IOException localIOException1;
      (localIOException1 = localIOException2).printStackTrace();
      class_29.a13(localIOException1, true);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_804
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */