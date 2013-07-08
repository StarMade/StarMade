package org.newdawn.slick.geom;

public class OverTriangulator
  implements Triangulator
{
  private float[][] triangles;
  
  public OverTriangulator(Triangulator tris)
  {
    this.triangles = new float[tris.getTriangleCount() * 6 * 3][2];
    int tcount = 0;
    for (int local_i = 0; local_i < tris.getTriangleCount(); local_i++)
    {
      float local_cx = 0.0F;
      float local_cy = 0.0F;
      for (int local_p = 0; local_p < 3; local_p++)
      {
        float[] local_pt = tris.getTrianglePoint(local_i, local_p);
        local_cx += local_pt[0];
        local_cy += local_pt[1];
      }
      local_cx /= 3.0F;
      local_cy /= 3.0F;
      for (int local_p = 0; local_p < 3; local_p++)
      {
        int local_pt = local_p + 1;
        if (local_pt > 2) {
          local_pt = 0;
        }
        float[] pt1 = tris.getTrianglePoint(local_i, local_p);
        float[] pt2 = tris.getTrianglePoint(local_i, local_pt);
        pt1[0] = ((pt1[0] + pt2[0]) / 2.0F);
        pt1[1] = ((pt1[1] + pt2[1]) / 2.0F);
        this.triangles[(tcount * 3 + 0)][0] = local_cx;
        this.triangles[(tcount * 3 + 0)][1] = local_cy;
        this.triangles[(tcount * 3 + 1)][0] = pt1[0];
        this.triangles[(tcount * 3 + 1)][1] = pt1[1];
        this.triangles[(tcount * 3 + 2)][0] = pt2[0];
        this.triangles[(tcount * 3 + 2)][1] = pt2[1];
        tcount++;
      }
      for (int local_p = 0; local_p < 3; local_p++)
      {
        int local_pt = local_p + 1;
        if (local_pt > 2) {
          local_pt = 0;
        }
        float[] pt1 = tris.getTrianglePoint(local_i, local_p);
        float[] pt2 = tris.getTrianglePoint(local_i, local_pt);
        pt2[0] = ((pt1[0] + pt2[0]) / 2.0F);
        pt2[1] = ((pt1[1] + pt2[1]) / 2.0F);
        this.triangles[(tcount * 3 + 0)][0] = local_cx;
        this.triangles[(tcount * 3 + 0)][1] = local_cy;
        this.triangles[(tcount * 3 + 1)][0] = pt1[0];
        this.triangles[(tcount * 3 + 1)][1] = pt1[1];
        this.triangles[(tcount * 3 + 2)][0] = pt2[0];
        this.triangles[(tcount * 3 + 2)][1] = pt2[1];
        tcount++;
      }
    }
  }
  
  public void addPolyPoint(float local_x, float local_y) {}
  
  public int getTriangleCount()
  {
    return this.triangles.length / 3;
  }
  
  public float[] getTrianglePoint(int tri, int local_i)
  {
    float[] local_pt = this.triangles[(tri * 3 + local_i)];
    return new float[] { local_pt[0], local_pt[1] };
  }
  
  public void startHole() {}
  
  public boolean triangulate()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.OverTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */