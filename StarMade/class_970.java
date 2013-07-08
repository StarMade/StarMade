import java.util.ArrayList;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_970
  extends class_1363
{
  private int jdField_field_89_of_type_Int = class_968.jdField_field_89_of_type_Int;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 jdField_field_90_of_type_Class_972;
  private class_972 field_92;
  private class_972 field_93;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_968 jdField_field_89_of_type_Class_968;
  private boolean jdField_field_90_of_type_Boolean;
  
  public class_970(ClientState paramClientState, class_968 paramclass_968)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_968 = paramclass_968;
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    int i = 0;
    if (b3()) {
      i = 4;
    }
    r();
    l();
    this.jdField_field_89_of_type_Class_972.a_2(i);
    this.jdField_field_90_of_type_Class_972.a_2(i + 1);
    this.field_92.a_2(i + 2);
    this.field_93.a_2(i + 3);
    this.jdField_field_89_of_type_Class_972.a169(5, 0, 0, (int)b1(), (int)a3());
    this.jdField_field_90_of_type_Class_972.a169(9, 0, 0, (int)b1(), (int)a3());
    this.field_92.a169(17, 0, 0, (int)b1(), (int)a3());
    this.field_93.a169(17, 0, 0, (int)b1(), (int)a3());
    float f1;
    class_939 localclass_939;
    if (b3())
    {
      f1 = b1() - 64.0F;
      localclass_939 = null;
      this.field_92.field_89.field_615 = (f1 / 32.0F);
      this.field_93.a83().field_615 = (32.0F + this.jdField_field_89_of_type_Class_968.c8() * (f1 - 32.0F));
    }
    else
    {
      f1 = a3() - 64.0F;
      localclass_939 = null;
      this.field_92.field_89.field_616 = (f1 / 32.0F + 0.03F);
      this.field_93.a83().field_616 = (32.0F + this.jdField_field_89_of_type_Class_968.d5() * (f1 - 32.0F));
    }
    this.field_92.field_96 = this.jdField_field_89_of_type_Class_968.a_();
    this.jdField_field_89_of_type_Class_972.field_96 = this.jdField_field_89_of_type_Class_968.a_();
    this.jdField_field_90_of_type_Class_972.field_96 = this.jdField_field_89_of_type_Class_968.a_();
    this.field_93.field_96 = this.jdField_field_89_of_type_Class_968.a_();
    this.field_92.field_95 = false;
    this.field_92.b();
    this.jdField_field_89_of_type_Class_972.field_95 = false;
    this.jdField_field_89_of_type_Class_972.b();
    this.jdField_field_90_of_type_Class_972.field_95 = false;
    this.jdField_field_90_of_type_Class_972.b();
    this.field_93.field_95 = false;
    this.field_93.b();
    if ((!this.jdField_field_90_of_type_Boolean) && (Mouse.isButtonDown(0)) && (this.jdField_field_89_of_type_Class_972.a_())) {
      if (b3()) {
        this.jdField_field_89_of_type_Class_968.a(-1.0F);
      } else {
        this.jdField_field_89_of_type_Class_968.d6(-1.0F);
      }
    }
    if ((!this.jdField_field_90_of_type_Boolean) && (Mouse.isButtonDown(0)) && (this.jdField_field_90_of_type_Class_972.a_())) {
      if (b3()) {
        this.jdField_field_89_of_type_Class_968.a(1.0F);
      } else {
        this.jdField_field_89_of_type_Class_968.d6(1.0F);
      }
    }
    Matrix4f localMatrix4f = class_969.field_1259;
    float f3 = new Vector3f(localMatrix4f.m00, localMatrix4f.m01, localMatrix4f.m02).length();
    float f2 = new Vector3f(localMatrix4f.m10, localMatrix4f.m11, localMatrix4f.m12).length();
    Iterator localIterator;
    if ((this.field_92.a_()) && (!this.field_93.a_()))
    {
      localIterator = a24().getMouseEvents().iterator();
      while (localIterator.hasNext()) {
        if (((class_939)localIterator.next()).jdField_field_1163_of_type_Boolean) {
          if (b3())
          {
            if (this.jdField_field_89_of_type_Class_968.field_90.field_615 > this.field_93.a83().field_615 * f3 * f3) {
              this.jdField_field_89_of_type_Class_968.a(50.0F);
            } else {
              this.jdField_field_89_of_type_Class_968.a(-50.0F);
            }
          }
          else if (this.jdField_field_89_of_type_Class_968.field_90.field_616 > this.field_93.a83().field_616 * f2 * f2) {
            this.jdField_field_89_of_type_Class_968.d6(50.0F);
          } else {
            this.jdField_field_89_of_type_Class_968.d6(-50.0F);
          }
        }
      }
    }
    if (this.field_93.a_())
    {
      localIterator = a24().getMouseEvents().iterator();
      while (localIterator.hasNext()) {
        if ((localclass_939 = (class_939)localIterator.next()).jdField_field_1163_of_type_Int == 0) {
          this.jdField_field_90_of_type_Boolean = localclass_939.jdField_field_1163_of_type_Boolean;
        }
      }
    }
    if (!Mouse.isButtonDown(0)) {
      this.jdField_field_90_of_type_Boolean = false;
    }
    if (this.jdField_field_90_of_type_Boolean)
    {
      float f4;
      if (b3())
      {
        f4 = Math.max(0.0F, Math.min(1.0F, (this.field_90.field_615 - 32.0F) / (f3 * f3) / (b1() - 64.0F / (f3 * f3))));
        this.jdField_field_89_of_type_Class_968.c9(f4);
      }
      else
      {
        f4 = Math.max(0.0F, Math.min(1.0F, (this.field_90.field_616 - 32.0F) / (f2 * f2) / (a3() - 64.0F / (f2 * f2))));
        this.jdField_field_89_of_type_Class_968.e5(f4);
      }
    }
    GlUtil.c2();
  }
  
  public final float a3()
  {
    if (this.jdField_field_89_of_type_Int == class_968.jdField_field_89_of_type_Int) {
      return this.jdField_field_89_of_type_Class_968.a3();
    }
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    if (this.jdField_field_89_of_type_Int == class_968.field_90) {
      return this.jdField_field_89_of_type_Class_968.b1();
    }
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  private boolean b3()
  {
    return this.jdField_field_89_of_type_Int == class_968.field_90;
  }
  
  public final void c()
  {
    class_1380 localclass_1380 = class_969.a2().a5("tools-16x16-gui-");
    this.jdField_field_89_of_type_Class_972 = new class_972(localclass_1380, a24());
    this.jdField_field_90_of_type_Class_972 = new class_972(localclass_1380, a24());
    this.field_92 = new class_972(localclass_1380, a24());
    this.field_93 = new class_972(localclass_1380, a24());
    this.jdField_field_89_of_type_Class_972.field_96 = true;
    this.jdField_field_90_of_type_Class_972.field_96 = true;
    this.field_93.field_96 = true;
    this.jdField_field_89_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_970
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */