/*   1:    */import java.text.DecimalFormat;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */import javax.vecmath.Vector4f;
/*   4:    */import org.schema.common.FastMath;
/*   5:    */
/*  57:    */public final class p
/*  58:    */{
/*  59:    */  static
/*  60:    */  {
/*  61: 61 */    new DecimalFormat("#,###,###,##0.00");
/*  62:    */  }
/*  63:    */  
/*  67:    */  public static Vector3f a(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3)
/*  68:    */  {
/*  69:    */    Vector3f localVector3f;
/*  70:    */    
/*  72: 72 */    (localVector3f = new Vector3f()).sub(paramVector3f1, paramVector3f3);
/*  73: 73 */    if (paramVector3f2.lengthSquared() == 0.0F)
/*  74:    */    {
/*  75: 75 */      return localVector3f;
/*  76:    */    }
/*  77:    */    
/*  80: 80 */    paramVector3f2.dot(paramVector3f2);
/*  81:    */    
/*  82: 82 */    double d1 = paramVector3f2.dot(paramVector3f2) - paramFloat * paramFloat;
/*  83: 83 */    double d2 = 2.0F * localVector3f.dot(paramVector3f2);
/*  84: 84 */    double d3 = localVector3f.dot(localVector3f);
/*  85:    */    
/*  87: 87 */    double d4 = (float)Math.sqrt(Math.abs(d2 * d2 - d1 * 4.0D * d3));
/*  88:    */    
/*  91: 91 */    double d5 = (-d2 - d4) / (d1 * 2.0D);
/*  92: 92 */    double d6 = (-d2 + d4) / (d1 * 2.0D);
/*  93:    */    
/*  94:    */    double d7;
/*  95: 95 */    if ((d5 > d6) && (d6 > 0.0D))
/*  96:    */    {
/*  97: 97 */      d7 = d6;
/*  98:    */    }
/*  99:    */    else
/* 100:    */    {
/* 101:101 */      d7 = d5;
/* 102:    */    }
/* 103:    */    
/* 104:104 */    paramFloat = new Vector3f();
/* 105:105 */    (
/* 106:106 */      paramVector3f2 = new Vector3f(paramVector3f2)).scale((float)d7);
/* 107:107 */    paramFloat.add(paramVector3f1, paramVector3f2);
/* 108:    */    
/* 109:109 */    (
/* 110:110 */      paramVector3f1 = new Vector3f()).sub(paramFloat, paramVector3f3);
/* 111:    */    
/* 113:113 */    return paramVector3f1;
/* 114:    */  }
/* 115:    */  
/* 158:    */  public static void a(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
/* 159:    */  {
/* 160:160 */    paramVector3f1.x = FastMath.a(paramVector3f1.x, paramVector3f2.x, paramVector3f3.x);
/* 161:161 */    paramVector3f1.y = FastMath.a(paramVector3f1.y, paramVector3f2.y, paramVector3f3.y);
/* 162:162 */    paramVector3f1.z = FastMath.a(paramVector3f1.z, paramVector3f2.z, paramVector3f3.z);
/* 163:    */  }
/* 164:    */  
/* 237:    */  public static float a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/* 238:    */  {
/* 239:239 */    float f = paramVector3f1.x * paramVector3f2.x + paramVector3f1.y * paramVector3f2.y;
/* 240:    */    
/* 242:242 */    return FastMath.a(paramVector3f1.x * paramVector3f2.y - paramVector3f1.y * paramVector3f2.x, f);
/* 243:    */  }
/* 244:    */  
/* 429:    */  public static boolean a(Vector3f paramVector3f)
/* 430:    */  {
/* 431:431 */    return (Float.isNaN(paramVector3f.x)) || (Float.isNaN(paramVector3f.y)) || (Float.isNaN(paramVector3f.z));
/* 432:    */  }
/* 433:    */  
/* 440:    */  public static void a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/* 441:    */  {
/* 442:442 */    if (paramVector3f2.x > paramVector3f1.x) paramVector3f1.x = paramVector3f2.x;
/* 443:443 */    if (paramVector3f2.y > paramVector3f1.y) paramVector3f1.y = paramVector3f2.y;
/* 444:444 */    if (paramVector3f2.z > paramVector3f1.z) { paramVector3f1.z = paramVector3f2.z;
/* 445:    */    }
/* 446:    */  }
/* 447:    */  
/* 454:    */  public static void b(Vector3f paramVector3f1, Vector3f paramVector3f2)
/* 455:    */  {
/* 456:456 */    if (paramVector3f2.x < paramVector3f1.x) paramVector3f1.x = paramVector3f2.x;
/* 457:457 */    if (paramVector3f2.y < paramVector3f1.y) paramVector3f1.y = paramVector3f2.y;
/* 458:458 */    if (paramVector3f2.z < paramVector3f1.z) { paramVector3f1.z = paramVector3f2.z;
/* 459:    */    }
/* 460:    */  }
/* 461:    */  
/* 497:    */  public static void a(Vector4f paramVector4f)
/* 498:    */  {
/* 499:499 */    if ((paramVector4f.x == 0.0F) && (paramVector4f.y == 0.0F) && (paramVector4f.z == 0.0F)) {
/* 500:500 */      paramVector4f.w = 0.0F;
/* 501:501 */      return;
/* 502:    */    }
/* 503:    */    
/* 504:504 */    float f = FastMath.m(paramVector4f.x * paramVector4f.x + paramVector4f.y * paramVector4f.y + paramVector4f.z * paramVector4f.z);
/* 505:    */    
/* 507:507 */    paramVector4f.x /= f;
/* 508:508 */    paramVector4f.y /= f;
/* 509:509 */    paramVector4f.z /= f;
/* 510:    */    
/* 511:511 */    paramVector4f.w = ((float)(2.0D * FastMath.a(paramVector4f.w)));
/* 512:    */  }
/* 513:    */  
/* 520:    */  public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
/* 521:    */  {
/* 522:522 */    return FastMath.l(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 + paramFloat3 * paramFloat3);
/* 523:    */  }
/* 524:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     p
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */