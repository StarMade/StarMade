/*   1:    */import java.nio.FloatBuffer;
/*   2:    */import org.lwjgl.BufferUtils;
/*   3:    */import org.lwjgl.util.vector.Matrix3f;
/*   4:    */import org.lwjgl.util.vector.Matrix4f;
/*   5:    */
/*  11:    */public class zu
/*  12:    */{
/*  13:    */  protected xi a;
/*  14:    */  protected xi b;
/*  15: 15 */  private static FloatBuffer b = BufferUtils.createFloatBuffer(9);
/*  16:    */  protected xi c;
/*  17:    */  
/*  18: 18 */  static { jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16); }
/*  19:    */  
/*  27:    */  protected int a;
/*  28:    */  
/*  34:    */  static FloatBuffer a;
/*  35:    */  
/*  41:    */  public static float[] a()
/*  42:    */  {
/*  43: 43 */    float[] arrayOfFloat = new float[19];
/*  44:    */    
/*  45: 45 */    double d1 = 1.0D / (Math.sqrt(6.283185307179586D) * 9.0D);
/*  46: 46 */    int i = -9;
/*  47:    */    
/*  49: 49 */    double d2 = 0.0D;
/*  50: 50 */    for (int j = 0; j < arrayOfFloat.length; j++)
/*  51:    */    {
/*  52: 52 */      double tmp36_35 = i;
/*  53: 53 */      double d4 = tmp36_35 * tmp36_35;
/*  54: 54 */      arrayOfFloat[j] = ((float)(d1 * Math.exp(-d4 * d1)));
/*  55:    */      
/*  56: 56 */      i++;
/*  57: 57 */      d2 += arrayOfFloat[j];
/*  58:    */    }
/*  59:    */    
/*  60: 60 */    double d3 = d2;
/*  61: 61 */    for (int k = 0; k < arrayOfFloat.length; k++)
/*  62:    */    {
/*  63: 63 */      int tmp91_89 = k;arrayOfFloat[tmp91_89] = ((float)(arrayOfFloat[tmp91_89] / d3));
/*  64:    */    }
/*  65:    */    
/*  66: 66 */    return arrayOfFloat;
/*  67:    */  }
/*  68:    */  
/*  89:    */  static void d()
/*  90:    */  {
/*  91: 91 */    Object localObject = new Matrix4f(xe.b);
/*  92: 92 */    Matrix4f localMatrix4f1 = new Matrix4f(xe.a);
/*  93:    */    
/*  94: 94 */    Matrix4f localMatrix4f2 = new Matrix4f();
/*  95:    */    
/*  96: 96 */    Matrix4f.mul((Matrix4f)localObject, localMatrix4f1, localMatrix4f2);
/*  97: 97 */    jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  98: 98 */    localMatrix4f2.store(jdField_a_of_type_JavaNioFloatBuffer);
/*  99: 99 */    jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 100:    */    
/* 102:102 */    (localObject = new Matrix3f()).m00 = localMatrix4f1.m00;((Matrix3f)localObject).m10 = localMatrix4f1.m10;((Matrix3f)localObject).m20 = localMatrix4f1.m20;
/* 103:103 */    ((Matrix3f)localObject).m01 = localMatrix4f1.m01;((Matrix3f)localObject).m11 = localMatrix4f1.m11;((Matrix3f)localObject).m21 = localMatrix4f1.m21;
/* 104:104 */    ((Matrix3f)localObject).m02 = localMatrix4f1.m02;((Matrix3f)localObject).m12 = localMatrix4f1.m12;((Matrix3f)localObject).m22 = localMatrix4f1.m22;
/* 105:105 */    ((Matrix3f)localObject).invert();
/* 106:    */    
/* 107:107 */    b.rewind();
/* 108:108 */    ((Matrix3f)localObject).store(b);
/* 109:109 */    b.rewind();
/* 110:    */  }
/* 111:    */  
/* 112:    */  public final void a(int paramInt) {
/* 113:113 */    this.jdField_a_of_type_Int = paramInt;
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */