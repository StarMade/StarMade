/*   1:    */package org.newdawn.slick.opengl.pbuffer;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */import org.lwjgl.opengl.ContextCapabilities;
/*   5:    */import org.lwjgl.opengl.GLContext;
/*   6:    */import org.lwjgl.opengl.Pbuffer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.util.Log;
/*  11:    */
/*  18:    */public class GraphicsFactory
/*  19:    */{
/*  20: 20 */  private static HashMap graphics = new HashMap();
/*  21:    */  
/*  22: 22 */  private static boolean pbuffer = true;
/*  23:    */  
/*  24: 24 */  private static boolean pbufferRT = true;
/*  25:    */  
/*  26: 26 */  private static boolean fbo = true;
/*  27:    */  
/*  28: 28 */  private static boolean init = false;
/*  29:    */  
/*  34:    */  private static void init()
/*  35:    */    throws SlickException
/*  36:    */  {
/*  37: 37 */    init = true;
/*  38:    */    
/*  39: 39 */    if (fbo) {
/*  40: 40 */      fbo = GLContext.getCapabilities().GL_EXT_framebuffer_object;
/*  41:    */    }
/*  42: 42 */    pbuffer = (Pbuffer.getCapabilities() & 0x1) != 0;
/*  43: 43 */    pbufferRT = (Pbuffer.getCapabilities() & 0x2) != 0;
/*  44:    */    
/*  45: 45 */    if ((!fbo) && (!pbuffer) && (!pbufferRT)) {
/*  46: 46 */      throw new SlickException("Your OpenGL card does not support offscreen buffers and hence can't handle the dynamic images required for this application.");
/*  47:    */    }
/*  48:    */    
/*  49: 49 */    Log.info("Offscreen Buffers FBO=" + fbo + " PBUFFER=" + pbuffer + " PBUFFERRT=" + pbufferRT);
/*  50:    */  }
/*  51:    */  
/*  56:    */  public static void setUseFBO(boolean useFBO)
/*  57:    */  {
/*  58: 58 */    fbo = useFBO;
/*  59:    */  }
/*  60:    */  
/*  65:    */  public static boolean usingFBO()
/*  66:    */  {
/*  67: 67 */    return fbo;
/*  68:    */  }
/*  69:    */  
/*  74:    */  public static boolean usingPBuffer()
/*  75:    */  {
/*  76: 76 */    return (!fbo) && (pbuffer);
/*  77:    */  }
/*  78:    */  
/*  85:    */  public static Graphics getGraphicsForImage(Image image)
/*  86:    */    throws SlickException
/*  87:    */  {
/*  88: 88 */    Graphics g = (Graphics)graphics.get(image.getTexture());
/*  89:    */    
/*  90: 90 */    if (g == null) {
/*  91: 91 */      g = createGraphics(image);
/*  92: 92 */      graphics.put(image.getTexture(), g);
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    return g;
/*  96:    */  }
/*  97:    */  
/* 102:    */  public static void releaseGraphicsForImage(Image image)
/* 103:    */    throws SlickException
/* 104:    */  {
/* 105:105 */    Graphics g = (Graphics)graphics.remove(image.getTexture());
/* 106:    */    
/* 107:107 */    if (g != null) {
/* 108:108 */      g.destroy();
/* 109:    */    }
/* 110:    */  }
/* 111:    */  
/* 115:    */  private static Graphics createGraphics(Image image)
/* 116:    */    throws SlickException
/* 117:    */  {
/* 118:    */    
/* 119:    */    
/* 122:122 */    if (fbo) {
/* 123:    */      try {
/* 124:124 */        return new FBOGraphics(image);
/* 125:    */      } catch (Exception e) {
/* 126:126 */        fbo = false;
/* 127:127 */        Log.warn("FBO failed in use, falling back to PBuffer");
/* 128:    */      }
/* 129:    */    }
/* 130:    */    
/* 131:131 */    if (pbuffer) {
/* 132:132 */      if (pbufferRT) {
/* 133:133 */        return new PBufferGraphics(image);
/* 134:    */      }
/* 135:135 */      return new PBufferUniqueGraphics(image);
/* 136:    */    }
/* 137:    */    
/* 139:139 */    throw new SlickException("Failed to create offscreen buffer even though the card reports it's possible");
/* 140:    */  }
/* 141:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.pbuffer.GraphicsFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */