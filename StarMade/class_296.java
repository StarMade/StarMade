import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBoolean;

final class class_296
  extends class_15
{
  class_296(class_286 paramclass_286, class_371 paramclass_371, Object paramObject1, Object paramObject2, String paramString)
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
    this.field_4.a14().e2(false);
  }
  
  public final void onFailedTextCheck(String paramString)
  {
    a9("ONWER INVALID: " + paramString);
  }
  
  public final boolean a7(String paramString)
  {
    if ((class_286.a113(this.field_4)) && (((Boolean)this.field_4.a20().a127().isAdminClient.get()).booleanValue()))
    {
      class_781 localclass_781;
      (localclass_781 = new class_781(class_286.a112(this.field_4))).field_139 = paramString;
      localclass_781.field_136 = true;
      this.field_4.a53().a96(localclass_781);
    }
    else
    {
      System.err.println("ERROR: CANNOT CHANGE OWNER (PERMISSION DENIED)");
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_296
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */