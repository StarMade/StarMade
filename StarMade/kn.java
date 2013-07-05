/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.network.objects.NetworkVehicle;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ 
/*     */ public class kn extends EditableSendableSegmentController
/*     */   implements wp
/*     */ {
/*     */   public kn(StateInterface paramStateInterface)
/*     */   {
/*  31 */     super(paramStateInterface);
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*  39 */     if ((!a) && (!paramAd.a().equals(getClass().getSimpleName()))) throw new AssertionError();
/*  40 */     paramAd = (Ad[])paramAd.a();
/*     */ 
/*  43 */     super.fromTagStructure(paramAd[0]);
/*     */   }
/*     */ 
/*     */   protected short getCoreType()
/*     */   {
/*  61 */     return 65;
/*     */   }
/*     */ 
/*     */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*     */   {
/*  75 */     super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/*  86 */     super.initialize();
/*     */ 
/*  88 */     setMass(0.01F);
/*     */   }
/*     */ 
/*     */   public final boolean a(String[] paramArrayOfString, q paramq)
/*     */   {
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 107 */     setNetworkObject(new NetworkVehicle(getState(), this));
/*     */   }
/*     */ 
/*     */   protected void onCoreDestroyed(lb paramlb)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onPhysicsAdd()
/*     */   {
/* 121 */     super.onPhysicsAdd();
/*     */ 
/* 123 */     ((RigidBody)getPhysicsDataContainer().getObject())
/* 125 */       .setGravity(new Vector3f(0.0F, -1.89F, 0.0F));
/*     */   }
/*     */ 
/*     */   public void startCreatorThread()
/*     */   {
/* 132 */     if (getCreatorThread() == null)
/* 133 */       setCreatorThread(new kI(this));
/*     */   }
/*     */ 
/*     */   public String toNiceString()
/*     */   {
/* 139 */     return "Vehicle";
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 152 */     return new Ad(Af.n, getClass().getSimpleName(), new Ad[] { super.toTagStructure(), new Ad(Af.a, null, null) });
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 164 */     super.updateFromNetworkObject(paramNetworkObject);
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 175 */     super.updateLocal(paramxq);
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 183 */     super.updateToFullNetworkObject();
/*     */   }
/*     */ 
/*     */   protected String getSegmentControllerTypeString()
/*     */   {
/* 189 */     return "Vehicle";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kn
 * JD-Core Version:    0.6.2
 */