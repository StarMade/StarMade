import java.io.IOException;
import java.util.ArrayList;
import org.schema.game.common.data.UploadInProgressException;

final class class_408
  extends class_15
{
  class_408(class_303 paramclass_303, class_371 paramclass_371, Object paramObject1, Object paramObject2, String paramString)
  {
    super(paramclass_371, 50, paramObject1, paramObject2, paramString);
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
    this.field_4.e2(false);
  }
  
  public final void onFailedTextCheck(String paramString)
  {
    a9("SHIPNAME INVALID: " + paramString);
  }
  
  public final boolean a7(String paramString)
  {
    try
    {
      this.field_4.a20().a138().a4(paramString);
      return true;
    }
    catch (IOException localIOException)
    {
      (paramString = localIOException).printStackTrace();
      class_933.a2(paramString);
    }
    catch (UploadInProgressException paramString)
    {
      this.field_4.a4().b1("Cannot Upload!\nThere is already\nan Upload in progress");
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_408
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */