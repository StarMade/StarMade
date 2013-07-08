package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.AxisSweep3Internal.EdgeArray;
import java.io.PrintStream;

public class AxisSweep3Ext$EdgeArrayImplExt
  extends AxisSweep3Internal.EdgeArray
{
  private short[] pos;
  private short[] handle;
  
  public AxisSweep3Ext$EdgeArrayImplExt(int paramInt)
  {
    this.pos = new short[paramInt];
    this.handle = new short[paramInt];
  }
  
  public void insert(EdgeArrayImplExt paramEdgeArrayImplExt)
  {
    System.err.println("[Physics][AXIS-SWEEP] EDGE ARRAY INSERTING grow: " + paramEdgeArrayImplExt.pos.length + " -> " + this.pos.length);
    for (int i = 0; i < paramEdgeArrayImplExt.pos.length; i++)
    {
      this.pos[i] = paramEdgeArrayImplExt.pos[i];
      this.handle[i] = paramEdgeArrayImplExt.handle[i];
    }
  }
  
  public void swap(int paramInt1, int paramInt2)
  {
    int i = this.pos[paramInt1];
    int j = this.handle[paramInt1];
    this.pos[paramInt1] = this.pos[paramInt2];
    this.handle[paramInt1] = this.handle[paramInt2];
    this.pos[paramInt2] = i;
    this.handle[paramInt2] = j;
  }
  
  public void set(int paramInt1, int paramInt2)
  {
    this.pos[paramInt1] = this.pos[paramInt2];
    this.handle[paramInt1] = this.handle[paramInt2];
  }
  
  public int getPos(int paramInt)
  {
    return this.pos[paramInt] & 0xFFFF;
  }
  
  public void setPos(int paramInt1, int paramInt2)
  {
    this.pos[paramInt1] = ((short)paramInt2);
  }
  
  public int getHandle(int paramInt)
  {
    return this.handle[paramInt] & 0xFFFF;
  }
  
  public void setHandle(int paramInt1, int paramInt2)
  {
    this.handle[paramInt1] = ((short)paramInt2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.AxisSweep3Ext.EdgeArrayImplExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */