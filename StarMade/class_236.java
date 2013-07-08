import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.newdawn.slick.Color;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_236
  extends class_1363
{
  class_185[] jdField_field_89_of_type_ArrayOfClass_185 = new class_185[256];
  private class_930[] jdField_field_89_of_type_ArrayOfClass_930 = new class_930[256];
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_130 jdField_field_89_of_type_Class_130;
  private int jdField_field_89_of_type_Int;
  private long jdField_field_89_of_type_Long;
  
  public class_236(class_1380 paramclass_1380, class_371 paramclass_371)
  {
    super(paramclass_371);
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_185.length; i++)
    {
      this.jdField_field_89_of_type_ArrayOfClass_185[i] = new class_185(paramclass_371, true, null);
      this.jdField_field_89_of_type_ArrayOfClass_930[i] = new class_930(32, 32, class_29.c(), paramclass_371);
      this.jdField_field_89_of_type_ArrayOfClass_930[i].a135(Color.white);
      this.jdField_field_89_of_type_ArrayOfClass_930[i].field_90 = new ArrayList();
      this.jdField_field_89_of_type_ArrayOfClass_930[i].field_90.add("i " + i);
      this.jdField_field_89_of_type_ArrayOfClass_930[i].a161(2.0F, 2.0F, 0.0F);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].a9(this.jdField_field_89_of_type_ArrayOfClass_930[i]);
    }
    this.jdField_field_89_of_type_Class_972 = new class_972(paramclass_1380, paramclass_371);
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 64.0F, 64.0F, new Vector4f(1.0F, 1.0F, 1.0F, 0.18F));
  }
  
  public final void a29(boolean paramBoolean)
  {
    for (int i = 0; i < 10; i++) {
      this.jdField_field_89_of_type_ArrayOfClass_185[i].field_96 = paramBoolean;
    }
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    GlUtil.d1();
    class_453 localclass_453 = ((class_371)a24()).a14().field_4.field_4.field_4.a51().a45().field_4;
    class_443 localclass_443 = ((class_371)a24()).a14().field_4.field_4.field_4;
    if (localclass_453 == null) {
      return;
    }
    int i = localclass_443.b6();
    class_639 localclass_639 = ((class_371)a24()).a20().getInventory(null);
    r();
    l();
    this.jdField_field_89_of_type_Class_972.b();
    for (int j = 0; j < 10; j++)
    {
      short s = 0;
      int m = 511;
      int n = localclass_443.a55(j + 2);
      if (localclass_639.a18(n)) {
        this.jdField_field_89_of_type_ArrayOfClass_930[j].field_90.set(0, "");
      } else {
        m = ElementKeyMap.getInfo(s = localclass_639.a45(n)).getBuildIconNum();
      }
      int i1 = localclass_639.a41(n);
      this.jdField_field_89_of_type_ArrayOfClass_185[j].f4(n);
      this.jdField_field_89_of_type_ArrayOfClass_185[j].a74(s);
      this.jdField_field_89_of_type_ArrayOfClass_185[j].a72(i1);
      if ((k = this.jdField_field_89_of_type_ArrayOfClass_185[j].a68(false)) <= 0) {
        m = 511;
      }
      if (k > 0) {
        this.jdField_field_89_of_type_ArrayOfClass_930[j].field_90.set(0, String.valueOf(k));
      } else {
        this.jdField_field_89_of_type_ArrayOfClass_930[j].field_90.set(0, "");
      }
      this.jdField_field_89_of_type_ArrayOfClass_185[j].b21(false);
      this.jdField_field_89_of_type_ArrayOfClass_185[j].a83().field_616 = 21.0F;
      this.jdField_field_89_of_type_ArrayOfClass_185[j].a83().field_615 = (159.0F + j * 70.0F);
      int k = m / 256;
      m %= 256;
      this.jdField_field_89_of_type_ArrayOfClass_185[j].e4(k);
      this.jdField_field_89_of_type_ArrayOfClass_185[j].a147().b13(m);
      this.jdField_field_89_of_type_ArrayOfClass_185[j].b();
      if (n == i)
      {
        if (this.jdField_field_89_of_type_Int != n)
        {
          this.jdField_field_89_of_type_Long = System.currentTimeMillis();
          this.jdField_field_89_of_type_Int = n;
        }
        this.jdField_field_89_of_type_Class_1402.a83().set(this.jdField_field_89_of_type_ArrayOfClass_185[j].a83());
        this.jdField_field_89_of_type_Class_1402.field_89.set(this.jdField_field_89_of_type_ArrayOfClass_185[j].field_89);
        this.jdField_field_89_of_type_Class_1402.b();
        long l;
        if ((l = System.currentTimeMillis() - this.jdField_field_89_of_type_Long) < 1200L)
        {
          GlUtil.d1();
          this.jdField_field_89_of_type_ArrayOfClass_185[j].a((float)l / 1200.0F);
          GlUtil.c2();
        }
      }
    }
    this.jdField_field_89_of_type_ArrayOfClass_185[0].a147().b13(0);
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_185.length; i++) {
      this.jdField_field_89_of_type_ArrayOfClass_185[i].c();
    }
    this.jdField_field_89_of_type_Class_1402.c();
    this.jdField_field_89_of_type_Class_130 = new class_130((class_371)a24());
    this.jdField_field_89_of_type_Class_130.c();
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_130);
  }
  
  public final void a12(class_941 paramclass_941)
  {
    super.a12(paramclass_941);
    this.jdField_field_89_of_type_Class_130.a12(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_236
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */