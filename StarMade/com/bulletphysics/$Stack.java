package com.bulletphysics;

import com.bulletphysics.extras.gimpact.BoxCollision.AABB;
import com.bulletphysics.extras.gimpact.BoxCollision.BoxBoxTransformCache;
import com.bulletphysics.extras.gimpact.PrimitiveTriangle;
import com.bulletphysics.extras.gimpact.TriangleContact;
import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public final class $Stack
{
  public static final ThreadLocal threadLocal = new ThreadLocal()
  {
    protected Object initialValue()
    {
      return new .Stack();
    }
  };
  private ArrayList list$com$bulletphysics$linearmath$Transform = new ArrayList();
  private int[] stack$com$bulletphysics$linearmath$Transform = new int[16];
  private int count$com$bulletphysics$linearmath$Transform = 0;
  private int pos$com$bulletphysics$linearmath$Transform = 0;
  private ArrayList list$com$bulletphysics$extras$gimpact$BoxCollision$AABB = new ArrayList();
  private int[] stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB = new int[16];
  private int count$com$bulletphysics$extras$gimpact$BoxCollision$AABB = 0;
  private int pos$com$bulletphysics$extras$gimpact$BoxCollision$AABB = 0;
  private ArrayList list$com$bulletphysics$extras$gimpact$PrimitiveTriangle = new ArrayList();
  private int[] stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle = new int[16];
  private int count$com$bulletphysics$extras$gimpact$PrimitiveTriangle = 0;
  private int pos$com$bulletphysics$extras$gimpact$PrimitiveTriangle = 0;
  private ArrayList list$javax$vecmath$Vector3f = new ArrayList();
  private int[] stack$javax$vecmath$Vector3f = new int[16];
  private int count$javax$vecmath$Vector3f = 0;
  private int pos$javax$vecmath$Vector3f = 0;
  private ArrayList list$com$bulletphysics$extras$gimpact$TriangleContact = new ArrayList();
  private int[] stack$com$bulletphysics$extras$gimpact$TriangleContact = new int[16];
  private int count$com$bulletphysics$extras$gimpact$TriangleContact = 0;
  private int pos$com$bulletphysics$extras$gimpact$TriangleContact = 0;
  private ArrayList list$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache = new ArrayList();
  private int[] stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache = new int[16];
  private int count$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache = 0;
  private int pos$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache = 0;
  private ArrayList list$javax$vecmath$Quat4f = new ArrayList();
  private int[] stack$javax$vecmath$Quat4f = new int[16];
  private int count$javax$vecmath$Quat4f = 0;
  private int pos$javax$vecmath$Quat4f = 0;
  private ArrayList list$javax$vecmath$Vector4f = new ArrayList();
  private int[] stack$javax$vecmath$Vector4f = new int[16];
  private int count$javax$vecmath$Vector4f = 0;
  private int pos$javax$vecmath$Vector4f = 0;
  private ArrayList list$javax$vecmath$Matrix3f = new ArrayList();
  private int[] stack$javax$vecmath$Matrix3f = new int[16];
  private int count$javax$vecmath$Matrix3f = 0;
  private int pos$javax$vecmath$Matrix3f = 0;
  
  public static Stack get()
  {
    return (Stack)threadLocal.get();
  }
  
  public void push$com$bulletphysics$linearmath$Transform()
  {
    if (this.count$com$bulletphysics$linearmath$Transform == this.stack$com$bulletphysics$linearmath$Transform.length) {
      resize$com$bulletphysics$linearmath$Transform();
    }
    this.stack$com$bulletphysics$linearmath$Transform[(this.count$com$bulletphysics$linearmath$Transform++)] = this.pos$com$bulletphysics$linearmath$Transform;
  }
  
  private void resize$com$bulletphysics$linearmath$Transform()
  {
    int[] arrayOfInt = new int[this.stack$com$bulletphysics$linearmath$Transform.length << 1];
    System.arraycopy(this.stack$com$bulletphysics$linearmath$Transform, 0, arrayOfInt, 0, this.stack$com$bulletphysics$linearmath$Transform.length);
    this.stack$com$bulletphysics$linearmath$Transform = arrayOfInt;
  }
  
  public void pop$com$bulletphysics$linearmath$Transform()
  {
    this.pos$com$bulletphysics$linearmath$Transform = this.stack$com$bulletphysics$linearmath$Transform[(--this.count$com$bulletphysics$linearmath$Transform)];
  }
  
  public Transform get$com$bulletphysics$linearmath$Transform()
  {
    if (this.pos$com$bulletphysics$linearmath$Transform == this.list$com$bulletphysics$linearmath$Transform.size()) {
      this.list$com$bulletphysics$linearmath$Transform.add(new Transform());
    }
    return (Transform)this.list$com$bulletphysics$linearmath$Transform.get(this.pos$com$bulletphysics$linearmath$Transform++);
  }
  
  public Transform get$com$bulletphysics$linearmath$Transform(Transform paramTransform)
  {
    Transform localTransform = get$com$bulletphysics$linearmath$Transform();
    localTransform.set(paramTransform);
    return localTransform;
  }
  
  public void push$com$bulletphysics$extras$gimpact$BoxCollision$AABB()
  {
    if (this.count$com$bulletphysics$extras$gimpact$BoxCollision$AABB == this.stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB.length) {
      resize$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
    this.stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB[(this.count$com$bulletphysics$extras$gimpact$BoxCollision$AABB++)] = this.pos$com$bulletphysics$extras$gimpact$BoxCollision$AABB;
  }
  
  private void resize$com$bulletphysics$extras$gimpact$BoxCollision$AABB()
  {
    int[] arrayOfInt = new int[this.stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB.length << 1];
    System.arraycopy(this.stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB, 0, arrayOfInt, 0, this.stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB.length);
    this.stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB = arrayOfInt;
  }
  
  public void pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB()
  {
    this.pos$com$bulletphysics$extras$gimpact$BoxCollision$AABB = this.stack$com$bulletphysics$extras$gimpact$BoxCollision$AABB[(--this.count$com$bulletphysics$extras$gimpact$BoxCollision$AABB)];
  }
  
  public BoxCollision.AABB get$com$bulletphysics$extras$gimpact$BoxCollision$AABB()
  {
    if (this.pos$com$bulletphysics$extras$gimpact$BoxCollision$AABB == this.list$com$bulletphysics$extras$gimpact$BoxCollision$AABB.size()) {
      this.list$com$bulletphysics$extras$gimpact$BoxCollision$AABB.add(new BoxCollision.AABB());
    }
    return (BoxCollision.AABB)this.list$com$bulletphysics$extras$gimpact$BoxCollision$AABB.get(this.pos$com$bulletphysics$extras$gimpact$BoxCollision$AABB++);
  }
  
  public BoxCollision.AABB get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(BoxCollision.AABB paramAABB)
  {
    BoxCollision.AABB localAABB = get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    localAABB.set(paramAABB);
    return localAABB;
  }
  
  public void push$com$bulletphysics$extras$gimpact$PrimitiveTriangle()
  {
    if (this.count$com$bulletphysics$extras$gimpact$PrimitiveTriangle == this.stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle.length) {
      resize$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
    }
    this.stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle[(this.count$com$bulletphysics$extras$gimpact$PrimitiveTriangle++)] = this.pos$com$bulletphysics$extras$gimpact$PrimitiveTriangle;
  }
  
  private void resize$com$bulletphysics$extras$gimpact$PrimitiveTriangle()
  {
    int[] arrayOfInt = new int[this.stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle.length << 1];
    System.arraycopy(this.stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle, 0, arrayOfInt, 0, this.stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle.length);
    this.stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle = arrayOfInt;
  }
  
  public void pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle()
  {
    this.pos$com$bulletphysics$extras$gimpact$PrimitiveTriangle = this.stack$com$bulletphysics$extras$gimpact$PrimitiveTriangle[(--this.count$com$bulletphysics$extras$gimpact$PrimitiveTriangle)];
  }
  
  public PrimitiveTriangle get$com$bulletphysics$extras$gimpact$PrimitiveTriangle()
  {
    if (this.pos$com$bulletphysics$extras$gimpact$PrimitiveTriangle == this.list$com$bulletphysics$extras$gimpact$PrimitiveTriangle.size()) {
      this.list$com$bulletphysics$extras$gimpact$PrimitiveTriangle.add(new PrimitiveTriangle());
    }
    return (PrimitiveTriangle)this.list$com$bulletphysics$extras$gimpact$PrimitiveTriangle.get(this.pos$com$bulletphysics$extras$gimpact$PrimitiveTriangle++);
  }
  
  public PrimitiveTriangle get$com$bulletphysics$extras$gimpact$PrimitiveTriangle(PrimitiveTriangle paramPrimitiveTriangle)
  {
    PrimitiveTriangle localPrimitiveTriangle = get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
    localPrimitiveTriangle.set(paramPrimitiveTriangle);
    return localPrimitiveTriangle;
  }
  
  public void push$javax$vecmath$Vector3f()
  {
    if (this.count$javax$vecmath$Vector3f == this.stack$javax$vecmath$Vector3f.length) {
      resize$javax$vecmath$Vector3f();
    }
    this.stack$javax$vecmath$Vector3f[(this.count$javax$vecmath$Vector3f++)] = this.pos$javax$vecmath$Vector3f;
  }
  
  private void resize$javax$vecmath$Vector3f()
  {
    int[] arrayOfInt = new int[this.stack$javax$vecmath$Vector3f.length << 1];
    System.arraycopy(this.stack$javax$vecmath$Vector3f, 0, arrayOfInt, 0, this.stack$javax$vecmath$Vector3f.length);
    this.stack$javax$vecmath$Vector3f = arrayOfInt;
  }
  
  public void pop$javax$vecmath$Vector3f()
  {
    this.pos$javax$vecmath$Vector3f = this.stack$javax$vecmath$Vector3f[(--this.count$javax$vecmath$Vector3f)];
  }
  
  public Vector3f get$javax$vecmath$Vector3f()
  {
    if (this.pos$javax$vecmath$Vector3f == this.list$javax$vecmath$Vector3f.size()) {
      this.list$javax$vecmath$Vector3f.add(new Vector3f());
    }
    return (Vector3f)this.list$javax$vecmath$Vector3f.get(this.pos$javax$vecmath$Vector3f++);
  }
  
  public Vector3f get$javax$vecmath$Vector3f(Vector3f paramVector3f)
  {
    Vector3f localVector3f = get$javax$vecmath$Vector3f();
    localVector3f.set(paramVector3f);
    return localVector3f;
  }
  
  public void push$com$bulletphysics$extras$gimpact$TriangleContact()
  {
    if (this.count$com$bulletphysics$extras$gimpact$TriangleContact == this.stack$com$bulletphysics$extras$gimpact$TriangleContact.length) {
      resize$com$bulletphysics$extras$gimpact$TriangleContact();
    }
    this.stack$com$bulletphysics$extras$gimpact$TriangleContact[(this.count$com$bulletphysics$extras$gimpact$TriangleContact++)] = this.pos$com$bulletphysics$extras$gimpact$TriangleContact;
  }
  
  private void resize$com$bulletphysics$extras$gimpact$TriangleContact()
  {
    int[] arrayOfInt = new int[this.stack$com$bulletphysics$extras$gimpact$TriangleContact.length << 1];
    System.arraycopy(this.stack$com$bulletphysics$extras$gimpact$TriangleContact, 0, arrayOfInt, 0, this.stack$com$bulletphysics$extras$gimpact$TriangleContact.length);
    this.stack$com$bulletphysics$extras$gimpact$TriangleContact = arrayOfInt;
  }
  
  public void pop$com$bulletphysics$extras$gimpact$TriangleContact()
  {
    this.pos$com$bulletphysics$extras$gimpact$TriangleContact = this.stack$com$bulletphysics$extras$gimpact$TriangleContact[(--this.count$com$bulletphysics$extras$gimpact$TriangleContact)];
  }
  
  public TriangleContact get$com$bulletphysics$extras$gimpact$TriangleContact()
  {
    if (this.pos$com$bulletphysics$extras$gimpact$TriangleContact == this.list$com$bulletphysics$extras$gimpact$TriangleContact.size()) {
      this.list$com$bulletphysics$extras$gimpact$TriangleContact.add(new TriangleContact());
    }
    return (TriangleContact)this.list$com$bulletphysics$extras$gimpact$TriangleContact.get(this.pos$com$bulletphysics$extras$gimpact$TriangleContact++);
  }
  
  public TriangleContact get$com$bulletphysics$extras$gimpact$TriangleContact(TriangleContact paramTriangleContact)
  {
    TriangleContact localTriangleContact = get$com$bulletphysics$extras$gimpact$TriangleContact();
    localTriangleContact.set(paramTriangleContact);
    return localTriangleContact;
  }
  
  public void push$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache()
  {
    if (this.count$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache == this.stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache.length) {
      resize$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
    }
    this.stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache[(this.count$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache++)] = this.pos$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache;
  }
  
  private void resize$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache()
  {
    int[] arrayOfInt = new int[this.stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache.length << 1];
    System.arraycopy(this.stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache, 0, arrayOfInt, 0, this.stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache.length);
    this.stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache = arrayOfInt;
  }
  
  public void pop$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache()
  {
    this.pos$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache = this.stack$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache[(--this.count$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache)];
  }
  
  public BoxCollision.BoxBoxTransformCache get$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache()
  {
    if (this.pos$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache == this.list$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache.size()) {
      this.list$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache.add(new BoxCollision.BoxBoxTransformCache());
    }
    return (BoxCollision.BoxBoxTransformCache)this.list$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache.get(this.pos$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache++);
  }
  
  public BoxCollision.BoxBoxTransformCache get$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache(BoxCollision.BoxBoxTransformCache paramBoxBoxTransformCache)
  {
    BoxCollision.BoxBoxTransformCache localBoxBoxTransformCache = get$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
    localBoxBoxTransformCache.set(paramBoxBoxTransformCache);
    return localBoxBoxTransformCache;
  }
  
  public void push$javax$vecmath$Quat4f()
  {
    if (this.count$javax$vecmath$Quat4f == this.stack$javax$vecmath$Quat4f.length) {
      resize$javax$vecmath$Quat4f();
    }
    this.stack$javax$vecmath$Quat4f[(this.count$javax$vecmath$Quat4f++)] = this.pos$javax$vecmath$Quat4f;
  }
  
  private void resize$javax$vecmath$Quat4f()
  {
    int[] arrayOfInt = new int[this.stack$javax$vecmath$Quat4f.length << 1];
    System.arraycopy(this.stack$javax$vecmath$Quat4f, 0, arrayOfInt, 0, this.stack$javax$vecmath$Quat4f.length);
    this.stack$javax$vecmath$Quat4f = arrayOfInt;
  }
  
  public void pop$javax$vecmath$Quat4f()
  {
    this.pos$javax$vecmath$Quat4f = this.stack$javax$vecmath$Quat4f[(--this.count$javax$vecmath$Quat4f)];
  }
  
  public Quat4f get$javax$vecmath$Quat4f()
  {
    if (this.pos$javax$vecmath$Quat4f == this.list$javax$vecmath$Quat4f.size()) {
      this.list$javax$vecmath$Quat4f.add(new Quat4f());
    }
    return (Quat4f)this.list$javax$vecmath$Quat4f.get(this.pos$javax$vecmath$Quat4f++);
  }
  
  public Quat4f get$javax$vecmath$Quat4f(Quat4f paramQuat4f)
  {
    Quat4f localQuat4f = get$javax$vecmath$Quat4f();
    localQuat4f.set(paramQuat4f);
    return localQuat4f;
  }
  
  public void push$javax$vecmath$Vector4f()
  {
    if (this.count$javax$vecmath$Vector4f == this.stack$javax$vecmath$Vector4f.length) {
      resize$javax$vecmath$Vector4f();
    }
    this.stack$javax$vecmath$Vector4f[(this.count$javax$vecmath$Vector4f++)] = this.pos$javax$vecmath$Vector4f;
  }
  
  private void resize$javax$vecmath$Vector4f()
  {
    int[] arrayOfInt = new int[this.stack$javax$vecmath$Vector4f.length << 1];
    System.arraycopy(this.stack$javax$vecmath$Vector4f, 0, arrayOfInt, 0, this.stack$javax$vecmath$Vector4f.length);
    this.stack$javax$vecmath$Vector4f = arrayOfInt;
  }
  
  public void pop$javax$vecmath$Vector4f()
  {
    this.pos$javax$vecmath$Vector4f = this.stack$javax$vecmath$Vector4f[(--this.count$javax$vecmath$Vector4f)];
  }
  
  public Vector4f get$javax$vecmath$Vector4f()
  {
    if (this.pos$javax$vecmath$Vector4f == this.list$javax$vecmath$Vector4f.size()) {
      this.list$javax$vecmath$Vector4f.add(new Vector4f());
    }
    return (Vector4f)this.list$javax$vecmath$Vector4f.get(this.pos$javax$vecmath$Vector4f++);
  }
  
  public Vector4f get$javax$vecmath$Vector4f(Vector4f paramVector4f)
  {
    Vector4f localVector4f = get$javax$vecmath$Vector4f();
    localVector4f.set(paramVector4f);
    return localVector4f;
  }
  
  public void push$javax$vecmath$Matrix3f()
  {
    if (this.count$javax$vecmath$Matrix3f == this.stack$javax$vecmath$Matrix3f.length) {
      resize$javax$vecmath$Matrix3f();
    }
    this.stack$javax$vecmath$Matrix3f[(this.count$javax$vecmath$Matrix3f++)] = this.pos$javax$vecmath$Matrix3f;
  }
  
  private void resize$javax$vecmath$Matrix3f()
  {
    int[] arrayOfInt = new int[this.stack$javax$vecmath$Matrix3f.length << 1];
    System.arraycopy(this.stack$javax$vecmath$Matrix3f, 0, arrayOfInt, 0, this.stack$javax$vecmath$Matrix3f.length);
    this.stack$javax$vecmath$Matrix3f = arrayOfInt;
  }
  
  public void pop$javax$vecmath$Matrix3f()
  {
    this.pos$javax$vecmath$Matrix3f = this.stack$javax$vecmath$Matrix3f[(--this.count$javax$vecmath$Matrix3f)];
  }
  
  public Matrix3f get$javax$vecmath$Matrix3f()
  {
    if (this.pos$javax$vecmath$Matrix3f == this.list$javax$vecmath$Matrix3f.size()) {
      this.list$javax$vecmath$Matrix3f.add(new Matrix3f());
    }
    return (Matrix3f)this.list$javax$vecmath$Matrix3f.get(this.pos$javax$vecmath$Matrix3f++);
  }
  
  public Matrix3f get$javax$vecmath$Matrix3f(Matrix3f paramMatrix3f)
  {
    Matrix3f localMatrix3f = get$javax$vecmath$Matrix3f();
    localMatrix3f.set(paramMatrix3f);
    return localMatrix3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics..Stack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */