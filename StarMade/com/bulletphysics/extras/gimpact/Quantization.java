/*    */ package com.bulletphysics.extras.gimpact;
/*    */ 
/*    */ import com.bulletphysics..Stack;
/*    */ import com.bulletphysics.linearmath.VectorUtil;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ class Quantization
/*    */ {
/*    */   public static void bt_calc_quantization_parameters(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, float arg5)
/*    */   {
/* 42 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f clampValue = localStack.get$javax$vecmath$Vector3f();
/* 43 */       clampValue.set(quantizationMargin, quantizationMargin, quantizationMargin);
/* 44 */       outMinBound.sub(srcMinBound, clampValue);
/* 45 */       outMaxBound.add(srcMaxBound, clampValue);
/* 46 */       Vector3f aabbSize = localStack.get$javax$vecmath$Vector3f();
/* 47 */       aabbSize.sub(outMaxBound, outMinBound);
/* 48 */       bvhQuantization.set(65535.0F, 65535.0F, 65535.0F);
/* 49 */       VectorUtil.div(bvhQuantization, bvhQuantization, aabbSize);
/*    */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */   public static void bt_quantize_clamp(short[] arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4) {
/* 53 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
/* 54 */       VectorUtil.setMax(clampedPoint, min_bound);
/* 55 */       VectorUtil.setMin(clampedPoint, max_bound);
/*    */ 
/* 57 */       Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 58 */       v.sub(clampedPoint, min_bound);
/* 59 */       VectorUtil.mul(v, v, bvhQuantization);
/*    */ 
/* 61 */       out[0] = ((short)(int)(v.x + 0.5F));
/* 62 */       out[1] = ((short)(int)(v.y + 0.5F));
/* 63 */       out[2] = ((short)(int)(v.z + 0.5F));
/*    */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */   public static Vector3f bt_unquantize(short[] vecIn, Vector3f offset, Vector3f bvhQuantization, Vector3f out) {
/* 67 */     out.set((vecIn[0] & 0xFFFF) / bvhQuantization.x, (vecIn[1] & 0xFFFF) / bvhQuantization.y, (vecIn[2] & 0xFFFF) / bvhQuantization.z);
/*    */ 
/* 70 */     out.add(offset);
/* 71 */     return out;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.Quantization
 * JD-Core Version:    0.6.2
 */