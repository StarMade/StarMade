import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;
import org.schema.game.common.Starter;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.exception.ServerPortNotAvailableException;
import org.schema.schine.network.server.ServerController;

public final class jl
  implements Runnable
{
  public jl(boolean paramBoolean)
  {
  }

  public final void run()
  {
    while (true)
    {
      System.err.println("[SERVER] initializing ");

      xe.a(new cM());
      vg localvg = null;
      try {
        localvg = new vg(); } catch (SQLException localSQLException) {
        (
          localObject6 = 
          localSQLException).printStackTrace();
        d.a((Exception)localObject6);
        System.exit(-1);
      }
      Object localObject6 = new GameServerController(localvg);
      pE localpE = null;
      int i = 0;
      if (this.jdField_a_of_type_Boolean) {
        (
          localpE = new pE((ServerController)localObject6))
          .setVisible(true);
      }
      Starter.c = false;
      try {
        ((GameServerController)localObject6).startServerAndListen();

        while (!((GameServerController)localObject6).isListenting()) {
          try {
            Thread.sleep(30L);
          }
          catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
          }
        }

        Starter.c = true;
        File localFile;
        if ((
          localFile = new File("./debugPlayerLock.lock"))
          .exists()) {
          localFile.delete();
        }
        localFile.createNewFile();
        localObject6 = new DataOutputStream(new FileOutputStream(localFile));
        synchronized (localvg.getClients()) {
          ((DataOutputStream)localObject6).writeInt(localvg.getClients().size());
        }
        ((DataOutputStream)localObject6).close();

        Starter.b = true;

        synchronized (Starter.jdField_a_of_type_JavaLangObject) {
          Starter.jdField_a_of_type_JavaLangObject.notify();
          return;
        }
      }
      catch (Exception localFile)
      {
        if (localpE != null) {
          localpE.setVisible(false);
        }
        if ((??? instanceof ServerPortNotAvailableException))
        {
          if ((
            localObject6 = (ServerPortNotAvailableException)???)
            .isInstanceRunning()) {
            System.out.println("NOT STARTING SERVER. A StarMade Server Instance is already running on port " + ServerController.port + " DEDICATED: " + Starter.jdField_a_of_type_Boolean);
            if (Starter.jdField_a_of_type_Boolean) {
              System.err.println("HANDLING EXCEPTION NOW");
              Starter.c("A StarMade Server Instance is already running on port " + ServerController.port); break label376;
            }
          } else {
            ((ServerPortNotAvailableException)localObject6).printStackTrace();
            System.out.println("[ERROR] Some other program is blocking port " + ServerController.port + ". Please end that program or choose another port for starmade");
            Starter.a("");
            i = 1;
          }
        }
        else ((Exception)???).printStackTrace();

        label376: if (i == 0) {
          Starter.b = true;
          synchronized (Starter.jdField_a_of_type_JavaLangObject) {
            Starter.jdField_a_of_type_JavaLangObject.notify();
            return;
          }
        }
      } finally {
        if (i == 0)
        {
          Starter.b = true;
          synchronized (Starter.jdField_a_of_type_JavaLangObject) {
            Starter.jdField_a_of_type_JavaLangObject.notify();
          }
        }
        run();
      }
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jl
 * JD-Core Version:    0.6.2
 */