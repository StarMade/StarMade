package com.bulletphysics.collision.broadphase;

public enum BroadphaseNativeType
{
  BOX_SHAPE_PROXYTYPE,  TRIANGLE_SHAPE_PROXYTYPE,  TETRAHEDRAL_SHAPE_PROXYTYPE,  CONVEX_TRIANGLEMESH_SHAPE_PROXYTYPE,  CONVEX_HULL_SHAPE_PROXYTYPE,  IMPLICIT_CONVEX_SHAPES_START_HERE,  SPHERE_SHAPE_PROXYTYPE,  MULTI_SPHERE_SHAPE_PROXYTYPE,  CAPSULE_SHAPE_PROXYTYPE,  CONE_SHAPE_PROXYTYPE,  CONVEX_SHAPE_PROXYTYPE,  CYLINDER_SHAPE_PROXYTYPE,  UNIFORM_SCALING_SHAPE_PROXYTYPE,  MINKOWSKI_SUM_SHAPE_PROXYTYPE,  MINKOWSKI_DIFFERENCE_SHAPE_PROXYTYPE,  CONCAVE_SHAPES_START_HERE,  TRIANGLE_MESH_SHAPE_PROXYTYPE,  SCALED_TRIANGLE_MESH_SHAPE_PROXYTYPE,  FAST_CONCAVE_MESH_PROXYTYPE,  TERRAIN_SHAPE_PROXYTYPE,  GIMPACT_SHAPE_PROXYTYPE,  MULTIMATERIAL_TRIANGLE_MESH_PROXYTYPE,  EMPTY_SHAPE_PROXYTYPE,  STATIC_PLANE_PROXYTYPE,  CONCAVE_SHAPES_END_HERE,  COMPOUND_SHAPE_PROXYTYPE,  SOFTBODY_SHAPE_PROXYTYPE,  INVALID_SHAPE_PROXYTYPE,  MAX_BROADPHASE_COLLISION_TYPES;
  
  private static BroadphaseNativeType[] values = values();
  
  private BroadphaseNativeType() {}
  
  public static BroadphaseNativeType forValue(int value)
  {
    return values[value];
  }
  
  public boolean isPolyhedral()
  {
    return ordinal() < IMPLICIT_CONVEX_SHAPES_START_HERE.ordinal();
  }
  
  public boolean isConvex()
  {
    return ordinal() < CONCAVE_SHAPES_START_HERE.ordinal();
  }
  
  public boolean isConcave()
  {
    return (ordinal() > CONCAVE_SHAPES_START_HERE.ordinal()) && (ordinal() < CONCAVE_SHAPES_END_HERE.ordinal());
  }
  
  public boolean isCompound()
  {
    return ordinal() == COMPOUND_SHAPE_PROXYTYPE.ordinal();
  }
  
  public boolean isInfinite()
  {
    return ordinal() == STATIC_PLANE_PROXYTYPE.ordinal();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphaseNativeType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */