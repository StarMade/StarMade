/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*    */ import jL;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import ka;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ import q;
/*    */ import s;
/*    */ 
/*    */ public abstract class CustomOutputUnit extends ElementCollection
/*    */ {
/* 11 */   private q output = new q(0, 0, 0);
/*    */ 
/*    */   public q getOutput()
/*    */   {
/* 16 */     return this.output;
/*    */   }
/*    */   public void onChangeFinished() {
/* 19 */     super.onChangeFinished();
/* 20 */     le localle1 = new le();
/* 21 */     int i = 0;
/*    */     try {
/* 23 */       localq = new q();
/* 24 */       for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext(); ) {
/* 25 */         getPosFromIndex(((Long)localIterator.next()).longValue(), 
/* 25 */           localq);
/*    */         le localle2;
/* 29 */         if (((
/* 29 */           localle2 = getController().getSegmentBuffer().a(localq, false, localle1)) != null) && 
/* 29 */           (localle2.a())) {
/* 30 */           this.output.b(localq);
/*    */ 
/* 32 */           i = 1;
/* 33 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (IOException localIOException)
/*    */     {
/*    */       Iterator localIterator;
/* 36 */       localq = null;
/*    */ 
/* 40 */       localIOException.printStackTrace();
/*    */     } catch (InterruptedException localInterruptedException) {
/* 38 */       q localq = null;
/*    */ 
/* 40 */       localInterruptedException.printStackTrace();
/*    */     }
/*    */ 
/* 41 */     if (i == 0)
/* 42 */       this.output.b(getSignificator());
/*    */   }
/*    */ 
/*    */   public void setMainPiece(le paramle, boolean paramBoolean) {
/* 46 */     System.err.println("SET NEW MAIN PIECE: " + paramle.a(new q()) + "; ACTIVE: " + paramBoolean);
/* 47 */     if (paramBoolean)
/*    */     {
/*    */       le localle;
/*    */       q localq;
/*    */       Iterator localIterator;
/* 48 */       if (paramle.a().a().isOnServer())
/*    */       {
/* 50 */         localle = new le();
/*    */ 
/* 52 */         localq = new q();
/* 53 */         for (localIterator = getNeighboringCollection().iterator(); localIterator.hasNext(); ) {
/* 54 */           getPosFromIndex(((Long)localIterator.next()).longValue(), 
/* 54 */             localq);
/* 55 */           if (!paramle.a(localq)) {
/*    */             try
/*    */             {
/* 58 */               paramle.a().a().getSegmentBuffer().a(localq, false, localle);
/* 59 */               if (localle.a())
/*    */               {
/*    */                 s locals;
/* 61 */                 (
/* 62 */                   locals = new s())
/* 62 */                   .a(localq.a, localq.b, localq.c, -2);
/* 63 */                 ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/*    */               }
/*    */ 
/*    */             }
/*    */             catch (IOException localIOException)
/*    */             {
/* 71 */               localIOException.printStackTrace();
/*    */             }
/*    */             catch (InterruptedException localInterruptedException)
/*    */             {
/* 71 */               localInterruptedException.printStackTrace();
/*    */             }
/*    */           }
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 75 */       System.err.println("NEW OUTPUT SET: " + paramle.a(new q()) + "; ACTIVE: " + paramBoolean);
/* 76 */       this.output.b(paramle.a(new q()));
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.CustomOutputUnit
 * JD-Core Version:    0.6.2
 */