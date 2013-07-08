/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import org.newdawn.slick.opengl.ImageData;
/*   6:    */import org.newdawn.slick.util.GlUtil;
/*   7:    */
/*  30:    */public class ImageBuffer
/*  31:    */  implements ImageData
/*  32:    */{
/*  33:    */  private int width;
/*  34:    */  private int height;
/*  35:    */  private int texWidth;
/*  36:    */  private int texHeight;
/*  37:    */  private byte[] rawData;
/*  38:    */  
/*  39:    */  public ImageBuffer(int width, int height)
/*  40:    */  {
/*  41: 41 */    this.width = width;
/*  42: 42 */    this.height = height;
/*  43:    */    
/*  44: 44 */    this.texWidth = get2Fold(width);
/*  45: 45 */    this.texHeight = get2Fold(height);
/*  46:    */    
/*  47: 47 */    this.rawData = new byte[this.texWidth * this.texHeight * 4];
/*  48:    */  }
/*  49:    */  
/*  54:    */  public byte[] getRGBA()
/*  55:    */  {
/*  56: 56 */    return this.rawData;
/*  57:    */  }
/*  58:    */  
/*  61:    */  public int getDepth()
/*  62:    */  {
/*  63: 63 */    return 32;
/*  64:    */  }
/*  65:    */  
/*  68:    */  public int getHeight()
/*  69:    */  {
/*  70: 70 */    return this.height;
/*  71:    */  }
/*  72:    */  
/*  75:    */  public int getTexHeight()
/*  76:    */  {
/*  77: 77 */    return this.texHeight;
/*  78:    */  }
/*  79:    */  
/*  82:    */  public int getTexWidth()
/*  83:    */  {
/*  84: 84 */    return this.texWidth;
/*  85:    */  }
/*  86:    */  
/*  89:    */  public int getWidth()
/*  90:    */  {
/*  91: 91 */    return this.width;
/*  92:    */  }
/*  93:    */  
/*  96:    */  public ByteBuffer getImageBufferData()
/*  97:    */  {
/*  98: 98 */    ByteBuffer scratch = GlUtil.getDynamicByteBuffer(this.rawData.length);
/*  99: 99 */    scratch.put(this.rawData);
/* 100:100 */    scratch.flip();
/* 101:    */    
/* 102:102 */    return scratch;
/* 103:    */  }
/* 104:    */  
/* 114:    */  public void setRGBA(int x, int y, int r, int g, int b, int a)
/* 115:    */  {
/* 116:116 */    if ((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height)) {
/* 117:117 */      throw new RuntimeException("Specified location: " + x + "," + y + " outside of image");
/* 118:    */    }
/* 119:    */    
/* 120:120 */    int ofs = (x + y * this.texWidth) * 4;
/* 121:    */    
/* 122:122 */    if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
/* 123:123 */      this.rawData[ofs] = ((byte)b);
/* 124:124 */      this.rawData[(ofs + 1)] = ((byte)g);
/* 125:125 */      this.rawData[(ofs + 2)] = ((byte)r);
/* 126:126 */      this.rawData[(ofs + 3)] = ((byte)a);
/* 127:    */    } else {
/* 128:128 */      this.rawData[ofs] = ((byte)r);
/* 129:129 */      this.rawData[(ofs + 1)] = ((byte)g);
/* 130:130 */      this.rawData[(ofs + 2)] = ((byte)b);
/* 131:131 */      this.rawData[(ofs + 3)] = ((byte)a);
/* 132:    */    }
/* 133:    */  }
/* 134:    */  
/* 139:    */  public Image getImage()
/* 140:    */  {
/* 141:141 */    return new Image(this);
/* 142:    */  }
/* 143:    */  
/* 149:    */  public Image getImage(int filter)
/* 150:    */  {
/* 151:151 */    return new Image(this, filter);
/* 152:    */  }
/* 153:    */  
/* 159:    */  private int get2Fold(int fold)
/* 160:    */  {
/* 161:161 */    int ret = 2;
/* 162:162 */    while (ret < fold) {
/* 163:163 */      ret *= 2;
/* 164:    */    }
/* 165:165 */    return ret;
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.ImageBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */