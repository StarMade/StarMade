import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_873
  extends class_1363
{
  ArrayList jdField_field_89_of_type_JavaUtilArrayList;
  private static class_930 jdField_field_89_of_type_Class_930;
  private class_968 jdField_field_89_of_type_Class_968;
  private boolean jdField_field_89_of_type_Boolean = true;
  private ElementInformation jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation;
  private static ElementInformation field_90;
  
  public class_873(ElementInformation paramElementInformation, ClientState paramClientState, UnicodeFont paramUnicodeFont)
  {
    super(paramClientState);
    if (jdField_field_89_of_type_Class_930 == null)
    {
      (class_873.jdField_field_89_of_type_Class_930 = new class_930(512, 512, paramUnicodeFont, paramClientState)).a72(512);
      jdField_field_89_of_type_Class_930.a135(Color.green);
    }
    this.jdField_field_89_of_type_Class_968 = new class_968(336.0F, 184.0F, paramClientState);
    this.jdField_field_89_of_type_Class_968.c7(jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    if (field_90 != this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation)
    {
      jdField_field_89_of_type_Class_930.field_90 = this.jdField_field_89_of_type_JavaUtilArrayList;
      jdField_field_89_of_type_Class_930.jdField_field_89_of_type_Boolean = false;
      jdField_field_89_of_type_Class_930.e();
      field_90 = this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation;
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_968.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_968.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_968.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_968.c();
    this.jdField_field_89_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_873
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */