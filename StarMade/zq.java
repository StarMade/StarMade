/*     */ import java.nio.FloatBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.util.vector.Matrix3f;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ 
/*     */ public class zq
/*     */ {
/*     */   protected xi a;
/*     */   protected xi b;
/*  15 */   private static FloatBuffer b = BufferUtils.createFloatBuffer(9);
/*     */   protected xi c;
/*     */   protected int a;
/*     */   static FloatBuffer a;
/*     */ 
/*     */   public static float[] a()
/*     */   {
/*  43 */     float[] arrayOfFloat = new float[19];
/*     */ 
/*  45 */     double d1 = 1.0D / (Math.sqrt(6.283185307179586D) * 9.0D);
/*  46 */     int i = -9;
/*     */ 
/*  49 */     double d2 = 0.0D;
/*  50 */     for (int j = 0; j < arrayOfFloat.length; j++)
/*     */     {
/*     */       double tmp36_35 = i;
/*  53 */       double d4 = tmp36_35 * tmp36_35;
/*     */ 
/*  54 */       arrayOfFloat[j] = ((float)(d1 * Math.exp(-d4 * d1)));
/*     */ 
/*  56 */       i++;
/*  57 */       d2 += arrayOfFloat[j];
/*     */     }
/*     */ 
/*  60 */     double d3 = d2;
/*  61 */     for (int k = 0; k < arrayOfFloat.length; k++)
/*     */     {
/*     */       int tmp91_89 = k; arrayOfFloat[tmp91_89] = ((float)(arrayOfFloat[tmp91_89] / d3));
/*     */     }
/*     */ 
/*  66 */     return arrayOfFloat;
/*     */   }
/*     */ 
/*     */   static void d()
/*     */   {
/*  91 */     Object localObject = new Matrix4f(xe.b);
/*  92 */     Matrix4f localMatrix4f1 = new Matrix4f(xe.a);
/*     */ 
/*  94 */     Matrix4f localMatrix4f2 = new Matrix4f();
/*     */ 
/*  96 */     Matrix4f.mul((Matrix4f)localObject, localMatrix4f1, localMatrix4f2);
/*  97 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  98 */     localMatrix4f2.store(jdField_a_of_type_JavaNioFloatBuffer);
/*  99 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*     */ 
/* 101 */     (
/* 102 */       localObject = new Matrix3f()).m00 = 
/* 102 */       localMatrix4f1.m00; ((Matrix3f)localObject).m10 = localMatrix4f1.m10; ((Matrix3f)localObject).m20 = localMatrix4f1.m20;
/* 103 */     ((Matrix3f)localObject).m01 = localMatrix4f1.m01; ((Matrix3f)localObject).m11 = localMatrix4f1.m11; ((Matrix3f)localObject).m21 = localMatrix4f1.m21;
/* 104 */     ((Matrix3f)localObject).m02 = localMatrix4f1.m02; ((Matrix3f)localObject).m12 = localMatrix4f1.m12; ((Matrix3f)localObject).m22 = localMatrix4f1.m22;
/* 105 */     ((Matrix3f)localObject).invert();
/*     */ 
/* 107 */     b.rewind();
/* 108 */     ((Matrix3f)localObject).store(b);
/* 109 */     b.rewind();
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt) {
/* 113 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  18 */     jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zq
 * JD-Core Version:    0.6.2
 */