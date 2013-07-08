package com.bulletphysics.collision.narrowphase;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.QuaternionUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ArrayPool;
import com.bulletphysics.util.ObjectStackList;
import java.util.Arrays;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class GjkEpaSolver
{
  protected final ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
  protected final ObjectStackList<Mkv> stackMkv = new ObjectStackList(Mkv.class);
  protected final ObjectStackList<He> stackHe = new ObjectStackList(He.class);
  protected final ObjectStackList<Face> stackFace = new ObjectStackList(Face.class);
  private static final float cstInf = 3.4028235E+38F;
  private static final float cstPi = 3.141593F;
  private static final float cst2Pi = 6.283186F;
  private static final int GJK_maxiterations = 128;
  private static final int GJK_hashsize = 64;
  private static final int GJK_hashmask = 63;
  private static final float GJK_insimplex_eps = 1.0E-004F;
  private static final float GJK_sqinsimplex_eps = 9.999999E-009F;
  private static final int EPA_maxiterations = 256;
  private static final float EPA_inface_eps = 0.01F;
  private static final float EPA_accuracy = 0.001F;
  private static int[] mod3 = { 0, 1, 2, 0, 1 };
  private static final int[][] tetrahedron_fidx = { { 2, 1, 0 }, { 3, 0, 1 }, { 3, 1, 2 }, { 3, 2, 0 } };
  private static final int[][] tetrahedron_eidx = { { 0, 0, 2, 1 }, { 0, 1, 1, 1 }, { 0, 2, 3, 1 }, { 1, 0, 3, 2 }, { 2, 0, 1, 2 }, { 3, 0, 2, 2 } };
  private static final int[][] hexahedron_fidx = { { 2, 0, 4 }, { 4, 1, 2 }, { 1, 4, 0 }, { 0, 3, 1 }, { 0, 2, 3 }, { 1, 3, 2 } };
  private static final int[][] hexahedron_eidx = { { 0, 0, 4, 0 }, { 0, 1, 2, 1 }, { 0, 2, 1, 2 }, { 1, 1, 5, 2 }, { 1, 0, 2, 0 }, { 2, 2, 3, 2 }, { 3, 1, 5, 0 }, { 3, 0, 4, 2 }, { 5, 1, 4, 1 } };
  private GJK gjk = new GJK();
  
  protected void pushStack()
  {
    this.stackMkv.push();
    this.stackHe.push();
    this.stackFace.push();
  }
  
  protected void popStack()
  {
    this.stackMkv.pop();
    this.stackHe.pop();
    this.stackFace.pop();
  }
  
  public boolean collide(ConvexShape shape0, Transform wtrs0, ConvexShape shape1, Transform wtrs1, float radialmargin, Results results)
  {
    results.witnesses[0].set(0.0F, 0.0F, 0.0F);
    results.witnesses[1].set(0.0F, 0.0F, 0.0F);
    results.normal.set(0.0F, 0.0F, 0.0F);
    results.depth = 0.0F;
    results.status = ResultsStatus.Separated;
    results.epa_iterations = 0;
    results.gjk_iterations = 0;
    this.gjk.init(wtrs0.basis, wtrs0.origin, shape0, wtrs1.basis, wtrs1.origin, shape1, radialmargin + 0.001F);
    try
    {
      boolean collide = this.gjk.SearchOrigin();
      results.gjk_iterations = (this.gjk.iterations + 1);
      if (collide)
      {
        epa = new EPA(this.gjk);
        float local_pd = epa.EvaluatePD();
        results.epa_iterations = (epa.iterations + 1);
        if (local_pd > 0.0F)
        {
          results.status = ResultsStatus.Penetrating;
          results.normal.set(epa.normal);
          results.depth = local_pd;
          results.witnesses[0].set(epa.nearest[0]);
          results.witnesses[1].set(epa.nearest[1]);
          boolean bool1 = true;
          return bool1;
        }
        if (epa.failed) {
          results.status = ResultsStatus.EPA_Failed;
        }
      }
      else if (this.gjk.failed)
      {
        results.status = ResultsStatus.GJK_Failed;
      }
      EPA epa = 0;
      return epa;
    }
    finally
    {
      this.gjk.destroy();
    }
  }
  
  protected class EPA
  {
    public GjkEpaSolver.GJK gjk;
    public GjkEpaSolver.Face root;
    public int nfaces;
    public int iterations;
    public final Vector3f[][] features = new Vector3f[2][3];
    public final Vector3f[] nearest = { new Vector3f(), new Vector3f() };
    public final Vector3f normal = new Vector3f();
    public float depth;
    public boolean failed;
    
    public EPA(GjkEpaSolver.GJK pgjk)
    {
      for (int local_i = 0; local_i < this.features.length; local_i++) {
        for (int local_j = 0; local_j < this.features[local_i].length; local_j++) {
          this.features[local_i][local_j] = new Vector3f();
        }
      }
      this.gjk = pgjk;
    }
    
    public Vector3f GetCoordinates(GjkEpaSolver.Face arg1, Vector3f arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
        Vector3f local_o = localStack.get$javax$vecmath$Vector3f();
        local_o.scale(-face.field_1709, face.field_1708);
        float[] local_a = (float[])GjkEpaSolver.this.floatArrays.getFixed(3);
        tmp1.sub(face.field_1705[0].field_2116, local_o);
        tmp2.sub(face.field_1705[1].field_2116, local_o);
        tmp.cross(tmp1, tmp2);
        local_a[0] = tmp.length();
        tmp1.sub(face.field_1705[1].field_2116, local_o);
        tmp2.sub(face.field_1705[2].field_2116, local_o);
        tmp.cross(tmp1, tmp2);
        local_a[1] = tmp.length();
        tmp1.sub(face.field_1705[2].field_2116, local_o);
        tmp2.sub(face.field_1705[0].field_2116, local_o);
        tmp.cross(tmp1, tmp2);
        local_a[2] = tmp.length();
        float local_sm = local_a[0] + local_a[1] + local_a[2];
        out.set(local_a[1], local_a[2], local_a[0]);
        out.scale(1.0F / (local_sm > 0.0F ? local_sm : 1.0F));
        GjkEpaSolver.this.floatArrays.release(local_a);
        return out;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public GjkEpaSolver.Face FindBest()
    {
      GjkEpaSolver.Face local_bf = null;
      if (this.root != null)
      {
        GjkEpaSolver.Face local_cf = this.root;
        float local_bd = 3.4028235E+38F;
        do
        {
          if (local_cf.field_1709 < local_bd)
          {
            local_bd = local_cf.field_1709;
            local_bf = local_cf;
          }
        } while (null != (local_cf = local_cf.next));
      }
      return local_bf;
    }
    
    public boolean Set(GjkEpaSolver.Face arg1, GjkEpaSolver.Mkv arg2, GjkEpaSolver.Mkv arg3, GjkEpaSolver.Mkv arg4)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
        Vector3f nrm = localStack.get$javax$vecmath$Vector3f();
        tmp1.sub(local_b.field_2116, local_a.field_2116);
        tmp2.sub(local_c.field_2116, local_a.field_2116);
        nrm.cross(tmp1, tmp2);
        float len = nrm.length();
        tmp1.cross(local_a.field_2116, local_b.field_2116);
        tmp2.cross(local_b.field_2116, local_c.field_2116);
        tmp3.cross(local_c.field_2116, local_a.field_2116);
        boolean valid = (tmp1.dot(nrm) >= -0.01F) && (tmp2.dot(nrm) >= -0.01F) && (tmp3.dot(nrm) >= -0.01F);
        local_f.field_1705[0] = local_a;
        local_f.field_1705[1] = local_b;
        local_f.field_1705[2] = local_c;
        local_f.mark = 0;
        local_f.field_1708.scale(1.0F / (len > 0.0F ? len : 3.4028235E+38F), nrm);
        local_f.field_1709 = Math.max(0.0F, -local_f.field_1708.dot(local_a.field_2116));
        return valid;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public GjkEpaSolver.Face NewFace(GjkEpaSolver.Mkv local_a, GjkEpaSolver.Mkv local_b, GjkEpaSolver.Mkv local_c)
    {
      GjkEpaSolver.Face local_pf = (GjkEpaSolver.Face)GjkEpaSolver.this.stackFace.get();
      if (Set(local_pf, local_a, local_b, local_c))
      {
        if (this.root != null) {
          this.root.prev = local_pf;
        }
        local_pf.prev = null;
        local_pf.next = this.root;
        this.root = local_pf;
        this.nfaces += 1;
      }
      else
      {
        local_pf.prev = (local_pf.next = null);
      }
      return local_pf;
    }
    
    public void Detach(GjkEpaSolver.Face face)
    {
      if ((face.prev != null) || (face.next != null))
      {
        this.nfaces -= 1;
        if (face == this.root)
        {
          this.root = face.next;
          this.root.prev = null;
        }
        else if (face.next == null)
        {
          face.prev.next = null;
        }
        else
        {
          face.prev.next = face.next;
          face.next.prev = face.prev;
        }
        face.prev = (face.next = null);
      }
    }
    
    public void Link(GjkEpaSolver.Face local_f0, int local_e0, GjkEpaSolver.Face local_f1, int local_e1)
    {
      local_f0.field_1706[local_e0] = local_f1;
      local_f1.field_1707[local_e1] = local_e0;
      local_f1.field_1706[local_e1] = local_f0;
      local_f0.field_1707[local_e0] = local_e1;
    }
    
    public GjkEpaSolver.Mkv Support(Vector3f local_w)
    {
      GjkEpaSolver.Mkv local_v = (GjkEpaSolver.Mkv)GjkEpaSolver.this.stackMkv.get();
      this.gjk.Support(local_w, local_v);
      return local_v;
    }
    
    public int BuildHorizon(int markid, GjkEpaSolver.Mkv local_w, GjkEpaSolver.Face local_f, int local_e, GjkEpaSolver.Face[] local_cf, GjkEpaSolver.Face[] local_ff)
    {
      int local_ne = 0;
      if (local_f.mark != markid)
      {
        int local_e1 = GjkEpaSolver.mod3[(local_e + 1)];
        if (local_f.field_1708.dot(local_w.field_2116) + local_f.field_1709 > 0.0F)
        {
          GjkEpaSolver.Face local_nf = NewFace(local_f.field_1705[local_e1], local_f.field_1705[local_e], local_w);
          Link(local_nf, 0, local_f, local_e);
          if (local_cf[0] != null) {
            Link(local_cf[0], 1, local_nf, 2);
          } else {
            local_ff[0] = local_nf;
          }
          local_cf[0] = local_nf;
          local_ne = 1;
        }
        else
        {
          int local_nf = GjkEpaSolver.mod3[(local_e + 2)];
          Detach(local_f);
          local_f.mark = markid;
          local_ne += BuildHorizon(markid, local_w, local_f.field_1706[local_e1], local_f.field_1707[local_e1], local_cf, local_ff);
          local_ne += BuildHorizon(markid, local_w, local_f.field_1706[local_nf], local_f.field_1707[local_nf], local_cf, local_ff);
        }
      }
      return local_ne;
    }
    
    public float EvaluatePD()
    {
      return EvaluatePD(0.001F);
    }
    
    /* Error */
    public float EvaluatePD(float arg1)
    {
      // Byte code:
      //   0: invokestatic 68	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
      //   3: astore 15
      //   5: aload 15
      //   7: invokevirtual 71	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
      //   10: aload_0
      //   11: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
      //   14: invokevirtual 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver:pushStack	()V
      //   17: aload 15
      //   19: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
      //   22: astore_2
      //   23: aconst_null
      //   24: astore_3
      //   25: iconst_1
      //   26: istore 4
      //   28: aload_0
      //   29: ldc 249
      //   31: putfield 251	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
      //   34: aload_0
      //   35: getfield 53	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:normal	Ljavax/vecmath/Vector3f;
      //   38: fconst_0
      //   39: fconst_0
      //   40: fconst_0
      //   41: invokevirtual 120	javax/vecmath/Vector3f:set	(FFF)V
      //   44: aload_0
      //   45: aconst_null
      //   46: putfield 144	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:root	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
      //   49: aload_0
      //   50: iconst_0
      //   51: putfield 193	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nfaces	I
      //   54: aload_0
      //   55: iconst_0
      //   56: putfield 253	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
      //   59: aload_0
      //   60: iconst_0
      //   61: putfield 255	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:failed	Z
      //   64: aload_0
      //   65: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
      //   68: invokevirtual 259	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:EncloseOrigin	()Z
      //   71: ifeq +289 -> 360
      //   74: aconst_null
      //   75: checkcast 261	[[I
      //   78: astore 5
      //   80: iconst_0
      //   81: istore 6
      //   83: iconst_0
      //   84: istore 7
      //   86: aconst_null
      //   87: checkcast 261	[[I
      //   90: astore 8
      //   92: iconst_0
      //   93: istore 9
      //   95: iconst_0
      //   96: istore 10
      //   98: iconst_5
      //   99: anewarray 16	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv
      //   102: astore 11
      //   104: bipush 6
      //   106: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
      //   109: astore 12
      //   111: aload_0
      //   112: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
      //   115: getfield 264	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:order	I
      //   118: lookupswitch	default:+76->194, 3:+26->144, 4:+52->170
      //   145: aconst_null
      //   146: fconst_2
      //   147: astore 5
      //   149: iconst_0
      //   150: istore 6
      //   152: iconst_4
      //   153: istore 7
      //   155: invokestatic 272	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$200	()[[I
      //   158: astore 8
      //   160: iconst_0
      //   161: istore 9
      //   163: bipush 6
      //   165: istore 10
      //   167: goto +27 -> 194
      //   170: invokestatic 275	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$300	()[[I
      //   173: astore 5
      //   175: iconst_0
      //   176: istore 6
      //   178: bipush 6
      //   180: istore 7
      //   182: invokestatic 278	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$400	()[[I
      //   185: astore 8
      //   187: iconst_0
      //   188: istore 9
      //   190: bipush 9
      //   192: istore 10
      //   194: iconst_0
      //   195: istore 13
      //   197: iload 13
      //   199: aload_0
      //   200: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
      //   203: getfield 264	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:order	I
      //   206: if_icmpgt +39 -> 245
      //   209: aload 11
      //   211: iload 13
      //   213: new 16	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv
      //   216: dup
      //   217: invokespecial 279	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:<init>	()V
      //   220: aastore
      //   221: aload 11
      //   223: iload 13
      //   225: aaload
      //   226: aload_0
      //   227: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
      //   230: getfield 282	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:simplex	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
      //   233: iload 13
      //   235: aaload
      //   236: invokevirtual 285	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:set	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;)V
      //   239: iinc 13 1
      //   242: goto -45 -> 197
      //   245: iconst_0
      //   246: istore 13
      //   248: iload 13
      //   250: iload 7
      //   252: if_icmpge +51 -> 303
      //   255: aload 12
      //   257: iload 13
      //   259: aload_0
      //   260: aload 11
      //   262: aload 5
      //   264: iload 6
      //   266: aaload
      //   267: iconst_0
      //   268: iaload
      //   269: aaload
      //   270: aload 11
      //   272: aload 5
      //   274: iload 6
      //   276: aaload
      //   277: iconst_1
      //   278: iaload
      //   279: aaload
      //   280: aload 11
      //   282: aload 5
      //   284: iload 6
      //   286: aaload
      //   287: iconst_2
      //   288: iaload
      //   289: aaload
      //   290: invokevirtual 228	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:NewFace	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;)Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
      //   293: aastore
      //   294: iinc 13 1
      //   297: iinc 6 1
      //   300: goto -52 -> 248
      //   303: iconst_0
      //   304: istore 13
      //   306: iload 13
      //   308: iload 10
      //   310: if_icmpge +50 -> 360
      //   313: aload_0
      //   314: aload 12
      //   316: aload 8
      //   318: iload 9
      //   320: aaload
      //   321: iconst_0
      //   322: iaload
      //   323: aaload
      //   324: aload 8
      //   326: iload 9
      //   328: aaload
      //   329: iconst_1
      //   330: iaload
      //   331: aload 12
      //   333: aload 8
      //   335: iload 9
      //   337: aaload
      //   338: iconst_2
      //   339: iaload
      //   340: aaload
      //   341: aload 8
      //   343: iload 9
      //   345: aaload
      //   346: iconst_3
      //   347: iaload
      //   348: invokevirtual 230	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Link	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I)V
      //   351: iinc 13 1
      //   354: iinc 9 1
      //   357: goto -51 -> 306
      //   360: iconst_0
      //   361: aload_0
      //   362: getfield 193	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nfaces	I
      //   365: if_icmpne +24 -> 389
      //   368: aload_0
      //   369: getfield 251	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
      //   372: fstore 5
      //   374: aload_0
      //   375: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
      //   378: invokevirtual 288	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
      //   381: fload 5
      //   383: aload 15
      //   385: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
      //   388: freturn
      //   389: aload_0
      //   390: getfield 253	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
      //   393: sipush 256
      //   396: if_icmpge +185 -> 581
      //   399: aload_0
      //   400: invokevirtual 290	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:FindBest	()Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
      //   403: astore 5
      //   405: aload 5
      //   407: ifnull +174 -> 581
      //   410: aload_2
      //   411: aload 5
      //   413: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1708	Ljavax/vecmath/Vector3f;
      //   416: invokevirtual 294	javax/vecmath/Vector3f:negate	(Ljavax/vecmath/Tuple3f;)V
      //   419: aload_0
      //   420: aload_2
      //   421: invokevirtual 296	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Support	(Ljavax/vecmath/Vector3f;)Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
      //   424: astore 6
      //   426: aload 5
      //   428: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1708	Ljavax/vecmath/Vector3f;
      //   431: aload 6
      //   433: getfield 104	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:field_2116	Ljavax/vecmath/Vector3f;
      //   436: invokevirtual 157	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
      //   439: aload 5
      //   441: getfield 78	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1709	F
      //   444: fadd
      //   445: fstore 7
      //   447: aload 5
      //   449: astore_3
      //   450: fload 7
      //   452: fload_1
      //   453: fneg
      //   454: fcmpg
      //   455: ifge +126 -> 581
      //   458: iconst_1
      //   459: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
      //   462: dup
      //   463: iconst_0
      //   464: aconst_null
      //   465: aastore
      //   466: astore 8
      //   468: iconst_1
      //   469: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
      //   472: dup
      //   473: iconst_0
      //   474: aconst_null
      //   475: aastore
      //   476: astore 9
      //   478: iconst_0
      //   479: istore 10
      //   481: aload_0
      //   482: aload 5
      //   484: invokevirtual 233	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Detach	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;)V
      //   487: aload 5
      //   489: iinc 4 1
      //   492: iload 4
      //   494: putfield 161	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:mark	I
      //   497: iconst_0
      //   498: istore 11
      //   500: iload 11
      //   502: iconst_3
      //   503: if_icmpge +42 -> 545
      //   506: iload 10
      //   508: aload_0
      //   509: iload 4
      //   511: aload 6
      //   513: aload 5
      //   515: getfield 202	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1706	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
      //   518: iload 11
      //   520: aaload
      //   521: aload 5
      //   523: getfield 206	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1707	[I
      //   526: iload 11
      //   528: iaload
      //   529: aload 8
      //   531: aload 9
      //   533: invokevirtual 235	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:BuildHorizon	(ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;)I
      //   536: iadd
      //   537: istore 10
      //   539: iinc 11 1
      //   542: goto -42 -> 500
      //   545: iload 10
      //   547: iconst_2
      //   548: if_icmpgt +6 -> 554
      //   551: goto +30 -> 581
      //   554: aload_0
      //   555: aload 8
      //   557: iconst_0
      //   558: aaload
      //   559: iconst_1
      //   560: aload 9
      //   562: iconst_0
      //   563: aaload
      //   564: iconst_2
      //   565: invokevirtual 230	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Link	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I)V
      //   568: aload_0
      //   569: dup
      //   570: getfield 253	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
      //   573: iconst_1
      //   574: iadd
      //   575: putfield 253	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
      //   578: goto -189 -> 389
      //   581: aload_3
      //   582: ifnull +281 -> 863
      //   585: aload_0
      //   586: aload_3
      //   587: aload 15
      //   589: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
      //   592: invokevirtual 298	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:GetCoordinates	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
      //   595: astore 5
      //   597: aload_0
      //   598: getfield 53	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:normal	Ljavax/vecmath/Vector3f;
      //   601: aload_3
      //   602: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1708	Ljavax/vecmath/Vector3f;
      //   605: invokevirtual 300	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
      //   608: aload_0
      //   609: fconst_0
      //   610: aload_3
      //   611: getfield 78	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1709	F
      //   614: invokestatic 167	java/lang/Math:max	(FF)F
      //   617: putfield 251	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
      //   620: iconst_0
      //   621: istore 6
      //   623: iload 6
      //   625: iconst_2
      //   626: if_icmpge +75 -> 701
      //   629: iload 6
      //   631: ifeq +9 -> 640
      //   634: ldc_w 301
      //   637: goto +4 -> 641
      //   640: fconst_1
      //   641: fstore 7
      //   643: iconst_0
      //   644: istore 8
      //   646: iload 8
      //   648: iconst_3
      //   649: if_icmpge +46 -> 695
      //   652: aload_2
      //   653: fload 7
      //   655: aload_3
      //   656: getfield 101	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:field_1705	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
      //   659: iload 8
      //   661: aaload
      //   662: getfield 304	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:field_2117	Ljavax/vecmath/Vector3f;
      //   665: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
      //   668: aload_0
      //   669: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
      //   672: aload_2
      //   673: iload 6
      //   675: aload_0
      //   676: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
      //   679: iload 6
      //   681: aaload
      //   682: iload 8
      //   684: aaload
      //   685: invokevirtual 308	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:LocalSupport	(Ljavax/vecmath/Vector3f;ILjavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
      //   688: pop
      //   689: iinc 8 1
      //   692: goto -46 -> 646
      //   695: iinc 6 1
      //   698: goto -75 -> 623
      //   701: aload 15
      //   703: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
      //   706: astore 6
      //   708: aload 15
      //   710: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
      //   713: astore 7
      //   715: aload 15
      //   717: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
      //   720: astore 8
      //   722: aload 6
      //   724: aload 5
      //   726: getfield 311	javax/vecmath/Vector3f:field_615	F
      //   729: aload_0
      //   730: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
      //   733: iconst_0
      //   734: aaload
      //   735: iconst_0
      //   736: aaload
      //   737: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
      //   740: aload 7
      //   742: aload 5
      //   744: getfield 314	javax/vecmath/Vector3f:field_616	F
      //   747: aload_0
      //   748: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
      //   751: iconst_0
      //   752: aaload
      //   753: iconst_1
      //   754: aaload
      //   755: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
      //   758: aload 8
      //   760: aload 5
      //   762: getfield 317	javax/vecmath/Vector3f:field_617	F
      //   765: aload_0
      //   766: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
      //   769: iconst_0
      //   770: aaload
      //   771: iconst_2
      //   772: aaload
      //   773: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
      //   776: aload_0
      //   777: getfield 51	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nearest	[Ljavax/vecmath/Vector3f;
      //   780: iconst_0
      //   781: aaload
      //   782: aload 6
      //   784: aload 7
      //   786: aload 8
      //   788: invokestatic 323	com/bulletphysics/linearmath/VectorUtil:add	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
      //   791: aload 6
      //   793: aload 5
      //   795: getfield 311	javax/vecmath/Vector3f:field_615	F
      //   798: aload_0
      //   799: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
      //   802: iconst_1
      //   803: aaload
      //   804: iconst_0
      //   805: aaload
      //   806: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
      //   809: aload 7
      //   811: aload 5
      //   813: getfield 314	javax/vecmath/Vector3f:field_616	F
      //   816: aload_0
      //   817: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
      //   820: iconst_1
      //   821: aaload
      //   822: iconst_1
      //   823: aaload
      //   824: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
      //   827: aload 8
      //   829: aload 5
      //   831: getfield 317	javax/vecmath/Vector3f:field_617	F
      //   834: aload_0
      //   835: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
      //   838: iconst_1
      //   839: aaload
      //   840: iconst_2
      //   841: aaload
      //   842: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
      //   845: aload_0
      //   846: getfield 51	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nearest	[Ljavax/vecmath/Vector3f;
      //   849: iconst_1
      //   850: aaload
      //   851: aload 6
      //   853: aload 7
      //   855: aload 8
      //   857: invokestatic 323	com/bulletphysics/linearmath/VectorUtil:add	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
      //   860: goto +8 -> 868
      //   863: aload_0
      //   864: iconst_1
      //   865: putfield 255	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:failed	Z
      //   868: aload_0
      //   869: getfield 251	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
      //   872: fstore 5
      //   874: aload_0
      //   875: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
      //   878: invokevirtual 288	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
      //   881: fload 5
      //   883: aload 15
      //   885: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
      //   888: freturn
      //   889: astore 14
      //   891: aload_0
      //   892: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
      //   895: invokevirtual 288	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
      //   898: aload 14
      //   900: athrow
      //   901: aload 15
      //   903: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
      //   906: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   10	891	0	this	EPA
      //   10	891	1	accuracy	float
      //   22	651	2	tmp	Vector3f
      //   24	632	3	bestface	GjkEpaSolver.Face
      //   26	484	4	markid	int
      //   78	304	5	pfidx_ptr	int[][]
      //   403	119	5	pfidx_ptr	GjkEpaSolver.Face
      //   595	287	5	pfidx_ptr	Vector3f
      //   81	217	6	pfidx_index	int
      //   424	88	6	pfidx_index	GjkEpaSolver.Mkv
      //   621	75	6	pfidx_index	int
      //   706	146	6	pfidx_index	Vector3f
      //   84	167	7	nfidx	int
      //   445	6	7	nfidx	float
      //   641	13	7	nfidx	float
      //   713	141	7	nfidx	Vector3f
      //   90	252	8	peidx_ptr	int[][]
      //   466	90	8	peidx_ptr	GjkEpaSolver.Face[]
      //   644	46	8	peidx_ptr	int
      //   720	136	8	peidx_ptr	Vector3f
      //   93	262	9	peidx_index	int
      //   476	85	9	peidx_index	GjkEpaSolver.Face[]
      //   96	213	10	neidx	int
      //   479	67	10	neidx	int
      //   102	179	11	basemkv	GjkEpaSolver.Mkv[]
      //   498	42	11	basemkv	int
      //   109	223	12	basefaces	GjkEpaSolver.Face[]
      //   195	157	13	local_i	int
      //   889	10	14	localObject	Object
      //   3	899	15	localStack	.Stack
      // Exception table:
      //   from	to	target	type
      //   17	374	889	finally
      //   389	874	889	finally
      //   889	891	889	finally
      //   5	901	901	finally
    }
  }
  
  public static class Face
  {
    public final GjkEpaSolver.Mkv[] field_1705 = new GjkEpaSolver.Mkv[3];
    public final Face[] field_1706 = new Face[3];
    public final int[] field_1707 = new int[3];
    public final Vector3f field_1708 = new Vector3f();
    public float field_1709;
    public int mark;
    public Face prev;
    public Face next;
  }
  
  protected class GJK
  {
    public final GjkEpaSolver.He[] table = new GjkEpaSolver.He[64];
    public final Matrix3f[] wrotations = { new Matrix3f(), new Matrix3f() };
    public final Vector3f[] positions = { new Vector3f(), new Vector3f() };
    public final ConvexShape[] shapes = new ConvexShape[2];
    public final GjkEpaSolver.Mkv[] simplex = new GjkEpaSolver.Mkv[5];
    public final Vector3f ray = new Vector3f();
    public int order;
    public int iterations;
    public float margin;
    public boolean failed;
    
    public GJK()
    {
      for (int local_i = 0; local_i < this.simplex.length; local_i++) {
        this.simplex[local_i] = new GjkEpaSolver.Mkv();
      }
    }
    
    public GJK(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1)
    {
      this(wrot0, pos0, shape0, wrot1, pos1, shape1, 0.0F);
    }
    
    public GJK(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1, float pmargin)
    {
      for (int local_i = 0; local_i < this.simplex.length; local_i++) {
        this.simplex[local_i] = new GjkEpaSolver.Mkv();
      }
      init(wrot0, pos0, shape0, wrot1, pos1, shape1, pmargin);
    }
    
    public void init(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1, float pmargin)
    {
      GjkEpaSolver.this.pushStack();
      this.wrotations[0].set(wrot0);
      this.positions[0].set(pos0);
      this.shapes[0] = shape0;
      this.wrotations[1].set(wrot1);
      this.positions[1].set(pos1);
      this.shapes[1] = shape1;
      this.margin = pmargin;
      this.failed = false;
    }
    
    public void destroy()
    {
      GjkEpaSolver.this.popStack();
    }
    
    public int Hash(Vector3f local_v)
    {
      int local_h = (int)(local_v.field_615 * 15461.0F) ^ (int)(local_v.field_616 * 83003.0F) ^ (int)(local_v.field_617 * 15473.0F);
      return local_h * 169639 & 0x3F;
    }
    
    public Vector3f LocalSupport(Vector3f arg1, int arg2, Vector3f arg3)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        MatrixUtil.transposeTransform(tmp, local_d, this.wrotations[local_i]);
        this.shapes[local_i].localGetSupportingVertex(tmp, out);
        this.wrotations[local_i].transform(out);
        out.add(this.positions[local_i]);
        return out;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public void Support(Vector3f arg1, GjkEpaSolver.Mkv arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        local_v.field_2117.set(local_d);
        Vector3f tmp1 = LocalSupport(local_d, 0, localStack.get$javax$vecmath$Vector3f());
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.set(local_d);
        tmp.negate();
        Vector3f tmp2 = LocalSupport(tmp, 1, localStack.get$javax$vecmath$Vector3f());
        local_v.field_2116.sub(tmp1, tmp2);
        local_v.field_2116.scaleAdd(this.margin, local_d, local_v.field_2116);
        return;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean FetchSupport()
    {
      int local_h = Hash(this.ray);
      for (GjkEpaSolver.He local_e = this.table[local_h]; local_e != null; local_e = local_e.field_2093) {
        if (local_e.field_2092.equals(this.ray))
        {
          this.order -= 1;
          return false;
        }
      }
      local_e = (GjkEpaSolver.He)GjkEpaSolver.this.stackHe.get();
      local_e.field_2092.set(this.ray);
      local_e.field_2093 = this.table[local_h];
      this.table[local_h] = local_e;
      Support(this.ray, this.simplex[(++this.order)]);
      return this.ray.dot(this.simplex[this.order].field_2116) > 0.0F;
    }
    
    public boolean SolveSimplex2(Vector3f arg1, Vector3f arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        if (local_ab.dot(local_ao) >= 0.0F)
        {
          Vector3f cabo = localStack.get$javax$vecmath$Vector3f();
          cabo.cross(local_ab, local_ao);
          if (cabo.lengthSquared() > 9.999999E-009F) {
            this.ray.cross(cabo, local_ab);
          } else {
            return true;
          }
        }
        else
        {
          this.order = 0;
          this.simplex[0].set(this.simplex[1]);
          this.ray.set(local_ao);
        }
        return false;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean SolveSimplex3(Vector3f arg1, Vector3f arg2, Vector3f arg3)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.cross(local_ab, local_ac);
        return SolveSimplex3a(local_ao, local_ab, local_ac, tmp);
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean SolveSimplex3a(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.cross(cabc, local_ab);
        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
        tmp2.cross(cabc, local_ac);
        if (tmp.dot(local_ao) < -1.0E-004F)
        {
          this.order = 1;
          this.simplex[0].set(this.simplex[1]);
          this.simplex[1].set(this.simplex[2]);
          return SolveSimplex2(local_ao, local_ab);
        }
        if (tmp2.dot(local_ao) > 1.0E-004F)
        {
          this.order = 1;
          this.simplex[1].set(this.simplex[2]);
          return SolveSimplex2(local_ao, local_ac);
        }
        float local_d = cabc.dot(local_ao);
        if (Math.abs(local_d) > 1.0E-004F)
        {
          if (local_d > 0.0F)
          {
            this.ray.set(cabc);
          }
          else
          {
            this.ray.negate(cabc);
            GjkEpaSolver.Mkv swapTmp = new GjkEpaSolver.Mkv();
            swapTmp.set(this.simplex[0]);
            this.simplex[0].set(this.simplex[1]);
            this.simplex[1].set(swapTmp);
          }
          return false;
        }
        return true;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean SolveSimplex4(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f crs = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.cross(local_ab, local_ac);
        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
        tmp2.cross(local_ac, local_ad);
        Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
        tmp3.cross(local_ad, local_ab);
        if (tmp.dot(local_ao) > 1.0E-004F)
        {
          crs.set(tmp);
          this.order = 2;
          this.simplex[0].set(this.simplex[1]);
          this.simplex[1].set(this.simplex[2]);
          this.simplex[2].set(this.simplex[3]);
          return SolveSimplex3a(local_ao, local_ab, local_ac, crs);
        }
        if (tmp2.dot(local_ao) > 1.0E-004F)
        {
          crs.set(tmp2);
          this.order = 2;
          this.simplex[2].set(this.simplex[3]);
          return SolveSimplex3a(local_ao, local_ac, local_ad, crs);
        }
        if (tmp3.dot(local_ao) > 1.0E-004F)
        {
          crs.set(tmp3);
          this.order = 2;
          this.simplex[1].set(this.simplex[0]);
          this.simplex[0].set(this.simplex[2]);
          this.simplex[2].set(this.simplex[3]);
          return SolveSimplex3a(local_ao, local_ad, local_ab, crs);
        }
        return true;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean SearchOrigin()
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.set(1.0F, 0.0F, 0.0F);
        return SearchOrigin(tmp);
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean SearchOrigin(Vector3f arg1)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp4 = localStack.get$javax$vecmath$Vector3f();
        this.iterations = 0;
        this.order = -1;
        this.failed = false;
        this.ray.set(initray);
        this.ray.normalize();
        Arrays.fill(this.table, null);
        FetchSupport();
        this.ray.negate(this.simplex[0].field_2116);
        while (this.iterations < 128)
        {
          float local_rl = this.ray.length();
          this.ray.scale(1.0F / (local_rl > 0.0F ? local_rl : 1.0F));
          if (FetchSupport())
          {
            boolean found = false;
            switch (this.order)
            {
            case 1: 
              tmp1.negate(this.simplex[1].field_2116);
              tmp2.sub(this.simplex[0].field_2116, this.simplex[1].field_2116);
              found = SolveSimplex2(tmp1, tmp2);
              break;
            case 2: 
              tmp1.negate(this.simplex[2].field_2116);
              tmp2.sub(this.simplex[1].field_2116, this.simplex[2].field_2116);
              tmp3.sub(this.simplex[0].field_2116, this.simplex[2].field_2116);
              found = SolveSimplex3(tmp1, tmp2, tmp3);
              break;
            case 3: 
              tmp1.negate(this.simplex[3].field_2116);
              tmp2.sub(this.simplex[2].field_2116, this.simplex[3].field_2116);
              tmp3.sub(this.simplex[1].field_2116, this.simplex[3].field_2116);
              tmp4.sub(this.simplex[0].field_2116, this.simplex[3].field_2116);
              found = SolveSimplex4(tmp1, tmp2, tmp3, tmp4);
            }
            if (found) {
              return true;
            }
          }
          else
          {
            return false;
          }
          this.iterations += 1;
        }
        this.failed = true;
        return false;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean EncloseOrigin()
    {
      .Stack localStack = .Stack.get();
      try
      {
        .Stack tmp7_5 = localStack;
        tmp7_5.push$javax$vecmath$Vector3f();
        .Stack tmp11_7 = tmp7_5;
        tmp11_7.push$javax$vecmath$Quat4f();
        tmp11_7.push$javax$vecmath$Matrix3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
        switch (this.order)
        {
        case 0: 
          break;
        case 1: 
          Vector3f local_ab = localStack.get$javax$vecmath$Vector3f();
          local_ab.sub(this.simplex[1].field_2116, this.simplex[0].field_2116);
          Vector3f[] local_b = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
          local_b[0].set(1.0F, 0.0F, 0.0F);
          local_b[1].set(0.0F, 1.0F, 0.0F);
          local_b[2].set(0.0F, 0.0F, 1.0F);
          local_b[0].cross(local_ab, local_b[0]);
          local_b[1].cross(local_ab, local_b[1]);
          local_b[2].cross(local_ab, local_b[2]);
          float[] local_m = { local_b[0].lengthSquared(), local_b[1].lengthSquared(), local_b[2].lengthSquared() };
          Quat4f tmpQuat = localStack.get$javax$vecmath$Quat4f();
          tmp.normalize(local_ab);
          QuaternionUtil.setRotation(tmpQuat, tmp, 2.094395F);
          Matrix3f local_r = localStack.get$javax$vecmath$Matrix3f();
          MatrixUtil.setRotation(local_r, tmpQuat);
          Vector3f local_w = localStack.get$javax$vecmath$Vector3f();
          local_w.set(local_b[2]);
          tmp.normalize(local_w);
          Support(tmp, this.simplex[4]);
          local_r.transform(local_w);
          tmp.normalize(local_w);
          Support(tmp, this.simplex[2]);
          local_r.transform(local_w);
          tmp.normalize(local_w);
          Support(tmp, this.simplex[3]);
          local_r.transform(local_w);
          this.order = 4;
          return true;
        case 2: 
          tmp1.sub(this.simplex[1].field_2116, this.simplex[0].field_2116);
          tmp2.sub(this.simplex[2].field_2116, this.simplex[0].field_2116);
          Vector3f local_ab = localStack.get$javax$vecmath$Vector3f();
          local_ab.cross(tmp1, tmp2);
          local_ab.normalize();
          Support(local_ab, this.simplex[3]);
          tmp.negate(local_ab);
          Support(tmp, this.simplex[4]);
          this.order = 4;
          return true;
        case 3: 
          return true;
        case 4: 
          return true;
        }
        return false;
      }
      finally
      {
        .Stack tmp594_592 = localStack;
        tmp594_592.pop$javax$vecmath$Vector3f();
        .Stack tmp598_594 = tmp594_592;
        tmp598_594.pop$javax$vecmath$Quat4f();
        tmp598_594.pop$javax$vecmath$Matrix3f();
      }
    }
  }
  
  public static class He
  {
    public final Vector3f field_2092 = new Vector3f();
    public He field_2093;
  }
  
  public static class Mkv
  {
    public final Vector3f field_2116 = new Vector3f();
    public final Vector3f field_2117 = new Vector3f();
    
    public void set(Mkv local_m)
    {
      this.field_2116.set(local_m.field_2116);
      this.field_2117.set(local_m.field_2117);
    }
  }
  
  public static class Results
  {
    public GjkEpaSolver.ResultsStatus status;
    public final Vector3f[] witnesses = { new Vector3f(), new Vector3f() };
    public final Vector3f normal = new Vector3f();
    public float depth;
    public int epa_iterations;
    public int gjk_iterations;
  }
  
  public static enum ResultsStatus
  {
    Separated,  Penetrating,  GJK_Failed,  EPA_Failed;
    
    private ResultsStatus() {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkEpaSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */