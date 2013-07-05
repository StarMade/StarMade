/*    */ import java.util.Arrays;
/*    */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*    */ 
/*    */ public final class xv
/*    */ {
/*    */   public static Object[] a(String[] paramArrayOfString)
/*    */   {
/* 17 */     if (paramArrayOfString.length != null.length) {
/* 18 */       throw new StateParameterNotFoundException(Arrays.toString(paramArrayOfString), null);
/*    */     }
/* 20 */     Object[] arrayOfObject = new Object[null.length];
/*    */     try {
/* 22 */       for (int i = 0; i < null.length; i++)
/* 23 */         arrayOfObject[i] = null.a(paramArrayOfString[i]);
/*    */     }
/*    */     catch (NumberFormatException localNumberFormatException) {
/* 26 */       throw new StateParameterNotFoundException(Arrays.toString(paramArrayOfString), null);
/*    */     }
/*    */ 
/* 29 */     return arrayOfObject;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xv
 * JD-Core Version:    0.6.2
 */