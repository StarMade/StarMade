/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.Transform;
/*   5:    */import com.bulletphysics.util.ObjectArrayList;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import javax.vecmath.Vector4f;
/*   8:    */
/*  40:    */public class PrimitiveTriangle
/*  41:    */{
/*  42: 42 */  private final ObjectArrayList<Vector3f> tmpVecList1 = new ObjectArrayList(16);
/*  43: 43 */  private final ObjectArrayList<Vector3f> tmpVecList2 = new ObjectArrayList(16);
/*  44: 44 */  private final ObjectArrayList<Vector3f> tmpVecList3 = new ObjectArrayList(16);
/*  45:    */  public final Vector3f[] vertices;
/*  46:    */  
/*  47: 47 */  public PrimitiveTriangle() { for (int i = 0; i < 16; i++) {
/*  48: 48 */      this.tmpVecList1.add(new Vector3f());
/*  49: 49 */      this.tmpVecList2.add(new Vector3f());
/*  50: 50 */      this.tmpVecList3.add(new Vector3f());
/*  51:    */    }
/*  52:    */    
/*  54: 54 */    this.vertices = new Vector3f[3];
/*  55: 55 */    this.plane = new Vector4f();
/*  56: 56 */    this.margin = 0.01F;
/*  57:    */    
/*  59: 59 */    for (int i = 0; i < this.vertices.length; i++) {
/*  60: 60 */      this.vertices[i] = new Vector3f();
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void set(PrimitiveTriangle tri) {
/*  65: 65 */    throw new UnsupportedOperationException();
/*  66:    */  }
/*  67:    */  
/*  68:    */  public void buildTriPlane() {
/*  69: 69 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  70: 70 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  71:    */      
/*  72: 72 */      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  73: 73 */      tmp1.sub(this.vertices[1], this.vertices[0]);
/*  74: 74 */      tmp2.sub(this.vertices[2], this.vertices[0]);
/*  75: 75 */      normal.cross(tmp1, tmp2);
/*  76: 76 */      normal.normalize();
/*  77:    */      
/*  78: 78 */      this.plane.set(normal.x, normal.y, normal.z, this.vertices[0].dot(normal));
/*  79: 79 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  83:    */  public boolean overlap_test_conservative(PrimitiveTriangle other)
/*  84:    */  {
/*  85: 85 */    float total_margin = this.margin + other.margin;
/*  86:    */    
/*  87: 87 */    float dis0 = ClipPolygon.distance_point_plane(this.plane, other.vertices[0]) - total_margin;
/*  88:    */    
/*  89: 89 */    float dis1 = ClipPolygon.distance_point_plane(this.plane, other.vertices[1]) - total_margin;
/*  90:    */    
/*  91: 91 */    float dis2 = ClipPolygon.distance_point_plane(this.plane, other.vertices[2]) - total_margin;
/*  92:    */    
/*  93: 93 */    if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/*  94: 94 */      return false;
/*  95:    */    }
/*  96:    */    
/*  97: 97 */    dis0 = ClipPolygon.distance_point_plane(other.plane, this.vertices[0]) - total_margin;
/*  98:    */    
/*  99: 99 */    dis1 = ClipPolygon.distance_point_plane(other.plane, this.vertices[1]) - total_margin;
/* 100:    */    
/* 101:101 */    dis2 = ClipPolygon.distance_point_plane(other.plane, this.vertices[2]) - total_margin;
/* 102:    */    
/* 103:103 */    if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/* 104:104 */      return false;
/* 105:    */    }
/* 106:106 */    return true;
/* 107:    */  }
/* 108:    */  
/* 112:    */  public void get_edge_plane(int arg1, Vector4f arg2)
/* 113:    */  {
/* 114:114 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f e0 = this.vertices[edge_index];
/* 115:115 */      Vector3f e1 = this.vertices[((edge_index + 1) % 3)];
/* 116:    */      
/* 117:117 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 118:118 */      tmp.set(this.plane.x, this.plane.y, this.plane.z);
/* 119:    */      
/* 120:120 */      GeometryOperations.edge_plane(e0, e1, tmp, plane);
/* 121:121 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 122:    */    } }
/* 123:    */  
/* 124:124 */  public void applyTransform(Transform t) { t.transform(this.vertices[0]);
/* 125:125 */    t.transform(this.vertices[1]);
/* 126:126 */    t.transform(this.vertices[2]);
/* 127:    */  }
/* 128:    */  
/* 131:    */  public final Vector4f plane;
/* 132:    */  
/* 133:    */  public float margin;
/* 134:    */  
/* 135:    */  public int clip_triangle(PrimitiveTriangle arg1, ObjectArrayList<Vector3f> arg2)
/* 136:    */  {
/* 137:137 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector4f();ObjectArrayList<Vector3f> temp_points = this.tmpVecList1;
/* 138:    */      
/* 139:139 */      Vector4f edgeplane = localStack.get$javax$vecmath$Vector4f();
/* 140:    */      
/* 141:141 */      get_edge_plane(0, edgeplane);
/* 142:    */      
/* 143:143 */      int clipped_count = ClipPolygon.plane_clip_triangle(edgeplane, other.vertices[0], other.vertices[1], other.vertices[2], temp_points);
/* 144:    */      
/* 145:145 */      if (clipped_count == 0) {
/* 146:146 */        return 0;
/* 147:    */      }
/* 148:148 */      ObjectArrayList<Vector3f> temp_points1 = this.tmpVecList2;
/* 149:    */      
/* 151:151 */      get_edge_plane(1, edgeplane);
/* 152:    */      
/* 153:153 */      clipped_count = ClipPolygon.plane_clip_polygon(edgeplane, temp_points, clipped_count, temp_points1);
/* 154:    */      
/* 155:155 */      if (clipped_count == 0) {
/* 156:156 */        return 0;
/* 157:    */      }
/* 158:158 */      get_edge_plane(2, edgeplane);
/* 159:    */      
/* 160:160 */      return ClipPolygon.plane_clip_polygon(edgeplane, temp_points1, clipped_count, clipped_points);
/* 161:    */    } finally {
/* 162:162 */      localStack.pop$javax$vecmath$Vector4f();
/* 163:    */    }
/* 164:    */  }
/* 165:    */  
/* 168:    */  public boolean find_triangle_collision_clip_method(PrimitiveTriangle arg1, TriangleContact arg2)
/* 169:    */  {
/* 170:170 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$TriangleContact();float margin = this.margin + other.margin;
/* 171:    */      
/* 172:172 */      ObjectArrayList<Vector3f> clipped_points = this.tmpVecList3;
/* 173:    */      
/* 178:178 */      TriangleContact contacts1 = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
/* 179:    */      
/* 180:180 */      contacts1.separating_normal.set(this.plane);
/* 181:    */      
/* 182:182 */      int clipped_count = clip_triangle(other, clipped_points);
/* 183:    */      
/* 184:184 */      if (clipped_count == 0) {
/* 185:185 */        return false;
/* 186:    */      }
/* 187:    */      
/* 189:189 */      contacts1.merge_points(contacts1.separating_normal, margin, clipped_points, clipped_count);
/* 190:190 */      if (contacts1.point_count == 0) {
/* 191:191 */        return false;
/* 192:    */      }
/* 193:    */      
/* 194:194 */      contacts1.separating_normal.x *= -1.0F;
/* 195:195 */      contacts1.separating_normal.y *= -1.0F;
/* 196:196 */      contacts1.separating_normal.z *= -1.0F;
/* 197:    */      
/* 199:199 */      TriangleContact contacts2 = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
/* 200:200 */      contacts2.separating_normal.set(other.plane);
/* 201:    */      
/* 202:202 */      clipped_count = other.clip_triangle(this, clipped_points);
/* 203:    */      
/* 204:204 */      if (clipped_count == 0) {
/* 205:205 */        return false;
/* 206:    */      }
/* 207:    */      
/* 209:209 */      contacts2.merge_points(contacts2.separating_normal, margin, clipped_points, clipped_count);
/* 210:210 */      if (contacts2.point_count == 0) {
/* 211:211 */        return false;
/* 212:    */      }
/* 213:    */      
/* 215:215 */      if (contacts2.penetration_depth < contacts1.penetration_depth) {
/* 216:216 */        contacts.copy_from(contacts2);
/* 217:    */      }
/* 218:    */      else {
/* 219:219 */        contacts.copy_from(contacts1);
/* 220:    */      }
/* 221:221 */      return true; } finally { localStack.pop$com$bulletphysics$extras$gimpact$TriangleContact();
/* 222:    */    }
/* 223:    */  }
/* 224:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.PrimitiveTriangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */