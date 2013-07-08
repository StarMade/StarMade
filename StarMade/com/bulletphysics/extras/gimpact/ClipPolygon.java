/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.VectorUtil;
/*   4:    */import com.bulletphysics.util.ArrayPool;
/*   5:    */import com.bulletphysics.util.ObjectArrayList;
/*   6:    */import javax.vecmath.Tuple3f;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */import javax.vecmath.Vector4f;
/*   9:    */
/*  40:    */class ClipPolygon
/*  41:    */{
/*  42:    */  public static float distance_point_plane(Vector4f plane, Vector3f point)
/*  43:    */  {
/*  44: 44 */    return VectorUtil.dot3(point, plane) - plane.w;
/*  45:    */  }
/*  46:    */  
/*  49:    */  public static void vec_blend(Vector3f vr, Vector3f va, Vector3f vb, float blend_factor)
/*  50:    */  {
/*  51: 51 */    vr.scale(1.0F - blend_factor, va);
/*  52: 52 */    vr.scaleAdd(blend_factor, vb, vr);
/*  53:    */  }
/*  54:    */  
/*  57:    */  public static void plane_clip_polygon_collect(Vector3f point0, Vector3f point1, float dist0, float dist1, ObjectArrayList<Vector3f> clipped, int[] clipped_count)
/*  58:    */  {
/*  59: 59 */    boolean _prevclassif = dist0 > 1.192093E-007F;
/*  60: 60 */    boolean _classif = dist1 > 1.192093E-007F;
/*  61: 61 */    if (_classif != _prevclassif) {
/*  62: 62 */      float blendfactor = -dist0 / (dist1 - dist0);
/*  63: 63 */      vec_blend((Vector3f)clipped.getQuick(clipped_count[0]), point0, point1, blendfactor);
/*  64: 64 */      clipped_count[0] += 1;
/*  65:    */    }
/*  66: 66 */    if (!_classif) {
/*  67: 67 */      ((Vector3f)clipped.getQuick(clipped_count[0])).set(point1);
/*  68: 68 */      clipped_count[0] += 1;
/*  69:    */    }
/*  70:    */  }
/*  71:    */  
/*  76:    */  public static int plane_clip_polygon(Vector4f plane, ObjectArrayList<Vector3f> polygon_points, int polygon_point_count, ObjectArrayList<Vector3f> clipped)
/*  77:    */  {
/*  78: 78 */    ArrayPool<int[]> intArrays = ArrayPool.get(Integer.TYPE);
/*  79:    */    
/*  80: 80 */    int[] clipped_count = (int[])intArrays.getFixed(1);
/*  81: 81 */    clipped_count[0] = 0;
/*  82:    */    
/*  84: 84 */    float firstdist = distance_point_plane(plane, (Vector3f)polygon_points.getQuick(0));
/*  85: 85 */    if (firstdist <= 1.192093E-007F) {
/*  86: 86 */      ((Vector3f)clipped.getQuick(clipped_count[0])).set((Tuple3f)polygon_points.getQuick(0));
/*  87: 87 */      clipped_count[0] += 1;
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    float olddist = firstdist;
/*  91: 91 */    for (int i = 1; i < polygon_point_count; i++) {
/*  92: 92 */      float dist = distance_point_plane(plane, (Vector3f)polygon_points.getQuick(i));
/*  93:    */      
/*  94: 94 */      plane_clip_polygon_collect((Vector3f)polygon_points.getQuick(i - 1), (Vector3f)polygon_points.getQuick(i), olddist, dist, clipped, clipped_count);
/*  95:    */      
/* 102:102 */      olddist = dist;
/* 103:    */    }
/* 104:    */    
/* 107:107 */    plane_clip_polygon_collect((Vector3f)polygon_points.getQuick(polygon_point_count - 1), (Vector3f)polygon_points.getQuick(0), olddist, firstdist, clipped, clipped_count);
/* 108:    */    
/* 114:114 */    int ret = clipped_count[0];
/* 115:115 */    intArrays.release(clipped_count);
/* 116:116 */    return ret;
/* 117:    */  }
/* 118:    */  
/* 124:    */  public static int plane_clip_triangle(Vector4f plane, Vector3f point0, Vector3f point1, Vector3f point2, ObjectArrayList<Vector3f> clipped)
/* 125:    */  {
/* 126:126 */    ArrayPool<int[]> intArrays = ArrayPool.get(Integer.TYPE);
/* 127:    */    
/* 128:128 */    int[] clipped_count = (int[])intArrays.getFixed(1);
/* 129:129 */    clipped_count[0] = 0;
/* 130:    */    
/* 132:132 */    float firstdist = distance_point_plane(plane, point0);
/* 133:133 */    if (firstdist <= 1.192093E-007F) {
/* 134:134 */      ((Vector3f)clipped.getQuick(clipped_count[0])).set(point0);
/* 135:135 */      clipped_count[0] += 1;
/* 136:    */    }
/* 137:    */    
/* 139:139 */    float olddist = firstdist;
/* 140:140 */    float dist = distance_point_plane(plane, point1);
/* 141:    */    
/* 142:142 */    plane_clip_polygon_collect(point0, point1, olddist, dist, clipped, clipped_count);
/* 143:    */    
/* 149:149 */    olddist = dist;
/* 150:    */    
/* 153:153 */    dist = distance_point_plane(plane, point2);
/* 154:    */    
/* 155:155 */    plane_clip_polygon_collect(point1, point2, olddist, dist, clipped, clipped_count);
/* 156:    */    
/* 161:161 */    olddist = dist;
/* 162:    */    
/* 166:166 */    plane_clip_polygon_collect(point2, point0, olddist, firstdist, clipped, clipped_count);
/* 167:    */    
/* 173:173 */    int ret = clipped_count[0];
/* 174:174 */    intArrays.release(clipped_count);
/* 175:175 */    return ret;
/* 176:    */  }
/* 177:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.ClipPolygon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */