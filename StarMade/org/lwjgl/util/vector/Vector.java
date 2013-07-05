/*    */ package org.lwjgl.util.vector;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.nio.FloatBuffer;
/*    */ 
/*    */ public abstract class Vector
/*    */   implements Serializable, ReadableVector
/*    */ {
/*    */   public final float length()
/*    */   {
/* 58 */     return (float)Math.sqrt(lengthSquared());
/*    */   }
/*    */ 
/*    */   public abstract float lengthSquared();
/*    */ 
/*    */   public abstract Vector load(FloatBuffer paramFloatBuffer);
/*    */ 
/*    */   public abstract Vector negate();
/*    */ 
/*    */   public final Vector normalise()
/*    */   {
/* 86 */     float len = length();
/* 87 */     if (len != 0.0F) {
/* 88 */       float l = 1.0F / len;
/* 89 */       return scale(l);
/*    */     }
/* 91 */     throw new IllegalStateException("Zero length vector");
/*    */   }
/*    */ 
/*    */   public abstract Vector store(FloatBuffer paramFloatBuffer);
/*    */ 
/*    */   public abstract Vector scale(float paramFloat);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector
 * JD-Core Version:    0.6.2
 */