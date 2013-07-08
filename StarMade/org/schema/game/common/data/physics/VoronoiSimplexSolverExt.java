package org.schema.game.common.data.physics;

import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Vector3f;

public class VoronoiSimplexSolverExt
  extends SimplexSolverInterface
{
  private static ThreadLocal threadLocal = new VoronoiSimplexSolverExt.1();
  protected final ObjectPool subsimplexResultsPool = ObjectPool.get(VoronoiSimplexSolverExt.SubSimplexClosestResult.class);
  private static final int VORONOI_SIMPLEX_MAX_VERTS = 5;
  private static final int VERTA = 0;
  private static final int VERTB = 1;
  private static final int VERTC = 2;
  private static final int VERTD = 3;
  private final VoronoiSimplexSolverVariables field_307;
  public int numVertices;
  public final Vector3f[] simplexVectorW = new Vector3f[5];
  public final Vector3f[] simplexPointsP = new Vector3f[5];
  public final Vector3f[] simplexPointsQ = new Vector3f[5];
  public final Vector3f cachedP1 = new Vector3f();
  public final Vector3f cachedP2 = new Vector3f();
  public final Vector3f cachedV = new Vector3f();
  public final Vector3f lastW = new Vector3f();
  public boolean cachedValidClosest;
  public final VoronoiSimplexSolverExt.SubSimplexClosestResult cachedBC = new VoronoiSimplexSolverExt.SubSimplexClosestResult();
  public boolean needsUpdate;
  
  public VoronoiSimplexSolverExt()
  {
    for (int i = 0; i < 5; i++)
    {
      this.simplexVectorW[i] = new Vector3f();
      this.simplexPointsP[i] = new Vector3f();
      this.simplexPointsQ[i] = new Vector3f();
    }
    this.field_307 = ((VoronoiSimplexSolverVariables)threadLocal.get());
  }
  
  public void removeVertex(int paramInt)
  {
    assert (this.numVertices > 0);
    this.numVertices -= 1;
    this.simplexVectorW[paramInt].set(this.simplexVectorW[this.numVertices]);
    this.simplexPointsP[paramInt].set(this.simplexPointsP[this.numVertices]);
    this.simplexPointsQ[paramInt].set(this.simplexPointsQ[this.numVertices]);
  }
  
  public void reduceVertices(VoronoiSimplexSolverExt.UsageBitfield paramUsageBitfield)
  {
    if ((numVertices() >= 4) && (!paramUsageBitfield.usedVertexD)) {
      removeVertex(3);
    }
    if ((numVertices() >= 3) && (!paramUsageBitfield.usedVertexC)) {
      removeVertex(2);
    }
    if ((numVertices() >= 2) && (!paramUsageBitfield.usedVertexB)) {
      removeVertex(1);
    }
    if ((numVertices() > 0) && (!paramUsageBitfield.usedVertexA)) {
      removeVertex(0);
    }
  }
  
  public boolean updateClosestVectorAndPoints()
  {
    if (this.needsUpdate)
    {
      this.cachedBC.reset();
      this.needsUpdate = false;
      Vector3f localVector3f1;
      Vector3f localVector3f2;
      Vector3f localVector3f3;
      Vector3f localVector3f4;
      Vector3f localVector3f5;
      Vector3f localVector3f6;
      Vector3f localVector3f7;
      switch (numVertices())
      {
      case 0: 
        this.cachedValidClosest = false;
        break;
      case 1: 
        this.cachedP1.set(this.simplexPointsP[0]);
        this.cachedP2.set(this.simplexPointsQ[0]);
        this.cachedV.sub(this.cachedP1, this.cachedP2);
        this.cachedBC.reset();
        this.cachedBC.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
        this.cachedValidClosest = this.cachedBC.isValid();
        break;
      case 2: 
        localVector3f1 = this.field_307.tmp;
        localVector3f2 = this.simplexVectorW[0];
        localVector3f3 = this.simplexVectorW[1];
        localVector3f4 = this.field_307.nearest;
        (localVector3f5 = this.field_307.field_1811).set(0.0F, 0.0F, 0.0F);
        (localVector3f6 = this.field_307.diff).sub(localVector3f5, localVector3f2);
        (localVector3f7 = this.field_307.field_1812).sub(localVector3f3, localVector3f2);
        float f1;
        if ((f1 = localVector3f7.dot(localVector3f6)) > 0.0F)
        {
          float f2 = localVector3f7.dot(localVector3f7);
          if (f1 < f2)
          {
            f1 /= f2;
            localVector3f1.scale(f1, localVector3f7);
            localVector3f6.sub(localVector3f1);
            this.cachedBC.usedVertices.usedVertexA = true;
            this.cachedBC.usedVertices.usedVertexB = true;
          }
          else
          {
            f1 = 1.0F;
            localVector3f6.sub(localVector3f7);
            this.cachedBC.usedVertices.usedVertexB = true;
          }
        }
        else
        {
          f1 = 0.0F;
          this.cachedBC.usedVertices.usedVertexA = true;
        }
        this.cachedBC.setBarycentricCoordinates(1.0F - f1, f1, 0.0F, 0.0F);
        localVector3f1.scale(f1, localVector3f7);
        localVector3f4.add(localVector3f2, localVector3f1);
        localVector3f1.sub(this.simplexPointsP[1], this.simplexPointsP[0]);
        localVector3f1.scale(f1);
        this.cachedP1.add(this.simplexPointsP[0], localVector3f1);
        localVector3f1.sub(this.simplexPointsQ[1], this.simplexPointsQ[0]);
        localVector3f1.scale(f1);
        this.cachedP2.add(this.simplexPointsQ[0], localVector3f1);
        this.cachedV.sub(this.cachedP1, this.cachedP2);
        reduceVertices(this.cachedBC.usedVertices);
        this.cachedValidClosest = this.cachedBC.isValid();
        break;
      case 3: 
        localVector3f1 = this.field_307.tmp1;
        localVector3f2 = this.field_307.tmp2;
        localVector3f3 = this.field_307.tmp3;
        (localVector3f4 = this.field_307.field_1811).set(0.0F, 0.0F, 0.0F);
        localVector3f5 = this.simplexVectorW[0];
        localVector3f6 = this.simplexVectorW[1];
        localVector3f7 = this.simplexVectorW[2];
        closestPtPointTriangle(localVector3f4, localVector3f5, localVector3f6, localVector3f7, this.cachedBC);
        localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
        localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
        localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
        VectorUtil.add(this.cachedP1, localVector3f1, localVector3f2, localVector3f3);
        localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
        localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
        localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
        VectorUtil.add(this.cachedP2, localVector3f1, localVector3f2, localVector3f3);
        this.cachedV.sub(this.cachedP1, this.cachedP2);
        reduceVertices(this.cachedBC.usedVertices);
        this.cachedValidClosest = this.cachedBC.isValid();
        break;
      case 4: 
        localVector3f1 = this.field_307.tmp1;
        localVector3f2 = this.field_307.tmp2;
        localVector3f3 = this.field_307.tmp3;
        localVector3f4 = this.field_307.tmp4;
        (localVector3f5 = this.field_307.field_1811).set(0.0F, 0.0F, 0.0F);
        localVector3f6 = this.simplexVectorW[0];
        localVector3f7 = this.simplexVectorW[1];
        Vector3f localVector3f8 = this.simplexVectorW[2];
        Vector3f localVector3f9 = this.simplexVectorW[3];
        if (closestPtPointTetrahedron(localVector3f5, localVector3f6, localVector3f7, localVector3f8, localVector3f9, this.cachedBC))
        {
          localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
          localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
          localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
          localVector3f4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsP[3]);
          VectorUtil.add(this.cachedP1, localVector3f1, localVector3f2, localVector3f3, localVector3f4);
          localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
          localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
          localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
          localVector3f4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsQ[3]);
          VectorUtil.add(this.cachedP2, localVector3f1, localVector3f2, localVector3f3, localVector3f4);
          this.cachedV.sub(this.cachedP1, this.cachedP2);
          reduceVertices(this.cachedBC.usedVertices);
        }
        else
        {
          if (this.cachedBC.degenerate) {
            break;
          }
          this.cachedValidClosest = true;
          this.cachedV.set(0.0F, 0.0F, 0.0F);
          break label1084;
        }
        this.cachedValidClosest = this.cachedBC.isValid();
        break;
      }
      this.cachedValidClosest = false;
    }
    label1084:
    return this.cachedValidClosest;
  }
  
  public boolean closestPtPointTriangle(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, VoronoiSimplexSolverExt.SubSimplexClosestResult paramSubSimplexClosestResult)
  {
    paramSubSimplexClosestResult.usedVertices.reset();
    Vector3f localVector3f1;
    (localVector3f1 = this.field_307.field_1813).sub(paramVector3f3, paramVector3f2);
    Vector3f localVector3f2;
    (localVector3f2 = this.field_307.field_1814).sub(paramVector3f4, paramVector3f2);
    Vector3f localVector3f3;
    (localVector3f3 = this.field_307.field_1815).sub(paramVector3f1, paramVector3f2);
    float f3 = localVector3f1.dot(localVector3f3);
    float f1 = localVector3f2.dot(localVector3f3);
    if ((f3 <= 0.0F) && (f1 <= 0.0F))
    {
      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f2);
      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
      paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
      return true;
    }
    Vector3f localVector3f5;
    (localVector3f5 = this.field_307.field_1816).sub(paramVector3f1, paramVector3f3);
    float f5 = localVector3f1.dot(localVector3f5);
    float f4 = localVector3f2.dot(localVector3f5);
    if ((f5 >= 0.0F) && (f4 <= f5))
    {
      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f3);
      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
      paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 1.0F, 0.0F, 0.0F);
      return true;
    }
    float f6;
    if (((f6 = f3 * f4 - f5 * f1) <= 0.0F) && (f3 >= 0.0F) && (f5 <= 0.0F))
    {
      float f7 = f3 / (f3 - f5);
      paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(f7, localVector3f1, paramVector3f2);
      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
      paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f7, f7, 0.0F, 0.0F);
      return true;
    }
    Vector3f localVector3f6;
    (localVector3f6 = this.field_307.field_1817).sub(paramVector3f1, paramVector3f4);
    paramVector3f1 = localVector3f1.dot(localVector3f6);
    float f8;
    if (((f8 = localVector3f2.dot(localVector3f6)) >= 0.0F) && (paramVector3f1 <= f8))
    {
      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f4);
      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
      paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 0.0F, 1.0F, 0.0F);
      return true;
    }
    if (((f3 = paramVector3f1 * f1 - f3 * f8) <= 0.0F) && (f1 >= 0.0F) && (f8 <= 0.0F))
    {
      f1 /= (f1 - f8);
      paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(f1, localVector3f2, paramVector3f2);
      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
      paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f1, 0.0F, f1, 0.0F);
      return true;
    }
    Vector3f localVector3f4;
    if (((f1 = f5 * f8 - paramVector3f1 * f4) <= 0.0F) && (f4 - f5 >= 0.0F) && (paramVector3f1 - f8 >= 0.0F))
    {
      paramVector3f1 = (f4 - f5) / (f4 - f5 + (paramVector3f1 - f8));
      (localVector3f4 = this.field_307.tmptmp).sub(paramVector3f4, paramVector3f3);
      paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(paramVector3f1, localVector3f4, paramVector3f3);
      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
      paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 1.0F - paramVector3f1, paramVector3f1, 0.0F);
      return true;
    }
    paramVector3f1 = 1.0F / (localVector3f4 + f3 + f6);
    float f2 = f3 * paramVector3f1;
    paramVector3f1 = f6 * paramVector3f1;
    paramVector3f3 = this.field_307.tmptmp1;
    paramVector3f4 = this.field_307.tmptmp2;
    paramVector3f3.scale(f2, localVector3f1);
    paramVector3f4.scale(paramVector3f1, localVector3f2);
    VectorUtil.add(paramSubSimplexClosestResult.closestPointOnSimplex, paramVector3f2, paramVector3f3, paramVector3f4);
    paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
    paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
    paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
    paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f2 - paramVector3f1, f2, paramVector3f1, 0.0F);
    return true;
  }
  
  public static int pointOutsideOfPlane(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, VoronoiSimplexSolverVariables paramVoronoiSimplexSolverVariables)
  {
    Vector3f localVector3f = paramVoronoiSimplexSolverVariables.tmpTmpTmp;
    (paramVoronoiSimplexSolverVariables = paramVoronoiSimplexSolverVariables.normal).sub(paramVector3f3, paramVector3f2);
    localVector3f.sub(paramVector3f4, paramVector3f2);
    paramVoronoiSimplexSolverVariables.cross(paramVoronoiSimplexSolverVariables, localVector3f);
    localVector3f.sub(paramVector3f1, paramVector3f2);
    paramVector3f1 = localVector3f.dot(paramVoronoiSimplexSolverVariables);
    localVector3f.sub(paramVector3f5, paramVector3f2);
    float tmp66_63 = localVector3f.dot(paramVoronoiSimplexSolverVariables);
    if (tmp66_63 * (paramVector3f2 = tmp66_63) < 9.999999E-009F) {
      return -1;
    }
    if (paramVector3f1 * paramVector3f2 < 0.0F) {
      return 1;
    }
    return 0;
  }
  
  public boolean closestPtPointTetrahedron(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, VoronoiSimplexSolverExt.SubSimplexClosestResult paramSubSimplexClosestResult)
  {
    VoronoiSimplexSolverExt.SubSimplexClosestResult localSubSimplexClosestResult;
    (localSubSimplexClosestResult = (VoronoiSimplexSolverExt.SubSimplexClosestResult)this.subsimplexResultsPool.get()).reset();
    try
    {
      Vector3f localVector3f1 = this.field_307.tmptmptmptmp;
      Vector3f localVector3f2 = this.field_307.field_1818;
      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f1);
      paramSubSimplexClosestResult.usedVertices.reset();
      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
      paramSubSimplexClosestResult.usedVertices.usedVertexD = true;
      int i = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f3, paramVector3f4, paramVector3f5, this.field_307);
      int j = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f4, paramVector3f5, paramVector3f3, this.field_307);
      int k = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f5, paramVector3f3, paramVector3f4, this.field_307);
      int m = pointOutsideOfPlane(paramVector3f1, paramVector3f3, paramVector3f5, paramVector3f4, paramVector3f2, this.field_307);
      if ((i < 0) || (j < 0) || (k < 0) || (m < 0))
      {
        paramSubSimplexClosestResult.degenerate = true;
        return false;
      }
      if ((i == 0) && (j == 0) && (k == 0) && (m == 0)) {
        return false;
      }
      float f2 = 3.4028235E+38F;
      float f1;
      if (i != 0)
      {
        closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f3, paramVector3f4, localSubSimplexClosestResult);
        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
        localVector3f1.sub(localVector3f2, paramVector3f1);
        if ((f1 = localVector3f1.dot(localVector3f1)) < 3.4028235E+38F)
        {
          f2 = f1;
          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
          paramSubSimplexClosestResult.usedVertices.reset();
          paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
          paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexB;
          paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexC;
          paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[1], localSubSimplexClosestResult.barycentricCoords[2], 0.0F);
        }
      }
      if (j != 0)
      {
        closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f4, paramVector3f5, localSubSimplexClosestResult);
        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
        localVector3f1.sub(localVector3f2, paramVector3f1);
        if ((f1 = localVector3f1.dot(localVector3f1)) < f2)
        {
          f2 = f1;
          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
          paramSubSimplexClosestResult.usedVertices.reset();
          paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
          paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexB;
          paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexC;
          paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], 0.0F, localSubSimplexClosestResult.barycentricCoords[1], localSubSimplexClosestResult.barycentricCoords[2]);
        }
      }
      if (k != 0)
      {
        closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f5, paramVector3f3, localSubSimplexClosestResult);
        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
        localVector3f1.sub(localVector3f2, paramVector3f1);
        if ((f1 = localVector3f1.dot(localVector3f1)) < f2)
        {
          f2 = f1;
          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
          paramSubSimplexClosestResult.usedVertices.reset();
          paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
          paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexC;
          paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexB;
          paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[2], 0.0F, localSubSimplexClosestResult.barycentricCoords[1]);
        }
      }
      if (m != 0)
      {
        closestPtPointTriangle(paramVector3f1, paramVector3f3, paramVector3f5, paramVector3f4, localSubSimplexClosestResult);
        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
        localVector3f1.sub(localVector3f2, paramVector3f1);
        if (localVector3f1.dot(localVector3f1) < f2)
        {
          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
          paramSubSimplexClosestResult.usedVertices.reset();
          paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexA;
          paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexC;
          paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexB;
          paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[2], localSubSimplexClosestResult.barycentricCoords[1]);
        }
      }
      return (paramSubSimplexClosestResult.usedVertices.usedVertexA) && (paramSubSimplexClosestResult.usedVertices.usedVertexB) && (paramSubSimplexClosestResult.usedVertices.usedVertexC) && (paramSubSimplexClosestResult.usedVertices.usedVertexD);
    }
    finally
    {
      this.subsimplexResultsPool.release(localSubSimplexClosestResult);
    }
  }
  
  public void reset()
  {
    this.cachedValidClosest = false;
    this.numVertices = 0;
    this.needsUpdate = true;
    this.lastW.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
    this.cachedBC.reset();
  }
  
  public void addVertex(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
  {
    this.lastW.set(paramVector3f1);
    this.needsUpdate = true;
    this.simplexVectorW[this.numVertices].set(paramVector3f1);
    this.simplexPointsP[this.numVertices].set(paramVector3f2);
    this.simplexPointsQ[this.numVertices].set(paramVector3f3);
    this.numVertices += 1;
  }
  
  public boolean closest(Vector3f paramVector3f)
  {
    boolean bool = updateClosestVectorAndPoints();
    paramVector3f.set(this.cachedV);
    return bool;
  }
  
  public float maxVertex()
  {
    int j = numVertices();
    float f1 = 0.0F;
    for (int i = 0; i < j; i++)
    {
      float f2 = this.simplexVectorW[i].lengthSquared();
      if (f1 < f2) {
        f1 = f2;
      }
    }
    return f1;
  }
  
  public boolean fullSimplex()
  {
    return this.numVertices == 4;
  }
  
  public int getSimplex(Vector3f[] paramArrayOfVector3f1, Vector3f[] paramArrayOfVector3f2, Vector3f[] paramArrayOfVector3f3)
  {
    for (int i = 0; i < numVertices(); i++)
    {
      paramArrayOfVector3f3[i].set(this.simplexVectorW[i]);
      paramArrayOfVector3f1[i].set(this.simplexPointsP[i]);
      paramArrayOfVector3f2[i].set(this.simplexPointsQ[i]);
    }
    return numVertices();
  }
  
  public boolean inSimplex(Vector3f paramVector3f)
  {
    boolean bool = false;
    int j = numVertices();
    for (int i = 0; i < j; i++) {
      if (this.simplexVectorW[i].equals(paramVector3f)) {
        bool = true;
      }
    }
    if (paramVector3f.equals(this.lastW)) {
      return true;
    }
    return bool;
  }
  
  public void backup_closest(Vector3f paramVector3f)
  {
    paramVector3f.set(this.cachedV);
  }
  
  public boolean emptySimplex()
  {
    return numVertices() == 0;
  }
  
  public void compute_points(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    updateClosestVectorAndPoints();
    paramVector3f1.set(this.cachedP1);
    paramVector3f2.set(this.cachedP2);
  }
  
  public int numVertices()
  {
    return this.numVertices;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.VoronoiSimplexSolverExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */