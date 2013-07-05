/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import org.schema.game.client.view.SegmentDrawer;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ 
/*     */ public final class dd extends Thread
/*     */ {
/*     */   private dL jdField_a_of_type_DL;
/*     */   private mr jdField_a_of_type_Mr;
/*     */   private mr b;
/*     */   private de jdField_a_of_type_De;
/*     */ 
/*     */   public dd(SegmentDrawer paramSegmentDrawer, de paramde)
/*     */   {
/* 179 */     super("LightUpdate" + SegmentDrawer.jdField_a_of_type_Int++);
/* 180 */     setPriority(4);
/* 181 */     this.jdField_a_of_type_DL = new dL();
/* 182 */     this.jdField_a_of_type_De = paramde;
/*     */   }
/*     */ 
/*     */   public final void a(mr parammr) {
/* 186 */     synchronized (this) {
/* 187 */       this.jdField_a_of_type_Mr = parammr;
/* 188 */       notify();
/* 189 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(mr parammr, CubeMeshBufferContainer paramCubeMeshBufferContainer, dI paramdI) {
/*     */     while (true) try { if ((!parammr.g()) && (parammr.a() != null)) {
/* 195 */           synchronized (
/* 196 */             localSegmentData1 = parammr.a())
/*     */           {
/* 197 */             SegmentData localSegmentData1;
/* 197 */             CubeMeshBufferContainer localCubeMeshBufferContainer = paramCubeMeshBufferContainer; SegmentData localSegmentData2 = localSegmentData1; dd localdd = this; if (localSegmentData2.getSize() > 0) { if ((!jdField_a_of_type_Boolean) && (localSegmentData2 == null)) throw new AssertionError(); localdd.jdField_a_of_type_DL.a(localCubeMeshBufferContainer);
/*     */             }
/* 199 */             localCubeMeshBufferContainer = paramCubeMeshBufferContainer; localSegmentData2 = localSegmentData1; localdd = this; if ((!jdField_a_of_type_Boolean) && (localSegmentData2 == null)) throw new AssertionError(); localdd.jdField_a_of_type_DL.a(localSegmentData2, localCubeMeshBufferContainer);
/*     */ 
/* 201 */             SegmentDrawer.a(paramdI, localSegmentData1, paramCubeMeshBufferContainer);
/* 202 */             return;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 208 */         return;
/*     */       }
/*     */       catch (Exception localException2)
/*     */       {
/*     */         Exception localException1;
/* 204 */         (
/* 205 */           localException1 = 
/* 208 */           localException2).printStackTrace();
/* 206 */         System.err.println("[CLIENT] Exception: " + localException1.getClass().getSimpleName() + " in computing Lighting. retrying");
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 262 */       while (!xm.a()) {
/* 263 */         synchronized (this) {
/* 264 */           while ((this.jdField_a_of_type_Mr == null) && (this.b == null)) {
/* 265 */             wait();
/*     */           }
/* 267 */           this.b = this.jdField_a_of_type_Mr;
/* 268 */           this.jdField_a_of_type_Mr = null;
/*     */         }
/* 270 */         mr localmr = this.b; ??? = this; if ((!jdField_a_of_type_Boolean) && (localmr.b() != null)) throw new AssertionError(); synchronized (localmr.a) { dI localdI = SegmentDrawer.jdField_a_of_type_DH.a(localmr); localmr.a(localdI); CubeMeshBufferContainer localCubeMeshBufferContainer = localmr.a(); ((dd)???).a(localmr, localCubeMeshBufferContainer, localdI); synchronized (((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_JavaUtilArrayList)
/*     */           {
/* 270 */             int i;
/* 270 */             if ((i = ((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_JavaUtilArrayList.indexOf(localmr)) >= 0) { if ((!jdField_a_of_type_Boolean) && (localmr.getId() == ((mr)((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_JavaUtilArrayList.get(i)).getId())) throw new AssertionError(); synchronized (SegmentDrawer.a(((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) { SegmentDrawer.a(((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).add(((dd)???).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_JavaUtilArrayList.get(i)); } localObject1.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_JavaUtilArrayList.remove(i); } localObject1.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_JavaUtilArrayList.add(localmr); }  } localObject3.jdField_a_of_type_De.jdField_a_of_type_Int += 1;
/*     */ 
/* 272 */         Thread.sleep(3L);
/*     */ 
/* 274 */         this.jdField_a_of_type_De.a(this, this.b, true);
/* 275 */         this.b = null;
/*     */       }
/*     */       return;
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 280 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dd
 * JD-Core Version:    0.6.2
 */