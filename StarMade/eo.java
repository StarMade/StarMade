/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.AxisAngle4f;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL15;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlElementMapper;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public class eo
/*     */   implements xg
/*     */ {
/*     */   private final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  36 */   private final Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*     */   private int jdField_a_of_type_Int;
/*  40 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  41 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  42 */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  43 */   private Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 1.0F, 0.0F);
/*  44 */   private Vector3f e = new Vector3f();
/*     */   private static Vector3f[] jdField_a_of_type_ArrayOfJavaxVecmathVector3f;
/*     */   private static Vector3f[] jdField_b_of_type_ArrayOfJavaxVecmathVector3f;
/*     */   private static Vector3f[] jdField_c_of_type_ArrayOfJavaxVecmathVector3f;
/*     */   private static Vector3f[] jdField_d_of_type_ArrayOfJavaxVecmathVector3f;
/*  57 */   private Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/*  58 */   private AxisAngle4f jdField_a_of_type_JavaxVecmathAxisAngle4f = new AxisAngle4f();
/*     */   private int jdField_b_of_type_Int;
/*  60 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private int jdField_c_of_type_Int;
/*     */   private int jdField_d_of_type_Int;
/*     */   private zx jdField_a_of_type_Zx;
/*  65 */   private static IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  66 */   private static IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  67 */   private static IntBuffer jdField_c_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*     */ 
/*     */   public eo(SegmentController paramSegmentController)
/*     */   {
/*  71 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  76 */     if (this.jdField_a_of_type_Int != 0)
/*  77 */       GL15.glDeleteBuffers(this.jdField_a_of_type_Int);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 101 */     if (this.jdField_a_of_type_Boolean) {
/* 102 */       eo localeo = this; this.jdField_a_of_type_JavaUtilMap.clear(); for (Iterator localIterator = localeo.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getControlElementMap().getControllingMap().getAll().entrySet().iterator(); localIterator.hasNext(); )
/*     */       {
/* 102 */         Map.Entry localEntry;
/* 102 */         if (((ObjectOpenHashSet)(localEntry = (Map.Entry)localIterator.next()).getValue()).size() > 0) { short s = 0; ObjectIterator localObjectIterator = ((ObjectOpenHashSet)localEntry.getValue()).iterator(); while ((localObjectIterator.hasNext()) && ((s = ((ja)localObjectIterator.next()).jdField_a_of_type_Short) == 32767));
/* 102 */           if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(s))) localeo.jdField_a_of_type_JavaUtilMap.put(ElementCollection.getPosFromIndex(((Long)localEntry.getKey()).longValue(), new q()), localEntry.getValue()); else System.err.println("the type is not a factory type: " + s);  } 
/*     */       }
/* 103 */       e();
/* 104 */       this.jdField_a_of_type_Boolean = false;
/*     */     }
/*     */ 
/* 109 */     if ((!this.jdField_b_of_type_Boolean) && (this.jdField_a_of_type_JavaUtilMap.size() > 0))
/*     */     {
/* 111 */       GL11.glEnable(2903);
/* 112 */       GL11.glEnable(2896);
/* 113 */       this.jdField_a_of_type_Zx.a();
/*     */ 
/* 117 */       GL11.glEnableClientState(32884);
/* 118 */       GL11.glEnableClientState(32885);
/* 119 */       GL11.glEnableClientState(32888);
/*     */ 
/* 123 */       GL15.glBindBuffer(34962, this.jdField_d_of_type_Int);
/*     */ 
/* 125 */       GL11.glTexCoordPointer(3, 5126, 0, 0L);
/*     */ 
/* 128 */       GL15.glBindBuffer(34962, this.jdField_c_of_type_Int);
/*     */ 
/* 130 */       GL11.glNormalPointer(5126, 0, 0L);
/*     */ 
/* 133 */       GL15.glBindBuffer(34962, this.jdField_a_of_type_Int);
/*     */ 
/* 135 */       GL11.glVertexPointer(3, 5126, 0, 0L);
/*     */ 
/* 138 */       GL11.glDrawArrays(7, 0, this.jdField_b_of_type_Int);
/*     */ 
/* 140 */       GL15.glBindBuffer(34962, 0);
/*     */ 
/* 142 */       GL11.glDisableClientState(32884);
/* 143 */       GL11.glDisableClientState(32885);
/* 144 */       GL11.glDisableClientState(32888);
/* 145 */       GL11.glDisable(2903);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void e()
/*     */   {
/* 152 */     int i = 0;
/* 153 */     for (Object localObject1 = this.jdField_a_of_type_JavaUtilMap.entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) {
/* 154 */       for (localObject2 = ((ObjectOpenHashSet)((Map.Entry)((Iterator)localObject1).next())
/* 154 */         .getValue()).iterator(); ((Iterator)localObject2).hasNext(); ) { ((Iterator)localObject2).next();
/* 155 */         i++;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 160 */     this.jdField_b_of_type_Boolean = (i == 0);
/* 161 */     if (this.jdField_b_of_type_Boolean) {
/* 162 */       System.err.println("NOTHING TO DRAW");
/* 163 */       return;
/*     */     }
/* 165 */     System.err.println("UPDATING CONNECTIONS: " + i);
/* 166 */     this.jdField_b_of_type_Int = (i << 1 << 1 << 3);
/*     */ 
/* 168 */     localObject1 = GlUtil.a(this.jdField_b_of_type_Int * 3 << 2, 0).asFloatBuffer();
/* 169 */     FloatBuffer localFloatBuffer = GlUtil.a(this.jdField_b_of_type_Int * 3 << 2, 1).asFloatBuffer();
/* 170 */     Object localObject2 = GlUtil.a(this.jdField_b_of_type_Int * 3 << 2, 2).asFloatBuffer();
/* 171 */     i = 0;
/*     */ 
/* 175 */     for (Iterator localIterator = this.jdField_a_of_type_JavaUtilMap.entrySet().iterator(); localIterator.hasNext(); ) { localObject3 = (Map.Entry)localIterator.next();
/* 176 */       this.jdField_a_of_type_JavaxVecmathVector3f.set(-8.0F + ((q)((Map.Entry)localObject3).getKey()).jdField_a_of_type_Int, -8.0F + ((q)((Map.Entry)localObject3).getKey()).jdField_b_of_type_Int, -8.0F + ((q)((Map.Entry)localObject3).getKey()).jdField_c_of_type_Int);
/* 177 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient().transform(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 178 */       for (localObject3 = ((ObjectOpenHashSet)((Map.Entry)localObject3).getValue()).iterator(); ((Iterator)localObject3).hasNext(); ) { ja localja = (ja)((Iterator)localObject3).next();
/* 179 */         this.jdField_b_of_type_JavaxVecmathVector3f.set(-8.0F + localja.jdField_a_of_type_Int, -8.0F + localja.jdField_b_of_type_Int, -8.0F + localja.jdField_c_of_type_Int);
/* 180 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient().transform(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 181 */         i += a(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, (FloatBuffer)localObject1, localFloatBuffer, (FloatBuffer)localObject2);
/*     */       }
/*     */     }
/* 184 */     Object localObject3;
/* 184 */     if ((!jdField_c_of_type_Boolean) && (this.jdField_b_of_type_Int != i)) throw new AssertionError(this.jdField_b_of_type_Int + "/" + i);
/*     */ 
/* 186 */     if (this.jdField_a_of_type_Int == 0)
/*     */     {
/* 189 */       GL15.glGenBuffers(jdField_a_of_type_JavaNioIntBuffer);
/*     */ 
/* 191 */       this.jdField_a_of_type_Int = jdField_a_of_type_JavaNioIntBuffer.get(0);
/*     */ 
/* 193 */       GL15.glGenBuffers(jdField_b_of_type_JavaNioIntBuffer);
/*     */ 
/* 195 */       this.jdField_c_of_type_Int = jdField_b_of_type_JavaNioIntBuffer.get(0);
/*     */ 
/* 197 */       GL15.glGenBuffers(jdField_c_of_type_JavaNioIntBuffer);
/*     */ 
/* 199 */       this.jdField_d_of_type_Int = jdField_c_of_type_JavaNioIntBuffer.get(0);
/*     */     }
/*     */ 
/* 202 */     GL15.glBindBuffer(34962, this.jdField_a_of_type_Int);
/*     */ 
/* 205 */     ((FloatBuffer)localObject1).flip();
/* 206 */     System.err.println("BUFFER LIMIT " + ((FloatBuffer)localObject1).limit() + " / " + (this.jdField_b_of_type_Int * 3 << 2) + " (" + this.jdField_b_of_type_Int + ")");
/* 207 */     GL15.glBufferData(34962, (FloatBuffer)localObject1, 35044);
/*     */ 
/* 210 */     GL15.glBindBuffer(34962, this.jdField_c_of_type_Int);
/* 211 */     localFloatBuffer.flip();
/* 212 */     GL15.glBufferData(34962, localFloatBuffer, 35044);
/*     */ 
/* 214 */     GL15.glBindBuffer(34962, this.jdField_d_of_type_Int);
/* 215 */     ((FloatBuffer)localObject2).flip();
/* 216 */     GL15.glBufferData(34962, (FloatBuffer)localObject2, 35044);
/*     */ 
/* 218 */     GL15.glBindBuffer(34962, 0);
/*     */ 
/* 221 */     this.jdField_a_of_type_Zx = new zx();
/* 222 */     this.jdField_a_of_type_Zx.f();
/* 223 */     this.jdField_a_of_type_Zx.c(new float[] { 0.9F, 0.9F, 0.9F, 1.0F });
/*     */   }
/*     */ 
/*     */   private int a(Vector3f paramVector3f1, Vector3f paramVector3f2, FloatBuffer paramFloatBuffer1, FloatBuffer paramFloatBuffer2, FloatBuffer paramFloatBuffer3)
/*     */   {
/* 229 */     this.jdField_c_of_type_JavaxVecmathVector3f.sub(paramVector3f2, paramVector3f1);
/* 230 */     this.e.cross(this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*     */ 
/* 232 */     float f1 = 0.0F;
/*     */ 
/* 234 */     float f2 = 0.0F;
/* 235 */     for (int i = 0; i < 8; 
/* 237 */       i++)
/*     */     {
/* 239 */       this.jdField_a_of_type_JavaxVecmathAxisAngle4f.set(this.jdField_c_of_type_JavaxVecmathVector3f, f1);
/* 240 */       this.jdField_a_of_type_JavaxVecmathMatrix3f.set(this.jdField_a_of_type_JavaxVecmathAxisAngle4f);
/* 241 */       jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i].set(0.0F, 0.1F, 0.0F);
/*     */ 
/* 243 */       this.jdField_a_of_type_JavaxVecmathMatrix3f.transform(jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i]);
/*     */ 
/* 245 */       jdField_b_of_type_ArrayOfJavaxVecmathVector3f[i].set(jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i]);
/*     */ 
/* 247 */       jdField_c_of_type_ArrayOfJavaxVecmathVector3f[i].set(jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i]);
/*     */ 
/* 249 */       jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i].add(paramVector3f1);
/* 250 */       jdField_c_of_type_ArrayOfJavaxVecmathVector3f[i].add(paramVector3f2);
/* 251 */       jdField_d_of_type_ArrayOfJavaxVecmathVector3f[i].set(0.0F, f2, 0.0F);
/* 252 */       f2 += 0.125F;
/* 253 */       f1 += 0.7853982F;
/*     */     }
/*     */ 
/* 257 */     for (paramVector3f1 = 0; paramVector3f1 < 8; 
/* 259 */       paramVector3f1++) {
/* 260 */       GlUtil.a(paramFloatBuffer1, jdField_a_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 261 */       GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 262 */       jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1].x = 0.0F;
/* 263 */       GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/*     */ 
/* 266 */       GlUtil.a(paramFloatBuffer1, jdField_a_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 267 */       GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 268 */       jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)].x = 0.0F;
/* 269 */       GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/*     */ 
/* 271 */       GlUtil.a(paramFloatBuffer1, jdField_c_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 272 */       GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/* 273 */       jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)].x = 1.0F;
/* 274 */       GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
/*     */ 
/* 276 */       GlUtil.a(paramFloatBuffer1, jdField_c_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 277 */       GlUtil.a(paramFloatBuffer2, jdField_b_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/* 278 */       jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1].x = 1.0F;
/* 279 */       GlUtil.a(paramFloatBuffer3, jdField_d_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
/*     */     }
/*     */ 
/* 283 */     return 32;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 301 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final SegmentController a()
/*     */   {
/* 308 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  45 */     jdField_a_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*  46 */     jdField_b_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*  47 */     jdField_c_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*  48 */     jdField_d_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
/*     */ 
/*  50 */     for (int i = 0; i < 8; i++) {
/*  51 */       jdField_a_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
/*  52 */       jdField_c_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
/*  53 */       jdField_b_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
/*  54 */       jdField_d_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eo
 * JD-Core Version:    0.6.2
 */