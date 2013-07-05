/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*     */ import jL;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import ka;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import q;
/*     */ import s;
/*     */ 
/*     */ public abstract class PointDistributionCustomOutputUnit extends PointDistributionUnit
/*     */ {
/*  11 */   private q output = new q(0, 0, 0);
/*     */ 
/*     */   public q getOutput()
/*     */   {
/*  16 */     return this.output;
/*     */   }
/*     */   public void onChangeFinished() {
/*  19 */     super.onChangeFinished();
/*  20 */     this.output.b(getSignificator());
/*  21 */     le localle1 = new le();
/*  22 */     int i = 0;
/*     */     try
/*     */     {
/*  25 */       localq = new q();
/*  26 */       for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext(); ) {
/*  27 */         getPosFromIndex(((Long)localIterator.next()).longValue(), 
/*  27 */           localq);
/*     */         le localle2;
/*  30 */         if (((
/*  30 */           localle2 = getController().getSegmentBuffer().a(localq, false, localle1)) != null) && 
/*  30 */           (localle2.a()) && (localle2.a() == getClazzId())) {
/*  31 */           this.output.b(localq);
/*     */ 
/*  33 */           i = 1;
/*  34 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */       Iterator localIterator;
/*  37 */       localq = null;
/*     */ 
/*  41 */       localIOException.printStackTrace();
/*     */     } catch (InterruptedException localInterruptedException) {
/*  39 */       q localq = null;
/*     */ 
/*  41 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */ 
/*  42 */     if (i == 0)
/*     */     {
/*  44 */       this.output.b(getSignificator());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setMainPiece(le paramle, boolean paramBoolean)
/*     */   {
/*  51 */     if (paramBoolean)
/*     */     {
/*     */       le localle;
/*     */       q localq;
/*     */       Iterator localIterator;
/*  52 */       if (paramle.a().a().isOnServer())
/*     */       {
/*  54 */         localle = new le();
/*     */ 
/*  56 */         localq = new q();
/*     */ 
/*  58 */         for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext(); ) {
/*  59 */           getPosFromIndex(((Long)localIterator.next()).longValue(), 
/*  59 */             localq);
/*  60 */           if (!paramle.a(localq))
/*     */           {
/*     */             try
/*     */             {
/*  64 */               getController().getSegmentBuffer().a(localq, false, localle);
/*  65 */               if (localle.a())
/*     */               {
/*     */                 s locals;
/*  67 */                 (
/*  68 */                   locals = new s())
/*  68 */                   .a(localq.a, localq.b, localq.c, -2);
/*  69 */                 ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/*     */               }
/*     */ 
/*     */             }
/*     */             catch (IOException localIOException)
/*     */             {
/*  77 */               localIOException.printStackTrace();
/*     */             }
/*     */             catch (InterruptedException localInterruptedException)
/*     */             {
/*  77 */               localInterruptedException.printStackTrace();
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  81 */       System.err.println("NEW OUTPUT SET: " + paramle.a(new q()) + "; ACTIVE: " + paramBoolean);
/*  82 */       this.output.b(paramle.a(new q()));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setMainPiece()
/*     */   {
/*  88 */     le localle = new le();
/*     */ 
/*  90 */     q localq = new q();
/*     */ 
/*  92 */     for (Iterator localIterator = getNeighboringCollection().iterator(); localIterator.hasNext(); ) {
/*  93 */       getPosFromIndex(((Long)localIterator.next()).longValue(), 
/*  93 */         localq);
/*     */       try
/*     */       {
/*  98 */         if ((
/*  98 */           localle = getController().getSegmentBuffer().a(localq, false, localle))
/*  98 */           .a())
/*     */         {
/*     */           s locals;
/* 100 */           (
/* 101 */             locals = new s())
/* 101 */             .a(localq.a, localq.b, localq.c, -2);
/* 102 */           ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/*     */         }
/*     */ 
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 110 */         localIOException.printStackTrace();
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 110 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 112 */     System.err.println("NEW OUTPUT SET: " + localle.a(new q()) + ";");
/* 113 */     this.output.b(localle.a(new q()));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionCustomOutputUnit
 * JD-Core Version:    0.6.2
 */