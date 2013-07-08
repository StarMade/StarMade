import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBoolean;

public final class class_428
  extends class_12
{
  private final class_244 field_4;
  private boolean field_5;
  
  public class_428(class_371 paramclass_371, class_781 paramclass_781, boolean paramBoolean)
  {
    super(paramclass_371);
    this.field_4 = new class_244(this.field_4, paramclass_781, this, paramBoolean);
    this.field_5 = paramBoolean;
    this.field_4.c();
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void handleKeyEvent() {}
  
  public final class_1363 a3()
  {
    return this.field_4;
  }
  
  public final void a2()
  {
    this.field_4.a14().field_4.field_4.field_4.a13(500);
    this.field_4.a14().field_4.field_4.field_4.e2(false);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if (paramclass_1363.b29().equals("OK"))
      {
        if ((this.field_5) && (((Boolean)this.field_4.a20().a127().isAdminClient.get()).booleanValue()))
        {
          this.field_4.a108().field_136 = true;
          this.field_4.a53().a96(this.field_4.a108());
        }
        else
        {
          this.field_4.a108().field_139 = this.field_4.a20().getName();
          this.field_4.a53().a96(this.field_4.a108());
        }
        d();
        return;
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X"))) {
        d();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_428
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */