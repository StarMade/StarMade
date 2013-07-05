/*    */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*    */ 
/*    */ public final class xy extends xA
/*    */ {
/*    */   public final Object a(String paramString)
/*    */   {
/*    */     try
/*    */     {
/* 27 */       return Float.valueOf(Float.parseFloat(paramString)); } catch (NumberFormatException localNumberFormatException) { localNumberFormatException
/* 28 */         .printStackTrace();
/*    */     }
/* 30 */     throw new StateParameterNotFoundException(paramString, null);
/*    */   }
/*    */ 
/*    */   public final String a()
/*    */   {
/* 36 */     return "Float";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xy
 * JD-Core Version:    0.6.2
 */