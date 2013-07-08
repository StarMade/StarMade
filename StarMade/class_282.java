import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBoolean;

final class class_282
  extends class_13
{
  class_282(class_286 paramclass_286, class_371 paramclass_371, Object paramObject1, Object paramObject2)
  {
    super(paramclass_371, paramObject1, paramObject2);
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void b()
  {
    class_781 localclass_781;
    if ((class_286.a113(this.field_4)) && (((Boolean)this.field_4.a20().a127().isAdminClient.get()).booleanValue()))
    {
      (localclass_781 = new class_781(class_286.a112(this.field_4))).field_136 = true;
      this.field_4.a53().b15(localclass_781);
    }
    else
    {
      (localclass_781 = new class_781(class_286.a112(this.field_4))).field_139 = this.field_4.a20().getName();
      this.field_4.a53().b15(localclass_781);
    }
    d();
  }
  
  public final void a2()
  {
    this.field_4.a14().e2(false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_282
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */