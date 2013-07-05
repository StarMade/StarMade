/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ 
/*     */ public class me
/*     */   implements Ag, xZ
/*     */ {
/*     */   private short jdField_a_of_type_Short;
/*     */   private int jdField_a_of_type_Int;
/*     */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*  30 */   private long jdField_a_of_type_Long = -1L;
/*     */   private int jdField_b_of_type_Int;
/*  33 */   private static int c = 180000;
/*  34 */   private int d = -1;
/*  35 */   private int e = 0;
/*  36 */   private static int f = 10;
/*  37 */   private static int g = c / f;
/*     */ 
/* 176 */   private static q jdField_a_of_type_Q = new q();
/* 177 */   private static Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 178 */   private static q jdField_b_of_type_Q = new q();
/*     */ 
/*     */   public me()
/*     */   {
/*  40 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/*     */   }
/*     */   public me(int paramInt1, short paramShort, int paramInt2, Vector3f paramVector3f) {
/*  43 */     this();
/*  44 */     a(paramInt1, paramShort, paramInt2, paramVector3f);
/*     */   }
/*     */   public final void a(int paramInt1, short paramShort, int paramInt2, Vector3f paramVector3f) {
/*  47 */     this.jdField_b_of_type_Int = paramInt1;
/*  48 */     this.jdField_a_of_type_Short = paramShort;
/*  49 */     this.jdField_a_of_type_Int = paramInt2;
/*  50 */     this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/*     */   }
/*     */ 
/*     */   public final boolean a(long paramLong)
/*     */   {
/*  55 */     long l2 = paramLong; paramLong = this; long l1 = l2 - paramLong.jdField_a_of_type_Long;
/*  56 */     this.e = ((int)(l1 / g));
/*  57 */     return l1 < c;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  63 */     boolean bool = this.d != this.e;
/*  64 */     this.d = this.e;
/*  65 */     return bool;
/*     */   }
/*     */ 
/*     */   public final short a()
/*     */   {
/*  71 */     return this.jdField_a_of_type_Short;
/*     */   }
/*     */ 
/*     */   public final void a(short paramShort)
/*     */   {
/*  77 */     this.jdField_a_of_type_Short = paramShort;
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/*  83 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt)
/*     */   {
/*  89 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final Vector3f a()
/*     */   {
/*  95 */     return this.jdField_a_of_type_JavaxVecmathVector3f;
/*     */   }
/*     */ 
/*     */   public final void a(Vector3f paramVector3f)
/*     */   {
/* 101 */     this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/*     */   }
/*     */ 
/*     */   public final long a()
/*     */   {
/* 107 */     return this.jdField_a_of_type_Long;
/*     */   }
/*     */ 
/*     */   public final void a(long paramLong)
/*     */   {
/* 113 */     this.jdField_a_of_type_Long = paramLong;
/*     */   }
/*     */ 
/*     */   public final int b()
/*     */   {
/* 120 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final int a(yg paramyg)
/*     */   {
/* 125 */     if (this.jdField_a_of_type_Short == 0) {
/* 126 */       System.err.println("[CLIENT] WARNING: TRIED TO DRAW FREE ITEM, BUT type == TYPE_NONE");
/* 127 */       return -1;
/*     */     }
/* 129 */     if (this.jdField_a_of_type_Short == -2) {
/* 130 */       if (paramyg.b().startsWith("build-icons-extra")) {
/* 131 */         return 0;
/*     */       }
/* 133 */       return -1;
/*     */     }
/*     */ 
/* 136 */     int i = ElementKeyMap.getInfo(this.jdField_a_of_type_Short).getBuildIconNum() / 256;
/* 137 */     if (paramyg.b().startsWith("build-icons-" + i.b(i))) {
/* 138 */       return ElementKeyMap.getInfo(this.jdField_a_of_type_Short).getBuildIconNum() % 256;
/*     */     }
/* 140 */     return -1;
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 147 */     Ad localAd1 = new Ad(Af.j, null, this.jdField_a_of_type_JavaxVecmathVector3f);
/* 148 */     Ad localAd2 = new Ad(Af.c, null, Short.valueOf(this.jdField_a_of_type_Short));
/* 149 */     Ad localAd3 = new Ad(Af.d, null, Integer.valueOf(this.jdField_a_of_type_Int));
/* 150 */     return new Ad(Af.n, null, new Ad[] { localAd1, localAd2, localAd3, new Ad(Af.a, null, null) });
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd) {
/* 154 */     paramAd = (Ad[])paramAd.a();
/* 155 */     this.jdField_a_of_type_JavaxVecmathVector3f = ((Vector3f)paramAd[0].a());
/* 156 */     this.jdField_a_of_type_Short = ((Short)paramAd[1].a()).shortValue();
/* 157 */     this.jdField_a_of_type_Int = ((Integer)paramAd[2].a()).intValue();
/*     */   }
/*     */ 
/*     */   public String getUniqueIdentifier() {
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean isVolatile() {
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   public final float a(long paramLong) {
/* 169 */     long l = paramLong - this.jdField_a_of_type_Long;
/* 170 */     return 0.001F + 0.009F * (1.0F - (float)l / c);
/*     */   }
/*     */   public final void b(int paramInt) {
/* 173 */     this.jdField_b_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final boolean a(mx parammx)
/*     */   {
/*     */     Iterator localIterator;
/* 180 */     synchronized (parammx.a().getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 181 */       for (localIterator = parammx.a().getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         Object localObject1;
/* 182 */         if (((
/* 182 */           localObject1 = (Sendable)localIterator.next()) instanceof SegmentController))
/*     */         {
/* 186 */           if ((
/* 186 */             localObject1 = (SegmentController)localObject1)
/* 186 */             .getSectorId() == parammx.a()) {
/* 187 */             jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/*     */ 
/* 191 */             ((SegmentController)localObject1).getWorldTransformInverse().transform(jdField_b_of_type_JavaxVecmathVector3f);
/*     */ 
/* 193 */             if (((SegmentController)localObject1).getSegmentBuffer().a().a(jdField_b_of_type_JavaxVecmathVector3f))
/*     */             {
/* 195 */               int i = Math.round(jdField_b_of_type_JavaxVecmathVector3f.x) + 8;
/*     */ 
/* 197 */               int j = Math.round(jdField_b_of_type_JavaxVecmathVector3f.y) + 8;
/* 198 */               int k = Math.round(jdField_b_of_type_JavaxVecmathVector3f.z) + 8;
/* 199 */               jdField_a_of_type_Q.b(i, j, k);
/*     */ 
/* 201 */               le localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false);
/*     */ 
/* 203 */               System.err.println(localObject1 + " POINT INSIDE " + this + ":    " + this.jdField_a_of_type_JavaxVecmathVector3f + " -trans> " + jdField_a_of_type_Q + ": " + localle);
/*     */ 
/* 205 */               if ((localle != null) && (localle.a() != 0))
/*     */               {
/* 209 */                 jdField_b_of_type_Q.b(jdField_a_of_type_Q);
/*     */ 
/* 212 */                 for (parammx = 1; parammx < 8; )
/*     */                 {
/* 220 */                   jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/* 221 */                   jdField_a_of_type_Q.jdField_b_of_type_Int -= parammx;
/*     */ 
/* 223 */                   if (((
/* 223 */                     localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || 
/* 223 */                     (localle.a() == 0)) break;
/* 224 */                   jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/*     */ 
/* 228 */                   jdField_a_of_type_Q.jdField_a_of_type_Int += parammx;
/*     */ 
/* 230 */                   if (((
/* 230 */                     localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || 
/* 230 */                     (localle.a() == 0)) break;
/* 231 */                   jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/*     */ 
/* 235 */                   jdField_a_of_type_Q.jdField_a_of_type_Int -= parammx;
/*     */ 
/* 237 */                   if (((
/* 237 */                     localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || 
/* 237 */                     (localle.a() == 0)) break;
/* 238 */                   jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/*     */ 
/* 242 */                   jdField_a_of_type_Q.c += parammx;
/*     */ 
/* 244 */                   if (((
/* 244 */                     localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || 
/* 244 */                     (localle.a() == 0)) break;
/* 245 */                   jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/*     */ 
/* 249 */                   jdField_a_of_type_Q.c -= parammx;
/*     */ 
/* 251 */                   if (((
/* 251 */                     localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || 
/* 251 */                     (localle.a() == 0)) break;
/* 252 */                   jdField_a_of_type_Q.b(jdField_b_of_type_Q);
/*     */ 
/* 258 */                   jdField_a_of_type_Q.jdField_b_of_type_Int += parammx;
/*     */ 
/* 260 */                   if (((
/* 260 */                     localle = ((SegmentController)localObject1).getSegmentBuffer().a(jdField_a_of_type_Q, false)) == null) || 
/* 260 */                     (localle.a() == 0)) break;
/* 261 */                   parammx++;
/*     */                 }
/*     */ 
/* 265 */                 jdField_a_of_type_Q.c(jdField_b_of_type_Q);
/*     */ 
/* 267 */                 this.jdField_a_of_type_JavaxVecmathVector3f.x += jdField_a_of_type_Q.jdField_a_of_type_Int;
/* 268 */                 this.jdField_a_of_type_JavaxVecmathVector3f.y += jdField_a_of_type_Q.jdField_b_of_type_Int;
/* 269 */                 this.jdField_a_of_type_JavaxVecmathVector3f.z += jdField_a_of_type_Q.c;
/*     */ 
/* 271 */                 this.jdField_a_of_type_JavaxVecmathVector3f.x = (FastMath.a(this.jdField_a_of_type_JavaxVecmathVector3f.x) + 0.5F);
/* 272 */                 this.jdField_a_of_type_JavaxVecmathVector3f.y = (FastMath.a(this.jdField_a_of_type_JavaxVecmathVector3f.y) + 0.5F);
/* 273 */                 this.jdField_a_of_type_JavaxVecmathVector3f.z = (FastMath.a(this.jdField_a_of_type_JavaxVecmathVector3f.z) + 0.5F);
/*     */ 
/* 275 */                 System.err.println("[ITEM][COLLISION] warping item out of collision " + this.jdField_a_of_type_JavaxVecmathVector3f);
/* 276 */                 return true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 284 */     return false;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 291 */     return "(ITEM[" + this.jdField_b_of_type_Int + "]: type " + this.jdField_a_of_type_Short + "; #" + this.jdField_a_of_type_Int + "; " + this.jdField_a_of_type_JavaxVecmathVector3f + ")";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     me
 * JD-Core Version:    0.6.2
 */