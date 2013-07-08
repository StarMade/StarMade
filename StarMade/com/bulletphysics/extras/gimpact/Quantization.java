/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/*  3:   */import com.bulletphysics..Stack;
/*  4:   */import com.bulletphysics.linearmath.VectorUtil;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 38:   */class Quantization
/* 39:   */{
/* 40:   */  public static void bt_calc_quantization_parameters(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, float arg5)
/* 41:   */  {
/* 42:42 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f clampValue = localStack.get$javax$vecmath$Vector3f();
/* 43:43 */      clampValue.set(quantizationMargin, quantizationMargin, quantizationMargin);
/* 44:44 */      outMinBound.sub(srcMinBound, clampValue);
/* 45:45 */      outMaxBound.add(srcMaxBound, clampValue);
/* 46:46 */      Vector3f aabbSize = localStack.get$javax$vecmath$Vector3f();
/* 47:47 */      aabbSize.sub(outMaxBound, outMinBound);
/* 48:48 */      bvhQuantization.set(65535.0F, 65535.0F, 65535.0F);
/* 49:49 */      VectorUtil.div(bvhQuantization, bvhQuantization, aabbSize);
/* 50:50 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 51:   */    } }
/* 52:   */  
/* 53:53 */  public static void bt_quantize_clamp(short[] arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
/* 54:54 */      VectorUtil.setMax(clampedPoint, min_bound);
/* 55:55 */      VectorUtil.setMin(clampedPoint, max_bound);
/* 56:   */      
/* 57:57 */      Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 58:58 */      v.sub(clampedPoint, min_bound);
/* 59:59 */      VectorUtil.mul(v, v, bvhQuantization);
/* 60:   */      
/* 61:61 */      out[0] = ((short)(int)(v.x + 0.5F));
/* 62:62 */      out[1] = ((short)(int)(v.y + 0.5F));
/* 63:63 */      out[2] = ((short)(int)(v.z + 0.5F));
/* 64:64 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 65:   */    } }
/* 66:   */  
/* 67:67 */  public static Vector3f bt_unquantize(short[] vecIn, Vector3f offset, Vector3f bvhQuantization, Vector3f out) { out.set((vecIn[0] & 0xFFFF) / bvhQuantization.x, (vecIn[1] & 0xFFFF) / bvhQuantization.y, (vecIn[2] & 0xFFFF) / bvhQuantization.z);
/* 68:   */    
/* 70:70 */    out.add(offset);
/* 71:71 */    return out;
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.Quantization
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */