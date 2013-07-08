import java.util.ArrayList;
import javax.vecmath.Vector3f;

public final class class_463
  extends class_15
{
  private class_1418 field_4;
  
  public class_463(class_371 paramclass_371, short paramShort, String paramString, Object paramObject, class_1418 paramclass_1418)
  {
    super(paramclass_371, 5, paramString, paramObject, "1");
    this.field_4 = paramclass_1418;
    a10(new class_465(paramShort));
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
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
  
  public final void onFailedTextCheck(String paramString)
  {
    a9(paramString);
  }
  
  public final boolean a7(String paramString)
  {
    paramString = Integer.parseInt(paramString);
    paramString = Math.min(((class_185)this.field_4).a68(false), paramString);
    this.field_4.a5(true);
    this.field_4.setDragging(this.field_4);
    this.field_4.c((int)this.field_4.f5().field_615 + 32);
    this.field_4.d((int)this.field_4.f5().field_616 + 32);
    this.field_4.a6(System.currentTimeMillis());
    ((class_185)this.field_4).b13(paramString);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_463
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */