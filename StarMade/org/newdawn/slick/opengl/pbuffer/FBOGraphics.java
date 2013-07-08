/*   1:    */package org.newdawn.slick.opengl.pbuffer;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.opengl.ContextCapabilities;
/*   6:    */import org.lwjgl.opengl.EXTFramebufferObject;
/*   7:    */import org.lwjgl.opengl.GL11;
/*   8:    */import org.lwjgl.opengl.GLContext;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.Image;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.opengl.InternalTextureLoader;
/*  13:    */import org.newdawn.slick.opengl.SlickCallable;
/*  14:    */import org.newdawn.slick.opengl.Texture;
/*  15:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  16:    */import org.newdawn.slick.util.Log;
/*  17:    */
/*  23:    */public class FBOGraphics
/*  24:    */  extends Graphics
/*  25:    */{
/*  26:    */  private Image image;
/*  27:    */  private int FBO;
/*  28: 28 */  private boolean valid = true;
/*  29:    */  
/*  34:    */  public FBOGraphics(Image image)
/*  35:    */    throws SlickException
/*  36:    */  {
/*  37: 37 */    super(image.getTexture().getTextureWidth(), image.getTexture().getTextureHeight());
/*  38: 38 */    this.image = image;
/*  39:    */    
/*  40: 40 */    Log.debug("Creating FBO " + image.getWidth() + "x" + image.getHeight());
/*  41:    */    
/*  42: 42 */    boolean FBOEnabled = GLContext.getCapabilities().GL_EXT_framebuffer_object;
/*  43: 43 */    if (!FBOEnabled) {
/*  44: 44 */      throw new SlickException("Your OpenGL card does not support FBO and hence can't handle the dynamic images required for this application.");
/*  45:    */    }
/*  46:    */    
/*  47: 47 */    init();
/*  48:    */  }
/*  49:    */  
/*  53:    */  private void completeCheck()
/*  54:    */    throws SlickException
/*  55:    */  {
/*  56: 56 */    int framebuffer = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
/*  57: 57 */    switch (framebuffer) {
/*  58:    */    case 36053: 
/*  59: 59 */      break;
/*  60:    */    case 36054: 
/*  61: 61 */      throw new SlickException("FrameBuffer: " + this.FBO + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT exception");
/*  62:    */    
/*  63:    */    case 36055: 
/*  64: 64 */      throw new SlickException("FrameBuffer: " + this.FBO + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT exception");
/*  65:    */    
/*  66:    */    case 36057: 
/*  67: 67 */      throw new SlickException("FrameBuffer: " + this.FBO + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT exception");
/*  68:    */    
/*  69:    */    case 36059: 
/*  70: 70 */      throw new SlickException("FrameBuffer: " + this.FBO + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT exception");
/*  71:    */    
/*  72:    */    case 36058: 
/*  73: 73 */      throw new SlickException("FrameBuffer: " + this.FBO + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT exception");
/*  74:    */    
/*  75:    */    case 36060: 
/*  76: 76 */      throw new SlickException("FrameBuffer: " + this.FBO + ", has caused a GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT exception");
/*  77:    */    case 36056: 
/*  78:    */    default: 
/*  79: 79 */      throw new SlickException("Unexpected reply from glCheckFramebufferStatusEXT: " + framebuffer);
/*  80:    */    }
/*  81:    */    
/*  82:    */  }
/*  83:    */  
/*  86:    */  private void init()
/*  87:    */    throws SlickException
/*  88:    */  {
/*  89: 89 */    IntBuffer buffer = BufferUtils.createIntBuffer(1);
/*  90: 90 */    EXTFramebufferObject.glGenFramebuffersEXT(buffer);
/*  91: 91 */    this.FBO = buffer.get();
/*  92:    */    
/*  94:    */    try
/*  95:    */    {
/*  96: 96 */      Texture tex = InternalTextureLoader.get().createTexture(this.image.getWidth(), this.image.getHeight(), this.image.getFilter());
/*  97:    */      
/*  98: 98 */      EXTFramebufferObject.glBindFramebufferEXT(36160, this.FBO);
/*  99: 99 */      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, tex.getTextureID(), 0);
/* 100:    */      
/* 103:103 */      completeCheck();
/* 104:104 */      unbind();
/* 105:    */      
/* 107:107 */      clear();
/* 108:108 */      flush();
/* 109:    */      
/* 111:111 */      drawImage(this.image, 0.0F, 0.0F);
/* 112:112 */      this.image.setTexture(tex);
/* 113:    */    }
/* 114:    */    catch (Exception e) {
/* 115:115 */      throw new SlickException("Failed to create new texture for FBO");
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 121:    */  private void bind()
/* 122:    */  {
/* 123:123 */    EXTFramebufferObject.glBindFramebufferEXT(36160, this.FBO);
/* 124:124 */    GL11.glReadBuffer(36064);
/* 125:    */  }
/* 126:    */  
/* 129:    */  private void unbind()
/* 130:    */  {
/* 131:131 */    EXTFramebufferObject.glBindFramebufferEXT(36160, 0);
/* 132:132 */    GL11.glReadBuffer(1029);
/* 133:    */  }
/* 134:    */  
/* 137:    */  protected void disable()
/* 138:    */  {
/* 139:139 */    GL.flush();
/* 140:    */    
/* 141:141 */    unbind();
/* 142:142 */    GL11.glPopClientAttrib();
/* 143:143 */    GL11.glPopAttrib();
/* 144:144 */    GL11.glMatrixMode(5888);
/* 145:145 */    GL11.glPopMatrix();
/* 146:146 */    GL11.glMatrixMode(5889);
/* 147:147 */    GL11.glPopMatrix();
/* 148:148 */    GL11.glMatrixMode(5888);
/* 149:    */    
/* 150:150 */    SlickCallable.leaveSafeBlock();
/* 151:    */  }
/* 152:    */  
/* 155:    */  protected void enable()
/* 156:    */  {
/* 157:157 */    if (!this.valid) {
/* 158:158 */      throw new RuntimeException("Attempt to use a destroy()ed offscreen graphics context.");
/* 159:    */    }
/* 160:160 */    SlickCallable.enterSafeBlock();
/* 161:    */    
/* 162:162 */    GL11.glPushAttrib(1048575);
/* 163:163 */    GL11.glPushClientAttrib(-1);
/* 164:164 */    GL11.glMatrixMode(5889);
/* 165:165 */    GL11.glPushMatrix();
/* 166:166 */    GL11.glMatrixMode(5888);
/* 167:167 */    GL11.glPushMatrix();
/* 168:    */    
/* 169:169 */    bind();
/* 170:170 */    initGL();
/* 171:    */  }
/* 172:    */  
/* 175:    */  protected void initGL()
/* 176:    */  {
/* 177:177 */    GL11.glEnable(3553);
/* 178:178 */    GL11.glShadeModel(7425);
/* 179:179 */    GL11.glDisable(2929);
/* 180:180 */    GL11.glDisable(2896);
/* 181:    */    
/* 182:182 */    GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 183:183 */    GL11.glClearDepth(1.0D);
/* 184:    */    
/* 185:185 */    GL11.glEnable(3042);
/* 186:186 */    GL11.glBlendFunc(770, 771);
/* 187:    */    
/* 188:188 */    GL11.glViewport(0, 0, this.screenWidth, this.screenHeight);
/* 189:189 */    GL11.glMatrixMode(5888);
/* 190:190 */    GL11.glLoadIdentity();
/* 191:    */    
/* 192:192 */    enterOrtho();
/* 193:    */  }
/* 194:    */  
/* 197:    */  protected void enterOrtho()
/* 198:    */  {
/* 199:199 */    GL11.glMatrixMode(5889);
/* 200:200 */    GL11.glLoadIdentity();
/* 201:201 */    GL11.glOrtho(0.0D, this.screenWidth, 0.0D, this.screenHeight, 1.0D, -1.0D);
/* 202:202 */    GL11.glMatrixMode(5888);
/* 203:    */  }
/* 204:    */  
/* 207:    */  public void destroy()
/* 208:    */  {
/* 209:209 */    super.destroy();
/* 210:    */    
/* 211:211 */    IntBuffer buffer = BufferUtils.createIntBuffer(1);
/* 212:212 */    buffer.put(this.FBO);
/* 213:213 */    buffer.flip();
/* 214:    */    
/* 215:215 */    EXTFramebufferObject.glDeleteFramebuffersEXT(buffer);
/* 216:216 */    this.valid = false;
/* 217:    */  }
/* 218:    */  
/* 221:    */  public void flush()
/* 222:    */  {
/* 223:223 */    super.flush();
/* 224:    */    
/* 225:225 */    this.image.flushPixelData();
/* 226:    */  }
/* 227:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.pbuffer.FBOGraphics
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */