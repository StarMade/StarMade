package org.lwjgl.util.glu.tessellation;

class Render
{
  private static final boolean USE_OPTIMIZED_CODE_PATH = false;
  private static final RenderFan renderFan = new RenderFan(null);
  private static final RenderStrip renderStrip = new RenderStrip(null);
  private static final RenderTriangle renderTriangle = new RenderTriangle(null);
  private static final int SIGN_INCONSISTENT = 2;
  
  public static void __gl_renderMesh(GLUtessellatorImpl tess, GLUmesh mesh)
  {
    tess.lonelyTriList = null;
    for (GLUface local_f = mesh.fHead.next; local_f != mesh.fHead; local_f = local_f.next) {
      local_f.marked = false;
    }
    for (local_f = mesh.fHead.next; local_f != mesh.fHead; local_f = local_f.next) {
      if ((local_f.inside) && (!local_f.marked))
      {
        RenderMaximumFaceGroup(tess, local_f);
        assert (local_f.marked);
      }
    }
    if (tess.lonelyTriList != null)
    {
      RenderLonelyTriangles(tess, tess.lonelyTriList);
      tess.lonelyTriList = null;
    }
  }
  
  static void RenderMaximumFaceGroup(GLUtessellatorImpl tess, GLUface fOrig)
  {
    GLUhalfEdge local_e = fOrig.anEdge;
    FaceCount max = new FaceCount(null);
    max.size = 1L;
    max.eStart = local_e;
    max.render = renderTriangle;
    if (!tess.flagBoundary)
    {
      FaceCount newFace = MaximumFan(local_e);
      if (newFace.size > max.size) {
        max = newFace;
      }
      newFace = MaximumFan(local_e.Lnext);
      if (newFace.size > max.size) {
        max = newFace;
      }
      newFace = MaximumFan(local_e.Onext.Sym);
      if (newFace.size > max.size) {
        max = newFace;
      }
      newFace = MaximumStrip(local_e);
      if (newFace.size > max.size) {
        max = newFace;
      }
      newFace = MaximumStrip(local_e.Lnext);
      if (newFace.size > max.size) {
        max = newFace;
      }
      newFace = MaximumStrip(local_e.Onext.Sym);
      if (newFace.size > max.size) {
        max = newFace;
      }
    }
    max.render.render(tess, max.eStart, max.size);
  }
  
  private static boolean Marked(GLUface local_f)
  {
    return (!local_f.inside) || (local_f.marked);
  }
  
  private static GLUface AddToTrail(GLUface local_f, GLUface local_t)
  {
    local_f.trail = local_t;
    local_f.marked = true;
    return local_f;
  }
  
  private static void FreeTrail(GLUface local_t)
  {
    while (local_t != null)
    {
      local_t.marked = false;
      local_t = local_t.trail;
    }
  }
  
  static FaceCount MaximumFan(GLUhalfEdge eOrig)
  {
    FaceCount newFace = new FaceCount(0L, null, renderFan, null);
    GLUface trail = null;
    for (GLUhalfEdge local_e = eOrig; !Marked(local_e.Lface); local_e = local_e.Onext)
    {
      trail = AddToTrail(local_e.Lface, trail);
      newFace.size += 1L;
    }
    for (local_e = eOrig; !Marked(local_e.Sym.Lface); local_e = local_e.Sym.Lnext)
    {
      trail = AddToTrail(local_e.Sym.Lface, trail);
      newFace.size += 1L;
    }
    newFace.eStart = local_e;
    FreeTrail(trail);
    return newFace;
  }
  
  private static boolean IsEven(long local_n)
  {
    return (local_n & 1L) == 0L;
  }
  
  static FaceCount MaximumStrip(GLUhalfEdge eOrig)
  {
    FaceCount newFace = new FaceCount(0L, null, renderStrip, null);
    long headSize = 0L;
    long tailSize = 0L;
    GLUface trail = null;
    for (GLUhalfEdge local_e = eOrig; !Marked(local_e.Lface); local_e = local_e.Onext)
    {
      trail = AddToTrail(local_e.Lface, trail);
      tailSize += 1L;
      local_e = local_e.Lnext.Sym;
      if (Marked(local_e.Lface)) {
        break;
      }
      trail = AddToTrail(local_e.Lface, trail);
      tailSize += 1L;
    }
    GLUhalfEdge eTail = local_e;
    for (local_e = eOrig; !Marked(local_e.Sym.Lface); local_e = local_e.Sym.Onext.Sym)
    {
      trail = AddToTrail(local_e.Sym.Lface, trail);
      headSize += 1L;
      local_e = local_e.Sym.Lnext;
      if (Marked(local_e.Sym.Lface)) {
        break;
      }
      trail = AddToTrail(local_e.Sym.Lface, trail);
      headSize += 1L;
    }
    GLUhalfEdge eHead = local_e;
    newFace.size = (tailSize + headSize);
    if (IsEven(tailSize))
    {
      newFace.eStart = eTail.Sym;
    }
    else if (IsEven(headSize))
    {
      newFace.eStart = eHead;
    }
    else
    {
      newFace.size -= 1L;
      newFace.eStart = eHead.Onext;
    }
    FreeTrail(trail);
    return newFace;
  }
  
  static void RenderLonelyTriangles(GLUtessellatorImpl tess, GLUface local_f)
  {
    int edgeState = -1;
    tess.callBeginOrBeginData(4);
    while (local_f != null)
    {
      GLUhalfEdge local_e = local_f.anEdge;
      do
      {
        if (tess.flagBoundary)
        {
          int newState = !local_e.Sym.Lface.inside ? 1 : 0;
          if (edgeState != newState)
          {
            edgeState = newState;
            tess.callEdgeFlagOrEdgeFlagData(edgeState != 0);
          }
        }
        tess.callVertexOrVertexData(local_e.Org.data);
        local_e = local_e.Lnext;
      } while (local_e != local_f.anEdge);
      local_f = local_f.trail;
    }
    tess.callEndOrEndData();
  }
  
  public static void __gl_renderBoundary(GLUtessellatorImpl tess, GLUmesh mesh)
  {
    for (GLUface local_f = mesh.fHead.next; local_f != mesh.fHead; local_f = local_f.next) {
      if (local_f.inside)
      {
        tess.callBeginOrBeginData(2);
        GLUhalfEdge local_e = local_f.anEdge;
        do
        {
          tess.callVertexOrVertexData(local_e.Org.data);
          local_e = local_e.Lnext;
        } while (local_e != local_f.anEdge);
        tess.callEndOrEndData();
      }
    }
  }
  
  static int ComputeNormal(GLUtessellatorImpl tess, double[] norm, boolean check)
  {
    CachedVertex[] local_v = tess.cache;
    int local_vn = tess.cacheCount;
    double[] local_n = new double[3];
    int sign = 0;
    if (!check)
    {
      double tmp32_31 = (norm[2] = 0.0D);
      norm[1] = tmp32_31;
      norm[0] = tmp32_31;
    }
    int local_vc = 1;
    double local_xc = local_v[local_vc].coords[0] - local_v[0].coords[0];
    double local_yc = local_v[local_vc].coords[1] - local_v[0].coords[1];
    double local_zc = local_v[local_vc].coords[2] - local_v[0].coords[2];
    for (;;)
    {
      local_vc++;
      if (local_vc >= local_vn) {
        break;
      }
      double local_xp = local_xc;
      double local_yp = local_yc;
      double local_zp = local_zc;
      local_xc = local_v[local_vc].coords[0] - local_v[0].coords[0];
      local_yc = local_v[local_vc].coords[1] - local_v[0].coords[1];
      local_zc = local_v[local_vc].coords[2] - local_v[0].coords[2];
      local_n[0] = (local_yp * local_zc - local_zp * local_yc);
      local_n[1] = (local_zp * local_xc - local_xp * local_zc);
      local_n[2] = (local_xp * local_yc - local_yp * local_xc);
      double dot = local_n[0] * norm[0] + local_n[1] * norm[1] + local_n[2] * norm[2];
      if (!check)
      {
        if (dot >= 0.0D)
        {
          norm[0] += local_n[0];
          norm[1] += local_n[1];
          norm[2] += local_n[2];
        }
        else
        {
          norm[0] -= local_n[0];
          norm[1] -= local_n[1];
          norm[2] -= local_n[2];
        }
      }
      else if (dot != 0.0D) {
        if (dot > 0.0D)
        {
          if (sign < 0) {
            return 2;
          }
          sign = 1;
        }
        else
        {
          if (sign > 0) {
            return 2;
          }
          sign = -1;
        }
      }
    }
    return sign;
  }
  
  public static boolean __gl_renderCache(GLUtessellatorImpl tess)
  {
    CachedVertex[] local_v = tess.cache;
    int local_vn = tess.cacheCount;
    double[] norm = new double[3];
    if (tess.cacheCount < 3) {
      return true;
    }
    norm[0] = tess.normal[0];
    norm[1] = tess.normal[1];
    norm[2] = tess.normal[2];
    if ((norm[0] == 0.0D) && (norm[1] == 0.0D) && (norm[2] == 0.0D)) {
      ComputeNormal(tess, norm, false);
    }
    int sign = ComputeNormal(tess, norm, true);
    if (sign == 2) {
      return false;
    }
    return sign == 0;
  }
  
  private static class RenderStrip
    implements Render.renderCallBack
  {
    public void render(GLUtessellatorImpl tess, GLUhalfEdge local_e, long size)
    {
      tess.callBeginOrBeginData(5);
      tess.callVertexOrVertexData(local_e.Org.data);
      tess.callVertexOrVertexData(local_e.Sym.Org.data);
      while (!Render.Marked(local_e.Lface))
      {
        local_e.Lface.marked = true;
        size -= 1L;
        local_e = local_e.Lnext.Sym;
        tess.callVertexOrVertexData(local_e.Org.data);
        if (Render.Marked(local_e.Lface)) {
          break;
        }
        local_e.Lface.marked = true;
        size -= 1L;
        local_e = local_e.Onext;
        tess.callVertexOrVertexData(local_e.Sym.Org.data);
      }
      assert (size == 0L);
      tess.callEndOrEndData();
    }
  }
  
  private static class RenderFan
    implements Render.renderCallBack
  {
    public void render(GLUtessellatorImpl tess, GLUhalfEdge local_e, long size)
    {
      tess.callBeginOrBeginData(6);
      tess.callVertexOrVertexData(local_e.Org.data);
      tess.callVertexOrVertexData(local_e.Sym.Org.data);
      while (!Render.Marked(local_e.Lface))
      {
        local_e.Lface.marked = true;
        size -= 1L;
        local_e = local_e.Onext;
        tess.callVertexOrVertexData(local_e.Sym.Org.data);
      }
      assert (size == 0L);
      tess.callEndOrEndData();
    }
  }
  
  private static class RenderTriangle
    implements Render.renderCallBack
  {
    public void render(GLUtessellatorImpl tess, GLUhalfEdge local_e, long size)
    {
      assert (size == 1L);
      tess.lonelyTriList = Render.AddToTrail(local_e.Lface, tess.lonelyTriList);
    }
  }
  
  private static abstract interface renderCallBack
  {
    public abstract void render(GLUtessellatorImpl paramGLUtessellatorImpl, GLUhalfEdge paramGLUhalfEdge, long paramLong);
  }
  
  private static class FaceCount
  {
    long size;
    GLUhalfEdge eStart;
    Render.renderCallBack render;
    
    private FaceCount() {}
    
    private FaceCount(long size, GLUhalfEdge eStart, Render.renderCallBack render)
    {
      this.size = size;
      this.eStart = eStart;
      this.render = render;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Render
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */