import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.Arrays;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.exception.DisconnectException;

final class class_927
  implements Runnable
{
  class_927(String[] paramArrayOfString) {}
  
  public final void run()
  {
    try
    {
      localObject1 = "";
      for (int i = 0; i < this.field_1154.length; i++) {
        localObject1 = (String)localObject1 + " " + this.field_1154[i];
      }
      Object localObject2 = "./CrashAndBugReport.jar";
      localObject2 = new File((String)localObject2);
      localObject1 = new String[] { "java", "-jar", ((File)localObject2).getAbsolutePath(), localObject1 };
      System.err.println("[CRASHREPORTER] RUNNING COMMAND: " + Arrays.toString((Object[])localObject1));
      (localObject1 = new ProcessBuilder((String[])localObject1)).environment();
      ((ProcessBuilder)localObject1).start();
      System.err.println("Exiting because of starting crash report");
      if (class_933.field_1156 != null) {
        class_933.field_1156.exit();
      }
      return;
    }
    catch (IOException localIOException)
    {
      Object localObject1;
      (localObject1 = localIOException).printStackTrace();
      if ((localObject1 instanceof SocketException))
      {
        class_933.a2(new DisconnectException("You have been disconnected from the Server (either connection problem or server crash)\nActualException: " + localObject1.getClass().getSimpleName()));
        return;
      }
      class_933.a2((Exception)localObject1);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_927
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */