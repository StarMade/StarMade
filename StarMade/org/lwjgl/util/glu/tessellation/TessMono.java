package org.lwjgl.util.glu.tessellation;

class TessMono
{
  static boolean __gl_meshTessellateMonoRegion(GLUface face)
  {
    GLUhalfEdge local_up = face.anEdge;
    assert ((local_up.Lnext != local_up) && (local_up.Lnext.Lnext != local_up));
    while (Geom.VertLeq(local_up.Sym.Org, local_up.Org)) {
      local_up = local_up.Onext.Sym;
    }
    while (Geom.VertLeq(local_up.Org, local_up.Sym.Org)) {
      local_up = local_up.Lnext;
    }
    GLUhalfEdge local_lo = local_up.Onext.Sym;
    while (local_up.Lnext != local_lo) {
      if (Geom.VertLeq(local_up.Sym.Org, local_lo.Org))
      {
        while ((local_lo.Lnext != local_up) && ((Geom.EdgeGoesLeft(local_lo.Lnext)) || (Geom.EdgeSign(local_lo.Org, local_lo.Sym.Org, local_lo.Lnext.Sym.Org) <= 0.0D)))
        {
          GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(local_lo.Lnext, local_lo);
          if (tempHalfEdge == null) {
            return false;
          }
          local_lo = tempHalfEdge.Sym;
        }
        local_lo = local_lo.Onext.Sym;
      }
      else
      {
        while ((local_lo.Lnext != local_up) && ((Geom.EdgeGoesRight(local_up.Onext.Sym)) || (Geom.EdgeSign(local_up.Sym.Org, local_up.Org, local_up.Onext.Sym.Org) >= 0.0D)))
        {
          GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(local_up, local_up.Onext.Sym);
          if (tempHalfEdge == null) {
            return false;
          }
          local_up = tempHalfEdge.Sym;
        }
        local_up = local_up.Lnext;
      }
    }
    assert (local_lo.Lnext != local_up);
    while (local_lo.Lnext.Lnext != local_up)
    {
      GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(local_lo.Lnext, local_lo);
      if (tempHalfEdge == null) {
        return false;
      }
      local_lo = tempHalfEdge.Sym;
    }
    return true;
  }
  
  public static boolean __gl_meshTessellateInterior(GLUmesh mesh)
  {
    GLUface next;
    for (GLUface local_f = mesh.fHead.next; local_f != mesh.fHead; local_f = next)
    {
      next = local_f.next;
      if ((local_f.inside) && (!__gl_meshTessellateMonoRegion(local_f))) {
        return false;
      }
    }
    return true;
  }
  
  public static void __gl_meshDiscardExterior(GLUmesh mesh)
  {
    GLUface next;
    for (GLUface local_f = mesh.fHead.next; local_f != mesh.fHead; local_f = next)
    {
      next = local_f.next;
      if (!local_f.inside) {
        Mesh.__gl_meshZapFace(local_f);
      }
    }
  }
  
  public static boolean __gl_meshSetWindingNumber(GLUmesh mesh, int value, boolean keepOnlyBoundary)
  {
    GLUhalfEdge eNext;
    for (GLUhalfEdge local_e = mesh.eHead.next; local_e != mesh.eHead; local_e = eNext)
    {
      eNext = local_e.next;
      if (local_e.Sym.Lface.inside != local_e.Lface.inside) {
        local_e.winding = (local_e.Lface.inside ? value : -value);
      } else if (!keepOnlyBoundary) {
        local_e.winding = 0;
      } else if (!Mesh.__gl_meshDelete(local_e)) {
        return false;
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.TessMono
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */