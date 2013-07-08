import javax.vecmath.Vector3f;
import org.newdawn.slick.Color;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.ChatSystem;
import org.schema.schine.network.client.ClientState;

public final class class_1408
  extends class_1363
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean = true;
  private boolean jdField_field_90_of_type_Boolean;
  private class_928 jdField_field_89_of_type_Class_928;
  private class_1410 jdField_field_89_of_type_Class_1410;
  private String jdField_field_90_of_type_JavaLangString = "";
  
  public class_1408(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_Class_930 = new class_930(class_29.n(), new Color(0.8F, 0.8F, 0.8F, 0.8F), paramClientState);
    this.jdField_field_90_of_type_Class_930.a136(new class_1406(this));
    this.jdField_field_89_of_type_Class_930 = new class_930(300, 300, class_29.n(), paramClientState);
    class_29.n();
    this.jdField_field_89_of_type_Class_1410 = new class_1410(paramClientState);
    this.jdField_field_89_of_type_Class_928 = new class_928(class_29.n(), paramClientState);
    this.jdField_field_89_of_type_Class_928.jdField_field_90_of_type_JavaLangString = "[CHAT]: ";
  }
  
  public final void a2()
  {
    this.jdField_field_89_of_type_Class_930.a2();
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_928.a12(paramclass_941);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    if (this.jdField_field_90_of_type_Boolean)
    {
      this.jdField_field_89_of_type_Class_1410.b();
      this.jdField_field_89_of_type_Class_928.b();
      if (this.jdField_field_90_of_type_JavaLangString.length() > 0) {
        this.jdField_field_90_of_type_Class_930.b();
      }
    }
    else
    {
      this.jdField_field_89_of_type_Class_930.b();
    }
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_928.a3();
  }
  
  public final String b14()
  {
    return "chatWindow";
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_928.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.field_89 = 8;
    this.jdField_field_89_of_type_Class_930.field_90 = a24().getVisibleChatLog();
    this.jdField_field_90_of_type_Class_930.a83().field_616 -= 55.0F;
    this.jdField_field_89_of_type_Class_928.a83().field_616 -= 30.0F;
    this.jdField_field_89_of_type_Class_928.c();
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_1410.c();
    this.jdField_field_90_of_type_Class_930.c();
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void a172(ChatSystem paramChatSystem)
  {
    this.jdField_field_89_of_type_Class_928.field_89 = paramChatSystem.getTextInput();
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.jdField_field_90_of_type_Boolean = paramBoolean;
  }
  
  public final void a17(String paramString)
  {
    this.jdField_field_90_of_type_JavaLangString = paramString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1408
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */