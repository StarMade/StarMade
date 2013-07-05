/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class AMDPerformanceMonitor
/*     */ {
/*     */   public static final int GL_COUNTER_TYPE_AMD = 35776;
/*     */   public static final int GL_COUNTER_RANGE_AMD = 35777;
/*     */   public static final int GL_UNSIGNED_INT64_AMD = 35778;
/*     */   public static final int GL_PERCENTAGE_AMD = 35779;
/*     */   public static final int GL_PERFMON_RESULT_AVAILABLE_AMD = 35780;
/*     */   public static final int GL_PERFMON_RESULT_SIZE_AMD = 35781;
/*     */   public static final int GL_PERFMON_RESULT_AMD = 35782;
/*     */ 
/*     */   public static void glGetPerfMonitorGroupsAMD(IntBuffer numGroups, IntBuffer groups)
/*     */   {
/*  33 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  34 */     long function_pointer = caps.glGetPerfMonitorGroupsAMD;
/*  35 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  36 */     if (numGroups != null)
/*  37 */       BufferChecks.checkBuffer(numGroups, 1);
/*  38 */     BufferChecks.checkDirect(groups);
/*  39 */     nglGetPerfMonitorGroupsAMD(MemoryUtil.getAddressSafe(numGroups), groups.remaining(), MemoryUtil.getAddress(groups), function_pointer);
/*     */   }
/*     */   static native void nglGetPerfMonitorGroupsAMD(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glGetPerfMonitorCountersAMD(int group, IntBuffer numCounters, IntBuffer maxActiveCounters, IntBuffer counters) {
/*  44 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  45 */     long function_pointer = caps.glGetPerfMonitorCountersAMD;
/*  46 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  47 */     BufferChecks.checkBuffer(numCounters, 1);
/*  48 */     BufferChecks.checkBuffer(maxActiveCounters, 1);
/*  49 */     if (counters != null)
/*  50 */       BufferChecks.checkDirect(counters);
/*  51 */     nglGetPerfMonitorCountersAMD(group, MemoryUtil.getAddress(numCounters), MemoryUtil.getAddress(maxActiveCounters), counters == null ? 0 : counters.remaining(), MemoryUtil.getAddressSafe(counters), function_pointer);
/*     */   }
/*     */   static native void nglGetPerfMonitorCountersAMD(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3, long paramLong4);
/*     */ 
/*     */   public static void glGetPerfMonitorGroupStringAMD(int group, IntBuffer length, ByteBuffer groupString) {
/*  56 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  57 */     long function_pointer = caps.glGetPerfMonitorGroupStringAMD;
/*  58 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  59 */     if (length != null)
/*  60 */       BufferChecks.checkBuffer(length, 1);
/*  61 */     if (groupString != null)
/*  62 */       BufferChecks.checkDirect(groupString);
/*  63 */     nglGetPerfMonitorGroupStringAMD(group, groupString == null ? 0 : groupString.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(groupString), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPerfMonitorGroupStringAMD(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static String glGetPerfMonitorGroupStringAMD(int group, int bufSize) {
/*  69 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  70 */     long function_pointer = caps.glGetPerfMonitorGroupStringAMD;
/*  71 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  72 */     IntBuffer groupString_length = APIUtil.getLengths(caps);
/*  73 */     ByteBuffer groupString = APIUtil.getBufferByte(caps, bufSize);
/*  74 */     nglGetPerfMonitorGroupStringAMD(group, bufSize, MemoryUtil.getAddress0(groupString_length), MemoryUtil.getAddress(groupString), function_pointer);
/*  75 */     groupString.limit(groupString_length.get(0));
/*  76 */     return APIUtil.getString(caps, groupString);
/*     */   }
/*     */ 
/*     */   public static void glGetPerfMonitorCounterStringAMD(int group, int counter, IntBuffer length, ByteBuffer counterString) {
/*  80 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  81 */     long function_pointer = caps.glGetPerfMonitorCounterStringAMD;
/*  82 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  83 */     if (length != null)
/*  84 */       BufferChecks.checkBuffer(length, 1);
/*  85 */     if (counterString != null)
/*  86 */       BufferChecks.checkDirect(counterString);
/*  87 */     nglGetPerfMonitorCounterStringAMD(group, counter, counterString == null ? 0 : counterString.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(counterString), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPerfMonitorCounterStringAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static String glGetPerfMonitorCounterStringAMD(int group, int counter, int bufSize) {
/*  93 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  94 */     long function_pointer = caps.glGetPerfMonitorCounterStringAMD;
/*  95 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  96 */     IntBuffer counterString_length = APIUtil.getLengths(caps);
/*  97 */     ByteBuffer counterString = APIUtil.getBufferByte(caps, bufSize);
/*  98 */     nglGetPerfMonitorCounterStringAMD(group, counter, bufSize, MemoryUtil.getAddress0(counterString_length), MemoryUtil.getAddress(counterString), function_pointer);
/*  99 */     counterString.limit(counterString_length.get(0));
/* 100 */     return APIUtil.getString(caps, counterString);
/*     */   }
/*     */ 
/*     */   public static void glGetPerfMonitorCounterInfoAMD(int group, int counter, int pname, ByteBuffer data) {
/* 104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 105 */     long function_pointer = caps.glGetPerfMonitorCounterInfoAMD;
/* 106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 107 */     BufferChecks.checkBuffer(data, 16);
/* 108 */     nglGetPerfMonitorCounterInfoAMD(group, counter, pname, MemoryUtil.getAddress(data), function_pointer);
/*     */   }
/*     */   static native void nglGetPerfMonitorCounterInfoAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glGenPerfMonitorsAMD(IntBuffer monitors) {
/* 113 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 114 */     long function_pointer = caps.glGenPerfMonitorsAMD;
/* 115 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 116 */     BufferChecks.checkDirect(monitors);
/* 117 */     nglGenPerfMonitorsAMD(monitors.remaining(), MemoryUtil.getAddress(monitors), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenPerfMonitorsAMD(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenPerfMonitorsAMD() {
/* 123 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 124 */     long function_pointer = caps.glGenPerfMonitorsAMD;
/* 125 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 126 */     IntBuffer monitors = APIUtil.getBufferInt(caps);
/* 127 */     nglGenPerfMonitorsAMD(1, MemoryUtil.getAddress(monitors), function_pointer);
/* 128 */     return monitors.get(0);
/*     */   }
/*     */ 
/*     */   public static void glDeletePerfMonitorsAMD(IntBuffer monitors) {
/* 132 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 133 */     long function_pointer = caps.glDeletePerfMonitorsAMD;
/* 134 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 135 */     BufferChecks.checkDirect(monitors);
/* 136 */     nglDeletePerfMonitorsAMD(monitors.remaining(), MemoryUtil.getAddress(monitors), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeletePerfMonitorsAMD(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeletePerfMonitorsAMD(int monitor) {
/* 142 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 143 */     long function_pointer = caps.glDeletePerfMonitorsAMD;
/* 144 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 145 */     nglDeletePerfMonitorsAMD(1, APIUtil.getInt(caps, monitor), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glSelectPerfMonitorCountersAMD(int monitor, boolean enable, int group, IntBuffer counterList) {
/* 149 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 150 */     long function_pointer = caps.glSelectPerfMonitorCountersAMD;
/* 151 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 152 */     BufferChecks.checkDirect(counterList);
/* 153 */     nglSelectPerfMonitorCountersAMD(monitor, enable, group, counterList.remaining(), MemoryUtil.getAddress(counterList), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglSelectPerfMonitorCountersAMD(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glSelectPerfMonitorCountersAMD(int monitor, boolean enable, int group, int counter) {
/* 159 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 160 */     long function_pointer = caps.glSelectPerfMonitorCountersAMD;
/* 161 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 162 */     nglSelectPerfMonitorCountersAMD(monitor, enable, group, 1, APIUtil.getInt(caps, counter), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glBeginPerfMonitorAMD(int monitor) {
/* 166 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 167 */     long function_pointer = caps.glBeginPerfMonitorAMD;
/* 168 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 169 */     nglBeginPerfMonitorAMD(monitor, function_pointer);
/*     */   }
/*     */   static native void nglBeginPerfMonitorAMD(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glEndPerfMonitorAMD(int monitor) {
/* 174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 175 */     long function_pointer = caps.glEndPerfMonitorAMD;
/* 176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 177 */     nglEndPerfMonitorAMD(monitor, function_pointer);
/*     */   }
/*     */   static native void nglEndPerfMonitorAMD(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glGetPerfMonitorCounterDataAMD(int monitor, int pname, IntBuffer data, IntBuffer bytesWritten) {
/* 182 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 183 */     long function_pointer = caps.glGetPerfMonitorCounterDataAMD;
/* 184 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 185 */     BufferChecks.checkDirect(data);
/* 186 */     if (bytesWritten != null)
/* 187 */       BufferChecks.checkBuffer(bytesWritten, 1);
/* 188 */     nglGetPerfMonitorCounterDataAMD(monitor, pname, data.remaining(), MemoryUtil.getAddress(data), MemoryUtil.getAddressSafe(bytesWritten), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetPerfMonitorCounterDataAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static int glGetPerfMonitorCounterDataAMD(int monitor, int pname) {
/* 194 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 195 */     long function_pointer = caps.glGetPerfMonitorCounterDataAMD;
/* 196 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 197 */     IntBuffer data = APIUtil.getBufferInt(caps);
/* 198 */     nglGetPerfMonitorCounterDataAMD(monitor, pname, 4, MemoryUtil.getAddress(data), 0L, function_pointer);
/* 199 */     return data.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDPerformanceMonitor
 * JD-Core Version:    0.6.2
 */