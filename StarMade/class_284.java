import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBoolean;

final class class_284
  extends class_14
{
  class_284(class_286 paramclass_286, class_371 paramclass_371, Object paramObject1, Object paramObject2, String paramString)
  {
    super(paramclass_371, 3, paramObject1, paramObject2, paramString);
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void onFailedTextCheck(String paramString) {}
  
  public final String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return null;
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final boolean a7(String paramString)
  {
    class_781 localclass_781;
    (localclass_781 = new class_781(class_286.a112(this.field_4))).field_182 = paramString;
    if ((class_286.a113(this.field_4)) && (((Boolean)this.field_4.a20().a127().isAdminClient.get()).booleanValue()))
    {
      localclass_781.field_136 = true;
      this.field_4.a53().a96(localclass_781);
    }
    else
    {
      localclass_781.field_139 = this.field_4.a20().getName();
      this.field_4.a53().a96(localclass_781);
    }
    return true;
  }
  
  public final void a2()
  {
    this.field_4.a14().e2(false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_284
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */