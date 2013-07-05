package com.bulletphysics.extras.gimpact;

abstract class PrimitiveManagerBase
{
  public abstract boolean is_trimesh();

  public abstract int get_primitive_count();

  public abstract void get_primitive_box(int paramInt, BoxCollision.AABB paramAABB);

  public abstract void get_primitive_triangle(int paramInt, PrimitiveTriangle paramPrimitiveTriangle);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.PrimitiveManagerBase
 * JD-Core Version:    0.6.2
 */