/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import javax.vecmath.Matrix3f;
/*   5:    */import javax.vecmath.Matrix4f;
/*   6:    */import javax.vecmath.Quat4f;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  46:    */public class Transform
/*  47:    */{
/*  48: 48 */  public final Matrix3f basis = new Matrix3f();
/*  49:    */  
/*  51: 51 */  public final Vector3f origin = new Vector3f();
/*  52:    */  
/*  53:    */  public Transform() {}
/*  54:    */  
/*  55:    */  public Transform(Matrix3f mat)
/*  56:    */  {
/*  57: 57 */    this.basis.set(mat);
/*  58:    */  }
/*  59:    */  
/*  60:    */  public Transform(Matrix4f mat) {
/*  61: 61 */    set(mat);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public Transform(Transform tr) {
/*  65: 65 */    set(tr);
/*  66:    */  }
/*  67:    */  
/*  68:    */  public void set(Transform tr) {
/*  69: 69 */    this.basis.set(tr.basis);
/*  70: 70 */    this.origin.set(tr.origin);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void set(Matrix3f mat) {
/*  74: 74 */    this.basis.set(mat);
/*  75: 75 */    this.origin.set(0.0F, 0.0F, 0.0F);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void set(Matrix4f mat) {
/*  79: 79 */    mat.getRotationScale(this.basis);
/*  80: 80 */    this.origin.set(mat.m03, mat.m13, mat.m23);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void transform(Vector3f v) {
/*  84: 84 */    this.basis.transform(v);
/*  85: 85 */    v.add(this.origin);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void setIdentity() {
/*  89: 89 */    this.basis.setIdentity();
/*  90: 90 */    this.origin.set(0.0F, 0.0F, 0.0F);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void inverse() {
/*  94: 94 */    this.basis.transpose();
/*  95: 95 */    this.origin.scale(-1.0F);
/*  96: 96 */    this.basis.transform(this.origin);
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void inverse(Transform tr) {
/* 100:100 */    set(tr);
/* 101:101 */    inverse();
/* 102:    */  }
/* 103:    */  
/* 104:    */  public void mul(Transform arg1) {
/* 105:105 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f vec = localStack.get$javax$vecmath$Vector3f(tr.origin);
/* 106:106 */      transform(vec);
/* 107:    */      
/* 108:108 */      this.basis.mul(tr.basis);
/* 109:109 */      this.origin.set(vec);
/* 110:110 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 114:114 */  public void mul(Transform arg1, Transform arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f vec = localStack.get$javax$vecmath$Vector3f(tr2.origin);
/* 115:115 */      tr1.transform(vec);
/* 116:    */      
/* 117:117 */      this.basis.mul(tr1.basis, tr2.basis);
/* 118:118 */      this.origin.set(vec);
/* 119:119 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 120:    */    } }
/* 121:    */  
/* 122:122 */  public void invXform(Vector3f arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Matrix3f();out.sub(inVec, this.origin);
/* 123:    */      
/* 124:124 */      Matrix3f mat = localStack.get$javax$vecmath$Matrix3f(this.basis);
/* 125:125 */      mat.transpose();
/* 126:126 */      mat.transform(out);
/* 127:127 */    } finally { localStack.pop$javax$vecmath$Matrix3f();
/* 128:    */    } }
/* 129:    */  
/* 130:130 */  public Quat4f getRotation(Quat4f out) { MatrixUtil.getRotation(this.basis, out);
/* 131:131 */    return out;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public void setRotation(Quat4f q) {
/* 135:135 */    MatrixUtil.setRotation(this.basis, q);
/* 136:    */  }
/* 137:    */  
/* 138:    */  public void setFromOpenGLMatrix(float[] m) {
/* 139:139 */    MatrixUtil.setFromOpenGLSubMatrix(this.basis, m);
/* 140:140 */    this.origin.set(m[12], m[13], m[14]);
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void getOpenGLMatrix(float[] m) {
/* 144:144 */    MatrixUtil.getOpenGLSubMatrix(this.basis, m);
/* 145:145 */    m[12] = this.origin.x;
/* 146:146 */    m[13] = this.origin.y;
/* 147:147 */    m[14] = this.origin.z;
/* 148:148 */    m[15] = 1.0F;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public Matrix4f getMatrix(Matrix4f out) {
/* 152:152 */    out.set(this.basis);
/* 153:153 */    out.m03 = this.origin.x;
/* 154:154 */    out.m13 = this.origin.y;
/* 155:155 */    out.m23 = this.origin.z;
/* 156:156 */    return out;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public boolean equals(Object obj)
/* 160:    */  {
/* 161:161 */    if ((obj == null) || (!(obj instanceof Transform))) return false;
/* 162:162 */    Transform tr = (Transform)obj;
/* 163:163 */    return (this.basis.equals(tr.basis)) && (this.origin.equals(tr.origin));
/* 164:    */  }
/* 165:    */  
/* 166:    */  public int hashCode()
/* 167:    */  {
/* 168:168 */    int hash = 3;
/* 169:169 */    hash = 41 * hash + this.basis.hashCode();
/* 170:170 */    hash = 41 * hash + this.origin.hashCode();
/* 171:171 */    return hash;
/* 172:    */  }
/* 173:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.Transform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */