/*    */ package org.newdawn.slick.opengl;
/*    */ 
/*    */ import java.security.AccessController;
/*    */ import java.security.PrivilegedAction;
/*    */ import org.newdawn.slick.util.Log;
/*    */ 
/*    */ public class ImageDataFactory
/*    */ {
/* 17 */   private static boolean usePngLoader = true;
/*    */ 
/* 19 */   private static boolean pngLoaderPropertyChecked = false;
/*    */   private static final String PNG_LOADER = "org.newdawn.slick.pngloader";
/*    */ 
/*    */   private static void checkProperty()
/*    */   {
/* 29 */     if (!pngLoaderPropertyChecked) {
/* 30 */       pngLoaderPropertyChecked = true;
/*    */       try
/*    */       {
/* 33 */         AccessController.doPrivileged(new PrivilegedAction() {
/*    */           public Object run() {
/* 35 */             String val = System.getProperty("org.newdawn.slick.pngloader");
/* 36 */             if ("false".equalsIgnoreCase(val)) {
/* 37 */               ImageDataFactory.access$002(false);
/*    */             }
/*    */ 
/* 40 */             Log.info("Use Java PNG Loader = " + ImageDataFactory.usePngLoader);
/* 41 */             return null;
/*    */           }
/*    */         });
/*    */       }
/*    */       catch (Throwable e)
/*    */       {
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public static LoadableImageData getImageDataFor(String ref)
/*    */   {
/* 58 */     checkProperty();
/*    */ 
/* 60 */     ref = ref.toLowerCase();
/*    */ 
/* 62 */     if (ref.endsWith(".tga")) {
/* 63 */       return new TGAImageData();
/*    */     }
/* 65 */     if (ref.endsWith(".png")) {
/* 66 */       CompositeImageData data = new CompositeImageData();
/* 67 */       if (usePngLoader) {
/* 68 */         data.add(new PNGImageData());
/*    */       }
/* 70 */       data.add(new ImageIOImageData());
/*    */ 
/* 72 */       return data;
/*    */     }
/*    */ 
/* 75 */     return new ImageIOImageData();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.ImageDataFactory
 * JD-Core Version:    0.6.2
 */