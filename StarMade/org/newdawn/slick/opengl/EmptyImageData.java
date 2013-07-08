/*  1:   */package org.newdawn.slick.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.newdawn.slick.util.GlUtil;
/*  5:   */
/* 18:   */public class EmptyImageData
/* 19:   */  implements ImageData
/* 20:   */{
/* 21:   */  private int width;
/* 22:   */  private int height;
/* 23:   */  
/* 24:   */  public EmptyImageData(int width, int height)
/* 25:   */  {
/* 26:26 */    this.width = width;
/* 27:27 */    this.height = height;
/* 28:   */  }
/* 29:   */  
/* 32:   */  public int getDepth()
/* 33:   */  {
/* 34:34 */    return 32;
/* 35:   */  }
/* 36:   */  
/* 39:   */  public int getHeight()
/* 40:   */  {
/* 41:41 */    return this.height;
/* 42:   */  }
/* 43:   */  
/* 46:   */  public ByteBuffer getImageBufferData()
/* 47:   */  {
/* 48:48 */    return GlUtil.getDynamicByteBuffer(getTexWidth() * getTexHeight() * 4);
/* 49:   */  }
/* 50:   */  
/* 53:   */  public int getTexHeight()
/* 54:   */  {
/* 55:55 */    return InternalTextureLoader.get2Fold(this.height);
/* 56:   */  }
/* 57:   */  
/* 60:   */  public int getTexWidth()
/* 61:   */  {
/* 62:62 */    return InternalTextureLoader.get2Fold(this.width);
/* 63:   */  }
/* 64:   */  
/* 67:   */  public int getWidth()
/* 68:   */  {
/* 69:69 */    return this.width;
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.EmptyImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */