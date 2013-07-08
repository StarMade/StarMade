import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.newdawn.slick.UnicodeFont;
import org.schema.schine.network.client.ClientState;

public final class class_914
  extends class_1414
  implements class_1412
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 field_90;
  private class_930 field_92;
  private class_930 field_93;
  private class_930 field_94;
  private class_930 field_95;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_914(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((a130() != null) && (paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if (paramclass_1363 == this.field_90)
      {
        a130().c();
        return;
      }
      if (paramclass_1363 == this.field_92)
      {
        a130().e();
        return;
      }
      if (paramclass_1363 == this.field_93)
      {
        a130().f();
        return;
      }
      if (paramclass_1363 == this.field_94)
      {
        a130().b();
        return;
      }
      if (paramclass_1363 == this.field_95) {
        a130().d();
      }
    }
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    super.b();
  }
  
  public final float a3()
  {
    return 180.0F;
  }
  
  private class_460 a130()
  {
    return ((class_371)a24()).a4().a5();
  }
  
  public final float b1()
  {
    return 260.0F;
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final void c()
  {
    Object localObject = class_29.n();
    this.jdField_field_89_of_type_Class_930 = new class_930(120, 30, class_29.o(), a24());
    this.field_90 = new class_930(120, 30, (UnicodeFont)localObject, a24());
    this.field_92 = new class_930(120, 30, (UnicodeFont)localObject, a24());
    this.field_93 = new class_930(120, 30, (UnicodeFont)localObject, a24());
    this.field_94 = new class_930(120, 30, (UnicodeFont)localObject, a24());
    this.field_95 = new class_930(120, 30, (UnicodeFont)localObject, a24());
    this.field_90.a141(this);
    this.field_92.a141(this);
    this.field_93.a141(this);
    this.field_94.a141(this);
    this.field_95.a141(this);
    this.field_90.field_96 = true;
    this.field_92.field_96 = true;
    this.field_93.field_96 = true;
    this.field_94.field_96 = true;
    this.field_95.field_96 = true;
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList(1);
    this.field_90.field_90 = new ArrayList(1);
    this.field_92.field_90 = new ArrayList(1);
    this.field_93.field_90 = new ArrayList(1);
    this.field_94.field_90 = new ArrayList(1);
    this.field_95.field_90 = new ArrayList(1);
    this.jdField_field_89_of_type_Class_930.field_90.add("Tutorial Controls");
    this.field_90.field_90.add("Skip Part");
    this.field_92.field_90.add("Repeat Part");
    this.field_93.field_90.add("skip current step");
    this.field_94.field_90.add("end tutorial");
    this.field_95.field_90.add("reset tutorial");
    localObject = new Vector4f(0.0F, 0.0F, 0.0F, 0.7F);
    class_1402 localclass_14021 = new class_1402(a24(), 120.0F, 20.0F, (Vector4f)localObject);
    class_1402 localclass_14022 = new class_1402(a24(), 120.0F, 20.0F, (Vector4f)localObject);
    class_1402 localclass_14023 = new class_1402(a24(), 120.0F, 20.0F, (Vector4f)localObject);
    class_1402 localclass_14024 = new class_1402(a24(), 120.0F, 20.0F, (Vector4f)localObject);
    class_1402 localclass_14025 = new class_1402(a24(), 120.0F, 20.0F, (Vector4f)localObject);
    localObject = new class_1402(a24(), 120.0F, 20.0F, (Vector4f)localObject);
    localclass_14024.a83().set(0.0F, 30.0F, 0.0F);
    localclass_14023.a83().set(0.0F, 60.0F, 0.0F);
    localclass_14022.a83().set(120.0F, 60.0F, 0.0F);
    ((class_1402)localObject).a83().set(0.0F, 90.0F, 0.0F);
    localclass_14025.a83().set(120.0F, 90.0F, 0.0F);
    localclass_14021.a9(this.jdField_field_89_of_type_Class_930);
    localclass_14022.a9(this.field_90);
    localclass_14023.a9(this.field_92);
    localclass_14024.a9(this.field_93);
    localclass_14025.a9(this.field_94);
    ((class_1402)localObject).a9(this.field_95);
    a9(localclass_14021);
    a9(localclass_14022);
    a9(localclass_14023);
    a9(localclass_14024);
    a9(localclass_14025);
    a9((class_1363)localObject);
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_914
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */