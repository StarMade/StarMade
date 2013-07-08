import java.util.Arrays;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;

public final class class_943
{
  public static Object[] a(String... paramVarArgs)
  {
    if (paramVarArgs.length != null.length) {
      throw new StateParameterNotFoundException(Arrays.toString(paramVarArgs), null);
    }
    Object[] arrayOfObject = new Object[null.length];
    try
    {
      for (int i = 0; i < null.length; i++) {
        arrayOfObject[i] = null.a(paramVarArgs[i]);
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new StateParameterNotFoundException(Arrays.toString(paramVarArgs), null);
    }
    return arrayOfObject;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_943
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */