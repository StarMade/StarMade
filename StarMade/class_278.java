import java.util.Observable;
import java.util.Observer;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.remote.RemoteBoolean;

public final class class_278
  extends class_1363
  implements Observer
{
  private class_160 jdField_field_89_of_type_Class_160;
  private boolean jdField_field_89_of_type_Boolean;
  private class_930 jdField_field_89_of_type_Class_930;
  
  public class_278(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (((Boolean)((class_371)a24()).a20().a127().isAdminClient.get()).booleanValue())
    {
      if (this.jdField_field_89_of_type_Boolean)
      {
        this.jdField_field_89_of_type_Class_160.jdField_field_89_of_type_Boolean = true;
        this.jdField_field_89_of_type_Boolean = false;
      }
      k();
      return;
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_930.b();
    GlUtil.c2();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_160 = new class_276(a24());
    this.jdField_field_89_of_type_Class_160.c();
    a9(this.jdField_field_89_of_type_Class_160);
    this.jdField_field_89_of_type_Class_930 = new class_930(1, 1, a24());
    this.jdField_field_89_of_type_Class_930.a136("Permission denied! You are not an admin");
    this.jdField_field_89_of_type_Class_930.c();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_160.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_160.b1();
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_278
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */