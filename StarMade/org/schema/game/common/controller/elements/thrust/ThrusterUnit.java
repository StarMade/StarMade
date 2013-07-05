/*     */ package org.schema.game.common.controller.elements.thrust;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import q;
/*     */ 
/*     */ public class ThrusterUnit extends ElementCollection
/*     */ {
/*  15 */   private final q significator = new q();
/*     */   float thrust;
/*  19 */   private final HashMap lastElements = new HashMap();
/*     */ 
/*  22 */   private final Point pointTmp = new Point();
/*  23 */   private final q abspos = new q();
/*     */ 
/*     */   protected void onAdd(long paramLong)
/*     */   {
/*  28 */     long l1 = paramLong / 4294705156L;
/*     */ 
/*  31 */     long l2 = (
/*  31 */       paramLong = paramLong - l1 * 4294705156L) / 
/*  31 */       65534L;
/*     */ 
/*  34 */     paramLong = (int)(paramLong - l2 * 65534L - 
/*  34 */       32767L);
/*     */ 
/*  37 */     int i = (int)(l2 - 32767L);
/*  38 */     int j = (int)(l1 - 32767L);
/*     */ 
/*  40 */     this.pointTmp.setLocation(paramLong, i);
/*  41 */     if (!getLastElements().containsKey(this.pointTmp)) {
/*  42 */       localObject = new Point(paramLong, i);
/*  43 */       getLastElements().put(localObject, new q(paramLong, i, j));
/*  44 */       return;
/*  45 */     }Object localObject = (q)getLastElements().get(this.pointTmp);
/*  46 */     if (j < ((q)localObject).c)
/*  47 */       ((q)getLastElements().get(this.pointTmp)).b(paramLong, i, j);
/*     */   }
/*     */ 
/*     */   public boolean addElement(long paramLong)
/*     */   {
/*     */     boolean bool;
/*  59 */     if ((
/*  59 */       bool = super.addElement(paramLong)))
/*     */     {
/*  60 */       getPosFromIndex(paramLong, this.abspos);
/*     */ 
/*  62 */       onAdd(paramLong);
/*     */     }
/*  64 */     return bool;
/*     */   }
/*     */ 
/*     */   protected void onRemove(q paramq)
/*     */   {
/*  73 */     super.onRemove(paramq);
/*  74 */     paramq = new Point(paramq.a, paramq.b);
/*  75 */     q localq1 = (q)getLastElements().remove(paramq);
/*  76 */     q localq2 = new q(localq1);
/*  77 */     for (int i = localq1.c - 1; i > getMin().c; i--) {
/*  78 */       localq2.c = i;
/*     */ 
/*  80 */       long l = ElementCollection.getIndex(localq2);
/*  81 */       if (getNeighboringCollection().contains(Long.valueOf(l)))
/*  82 */         getLastElements().put(paramq, localq2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public q getSignificator()
/*     */   {
/*  90 */     return this.significator;
/*     */   }
/*     */ 
/*     */   public void refreshThrusterCapabilities() {
/*  94 */     this.thrust = Math.max(0.0F, getMax().c - getMin().c);
/*  95 */     this.thrust += Math.max(0.0F, getMax().a - getMin().a);
/*  96 */     this.thrust += Math.max(0.0F, getMax().b - getMin().b);
/*  97 */     float f = (float)Math.pow(size(), 1.125D);
/*     */ 
/*  99 */     this.thrust += f;
/* 100 */     this.thrust = Math.max(1.0F, this.thrust);
/*     */   }
/*     */ 
/*     */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 110 */     if ((paramInt1 <= this.significator.a) && (paramInt2 <= this.significator.b) && (paramInt3 < this.significator.c))
/* 111 */       this.significator.b(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   public HashMap getLastElements()
/*     */   {
/* 120 */     return this.lastElements;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterUnit
 * JD-Core Version:    0.6.2
 */