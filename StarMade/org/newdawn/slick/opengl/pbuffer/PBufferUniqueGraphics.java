/*   1:    */package org.newdawn.slick.opengl.pbuffer;
/*   2:    */
/*   3:    */import org.lwjgl.LWJGLException;
/*   4:    */import org.lwjgl.opengl.Display;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.lwjgl.opengl.Pbuffer;
/*   7:    */import org.lwjgl.opengl.PixelFormat;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.Image;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.opengl.InternalTextureLoader;
/*  12:    */import org.newdawn.slick.opengl.SlickCallable;
/*  13:    */import org.newdawn.slick.opengl.Texture;
/*  14:    */import org.newdawn.slick.opengl.TextureImpl;
/*  15:    */import org.newdawn.slick.util.Log;
/*  16:    */
/*  27:    */public class PBufferUniqueGraphics
/*  28:    */  extends Graphics
/*  29:    */{
/*  30:    */  private Pbuffer pbuffer;
/*  31:    */  private Image image;
/*  32:    */  
/*  33:    */  public PBufferUniqueGraphics(Image image)
/*  34:    */    throws SlickException
/*  35:    */  {
/*  36: 36 */    super(image.getTexture().getTextureWidth(), image.getTexture().getTextureHeight());
/*  37: 37 */    this.image = image;
/*  38:    */    
/*  39: 39 */    Log.debug("Creating pbuffer(unique) " + image.getWidth() + "x" + image.getHeight());
/*  40: 40 */    if ((Pbuffer.getCapabilities() & 0x1) == 0) {
/*  41: 41 */      throw new SlickException("Your OpenGL card does not support PBuffers and hence can't handle the dynamic images required for this application.");
/*  42:    */    }
/*  43:    */    
/*  44: 44 */    init();
/*  45:    */  }
/*  46:    */  
/*  49:    */  private void init()
/*  50:    */    throws SlickException
/*  51:    */  {
/*  52:    */    try
/*  53:    */    {
/*  54: 54 */      Texture tex = InternalTextureLoader.get().createTexture(this.image.getWidth(), this.image.getHeight(), this.image.getFilter());
/*  55:    */      
/*  56: 56 */      this.pbuffer = new Pbuffer(this.screenWidth, this.screenHeight, new PixelFormat(8, 0, 0), null, null);
/*  57:    */      
/*  58: 58 */      this.pbuffer.makeCurrent();
/*  59:    */      
/*  60: 60 */      initGL();
/*  61: 61 */      this.image.draw(0.0F, 0.0F);
/*  62: 62 */      GL11.glBindTexture(3553, tex.getTextureID());
/*  63: 63 */      GL11.glCopyTexImage2D(3553, 0, 6408, 0, 0, tex.getTextureWidth(), tex.getTextureHeight(), 0);
/*  64:    */      
/*  66: 66 */      this.image.setTexture(tex);
/*  67:    */      
/*  68: 68 */      Display.makeCurrent();
/*  69:    */    } catch (Exception e) {
/*  70: 70 */      Log.error(e);
/*  71: 71 */      throw new SlickException("Failed to create PBuffer for dynamic image. OpenGL driver failure?");
/*  72:    */    }
/*  73:    */  }
/*  74:    */  
/*  78:    */  protected void disable()
/*  79:    */  {
/*  80: 80 */    GL11.glBindTexture(3553, this.image.getTexture().getTextureID());
/*  81: 81 */    GL11.glCopyTexImage2D(3553, 0, 6408, 0, 0, this.image.getTexture().getTextureWidth(), this.image.getTexture().getTextureHeight(), 0);
/*  82:    */    
/*  84:    */    try
/*  85:    */    {
/*  86: 86 */      Display.makeCurrent();
/*  87:    */    } catch (LWJGLException e) {
/*  88: 88 */      Log.error(e);
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    SlickCallable.leaveSafeBlock();
/*  92:    */  }
/*  93:    */  
/*  95:    */  protected void enable()
/*  96:    */  {
/*  97:    */    
/*  98:    */    
/*  99:    */    try
/* 100:    */    {
/* 101:101 */      if (this.pbuffer.isBufferLost()) {
/* 102:102 */        this.pbuffer.destroy();
/* 103:103 */        init();
/* 104:    */      }
/* 105:    */      
/* 106:106 */      this.pbuffer.makeCurrent();
/* 107:    */    } catch (Exception e) {
/* 108:108 */      Log.error("Failed to recreate the PBuffer");
/* 109:109 */      Log.error(e);
/* 110:110 */      throw new RuntimeException(e);
/* 111:    */    }
/* 112:    */    
/* 114:114 */    TextureImpl.unbind();
/* 115:115 */    initGL();
/* 116:    */  }
/* 117:    */  
/* 120:    */  protected void initGL()
/* 121:    */  {
/* 122:122 */    GL11.glEnable(3553);
/* 123:123 */    GL11.glShadeModel(7425);
/* 124:124 */    GL11.glDisable(2929);
/* 125:125 */    GL11.glDisable(2896);
/* 126:    */    
/* 127:127 */    GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 128:128 */    GL11.glClearDepth(1.0D);
/* 129:    */    
/* 130:130 */    GL11.glEnable(3042);
/* 131:131 */    GL11.glBlendFunc(770, 771);
/* 132:    */    
/* 133:133 */    GL11.glViewport(0, 0, this.screenWidth, this.screenHeight);
/* 134:134 */    GL11.glMatrixMode(5888);
/* 135:135 */    GL11.glLoadIdentity();
/* 136:    */    
/* 137:137 */    enterOrtho();
/* 138:    */  }
/* 139:    */  
/* 142:    */  protected void enterOrtho()
/* 143:    */  {
/* 144:144 */    GL11.glMatrixMode(5889);
/* 145:145 */    GL11.glLoadIdentity();
/* 146:146 */    GL11.glOrtho(0.0D, this.screenWidth, 0.0D, this.screenHeight, 1.0D, -1.0D);
/* 147:147 */    GL11.glMatrixMode(5888);
/* 148:    */  }
/* 149:    */  
/* 152:    */  public void destroy()
/* 153:    */  {
/* 154:154 */    super.destroy();
/* 155:    */    
/* 156:156 */    this.pbuffer.destroy();
/* 157:    */  }
/* 158:    */  
/* 161:    */  public void flush()
/* 162:    */  {
/* 163:163 */    super.flush();
/* 164:    */    
/* 165:165 */    this.image.flushPixelData();
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.pbuffer.PBufferUniqueGraphics
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */