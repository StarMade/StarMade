/*     */ package org.schema.game.common.controller.elements.explosive;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import kd;
/*     */ import lA;
/*     */ import lE;
/*     */ import le;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.UpdatableCollectionManager;
/*     */ import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.game.network.objects.NetworkSegmentController;
/*     */ import org.schema.schine.network.objects.remote.RemoteBooleanArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*     */ import q;
/*     */ import xq;
/*     */ 
/*     */ public class ExplosiveElementManager extends UsableControllableSingleElementManager
/*     */   implements UpdatableCollectionManager
/*     */ {
/*     */   private ExplosiveCollectionManager explosiveManager;
/*  55 */   private final ArrayList explosions = new ArrayList();
/*     */ 
/*  57 */   public ExplosiveElementManager(SegmentController paramSegmentController) { super(new ExplosiveCollectionManager(paramSegmentController), paramSegmentController);
/*  58 */     this.explosiveManager = ((ExplosiveCollectionManager)getCollection()); }
/*     */ 
/*     */   public void addExplosion(q paramq, Vector3f paramVector3f, EditableSendableSegmentController paramEditableSendableSegmentController, le paramle) {
/*  61 */     paramle = null;
/*  62 */     for (Iterator localIterator = getCollection().getCollection().iterator(); localIterator.hasNext(); )
/*  63 */       if ((
/*  63 */         localObject1 = (ExplosiveUnit)localIterator.next())
/*  63 */         .getNeighboringCollection().contains(Long.valueOf(ElementCollection.getIndex(paramq)))) {
/*  64 */         paramle = (le)localObject1;
/*  65 */         break;
/*     */       }
/*     */     Object localObject1;
/*  68 */     if (paramle != null)
/*  69 */       synchronized (this.explosions)
/*     */       {
/*  71 */         localObject1 = new ExplosiveElementManager.Explosion(this, new q(paramq), new Vector3f(paramVector3f), (EditableSendableSegmentController)getSegmentController(), paramEditableSendableSegmentController, (byte)0);
/*  72 */         if (!this.explosions.contains(localObject1)) {
/*  73 */           System.err.println("[EXPLOSION] INITIATING EXPLOSION " + paramVector3f);
/*  74 */           this.explosions.add(localObject1);
/*     */         }
/*  76 */         paramq = new ExplosiveElementManager.Explosion(this, new q(paramq), new Vector3f(paramVector3f), (EditableSendableSegmentController)getSegmentController(), (EditableSendableSegmentController)getSegmentController(), (byte)1);
/*  77 */         if (!this.explosions.contains(paramq)) {
/*  78 */           this.explosions.add(paramq);
/*     */         }
/*  80 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public float getActualExplosive()
/*     */   {
/*     */     float f;
/*  86 */     if ((
/*  86 */       f = this.explosiveManager.getTotalExplosive()) == 
/*  86 */       0.0F) {
/*  87 */       return 0.0F;
/*     */     }
/*  89 */     return f;
/*     */   }
/*     */ 
/*     */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*     */   {
/*  99 */     return new ExplosiveCollectionManager(getSegmentController());
/*     */   }
/*     */ 
/*     */   public void handle(lA paramlA)
/*     */   {
/* 108 */     if (!((Boolean)paramlA.jdField_a_of_type_LE.a().activeControllerMask.get(1).get()).booleanValue()) {
/* 109 */       return;
/*     */     }
/*     */ 
/* 112 */     if (!kd.a.equals(paramlA.jdField_a_of_type_JavaLangObject))
/*     */     {
/* 114 */       return;
/*     */     }
/*     */ 
/* 117 */     getActualExplosive();
/*     */   }
/*     */ 
/*     */   public void onControllerChange()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void update(xq arg1)
/*     */   {
/* 127 */     synchronized (this.explosions) {
/* 128 */       while (!this.explosions.isEmpty()) {
/* 129 */         ExplosiveElementManager.Explosion localExplosion = (ExplosiveElementManager.Explosion)this.explosions.remove(0);
/* 130 */         System.err.println("Executing explosion for " + ExplosiveElementManager.Explosion.access$000(localExplosion));
/*     */         Transform localTransform;
/* 131 */         (
/* 132 */           localTransform = new Transform())
/* 132 */           .setIdentity();
/* 133 */         localTransform.origin.set(ExplosiveElementManager.Explosion.access$100(localExplosion));
/*     */ 
/* 135 */         ExplosiveElementManager.Explosion.access$000(localExplosion).handleExplosion(localTransform, 16.0F, 200.0F, ExplosiveElementManager.Explosion.access$200(localExplosion), ExplosiveElementManager.Explosion.access$300(localExplosion));
/* 136 */         ExplosiveElementManager.Explosion.access$000(localExplosion).getNetworkObject().explosions.add(new RemoteVector3f(ExplosiveElementManager.Explosion.access$000(localExplosion).getNetworkObject(), ExplosiveElementManager.Explosion.access$100(localExplosion)));
/*     */       }
/*     */ 
/* 139 */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveElementManager
 * JD-Core Version:    0.6.2
 */