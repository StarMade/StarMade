/*     */ package org.schema.game.common.controller.elements.factory;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import eH;
/*     */ import ij;
/*     */ import jD;
/*     */ import jL;
/*     */ import ja;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import ld;
/*     */ import le;
/*     */ import mf;
/*     */ import mh;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.FactoryResource;
/*     */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*     */ import q;
/*     */ 
/*     */ public class FactoryCollectionManager extends ControlBlockElementCollectionManager
/*     */ {
/*  59 */   private q absPos = new q();
/*  60 */   private int capability = 1;
/*     */ 
/* 114 */   private final q posTmp = new q();
/*     */   private long lastCheck;
/*     */ 
/*     */   public FactoryCollectionManager(short paramShort, le paramle, SegmentController paramSegmentController)
/*     */   {
/*  33 */     super(paramle, paramShort, paramSegmentController);
/*     */   }
/*     */ 
/*     */   public int getMargin()
/*     */   {
/*  38 */     return 0;
/*     */   }
/*     */ 
/*     */   protected Class getType()
/*     */   {
/*  43 */     return FactoryUnit.class;
/*     */   }
/*     */ 
/*     */   protected void onChangedCollection()
/*     */   {
/*  48 */     refreshEnhancerCapabiities();
/*     */   }
/*     */   public void refreshEnhancerCapabiities() {
/*  51 */     this.capability = 1;
/*  52 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((FactoryUnit)localIterator.next())
/*  53 */         .refreshFactoryCapabilities(this);
/*     */   }
/*     */ 
/*     */   public void manufractureStep(FactoryElementManager paramFactoryElementManager, HashMap paramHashMap)
/*     */   {
/*  67 */     if (!consumePower(paramFactoryElementManager))
/*     */     {
/*  69 */       return;
/*     */     }
/*     */ 
/*  72 */     if (getSegmentController().isOnServer())
/*     */     {
/*  74 */       q localq = getControllerElement().a(this.absPos);
/*  75 */       jD localjD = paramFactoryElementManager.getControlElementMap().getControlledElements((short)32767, localq);
/*     */       mf localmf;
/*  84 */       if ((
/*  84 */         localmf = ((ld)getSegmentController()).a().getInventory(localq)) == null)
/*     */       {
/*  85 */         System.err.println("[SERVER][FACTORY] Exception: Factory has no own inventory: " + getSegmentController() + " -> " + localq); return;
/*     */       }
/*  87 */       if (!paramFactoryElementManager.isInputFactory()) {
/*  88 */         int i = paramFactoryElementManager.getProductCount();
/*     */ 
/*  90 */         for (int j = 0; j < i; j++)
/*     */         {
/*     */           HashSet localHashSet;
/*  92 */           if ((
/*  92 */             localHashSet = (HashSet)paramHashMap.get(localmf)) == null)
/*     */           {
/*  93 */             localHashSet = new HashSet();
/*  94 */             paramHashMap.put(localmf, localHashSet);
/*     */           }
/*     */ 
/*  97 */           handleProduct(localjD, paramFactoryElementManager, j, localmf, paramHashMap, localHashSet);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void handleProduct(jD paramjD, FactoryElementManager paramFactoryElementManager, int paramInt, mf parammf, HashMap paramHashMap, HashSet paramHashSet)
/*     */   {
/* 118 */     for (ja localja : paramjD.a)
/*     */     {
/* 120 */       this.posTmp.b(localja.a, localja.b, localja.c);
/*     */       mf localmf;
/*     */       int i;
/*     */       le localle;
/* 124 */       if ((
/* 124 */         localmf = ((ld)getSegmentController()).a().getInventory(this.posTmp)) != null)
/*     */       {
/* 127 */         if ((
/* 127 */           localObject = (HashSet)paramHashMap.get(localmf)) == null)
/*     */         {
/* 128 */           localObject = new HashSet();
/* 129 */           paramHashMap.put(localmf, localObject);
/*     */         }
/*     */ 
/* 132 */         for (localja : paramFactoryElementManager.getInputType(paramInt))
/*     */         {
/*     */           int k;
/* 136 */           if ((
/* 136 */             k = localmf.b(localja.type)) >= 
/* 136 */             getCount(localja)) try {
/* 137 */               localmf.a(localja.type, (Collection)localObject);
/*     */ 
/* 144 */               k = localmf.b(localja.type, k - getCount(localja));
/*     */ 
/* 148 */               ((HashSet)localObject).add(Integer.valueOf(k));
/*     */ 
/* 151 */               i = parammf.b(localja.type, getCount(localja));
/*     */ 
/* 154 */               paramHashSet.add(Integer.valueOf(i));
/*     */             } catch (NoSlotFreeException localNoSlotFreeException1) {
/* 156 */               localle = null;
/*     */ 
/* 158 */               localNoSlotFreeException1.printStackTrace();
/*     */             }
/*     */ 
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/* 163 */       else if (System.currentTimeMillis() - this.lastCheck > 1000L)
/*     */       {
/*     */         try {
/* 166 */           if (((
/* 166 */             localle = getSegmentController().getSegmentBuffer().a(this.posTmp, true)) == null) || 
/* 166 */             (localle.a() != 212))
/*     */           {
/* 169 */             System.err.println("[FACTORY] " + getSegmentController() + ": no inventory found at " + i + "; ControllerPos: " + getControllerPos() + "; NOW loaded supposed inventory position (Unsave Point): " + localle);
/*     */           }
/*     */         } catch (IOException localIOException) { localle = null;
/*     */ 
/* 175 */           localIOException.printStackTrace();
/*     */         } catch (InterruptedException localInterruptedException) {
/* 173 */           localle = null;
/*     */ 
/* 175 */           localInterruptedException.printStackTrace();
/*     */         }
/*     */ 
/* 176 */         this.lastCheck = System.currentTimeMillis();
/*     */       }
/*     */     }
/*     */     Object localObject;
/* 180 */     paramjD = 1;
/* 181 */     for (localObject : paramFactoryElementManager.getInputType(paramInt))
/*     */     {
/* 185 */       if (parammf.b(((FactoryResource)localObject).type) < 
/* 185 */         getCount((FactoryResource)localObject)) {
/* 186 */         paramjD = 0;
/*     */       }
/*     */     }
/* 189 */     if (paramjD != 0)
/*     */     {
/*     */       int n;
/* 190 */       for (localObject : paramFactoryElementManager.getInputType(paramInt)) {
/* 191 */         n = parammf.b(((FactoryResource)localObject).type);
/*     */ 
/* 193 */         parammf.a(((FactoryResource)localObject).type, paramHashSet);
/*     */         try
/*     */         {
/* 196 */           ??? = parammf.b(((FactoryResource)localObject).type, n - getCount((FactoryResource)localObject));
/*     */ 
/* 198 */           paramHashSet.add(Integer.valueOf(???));
/*     */         }
/*     */         catch (NoSlotFreeException localNoSlotFreeException2)
/*     */         {
/* 202 */           localNoSlotFreeException2.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 204 */       for (localObject : paramFactoryElementManager.getOutputType(paramInt))
/*     */         try {
/* 206 */           n = parammf.b(((FactoryResource)localObject).type, getCount((FactoryResource)localObject));
/*     */ 
/* 208 */           paramHashSet.add(Integer.valueOf(n));
/*     */         }
/*     */         catch (NoSlotFreeException localNoSlotFreeException3) {
/* 211 */           localNoSlotFreeException3.printStackTrace();
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean consumePower(FactoryElementManager paramFactoryElementManager)
/*     */   {
/* 217 */     boolean bool = (
/* 217 */       paramFactoryElementManager = ((PowerManagerInterface)paramFactoryElementManager.getManagerContainer()).getPowerAddOn())
/* 217 */       .consumePowerInstantly(this.capability * 500);
/*     */ 
/* 219 */     if ((!getSegmentController().isOnServer()) && (!bool))
/*     */     {
/*     */       Transform localTransform;
/* 220 */       (
/* 221 */         localTransform = new Transform())
/* 221 */         .setIdentity();
/* 222 */       q localq = getControllerElement().a(this.absPos);
/* 223 */       localTransform.origin.set(localq.a - 8, localq.b - 8, localq.c - 8);
/* 224 */       getSegmentController().getWorldTransform().transform(localTransform.origin);
/* 225 */       ij.a.add(new eH(localTransform, "Insufficient Energy (" + (int)paramFactoryElementManager.getPower() + " / " + this.capability * 100 + ")", 1.0F, 0.1F, 0.1F));
/*     */     }
/* 227 */     return bool;
/*     */   }
/*     */ 
/*     */   public int getCount(FactoryResource paramFactoryResource) {
/* 231 */     return paramFactoryResource.count * this.capability;
/*     */   }
/*     */   public void addCapability(int paramInt) {
/* 234 */     this.capability += paramInt;
/*     */   }
/*     */ 
/*     */   public boolean needsUpdate() {
/* 238 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryCollectionManager
 * JD-Core Version:    0.6.2
 */