import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Random;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.EntityNotFountException;

public final class class_1155
  extends class_1163
{
  private static final long serialVersionUID = 1L;
  
  public class_1155(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final boolean c()
  {
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    Object localObject;
    if ((localObject = (class_1063)a8()).a18().size() > 0)
    {
      try
      {
        (localObject = ((class_1063)localObject).a14((String)((class_1063)localObject).a18().get(0), new class_48())).a(Universe.getRandom().nextInt(20) - 10, Universe.getRandom().nextInt(20) - 10, Universe.getRandom().nextInt(20) - 10);
        ((class_1256)a8().a3()).a10((class_48)localObject);
      }
      catch (EntityNotFountException localEntityNotFountException)
      {
        localEntityNotFountException.printStackTrace();
        a12(new class_1147());
      }
      catch (SQLException localSQLException)
      {
        localSQLException.printStackTrace();
        a12(new class_1147());
      }
      a12(new class_1124());
    }
    else
    {
      System.err.println("[SIM][AI] RETURNING HOME TO (NO MEMBERS) " + ((class_1063)localObject).a20());
      ((class_1256)a8().a3()).a10(new class_48(((class_1063)localObject).a20()));
      a12(new class_1124());
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1155
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */