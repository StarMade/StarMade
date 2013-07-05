/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import java.io.PrintStream;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*     */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.game.network.objects.NetworkTeamDeathStar;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ public class kl extends EditableSendableSegmentController
/*     */ {
/*     */   public kl(StateInterface paramStateInterface)
/*     */   {
/*  31 */     super(paramStateInterface);
/*     */   }
/*     */ 
/*     */   public boolean allowedType(short paramShort)
/*     */   {
/*     */     int i;
/*  41 */     if (((
/*  41 */       i = (paramShort != 121) && (!SpaceStationElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType())) ? 1 : 0) == 0) && 
/*  41 */       (!isOnServer())) {
/*  42 */       ((x)getState().getController()).b("Cannot place\n" + ElementKeyMap.getInfo(paramShort).getName() + "\non a death star");
/*     */     }
/*     */ 
/*  47 */     return (super.allowedType(paramShort)) && (i != 0);
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*  59 */     if ((!a) && (!paramAd.a().equals("DeathStar"))) throw new AssertionError();
/*     */ 
/*  62 */     ((Integer)(
/*  62 */       paramAd = (Ad[])paramAd.a())[
/*  62 */       0].a()).intValue();
/*     */ 
/*  72 */     super.fromTagStructure(paramAd[1]);
/*     */   }
/*     */ 
/*     */   protected short getCoreType()
/*     */   {
/*  77 */     return 65;
/*     */   }
/*     */ 
/*     */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*     */   {
/*  93 */     super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*     */ 
/*  97 */     if (!isOnServer())
/*     */     {
/*  99 */       if (((
/*  99 */         paramlb = (CubeRayCastResult)paramClosestRayResultCallback)
/*  99 */         .hasHit()) && (paramlb.getSegment() != null) && (!paramlb.getSegment().g())) {
/* 100 */         paramlb.getSegment().a();
/* 101 */         paramFloat = SegmentData.getInfoIndex(paramlb.cubePos);
/*     */ 
/* 103 */         if (paramlb.getSegment().a().getType(paramFloat) == 
/* 103 */           65) {
/* 104 */           new StringBuilder("WARNING!\nYour base's CORE is unter attack!\nHP left: ").append(paramlb.getSegment().a().getHitpoints(paramFloat)).toString();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 112 */       ((ct)getState())
/* 113 */         .a().a().a(paramClosestRayResultCallback);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 137 */     super.initialize();
/*     */ 
/* 139 */     setMass(0.0F);
/*     */   }
/*     */ 
/*     */   public final boolean a(String[] paramArrayOfString, q paramq)
/*     */   {
/* 151 */     if (kd.a.equals(paramq)) {
/* 152 */       paramArrayOfString[0] = "Can't salvage core!";
/* 153 */       return false;
/*     */     }
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 167 */     setNetworkObject(new NetworkTeamDeathStar(getState(), this));
/*     */   }
/*     */ 
/*     */   protected void onCoreDestroyed(lb paramlb)
/*     */   {
/* 175 */     System.err.println("DEATHSTAR HAS BEEN DESTROYED BY !!!! " + paramlb);
/* 176 */     if (isOnServer()) {
/* 177 */       int i = getFactionId();
/* 178 */       paramlb = (paramlb instanceof mF) ? ((mF)paramlb).getFactionId() : 0;
/* 179 */       ((vg)getState()).a().a(paramlb, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startCreatorThread()
/*     */   {
/* 187 */     if (getCreatorThread() == null)
/* 188 */       setCreatorThread(new kH(this));
/*     */   }
/*     */ 
/*     */   public String toNiceString()
/*     */   {
/* 194 */     return "Death Star";
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 203 */     Ad localAd = new Ad(Af.d, "team", Integer.valueOf(getFactionId()));
/*     */ 
/* 205 */     return new Ad(Af.n, "DeathStar", new Ad[] { localAd, super.toTagStructure(), new Ad(Af.a, null, null) });
/*     */   }
/*     */ 
/*     */   protected String getSegmentControllerTypeString()
/*     */   {
/* 213 */     return "DeathStar";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kl
 * JD-Core Version:    0.6.2
 */