/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  21:    */public final class AMDPerformanceMonitor
/*  22:    */{
/*  23:    */  public static final int GL_COUNTER_TYPE_AMD = 35776;
/*  24:    */  public static final int GL_COUNTER_RANGE_AMD = 35777;
/*  25:    */  public static final int GL_UNSIGNED_INT64_AMD = 35778;
/*  26:    */  public static final int GL_PERCENTAGE_AMD = 35779;
/*  27:    */  public static final int GL_PERFMON_RESULT_AVAILABLE_AMD = 35780;
/*  28:    */  public static final int GL_PERFMON_RESULT_SIZE_AMD = 35781;
/*  29:    */  public static final int GL_PERFMON_RESULT_AMD = 35782;
/*  30:    */  
/*  31:    */  public static void glGetPerfMonitorGroupsAMD(IntBuffer numGroups, IntBuffer groups)
/*  32:    */  {
/*  33: 33 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  34: 34 */    long function_pointer = caps.glGetPerfMonitorGroupsAMD;
/*  35: 35 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  36: 36 */    if (numGroups != null)
/*  37: 37 */      BufferChecks.checkBuffer(numGroups, 1);
/*  38: 38 */    BufferChecks.checkDirect(groups);
/*  39: 39 */    nglGetPerfMonitorGroupsAMD(MemoryUtil.getAddressSafe(numGroups), groups.remaining(), MemoryUtil.getAddress(groups), function_pointer);
/*  40:    */  }
/*  41:    */  
/*  42:    */  static native void nglGetPerfMonitorGroupsAMD(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*  43:    */  
/*  44: 44 */  public static void glGetPerfMonitorCountersAMD(int group, IntBuffer numCounters, IntBuffer maxActiveCounters, IntBuffer counters) { ContextCapabilities caps = GLContext.getCapabilities();
/*  45: 45 */    long function_pointer = caps.glGetPerfMonitorCountersAMD;
/*  46: 46 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  47: 47 */    BufferChecks.checkBuffer(numCounters, 1);
/*  48: 48 */    BufferChecks.checkBuffer(maxActiveCounters, 1);
/*  49: 49 */    if (counters != null)
/*  50: 50 */      BufferChecks.checkDirect(counters);
/*  51: 51 */    nglGetPerfMonitorCountersAMD(group, MemoryUtil.getAddress(numCounters), MemoryUtil.getAddress(maxActiveCounters), counters == null ? 0 : counters.remaining(), MemoryUtil.getAddressSafe(counters), function_pointer);
/*  52:    */  }
/*  53:    */  
/*  54:    */  static native void nglGetPerfMonitorCountersAMD(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3, long paramLong4);
/*  55:    */  
/*  56: 56 */  public static void glGetPerfMonitorGroupStringAMD(int group, IntBuffer length, ByteBuffer groupString) { ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glGetPerfMonitorGroupStringAMD;
/*  58: 58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    if (length != null)
/*  60: 60 */      BufferChecks.checkBuffer(length, 1);
/*  61: 61 */    if (groupString != null)
/*  62: 62 */      BufferChecks.checkDirect(groupString);
/*  63: 63 */    nglGetPerfMonitorGroupStringAMD(group, groupString == null ? 0 : groupString.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(groupString), function_pointer);
/*  64:    */  }
/*  65:    */  
/*  66:    */  static native void nglGetPerfMonitorGroupStringAMD(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  67:    */  
/*  68:    */  public static String glGetPerfMonitorGroupStringAMD(int group, int bufSize) {
/*  69: 69 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  70: 70 */    long function_pointer = caps.glGetPerfMonitorGroupStringAMD;
/*  71: 71 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  72: 72 */    IntBuffer groupString_length = APIUtil.getLengths(caps);
/*  73: 73 */    ByteBuffer groupString = APIUtil.getBufferByte(caps, bufSize);
/*  74: 74 */    nglGetPerfMonitorGroupStringAMD(group, bufSize, MemoryUtil.getAddress0(groupString_length), MemoryUtil.getAddress(groupString), function_pointer);
/*  75: 75 */    groupString.limit(groupString_length.get(0));
/*  76: 76 */    return APIUtil.getString(caps, groupString);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public static void glGetPerfMonitorCounterStringAMD(int group, int counter, IntBuffer length, ByteBuffer counterString) {
/*  80: 80 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  81: 81 */    long function_pointer = caps.glGetPerfMonitorCounterStringAMD;
/*  82: 82 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  83: 83 */    if (length != null)
/*  84: 84 */      BufferChecks.checkBuffer(length, 1);
/*  85: 85 */    if (counterString != null)
/*  86: 86 */      BufferChecks.checkDirect(counterString);
/*  87: 87 */    nglGetPerfMonitorCounterStringAMD(group, counter, counterString == null ? 0 : counterString.remaining(), MemoryUtil.getAddressSafe(length), MemoryUtil.getAddressSafe(counterString), function_pointer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  static native void nglGetPerfMonitorCounterStringAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/*  91:    */  
/*  92:    */  public static String glGetPerfMonitorCounterStringAMD(int group, int counter, int bufSize) {
/*  93: 93 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  94: 94 */    long function_pointer = caps.glGetPerfMonitorCounterStringAMD;
/*  95: 95 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  96: 96 */    IntBuffer counterString_length = APIUtil.getLengths(caps);
/*  97: 97 */    ByteBuffer counterString = APIUtil.getBufferByte(caps, bufSize);
/*  98: 98 */    nglGetPerfMonitorCounterStringAMD(group, counter, bufSize, MemoryUtil.getAddress0(counterString_length), MemoryUtil.getAddress(counterString), function_pointer);
/*  99: 99 */    counterString.limit(counterString_length.get(0));
/* 100:100 */    return APIUtil.getString(caps, counterString);
/* 101:    */  }
/* 102:    */  
/* 103:    */  public static void glGetPerfMonitorCounterInfoAMD(int group, int counter, int pname, ByteBuffer data) {
/* 104:104 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 105:105 */    long function_pointer = caps.glGetPerfMonitorCounterInfoAMD;
/* 106:106 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 107:107 */    BufferChecks.checkBuffer(data, 16);
/* 108:108 */    nglGetPerfMonitorCounterInfoAMD(group, counter, pname, MemoryUtil.getAddress(data), function_pointer);
/* 109:    */  }
/* 110:    */  
/* 111:    */  static native void nglGetPerfMonitorCounterInfoAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 112:    */  
/* 113:113 */  public static void glGenPerfMonitorsAMD(IntBuffer monitors) { ContextCapabilities caps = GLContext.getCapabilities();
/* 114:114 */    long function_pointer = caps.glGenPerfMonitorsAMD;
/* 115:115 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 116:116 */    BufferChecks.checkDirect(monitors);
/* 117:117 */    nglGenPerfMonitorsAMD(monitors.remaining(), MemoryUtil.getAddress(monitors), function_pointer);
/* 118:    */  }
/* 119:    */  
/* 120:    */  static native void nglGenPerfMonitorsAMD(int paramInt, long paramLong1, long paramLong2);
/* 121:    */  
/* 122:    */  public static int glGenPerfMonitorsAMD() {
/* 123:123 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 124:124 */    long function_pointer = caps.glGenPerfMonitorsAMD;
/* 125:125 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 126:126 */    IntBuffer monitors = APIUtil.getBufferInt(caps);
/* 127:127 */    nglGenPerfMonitorsAMD(1, MemoryUtil.getAddress(monitors), function_pointer);
/* 128:128 */    return monitors.get(0);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public static void glDeletePerfMonitorsAMD(IntBuffer monitors) {
/* 132:132 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 133:133 */    long function_pointer = caps.glDeletePerfMonitorsAMD;
/* 134:134 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 135:135 */    BufferChecks.checkDirect(monitors);
/* 136:136 */    nglDeletePerfMonitorsAMD(monitors.remaining(), MemoryUtil.getAddress(monitors), function_pointer);
/* 137:    */  }
/* 138:    */  
/* 139:    */  static native void nglDeletePerfMonitorsAMD(int paramInt, long paramLong1, long paramLong2);
/* 140:    */  
/* 141:    */  public static void glDeletePerfMonitorsAMD(int monitor) {
/* 142:142 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 143:143 */    long function_pointer = caps.glDeletePerfMonitorsAMD;
/* 144:144 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 145:145 */    nglDeletePerfMonitorsAMD(1, APIUtil.getInt(caps, monitor), function_pointer);
/* 146:    */  }
/* 147:    */  
/* 148:    */  public static void glSelectPerfMonitorCountersAMD(int monitor, boolean enable, int group, IntBuffer counterList) {
/* 149:149 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 150:150 */    long function_pointer = caps.glSelectPerfMonitorCountersAMD;
/* 151:151 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 152:152 */    BufferChecks.checkDirect(counterList);
/* 153:153 */    nglSelectPerfMonitorCountersAMD(monitor, enable, group, counterList.remaining(), MemoryUtil.getAddress(counterList), function_pointer);
/* 154:    */  }
/* 155:    */  
/* 156:    */  static native void nglSelectPerfMonitorCountersAMD(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 157:    */  
/* 158:    */  public static void glSelectPerfMonitorCountersAMD(int monitor, boolean enable, int group, int counter) {
/* 159:159 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 160:160 */    long function_pointer = caps.glSelectPerfMonitorCountersAMD;
/* 161:161 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 162:162 */    nglSelectPerfMonitorCountersAMD(monitor, enable, group, 1, APIUtil.getInt(caps, counter), function_pointer);
/* 163:    */  }
/* 164:    */  
/* 165:    */  public static void glBeginPerfMonitorAMD(int monitor) {
/* 166:166 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 167:167 */    long function_pointer = caps.glBeginPerfMonitorAMD;
/* 168:168 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 169:169 */    nglBeginPerfMonitorAMD(monitor, function_pointer);
/* 170:    */  }
/* 171:    */  
/* 172:    */  static native void nglBeginPerfMonitorAMD(int paramInt, long paramLong);
/* 173:    */  
/* 174:174 */  public static void glEndPerfMonitorAMD(int monitor) { ContextCapabilities caps = GLContext.getCapabilities();
/* 175:175 */    long function_pointer = caps.glEndPerfMonitorAMD;
/* 176:176 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 177:177 */    nglEndPerfMonitorAMD(monitor, function_pointer);
/* 178:    */  }
/* 179:    */  
/* 180:    */  static native void nglEndPerfMonitorAMD(int paramInt, long paramLong);
/* 181:    */  
/* 182:182 */  public static void glGetPerfMonitorCounterDataAMD(int monitor, int pname, IntBuffer data, IntBuffer bytesWritten) { ContextCapabilities caps = GLContext.getCapabilities();
/* 183:183 */    long function_pointer = caps.glGetPerfMonitorCounterDataAMD;
/* 184:184 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 185:185 */    BufferChecks.checkDirect(data);
/* 186:186 */    if (bytesWritten != null)
/* 187:187 */      BufferChecks.checkBuffer(bytesWritten, 1);
/* 188:188 */    nglGetPerfMonitorCounterDataAMD(monitor, pname, data.remaining(), MemoryUtil.getAddress(data), MemoryUtil.getAddressSafe(bytesWritten), function_pointer);
/* 189:    */  }
/* 190:    */  
/* 191:    */  static native void nglGetPerfMonitorCounterDataAMD(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3);
/* 192:    */  
/* 193:    */  public static int glGetPerfMonitorCounterDataAMD(int monitor, int pname) {
/* 194:194 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 195:195 */    long function_pointer = caps.glGetPerfMonitorCounterDataAMD;
/* 196:196 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 197:197 */    IntBuffer data = APIUtil.getBufferInt(caps);
/* 198:198 */    nglGetPerfMonitorCounterDataAMD(monitor, pname, 4, MemoryUtil.getAddress(data), 0L, function_pointer);
/* 199:199 */    return data.get(0);
/* 200:    */  }
/* 201:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDPerformanceMonitor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */