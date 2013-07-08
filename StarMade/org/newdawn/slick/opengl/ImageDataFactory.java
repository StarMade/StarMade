/*  1:   */package org.newdawn.slick.opengl;
/*  2:   */
/*  3:   */import java.security.AccessController;
/*  4:   */import java.security.PrivilegedAction;
/*  5:   */import org.newdawn.slick.util.Log;
/*  6:   */
/* 15:   */public class ImageDataFactory
/* 16:   */{
/* 17:17 */  private static boolean usePngLoader = true;
/* 18:   */  
/* 19:19 */  private static boolean pngLoaderPropertyChecked = false;
/* 20:   */  
/* 23:   */  private static final String PNG_LOADER = "org.newdawn.slick.pngloader";
/* 24:   */  
/* 27:   */  private static void checkProperty()
/* 28:   */  {
/* 29:29 */    if (!pngLoaderPropertyChecked) {
/* 30:30 */      pngLoaderPropertyChecked = true;
/* 31:   */      try
/* 32:   */      {
/* 33:33 */        AccessController.doPrivileged(new PrivilegedAction() {
/* 34:   */          public Object run() {
/* 35:35 */            String val = System.getProperty("org.newdawn.slick.pngloader");
/* 36:36 */            if ("false".equalsIgnoreCase(val)) {
/* 37:37 */              ImageDataFactory.access$002(false);
/* 38:   */            }
/* 39:   */            
/* 40:40 */            Log.info("Use Java PNG Loader = " + ImageDataFactory.usePngLoader);
/* 41:41 */            return null;
/* 42:   */          }
/* 43:   */        });
/* 44:   */      }
/* 45:   */      catch (Throwable e) {}
/* 46:   */    }
/* 47:   */  }
/* 48:   */  
/* 56:   */  public static LoadableImageData getImageDataFor(String ref)
/* 57:   */  {
/* 58:58 */    checkProperty();
/* 59:   */    
/* 60:60 */    ref = ref.toLowerCase();
/* 61:   */    
/* 62:62 */    if (ref.endsWith(".tga")) {
/* 63:63 */      return new TGAImageData();
/* 64:   */    }
/* 65:65 */    if (ref.endsWith(".png")) {
/* 66:66 */      CompositeImageData data = new CompositeImageData();
/* 67:67 */      if (usePngLoader) {
/* 68:68 */        data.add(new PNGImageData());
/* 69:   */      }
/* 70:70 */      data.add(new ImageIOImageData());
/* 71:   */      
/* 72:72 */      return data;
/* 73:   */    }
/* 74:   */    
/* 75:75 */    return new ImageIOImageData();
/* 76:   */  }
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.ImageDataFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */