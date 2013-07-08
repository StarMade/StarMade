package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

class BoxCollision
{
  public static final float BOX_PLANE_EPSILON = 1.0E-006F;
  
  public static boolean BT_GREATER(float local_x, float local_y)
  {
    return Math.abs(local_x) > local_y;
  }
  
  public static float BT_MAX3(float local_a, float local_b, float local_c)
  {
    return Math.max(local_a, Math.max(local_b, local_c));
  }
  
  public static float BT_MIN3(float local_a, float local_b, float local_c)
  {
    return Math.min(local_a, Math.min(local_b, local_c));
  }
  
  public static boolean TEST_CROSS_EDGE_BOX_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend, int i_dir_0, int i_dir_1, int i_comp_0, int i_comp_1)
  {
    float dir0 = -VectorUtil.getCoord(edge, i_dir_0);
    float dir1 = VectorUtil.getCoord(edge, i_dir_1);
    float pmin = VectorUtil.getCoord(pointa, i_comp_0) * dir0 + VectorUtil.getCoord(pointa, i_comp_1) * dir1;
    float pmax = VectorUtil.getCoord(pointb, i_comp_0) * dir0 + VectorUtil.getCoord(pointb, i_comp_1) * dir1;
    if (pmin > pmax)
    {
      pmin += pmax;
      pmax = pmin - pmax;
      pmin -= pmax;
    }
    float abs_dir0 = VectorUtil.getCoord(absolute_edge, i_dir_0);
    float abs_dir1 = VectorUtil.getCoord(absolute_edge, i_dir_1);
    float rad = VectorUtil.getCoord(_extend, i_comp_0) * abs_dir0 + VectorUtil.getCoord(_extend, i_comp_1) * abs_dir1;
    return (pmin <= rad) && (-rad <= pmax);
  }
  
  public static boolean TEST_CROSS_EDGE_BOX_X_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend)
  {
    return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 2, 1, 1, 2);
  }
  
  public static boolean TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend)
  {
    return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 0, 2, 2, 0);
  }
  
  public static boolean TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend)
  {
    return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 1, 0, 0, 1);
  }
  
  public static float bt_mat3_dot_col(Matrix3f mat, Vector3f vec3, int colindex)
  {
    return vec3.field_615 * mat.getElement(0, colindex) + vec3.field_616 * mat.getElement(1, colindex) + vec3.field_617 * mat.getElement(2, colindex);
  }
  
  public static boolean compareTransformsEqual(Transform local_t1, Transform local_t2)
  {
    return local_t1.equals(local_t2);
  }
  
  public static class AABB
  {
    public final Vector3f min = new Vector3f();
    public final Vector3f max = new Vector3f();
    
    public AABB() {}
    
    public AABB(Vector3f local_V1, Vector3f local_V2, Vector3f local_V3)
    {
      calc_from_triangle(local_V1, local_V2, local_V3);
    }
    
    public AABB(Vector3f local_V1, Vector3f local_V2, Vector3f local_V3, float margin)
    {
      calc_from_triangle_margin(local_V1, local_V2, local_V3, margin);
    }
    
    public AABB(AABB other)
    {
      set(other);
    }
    
    public AABB(AABB other, float margin)
    {
      this(other);
      this.min.field_615 -= margin;
      this.min.field_616 -= margin;
      this.min.field_617 -= margin;
      this.max.field_615 += margin;
      this.max.field_616 += margin;
      this.max.field_617 += margin;
    }
    
    public void init(Vector3f local_V1, Vector3f local_V2, Vector3f local_V3, float margin)
    {
      calc_from_triangle_margin(local_V1, local_V2, local_V3, margin);
    }
    
    public void set(AABB other)
    {
      this.min.set(other.min);
      this.max.set(other.max);
    }
    
    public void invalidate()
    {
      this.min.set(3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F);
      this.max.set(-3.402824E+038F, -3.402824E+038F, -3.402824E+038F);
    }
    
    public void increment_margin(float margin)
    {
      this.min.field_615 -= margin;
      this.min.field_616 -= margin;
      this.min.field_617 -= margin;
      this.max.field_615 += margin;
      this.max.field_616 += margin;
      this.max.field_617 += margin;
    }
    
    public void copy_with_margin(AABB other, float margin)
    {
      other.min.field_615 -= margin;
      other.min.field_616 -= margin;
      other.min.field_617 -= margin;
      other.max.field_615 += margin;
      other.max.field_616 += margin;
      other.max.field_617 += margin;
    }
    
    public void calc_from_triangle(Vector3f local_V1, Vector3f local_V2, Vector3f local_V3)
    {
      this.min.field_615 = BoxCollision.BT_MIN3(local_V1.field_615, local_V2.field_615, local_V3.field_615);
      this.min.field_616 = BoxCollision.BT_MIN3(local_V1.field_616, local_V2.field_616, local_V3.field_616);
      this.min.field_617 = BoxCollision.BT_MIN3(local_V1.field_617, local_V2.field_617, local_V3.field_617);
      this.max.field_615 = BoxCollision.BT_MAX3(local_V1.field_615, local_V2.field_615, local_V3.field_615);
      this.max.field_616 = BoxCollision.BT_MAX3(local_V1.field_616, local_V2.field_616, local_V3.field_616);
      this.max.field_617 = BoxCollision.BT_MAX3(local_V1.field_617, local_V2.field_617, local_V3.field_617);
    }
    
    public void calc_from_triangle_margin(Vector3f local_V1, Vector3f local_V2, Vector3f local_V3, float margin)
    {
      calc_from_triangle(local_V1, local_V2, local_V3);
      this.min.field_615 -= margin;
      this.min.field_616 -= margin;
      this.min.field_617 -= margin;
      this.max.field_615 += margin;
      this.max.field_616 += margin;
      this.max.field_617 += margin;
    }
    
    public void appy_transform(Transform arg1)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        Vector3f center = localStack.get$javax$vecmath$Vector3f();
        center.add(this.max, this.min);
        center.scale(0.5F);
        Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
        extends_.sub(this.max, center);
        trans.transform(center);
        Vector3f textends = localStack.get$javax$vecmath$Vector3f();
        trans.basis.getRow(0, tmp);
        tmp.absolute();
        textends.field_615 = extends_.dot(tmp);
        trans.basis.getRow(1, tmp);
        tmp.absolute();
        textends.field_616 = extends_.dot(tmp);
        trans.basis.getRow(2, tmp);
        tmp.absolute();
        textends.field_617 = extends_.dot(tmp);
        this.min.sub(center, textends);
        this.max.add(center, textends);
        return;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public void appy_transform_trans_cache(BoxCollision.BoxBoxTransformCache arg1)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        Vector3f center = localStack.get$javax$vecmath$Vector3f();
        center.add(this.max, this.min);
        center.scale(0.5F);
        Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
        extends_.sub(this.max, center);
        trans.transform(center, center);
        Vector3f textends = localStack.get$javax$vecmath$Vector3f();
        trans.R1to0.getRow(0, tmp);
        tmp.absolute();
        textends.field_615 = extends_.dot(tmp);
        trans.R1to0.getRow(1, tmp);
        tmp.absolute();
        textends.field_616 = extends_.dot(tmp);
        trans.R1to0.getRow(2, tmp);
        tmp.absolute();
        textends.field_617 = extends_.dot(tmp);
        this.min.sub(center, textends);
        this.max.add(center, textends);
        return;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public void merge(AABB box)
    {
      this.min.field_615 = Math.min(this.min.field_615, box.min.field_615);
      this.min.field_616 = Math.min(this.min.field_616, box.min.field_616);
      this.min.field_617 = Math.min(this.min.field_617, box.min.field_617);
      this.max.field_615 = Math.max(this.max.field_615, box.max.field_615);
      this.max.field_616 = Math.max(this.max.field_616, box.max.field_616);
      this.max.field_617 = Math.max(this.max.field_617, box.max.field_617);
    }
    
    public void merge_point(Vector3f point)
    {
      this.min.field_615 = Math.min(this.min.field_615, point.field_615);
      this.min.field_616 = Math.min(this.min.field_616, point.field_616);
      this.min.field_617 = Math.min(this.min.field_617, point.field_617);
      this.max.field_615 = Math.max(this.max.field_615, point.field_615);
      this.max.field_616 = Math.max(this.max.field_616, point.field_616);
      this.max.field_617 = Math.max(this.max.field_617, point.field_617);
    }
    
    public void get_center_extend(Vector3f center, Vector3f extend)
    {
      center.add(this.max, this.min);
      center.scale(0.5F);
      extend.sub(this.max, center);
    }
    
    public void find_intersection(AABB other, AABB intersection)
    {
      intersection.min.field_615 = Math.max(other.min.field_615, this.min.field_615);
      intersection.min.field_616 = Math.max(other.min.field_616, this.min.field_616);
      intersection.min.field_617 = Math.max(other.min.field_617, this.min.field_617);
      intersection.max.field_615 = Math.min(other.max.field_615, this.max.field_615);
      intersection.max.field_616 = Math.min(other.max.field_616, this.max.field_616);
      intersection.max.field_617 = Math.min(other.max.field_617, this.max.field_617);
    }
    
    public boolean has_collision(AABB other)
    {
      return (this.min.field_615 <= other.max.field_615) && (this.max.field_615 >= other.min.field_615) && (this.min.field_616 <= other.max.field_616) && (this.max.field_616 >= other.min.field_616) && (this.min.field_617 <= other.max.field_617) && (this.max.field_617 >= other.min.field_617);
    }
    
    public boolean collide_ray(Vector3f arg1, Vector3f arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f extents = localStack.get$javax$vecmath$Vector3f();
        Vector3f center = localStack.get$javax$vecmath$Vector3f();
        get_center_extend(center, extents);
        float local_Dx = vorigin.field_615 - center.field_615;
        if ((BoxCollision.BT_GREATER(local_Dx, extents.field_615)) && (local_Dx * vdir.field_615 >= 0.0F)) {
          return false;
        }
        float local_Dy = vorigin.field_616 - center.field_616;
        if ((BoxCollision.BT_GREATER(local_Dy, extents.field_616)) && (local_Dy * vdir.field_616 >= 0.0F)) {
          return false;
        }
        float local_Dz = vorigin.field_617 - center.field_617;
        if ((BoxCollision.BT_GREATER(local_Dz, extents.field_617)) && (local_Dz * vdir.field_617 >= 0.0F)) {
          return false;
        }
        float local_f = vdir.field_616 * local_Dz - vdir.field_617 * local_Dy;
        if (Math.abs(local_f) > extents.field_616 * Math.abs(vdir.field_617) + extents.field_617 * Math.abs(vdir.field_616)) {
          return false;
        }
        local_f = vdir.field_617 * local_Dx - vdir.field_615 * local_Dz;
        if (Math.abs(local_f) > extents.field_615 * Math.abs(vdir.field_617) + extents.field_617 * Math.abs(vdir.field_615)) {
          return false;
        }
        local_f = vdir.field_615 * local_Dy - vdir.field_616 * local_Dx;
        return Math.abs(local_f) <= extents.field_615 * Math.abs(vdir.field_616) + extents.field_616 * Math.abs(vdir.field_615);
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public void projection_interval(Vector3f arg1, float[] arg2, float[] arg3)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        Vector3f center = localStack.get$javax$vecmath$Vector3f();
        Vector3f extend = localStack.get$javax$vecmath$Vector3f();
        get_center_extend(center, extend);
        float _fOrigin = direction.dot(center);
        tmp.absolute(direction);
        float _fMaximumExtent = extend.dot(tmp);
        vmin[0] = (_fOrigin - _fMaximumExtent);
        vmax[0] = (_fOrigin + _fMaximumExtent);
        return;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public PlaneIntersectionType plane_classify(Vector4f arg1)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        float[] _fmin = new float[1];
        float[] _fmax = new float[1];
        tmp.set(plane.field_596, plane.field_597, plane.field_598);
        projection_interval(tmp, _fmin, _fmax);
        if (plane.field_599 > _fmax[0] + 1.0E-006F) {
          return PlaneIntersectionType.BACK_PLANE;
        }
        if (plane.field_599 + 1.0E-006F >= _fmin[0]) {
          return PlaneIntersectionType.COLLIDE_PLANE;
        }
        return PlaneIntersectionType.FRONT_PLANE;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean overlapping_trans_conservative(AABB arg1, Transform arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
        AABB tbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
        tbox.appy_transform(trans1_to_0);
        return has_collision(tbox);
      }
      finally
      {
        localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      }
    }
    
    public boolean overlapping_trans_conservative2(AABB arg1, BoxCollision.BoxBoxTransformCache arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
        AABB tbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
        tbox.appy_transform_trans_cache(trans1_to_0);
        return has_collision(tbox);
      }
      finally
      {
        localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      }
    }
    
    public boolean overlapping_trans_cache(AABB arg1, BoxCollision.BoxBoxTransformCache arg2, boolean arg3)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        Vector3f local_ea = localStack.get$javax$vecmath$Vector3f();
        Vector3f local_eb = localStack.get$javax$vecmath$Vector3f();
        Vector3f local_ca = localStack.get$javax$vecmath$Vector3f();
        Vector3f local_cb = localStack.get$javax$vecmath$Vector3f();
        get_center_extend(local_ca, local_ea);
        box.get_center_extend(local_cb, local_eb);
        Vector3f local_T = localStack.get$javax$vecmath$Vector3f();
        for (int local_i = 0; local_i < 3; local_i++)
        {
          transcache.R1to0.getRow(local_i, tmp);
          VectorUtil.setCoord(local_T, local_i, tmp.dot(local_cb) + VectorUtil.getCoord(transcache.T1to0, local_i) - VectorUtil.getCoord(local_ca, local_i));
          transcache.field_2080.getRow(local_i, tmp);
          float local_t = tmp.dot(local_eb) + VectorUtil.getCoord(local_ea, local_i);
          if (BoxCollision.BT_GREATER(VectorUtil.getCoord(local_T, local_i), local_t)) {
            return false;
          }
        }
        for (int local_i = 0; local_i < 3; local_i++)
        {
          float local_t = BoxCollision.bt_mat3_dot_col(transcache.R1to0, local_T, local_i);
          float local_t2 = BoxCollision.bt_mat3_dot_col(transcache.field_2080, local_ea, local_i) + VectorUtil.getCoord(local_eb, local_i);
          if (BoxCollision.BT_GREATER(local_t, local_t2)) {
            return false;
          }
        }
        if (fulltest) {
          for (int local_i1 = 0; local_i1 < 3; local_i1++)
          {
            int local_i = (local_i1 + 1) % 3;
            int local_n = (local_i1 + 2) % 3;
            int local_o = local_i1 == 0 ? 1 : 0;
            int local_p = local_i1 == 2 ? 1 : 2;
            for (int local_j = 0; local_j < 3; local_j++)
            {
              int local_q = local_j == 2 ? 1 : 2;
              int local_r = local_j == 0 ? 1 : 0;
              float local_t = VectorUtil.getCoord(local_T, local_n) * transcache.R1to0.getElement(local_i, local_j) - VectorUtil.getCoord(local_T, local_i) * transcache.R1to0.getElement(local_n, local_j);
              float local_t2 = VectorUtil.getCoord(local_ea, local_o) * transcache.field_2080.getElement(local_p, local_j) + VectorUtil.getCoord(local_ea, local_p) * transcache.field_2080.getElement(local_o, local_j) + VectorUtil.getCoord(local_eb, local_r) * transcache.field_2080.getElement(local_i1, local_q) + VectorUtil.getCoord(local_eb, local_q) * transcache.field_2080.getElement(local_i1, local_r);
              if (BoxCollision.BT_GREATER(local_t, local_t2)) {
                return false;
              }
            }
          }
        }
        return true;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean collide_plane(Vector4f plane)
    {
      PlaneIntersectionType classify = plane_classify(plane);
      return classify == PlaneIntersectionType.COLLIDE_PLANE;
    }
    
    public boolean collide_triangle_exact(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector4f arg4)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        if (!collide_plane(triangle_plane)) {
          return false;
        }
        Vector3f center = localStack.get$javax$vecmath$Vector3f();
        Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
        get_center_extend(center, extends_);
        Vector3f local_v1 = localStack.get$javax$vecmath$Vector3f();
        local_v1.sub(local_p1, center);
        Vector3f local_v2 = localStack.get$javax$vecmath$Vector3f();
        local_v2.sub(local_p2, center);
        Vector3f local_v3 = localStack.get$javax$vecmath$Vector3f();
        local_v3.sub(local_p3, center);
        Vector3f diff = localStack.get$javax$vecmath$Vector3f();
        diff.sub(local_v2, local_v1);
        Vector3f abs_diff = localStack.get$javax$vecmath$Vector3f();
        abs_diff.absolute(diff);
        BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, local_v1, local_v3, extends_);
        BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, local_v1, local_v3, extends_);
        BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, local_v1, local_v3, extends_);
        diff.sub(local_v3, local_v2);
        abs_diff.absolute(diff);
        BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, local_v2, local_v1, extends_);
        BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, local_v2, local_v1, extends_);
        BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, local_v2, local_v1, extends_);
        diff.sub(local_v1, local_v3);
        abs_diff.absolute(diff);
        BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, local_v3, local_v2, extends_);
        BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, local_v3, local_v2, extends_);
        BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, local_v3, local_v2, extends_);
        return true;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
  }
  
  public static class BoxBoxTransformCache
  {
    public final Vector3f T1to0 = new Vector3f();
    public final Matrix3f R1to0 = new Matrix3f();
    public final Matrix3f field_2080 = new Matrix3f();
    
    public void set(BoxBoxTransformCache cache)
    {
      throw new UnsupportedOperationException();
    }
    
    public void calc_absolute_matrix()
    {
      for (int local_i = 0; local_i < 3; local_i++) {
        for (int local_j = 0; local_j < 3; local_j++) {
          this.field_2080.setElement(local_i, local_j, 1.0E-006F + Math.abs(this.R1to0.getElement(local_i, local_j)));
        }
      }
    }
    
    public void calc_from_homogenic(Transform arg1, Transform arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$com$bulletphysics$linearmath$Transform();
        Transform temp_trans = localStack.get$com$bulletphysics$linearmath$Transform();
        temp_trans.inverse(trans0);
        temp_trans.mul(trans1);
        this.T1to0.set(temp_trans.origin);
        this.R1to0.set(temp_trans.basis);
        calc_absolute_matrix();
        return;
      }
      finally
      {
        localStack.pop$com$bulletphysics$linearmath$Transform();
      }
    }
    
    public void calc_from_full_invert(Transform arg1, Transform arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        this.R1to0.invert(trans0.basis);
        this.T1to0.negate(trans0.origin);
        this.R1to0.transform(this.T1to0);
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.set(trans1.origin);
        this.R1to0.transform(tmp);
        this.T1to0.add(tmp);
        this.R1to0.mul(trans1.basis);
        calc_absolute_matrix();
        return;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public Vector3f transform(Vector3f arg1, Vector3f arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        if (point == out) {
          point = localStack.get$javax$vecmath$Vector3f(point);
        }
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        this.R1to0.getRow(0, tmp);
        out.field_615 = (tmp.dot(point) + this.T1to0.field_615);
        this.R1to0.getRow(1, tmp);
        out.field_616 = (tmp.dot(point) + this.T1to0.field_616);
        this.R1to0.getRow(2, tmp);
        out.field_617 = (tmp.dot(point) + this.T1to0.field_617);
        return out;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.BoxCollision
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */