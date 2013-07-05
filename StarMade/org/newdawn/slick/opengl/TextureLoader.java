/*    */ package org.newdawn.slick.opengl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class TextureLoader
/*    */ {
/*    */   public static Texture getTexture(String format, InputStream in)
/*    */     throws IOException
/*    */   {
/* 24 */     return getTexture(format, in, false, 9729);
/*    */   }
/*    */ 
/*    */   public static Texture getTexture(String format, InputStream in, boolean flipped)
/*    */     throws IOException
/*    */   {
/* 37 */     return getTexture(format, in, flipped, 9729);
/*    */   }
/*    */ 
/*    */   public static Texture getTexture(String format, InputStream in, int filter)
/*    */     throws IOException
/*    */   {
/* 50 */     return getTexture(format, in, false, filter);
/*    */   }
/*    */ 
/*    */   public static Texture getTexture(String format, InputStream in, boolean flipped, int filter)
/*    */     throws IOException
/*    */   {
/* 64 */     return InternalTextureLoader.get().getTexture(in, in.toString() + "." + format, flipped, filter);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.TextureLoader
 * JD-Core Version:    0.6.2
 */