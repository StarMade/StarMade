import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Mouse;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_871
  extends class_1363
{
  private int jdField_field_89_of_type_Int = 400;
  private int jdField_field_90_of_type_Int = 217;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 jdField_field_90_of_type_Class_972;
  private class_972 field_92;
  private class_972 field_93;
  private class_303 jdField_field_89_of_type_Class_303;
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_930 jdField_field_90_of_type_Class_930;
  
  public class_871(ClientState paramClientState, class_303 paramclass_303)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_303 = paramclass_303;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.m1();
    this.jdField_field_90_of_type_Class_972.m1();
    this.field_92.m1();
    this.field_93.m1();
    this.jdField_field_89_of_type_Class_972.a_2(class_319.field_661.a((this.jdField_field_89_of_type_Class_972.a_()) && (Mouse.isButtonDown(0))));
    this.jdField_field_89_of_type_Class_972.b();
    this.jdField_field_90_of_type_Class_972.a_2(class_319.field_662.a((this.jdField_field_90_of_type_Class_972.a_()) && (Mouse.isButtonDown(0))));
    this.jdField_field_90_of_type_Class_972.b();
    this.field_92.a_2(class_319.field_671.a((this.field_92.a_()) && (Mouse.isButtonDown(0))));
    this.field_92.b();
    this.field_93.a_2(class_319.field_672.a((this.field_93.a_()) && (Mouse.isButtonDown(0))));
    this.field_93.b();
    if (this.jdField_field_89_of_type_Class_303.field_4 >= 0)
    {
      ElementInformation localElementInformation = ElementKeyMap.getInfo(this.jdField_field_89_of_type_Class_303.field_4);
      this.jdField_field_90_of_type_Class_930.field_90.set(0, localElementInformation.getFullName());
      class_743 localclass_743 = ((class_371)a24()).a5();
      long l = 0L;
      if (localclass_743 != null)
      {
        int i = localclass_743.a108().a42(localElementInformation.getId());
        int j = localclass_743.a108().a41(i);
        if ((i >= 0) && (j > 0)) {
          l = localclass_743.a107(localElementInformation, 1);
        }
      }
      if (l <= 0L) {
        this.jdField_field_89_of_type_Class_930.field_90.set(0, "not for sale");
      } else {
        this.jdField_field_89_of_type_Class_930.field_90.set(0, "Cost: " + l);
      }
    }
    this.jdField_field_89_of_type_Class_930.b();
    this.jdField_field_90_of_type_Class_930.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_90_of_type_Int;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Int;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("buttons-8x8-gui-"), a24());
    this.jdField_field_90_of_type_Class_972 = new class_972(class_969.a2().a5("buttons-8x8-gui-"), a24());
    this.field_93 = new class_972(class_969.a2().a5("buttons-8x8-gui-"), a24());
    this.field_92 = new class_972(class_969.a2().a5("buttons-8x8-gui-"), a24());
    this.jdField_field_89_of_type_Class_972.a_2(class_319.field_661.a(false));
    this.jdField_field_90_of_type_Class_972.a_2(class_319.field_662.a(false));
    this.field_92.a_2(class_319.field_671.a(false));
    this.field_93.a_2(class_319.field_672.a(false));
    this.jdField_field_89_of_type_Class_972.a161(16.0F, 47.0F, 0.0F);
    this.jdField_field_90_of_type_Class_972.a161(172.0F, 47.0F, 0.0F);
    this.field_92.a161(16.0F, 115.0F, 0.0F);
    this.field_93.a161(172.0F, 115.0F, 0.0F);
    this.field_92.field_89.set(0.8F, 0.8F, 0.8F);
    this.field_93.field_89.set(0.8F, 0.8F, 0.8F);
    this.jdField_field_89_of_type_Class_972.a141(this.jdField_field_89_of_type_Class_303);
    this.jdField_field_90_of_type_Class_972.a141(this.jdField_field_89_of_type_Class_303);
    this.field_92.a141(this.jdField_field_89_of_type_Class_303);
    this.field_93.a141(this.jdField_field_89_of_type_Class_303);
    this.jdField_field_89_of_type_Class_972.field_89 = "buy";
    this.jdField_field_90_of_type_Class_972.field_89 = "sell";
    this.field_92.field_89 = "buy_more";
    this.field_93.field_89 = "sell_more";
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_90_of_type_Class_972.c();
    this.field_92.c();
    this.field_93.c();
    this.jdField_field_89_of_type_Class_930 = new class_930(200, 200, class_29.i(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList(1);
    this.jdField_field_89_of_type_Class_930.field_90.add("Cost: 0");
    this.jdField_field_89_of_type_Class_930.a161(16.0F, 27.0F, 0.0F);
    this.jdField_field_90_of_type_Class_930 = new class_930(200, 200, class_29.h(), a24());
    this.jdField_field_90_of_type_Class_930.field_90 = new ArrayList(1);
    this.jdField_field_90_of_type_Class_930.field_90.add("");
    this.jdField_field_90_of_type_Class_930.a161(16.0F, 3.0F, 0.0F);
    this.jdField_field_89_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_871
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */