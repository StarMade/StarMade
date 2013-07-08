import java.util.ArrayList;

public abstract class class_316
  extends class_15
{
  protected short field_4;
  protected final Object field_4;
  
  public class_316(class_371 paramclass_371, short paramShort, String paramString, Object paramObject, int paramInt)
  {
    super(paramclass_371, 5, paramString, paramObject, String.valueOf(paramInt));
    this.jdField_field_4_of_type_JavaLangObject = paramObject;
    this.jdField_field_4_of_type_Short = paramShort;
  }
  
  public class_316(class_371 paramclass_371, String paramString, Object paramObject)
  {
    super(paramclass_371, 10, paramString, paramObject, "0");
    this.jdField_field_4_of_type_JavaLangObject = paramObject;
    this.jdField_field_4_of_type_Short = -2;
  }
  
  public String[] getCommandPrefixes()
  {
    return null;
  }
  
  public String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return paramString1;
  }
  
  public final boolean a1()
  {
    return this.field_4.b().indexOf(this) != this.field_4.b().size() - 1;
  }
  
  public final void a2()
  {
    this.field_4.a14().field_4.field_4.field_4.e2(false);
  }
  
  public void onFailedTextCheck(String paramString)
  {
    a9(paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_316
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */