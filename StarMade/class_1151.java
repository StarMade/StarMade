import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.sql.SQLException;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.EntityNotFountException;

public final class class_1151
  extends class_1163
{
  private static final long serialVersionUID = 1L;
  private long field_128;
  
  public class_1151(class_981 paramclass_981)
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
    class_1256 localclass_1256;
    if ((localclass_1256 = (class_1256)a8().a3()).a9() == null)
    {
      a12(new class_1115());
      return true;
    }
    class_48 localclass_481 = new class_48();
    for (int i = 0; i < ((class_1063)a8()).a18().size(); i++) {
      try
      {
        ((class_1063)a8()).a14((String)((class_1063)a8()).a18().get(i), localclass_481);
        if (localclass_481.equals(localclass_1256.a9()))
        {
          a12(new class_1212());
          return true;
        }
      }
      catch (EntityNotFountException localEntityNotFountException1)
      {
        localEntityNotFountException1;
      }
      catch (SQLException localSQLException1)
      {
        localSQLException1;
      }
    }
    if (System.currentTimeMillis() - this.field_128 > 1000L)
    {
      boolean bool2 = ((class_1063)a8()).a19().a69().a19(localclass_1256.a9());
      for (int j = 0; j < ((class_1063)a8()).a18().size(); j++)
      {
        boolean bool1;
        if (((class_1063)a8()).a19().a62().isSectorLoaded(localclass_1256.a9()))
        {
          bool1 = ((class_1063)a8()).a12((String)((class_1063)a8()).a18().get(j), localclass_1256.a9());
        }
        else if (bool2)
        {
          bool1 = true;
          new class_48();
          class_48 localclass_482 = org.schema.game.common.data.element.Element.DIRECTIONSi[Universe.getRandom().nextInt(org.schema.game.common.data.element.Element.DIRECTIONSi.length)];
          try
          {
            class_48 localclass_483;
            (localclass_483 = ((class_1063)a8()).a14((String)((class_1063)a8()).a18().get(j), new class_48())).a1(localclass_482);
            System.err.println("[MOVING TO SECTOR] Position occupied for " + (String)((class_1063)a8()).a18().get(j));
            ((class_1063)a8()).a12((String)((class_1063)a8()).a18().get(j), localclass_483);
          }
          catch (EntityNotFountException localEntityNotFountException2)
          {
            localEntityNotFountException2;
          }
          catch (SQLException localSQLException2)
          {
            localSQLException2;
          }
          catch (Exception localException)
          {
            localException;
          }
        }
        else
        {
          bool1 = ((class_1063)a8()).a12((String)((class_1063)a8()).a18().get(j), localclass_1256.a9());
        }
        if (!bool1)
        {
          System.err.println("[SIMULATION] Exception while moving entity: REMOVING FROM MEMBERS: " + (String)((class_1063)a8()).a18().get(j));
          ((class_1063)a8()).a18().remove(j);
          j--;
        }
        this.field_128 = System.currentTimeMillis();
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1151
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */