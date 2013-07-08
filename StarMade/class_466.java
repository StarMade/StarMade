import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import org.schema.game.server.data.admin.AdminCommandIllegalArgument;
import org.schema.game.server.data.admin.AdminCommands;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;

public final class class_466
  extends class_454
  implements Observer
{
  private static final long serialVersionUID = 438885771406304916L;
  private boolean field_158;
  private String jdField_field_162_of_type_JavaLangString;
  private class_371 jdField_field_162_of_type_Class_371;
  
  public class_466(class_981 paramclass_981, String paramString, class_371 paramclass_371)
  {
    super(paramclass_981, paramString, paramclass_371);
    this.jdField_field_162_of_type_Class_371 = paramclass_371;
    this.jdField_field_162_of_type_Boolean = true;
  }
  
  protected final boolean a()
  {
    if (this.field_158)
    {
      this.field_158 = false;
      return true;
    }
    return false;
  }
  
  public final boolean b()
  {
    this.jdField_field_162_of_type_Class_371.a14().a18().a79().a60().a52().deleteObserver(this);
    return super.b();
  }
  
  public final boolean c()
  {
    this.jdField_field_162_of_type_Class_371.a14().a18().a79().a60().a52().addObserver(this);
    this.field_128.a4().a5().field_163 = null;
    if (!this.field_128.a20().getInventory(null).b2())
    {
      System.err.println("[TUTORIAL] ship core does NOT exist");
      this.field_128.a4().b1("WARNING\nYou already used your core!\nYou will be given one more.\n\n(this works if you're an admin)");
      try
      {
        this.field_128.a4().a23(AdminCommands.field_1953, AdminCommands.a2(AdminCommands.field_1953, new String[] { this.field_128.a20().getName(), "1", "1" }));
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
      catch (AdminCommandIllegalArgument localAdminCommandIllegalArgument)
      {
        localAdminCommandIllegalArgument;
      }
    }
    else
    {
      System.err.println("[TUTORIAL] ship core exists");
    }
    return super.c();
  }
  
  public final boolean d()
  {
    if (this.field_128.a4().a5().field_163 != null) {
      this.field_158 = true;
    }
    if (this.jdField_field_162_of_type_JavaLangString != null) {
      synchronized (this.field_128.getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Iterator localIterator = this.field_128.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Sendable localSendable;
          if ((((localSendable = (Sendable)localIterator.next()) instanceof class_747)) && (((class_747)localSendable).getUniqueIdentifier().equals(this.jdField_field_162_of_type_JavaLangString)))
          {
            this.field_128.a4().a5().field_163 = ((class_747)localSendable);
            this.field_128.a14().a18().a79().a60().a56((class_797)localSendable);
            this.jdField_field_162_of_type_JavaLangString = null;
          }
        }
      }
    }
    return super.d();
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    System.err.println("UPDATE BY PLAYER EXTERNAL CONTROLLER " + paramObject);
    if (((paramObservable instanceof class_431)) && (paramObject != null) && ((paramObject instanceof String)) && (!paramObject.equals("ON_SWITCH"))) {
      this.jdField_field_162_of_type_JavaLangString = ((String)paramObject);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_466
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */