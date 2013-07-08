import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteStringArray;

final class class_486
  extends class_15
{
  class_486(class_303 paramclass_303, class_371 paramclass_371, Object paramObject1, Object paramObject2, String paramString)
  {
    super(paramclass_371, 50, paramObject1, paramObject2, paramString);
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return paramString1;
  }
  
  public final boolean a1()
  {
    return this.field_4.b().indexOf(this) != this.field_4.b().size() - 1;
  }
  
  public final void a2()
  {
    this.field_4.e2(false);
  }
  
  public final void onFailedTextCheck(String paramString)
  {
    a9("SHIPNAME INVALID: " + paramString);
  }
  
  public final boolean a7(String paramString)
  {
    if ((this.field_4.a3() == null) || (this.field_4.a3().getPhysicsDataContainer() == null) || (!this.field_4.a3().getPhysicsDataContainer().isInitialized()))
    {
      System.err.println("[ERROR] Character might not have been initialized");
      return false;
    }
    RemoteStringArray localRemoteStringArray = new RemoteStringArray(2, this.field_4.a20().a127());
    System.err.println("BUYING CATALOG: " + this.field_4.a33().field_136 + " FOR " + this.field_4.a20().a127());
    localRemoteStringArray.set(0, this.field_4.a33().field_136);
    localRemoteStringArray.set(1, paramString);
    this.field_4.a20().a127().catalogBuyBuffer.add(localRemoteStringArray);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_486
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */