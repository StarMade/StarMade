package com.bulletphysics.collision.narrowphase;

import javax.vecmath.Vector3f;

public abstract class SimplexSolverInterface
{
  public abstract void reset();

  public abstract void addVertex(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3);

  public abstract boolean closest(Vector3f paramVector3f);

  public abstract float maxVertex();

  public abstract boolean fullSimplex();

  public abstract int getSimplex(Vector3f[] paramArrayOfVector3f1, Vector3f[] paramArrayOfVector3f2, Vector3f[] paramArrayOfVector3f3);

  public abstract boolean inSimplex(Vector3f paramVector3f);

  public abstract void backup_closest(Vector3f paramVector3f);

  public abstract boolean emptySimplex();

  public abstract void compute_points(Vector3f paramVector3f1, Vector3f paramVector3f2);

  public abstract int numVertices();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.SimplexSolverInterface
 * JD-Core Version:    0.6.2
 */