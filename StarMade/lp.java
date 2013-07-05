/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ 
/*     */ public abstract class lp extends ln
/*     */ {
/*  19 */   private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */   protected mF a;
/*  21 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  22 */   private Vector3f c = new Vector3f();
/*     */ 
/*     */   public lp(StateInterface paramStateInterface)
/*     */   {
/*  26 */     super(paramStateInterface);
/*  27 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  28 */     this.jdField_a_of_type_Float = 15.0F;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*  40 */     c(paramxq);
/*  41 */     d(paramxq);
/*     */ 
/*  43 */     super.a(paramxq);
/*     */   }
/*     */ 
/*     */   private void d(xq paramxq)
/*     */   {
/*     */     Object localObject;
/*  51 */     if (this.a != null) {
/*  52 */       if (!this.b)
/*     */       {
/*  54 */         if (((
/*  54 */           localObject = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_a_of_type_Int)) != null) && 
/*  54 */           ((localObject instanceof mv)))
/*     */         {
/*  57 */           localObject = (mv)localObject;
/*  58 */           this.a.calcWorldTransformRelative(((mv)localObject).getId(), ((mv)localObject).a());
/*  59 */           this.c.sub(this.a.getClientTransform().origin, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/*     */         }
/*     */ 
/*     */       }
/*  64 */       else if ((
/*  64 */         localObject = ((vg)getState()).a().getSector(this.jdField_a_of_type_Int)) != null)
/*     */       {
/*  65 */         this.a.calcWorldTransformRelative(((mx)localObject).a(), ((mx)localObject).a);
/*     */ 
/*  67 */         this.c.sub(this.a.getClientTransform().origin, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/*     */       } else {
/*  69 */         this.jdField_a_of_type_Boolean = false;
/*     */       }
/*     */ 
/*  72 */       if (this.c.lengthSquared() != 0.0F) {
/*  73 */         GlUtil.a(new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f), new Vector3f(this.c), this.jdField_b_of_type_JavaxVecmathVector3f);
/*     */ 
/*  76 */         this.jdField_b_of_type_JavaxVecmathVector3f.normalize();
/*  77 */         this.jdField_b_of_type_JavaxVecmathVector3f.scale(paramxq.a() * 2.07F);
/*  78 */         this.jdField_a_of_type_JavaxVecmathVector3f.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/*  79 */         this.jdField_a_of_type_JavaxVecmathVector3f.normalize();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  87 */     (
/*  88 */       localObject = new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f))
/*  88 */       .scale(paramxq.a() * 25.0F);
/*     */ 
/*  90 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*  91 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject);
/*     */ 
/*  93 */     a(this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*     */   }
/*     */ 
/*     */   public final void b(xq paramxq)
/*     */   {
/* 101 */     d(paramxq);
/*     */   }
/*     */ 
/*     */   public abstract void c(xq paramxq);
/*     */ 
/*     */   public final void c(int paramInt)
/*     */   {
/* 115 */     if (paramInt > 0)
/*     */     {
/*     */       Sendable localSendable;
/* 117 */       if (((
/* 117 */         localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().get(paramInt)) != null) && 
/* 117 */         ((localSendable instanceof mF))) {
/* 118 */         this.a = ((mF)localSendable); return;
/*     */       }
/* 120 */       System.err.println("Exception: target is not known: ID: " + paramInt + "; " + getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects());
/*     */ 
/* 122 */       return;
/* 123 */     }this.a = null;
/*     */   }
/*     */ 
/*     */   public final void a(lu paramlu)
/*     */   {
/* 130 */     super.a(paramlu);
/* 131 */     c(paramlu.jdField_a_of_type_Int);
/*     */   }
/*     */ 
/*     */   public final mF a()
/*     */   {
/* 141 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lp
 * JD-Core Version:    0.6.2
 */