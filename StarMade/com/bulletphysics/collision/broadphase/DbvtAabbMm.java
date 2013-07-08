package com.bulletphysics.collision.broadphase;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public class DbvtAabbMm
{
  private final Vector3f field_1872 = new Vector3f();
  private final Vector3f field_1873 = new Vector3f();
  
  public DbvtAabbMm() {}
  
  public DbvtAabbMm(DbvtAabbMm local_o)
  {
    set(local_o);
  }
  
  public void set(DbvtAabbMm local_o)
  {
    this.field_1872.set(local_o.field_1872);
    this.field_1873.set(local_o.field_1873);
  }
  
  public static void swap(DbvtAabbMm arg0, DbvtAabbMm arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.set(local_p1.field_1872);
      local_p1.field_1872.set(local_p2.field_1872);
      local_p2.field_1872.set(tmp);
      tmp.set(local_p1.field_1873);
      local_p1.field_1873.set(local_p2.field_1873);
      local_p2.field_1873.set(tmp);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public Vector3f Center(Vector3f out)
  {
    out.add(this.field_1872, this.field_1873);
    out.scale(0.5F);
    return out;
  }
  
  public Vector3f Lengths(Vector3f out)
  {
    out.sub(this.field_1873, this.field_1872);
    return out;
  }
  
  public Vector3f Extents(Vector3f out)
  {
    out.sub(this.field_1873, this.field_1872);
    out.scale(0.5F);
    return out;
  }
  
  public Vector3f Mins()
  {
    return this.field_1872;
  }
  
  public Vector3f Maxs()
  {
    return this.field_1873;
  }
  
  public static DbvtAabbMm FromCE(Vector3f local_c, Vector3f local_e, DbvtAabbMm out)
  {
    DbvtAabbMm box = out;
    box.field_1872.sub(local_c, local_e);
    box.field_1873.add(local_c, local_e);
    return box;
  }
  
  public static DbvtAabbMm FromCR(Vector3f arg0, float arg1, DbvtAabbMm arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.set(local_r, local_r, local_r);
      return FromCE(local_c, tmp, out);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static DbvtAabbMm FromMM(Vector3f local_mi, Vector3f local_mx, DbvtAabbMm out)
  {
    DbvtAabbMm box = out;
    box.field_1872.set(local_mi);
    box.field_1873.set(local_mx);
    return box;
  }
  
  public void Expand(Vector3f local_e)
  {
    this.field_1872.sub(local_e);
    this.field_1873.add(local_e);
  }
  
  public void SignedExpand(Vector3f local_e)
  {
    if (local_e.field_615 > 0.0F) {
      this.field_1873.field_615 += local_e.field_615;
    } else {
      this.field_1872.field_615 += local_e.field_615;
    }
    if (local_e.field_616 > 0.0F) {
      this.field_1873.field_616 += local_e.field_616;
    } else {
      this.field_1872.field_616 += local_e.field_616;
    }
    if (local_e.field_617 > 0.0F) {
      this.field_1873.field_617 += local_e.field_617;
    } else {
      this.field_1872.field_617 += local_e.field_617;
    }
  }
  
  public boolean Contain(DbvtAabbMm local_a)
  {
    return (this.field_1872.field_615 <= local_a.field_1872.field_615) && (this.field_1872.field_616 <= local_a.field_1872.field_616) && (this.field_1872.field_617 <= local_a.field_1872.field_617) && (this.field_1873.field_615 >= local_a.field_1873.field_615) && (this.field_1873.field_616 >= local_a.field_1873.field_616) && (this.field_1873.field_617 >= local_a.field_1873.field_617);
  }
  
  public int Classify(Vector3f arg1, float arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_pi = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_px = localStack.get$javax$vecmath$Vector3f();
      switch (local_s)
      {
      case 0: 
        local_px.set(this.field_1872.field_615, this.field_1872.field_616, this.field_1872.field_617);
        local_pi.set(this.field_1873.field_615, this.field_1873.field_616, this.field_1873.field_617);
        break;
      case 1: 
        local_px.set(this.field_1873.field_615, this.field_1872.field_616, this.field_1872.field_617);
        local_pi.set(this.field_1872.field_615, this.field_1873.field_616, this.field_1873.field_617);
        break;
      case 2: 
        local_px.set(this.field_1872.field_615, this.field_1873.field_616, this.field_1872.field_617);
        local_pi.set(this.field_1873.field_615, this.field_1872.field_616, this.field_1873.field_617);
        break;
      case 3: 
        local_px.set(this.field_1873.field_615, this.field_1873.field_616, this.field_1872.field_617);
        local_pi.set(this.field_1872.field_615, this.field_1872.field_616, this.field_1873.field_617);
        break;
      case 4: 
        local_px.set(this.field_1872.field_615, this.field_1872.field_616, this.field_1873.field_617);
        local_pi.set(this.field_1873.field_615, this.field_1873.field_616, this.field_1872.field_617);
        break;
      case 5: 
        local_px.set(this.field_1873.field_615, this.field_1872.field_616, this.field_1873.field_617);
        local_pi.set(this.field_1872.field_615, this.field_1873.field_616, this.field_1872.field_617);
        break;
      case 6: 
        local_px.set(this.field_1872.field_615, this.field_1873.field_616, this.field_1873.field_617);
        local_pi.set(this.field_1873.field_615, this.field_1872.field_616, this.field_1872.field_617);
        break;
      case 7: 
        local_px.set(this.field_1873.field_615, this.field_1873.field_616, this.field_1873.field_617);
        local_pi.set(this.field_1872.field_615, this.field_1872.field_616, this.field_1872.field_617);
      }
      if (local_n.dot(local_px) + local_o < 0.0F) {
        return -1;
      }
      if (local_n.dot(local_pi) + local_o >= 0.0F) {
        return 1;
      }
      return 0;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public float ProjectMinimum(Vector3f arg1, int arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f[] local_b = { this.field_1873, this.field_1872 };
      Vector3f local_p = localStack.get$javax$vecmath$Vector3f();
      local_p.set(local_b[(signs >> 0 & 0x1)].field_615, local_b[(signs >> 1 & 0x1)].field_616, local_b[(signs >> 2 & 0x1)].field_617);
      return local_p.dot(local_v);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static boolean Intersect(DbvtAabbMm local_a, DbvtAabbMm local_b)
  {
    return (local_a.field_1872.field_615 <= local_b.field_1873.field_615) && (local_a.field_1873.field_615 >= local_b.field_1872.field_615) && (local_a.field_1872.field_616 <= local_b.field_1873.field_616) && (local_a.field_1873.field_616 >= local_b.field_1872.field_616) && (local_a.field_1872.field_617 <= local_b.field_1873.field_617) && (local_a.field_1873.field_617 >= local_b.field_1872.field_617);
  }
  
  public static boolean Intersect(DbvtAabbMm arg0, DbvtAabbMm arg1, Transform arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_d0 = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_d1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      local_b.Center(local_d0);
      xform.transform(local_d0);
      local_d0.sub(local_a.Center(tmp));
      MatrixUtil.transposeTransform(local_d1, local_d0, xform.basis);
      float[] local_s0 = { 0.0F, 0.0F };
      float[] local_s1 = new float[2];
      local_s1[0] = xform.origin.dot(local_d0);
      local_s1[1] = local_s1[0];
      local_a.AddSpan(local_d0, local_s0, 0, local_s0, 1);
      local_b.AddSpan(local_d1, local_s1, 0, local_s1, 1);
      if (local_s0[0] > local_s1[1]) {
        return false;
      }
      return local_s0[1] >= local_s1[0];
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static boolean Intersect(DbvtAabbMm local_a, Vector3f local_b)
  {
    return (local_b.field_615 >= local_a.field_1872.field_615) && (local_b.field_616 >= local_a.field_1872.field_616) && (local_b.field_617 >= local_a.field_1872.field_617) && (local_b.field_615 <= local_a.field_1873.field_615) && (local_b.field_616 <= local_a.field_1873.field_616) && (local_b.field_617 <= local_a.field_1873.field_617);
  }
  
  public static boolean Intersect(DbvtAabbMm local_a, Vector3f org, Vector3f invdir, int[] signs)
  {
    Vector3f[] bounds = { local_a.field_1872, local_a.field_1873 };
    float txmin = (bounds[signs[0]].field_615 - org.field_615) * invdir.field_615;
    float txmax = (bounds[(1 - signs[0])].field_615 - org.field_615) * invdir.field_615;
    float tymin = (bounds[signs[1]].field_616 - org.field_616) * invdir.field_616;
    float tymax = (bounds[(1 - signs[1])].field_616 - org.field_616) * invdir.field_616;
    if ((txmin > tymax) || (tymin > txmax)) {
      return false;
    }
    if (tymin > txmin) {
      txmin = tymin;
    }
    if (tymax < txmax) {
      txmax = tymax;
    }
    float tzmin = (bounds[signs[2]].field_617 - org.field_617) * invdir.field_617;
    float tzmax = (bounds[(1 - signs[2])].field_617 - org.field_617) * invdir.field_617;
    if ((txmin > tzmax) || (tzmin > txmax)) {
      return false;
    }
    if (tzmin > txmin) {
      txmin = tzmin;
    }
    if (tzmax < txmax) {
      txmax = tzmax;
    }
    return txmax > 0.0F;
  }
  
  public static float Proximity(DbvtAabbMm arg0, DbvtAabbMm arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_d = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      local_d.add(local_a.field_1872, local_a.field_1873);
      tmp.add(local_b.field_1872, local_b.field_1873);
      local_d.sub(tmp);
      return Math.abs(local_d.field_615) + Math.abs(local_d.field_616) + Math.abs(local_d.field_617);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void Merge(DbvtAabbMm local_a, DbvtAabbMm local_b, DbvtAabbMm local_r)
  {
    for (int local_i = 0; local_i < 3; local_i++)
    {
      if (VectorUtil.getCoord(local_a.field_1872, local_i) < VectorUtil.getCoord(local_b.field_1872, local_i)) {
        VectorUtil.setCoord(local_r.field_1872, local_i, VectorUtil.getCoord(local_a.field_1872, local_i));
      } else {
        VectorUtil.setCoord(local_r.field_1872, local_i, VectorUtil.getCoord(local_b.field_1872, local_i));
      }
      if (VectorUtil.getCoord(local_a.field_1873, local_i) > VectorUtil.getCoord(local_b.field_1873, local_i)) {
        VectorUtil.setCoord(local_r.field_1873, local_i, VectorUtil.getCoord(local_a.field_1873, local_i));
      } else {
        VectorUtil.setCoord(local_r.field_1873, local_i, VectorUtil.getCoord(local_b.field_1873, local_i));
      }
    }
  }
  
  public static boolean NotEqual(DbvtAabbMm local_a, DbvtAabbMm local_b)
  {
    return (local_a.field_1872.field_615 != local_b.field_1872.field_615) || (local_a.field_1872.field_616 != local_b.field_1872.field_616) || (local_a.field_1872.field_617 != local_b.field_1872.field_617) || (local_a.field_1873.field_615 != local_b.field_1873.field_615) || (local_a.field_1873.field_616 != local_b.field_1873.field_616) || (local_a.field_1873.field_617 != local_b.field_1873.field_617);
  }
  
  private void AddSpan(Vector3f local_d, float[] smi, int smi_idx, float[] smx, int smx_idx)
  {
    for (int local_i = 0; local_i < 3; local_i++) {
      if (VectorUtil.getCoord(local_d, local_i) < 0.0F)
      {
        smi[smi_idx] += VectorUtil.getCoord(this.field_1873, local_i) * VectorUtil.getCoord(local_d, local_i);
        smx[smx_idx] += VectorUtil.getCoord(this.field_1872, local_i) * VectorUtil.getCoord(local_d, local_i);
      }
      else
      {
        smi[smi_idx] += VectorUtil.getCoord(this.field_1872, local_i) * VectorUtil.getCoord(local_d, local_i);
        smx[smx_idx] += VectorUtil.getCoord(this.field_1873, local_i) * VectorUtil.getCoord(local_d, local_i);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtAabbMm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */