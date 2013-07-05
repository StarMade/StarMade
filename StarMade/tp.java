/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.physics.KinematicCharacterControllerExt;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.schine.ai.stateMachines.FSMException;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ 
/*     */ public final class tp extends sM
/*     */ {
/*  32 */   private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  33 */   private final Vector3f b = new Vector3f();
/*  34 */   private Vector3f c = new Vector3f();
/*  35 */   private float jdField_a_of_type_Float = -1.0F;
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public tp(wk paramwk)
/*     */   {
/*  42 */     super(paramwk);
/*     */   }
/*     */ 
/*     */   public final Vector3f a()
/*     */   {
/*  83 */     return this.jdField_a_of_type_JavaxVecmathVector3f;
/*     */   }
/*     */   public final Vector3f b() {
/*  86 */     return this.b;
/*     */   }
/*     */   private static boolean a(SegmentController paramSegmentController1, SegmentController paramSegmentController2) {
/*  89 */     return (paramSegmentController1.getDockingController().b()) && (paramSegmentController1.getDockingController().a().to.a().a() == paramSegmentController2);
/*     */   }
/*     */ 
/*     */   public final boolean c() {
/*  93 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/*     */     try
/*     */     {
/* 113 */       d();
/*     */     }
/*     */     catch (FSMException localFSMException) {
/* 116 */       localFSMException.printStackTrace();
/*     */     }
/*     */ 
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean b() {
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean d()
/*     */   {
/*     */     mF localmF;
/* 128 */     if ((
/* 128 */       localmF = ((sL)a().a).a()) != null)
/*     */     {
/* 130 */       localmF.calcWorldTransformRelative(((SegmentController)a()).getSectorId(), ((vg)((SegmentController)a()).getState()).a().getSector(((SegmentController)a()).getSectorId()).a);
/*     */       Object localObject2;
/* 132 */       if (localmF != a()) { localObject2 = localmF; localObject1 = this; if ((localObject2 instanceof SegmentController));
/* 132 */         ((tp)localObject1).c.sub(((mF)localObject2).getClientTransform().origin, ((SegmentController)((tp)localObject1).a()).getWorldTransform().origin); if ((((tp)localObject1).c.length() > ((tp)localObject1).a().b() ? 0 : ((localObject2 instanceof kd)) && (((kd)localObject2).c()) && (((tp)localObject1).c.length() > ((tp)localObject1).a().b() / 2.0F) ? 0 : ((localObject2 instanceof kd)) && (((kd)localObject2).a()) ? 0 : a((SegmentController)localObject2, (SegmentController)((tp)localObject1).a()) ? 0 : a((SegmentController)((tp)localObject1).a(), (SegmentController)localObject2) ? 0 : ((mF)localObject2).isHidden() ? 0 : ((mF)localObject2).getPhysicsDataContainer().getObject() == null ? 0 : 1) != 0); } else { a(new tx());
/* 134 */         return false;
/*     */       }
/* 136 */       Object localObject1 = null;
/* 137 */       if ((localmF.getPhysicsDataContainer().getObject() instanceof RigidBody))
/*     */       {
/* 139 */         localObject1 = ((RigidBody)localmF.getPhysicsDataContainer().getObject())
/* 139 */           .getLinearVelocity(new Vector3f());
/*     */       }
/* 144 */       else if ((localmF instanceof lD)) {
/* 145 */         localObject1 = ((lD)localmF).a().getLinearVelocity(new Vector3f());
/*     */       }
/*     */ 
/* 148 */       if (localObject1 != null)
/*     */       {
/* 153 */         localObject2 = new Vector3f(localmF.getClientTransform().origin);
/*     */         Vector3f localVector3f;
/* 155 */         (
/* 156 */           localVector3f = new Vector3f(((SegmentController)a()).getWorldTransform().origin))
/* 156 */           .sub((Tuple3f)localObject2);
/* 157 */         wk localwk = ((wp)a()).a().a();
/* 158 */         float f = 10.0F;
/* 159 */         if ((localwk instanceof sJ)) {
/* 160 */           f = ((sJ)localwk).a();
/*     */         }
/* 162 */         if (((localmF instanceof kd)) && (((kd)localmF).c()))
/* 163 */           f = Math.max(1.0F, f * 0.1F);
/*     */         Object tmp463_462 = localObject2; tmp463_462.x = ((float)(tmp463_462.x + (Math.random() - 0.5D) * (localVector3f.length() / f)));
/*     */         Object tmp491_490 = localObject2; tmp491_490.y = ((float)(tmp491_490.y + (Math.random() - 0.5D) * (localVector3f.length() / f)));
/*     */         Object tmp519_518 = localObject2; tmp519_518.z = ((float)(tmp519_518.z + (Math.random() - 0.5D) * (localVector3f.length() / f)));
/*     */ 
/* 170 */         this.jdField_a_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject2);
/* 171 */         this.b.set((Tuple3f)localObject1);
/*     */       } else {
/* 173 */         a(new tx());
/* 174 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 178 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tp
 * JD-Core Version:    0.6.2
 */