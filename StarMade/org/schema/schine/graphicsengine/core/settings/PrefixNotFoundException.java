/*    */ package org.schema.schine.graphicsengine.core.settings;
/*    */ 
/*    */ public class PrefixNotFoundException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -8677548242927628561L;
/*    */ 
/*    */   public PrefixNotFoundException(String paramString)
/*    */   {
/* 11 */     super("ERROR: prefix not found: " + paramString);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.core.settings.PrefixNotFoundException
 * JD-Core Version:    0.6.2
 */