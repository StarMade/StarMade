/*    1:     */package org.schema.schine.graphicsengine.forms;
/*    2:     */
/*    3:     */import java.io.BufferedReader;
/*    4:     */import java.io.File;
/*    5:     */import java.io.FileNotFoundException;
/*    6:     */import java.io.FileReader;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.PrintStream;
/*    9:     */import java.nio.FloatBuffer;
/*   10:     */import java.nio.IntBuffer;
/*   11:     */import java.util.Iterator;
/*   12:     */import java.util.List;
/*   13:     */import javax.vecmath.Vector3f;
/*   14:     */import org.lwjgl.BufferUtils;
/*   15:     */import org.lwjgl.opengl.GL11;
/*   16:     */import org.lwjgl.opengl.GL15;
/*   17:     */import org.lwjgl.opengl.GL20;
/*   18:     */import org.schema.schine.graphicsengine.core.GlUtil;
/*   19:     */import xM;
/*   20:     */import xO;
/*   21:     */import xS;
/*   22:     */import xV;
/*   23:     */import xu;
/*   24:     */import yf;
/*   25:     */import yi;
/*   26:     */import zB;
/*   27:     */
/*  305:     */public class Mesh
/*  306:     */  extends xS
/*  307:     */{
/*  308:     */  public xV[] a;
/*  309:     */  private Vector3f[] jdField_b_of_type_ArrayOfJavaxVecmathVector3f;
/*  310:     */  private Vector3f[] jdField_c_of_type_ArrayOfJavaxVecmathVector3f;
/*  311:     */  
/*  312:     */  public static void a(Mesh paramMesh)
/*  313:     */  {
/*  314: 314 */    if ((paramMesh.jdField_d_of_type_Int == 0) && (jdField_c_of_type_Int > 0))
/*  315: 315 */      return;
/*  316: 316 */    if (paramMesh.jdField_d_of_type_Int == 0) {
/*  317: 317 */      jdField_c_of_type_Int += 1;
/*  318:     */    }
/*  319:     */    
/*  320: 320 */    jdField_e_of_type_Int += 1;
/*  321: 321 */    if ((paramMesh.jdField_d_of_type_Int != 0) && (jdField_e_of_type_Int % 5 != 0)) {
/*  322: 322 */      return;
/*  323:     */    }
/*  324:     */    
/*  328: 328 */    long l1 = System.currentTimeMillis();
/*  329:     */    
/*  330: 330 */    if (paramMesh.jdField_d_of_type_Int == 0) {
/*  331: 331 */      paramMesh.jdField_d_of_type_Int += 1;
/*  332: 332 */      return;
/*  333:     */    }
/*  334: 334 */    if (paramMesh.jdField_d_of_type_Int == 1) {
/*  335: 335 */      paramMesh.jdField_d_of_type_Int += 1;
/*  336: 336 */      return;
/*  337:     */    }
/*  338:     */    
/*  339: 339 */    if (paramMesh.jdField_d_of_type_Int == 2) {
/*  340: 340 */      paramMesh.jdField_d_of_type_Int += 1;
/*  341: 341 */      return;
/*  342:     */    }
/*  343: 343 */    l1 = System.currentTimeMillis() - l1;
/*  344:     */    
/*  347: 347 */    long l2 = System.currentTimeMillis();
/*  348:     */    
/*  350: 350 */    if (paramMesh.jdField_d_of_type_Int == 3) {
/*  351: 351 */      paramMesh.jdField_d_of_type_Int += 1;
/*  352: 352 */      return;
/*  353:     */    }
/*  354: 354 */    l2 = System.currentTimeMillis() - l2;
/*  355:     */    
/*  358: 358 */    long l3 = System.currentTimeMillis();
/*  359:     */    
/*  362: 362 */    l3 = System.currentTimeMillis() - l3;
/*  363:     */    
/*  364: 364 */    long l4 = System.currentTimeMillis();
/*  365: 365 */    if (paramMesh.jdField_d_of_type_Int == 4) {
/*  366: 366 */      if ((!jdField_f_of_type_Boolean) && (paramMesh.jdField_b_of_type_JavaNioFloatBuffer.position() != 0)) throw new AssertionError();
/*  367: 367 */      a(2, paramMesh);
/*  368: 368 */      paramMesh.jdField_d_of_type_Int += 1;
/*  369: 369 */      return;
/*  370:     */    }
/*  371: 371 */    if (paramMesh.jdField_d_of_type_Int == 5) {
/*  372: 372 */      if ((!jdField_f_of_type_Boolean) && (paramMesh.jdField_c_of_type_JavaNioFloatBuffer.position() != 0)) throw new AssertionError();
/*  373: 373 */      a(4, paramMesh);
/*  374: 374 */      paramMesh.jdField_d_of_type_Int += 1;
/*  375: 375 */      return;
/*  376:     */    }
/*  377: 377 */    if (paramMesh.jdField_d_of_type_Int == 6) {
/*  378: 378 */      if ((!jdField_f_of_type_Boolean) && (paramMesh.jdField_d_of_type_JavaNioFloatBuffer.position() != 0)) throw new AssertionError();
/*  379: 379 */      a(3, paramMesh);
/*  380: 380 */      paramMesh.jdField_d_of_type_Int += 1;
/*  381: 381 */      return;
/*  382:     */    }
/*  383: 383 */    if (paramMesh.jdField_d_of_type_Int == 7) {
/*  384: 384 */      if ((!jdField_f_of_type_Boolean) && (paramMesh.a.position() != 0)) throw new AssertionError();
/*  385: 385 */      paramMesh.a.capacity();a(1, paramMesh);
/*  386: 386 */      paramMesh.jdField_d_of_type_Int += 1;
/*  387: 387 */      return;
/*  388:     */    }
/*  389: 389 */    l4 = System.currentTimeMillis() - l4;
/*  390:     */    
/*  393: 393 */    if (paramMesh.jdField_d_of_type_Int == 8)
/*  394:     */    {
/*  400: 400 */      paramMesh.jdField_g_of_type_Int = 3;
/*  401:     */      
/*  403: 403 */      jdField_c_of_type_Int -= 1;
/*  404:     */      
/*  405: 405 */      System.err.println("BOUND " + paramMesh.b() + " to VBO(" + paramMesh.jdField_d_of_type_JavaNioIntBuffer.get(0) + "): faces: " + paramMesh.jdField_h_of_type_Int + ", indices: " + paramMesh.a.capacity() + " type: " + paramMesh.jdField_g_of_type_Int + ", alloc: " + l1 + ", prep: " + l2 + ", gen: " + l3 + ", bufferList: " + l4 + ", total: " + (l1 + l2 + l3 + l4) + ", currently building: " + jdField_c_of_type_Int);
/*  406: 406 */      paramMesh.e = true;
/*  407:     */    }
/*  408:     */    
/*  409: 409 */    GL15.glBindBuffer(34962, 0);
/*  410:     */  }
/*  411:     */  
/*  436:     */  private static void a(int paramInt, Mesh paramMesh)
/*  437:     */  {
/*  438: 438 */    switch (paramInt) {
/*  439:     */    case 1: 
/*  440: 440 */      if (paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0) {
/*  441: 441 */        GL15.glGenBuffers(paramMesh.jdField_f_of_type_JavaNioIntBuffer);
/*  442:     */      }
/*  443: 443 */      GL15.glBindBuffer(34963, paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0));
/*  444:     */      
/*  445: 445 */      GL15.glBufferData(34963, paramMesh.a, 35040);
/*  446:     */      
/*  447: 447 */      GL15.glBindBuffer(34963, 0);
/*  448: 448 */      break;
/*  449:     */    
/*  450:     */    case 5: 
/*  451: 451 */      if (paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0) {
/*  452: 452 */        GL15.glGenBuffers(paramMesh.jdField_f_of_type_JavaNioIntBuffer);
/*  453:     */      }
/*  454: 454 */      GL15.glBindBuffer(34963, paramMesh.jdField_h_of_type_JavaNioIntBuffer.get(0));
/*  455:     */      
/*  456: 456 */      GL15.glBufferData(34963, paramMesh.jdField_b_of_type_JavaNioIntBuffer, 35040);
/*  457:     */      
/*  458: 458 */      GL15.glBindBuffer(34963, 0);
/*  459: 459 */      break;
/*  460:     */    
/*  461:     */    case 6: 
/*  462: 462 */      if (paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0) {
/*  463: 463 */        GL15.glGenBuffers(paramMesh.jdField_f_of_type_JavaNioIntBuffer);
/*  464:     */      }
/*  465: 465 */      GL15.glBindBuffer(34963, paramMesh.jdField_g_of_type_JavaNioIntBuffer.get(0));
/*  466:     */      
/*  467: 467 */      GL15.glBufferData(34963, paramMesh.jdField_c_of_type_JavaNioIntBuffer, 35040);
/*  468:     */      
/*  469: 469 */      GL15.glBindBuffer(34963, 0);
/*  470: 470 */      break;
/*  471:     */    
/*  473:     */    case 2: 
/*  474: 474 */      GL15.glGenBuffers(paramMesh.jdField_d_of_type_JavaNioIntBuffer);
/*  475:     */      
/*  476: 476 */      GL15.glBindBuffer(34962, paramMesh.jdField_d_of_type_JavaNioIntBuffer.get(0));
/*  477:     */      
/*  479: 479 */      GL15.glBufferData(34962, paramMesh.jdField_b_of_type_JavaNioFloatBuffer, 35044);
/*  480:     */      
/*  481: 481 */      GL15.glBindBuffer(34962, 0);
/*  482: 482 */      break;
/*  483:     */    
/*  485:     */    case 4: 
/*  486: 486 */      GL15.glGenBuffers(paramMesh.jdField_e_of_type_JavaNioIntBuffer);
/*  487: 487 */      GL15.glBindBuffer(34962, paramMesh.jdField_e_of_type_JavaNioIntBuffer.get(0));
/*  488:     */      
/*  489: 489 */      GL15.glBufferData(34962, paramMesh.jdField_c_of_type_JavaNioFloatBuffer, 35044);
/*  490: 490 */      GL15.glBindBuffer(34962, 0);
/*  491: 491 */      break;
/*  492:     */    
/*  494:     */    case 3: 
/*  495: 495 */      GL15.glGenBuffers(paramMesh.jdField_i_of_type_JavaNioIntBuffer);
/*  496: 496 */      GL15.glBindBuffer(34962, paramMesh.jdField_i_of_type_JavaNioIntBuffer.get(0));
/*  497:     */      
/*  498: 498 */      GL15.glBufferData(34962, paramMesh.jdField_d_of_type_JavaNioFloatBuffer, 35044);
/*  499: 499 */      GL15.glBindBuffer(34962, 0);
/*  500:     */    }
/*  501:     */    
/*  502: 502 */    GlUtil.f();
/*  503:     */  }
/*  504:     */  
/*  591:     */  private static Mesh a(String paramString)
/*  592:     */  {
/*  593:     */    Mesh localMesh;
/*  594:     */    
/*  680: 680 */    String str1 = paramString;String[] arrayOfString = null;(localMesh = new Mesh()).jdField_a_of_type_JavaLangString = str1;
/*  681: 681 */    int j = 0;
/*  682: 682 */    int k = 0;
/*  683: 683 */    int m = 0;
/*  684: 684 */    int n = 0;
/*  685:     */    
/*  686: 686 */    if (!(paramString = new File(paramString)).exists()) {
/*  687: 687 */      throw new FileNotFoundException(paramString.getPath());
/*  688:     */    }
/*  689:     */    
/*  691: 691 */    BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));
/*  692:     */    try {
/*  693: 693 */      while (localBufferedReader.ready())
/*  694:     */      {
/*  695:     */        String str2;
/*  696: 696 */        if ((str2 = localBufferedReader.readLine()).contains("vn ")) {
/*  697: 697 */          localMesh.jdField_b_of_type_Int += 1;
/*  698:     */        }
/*  699: 699 */        if (str2.contains("vt ")) {
/*  700: 700 */          localMesh.jdField_a_of_type_Int += 1;
/*  701:     */        }
/*  702: 702 */        if (str2.contains("clazz ")) {
/*  703: 703 */          localMesh.f += 1;
/*  704:     */        }
/*  705: 705 */        if (str2.contains("f ")) {
/*  706: 706 */          localMesh.jdField_h_of_type_Int += 1;
/*  707:     */        }
/*  708:     */      }
/*  709:     */      
/*  715: 715 */      localMesh.jdField_c_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.jdField_b_of_type_Int];
/*  716: 716 */      localMesh.jdField_b_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.jdField_a_of_type_Int];
/*  717: 717 */      localMesh.jdField_a_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.f];
/*  718: 718 */      localMesh.jdField_a_of_type_ArrayOfXV = new xV[localMesh.jdField_h_of_type_Int];
/*  719: 719 */      for (int i1 = 0; i1 < localMesh.jdField_a_of_type_ArrayOfXV.length; i1++) {
/*  720: 720 */        localMesh.jdField_a_of_type_ArrayOfXV[i1] = new xV();
/*  721:     */      }
/*  722: 722 */      localBufferedReader = new BufferedReader(new FileReader(paramString));
/*  723: 723 */      while (localBufferedReader.ready()) {
/*  724:     */        String str3;
/*  725: 725 */        if ((str3 = localBufferedReader.readLine()).contains("vn ")) {
/*  726: 726 */          paramString = str3.split("[\\s]+");
/*  727:     */          
/*  728: 728 */          localMesh.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[k] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
/*  729: 729 */          k++;
/*  730:     */        }
/*  731: 731 */        if (str3.contains("vt ")) {
/*  732: 732 */          paramString = str3.split("[\\s]+");
/*  733:     */          
/*  734: 734 */          localMesh.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[m] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
/*  735: 735 */          m++;
/*  736:     */        }
/*  737: 737 */        if (str3.contains("clazz ")) {
/*  738: 738 */          paramString = str3.split("[\\s]+");
/*  739:     */          
/*  740: 740 */          localMesh.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[j] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
/*  741: 741 */          j++;
/*  742:     */        }
/*  743: 743 */        if (str3.contains("f ")) {
/*  744: 744 */          paramString = str3.split("[\\s]+");
/*  745:     */          
/*  746: 746 */          localMesh.jdField_a_of_type_Boolean = true;
/*  747:     */          
/*  752: 752 */          for (int i2 = 1; i2 < paramString.length; i2++)
/*  753:     */          {
/*  755: 755 */            arrayOfString = paramString[i2].split("/");
/*  756: 756 */            localMesh.jdField_a_of_type_ArrayOfXV[n].a[(i2 - 1)] = (Integer.parseInt(arrayOfString[0]) - 1);
/*  757:     */            
/*  759: 759 */            localMesh.jdField_a_of_type_ArrayOfXV[n].c[(i2 - 1)] = (Integer.parseInt(arrayOfString[1]) - 1);
/*  760:     */            
/*  762: 762 */            localMesh.jdField_a_of_type_ArrayOfXV[n].b[(i2 - 1)] = (Integer.parseInt(arrayOfString[2]) - 1);
/*  763:     */          }
/*  764:     */          
/*  765: 765 */          n++;
/*  766:     */        }
/*  767:     */      }
/*  768:     */    }
/*  769:     */    catch (IOException localIOException)
/*  770:     */    {
/*  771: 771 */      
/*  772:     */      
/*  773: 773 */        localIOException;
/*  774:     */    }
/*  775:     */    
/*  777: 775 */    return localMesh;
/*  778:     */  }
/*  779:     */  
/*  783:     */  public static void main(String[] paramArrayOfString)
/*  784:     */  {
/*  785:     */    try
/*  786:     */    {
/*  787: 785 */      a("data/test.obj"); return;
/*  788: 786 */    } catch (FileNotFoundException localFileNotFoundException) { 
/*  789:     */      
/*  790: 788 */        localFileNotFoundException;
/*  791:     */    }
/*  792:     */  }
/*  793:     */  
/*  830: 826 */  private boolean jdField_a_of_type_Boolean = false;
/*  831:     */  
/*  833: 829 */  private IntBuffer jdField_d_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  834:     */  
/*  836: 832 */  private IntBuffer jdField_e_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  837:     */  
/*  838: 834 */  private IntBuffer jdField_f_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  839:     */  
/*  841:     */  private IntBuffer g;
/*  842:     */  
/*  844:     */  private IntBuffer h;
/*  845:     */  
/*  847: 843 */  private IntBuffer jdField_i_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  848:     */  public int g;
/*  849: 845 */  private int jdField_a_of_type_Int; public int h; private int jdField_b_of_type_Int; private boolean jdField_b_of_type_Boolean; private yi[] jdField_a_of_type_ArrayOfYi; private yf jdField_a_of_type_Yf; private boolean jdField_c_of_type_Boolean; private static int jdField_c_of_type_Int; private int jdField_d_of_type_Int = 0;
/*  850:     */  
/*  943:     */  public IntBuffer b;
/*  944:     */  
/* 1037:     */  public IntBuffer c;
/* 1038:     */  
/* 1130:     */  public FloatBuffer c;
/* 1131:     */  
/* 1223:     */  public FloatBuffer d;
/* 1224:     */  
/* 1316:     */  private static int jdField_e_of_type_Int;
/* 1317:     */  
/* 1409:1405 */  private int jdField_i_of_type_Int = 4;
/* 1410:     */  
/* 1413:     */  private boolean jdField_d_of_type_Boolean;
/* 1414:     */  
/* 1416:1412 */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/* 1417:     */  
/* 1418:     */  public Mesh()
/* 1419:     */  {
/* 1420: 836 */    this.jdField_g_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/* 1421:     */    
/* 1422: 838 */    this.jdField_h_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/* 1423:     */    
/* 2001:1417 */    this.jdField_b_of_type_Boolean = true;
/* 2002:1418 */    this.jdField_a_of_type_ZB = new zB();
/* 2003:     */  }
/* 2004:     */  
/* 2005:     */  public void a()
/* 2006:     */  {
/* 2007:1423 */    if (a() != null) {
/* 2008:1424 */      a().b();
/* 2009:     */    }
/* 2010:1426 */    if (this.jdField_g_of_type_Int == 3)
/* 2011:     */    {
/* 2012:1428 */      this.jdField_d_of_type_JavaNioIntBuffer.rewind();
/* 2013:1429 */      this.jdField_e_of_type_JavaNioIntBuffer.rewind();
/* 2014:1430 */      this.jdField_i_of_type_JavaNioIntBuffer.rewind();
/* 2015:1431 */      GL15.glDeleteBuffers(this.jdField_d_of_type_JavaNioIntBuffer);
/* 2016:1432 */      GL15.glDeleteBuffers(this.jdField_e_of_type_JavaNioIntBuffer);
/* 2017:1433 */      GL15.glDeleteBuffers(this.jdField_i_of_type_JavaNioIntBuffer);
/* 2018:     */    }
/* 2019:1435 */    for (Iterator localIterator = this.jdField_a_of_type_JavaUtilList.iterator(); localIterator.hasNext();) {
/* 2020:1436 */      ((xM)localIterator.next()).a();
/* 2021:     */    }
/* 2022:     */  }
/* 2023:     */  
/* 2040:     */  public void b()
/* 2041:     */  {
/* 2042:1458 */    if (h()) {
/* 2043:1459 */      return;
/* 2044:     */    }
/* 2045:     */    
/* 2046:1462 */    if (xu.A.b()) {
/* 2047:1463 */      this.jdField_c_of_type_Boolean = true;
/* 2048:1464 */      GL11.glPolygonMode(1032, 6913);
/* 2049:     */    }
/* 2050:     */    
/* 2051:1467 */    Object localObject = this; if (!h()) { if (((Mesh)localObject).jdField_b_of_type_Boolean) { ((Mesh)localObject).c();((Mesh)localObject).jdField_b_of_type_Boolean = false; } if (((Mesh)localObject).a() != null) ((Mesh)localObject).jdField_c_of_type_Boolean = ((Mesh)((Mesh)localObject).a()).jdField_c_of_type_Boolean; GlUtil.d();GL11.glDisable(3553);GL11.glBindTexture(3553, 0);GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);GL11.glEnable(2896);GL11.glEnable(2929); if (((Mesh)localObject).jdField_c_of_type_Boolean) { GL11.glDisable(3042);GL11.glDisable(2896);GL11.glDisable(3553);GL11.glEnable(2903);GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F); } else { ((Mesh)localObject).jdField_a_of_type_ZB.a();GL11.glEnable(3553);
/* 2052:     */      }
/* 2053:     */    }
/* 2054:1470 */    switch (this.jdField_g_of_type_Int)
/* 2055:     */    {
/* 2056:     */    case 3: 
/* 2057:1473 */      if ((!jdField_f_of_type_Boolean) && (this.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 2058:1474 */      e();
/* 2059:     */      
/* 2060:1476 */      break;
/* 2061:     */    case 2: 
/* 2062:1478 */      throw new UnsupportedOperationException();
/* 2063:     */    case 0: case 1: 
/* 2064:     */    default: 
/* 2065:1481 */      System.err.println("SOFTWARE " + this);
/* 2066:1482 */      d();
/* 2067:     */    }
/* 2068:1484 */    for (localObject = this.jdField_a_of_type_JavaUtilList.iterator(); ((Iterator)localObject).hasNext();) {
/* 2069:1485 */      ((xM)((Iterator)localObject).next()).b();
/* 2070:     */    }
/* 2071:     */    
/* 2073:1489 */    localObject = this;GlUtil.c();GL11.glDisable(2884);((Mesh)localObject).jdField_a_of_type_ZB.c();
/* 2074:     */    
/* 2075:1491 */    if (xu.A.b()) {
/* 2076:1492 */      GL11.glPolygonMode(1032, 6914);
/* 2077:1493 */      this.jdField_c_of_type_Boolean = false;
/* 2078:     */    }
/* 2079:     */  }
/* 2080:     */  
/* 2081:     */  public final void e()
/* 2082:     */  {
/* 2083:1499 */    h();
/* 2084:     */    
/* 2085:1501 */    i();
/* 2086:     */    
/* 2087:1503 */    m();
/* 2088:     */  }
/* 2089:     */  
/* 2091:     */  public final void f()
/* 2092:     */  {
/* 2093:1509 */    if ((!jdField_f_of_type_Boolean) && (!this.jdField_d_of_type_Boolean)) { throw new AssertionError();
/* 2094:     */    }
/* 2095:1511 */    GL11.glDrawElements(this.jdField_i_of_type_Int, this.jdField_h_of_type_Int * 3, 5125, 0L);
/* 2096:     */  }
/* 2097:     */  
/* 2238:     */  public final yf a()
/* 2239:     */  {
/* 2240:1656 */    return this.jdField_a_of_type_Yf;
/* 2241:     */  }
/* 2242:     */  
/* 2289:     */  public final yi[] a()
/* 2290:     */  {
/* 2291:1707 */    return this.jdField_a_of_type_ArrayOfYi;
/* 2292:     */  }
/* 2293:     */  
/* 2322:     */  public final boolean a()
/* 2323:     */  {
/* 2324:1740 */    this.jdField_a_of_type_XO.b(this.jdField_c_of_type_JavaxVecmathVector3f);
/* 2325:1741 */    this.jdField_c_of_type_JavaxVecmathVector3f.sub(this.b);
/* 2326:1742 */    return this.jdField_c_of_type_JavaxVecmathVector3f.length() < 2.0F;
/* 2327:     */  }
/* 2328:     */  
/* 2346:     */  public final void g()
/* 2347:     */  {
/* 2348:1764 */    if ((!jdField_f_of_type_Boolean) && (this.jdField_d_of_type_Boolean)) throw new AssertionError("Type is: " + this.jdField_g_of_type_Int);
/* 2349:1765 */    if ((!jdField_f_of_type_Boolean) && (this.jdField_g_of_type_Int != 3)) throw new AssertionError("Type is: " + this.jdField_g_of_type_Int);
/* 2350:1766 */    if ((!jdField_f_of_type_Boolean) && (this.jdField_d_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 2351:1767 */    if ((!jdField_f_of_type_Boolean) && (this.jdField_e_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 2352:1768 */    if ((!jdField_f_of_type_Boolean) && (this.jdField_i_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 2353:1769 */    if ((!jdField_f_of_type_Boolean) && (this.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0)) { throw new AssertionError();
/* 2354:     */    }
/* 2355:     */    
/* 2356:1772 */    GL20.glEnableVertexAttribArray(0);
/* 2357:1773 */    GL20.glEnableVertexAttribArray(1);
/* 2358:1774 */    GL20.glEnableVertexAttribArray(2);
/* 2359:     */    
/* 2362:1778 */    GL15.glBindBuffer(34962, this.jdField_d_of_type_JavaNioIntBuffer.get(0));
/* 2363:1779 */    GL20.glVertexAttribPointer(0, 3, 5126, false, 0, 0L);
/* 2364:     */    
/* 2367:1783 */    GL15.glBindBuffer(34962, this.jdField_i_of_type_JavaNioIntBuffer.get(0));
/* 2368:1784 */    GL20.glVertexAttribPointer(1, 3, 5126, false, 0, 0L);
/* 2369:     */    
/* 2371:1787 */    GL15.glBindBuffer(34962, this.jdField_e_of_type_JavaNioIntBuffer.get(0));
/* 2372:1788 */    GL20.glVertexAttribPointer(2, 2, 5126, false, 0, 0L);
/* 2373:     */    
/* 2377:1793 */    GL15.glBindBuffer(34963, this.jdField_f_of_type_JavaNioIntBuffer.get(0));
/* 2378:     */    
/* 2379:1795 */    this.jdField_d_of_type_Boolean = true;
/* 2380:     */  }
/* 2381:     */  
/* 2386:     */  public final void h()
/* 2387:     */  {
/* 2388:1804 */    GL11.glEnableClientState(32884);
/* 2389:     */    
/* 2390:1806 */    GL15.glBindBuffer(34962, this.jdField_d_of_type_JavaNioIntBuffer.get(0));
/* 2391:     */    
/* 2392:1808 */    GL11.glVertexPointer(3, 5126, 0, 0L);
/* 2393:     */    
/* 2399:1815 */    GL11.glEnableClientState(32885);
/* 2400:     */    
/* 2401:1817 */    GL15.glBindBuffer(34962, this.jdField_i_of_type_JavaNioIntBuffer.get(0));
/* 2402:     */    
/* 2403:1819 */    GL11.glNormalPointer(5126, 0, 0L);
/* 2404:     */    
/* 2406:1822 */    GL11.glEnableClientState(32888);
/* 2407:     */    
/* 2408:1824 */    GL15.glBindBuffer(34962, this.jdField_e_of_type_JavaNioIntBuffer.get(0));
/* 2409:     */    
/* 2410:1826 */    GL11.glTexCoordPointer(2, 5126, 0, 0L);
/* 2411:     */    
/* 2412:1828 */    if (this.jdField_a_of_type_Yf != null) {
/* 2413:1829 */      this.jdField_a_of_type_Yf.a();
/* 2414:     */    }
/* 2415:1831 */    GL15.glBindBuffer(34963, this.jdField_f_of_type_JavaNioIntBuffer.get(0));
/* 2416:     */  }
/* 2417:     */  
/* 2451:     */  public void c() {}
/* 2452:     */  
/* 2486:     */  public final void i()
/* 2487:     */  {
/* 2488:1904 */    GL11.glDrawElements(this.jdField_i_of_type_Int, this.jdField_h_of_type_Int * 3, 5125, 0L);
/* 2489:     */  }
/* 2490:     */  
/* 2541:     */  public final void b(int paramInt)
/* 2542:     */  {
/* 2543:1959 */    this.jdField_h_of_type_Int = paramInt;
/* 2544:     */  }
/* 2545:     */  
/* 2559:     */  public final void j()
/* 2560:     */  {
/* 2561:1977 */    this.jdField_a_of_type_Boolean = true;
/* 2562:     */  }
/* 2563:     */  
/* 2566:     */  public final void a(yf paramyf)
/* 2567:     */  {
/* 2568:1984 */    this.jdField_a_of_type_Yf = paramyf;
/* 2569:     */  }
/* 2570:     */  
/* 2577:     */  public static void k() {}
/* 2578:     */  
/* 2584:     */  public final void c(int paramInt)
/* 2585:     */  {
/* 2586:2002 */    this.jdField_a_of_type_Int = paramInt;
/* 2587:     */  }
/* 2588:     */  
/* 2634:     */  public final void a(yi[] paramArrayOfyi)
/* 2635:     */  {
/* 2636:2052 */    this.jdField_a_of_type_ArrayOfYi = paramArrayOfyi;
/* 2637:     */  }
/* 2638:     */  
/* 2639:     */  private void d()
/* 2640:     */  {
/* 2641:2057 */    GL11.glBegin(4);
/* 2642:     */    
/* 2647:2063 */    if ((this.jdField_a_of_type_ArrayOfXV == null) || (this.jdField_a_of_type_ArrayOfXV.length <= 0)) {
/* 2648:2064 */      throw new IllegalArgumentException("Mesh " + b() + " has no faces");
/* 2649:     */    }
/* 2650:     */    
/* 2651:2067 */    for (int j = 0; j < this.jdField_a_of_type_ArrayOfXV.length; 
/* 2652:     */        
/* 2654:2070 */        j++)
/* 2655:     */    {
/* 2662:2078 */      if ((this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_a_of_type_ArrayOfXV[j].c != null))
/* 2663:     */      {
/* 2664:2080 */        GL11.glTexCoord2f(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[0]].x, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[0]].y);
/* 2665:     */      }
/* 2666:     */      
/* 2671:2087 */      if (this.jdField_a_of_type_Boolean) {
/* 2672:2088 */        GL11.glNormal3f(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[0]].x, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[0]].y, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[0]].z);
/* 2674:     */      }
/* 2675:     */      else
/* 2676:     */      {
/* 2677:2093 */        GL11.glNormal3f(null.x, null.y, null.z);
/* 2678:     */      }
/* 2679:     */      
/* 2685:     */      Vector3f[] arrayOfVector3f;
/* 2686:     */      
/* 2691:2107 */      GL11.glVertex3f((arrayOfVector3f = this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f)[this.jdField_a_of_type_ArrayOfXV[j].a[0]].x, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[0]].y, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[0]].z);
/* 2692:     */      
/* 2698:2114 */      if ((this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_a_of_type_ArrayOfXV[j].c != null)) {
/* 2699:2115 */        GL11.glTexCoord2f(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[1]].x, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[1]].y);
/* 2700:     */      }
/* 2701:     */      
/* 2706:2122 */      if (this.jdField_a_of_type_Boolean) {
/* 2707:2123 */        GL11.glNormal3f(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[1]].x, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[1]].y, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[1]].z);
/* 2709:     */      }
/* 2710:     */      else
/* 2711:     */      {
/* 2712:2128 */        GL11.glNormal3f(null.x, null.y, null.z);
/* 2713:     */      }
/* 2714:     */      
/* 2724:2140 */      GL11.glVertex3f(arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[1]].x, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[1]].y, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[1]].z);
/* 2725:     */      
/* 2729:2145 */      if ((this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_a_of_type_ArrayOfXV[j].c != null)) {
/* 2730:2146 */        GL11.glTexCoord2f(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[2]].x, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[2]].y);
/* 2731:     */      }
/* 2732:     */      
/* 2737:2153 */      if (this.jdField_a_of_type_Boolean) {
/* 2738:2154 */        GL11.glNormal3f(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[2]].x, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[2]].y, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[2]].z);
/* 2740:     */      }
/* 2741:     */      else
/* 2742:     */      {
/* 2743:2159 */        GL11.glNormal3f(null.x, null.y, null.z);
/* 2744:     */      }
/* 2745:     */      
/* 2754:2170 */      GL11.glVertex3f(arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[2]].x, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[2]].y, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[2]].z);
/* 2755:     */    }
/* 2756:     */    
/* 2759:2175 */    GL11.glEnd();
/* 2760:     */  }
/* 2761:     */  
/* 2858:     */  public final void l()
/* 2859:     */  {
/* 2860:2276 */    GL15.glBindBuffer(34963, 0);GL15.glBindBuffer(34962, 0);
/* 2861:     */    
/* 2863:2279 */    GL20.glDisableVertexAttribArray(0);
/* 2864:2280 */    GL20.glDisableVertexAttribArray(1);
/* 2865:2281 */    GL20.glDisableVertexAttribArray(2);
/* 2866:2282 */    this.jdField_d_of_type_Boolean = false;
/* 2867:     */  }
/* 2868:     */  
/* 2869:2285 */  public final void m() { GL15.glBindBuffer(34962, 0);
/* 2870:     */    
/* 2872:2288 */    GL11.glDisableClientState(32884);
/* 2873:     */    
/* 2874:2290 */    GL11.glDisableClientState(32888);
/* 2875:     */    
/* 2876:2292 */    GL11.glDisableClientState(32885);
/* 2877:     */    
/* 2878:2294 */    if (this.jdField_a_of_type_Yf != null) {
/* 2879:2295 */      this.jdField_a_of_type_Yf.b();
/* 2880:     */    }
/* 2881:2297 */    GL15.glBindBuffer(34963, 0);
/* 2882:     */  }
/* 2883:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.forms.Mesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */