/*   1:    */package org.schema.game.common.data.world;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import jR;
/*   5:    */import jY;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.util.ArrayList;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import ld;
/*  11:    */import le;
/*  12:    */import o;
/*  13:    */import org.schema.common.util.ByteUtil;
/*  14:    */import org.schema.game.common.controller.SegmentController;
/*  15:    */import org.schema.game.common.data.element.ElementInformation;
/*  16:    */import org.schema.schine.network.IdGen;
/*  17:    */import org.schema.schine.network.Identifiable;
/*  18:    */import q;
/*  19:    */
/*  23:    */public abstract class Segment
/*  24:    */  implements Identifiable
/*  25:    */{
/*  26:    */  public float[] a;
/*  27:    */  
/*  28:    */  static
/*  29:    */  {
/*  30: 30 */    Segment.class.desiredAssertionStatus();
/*  31:    */  }
/*  32:    */  
/*  88:    */  public static q a(int paramInt1, int paramInt2, int paramInt3, q paramq)
/*  89:    */  {
/*  90: 90 */    paramq.b(ByteUtil.b(paramInt1), ByteUtil.b(paramInt2), ByteUtil.b(paramInt3));
/*  91: 91 */    return paramq;
/*  92:    */  }
/*  93:    */  
/* 111:    */  public static void main(String[] paramArrayOfString)
/* 112:    */  {
/* 113:113 */    for (paramArrayOfString = -64; paramArrayOfString < 64; paramArrayOfString++) {
/* 114:114 */      o localo = new o();int i = paramArrayOfString;localo.b((byte)ByteUtil.d(0), (byte)ByteUtil.d(0), (byte)ByteUtil.d(i));System.err.println("0, 0, " + paramArrayOfString + " -> " + localo);
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 119:119 */  private int jdField_a_of_type_Int = 0;
/* 120:    */  private int b;
/* 121:    */  public SegmentData a;
/* 122:    */  public final SegmentController a;
/* 123:    */  public final q a;
/* 124:    */  public final q b;
/* 125:    */  public Object c;
/* 126:    */  private o jdField_a_of_type_O;
/* 127:    */  public float[] b;
/* 128:    */  public float[] c;
/* 129:    */  public short a;
/* 130:    */  public boolean c;
/* 131:    */  
/* 132:    */  public Segment(SegmentController paramSegmentController)
/* 133:    */  {
/* 134:117 */    this.jdField_a_of_type_ArrayOfFloat = new float[16];
/* 135:    */    
/* 156:139 */    new q();
/* 157:    */    
/* 158:141 */    this.jdField_c_of_type_JavaLangObject = new Object();
/* 159:    */    
/* 160:143 */    this.jdField_a_of_type_O = new o();
/* 161:    */    
/* 164:147 */    this.jdField_b_of_type_ArrayOfFloat = new float[3];
/* 165:148 */    this.jdField_c_of_type_ArrayOfFloat = new float[3];
/* 166:    */    
/* 174:157 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/* 175:    */    
/* 178:161 */    this.jdField_b_of_type_Int = IdGen.getIndependentId();
/* 179:    */    
/* 180:163 */    this.jdField_a_of_type_Q = new q();
/* 181:164 */    this.jdField_b_of_type_Q = new q();
/* 182:    */  }
/* 183:    */  
/* 184:    */  public final boolean a(short paramShort, o paramo, int paramInt, boolean paramBoolean)
/* 185:    */  {
/* 186:169 */    boolean bool = false;
/* 187:    */    
/* 188:171 */    if ((g()) && 
/* 189:172 */      (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null))
/* 190:    */    {
/* 191:174 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a().assignData(this);
/* 192:    */    }
/* 193:    */    
/* 195:178 */    SegmentData.getInfoIndex(paramo);
/* 196:179 */    if (!this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.contains(paramo))
/* 197:    */    {
/* 200:183 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer();
/* 201:184 */      paramBoolean = !paramBoolean ? 0 : ElementInformation.defaultActive(paramShort);
/* 202:    */      
/* 207:190 */      this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.setInfoElement(paramo, paramShort, (byte)paramInt, paramBoolean, true);
/* 208:    */      
/* 211:194 */      bool = true;
/* 212:    */    }
/* 213:    */    
/* 218:201 */    return bool;
/* 219:    */  }
/* 220:    */  
/* 242:    */  public boolean equals(Object paramObject)
/* 243:    */  {
/* 244:227 */    return this.jdField_b_of_type_Int == ((Segment)paramObject).jdField_b_of_type_Int;
/* 245:    */  }
/* 246:    */  
/* 251:    */  public final boolean a(q paramq, byte paramByte1, byte paramByte2, byte paramByte3)
/* 252:    */  {
/* 253:236 */    return paramq.a(this.jdField_a_of_type_Q.jdField_a_of_type_Int + paramByte1, this.jdField_a_of_type_Q.jdField_b_of_type_Int + paramByte2, this.jdField_a_of_type_Q.c + paramByte3);
/* 254:    */  }
/* 255:    */  
/* 261:    */  public final q a(byte paramByte1, byte paramByte2, byte paramByte3, q paramq)
/* 262:    */  {
/* 263:246 */    paramq.jdField_a_of_type_Int = (this.jdField_a_of_type_Q.jdField_a_of_type_Int + paramByte1);
/* 264:247 */    paramq.jdField_b_of_type_Int = (this.jdField_a_of_type_Q.jdField_b_of_type_Int + paramByte2);
/* 265:248 */    paramq.c = (this.jdField_a_of_type_Q.c + paramByte3);
/* 266:249 */    return paramq;
/* 267:    */  }
/* 268:    */  
/* 273:    */  public final q a(o paramo, q paramq)
/* 274:    */  {
/* 275:258 */    return a(paramo.a, paramo.b, paramo.c, paramq);
/* 276:    */  }
/* 277:    */  
/* 291:    */  public int getId()
/* 292:    */  {
/* 293:276 */    return this.jdField_b_of_type_Int;
/* 294:    */  }
/* 295:    */  
/* 391:    */  public final SegmentController a()
/* 392:    */  {
/* 393:376 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 394:    */  }
/* 395:    */  
/* 399:    */  public final SegmentData a()
/* 400:    */  {
/* 401:384 */    return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData;
/* 402:    */  }
/* 403:    */  
/* 406:    */  public final int b()
/* 407:    */  {
/* 408:391 */    return this.jdField_a_of_type_Int;
/* 409:    */  }
/* 410:    */  
/* 441:    */  public final Vector3f a(Vector3f paramVector3f)
/* 442:    */  {
/* 443:426 */    paramVector3f.set(this.jdField_a_of_type_Q.jdField_a_of_type_Int, this.jdField_a_of_type_Q.jdField_b_of_type_Int, this.jdField_a_of_type_Q.c);
/* 444:427 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis.transform(paramVector3f);
/* 445:428 */    paramVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().origin);
/* 446:429 */    return paramVector3f;
/* 447:    */  }
/* 448:    */  
/* 458:    */  public void a(le paramle)
/* 459:    */  {
/* 460:443 */    if (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null)
/* 461:    */    {
/* 462:445 */      System.err.println("[SEGMENT][" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] RECEIVED SEG PIECE: SEGMENT DATA WAS NULL!!!! " + g() + " " + this.jdField_a_of_type_Int);
/* 463:446 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a()
/* 464:447 */        .assignData(this);
/* 465:    */    }
/* 466:    */    
/* 468:    */    int i;
/* 469:    */    
/* 470:453 */    if (((i = this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.applySegmentData(paramle.a(this.jdField_a_of_type_O), paramle.a())) == 0) && 
/* 471:454 */      (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (paramle.a() == 291)) {
/* 472:455 */      System.err.println("[SREVER] FACTION BLOCK ADDED TO " + this + "; resetting faction !!!!!!!!!!!!!!");
/* 473:456 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setFactionId(0);
/* 474:    */    }
/* 475:    */    
/* 476:459 */    if ((i == 4) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()))
/* 477:    */    {
/* 478:461 */      if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 479:462 */        System.err.println("PIECE ACTIVE CHANGED");
/* 480:463 */        if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController instanceof ld)) {
/* 481:464 */          synchronized (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNeedsActiveUpdateClient()) {
/* 482:465 */            this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNeedsActiveUpdateClient().add(paramle);
/* 483:    */          }
/* 484:    */        }
/* 485:    */      }
/* 486:    */    }
/* 487:    */    
/* 491:474 */    if (g()) {
/* 492:475 */      if (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null) {
/* 493:476 */        System.err.println("[SEGMENT][" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "] SEGMENT DATA SET TO NULL BECAUSE ITS NOT EMPTY!!!!");
/* 494:477 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData);
/* 495:    */      }
/* 496:    */      
/* 497:    */    }
/* 498:481 */    else if ((paramle.a() == 122) && (paramle.b())) {
/* 499:482 */      int j = paramle.a();
/* 500:483 */      for (paramle = 0; paramle < 6; paramle++) {
/* 501:484 */        if ((org.schema.game.common.data.element.Element.SIDE_FLAG[paramle] & j) == org.schema.game.common.data.element.Element.SIDE_FLAG[paramle]) {
/* 502:485 */          a(org.schema.game.common.data.element.Element.DIRECTIONSb[paramle]);
/* 503:    */        }
/* 504:    */      }
/* 505:488 */      paramle = new o();
/* 506:489 */      int k = 0;
/* 507:490 */      for (int m = 0; m < 6; m++) {
/* 508:491 */        if ((org.schema.game.common.data.element.Element.SIDE_FLAG[m] & j) == org.schema.game.common.data.element.Element.SIDE_FLAG[m]) {
/* 509:492 */          k++;
/* 510:493 */          paramle.a(org.schema.game.common.data.element.Element.DIRECTIONSb[m]);
/* 511:    */        }
/* 512:    */      }
/* 513:496 */      if (k > 1)
/* 514:    */      {
/* 515:498 */        a(paramle);
/* 516:    */      }
/* 517:    */    }
/* 518:    */    
/* 521:504 */    a(true);
/* 522:    */  }
/* 523:    */  
/* 525:    */  public final boolean a(q paramq1, q paramq2)
/* 526:    */  {
/* 527:510 */    if ((paramq1.jdField_a_of_type_Int < this.jdField_a_of_type_Q.jdField_a_of_type_Int) && (paramq1.jdField_b_of_type_Int < this.jdField_a_of_type_Q.jdField_b_of_type_Int) && (paramq1.c < this.jdField_a_of_type_Q.c) && (paramq2.jdField_a_of_type_Int > this.jdField_a_of_type_Q.jdField_a_of_type_Int + 16) && (paramq2.jdField_b_of_type_Int > this.jdField_a_of_type_Q.jdField_b_of_type_Int + 16) && (paramq2.c > this.jdField_a_of_type_Q.c + 16))
/* 528:    */    {
/* 531:514 */      return !g();
/* 532:    */    }
/* 533:516 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCollisionChecker().a(this, paramq1, paramq2);
/* 534:    */  }
/* 535:    */  
/* 538:    */  public int hashCode()
/* 539:    */  {
/* 540:523 */    return this.jdField_a_of_type_Q.hashCode() + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getId();
/* 541:    */  }
/* 542:    */  
/* 676:    */  public final boolean g()
/* 677:    */  {
/* 678:661 */    return ((this.jdField_a_of_type_Int == 0) && (!this.jdField_c_of_type_Boolean)) || (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null);
/* 679:    */  }
/* 680:    */  
/* 699:    */  public final boolean a(o paramo, boolean paramBoolean)
/* 700:    */  {
/* 701:684 */    boolean bool1 = paramBoolean;paramBoolean = paramo;paramo = this;boolean bool2 = false; if (!paramo.g()) { bool2 = paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.contains(paramBoolean);paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.setInfoElement(paramBoolean, (short)0, bool1); } if ((paramo.g()) && (paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null)) paramo.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a(paramo.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData); return bool2;
/* 702:    */  }
/* 703:    */  
/* 727:    */  private void a(o paramo)
/* 728:    */  {
/* 729:712 */    if ((paramo = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentFromCache(this.jdField_a_of_type_Q.jdField_a_of_type_Int + paramo.a, this.jdField_a_of_type_Q.jdField_b_of_type_Int + paramo.b, this.jdField_a_of_type_Q.c + paramo.c)) != null) {
/* 730:713 */      paramo.a(true);
/* 731:    */    }
/* 732:    */  }
/* 733:    */  
/* 744:    */  public void setId(int paramInt)
/* 745:    */  {
/* 746:729 */    this.jdField_b_of_type_Int = paramInt;
/* 747:    */  }
/* 748:    */  
/* 761:    */  public final void a(SegmentData paramSegmentData)
/* 762:    */  {
/* 763:746 */    synchronized (this.jdField_c_of_type_JavaLangObject) {
/* 764:747 */      this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData = paramSegmentData;
/* 765:748 */      return;
/* 766:    */    }
/* 767:    */  }
/* 768:    */  
/* 769:    */  public final void b(int paramInt)
/* 770:    */  {
/* 771:754 */    this.jdField_a_of_type_Int = paramInt;
/* 772:    */  }
/* 773:    */  
/* 779:    */  public String toString()
/* 780:    */  {
/* 781:764 */    return "DATA:" + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getId() + ":" + this.jdField_a_of_type_Int;
/* 782:    */  }
/* 783:    */  
/* 805:    */  public final void a(float paramFloat1, Vector3f paramVector3f, float paramFloat2)
/* 806:    */  {
/* 807:790 */    if (!g())
/* 808:    */    {
/* 809:792 */      (paramVector3f = new q(paramVector3f)).c(this.jdField_a_of_type_Q);
/* 810:793 */      this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.damage(paramFloat1, paramVector3f, paramFloat2);
/* 811:    */    }
/* 812:    */  }
/* 813:    */  
/* 814:797 */  public final void a(q paramq) { a(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.c); }
/* 815:    */  
/* 816:    */  public final void a(int paramInt1, int paramInt2, int paramInt3) {
/* 817:800 */    this.jdField_a_of_type_Q.b(paramInt1, paramInt2, paramInt3);
/* 818:801 */    this.jdField_b_of_type_Q.b(ByteUtil.b(paramInt1), ByteUtil.b(paramInt2), ByteUtil.b(paramInt3));
/* 819:    */  }
/* 820:    */  
/* 821:    */  public abstract void a(boolean paramBoolean);
/* 822:    */  
/* 823:    */  public static void d() {}
/* 824:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.Segment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */