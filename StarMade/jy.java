/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.element.factory.FactoryElement;
/*     */ import org.schema.game.common.data.element.factory.ManufacturingElement;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ 
/*     */ public class jy extends EditableSendableSegmentController
/*     */   implements km
/*     */ {
/*     */   private boolean a;
/*     */ 
/*     */   public jy(StateInterface paramStateInterface)
/*     */   {
/*  31 */     super(paramStateInterface);
/*     */   }
/*     */ 
/*     */   public boolean allowedType(short paramShort)
/*     */   {
/*  46 */     if ((!FactoryElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0);
/*  47 */     if ((!ManufacturingElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0);
/*  48 */     if ((paramShort != 121 ? 1 : 0) != 0)
/*  48 */       if (super.allowedType(paramShort)) return true; 
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isEmptyOnServer()
/*     */   {
/*  64 */     if (!this.a)
/*     */     {
/*  70 */       return false;
/*     */     }
/*     */ 
/*  73 */     return super.isEmptyOnServer();
/*     */   }
/*     */ 
/*     */   public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*     */   {
/*  87 */     super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*  88 */     if ((isOnServer()) && (getTotalElements() <= 0)) {
/*  89 */       System.err.println("[FLOATINGROCK] DESTROYING " + this + " -> TOTAL ELEMENTS: " + getTotalElements());
/*  90 */       destroy();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void handleHitMissile(ln paramln, Transform paramTransform)
/*     */   {
/* 101 */     super.handleHitMissile(paramln, paramTransform);
/* 102 */     if ((isOnServer()) && (getTotalElements() <= 0))
/* 103 */       destroy();
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 116 */     super.initialize();
/* 117 */     setMass(0.0F);
/*     */   }
/*     */ 
/*     */   public final boolean a(String[] paramArrayOfString, q paramq) {
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   protected void onCoreDestroyed(lb paramlb)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void startCreatorThread()
/*     */   {
/* 134 */     if (getCreatorThread() == null)
/*     */     {
/* 136 */       setCreatorThread(new kD(this, kF.values()[getCreatorId()]));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String toNiceString()
/*     */   {
/* 143 */     if (!isOnServer())
/*     */     {
/*     */       Sendable localSendable;
/* 145 */       if ((
/* 145 */         localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId())) != null)
/*     */       {
/* 146 */         return "Rock " + ((mv)localSendable).a();
/*     */       }
/*     */     }
/*     */ 
/* 150 */     return "Floating Rock <can be harvested>";
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 158 */     super.updateLocal(paramxq);
/*     */     try {
/* 160 */       if ((isOnServer()) && (getTotalElements() <= 0) && (System.currentTimeMillis() - getTimeCreated() > 3000L) && (isEmptyOnServer()))
/*     */       {
/* 164 */         System.err.println("[SERVER][FloatingRock] Empty rock: deleting!!!!!!!!!!!!!!!!!!! " + this);
/* 165 */         setMarkedForDeleteVolatile(true);
/*     */       }return; } catch (IOException localIOException) {
/* 171 */       localIOException.printStackTrace();
/*     */       return;
/*     */     } catch (InterruptedException localInterruptedException) {
/* 171 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 181 */     return "Asteroid(" + getId() + ")sec[" + getSectorId() + "]" + (this.a ? "(!)" : "");
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 191 */     this.a = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 201 */     return this.a;
/*     */   }
/*     */ 
/*     */   protected String getSegmentControllerTypeString()
/*     */   {
/* 211 */     return "Asteroid";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jy
 * JD-Core Version:    0.6.2
 */