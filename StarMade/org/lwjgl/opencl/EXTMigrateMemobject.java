/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ import org.lwjgl.PointerBuffer;
/*    */ 
/*    */ public final class EXTMigrateMemobject
/*    */ {
/*    */   public static final int CL_MIGRATE_MEM_OBJECT_HOST_EXT = 1;
/*    */   public static final int CL_COMMAND_MIGRATE_MEM_OBJECT_EXT = 16448;
/*    */ 
/*    */   public static int clEnqueueMigrateMemObjectEXT(CLCommandQueue command_queue, PointerBuffer mem_objects, long flags, PointerBuffer event_wait_list, PointerBuffer event)
/*    */   {
/* 25 */     long function_pointer = CLCapabilities.clEnqueueMigrateMemObjectEXT;
/* 26 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 27 */     BufferChecks.checkBuffer(mem_objects, 1);
/* 28 */     if (event_wait_list != null)
/* 29 */       BufferChecks.checkDirect(event_wait_list);
/* 30 */     if (event != null)
/* 31 */       BufferChecks.checkBuffer(event, 1);
/* 32 */     int __result = nclEnqueueMigrateMemObjectEXT(command_queue.getPointer(), mem_objects.remaining(), MemoryUtil.getAddress(mem_objects), flags, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 33 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 34 */     return __result;
/*    */   }
/*    */ 
/*    */   static native int nclEnqueueMigrateMemObjectEXT(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6);
/*    */ 
/*    */   public static int clEnqueueMigrateMemObjectEXT(CLCommandQueue command_queue, CLMem mem_object, long flags, PointerBuffer event_wait_list, PointerBuffer event) {
/* 40 */     long function_pointer = CLCapabilities.clEnqueueMigrateMemObjectEXT;
/* 41 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 42 */     if (event_wait_list != null)
/* 43 */       BufferChecks.checkDirect(event_wait_list);
/* 44 */     if (event != null)
/* 45 */       BufferChecks.checkBuffer(event, 1);
/* 46 */     int __result = nclEnqueueMigrateMemObjectEXT(command_queue.getPointer(), 1, APIUtil.getPointer(mem_object), flags, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 47 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 48 */     return __result;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.EXTMigrateMemobject
 * JD-Core Version:    0.6.2
 */