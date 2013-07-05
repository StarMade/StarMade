/*     */ package org.schema.game.common.controller.elements.missile;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Vector3f;
/*     */ import lb;
/*     */ import lk;
/*     */ import ll;
/*     */ import lm;
/*     */ import ln;
/*     */ import lo;
/*     */ import mF;
/*     */ import org.schema.game.network.objects.NetworkClientChannel;
/*     */ import org.schema.schine.network.server.ServerState;
/*     */ import vg;
/*     */ import xq;
/*     */ 
/*     */ public class MissileController
/*     */ {
/*     */   private vg state;
/*     */   private final boolean onServer;
/*     */   private static short missileIdCreator;
/*     */   private final lo missileManager;
/*     */ 
/*     */   public MissileController(vg paramvg)
/*     */   {
/*  32 */     this.state = paramvg;
/*  33 */     this.onServer = (paramvg instanceof ServerState);
/*  34 */     this.missileManager = new lo(paramvg);
/*     */   }
/*     */ 
/*     */   public void addDumbMissile(lb paramlb, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/*     */   {
/*     */     lk locallk;
/*  38 */     (
/*  39 */       locallk = new lk(this.state))
/*  39 */       .a(paramFloat2);
/*  40 */     locallk.c(paramFloat1);
/*  41 */     locallk.b(paramFloat4);
/*  42 */     locallk.a((int)paramFloat3);
/*  43 */     addMissile(locallk, paramTransform, paramVector3f, paramlb);
/*     */   }
/*     */   public void addFafoMissile(lb paramlb, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, mF parammF) {
/*  46 */     System.err.println("ADDING FAFO MISSILE!!!!!!!!!!!!!!! Dir: " + paramVector3f + " onServer: " + isOnServer());
/*     */     ll localll;
/*  47 */     (
/*  48 */       localll = new ll(this.state))
/*  48 */       .a(paramFloat2);
/*  49 */     localll.c(paramFloat1);
/*  50 */     localll.b(paramFloat4);
/*  51 */     localll.a((int)paramFloat3);
/*  52 */     paramVector3f.normalize();
/*  53 */     System.err.println("MISSILE TARGET " + parammF);
/*  54 */     localll.a(parammF);
/*  55 */     addMissile(localll, paramTransform, paramVector3f, paramlb);
/*     */   }
/*     */ 
/*     */   public void addHeatMissile(lb paramlb, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/*     */   {
/*  61 */     System.err.println("ADDING HEAT MISSILE!!!!!!!!!!!!!!!");
/*     */     lm locallm;
/*  62 */     (
/*  63 */       locallm = new lm(this.state))
/*  63 */       .a(paramFloat2);
/*  64 */     locallm.c(paramFloat1);
/*  65 */     locallm.b(paramFloat4);
/*  66 */     locallm.a((int)paramFloat3);
/*     */ 
/*  68 */     addMissile(locallm, paramTransform, paramVector3f, paramlb);
/*     */   }
/*     */ 
/*     */   public void addMissile(ln paramln, Transform paramTransform, Vector3f paramVector3f, lb paramlb)
/*     */   {
/*  73 */     assert (isOnServer());
/*  74 */     if (paramlb == null) {
/*  75 */       throw new NullPointerException("OWNER NULL");
/*     */     }
/*  77 */     System.err.println("[MISSILE] NEW MISSILE: " + paramTransform.origin + "; " + paramln + ": Speed: " + paramln.c() + ", Damage: " + paramln.a() + ", Radius: " + paramln.a() + ", Lifetime: " + paramln.b());
/*  78 */     paramln.a(paramlb);
/*  79 */     paramln.b(((mF)paramlb).getSectorId());
/*     */ 
/*  81 */     paramln.a().set(paramTransform);
/*  82 */     paramln.b().set(paramTransform);
/*  83 */     paramln.a(paramVector3f);
/*  84 */     paramln.a(missileIdCreator++);
/*     */ 
/*  86 */     this.missileManager.a(paramln);
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/*  94 */     return this.onServer;
/*     */   }
/*     */ 
/*     */   public void fromNetwork(NetworkClientChannel paramNetworkClientChannel) {
/*  98 */     this.missileManager.a(paramNetworkClientChannel);
/*     */   }
/*     */ 
/*     */   public void updateServer(xq paramxq) {
/* 102 */     this.missileManager.a(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileController
 * JD-Core Version:    0.6.2
 */