/*    1:     */package org.schema.schine.graphicsengine.core;
/*    2:     */
/*    3:     */import com.bulletphysics.linearmath.AabbUtil2;
/*    4:     */import com.bulletphysics.linearmath.Transform;
/*    5:     */import java.awt.image.BufferedImage;
/*    6:     */import java.io.File;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.PrintStream;
/*    9:     */import java.lang.reflect.InvocationTargetException;
/*   10:     */import java.lang.reflect.Method;
/*   11:     */import java.nio.ByteBuffer;
/*   12:     */import java.nio.FloatBuffer;
/*   13:     */import java.nio.IntBuffer;
/*   14:     */import java.util.ArrayList;
/*   15:     */import javax.imageio.ImageIO;
/*   16:     */import javax.vecmath.Color4f;
/*   17:     */import javax.vecmath.Matrix3f;
/*   18:     */import javax.vecmath.Vector4f;
/*   19:     */import org.lwjgl.BufferUtils;
/*   20:     */import org.lwjgl.opengl.GL11;
/*   21:     */import org.lwjgl.opengl.GL20;
/*   22:     */import org.lwjgl.util.vector.Matrix4f;
/*   23:     */import org.schema.common.FastMath;
/*   24:     */import org.schema.schine.graphicsengine.camera.Camera;
/*   25:     */import org.schema.schine.graphicsengine.forms.Mesh;
/*   26:     */import xO;
/*   27:     */import xd;
/*   28:     */import xe;
/*   29:     */import xm;
/*   30:     */import xu;
/*   31:     */import zj;
/*   32:     */
/*   60:     */public class GlUtil
/*   61:     */{
/*   62:     */  public static FloatBuffer a;
/*   63:     */  public static FloatBuffer b;
/*   64:     */  private static IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/*   65:     */  private static ByteBuffer[] jdField_a_of_type_ArrayOfJavaNioByteBuffer;
/*   66:     */  private static javax.vecmath.Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*   67:     */  private static javax.vecmath.Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*   68:     */  private static javax.vecmath.Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/*   69:     */  private static javax.vecmath.Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/*   70:     */  private static Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*   71:     */  private static Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/*   72:     */  private static Vector4f jdField_c_of_type_JavaxVecmathVector4f;
/*   73:     */  private static Vector4f jdField_d_of_type_JavaxVecmathVector4f;
/*   74:     */  private static javax.vecmath.Vector3f jdField_e_of_type_JavaxVecmathVector3f;
/*   75:     */  private static javax.vecmath.Vector3f f;
/*   76:     */  private static javax.vecmath.Vector3f g;
/*   77:     */  private static javax.vecmath.Vector3f h;
/*   78:     */  private static javax.vecmath.Vector3f i;
/*   79:     */  private static javax.vecmath.Vector3f j;
/*   80:     */  private static javax.vecmath.Vector3f k;
/*   81:     */  private static javax.vecmath.Vector3f l;
/*   82:     */  private static org.lwjgl.util.vector.Vector3f jdField_a_of_type_OrgLwjglUtilVectorVector3f;
/*   83:     */  private static FloatBuffer[] jdField_a_of_type_ArrayOfJavaNioFloatBuffer;
/*   84:     */  private static int jdField_a_of_type_Int;
/*   85:     */  private static FloatBuffer[] jdField_b_of_type_ArrayOfJavaNioFloatBuffer;
/*   86:     */  private static int jdField_b_of_type_Int;
/*   87:     */  
/*   88:     */  static
/*   89:     */  {
/*   90:  90 */    BufferUtils.createFloatBuffer(3);
/*   91:     */    
/*   92:  92 */    jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*   93:  93 */    jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*   94:  94 */    BufferUtils.createFloatBuffer(16);
/*   95:  95 */    BufferUtils.createFloatBuffer(16);
/*   96:  96 */    BufferUtils.createFloatBuffer(16);
/*   97:  97 */    new Matrix3f();
/*   98:     */    
/*   99:  99 */    new javax.vecmath.Vector3f();
/*  100: 100 */    new javax.vecmath.Vector3f();
/*  101:     */    
/*  102: 102 */    new javax.vecmath.Vector3f();
/*  103:     */    
/*  104: 104 */    new Transform();
/*  105: 105 */    BufferUtils.createIntBuffer(1);
/*  106: 106 */    jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  107: 107 */    jdField_a_of_type_ArrayOfJavaNioByteBuffer = new ByteBuffer[6];
/*  108: 108 */    BufferUtils.createFloatBuffer(16);
/*  109:     */    
/*  110: 110 */    jdField_a_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  111: 111 */    jdField_b_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  112:     */    
/*  113: 113 */    jdField_c_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  114: 114 */    jdField_d_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  115:     */    
/*  116: 116 */    jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/*  117: 117 */    jdField_b_of_type_JavaxVecmathVector4f = new Vector4f();
/*  118: 118 */    jdField_c_of_type_JavaxVecmathVector4f = new Vector4f();
/*  119:     */    
/*  122: 122 */    jdField_d_of_type_JavaxVecmathVector4f = new Vector4f();
/*  123: 123 */    new javax.vecmath.Vector3f();
/*  124: 124 */    new javax.vecmath.Vector3f();
/*  125:     */    
/*  126: 126 */    new javax.vecmath.Vector3f();
/*  127:     */    
/*  128: 128 */    new javax.vecmath.Vector3f();
/*  129:     */    
/*  130: 130 */    jdField_e_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  131:     */    
/*  132: 132 */    f = new javax.vecmath.Vector3f();
/*  133:     */    
/*  135: 135 */    new Vector4f();
/*  136: 136 */    new Vector4f();
/*  137: 137 */    g = new javax.vecmath.Vector3f();
/*  138: 138 */    h = new javax.vecmath.Vector3f();
/*  139:     */    
/*  141: 141 */    i = new javax.vecmath.Vector3f();
/*  142:     */    
/*  143: 143 */    j = new javax.vecmath.Vector3f();
/*  144:     */    
/*  146: 146 */    k = new javax.vecmath.Vector3f();
/*  147:     */    
/*  148: 148 */    l = new javax.vecmath.Vector3f();
/*  149:     */    
/*  151: 151 */    jdField_a_of_type_OrgLwjglUtilVectorVector3f = new org.lwjgl.util.vector.Vector3f(0.0F, 0.0F, 0.0F);
/*  152:     */    
/*  153: 153 */    jdField_a_of_type_ArrayOfJavaNioFloatBuffer = new FloatBuffer[64];
/*  154: 154 */    jdField_a_of_type_Int = 0;
/*  155: 155 */    jdField_b_of_type_ArrayOfJavaNioFloatBuffer = new FloatBuffer[64];
/*  156:     */    
/*  157: 157 */    jdField_b_of_type_Int = 0;
/*  158:     */    
/*  159: 159 */    new Transform(); }
/*  160: 160 */  private static float[] jdField_a_of_type_ArrayOfFloat = new float[16];
/*  161: 161 */  private static FloatBuffer jdField_c_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  162: 162 */  private static Matrix4f jdField_a_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
/*  163:     */  
/*  164:     */  private static int jdField_c_of_type_Int;
/*  165:     */  
/*  166: 166 */  private static Vector4f jdField_e_of_type_JavaxVecmathVector4f = new Vector4f();
/*  167:     */  
/*  169:     */  public static Transform a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3, Transform paramTransform)
/*  170:     */  {
/*  171: 171 */    j.sub(paramVector3f3, paramVector3f1);
/*  172: 172 */    j.normalize();
/*  173:     */    
/*  175: 175 */    k.set(paramVector3f2);
/*  176: 176 */    l.cross(k, j);
/*  177:     */    
/*  180: 180 */    l.normalize();
/*  181:     */    
/*  185: 185 */    j.cross(l, k);
/*  186:     */    
/*  187: 187 */    d(k, paramTransform);
/*  188: 188 */    a(j, paramTransform);
/*  189: 189 */    c(l, paramTransform);
/*  190: 190 */    paramTransform.origin.set(paramVector3f1);
/*  191:     */    
/*  192: 192 */    return paramTransform;
/*  193:     */  }
/*  194:     */  
/*  244:     */  public static boolean a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/*  245:     */  {
/*  246: 246 */    return (paramVector3f1.x <= paramVector3f2.x) && (paramVector3f1.y <= paramVector3f2.y) && (paramVector3f1.z <= paramVector3f2.z);
/*  247:     */  }
/*  248:     */  
/*  249:     */  public static void a() {
/*  250: 250 */    for (int m = 0; m < jdField_a_of_type_ArrayOfJavaNioFloatBuffer.length; m++) {
/*  251: 251 */      jdField_a_of_type_ArrayOfJavaNioFloatBuffer[m] = BufferUtils.createFloatBuffer(16);
/*  252:     */    }
/*  253: 253 */    for (m = 0; m < jdField_b_of_type_ArrayOfJavaNioFloatBuffer.length; m++) {
/*  254: 254 */      jdField_b_of_type_ArrayOfJavaNioFloatBuffer[m] = BufferUtils.createFloatBuffer(16);
/*  255:     */    }
/*  256:     */  }
/*  257:     */  
/*  265:     */  public static void a(ByteBuffer paramByteBuffer)
/*  266:     */  {
/*  267:     */    Method localMethod;
/*  268:     */    
/*  274: 274 */    (localMethod = paramByteBuffer.getClass().getMethod("cleaner", new Class[0])).setAccessible(true);
/*  275:     */    
/*  276: 276 */    (
/*  277: 277 */      localMethod = (paramByteBuffer = localMethod.invoke(paramByteBuffer, new Object[0])).getClass().getMethod("clean", new Class[0])).setAccessible(true);
/*  278: 278 */    localMethod.invoke(paramByteBuffer, new Object[0]);
/*  279:     */  }
/*  280:     */  
/*  316:     */  public static javax.vecmath.Vector3f a(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*  317:     */  {
/*  318: 318 */    paramTransform.basis.getColumn(2, paramVector3f);
/*  319: 319 */    paramVector3f.negate();
/*  320: 320 */    return paramVector3f;
/*  321:     */  }
/*  322:     */  
/*  327:     */  public static javax.vecmath.Vector3f b(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*  328:     */  {
/*  329: 329 */    f(paramVector3f, paramTransform);
/*  330: 330 */    paramVector3f.negate();
/*  331: 331 */    return paramVector3f;
/*  332:     */  }
/*  333:     */  
/*  334:     */  public static ByteBuffer a(int paramInt1, int paramInt2) {
/*  335: 335 */    if (((paramInt2 = jdField_a_of_type_ArrayOfJavaNioByteBuffer[paramInt2]) == null) || (paramInt2.capacity() < paramInt1)) {
/*  336: 336 */      if (paramInt2 != null)
/*  337:     */      {
/*  338:     */        try {
/*  339: 339 */          a(paramInt2);
/*  340: 340 */        } catch (IllegalArgumentException localIllegalArgumentException) { 
/*  341:     */          
/*  350: 350 */            localIllegalArgumentException;
/*  351:     */        } catch (SecurityException localSecurityException) {
/*  352: 342 */          
/*  353:     */          
/*  360: 350 */            localSecurityException;
/*  361:     */        } catch (IllegalAccessException localIllegalAccessException) {
/*  362: 344 */          
/*  363:     */          
/*  368: 350 */            localIllegalAccessException;
/*  369:     */        } catch (InvocationTargetException localInvocationTargetException) {
/*  370: 346 */          
/*  371:     */          
/*  374: 350 */            localInvocationTargetException;
/*  375:     */        } catch (NoSuchMethodException localNoSuchMethodException) {
/*  376: 348 */          
/*  377:     */          
/*  378: 350 */            localNoSuchMethodException;
/*  379:     */        }
/*  380:     */        
/*  381: 351 */        System.gc();
/*  382:     */      }
/*  383:     */      
/*  385: 355 */      paramInt2 = BufferUtils.createByteBuffer(paramInt1);
/*  386:     */    }
/*  387: 357 */    paramInt2.limit(paramInt1);
/*  388: 358 */    paramInt2.rewind();
/*  389: 359 */    return paramInt2;
/*  390:     */  }
/*  391:     */  
/*  392:     */  public static javax.vecmath.Vector3f a(javax.vecmath.Vector3f paramVector3f) {
/*  393: 363 */    jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  394: 364 */    xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_b_of_type_JavaNioFloatBuffer);
/*  395: 365 */    paramVector3f.x = jdField_b_of_type_JavaNioFloatBuffer.get(2);
/*  396: 366 */    paramVector3f.y = jdField_b_of_type_JavaNioFloatBuffer.get(6);
/*  397: 367 */    paramVector3f.z = jdField_b_of_type_JavaNioFloatBuffer.get(10);
/*  398: 368 */    return paramVector3f;
/*  399:     */  }
/*  400:     */  
/*  405:     */  public static javax.vecmath.Vector3f c(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*  406:     */  {
/*  407: 377 */    paramTransform.basis.getColumn(2, paramVector3f);
/*  408: 378 */    return paramVector3f;
/*  409:     */  }
/*  410:     */  
/*  415:     */  public static String a()
/*  416:     */  {
/*  417: 387 */    int m = GL11.glGetError();
/*  418: 388 */    String str = "unknown " + m;
/*  419: 389 */    switch (m) {
/*  420:     */    case 0: 
/*  421: 391 */      return null;
/*  422:     */    case 1285: 
/*  423: 393 */      str = "GL_OUT_OF_MEMORY";
/*  424: 394 */      break;
/*  425:     */    case 1280: 
/*  426: 396 */      str = "GL_INVALID_ENUM";
/*  427: 397 */      break;
/*  428:     */    case 1281: 
/*  429: 399 */      str = "GL_INVALID_VALUE";
/*  430: 400 */      break;
/*  431:     */    case 1282: 
/*  432: 402 */      str = "GL_INVALID_OPERATION";
/*  433: 403 */      break;
/*  434:     */    case 1283: 
/*  435: 405 */      str = "GL_STACK_OVERFLOW: modelstack-sizes: proj: " + GL11.glGetInteger(2980) + ", model: " + GL11.glGetInteger(2979);
/*  436: 406 */      break;
/*  437:     */    case 1284: 
/*  438: 408 */      str = "GL_STACK_UNDERFLOW";
/*  439: 409 */      break;
/*  440:     */    case 1286: 
/*  441: 411 */      str = "GL_INVALID_FRAMEBUFFER_OPERATION";
/*  442:     */    }
/*  443: 413 */    return str;
/*  444:     */  }
/*  445:     */  
/*  446: 416 */  public static IntBuffer a() { jdField_a_of_type_JavaNioIntBuffer.rewind();
/*  447: 417 */    return jdField_a_of_type_JavaNioIntBuffer;
/*  448:     */  }
/*  449:     */  
/*  454:     */  public static javax.vecmath.Vector3f d(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*  455:     */  {
/*  456: 426 */    e(paramVector3f, paramTransform);
/*  457: 427 */    paramVector3f.negate();
/*  458: 428 */    return paramVector3f;
/*  459:     */  }
/*  460:     */  
/*  461:     */  public static void a(Transform paramTransform, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f) {
/*  462: 432 */    paramArrayOfVector3f[0].set(-paramFloat, -paramFloat, 0.0F);
/*  463:     */    
/*  465: 435 */    paramArrayOfVector3f[1].set(paramFloat, -paramFloat, 0.0F);
/*  466: 436 */    paramArrayOfVector3f[2].set(paramFloat, paramFloat, 0.0F);
/*  467: 437 */    paramArrayOfVector3f[3].set(-paramFloat, paramFloat, 0.0F);
/*  468:     */    
/*  469: 439 */    paramTransform.transform(paramArrayOfVector3f[0]);
/*  470: 440 */    paramTransform.transform(paramArrayOfVector3f[1]);
/*  471: 441 */    paramTransform.transform(paramArrayOfVector3f[2]);
/*  472: 442 */    paramTransform.transform(paramArrayOfVector3f[3]);
/*  473:     */  }
/*  474:     */  
/*  475: 445 */  public static javax.vecmath.Vector3f b(javax.vecmath.Vector3f paramVector3f) { jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  476: 446 */    xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_b_of_type_JavaNioFloatBuffer);
/*  477: 447 */    paramVector3f.x = jdField_b_of_type_JavaNioFloatBuffer.get(0);
/*  478: 448 */    paramVector3f.y = jdField_b_of_type_JavaNioFloatBuffer.get(4);
/*  479: 449 */    paramVector3f.z = jdField_b_of_type_JavaNioFloatBuffer.get(8);
/*  480: 450 */    return paramVector3f;
/*  481:     */  }
/*  482:     */  
/*  487:     */  public static javax.vecmath.Vector3f e(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*  488:     */  {
/*  489: 459 */    paramTransform.basis.getColumn(0, paramVector3f);
/*  490: 460 */    return paramVector3f;
/*  491:     */  }
/*  492:     */  
/*  493:     */  public static javax.vecmath.Vector3f c(javax.vecmath.Vector3f paramVector3f) {
/*  494: 464 */    jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  495: 465 */    xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_b_of_type_JavaNioFloatBuffer);
/*  496: 466 */    paramVector3f.x = jdField_b_of_type_JavaNioFloatBuffer.get(1);
/*  497: 467 */    paramVector3f.y = jdField_b_of_type_JavaNioFloatBuffer.get(5);
/*  498: 468 */    paramVector3f.z = jdField_b_of_type_JavaNioFloatBuffer.get(9);
/*  499: 469 */    return paramVector3f;
/*  500:     */  }
/*  501:     */  
/*  507:     */  public static javax.vecmath.Vector3f f(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*  508:     */  {
/*  509: 479 */    paramTransform.basis.getColumn(1, paramVector3f);
/*  510: 480 */    return paramVector3f;
/*  511:     */  }
/*  512:     */  
/*  513: 483 */  public static void b() { if (jdField_c_of_type_Int == 5889) {
/*  514: 484 */      xe.b.setIdentity();
/*  515:     */    } else {
/*  516: 486 */      xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.setIdentity();
/*  517:     */    }
/*  518: 488 */    GL11.glLoadIdentity();
/*  519:     */  }
/*  520:     */  
/*  524:     */  public static void a(FloatBuffer paramFloatBuffer)
/*  525:     */  {
/*  526: 496 */    paramFloatBuffer.rewind();
/*  527: 497 */    if (jdField_c_of_type_Int == 5889) {
/*  528: 498 */      xe.b.load(paramFloatBuffer);
/*  529:     */    } else {
/*  530: 500 */      xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(paramFloatBuffer);
/*  531:     */    }
/*  532: 502 */    paramFloatBuffer.rewind();
/*  533: 503 */    GL11.glLoadMatrix(paramFloatBuffer);
/*  534:     */  }
/*  535:     */  
/*  536: 506 */  public static void a(Transform paramTransform) { paramTransform.getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/*  537: 507 */    jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  538: 508 */    jdField_c_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/*  539: 509 */    jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  540: 510 */    a(jdField_c_of_type_JavaNioFloatBuffer);
/*  541:     */  }
/*  542:     */  
/*  543:     */  public static void a(int paramInt) {
/*  544: 514 */    GL11.glMatrixMode(GlUtil.jdField_c_of_type_Int = paramInt);
/*  545:     */  }
/*  546:     */  
/*  594:     */  public static void b(Transform paramTransform)
/*  595:     */  {
/*  596: 566 */    jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  597: 567 */    FloatBuffer localFloatBuffer = jdField_c_of_type_JavaNioFloatBuffer;paramTransform = paramTransform;localFloatBuffer.put(paramTransform.basis.m00);localFloatBuffer.put(paramTransform.basis.m10);localFloatBuffer.put(paramTransform.basis.m20);localFloatBuffer.put(0.0F);localFloatBuffer.put(paramTransform.basis.m01);localFloatBuffer.put(paramTransform.basis.m11);localFloatBuffer.put(paramTransform.basis.m21);localFloatBuffer.put(0.0F);localFloatBuffer.put(paramTransform.basis.m02);localFloatBuffer.put(paramTransform.basis.m12);localFloatBuffer.put(paramTransform.basis.m22);localFloatBuffer.put(0.0F);localFloatBuffer.put(paramTransform.origin.x);localFloatBuffer.put(paramTransform.origin.y);localFloatBuffer.put(paramTransform.origin.z);localFloatBuffer.put(1.0F);
/*  598: 568 */    jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  599: 569 */    (paramTransform = jdField_c_of_type_JavaNioFloatBuffer).rewind();jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(paramTransform);Matrix4f.mul(xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f, jdField_a_of_type_OrgLwjglUtilVectorMatrix4f, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f);paramTransform.rewind();GL11.glMultMatrix(paramTransform);
/*  600:     */  }
/*  601:     */  
/*  628:     */  public static final void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
/*  629:     */  {
/*  630: 600 */    float f2 = 2.0F / paramFloat1;
/*  631: 601 */    float f3 = 2.0F / (paramFloat3 - paramFloat2);
/*  632: 602 */    float f4 = -2.0F / (paramFloat5 - paramFloat4);
/*  633: 603 */    float f1 = -(paramFloat1 + 0.0F) / paramFloat1;
/*  634: 604 */    paramFloat1 = -(paramFloat3 + paramFloat2) / (paramFloat3 - paramFloat2);
/*  635: 605 */    paramFloat2 = -(paramFloat5 + paramFloat4) / (paramFloat5 - paramFloat4);
/*  636:     */    
/*  637: 607 */    jdField_a_of_type_ArrayOfFloat[0] = f2;
/*  638: 608 */    jdField_a_of_type_ArrayOfFloat[1] = 0.0F;
/*  639: 609 */    jdField_a_of_type_ArrayOfFloat[2] = 0.0F;
/*  640: 610 */    jdField_a_of_type_ArrayOfFloat[3] = 0.0F;
/*  641:     */    
/*  642: 612 */    jdField_a_of_type_ArrayOfFloat[4] = 0.0F;
/*  643: 613 */    jdField_a_of_type_ArrayOfFloat[5] = f3;
/*  644: 614 */    jdField_a_of_type_ArrayOfFloat[6] = 0.0F;
/*  645: 615 */    jdField_a_of_type_ArrayOfFloat[7] = 0.0F;
/*  646:     */    
/*  647: 617 */    jdField_a_of_type_ArrayOfFloat[8] = 0.0F;
/*  648: 618 */    jdField_a_of_type_ArrayOfFloat[9] = 0.0F;
/*  649: 619 */    jdField_a_of_type_ArrayOfFloat[10] = f4;
/*  650: 620 */    jdField_a_of_type_ArrayOfFloat[11] = 0.0F;
/*  651:     */    
/*  652: 622 */    jdField_a_of_type_ArrayOfFloat[12] = f1;
/*  653: 623 */    jdField_a_of_type_ArrayOfFloat[13] = paramFloat1;
/*  654: 624 */    jdField_a_of_type_ArrayOfFloat[14] = paramFloat2;
/*  655: 625 */    jdField_a_of_type_ArrayOfFloat[15] = 1.0F;
/*  656:     */    
/*  657: 627 */    jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  658: 628 */    jdField_c_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/*  659: 629 */    jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  660:     */    
/*  661: 631 */    xe.b.load(jdField_c_of_type_JavaNioFloatBuffer);
/*  662: 632 */    jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  663: 633 */    GL11.glLoadMatrix(jdField_c_of_type_JavaNioFloatBuffer);
/*  664:     */  }
/*  665:     */  
/*  666:     */  public static void c() {
/*  667: 637 */    if (jdField_c_of_type_Int == 5889) {
/*  668: 638 */      if ((!jdField_a_of_type_Boolean) && (jdField_b_of_type_Int <= 0)) throw new AssertionError(); jdField_b_of_type_Int -= 1;jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int].rewind();xe.b.load(jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int]);GL11.glPopMatrix();return;
/*  669:     */    }
/*  670: 640 */    if ((!jdField_a_of_type_Boolean) && (jdField_a_of_type_Int <= 0)) throw new AssertionError(); jdField_a_of_type_Int -= 1;jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int].rewind();xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int]);GL11.glPopMatrix();
/*  671:     */  }
/*  672:     */  
/*  686:     */  public static void d()
/*  687:     */  {
/*  688: 658 */    if (jdField_c_of_type_Int == 5889) {
/*  689: 659 */      jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int].rewind();xe.b.store(jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int]);jdField_b_of_type_Int += 1; if ((!jdField_a_of_type_Boolean) && (jdField_b_of_type_Int > jdField_b_of_type_ArrayOfJavaNioFloatBuffer.length)) throw new AssertionError(); GL11.glPushMatrix();return;
/*  690:     */    }
/*  691: 661 */    jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int].rewind();xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int]);jdField_a_of_type_Int += 1; if ((!jdField_a_of_type_Boolean) && (jdField_a_of_type_Int > jdField_a_of_type_ArrayOfJavaNioFloatBuffer.length)) throw new AssertionError(); GL11.glPushMatrix();
/*  692:     */  }
/*  693:     */  
/*  711:     */  public static void a(float paramFloat1, float paramFloat2, float paramFloat3)
/*  712:     */  {
/*  713: 683 */    a(paramFloat1, paramFloat2, paramFloat3, -1.0F, 1.0F);
/*  714:     */  }
/*  715:     */  
/*  789:     */  public static Matrix4f a(Matrix4f paramMatrix4f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean)
/*  790:     */  {
/*  791: 761 */    paramMatrix4f.setZero();
/*  792:     */    
/*  794:     */    float f1;
/*  795:     */    
/*  797: 767 */    float f2 = (paramFloat1 = -(f1 = paramFloat3 * FastMath.n(paramFloat1 / 360.0F * 3.141593F))) / paramFloat2;
/*  798: 768 */    paramFloat2 = f1 / paramFloat2;
/*  799:     */    
/*  801: 771 */    paramMatrix4f.m00 = (paramFloat3 * 2.0F / (f1 - paramFloat1));
/*  802: 772 */    paramMatrix4f.m11 = (paramFloat3 * 2.0F / (paramFloat2 - f2));
/*  803: 773 */    paramMatrix4f.m22 = (-(paramFloat4 + paramFloat3) / (paramFloat4 - paramFloat3));
/*  804:     */    
/*  805: 775 */    paramMatrix4f.m20 = ((f1 + paramFloat1) / (f1 - paramFloat1));
/*  806: 776 */    paramMatrix4f.m21 = ((paramFloat2 + f2) / (paramFloat2 - f2));
/*  807: 777 */    paramMatrix4f.m23 = -1.0F;
/*  808:     */    
/*  809: 779 */    paramMatrix4f.m32 = (-(paramFloat4 * 2.0F * paramFloat3) / (paramFloat4 - paramFloat3));
/*  810:     */    
/*  812: 782 */    if (paramBoolean) {
/*  813: 783 */      jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  814: 784 */      paramMatrix4f.store(jdField_a_of_type_JavaNioFloatBuffer);
/*  815: 785 */      jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  816:     */      
/*  817: 787 */      a(jdField_a_of_type_JavaNioFloatBuffer);
/*  818:     */    }
/*  819:     */    
/*  820: 790 */    return paramMatrix4f;
/*  821:     */  }
/*  822:     */  
/*  850:     */  public static int a(float paramFloat1, float paramFloat2, float paramFloat3, FloatBuffer paramFloatBuffer1, FloatBuffer paramFloatBuffer2, IntBuffer paramIntBuffer, FloatBuffer paramFloatBuffer3)
/*  851:     */  {
/*  852:     */    float[] arrayOfFloat;
/*  853:     */    
/*  880: 850 */    (arrayOfFloat = new float[8])[0] = (paramFloatBuffer1.get(0) * paramFloat1 + paramFloatBuffer1.get(4) * paramFloat2 + paramFloatBuffer1.get(8) * paramFloat3 + paramFloatBuffer1.get(12));
/*  881:     */    
/*  882: 852 */    arrayOfFloat[1] = (paramFloatBuffer1.get(1) * paramFloat1 + paramFloatBuffer1.get(5) * paramFloat2 + paramFloatBuffer1.get(9) * paramFloat3 + paramFloatBuffer1.get(13));
/*  883:     */    
/*  884: 854 */    arrayOfFloat[2] = (paramFloatBuffer1.get(2) * paramFloat1 + paramFloatBuffer1.get(6) * paramFloat2 + paramFloatBuffer1.get(10) * paramFloat3 + paramFloatBuffer1.get(14));
/*  885:     */    
/*  886: 856 */    arrayOfFloat[3] = (paramFloatBuffer1.get(3) * paramFloat1 + paramFloatBuffer1.get(7) * paramFloat2 + paramFloatBuffer1.get(11) * paramFloat3 + paramFloatBuffer1.get(15));
/*  887:     */    
/*  892: 862 */    arrayOfFloat[4] = (paramFloatBuffer2.get(0) * arrayOfFloat[0] + paramFloatBuffer2.get(4) * arrayOfFloat[1] + paramFloatBuffer2.get(8) * arrayOfFloat[2] + paramFloatBuffer2.get(12) * arrayOfFloat[3]);
/*  893:     */    
/*  894: 864 */    arrayOfFloat[5] = (paramFloatBuffer2.get(1) * arrayOfFloat[0] + paramFloatBuffer2.get(5) * arrayOfFloat[1] + paramFloatBuffer2.get(9) * arrayOfFloat[2] + paramFloatBuffer2.get(13) * arrayOfFloat[3]);
/*  895:     */    
/*  896: 866 */    arrayOfFloat[6] = (paramFloatBuffer2.get(2) * arrayOfFloat[0] + paramFloatBuffer2.get(6) * arrayOfFloat[1] + paramFloatBuffer2.get(10) * arrayOfFloat[2] + paramFloatBuffer2.get(14) * arrayOfFloat[3]);
/*  897:     */    
/*  898: 868 */    arrayOfFloat[7] = (-arrayOfFloat[2]);
/*  899:     */    
/*  901: 871 */    if (arrayOfFloat[7] == 0.0D) {
/*  902: 872 */      return 0;
/*  903:     */    }
/*  904: 874 */    arrayOfFloat[7] = (1.0F / arrayOfFloat[7]);
/*  905:     */    
/*  907: 877 */    arrayOfFloat[4] *= arrayOfFloat[7];
/*  908: 878 */    arrayOfFloat[5] *= arrayOfFloat[7];
/*  909: 879 */    arrayOfFloat[6] *= arrayOfFloat[7];
/*  910:     */    
/*  913: 883 */    paramFloatBuffer3.put(0, (arrayOfFloat[4] * 0.5F + 0.5F) * paramIntBuffer.get(2) + paramIntBuffer.get(0));
/*  914:     */    
/*  915: 885 */    paramFloatBuffer3.put(1, (arrayOfFloat[5] * 0.5F + 0.5F) * paramIntBuffer.get(3) + paramIntBuffer.get(1));
/*  916:     */    
/*  919: 889 */    paramFloatBuffer3.put(2, (1.0F + arrayOfFloat[6]) * 0.5F);
/*  920: 890 */    return 1;
/*  921:     */  }
/*  922:     */  
/*  923:     */  private static void a(zj paramzj, String paramString) {
/*  924: 894 */    if (xu.n.b()) {
/*  925: 895 */      paramzj = "[ERROR][SHADER] " + paramzj.jdField_a_of_type_JavaLangString + " - " + paramString + " HANDLE -1 ";
/*  926: 896 */      if (!xd.a.contains(paramzj)) {
/*  927: 897 */        xd.a.add(paramzj);
/*  928:     */      }
/*  929:     */    }
/*  930:     */  }
/*  931:     */  
/*  935:     */  public static boolean a(Transform paramTransform, Mesh paramMesh)
/*  936:     */  {
/*  937: 907 */    h.set(paramMesh.a().jdField_a_of_type_JavaxVecmathVector3f);
/*  938: 908 */    i.set(paramMesh.a().jdField_b_of_type_JavaxVecmathVector3f);
/*  939: 909 */    AabbUtil2.transformAabb(h, i, 0.0F, paramTransform, jdField_e_of_type_JavaxVecmathVector3f, f);
/*  940: 910 */    return xe.a().a(jdField_e_of_type_JavaxVecmathVector3f, f);
/*  941:     */  }
/*  942:     */  
/*  943:     */  public static boolean a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat) {
/*  944: 914 */    if ((a(paramVector3f1, paramFloat)) && (a(paramVector3f2, paramFloat))) { i.x = Math.min(paramVector3f1.x, paramVector3f2.x);i.y = Math.min(paramVector3f1.y, paramVector3f2.y);i.z = Math.min(paramVector3f1.z, paramVector3f2.z);h.x = Math.max(paramVector3f1.x, paramVector3f2.x);h.y = Math.max(paramVector3f1.y, paramVector3f2.y);h.z = Math.max(paramVector3f1.z, paramVector3f2.z); if (xe.a().a(h, i)) return true; } return false;
/*  945:     */  }
/*  946:     */  
/*  957:     */  public static boolean a(javax.vecmath.Vector3f paramVector3f, float paramFloat)
/*  958:     */  {
/*  959: 929 */    g.sub(paramVector3f, xe.a().a());
/*  960: 930 */    return g.length() <= paramFloat;
/*  961:     */  }
/*  962:     */  
/*  963: 933 */  public static boolean b(javax.vecmath.Vector3f paramVector3f, float paramFloat) { return (a(paramVector3f, paramFloat)) && (xe.a().a(paramVector3f)); }
/*  964:     */  
/* 1024:     */  public static void e()
/* 1025:     */  {
/* 1026:     */    String str;
/* 1027:     */    
/* 1086:1056 */    if ((str = a()) != null) {
/* 1087:     */      try {
/* 1088:1058 */        throw new GLException(str);
/* 1089:     */      } catch (GLException localGLException) {
/* 1090:1060 */        System.err.println("GL_ERROR: " + str + " ");
/* 1091:1061 */        localGLException.printStackTrace();
/* 1092:     */      }
/* 1093:     */    }
/* 1094:     */  }
/* 1095:     */  
/* 1098:     */  public static void f()
/* 1099:     */  {
/* 1100:     */    String str;
/* 1101:     */    
/* 1103:1073 */    if ((str = a()) != null) {
/* 1104:     */      try {
/* 1105:1075 */        throw new GLException(str);
/* 1106:     */      } catch (GLException localGLException) {
/* 1107:1077 */        System.err.println("GL_ERROR: " + str + " ");
/* 1108:1078 */        localGLException.printStackTrace();
/* 1109:1079 */        xm.a(localGLException);
/* 1110:     */      }
/* 1111:     */    }
/* 1112:     */  }
/* 1113:     */  
/* 1130:     */  public static void a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
/* 1131:     */  {
/* 1132:1102 */    javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(paramVector3f1);
/* 1133:1103 */    new javax.vecmath.Vector3f(paramVector3f1)
/* 1134:1104 */      .normalize();
/* 1135:1105 */    localVector3f.normalize();
/* 1136:     */    
/* 1140:1110 */    localVector3f.scale(paramVector3f2.dot(localVector3f));
/* 1141:     */    
/* 1142:1112 */    paramVector3f3.sub(paramVector3f2, localVector3f);
/* 1143:     */  }
/* 1144:     */  
/* 1156:     */  public static javax.vecmath.Vector3f a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
/* 1157:     */  {
/* 1158:1128 */    double d1 = paramVector3f2.dot(paramVector3f3);
/* 1159:     */    
/* 1160:1130 */    paramVector3f1.set(paramVector3f2);
/* 1161:1131 */    paramVector3f1.x = ((float)(paramVector3f1.x - d1 * paramVector3f3.x));
/* 1162:1132 */    paramVector3f1.y = ((float)(paramVector3f1.y - d1 * paramVector3f3.y));
/* 1163:1133 */    paramVector3f1.z = ((float)(paramVector3f1.z - d1 * paramVector3f3.z));
/* 1164:1134 */    return paramVector3f1;
/* 1165:     */  }
/* 1166:     */  
/* 1372:     */  public static void a(FloatBuffer paramFloatBuffer, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, Vector4f paramVector4f)
/* 1373:     */  {
/* 1374:1344 */    jdField_a_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1375:1345 */    jdField_a_of_type_JavaxVecmathVector3f.sub(paramVector3f1);
/* 1376:     */    
/* 1377:1347 */    jdField_b_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1378:1348 */    jdField_b_of_type_JavaxVecmathVector3f.add(paramVector3f2);
/* 1379:     */    
/* 1380:1350 */    jdField_c_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1381:1351 */    jdField_c_of_type_JavaxVecmathVector3f.add(paramVector3f1);
/* 1382:     */    
/* 1383:1353 */    jdField_d_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1384:1354 */    jdField_d_of_type_JavaxVecmathVector3f.sub(paramVector3f2);
/* 1385:     */    
/* 1386:1356 */    jdField_a_of_type_JavaxVecmathVector4f.set(jdField_a_of_type_JavaxVecmathVector3f.x, jdField_a_of_type_JavaxVecmathVector3f.y, jdField_a_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/* 1387:1357 */    jdField_b_of_type_JavaxVecmathVector4f.set(jdField_b_of_type_JavaxVecmathVector3f.x, jdField_b_of_type_JavaxVecmathVector3f.y, jdField_b_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/* 1388:1358 */    jdField_c_of_type_JavaxVecmathVector4f.set(jdField_c_of_type_JavaxVecmathVector3f.x, jdField_c_of_type_JavaxVecmathVector3f.y, jdField_c_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/* 1389:1359 */    jdField_d_of_type_JavaxVecmathVector4f.set(jdField_d_of_type_JavaxVecmathVector3f.x, jdField_d_of_type_JavaxVecmathVector3f.y, jdField_d_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/* 1390:     */    
/* 1391:1361 */    a(paramFloatBuffer, jdField_a_of_type_JavaxVecmathVector4f);
/* 1392:1362 */    a(paramFloatBuffer, jdField_b_of_type_JavaxVecmathVector4f);
/* 1393:1363 */    a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f);
/* 1394:1364 */    a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f);
/* 1395:     */  }
/* 1396:     */  
/* 1397:     */  public static void a(FloatBuffer paramFloatBuffer, javax.vecmath.Vector3f paramVector3f) {
/* 1398:1368 */    paramFloatBuffer.put(paramVector3f.x);
/* 1399:1369 */    paramFloatBuffer.put(paramVector3f.y);
/* 1400:1370 */    paramFloatBuffer.put(paramVector3f.z);
/* 1401:     */  }
/* 1402:     */  
/* 1403:     */  public static void a(FloatBuffer paramFloatBuffer, Vector4f paramVector4f) {
/* 1404:1374 */    paramFloatBuffer.put(paramVector4f.x);
/* 1405:1375 */    paramFloatBuffer.put(paramVector4f.y);
/* 1406:1376 */    paramFloatBuffer.put(paramVector4f.z);
/* 1407:1377 */    paramFloatBuffer.put(paramVector4f.w);
/* 1408:     */  }
/* 1409:     */  
/* 1410:1380 */  private static void a(FloatBuffer paramFloatBuffer, Vector4f paramVector4f, int paramInt) { paramFloatBuffer.put(paramInt, paramVector4f.x);
/* 1411:1381 */    paramFloatBuffer.put(paramInt + 1, paramVector4f.y);
/* 1412:1382 */    paramFloatBuffer.put(paramInt + 2, paramVector4f.z);
/* 1413:1383 */    paramFloatBuffer.put(paramInt + 3, paramVector4f.w);
/* 1414:     */  }
/* 1415:     */  
/* 1446:     */  public static void a(FloatBuffer paramFloatBuffer, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f, int paramInt)
/* 1447:     */  {
/* 1448:1418 */    jdField_c_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z, paramFloat);
/* 1449:1419 */    jdField_d_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[3].x, paramArrayOfVector3f[3].y, paramArrayOfVector3f[3].z, paramFloat);
/* 1450:1420 */    if (paramInt < 0)
/* 1451:     */    {
/* 1453:1423 */      a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f);
/* 1454:1424 */      a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f);return;
/* 1455:     */    }
/* 1456:1426 */    a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f, paramInt);
/* 1457:     */    
/* 1460:1430 */    a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f, paramInt + 4);
/* 1461:     */  }
/* 1462:     */  
/* 1465:     */  public static void a(FloatBuffer paramFloatBuffer, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f)
/* 1466:     */  {
/* 1467:1437 */    jdField_a_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z, paramFloat);
/* 1468:1438 */    jdField_b_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z, paramFloat);
/* 1469:1439 */    jdField_c_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z, paramFloat);
/* 1470:1440 */    jdField_d_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[3].x, paramArrayOfVector3f[3].y, paramArrayOfVector3f[3].z, paramFloat);
/* 1471:     */    
/* 1472:1442 */    a(paramFloatBuffer, jdField_a_of_type_JavaxVecmathVector4f);
/* 1473:1443 */    a(paramFloatBuffer, jdField_b_of_type_JavaxVecmathVector4f);
/* 1474:1444 */    a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f);
/* 1475:1445 */    a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f);
/* 1476:     */  }
/* 1477:     */  
/* 1535:     */  public static void a(float paramFloat)
/* 1536:     */  {
/* 1537:1507 */    jdField_a_of_type_OrgLwjglUtilVectorVector3f.set(0.0F, 0.0F, 1.0F);
/* 1538:1508 */    xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.rotate(paramFloat, jdField_a_of_type_OrgLwjglUtilVectorVector3f);
/* 1539:1509 */    GL11.glRotatef(paramFloat, 0.0F, 0.0F, 1.0F);
/* 1540:     */  }
/* 1541:     */  
/* 1542:1512 */  public static void b(float paramFloat1, float paramFloat2, float paramFloat3) { jdField_a_of_type_OrgLwjglUtilVectorVector3f.set(paramFloat1, paramFloat2, paramFloat3);
/* 1543:1513 */    xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.scale(jdField_a_of_type_OrgLwjglUtilVectorVector3f);
/* 1544:1514 */    GL11.glScalef(paramFloat1, paramFloat2, paramFloat3);
/* 1545:     */  }
/* 1546:     */  
/* 1563:     */  public static void a(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/* 1564:     */  {
/* 1565:1535 */    paramTransform.basis.setColumn(2, paramVector3f);
/* 1566:     */  }
/* 1567:     */  
/* 1572:     */  public static void b(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/* 1573:     */  {
/* 1574:1544 */    paramVector3f.negate();
/* 1575:1545 */    c(paramVector3f, paramTransform);
/* 1576:     */  }
/* 1577:     */  
/* 1582:     */  public static void c(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/* 1583:     */  {
/* 1584:1554 */    paramTransform.basis.setColumn(0, paramVector3f);
/* 1585:     */  }
/* 1586:     */  
/* 1591:     */  public static void d(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/* 1592:     */  {
/* 1593:1563 */    paramTransform.basis.setColumn(1, paramVector3f);
/* 1594:     */  }
/* 1595:     */  
/* 1614:1584 */  public static void a(javax.vecmath.Vector3f paramVector3f) { c(paramVector3f.x, paramVector3f.y, paramVector3f.z); }
/* 1615:     */  
/* 1616:     */  public static void c(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 1617:1587 */    jdField_a_of_type_OrgLwjglUtilVectorVector3f.set(paramFloat1, paramFloat2, paramFloat3);
/* 1618:1588 */    xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.translate(jdField_a_of_type_OrgLwjglUtilVectorVector3f);
/* 1619:1589 */    GL11.glTranslatef(paramFloat1, paramFloat2, paramFloat3);
/* 1620:     */  }
/* 1621:     */  
/* 1626:     */  public static void a(zj paramzj, String paramString, Color4f paramColor4f)
/* 1627:     */  {
/* 1628:     */    int m;
/* 1629:     */    
/* 1633:1603 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1634:1604 */      a(paramzj, paramString);return;
/* 1635:     */    }
/* 1636:1606 */    GL20.glUniform4f(m, paramColor4f.x, paramColor4f.y, paramColor4f.z, paramColor4f.w);
/* 1637:     */  }
/* 1638:     */  
/* 1643:     */  public static void a(zj paramzj, String paramString, float paramFloat)
/* 1644:     */  {
/* 1645:     */    int m;
/* 1646:     */    
/* 1650:1620 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1651:1621 */      a(paramzj, paramString);return;
/* 1652:     */    }
/* 1653:1623 */    GL20.glUniform1f(m, paramFloat);
/* 1654:     */  }
/* 1655:     */  
/* 1660:     */  public static void a(zj paramzj, String paramString, FloatBuffer paramFloatBuffer)
/* 1661:     */  {
/* 1662:     */    int m;
/* 1663:     */    
/* 1666:1636 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1667:1637 */      a(paramzj, paramString);return;
/* 1668:     */    }
/* 1669:1639 */    GL20.glUniform1(m, paramFloatBuffer);
/* 1670:     */  }
/* 1671:     */  
/* 1676:     */  public static void b(zj paramzj, String paramString, FloatBuffer paramFloatBuffer)
/* 1677:     */  {
/* 1678:     */    int m;
/* 1679:     */    
/* 1682:1652 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1683:1653 */      a(paramzj, paramString);return;
/* 1684:     */    }
/* 1685:1655 */    GL20.glUniform3(m, paramFloatBuffer);
/* 1686:     */  }
/* 1687:     */  
/* 1692:     */  public static void a(zj paramzj, String paramString, int paramInt)
/* 1693:     */  {
/* 1694:     */    int m;
/* 1695:     */    
/* 1698:1668 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1699:1669 */      a(paramzj, paramString);return;
/* 1700:     */    }
/* 1701:1671 */    GL20.glUniform1i(m, paramInt);
/* 1702:     */  }
/* 1703:     */  
/* 1737:     */  public static void c(zj paramzj, String paramString, FloatBuffer paramFloatBuffer)
/* 1738:     */  {
/* 1739:1709 */    int m = paramzj.a(paramString);
/* 1740:1710 */    if ((!jdField_a_of_type_Boolean) && (paramFloatBuffer.remaining() != 16)) throw new AssertionError();
/* 1741:1711 */    if (m == -1) {
/* 1742:1712 */      a(paramzj, paramString);return;
/* 1743:     */    }
/* 1744:1714 */    GL20.glUniformMatrix4(m, false, paramFloatBuffer);
/* 1745:     */  }
/* 1746:     */  
/* 1782:     */  public static void a(zj paramzj, String paramString, Transform[] paramArrayOfTransform)
/* 1783:     */  {
/* 1784:     */    FloatBuffer localFloatBuffer;
/* 1785:     */    
/* 1819:1789 */    if (((localFloatBuffer = paramzj.jdField_a_of_type_JavaNioFloatBuffer) == null) || (localFloatBuffer.capacity() < paramArrayOfTransform.length << 4)) {
/* 1820:1790 */      localFloatBuffer = BufferUtils.createFloatBuffer(paramArrayOfTransform.length << 4);
/* 1821:1791 */      paramzj.jdField_a_of_type_JavaNioFloatBuffer = localFloatBuffer;
/* 1822:     */    }
/* 1823:1793 */    localFloatBuffer.limit(paramArrayOfTransform.length << 4);
/* 1824:     */    
/* 1825:1795 */    localFloatBuffer.rewind();
/* 1826:1796 */    int m = (paramArrayOfTransform = paramArrayOfTransform).length; for (int n = 0; n < m; n++) {
/* 1827:1797 */      paramArrayOfTransform[n].getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/* 1828:1798 */      localFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/* 1829:     */    }
/* 1830:1800 */    localFloatBuffer.flip();
/* 1831:     */    
/* 1833:1803 */    if ((paramArrayOfTransform = paramzj.a(paramString)) == -1) {
/* 1834:1804 */      a(paramzj, paramString);return;
/* 1835:     */    }
/* 1836:1806 */    GL20.glUniformMatrix4(paramArrayOfTransform, false, localFloatBuffer);
/* 1837:     */  }
/* 1838:     */  
/* 1843:     */  public static void a(zj paramzj, String paramString, float paramFloat1, float paramFloat2)
/* 1844:     */  {
/* 1845:     */    int m;
/* 1846:     */    
/* 1850:1820 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1851:1821 */      a(paramzj, paramString);return;
/* 1852:     */    }
/* 1853:1823 */    GL20.glUniform2f(m, paramFloat1, paramFloat2);
/* 1854:     */  }
/* 1855:     */  
/* 1892:     */  public static void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/* 1893:     */  {
/* 1894:1864 */    if ((jdField_e_of_type_JavaxVecmathVector4f.x != paramFloat1) || (jdField_e_of_type_JavaxVecmathVector4f.y != paramFloat2) || (jdField_e_of_type_JavaxVecmathVector4f.z != paramFloat3) || (jdField_e_of_type_JavaxVecmathVector4f.w != paramFloat4))
/* 1895:     */    {
/* 1899:1869 */      jdField_e_of_type_JavaxVecmathVector4f.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/* 1900:1870 */      GL11.glColor4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/* 1901:     */    }
/* 1902:     */  }
/* 1903:     */  
/* 1905:     */  public static void a(zj paramzj, String paramString, float paramFloat1, float paramFloat2, float paramFloat3)
/* 1906:     */  {
/* 1907:     */    int m;
/* 1908:1878 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1909:1879 */      a(paramzj, paramString);return;
/* 1910:     */    }
/* 1911:1881 */    GL20.glUniform3f(m, paramFloat1, paramFloat2, paramFloat3);
/* 1912:     */  }
/* 1913:     */  
/* 1915:     */  public static void a(zj paramzj, String paramString, javax.vecmath.Vector3f paramVector3f)
/* 1916:     */  {
/* 1917:     */    int m;
/* 1918:1888 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1919:1889 */      a(paramzj, paramString);return;
/* 1920:     */    }
/* 1921:1891 */    GL20.glUniform3f(m, paramVector3f.x, paramVector3f.y, paramVector3f.z);
/* 1922:     */  }
/* 1923:     */  
/* 1933:     */  public static void a(zj paramzj, String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/* 1934:     */  {
/* 1935:     */    int m;
/* 1936:     */    
/* 1944:1914 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1945:1915 */      a(paramzj, paramString);return;
/* 1946:     */    }
/* 1947:1917 */    GL20.glUniform4f(m, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/* 1948:     */  }
/* 1949:     */  
/* 1950:     */  public static void a(zj paramzj, String paramString, Vector4f paramVector4f) {
/* 1951:     */    int m;
/* 1952:1922 */    if ((m = paramzj.a(paramString)) == -1) {
/* 1953:1923 */      a(paramzj, paramString);return;
/* 1954:     */    }
/* 1955:1925 */    GL20.glUniform4f(m, paramVector4f.x, paramVector4f.y, paramVector4f.z, paramVector4f.w);
/* 1956:     */  }
/* 1957:     */  
/* 1968:     */  public static void a(String paramString1, String paramString2, int paramInt1, int paramInt2)
/* 1969:     */  {
/* 1970:1940 */    ByteBuffer localByteBuffer = a(paramInt1 * paramInt2 << 2, 0);
/* 1971:     */    
/* 1972:1942 */    GL11.glReadPixels(0, 0, paramInt1, paramInt2, 6408, 5121, localByteBuffer);
/* 1973:     */    
/* 1974:1944 */    paramString1 = new File(paramString1 + "." + paramString2);
/* 1975:1945 */    paramString2 = paramString2.toUpperCase();
/* 1976:1946 */    BufferedImage localBufferedImage = new BufferedImage(paramInt1, paramInt2, 2);
/* 1977:     */    
/* 1978:1948 */    for (int m = 0; m < paramInt1; m++) {
/* 1979:1949 */      for (int n = 0; n < paramInt2; n++) {
/* 1980:1950 */        int i1 = m + paramInt1 * n << 2;
/* 1981:1951 */        int i2 = localByteBuffer.get(i1) & 0xFF;
/* 1982:1952 */        int i3 = localByteBuffer.get(i1 + 1) & 0xFF;
/* 1983:1953 */        int i4 = localByteBuffer.get(i1 + 2) & 0xFF;
/* 1984:1954 */        i1 = localByteBuffer.get(i1 + 3) & 0xFF;
/* 1985:     */        
/* 1987:1957 */        localBufferedImage.setRGB(m, paramInt2 - (n + 1), i1 << 24 | i2 << 16 | i3 << 8 | i4);
/* 1988:     */      }
/* 1989:     */    }
/* 1990:     */    
/* 1991:     */    try
/* 1992:     */    {
/* 1993:1963 */      ImageIO.write(localBufferedImage, paramString2, paramString1);
/* 1994:1964 */      return; } catch (IOException localIOException) { localIOException;
/* 1995:     */    }
/* 1996:     */  }
/* 1997:     */  
/* 1998:     */  public static void main(String[] paramArrayOfString) {}
/* 1999:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.core.GlUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */