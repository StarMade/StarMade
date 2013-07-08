package org.lwjgl.util.glu.tessellation;

class Mesh
{
  static GLUhalfEdge MakeEdge(GLUhalfEdge eNext)
  {
    GLUhalfEdge local_e = new GLUhalfEdge(true);
    GLUhalfEdge eSym = new GLUhalfEdge(false);
    if (!eNext.first) {
      eNext = eNext.Sym;
    }
    GLUhalfEdge ePrev = eNext.Sym.next;
    eSym.next = ePrev;
    ePrev.Sym.next = local_e;
    local_e.next = eNext;
    eNext.Sym.next = eSym;
    local_e.Sym = eSym;
    local_e.Onext = local_e;
    local_e.Lnext = eSym;
    local_e.Org = null;
    local_e.Lface = null;
    local_e.winding = 0;
    local_e.activeRegion = null;
    eSym.Sym = local_e;
    eSym.Onext = eSym;
    eSym.Lnext = local_e;
    eSym.Org = null;
    eSym.Lface = null;
    eSym.winding = 0;
    eSym.activeRegion = null;
    return local_e;
  }
  
  static void Splice(GLUhalfEdge local_a, GLUhalfEdge local_b)
  {
    GLUhalfEdge aOnext = local_a.Onext;
    GLUhalfEdge bOnext = local_b.Onext;
    aOnext.Sym.Lnext = local_b;
    bOnext.Sym.Lnext = local_a;
    local_a.Onext = bOnext;
    local_b.Onext = aOnext;
  }
  
  static void MakeVertex(GLUvertex newVertex, GLUhalfEdge eOrig, GLUvertex vNext)
  {
    GLUvertex vNew = newVertex;
    assert (vNew != null);
    GLUvertex vPrev = vNext.prev;
    vNew.prev = vPrev;
    vPrev.next = vNew;
    vNew.next = vNext;
    vNext.prev = vNew;
    vNew.anEdge = eOrig;
    vNew.data = null;
    GLUhalfEdge local_e = eOrig;
    do
    {
      local_e.Org = vNew;
      local_e = local_e.Onext;
    } while (local_e != eOrig);
  }
  
  static void MakeFace(GLUface newFace, GLUhalfEdge eOrig, GLUface fNext)
  {
    GLUface fNew = newFace;
    assert (fNew != null);
    GLUface fPrev = fNext.prev;
    fNew.prev = fPrev;
    fPrev.next = fNew;
    fNew.next = fNext;
    fNext.prev = fNew;
    fNew.anEdge = eOrig;
    fNew.data = null;
    fNew.trail = null;
    fNew.marked = false;
    fNew.inside = fNext.inside;
    GLUhalfEdge local_e = eOrig;
    do
    {
      local_e.Lface = fNew;
      local_e = local_e.Lnext;
    } while (local_e != eOrig);
  }
  
  static void KillEdge(GLUhalfEdge eDel)
  {
    if (!eDel.first) {
      eDel = eDel.Sym;
    }
    GLUhalfEdge eNext = eDel.next;
    GLUhalfEdge ePrev = eDel.Sym.next;
    eNext.Sym.next = ePrev;
    ePrev.Sym.next = eNext;
  }
  
  static void KillVertex(GLUvertex vDel, GLUvertex newOrg)
  {
    GLUhalfEdge eStart = vDel.anEdge;
    GLUhalfEdge local_e = eStart;
    do
    {
      local_e.Org = newOrg;
      local_e = local_e.Onext;
    } while (local_e != eStart);
    GLUvertex vPrev = vDel.prev;
    GLUvertex vNext = vDel.next;
    vNext.prev = vPrev;
    vPrev.next = vNext;
  }
  
  static void KillFace(GLUface fDel, GLUface newLface)
  {
    GLUhalfEdge eStart = fDel.anEdge;
    GLUhalfEdge local_e = eStart;
    do
    {
      local_e.Lface = newLface;
      local_e = local_e.Lnext;
    } while (local_e != eStart);
    GLUface fPrev = fDel.prev;
    GLUface fNext = fDel.next;
    fNext.prev = fPrev;
    fPrev.next = fNext;
  }
  
  public static GLUhalfEdge __gl_meshMakeEdge(GLUmesh mesh)
  {
    GLUvertex newVertex1 = new GLUvertex();
    GLUvertex newVertex2 = new GLUvertex();
    GLUface newFace = new GLUface();
    GLUhalfEdge local_e = MakeEdge(mesh.eHead);
    if (local_e == null) {
      return null;
    }
    MakeVertex(newVertex1, local_e, mesh.vHead);
    MakeVertex(newVertex2, local_e.Sym, mesh.vHead);
    MakeFace(newFace, local_e, mesh.fHead);
    return local_e;
  }
  
  public static boolean __gl_meshSplice(GLUhalfEdge eOrg, GLUhalfEdge eDst)
  {
    boolean joiningLoops = false;
    boolean joiningVertices = false;
    if (eOrg == eDst) {
      return true;
    }
    if (eDst.Org != eOrg.Org)
    {
      joiningVertices = true;
      KillVertex(eDst.Org, eOrg.Org);
    }
    if (eDst.Lface != eOrg.Lface)
    {
      joiningLoops = true;
      KillFace(eDst.Lface, eOrg.Lface);
    }
    Splice(eDst, eOrg);
    if (!joiningVertices)
    {
      GLUvertex newVertex = new GLUvertex();
      MakeVertex(newVertex, eDst, eOrg.Org);
      eOrg.Org.anEdge = eOrg;
    }
    if (!joiningLoops)
    {
      GLUface newVertex = new GLUface();
      MakeFace(newVertex, eDst, eOrg.Lface);
      eOrg.Lface.anEdge = eOrg;
    }
    return true;
  }
  
  static boolean __gl_meshDelete(GLUhalfEdge eDel)
  {
    GLUhalfEdge eDelSym = eDel.Sym;
    boolean joiningLoops = false;
    if (eDel.Lface != eDel.Sym.Lface)
    {
      joiningLoops = true;
      KillFace(eDel.Lface, eDel.Sym.Lface);
    }
    if (eDel.Onext == eDel)
    {
      KillVertex(eDel.Org, null);
    }
    else
    {
      eDel.Sym.Lface.anEdge = eDel.Sym.Lnext;
      eDel.Org.anEdge = eDel.Onext;
      Splice(eDel, eDel.Sym.Lnext);
      if (!joiningLoops)
      {
        GLUface newFace = new GLUface();
        MakeFace(newFace, eDel, eDel.Lface);
      }
    }
    if (eDelSym.Onext == eDelSym)
    {
      KillVertex(eDelSym.Org, null);
      KillFace(eDelSym.Lface, null);
    }
    else
    {
      eDel.Lface.anEdge = eDelSym.Sym.Lnext;
      eDelSym.Org.anEdge = eDelSym.Onext;
      Splice(eDelSym, eDelSym.Sym.Lnext);
    }
    KillEdge(eDel);
    return true;
  }
  
  static GLUhalfEdge __gl_meshAddEdgeVertex(GLUhalfEdge eOrg)
  {
    GLUhalfEdge eNew = MakeEdge(eOrg);
    GLUhalfEdge eNewSym = eNew.Sym;
    Splice(eNew, eOrg.Lnext);
    eNew.Org = eOrg.Sym.Org;
    GLUvertex newVertex = new GLUvertex();
    MakeVertex(newVertex, eNewSym, eNew.Org);
    eNew.Lface = (eNewSym.Lface = eOrg.Lface);
    return eNew;
  }
  
  public static GLUhalfEdge __gl_meshSplitEdge(GLUhalfEdge eOrg)
  {
    GLUhalfEdge tempHalfEdge = __gl_meshAddEdgeVertex(eOrg);
    GLUhalfEdge eNew = tempHalfEdge.Sym;
    Splice(eOrg.Sym, eOrg.Sym.Sym.Lnext);
    Splice(eOrg.Sym, eNew);
    eOrg.Sym.Org = eNew.Org;
    eNew.Sym.Org.anEdge = eNew.Sym;
    eNew.Sym.Lface = eOrg.Sym.Lface;
    eNew.winding = eOrg.winding;
    eNew.Sym.winding = eOrg.Sym.winding;
    return eNew;
  }
  
  static GLUhalfEdge __gl_meshConnect(GLUhalfEdge eOrg, GLUhalfEdge eDst)
  {
    boolean joiningLoops = false;
    GLUhalfEdge eNew = MakeEdge(eOrg);
    GLUhalfEdge eNewSym = eNew.Sym;
    if (eDst.Lface != eOrg.Lface)
    {
      joiningLoops = true;
      KillFace(eDst.Lface, eOrg.Lface);
    }
    Splice(eNew, eOrg.Lnext);
    Splice(eNewSym, eDst);
    eNew.Org = eOrg.Sym.Org;
    eNewSym.Org = eDst.Org;
    eNew.Lface = (eNewSym.Lface = eOrg.Lface);
    eOrg.Lface.anEdge = eNewSym;
    if (!joiningLoops)
    {
      GLUface newFace = new GLUface();
      MakeFace(newFace, eNew, eOrg.Lface);
    }
    return eNew;
  }
  
  static void __gl_meshZapFace(GLUface fZap)
  {
    GLUhalfEdge eStart = fZap.anEdge;
    GLUhalfEdge eNext = eStart.Lnext;
    GLUhalfEdge local_e;
    do
    {
      local_e = eNext;
      eNext = local_e.Lnext;
      local_e.Lface = null;
      if (local_e.Sym.Lface == null)
      {
        if (local_e.Onext == local_e)
        {
          KillVertex(local_e.Org, null);
        }
        else
        {
          local_e.Org.anEdge = local_e.Onext;
          Splice(local_e, local_e.Sym.Lnext);
        }
        GLUhalfEdge eSym = local_e.Sym;
        if (eSym.Onext == eSym)
        {
          KillVertex(eSym.Org, null);
        }
        else
        {
          eSym.Org.anEdge = eSym.Onext;
          Splice(eSym, eSym.Sym.Lnext);
        }
        KillEdge(local_e);
      }
    } while (local_e != eStart);
    GLUface fPrev = fZap.prev;
    GLUface fNext = fZap.next;
    fNext.prev = fPrev;
    fPrev.next = fNext;
  }
  
  public static GLUmesh __gl_meshNewMesh()
  {
    GLUmesh mesh = new GLUmesh();
    GLUvertex local_v = mesh.vHead;
    GLUface local_f = mesh.fHead;
    GLUhalfEdge local_e = mesh.eHead;
    GLUhalfEdge eSym = mesh.eHeadSym;
    local_v.prev = local_v;
    local_v.next = local_v;
    local_v.anEdge = null;
    local_v.data = null;
    local_f.next = (local_f.prev = local_f);
    local_f.anEdge = null;
    local_f.data = null;
    local_f.trail = null;
    local_f.marked = false;
    local_f.inside = false;
    local_e.next = local_e;
    local_e.Sym = eSym;
    local_e.Onext = null;
    local_e.Lnext = null;
    local_e.Org = null;
    local_e.Lface = null;
    local_e.winding = 0;
    local_e.activeRegion = null;
    eSym.next = eSym;
    eSym.Sym = local_e;
    eSym.Onext = null;
    eSym.Lnext = null;
    eSym.Org = null;
    eSym.Lface = null;
    eSym.winding = 0;
    eSym.activeRegion = null;
    return mesh;
  }
  
  static GLUmesh __gl_meshUnion(GLUmesh mesh1, GLUmesh mesh2)
  {
    GLUface local_f1 = mesh1.fHead;
    GLUvertex local_v1 = mesh1.vHead;
    GLUhalfEdge local_e1 = mesh1.eHead;
    GLUface local_f2 = mesh2.fHead;
    GLUvertex local_v2 = mesh2.vHead;
    GLUhalfEdge local_e2 = mesh2.eHead;
    if (local_f2.next != local_f2)
    {
      local_f1.prev.next = local_f2.next;
      local_f2.next.prev = local_f1.prev;
      local_f2.prev.next = local_f1;
      local_f1.prev = local_f2.prev;
    }
    if (local_v2.next != local_v2)
    {
      local_v1.prev.next = local_v2.next;
      local_v2.next.prev = local_v1.prev;
      local_v2.prev.next = local_v1;
      local_v1.prev = local_v2.prev;
    }
    if (local_e2.next != local_e2)
    {
      local_e1.Sym.next.Sym.next = local_e2.next;
      local_e2.next.Sym.next = local_e1.Sym.next;
      local_e2.Sym.next.Sym.next = local_e1;
      local_e1.Sym.next = local_e2.Sym.next;
    }
    return mesh1;
  }
  
  static void __gl_meshDeleteMeshZap(GLUmesh mesh)
  {
    GLUface fHead = mesh.fHead;
    while (fHead.next != fHead) {
      __gl_meshZapFace(fHead.next);
    }
    assert (mesh.vHead.next == mesh.vHead);
  }
  
  public static void __gl_meshDeleteMesh(GLUmesh mesh)
  {
    GLUface fNext;
    for (GLUface local_f = mesh.fHead.next; local_f != mesh.fHead; local_f = fNext) {
      fNext = local_f.next;
    }
    GLUvertex vNext;
    for (GLUvertex local_v = mesh.vHead.next; local_v != mesh.vHead; local_v = vNext) {
      vNext = local_v.next;
    }
    GLUhalfEdge eNext;
    for (GLUhalfEdge local_e = mesh.eHead.next; local_e != mesh.eHead; local_e = eNext) {
      eNext = local_e.next;
    }
  }
  
  public static void __gl_meshCheckMesh(GLUmesh mesh)
  {
    GLUface fHead = mesh.fHead;
    GLUvertex vHead = mesh.vHead;
    GLUhalfEdge eHead = mesh.eHead;
    GLUface fPrev = fHead;
    GLUface local_f;
    for (fPrev = fHead; (local_f = fPrev.next) != fHead; fPrev = local_f)
    {
      assert (local_f.prev == fPrev);
      GLUhalfEdge local_e = local_f.anEdge;
      do
      {
        assert (local_e.Sym != local_e);
        assert (local_e.Sym.Sym == local_e);
        assert (local_e.Lnext.Onext.Sym == local_e);
        assert (local_e.Onext.Sym.Lnext == local_e);
        assert (local_e.Lface == local_f);
        local_e = local_e.Lnext;
      } while (local_e != local_f.anEdge);
    }
    assert ((local_f.prev == fPrev) && (local_f.anEdge == null) && (local_f.data == null));
    GLUvertex vPrev = vHead;
    GLUvertex local_v;
    for (vPrev = vHead; (local_v = vPrev.next) != vHead; vPrev = local_v)
    {
      assert (local_v.prev == vPrev);
      GLUhalfEdge local_e = local_v.anEdge;
      do
      {
        assert (local_e.Sym != local_e);
        assert (local_e.Sym.Sym == local_e);
        assert (local_e.Lnext.Onext.Sym == local_e);
        assert (local_e.Onext.Sym.Lnext == local_e);
        assert (local_e.Org == local_v);
        local_e = local_e.Onext;
      } while (local_e != local_v.anEdge);
    }
    assert ((local_v.prev == vPrev) && (local_v.anEdge == null) && (local_v.data == null));
    GLUhalfEdge ePrev = eHead;
    GLUhalfEdge local_e;
    for (ePrev = eHead; (local_e = ePrev.next) != eHead; ePrev = local_e)
    {
      assert (local_e.Sym.next == ePrev.Sym);
      assert (local_e.Sym != local_e);
      assert (local_e.Sym.Sym == local_e);
      assert (local_e.Org != null);
      assert (local_e.Sym.Org != null);
      assert (local_e.Lnext.Onext.Sym == local_e);
      assert (local_e.Onext.Sym.Lnext == local_e);
    }
    assert ((local_e.Sym.next == ePrev.Sym) && (local_e.Sym == mesh.eHeadSym) && (local_e.Sym.Sym == local_e) && (local_e.Org == null) && (local_e.Sym.Org == null) && (local_e.Lface == null) && (local_e.Sym.Lface == null));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Mesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */