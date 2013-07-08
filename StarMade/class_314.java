import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;

public final class class_314
  extends class_316
{
  public class_314(class_371 paramclass_371)
  {
    super(paramclass_371, "Drop Credits", "How many credits do you want to drop");
    a10(new class_312(this));
    this.field_4.a83(new class_318(this));
  }
  
  public final boolean a7(String paramString)
  {
    paramString = Integer.parseInt(paramString);
    this.field_4.a20().a127().creditsDropBuffer.add(new RemoteInteger(Integer.valueOf(paramString), false));
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_314
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */