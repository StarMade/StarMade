/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.util.ArrayList;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ final class jt
/*     */   implements Runnable
/*     */ {
/*     */   jt(js paramjs)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/*     */       while (true)
/*     */       {
/* 167 */         js.a(this.a);
/* 168 */         int i = 0;
/* 169 */         for (int j = 0; j < js.a(this.a).size(); j++)
/*     */         {
/*     */           SegmentController localSegmentController;
/* 171 */           if (((
/* 171 */             localSegmentController = (SegmentController)js.a(this.a).get(j))
/* 171 */             .getCreatorThread() != null) && (((ct)js.a(this.a)).a().containsKey(localSegmentController.getId())))
/*     */           {
/* 172 */             kG localkG;
/* 172 */             if (System.currentTimeMillis() - localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a > 10000L) { localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().b(); localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a = System.currentTimeMillis(); }
/* 173 */             if ((((localkG = localSegmentController.getCreatorThread()).jdField_a_of_type_Boolean) && (!xm.a()) ? localkG.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().b() : 0))
/*     */             {
/* 174 */               i = 1;
/* 175 */               Thread.sleep(5L);
/*     */             }
/*     */           }
/*     */           try {
/* 179 */             if (!js.a(this.a).getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(localSegmentController.getId())) {
/* 180 */               js.a(this.a).remove(j);
/* 181 */               j--;
/*     */             }
/*     */           }
/*     */           catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
/*     */           {
/* 186 */             localArrayIndexOutOfBoundsException.printStackTrace();
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 188 */         if (i == 0)
/* 189 */           Thread.sleep(20L); 
/*     */       }
/*     */     } catch (InterruptedException localInterruptedException) { localInterruptedException
/* 192 */         .printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jt
 * JD-Core Version:    0.6.2
 */