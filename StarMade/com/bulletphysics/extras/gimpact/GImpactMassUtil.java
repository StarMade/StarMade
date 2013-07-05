/*    */ package com.bulletphysics.extras.gimpact;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ class GImpactMassUtil
/*    */ {
/*    */   public static Vector3f get_point_inertia(Vector3f point, float mass, Vector3f out)
/*    */   {
/* 39 */     float x2 = point.x * point.x;
/* 40 */     float y2 = point.y * point.y;
/* 41 */     float z2 = point.z * point.z;
/* 42 */     out.set(mass * (y2 + z2), mass * (x2 + z2), mass * (x2 + y2));
/* 43 */     return out;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMassUtil
 * JD-Core Version:    0.6.2
 */