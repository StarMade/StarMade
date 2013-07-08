import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import org.schema.game.server.controller.GameServerController;

public final class class_1192
  implements Runnable
{
  public class_1192(GameServerController paramGameServerController, class_1041 paramclass_1041) {}
  
  public final void run()
  {
    System.out.println("[SERVER] SERVER SHUTDOWN. Dumping server State!");
    try
    {
      this.jdField_field_1399_of_type_OrgSchemaGameServerControllerGameServerController.a50(true, this.jdField_field_1399_of_type_Class_1041.a62());
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    catch (SQLException localSQLException)
    {
      localSQLException;
    }
    System.out.println("[SERVER] SERVER SHUTDOWN. ServerState saved!");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1192
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */