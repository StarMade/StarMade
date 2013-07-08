/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */
/*  48:    */public class Quadric
/*  49:    */{
/*  50:    */  protected int drawStyle;
/*  51:    */  protected int orientation;
/*  52:    */  protected boolean textureFlag;
/*  53:    */  protected int normals;
/*  54:    */  
/*  55:    */  public Quadric()
/*  56:    */  {
/*  57: 57 */    this.drawStyle = 100012;
/*  58: 58 */    this.orientation = 100020;
/*  59: 59 */    this.textureFlag = false;
/*  60: 60 */    this.normals = 100000;
/*  61:    */  }
/*  62:    */  
/*  71:    */  protected void normal3f(float x, float y, float z)
/*  72:    */  {
/*  73: 73 */    float mag = (float)Math.sqrt(x * x + y * y + z * z);
/*  74: 74 */    if (mag > 1.0E-005F) {
/*  75: 75 */      x /= mag;
/*  76: 76 */      y /= mag;
/*  77: 77 */      z /= mag;
/*  78:    */    }
/*  79: 79 */    GL11.glNormal3f(x, y, z);
/*  80:    */  }
/*  81:    */  
/*  99:    */  public void setDrawStyle(int drawStyle)
/* 100:    */  {
/* 101:101 */    this.drawStyle = drawStyle;
/* 102:    */  }
/* 103:    */  
/* 116:    */  public void setNormals(int normals)
/* 117:    */  {
/* 118:118 */    this.normals = normals;
/* 119:    */  }
/* 120:    */  
/* 133:    */  public void setOrientation(int orientation)
/* 134:    */  {
/* 135:135 */    this.orientation = orientation;
/* 136:    */  }
/* 137:    */  
/* 148:    */  public void setTextureFlag(boolean textureFlag)
/* 149:    */  {
/* 150:150 */    this.textureFlag = textureFlag;
/* 151:    */  }
/* 152:    */  
/* 157:    */  public int getDrawStyle()
/* 158:    */  {
/* 159:159 */    return this.drawStyle;
/* 160:    */  }
/* 161:    */  
/* 165:    */  public int getNormals()
/* 166:    */  {
/* 167:167 */    return this.normals;
/* 168:    */  }
/* 169:    */  
/* 173:    */  public int getOrientation()
/* 174:    */  {
/* 175:175 */    return this.orientation;
/* 176:    */  }
/* 177:    */  
/* 181:    */  public boolean getTextureFlag()
/* 182:    */  {
/* 183:183 */    return this.textureFlag;
/* 184:    */  }
/* 185:    */  
/* 186:    */  protected void TXTR_COORD(float x, float y) {
/* 187:187 */    if (this.textureFlag) GL11.glTexCoord2f(x, y);
/* 188:    */  }
/* 189:    */  
/* 190:    */  protected float sin(float r)
/* 191:    */  {
/* 192:192 */    return (float)Math.sin(r);
/* 193:    */  }
/* 194:    */  
/* 195:    */  protected float cos(float r) {
/* 196:196 */    return (float)Math.cos(r);
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Quadric
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */