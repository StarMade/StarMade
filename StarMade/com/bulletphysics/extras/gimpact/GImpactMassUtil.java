/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 35:   */class GImpactMassUtil
/* 36:   */{
/* 37:   */  public static Vector3f get_point_inertia(Vector3f point, float mass, Vector3f out)
/* 38:   */  {
/* 39:39 */    float x2 = point.x * point.x;
/* 40:40 */    float y2 = point.y * point.y;
/* 41:41 */    float z2 = point.z * point.z;
/* 42:42 */    out.set(mass * (y2 + z2), mass * (x2 + z2), mass * (x2 + y2));
/* 43:43 */    return out;
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMassUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */