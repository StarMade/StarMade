package com.bulletphysics.collision.narrowphase;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Vector3f;

public class VoronoiSimplexSolver
  extends SimplexSolverInterface
{
  protected final ObjectPool<SubSimplexClosestResult> subsimplexResultsPool = ObjectPool.get(SubSimplexClosestResult.class);
  private static final int VORONOI_SIMPLEX_MAX_VERTS = 5;
  private static final int VERTA = 0;
  private static final int VERTB = 1;
  private static final int VERTC = 2;
  private static final int VERTD = 3;
  public int numVertices;
  public final Vector3f[] simplexVectorW = new Vector3f[5];
  public final Vector3f[] simplexPointsP = new Vector3f[5];
  public final Vector3f[] simplexPointsQ = new Vector3f[5];
  public final Vector3f cachedP1 = new Vector3f();
  public final Vector3f cachedP2 = new Vector3f();
  public final Vector3f cachedV = new Vector3f();
  public final Vector3f lastW = new Vector3f();
  public boolean cachedValidClosest;
  public final SubSimplexClosestResult cachedBC = new SubSimplexClosestResult();
  public boolean needsUpdate;
  
  public VoronoiSimplexSolver()
  {
    for (int local_i = 0; local_i < 5; local_i++)
    {
      this.simplexVectorW[local_i] = new Vector3f();
      this.simplexPointsP[local_i] = new Vector3f();
      this.simplexPointsQ[local_i] = new Vector3f();
    }
  }
  
  public void removeVertex(int index)
  {
    assert (this.numVertices > 0);
    this.numVertices -= 1;
    this.simplexVectorW[index].set(this.simplexVectorW[this.numVertices]);
    this.simplexPointsP[index].set(this.simplexPointsP[this.numVertices]);
    this.simplexPointsQ[index].set(this.simplexPointsQ[this.numVertices]);
  }
  
  public void reduceVertices(UsageBitfield usedVerts)
  {
    if ((numVertices() >= 4) && (!usedVerts.usedVertexD)) {
      removeVertex(3);
    }
    if ((numVertices() >= 3) && (!usedVerts.usedVertexC)) {
      removeVertex(2);
    }
    if ((numVertices() >= 2) && (!usedVerts.usedVertexB)) {
      removeVertex(1);
    }
    if ((numVertices() >= 1) && (!usedVerts.usedVertexA)) {
      removeVertex(0);
    }
  }
  
  public boolean updateClosestVectorAndPoints()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (this.needsUpdate)
      {
        this.cachedBC.reset();
        this.needsUpdate = false;
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
          Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
          Vector3f from = this.simplexVectorW[0];
          Vector3f local_to = this.simplexVectorW[1];
          Vector3f nearest = localStack.get$javax$vecmath$Vector3f();
          Vector3f local_p = localStack.get$javax$vecmath$Vector3f();
          local_p.set(0.0F, 0.0F, 0.0F);
          Vector3f diff = localStack.get$javax$vecmath$Vector3f();
          diff.sub(local_p, from);
          Vector3f local_v = localStack.get$javax$vecmath$Vector3f();
          local_v.sub(local_to, from);
          float local_t = local_v.dot(diff);
          if (local_t > 0.0F)
          {
            float dotVV = local_v.dot(local_v);
            if (local_t < dotVV)
            {
              local_t /= dotVV;
              tmp.scale(local_t, local_v);
              diff.sub(tmp);
              this.cachedBC.usedVertices.usedVertexA = true;
              this.cachedBC.usedVertices.usedVertexB = true;
            }
            else
            {
              local_t = 1.0F;
              diff.sub(local_v);
              this.cachedBC.usedVertices.usedVertexB = true;
            }
          }
          else
          {
            local_t = 0.0F;
            this.cachedBC.usedVertices.usedVertexA = true;
          }
          this.cachedBC.setBarycentricCoordinates(1.0F - local_t, local_t, 0.0F, 0.0F);
          tmp.scale(local_t, local_v);
          nearest.add(from, tmp);
          tmp.sub(this.simplexPointsP[1], this.simplexPointsP[0]);
          tmp.scale(local_t);
          this.cachedP1.add(this.simplexPointsP[0], tmp);
          tmp.sub(this.simplexPointsQ[1], this.simplexPointsQ[0]);
          tmp.scale(local_t);
          this.cachedP2.add(this.simplexPointsQ[0], tmp);
          this.cachedV.sub(this.cachedP1, this.cachedP2);
          reduceVertices(this.cachedBC.usedVertices);
          this.cachedValidClosest = this.cachedBC.isValid();
          break;
        case 3: 
          Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
          Vector3f from = localStack.get$javax$vecmath$Vector3f();
          Vector3f local_to = localStack.get$javax$vecmath$Vector3f();
          Vector3f nearest = localStack.get$javax$vecmath$Vector3f();
          nearest.set(0.0F, 0.0F, 0.0F);
          Vector3f local_p = this.simplexVectorW[0];
          Vector3f diff = this.simplexVectorW[1];
          Vector3f local_v = this.simplexVectorW[2];
          closestPtPointTriangle(nearest, local_p, diff, local_v, this.cachedBC);
          tmp.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
          from.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
          local_to.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
          VectorUtil.add(this.cachedP1, tmp, from, local_to);
          tmp.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
          from.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
          local_to.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
          VectorUtil.add(this.cachedP2, tmp, from, local_to);
          this.cachedV.sub(this.cachedP1, this.cachedP2);
          reduceVertices(this.cachedBC.usedVertices);
          this.cachedValidClosest = this.cachedBC.isValid();
          break;
        case 4: 
          Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
          Vector3f from = localStack.get$javax$vecmath$Vector3f();
          Vector3f local_to = localStack.get$javax$vecmath$Vector3f();
          Vector3f nearest = localStack.get$javax$vecmath$Vector3f();
          Vector3f local_p = localStack.get$javax$vecmath$Vector3f();
          local_p.set(0.0F, 0.0F, 0.0F);
          Vector3f diff = this.simplexVectorW[0];
          Vector3f local_v = this.simplexVectorW[1];
          Vector3f local_t = this.simplexVectorW[2];
          Vector3f dotVV = this.simplexVectorW[3];
          boolean hasSeperation = closestPtPointTetrahedron(local_p, diff, local_v, local_t, dotVV, this.cachedBC);
          if (hasSeperation)
          {
            tmp.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
            from.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
            local_to.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
            nearest.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsP[3]);
            VectorUtil.add(this.cachedP1, tmp, from, local_to, nearest);
            tmp.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
            from.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
            local_to.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
            nearest.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsQ[3]);
            VectorUtil.add(this.cachedP2, tmp, from, local_to, nearest);
            this.cachedV.sub(this.cachedP1, this.cachedP2);
            reduceVertices(this.cachedBC.usedVertices);
          }
          else
          {
            if (this.cachedBC.degenerate)
            {
              this.cachedValidClosest = false;
              break;
            }
            this.cachedValidClosest = true;
            this.cachedV.set(0.0F, 0.0F, 0.0F);
            break;
          }
          this.cachedValidClosest = this.cachedBC.isValid();
          break;
        default: 
          this.cachedValidClosest = false;
        }
      }
      return this.cachedValidClosest;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean closestPtPointTriangle(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, SubSimplexClosestResult arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      result.usedVertices.reset();
      Vector3f local_ab = localStack.get$javax$vecmath$Vector3f();
      local_ab.sub(local_b, local_a);
      Vector3f local_ac = localStack.get$javax$vecmath$Vector3f();
      local_ac.sub(local_c, local_a);
      Vector3f local_ap = localStack.get$javax$vecmath$Vector3f();
      local_ap.sub(local_p, local_a);
      float local_d1 = local_ab.dot(local_ap);
      float local_d2 = local_ac.dot(local_ap);
      if ((local_d1 <= 0.0F) && (local_d2 <= 0.0F))
      {
        result.closestPointOnSimplex.set(local_a);
        result.usedVertices.usedVertexA = true;
        result.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
        return true;
      }
      Vector3f local_bp = localStack.get$javax$vecmath$Vector3f();
      local_bp.sub(local_p, local_b);
      float local_d3 = local_ab.dot(local_bp);
      float local_d4 = local_ac.dot(local_bp);
      if ((local_d3 >= 0.0F) && (local_d4 <= local_d3))
      {
        result.closestPointOnSimplex.set(local_b);
        result.usedVertices.usedVertexB = true;
        result.setBarycentricCoordinates(0.0F, 1.0F, 0.0F, 0.0F);
        return true;
      }
      float local_vc = local_d1 * local_d4 - local_d3 * local_d2;
      if ((local_vc <= 0.0F) && (local_d1 >= 0.0F) && (local_d3 <= 0.0F))
      {
        float local_v = local_d1 / (local_d1 - local_d3);
        result.closestPointOnSimplex.scaleAdd(local_v, local_ab, local_a);
        result.usedVertices.usedVertexA = true;
        result.usedVertices.usedVertexB = true;
        result.setBarycentricCoordinates(1.0F - local_v, local_v, 0.0F, 0.0F);
        return true;
      }
      Vector3f local_v = localStack.get$javax$vecmath$Vector3f();
      local_v.sub(local_p, local_c);
      float local_d5 = local_ab.dot(local_v);
      float local_d6 = local_ac.dot(local_v);
      if ((local_d6 >= 0.0F) && (local_d5 <= local_d6))
      {
        result.closestPointOnSimplex.set(local_c);
        result.usedVertices.usedVertexC = true;
        result.setBarycentricCoordinates(0.0F, 0.0F, 1.0F, 0.0F);
        return true;
      }
      float local_vb = local_d5 * local_d2 - local_d1 * local_d6;
      if ((local_vb <= 0.0F) && (local_d2 >= 0.0F) && (local_d6 <= 0.0F))
      {
        float local_w = local_d2 / (local_d2 - local_d6);
        result.closestPointOnSimplex.scaleAdd(local_w, local_ac, local_a);
        result.usedVertices.usedVertexA = true;
        result.usedVertices.usedVertexC = true;
        result.setBarycentricCoordinates(1.0F - local_w, 0.0F, local_w, 0.0F);
        return true;
      }
      float local_w = local_d3 * local_d6 - local_d5 * local_d4;
      if ((local_w <= 0.0F) && (local_d4 - local_d3 >= 0.0F) && (local_d5 - local_d6 >= 0.0F))
      {
        float local_w1 = (local_d4 - local_d3) / (local_d4 - local_d3 + (local_d5 - local_d6));
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.sub(local_c, local_b);
        result.closestPointOnSimplex.scaleAdd(local_w1, tmp, local_b);
        result.usedVertices.usedVertexB = true;
        result.usedVertices.usedVertexC = true;
        result.setBarycentricCoordinates(0.0F, 1.0F - local_w1, local_w1, 0.0F);
        return true;
      }
      float local_w1 = 1.0F / (local_w + local_vb + local_vc);
      float tmp = local_vb * local_w1;
      float local_w2 = local_vc * local_w1;
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      tmp1.scale(tmp, local_ab);
      tmp2.scale(local_w2, local_ac);
      VectorUtil.add(result.closestPointOnSimplex, local_a, tmp1, tmp2);
      result.usedVertices.usedVertexA = true;
      result.usedVertices.usedVertexB = true;
      result.usedVertices.usedVertexC = true;
      result.setBarycentricCoordinates(1.0F - tmp - local_w2, tmp, local_w2, 0.0F);
      return true;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static int pointOutsideOfPlane(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      normal.sub(local_b, local_a);
      tmp.sub(local_c, local_a);
      normal.cross(normal, tmp);
      tmp.sub(local_p, local_a);
      float signp = tmp.dot(normal);
      tmp.sub(local_d, local_a);
      float signd = tmp.dot(normal);
      if (signd * signd < 9.999999E-009F) {
        return -1;
      }
      return signp * signd < 0.0F ? 1 : 0;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  /* Error */
  public boolean closestPtPointTetrahedron(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, SubSimplexClosestResult arg6)
  {
    // Byte code:
    //   0: invokestatic 118	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
    //   3: astore 17
    //   5: aload 17
    //   7: invokevirtual 121	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
    //   10: aload_0
    //   11: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
    //   14: invokevirtual 249	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
    //   17: checkcast 7	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult
    //   20: astore 7
    //   22: aload 7
    //   24: invokevirtual 126	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:reset	()V
    //   27: aload 17
    //   29: invokevirtual 143	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
    //   32: astore 8
    //   34: aload 17
    //   36: invokevirtual 143	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
    //   39: astore 9
    //   41: aload 6
    //   43: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   46: aload_1
    //   47: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   50: aload 6
    //   52: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   55: invokevirtual 207	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
    //   58: aload 6
    //   60: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   63: iconst_1
    //   64: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   67: aload 6
    //   69: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   72: iconst_1
    //   73: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   76: aload 6
    //   78: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   81: iconst_1
    //   82: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   85: aload 6
    //   87: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   90: iconst_1
    //   91: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
    //   94: aload_1
    //   95: aload_2
    //   96: aload_3
    //   97: aload 4
    //   99: aload 5
    //   101: invokestatic 251	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
    //   104: istore 10
    //   106: aload_1
    //   107: aload_2
    //   108: aload 4
    //   110: aload 5
    //   112: aload_3
    //   113: invokestatic 251	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
    //   116: istore 11
    //   118: aload_1
    //   119: aload_2
    //   120: aload 5
    //   122: aload_3
    //   123: aload 4
    //   125: invokestatic 251	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
    //   128: istore 12
    //   130: aload_1
    //   131: aload_3
    //   132: aload 5
    //   134: aload 4
    //   136: aload_2
    //   137: invokestatic 251	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
    //   140: istore 13
    //   142: iload 10
    //   144: iflt +18 -> 162
    //   147: iload 11
    //   149: iflt +13 -> 162
    //   152: iload 12
    //   154: iflt +8 -> 162
    //   157: iload 13
    //   159: ifge +29 -> 188
    //   162: aload 6
    //   164: iconst_1
    //   165: putfield 190	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:degenerate	Z
    //   168: iconst_0
    //   169: istore 14
    //   171: aload_0
    //   172: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
    //   175: aload 7
    //   177: invokevirtual 255	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
    //   180: iload 14
    //   182: aload 17
    //   184: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
    //   187: ireturn
    //   188: iload 10
    //   190: ifne +38 -> 228
    //   193: iload 11
    //   195: ifne +33 -> 228
    //   198: iload 12
    //   200: ifne +28 -> 228
    //   203: iload 13
    //   205: ifne +23 -> 228
    //   208: iconst_0
    //   209: istore 14
    //   211: aload_0
    //   212: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
    //   215: aload 7
    //   217: invokevirtual 255	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
    //   220: iload 14
    //   222: aload 17
    //   224: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
    //   227: ireturn
    //   228: ldc_w 256
    //   231: fstore 14
    //   233: iload 10
    //   235: ifeq +147 -> 382
    //   238: aload_0
    //   239: aload_1
    //   240: aload_2
    //   241: aload_3
    //   242: aload 4
    //   244: aload 7
    //   246: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
    //   249: pop
    //   250: aload 9
    //   252: aload 7
    //   254: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   257: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   260: aload 8
    //   262: aload 9
    //   264: aload_1
    //   265: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
    //   268: aload 8
    //   270: aload 8
    //   272: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
    //   275: fstore 15
    //   277: fload 15
    //   279: fload 14
    //   281: fcmpg
    //   282: ifge +100 -> 382
    //   285: fload 15
    //   287: fstore 14
    //   289: aload 6
    //   291: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   294: aload 9
    //   296: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   299: aload 6
    //   301: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   304: invokevirtual 207	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
    //   307: aload 6
    //   309: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   312: aload 7
    //   314: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   317: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   320: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   323: aload 6
    //   325: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   328: aload 7
    //   330: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   333: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   336: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   339: aload 6
    //   341: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   344: aload 7
    //   346: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   349: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   352: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   355: aload 6
    //   357: aload 7
    //   359: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   362: iconst_0
    //   363: faload
    //   364: aload 7
    //   366: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   369: iconst_1
    //   370: faload
    //   371: aload 7
    //   373: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   376: iconst_2
    //   377: faload
    //   378: fconst_0
    //   379: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
    //   382: iload 11
    //   384: ifeq +148 -> 532
    //   387: aload_0
    //   388: aload_1
    //   389: aload_2
    //   390: aload 4
    //   392: aload 5
    //   394: aload 7
    //   396: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
    //   399: pop
    //   400: aload 9
    //   402: aload 7
    //   404: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   407: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   410: aload 8
    //   412: aload 9
    //   414: aload_1
    //   415: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
    //   418: aload 8
    //   420: aload 8
    //   422: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
    //   425: fstore 15
    //   427: fload 15
    //   429: fload 14
    //   431: fcmpg
    //   432: ifge +100 -> 532
    //   435: fload 15
    //   437: fstore 14
    //   439: aload 6
    //   441: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   444: aload 9
    //   446: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   449: aload 6
    //   451: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   454: invokevirtual 207	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
    //   457: aload 6
    //   459: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   462: aload 7
    //   464: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   467: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   470: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   473: aload 6
    //   475: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   478: aload 7
    //   480: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   483: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   486: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   489: aload 6
    //   491: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   494: aload 7
    //   496: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   499: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   502: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
    //   505: aload 6
    //   507: aload 7
    //   509: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   512: iconst_0
    //   513: faload
    //   514: fconst_0
    //   515: aload 7
    //   517: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   520: iconst_1
    //   521: faload
    //   522: aload 7
    //   524: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   527: iconst_2
    //   528: faload
    //   529: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
    //   532: iload 12
    //   534: ifeq +147 -> 681
    //   537: aload_0
    //   538: aload_1
    //   539: aload_2
    //   540: aload 5
    //   542: aload_3
    //   543: aload 7
    //   545: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
    //   548: pop
    //   549: aload 9
    //   551: aload 7
    //   553: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   556: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   559: aload 8
    //   561: aload 9
    //   563: aload_1
    //   564: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
    //   567: aload 8
    //   569: aload 8
    //   571: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
    //   574: fstore 15
    //   576: fload 15
    //   578: fload 14
    //   580: fcmpg
    //   581: ifge +100 -> 681
    //   584: fload 15
    //   586: fstore 14
    //   588: aload 6
    //   590: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   593: aload 9
    //   595: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   598: aload 6
    //   600: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   603: invokevirtual 207	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
    //   606: aload 6
    //   608: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   611: aload 7
    //   613: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   616: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   619: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   622: aload 6
    //   624: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   627: aload 7
    //   629: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   632: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   635: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   638: aload 6
    //   640: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   643: aload 7
    //   645: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   648: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   651: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
    //   654: aload 6
    //   656: aload 7
    //   658: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   661: iconst_0
    //   662: faload
    //   663: aload 7
    //   665: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   668: iconst_2
    //   669: faload
    //   670: fconst_0
    //   671: aload 7
    //   673: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   676: iconst_1
    //   677: faload
    //   678: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
    //   681: iload 13
    //   683: ifeq +148 -> 831
    //   686: aload_0
    //   687: aload_1
    //   688: aload_3
    //   689: aload 5
    //   691: aload 4
    //   693: aload 7
    //   695: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
    //   698: pop
    //   699: aload 9
    //   701: aload 7
    //   703: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   706: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   709: aload 8
    //   711: aload 9
    //   713: aload_1
    //   714: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
    //   717: aload 8
    //   719: aload 8
    //   721: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
    //   724: fstore 15
    //   726: fload 15
    //   728: fload 14
    //   730: fcmpg
    //   731: ifge +100 -> 831
    //   734: fload 15
    //   736: fstore 14
    //   738: aload 6
    //   740: getfield 210	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
    //   743: aload 9
    //   745: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
    //   748: aload 6
    //   750: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   753: invokevirtual 207	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
    //   756: aload 6
    //   758: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   761: aload 7
    //   763: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   766: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   769: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   772: aload 6
    //   774: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   777: aload 7
    //   779: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   782: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   785: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   788: aload 6
    //   790: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   793: aload 7
    //   795: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   798: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   801: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
    //   804: aload 6
    //   806: fconst_0
    //   807: aload 7
    //   809: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   812: iconst_0
    //   813: faload
    //   814: aload 7
    //   816: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   819: iconst_2
    //   820: faload
    //   821: aload 7
    //   823: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
    //   826: iconst_1
    //   827: faload
    //   828: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
    //   831: aload 6
    //   833: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   836: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
    //   839: ifeq +56 -> 895
    //   842: aload 6
    //   844: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   847: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
    //   850: ifeq +45 -> 895
    //   853: aload 6
    //   855: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   858: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
    //   861: ifeq +34 -> 895
    //   864: aload 6
    //   866: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
    //   869: getfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
    //   872: ifeq +23 -> 895
    //   875: iconst_1
    //   876: istore 15
    //   878: aload_0
    //   879: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
    //   882: aload 7
    //   884: invokevirtual 255	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
    //   887: iload 15
    //   889: aload 17
    //   891: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
    //   894: ireturn
    //   895: iconst_1
    //   896: istore 15
    //   898: aload_0
    //   899: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
    //   902: aload 7
    //   904: invokevirtual 255	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
    //   907: iload 15
    //   909: aload 17
    //   911: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
    //   914: ireturn
    //   915: astore 16
    //   917: aload_0
    //   918: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
    //   921: aload 7
    //   923: invokevirtual 255	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
    //   926: aload 16
    //   928: athrow
    //   929: aload 17
    //   931: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
    //   934: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   10	919	0	this	VoronoiSimplexSolver
    //   10	919	1	local_p	Vector3f
    //   10	919	2	local_a	Vector3f
    //   10	919	3	local_b	Vector3f
    //   10	919	4	local_c	Vector3f
    //   10	919	5	local_d	Vector3f
    //   10	919	6	finalResult	SubSimplexClosestResult
    //   20	902	7	tempResult	SubSimplexClosestResult
    //   32	688	8	tmp	Vector3f
    //   39	705	9	local_q	Vector3f
    //   104	130	10	pointOutsideABC	int
    //   116	267	11	pointOutsideACD	int
    //   128	405	12	pointOutsideADB	int
    //   140	542	13	pointOutsideBDC	int
    //   169	52	14	bool	boolean
    //   231	506	14	bestSqDist	float
    //   275	11	15	sqDist	float
    //   425	11	15	sqDist	float
    //   574	11	15	sqDist	float
    //   724	184	15	sqDist	float
    //   915	12	16	localObject	Object
    //   3	927	17	localStack	.Stack
    // Exception table:
    //   from	to	target	type
    //   27	171	915	finally
    //   188	211	915	finally
    //   228	878	915	finally
    //   895	898	915	finally
    //   915	917	915	finally
    //   5	929	929	finally
  }
  
  public void reset()
  {
    this.cachedValidClosest = false;
    this.numVertices = 0;
    this.needsUpdate = true;
    this.lastW.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
    this.cachedBC.reset();
  }
  
  public void addVertex(Vector3f local_w, Vector3f local_p, Vector3f local_q)
  {
    this.lastW.set(local_w);
    this.needsUpdate = true;
    this.simplexVectorW[this.numVertices].set(local_w);
    this.simplexPointsP[this.numVertices].set(local_p);
    this.simplexPointsQ[this.numVertices].set(local_q);
    this.numVertices += 1;
  }
  
  public boolean closest(Vector3f local_v)
  {
    boolean succes = updateClosestVectorAndPoints();
    local_v.set(this.cachedV);
    return succes;
  }
  
  public float maxVertex()
  {
    int numverts = numVertices();
    float maxV = 0.0F;
    for (int local_i = 0; local_i < numverts; local_i++)
    {
      float curLen2 = this.simplexVectorW[local_i].lengthSquared();
      if (maxV < curLen2) {
        maxV = curLen2;
      }
    }
    return maxV;
  }
  
  public boolean fullSimplex()
  {
    return this.numVertices == 4;
  }
  
  public int getSimplex(Vector3f[] pBuf, Vector3f[] qBuf, Vector3f[] yBuf)
  {
    for (int local_i = 0; local_i < numVertices(); local_i++)
    {
      yBuf[local_i].set(this.simplexVectorW[local_i]);
      pBuf[local_i].set(this.simplexPointsP[local_i]);
      qBuf[local_i].set(this.simplexPointsQ[local_i]);
    }
    return numVertices();
  }
  
  public boolean inSimplex(Vector3f local_w)
  {
    boolean found = false;
    int numverts = numVertices();
    for (int local_i = 0; local_i < numverts; local_i++) {
      if (this.simplexVectorW[local_i].equals(local_w)) {
        found = true;
      }
    }
    if (local_w.equals(this.lastW)) {
      return true;
    }
    return found;
  }
  
  public void backup_closest(Vector3f local_v)
  {
    local_v.set(this.cachedV);
  }
  
  public boolean emptySimplex()
  {
    return numVertices() == 0;
  }
  
  public void compute_points(Vector3f local_p1, Vector3f local_p2)
  {
    updateClosestVectorAndPoints();
    local_p1.set(this.cachedP1);
    local_p2.set(this.cachedP2);
  }
  
  public int numVertices()
  {
    return this.numVertices;
  }
  
  public static class SubSimplexClosestResult
  {
    public final Vector3f closestPointOnSimplex = new Vector3f();
    public final VoronoiSimplexSolver.UsageBitfield usedVertices = new VoronoiSimplexSolver.UsageBitfield();
    public final float[] barycentricCoords = new float[4];
    public boolean degenerate;
    
    public void reset()
    {
      this.degenerate = false;
      setBarycentricCoordinates(0.0F, 0.0F, 0.0F, 0.0F);
      this.usedVertices.reset();
    }
    
    public boolean isValid()
    {
      boolean valid = (this.barycentricCoords[0] >= 0.0F) && (this.barycentricCoords[1] >= 0.0F) && (this.barycentricCoords[2] >= 0.0F) && (this.barycentricCoords[3] >= 0.0F);
      return valid;
    }
    
    public void setBarycentricCoordinates(float local_a, float local_b, float local_c, float local_d)
    {
      this.barycentricCoords[0] = local_a;
      this.barycentricCoords[1] = local_b;
      this.barycentricCoords[2] = local_c;
      this.barycentricCoords[3] = local_d;
    }
  }
  
  public static class UsageBitfield
  {
    public boolean usedVertexA;
    public boolean usedVertexB;
    public boolean usedVertexC;
    public boolean usedVertexD;
    
    public void reset()
    {
      this.usedVertexA = false;
      this.usedVertexB = false;
      this.usedVertexC = false;
      this.usedVertexD = false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */