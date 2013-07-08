import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_85
  extends class_1363
{
  private class_1414 jdField_field_89_of_type_Class_1414 = new class_1414(a24(), 300.0F, 300.0F);
  private class_1414 jdField_field_90_of_type_Class_1414;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_893 jdField_field_89_of_type_Class_893;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_964 jdField_field_89_of_type_Class_964;
  
  public class_85(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_893 = new class_893(paramClientState);
  }
  
  public final void a9(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_1414.a9(paramclass_1363);
  }
  
  public final void a2()
  {
    this.jdField_field_89_of_type_Class_1414.a2();
  }
  
  public final void b2(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_1414.b2(paramclass_1363);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    String str;
    if (!(str = "press " + class_367.field_758.b1() + "\nto enter advanced\nbuild mode\n\n").equals(this.jdField_field_89_of_type_Class_930.field_90.get(0))) {
      this.jdField_field_89_of_type_Class_930.field_90.set(0, str);
    }
    GlUtil.d1();
    this.jdField_field_89_of_type_Class_893.h3(17);
    this.jdField_field_89_of_type_Class_893.b();
    r();
    this.jdField_field_89_of_type_Class_1414.b();
    GlUtil.c2();
  }
  
  public final class_459 a10()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4.a50();
  }
  
  public final class_433 a11()
  {
    class_443 localclass_443;
    if ((localclass_443 = ((class_371)a24()).a14().field_4.field_4.field_4).a51().a45().field_4.field_6) {
      return localclass_443.a51().a45().field_4.a70();
    }
    return localclass_443.a53().a36().a70();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_1414.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_1414.b1();
  }
  
  public final boolean a_()
  {
    return this.jdField_field_89_of_type_Class_1414.a_();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_893.c();
    this.jdField_field_89_of_type_Class_930 = new class_930(100, 30, class_29.c(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("press " + class_367.field_758.b1() + "\nto enter advanced\nbuild mode\n\n");
    this.jdField_field_89_of_type_Class_930.field_90.add("press " + class_367.field_730.b1() + "\nto reset the camera\n\n");
    this.jdField_field_89_of_type_Class_930.field_90.add("hold Shift\nto move faster\n\n");
    this.jdField_field_89_of_type_Class_930.field_90.add("press " + class_367.field_728.b1() + "\nfor flight mode\n\n");
    this.jdField_field_89_of_type_Class_930.field_90.add("Shift + MouseWheel to zoom\n\n");
    this.jdField_field_90_of_type_Class_1414 = new class_1414(a24());
    this.jdField_field_90_of_type_Class_930 = new class_930(100, 30, class_29.d(), a24());
    this.jdField_field_90_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_90_of_type_Class_930.field_90.add("");
    this.jdField_field_90_of_type_Class_930.a83().field_616 = 100.0F;
    this.jdField_field_89_of_type_Class_968 = new class_968(533.0F, 316.0F, a24());
    this.jdField_field_89_of_type_Class_968.a83().set(0.0F, 0.0F, 0.0F);
    this.jdField_field_89_of_type_Class_964 = new class_964(a24());
    this.jdField_field_89_of_type_Class_964.a141(a10());
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_964);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_964.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_1414.c();
    class_85 localclass_85 = this;
    this.jdField_field_89_of_type_Class_964.clear();
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 100, "Remove Mode", new class_87(localclass_85, localclass_85.a24()), false));
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 60, "X", new class_82(localclass_85.a24(), localclass_85.a10().field_4), false));
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 60, "Y", new class_82(localclass_85.a24(), localclass_85.a10().field_5), false));
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 60, "Z", new class_82(localclass_85.a24(), localclass_85.a10().field_6), false));
    Object localObject = new class_932(localclass_85.a24(), 90, "Orientation", new class_272(localclass_85.a24()), false);
    localclass_85.jdField_field_89_of_type_Class_964.a144((class_959)localObject);
    (localObject = new class_930(100, 40, class_29.d(), localclass_85.a24())).a136("Symmetry Build Planes");
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_959((class_1363)localObject, (class_1363)localObject, localclass_85.a24()));
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 60, "XY-Plane", new class_206(localclass_85.a24(), 1), false));
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 60, "XZ-Plane", new class_206(localclass_85.a24(), 2), false));
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 60, "YZ-Plane", new class_206(localclass_85.a24(), 4), false));
    localclass_85.jdField_field_89_of_type_Class_964.a144(new class_932(localclass_85.a24(), 100, "Odd-sym Mode", new class_89(localclass_85, localclass_85.a24()), false));
    super.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_968);
    d();
    this.jdField_field_89_of_type_Class_1414.a83().set(class_933.b() - 270, 64.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414.field_96 = true;
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    super.a12(paramclass_941);
    if (Keyboard.isKeyDown(class_367.field_758.a5()))
    {
      this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_90_of_type_Class_1414);
      return;
    }
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_85
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */