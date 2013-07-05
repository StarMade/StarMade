/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ public enum BroadphaseNativeType
/*     */ {
/*  37 */   BOX_SHAPE_PROXYTYPE, 
/*  38 */   TRIANGLE_SHAPE_PROXYTYPE, 
/*  39 */   TETRAHEDRAL_SHAPE_PROXYTYPE, 
/*  40 */   CONVEX_TRIANGLEMESH_SHAPE_PROXYTYPE, 
/*  41 */   CONVEX_HULL_SHAPE_PROXYTYPE, 
/*     */ 
/*  44 */   IMPLICIT_CONVEX_SHAPES_START_HERE, 
/*  45 */   SPHERE_SHAPE_PROXYTYPE, 
/*  46 */   MULTI_SPHERE_SHAPE_PROXYTYPE, 
/*  47 */   CAPSULE_SHAPE_PROXYTYPE, 
/*  48 */   CONE_SHAPE_PROXYTYPE, 
/*  49 */   CONVEX_SHAPE_PROXYTYPE, 
/*  50 */   CYLINDER_SHAPE_PROXYTYPE, 
/*  51 */   UNIFORM_SCALING_SHAPE_PROXYTYPE, 
/*  52 */   MINKOWSKI_SUM_SHAPE_PROXYTYPE, 
/*  53 */   MINKOWSKI_DIFFERENCE_SHAPE_PROXYTYPE, 
/*     */ 
/*  56 */   CONCAVE_SHAPES_START_HERE, 
/*     */ 
/*  59 */   TRIANGLE_MESH_SHAPE_PROXYTYPE, 
/*  60 */   SCALED_TRIANGLE_MESH_SHAPE_PROXYTYPE, 
/*     */ 
/*  63 */   FAST_CONCAVE_MESH_PROXYTYPE, 
/*     */ 
/*  66 */   TERRAIN_SHAPE_PROXYTYPE, 
/*     */ 
/*  69 */   GIMPACT_SHAPE_PROXYTYPE, 
/*     */ 
/*  72 */   MULTIMATERIAL_TRIANGLE_MESH_PROXYTYPE, 
/*     */ 
/*  74 */   EMPTY_SHAPE_PROXYTYPE, 
/*  75 */   STATIC_PLANE_PROXYTYPE, 
/*  76 */   CONCAVE_SHAPES_END_HERE, 
/*  77 */   COMPOUND_SHAPE_PROXYTYPE, 
/*     */ 
/*  79 */   SOFTBODY_SHAPE_PROXYTYPE, 
/*     */ 
/*  81 */   INVALID_SHAPE_PROXYTYPE, 
/*     */ 
/*  83 */   MAX_BROADPHASE_COLLISION_TYPES;
/*     */ 
/*  85 */   private static BroadphaseNativeType[] values = values();
/*     */ 
/*     */   public static BroadphaseNativeType forValue(int value) {
/*  88 */     return values[value];
/*     */   }
/*     */ 
/*     */   public boolean isPolyhedral() {
/*  92 */     return ordinal() < IMPLICIT_CONVEX_SHAPES_START_HERE.ordinal();
/*     */   }
/*     */ 
/*     */   public boolean isConvex() {
/*  96 */     return ordinal() < CONCAVE_SHAPES_START_HERE.ordinal();
/*     */   }
/*     */ 
/*     */   public boolean isConcave() {
/* 100 */     return (ordinal() > CONCAVE_SHAPES_START_HERE.ordinal()) && (ordinal() < CONCAVE_SHAPES_END_HERE.ordinal());
/*     */   }
/*     */ 
/*     */   public boolean isCompound()
/*     */   {
/* 105 */     return ordinal() == COMPOUND_SHAPE_PROXYTYPE.ordinal();
/*     */   }
/*     */ 
/*     */   public boolean isInfinite() {
/* 109 */     return ordinal() == STATIC_PLANE_PROXYTYPE.ordinal();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphaseNativeType
 * JD-Core Version:    0.6.2
 */