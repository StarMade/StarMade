package org.lwjgl.util.glu.tessellation;

class Geom
{
  static double EdgeEval(GLUvertex local_u, GLUvertex local_v, GLUvertex local_w)
  {
    assert ((VertLeq(local_u, local_v)) && (VertLeq(local_v, local_w)));
    double gapL = local_v.field_2227 - local_u.field_2227;
    double gapR = local_w.field_2227 - local_v.field_2227;
    if (gapL + gapR > 0.0D)
    {
      if (gapL < gapR) {
        return local_v.field_2228 - local_u.field_2228 + (local_u.field_2228 - local_w.field_2228) * (gapL / (gapL + gapR));
      }
      return local_v.field_2228 - local_w.field_2228 + (local_w.field_2228 - local_u.field_2228) * (gapR / (gapL + gapR));
    }
    return 0.0D;
  }
  
  static double EdgeSign(GLUvertex local_u, GLUvertex local_v, GLUvertex local_w)
  {
    assert ((VertLeq(local_u, local_v)) && (VertLeq(local_v, local_w)));
    double gapL = local_v.field_2227 - local_u.field_2227;
    double gapR = local_w.field_2227 - local_v.field_2227;
    if (gapL + gapR > 0.0D) {
      return (local_v.field_2228 - local_w.field_2228) * gapL + (local_v.field_2228 - local_u.field_2228) * gapR;
    }
    return 0.0D;
  }
  
  static double TransEval(GLUvertex local_u, GLUvertex local_v, GLUvertex local_w)
  {
    assert ((TransLeq(local_u, local_v)) && (TransLeq(local_v, local_w)));
    double gapL = local_v.field_2228 - local_u.field_2228;
    double gapR = local_w.field_2228 - local_v.field_2228;
    if (gapL + gapR > 0.0D)
    {
      if (gapL < gapR) {
        return local_v.field_2227 - local_u.field_2227 + (local_u.field_2227 - local_w.field_2227) * (gapL / (gapL + gapR));
      }
      return local_v.field_2227 - local_w.field_2227 + (local_w.field_2227 - local_u.field_2227) * (gapR / (gapL + gapR));
    }
    return 0.0D;
  }
  
  static double TransSign(GLUvertex local_u, GLUvertex local_v, GLUvertex local_w)
  {
    assert ((TransLeq(local_u, local_v)) && (TransLeq(local_v, local_w)));
    double gapL = local_v.field_2228 - local_u.field_2228;
    double gapR = local_w.field_2228 - local_v.field_2228;
    if (gapL + gapR > 0.0D) {
      return (local_v.field_2227 - local_w.field_2227) * gapL + (local_v.field_2227 - local_u.field_2227) * gapR;
    }
    return 0.0D;
  }
  
  static boolean VertCCW(GLUvertex local_u, GLUvertex local_v, GLUvertex local_w)
  {
    return local_u.field_2227 * (local_v.field_2228 - local_w.field_2228) + local_v.field_2227 * (local_w.field_2228 - local_u.field_2228) + local_w.field_2227 * (local_u.field_2228 - local_v.field_2228) >= 0.0D;
  }
  
  static double Interpolate(double local_a, double local_x, double local_b, double local_y)
  {
    local_a = local_a < 0.0D ? 0.0D : local_a;
    local_b = local_b < 0.0D ? 0.0D : local_b;
    if (local_a <= local_b)
    {
      if (local_b == 0.0D) {
        return (local_x + local_y) / 2.0D;
      }
      return local_x + (local_y - local_x) * (local_a / (local_a + local_b));
    }
    return local_y + (local_x - local_y) * (local_b / (local_a + local_b));
  }
  
  static void EdgeIntersect(GLUvertex local_o1, GLUvertex local_d1, GLUvertex local_o2, GLUvertex local_d2, GLUvertex local_v)
  {
    if (!VertLeq(local_o1, local_d1))
    {
      GLUvertex temp = local_o1;
      local_o1 = local_d1;
      local_d1 = temp;
    }
    if (!VertLeq(local_o2, local_d2))
    {
      GLUvertex temp = local_o2;
      local_o2 = local_d2;
      local_d2 = temp;
    }
    if (!VertLeq(local_o1, local_o2))
    {
      GLUvertex temp = local_o1;
      local_o1 = local_o2;
      local_o2 = temp;
      temp = local_d1;
      local_d1 = local_d2;
      local_d2 = temp;
    }
    if (!VertLeq(local_o2, local_d1))
    {
      local_v.field_2227 = ((local_o2.field_2227 + local_d1.field_2227) / 2.0D);
    }
    else if (VertLeq(local_d1, local_d2))
    {
      double local_z1 = EdgeEval(local_o1, local_o2, local_d1);
      double local_z2 = EdgeEval(local_o2, local_d1, local_d2);
      if (local_z1 + local_z2 < 0.0D)
      {
        local_z1 = -local_z1;
        local_z2 = -local_z2;
      }
      local_v.field_2227 = Interpolate(local_z1, local_o2.field_2227, local_z2, local_d1.field_2227);
    }
    else
    {
      double local_z1 = EdgeSign(local_o1, local_o2, local_d1);
      double local_z2 = -EdgeSign(local_o1, local_d2, local_d1);
      if (local_z1 + local_z2 < 0.0D)
      {
        local_z1 = -local_z1;
        local_z2 = -local_z2;
      }
      local_v.field_2227 = Interpolate(local_z1, local_o2.field_2227, local_z2, local_d2.field_2227);
    }
    if (!TransLeq(local_o1, local_d1))
    {
      GLUvertex temp = local_o1;
      local_o1 = local_d1;
      local_d1 = temp;
    }
    if (!TransLeq(local_o2, local_d2))
    {
      GLUvertex temp = local_o2;
      local_o2 = local_d2;
      local_d2 = temp;
    }
    if (!TransLeq(local_o1, local_o2))
    {
      GLUvertex temp = local_o2;
      local_o2 = local_o1;
      local_o1 = temp;
      temp = local_d2;
      local_d2 = local_d1;
      local_d1 = temp;
    }
    if (!TransLeq(local_o2, local_d1))
    {
      local_v.field_2228 = ((local_o2.field_2228 + local_d1.field_2228) / 2.0D);
    }
    else if (TransLeq(local_d1, local_d2))
    {
      double local_z1 = TransEval(local_o1, local_o2, local_d1);
      double local_z2 = TransEval(local_o2, local_d1, local_d2);
      if (local_z1 + local_z2 < 0.0D)
      {
        local_z1 = -local_z1;
        local_z2 = -local_z2;
      }
      local_v.field_2228 = Interpolate(local_z1, local_o2.field_2228, local_z2, local_d1.field_2228);
    }
    else
    {
      double local_z1 = TransSign(local_o1, local_o2, local_d1);
      double local_z2 = -TransSign(local_o1, local_d2, local_d1);
      if (local_z1 + local_z2 < 0.0D)
      {
        local_z1 = -local_z1;
        local_z2 = -local_z2;
      }
      local_v.field_2228 = Interpolate(local_z1, local_o2.field_2228, local_z2, local_d2.field_2228);
    }
  }
  
  static boolean VertEq(GLUvertex local_u, GLUvertex local_v)
  {
    return (local_u.field_2227 == local_v.field_2227) && (local_u.field_2228 == local_v.field_2228);
  }
  
  static boolean VertLeq(GLUvertex local_u, GLUvertex local_v)
  {
    return (local_u.field_2227 < local_v.field_2227) || ((local_u.field_2227 == local_v.field_2227) && (local_u.field_2228 <= local_v.field_2228));
  }
  
  static boolean TransLeq(GLUvertex local_u, GLUvertex local_v)
  {
    return (local_u.field_2228 < local_v.field_2228) || ((local_u.field_2228 == local_v.field_2228) && (local_u.field_2227 <= local_v.field_2227));
  }
  
  static boolean EdgeGoesLeft(GLUhalfEdge local_e)
  {
    return VertLeq(local_e.Sym.Org, local_e.Org);
  }
  
  static boolean EdgeGoesRight(GLUhalfEdge local_e)
  {
    return VertLeq(local_e.Org, local_e.Sym.Org);
  }
  
  static double VertL1dist(GLUvertex local_u, GLUvertex local_v)
  {
    return Math.abs(local_u.field_2227 - local_v.field_2227) + Math.abs(local_u.field_2228 - local_v.field_2228);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Geom
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */