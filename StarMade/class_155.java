import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.pointeffect.PointEffect;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_155
  extends class_922
  implements Observer, class_1412
{
  private final class_930 jdField_field_89_of_type_Class_930;
  private final class_972 jdField_field_89_of_type_Class_972;
  private final class_972 jdField_field_90_of_type_Class_972;
  private boolean jdField_field_89_of_type_Boolean;
  private final PointEffect jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointeffectPointEffect;
  private boolean jdField_field_90_of_type_Boolean;
  private boolean field_92;
  
  public class_155(ClientState paramClientState, PointEffect paramPointEffect)
  {
    super(paramClientState);
    this.field_96 = true;
    a141(this);
    this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointeffectPointEffect = paramPointEffect;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.jdField_field_90_of_type_Class_972 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.jdField_field_89_of_type_Class_930 = new class_930(50, 30, class_29.b2(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("");
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0)) {
      System.err.println("NOTHING TO BE DONE");
    }
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    if (!this.jdField_field_90_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_930.b();
    this.jdField_field_89_of_type_Class_972.b();
    this.jdField_field_90_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_930.b1() + 20.0F + this.jdField_field_89_of_type_Class_972.b1() + this.jdField_field_90_of_type_Class_972.b1();
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_930.a83().field_616 += 8.0F;
    this.jdField_field_89_of_type_Class_972.field_96 = true;
    this.jdField_field_90_of_type_Class_972.field_96 = true;
    this.jdField_field_89_of_type_Class_972.a141(new class_157(this));
    this.jdField_field_90_of_type_Class_972.a141(new class_152(this));
    this.jdField_field_89_of_type_Class_972.a_2(21);
    this.jdField_field_90_of_type_Class_972.a_2(20);
    this.jdField_field_89_of_type_Class_930.a83().field_615 = (this.jdField_field_89_of_type_Class_972.b1() + 10.0F);
    this.jdField_field_90_of_type_Class_972.a83().field_615 = 150.0F;
    e();
    this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointeffectPointEffect.deleteObservers();
    this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointeffectPointEffect.addObserver(this);
    this.jdField_field_90_of_type_Boolean = true;
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.field_92 = true;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (this.field_92)
    {
      e();
      this.field_92 = false;
    }
    super.a12(paramclass_941);
  }
  
  private void e()
  {
    PointEffect localPointEffect;
    if ((localPointEffect = this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointeffectPointEffect) != null) {
      this.jdField_field_89_of_type_Class_930.field_90.set(0, localPointEffect.getDistribution() + "% = " + class_41.a2(localPointEffect.getValue()));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_155
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */