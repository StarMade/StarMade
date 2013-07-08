/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.shapes.TriangleShape;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import javax.vecmath.Vector4f;
/*   8:    */
/*  41:    */public class TriangleShapeEx
/*  42:    */  extends TriangleShape
/*  43:    */{
/*  44:    */  public TriangleShapeEx() {}
/*  45:    */  
/*  46:    */  public TriangleShapeEx(Vector3f p0, Vector3f p1, Vector3f p2)
/*  47:    */  {
/*  48: 48 */    super(p0, p1, p2);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*  52:    */  {
/*  53: 53 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tv0 = localStack.get$javax$vecmath$Vector3f(this.vertices1[0]);
/*  54: 54 */      t.transform(tv0);
/*  55: 55 */      Vector3f tv1 = localStack.get$javax$vecmath$Vector3f(this.vertices1[1]);
/*  56: 56 */      t.transform(tv1);
/*  57: 57 */      Vector3f tv2 = localStack.get$javax$vecmath$Vector3f(this.vertices1[2]);
/*  58: 58 */      t.transform(tv2);
/*  59:    */      
/*  60: 60 */      BoxCollision.AABB trianglebox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  61: 61 */      trianglebox.init(tv0, tv1, tv2, this.collisionMargin);
/*  62:    */      
/*  63: 63 */      aabbMin.set(trianglebox.min);
/*  64: 64 */      aabbMax.set(trianglebox.max);
/*  65: 65 */    } finally { .Stack tmp126_124 = localStack;tmp126_124.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();tmp126_124.pop$javax$vecmath$Vector3f();
/*  66:    */    } }
/*  67:    */  
/*  68: 68 */  public void applyTransform(Transform t) { t.transform(this.vertices1[0]);
/*  69: 69 */    t.transform(this.vertices1[1]);
/*  70: 70 */    t.transform(this.vertices1[2]);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void buildTriPlane(Vector4f arg1) {
/*  74: 74 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  75: 75 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  76:    */      
/*  77: 77 */      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  78: 78 */      tmp1.sub(this.vertices1[1], this.vertices1[0]);
/*  79: 79 */      tmp2.sub(this.vertices1[2], this.vertices1[0]);
/*  80: 80 */      normal.cross(tmp1, tmp2);
/*  81: 81 */      normal.normalize();
/*  82:    */      
/*  83: 83 */      plane.set(normal.x, normal.y, normal.z, this.vertices1[0].dot(normal));
/*  84: 84 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  85:    */    } }
/*  86:    */  
/*  87: 87 */  public boolean overlap_test_conservative(TriangleShapeEx arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector4f();float total_margin = getMargin() + other.getMargin();
/*  88:    */      
/*  89: 89 */      Vector4f plane0 = localStack.get$javax$vecmath$Vector4f();
/*  90: 90 */      buildTriPlane(plane0);
/*  91: 91 */      Vector4f plane1 = localStack.get$javax$vecmath$Vector4f();
/*  92: 92 */      other.buildTriPlane(plane1);
/*  93:    */      
/*  95: 95 */      float dis0 = ClipPolygon.distance_point_plane(plane0, other.vertices1[0]) - total_margin;
/*  96:    */      
/*  97: 97 */      float dis1 = ClipPolygon.distance_point_plane(plane0, other.vertices1[1]) - total_margin;
/*  98:    */      
/*  99: 99 */      float dis2 = ClipPolygon.distance_point_plane(plane0, other.vertices1[2]) - total_margin;
/* 100:    */      
/* 101:101 */      if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/* 102:102 */        return false;
/* 103:    */      }
/* 104:104 */      dis0 = ClipPolygon.distance_point_plane(plane1, this.vertices1[0]) - total_margin;
/* 105:    */      
/* 106:106 */      dis1 = ClipPolygon.distance_point_plane(plane1, this.vertices1[1]) - total_margin;
/* 107:    */      
/* 108:108 */      dis2 = ClipPolygon.distance_point_plane(plane1, this.vertices1[2]) - total_margin;
/* 109:    */      
/* 110:110 */      if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/* 111:111 */        return false;
/* 112:    */      }
/* 113:113 */      return true; } finally { localStack.pop$javax$vecmath$Vector4f();
/* 114:    */    }
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TriangleShapeEx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */