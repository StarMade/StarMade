/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.shapes.CollisionShape;
/*      */ import com.bulletphysics.collision.shapes.CompoundShape;
/*      */ import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*      */ import com.bulletphysics.dynamics.RigidBody;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import com.bulletphysics.util.ObjectArrayList;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Quat4f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*      */ import org.schema.game.common.controller.CollectionNotLoadedException;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*      */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*      */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*      */ import org.schema.game.common.data.element.ElementDocking;
/*      */ import org.schema.game.common.data.element.ElementInformation;
/*      */ import org.schema.game.common.data.element.ElementKeyMap;
/*      */ import org.schema.game.common.data.physics.CubesCompoundShape;
/*      */ import org.schema.game.common.data.physics.PhysicsExt;
/*      */ import org.schema.game.common.data.physics.RigidBodyExt;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.network.objects.NetworkSegmentController;
/*      */ import org.schema.game.server.controller.GameServerController;
/*      */ import org.schema.schine.graphicsengine.core.GlUtil;
/*      */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.StateInterface;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*      */ import org.schema.schine.network.objects.remote.RemoteString;
/*      */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*      */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*      */ import org.schema.schine.network.server.ServerMessage;
/*      */ 
/*      */ public class jv
/*      */ {
/*   55 */   private ElementDocking jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking = null;
/*   56 */   private final Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*      */ 
/*   58 */   private String jdField_a_of_type_JavaLangString = null;
/*      */   private q jdField_a_of_type_Q;
/*      */   private boolean jdField_a_of_type_Boolean;
/*      */   private long jdField_a_of_type_Long;
/*      */   private final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*   66 */   private final Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*   67 */   private final Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*      */ 
/*   70 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*      */   public Quat4f a;
/*      */   private long jdField_b_of_type_Long;
/*  161 */   private final Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*      */   private long jdField_c_of_type_Long;
/*      */   private boolean jdField_b_of_type_Boolean;
/*  889 */   private final Quat4f jdField_b_of_type_JavaxVecmathQuat4f = new Quat4f();
/*  890 */   private final Quat4f jdField_c_of_type_JavaxVecmathQuat4f = new Quat4f();
/*      */   public vB a;
/*      */ 
/*      */   public jv(SegmentController paramSegmentController)
/*      */   {
/*   71 */     this.jdField_a_of_type_JavaxVecmathQuat4f = new Quat4f();
/*      */ 
/*   76 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*   77 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*   78 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*      */   }
/*      */ 
/*      */   public final void a()
/*      */   {
/*      */     Object localObject1;
/*   83 */     if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()))
/*      */     {
/*   85 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_b_of_type_Boolean) {
/*   86 */         int i = 0;
/*      */ 
/*   88 */         if ((((
/*   88 */           localObject1 = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a()) instanceof ld)) && 
/*   88 */           ((((ld)localObject1).a() instanceof DockingBlockManagerInterface))) {
/*   89 */           Object localObject2 = (DockingBlockManagerInterface)((ld)localObject1).a();
/*   90 */           q localq = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q());
/*   91 */           System.err.println("[DOCKING][" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " TO DOCKED POS: " + localq + ": " + ((DockingBlockManagerInterface)localObject2).getDockingBlock());
/*   92 */           if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/*      */             try {
/*   94 */               this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a().a().getSegmentBuffer().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a(new q()), true, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from);
/*      */ 
/*   96 */               this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getSegmentBuffer().a(localq, true, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to);
/*      */             }
/*      */             catch (IOException localIOException)
/*      */             {
/*  101 */               localIOException.printStackTrace();
/*      */             }
/*      */             catch (InterruptedException localInterruptedException)
/*      */             {
/*  101 */               localInterruptedException.printStackTrace(); } 
/*      */  } 
/*      */ Iterator localIterator2 = ((DockingBlockManagerInterface)localObject2).getDockingBlock().iterator();
/*      */           int j;
/*      */           do { if (!localIterator2.hasNext()) break; localObject2 = (ManagerModuleCollection)localIterator2.next();
/*  105 */             j = 0;
/*      */             try
/*      */             {
/*  108 */               for (localObject2 = ((ManagerModuleCollection)localObject2).getCollectionManagers().iterator(); ((Iterator)localObject2).hasNext(); )
/*      */               {
/*      */                 DockingBlockCollectionManager localDockingBlockCollectionManager;
/*      */                 Iterator localIterator1;
/*  109 */                 if ((
/*  109 */                   localDockingBlockCollectionManager = (DockingBlockCollectionManager)((Iterator)localObject2).next())
/*  109 */                   .getControllerPos().equals(localq)) {
/*  110 */                   i = 1;
/*  111 */                   if (!localDockingBlockCollectionManager.isObjectDockable(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController)) {
/*  112 */                     if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*  113 */                       b();
/*  114 */                     else if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) {
/*  115 */                       ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().b("Size of docked structure\ntoo big for docking area!\nUndocking " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getRealName());
/*      */                     }
/*      */ 
/*  118 */                     j = 1;
/*  119 */                     break;
/*      */                   }
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/*      */             }
/*      */             catch (ConcurrentModificationException localConcurrentModificationException)
/*      */             {
/*  128 */               localConcurrentModificationException.printStackTrace();
/*      */ 
/*  126 */               System.err.println("Exception could be catched and handeled by deferring docking valid request");
/*  127 */               throw new CollectionNotLoadedException();
/*      */             } }
/*  129 */           while (j == 0);
/*  130 */         }if (i == 0)
/*      */         {
/*  136 */           if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*  137 */             b();
/*  138 */           else if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) || (((SegmentController)localObject1).isClientOwnObject())) {
/*  139 */             ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().b("Docking module has been removed\n\nUndocking  " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getRealName());
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  147 */       for (localIterator1 = this.jdField_a_of_type_JavaUtilSet.iterator(); localIterator1.hasNext(); ) { (
/*  148 */           localObject1 = (ElementDocking)localIterator1.next()).from
/*  148 */           .a().a().flagUpdateDocking();
/*  149 */         System.err.println("[DOCKING][" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] Delegating checkValid request " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " -> " + ((ElementDocking)localObject1).to.a().a());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public final boolean a()
/*      */   {
/*  156 */     return System.currentTimeMillis() - this.jdField_a_of_type_Long > 1000L;
/*      */   }
/*      */   private static boolean a(le paramle) {
/*  159 */     return paramle.a() == 7;
/*      */   }
/*      */ 
/*      */   private void a(le paramle1, le paramle2)
/*      */   {
/*  165 */     SegmentController localSegmentController = paramle2.a().a();
/*      */ 
/*  167 */     if (paramle2.a().a().getDockingController().jdField_b_of_type_Boolean) {
/*  168 */       System.err.println("ERROR: cannot dock onto docked object: " + paramle1.a().a() + " -> " + paramle2.a().a());
/*      */ 
/*  171 */       if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && 
/*  172 */         ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw)) && 
/*  173 */         (((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().isEmpty())) {
/*  174 */         ((vg)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a(((lE)((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().get(0)).getName(), "Chain docking is not\nyet implemented\n(will come soon)", 3);
/*      */       }
/*      */ 
/*  182 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  183 */       return;
/*      */     }
/*  185 */     if (!this.jdField_a_of_type_JavaUtilSet.isEmpty()) {
/*  186 */       System.err.println("ERROR: cannot dock onto with already docked object: " + paramle1.a().a() + " -> " + paramle2.a().a());
/*      */ 
/*  189 */       if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && 
/*  190 */         ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw)) && 
/*  191 */         (!((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().isEmpty())) {
/*  192 */         ((vg)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a(((lE)((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).a().get(0)).getName(), "Chain docking is not\nyet implemented\n(will come soon)", 3);
/*      */       }
/*      */ 
/*  200 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  201 */       return;
/*      */     }
/*      */ 
/*  204 */     if ((!this.jdField_b_of_type_Boolean) || (this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a() != localSegmentController)) {
/*  205 */       a(new ElementDocking(paramle1, paramle2));
/*  206 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  207 */       localSegmentController.getDockingController().jdField_a_of_type_JavaUtilSet.add(new ElementDocking(paramle1, paramle2));
/*      */ 
/*  210 */       d();
/*  211 */       (
/*  212 */         paramle1 = new Transform())
/*  212 */         .setIdentity();
/*      */ 
/*  214 */       Object localObject1 = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q());
/*      */ 
/*  216 */       paramle1.origin.set(((q)localObject1).a - 8, ((q)localObject1).b - 8, ((q)localObject1).c - 8);
/*      */ 
/*  218 */       switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) { case 0:
/*  219 */         GlUtil.e(this.jdField_a_of_type_JavaxVecmathVector3f, paramle1); break;
/*      */       case 1:
/*  220 */         GlUtil.d(this.jdField_a_of_type_JavaxVecmathVector3f, paramle1); break;
/*      */       case 2:
/*  221 */         GlUtil.f(this.jdField_a_of_type_JavaxVecmathVector3f, paramle1); break;
/*      */       case 3:
/*  222 */         GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f, paramle1); break;
/*      */       case 4:
/*  223 */         GlUtil.c(this.jdField_a_of_type_JavaxVecmathVector3f, paramle1); break;
/*      */       case 5:
/*  224 */         GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector3f, paramle1);
/*      */       }
/*  226 */       localObject1 = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a().a().getSegmentBuffer().a();
/*      */ 
/*  229 */       if (this.jdField_b_of_type_JavaxVecmathVector3f.length() <= 0.0F)
/*      */       {
/*  232 */         this.jdField_b_of_type_JavaxVecmathVector3f.set(((xO)localObject1).jdField_a_of_type_JavaxVecmathVector3f);
/*      */       }
/*      */ 
/*  240 */       if (a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to)) {
/*  241 */         this.jdField_a_of_type_JavaxVecmathVector3f.scale(1.5F);
/*      */       } else {
/*  243 */         float f1 = 0.0F;
/*      */ 
/*  247 */         switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) { case 0:
/*  248 */           f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y; break;
/*      */         case 1:
/*  249 */           f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y; break;
/*      */         case 2:
/*  250 */           f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y; break;
/*      */         case 3:
/*  251 */           f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y; break;
/*      */         case 4:
/*  252 */           f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y; break;
/*      */         case 5:
/*  253 */           f1 = this.jdField_b_of_type_JavaxVecmathVector3f.y;
/*      */         }
/*  255 */         System.err.println("[DOCK] NOW DOCKING: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": BOUNDING BOX: " + localObject1 + "; DIST: " + f1);
/*  256 */         f1 = Math.abs(f1);
/*  257 */         this.jdField_a_of_type_JavaxVecmathVector3f.scale(f1 + 0.5F);
/*      */       }
/*      */ 
/*  260 */       paramle1.origin.add(this.jdField_a_of_type_JavaxVecmathVector3f);
/*      */ 
/*  264 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.onPhysicsRemove();
/*      */ 
/*  266 */       localSegmentController.onPhysicsRemove();
/*      */ 
/*  268 */       Object localObject2 = (CompoundShape)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape();
/*      */ 
/*  270 */       localObject1 = (CompoundShape)localSegmentController.getPhysicsDataContainer().getShape();
/*      */ 
/*  273 */       for (int i = 0; i < ((CompoundShape)localObject2).getNumChildShapes(); i++)
/*      */       {
/*  275 */         this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a();
/*      */ 
/*  277 */         int j = 0;
/*      */ 
/*  276 */         switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) {
/*      */         case 2:
/*  278 */           break;
/*      */         case 3:
/*  279 */           paramle1.basis.rotX(3.141593F); break;
/*      */         case 4:
/*  280 */           paramle1.basis.rotX(1.570796F); break;
/*      */         case 5:
/*      */           Matrix3f localMatrix3f;
/*  282 */           (localMatrix3f = new Matrix3f()).rotY(-1.570796F);
/*  283 */           paramle1.basis.rotZ(1.570796F); paramle1.basis.mul(localMatrix3f); localMatrix3f.rotZ(1.570796F); paramle1.basis.mul(localMatrix3f); break;
/*      */         case 1:
/*  284 */           paramle1.basis.rotZ(1.570796F); break;
/*      */         case 0:
/*  285 */           paramle1.basis.rotZ(-1.570796F);
/*      */         }
/*      */ 
/*  288 */         ((CompoundShape)localObject1).addChildShape(paramle1, ((CompoundShape)localObject2).getChildShape(i));
/*      */ 
/*  290 */         d.a(paramle1.basis, this.jdField_a_of_type_JavaxVecmathQuat4f);
/*      */ 
/*  292 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShapeChield((CompoundShapeChild)((CompoundShape)localObject1).getChildList().get(((CompoundShape)localObject1).getChildList().size() - 1));
/*      */       }
/*      */ 
/*  297 */       ((CompoundShape)localObject1).recalculateLocalAabb();
/*  298 */       float f2 = ((RigidBodyExt)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject()).getInvMass();
/*  299 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().updateMass(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getMass(), true);
/*  300 */       float f3 = ((RigidBodyExt)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject()).getInvMass();
/*  301 */       System.err.println("[DOCKING] " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " MASS: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getMass() + ", invBidyMass: " + f2 + "/" + f3 + " --TO-- " + localSegmentController + " MASS: " + localSegmentController.getMass());
/*      */ 
/*  303 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId() != localSegmentController.getSectorId()) {
/*  304 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setSectorId(localSegmentController.getSectorId());
/*  305 */         if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw))
/*      */         {
/*  307 */           localObject3 = null; for (paramle1 = ((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController)
/*  307 */             .a().iterator(); paramle1.hasNext(); ) { localObject2 = (lE)paramle1.next();
/*  308 */             System.err.println("[SERVER][DOCKING] sector docking on border! " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " has players attached. Doing Sector Change for " + localObject2);
/*      */             q localq;
/*  311 */             if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*  312 */               localq = ((vg)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().getSector(localSegmentController.getSectorId()).jdField_a_of_type_Q;
/*      */             else {
/*  314 */               localq = ((mv)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(localSegmentController.getSectorId())).a();
/*      */             }
/*      */ 
/*  317 */             ((lE)localObject2).a(new q(localq));
/*  318 */             ((lE)localObject2).c(localSegmentController.getSectorId());
/*      */ 
/*  322 */             if ((
/*  322 */               localObject3 = ((lE)localObject2).a()) != null)
/*      */             {
/*  323 */               System.err.println("[SERVER][DOCKING] sector docking on border! " + localq + " has CHARACTER. Doing Sector Change for " + localObject3 + ": ");
/*  324 */               ((lD)localObject3).setSectorId(localSegmentController.getSectorId());
/*      */             } else {
/*  326 */               System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + localObject2);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  335 */       Object localObject3 = localSegmentController.getPhysics().getBodyFromShape((CollisionShape)localObject1, localSegmentController.getMass() > 0.0F ? localSegmentController.getMass() + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getMass() : 0.0F, localSegmentController.getWorldTransform());
/*      */ 
/*  341 */       System.err.println("[DOCKING] ADDED CHILD COMPOUND: " + ((CompoundShape)localObject1).getNumChildShapes() + "; " + localObject1 + ": InvMass " + ((RigidBody)localObject3).getInvMass());
/*      */ 
/*  343 */       ((RigidBody)localObject3).setUserPointer(Integer.valueOf(localSegmentController.getId()));
/*      */ 
/*  345 */       if ((!jdField_c_of_type_Boolean) && (((RigidBody)localObject3).getCollisionShape() != localObject1)) throw new AssertionError();
/*      */ 
/*  347 */       localSegmentController.getPhysicsDataContainer().setObject((CollisionObject)localObject3);
/*      */ 
/*  350 */       for (paramle1 = localSegmentController.getDockingController().jdField_a_of_type_JavaUtilSet.iterator(); paramle1.hasNext(); ) ((ElementDocking)paramle1.next()).from
/*  351 */           .a().a().getPhysicsDataContainer().setObject(null);
/*      */ 
/*  353 */       localSegmentController.getPhysicsDataContainer().updatePhysical();
/*  354 */       localSegmentController.onPhysicsAdd();
/*      */ 
/*  356 */       ((RigidBodyExt)localSegmentController.getPhysicsDataContainer().getObject()).activate(true);
/*      */ 
/*  360 */       if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof ka)) {
/*  361 */         ((ka)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).handleNTDockChanged();
/*      */       }
/*      */ 
/*  364 */       if ((!jdField_c_of_type_Boolean) && (localSegmentController.getPhysicsDataContainer().getShape() != localObject1)) throw new AssertionError();
/*  365 */       if ((!jdField_c_of_type_Boolean) && (localSegmentController.getPhysicsDataContainer().getShape() != ((RigidBody)localObject3).getCollisionShape())) throw new AssertionError();
/*  366 */       System.err.println("[SEGCON] NOW DOCKED ON " + paramle2 + " " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " -> " + paramle2.a().a() + "  on " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
/*      */ 
/*  368 */       if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a() == this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId()))
/*      */       {
/*  370 */         xe.a("0022_ambience sfx - ambient hangar sounds hydraulics", this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform(), 0.99F);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void b(le paramle1, le paramle2)
/*      */   {
/*  377 */     paramle1.a();
/*  378 */     if (paramle1.a() == 0) {
/*  379 */       System.err.println("[DOCKING] NOT DOCKING " + paramle2.a().a() + " ON NOTHING: " + paramle1 + " ON " + paramle1.a().a());
/*  380 */       return;
/*      */     }
/*      */ 
/*  383 */     if (paramle2.a().a().getFactionId() != paramle1.a().a().getFactionId()) {
/*  384 */       paramle1 = null; if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a() == this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController)) {
/*  385 */         ((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().b("You cannot dock on a\nship of another\nfaction");
/*      */       }
/*  387 */       System.err.println("[DOCKING] NOT DOCKING: faction does not equal " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
/*  388 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*      */       {
/*  390 */         if (System.currentTimeMillis() - this.jdField_b_of_type_Long > 4000L) {
/*  391 */           if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof cw))
/*      */           {
/*  393 */             for (paramle1 = ((cw)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController)
/*  393 */               .a().iterator(); paramle1.hasNext(); ) (
/*  394 */                 paramle2 = (lE)paramle1.next())
/*  394 */                 .a(new ServerMessage("You cannot dock on a\nship of another\nfaction", 3, paramle2.getId()));
/*      */           }
/*      */ 
/*  397 */           this.jdField_b_of_type_Long = System.currentTimeMillis();
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  403 */       if (ElementKeyMap.getInfo(paramle1.a()).isDockable()) {
/*  404 */         System.err.println("[DOCKING] " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " DOING THE DOCK TO " + paramle1.a().a());
/*  405 */         a(paramle2, paramle1); return;
/*      */       }
/*  407 */       if (!jdField_c_of_type_Boolean) throw new AssertionError(paramle1.a());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final ElementDocking a()
/*      */   {
/*  417 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking;
/*      */   }
/*      */ 
/*      */   public final Set a()
/*      */   {
/*  425 */     return this.jdField_a_of_type_JavaUtilSet;
/*      */   }
/*      */ 
/*      */   public final void a(NetworkSegmentController paramNetworkSegmentController)
/*      */   {
/*  536 */     if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/*  537 */       if (this.jdField_b_of_type_Boolean) {
/*  538 */         synchronized (paramNetworkSegmentController) {
/*  539 */           paramNetworkSegmentController.dockingSize.set(this.jdField_b_of_type_JavaxVecmathVector3f);
/*  540 */           paramNetworkSegmentController.dockedTo.set(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getUniqueIdentifier());
/*      */ 
/*  542 */           paramNetworkSegmentController.dockedElement.set(new s(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q()), 0));
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  547 */       paramNetworkSegmentController.dockingSize.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  548 */       paramNetworkSegmentController.dockedTo.set("NONE");
/*      */ 
/*  550 */       System.err.println("[DOCKING] SET NT DOCK TO " + (String)paramNetworkSegmentController.dockedTo.get());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void a(String paramString, q paramq)
/*      */   {
/*  557 */     if (a())
/*      */     {
/*  559 */       this.jdField_a_of_type_JavaLangString = paramString;
/*  560 */       this.jdField_a_of_type_Q = paramq;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void b() {
/*  565 */     if (a()) {
/*  566 */       System.err.println("[DOCKING] REQUEST UNDOCK " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + "; " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
/*  567 */       this.jdField_a_of_type_Boolean = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void a(ElementDocking paramElementDocking)
/*      */   {
/*  575 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking = paramElementDocking;
/*  576 */     this.jdField_b_of_type_Boolean = (paramElementDocking != null);
/*      */   }
/*      */ 
/*      */   public final void a(Ad paramAd)
/*      */   {
/*  583 */     if ((!jdField_c_of_type_Boolean) && (!paramAd.a().equals("dock"))) throw new AssertionError();
/*      */ 
/*  586 */     String str = (String)(
/*  586 */       paramAd = (Ad[])paramAd.a())[
/*  586 */       0].a();
/*  587 */     if ((paramAd.length > 4) && (paramAd[4].a() == Af.b))
/*      */     {
/*  589 */       ((Byte)paramAd[4].a()).byteValue();
/*      */     }
/*  591 */     if (!str.equals("NONE")) {
/*  592 */       a(str, (q)paramAd[1].a());
/*      */     }
/*      */ 
/*  600 */     if ((paramAd.length > 3) && (paramAd[3].a() == Af.j))
/*      */     {
/*  605 */       if ("s".equals(paramAd[3]))
/*  606 */         this.jdField_b_of_type_JavaxVecmathVector3f.set((Vector3f)paramAd[3].a());
/*      */     }
/*      */   }
/*      */ 
/*      */   public final Ad a()
/*      */   {
/*  617 */     if (this.jdField_a_of_type_JavaLangString != null)
/*      */       try {
/*  619 */         throw new IllegalStateException("Exception DELAYED DOCK OF " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " TO " + this.jdField_a_of_type_JavaLangString + " HAS FAILED"); } catch (Exception localException) { localException
/*  620 */           .printStackTrace();
/*      */ 
/*  622 */         System.err.println("Exception successfully catched: rewriting desired docking");
/*      */ 
/*  624 */         this.jdField_a_of_type_VB = new vB(this.jdField_a_of_type_JavaLangString, this.jdField_a_of_type_Q);
/*      */       }
/*      */     Ad localAd1;
/*  627 */     if (this.jdField_a_of_type_VB == null)
/*  628 */       localAd1 = new Ad(Af.i, "dockedTo", this.jdField_b_of_type_Boolean ? this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getUniqueIdentifier() : "NONE");
/*      */     else {
/*  630 */       localAd1 = new Ad(Af.i, "dockedTo", this.jdField_a_of_type_VB.jdField_a_of_type_JavaLangString);
/*      */     }
/*  632 */     if (!((String)localAd1.a()).equals("NONE"))
/*  633 */       System.err.println("WROTE DOCKED TO " + localAd1.a());
/*      */     Ad localAd2;
/*  636 */     if (this.jdField_a_of_type_VB == null)
/*  637 */       localAd2 = new Ad(Af.k, "dockedToPos", this.jdField_b_of_type_Boolean ? this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(new q()) : new q());
/*      */     else {
/*  639 */       localAd2 = new Ad(Af.k, "dockedToPos", this.jdField_a_of_type_VB.jdField_a_of_type_Q);
/*      */     }
/*      */ 
/*  642 */     Ad localAd3 = new Ad(Af.b, null, Byte.valueOf((byte)0));
/*  643 */     Ad localAd4 = new Ad(Af.b, null, Byte.valueOf((byte)0));
/*      */ 
/*  645 */     Ad localAd5 = new Ad(Af.j, "s", this.jdField_b_of_type_JavaxVecmathVector3f);
/*      */ 
/*  647 */     return new Ad(Af.n, "dock", new Ad[] { localAd1, localAd2, localAd3, localAd4, localAd5, new Ad(Af.a, null, null) });
/*      */   }
/*      */ 
/*      */   private void c()
/*      */   {
/*  684 */     if (this.jdField_b_of_type_Boolean)
/*      */     {
/*      */       ElementDocking localElementDocking;
/*  686 */       if (((
/*  686 */         localElementDocking = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking).to
/*  686 */         .a() == 0) || (ElementKeyMap.getInfo(localElementDocking.to.a()).isDockable())) {
/*  687 */         System.err.println("NOW UNDOCKING: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + "; " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "; DOCKED TO TYPE: " + localElementDocking.to.a());
/*      */         SegmentController localSegmentController;
/*  691 */         if (!(
/*  690 */           localSegmentController = localElementDocking.to.a().a())
/*  690 */           .getDockingController().jdField_a_of_type_JavaUtilSet.remove(localElementDocking))
/*      */         {
/*  692 */           System.err.println("Exception: WARNING! UNDOCK UNSUCCESSFULL " + localElementDocking + ": " + localSegmentController.getDockingController().jdField_a_of_type_JavaUtilSet);
/*      */         }
/*  694 */         a(null);
/*      */ 
/*  697 */         a(localSegmentController, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*      */ 
/*  701 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setObject(null);
/*  702 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShape(null);
/*  703 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShapeChield(null);
/*  704 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().initialTransform.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*  705 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getRemoteTransformable().a().set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*  706 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.initPhysics();
/*  707 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.onPhysicsAdd();
/*      */ 
/*  709 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setFlagPhysicsInit(false);
/*  710 */         this.jdField_b_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/*      */ 
/*  712 */         if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof ka)) {
/*  713 */           ((ka)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController).handleNTDockChanged();
/*      */         }
/*      */ 
/*      */       }
/*  719 */       else if (!jdField_c_of_type_Boolean) { throw new AssertionError(localElementDocking.to.a()); }
/*      */ 
/*  721 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void a(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
/*      */   {
/*  727 */     paramSegmentController1.onPhysicsRemove();
/*      */ 
/*  731 */     if (paramSegmentController1.getDockingController().jdField_a_of_type_JavaUtilSet.size() > 0)
/*      */     {
/*      */       CubesCompoundShape localCubesCompoundShape;
/*  734 */       int i = (
/*  734 */         localCubesCompoundShape = (CubesCompoundShape)paramSegmentController1.getPhysicsDataContainer().getShape())
/*  734 */         .getNumChildShapes();
/*      */ 
/*  736 */       localCubesCompoundShape.removeChildShape(paramSegmentController2.getPhysicsDataContainer().getShapeChild().childShape);
/*  737 */       if (paramSegmentController1.getMass() > 0.0F) {
/*  738 */         paramSegmentController1.getPhysicsDataContainer().updateMass(paramSegmentController1.getMass(), true);
/*      */       }
/*      */ 
/*  741 */       if (localCubesCompoundShape.getNumChildShapes() != i - 1) {
/*  742 */         System.err.println("[DOCKING] UPDATING SHAPE, BUT NO SHAPE HAS BEEN REMOVED (DELETION OF DOCKED OBJECT)");
/*      */       }
/*  744 */       (
/*  749 */         paramSegmentController2 = paramSegmentController1.getPhysics().getBodyFromShape(localCubesCompoundShape, paramSegmentController1.getMass(), paramSegmentController1.getWorldTransform()))
/*  749 */         .setUserPointer(Integer.valueOf(paramSegmentController1.getId()));
/*  750 */       if ((!jdField_c_of_type_Boolean) && (paramSegmentController2.getCollisionShape() != localCubesCompoundShape)) throw new AssertionError();
/*      */ 
/*  754 */       paramSegmentController1.getPhysicsDataContainer().setObject(paramSegmentController2);
/*      */ 
/*  757 */       for (Iterator localIterator = paramSegmentController1.getDockingController().jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext(); ) ((ElementDocking)localIterator.next()).from
/*  758 */           .a().a().getPhysicsDataContainer().setObject(null);
/*      */ 
/*  761 */       if ((!jdField_c_of_type_Boolean) && (paramSegmentController1.getPhysicsDataContainer().getShape() != localCubesCompoundShape)) throw new AssertionError();
/*  762 */       if ((!jdField_c_of_type_Boolean) && (paramSegmentController1.getPhysicsDataContainer().getShape() != paramSegmentController2.getCollisionShape())) throw new AssertionError();
/*      */ 
/*  764 */       paramSegmentController1.onPhysicsAdd();
/*  765 */       if (paramSegmentController1.getMass() > 0.0F) {
/*  766 */         paramSegmentController1.getPhysicsDataContainer().updateMass(paramSegmentController1.getMass(), true);
/*      */       }
/*  768 */       return;
/*  769 */     }System.err.println("[DOCKING] doing complete physics reset for " + paramSegmentController1);
/*  770 */     paramSegmentController1.getPhysicsDataContainer().setObject(null);
/*  771 */     paramSegmentController1.getPhysicsDataContainer().setShape(null);
/*  772 */     paramSegmentController1.getPhysicsDataContainer().setShapeChield(null);
/*  773 */     paramSegmentController1.getPhysicsDataContainer().initialTransform.set(paramSegmentController1.getWorldTransform());
/*  774 */     paramSegmentController1.getRemoteTransformable().a().set(paramSegmentController1.getWorldTransform());
/*  775 */     paramSegmentController1.initPhysics();
/*      */ 
/*  777 */     paramSegmentController1.onPhysicsAdd();
/*      */   }
/*      */ 
/*      */   private void d()
/*      */   {
/*  921 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*      */ 
/*  923 */     switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.b()]) { case 0:
/*  924 */       GlUtil.e(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform); break;
/*      */     case 1:
/*  925 */       GlUtil.d(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform); break;
/*      */     case 2:
/*  926 */       GlUtil.f(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform); break;
/*      */     case 3:
/*  927 */       GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform); break;
/*      */     case 4:
/*  928 */       GlUtil.c(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform); break;
/*      */     case 5:
/*  929 */       GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*      */     }
/*  931 */     xO localxO = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a().a().getSegmentBuffer().a();
/*      */     Vector3f localVector3f;
/*  932 */     (
/*  933 */       localVector3f = new Vector3f())
/*  933 */       .sub(localxO.jdField_b_of_type_JavaxVecmathVector3f, localxO.jdField_a_of_type_JavaxVecmathVector3f);
/*  934 */     if (a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to))
/*  935 */       this.jdField_a_of_type_JavaxVecmathVector3f.scale(4.5F);
/*      */     else {
/*  937 */       this.jdField_a_of_type_JavaxVecmathVector3f.scale(localVector3f.y / 2.0F);
/*      */     }
/*      */ 
/*  942 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_a_of_type_JavaxVecmathVector3f);
/*      */ 
/*  944 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*  945 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.inverse();
/*      */   }
/*      */ 
/*      */   public final void b(NetworkSegmentController paramNetworkSegmentController)
/*      */   {
/*  951 */     if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/*      */     {
/*  953 */       paramNetworkSegmentController.dockingSize.getVector(this.jdField_b_of_type_JavaxVecmathVector3f);
/*      */ 
/*  955 */       String str = (String)paramNetworkSegmentController.dockedTo.get();
/*  956 */       int i = (!this.jdField_b_of_type_Boolean) && (!str.equals("NONE")) ? 1 : 0;
/*      */ 
/*  958 */       int j = (this.jdField_b_of_type_Boolean) && (!str.equals("NONE")) && (!str.equals(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getUniqueIdentifier())) ? 1 : 0;
/*      */ 
/*  961 */       int k = (this.jdField_b_of_type_Boolean) && (str.equals("NONE")) ? 1 : 0;
/*      */ 
/*  963 */       if ((i != 0) || (j != 0))
/*      */       {
/*  965 */         paramNetworkSegmentController = paramNetworkSegmentController.dockedElement.getVector();
/*  966 */         a(str, new q(paramNetworkSegmentController.a, paramNetworkSegmentController.b, paramNetworkSegmentController.c));
/*      */       }
/*  968 */       if (k != 0)
/*      */       {
/*  970 */         b();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void a(xq paramxq)
/*      */   {
/*      */     try
/*      */     {
/* 1001 */       xq localxq1 = paramxq; paramxq = this; if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()) || (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a().a()) || (System.currentTimeMillis() - paramxq.jdField_c_of_type_Long < 1000L))
/*      */         return;
/* 1001 */       Object localObject1;
/*      */       Object localObject2;
/* 1001 */       if (paramxq.jdField_a_of_type_JavaLangString != null) { localTransform = null; synchronized (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects()) { for (localObject1 = paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); ((Iterator)localObject1).hasNext(); ) if ((((localObject2 = (Sendable)((Iterator)localObject1).next()) instanceof SegmentController)) && (((SegmentController)localObject2).getUniqueIdentifier().equals(paramxq.jdField_a_of_type_JavaLangString))) { if ((!(localObject1 = (SegmentController)localObject2).getPhysicsDataContainer().isInitialized()) || (!((SegmentController)localObject1).getSegmentBuffer().a().a()) || (!((SegmentController)localObject1).getSegmentBuffer().a().c())) { System.err.println("[DOCKING] TARGET PHYSICS NOT YET INITIALIZED: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT")); paramxq.jdField_c_of_type_Long = System.currentTimeMillis(); return; } if ((!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()) || (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a().a()) || (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a().c())) { System.err.println("[DOCKING] SELF PHYSICS NOT YET INITIALIZED: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT")); paramxq.jdField_c_of_type_Long = System.currentTimeMillis(); return; } if ((((SegmentController)localObject1).getTotalElements() <= 0) || (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getTotalElements() <= 0)) System.err.println("[DOCKING][LANDING] Object has zero elements: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT"));  }   } 
/*      */       }
/* 1001 */       if (paramxq.jdField_a_of_type_Boolean) { localTransform = null; System.err.println("[DOCKING] Delayed undock requested on " + (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "Server" : "Client")); paramxq.c(); paramxq.jdField_a_of_type_Boolean = false; } Transform localTransform = null; if ((!paramxq.jdField_a_of_type_JavaUtilSet.isEmpty()) && (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized())) { if ((!jdField_c_of_type_Boolean) && (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape() != paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape())) throw new AssertionError(paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape() + "; " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape()); ??? = null; for (localObject1 = paramxq.jdField_a_of_type_JavaUtilSet.iterator(); ((Iterator)localObject1).hasNext(); ((ElementDocking)localObject2).from.a().a().getPhysicsDataContainer().updatePhysical(paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject())) { if (((localObject2 = (ElementDocking)((Iterator)localObject1).next()) == null) || (((ElementDocking)localObject2).from == null)) { if (!jdField_c_of_type_Boolean) throw new AssertionError(); throw new NullPointerException("Invalid docking: " + localObject2); } if (((ElementDocking)localObject2).from.a().a().getPhysicsDataContainer().isInitialized()) if (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((ElementDocking)localObject2).from.a().a().getId())) { System.err.println("[DOCKING] UPDATING " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " MASS BECAUSE DOCKED SHIP DOESNT EXIST ANYMORE: " + ((ElementDocking)localObject2).from.a().a()); a(paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, ((ElementDocking)localObject2).from.a().a()); ??? = localObject2; } else { xq localxq2 = localxq1;
/*      */               jv localjv;
/* 1001 */               if ((localjv = ((ElementDocking)localObject2).from.a().a().getDockingController()).jdField_b_of_type_Boolean) { localjv.d(); if (a(localjv.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to)) { d.a((localTransform = localjv.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShapeChild().transform).basis, localjv.jdField_b_of_type_JavaxVecmathQuat4f); localjv.jdField_b_of_type_JavaxVecmathQuat4f.normalize(); localjv.jdField_a_of_type_JavaxVecmathQuat4f.normalize(); if (localjv.jdField_a_of_type_JavaxVecmathQuat4f.w != 0.0F) { if (localjv.jdField_b_of_type_JavaxVecmathQuat4f.w == 0.0F) { localjv.jdField_c_of_type_JavaxVecmathQuat4f.set(localjv.jdField_a_of_type_JavaxVecmathQuat4f); } else { d.a(localjv.jdField_b_of_type_JavaxVecmathQuat4f, localjv.jdField_a_of_type_JavaxVecmathQuat4f, Math.min(1.0F, localxq2.a() * 50.0F), localjv.jdField_c_of_type_JavaxVecmathQuat4f); localjv.jdField_c_of_type_JavaxVecmathQuat4f.normalize(); } localTransform.basis.set(localjv.jdField_c_of_type_JavaxVecmathQuat4f); } } } } if (paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject() == null) throw new NullPointerException("Tried chain update");  } if (??? != null) paramxq.jdField_a_of_type_JavaUtilSet.remove(???); ((CompoundShape)paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape()).recalculateLocalAabb(); } if ((paramxq.jdField_b_of_type_Boolean) && (!paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(paramxq.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a().getId()))) { System.err.println("[DOCKING] undocking this " + paramxq.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " because mothership is deleted: " + paramxq.jdField_a_of_type_OrgSchemaGameCommonDataElementElementDocking.to.a().a()); paramxq.c(); } return; } catch (IOException localIOException) {
/* 1002 */       (
/* 1003 */         paramxq = 
/* 1008 */         localIOException).printStackTrace();
/* 1004 */       throw new ErrorDialogException(paramxq.getMessage()); } catch (InterruptedException localInterruptedException) {
/* 1005 */       (
/* 1006 */         paramxq = localInterruptedException)
/* 1006 */         .printStackTrace();
/* 1007 */     }throw new ErrorDialogException(paramxq.getMessage());
/*      */   }
/*      */ 
/*      */   public final boolean b()
/*      */   {
/* 1017 */     return this.jdField_b_of_type_Boolean;
/*      */   }
/*      */ 
/*      */   public final Vector3f a()
/*      */   {
/* 1033 */     return this.jdField_b_of_type_JavaxVecmathVector3f;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jv
 * JD-Core Version:    0.6.2
 */