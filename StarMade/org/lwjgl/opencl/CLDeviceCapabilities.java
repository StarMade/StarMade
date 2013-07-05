/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class CLDeviceCapabilities
/*     */ {
/*     */   public final int majorVersion;
/*     */   public final int minorVersion;
/*     */   public final boolean OpenCL11;
/*     */   public final boolean OpenCL12;
/*     */   public final boolean CL_AMD_device_attribute_query;
/*     */   public final boolean CL_AMD_device_memory_flags;
/*     */   public final boolean CL_AMD_fp64;
/*     */   public final boolean CL_AMD_media_ops;
/*     */   public final boolean CL_AMD_media_ops2;
/*     */   public final boolean CL_AMD_offline_devices;
/*     */   public final boolean CL_AMD_popcnt;
/*     */   public final boolean CL_AMD_printf;
/*     */   public final boolean CL_AMD_vec3;
/*     */   final boolean CL_APPLE_ContextLoggingFunctions;
/*     */   public final boolean CL_APPLE_SetMemObjectDestructor;
/*     */   public final boolean CL_APPLE_gl_sharing;
/*     */   public final boolean CL_EXT_atomic_counters_32;
/*     */   public final boolean CL_EXT_atomic_counters_64;
/*     */   public final boolean CL_EXT_device_fission;
/*     */   public final boolean CL_EXT_migrate_memobject;
/*     */   public final boolean CL_INTEL_immediate_execution;
/*     */   public final boolean CL_INTEL_printf;
/*     */   public final boolean CL_INTEL_thread_local_exec;
/*     */   public final boolean CL_KHR_3d_image_writes;
/*     */   public final boolean CL_KHR_byte_addressable_store;
/*     */   public final boolean CL_KHR_depth_images;
/*     */   public final boolean CL_KHR_fp16;
/*     */   public final boolean CL_KHR_fp64;
/*     */   public final boolean CL_KHR_gl_depth_images;
/*     */   public final boolean CL_KHR_gl_event;
/*     */   public final boolean CL_KHR_gl_msaa_sharing;
/*     */   public final boolean CL_KHR_gl_sharing;
/*     */   public final boolean CL_KHR_global_int32_base_atomics;
/*     */   public final boolean CL_KHR_global_int32_extended_atomics;
/*     */   public final boolean CL_KHR_image2d_from_buffer;
/*     */   public final boolean CL_KHR_initialize_memory;
/*     */   public final boolean CL_KHR_int64_base_atomics;
/*     */   public final boolean CL_KHR_int64_extended_atomics;
/*     */   public final boolean CL_KHR_local_int32_base_atomics;
/*     */   public final boolean CL_KHR_local_int32_extended_atomics;
/*     */   public final boolean CL_KHR_select_fprounding_mode;
/*     */   public final boolean CL_KHR_spir;
/*     */   public final boolean CL_KHR_terminate_context;
/*     */   public final boolean CL_NV_compiler_options;
/*     */   public final boolean CL_NV_device_attribute_query;
/*     */   public final boolean CL_NV_pragma_unroll;
/*     */ 
/*     */   public CLDeviceCapabilities(CLDevice device)
/*     */   {
/*  59 */     String extensionList = device.getInfoString(4144);
/*  60 */     String version = device.getInfoString(4143);
/*  61 */     if (!version.startsWith("OpenCL "))
/*  62 */       throw new RuntimeException("Invalid OpenCL version string: " + version);
/*     */     try
/*     */     {
/*  65 */       StringTokenizer tokenizer = new StringTokenizer(version.substring(7), ". ");
/*     */ 
/*  67 */       this.majorVersion = Integer.parseInt(tokenizer.nextToken());
/*  68 */       this.minorVersion = Integer.parseInt(tokenizer.nextToken());
/*     */ 
/*  70 */       this.OpenCL11 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (1 <= this.minorVersion)));
/*  71 */       this.OpenCL12 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (2 <= this.minorVersion)));
/*     */     } catch (RuntimeException e) {
/*  73 */       throw new RuntimeException("The major and/or minor OpenCL version \"" + version + "\" is malformed: " + e.getMessage());
/*     */     }
/*     */ 
/*  76 */     Set extensions = APIUtil.getExtensions(extensionList);
/*  77 */     this.CL_AMD_device_attribute_query = extensions.contains("cl_amd_device_attribute_query");
/*  78 */     this.CL_AMD_device_memory_flags = extensions.contains("cl_amd_device_memory_flags");
/*  79 */     this.CL_AMD_fp64 = extensions.contains("cl_amd_fp64");
/*  80 */     this.CL_AMD_media_ops = extensions.contains("cl_amd_media_ops");
/*  81 */     this.CL_AMD_media_ops2 = extensions.contains("cl_amd_media_ops2");
/*  82 */     this.CL_AMD_offline_devices = extensions.contains("cl_amd_offline_devices");
/*  83 */     this.CL_AMD_popcnt = extensions.contains("cl_amd_popcnt");
/*  84 */     this.CL_AMD_printf = extensions.contains("cl_amd_printf");
/*  85 */     this.CL_AMD_vec3 = extensions.contains("cl_amd_vec3");
/*  86 */     this.CL_APPLE_ContextLoggingFunctions = ((extensions.contains("cl_apple_contextloggingfunctions")) && (CLCapabilities.CL_APPLE_ContextLoggingFunctions));
/*  87 */     this.CL_APPLE_SetMemObjectDestructor = ((extensions.contains("cl_apple_setmemobjectdestructor")) && (CLCapabilities.CL_APPLE_SetMemObjectDestructor));
/*  88 */     this.CL_APPLE_gl_sharing = ((extensions.contains("cl_apple_gl_sharing")) && (CLCapabilities.CL_APPLE_gl_sharing));
/*  89 */     this.CL_EXT_atomic_counters_32 = extensions.contains("cl_ext_atomic_counters_32");
/*  90 */     this.CL_EXT_atomic_counters_64 = extensions.contains("cl_ext_atomic_counters_64");
/*  91 */     this.CL_EXT_device_fission = ((extensions.contains("cl_ext_device_fission")) && (CLCapabilities.CL_EXT_device_fission));
/*  92 */     this.CL_EXT_migrate_memobject = ((extensions.contains("cl_ext_migrate_memobject")) && (CLCapabilities.CL_EXT_migrate_memobject));
/*  93 */     this.CL_INTEL_immediate_execution = extensions.contains("cl_intel_immediate_execution");
/*  94 */     this.CL_INTEL_printf = extensions.contains("cl_intel_printf");
/*  95 */     this.CL_INTEL_thread_local_exec = extensions.contains("cl_intel_thread_local_exec");
/*  96 */     this.CL_KHR_3d_image_writes = extensions.contains("cl_khr_3d_image_writes");
/*  97 */     this.CL_KHR_byte_addressable_store = extensions.contains("cl_khr_byte_addressable_store");
/*  98 */     this.CL_KHR_depth_images = extensions.contains("cl_khr_depth_images");
/*  99 */     this.CL_KHR_fp16 = extensions.contains("cl_khr_fp16");
/* 100 */     this.CL_KHR_fp64 = extensions.contains("cl_khr_fp64");
/* 101 */     this.CL_KHR_gl_depth_images = extensions.contains("cl_khr_gl_depth_images");
/* 102 */     this.CL_KHR_gl_event = ((extensions.contains("cl_khr_gl_event")) && (CLCapabilities.CL_KHR_gl_event));
/* 103 */     this.CL_KHR_gl_msaa_sharing = extensions.contains("cl_khr_gl_msaa_sharing");
/* 104 */     this.CL_KHR_gl_sharing = ((extensions.contains("cl_khr_gl_sharing")) && (CLCapabilities.CL_KHR_gl_sharing));
/* 105 */     this.CL_KHR_global_int32_base_atomics = extensions.contains("cl_khr_global_int32_base_atomics");
/* 106 */     this.CL_KHR_global_int32_extended_atomics = extensions.contains("cl_khr_global_int32_extended_atomics");
/* 107 */     this.CL_KHR_image2d_from_buffer = extensions.contains("cl_khr_image2d_from_buffer");
/* 108 */     this.CL_KHR_initialize_memory = extensions.contains("cl_khr_initialize_memory");
/* 109 */     this.CL_KHR_int64_base_atomics = extensions.contains("cl_khr_int64_base_atomics");
/* 110 */     this.CL_KHR_int64_extended_atomics = extensions.contains("cl_khr_int64_extended_atomics");
/* 111 */     this.CL_KHR_local_int32_base_atomics = extensions.contains("cl_khr_local_int32_base_atomics");
/* 112 */     this.CL_KHR_local_int32_extended_atomics = extensions.contains("cl_khr_local_int32_extended_atomics");
/* 113 */     this.CL_KHR_select_fprounding_mode = extensions.contains("cl_khr_select_fprounding_mode");
/* 114 */     this.CL_KHR_spir = extensions.contains("cl_khr_spir");
/* 115 */     this.CL_KHR_terminate_context = ((extensions.contains("cl_khr_terminate_context")) && (CLCapabilities.CL_KHR_terminate_context));
/* 116 */     this.CL_NV_compiler_options = extensions.contains("cl_nv_compiler_options");
/* 117 */     this.CL_NV_device_attribute_query = extensions.contains("cl_nv_device_attribute_query");
/* 118 */     this.CL_NV_pragma_unroll = extensions.contains("cl_nv_pragma_unroll");
/*     */   }
/*     */ 
/*     */   public int getMajorVersion() {
/* 122 */     return this.majorVersion;
/*     */   }
/*     */ 
/*     */   public int getMinorVersion() {
/* 126 */     return this.minorVersion;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 130 */     StringBuilder buf = new StringBuilder();
/*     */ 
/* 132 */     buf.append("OpenCL ").append(this.majorVersion).append('.').append(this.minorVersion);
/*     */ 
/* 134 */     buf.append(" - Extensions: ");
/* 135 */     if (this.CL_AMD_device_attribute_query) buf.append("cl_amd_device_attribute_query ");
/* 136 */     if (this.CL_AMD_device_memory_flags) buf.append("cl_amd_device_memory_flags ");
/* 137 */     if (this.CL_AMD_fp64) buf.append("cl_amd_fp64 ");
/* 138 */     if (this.CL_AMD_media_ops) buf.append("cl_amd_media_ops ");
/* 139 */     if (this.CL_AMD_media_ops2) buf.append("cl_amd_media_ops2 ");
/* 140 */     if (this.CL_AMD_offline_devices) buf.append("cl_amd_offline_devices ");
/* 141 */     if (this.CL_AMD_popcnt) buf.append("cl_amd_popcnt ");
/* 142 */     if (this.CL_AMD_printf) buf.append("cl_amd_printf ");
/* 143 */     if (this.CL_AMD_vec3) buf.append("cl_amd_vec3 ");
/* 144 */     if (this.CL_APPLE_ContextLoggingFunctions) buf.append("cl_apple_contextloggingfunctions ");
/* 145 */     if (this.CL_APPLE_SetMemObjectDestructor) buf.append("cl_apple_setmemobjectdestructor ");
/* 146 */     if (this.CL_APPLE_gl_sharing) buf.append("cl_apple_gl_sharing ");
/* 147 */     if (this.CL_EXT_atomic_counters_32) buf.append("cl_ext_atomic_counters_32 ");
/* 148 */     if (this.CL_EXT_atomic_counters_64) buf.append("cl_ext_atomic_counters_64 ");
/* 149 */     if (this.CL_EXT_device_fission) buf.append("cl_ext_device_fission ");
/* 150 */     if (this.CL_EXT_migrate_memobject) buf.append("cl_ext_migrate_memobject ");
/* 151 */     if (this.CL_INTEL_immediate_execution) buf.append("cl_intel_immediate_execution ");
/* 152 */     if (this.CL_INTEL_printf) buf.append("cl_intel_printf ");
/* 153 */     if (this.CL_INTEL_thread_local_exec) buf.append("cl_intel_thread_local_exec ");
/* 154 */     if (this.CL_KHR_3d_image_writes) buf.append("cl_khr_3d_image_writes ");
/* 155 */     if (this.CL_KHR_byte_addressable_store) buf.append("cl_khr_byte_addressable_store ");
/* 156 */     if (this.CL_KHR_depth_images) buf.append("cl_khr_depth_images ");
/* 157 */     if (this.CL_KHR_fp16) buf.append("cl_khr_fp16 ");
/* 158 */     if (this.CL_KHR_fp64) buf.append("cl_khr_fp64 ");
/* 159 */     if (this.CL_KHR_gl_depth_images) buf.append("cl_khr_gl_depth_images ");
/* 160 */     if (this.CL_KHR_gl_event) buf.append("cl_khr_gl_event ");
/* 161 */     if (this.CL_KHR_gl_msaa_sharing) buf.append("cl_khr_gl_msaa_sharing ");
/* 162 */     if (this.CL_KHR_gl_sharing) buf.append("cl_khr_gl_sharing ");
/* 163 */     if (this.CL_KHR_global_int32_base_atomics) buf.append("cl_khr_global_int32_base_atomics ");
/* 164 */     if (this.CL_KHR_global_int32_extended_atomics) buf.append("cl_khr_global_int32_extended_atomics ");
/* 165 */     if (this.CL_KHR_image2d_from_buffer) buf.append("cl_khr_image2d_from_buffer ");
/* 166 */     if (this.CL_KHR_initialize_memory) buf.append("cl_khr_initialize_memory ");
/* 167 */     if (this.CL_KHR_int64_base_atomics) buf.append("cl_khr_int64_base_atomics ");
/* 168 */     if (this.CL_KHR_int64_extended_atomics) buf.append("cl_khr_int64_extended_atomics ");
/* 169 */     if (this.CL_KHR_local_int32_base_atomics) buf.append("cl_khr_local_int32_base_atomics ");
/* 170 */     if (this.CL_KHR_local_int32_extended_atomics) buf.append("cl_khr_local_int32_extended_atomics ");
/* 171 */     if (this.CL_KHR_select_fprounding_mode) buf.append("cl_khr_select_fprounding_mode ");
/* 172 */     if (this.CL_KHR_spir) buf.append("cl_khr_spir ");
/* 173 */     if (this.CL_KHR_terminate_context) buf.append("cl_khr_terminate_context ");
/* 174 */     if (this.CL_NV_compiler_options) buf.append("cl_nv_compiler_options ");
/* 175 */     if (this.CL_NV_device_attribute_query) buf.append("cl_nv_device_attribute_query ");
/* 176 */     if (this.CL_NV_pragma_unroll) buf.append("cl_nv_pragma_unroll ");
/*     */ 
/* 178 */     return buf.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLDeviceCapabilities
 * JD-Core Version:    0.6.2
 */