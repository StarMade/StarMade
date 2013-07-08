package org.lwjgl.util.glu.tessellation;

import org.lwjgl.util.glu.GLUtessellator;
import org.lwjgl.util.glu.GLUtessellatorCallback;
import org.lwjgl.util.glu.GLUtessellatorCallbackAdapter;

public class GLUtessellatorImpl
  implements GLUtessellator
{
  public static final int TESS_MAX_CACHE = 100;
  private int state = 0;
  private GLUhalfEdge lastEdge;
  GLUmesh mesh;
  double[] normal = new double[3];
  double[] sUnit = new double[3];
  double[] tUnit = new double[3];
  private double relTolerance;
  int windingRule;
  boolean fatalError;
  Dict dict;
  PriorityQ field_367;
  GLUvertex event;
  boolean flagBoundary;
  boolean boundaryOnly;
  GLUface lonelyTriList;
  private boolean flushCacheOnNextVertex;
  int cacheCount;
  CachedVertex[] cache = new CachedVertex[100];
  private Object polygonData;
  private GLUtessellatorCallback callBegin;
  private GLUtessellatorCallback callEdgeFlag;
  private GLUtessellatorCallback callVertex;
  private GLUtessellatorCallback callEnd;
  private GLUtessellatorCallback callError;
  private GLUtessellatorCallback callCombine;
  private GLUtessellatorCallback callBeginData;
  private GLUtessellatorCallback callEdgeFlagData;
  private GLUtessellatorCallback callVertexData;
  private GLUtessellatorCallback callEndData;
  private GLUtessellatorCallback callErrorData;
  private GLUtessellatorCallback callCombineData;
  private static final double GLU_TESS_DEFAULT_TOLERANCE = 0.0D;
  private static GLUtessellatorCallback NULL_CB = new GLUtessellatorCallbackAdapter();
  
  public GLUtessellatorImpl()
  {
    this.normal[0] = 0.0D;
    this.normal[1] = 0.0D;
    this.normal[2] = 0.0D;
    this.relTolerance = 0.0D;
    this.windingRule = 100130;
    this.flagBoundary = false;
    this.boundaryOnly = false;
    this.callBegin = NULL_CB;
    this.callEdgeFlag = NULL_CB;
    this.callVertex = NULL_CB;
    this.callEnd = NULL_CB;
    this.callError = NULL_CB;
    this.callCombine = NULL_CB;
    this.callBeginData = NULL_CB;
    this.callEdgeFlagData = NULL_CB;
    this.callVertexData = NULL_CB;
    this.callEndData = NULL_CB;
    this.callErrorData = NULL_CB;
    this.callCombineData = NULL_CB;
    this.polygonData = null;
    for (int local_i = 0; local_i < this.cache.length; local_i++) {
      this.cache[local_i] = new CachedVertex();
    }
  }
  
  public static GLUtessellator gluNewTess()
  {
    return new GLUtessellatorImpl();
  }
  
  private void makeDormant()
  {
    if (this.mesh != null) {
      Mesh.__gl_meshDeleteMesh(this.mesh);
    }
    this.state = 0;
    this.lastEdge = null;
    this.mesh = null;
  }
  
  private void requireState(int newState)
  {
    if (this.state != newState) {
      gotoState(newState);
    }
  }
  
  private void gotoState(int newState)
  {
    while (this.state != newState) {
      if (this.state < newState)
      {
        if (this.state == 0)
        {
          callErrorOrErrorData(100151);
          gluTessBeginPolygon(null);
        }
        else if (this.state == 1)
        {
          callErrorOrErrorData(100152);
          gluTessBeginContour();
        }
      }
      else if (this.state == 2)
      {
        callErrorOrErrorData(100154);
        gluTessEndContour();
      }
      else if (this.state == 1)
      {
        callErrorOrErrorData(100153);
        makeDormant();
      }
    }
  }
  
  public void gluDeleteTess()
  {
    requireState(0);
  }
  
  public void gluTessProperty(int which, double value)
  {
    switch (which)
    {
    case 100142: 
      if ((value >= 0.0D) && (value <= 1.0D))
      {
        this.relTolerance = value;
        return;
      }
      break;
    case 100140: 
      int windingRule = (int)value;
      if (windingRule == value) {
        switch (windingRule)
        {
        case 100130: 
        case 100131: 
        case 100132: 
        case 100133: 
        case 100134: 
          this.windingRule = windingRule;
          return;
        }
      }
      break;
    case 100141: 
      this.boundaryOnly = (value != 0.0D);
      return;
    default: 
      callErrorOrErrorData(100900);
      return;
    }
    callErrorOrErrorData(100901);
  }
  
  public void gluGetTessProperty(int which, double[] value, int value_offset)
  {
    switch (which)
    {
    case 100142: 
      assert ((0.0D <= this.relTolerance) && (this.relTolerance <= 1.0D));
      value[value_offset] = this.relTolerance;
      break;
    case 100140: 
      assert ((this.windingRule == 100130) || (this.windingRule == 100131) || (this.windingRule == 100132) || (this.windingRule == 100133) || (this.windingRule == 100134));
      value[value_offset] = this.windingRule;
      break;
    case 100141: 
      assert ((this.boundaryOnly == true) || (!this.boundaryOnly));
      value[value_offset] = (this.boundaryOnly ? 1.0D : 0.0D);
      break;
    default: 
      value[value_offset] = 0.0D;
      callErrorOrErrorData(100900);
    }
  }
  
  public void gluTessNormal(double local_x, double local_y, double local_z)
  {
    this.normal[0] = local_x;
    this.normal[1] = local_y;
    this.normal[2] = local_z;
  }
  
  public void gluTessCallback(int which, GLUtessellatorCallback aCallback)
  {
    switch (which)
    {
    case 100100: 
      this.callBegin = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100106: 
      this.callBeginData = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100104: 
      this.callEdgeFlag = (aCallback == null ? NULL_CB : aCallback);
      this.flagBoundary = (aCallback != null);
      return;
    case 100110: 
      this.callEdgeFlagData = (this.callBegin = aCallback == null ? NULL_CB : aCallback);
      this.flagBoundary = (aCallback != null);
      return;
    case 100101: 
      this.callVertex = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100107: 
      this.callVertexData = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100102: 
      this.callEnd = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100108: 
      this.callEndData = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100103: 
      this.callError = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100109: 
      this.callErrorData = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100105: 
      this.callCombine = (aCallback == null ? NULL_CB : aCallback);
      return;
    case 100111: 
      this.callCombineData = (aCallback == null ? NULL_CB : aCallback);
      return;
    }
    callErrorOrErrorData(100900);
  }
  
  private boolean addVertex(double[] coords, Object vertexData)
  {
    GLUhalfEdge local_e = this.lastEdge;
    if (local_e == null)
    {
      local_e = Mesh.__gl_meshMakeEdge(this.mesh);
      if (local_e == null) {
        return false;
      }
      if (!Mesh.__gl_meshSplice(local_e, local_e.Sym)) {
        return false;
      }
    }
    else
    {
      if (Mesh.__gl_meshSplitEdge(local_e) == null) {
        return false;
      }
      local_e = local_e.Lnext;
    }
    local_e.Org.data = vertexData;
    local_e.Org.coords[0] = coords[0];
    local_e.Org.coords[1] = coords[1];
    local_e.Org.coords[2] = coords[2];
    local_e.winding = 1;
    local_e.Sym.winding = -1;
    this.lastEdge = local_e;
    return true;
  }
  
  private void cacheVertex(double[] coords, Object vertexData)
  {
    if (this.cache[this.cacheCount] == null) {
      this.cache[this.cacheCount] = new CachedVertex();
    }
    CachedVertex local_v = this.cache[this.cacheCount];
    local_v.data = vertexData;
    local_v.coords[0] = coords[0];
    local_v.coords[1] = coords[1];
    local_v.coords[2] = coords[2];
    this.cacheCount += 1;
  }
  
  private boolean flushCache()
  {
    CachedVertex[] local_v = this.cache;
    this.mesh = Mesh.__gl_meshNewMesh();
    if (this.mesh == null) {
      return false;
    }
    for (int local_i = 0; local_i < this.cacheCount; local_i++)
    {
      CachedVertex vertex = local_v[local_i];
      if (!addVertex(vertex.coords, vertex.data)) {
        return false;
      }
    }
    this.cacheCount = 0;
    this.flushCacheOnNextVertex = false;
    return true;
  }
  
  public void gluTessVertex(double[] coords, int coords_offset, Object vertexData)
  {
    boolean tooLarge = false;
    double[] clamped = new double[3];
    requireState(2);
    if (this.flushCacheOnNextVertex)
    {
      if (!flushCache())
      {
        callErrorOrErrorData(100902);
        return;
      }
      this.lastEdge = null;
    }
    for (int local_i = 0; local_i < 3; local_i++)
    {
      double local_x = coords[(local_i + coords_offset)];
      if (local_x < -1.0E+150D)
      {
        local_x = -1.0E+150D;
        tooLarge = true;
      }
      if (local_x > 1.0E+150D)
      {
        local_x = 1.0E+150D;
        tooLarge = true;
      }
      clamped[local_i] = local_x;
    }
    if (tooLarge) {
      callErrorOrErrorData(100155);
    }
    if (this.mesh == null)
    {
      if (this.cacheCount < 100)
      {
        cacheVertex(clamped, vertexData);
        return;
      }
      if (!flushCache())
      {
        callErrorOrErrorData(100902);
        return;
      }
    }
    if (!addVertex(clamped, vertexData)) {
      callErrorOrErrorData(100902);
    }
  }
  
  public void gluTessBeginPolygon(Object data)
  {
    requireState(0);
    this.state = 1;
    this.cacheCount = 0;
    this.flushCacheOnNextVertex = false;
    this.mesh = null;
    this.polygonData = data;
  }
  
  public void gluTessBeginContour()
  {
    requireState(1);
    this.state = 2;
    this.lastEdge = null;
    if (this.cacheCount > 0) {
      this.flushCacheOnNextVertex = true;
    }
  }
  
  public void gluTessEndContour()
  {
    requireState(2);
    this.state = 1;
  }
  
  public void gluTessEndPolygon()
  {
    try
    {
      requireState(1);
      this.state = 0;
      if (this.mesh == null)
      {
        if ((!this.flagBoundary) && (Render.__gl_renderCache(this)))
        {
          this.polygonData = null;
          return;
        }
        if (!flushCache()) {
          throw new RuntimeException();
        }
      }
      Normal.__gl_projectPolygon(this);
      if (!Sweep.__gl_computeInterior(this)) {
        throw new RuntimeException();
      }
      GLUmesh mesh = this.mesh;
      if (!this.fatalError)
      {
        boolean local_rc = true;
        if (this.boundaryOnly) {
          local_rc = TessMono.__gl_meshSetWindingNumber(mesh, 1, true);
        } else {
          local_rc = TessMono.__gl_meshTessellateInterior(mesh);
        }
        if (!local_rc) {
          throw new RuntimeException();
        }
        Mesh.__gl_meshCheckMesh(mesh);
        if ((this.callBegin != NULL_CB) || (this.callEnd != NULL_CB) || (this.callVertex != NULL_CB) || (this.callEdgeFlag != NULL_CB) || (this.callBeginData != NULL_CB) || (this.callEndData != NULL_CB) || (this.callVertexData != NULL_CB) || (this.callEdgeFlagData != NULL_CB)) {
          if (this.boundaryOnly) {
            Render.__gl_renderBoundary(this, mesh);
          } else {
            Render.__gl_renderMesh(this, mesh);
          }
        }
      }
      Mesh.__gl_meshDeleteMesh(mesh);
      this.polygonData = null;
      mesh = null;
    }
    catch (Exception local_rc)
    {
      local_rc.printStackTrace();
      callErrorOrErrorData(100902);
    }
  }
  
  public void gluBeginPolygon()
  {
    gluTessBeginPolygon(null);
    gluTessBeginContour();
  }
  
  public void gluNextContour(int type)
  {
    gluTessEndContour();
    gluTessBeginContour();
  }
  
  public void gluEndPolygon()
  {
    gluTessEndContour();
    gluTessEndPolygon();
  }
  
  void callBeginOrBeginData(int local_a)
  {
    if (this.callBeginData != NULL_CB) {
      this.callBeginData.beginData(local_a, this.polygonData);
    } else {
      this.callBegin.begin(local_a);
    }
  }
  
  void callVertexOrVertexData(Object local_a)
  {
    if (this.callVertexData != NULL_CB) {
      this.callVertexData.vertexData(local_a, this.polygonData);
    } else {
      this.callVertex.vertex(local_a);
    }
  }
  
  void callEdgeFlagOrEdgeFlagData(boolean local_a)
  {
    if (this.callEdgeFlagData != NULL_CB) {
      this.callEdgeFlagData.edgeFlagData(local_a, this.polygonData);
    } else {
      this.callEdgeFlag.edgeFlag(local_a);
    }
  }
  
  void callEndOrEndData()
  {
    if (this.callEndData != NULL_CB) {
      this.callEndData.endData(this.polygonData);
    } else {
      this.callEnd.end();
    }
  }
  
  void callCombineOrCombineData(double[] coords, Object[] vertexData, float[] weights, Object[] outData)
  {
    if (this.callCombineData != NULL_CB) {
      this.callCombineData.combineData(coords, vertexData, weights, outData, this.polygonData);
    } else {
      this.callCombine.combine(coords, vertexData, weights, outData);
    }
  }
  
  void callErrorOrErrorData(int local_a)
  {
    if (this.callErrorData != NULL_CB) {
      this.callErrorData.errorData(local_a, this.polygonData);
    } else {
      this.callError.error(local_a);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUtessellatorImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */