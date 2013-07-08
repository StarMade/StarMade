import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;

public final class class_1400
  extends class_1014
{
  public final Object a(String paramString)
  {
    try
    {
      return Float.valueOf(Float.parseFloat(paramString));
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      throw new StateParameterNotFoundException(paramString, null);
    }
  }
  
  public final String a1()
  {
    return "Float";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1400
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */