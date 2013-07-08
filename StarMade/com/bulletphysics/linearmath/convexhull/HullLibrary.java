package com.bulletphysics.linearmath.convexhull;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

public class HullLibrary
{
  public final IntArrayList vertexIndexMapping = new IntArrayList();
  private ObjectArrayList<Tri> tris = new ObjectArrayList();
  private static final float EPSILON = 1.0E-006F;
  
  public boolean createConvexHull(HullDesc arg1, HullResult arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      boolean ret = false;
      PHullResult local_hr = new PHullResult();
      int vcount = desc.vcount;
      if (vcount < 8) {
        vcount = 8;
      }
      ObjectArrayList<Vector3f> vertexSource = new ObjectArrayList();
      MiscUtil.resize(vertexSource, vcount, Vector3f.class);
      Vector3f scale = localStack.get$javax$vecmath$Vector3f();
      int[] ovcount = new int[1];
      boolean local_ok = cleanupVertices(desc.vcount, desc.vertices, desc.vertexStride, ovcount, vertexSource, desc.normalEpsilon, scale);
      if (local_ok)
      {
        for (int local_i = 0; local_i < ovcount[0]; local_i++)
        {
          Vector3f local_v = (Vector3f)vertexSource.getQuick(local_i);
          VectorUtil.mul(local_v, local_v, scale);
        }
        local_ok = computeHull(ovcount[0], vertexSource, local_hr, desc.maxVertices);
        if (local_ok)
        {
          ObjectArrayList<Vector3f> local_i = new ObjectArrayList();
          MiscUtil.resize(local_i, local_hr.vcount, Vector3f.class);
          bringOutYourDead(local_hr.vertices, local_hr.vcount, local_i, ovcount, local_hr.indices, local_hr.indexCount);
          ret = true;
          if (desc.hasHullFlag(HullFlags.TRIANGLES))
          {
            result.polygons = false;
            result.numOutputVertices = ovcount[0];
            MiscUtil.resize(result.outputVertices, ovcount[0], Vector3f.class);
            result.numFaces = local_hr.faceCount;
            result.numIndices = local_hr.indexCount;
            MiscUtil.resize(result.indices, local_hr.indexCount, 0);
            for (int local_v = 0; local_v < ovcount[0]; local_v++) {
              ((Vector3f)result.outputVertices.getQuick(local_v)).set((Tuple3f)local_i.getQuick(local_v));
            }
            if (desc.hasHullFlag(HullFlags.REVERSE_ORDER))
            {
              IntArrayList local_v = local_hr.indices;
              int source_idx = 0;
              IntArrayList dest_ptr = result.indices;
              int dest_idx = 0;
              for (int local_i1 = 0; local_i1 < local_hr.faceCount; local_i1++)
              {
                dest_ptr.set(dest_idx + 0, local_v.get(source_idx + 2));
                dest_ptr.set(dest_idx + 1, local_v.get(source_idx + 1));
                dest_ptr.set(dest_idx + 2, local_v.get(source_idx + 0));
                dest_idx += 3;
                source_idx += 3;
              }
            }
            else
            {
              for (int local_v = 0; local_v < local_hr.indexCount; local_v++) {
                result.indices.set(local_v, local_hr.indices.get(local_v));
              }
            }
          }
          else
          {
            result.polygons = true;
            result.numOutputVertices = ovcount[0];
            MiscUtil.resize(result.outputVertices, ovcount[0], Vector3f.class);
            result.numFaces = local_hr.faceCount;
            result.numIndices = (local_hr.indexCount + local_hr.faceCount);
            MiscUtil.resize(result.indices, result.numIndices, 0);
            for (int local_v = 0; local_v < ovcount[0]; local_v++) {
              ((Vector3f)result.outputVertices.getQuick(local_v)).set((Tuple3f)local_i.getQuick(local_v));
            }
            IntArrayList local_v = local_hr.indices;
            int source_idx = 0;
            IntArrayList dest_ptr = result.indices;
            int dest_idx = 0;
            for (int local_i1 = 0; local_i1 < local_hr.faceCount; local_i1++)
            {
              dest_ptr.set(dest_idx + 0, 3);
              if (desc.hasHullFlag(HullFlags.REVERSE_ORDER))
              {
                dest_ptr.set(dest_idx + 1, local_v.get(source_idx + 2));
                dest_ptr.set(dest_idx + 2, local_v.get(source_idx + 1));
                dest_ptr.set(dest_idx + 3, local_v.get(source_idx + 0));
              }
              else
              {
                dest_ptr.set(dest_idx + 1, local_v.get(source_idx + 0));
                dest_ptr.set(dest_idx + 2, local_v.get(source_idx + 1));
                dest_ptr.set(dest_idx + 3, local_v.get(source_idx + 2));
              }
              dest_idx += 4;
              source_idx += 3;
            }
          }
          releaseHull(local_hr);
        }
      }
      return ret;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean releaseResult(HullResult result)
  {
    if (result.outputVertices.size() != 0)
    {
      result.numOutputVertices = 0;
      result.outputVertices.clear();
    }
    if (result.indices.size() != 0)
    {
      result.numIndices = 0;
      result.indices.clear();
    }
    return true;
  }
  
  private boolean computeHull(int vcount, ObjectArrayList<Vector3f> vertices, PHullResult result, int vlimit)
  {
    int[] tris_count = new int[1];
    int ret = calchull(vertices, vcount, result.indices, tris_count, vlimit);
    if (ret == 0) {
      return false;
    }
    result.indexCount = (tris_count[0] * 3);
    result.faceCount = tris_count[0];
    result.vertices = vertices;
    result.vcount = vcount;
    return true;
  }
  
  private Tri allocateTriangle(int local_a, int local_b, int local_c)
  {
    Tri local_tr = new Tri(local_a, local_b, local_c);
    local_tr.field_315 = this.tris.size();
    this.tris.add(local_tr);
    return local_tr;
  }
  
  private void deAllocateTriangle(Tri tri)
  {
    assert (this.tris.getQuick(tri.field_315) == tri);
    this.tris.setQuick(tri.field_315, null);
  }
  
  private void b2bfix(Tri local_s, Tri local_t)
  {
    for (int local_i = 0; local_i < 3; local_i++)
    {
      int local_i1 = (local_i + 1) % 3;
      int local_i2 = (local_i + 2) % 3;
      int local_a = local_s.getCoord(local_i1);
      int local_b = local_s.getCoord(local_i2);
      assert (((Tri)this.tris.getQuick(local_s.neib(local_a, local_b).get())).neib(local_b, local_a).get() == local_s.field_315);
      assert (((Tri)this.tris.getQuick(local_t.neib(local_a, local_b).get())).neib(local_b, local_a).get() == local_t.field_315);
      ((Tri)this.tris.getQuick(local_s.neib(local_a, local_b).get())).neib(local_b, local_a).set(local_t.neib(local_b, local_a).get());
      ((Tri)this.tris.getQuick(local_t.neib(local_b, local_a).get())).neib(local_a, local_b).set(local_s.neib(local_a, local_b).get());
    }
  }
  
  private void removeb2b(Tri local_s, Tri local_t)
  {
    b2bfix(local_s, local_t);
    deAllocateTriangle(local_s);
    deAllocateTriangle(local_t);
  }
  
  private void checkit(Tri local_t)
  {
    assert (this.tris.getQuick(local_t.field_315) == local_t);
    for (int local_i = 0; local_i < 3; local_i++)
    {
      int local_i1 = (local_i + 1) % 3;
      int local_i2 = (local_i + 2) % 3;
      int local_a = local_t.getCoord(local_i1);
      int local_b = local_t.getCoord(local_i2);
      assert (local_a != local_b);
      assert (((Tri)this.tris.getQuick(local_t.field_314.getCoord(local_i))).neib(local_b, local_a).get() == local_t.field_315);
    }
  }
  
  private Tri extrudable(float epsilon)
  {
    Tri local_t = null;
    for (int local_i = 0; local_i < this.tris.size(); local_i++) {
      if ((local_t == null) || ((this.tris.getQuick(local_i) != null) && (local_t.rise < ((Tri)this.tris.getQuick(local_i)).rise))) {
        local_t = (Tri)this.tris.getQuick(local_i);
      }
    }
    return local_t.rise > epsilon ? local_t : null;
  }
  
  private int calchull(ObjectArrayList<Vector3f> verts, int verts_count, IntArrayList tris_out, int[] tris_count, int vlimit)
  {
    int local_rc = calchullgen(verts, verts_count, vlimit);
    if (local_rc == 0) {
      return 0;
    }
    IntArrayList local_ts = new IntArrayList();
    for (int local_i = 0; local_i < this.tris.size(); local_i++) {
      if (this.tris.getQuick(local_i) != null)
      {
        for (int local_j = 0; local_j < 3; local_j++) {
          local_ts.add(((Tri)this.tris.getQuick(local_i)).getCoord(local_j));
        }
        deAllocateTriangle((Tri)this.tris.getQuick(local_i));
      }
    }
    tris_count[0] = (local_ts.size() / 3);
    MiscUtil.resize(tris_out, local_ts.size(), 0);
    for (int local_i = 0; local_i < local_ts.size(); local_i++) {
      tris_out.set(local_i, local_ts.get(local_i));
    }
    MiscUtil.resize(this.tris, 0, Tri.class);
    return 1;
  }
  
  private int calchullgen(ObjectArrayList<Vector3f> arg1, int arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (verts_count < 4) {
        return 0;
      }
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      if (vlimit == 0) {
        vlimit = 1000000000;
      }
      Vector3f bmin = localStack.get$javax$vecmath$Vector3f((Vector3f)verts.getQuick(0));
      Vector3f bmax = localStack.get$javax$vecmath$Vector3f((Vector3f)verts.getQuick(0));
      IntArrayList isextreme = new IntArrayList();
      IntArrayList allow = new IntArrayList();
      for (int local_j = 0; local_j < verts_count; local_j++)
      {
        allow.add(1);
        isextreme.add(0);
        VectorUtil.setMin(bmin, (Vector3f)verts.getQuick(local_j));
        VectorUtil.setMax(bmax, (Vector3f)verts.getQuick(local_j));
      }
      tmp.sub(bmax, bmin);
      float local_j = tmp.length() * 0.001F;
      assert (local_j != 0.0F);
      Int4 local_p = findSimplex(verts, verts_count, allow, new Int4());
      if (local_p.field_2014 == -1) {
        return 0;
      }
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.add(center, (Vector3f)verts.getQuick(local_p.getCoord(0)), (Vector3f)verts.getQuick(local_p.getCoord(1)), (Vector3f)verts.getQuick(local_p.getCoord(2)), (Vector3f)verts.getQuick(local_p.getCoord(3)));
      center.scale(0.25F);
      Tri local_t0 = allocateTriangle(local_p.getCoord(2), local_p.getCoord(3), local_p.getCoord(1));
      local_t0.field_314.set(2, 3, 1);
      Tri local_t11 = allocateTriangle(local_p.getCoord(3), local_p.getCoord(2), local_p.getCoord(0));
      local_t11.field_314.set(3, 2, 0);
      Tri local_t2 = allocateTriangle(local_p.getCoord(0), local_p.getCoord(1), local_p.getCoord(3));
      local_t2.field_314.set(0, 1, 3);
      Tri local_t3 = allocateTriangle(local_p.getCoord(1), local_p.getCoord(0), local_p.getCoord(2));
      local_t3.field_314.set(1, 0, 2);
      isextreme.set(local_p.getCoord(0), 1);
      isextreme.set(local_p.getCoord(1), 1);
      isextreme.set(local_p.getCoord(2), 1);
      isextreme.set(local_p.getCoord(3), 1);
      checkit(local_t0);
      checkit(local_t11);
      checkit(local_t2);
      checkit(local_t3);
      Vector3f local_n = localStack.get$javax$vecmath$Vector3f();
      for (int local_j1 = 0; local_j1 < this.tris.size(); local_j1++)
      {
        Tri local_t = (Tri)this.tris.getQuick(local_j1);
        assert (local_t != null);
        assert (local_t.vmax < 0);
        triNormal((Vector3f)verts.getQuick(local_t.getCoord(0)), (Vector3f)verts.getQuick(local_t.getCoord(1)), (Vector3f)verts.getQuick(local_t.getCoord(2)), local_n);
        local_t.vmax = maxdirsterid(verts, verts_count, local_n, allow);
        tmp.sub((Tuple3f)verts.getQuick(local_t.vmax), (Tuple3f)verts.getQuick(local_t.getCoord(0)));
        local_t.rise = local_n.dot(tmp);
      }
      vlimit -= 4;
      Tri local_j1;
      while ((vlimit > 0) && ((local_j1 = extrudable(local_j)) != null))
      {
        Int3 local_t = local_j1;
        int local_v = local_j1.vmax;
        assert (local_v != -1);
        assert (isextreme.get(local_v) == 0);
        isextreme.set(local_v, 1);
        int local_j2 = this.tris.size();
        while (local_j2-- != 0) {
          if (this.tris.getQuick(local_j2) != null)
          {
            Int3 local_t1 = (Int3)this.tris.getQuick(local_j2);
            if (above(verts, local_t1, (Vector3f)verts.getQuick(local_v), 0.01F * local_j)) {
              extrude((Tri)this.tris.getQuick(local_j2), local_v);
            }
          }
        }
        local_j2 = this.tris.size();
        while (local_j2-- != 0) {
          if (this.tris.getQuick(local_j2) != null)
          {
            if (!hasvert((Int3)this.tris.getQuick(local_j2), local_v)) {
              break;
            }
            Int3 local_t1 = (Int3)this.tris.getQuick(local_j2);
            tmp1.sub((Tuple3f)verts.getQuick(local_t1.getCoord(1)), (Tuple3f)verts.getQuick(local_t1.getCoord(0)));
            tmp2.sub((Tuple3f)verts.getQuick(local_t1.getCoord(2)), (Tuple3f)verts.getQuick(local_t1.getCoord(1)));
            tmp.cross(tmp1, tmp2);
            if ((above(verts, local_t1, center, 0.01F * local_j)) || (tmp.length() < local_j * local_j * 0.1F))
            {
              Tri local_nb = (Tri)this.tris.getQuick(((Tri)this.tris.getQuick(local_j2)).field_314.getCoord(0));
              assert (local_nb != null);
              assert (!hasvert(local_nb, local_v));
              assert (local_nb.field_315 < local_j2);
              extrude(local_nb, local_v);
              local_j2 = this.tris.size();
            }
          }
        }
        local_j2 = this.tris.size();
        while (local_j2-- != 0)
        {
          Tri local_t1 = (Tri)this.tris.getQuick(local_j2);
          if (local_t1 != null)
          {
            if (local_t1.vmax >= 0) {
              break;
            }
            triNormal((Vector3f)verts.getQuick(local_t1.getCoord(0)), (Vector3f)verts.getQuick(local_t1.getCoord(1)), (Vector3f)verts.getQuick(local_t1.getCoord(2)), local_n);
            local_t1.vmax = maxdirsterid(verts, verts_count, local_n, allow);
            if (isextreme.get(local_t1.vmax) != 0)
            {
              local_t1.vmax = -1;
            }
            else
            {
              tmp.sub((Tuple3f)verts.getQuick(local_t1.vmax), (Tuple3f)verts.getQuick(local_t1.getCoord(0)));
              local_t1.rise = local_n.dot(tmp);
            }
          }
        }
        vlimit--;
      }
      return 1;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private Int4 findSimplex(ObjectArrayList<Vector3f> arg1, int arg2, IntArrayList arg3, Int4 arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f[] basis = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
      basis[0].set(0.01F, 0.02F, 1.0F);
      int local_p0 = maxdirsterid(verts, verts_count, basis[0], allow);
      tmp.negate(basis[0]);
      int local_p1 = maxdirsterid(verts, verts_count, tmp, allow);
      basis[0].sub((Tuple3f)verts.getQuick(local_p0), (Tuple3f)verts.getQuick(local_p1));
      if ((local_p0 == local_p1) || ((basis[0].field_615 == 0.0F) && (basis[0].field_616 == 0.0F) && (basis[0].field_617 == 0.0F)))
      {
        out.set(-1, -1, -1, -1);
        return out;
      }
      tmp.set(1.0F, 0.02F, 0.0F);
      basis[1].cross(tmp, basis[0]);
      tmp.set(-0.02F, 1.0F, 0.0F);
      basis[2].cross(tmp, basis[0]);
      if (basis[1].length() > basis[2].length())
      {
        basis[1].normalize();
      }
      else
      {
        basis[1].set(basis[2]);
        basis[1].normalize();
      }
      int local_p2 = maxdirsterid(verts, verts_count, basis[1], allow);
      if ((local_p2 == local_p0) || (local_p2 == local_p1))
      {
        tmp.negate(basis[1]);
        local_p2 = maxdirsterid(verts, verts_count, tmp, allow);
      }
      if ((local_p2 == local_p0) || (local_p2 == local_p1))
      {
        out.set(-1, -1, -1, -1);
        return out;
      }
      basis[1].sub((Tuple3f)verts.getQuick(local_p2), (Tuple3f)verts.getQuick(local_p0));
      basis[2].cross(basis[1], basis[0]);
      basis[2].normalize();
      int local_p3 = maxdirsterid(verts, verts_count, basis[2], allow);
      if ((local_p3 == local_p0) || (local_p3 == local_p1) || (local_p3 == local_p2))
      {
        tmp.negate(basis[2]);
        local_p3 = maxdirsterid(verts, verts_count, tmp, allow);
      }
      if ((local_p3 == local_p0) || (local_p3 == local_p1) || (local_p3 == local_p2))
      {
        out.set(-1, -1, -1, -1);
        return out;
      }
      assert ((local_p0 != local_p1) && (local_p0 != local_p2) && (local_p0 != local_p3) && (local_p1 != local_p2) && (local_p1 != local_p3) && (local_p2 != local_p3));
      tmp1.sub((Tuple3f)verts.getQuick(local_p1), (Tuple3f)verts.getQuick(local_p0));
      tmp2.sub((Tuple3f)verts.getQuick(local_p2), (Tuple3f)verts.getQuick(local_p0));
      tmp2.cross(tmp1, tmp2);
      tmp1.sub((Tuple3f)verts.getQuick(local_p3), (Tuple3f)verts.getQuick(local_p0));
      if (tmp1.dot(tmp2) < 0.0F)
      {
        int swap_tmp = local_p2;
        local_p2 = local_p3;
        local_p3 = swap_tmp;
      }
      out.set(local_p0, local_p1, local_p2, local_p3);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private void extrude(Tri local_t0, int local_v)
  {
    Int3 local_t = new Int3(local_t0);
    int local_n = this.tris.size();
    Tri local_ta = allocateTriangle(local_v, local_t.getCoord(1), local_t.getCoord(2));
    local_ta.field_314.set(local_t0.field_314.getCoord(0), local_n + 1, local_n + 2);
    ((Tri)this.tris.getQuick(local_t0.field_314.getCoord(0))).neib(local_t.getCoord(1), local_t.getCoord(2)).set(local_n + 0);
    Tri local_tb = allocateTriangle(local_v, local_t.getCoord(2), local_t.getCoord(0));
    local_tb.field_314.set(local_t0.field_314.getCoord(1), local_n + 2, local_n + 0);
    ((Tri)this.tris.getQuick(local_t0.field_314.getCoord(1))).neib(local_t.getCoord(2), local_t.getCoord(0)).set(local_n + 1);
    Tri local_tc = allocateTriangle(local_v, local_t.getCoord(0), local_t.getCoord(1));
    local_tc.field_314.set(local_t0.field_314.getCoord(2), local_n + 0, local_n + 1);
    ((Tri)this.tris.getQuick(local_t0.field_314.getCoord(2))).neib(local_t.getCoord(0), local_t.getCoord(1)).set(local_n + 2);
    checkit(local_ta);
    checkit(local_tb);
    checkit(local_tc);
    if (hasvert((Int3)this.tris.getQuick(local_ta.field_314.getCoord(0)), local_v)) {
      removeb2b(local_ta, (Tri)this.tris.getQuick(local_ta.field_314.getCoord(0)));
    }
    if (hasvert((Int3)this.tris.getQuick(local_tb.field_314.getCoord(0)), local_v)) {
      removeb2b(local_tb, (Tri)this.tris.getQuick(local_tb.field_314.getCoord(0)));
    }
    if (hasvert((Int3)this.tris.getQuick(local_tc.field_314.getCoord(0)), local_v)) {
      removeb2b(local_tc, (Tri)this.tris.getQuick(local_tc.field_314.getCoord(0)));
    }
    deAllocateTriangle(local_t0);
  }
  
  private void bringOutYourDead(ObjectArrayList<Vector3f> verts, int vcount, ObjectArrayList<Vector3f> overts, int[] ocount, IntArrayList indices, int indexcount)
  {
    IntArrayList tmpIndices = new IntArrayList();
    for (int local_i = 0; local_i < this.vertexIndexMapping.size(); local_i++) {
      tmpIndices.add(this.vertexIndexMapping.size());
    }
    IntArrayList local_i = new IntArrayList();
    MiscUtil.resize(local_i, vcount, 0);
    ocount[0] = 0;
    for (int local_i1 = 0; local_i1 < indexcount; local_i1++)
    {
      int local_v = indices.get(local_i1);
      assert ((local_v >= 0) && (local_v < vcount));
      if (local_i.get(local_v) != 0)
      {
        indices.set(local_i1, local_i.get(local_v) - 1);
      }
      else
      {
        indices.set(local_i1, ocount[0]);
        ((Vector3f)overts.getQuick(ocount[0])).set((Tuple3f)verts.getQuick(local_v));
        for (int local_k = 0; local_k < this.vertexIndexMapping.size(); local_k++) {
          if (tmpIndices.get(local_k) == local_v) {
            this.vertexIndexMapping.set(local_k, ocount[0]);
          }
        }
        ocount[0] += 1;
        assert ((ocount[0] >= 0) && (ocount[0] <= vcount));
        local_i.set(local_v, ocount[0]);
      }
    }
  }
  
  private boolean cleanupVertices(int arg1, ObjectArrayList<Vector3f> arg2, int arg3, int[] arg4, ObjectArrayList<Vector3f> arg5, float arg6, Vector3f arg7)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (svcount == 0) {
        return false;
      }
      this.vertexIndexMapping.clear();
      vcount[0] = 0;
      float[] recip = new float[3];
      if (scale != null) {
        scale.set(1.0F, 1.0F, 1.0F);
      }
      float[] bmin = { 3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F };
      float[] bmax = { -3.402824E+038F, -3.402824E+038F, -3.402824E+038F };
      ObjectArrayList<Vector3f> vtx_ptr = svertices;
      int vtx_idx = 0;
      for (int local_i = 0; local_i < svcount; local_i++)
      {
        Vector3f local_p = (Vector3f)vtx_ptr.getQuick(vtx_idx);
        vtx_idx++;
        for (int local_j = 0; local_j < 3; local_j++)
        {
          if (VectorUtil.getCoord(local_p, local_j) < bmin[local_j]) {
            bmin[local_j] = VectorUtil.getCoord(local_p, local_j);
          }
          if (VectorUtil.getCoord(local_p, local_j) > bmax[local_j]) {
            bmax[local_j] = VectorUtil.getCoord(local_p, local_j);
          }
        }
      }
      float local_i = bmax[0] - bmin[0];
      float local_p = bmax[1] - bmin[1];
      float local_j = bmax[2] - bmin[2];
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      center.field_615 = (local_i * 0.5F + bmin[0]);
      center.field_616 = (local_p * 0.5F + bmin[1]);
      center.field_617 = (local_j * 0.5F + bmin[2]);
      if ((local_i < 1.0E-006F) || (local_p < 1.0E-006F) || (local_j < 1.0E-006F) || (svcount < 3))
      {
        float len = 3.4028235E+38F;
        if ((local_i > 1.0E-006F) && (local_i < len)) {
          len = local_i;
        }
        if ((local_p > 1.0E-006F) && (local_p < len)) {
          len = local_p;
        }
        if ((local_j > 1.0E-006F) && (local_j < len)) {
          len = local_j;
        }
        if (len == 3.4028235E+38F)
        {
          local_i = local_p = local_j = 0.01F;
        }
        else
        {
          if (local_i < 1.0E-006F) {
            local_i = len * 0.05F;
          }
          if (local_p < 1.0E-006F) {
            local_p = len * 0.05F;
          }
          if (local_j < 1.0E-006F) {
            local_j = len * 0.05F;
          }
        }
        float local_x1 = center.field_615 - local_i;
        float local_x2 = center.field_615 + local_i;
        float local_y1 = center.field_616 - local_p;
        float local_y2 = center.field_616 + local_p;
        float local_z1 = center.field_617 - local_j;
        float local_z2 = center.field_617 + local_j;
        addPoint(vcount, vertices, local_x1, local_y1, local_z1);
        addPoint(vcount, vertices, local_x2, local_y1, local_z1);
        addPoint(vcount, vertices, local_x2, local_y2, local_z1);
        addPoint(vcount, vertices, local_x1, local_y2, local_z1);
        addPoint(vcount, vertices, local_x1, local_y1, local_z2);
        addPoint(vcount, vertices, local_x2, local_y1, local_z2);
        addPoint(vcount, vertices, local_x2, local_y2, local_z2);
        addPoint(vcount, vertices, local_x1, local_y2, local_z2);
        return true;
      }
      if (scale != null)
      {
        scale.field_615 = local_i;
        scale.field_616 = local_p;
        scale.field_617 = local_j;
        recip[0] = (1.0F / local_i);
        recip[1] = (1.0F / local_p);
        recip[2] = (1.0F / local_j);
        center.field_615 *= recip[0];
        center.field_616 *= recip[1];
        center.field_617 *= recip[2];
      }
      vtx_ptr = svertices;
      vtx_idx = 0;
      for (int len = 0; len < svcount; len++)
      {
        Vector3f local_x1 = (Vector3f)vtx_ptr.getQuick(vtx_idx);
        vtx_idx++;
        float local_x2 = local_x1.field_615;
        float local_y1 = local_x1.field_616;
        float local_y2 = local_x1.field_617;
        if (scale != null)
        {
          local_x2 *= recip[0];
          local_y1 *= recip[1];
          local_y2 *= recip[2];
        }
        for (int local_z1 = 0; local_z1 < vcount[0]; local_z1++)
        {
          Vector3f local_z2 = (Vector3f)vertices.getQuick(local_z1);
          float local_x = local_z2.field_615;
          float local_y = local_z2.field_616;
          float local_z = local_z2.field_617;
          local_i = Math.abs(local_x - local_x2);
          local_p = Math.abs(local_y - local_y1);
          local_j = Math.abs(local_z - local_y2);
          if ((local_i < normalepsilon) && (local_p < normalepsilon) && (local_j < normalepsilon))
          {
            float dist1 = getDist(local_x2, local_y1, local_y2, center);
            float dist2 = getDist(local_z2.field_615, local_z2.field_616, local_z2.field_617, center);
            if (dist1 <= dist2) {
              break;
            }
            local_z2.field_615 = local_x2;
            local_z2.field_616 = local_y1;
            local_z2.field_617 = local_y2;
            break;
          }
        }
        if (local_z1 == vcount[0])
        {
          Vector3f local_z2 = (Vector3f)vertices.getQuick(vcount[0]);
          local_z2.field_615 = local_x2;
          local_z2.field_616 = local_y1;
          local_z2.field_617 = local_y2;
          vcount[0] += 1;
        }
        this.vertexIndexMapping.add(local_z1);
      }
      bmin = new float[] { 3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F };
      bmax = new float[] { -3.402824E+038F, -3.402824E+038F, -3.402824E+038F };
      for (int len = 0; len < vcount[0]; len++)
      {
        Vector3f local_x1 = (Vector3f)vertices.getQuick(len);
        for (int local_x2 = 0; local_x2 < 3; local_x2++)
        {
          if (VectorUtil.getCoord(local_x1, local_x2) < bmin[local_x2]) {
            bmin[local_x2] = VectorUtil.getCoord(local_x1, local_x2);
          }
          if (VectorUtil.getCoord(local_x1, local_x2) > bmax[local_x2]) {
            bmax[local_x2] = VectorUtil.getCoord(local_x1, local_x2);
          }
        }
      }
      local_i = bmax[0] - bmin[0];
      local_p = bmax[1] - bmin[1];
      local_j = bmax[2] - bmin[2];
      if ((local_i < 1.0E-006F) || (local_p < 1.0E-006F) || (local_j < 1.0E-006F) || (vcount[0] < 3))
      {
        float len = local_i * 0.5F + bmin[0];
        float local_x1 = local_p * 0.5F + bmin[1];
        float local_x2 = local_j * 0.5F + bmin[2];
        float local_y1 = 3.4028235E+38F;
        if ((local_i >= 1.0E-006F) && (local_i < local_y1)) {
          local_y1 = local_i;
        }
        if ((local_p >= 1.0E-006F) && (local_p < local_y1)) {
          local_y1 = local_p;
        }
        if ((local_j >= 1.0E-006F) && (local_j < local_y1)) {
          local_y1 = local_j;
        }
        if (local_y1 == 3.4028235E+38F)
        {
          local_i = local_p = local_j = 0.01F;
        }
        else
        {
          if (local_i < 1.0E-006F) {
            local_i = local_y1 * 0.05F;
          }
          if (local_p < 1.0E-006F) {
            local_p = local_y1 * 0.05F;
          }
          if (local_j < 1.0E-006F) {
            local_j = local_y1 * 0.05F;
          }
        }
        float local_y2 = len - local_i;
        float local_z1 = len + local_i;
        float local_z2 = local_x1 - local_p;
        float local_x = local_x1 + local_p;
        float local_y = local_x2 - local_j;
        float local_z = local_x2 + local_j;
        vcount[0] = 0;
        addPoint(vcount, vertices, local_y2, local_z2, local_y);
        addPoint(vcount, vertices, local_z1, local_z2, local_y);
        addPoint(vcount, vertices, local_z1, local_x, local_y);
        addPoint(vcount, vertices, local_y2, local_x, local_y);
        addPoint(vcount, vertices, local_y2, local_z2, local_z);
        addPoint(vcount, vertices, local_z1, local_z2, local_z);
        addPoint(vcount, vertices, local_z1, local_x, local_z);
        addPoint(vcount, vertices, local_y2, local_x, local_z);
        return true;
      }
      return true;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static boolean hasvert(Int3 local_t, int local_v)
  {
    return (local_t.getCoord(0) == local_v) || (local_t.getCoord(1) == local_v) || (local_t.getCoord(2) == local_v);
  }
  
  private static Vector3f orth(Vector3f arg0, Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_a = localStack.get$javax$vecmath$Vector3f();
      local_a.set(0.0F, 0.0F, 1.0F);
      local_a.cross(local_v, local_a);
      Vector3f local_b = localStack.get$javax$vecmath$Vector3f();
      local_b.set(0.0F, 1.0F, 0.0F);
      local_b.cross(local_v, local_b);
      if (local_a.length() > local_b.length())
      {
        out.normalize(local_a);
        return out;
      }
      out.normalize(local_b);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static int maxdirfiltered(ObjectArrayList<Vector3f> local_p, int count, Vector3f dir, IntArrayList allow)
  {
    assert (count != 0);
    int local_m = -1;
    for (int local_i = 0; local_i < count; local_i++) {
      if ((allow.get(local_i) != 0) && ((local_m == -1) || (((Vector3f)local_p.getQuick(local_i)).dot(dir) > ((Vector3f)local_p.getQuick(local_m)).dot(dir)))) {
        local_m = local_i;
      }
    }
    assert (local_m != -1);
    return local_m;
  }
  
  private static int maxdirsterid(ObjectArrayList<Vector3f> arg0, int arg1, Vector3f arg2, IntArrayList arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_u = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_v = localStack.get$javax$vecmath$Vector3f();
      for (int local_m = -1; local_m == -1; local_m = -1)
      {
        local_m = maxdirfiltered(local_p, count, dir, allow);
        if (allow.get(local_m) == 3) {
          return local_m;
        }
        orth(dir, local_u);
        local_v.cross(local_u, dir);
        int local_ma = -1;
        for (float local_x = 0.0F; local_x <= 360.0F; local_x += 45.0F)
        {
          float local_s = (float)Math.sin(0.01745329F * local_x);
          float local_c = (float)Math.cos(0.01745329F * local_x);
          tmp1.scale(local_s, local_u);
          tmp2.scale(local_c, local_v);
          tmp.add(tmp1, tmp2);
          tmp.scale(0.025F);
          tmp.add(dir);
          int local_mb = maxdirfiltered(local_p, count, tmp, allow);
          if ((local_ma == local_m) && (local_mb == local_m))
          {
            allow.set(local_m, 3);
            return local_m;
          }
          if ((local_ma != -1) && (local_ma != local_mb))
          {
            int local_mc = local_ma;
            for (float local_xx = local_x - 40.0F; local_xx <= local_x; local_xx += 5.0F)
            {
              local_s = (float)Math.sin(0.01745329F * local_xx);
              local_c = (float)Math.cos(0.01745329F * local_xx);
              tmp1.scale(local_s, local_u);
              tmp2.scale(local_c, local_v);
              tmp.add(tmp1, tmp2);
              tmp.scale(0.025F);
              tmp.add(dir);
              int local_md = maxdirfiltered(local_p, count, tmp, allow);
              if ((local_mc == local_m) && (local_md == local_m))
              {
                allow.set(local_m, 3);
                return local_m;
              }
              local_mc = local_md;
            }
          }
          local_ma = local_mb;
        }
        allow.set(local_m, 0);
      }
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      return local_m;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static Vector3f triNormal(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      tmp1.sub(local_v1, local_v0);
      tmp2.sub(local_v2, local_v1);
      Vector3f local_cp = localStack.get$javax$vecmath$Vector3f();
      local_cp.cross(tmp1, tmp2);
      float local_m = local_cp.length();
      if (local_m == 0.0F)
      {
        out.set(1.0F, 0.0F, 0.0F);
        return out;
      }
      out.scale(1.0F / local_m, local_cp);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static boolean above(ObjectArrayList<Vector3f> arg0, Int3 arg1, Vector3f arg2, float arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_n = triNormal((Vector3f)vertices.getQuick(local_t.getCoord(0)), (Vector3f)vertices.getQuick(local_t.getCoord(1)), (Vector3f)vertices.getQuick(local_t.getCoord(2)), localStack.get$javax$vecmath$Vector3f());
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.sub(local_p, (Tuple3f)vertices.getQuick(local_t.getCoord(0)));
      return local_n.dot(tmp) > epsilon;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static void releaseHull(PHullResult result)
  {
    if (result.indices.size() != 0) {
      result.indices.clear();
    }
    result.vcount = 0;
    result.indexCount = 0;
    result.vertices = null;
  }
  
  private static void addPoint(int[] vcount, ObjectArrayList<Vector3f> local_p, float local_x, float local_y, float local_z)
  {
    Vector3f dest = (Vector3f)local_p.getQuick(vcount[0]);
    dest.field_615 = local_x;
    dest.field_616 = local_y;
    dest.field_617 = local_z;
    vcount[0] += 1;
  }
  
  private static float getDist(float local_px, float local_py, float local_pz, Vector3f local_p2)
  {
    float local_dx = local_px - local_p2.field_615;
    float local_dy = local_py - local_p2.field_616;
    float local_dz = local_pz - local_p2.field_617;
    return local_dx * local_dx + local_dy * local_dy + local_dz * local_dz;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.HullLibrary
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */