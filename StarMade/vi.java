/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.io.IOException;
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.List;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */import org.schema.game.server.controller.EntityAlreadyExistsException;
/*   9:    */import org.schema.game.server.controller.EntityNotFountException;
/*  10:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  11:    */
/* 589:    */final class vi
/* 590:    */  extends Thread
/* 591:    */{
/* 592:    */  private ArrayList jdField_a_of_type_JavaUtilArrayList;
/* 593:    */  
/* 594:    */  private vi(vg paramvg)
/* 595:    */  {
/* 596:596 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 597:    */  }
/* 598:    */  
/* 599:    */  public final void run() {
/* 600:600 */    ByteBuffer localByteBuffer = ByteBuffer.allocate(1024000);
/* 601:    */    for (;;)
/* 602:    */    {
/* 603:    */      vj localvj;
/* 604:604 */      synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 605:605 */        if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 606:    */          try {
/* 607:607 */            this.jdField_a_of_type_JavaUtilArrayList.wait();
/* 608:608 */          } catch (InterruptedException localInterruptedException) { 
/* 609:    */            
/* 610:610 */              localInterruptedException;
/* 611:    */          }
/* 612:610 */          continue;
/* 613:    */        }
/* 614:612 */        localvj = (vj)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/* 615:    */      }
/* 616:614 */      ??? = new Transform();
/* 617:615 */      List localList = localvj.jdField_a_of_type_TH.a();
/* 618:    */      
/* 620:618 */      ArrayList localArrayList = new ArrayList();
/* 621:619 */      for (int i = 0; i < localvj.jdField_a_of_type_Int; i++) {
/* 622:620 */        ((Transform)???).set(localvj.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 623:    */        
/* 624:622 */        ((Transform)???).origin.set(((Transform)???).origin.x + (float)(Math.random() - 0.5D) * 256.0F, ((Transform)???).origin.y + (float)(Math.random() - 0.5D) * 256.0F, ((Transform)???).origin.z + (float)(Math.random() - 0.5D) * 256.0F);
/* 625:    */        
/* 626:    */        Object localObject2;
/* 627:625 */        if (ve.a(localObject2 = "MOB_" + localvj.jdField_a_of_type_JavaLangString + "_" + System.currentTimeMillis() + "_" + i))
/* 628:    */        {
/* 629:    */          try
/* 630:    */          {
/* 632:630 */            (localObject2 = tH.a(this.jdField_a_of_type_Vg, localvj.jdField_a_of_type_JavaLangString, (String)localObject2, (Transform)???, -1, localvj.c, localList, localvj.b, localArrayList, "<system>", localByteBuffer)).jdField_a_of_type_Int = localvj.b;
/* 633:631 */            synchronized (this.jdField_a_of_type_Vg.b()) {
/* 634:632 */              this.jdField_a_of_type_Vg.b().add(localObject2);
/* 636:    */            }
/* 637:    */            
/* 640:    */          }
/* 641:    */          catch (EntityNotFountException localEntityNotFountException)
/* 642:    */          {
/* 645:643 */            localEntityNotFountException;
/* 646:    */          }
/* 647:    */          catch (IOException localIOException)
/* 648:    */          {
/* 649:637 */            
/* 650:    */            
/* 655:643 */              localIOException;
/* 656:    */          } catch (ErrorDialogException localErrorDialogException) {
/* 657:639 */            
/* 658:    */            
/* 661:643 */              localErrorDialogException;
/* 662:    */          } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) {
/* 663:641 */            
/* 664:    */            
/* 665:643 */              localEntityAlreadyExistsException;
/* 666:    */          }
/* 667:    */          
/* 668:    */        } else {
/* 669:645 */          System.err.println("[ADMIN] ERROR: Not a valid name: " + str);
/* 670:    */        }
/* 671:    */      }
/* 672:    */    }
/* 673:    */  }
/* 674:    */  
/* 675:    */  public final void a(int paramInt1, String arg2, int paramInt2, Transform paramTransform, int paramInt3, tH paramtH) {
/* 676:652 */    paramInt1 = new vj(paramInt1, ???, paramInt2, paramTransform, paramInt3, paramtH);
/* 677:653 */    synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 678:654 */      this.jdField_a_of_type_JavaUtilArrayList.add(paramInt1);
/* 679:655 */      this.jdField_a_of_type_JavaUtilArrayList.notify(); return;
/* 680:    */    }
/* 681:    */  }
/* 682:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */