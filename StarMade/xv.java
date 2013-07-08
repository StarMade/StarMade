/*  1:   */import java.util.Arrays;
/*  2:   */import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*  3:   */
/* 13:   */public final class xv
/* 14:   */{
/* 15:   */  public static Object[] a(String... paramVarArgs)
/* 16:   */  {
/* 17:17 */    if (paramVarArgs.length != null.length) {
/* 18:18 */      throw new StateParameterNotFoundException(Arrays.toString(paramVarArgs), null);
/* 19:   */    }
/* 20:20 */    Object[] arrayOfObject = new Object[null.length];
/* 21:   */    try {
/* 22:22 */      for (int i = 0; i < null.length; i++) {
/* 23:23 */        arrayOfObject[i] = null.a(paramVarArgs[i]);
/* 24:   */      }
/* 25:   */    } catch (NumberFormatException localNumberFormatException) {
/* 26:26 */      throw new StateParameterNotFoundException(Arrays.toString(paramVarArgs), null);
/* 27:   */    }
/* 28:   */    
/* 29:29 */    return arrayOfObject;
/* 30:   */  }
/* 31:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */