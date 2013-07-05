/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class im extends yz
/*     */ {
/*     */   private ct jdField_a_of_type_Ct;
/*     */   private float jdField_a_of_type_Float;
/*  30 */   private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/*     */ 
/*  36 */   private Transform b = new Transform();
/*  37 */   private Vector3f c = new Vector3f();
/*     */ 
/*     */   public im(ClientState paramClientState, float paramFloat)
/*     */   {
/*  26 */     super(paramClientState);
/*  27 */     this.jdField_a_of_type_Ct = ((ct)paramClientState);
/*  28 */     this.jdField_a_of_type_Float = paramFloat;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*     */     mF localmF1;
/*  41 */     if ((
/*  41 */       localmF1 = this.jdField_a_of_type_Ct.a()) != null)
/*     */     {
/*  42 */       if ((localmF1 instanceof SegmentController)) {
/*  43 */         this.b.set(((SegmentController)localmF1).getWorldTransformInverse());
/*     */       }
/*  45 */       else if ((localmF1 instanceof lD)) {
/*  46 */         this.b.set(xe.a().getWorldTransform());
/*  47 */         this.b.inverse();
/*     */       } else {
/*  49 */         this.b.set(localmF1.getWorldTransform());
/*  50 */         this.b.inverse();
/*     */       }
/*     */ 
/*  59 */       localmF1 = ((ct)a()).a().a.a.a
/*  59 */         .a();
/*     */ 
/*  63 */       GL11.glPointSize(2.0F);
/*     */ 
/*  65 */       GL11.glBegin(0);
/*     */ 
/*  69 */       for (mF localmF2 : this.jdField_a_of_type_Ct.a().values())
/*     */       {
/*  73 */         this.c.set(localmF2.getWorldTransformClient().origin);
/*  74 */         this.b.transform(this.c);
/*  75 */         this.c.scale(0.07F);
/*  76 */         if (this.c.length() < this.jdField_a_of_type_Float / 2.0F) {
/*  77 */           ij.a(localmF2, this.jdField_a_of_type_JavaxVecmathVector4f, localmF1 == localmF2, this.jdField_a_of_type_Ct);
/*  78 */           this.c.x = (this.jdField_a_of_type_Float / 2.0F - this.c.x);
/*  79 */           this.c.z = (this.jdField_a_of_type_Float / 2.0F - this.c.z);
/*  80 */           GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  81 */           GL11.glVertex2f(this.c.x, this.c.z);
/*     */         }
/*     */       }
/*  84 */       GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  85 */       GL11.glEnd();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  98 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 104 */     return 0.0F;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     im
 * JD-Core Version:    0.6.2
 */