import java.io.IOException;
import org.schema.game.server.data.admin.AdminCommands;

final class class_1241
  extends class_1288
{
  private static final long serialVersionUID = 1L;
  
  class_1241(String paramString, class_748 paramclass_748)
  {
    super(paramString);
  }
  
  public final void a4()
  {
    try
    {
      ((class_371)this.field_171.getState()).a4().a23(AdminCommands.field_1973, new Object[] { this.field_171.getName() });
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
  
  public final Object a()
  {
    return "";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1241
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */