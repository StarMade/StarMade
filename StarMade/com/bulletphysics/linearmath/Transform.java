package com.bulletphysics.linearmath;

import com.bulletphysics..Stack;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class Transform
{
  public final Matrix3f basis = new Matrix3f();
  public final Vector3f origin = new Vector3f();
  
  public Transform() {}
  
  public Transform(Matrix3f mat)
  {
    this.basis.set(mat);
  }
  
  public Transform(Matrix4f mat)
  {
    set(mat);
  }
  
  public Transform(Transform local_tr)
  {
    set(local_tr);
  }
  
  public void set(Transform local_tr)
  {
    this.basis.set(local_tr.basis);
    this.origin.set(local_tr.origin);
  }
  
  public void set(Matrix3f mat)
  {
    this.basis.set(mat);
    this.origin.set(0.0F, 0.0F, 0.0F);
  }
  
  public void set(Matrix4f mat)
  {
    mat.getRotationScale(this.basis);
    this.origin.set(mat.m03, mat.m13, mat.m23);
  }
  
  public void transform(Vector3f local_v)
  {
    this.basis.transform(local_v);
    local_v.add(this.origin);
  }
  
  public void setIdentity()
  {
    this.basis.setIdentity();
    this.origin.set(0.0F, 0.0F, 0.0F);
  }
  
  public void inverse()
  {
    this.basis.transpose();
    this.origin.scale(-1.0F);
    this.basis.transform(this.origin);
  }
  
  public void inverse(Transform local_tr)
  {
    set(local_tr);
    inverse();
  }
  
  public void mul(Transform arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f vec = localStack.get$javax$vecmath$Vector3f(local_tr.origin);
      transform(vec);
      this.basis.mul(local_tr.basis);
      this.origin.set(vec);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void mul(Transform arg1, Transform arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f vec = localStack.get$javax$vecmath$Vector3f(tr2.origin);
      tr1.transform(vec);
      this.basis.mul(tr1.basis, tr2.basis);
      this.origin.set(vec);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void invXform(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Matrix3f();
      out.sub(inVec, this.origin);
      Matrix3f mat = localStack.get$javax$vecmath$Matrix3f(this.basis);
      mat.transpose();
      mat.transform(out);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public Quat4f getRotation(Quat4f out)
  {
    MatrixUtil.getRotation(this.basis, out);
    return out;
  }
  
  public void setRotation(Quat4f local_q)
  {
    MatrixUtil.setRotation(this.basis, local_q);
  }
  
  public void setFromOpenGLMatrix(float[] local_m)
  {
    MatrixUtil.setFromOpenGLSubMatrix(this.basis, local_m);
    this.origin.set(local_m[12], local_m[13], local_m[14]);
  }
  
  public void getOpenGLMatrix(float[] local_m)
  {
    MatrixUtil.getOpenGLSubMatrix(this.basis, local_m);
    local_m[12] = this.origin.field_615;
    local_m[13] = this.origin.field_616;
    local_m[14] = this.origin.field_617;
    local_m[15] = 1.0F;
  }
  
  public Matrix4f getMatrix(Matrix4f out)
  {
    out.set(this.basis);
    out.m03 = this.origin.field_615;
    out.m13 = this.origin.field_616;
    out.m23 = this.origin.field_617;
    return out;
  }
  
  public boolean equals(Object obj)
  {
    if ((obj == null) || (!(obj instanceof Transform))) {
      return false;
    }
    Transform local_tr = (Transform)obj;
    return (this.basis.equals(local_tr.basis)) && (this.origin.equals(local_tr.origin));
  }
  
  public int hashCode()
  {
    int hash = 3;
    hash = 41 * hash + this.basis.hashCode();
    hash = 41 * hash + this.origin.hashCode();
    return hash;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.Transform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */