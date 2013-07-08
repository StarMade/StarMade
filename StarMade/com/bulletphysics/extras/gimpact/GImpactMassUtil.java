package com.bulletphysics.extras.gimpact;

import javax.vecmath.Vector3f;

class GImpactMassUtil
{
  public static Vector3f get_point_inertia(Vector3f point, float mass, Vector3f out)
  {
    float local_x2 = point.field_615 * point.field_615;
    float local_y2 = point.field_616 * point.field_616;
    float local_z2 = point.field_617 * point.field_617;
    out.set(mass * (local_y2 + local_z2), mass * (local_x2 + local_z2), mass * (local_x2 + local_y2));
    return out;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMassUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */