package org.lwjgl.util.glu.tessellation;

class Sweep
{
  private static final boolean TOLERANCE_NONZERO = false;
  private static final double SENTINEL_COORD = 4.0E+150D;
  
  private static void DebugEvent(GLUtessellatorImpl tess) {}
  
  private static void AddWinding(GLUhalfEdge eDst, GLUhalfEdge eSrc)
  {
    eDst.winding += eSrc.winding;
    eDst.Sym.winding += eSrc.Sym.winding;
  }
  
  private static ActiveRegion RegionBelow(ActiveRegion local_r)
  {
    return (ActiveRegion)Dict.dictKey(Dict.dictPred(local_r.nodeUp));
  }
  
  private static ActiveRegion RegionAbove(ActiveRegion local_r)
  {
    return (ActiveRegion)Dict.dictKey(Dict.dictSucc(local_r.nodeUp));
  }
  
  static boolean EdgeLeq(GLUtessellatorImpl tess, ActiveRegion reg1, ActiveRegion reg2)
  {
    GLUvertex event = tess.event;
    GLUhalfEdge local_e1 = reg1.eUp;
    GLUhalfEdge local_e2 = reg2.eUp;
    if (local_e1.Sym.Org == event)
    {
      if (local_e2.Sym.Org == event)
      {
        if (Geom.VertLeq(local_e1.Org, local_e2.Org)) {
          return Geom.EdgeSign(local_e2.Sym.Org, local_e1.Org, local_e2.Org) <= 0.0D;
        }
        return Geom.EdgeSign(local_e1.Sym.Org, local_e2.Org, local_e1.Org) >= 0.0D;
      }
      return Geom.EdgeSign(local_e2.Sym.Org, event, local_e2.Org) <= 0.0D;
    }
    if (local_e2.Sym.Org == event) {
      return Geom.EdgeSign(local_e1.Sym.Org, event, local_e1.Org) >= 0.0D;
    }
    double local_t1 = Geom.EdgeEval(local_e1.Sym.Org, event, local_e1.Org);
    double local_t2 = Geom.EdgeEval(local_e2.Sym.Org, event, local_e2.Org);
    return local_t1 >= local_t2;
  }
  
  static void DeleteRegion(GLUtessellatorImpl tess, ActiveRegion reg)
  {
    if ((reg.fixUpperEdge) && (!$assertionsDisabled) && (reg.eUp.winding != 0)) {
      throw new AssertionError();
    }
    reg.eUp.activeRegion = null;
    Dict.dictDelete(tess.dict, reg.nodeUp);
  }
  
  static boolean FixUpperEdge(ActiveRegion reg, GLUhalfEdge newEdge)
  {
    assert (reg.fixUpperEdge);
    if (!Mesh.__gl_meshDelete(reg.eUp)) {
      return false;
    }
    reg.fixUpperEdge = false;
    reg.eUp = newEdge;
    newEdge.activeRegion = reg;
    return true;
  }
  
  static ActiveRegion TopLeftRegion(ActiveRegion reg)
  {
    GLUvertex org = reg.eUp.Org;
    do
    {
      reg = RegionAbove(reg);
    } while (reg.eUp.Org == org);
    if (reg.fixUpperEdge)
    {
      GLUhalfEdge local_e = Mesh.__gl_meshConnect(RegionBelow(reg).eUp.Sym, reg.eUp.Lnext);
      if (local_e == null) {
        return null;
      }
      if (!FixUpperEdge(reg, local_e)) {
        return null;
      }
      reg = RegionAbove(reg);
    }
    return reg;
  }
  
  static ActiveRegion TopRightRegion(ActiveRegion reg)
  {
    GLUvertex dst = reg.eUp.Sym.Org;
    do
    {
      reg = RegionAbove(reg);
    } while (reg.eUp.Sym.Org == dst);
    return reg;
  }
  
  static ActiveRegion AddRegionBelow(GLUtessellatorImpl tess, ActiveRegion regAbove, GLUhalfEdge eNewUp)
  {
    ActiveRegion regNew = new ActiveRegion();
    if (regNew == null) {
      throw new RuntimeException();
    }
    regNew.eUp = eNewUp;
    regNew.nodeUp = Dict.dictInsertBefore(tess.dict, regAbove.nodeUp, regNew);
    if (regNew.nodeUp == null) {
      throw new RuntimeException();
    }
    regNew.fixUpperEdge = false;
    regNew.sentinel = false;
    regNew.dirty = false;
    eNewUp.activeRegion = regNew;
    return regNew;
  }
  
  static boolean IsWindingInside(GLUtessellatorImpl tess, int local_n)
  {
    switch (tess.windingRule)
    {
    case 100130: 
      return (local_n & 0x1) != 0;
    case 100131: 
      return local_n != 0;
    case 100132: 
      return local_n > 0;
    case 100133: 
      return local_n < 0;
    case 100134: 
      return (local_n >= 2) || (local_n <= -2);
    }
    throw new InternalError();
  }
  
  static void ComputeWinding(GLUtessellatorImpl tess, ActiveRegion reg)
  {
    reg.windingNumber = (RegionAbove(reg).windingNumber + reg.eUp.winding);
    reg.inside = IsWindingInside(tess, reg.windingNumber);
  }
  
  static void FinishRegion(GLUtessellatorImpl tess, ActiveRegion reg)
  {
    GLUhalfEdge local_e = reg.eUp;
    GLUface local_f = local_e.Lface;
    local_f.inside = reg.inside;
    local_f.anEdge = local_e;
    DeleteRegion(tess, reg);
  }
  
  static GLUhalfEdge FinishLeftRegions(GLUtessellatorImpl tess, ActiveRegion regFirst, ActiveRegion regLast)
  {
    ActiveRegion regPrev = regFirst;
    GLUhalfEdge ePrev = regFirst.eUp;
    while (regPrev != regLast)
    {
      regPrev.fixUpperEdge = false;
      ActiveRegion reg = RegionBelow(regPrev);
      GLUhalfEdge local_e = reg.eUp;
      if (local_e.Org != ePrev.Org)
      {
        if (!reg.fixUpperEdge)
        {
          FinishRegion(tess, regPrev);
          break;
        }
        local_e = Mesh.__gl_meshConnect(ePrev.Onext.Sym, local_e.Sym);
        if (local_e == null) {
          throw new RuntimeException();
        }
        if (!FixUpperEdge(reg, local_e)) {
          throw new RuntimeException();
        }
      }
      if (ePrev.Onext != local_e)
      {
        if (!Mesh.__gl_meshSplice(local_e.Sym.Lnext, local_e)) {
          throw new RuntimeException();
        }
        if (!Mesh.__gl_meshSplice(ePrev, local_e)) {
          throw new RuntimeException();
        }
      }
      FinishRegion(tess, regPrev);
      ePrev = reg.eUp;
      regPrev = reg;
    }
    return ePrev;
  }
  
  static void AddRightEdges(GLUtessellatorImpl tess, ActiveRegion regUp, GLUhalfEdge eFirst, GLUhalfEdge eLast, GLUhalfEdge eTopLeft, boolean cleanUp)
  {
    boolean firstTime = true;
    GLUhalfEdge local_e = eFirst;
    do
    {
      assert (Geom.VertLeq(local_e.Org, local_e.Sym.Org));
      AddRegionBelow(tess, regUp, local_e.Sym);
      local_e = local_e.Onext;
    } while (local_e != eLast);
    if (eTopLeft == null) {
      eTopLeft = RegionBelow(regUp).eUp.Sym.Onext;
    }
    ActiveRegion regPrev = regUp;
    ActiveRegion reg;
    for (GLUhalfEdge ePrev = eTopLeft;; ePrev = local_e)
    {
      reg = RegionBelow(regPrev);
      local_e = reg.eUp.Sym;
      if (local_e.Org != ePrev.Org) {
        break;
      }
      if (local_e.Onext != ePrev)
      {
        if (!Mesh.__gl_meshSplice(local_e.Sym.Lnext, local_e)) {
          throw new RuntimeException();
        }
        if (!Mesh.__gl_meshSplice(ePrev.Sym.Lnext, local_e)) {
          throw new RuntimeException();
        }
      }
      regPrev.windingNumber -= local_e.winding;
      reg.inside = IsWindingInside(tess, reg.windingNumber);
      regPrev.dirty = true;
      if ((!firstTime) && (CheckForRightSplice(tess, regPrev)))
      {
        AddWinding(local_e, ePrev);
        DeleteRegion(tess, regPrev);
        if (!Mesh.__gl_meshDelete(ePrev)) {
          throw new RuntimeException();
        }
      }
      firstTime = false;
      regPrev = reg;
    }
    regPrev.dirty = true;
    assert (regPrev.windingNumber - local_e.winding == reg.windingNumber);
    if (cleanUp) {
      WalkDirtyRegions(tess, regPrev);
    }
  }
  
  static void CallCombine(GLUtessellatorImpl tess, GLUvertex isect, Object[] data, float[] weights, boolean needed)
  {
    double[] coords = new double[3];
    coords[0] = isect.coords[0];
    coords[1] = isect.coords[1];
    coords[2] = isect.coords[2];
    Object[] outData = new Object[1];
    tess.callCombineOrCombineData(coords, data, weights, outData);
    isect.data = outData[0];
    if (isect.data == null) {
      if (!needed)
      {
        isect.data = data[0];
      }
      else if (!tess.fatalError)
      {
        tess.callErrorOrErrorData(100156);
        tess.fatalError = true;
      }
    }
  }
  
  static void SpliceMergeVertices(GLUtessellatorImpl tess, GLUhalfEdge local_e1, GLUhalfEdge local_e2)
  {
    Object[] data = new Object[4];
    float[] weights = { 0.5F, 0.5F, 0.0F, 0.0F };
    data[0] = local_e1.Org.data;
    data[1] = local_e2.Org.data;
    CallCombine(tess, local_e1.Org, data, weights, false);
    if (!Mesh.__gl_meshSplice(local_e1, local_e2)) {
      throw new RuntimeException();
    }
  }
  
  static void VertexWeights(GLUvertex isect, GLUvertex org, GLUvertex dst, float[] weights)
  {
    double local_t1 = Geom.VertL1dist(org, isect);
    double local_t2 = Geom.VertL1dist(dst, isect);
    weights[0] = ((float)(0.5D * local_t2 / (local_t1 + local_t2)));
    weights[1] = ((float)(0.5D * local_t1 / (local_t1 + local_t2)));
    isect.coords[0] += weights[0] * org.coords[0] + weights[1] * dst.coords[0];
    isect.coords[1] += weights[0] * org.coords[1] + weights[1] * dst.coords[1];
    isect.coords[2] += weights[0] * org.coords[2] + weights[1] * dst.coords[2];
  }
  
  static void GetIntersectData(GLUtessellatorImpl tess, GLUvertex isect, GLUvertex orgUp, GLUvertex dstUp, GLUvertex orgLo, GLUvertex dstLo)
  {
    Object[] data = new Object[4];
    float[] weights = new float[4];
    float[] weights1 = new float[2];
    float[] weights2 = new float[2];
    data[0] = orgUp.data;
    data[1] = dstUp.data;
    data[2] = orgLo.data;
    data[3] = dstLo.data;
    double tmp73_72 = (isect.coords[2] = 0.0D);
    isect.coords[1] = tmp73_72;
    isect.coords[0] = tmp73_72;
    VertexWeights(isect, orgUp, dstUp, weights1);
    VertexWeights(isect, orgLo, dstLo, weights2);
    System.arraycopy(weights1, 0, weights, 0, 2);
    System.arraycopy(weights2, 0, weights, 2, 2);
    CallCombine(tess, isect, data, weights, true);
  }
  
  static boolean CheckForRightSplice(GLUtessellatorImpl tess, ActiveRegion regUp)
  {
    ActiveRegion regLo = RegionBelow(regUp);
    GLUhalfEdge eUp = regUp.eUp;
    GLUhalfEdge eLo = regLo.eUp;
    if (Geom.VertLeq(eUp.Org, eLo.Org))
    {
      if (Geom.EdgeSign(eLo.Sym.Org, eUp.Org, eLo.Org) > 0.0D) {
        return false;
      }
      if (!Geom.VertEq(eUp.Org, eLo.Org))
      {
        if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) {
          throw new RuntimeException();
        }
        if (!Mesh.__gl_meshSplice(eUp, eLo.Sym.Lnext)) {
          throw new RuntimeException();
        }
        regUp.dirty = (regLo.dirty = 1);
      }
      else if (eUp.Org != eLo.Org)
      {
        tess.field_367.pqDelete(eUp.Org.pqHandle);
        SpliceMergeVertices(tess, eLo.Sym.Lnext, eUp);
      }
    }
    else
    {
      if (Geom.EdgeSign(eUp.Sym.Org, eLo.Org, eUp.Org) < 0.0D) {
        return false;
      }
      RegionAbove(regUp).dirty = (regUp.dirty = 1);
      if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) {
        throw new RuntimeException();
      }
      if (!Mesh.__gl_meshSplice(eLo.Sym.Lnext, eUp)) {
        throw new RuntimeException();
      }
    }
    return true;
  }
  
  static boolean CheckForLeftSplice(GLUtessellatorImpl tess, ActiveRegion regUp)
  {
    ActiveRegion regLo = RegionBelow(regUp);
    GLUhalfEdge eUp = regUp.eUp;
    GLUhalfEdge eLo = regLo.eUp;
    assert (!Geom.VertEq(eUp.Sym.Org, eLo.Sym.Org));
    if (Geom.VertLeq(eUp.Sym.Org, eLo.Sym.Org))
    {
      if (Geom.EdgeSign(eUp.Sym.Org, eLo.Sym.Org, eUp.Org) < 0.0D) {
        return false;
      }
      RegionAbove(regUp).dirty = (regUp.dirty = 1);
      GLUhalfEdge local_e = Mesh.__gl_meshSplitEdge(eUp);
      if (local_e == null) {
        throw new RuntimeException();
      }
      if (!Mesh.__gl_meshSplice(eLo.Sym, local_e)) {
        throw new RuntimeException();
      }
      local_e.Lface.inside = regUp.inside;
    }
    else
    {
      if (Geom.EdgeSign(eLo.Sym.Org, eUp.Sym.Org, eLo.Org) > 0.0D) {
        return false;
      }
      regUp.dirty = (regLo.dirty = 1);
      GLUhalfEdge local_e = Mesh.__gl_meshSplitEdge(eLo);
      if (local_e == null) {
        throw new RuntimeException();
      }
      if (!Mesh.__gl_meshSplice(eUp.Lnext, eLo.Sym)) {
        throw new RuntimeException();
      }
      local_e.Sym.Lface.inside = regUp.inside;
    }
    return true;
  }
  
  static boolean CheckForIntersect(GLUtessellatorImpl tess, ActiveRegion regUp)
  {
    ActiveRegion regLo = RegionBelow(regUp);
    GLUhalfEdge eUp = regUp.eUp;
    GLUhalfEdge eLo = regLo.eUp;
    GLUvertex orgUp = eUp.Org;
    GLUvertex orgLo = eLo.Org;
    GLUvertex dstUp = eUp.Sym.Org;
    GLUvertex dstLo = eLo.Sym.Org;
    GLUvertex isect = new GLUvertex();
    assert (!Geom.VertEq(dstLo, dstUp));
    assert (Geom.EdgeSign(dstUp, tess.event, orgUp) <= 0.0D);
    assert (Geom.EdgeSign(dstLo, tess.event, orgLo) >= 0.0D);
    assert ((orgUp != tess.event) && (orgLo != tess.event));
    assert ((!regUp.fixUpperEdge) && (!regLo.fixUpperEdge));
    if (orgUp == orgLo) {
      return false;
    }
    double tMinUp = Math.min(orgUp.field_2228, dstUp.field_2228);
    double tMaxLo = Math.max(orgLo.field_2228, dstLo.field_2228);
    if (tMinUp > tMaxLo) {
      return false;
    }
    if (Geom.VertLeq(orgUp, orgLo))
    {
      if (Geom.EdgeSign(dstLo, orgUp, orgLo) > 0.0D) {
        return false;
      }
    }
    else if (Geom.EdgeSign(dstUp, orgLo, orgUp) < 0.0D) {
      return false;
    }
    DebugEvent(tess);
    Geom.EdgeIntersect(dstUp, orgUp, dstLo, orgLo, isect);
    assert (Math.min(orgUp.field_2228, dstUp.field_2228) <= isect.field_2228);
    assert (isect.field_2228 <= Math.max(orgLo.field_2228, dstLo.field_2228));
    assert (Math.min(dstLo.field_2227, dstUp.field_2227) <= isect.field_2227);
    assert (isect.field_2227 <= Math.max(orgLo.field_2227, orgUp.field_2227));
    if (Geom.VertLeq(isect, tess.event))
    {
      isect.field_2227 = tess.event.field_2227;
      isect.field_2228 = tess.event.field_2228;
    }
    GLUvertex orgMin = Geom.VertLeq(orgUp, orgLo) ? orgUp : orgLo;
    if (Geom.VertLeq(orgMin, isect))
    {
      isect.field_2227 = orgMin.field_2227;
      isect.field_2228 = orgMin.field_2228;
    }
    if ((Geom.VertEq(isect, orgUp)) || (Geom.VertEq(isect, orgLo)))
    {
      CheckForRightSplice(tess, regUp);
      return false;
    }
    if (((!Geom.VertEq(dstUp, tess.event)) && (Geom.EdgeSign(dstUp, tess.event, isect) >= 0.0D)) || ((!Geom.VertEq(dstLo, tess.event)) && (Geom.EdgeSign(dstLo, tess.event, isect) <= 0.0D)))
    {
      if (dstLo == tess.event)
      {
        if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) {
          throw new RuntimeException();
        }
        if (!Mesh.__gl_meshSplice(eLo.Sym, eUp)) {
          throw new RuntimeException();
        }
        regUp = TopLeftRegion(regUp);
        if (regUp == null) {
          throw new RuntimeException();
        }
        eUp = RegionBelow(regUp).eUp;
        FinishLeftRegions(tess, RegionBelow(regUp), regLo);
        AddRightEdges(tess, regUp, eUp.Sym.Lnext, eUp, eUp, true);
        return true;
      }
      if (dstUp == tess.event)
      {
        if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) {
          throw new RuntimeException();
        }
        if (!Mesh.__gl_meshSplice(eUp.Lnext, eLo.Sym.Lnext)) {
          throw new RuntimeException();
        }
        regLo = regUp;
        regUp = TopRightRegion(regUp);
        GLUhalfEdge local_e = RegionBelow(regUp).eUp.Sym.Onext;
        regLo.eUp = eLo.Sym.Lnext;
        eLo = FinishLeftRegions(tess, regLo, null);
        AddRightEdges(tess, regUp, eLo.Onext, eUp.Sym.Onext, local_e, true);
        return true;
      }
      if (Geom.EdgeSign(dstUp, tess.event, isect) >= 0.0D)
      {
        RegionAbove(regUp).dirty = (regUp.dirty = 1);
        if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) {
          throw new RuntimeException();
        }
        eUp.Org.field_2227 = tess.event.field_2227;
        eUp.Org.field_2228 = tess.event.field_2228;
      }
      if (Geom.EdgeSign(dstLo, tess.event, isect) <= 0.0D)
      {
        regUp.dirty = (regLo.dirty = 1);
        if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) {
          throw new RuntimeException();
        }
        eLo.Org.field_2227 = tess.event.field_2227;
        eLo.Org.field_2228 = tess.event.field_2228;
      }
      return false;
    }
    if (Mesh.__gl_meshSplitEdge(eUp.Sym) == null) {
      throw new RuntimeException();
    }
    if (Mesh.__gl_meshSplitEdge(eLo.Sym) == null) {
      throw new RuntimeException();
    }
    if (!Mesh.__gl_meshSplice(eLo.Sym.Lnext, eUp)) {
      throw new RuntimeException();
    }
    eUp.Org.field_2227 = isect.field_2227;
    eUp.Org.field_2228 = isect.field_2228;
    eUp.Org.pqHandle = tess.field_367.pqInsert(eUp.Org);
    if (eUp.Org.pqHandle == 9223372036854775807L)
    {
      tess.field_367.pqDeletePriorityQ();
      tess.field_367 = null;
      throw new RuntimeException();
    }
    GetIntersectData(tess, eUp.Org, orgUp, dstUp, orgLo, dstLo);
    RegionAbove(regUp).dirty = (regUp.dirty = regLo.dirty = 1);
    return false;
  }
  
  static void WalkDirtyRegions(GLUtessellatorImpl tess, ActiveRegion regUp)
  {
    ActiveRegion regLo = RegionBelow(regUp);
    for (;;)
    {
      if (regLo.dirty)
      {
        regUp = regLo;
        regLo = RegionBelow(regLo);
      }
      else
      {
        if (!regUp.dirty)
        {
          regLo = regUp;
          regUp = RegionAbove(regUp);
          if ((regUp == null) || (!regUp.dirty)) {
            return;
          }
        }
        regUp.dirty = false;
        GLUhalfEdge eUp = regUp.eUp;
        GLUhalfEdge eLo = regLo.eUp;
        if ((eUp.Sym.Org != eLo.Sym.Org) && (CheckForLeftSplice(tess, regUp))) {
          if (regLo.fixUpperEdge)
          {
            DeleteRegion(tess, regLo);
            if (!Mesh.__gl_meshDelete(eLo)) {
              throw new RuntimeException();
            }
            regLo = RegionBelow(regUp);
            eLo = regLo.eUp;
          }
          else if (regUp.fixUpperEdge)
          {
            DeleteRegion(tess, regUp);
            if (!Mesh.__gl_meshDelete(eUp)) {
              throw new RuntimeException();
            }
            regUp = RegionAbove(regLo);
            eUp = regUp.eUp;
          }
        }
        if (eUp.Org != eLo.Org) {
          if ((eUp.Sym.Org != eLo.Sym.Org) && (!regUp.fixUpperEdge) && (!regLo.fixUpperEdge) && ((eUp.Sym.Org == tess.event) || (eLo.Sym.Org == tess.event)))
          {
            if (!CheckForIntersect(tess, regUp)) {}
          }
          else {
            CheckForRightSplice(tess, regUp);
          }
        }
        if ((eUp.Org == eLo.Org) && (eUp.Sym.Org == eLo.Sym.Org))
        {
          AddWinding(eLo, eUp);
          DeleteRegion(tess, regUp);
          if (!Mesh.__gl_meshDelete(eUp)) {
            throw new RuntimeException();
          }
          regUp = RegionAbove(regLo);
        }
      }
    }
  }
  
  static void ConnectRightVertex(GLUtessellatorImpl tess, ActiveRegion regUp, GLUhalfEdge eBottomLeft)
  {
    GLUhalfEdge eTopLeft = eBottomLeft.Onext;
    ActiveRegion regLo = RegionBelow(regUp);
    GLUhalfEdge eUp = regUp.eUp;
    GLUhalfEdge eLo = regLo.eUp;
    boolean degenerate = false;
    if (eUp.Sym.Org != eLo.Sym.Org) {
      CheckForIntersect(tess, regUp);
    }
    if (Geom.VertEq(eUp.Org, tess.event))
    {
      if (!Mesh.__gl_meshSplice(eTopLeft.Sym.Lnext, eUp)) {
        throw new RuntimeException();
      }
      regUp = TopLeftRegion(regUp);
      if (regUp == null) {
        throw new RuntimeException();
      }
      eTopLeft = RegionBelow(regUp).eUp;
      FinishLeftRegions(tess, RegionBelow(regUp), regLo);
      degenerate = true;
    }
    if (Geom.VertEq(eLo.Org, tess.event))
    {
      if (!Mesh.__gl_meshSplice(eBottomLeft, eLo.Sym.Lnext)) {
        throw new RuntimeException();
      }
      eBottomLeft = FinishLeftRegions(tess, regLo, null);
      degenerate = true;
    }
    if (degenerate)
    {
      AddRightEdges(tess, regUp, eBottomLeft.Onext, eTopLeft, eTopLeft, true);
      return;
    }
    GLUhalfEdge eNew;
    if (Geom.VertLeq(eLo.Org, eUp.Org)) {
      eNew = eLo.Sym.Lnext;
    } else {
      eNew = eUp;
    }
    GLUhalfEdge eNew = Mesh.__gl_meshConnect(eBottomLeft.Onext.Sym, eNew);
    if (eNew == null) {
      throw new RuntimeException();
    }
    AddRightEdges(tess, regUp, eNew, eNew.Onext, eNew.Onext, false);
    eNew.Sym.activeRegion.fixUpperEdge = true;
    WalkDirtyRegions(tess, regUp);
  }
  
  static void ConnectLeftDegenerate(GLUtessellatorImpl tess, ActiveRegion regUp, GLUvertex vEvent)
  {
    GLUhalfEdge local_e = regUp.eUp;
    if (Geom.VertEq(local_e.Org, vEvent))
    {
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      SpliceMergeVertices(tess, local_e, vEvent.anEdge);
      return;
    }
    if (!Geom.VertEq(local_e.Sym.Org, vEvent))
    {
      if (Mesh.__gl_meshSplitEdge(local_e.Sym) == null) {
        throw new RuntimeException();
      }
      if (regUp.fixUpperEdge)
      {
        if (!Mesh.__gl_meshDelete(local_e.Onext)) {
          throw new RuntimeException();
        }
        regUp.fixUpperEdge = false;
      }
      if (!Mesh.__gl_meshSplice(vEvent.anEdge, local_e)) {
        throw new RuntimeException();
      }
      SweepEvent(tess, vEvent);
      return;
    }
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    regUp = TopRightRegion(regUp);
    ActiveRegion reg = RegionBelow(regUp);
    GLUhalfEdge eTopRight = reg.eUp.Sym;
    GLUhalfEdge eLast;
    GLUhalfEdge eTopLeft = eLast = eTopRight.Onext;
    if (reg.fixUpperEdge)
    {
      assert (eTopLeft != eTopRight);
      DeleteRegion(tess, reg);
      if (!Mesh.__gl_meshDelete(eTopRight)) {
        throw new RuntimeException();
      }
      eTopRight = eTopLeft.Sym.Lnext;
    }
    if (!Mesh.__gl_meshSplice(vEvent.anEdge, eTopRight)) {
      throw new RuntimeException();
    }
    if (!Geom.EdgeGoesLeft(eTopLeft)) {
      eTopLeft = null;
    }
    AddRightEdges(tess, regUp, eTopRight.Onext, eLast, eTopLeft, true);
  }
  
  static void ConnectLeftVertex(GLUtessellatorImpl tess, GLUvertex vEvent)
  {
    ActiveRegion tmp = new ActiveRegion();
    tmp.eUp = vEvent.anEdge.Sym;
    ActiveRegion regUp = (ActiveRegion)Dict.dictKey(Dict.dictSearch(tess.dict, tmp));
    ActiveRegion regLo = RegionBelow(regUp);
    GLUhalfEdge eUp = regUp.eUp;
    GLUhalfEdge eLo = regLo.eUp;
    if (Geom.EdgeSign(eUp.Sym.Org, vEvent, eUp.Org) == 0.0D)
    {
      ConnectLeftDegenerate(tess, regUp, vEvent);
      return;
    }
    ActiveRegion reg = Geom.VertLeq(eLo.Sym.Org, eUp.Sym.Org) ? regUp : regLo;
    if ((regUp.inside) || (reg.fixUpperEdge))
    {
      GLUhalfEdge eNew;
      if (reg == regUp)
      {
        GLUhalfEdge eNew = Mesh.__gl_meshConnect(vEvent.anEdge.Sym, eUp.Lnext);
        if (eNew == null) {
          throw new RuntimeException();
        }
      }
      else
      {
        GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(eLo.Sym.Onext.Sym, vEvent.anEdge);
        if (tempHalfEdge == null) {
          throw new RuntimeException();
        }
        eNew = tempHalfEdge.Sym;
      }
      if (reg.fixUpperEdge)
      {
        if (!FixUpperEdge(reg, eNew)) {
          throw new RuntimeException();
        }
      }
      else {
        ComputeWinding(tess, AddRegionBelow(tess, regUp, eNew));
      }
      SweepEvent(tess, vEvent);
    }
    else
    {
      AddRightEdges(tess, regUp, vEvent.anEdge, vEvent.anEdge, null, true);
    }
  }
  
  static void SweepEvent(GLUtessellatorImpl tess, GLUvertex vEvent)
  {
    tess.event = vEvent;
    DebugEvent(tess);
    GLUhalfEdge local_e = vEvent.anEdge;
    while (local_e.activeRegion == null)
    {
      local_e = local_e.Onext;
      if (local_e == vEvent.anEdge)
      {
        ConnectLeftVertex(tess, vEvent);
        return;
      }
    }
    ActiveRegion regUp = TopLeftRegion(local_e.activeRegion);
    if (regUp == null) {
      throw new RuntimeException();
    }
    ActiveRegion reg = RegionBelow(regUp);
    GLUhalfEdge eTopLeft = reg.eUp;
    GLUhalfEdge eBottomLeft = FinishLeftRegions(tess, reg, null);
    if (eBottomLeft.Onext == eTopLeft) {
      ConnectRightVertex(tess, regUp, eBottomLeft);
    } else {
      AddRightEdges(tess, regUp, eBottomLeft.Onext, eTopLeft, eTopLeft, true);
    }
  }
  
  static void AddSentinel(GLUtessellatorImpl tess, double local_t)
  {
    ActiveRegion reg = new ActiveRegion();
    if (reg == null) {
      throw new RuntimeException();
    }
    GLUhalfEdge local_e = Mesh.__gl_meshMakeEdge(tess.mesh);
    if (local_e == null) {
      throw new RuntimeException();
    }
    local_e.Org.field_2227 = 4.0E+150D;
    local_e.Org.field_2228 = local_t;
    local_e.Sym.Org.field_2227 = -4.0E+150D;
    local_e.Sym.Org.field_2228 = local_t;
    tess.event = local_e.Sym.Org;
    reg.eUp = local_e;
    reg.windingNumber = 0;
    reg.inside = false;
    reg.fixUpperEdge = false;
    reg.sentinel = true;
    reg.dirty = false;
    reg.nodeUp = Dict.dictInsert(tess.dict, reg);
    if (reg.nodeUp == null) {
      throw new RuntimeException();
    }
  }
  
  static void InitEdgeDict(GLUtessellatorImpl tess)
  {
    tess.dict = Dict.dictNewDict(tess, new Dict.DictLeq()
    {
      public boolean leq(Object frame, Object key1, Object key2)
      {
        return Sweep.EdgeLeq(this.val$tess, (ActiveRegion)key1, (ActiveRegion)key2);
      }
    });
    if (tess.dict == null) {
      throw new RuntimeException();
    }
    AddSentinel(tess, -4.0E+150D);
    AddSentinel(tess, 4.0E+150D);
  }
  
  static void DoneEdgeDict(GLUtessellatorImpl tess)
  {
    int fixedEdges = 0;
    ActiveRegion reg;
    while ((reg = (ActiveRegion)Dict.dictKey(Dict.dictMin(tess.dict))) != null)
    {
      if (!reg.sentinel)
      {
        assert (reg.fixUpperEdge);
        if (!$assertionsDisabled)
        {
          fixedEdges++;
          if (fixedEdges != 1) {
            throw new AssertionError();
          }
        }
      }
      assert (reg.windingNumber == 0);
      DeleteRegion(tess, reg);
    }
    Dict.dictDeleteDict(tess.dict);
  }
  
  static void RemoveDegenerateEdges(GLUtessellatorImpl tess)
  {
    GLUhalfEdge eHead = tess.mesh.eHead;
    GLUhalfEdge eNext;
    for (GLUhalfEdge local_e = eHead.next; local_e != eHead; local_e = eNext)
    {
      eNext = local_e.next;
      GLUhalfEdge eLnext = local_e.Lnext;
      if ((Geom.VertEq(local_e.Org, local_e.Sym.Org)) && (local_e.Lnext.Lnext != local_e))
      {
        SpliceMergeVertices(tess, eLnext, local_e);
        if (!Mesh.__gl_meshDelete(local_e)) {
          throw new RuntimeException();
        }
        local_e = eLnext;
        eLnext = local_e.Lnext;
      }
      if (eLnext.Lnext == local_e)
      {
        if (eLnext != local_e)
        {
          if ((eLnext == eNext) || (eLnext == eNext.Sym)) {
            eNext = eNext.next;
          }
          if (!Mesh.__gl_meshDelete(eLnext)) {
            throw new RuntimeException();
          }
        }
        if ((local_e == eNext) || (local_e == eNext.Sym)) {
          eNext = eNext.next;
        }
        if (!Mesh.__gl_meshDelete(local_e)) {
          throw new RuntimeException();
        }
      }
    }
  }
  
  static boolean InitPriorityQ(GLUtessellatorImpl tess)
  {
    PriorityQ local_pq = tess.field_367 = PriorityQ.pqNewPriorityQ(new PriorityQ.Leq()
    {
      public boolean leq(Object key1, Object key2)
      {
        return Geom.VertLeq((GLUvertex)key1, (GLUvertex)key2);
      }
    });
    if (local_pq == null) {
      return false;
    }
    GLUvertex vHead = tess.mesh.vHead;
    for (GLUvertex local_v = vHead.next; local_v != vHead; local_v = local_v.next)
    {
      local_v.pqHandle = local_pq.pqInsert(local_v);
      if (local_v.pqHandle == 9223372036854775807L) {
        break;
      }
    }
    if ((local_v != vHead) || (!local_pq.pqInit()))
    {
      tess.field_367.pqDeletePriorityQ();
      tess.field_367 = null;
      return false;
    }
    return true;
  }
  
  static void DonePriorityQ(GLUtessellatorImpl tess)
  {
    tess.field_367.pqDeletePriorityQ();
  }
  
  static boolean RemoveDegenerateFaces(GLUmesh mesh)
  {
    GLUface fNext;
    for (GLUface local_f = mesh.fHead.next; local_f != mesh.fHead; local_f = fNext)
    {
      fNext = local_f.next;
      GLUhalfEdge local_e = local_f.anEdge;
      assert (local_e.Lnext != local_e);
      if (local_e.Lnext.Lnext == local_e)
      {
        AddWinding(local_e.Onext, local_e);
        if (!Mesh.__gl_meshDelete(local_e)) {
          return false;
        }
      }
    }
    return true;
  }
  
  public static boolean __gl_computeInterior(GLUtessellatorImpl tess)
  {
    tess.fatalError = false;
    RemoveDegenerateEdges(tess);
    if (!InitPriorityQ(tess)) {
      return false;
    }
    InitEdgeDict(tess);
    GLUvertex local_v;
    while ((local_v = (GLUvertex)tess.field_367.pqExtractMin()) != null)
    {
      for (;;)
      {
        GLUvertex vNext = (GLUvertex)tess.field_367.pqMinimum();
        if ((vNext == null) || (!Geom.VertEq(vNext, local_v))) {
          break;
        }
        vNext = (GLUvertex)tess.field_367.pqExtractMin();
        SpliceMergeVertices(tess, local_v.anEdge, vNext.anEdge);
      }
      SweepEvent(tess, local_v);
    }
    tess.event = ((ActiveRegion)Dict.dictKey(Dict.dictMin(tess.dict))).eUp.Org;
    DebugEvent(tess);
    DoneEdgeDict(tess);
    DonePriorityQ(tess);
    if (!RemoveDegenerateFaces(tess.mesh)) {
      return false;
    }
    Mesh.__gl_meshCheckMesh(tess.mesh);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Sweep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */