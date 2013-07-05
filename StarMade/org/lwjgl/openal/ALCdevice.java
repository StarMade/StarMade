/*     */ package org.lwjgl.openal;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public final class ALCdevice
/*     */ {
/*     */   final long device;
/*     */   private boolean valid;
/*  60 */   private final HashMap<Long, ALCcontext> contexts = new HashMap();
/*     */ 
/*     */   ALCdevice(long device)
/*     */   {
/*  68 */     this.device = device;
/*  69 */     this.valid = true;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object device)
/*     */   {
/*  76 */     if ((device instanceof ALCdevice)) {
/*  77 */       return ((ALCdevice)device).device == this.device;
/*     */     }
/*  79 */     return super.equals(device);
/*     */   }
/*     */ 
/*     */   void addContext(ALCcontext context)
/*     */   {
/*  88 */     synchronized (this.contexts) {
/*  89 */       this.contexts.put(Long.valueOf(context.context), context);
/*     */     }
/*     */   }
/*     */ 
/*     */   void removeContext(ALCcontext context)
/*     */   {
/*  99 */     synchronized (this.contexts) {
/* 100 */       this.contexts.remove(Long.valueOf(context.context));
/*     */     }
/*     */   }
/*     */ 
/*     */   void setInvalid()
/*     */   {
/* 108 */     this.valid = false;
/* 109 */     synchronized (this.contexts) {
/* 110 */       for (ALCcontext context : this.contexts.values())
/* 111 */         context.setInvalid();
/*     */     }
/* 113 */     this.contexts.clear();
/*     */   }
/*     */ 
/*     */   public boolean isValid()
/*     */   {
/* 120 */     return this.valid;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.ALCdevice
 * JD-Core Version:    0.6.2
 */