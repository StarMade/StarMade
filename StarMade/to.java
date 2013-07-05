/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ 
/*     */ public final class to extends sM
/*     */ {
/*  31 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  33 */   private long jdField_a_of_type_Long = 0L;
/*     */ 
/*  36 */   private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public to(wk paramwk)
/*     */   {
/*  43 */     super(paramwk);
/*     */   }
/*     */ 
/*     */   private static boolean a(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
/*     */   {
/*  62 */     return (paramSegmentController1.getDockingController().a() != null) && (paramSegmentController1.getDockingController().a().to.a().a() == paramSegmentController2);
/*     */   }
/*     */ 
/*     */   public final boolean c() {
/*  66 */     ((sL)a().a).a(null);
/*     */ 
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean b() {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean d()
/*     */   {
/*     */     to localto1;
/*     */     Object localObject1;
/*     */     Object localObject2;
/*     */     Object localObject3;
/* 192 */     if (a().a(kq.a).a().equals("Selected Target")) {
/* 193 */       localto1 = this; if (((a().a() instanceof SegmentController)) && (((SegmentController)localto1.a().a()).getDockingController().a() != null) && (((localObject1 = ((SegmentController)localto1.a().a()).getDockingController().a().to.a().a()) instanceof cw))) { cw localcw = (cw)localObject1; localObject2 = null; for (Iterator localIterator1 = localcw.a().iterator(); localIterator1.hasNext(); ) for (localObject4 = (localObject3 = (lE)localIterator1.next()).a().a().iterator(); ((Iterator)localObject4).hasNext(); localObject2 = localObject3)
/*     */           {
/*     */             lA locallA;
/* 193 */             if (((locallA = (lA)((Iterator)localObject4).next()).jdField_a_of_type_LE != localObject3) || (!locallA.jdField_a_of_type_JavaLangObject.equals(kd.a)));
/*     */           }
/* 193 */         Object localObject4;
/* 193 */         if (localObject2 != null) { int i = ((Integer)((lE)localObject2).a().selectedEntityId.get()).intValue(); if (((localObject3 = (Sendable)localto1.a().a().getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) != null) && ((localObject3 instanceof mF))) { localObject4 = (mF)localObject3; int j = 0; if (((localObject4 instanceof SegmentController)) && (a((SegmentController)localObject4, (SegmentController)localObject1))) j = 1; if (j == 0) { ((sL)localto1.a().a).a((mF)localObject4); localto1.a(new tB()); } } } }
/*     */     } else {
/* 195 */       localto1 = this; if (System.currentTimeMillis() - localto1.jdField_a_of_type_Long > 3000L) { localto1.jdField_a_of_type_Long = System.currentTimeMillis(); localto1.jdField_a_of_type_JavaUtilArrayList.clear();
/*     */         Iterator localIterator2;
/* 195 */         synchronized (localto1.a().a().getLocalAndRemoteObjectContainer().getLocalObjects()) { localObject2 = (mF)localto1.a().a(); for (localIterator2 = localto1.a().a().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); localIterator2.hasNext(); ) if (((localObject3 = (Sendable)localIterator2.next()) instanceof mF)) if ((((vf)localto1.a().a()).a().a((mF)localObject2, (mF)localObject3)) && ((!(localObject3 instanceof SegmentController)) || ((!a((SegmentController)localto1.a(), (SegmentController)localObject3)) && (!a((SegmentController)localObject3, (SegmentController)localto1.a()))))) { localObject1 = (mF)localObject3; to localto2 = localto1; if ((((localObject1 instanceof kd)) && (((kd)localObject1).c()) && (localto2.jdField_a_of_type_JavaxVecmathVector3f.length() > localto2.a().b() / 2.0F) ? 0 : ((localObject1 instanceof kd)) && (((kd)localObject1).a()) ? 0 : (localObject1 instanceof jy) ? 0 : 1) != 0) if ((!(localObject3 instanceof cw)) || ((localObject3 instanceof lD)) || (!((cw)localObject3).a().isEmpty()) || (((wp)localObject3).a().a())) { ((mF)localObject3).calcWorldTransformRelative(((SegmentController)localto1.a()).getSectorId(), ((vg)((SegmentController)localto1.a()).getState()).a().getSector(((SegmentController)localto1.a()).getSectorId()).a); localto1.jdField_a_of_type_JavaxVecmathVector3f.sub(((mF)localObject3).getClientTransform().origin, ((SegmentController)localto1.a()).getWorldTransform().origin); if (localto1.jdField_a_of_type_JavaxVecmathVector3f.length() < localto1.a().b()) { localto1.jdField_a_of_type_JavaxVecmathVector3f.length(); localto1.jdField_a_of_type_JavaUtilArrayList.add((mF)localObject3); }  }   }   
/*     */         }
/*     */       }
/* 195 */       if (localto1.jdField_a_of_type_JavaUtilArrayList.size() > 0) ((sL)localto1.a().a).a((mF)localto1.jdField_a_of_type_JavaUtilArrayList.get(Universe.getRandom().nextInt(localto1.jdField_a_of_type_JavaUtilArrayList.size()))); if (((sL)localto1.a().a).a() != null) localto1.a(new tB());
/*     */     }
/* 197 */     ((sL)a().a).a();
/* 198 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     to
 * JD-Core Version:    0.6.2
 */