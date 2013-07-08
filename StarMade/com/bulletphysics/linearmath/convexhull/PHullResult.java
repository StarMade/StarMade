package com.bulletphysics.linearmath.convexhull;

import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

class PHullResult
{
  public int vcount = 0;
  public int indexCount = 0;
  public int faceCount = 0;
  public ObjectArrayList<Vector3f> vertices = null;
  public IntArrayList indices = new IntArrayList();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.PHullResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */