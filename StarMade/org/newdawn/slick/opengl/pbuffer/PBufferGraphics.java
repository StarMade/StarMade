/*   1:    */package org.newdawn.slick.opengl.pbuffer;
/*   2:    */
/*   3:    */import org.lwjgl.LWJGLException;
/*   4:    */import org.lwjgl.opengl.Display;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.lwjgl.opengl.Pbuffer;
/*   7:    */import org.lwjgl.opengl.PixelFormat;
/*   8:    */import org.lwjgl.opengl.RenderTexture;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.Image;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.opengl.InternalTextureLoader;
/*  13:    */import org.newdawn.slick.opengl.SlickCallable;
/*  14:    */import org.newdawn.slick.opengl.Texture;
/*  15:    */import org.newdawn.slick.opengl.TextureImpl;
/*  16:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  17:    */import org.newdawn.slick.util.Log;
/*  18:    */
/*  28:    */public class PBufferGraphics
/*  29:    */  extends Graphics
/*  30:    */{
/*  31:    */  private Pbuffer pbuffer;
/*  32:    */  private Image image;
/*  33:    */  
/*  34:    */  public PBufferGraphics(Image image)
/*  35:    */    throws SlickException
/*  36:    */  {
/*  37: 37 */    super(image.getTexture().getTextureWidth(), image.getTexture().getTextureHeight());
/*  38: 38 */    this.image = image;
/*  39:    */    
/*  40: 40 */    Log.debug("Creating pbuffer(rtt) " + image.getWidth() + "x" + image.getHeight());
/*  41: 41 */    if ((Pbuffer.getCapabilities() & 0x1) == 0) {
/*  42: 42 */      throw new SlickException("Your OpenGL card does not support PBuffers and hence can't handle the dynamic images required for this application.");
/*  43:    */    }
/*  44: 44 */    if ((Pbuffer.getCapabilities() & 0x2) == 0) {
/*  45: 45 */      throw new SlickException("Your OpenGL card does not support Render-To-Texture and hence can't handle the dynamic images required for this application.");
/*  46:    */    }
/*  47:    */    
/*  48: 48 */    init();
/*  49:    */  }
/*  50:    */  
/*  53:    */  private void init()
/*  54:    */    throws SlickException
/*  55:    */  {
/*  56:    */    try
/*  57:    */    {
/*  58: 58 */      Texture tex = InternalTextureLoader.get().createTexture(this.image.getWidth(), this.image.getHeight(), this.image.getFilter());
/*  59:    */      
/*  60: 60 */      RenderTexture rt = new RenderTexture(false, true, false, false, 8314, 0);
/*  61: 61 */      this.pbuffer = new Pbuffer(this.screenWidth, this.screenHeight, new PixelFormat(8, 0, 0), rt, null);
/*  62:    */      
/*  64: 64 */      this.pbuffer.makeCurrent();
/*  65:    */      
/*  66: 66 */      initGL();
/*  67: 67 */      GL.glBindTexture(3553, tex.getTextureID());
/*  68: 68 */      this.pbuffer.releaseTexImage(8323);
/*  69: 69 */      this.image.draw(0.0F, 0.0F);
/*  70: 70 */      this.image.setTexture(tex);
/*  71:    */      
/*  72: 72 */      Display.makeCurrent();
/*  73:    */    } catch (Exception e) {
/*  74: 74 */      Log.error(e);
/*  75: 75 */      throw new SlickException("Failed to create PBuffer for dynamic image. OpenGL driver failure?");
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  81:    */  protected void disable()
/*  82:    */  {
/*  83: 83 */    GL.flush();
/*  84:    */    
/*  86: 86 */    GL.glBindTexture(3553, this.image.getTexture().getTextureID());
/*  87: 87 */    this.pbuffer.bindTexImage(8323);
/*  88:    */    try
/*  89:    */    {
/*  90: 90 */      Display.makeCurrent();
/*  91:    */    } catch (LWJGLException e) {
/*  92: 92 */      Log.error(e);
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    SlickCallable.leaveSafeBlock();
/*  96:    */  }
/*  97:    */  
/*  99:    */  protected void enable()
/* 100:    */  {
/* 101:    */    
/* 102:    */    
/* 103:    */    try
/* 104:    */    {
/* 105:105 */      if (this.pbuffer.isBufferLost()) {
/* 106:106 */        this.pbuffer.destroy();
/* 107:107 */        init();
/* 108:    */      }
/* 109:    */      
/* 110:110 */      this.pbuffer.makeCurrent();
/* 111:    */    } catch (Exception e) {
/* 112:112 */      Log.error("Failed to recreate the PBuffer");
/* 113:113 */      throw new RuntimeException(e);
/* 114:    */    }
/* 115:    */    
/* 117:117 */    GL.glBindTexture(3553, this.image.getTexture().getTextureID());
/* 118:118 */    this.pbuffer.releaseTexImage(8323);
/* 119:119 */    TextureImpl.unbind();
/* 120:120 */    initGL();
/* 121:    */  }
/* 122:    */  
/* 125:    */  protected void initGL()
/* 126:    */  {
/* 127:127 */    GL11.glEnable(3553);
/* 128:128 */    GL11.glShadeModel(7425);
/* 129:129 */    GL11.glDisable(2929);
/* 130:130 */    GL11.glDisable(2896);
/* 131:    */    
/* 132:132 */    GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 133:133 */    GL11.glClearDepth(1.0D);
/* 134:    */    
/* 135:135 */    GL11.glEnable(3042);
/* 136:136 */    GL11.glBlendFunc(770, 771);
/* 137:    */    
/* 138:138 */    GL11.glViewport(0, 0, this.screenWidth, this.screenHeight);
/* 139:139 */    GL11.glMatrixMode(5888);
/* 140:140 */    GL11.glLoadIdentity();
/* 141:    */    
/* 142:142 */    enterOrtho();
/* 143:    */  }
/* 144:    */  
/* 147:    */  protected void enterOrtho()
/* 148:    */  {
/* 149:149 */    GL11.glMatrixMode(5889);
/* 150:150 */    GL11.glLoadIdentity();
/* 151:151 */    GL11.glOrtho(0.0D, this.screenWidth, 0.0D, this.screenHeight, 1.0D, -1.0D);
/* 152:152 */    GL11.glMatrixMode(5888);
/* 153:    */  }
/* 154:    */  
/* 157:    */  public void destroy()
/* 158:    */  {
/* 159:159 */    super.destroy();
/* 160:    */    
/* 161:161 */    this.pbuffer.destroy();
/* 162:    */  }
/* 163:    */  
/* 166:    */  public void flush()
/* 167:    */  {
/* 168:168 */    super.flush();
/* 169:    */    
/* 170:170 */    this.image.flushPixelData();
/* 171:    */  }
/* 172:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.pbuffer.PBufferGraphics
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */