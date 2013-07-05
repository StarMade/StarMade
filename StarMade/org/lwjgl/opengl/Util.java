/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ public final class Util
/*    */ {
/*    */   public static void checkGLError()
/*    */     throws OpenGLException
/*    */   {
/* 57 */     int err = GL11.glGetError();
/* 58 */     if (err != 0)
/* 59 */       throw new OpenGLException(err);
/*    */   }
/*    */ 
/*    */   public static String translateGLErrorString(int error_code)
/*    */   {
/* 67 */     switch (error_code) {
/*    */     case 0:
/* 69 */       return "No error";
/*    */     case 1280:
/* 71 */       return "Invalid enum";
/*    */     case 1281:
/* 73 */       return "Invalid value";
/*    */     case 1282:
/* 75 */       return "Invalid operation";
/*    */     case 1283:
/* 77 */       return "Stack overflow";
/*    */     case 1284:
/* 79 */       return "Stack underflow";
/*    */     case 1285:
/* 81 */       return "Out of memory";
/*    */     case 32817:
/* 83 */       return "Table too large";
/*    */     case 1286:
/* 85 */       return "Invalid framebuffer operation";
/*    */     }
/* 87 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Util
 * JD-Core Version:    0.6.2
 */