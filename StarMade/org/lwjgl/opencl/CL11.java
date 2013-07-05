/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ public final class CL11
/*     */ {
/*     */   public static final int CL_MISALIGNED_SUB_BUFFER_OFFSET = -13;
/*     */   public static final int CL_EXEC_STATUS_ERROR_FOR_EVENTS_IN_WAIT_LIST = -14;
/*     */   public static final int CL_INVALID_PROPERTY = -64;
/*     */   public static final int CL_VERSION_1_1 = 1;
/*     */   public static final int CL_DEVICE_PREFERRED_VECTOR_WIDTH_HALF = 4148;
/*     */   public static final int CL_DEVICE_HOST_UNIFIED_MEMORY = 4149;
/*     */   public static final int CL_DEVICE_NATIVE_VECTOR_WIDTH_CHAR = 4150;
/*     */   public static final int CL_DEVICE_NATIVE_VECTOR_WIDTH_SHORT = 4151;
/*     */   public static final int CL_DEVICE_NATIVE_VECTOR_WIDTH_INT = 4152;
/*     */   public static final int CL_DEVICE_NATIVE_VECTOR_WIDTH_LONG = 4153;
/*     */   public static final int CL_DEVICE_NATIVE_VECTOR_WIDTH_FLOAT = 4154;
/*     */   public static final int CL_DEVICE_NATIVE_VECTOR_WIDTH_DOUBLE = 4155;
/*     */   public static final int CL_DEVICE_NATIVE_VECTOR_WIDTH_HALF = 4156;
/*     */   public static final int CL_DEVICE_OPENCL_C_VERSION = 4157;
/*     */   public static final int CL_FP_SOFT_FLOAT = 64;
/*     */   public static final int CL_CONTEXT_NUM_DEVICES = 4227;
/*     */   public static final int CL_Rx = 4282;
/*     */   public static final int CL_RGx = 4283;
/*     */   public static final int CL_RGBx = 4284;
/*     */   public static final int CL_MEM_ASSOCIATED_MEMOBJECT = 4359;
/*     */   public static final int CL_MEM_OFFSET = 4360;
/*     */   public static final int CL_ADDRESS_MIRRORED_REPEAT = 4404;
/*     */   public static final int CL_KERNEL_PREFERRED_WORK_GROUP_SIZE_MULTIPLE = 4531;
/*     */   public static final int CL_KERNEL_PRIVATE_MEM_SIZE = 4532;
/*     */   public static final int CL_EVENT_CONTEXT = 4564;
/*     */   public static final int CL_COMMAND_READ_BUFFER_RECT = 4609;
/*     */   public static final int CL_COMMAND_WRITE_BUFFER_RECT = 4610;
/*     */   public static final int CL_COMMAND_COPY_BUFFER_RECT = 4611;
/*     */   public static final int CL_COMMAND_USER = 4612;
/*     */   public static final int CL_BUFFER_CREATE_TYPE_REGION = 4640;
/*     */ 
/*     */   public static CLMem clCreateSubBuffer(CLMem buffer, long flags, int buffer_create_type, ByteBuffer buffer_create_info, IntBuffer errcode_ret)
/*     */   {
/*  94 */     long function_pointer = CLCapabilities.clCreateSubBuffer;
/*  95 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  96 */     BufferChecks.checkBuffer(buffer_create_info, 2 * PointerBuffer.getPointerSize());
/*  97 */     if (errcode_ret != null)
/*  98 */       BufferChecks.checkBuffer(errcode_ret, 1);
/*  99 */     CLMem __result = CLMem.create(nclCreateSubBuffer(buffer.getPointer(), flags, buffer_create_type, MemoryUtil.getAddress(buffer_create_info), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), (CLContext)buffer.getParent());
/* 100 */     return __result;
/*     */   }
/*     */   static native long nclCreateSubBuffer(long paramLong1, long paramLong2, int paramInt, long paramLong3, long paramLong4, long paramLong5);
/*     */ 
/*     */   public static int clSetMemObjectDestructorCallback(CLMem memobj, CLMemObjectDestructorCallback pfn_notify) {
/* 105 */     long function_pointer = CLCapabilities.clSetMemObjectDestructorCallback;
/* 106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 107 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 108 */     int __result = 0;
/*     */     try {
/* 110 */       __result = nclSetMemObjectDestructorCallback(memobj.getPointer(), pfn_notify.getPointer(), user_data, function_pointer);
/* 111 */       return __result;
/*     */     } finally {
/* 113 */       CallbackUtil.checkCallback(__result, user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   static native int nclSetMemObjectDestructorCallback(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*     */ 
/* 119 */   public static int clEnqueueReadBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_read, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) { long function_pointer = CLCapabilities.clEnqueueReadBufferRect;
/* 120 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 121 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 122 */     BufferChecks.checkBuffer(host_offset, 3);
/* 123 */     BufferChecks.checkBuffer(region, 3);
/* 124 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 125 */     if (event_wait_list != null)
/* 126 */       BufferChecks.checkDirect(event_wait_list);
/* 127 */     if (event != null)
/* 128 */       BufferChecks.checkBuffer(event, 1);
/* 129 */     int __result = nclEnqueueReadBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_read, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 130 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 131 */     return __result; }
/*     */ 
/*     */   public static int clEnqueueReadBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_read, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, DoubleBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 134 */     long function_pointer = CLCapabilities.clEnqueueReadBufferRect;
/* 135 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 136 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 137 */     BufferChecks.checkBuffer(host_offset, 3);
/* 138 */     BufferChecks.checkBuffer(region, 3);
/* 139 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 140 */     if (event_wait_list != null)
/* 141 */       BufferChecks.checkDirect(event_wait_list);
/* 142 */     if (event != null)
/* 143 */       BufferChecks.checkBuffer(event, 1);
/* 144 */     int __result = nclEnqueueReadBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_read, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 145 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 146 */     return __result;
/*     */   }
/*     */   public static int clEnqueueReadBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_read, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 149 */     long function_pointer = CLCapabilities.clEnqueueReadBufferRect;
/* 150 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 151 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 152 */     BufferChecks.checkBuffer(host_offset, 3);
/* 153 */     BufferChecks.checkBuffer(region, 3);
/* 154 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 155 */     if (event_wait_list != null)
/* 156 */       BufferChecks.checkDirect(event_wait_list);
/* 157 */     if (event != null)
/* 158 */       BufferChecks.checkBuffer(event, 1);
/* 159 */     int __result = nclEnqueueReadBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_read, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 160 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 161 */     return __result;
/*     */   }
/*     */   public static int clEnqueueReadBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_read, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 164 */     long function_pointer = CLCapabilities.clEnqueueReadBufferRect;
/* 165 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 166 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 167 */     BufferChecks.checkBuffer(host_offset, 3);
/* 168 */     BufferChecks.checkBuffer(region, 3);
/* 169 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 170 */     if (event_wait_list != null)
/* 171 */       BufferChecks.checkDirect(event_wait_list);
/* 172 */     if (event != null)
/* 173 */       BufferChecks.checkBuffer(event, 1);
/* 174 */     int __result = nclEnqueueReadBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_read, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 175 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 176 */     return __result;
/*     */   }
/*     */   public static int clEnqueueReadBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_read, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, LongBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 179 */     long function_pointer = CLCapabilities.clEnqueueReadBufferRect;
/* 180 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 181 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 182 */     BufferChecks.checkBuffer(host_offset, 3);
/* 183 */     BufferChecks.checkBuffer(region, 3);
/* 184 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 185 */     if (event_wait_list != null)
/* 186 */       BufferChecks.checkDirect(event_wait_list);
/* 187 */     if (event != null)
/* 188 */       BufferChecks.checkBuffer(event, 1);
/* 189 */     int __result = nclEnqueueReadBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_read, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 190 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 191 */     return __result;
/*     */   }
/*     */   public static int clEnqueueReadBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_read, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 194 */     long function_pointer = CLCapabilities.clEnqueueReadBufferRect;
/* 195 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 196 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 197 */     BufferChecks.checkBuffer(host_offset, 3);
/* 198 */     BufferChecks.checkBuffer(region, 3);
/* 199 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 200 */     if (event_wait_list != null)
/* 201 */       BufferChecks.checkDirect(event_wait_list);
/* 202 */     if (event != null)
/* 203 */       BufferChecks.checkBuffer(event, 1);
/* 204 */     int __result = nclEnqueueReadBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_read, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 205 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 206 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueReadBufferRect(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9, long paramLong10, int paramInt2, long paramLong11, long paramLong12, long paramLong13);
/*     */ 
/*     */   public static int clEnqueueWriteBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_write, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, ByteBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 211 */     long function_pointer = CLCapabilities.clEnqueueWriteBufferRect;
/* 212 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 213 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 214 */     BufferChecks.checkBuffer(host_offset, 3);
/* 215 */     BufferChecks.checkBuffer(region, 3);
/* 216 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 217 */     if (event_wait_list != null)
/* 218 */       BufferChecks.checkDirect(event_wait_list);
/* 219 */     if (event != null)
/* 220 */       BufferChecks.checkBuffer(event, 1);
/* 221 */     int __result = nclEnqueueWriteBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_write, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 222 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 223 */     return __result;
/*     */   }
/*     */   public static int clEnqueueWriteBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_write, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, DoubleBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 226 */     long function_pointer = CLCapabilities.clEnqueueWriteBufferRect;
/* 227 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 228 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 229 */     BufferChecks.checkBuffer(host_offset, 3);
/* 230 */     BufferChecks.checkBuffer(region, 3);
/* 231 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 232 */     if (event_wait_list != null)
/* 233 */       BufferChecks.checkDirect(event_wait_list);
/* 234 */     if (event != null)
/* 235 */       BufferChecks.checkBuffer(event, 1);
/* 236 */     int __result = nclEnqueueWriteBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_write, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 237 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 238 */     return __result;
/*     */   }
/*     */   public static int clEnqueueWriteBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_write, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, FloatBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 241 */     long function_pointer = CLCapabilities.clEnqueueWriteBufferRect;
/* 242 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 243 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 244 */     BufferChecks.checkBuffer(host_offset, 3);
/* 245 */     BufferChecks.checkBuffer(region, 3);
/* 246 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 247 */     if (event_wait_list != null)
/* 248 */       BufferChecks.checkDirect(event_wait_list);
/* 249 */     if (event != null)
/* 250 */       BufferChecks.checkBuffer(event, 1);
/* 251 */     int __result = nclEnqueueWriteBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_write, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 252 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 253 */     return __result;
/*     */   }
/*     */   public static int clEnqueueWriteBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_write, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, IntBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 256 */     long function_pointer = CLCapabilities.clEnqueueWriteBufferRect;
/* 257 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 258 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 259 */     BufferChecks.checkBuffer(host_offset, 3);
/* 260 */     BufferChecks.checkBuffer(region, 3);
/* 261 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 262 */     if (event_wait_list != null)
/* 263 */       BufferChecks.checkDirect(event_wait_list);
/* 264 */     if (event != null)
/* 265 */       BufferChecks.checkBuffer(event, 1);
/* 266 */     int __result = nclEnqueueWriteBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_write, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 267 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 268 */     return __result;
/*     */   }
/*     */   public static int clEnqueueWriteBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_write, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, LongBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 271 */     long function_pointer = CLCapabilities.clEnqueueWriteBufferRect;
/* 272 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 273 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 274 */     BufferChecks.checkBuffer(host_offset, 3);
/* 275 */     BufferChecks.checkBuffer(region, 3);
/* 276 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 277 */     if (event_wait_list != null)
/* 278 */       BufferChecks.checkDirect(event_wait_list);
/* 279 */     if (event != null)
/* 280 */       BufferChecks.checkBuffer(event, 1);
/* 281 */     int __result = nclEnqueueWriteBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_write, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 282 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 283 */     return __result;
/*     */   }
/*     */   public static int clEnqueueWriteBufferRect(CLCommandQueue command_queue, CLMem buffer, int blocking_write, PointerBuffer buffer_offset, PointerBuffer host_offset, PointerBuffer region, long buffer_row_pitch, long buffer_slice_pitch, long host_row_pitch, long host_slice_pitch, ShortBuffer ptr, PointerBuffer event_wait_list, PointerBuffer event) {
/* 286 */     long function_pointer = CLCapabilities.clEnqueueWriteBufferRect;
/* 287 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 288 */     BufferChecks.checkBuffer(buffer_offset, 3);
/* 289 */     BufferChecks.checkBuffer(host_offset, 3);
/* 290 */     BufferChecks.checkBuffer(region, 3);
/* 291 */     BufferChecks.checkBuffer(ptr, CLChecks.calculateBufferRectSize(host_offset, region, host_row_pitch, host_slice_pitch));
/* 292 */     if (event_wait_list != null)
/* 293 */       BufferChecks.checkDirect(event_wait_list);
/* 294 */     if (event != null)
/* 295 */       BufferChecks.checkBuffer(event, 1);
/* 296 */     int __result = nclEnqueueWriteBufferRect(command_queue.getPointer(), buffer.getPointer(), blocking_write, MemoryUtil.getAddress(buffer_offset), MemoryUtil.getAddress(host_offset), MemoryUtil.getAddress(region), buffer_row_pitch, buffer_slice_pitch, host_row_pitch, host_slice_pitch, MemoryUtil.getAddress(ptr), event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 297 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 298 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueWriteBufferRect(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9, long paramLong10, int paramInt2, long paramLong11, long paramLong12, long paramLong13);
/*     */ 
/*     */   public static int clEnqueueCopyBufferRect(CLCommandQueue command_queue, CLMem src_buffer, CLMem dst_buffer, PointerBuffer src_origin, PointerBuffer dst_origin, PointerBuffer region, long src_row_pitch, long src_slice_pitch, long dst_row_pitch, long dst_slice_pitch, PointerBuffer event_wait_list, PointerBuffer event) {
/* 303 */     long function_pointer = CLCapabilities.clEnqueueCopyBufferRect;
/* 304 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 305 */     BufferChecks.checkBuffer(src_origin, 3);
/* 306 */     BufferChecks.checkBuffer(dst_origin, 3);
/* 307 */     BufferChecks.checkBuffer(region, 3);
/* 308 */     if (event_wait_list != null)
/* 309 */       BufferChecks.checkDirect(event_wait_list);
/* 310 */     if (event != null)
/* 311 */       BufferChecks.checkBuffer(event, 1);
/* 312 */     int __result = nclEnqueueCopyBufferRect(command_queue.getPointer(), src_buffer.getPointer(), dst_buffer.getPointer(), MemoryUtil.getAddress(src_origin), MemoryUtil.getAddress(dst_origin), MemoryUtil.getAddress(region), src_row_pitch, src_slice_pitch, dst_row_pitch, dst_slice_pitch, event_wait_list == null ? 0 : event_wait_list.remaining(), MemoryUtil.getAddressSafe(event_wait_list), MemoryUtil.getAddressSafe(event), function_pointer);
/* 313 */     if (__result == 0) command_queue.registerCLEvent(event);
/* 314 */     return __result;
/*     */   }
/*     */   static native int nclEnqueueCopyBufferRect(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, long paramLong9, long paramLong10, int paramInt, long paramLong11, long paramLong12, long paramLong13);
/*     */ 
/*     */   public static CLEvent clCreateUserEvent(CLContext context, IntBuffer errcode_ret) {
/* 319 */     long function_pointer = CLCapabilities.clCreateUserEvent;
/* 320 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 321 */     if (errcode_ret != null)
/* 322 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 323 */     CLEvent __result = new CLEvent(nclCreateUserEvent(context.getPointer(), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 324 */     return __result;
/*     */   }
/*     */   static native long nclCreateUserEvent(long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static int clSetUserEventStatus(CLEvent event, int execution_status) {
/* 329 */     long function_pointer = CLCapabilities.clSetUserEventStatus;
/* 330 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 331 */     int __result = nclSetUserEventStatus(event.getPointer(), execution_status, function_pointer);
/* 332 */     return __result;
/*     */   }
/*     */   static native int nclSetUserEventStatus(long paramLong1, int paramInt, long paramLong2);
/*     */ 
/*     */   public static int clSetEventCallback(CLEvent event, int command_exec_callback_type, CLEventCallback pfn_notify) {
/* 337 */     long function_pointer = CLCapabilities.clSetEventCallback;
/* 338 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 339 */     long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 340 */     pfn_notify.setRegistry(event.getParentRegistry());
/* 341 */     int __result = 0;
/*     */     try {
/* 343 */       __result = nclSetEventCallback(event.getPointer(), command_exec_callback_type, pfn_notify.getPointer(), user_data, function_pointer);
/* 344 */       return __result;
/*     */     } finally {
/* 346 */       CallbackUtil.checkCallback(__result, user_data);
/*     */     }
/*     */   }
/*     */ 
/*     */   static native int nclSetEventCallback(long paramLong1, int paramInt, long paramLong2, long paramLong3, long paramLong4);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CL11
 * JD-Core Version:    0.6.2
 */