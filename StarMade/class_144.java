import java.util.ArrayList;
import java.util.List;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.game.common.data.element.pointeffect.PointEffect;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_144
  extends class_959
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 field_90;
  private float jdField_field_89_of_type_Float = 40.0F;
  private PointDistributionUnit jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointDistributionUnit;
  private class_964 jdField_field_89_of_type_Class_964;
  
  public class_144(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_964 = new class_964(paramClientState);
    this.jdField_field_89_of_type_Class_930 = new class_930(200, 30, class_29.g(), paramClientState);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.field_90 = new class_930(200, 30, class_29.d(), paramClientState);
    this.field_90.field_90 = new ArrayList();
    this.field_90.field_90.add("----------------------------------------");
    this.jdField_field_89_of_type_Class_964.a161(0.0F, this.jdField_field_89_of_type_Float, 0.0F);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    this.field_90.a161(0.0F, this.jdField_field_89_of_type_Float + this.jdField_field_89_of_type_Class_964.a3(), 0.0F);
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_930.b();
    this.jdField_field_89_of_type_Class_964.b();
    this.field_90.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_964.a3() + this.jdField_field_89_of_type_Float * 2.0F;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_964.b1();
  }
  
  public final void c() {}
  
  private void b6(PointDistributionUnit paramPointDistributionUnit)
  {
    this.jdField_field_89_of_type_Class_964.clear();
    int i = 0;
    if (paramPointDistributionUnit != null) {
      for (PointEffect localPointEffect : paramPointDistributionUnit.getEffects())
      {
        this.jdField_field_89_of_type_Class_964.a144(new class_932(a24(), 200, 15, class_29.o(), localPointEffect.getName(), new class_155(a24(), localPointEffect), i % 2 == 0, true));
        i++;
      }
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_964.a12(paramclass_941);
    this.jdField_field_89_of_type_Class_930.field_90.set(1, "Rest: " + this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointDistributionUnit.getAvailableDist() + "% (auto distributed)");
    super.a12(paramclass_941);
  }
  
  public final void a37(PointDistributionUnit paramPointDistributionUnit)
  {
    this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementPointDistributionUnit = paramPointDistributionUnit;
    String str = "unknown";
    try
    {
      if (paramPointDistributionUnit.getId() != null) {
        str = "Weapon pos: " + paramPointDistributionUnit.getId().a6(new class_35());
      }
    }
    catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
    this.jdField_field_89_of_type_Class_930.field_90.set(0, str);
    this.jdField_field_89_of_type_Class_930.field_90.set(1, "Rest: " + paramPointDistributionUnit.getAvailableDist() + "% (auto distributed)");
    b6(paramPointDistributionUnit);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_144
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */