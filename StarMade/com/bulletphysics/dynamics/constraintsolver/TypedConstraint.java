/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics.dynamics.RigidBody;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */
/*  36:    */public abstract class TypedConstraint
/*  37:    */{
/*  38:    */  private static RigidBody s_fixed;
/*  39:    */  
/*  40:    */  private static synchronized RigidBody getFixed()
/*  41:    */  {
/*  42: 42 */    if (s_fixed == null) {
/*  43: 43 */      s_fixed = new RigidBody(0.0F, null, null);
/*  44:    */    }
/*  45: 45 */    return s_fixed;
/*  46:    */  }
/*  47:    */  
/*  48: 48 */  private int userConstraintType = -1;
/*  49: 49 */  private int userConstraintId = -1;
/*  50:    */  
/*  51:    */  private TypedConstraintType constraintType;
/*  52:    */  
/*  53:    */  protected RigidBody rbA;
/*  54:    */  protected RigidBody rbB;
/*  55: 55 */  protected float appliedImpulse = 0.0F;
/*  56:    */  
/*  57:    */  public TypedConstraint(TypedConstraintType type) {
/*  58: 58 */    this(type, getFixed(), getFixed());
/*  59:    */  }
/*  60:    */  
/*  61:    */  public TypedConstraint(TypedConstraintType type, RigidBody rbA) {
/*  62: 62 */    this(type, rbA, getFixed());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public TypedConstraint(TypedConstraintType type, RigidBody rbA, RigidBody rbB) {
/*  66: 66 */    this.constraintType = type;
/*  67: 67 */    this.rbA = rbA;
/*  68: 68 */    this.rbB = rbB;
/*  69: 69 */    getFixed().setMassProps(0.0F, new Vector3f(0.0F, 0.0F, 0.0F));
/*  70:    */  }
/*  71:    */  
/*  72:    */  public abstract void buildJacobian();
/*  73:    */  
/*  74:    */  public abstract void solveConstraint(float paramFloat);
/*  75:    */  
/*  76:    */  public RigidBody getRigidBodyA() {
/*  77: 77 */    return this.rbA;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public RigidBody getRigidBodyB() {
/*  81: 81 */    return this.rbB;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public int getUserConstraintType() {
/*  85: 85 */    return this.userConstraintType;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void setUserConstraintType(int userConstraintType) {
/*  89: 89 */    this.userConstraintType = userConstraintType;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public int getUserConstraintId() {
/*  93: 93 */    return this.userConstraintId;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public int getUid() {
/*  97: 97 */    return this.userConstraintId;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public void setUserConstraintId(int userConstraintId) {
/* 101:101 */    this.userConstraintId = userConstraintId;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public float getAppliedImpulse() {
/* 105:105 */    return this.appliedImpulse;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public TypedConstraintType getConstraintType() {
/* 109:109 */    return this.constraintType;
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.TypedConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */