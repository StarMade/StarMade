package org.lwjgl.util.glu;

import org.lwjgl.opengl.GL11;

public class Quadric
{
  protected int drawStyle = 100012;
  protected int orientation = 100020;
  protected boolean textureFlag = false;
  protected int normals = 100000;
  
  protected void normal3f(float local_x, float local_y, float local_z)
  {
    float mag = (float)Math.sqrt(local_x * local_x + local_y * local_y + local_z * local_z);
    if (mag > 1.0E-005F)
    {
      local_x /= mag;
      local_y /= mag;
      local_z /= mag;
    }
    GL11.glNormal3f(local_x, local_y, local_z);
  }
  
  public void setDrawStyle(int drawStyle)
  {
    this.drawStyle = drawStyle;
  }
  
  public void setNormals(int normals)
  {
    this.normals = normals;
  }
  
  public void setOrientation(int orientation)
  {
    this.orientation = orientation;
  }
  
  public void setTextureFlag(boolean textureFlag)
  {
    this.textureFlag = textureFlag;
  }
  
  public int getDrawStyle()
  {
    return this.drawStyle;
  }
  
  public int getNormals()
  {
    return this.normals;
  }
  
  public int getOrientation()
  {
    return this.orientation;
  }
  
  public boolean getTextureFlag()
  {
    return this.textureFlag;
  }
  
  protected void TXTR_COORD(float local_x, float local_y)
  {
    if (this.textureFlag) {
      GL11.glTexCoord2f(local_x, local_y);
    }
  }
  
  protected float sin(float local_r)
  {
    return (float)Math.sin(local_r);
  }
  
  protected float cos(float local_r)
  {
    return (float)Math.cos(local_r);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.Quadric
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */