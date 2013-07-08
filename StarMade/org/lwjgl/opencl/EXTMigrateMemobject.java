/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */import org.lwjgl.MemoryUtil;
/*  5:   */import org.lwjgl.PointerBuffer;
/*  6:   */
/* 18:   */public final class EXTMigrateMemobject
/* 19:   */{
/* 20:   */  public static final int CL_MIGRATE_MEM_OBJECT_HOST_EXT = 1;
/* 21:   */  public static final int CL_COMMAND_MIGRATE_MEM_OBJECT_EXT = 16448;
/* 22:   */  
/* 23:   */  public static int clEnqueueMigrateMemObjectEXT(CLCommandQueue command_queue, PointerBuffer mem_objects, long flags, PointerBuffer event_wait_list, PointerBuffer event)
/* 24:   */  {
/* 25:25 */    long function_pointer = CLCapabilities.clEnqueueMigrateMemObjectEXT;
/* 26:26 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 27:27 */    BufferChecks.checkBuffer(mem_objects, 1);
/* 28:28 */    if (event_wait_list != null)
/* 29:29 */      BufferChecks.checkDirect(event_wait_list);
/* 30:30 */    if (event != null)
/* 31:31 */      BufferChecks.checkBuffer(event, 1);
/* 32:32 */    int __result = nclEnqueueMigrateMemObjectEXT(command_queue.getPointer(), mem_objects.remaining(), MemoryUtil.getAddress(mem_objects), flags, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 33:33 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 34:34 */    return __result;
/* 35:   */  }
/* 36:   */  
/* 37:   */  static native int nclEnqueueMigrateMemObjectEXT(long paramLong1, int paramInt1, long paramLong2, long paramLong3, int paramInt2, long paramLong4, long paramLong5, long paramLong6);
/* 38:   */  
/* 39:   */  public static int clEnqueueMigrateMemObjectEXT(CLCommandQueue command_queue, CLMem mem_object, long flags, PointerBuffer event_wait_list, PointerBuffer event) {
/* 40:40 */    long function_pointer = CLCapabilities.clEnqueueMigrateMemObjectEXT;
/* 41:41 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 42:42 */    if (event_wait_list != null)
/* 43:43 */      BufferChecks.checkDirect(event_wait_list);
/* 44:44 */    if (event != null)
/* 45:45 */      BufferChecks.checkBuffer(event, 1);
/* 46:46 */    int __result = nclEnqueueMigrateMemObjectEXT(command_queue.getPointer(), 1, APIUtil.getPointer(mem_object), flags, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 47:47 */    if (__result == 0) command_queue.registerCLEvent(event);
/* 48:48 */    return __result;
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.EXTMigrateMemobject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */