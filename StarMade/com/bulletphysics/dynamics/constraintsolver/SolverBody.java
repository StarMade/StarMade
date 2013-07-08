/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.TransformUtil;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  40:    */public class SolverBody
/*  41:    */{
/*  42: 42 */  public final Vector3f angularVelocity = new Vector3f();
/*  43:    */  public float angularFactor;
/*  44:    */  public float invMass;
/*  45:    */  public float friction;
/*  46:    */  public RigidBody originalBody;
/*  47: 47 */  public final Vector3f linearVelocity = new Vector3f();
/*  48: 48 */  public final Vector3f centerOfMassPosition = new Vector3f();
/*  49:    */  
/*  50: 50 */  public final Vector3f pushVelocity = new Vector3f();
/*  51: 51 */  public final Vector3f turnVelocity = new Vector3f();
/*  52:    */  
/*  53:    */  public void getVelocityInLocalPoint(Vector3f arg1, Vector3f arg2) {
/*  54: 54 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  55: 55 */      tmp.cross(this.angularVelocity, rel_pos);
/*  56: 56 */      velocity.add(this.linearVelocity, tmp);
/*  57: 57 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  58:    */    }
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void internalApplyImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude)
/*  62:    */  {
/*  63: 63 */    if (this.invMass != 0.0F) {
/*  64: 64 */      this.linearVelocity.scaleAdd(impulseMagnitude, linearComponent, this.linearVelocity);
/*  65: 65 */      this.angularVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.angularVelocity);
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void internalApplyPushImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude) {
/*  70: 70 */    if (this.invMass != 0.0F) {
/*  71: 71 */      this.pushVelocity.scaleAdd(impulseMagnitude, linearComponent, this.pushVelocity);
/*  72: 72 */      this.turnVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.turnVelocity);
/*  73:    */    }
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void writebackVelocity() {
/*  77: 77 */    if (this.invMass != 0.0F) {
/*  78: 78 */      this.originalBody.setLinearVelocity(this.linearVelocity);
/*  79: 79 */      this.originalBody.setAngularVelocity(this.angularVelocity);
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void writebackVelocity(float arg1)
/*  84:    */  {
/*  85: 85 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform(); if (this.invMass != 0.0F) {
/*  86: 86 */        this.originalBody.setLinearVelocity(this.linearVelocity);
/*  87: 87 */        this.originalBody.setAngularVelocity(this.angularVelocity);
/*  88:    */        
/*  90: 90 */        Transform newTransform = localStack.get$com$bulletphysics$linearmath$Transform();
/*  91: 91 */        Transform curTrans = this.originalBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*  92: 92 */        TransformUtil.integrateTransform(curTrans, this.pushVelocity, this.turnVelocity, timeStep, newTransform);
/*  93: 93 */        this.originalBody.setWorldTransform(newTransform);
/*  94:    */      }
/*  95:    */    }
/*  96:    */    finally {
/*  97: 97 */      localStack.pop$com$bulletphysics$linearmath$Transform();
/*  98:    */    } }
/*  99:    */  
/* 100:100 */  public void readVelocity() { if (this.invMass != 0.0F) {
/* 101:101 */      this.originalBody.getLinearVelocity(this.linearVelocity);
/* 102:102 */      this.originalBody.getAngularVelocity(this.angularVelocity);
/* 103:    */    }
/* 104:    */  }
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SolverBody
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */