import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_122
  extends class_1363
{
  private class_972 jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("ai-panel-gui-"), a24());
  private class_968 jdField_field_89_of_type_Class_968;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_964 jdField_field_89_of_type_Class_964;
  
  public class_122(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a9(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.a9(paramclass_1363);
  }
  
  public final void a2()
  {
    this.jdField_field_89_of_type_Class_972.a2();
  }
  
  public final void b2(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.b2(paramclass_1363);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  private class_19 a32()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
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
    this.jdField_field_89_of_type_Class_968 = new class_968(533.0F, 316.0F, a24());
    this.jdField_field_89_of_type_Class_968.a83().set(251.0F, 107.0F, 0.0F);
    this.jdField_field_89_of_type_Class_964 = new class_964(a24());
    this.jdField_field_89_of_type_Class_964.a141(a32());
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    this.jdField_field_89_of_type_Class_972.c();
    super.a9(this.jdField_field_89_of_type_Class_972);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_968);
    d();
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  private void a33(class_774 paramclass_774)
  {
    this.jdField_field_89_of_type_Class_964.clear();
    if (paramclass_774 != null)
    {
      int i = (paramclass_774 = paramclass_774.a185()).length;
      for (int j = 0; j < i; j++)
      {
        class_772 localclass_772;
        Object localObject;
        if (((localclass_772 = paramclass_774[j]).a171() instanceof Boolean))
        {
          localObject = new class_120(a24(), localclass_772);
          this.jdField_field_89_of_type_Class_964.a144(new class_932(a24(), localclass_772.a(), (class_922)localObject, true));
        }
        else
        {
          localObject = new class_126(a24(), localclass_772);
          this.jdField_field_89_of_type_Class_964.a144(new class_932(a24(), localclass_772.a(), (class_922)localObject, true));
        }
      }
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    super.a12(paramclass_941);
    if (a32().field_7)
    {
      if (a32().field_4 != null)
      {
        if (!(a32().field_4.a7().a15() instanceof class_991))
        {
          ((class_371)a24()).a4().b1("this structure has no AI");
          return;
        }
        a33((class_774)((class_991)a32().field_4.a7().a15()).a());
      }
      else
      {
        a33(null);
      }
      a32().field_7 = false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_122
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */