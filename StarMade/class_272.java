import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_272
  extends class_922
{
  private final class_192 jdField_field_89_of_type_Class_192;
  private final class_930 jdField_field_89_of_type_Class_930;
  private final class_972 jdField_field_89_of_type_Class_972;
  private final class_972 field_90;
  private boolean jdField_field_89_of_type_Boolean;
  private int jdField_field_89_of_type_Int = -1;
  
  public class_272(ClientState paramClientState)
  {
    super(paramClientState);
    this.field_96 = true;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.field_90 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.jdField_field_89_of_type_Class_192 = new class_192(paramClientState);
    this.jdField_field_89_of_type_Class_930 = new class_930(10, 10, a24());
    this.jdField_field_89_of_type_Class_930.a136("N/A for this block");
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    if (this.jdField_field_89_of_type_Int != a28().b6())
    {
      e();
      this.jdField_field_89_of_type_Int = a28().b6();
    }
    short s;
    if (((s = a28().a54()) == 0) || ((ElementKeyMap.getInfo(s).getBlockStyle() == 0) && (ElementKeyMap.getInfo(s).individualSides < 4) && (!ElementKeyMap.getInfo(s).isOrientatable())) || (ElementKeyMap.getInfo(s).getBlockStyle() == 3))
    {
      GlUtil.d1();
      r();
      this.jdField_field_89_of_type_Class_930.b();
      GlUtil.c2();
      return;
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_192.b();
    this.jdField_field_89_of_type_Class_972.b();
    this.field_90.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_192.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_192.b1() + this.jdField_field_89_of_type_Class_972.b1() + this.field_90.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_192.c();
    this.jdField_field_89_of_type_Class_972.field_96 = true;
    this.field_90.field_96 = true;
    this.jdField_field_89_of_type_Class_972.a141(new class_270(this));
    this.field_90.a141(new class_268(this));
    this.jdField_field_89_of_type_Class_972.a_2(21);
    this.field_90.a_2(20);
    this.jdField_field_89_of_type_Class_930.a83().field_615 = 6.0F;
    this.jdField_field_89_of_type_Class_930.a83().field_616 = 9.0F;
    this.jdField_field_89_of_type_Class_192.a83().field_615 = (this.jdField_field_89_of_type_Class_972.b1() + 29.0F);
    this.jdField_field_89_of_type_Class_192.a83().field_616 = 24.0F;
    this.field_90.a83().field_615 = (this.jdField_field_89_of_type_Class_972.b1() + 60.0F);
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  private void e()
  {
    int i = a28().a28();
    short s;
    if ((s = a28().a54()) != 0)
    {
      int j;
      if (((j = ElementKeyMap.getInfo(s).getBlockStyle()) > 0) && (j < 3))
      {
        this.jdField_field_89_of_type_Class_192.a74(s);
        this.jdField_field_89_of_type_Class_192.b13(0);
        this.jdField_field_89_of_type_Class_192.a72(org.schema.game.common.data.element.Element.orientationMapping[i]);
        return;
      }
      if (ElementKeyMap.getInfo(s).getIndividualSides() > 3)
      {
        this.jdField_field_89_of_type_Class_192.a74(s);
        this.jdField_field_89_of_type_Class_192.a72(0);
        this.jdField_field_89_of_type_Class_192.b13(org.schema.game.common.data.element.Element.orientationMapping[i]);
        return;
      }
      if (ElementKeyMap.getInfo(s).orientatable)
      {
        this.jdField_field_89_of_type_Class_192.a74(s);
        this.jdField_field_89_of_type_Class_192.a72(0);
        this.jdField_field_89_of_type_Class_192.b13(org.schema.game.common.data.element.Element.orientationMapping[i]);
        return;
      }
      this.jdField_field_89_of_type_Class_192.a74((short)0);
      this.jdField_field_89_of_type_Class_192.a72(0);
      this.jdField_field_89_of_type_Class_192.b13(0);
      return;
    }
    this.jdField_field_89_of_type_Class_192.a74((short)0);
    this.jdField_field_89_of_type_Class_192.a72(0);
    this.jdField_field_89_of_type_Class_192.b13(0);
  }
  
  public final class_443 a28()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_272
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */