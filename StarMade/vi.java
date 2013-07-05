/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.server.controller.EntityAlreadyExistsException;
/*     */ import org.schema.game.server.controller.EntityNotFountException;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ 
/*     */ final class vi extends Thread
/*     */ {
/*     */   private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*     */ 
/*     */   private vi(vg paramvg)
/*     */   {
/* 595 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */   }
/*     */ 
/*     */   public final void run() {
/* 599 */     ByteBuffer localByteBuffer = ByteBuffer.allocate(1024000);
/*     */     while (true)
/*     */     {
/*     */       vj localvj;
/* 603 */       synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 604 */         if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/*     */           try {
/* 606 */             this.jdField_a_of_type_JavaUtilArrayList.wait();
/*     */           }
/*     */           catch (InterruptedException localInterruptedException) {
/* 609 */             localInterruptedException.printStackTrace();
/*     */           }
/* 609 */           continue;
/*     */         }
/* 611 */         localvj = (vj)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/*     */       }
/* 613 */       ??? = new Transform();
/* 614 */       List localList = localvj.jdField_a_of_type_TH.a();
/*     */ 
/* 617 */       ArrayList localArrayList = new ArrayList();
/* 618 */       for (int i = 0; i < localvj.jdField_a_of_type_Int; i++) {
/* 619 */         ((Transform)???).set(localvj.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */ 
/* 621 */         ((Transform)???).origin.set(((Transform)???).origin.x + (float)(Math.random() - 0.5D) * 256.0F, ((Transform)???).origin.y + (float)(Math.random() - 0.5D) * 256.0F, ((Transform)???).origin.z + (float)(Math.random() - 0.5D) * 256.0F);
/*     */         Object localObject2;
/* 624 */         if (ve.a(localObject2 = "MOB_" + localvj.jdField_a_of_type_JavaLangString + "_" + System.currentTimeMillis() + "_" + i))
/*     */         {
/*     */           try
/*     */           {
/* 627 */             (
/* 629 */               localObject2 = tH.a(this.jdField_a_of_type_Vg, localvj.jdField_a_of_type_JavaLangString, (String)localObject2, (Transform)???, -1, localvj.c, localList, localvj.b, localArrayList, "<system>", localByteBuffer)).jdField_a_of_type_Int = 
/* 629 */               localvj.b;
/* 630 */             synchronized (this.jdField_a_of_type_Vg.b()) {
/* 631 */               this.jdField_a_of_type_Vg.b().add(localObject2);
/*     */             } } catch (EntityNotFountException localEntityNotFountException) { localEntityNotFountException
/* 642 */               .printStackTrace(); } catch (IOException localIOException) { localIOException.printStackTrace(); }
/*     */           catch (ErrorDialogException localErrorDialogException)
/*     */           {
/* 642 */             localErrorDialogException.printStackTrace();
/*     */           }
/*     */           catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
/*     */           {
/* 642 */             localEntityAlreadyExistsException.printStackTrace();
/*     */           }
/*     */         }
/*     */         else
/* 644 */           System.err.println("[ADMIN] ERROR: Not a valid name: " + str);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, String arg2, int paramInt2, Transform paramTransform, int paramInt3, tH paramtH)
/*     */   {
/* 651 */     paramInt1 = new vj(paramInt1, ???, paramInt2, paramTransform, paramInt3, paramtH);
/* 652 */     synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 653 */       this.jdField_a_of_type_JavaUtilArrayList.add(paramInt1);
/* 654 */       this.jdField_a_of_type_JavaUtilArrayList.notify();
/*     */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vi
 * JD-Core Version:    0.6.2
 */