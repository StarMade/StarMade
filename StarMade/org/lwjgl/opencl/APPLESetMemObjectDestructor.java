/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class APPLESetMemObjectDestructor
/*    */ {
/*    */   public static int clSetMemObjectDestructorAPPLE(CLMem memobj, CLMemObjectDestructorCallback pfn_notify)
/*    */   {
/* 13 */     long function_pointer = CLCapabilities.clSetMemObjectDestructorAPPLE;
/* 14 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 15 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 16 */     int __result = 0;
/*    */     try {
/* 18 */       __result = nclSetMemObjectDestructorAPPLE(memobj.getPointer(), pfn_notify.getPointer(), user_data, function_pointer);
/* 19 */       return __result;
/*    */     } finally {
/* 21 */       CallbackUtil.checkCallback(__result, user_data);
/*    */     }
/*    */   }
/*    */ 
/*    */   static native int nclSetMemObjectDestructorAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APPLESetMemObjectDestructor
 * JD-Core Version:    0.6.2
 */