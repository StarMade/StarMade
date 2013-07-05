/*     */ package org.newdawn.slick.loading;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.newdawn.slick.openal.SoundStore;
/*     */ import org.newdawn.slick.opengl.InternalTextureLoader;
/*     */ import org.newdawn.slick.util.Log;
/*     */ 
/*     */ public class LoadingList
/*     */ {
/*  17 */   private static LoadingList single = new LoadingList();
/*     */ 
/*  50 */   private ArrayList deferred = new ArrayList();
/*     */   private int total;
/*     */ 
/*     */   public static LoadingList get()
/*     */   {
/*  25 */     return single;
/*     */   }
/*     */ 
/*     */   public static void setDeferredLoading(boolean loading)
/*     */   {
/*  34 */     single = new LoadingList();
/*     */ 
/*  36 */     InternalTextureLoader.get().setDeferredLoading(loading);
/*  37 */     SoundStore.get().setDeferredLoading(loading);
/*     */   }
/*     */ 
/*     */   public static boolean isDeferredLoading()
/*     */   {
/*  46 */     return InternalTextureLoader.get().isDeferredLoading();
/*     */   }
/*     */ 
/*     */   public void add(DeferredResource resource)
/*     */   {
/*  66 */     this.total += 1;
/*  67 */     this.deferred.add(resource);
/*     */   }
/*     */ 
/*     */   public void remove(DeferredResource resource)
/*     */   {
/*  77 */     Log.info("Early loading of deferred resource due to req: " + resource.getDescription());
/*  78 */     this.total -= 1;
/*  79 */     this.deferred.remove(resource);
/*     */   }
/*     */ 
/*     */   public int getTotalResources()
/*     */   {
/*  88 */     return this.total;
/*     */   }
/*     */ 
/*     */   public int getRemainingResources()
/*     */   {
/*  97 */     return this.deferred.size();
/*     */   }
/*     */ 
/*     */   public DeferredResource getNext()
/*     */   {
/* 106 */     if (this.deferred.size() == 0) {
/* 107 */       return null;
/*     */     }
/*     */ 
/* 110 */     return (DeferredResource)this.deferred.remove(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.loading.LoadingList
 * JD-Core Version:    0.6.2
 */