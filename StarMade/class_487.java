import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

final class class_487
  extends WindowAdapter
{
  public final void windowClosed(WindowEvent paramWindowEvent)
  {
    System.err.println("Exiting because of window closed");
    System.exit(0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_487
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */