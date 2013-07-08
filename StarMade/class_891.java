import java.util.ArrayList;
import java.util.List;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_891
  extends class_1363
{
  public class_930 field_89;
  public class_930 field_90;
  public class_930 field_92;
  private class_972 field_89;
  
  public class_891(ClientState paramClientState)
  {
    super(paramClientState);
    new class_48();
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("top-bar-gui-"), paramClientState);
  }
  
  public final void a2() {}
  
  protected final void d()
  {
    this.jdField_field_89_of_type_Class_972.h3(36);
  }
  
  public final void b()
  {
    if (k1()) {
      d();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return 64.0F;
  }
  
  public final float b1()
  {
    return 768.0F;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930 = new class_930(300, 40, class_29.j(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList(1);
    this.jdField_field_89_of_type_Class_930.field_90.add("0");
    this.jdField_field_89_of_type_Class_930.a83().field_615 = 240.0F;
    this.jdField_field_89_of_type_Class_930.a83().field_616 = 34.0F;
    this.field_90 = new class_930(300, 40, class_29.k(), a24());
    this.field_90.field_90 = new ArrayList(1);
    this.field_90.field_90.add("");
    this.field_90.field_90.add("");
    this.field_90.a83().field_615 = 398.0F;
    this.field_90.a83().field_616 = 30.0F;
    this.field_92 = new class_930(300, 40, class_29.j(), a24());
    this.field_92.field_90 = new ArrayList(1);
    this.field_92.field_90.add("0");
    this.field_92.a83().field_615 = 609.0F;
    this.field_92.a83().field_616 = 34.0F;
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.field_92);
    this.jdField_field_89_of_type_Class_972.a9(this.field_90);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_891
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */