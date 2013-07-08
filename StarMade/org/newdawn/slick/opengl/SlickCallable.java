/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */import org.newdawn.slick.SlickException;
/*   5:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*   6:    */import org.newdawn.slick.opengl.renderer.SGL;
/*   7:    */
/*  44:    */public abstract class SlickCallable
/*  45:    */{
/*  46:    */  private static Texture lastUsed;
/*  47: 47 */  private static boolean inSafe = false;
/*  48:    */  
/*  54:    */  public static void enterSafeBlock()
/*  55:    */  {
/*  56: 56 */    if (inSafe) {
/*  57: 57 */      return;
/*  58:    */    }
/*  59:    */    
/*  60: 60 */    Renderer.get().flush();
/*  61: 61 */    lastUsed = TextureImpl.getLastBind();
/*  62: 62 */    TextureImpl.bindNone();
/*  63: 63 */    GL11.glPushAttrib(1048575);
/*  64: 64 */    GL11.glPushClientAttrib(-1);
/*  65: 65 */    GL11.glMatrixMode(5888);
/*  66: 66 */    GL11.glPushMatrix();
/*  67: 67 */    GL11.glMatrixMode(5889);
/*  68: 68 */    GL11.glPushMatrix();
/*  69: 69 */    GL11.glMatrixMode(5888);
/*  70:    */    
/*  71: 71 */    inSafe = true;
/*  72:    */  }
/*  73:    */  
/*  78:    */  public static void leaveSafeBlock()
/*  79:    */  {
/*  80: 80 */    if (!inSafe) {
/*  81: 81 */      return;
/*  82:    */    }
/*  83:    */    
/*  84: 84 */    GL11.glMatrixMode(5889);
/*  85: 85 */    GL11.glPopMatrix();
/*  86: 86 */    GL11.glMatrixMode(5888);
/*  87: 87 */    GL11.glPopMatrix();
/*  88: 88 */    GL11.glPopClientAttrib();
/*  89: 89 */    GL11.glPopAttrib();
/*  90:    */    
/*  91: 91 */    if (lastUsed != null) {
/*  92: 92 */      lastUsed.bind();
/*  93:    */    } else {
/*  94: 94 */      TextureImpl.bindNone();
/*  95:    */    }
/*  96:    */    
/*  97: 97 */    inSafe = false;
/*  98:    */  }
/*  99:    */  
/* 105:    */  public final void call()
/* 106:    */    throws SlickException
/* 107:    */  {
/* 108:108 */    enterSafeBlock();
/* 109:    */    
/* 110:110 */    performGLOperations();
/* 111:    */    
/* 112:112 */    leaveSafeBlock();
/* 113:    */  }
/* 114:    */  
/* 115:    */  protected abstract void performGLOperations()
/* 116:    */    throws SlickException;
/* 117:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.SlickCallable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */