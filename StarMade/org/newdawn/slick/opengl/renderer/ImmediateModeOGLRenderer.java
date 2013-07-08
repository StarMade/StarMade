package org.newdawn.slick.opengl.renderer;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTSecondaryColor;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class ImmediateModeOGLRenderer
  implements SGL
{
  private int width;
  private int height;
  private float[] current = { 1.0F, 1.0F, 1.0F, 1.0F };
  protected float alphaScale = 1.0F;
  
  public void initDisplay(int width, int height)
  {
    this.width = width;
    this.height = height;
    String extensions = GL11.glGetString(7939);
    GL11.glEnable(3553);
    GL11.glShadeModel(7425);
    GL11.glDisable(2929);
    GL11.glDisable(2896);
    GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
    GL11.glClearDepth(1.0D);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glViewport(0, 0, width, height);
    GL11.glMatrixMode(5888);
  }
  
  public void enterOrtho(int xsize, int ysize)
  {
    GL11.glMatrixMode(5889);
    GL11.glLoadIdentity();
    GL11.glOrtho(0.0D, this.width, this.height, 0.0D, 1.0D, -1.0D);
    GL11.glMatrixMode(5888);
    GL11.glTranslatef((this.width - xsize) / 2, (this.height - ysize) / 2, 0.0F);
  }
  
  public void glBegin(int geomType)
  {
    GL11.glBegin(geomType);
  }
  
  public void glBindTexture(int target, int local_id)
  {
    GL11.glBindTexture(target, local_id);
  }
  
  public void glBlendFunc(int src, int dest)
  {
    GL11.glBlendFunc(src, dest);
  }
  
  public void glCallList(int local_id)
  {
    GL11.glCallList(local_id);
  }
  
  public void glClear(int value)
  {
    GL11.glClear(value);
  }
  
  public void glClearColor(float red, float green, float blue, float alpha)
  {
    GL11.glClearColor(red, green, blue, alpha);
  }
  
  public void glClipPlane(int plane, DoubleBuffer buffer)
  {
    GL11.glClipPlane(plane, buffer);
  }
  
  public void glColor4f(float local_r, float local_g, float local_b, float local_a)
  {
    local_a *= this.alphaScale;
    this.current[0] = local_r;
    this.current[1] = local_g;
    this.current[2] = local_b;
    this.current[3] = local_a;
    GL11.glColor4f(local_r, local_g, local_b, local_a);
  }
  
  public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha)
  {
    GL11.glColorMask(red, green, blue, alpha);
  }
  
  public void glCopyTexImage2D(int target, int level, int internalFormat, int local_x, int local_y, int width, int height, int border)
  {
    GL11.glCopyTexImage2D(target, level, internalFormat, local_x, local_y, width, height, border);
  }
  
  public void glDeleteTextures(IntBuffer buffer)
  {
    GL11.glDeleteTextures(buffer);
  }
  
  public void glDisable(int item)
  {
    GL11.glDisable(item);
  }
  
  public void glEnable(int item)
  {
    GL11.glEnable(item);
  }
  
  public void glEnd() {}
  
  public void glEndList() {}
  
  public int glGenLists(int count)
  {
    return GL11.glGenLists(count);
  }
  
  public void glGetFloat(int local_id, FloatBuffer ret)
  {
    GL11.glGetFloat(local_id, ret);
  }
  
  public void glGetInteger(int local_id, IntBuffer ret)
  {
    GL11.glGetInteger(local_id, ret);
  }
  
  public void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels)
  {
    GL11.glGetTexImage(target, level, format, type, pixels);
  }
  
  public void glLineWidth(float width)
  {
    GL11.glLineWidth(width);
  }
  
  public void glLoadIdentity() {}
  
  public void glNewList(int local_id, int option)
  {
    GL11.glNewList(local_id, option);
  }
  
  public void glPointSize(float size)
  {
    GL11.glPointSize(size);
  }
  
  public void glPopMatrix() {}
  
  public void glPushMatrix() {}
  
  public void glReadPixels(int local_x, int local_y, int width, int height, int format, int type, ByteBuffer pixels)
  {
    GL11.glReadPixels(local_x, local_y, width, height, format, type, pixels);
  }
  
  public void glRotatef(float angle, float local_x, float local_y, float local_z)
  {
    GL11.glRotatef(angle, local_x, local_y, local_z);
  }
  
  public void glScalef(float local_x, float local_y, float local_z)
  {
    GL11.glScalef(local_x, local_y, local_z);
  }
  
  public void glScissor(int local_x, int local_y, int width, int height)
  {
    GL11.glScissor(local_x, local_y, width, height);
  }
  
  public void glTexCoord2f(float local_u, float local_v)
  {
    GL11.glTexCoord2f(local_u, local_v);
  }
  
  public void glTexEnvi(int target, int mode, int value)
  {
    GL11.glTexEnvi(target, mode, value);
  }
  
  public void glTranslatef(float local_x, float local_y, float local_z)
  {
    GL11.glTranslatef(local_x, local_y, local_z);
  }
  
  public void glVertex2f(float local_x, float local_y)
  {
    GL11.glVertex2f(local_x, local_y);
  }
  
  public void glVertex3f(float local_x, float local_y, float local_z)
  {
    GL11.glVertex3f(local_x, local_y, local_z);
  }
  
  public void flush() {}
  
  public void glTexParameteri(int target, int param, int value)
  {
    GL11.glTexParameteri(target, param, value);
  }
  
  public float[] getCurrentColor()
  {
    return this.current;
  }
  
  public void glDeleteLists(int list, int count)
  {
    GL11.glDeleteLists(list, count);
  }
  
  public void glClearDepth(float value)
  {
    GL11.glClearDepth(value);
  }
  
  public void glDepthFunc(int func)
  {
    GL11.glDepthFunc(func);
  }
  
  public void glDepthMask(boolean mask)
  {
    GL11.glDepthMask(mask);
  }
  
  public void setGlobalAlphaScale(float alphaScale)
  {
    this.alphaScale = alphaScale;
  }
  
  public void glLoadMatrix(FloatBuffer buffer)
  {
    GL11.glLoadMatrix(buffer);
  }
  
  public void glGenTextures(IntBuffer ids)
  {
    GL11.glGenTextures(ids);
  }
  
  public void glGetError()
  {
    GL11.glGetError();
  }
  
  public void glTexImage2D(int target, int local_i, int dstPixelFormat, int width, int height, int local_j, int srcPixelFormat, int glUnsignedByte, ByteBuffer textureBuffer)
  {
    GL11.glTexImage2D(target, local_i, dstPixelFormat, width, height, local_j, srcPixelFormat, glUnsignedByte, textureBuffer);
  }
  
  public void glTexSubImage2D(int glTexture2d, int local_i, int pageX, int pageY, int width, int height, int glBgra, int glUnsignedByte, ByteBuffer scratchByteBuffer)
  {
    GL11.glTexSubImage2D(glTexture2d, local_i, pageX, pageY, width, height, glBgra, glUnsignedByte, scratchByteBuffer);
  }
  
  public boolean canTextureMirrorClamp()
  {
    return GLContext.getCapabilities().GL_EXT_texture_mirror_clamp;
  }
  
  public boolean canSecondaryColor()
  {
    return GLContext.getCapabilities().GL_EXT_secondary_color;
  }
  
  public void glSecondaryColor3ubEXT(byte local_b, byte local_c, byte local_d)
  {
    EXTSecondaryColor.glSecondaryColor3ubEXT(local_b, local_c, local_d);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.renderer.ImmediateModeOGLRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */