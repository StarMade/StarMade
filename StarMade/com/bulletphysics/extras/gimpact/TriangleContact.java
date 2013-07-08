/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ArrayPool;
/*   4:    */import com.bulletphysics.util.ObjectArrayList;
/*   5:    */import javax.vecmath.Tuple3f;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import javax.vecmath.Vector4f;
/*   8:    */
/*  40:    */public class TriangleContact
/*  41:    */{
/*  42: 42 */  private final ArrayPool<int[]> intArrays = ArrayPool.get(Integer.TYPE);
/*  43:    */  
/*  44:    */  public static final int MAX_TRI_CLIPPING = 16;
/*  45:    */  
/*  46:    */  public float penetration_depth;
/*  47:    */  public int point_count;
/*  48: 48 */  public final Vector4f separating_normal = new Vector4f();
/*  49: 49 */  public Vector3f[] points = new Vector3f[16];
/*  50:    */  
/*  51:    */  public TriangleContact() {
/*  52: 52 */    for (int i = 0; i < this.points.length; i++) {
/*  53: 53 */      this.points[i] = new Vector3f();
/*  54:    */    }
/*  55:    */  }
/*  56:    */  
/*  57:    */  public TriangleContact(TriangleContact other) {
/*  58: 58 */    copy_from(other);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void set(TriangleContact other) {
/*  62: 62 */    copy_from(other);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void copy_from(TriangleContact other) {
/*  66: 66 */    this.penetration_depth = other.penetration_depth;
/*  67: 67 */    this.separating_normal.set(other.separating_normal);
/*  68: 68 */    this.point_count = other.point_count;
/*  69: 69 */    int i = this.point_count;
/*  70: 70 */    while (i-- != 0) {
/*  71: 71 */      this.points[i].set(other.points[i]);
/*  72:    */    }
/*  73:    */  }
/*  74:    */  
/*  77:    */  public void merge_points(Vector4f plane, float margin, ObjectArrayList<Vector3f> points, int point_count)
/*  78:    */  {
/*  79: 79 */    this.point_count = 0;
/*  80: 80 */    this.penetration_depth = -1000.0F;
/*  81:    */    
/*  82: 82 */    int[] point_indices = (int[])this.intArrays.getFixed(16);
/*  83:    */    
/*  84: 84 */    for (int _k = 0; _k < point_count; _k++) {
/*  85: 85 */      float _dist = -ClipPolygon.distance_point_plane(plane, (Vector3f)points.getQuick(_k)) + margin;
/*  86:    */      
/*  87: 87 */      if (_dist >= 0.0F) {
/*  88: 88 */        if (_dist > this.penetration_depth) {
/*  89: 89 */          this.penetration_depth = _dist;
/*  90: 90 */          point_indices[0] = _k;
/*  91: 91 */          this.point_count = 1;
/*  92:    */        }
/*  93: 93 */        else if (_dist + 1.192093E-007F >= this.penetration_depth) {
/*  94: 94 */          point_indices[this.point_count] = _k;
/*  95: 95 */          this.point_count += 1;
/*  96:    */        }
/*  97:    */      }
/*  98:    */    }
/*  99:    */    
/* 100:100 */    for (int _k = 0; _k < this.point_count; _k++) {
/* 101:101 */      this.points[_k].set((Tuple3f)points.getQuick(point_indices[_k]));
/* 102:    */    }
/* 103:    */    
/* 104:104 */    this.intArrays.release(point_indices);
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TriangleContact
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */