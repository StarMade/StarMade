import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_853
  extends class_1363
  implements Observer, class_1412
{
  private class_194 jdField_field_89_of_type_Class_194;
  private class_194 jdField_field_90_of_type_Class_194;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_1412 jdField_field_90_of_type_Class_1412;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_968 jdField_field_90_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private int jdField_field_89_of_type_Int = 0;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_1363 jdField_field_89_of_type_Class_1363;
  private class_964 jdField_field_90_of_type_Class_964;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_1414 jdField_field_90_of_type_Class_1414;
  private boolean jdField_field_90_of_type_Boolean;
  
  public class_853(ClientState paramClientState, class_1412 paramclass_1412)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_Class_1412 = paramclass_1412;
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0)) {
      if ((paramclass_1363.field_89.equals("GENERAL")) || (paramclass_1363.field_89.equals("X")))
      {
        if (this.jdField_field_89_of_type_Int != 0)
        {
          this.jdField_field_89_of_type_Int = 0;
          this.jdField_field_90_of_type_Boolean = true;
        }
      }
      else if (paramclass_1363.field_89.equals("KEYBOARD"))
      {
        if (this.jdField_field_89_of_type_Int != 1)
        {
          this.jdField_field_89_of_type_Int = 1;
          this.jdField_field_90_of_type_Boolean = true;
        }
      }
      else if (!field_92) {
        throw new AssertionError("not known command: " + paramclass_1363.field_89);
      }
    }
  }
  
  public final void a2()
  {
    this.jdField_field_89_of_type_Class_972.a2();
  }
  
  protected final void d()
  {
    this.jdField_field_89_of_type_Class_972.h3(48);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    class_853 localclass_853 = this;
    if (this.jdField_field_90_of_type_Boolean)
    {
      if (localclass_853.jdField_field_89_of_type_Int == 0)
      {
        localclass_853.jdField_field_89_of_type_Class_972.a148(class_969.a2().a5("optionsPanel-gui-"));
        localclass_853.jdField_field_89_of_type_Class_972.b2(localclass_853.jdField_field_90_of_type_Class_968);
        if (!localclass_853.field_89.contains(localclass_853.jdField_field_89_of_type_Class_968)) {
          localclass_853.jdField_field_89_of_type_Class_972.a9(localclass_853.jdField_field_89_of_type_Class_968);
        }
      }
      else if (localclass_853.jdField_field_89_of_type_Int == 1)
      {
        localclass_853.jdField_field_89_of_type_Class_972.a148(class_969.a2().a5("optionsKeyboard-gui-"));
        localclass_853.jdField_field_89_of_type_Class_972.b2(localclass_853.jdField_field_89_of_type_Class_968);
        if (!localclass_853.field_89.contains(localclass_853.jdField_field_90_of_type_Class_968)) {
          localclass_853.jdField_field_89_of_type_Class_972.a9(localclass_853.jdField_field_90_of_type_Class_968);
        }
      }
      localclass_853.jdField_field_90_of_type_Boolean = false;
    }
    if (k1()) {
      d();
    }
    GlUtil.d1();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GL11.glDisable(3042);
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return 256.0F;
  }
  
  public final float b1()
  {
    return 256.0F;
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("optionsPanel-gui-"), a24());
    this.jdField_field_89_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_663, "OK", this.jdField_field_90_of_type_Class_1412);
    this.jdField_field_89_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_90_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_664, "CANCEL", this.jdField_field_90_of_type_Class_1412);
    this.jdField_field_90_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_89_of_type_Class_194.a83().field_615 = 600.0F;
    this.jdField_field_89_of_type_Class_194.a83().field_616 = 410.0F;
    this.jdField_field_90_of_type_Class_194.a83().field_616 = 410.0F;
    this.jdField_field_90_of_type_Class_194.a83().field_615 = 686.0F;
    this.jdField_field_89_of_type_Class_1363 = new class_1414(a24(), 39.0F, 26.0F);
    this.jdField_field_89_of_type_Class_1363.field_89 = "X";
    this.jdField_field_89_of_type_Class_1363.field_96 = true;
    this.jdField_field_89_of_type_Class_1363.a141(this.jdField_field_90_of_type_Class_1412);
    this.jdField_field_89_of_type_Class_1363.a83().set(804.0F, 4.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1363.c();
    this.jdField_field_89_of_type_Class_1414 = new class_1414(a24(), 147.0F, 40.0F);
    this.jdField_field_89_of_type_Class_1414.field_89 = "GENERAL";
    this.jdField_field_89_of_type_Class_1414.field_96 = true;
    this.jdField_field_89_of_type_Class_1414.a141(this);
    this.jdField_field_89_of_type_Class_1414.a83().set(216.0F, 26.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414.c();
    this.jdField_field_90_of_type_Class_1414 = new class_1414(a24(), 147.0F, 40.0F);
    this.jdField_field_90_of_type_Class_1414.field_89 = "KEYBOARD";
    this.jdField_field_90_of_type_Class_1414.field_96 = true;
    this.jdField_field_90_of_type_Class_1414.a141(this);
    this.jdField_field_90_of_type_Class_1414.a83().set(366.0F, 26.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.c();
    this.jdField_field_89_of_type_Class_968 = new class_968(516.0F, 295.0F, a24());
    this.jdField_field_89_of_type_Class_968.a83().set(254.0F, 110.0F, 0.0F);
    this.jdField_field_90_of_type_Class_968 = new class_968(516.0F, 295.0F, a24());
    this.jdField_field_90_of_type_Class_968.a83().set(254.0F, 110.0F, 0.0F);
    this.jdField_field_89_of_type_Class_964 = new class_964(a24());
    int i = 0;
    Object localObject1;
    int j = (localObject1 = class_949.values()).length;
    Object localObject2;
    Object localObject3;
    for (int k = 0; k < j; k++) {
      if (!(localObject2 = localObject1[k]).a7())
      {
        if ((((class_949)localObject2).a4() instanceof Boolean))
        {
          localObject3 = new class_963(a24(), (class_949)localObject2);
          this.jdField_field_89_of_type_Class_964.a144(new class_932(a24(), ((class_949)localObject2).a5(), (class_922)localObject3, i % 2 == 0));
        }
        else
        {
          this.jdField_field_89_of_type_Class_964.a144(new class_932(a24(), ((class_949)localObject2).a5(), new class_924(a24(), (class_949)localObject2), i % 2 == 0));
        }
        i++;
      }
    }
    this.jdField_field_90_of_type_Class_964 = new class_964(a24());
    this.jdField_field_90_of_type_Class_968.c7(this.jdField_field_90_of_type_Class_964);
    class_853 localclass_853 = this;
    for (localObject2 : class_369.values())
    {
      (localObject3 = new class_930(176, 30, class_29.g(), localclass_853.a24())).field_90 = new ArrayList();
      ((class_930)localObject3).field_90.add("+ " + ((class_369)localObject2).a());
      ((class_930)localObject3).a83().field_616 += 8.0F;
      class_1402 localclass_1402 = new class_1402(localclass_853.a24(), 468.0F, 30.0F, new Vector4f(0.0F, 0.0F, 0.0F, 0.8F));
      class_930 localclass_930;
      (localclass_930 = new class_930(176, 30, class_29.g(), localclass_853.a24())).field_90 = new ArrayList();
      localclass_930.field_90.add(((class_369)localObject2).a());
      localclass_930.field_96 = true;
      localclass_930.a83().field_616 += 8.0F;
      localclass_1402.a9(localclass_930);
      (localObject3 = new class_961(localclass_853.a24(), (class_1363)localObject3, localclass_1402)).a83().field_615 = (((class_369)localObject2).a1() * 5);
      ((class_961)localObject3).addObserver(localclass_853);
      ((class_1363)localObject3).field_89 = "CATEGORY";
      ((class_961)localObject3).c();
      ((class_1363)localObject3).field_96 = true;
      ((class_961)localObject3).a29(true);
      localObject2 = new class_959((class_1363)localObject3, (class_1363)localObject3, localclass_853.a24());
      ((class_961)localObject3).b25(localclass_853);
      localclass_853.jdField_field_90_of_type_Class_964.a144((class_959)localObject2);
    }
    for (localObject2 : class_367.values()) {
      (localObject3 = (class_961)localclass_853.jdField_field_90_of_type_Class_964.a145(((class_367)localObject2).a3().ordinal()).a139()).a140().a144(new class_855(localclass_853.a24(), ((class_367)localObject2).a4(), new class_843(localclass_853.a24(), (class_367)localObject2), ((class_961)localObject3).a140().size() % 2 == 0));
    }
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    this.jdField_field_89_of_type_Class_972.c();
    a9(this.jdField_field_89_of_type_Class_972);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_194);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_194);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_968);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_1363);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_1414);
    d();
    this.jdField_field_89_of_type_Boolean = false;
    this.field_96 = true;
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_90_of_type_Class_964.f();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_853
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */