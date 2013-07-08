/*   1:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   2:    */import com.bulletphysics.collision.shapes.BoxShape;
/*   3:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   4:    */import com.bulletphysics.collision.shapes.CompoundShape;
/*   5:    */import com.bulletphysics.collision.shapes.ConcaveShape;
/*   6:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   7:    */import com.bulletphysics.collision.shapes.CylinderShape;
/*   8:    */import com.bulletphysics.collision.shapes.PolyhedralConvexShape;
/*   9:    */import com.bulletphysics.collision.shapes.ShapeHull;
/*  10:    */import com.bulletphysics.collision.shapes.SphereShape;
/*  11:    */import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*  12:    */import com.bulletphysics.dynamics.RigidBody;
/*  13:    */import com.bulletphysics.dynamics.constraintsolver.HingeConstraint;
/*  14:    */import com.bulletphysics.dynamics.constraintsolver.SliderConstraint;
/*  15:    */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*  16:    */import com.bulletphysics.linearmath.IDebugDraw;
/*  17:    */import com.bulletphysics.linearmath.Transform;
/*  18:    */import com.bulletphysics.linearmath.TransformUtil;
/*  19:    */import com.bulletphysics.linearmath.VectorUtil;
/*  20:    */import com.bulletphysics.util.ObjectPool;
/*  21:    */import java.io.PrintStream;
/*  22:    */import java.nio.FloatBuffer;
/*  23:    */import javax.vecmath.Tuple3f;
/*  24:    */import javax.vecmath.Vector3f;
/*  25:    */import org.lwjgl.BufferUtils;
/*  26:    */import org.lwjgl.opengl.GL11;
/*  27:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  28:    */
/* 250:    */public final class xj
/* 251:    */  extends IDebugDraw
/* 252:    */{
/* 253:    */  private static float[] jdField_a_of_type_ArrayOfFloat;
/* 254:254 */  private int jdField_a_of_type_Int = 0;
/* 255:    */  
/* 259:259 */  private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 260:    */  
/* 261:261 */  private static FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer((xj.jdField_a_of_type_ArrayOfFloat = new float[16]).length);
/* 262:    */  
/* 271:    */  public static void a(Transform paramTransform, CollisionShape paramCollisionShape, Vector3f paramVector3f, int paramInt)
/* 272:    */  {
/* 273:273 */    Object localObject1 = ObjectPool.get(Transform.class);
/* 274:274 */    ObjectPool localObjectPool = ObjectPool.get(Vector3f.class);
/* 275:    */    
/* 277:277 */    GlUtil.d();
/* 278:278 */    paramTransform.getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/* 279:279 */    jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 280:280 */    jdField_a_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/* 281:281 */    jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 282:282 */    GL11.glMultMatrix(jdField_a_of_type_JavaNioFloatBuffer);
/* 283:    */    
/* 299:299 */    GL11.glEnable(2903);
/* 300:300 */    GL11.glColor3f(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/* 301:301 */    GL11.glDisable(2896);
/* 302:302 */    GL11.glDisable(3553);
/* 303:303 */    Object localObject2; Object localObject4; if (paramCollisionShape.getShapeType() == BroadphaseNativeType.COMPOUND_SHAPE_PROXYTYPE)
/* 304:    */    {
/* 305:305 */      paramTransform = (CompoundShape)paramCollisionShape;
/* 306:306 */      localObject2 = (Transform)((ObjectPool)localObject1).get();
/* 307:307 */      for (int i = paramTransform.getNumChildShapes() - 1; i >= 0; i--) {
/* 308:308 */        paramTransform.getChildTransform(i, (Transform)localObject2);
/* 309:309 */        localObject4 = paramTransform.getChildShape(i);
/* 310:310 */        a((Transform)localObject2, (CollisionShape)localObject4, paramVector3f, paramInt);
/* 311:    */      }
/* 312:312 */      ((ObjectPool)localObject1).release(localObject2);
/* 317:    */    }
/* 318:    */    else
/* 319:    */    {
/* 323:323 */      paramTransform = 1;
/* 324:    */      Object localObject3;
/* 325:325 */      if ((paramInt & 0x1) == 0)
/* 326:    */      {
/* 329:329 */        switch (xk.a[paramCollisionShape.getShapeType().ordinal()]) {
/* 330:    */        case 1: 
/* 331:331 */          if ((paramCollisionShape instanceof BoxShape))
/* 332:    */          {
/* 333:333 */            Vector3f localVector3f1 = ((BoxShape)paramCollisionShape).getHalfExtentsWithMargin((Vector3f)localObjectPool.get());
/* 334:    */            
/* 335:335 */            GL11.glScalef(2.0F * localVector3f1.x, 2.0F * localVector3f1.y, 2.0F * localVector3f1.z);
/* 336:    */            
/* 337:337 */            xP.a();
/* 338:    */            
/* 339:339 */            localObjectPool.release(localVector3f1);
/* 340:340 */            paramTransform = 0; }
/* 341:341 */          break;
/* 342:    */        
/* 345:    */        case 2: 
/* 346:346 */          ((SphereShape)paramCollisionShape).getMargin();
/* 347:    */          
/* 350:350 */          System.err.println("cannot draw debug sphere");
/* 351:    */          
/* 358:358 */          paramTransform = 0;
/* 359:359 */          break;
/* 360:    */        
/* 391:    */        case 3: 
/* 392:392 */          float f2 = (localObject2 = (StaticPlaneShape)paramCollisionShape).getPlaneConstant();
/* 393:393 */          localObject4 = ((StaticPlaneShape)localObject2).getPlaneNormal((Vector3f)localObjectPool.get());
/* 394:394 */          (
/* 395:395 */            paramVector3f = (Vector3f)localObjectPool.get()).scale(f2, (Tuple3f)localObject4);
/* 396:396 */          localObject1 = (Vector3f)localObjectPool.get();
/* 397:397 */          localObject2 = (Vector3f)localObjectPool.get();
/* 398:398 */          TransformUtil.planeSpace1((Vector3f)localObject4, (Vector3f)localObject1, (Vector3f)localObject2);
/* 399:    */          
/* 400:    */          Vector3f localVector3f2;
/* 401:    */          
/* 402:402 */          (localVector3f2 = (Vector3f)localObjectPool.get()).scaleAdd(100.0F, (Tuple3f)localObject1, paramVector3f);
/* 403:    */          
/* 404:    */          Vector3f localVector3f4;
/* 405:405 */          (localVector3f4 = (Vector3f)localObjectPool.get()).scale(100.0F, (Tuple3f)localObject1);
/* 406:406 */          localVector3f4.sub(paramVector3f, localVector3f4);
/* 407:    */          
/* 408:    */          Vector3f localVector3f5;
/* 409:409 */          (localVector3f5 = (Vector3f)localObjectPool.get()).scaleAdd(100.0F, (Tuple3f)localObject2, paramVector3f);
/* 410:    */          
/* 411:    */          Vector3f localVector3f6;
/* 412:412 */          (localVector3f6 = (Vector3f)localObjectPool.get()).scale(100.0F, (Tuple3f)localObject2);
/* 413:413 */          localVector3f6.sub(paramVector3f, localVector3f6);
/* 414:    */          
/* 415:415 */          GL11.glBegin(1);
/* 416:416 */          GL11.glVertex3f(localVector3f2.x, localVector3f2.y, localVector3f2.z);
/* 417:417 */          GL11.glVertex3f(localVector3f4.x, localVector3f4.y, localVector3f4.z);
/* 418:418 */          GL11.glVertex3f(localVector3f5.x, localVector3f5.y, localVector3f5.z);
/* 419:419 */          GL11.glVertex3f(localVector3f6.x, localVector3f6.y, localVector3f6.z);
/* 420:420 */          GL11.glEnd();
/* 421:    */          
/* 422:422 */          localObjectPool.release(localObject4);
/* 423:423 */          localObjectPool.release(paramVector3f);
/* 424:424 */          localObjectPool.release(localObject1);
/* 425:425 */          localObjectPool.release(localObject2);
/* 426:426 */          localObjectPool.release(localVector3f2);
/* 427:427 */          localObjectPool.release(localVector3f4);
/* 428:428 */          localObjectPool.release(localVector3f5);
/* 429:429 */          localObjectPool.release(localVector3f6);
/* 430:    */          
/* 431:431 */          break;
/* 432:    */        
/* 436:    */        case 4: 
/* 437:437 */          int j = (localObject2 = (CylinderShape)paramCollisionShape).getUpAxis();
/* 438:    */          
/* 439:439 */          ((CylinderShape)localObject2).getRadius();
/* 440:440 */          paramVector3f = (Vector3f)localObjectPool.get();
/* 441:441 */          float f1 = VectorUtil.getCoord(((CylinderShape)localObject2).getHalfExtentsWithMargin(paramVector3f), j);
/* 442:    */          
/* 452:452 */          switch (j) {
/* 453:453 */          case 2:  GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);break;
/* 454:454 */          case 3:  GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);break;
/* 455:    */          default: 
/* 456:456 */            GL11.glTranslatef(-f1, 0.0F, 0.0F);
/* 457:457 */            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/* 458:    */          }
/* 459:    */          
/* 460:    */          
/* 462:462 */          System.err.println("cannot draw debug cylinder");
/* 463:463 */          localObjectPool.release(paramVector3f);
/* 464:    */          
/* 465:465 */          break;
/* 466:    */        
/* 467:    */        default: 
/* 468:468 */          if (paramCollisionShape.isConvex())
/* 469:    */          {
/* 470:470 */            xP.a();
/* 471:471 */            localObject2 = (ConvexShape)paramCollisionShape;
/* 472:472 */            if (paramCollisionShape.getUserPointer() == null)
/* 473:    */            {
/* 475:475 */              localObject3 = new ShapeHull((ConvexShape)localObject2);
/* 476:    */              
/* 481:481 */              float f3 = paramCollisionShape.getMargin();
/* 482:482 */              ((ShapeHull)localObject3).buildHull(f3);
/* 483:483 */              ((ConvexShape)localObject2).setUserPointer(localObject3);
/* 484:    */            }
/* 485:    */          }
/* 486:    */          
/* 508:    */          break;
/* 509:    */        }
/* 510:    */        
/* 511:    */      }
/* 512:    */      
/* 533:    */      Vector3f localVector3f3;
/* 534:    */      
/* 555:555 */      if (paramTransform != 0)
/* 556:    */      {
/* 557:557 */        if ((paramCollisionShape.isPolyhedral()) && ((paramCollisionShape instanceof PolyhedralConvexShape))) {
/* 558:558 */          localObject2 = (PolyhedralConvexShape)paramCollisionShape;
/* 559:    */          
/* 560:560 */          GL11.glBegin(1);
/* 561:    */          
/* 562:562 */          localObject3 = (Vector3f)localObjectPool.get();localVector3f3 = (Vector3f)localObjectPool.get();
/* 563:    */          
/* 564:564 */          for (paramVector3f = 0; paramVector3f < ((PolyhedralConvexShape)localObject2).getNumEdges(); paramVector3f++) {
/* 565:565 */            ((PolyhedralConvexShape)localObject2).getEdge(paramVector3f, (Vector3f)localObject3, localVector3f3);
/* 566:    */            
/* 567:567 */            GL11.glVertex3f(((Vector3f)localObject3).x, ((Vector3f)localObject3).y, ((Vector3f)localObject3).z);
/* 568:568 */            GL11.glVertex3f(localVector3f3.x, localVector3f3.y, localVector3f3.z);
/* 569:    */          }
/* 570:570 */          GL11.glEnd();
/* 571:    */          
/* 572:572 */          localObjectPool.release(localObject3);
/* 573:573 */          localObjectPool.release(localVector3f3);
/* 574:    */        }
/* 575:    */      }
/* 576:    */      
/* 622:622 */      if ((paramCollisionShape.isConcave()) && (paramCollisionShape.getShapeType() != BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE))
/* 623:    */      {
/* 626:626 */        localObject2 = (ConcaveShape)paramCollisionShape;
/* 627:    */        
/* 631:631 */        (
/* 632:632 */          localObject3 = (Vector3f)localObjectPool.get()).set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 633:633 */        (
/* 634:634 */          localVector3f3 = (Vector3f)localObjectPool.get()).set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/* 635:    */        
/* 637:637 */        (paramVector3f = new xl()).a = ((paramInt & 0x1) != 0);
/* 638:    */        
/* 639:639 */        ((ConcaveShape)localObject2).processAllTriangles(paramVector3f, localVector3f3, (Vector3f)localObject3);
/* 640:    */        
/* 641:641 */        localObjectPool.release(localObject3);
/* 642:642 */        localObjectPool.release(localVector3f3);
/* 643:    */      }
/* 644:    */    }
/* 645:    */    
/* 666:666 */    GlUtil.c();
/* 667:    */    
/* 678:678 */    GL11.glEnable(2896);
/* 679:    */  }
/* 680:    */  
/* 689:    */  public static void a(TypedConstraint paramTypedConstraint, Vector3f paramVector3f)
/* 690:    */  {
/* 691:691 */    GlUtil.d();
/* 692:692 */    GL11.glEnable(2903);
/* 693:693 */    GL11.glDisable(2896);
/* 694:694 */    GL11.glColor3f(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/* 695:695 */    GL11.glDisable(3553);
/* 696:696 */    Object localObject = paramTypedConstraint.getConstraintType();
/* 697:    */    
/* 698:    */    Transform localTransform;
/* 699:    */    
/* 700:700 */    switch (xk.b[localObject.ordinal()])
/* 701:    */    {
/* 702:    */    case 1: 
/* 703:703 */      break;
/* 704:    */    
/* 705:    */    case 2: 
/* 706:706 */      break;
/* 707:    */    case 3: 
/* 708:708 */      paramTypedConstraint = (HingeConstraint)paramTypedConstraint;
/* 709:709 */      localObject = new Transform();
/* 710:710 */      (
/* 711:711 */        localTransform = new Transform()).setIdentity();
/* 712:712 */      paramTypedConstraint.getRigidBodyA().getWorldTransform((Transform)localObject);
/* 713:    */      
/* 716:716 */      paramTypedConstraint.getAFrame(localTransform);
/* 717:717 */      ((Transform)localObject).mul(localTransform);
/* 718:718 */      a((Transform)localObject, 25.0F, paramVector3f);
/* 719:    */      
/* 720:720 */      ((Transform)localObject).setIdentity();
/* 721:721 */      localTransform.setIdentity();
/* 722:722 */      paramTypedConstraint.getRigidBodyB().getWorldTransform((Transform)localObject);
/* 723:    */      
/* 725:725 */      paramTypedConstraint.getBFrame(localTransform);
/* 726:726 */      ((Transform)localObject).mul(localTransform);
/* 727:727 */      a((Transform)localObject, 25.0F, new Vector3f(0.0F, 1.0F, 1.0F));
/* 728:728 */      break;
/* 729:    */    
/* 730:    */    case 4: 
/* 731:731 */      break;
/* 732:    */    
/* 733:    */    case 5: 
/* 734:734 */      paramTypedConstraint = (SliderConstraint)paramTypedConstraint;
/* 735:735 */      localObject = new Transform();
/* 736:736 */      (
/* 737:737 */        localTransform = new Transform()).setIdentity();
/* 738:738 */      paramTypedConstraint.getRigidBodyA().getWorldTransform((Transform)localObject);
/* 739:    */      
/* 742:742 */      paramTypedConstraint.getFrameOffsetA(localTransform);
/* 743:743 */      ((Transform)localObject).mul(localTransform);
/* 744:744 */      a((Transform)localObject, 25.0F, paramVector3f);
/* 745:    */      
/* 746:746 */      ((Transform)localObject).setIdentity();
/* 747:747 */      localTransform.setIdentity();
/* 748:748 */      paramTypedConstraint.getRigidBodyB().getWorldTransform((Transform)localObject);
/* 749:    */      
/* 751:751 */      paramTypedConstraint.getFrameOffsetB(localTransform);
/* 752:752 */      ((Transform)localObject).mul(localTransform);
/* 753:753 */      a((Transform)localObject, 25.0F, new Vector3f(0.0F, 1.0F, 1.0F)); }
/* 754:754 */    GlUtil.c();
/* 755:    */    
/* 763:763 */    GL11.glEnable(2896);
/* 764:    */  }
/* 765:    */  
/* 774:    */  public static void a(Transform paramTransform, float paramFloat, Vector3f paramVector3f)
/* 775:    */  {
/* 776:776 */    GlUtil.d();
/* 777:777 */    GL11.glDisable(3553);
/* 778:778 */    GL11.glEnable(2903);
/* 779:779 */    GL11.glDisable(2896);
/* 780:780 */    GL11.glColor3f(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/* 781:781 */    paramTransform.getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/* 782:782 */    jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 783:783 */    jdField_a_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/* 784:784 */    jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 785:785 */    GL11.glMultMatrix(jdField_a_of_type_JavaNioFloatBuffer);
/* 786:    */    
/* 788:788 */    GL11.glBegin(1);
/* 789:789 */    GL11.glVertex3f(-paramFloat, 0.0F, 0.0F);
/* 790:790 */    GL11.glVertex3f(paramFloat, 0.0F, 0.0F);
/* 791:791 */    GL11.glVertex3f(0.0F, 0.0F, -paramFloat);
/* 792:792 */    GL11.glVertex3f(0.0F, 0.0F, paramFloat);
/* 793:793 */    GL11.glVertex3f(0.0F, -paramFloat, 0.0F);
/* 794:794 */    GL11.glVertex3f(0.0F, paramFloat, 0.0F);
/* 795:795 */    GL11.glEnd();
/* 796:    */    
/* 797:797 */    GL11.glDisable(2903);
/* 798:798 */    GL11.glEnable(2896);
/* 799:799 */    GlUtil.c();
/* 800:    */  }
/* 801:    */  
/* 811:    */  public final void draw3dText(Vector3f paramVector3f, String paramString) {}
/* 812:    */  
/* 821:    */  public final void drawContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt, Vector3f paramVector3f3)
/* 822:    */  {
/* 823:823 */    if ((this.jdField_a_of_type_Int & 0x8) != 0)
/* 824:    */    {
/* 825:825 */      (paramInt = this.jdField_a_of_type_JavaxVecmathVector3f).scaleAdd(paramFloat * 100.0F, paramVector3f2, paramVector3f1);
/* 826:826 */      GL11.glBegin(1);
/* 827:    */      
/* 842:842 */      GL11.glColor3f(paramVector3f3.x, paramVector3f3.y, paramVector3f3.z);
/* 843:843 */      GL11.glVertex3f(paramVector3f1.x, paramVector3f1.y, paramVector3f1.z);
/* 844:844 */      GL11.glVertex3f(paramInt.x, paramInt.y, paramInt.z);
/* 845:845 */      GL11.glEnd();
/* 846:    */    }
/* 847:    */  }
/* 848:    */  
/* 868:    */  public final void drawLine(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
/* 869:    */  {
/* 870:870 */    GL11.glBegin(1);
/* 871:871 */    GL11.glColor3f(paramVector3f3.x, paramVector3f3.y, paramVector3f3.z);
/* 872:872 */    GL11.glVertex3f(paramVector3f1.x, paramVector3f1.y, paramVector3f1.z);
/* 873:873 */    GL11.glVertex3f(paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/* 874:874 */    GL11.glEnd();
/* 875:    */  }
/* 876:    */  
/* 880:    */  public final int getDebugMode()
/* 881:    */  {
/* 882:882 */    this.jdField_a_of_type_Int = Math.min(1025, ((Integer)xu.G.a()).intValue());
/* 883:    */    
/* 900:900 */    return this.jdField_a_of_type_Int;
/* 901:    */  }
/* 902:    */  
/* 906:    */  public final void reportErrorWarning(String paramString)
/* 907:    */  {
/* 908:908 */    System.err.println(paramString);
/* 909:    */  }
/* 910:    */  
/* 914:    */  public final void setDebugMode(int paramInt)
/* 915:    */  {
/* 916:916 */    this.jdField_a_of_type_Int = paramInt;
/* 917:    */  }
/* 918:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */