package org.lwjgl.util.glu.tessellation;

class Normal
{
  static boolean SLANTED_SWEEP;
  static double S_UNIT_X;
  static double S_UNIT_Y;
  private static final boolean TRUE_PROJECT = false;
  
  private static double Dot(double[] local_u, double[] local_v)
  {
    return local_u[0] * local_v[0] + local_u[1] * local_v[1] + local_u[2] * local_v[2];
  }
  
  static void Normalize(double[] local_v)
  {
    double len = local_v[0] * local_v[0] + local_v[1] * local_v[1] + local_v[2] * local_v[2];
    assert (len > 0.0D);
    len = Math.sqrt(len);
    local_v[0] /= len;
    local_v[1] /= len;
    local_v[2] /= len;
  }
  
  static int LongAxis(double[] local_v)
  {
    int local_i = 0;
    if (Math.abs(local_v[1]) > Math.abs(local_v[0])) {
      local_i = 1;
    }
    if (Math.abs(local_v[2]) > Math.abs(local_v[local_i])) {
      local_i = 2;
    }
    return local_i;
  }
  
  static void ComputeNormal(GLUtessellatorImpl tess, double[] norm)
  {
    GLUvertex vHead = tess.mesh.vHead;
    double[] maxVal = new double[3];
    double[] minVal = new double[3];
    GLUvertex[] minVert = new GLUvertex[3];
    GLUvertex[] maxVert = new GLUvertex[3];
    double[] local_d1 = new double[3];
    double[] local_d2 = new double[3];
    double[] tNorm = new double[3];
    double tmp60_59 = (maxVal[2] = -2.0E+150D);
    maxVal[1] = tmp60_59;
    maxVal[0] = tmp60_59;
    double tmp77_76 = (minVal[2] = 2.0E+150D);
    minVal[1] = tmp77_76;
    minVal[0] = tmp77_76;
    for (GLUvertex local_v = vHead.next; local_v != vHead; local_v = local_v.next) {
      for (int local_i = 0; local_i < 3; local_i++)
      {
        double local_c = local_v.coords[local_i];
        if (local_c < minVal[local_i])
        {
          minVal[local_i] = local_c;
          minVert[local_i] = local_v;
        }
        if (local_c > maxVal[local_i])
        {
          maxVal[local_i] = local_c;
          maxVert[local_i] = local_v;
        }
      }
    }
    int local_i = 0;
    if (maxVal[1] - minVal[1] > maxVal[0] - minVal[0]) {
      local_i = 1;
    }
    if (maxVal[2] - minVal[2] > maxVal[local_i] - minVal[local_i]) {
      local_i = 2;
    }
    if (minVal[local_i] >= maxVal[local_i])
    {
      norm[0] = 0.0D;
      norm[1] = 0.0D;
      norm[2] = 1.0D;
      return;
    }
    double maxLen2 = 0.0D;
    GLUvertex local_v1 = minVert[local_i];
    GLUvertex local_v2 = maxVert[local_i];
    local_d1[0] = (local_v1.coords[0] - local_v2.coords[0]);
    local_d1[1] = (local_v1.coords[1] - local_v2.coords[1]);
    local_d1[2] = (local_v1.coords[2] - local_v2.coords[2]);
    for (local_v = vHead.next; local_v != vHead; local_v = local_v.next)
    {
      local_d2[0] = (local_v.coords[0] - local_v2.coords[0]);
      local_d2[1] = (local_v.coords[1] - local_v2.coords[1]);
      local_d2[2] = (local_v.coords[2] - local_v2.coords[2]);
      tNorm[0] = (local_d1[1] * local_d2[2] - local_d1[2] * local_d2[1]);
      tNorm[1] = (local_d1[2] * local_d2[0] - local_d1[0] * local_d2[2]);
      tNorm[2] = (local_d1[0] * local_d2[1] - local_d1[1] * local_d2[0]);
      double tLen2 = tNorm[0] * tNorm[0] + tNorm[1] * tNorm[1] + tNorm[2] * tNorm[2];
      if (tLen2 > maxLen2)
      {
        maxLen2 = tLen2;
        norm[0] = tNorm[0];
        norm[1] = tNorm[1];
        norm[2] = tNorm[2];
      }
    }
    if (maxLen2 <= 0.0D)
    {
      double tmp547_546 = (norm[2] = 0.0D);
      norm[1] = tmp547_546;
      norm[0] = tmp547_546;
      norm[LongAxis(local_d1)] = 1.0D;
    }
  }
  
  static void CheckOrientation(GLUtessellatorImpl tess)
  {
    GLUface fHead = tess.mesh.fHead;
    GLUvertex vHead = tess.mesh.vHead;
    double area = 0.0D;
    for (GLUface local_f = fHead.next; local_f != fHead; local_f = local_f.next)
    {
      GLUhalfEdge local_e = local_f.anEdge;
      if (local_e.winding > 0) {
        do
        {
          area += (local_e.Org.field_2227 - local_e.Sym.Org.field_2227) * (local_e.Org.field_2228 + local_e.Sym.Org.field_2228);
          local_e = local_e.Lnext;
        } while (local_e != local_f.anEdge);
      }
    }
    if (area < 0.0D)
    {
      for (GLUvertex local_v = vHead.next; local_v != vHead; local_v = local_v.next) {
        local_v.field_2228 = (-local_v.field_2228);
      }
      tess.tUnit[0] = (-tess.tUnit[0]);
      tess.tUnit[1] = (-tess.tUnit[1]);
      tess.tUnit[2] = (-tess.tUnit[2]);
    }
  }
  
  public static void __gl_projectPolygon(GLUtessellatorImpl tess)
  {
    GLUvertex vHead = tess.mesh.vHead;
    double[] norm = new double[3];
    boolean computedNormal = false;
    norm[0] = tess.normal[0];
    norm[1] = tess.normal[1];
    norm[2] = tess.normal[2];
    if ((norm[0] == 0.0D) && (norm[1] == 0.0D) && (norm[2] == 0.0D))
    {
      ComputeNormal(tess, norm);
      computedNormal = true;
    }
    double[] sUnit = tess.sUnit;
    double[] tUnit = tess.tUnit;
    int local_i = LongAxis(norm);
    sUnit[local_i] = 0.0D;
    sUnit[((local_i + 1) % 3)] = S_UNIT_X;
    sUnit[((local_i + 2) % 3)] = S_UNIT_Y;
    tUnit[local_i] = 0.0D;
    tUnit[((local_i + 1) % 3)] = (norm[local_i] > 0.0D ? -S_UNIT_Y : S_UNIT_Y);
    tUnit[((local_i + 2) % 3)] = (norm[local_i] > 0.0D ? S_UNIT_X : -S_UNIT_X);
    for (GLUvertex local_v = vHead.next; local_v != vHead; local_v = local_v.next)
    {
      local_v.field_2227 = Dot(local_v.coords, sUnit);
      local_v.field_2228 = Dot(local_v.coords, tUnit);
    }
    if (computedNormal) {
      CheckOrientation(tess);
    }
  }
  
  static
  {
    if (SLANTED_SWEEP)
    {
      S_UNIT_X = 0.5094153956495539D;
      S_UNIT_Y = 0.8605207462201063D;
    }
    else
    {
      S_UNIT_X = 1.0D;
      S_UNIT_Y = 0.0D;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Normal
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */