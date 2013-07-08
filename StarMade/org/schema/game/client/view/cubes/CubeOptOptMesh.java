/*   1:    */package org.schema.game.client.view.cubes;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import ct;
/*   5:    */import iO;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.nio.ByteBuffer;
/*   8:    */import java.nio.ByteOrder;
/*   9:    */import java.nio.FloatBuffer;
/*  10:    */import java.nio.IntBuffer;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import org.lwjgl.BufferUtils;
/*  13:    */import org.lwjgl.opengl.ARBMapBufferRange;
/*  14:    */import org.lwjgl.opengl.ContextCapabilities;
/*  15:    */import org.lwjgl.opengl.GL11;
/*  16:    */import org.lwjgl.opengl.GL15;
/*  17:    */import org.lwjgl.opengl.GLContext;
/*  18:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  19:    */import org.schema.schine.graphicsengine.forms.Mesh;
/*  20:    */import q;
/*  21:    */import xu;
/*  22:    */
/*  36:    */public class CubeOptOptMesh
/*  37:    */  extends Mesh
/*  38:    */{
/*  39: 39 */  private static final int i = xu.R.a().equals("STATIC") ? 35044 : xu.R.a().equals("DYNAMIC") ? 35048 : 35040;
/*  40:    */  
/*  41:    */  private IntBuffer jdField_d_of_type_JavaNioIntBuffer;
/*  42:    */  
/*  43:    */  private static String jdField_b_of_type_JavaLangString;
/*  44:    */  
/*  45:    */  public static int a;
/*  46:    */  
/*  47:    */  public static iO a;
/*  48:    */  
/*  49:    */  public static int b;
/*  50:    */  
/*  51:    */  public static int c;
/*  52:    */  
/*  53:    */  private long jdField_c_of_type_Long;
/*  54:    */  
/*  55:    */  public int d;
/*  56:    */  
/*  57:    */  private static int j;
/*  58: 58 */  public static int e = 0;
/*  59:    */  
/*  77:    */  private static ByteBuffer jdField_a_of_type_JavaNioByteBuffer;
/*  78:    */  
/*  97:    */  static
/*  98:    */  {
/*  99: 99 */    BufferUtils.createFloatBuffer(1048576 * CubeMeshBufferContainer.jdField_a_of_type_Int);
/* 100:    */    
/* 105:105 */    BufferUtils.createFloatBuffer(4);
/* 106:    */  }
/* 107:    */  
/* 108:108 */  private static boolean jdField_a_of_type_Boolean = true;
/* 109:    */  private static boolean jdField_b_of_type_Boolean;
/* 110:    */  private static boolean jdField_c_of_type_Boolean;
/* 111:111 */  public static long a; public long b; private boolean jdField_d_of_type_Boolean; private int k; private int l; private int m; private static int n = 42;
/* 112:    */  
/* 163:    */  public static void d()
/* 164:    */  {
/* 165:165 */    jdField_a_of_type_IO = new iO();
/* 166:    */    
/* 167:167 */    jdField_b_of_type_JavaLangString = "CubeMesh(4096)";
/* 168:    */    
/* 169:169 */    new Vector3f();
/* 170:    */  }
/* 171:    */  
/* 172:    */  public CubeOptOptMesh()
/* 173:    */  {
/* 174: 48 */    new ObjectArrayList();
/* 175:    */    
/* 187: 61 */    new q();
/* 188:    */    
/* 199: 73 */    this.k = (j++);
/* 200:    */    
/* 324:198 */    this.a = jdField_b_of_type_JavaLangString;
/* 325:199 */    this.h = 8192;
/* 326:200 */    this.g = 3;
/* 327:    */  }
/* 328:    */  
/* 337:    */  public final void a()
/* 338:    */  {
/* 339:213 */    super.a();
/* 340:    */    
/* 341:215 */    this.jdField_d_of_type_JavaNioIntBuffer.rewind();
/* 342:216 */    GL15.glDeleteBuffers(this.jdField_d_of_type_JavaNioIntBuffer);
/* 343:217 */    jdField_a_of_type_Int -= 1;
/* 344:218 */    this.jdField_d_of_type_Boolean = false;
/* 345:    */  }
/* 346:    */  
/* 347:    */  public static void main(String[] paramArrayOfString) {
/* 348:222 */    paramArrayOfString = new float[8];
/* 349:    */    
/* 351:225 */    for (int i1 = 0; i1 < 8; i1++) {
/* 352:226 */      paramArrayOfString[i1] = (i1 / 8.0F);
/* 353:227 */      System.err.println("FF: " + paramArrayOfString[i1]);
/* 354:    */    }
/* 355:    */  }
/* 356:    */  
/* 398:    */  public final void a(FloatBuffer paramFloatBuffer)
/* 399:    */  {
/* 400:274 */    if (!jdField_c_of_type_Boolean) {
/* 401:275 */      jdField_b_of_type_Boolean = GLContext.getCapabilities().GL_ARB_map_buffer_range;
/* 402:276 */      System.err.println("USE BUFFER RANGE: " + jdField_b_of_type_Boolean);
/* 403:277 */      jdField_c_of_type_Boolean = true;
/* 404:    */    }
/* 405:279 */    int i1 = 0;
/* 406:    */    
/* 408:282 */    paramFloatBuffer.flip();
/* 409:    */    boolean bool1;
/* 410:284 */    if (!this.jdField_d_of_type_Boolean) {
/* 411:285 */      FloatBuffer localFloatBuffer1 = paramFloatBuffer;CubeOptOptMesh localCubeOptOptMesh = this;this.g = 3;localCubeOptOptMesh.jdField_d_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);jdField_a_of_type_Int += 1;localCubeOptOptMesh.jdField_d_of_type_JavaNioIntBuffer.rewind();GL15.glGenBuffers(localCubeOptOptMesh.jdField_d_of_type_JavaNioIntBuffer);GL15.glBindBuffer(34962, localCubeOptOptMesh.b());GL15.glBufferData(34962, localFloatBuffer1.limit() << 2, i);ct.j += (localFloatBuffer1.limit() << 2);ct.k += (49152 * CubeMeshBufferContainer.jdField_a_of_type_Int << 2);localCubeOptOptMesh.l = localFloatBuffer1.limit();GL15.glBindBuffer(34962, 0);localCubeOptOptMesh.jdField_d_of_type_Boolean = true;
/* 412:286 */      bool1 = true;
/* 413:    */    }
/* 414:    */    
/* 417:291 */    if (paramFloatBuffer.limit() != 0)
/* 418:    */    {
/* 419:293 */      long l1 = 0L;
/* 420:294 */      long l2 = 0L;
/* 421:295 */      long l3 = 0L;
/* 422:296 */      GL15.glBindBuffer(34962, b());
/* 423:    */      
/* 425:299 */      if (this.l != paramFloatBuffer.limit())
/* 426:    */      {
/* 427:301 */        ct.j = (ct.j = ct.j - (this.l << 2)) + (paramFloatBuffer.limit() << 2);
/* 428:302 */        GL15.glBufferData(34962, paramFloatBuffer.limit() << 2, i);
/* 429:303 */        this.l = paramFloatBuffer.limit();
/* 430:    */      }
/* 431:    */      
/* 433:307 */      long l5 = System.nanoTime();
/* 434:308 */      if (jdField_a_of_type_Boolean)
/* 435:    */      {
/* 438:312 */        if (jdField_b_of_type_Boolean) {
/* 439:313 */          jdField_a_of_type_JavaNioByteBuffer = ARBMapBufferRange.glMapBufferRange(34962, 0L, paramFloatBuffer.limit() << 2, n, jdField_a_of_type_JavaNioByteBuffer == null ? null : jdField_a_of_type_JavaNioByteBuffer);
/* 442:    */        }
/* 443:    */        else
/* 444:    */        {
/* 446:320 */          jdField_a_of_type_JavaNioByteBuffer = GL15.glMapBuffer(34962, 35001, jdField_a_of_type_JavaNioByteBuffer == null ? null : jdField_a_of_type_JavaNioByteBuffer);
/* 447:    */        }
/* 448:    */        
/* 451:325 */        if ((jdField_a_of_type_JavaNioByteBuffer == null) && (jdField_a_of_type_Boolean)) {
/* 452:326 */          jdField_a_of_type_Boolean = false;
/* 453:327 */          System.err.println("[Exception]WARNING: MAPPED BUFFER HAS BEEN TURNED OFF " + GlUtil.a());
/* 454:    */        }
/* 455:    */      }
/* 456:330 */      long l6 = System.nanoTime() - l5;
/* 457:331 */      boolean bool2 = false;
/* 458:332 */      long l4; if (jdField_a_of_type_Boolean) {
/* 459:333 */        long l7 = System.nanoTime();
/* 460:334 */        FloatBuffer localFloatBuffer2 = jdField_a_of_type_JavaNioByteBuffer.order(ByteOrder.nativeOrder()).asFloatBuffer();
/* 461:335 */        l2 = (System.nanoTime() - l7) / 1000000L;
/* 462:    */        
/* 463:337 */        l7 = System.nanoTime();
/* 464:338 */        localFloatBuffer2.put(paramFloatBuffer);
/* 465:339 */        l1 = (System.nanoTime() - l7) / 1000000L;
/* 466:    */        
/* 467:341 */        localFloatBuffer2.flip();
/* 468:342 */        l7 = System.nanoTime();
/* 469:343 */        bool2 = GL15.glUnmapBuffer(34962);
/* 470:344 */        l4 = (System.nanoTime() - l7) / 1000000L;
/* 471:    */      }
/* 472:    */      else
/* 473:    */      {
/* 474:348 */        GL15.glBufferSubData(34962, 0L, paramFloatBuffer);
/* 475:    */      }
/* 476:    */      
/* 482:356 */      if ((CubeOptOptMesh.jdField_a_of_type_Long = (CubeOptOptMesh.jdField_a_of_type_Long = System.nanoTime() - l5) / 1000000L) > 10L) {
/* 483:357 */        System.err.println("[CUBE] WARNING: context switch time: " + jdField_a_of_type_Long + " ms : " + l6 / 1000000L + "ms: O " + l2 + "; P " + l1 + "; U " + l4 + "::; map " + jdField_a_of_type_Boolean + "; range " + jdField_b_of_type_Boolean + "; init " + bool1 + "  unmap " + bool2);
/* 484:    */      }
/* 485:    */    }
/* 486:    */    
/* 491:365 */    this.m = (paramFloatBuffer.limit() / CubeMeshBufferContainer.jdField_a_of_type_Int);
/* 492:    */  }
/* 493:    */  
/* 511:    */  public final void b()
/* 512:    */  {
/* 513:387 */    this.jdField_b_of_type_Long = 0L;
/* 514:    */    
/* 515:389 */    this.jdField_d_of_type_Int = 0;
/* 516:390 */    jdField_a_of_type_Long = 0L;
/* 517:    */    
/* 521:395 */    this.jdField_c_of_type_Long = System.nanoTime();
/* 522:    */    
/* 523:397 */    CubeOptOptMesh localCubeOptOptMesh1 = this; if (this.jdField_d_of_type_Boolean) { jdField_c_of_type_Int += 1;CubeOptOptMesh localCubeOptOptMesh2 = localCubeOptOptMesh1;long l1 = System.nanoTime();GL15.glBindBuffer(34962, localCubeOptOptMesh2.b());CubeOptOptMesh localCubeOptOptMesh3 = localCubeOptOptMesh2;GL11.glVertexPointer(CubeMeshBufferContainer.jdField_a_of_type_Int, 5126, 0, 0L);GL11.glDrawArrays(7, 0, localCubeOptOptMesh3.m);localCubeOptOptMesh2.jdField_b_of_type_Long += System.nanoTime() - l1;localCubeOptOptMesh1.jdField_d_of_type_Int = ((int)(System.nanoTime() - localCubeOptOptMesh1.jdField_c_of_type_Long));
/* 524:    */    }
/* 525:    */  }
/* 526:    */  
/* 698:    */  public boolean equals(Object paramObject)
/* 699:    */  {
/* 700:574 */    return this.k == ((CubeOptOptMesh)paramObject).k;
/* 701:    */  }
/* 702:    */  
/* 703:577 */  private int b() { return this.jdField_d_of_type_JavaNioIntBuffer.get(0); }
/* 704:    */  
/* 708:    */  public int hashCode()
/* 709:    */  {
/* 710:584 */    return this.k;
/* 711:    */  }
/* 712:    */  
/* 747:    */  public String toString()
/* 748:    */  {
/* 749:623 */    return super.toString() + ":" + hashCode();
/* 750:    */  }
/* 751:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.cubes.CubeOptOptMesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */