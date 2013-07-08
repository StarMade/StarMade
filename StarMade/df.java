/*   1:    */import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.Collections;
/*   6:    */import java.util.HashSet;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import org.schema.game.client.view.SegmentDrawer;
/*  11:    */import org.schema.game.common.controller.SegmentBufferManager;
/*  12:    */import org.schema.game.common.controller.SegmentController;
/*  13:    */import org.schema.game.common.data.world.Segment;
/*  14:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  15:    */
/* 469:    */public final class df
/* 470:    */  extends Thread
/* 471:    */{
/* 472:472 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 473:    */  
/* 474:    */  protected ArrayList a;
/* 475:    */  
/* 476:    */  private ArrayList jdField_b_of_type_JavaUtilArrayList;
/* 477:    */  
/* 478:    */  private db jdField_a_of_type_Db;
/* 479:    */  
/* 480:    */  private cZ jdField_a_of_type_CZ;
/* 481:    */  
/* 482:    */  private dh jdField_a_of_type_Dh;
/* 483:    */  private dg jdField_a_of_type_Dg;
/* 484:    */  public Object a;
/* 485:485 */  private HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/* 486:    */  
/* 487:    */  public df(SegmentDrawer paramSegmentDrawer) {
/* 488:488 */    super("SegentSorter");this.jdField_a_of_type_JavaLangObject = new Object();
/* 489:489 */    setPriority(1);
/* 490:490 */    this.jdField_a_of_type_Db = new db();
/* 491:491 */    this.jdField_a_of_type_CZ = new cZ();
/* 492:492 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 493:493 */    this.jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/* 494:494 */    this.jdField_a_of_type_Dh = new dh(this, (byte)0);
/* 495:495 */    this.jdField_a_of_type_Dg = new dg(this, (byte)0);
/* 496:    */  }
/* 497:    */  
/* 521:521 */  private final Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 522:522 */  private Vector3f c = new Vector3f();
/* 523:    */  
/* 524:    */  public final void run() {
/* 525:525 */    while (!xm.a()) {
/* 526:    */      try {
/* 527:527 */        this.jdField_b_of_type_JavaxVecmathVector3f.set(xe.a().a());
/* 528:528 */        db.a(this.jdField_a_of_type_Db).set(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 529:529 */        cZ.a(this.jdField_a_of_type_CZ).set(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 530:530 */        this.jdField_a_of_type_Dh.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 531:    */        
/* 532:532 */        System.currentTimeMillis();
/* 533:    */        
/* 534:534 */        synchronized (SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 535:535 */          ArrayList localArrayList = SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer);localObject7 = this; for (int k = 0; k < localArrayList.size(); localObject6++) synchronized (((SegmentBufferManager)(localObject8 = (SegmentController)localArrayList.get(k)).getSegmentBuffer()).a()) { Object localObject8; for (localObject10 = ((SegmentBufferManager)((SegmentController)localObject8).getSegmentBuffer()).a().values().iterator(); ((Iterator)localObject10).hasNext(); ((df)localObject7).jdField_b_of_type_JavaUtilArrayList.add((jK)localObject8)) localObject8 = (jL)((Iterator)localObject10).next();
/* 536:    */            }
/* 537:    */        }
/* 538:538 */        try { Collections.sort(this.jdField_b_of_type_JavaUtilArrayList, this.jdField_a_of_type_CZ);
/* 539:539 */        } catch (Exception localException1) { 
/* 540:    */          
/* 542:542 */            localException1;
/* 543:    */        }
/* 544:    */      }
/* 545:    */      catch (InterruptedException localInterruptedException)
/* 546:    */      {
/* 547:    */        Object localObject7;
/* 548:    */        
/* 558:    */        Object localObject10;
/* 559:    */        
/* 569:    */        Object localObject5;
/* 570:    */        
/* 580:    */        int m;
/* 581:    */        
/* 591:    */        int n;
/* 592:    */        
/* 602:    */        int i1;
/* 603:    */        
/* 613:    */        int i;
/* 614:    */        
/* 623:    */        int j;
/* 624:    */        
/* 633:    */        Iterator localIterator;
/* 634:    */        
/* 643:640 */        
/* 644:    */        
/* 647:644 */          localInterruptedException;
/* 648:    */      } catch (Exception localException3) {
/* 649:642 */        
/* 650:    */        
/* 651:644 */          localException3;
/* 652:    */      }
/* 653:544 */      this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 654:    */      
/* 655:546 */      localObject5 = this.jdField_b_of_type_JavaUtilArrayList;localObject7 = this;this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_Dc.i = 0L;((df)localObject7).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_c_of_type_Int = 0;((df)localObject7).jdField_a_of_type_JavaUtilHashSet.clear();m = SegmentDrawer.jdField_a_of_type_DH.jdField_a_of_type_Int << 3;n = 0; for (i1 = 0; i1 < ((List)localObject5).size(); i1++) { localObject10 = (jK)((List)localObject5).get(i1); if (n + ((jK)localObject10).c() < m) { if ((!(((jK)localObject10).a() instanceof kd)) || (!((kd)((jK)localObject10).a()).a()) || (((kd)((jK)localObject10).a()).a().contains(SegmentDrawer.a(((df)localObject7).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).a()))) { n += ((jK)localObject10).c();((jK)localObject10).b(((df)localObject7).jdField_a_of_type_Dh, true); } } else if (((jK)localObject10).a()) { System.err.println("DEACTIVATING REGION: " + ((jK)localObject10).a() + " of " + ((jK)localObject10).a() + "; fill: " + n);((jK)localObject10).b(((df)localObject7).jdField_a_of_type_Dg, true); } } System.currentTimeMillis();
/* 656:    */      
/* 657:548 */      this.jdField_b_of_type_JavaUtilArrayList.clear();
/* 658:    */      
/* 660:551 */      System.currentTimeMillis();
/* 661:    */      
/* 665:556 */      for (i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++)
/* 666:    */      {
/* 668:559 */        (localObject5 = (mr)this.jdField_a_of_type_JavaUtilArrayList.get(i)).a().getAbsoluteSegmentWorldPositionClient((Segment)localObject5, this.c);
/* 669:560 */        this.c.sub(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 670:561 */        ((mr)localObject5).jdField_a_of_type_Float = this.c.lengthSquared();
/* 671:    */      }
/* 672:    */      try {
/* 673:564 */        Collections.sort(this.jdField_a_of_type_JavaUtilArrayList, this.jdField_a_of_type_Db);
/* 674:565 */      } catch (Exception localException2) { 
/* 675:    */        
/* 678:569 */          localException2.printStackTrace();System.err.println("[Exception] Catched: Resorting triggered by exception");
/* 679:    */      }
/* 680:568 */      continue;
/* 681:    */      
/* 682:570 */      i = 0;
/* 683:    */      
/* 684:572 */      j = 0;
/* 685:    */      
/* 695:583 */      if ((this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr.length != SegmentDrawer.jdField_a_of_type_DH.jdField_a_of_type_Int) && 
/* 696:584 */        (!jdField_a_of_type_Boolean)) { throw new AssertionError();
/* 697:    */      }
/* 698:    */      
/* 700:588 */      for (localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext();)
/* 701:    */      {
/* 702:590 */        localObject9 = (mr)localIterator.next();
/* 703:    */        
/* 704:592 */        if (j < SegmentDrawer.jdField_a_of_type_DH.jdField_a_of_type_Int - 100) {
/* 705:593 */          localObject7 = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr[i];
/* 706:594 */          this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d[i] = localObject7;
/* 707:595 */          this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr[i] = localObject9;
/* 708:596 */          this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr[i].a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_Int);
/* 709:597 */          localObject7 = localObject9; if ((((mr)localObject7).jdField_a_of_type_Ms == ms.d) || (((mr)localObject7).jdField_a_of_type_Ms == ms.e)) { ((mr)localObject7).jdField_a_of_type_Ms = ms.b;((mr)localObject7).jdField_a_of_type_Int = 0;((Q)((mr)localObject7).a().getSegmentProvider()).a((mr)localObject7); }
/* 710:598 */          i++;
/* 712:    */        }
/* 713:601 */        else if (((mr)localObject9).b())
/* 714:    */        {
/* 717:605 */          this.jdField_a_of_type_JavaUtilHashSet.add(localObject9);
/* 718:606 */          ((mr)localObject9).e(true);
/* 719:    */        }
/* 720:    */        
/* 721:609 */        j++;
/* 722:    */      }
/* 723:    */      
/* 724:612 */      synchronized (this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr)
/* 725:    */      {
/* 728:616 */        localObject7 = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr;
/* 729:617 */        Object localObject9 = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_c_of_type_ArrayOfMr;
/* 730:    */        
/* 731:619 */        SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer, i);
/* 732:    */        
/* 733:621 */        this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr;
/* 734:622 */        this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_c_of_type_ArrayOfMr = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d;
/* 735:623 */        this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr = ((mr[])localObject7);
/* 736:624 */        this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d = ((mr[])localObject9);
/* 737:    */        
/* 738:626 */        if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr == this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr)) { throw new AssertionError("Pointers equal...");
/* 739:    */        }
/* 740:    */      }
/* 741:    */      
/* 742:630 */      synchronized (SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 743:631 */        SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).addAll(this.jdField_a_of_type_JavaUtilHashSet);
/* 744:    */      }
/* 745:    */      
/* 746:634 */      synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 747:635 */        SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer);
/* 748:636 */        this.jdField_a_of_type_JavaLangObject.wait();
/* 749:    */      }
/* 750:638 */      Thread.sleep(500L);
/* 751:639 */      this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_Int += 1;
/* 752:    */    }
/* 753:    */  }
/* 754:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     df
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */