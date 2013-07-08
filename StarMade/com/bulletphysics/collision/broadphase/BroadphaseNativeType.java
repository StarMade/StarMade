/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*  18:    */public enum BroadphaseNativeType
/*  19:    */{
/*  20:    */  private BroadphaseNativeType() {}
/*  21:    */  
/*  37: 37 */  BOX_SHAPE_PROXYTYPE, 
/*  38: 38 */  TRIANGLE_SHAPE_PROXYTYPE, 
/*  39: 39 */  TETRAHEDRAL_SHAPE_PROXYTYPE, 
/*  40: 40 */  CONVEX_TRIANGLEMESH_SHAPE_PROXYTYPE, 
/*  41: 41 */  CONVEX_HULL_SHAPE_PROXYTYPE, 
/*  42:    */  
/*  44: 44 */  IMPLICIT_CONVEX_SHAPES_START_HERE, 
/*  45: 45 */  SPHERE_SHAPE_PROXYTYPE, 
/*  46: 46 */  MULTI_SPHERE_SHAPE_PROXYTYPE, 
/*  47: 47 */  CAPSULE_SHAPE_PROXYTYPE, 
/*  48: 48 */  CONE_SHAPE_PROXYTYPE, 
/*  49: 49 */  CONVEX_SHAPE_PROXYTYPE, 
/*  50: 50 */  CYLINDER_SHAPE_PROXYTYPE, 
/*  51: 51 */  UNIFORM_SCALING_SHAPE_PROXYTYPE, 
/*  52: 52 */  MINKOWSKI_SUM_SHAPE_PROXYTYPE, 
/*  53: 53 */  MINKOWSKI_DIFFERENCE_SHAPE_PROXYTYPE, 
/*  54:    */  
/*  56: 56 */  CONCAVE_SHAPES_START_HERE, 
/*  57:    */  
/*  59: 59 */  TRIANGLE_MESH_SHAPE_PROXYTYPE, 
/*  60: 60 */  SCALED_TRIANGLE_MESH_SHAPE_PROXYTYPE, 
/*  61:    */  
/*  63: 63 */  FAST_CONCAVE_MESH_PROXYTYPE, 
/*  64:    */  
/*  66: 66 */  TERRAIN_SHAPE_PROXYTYPE, 
/*  67:    */  
/*  69: 69 */  GIMPACT_SHAPE_PROXYTYPE, 
/*  70:    */  
/*  72: 72 */  MULTIMATERIAL_TRIANGLE_MESH_PROXYTYPE, 
/*  73:    */  
/*  74: 74 */  EMPTY_SHAPE_PROXYTYPE, 
/*  75: 75 */  STATIC_PLANE_PROXYTYPE, 
/*  76: 76 */  CONCAVE_SHAPES_END_HERE, 
/*  77: 77 */  COMPOUND_SHAPE_PROXYTYPE, 
/*  78:    */  
/*  79: 79 */  SOFTBODY_SHAPE_PROXYTYPE, 
/*  80:    */  
/*  81: 81 */  INVALID_SHAPE_PROXYTYPE, 
/*  82:    */  
/*  83: 83 */  MAX_BROADPHASE_COLLISION_TYPES;
/*  84:    */  
/*  85: 85 */  private static BroadphaseNativeType[] values = values();
/*  86:    */  
/*  87:    */  public static BroadphaseNativeType forValue(int value) {
/*  88: 88 */    return values[value];
/*  89:    */  }
/*  90:    */  
/*  91:    */  public boolean isPolyhedral() {
/*  92: 92 */    return ordinal() < IMPLICIT_CONVEX_SHAPES_START_HERE.ordinal();
/*  93:    */  }
/*  94:    */  
/*  95:    */  public boolean isConvex() {
/*  96: 96 */    return ordinal() < CONCAVE_SHAPES_START_HERE.ordinal();
/*  97:    */  }
/*  98:    */  
/*  99:    */  public boolean isConcave() {
/* 100:100 */    return (ordinal() > CONCAVE_SHAPES_START_HERE.ordinal()) && (ordinal() < CONCAVE_SHAPES_END_HERE.ordinal());
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean isCompound()
/* 104:    */  {
/* 105:105 */    return ordinal() == COMPOUND_SHAPE_PROXYTYPE.ordinal();
/* 106:    */  }
/* 107:    */  
/* 108:    */  public boolean isInfinite() {
/* 109:109 */    return ordinal() == STATIC_PLANE_PROXYTYPE.ordinal();
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphaseNativeType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */