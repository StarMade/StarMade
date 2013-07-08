/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */import org.lwjgl.opencl.CLContext;
/*  5:   */import org.lwjgl.opencl.CLEvent;
/*  6:   */
/* 17:   */public final class ARBCLEvent
/* 18:   */{
/* 19:   */  public static final int GL_SYNC_CL_EVENT_ARB = 33344;
/* 20:   */  public static final int GL_SYNC_CL_EVENT_COMPLETE_ARB = 33345;
/* 21:   */  
/* 22:   */  public static GLSync glCreateSyncFromCLeventARB(CLContext context, CLEvent event, int flags)
/* 23:   */  {
/* 24:24 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 25:25 */    long function_pointer = caps.glCreateSyncFromCLeventARB;
/* 26:26 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 27:27 */    GLSync __result = new GLSync(nglCreateSyncFromCLeventARB(context.getPointer(), event.getPointer(), flags, function_pointer));
/* 28:28 */    return __result;
/* 29:   */  }
/* 30:   */  
/* 31:   */  static native long nglCreateSyncFromCLeventARB(long paramLong1, long paramLong2, int paramInt, long paramLong3);
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBCLEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */