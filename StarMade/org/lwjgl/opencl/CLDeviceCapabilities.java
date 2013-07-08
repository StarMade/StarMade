/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.util.Set;
/*   4:    */import java.util.StringTokenizer;
/*   5:    */
/*   8:    */public class CLDeviceCapabilities
/*   9:    */{
/*  10:    */  public final int majorVersion;
/*  11:    */  public final int minorVersion;
/*  12:    */  public final boolean OpenCL11;
/*  13:    */  public final boolean OpenCL12;
/*  14:    */  public final boolean CL_AMD_device_attribute_query;
/*  15:    */  public final boolean CL_AMD_device_memory_flags;
/*  16:    */  public final boolean CL_AMD_fp64;
/*  17:    */  public final boolean CL_AMD_media_ops;
/*  18:    */  public final boolean CL_AMD_media_ops2;
/*  19:    */  public final boolean CL_AMD_offline_devices;
/*  20:    */  public final boolean CL_AMD_popcnt;
/*  21:    */  public final boolean CL_AMD_printf;
/*  22:    */  public final boolean CL_AMD_vec3;
/*  23:    */  final boolean CL_APPLE_ContextLoggingFunctions;
/*  24:    */  public final boolean CL_APPLE_SetMemObjectDestructor;
/*  25:    */  public final boolean CL_APPLE_gl_sharing;
/*  26:    */  public final boolean CL_EXT_atomic_counters_32;
/*  27:    */  public final boolean CL_EXT_atomic_counters_64;
/*  28:    */  public final boolean CL_EXT_device_fission;
/*  29:    */  public final boolean CL_EXT_migrate_memobject;
/*  30:    */  public final boolean CL_INTEL_immediate_execution;
/*  31:    */  public final boolean CL_INTEL_printf;
/*  32:    */  public final boolean CL_INTEL_thread_local_exec;
/*  33:    */  public final boolean CL_KHR_3d_image_writes;
/*  34:    */  public final boolean CL_KHR_byte_addressable_store;
/*  35:    */  public final boolean CL_KHR_depth_images;
/*  36:    */  public final boolean CL_KHR_fp16;
/*  37:    */  public final boolean CL_KHR_fp64;
/*  38:    */  public final boolean CL_KHR_gl_depth_images;
/*  39:    */  public final boolean CL_KHR_gl_event;
/*  40:    */  public final boolean CL_KHR_gl_msaa_sharing;
/*  41:    */  public final boolean CL_KHR_gl_sharing;
/*  42:    */  public final boolean CL_KHR_global_int32_base_atomics;
/*  43:    */  public final boolean CL_KHR_global_int32_extended_atomics;
/*  44:    */  public final boolean CL_KHR_image2d_from_buffer;
/*  45:    */  public final boolean CL_KHR_initialize_memory;
/*  46:    */  public final boolean CL_KHR_int64_base_atomics;
/*  47:    */  public final boolean CL_KHR_int64_extended_atomics;
/*  48:    */  public final boolean CL_KHR_local_int32_base_atomics;
/*  49:    */  public final boolean CL_KHR_local_int32_extended_atomics;
/*  50:    */  public final boolean CL_KHR_select_fprounding_mode;
/*  51:    */  public final boolean CL_KHR_spir;
/*  52:    */  public final boolean CL_KHR_terminate_context;
/*  53:    */  public final boolean CL_NV_compiler_options;
/*  54:    */  public final boolean CL_NV_device_attribute_query;
/*  55:    */  public final boolean CL_NV_pragma_unroll;
/*  56:    */  
/*  57:    */  public CLDeviceCapabilities(CLDevice device)
/*  58:    */  {
/*  59: 59 */    String extensionList = device.getInfoString(4144);
/*  60: 60 */    String version = device.getInfoString(4143);
/*  61: 61 */    if (!version.startsWith("OpenCL ")) {
/*  62: 62 */      throw new RuntimeException("Invalid OpenCL version string: " + version);
/*  63:    */    }
/*  64:    */    try {
/*  65: 65 */      StringTokenizer tokenizer = new StringTokenizer(version.substring(7), ". ");
/*  66:    */      
/*  67: 67 */      this.majorVersion = Integer.parseInt(tokenizer.nextToken());
/*  68: 68 */      this.minorVersion = Integer.parseInt(tokenizer.nextToken());
/*  69:    */      
/*  70: 70 */      this.OpenCL11 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (1 <= this.minorVersion)));
/*  71: 71 */      this.OpenCL12 = ((1 < this.majorVersion) || ((1 == this.majorVersion) && (2 <= this.minorVersion)));
/*  72:    */    } catch (RuntimeException e) {
/*  73: 73 */      throw new RuntimeException("The major and/or minor OpenCL version \"" + version + "\" is malformed: " + e.getMessage());
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    Set<String> extensions = APIUtil.getExtensions(extensionList);
/*  77: 77 */    this.CL_AMD_device_attribute_query = extensions.contains("cl_amd_device_attribute_query");
/*  78: 78 */    this.CL_AMD_device_memory_flags = extensions.contains("cl_amd_device_memory_flags");
/*  79: 79 */    this.CL_AMD_fp64 = extensions.contains("cl_amd_fp64");
/*  80: 80 */    this.CL_AMD_media_ops = extensions.contains("cl_amd_media_ops");
/*  81: 81 */    this.CL_AMD_media_ops2 = extensions.contains("cl_amd_media_ops2");
/*  82: 82 */    this.CL_AMD_offline_devices = extensions.contains("cl_amd_offline_devices");
/*  83: 83 */    this.CL_AMD_popcnt = extensions.contains("cl_amd_popcnt");
/*  84: 84 */    this.CL_AMD_printf = extensions.contains("cl_amd_printf");
/*  85: 85 */    this.CL_AMD_vec3 = extensions.contains("cl_amd_vec3");
/*  86: 86 */    this.CL_APPLE_ContextLoggingFunctions = ((extensions.contains("cl_apple_contextloggingfunctions")) && (CLCapabilities.CL_APPLE_ContextLoggingFunctions));
/*  87: 87 */    this.CL_APPLE_SetMemObjectDestructor = ((extensions.contains("cl_apple_setmemobjectdestructor")) && (CLCapabilities.CL_APPLE_SetMemObjectDestructor));
/*  88: 88 */    this.CL_APPLE_gl_sharing = ((extensions.contains("cl_apple_gl_sharing")) && (CLCapabilities.CL_APPLE_gl_sharing));
/*  89: 89 */    this.CL_EXT_atomic_counters_32 = extensions.contains("cl_ext_atomic_counters_32");
/*  90: 90 */    this.CL_EXT_atomic_counters_64 = extensions.contains("cl_ext_atomic_counters_64");
/*  91: 91 */    this.CL_EXT_device_fission = ((extensions.contains("cl_ext_device_fission")) && (CLCapabilities.CL_EXT_device_fission));
/*  92: 92 */    this.CL_EXT_migrate_memobject = ((extensions.contains("cl_ext_migrate_memobject")) && (CLCapabilities.CL_EXT_migrate_memobject));
/*  93: 93 */    this.CL_INTEL_immediate_execution = extensions.contains("cl_intel_immediate_execution");
/*  94: 94 */    this.CL_INTEL_printf = extensions.contains("cl_intel_printf");
/*  95: 95 */    this.CL_INTEL_thread_local_exec = extensions.contains("cl_intel_thread_local_exec");
/*  96: 96 */    this.CL_KHR_3d_image_writes = extensions.contains("cl_khr_3d_image_writes");
/*  97: 97 */    this.CL_KHR_byte_addressable_store = extensions.contains("cl_khr_byte_addressable_store");
/*  98: 98 */    this.CL_KHR_depth_images = extensions.contains("cl_khr_depth_images");
/*  99: 99 */    this.CL_KHR_fp16 = extensions.contains("cl_khr_fp16");
/* 100:100 */    this.CL_KHR_fp64 = extensions.contains("cl_khr_fp64");
/* 101:101 */    this.CL_KHR_gl_depth_images = extensions.contains("cl_khr_gl_depth_images");
/* 102:102 */    this.CL_KHR_gl_event = ((extensions.contains("cl_khr_gl_event")) && (CLCapabilities.CL_KHR_gl_event));
/* 103:103 */    this.CL_KHR_gl_msaa_sharing = extensions.contains("cl_khr_gl_msaa_sharing");
/* 104:104 */    this.CL_KHR_gl_sharing = ((extensions.contains("cl_khr_gl_sharing")) && (CLCapabilities.CL_KHR_gl_sharing));
/* 105:105 */    this.CL_KHR_global_int32_base_atomics = extensions.contains("cl_khr_global_int32_base_atomics");
/* 106:106 */    this.CL_KHR_global_int32_extended_atomics = extensions.contains("cl_khr_global_int32_extended_atomics");
/* 107:107 */    this.CL_KHR_image2d_from_buffer = extensions.contains("cl_khr_image2d_from_buffer");
/* 108:108 */    this.CL_KHR_initialize_memory = extensions.contains("cl_khr_initialize_memory");
/* 109:109 */    this.CL_KHR_int64_base_atomics = extensions.contains("cl_khr_int64_base_atomics");
/* 110:110 */    this.CL_KHR_int64_extended_atomics = extensions.contains("cl_khr_int64_extended_atomics");
/* 111:111 */    this.CL_KHR_local_int32_base_atomics = extensions.contains("cl_khr_local_int32_base_atomics");
/* 112:112 */    this.CL_KHR_local_int32_extended_atomics = extensions.contains("cl_khr_local_int32_extended_atomics");
/* 113:113 */    this.CL_KHR_select_fprounding_mode = extensions.contains("cl_khr_select_fprounding_mode");
/* 114:114 */    this.CL_KHR_spir = extensions.contains("cl_khr_spir");
/* 115:115 */    this.CL_KHR_terminate_context = ((extensions.contains("cl_khr_terminate_context")) && (CLCapabilities.CL_KHR_terminate_context));
/* 116:116 */    this.CL_NV_compiler_options = extensions.contains("cl_nv_compiler_options");
/* 117:117 */    this.CL_NV_device_attribute_query = extensions.contains("cl_nv_device_attribute_query");
/* 118:118 */    this.CL_NV_pragma_unroll = extensions.contains("cl_nv_pragma_unroll");
/* 119:    */  }
/* 120:    */  
/* 121:    */  public int getMajorVersion() {
/* 122:122 */    return this.majorVersion;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public int getMinorVersion() {
/* 126:126 */    return this.minorVersion;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public String toString() {
/* 130:130 */    StringBuilder buf = new StringBuilder();
/* 131:    */    
/* 132:132 */    buf.append("OpenCL ").append(this.majorVersion).append('.').append(this.minorVersion);
/* 133:    */    
/* 134:134 */    buf.append(" - Extensions: ");
/* 135:135 */    if (this.CL_AMD_device_attribute_query) buf.append("cl_amd_device_attribute_query ");
/* 136:136 */    if (this.CL_AMD_device_memory_flags) buf.append("cl_amd_device_memory_flags ");
/* 137:137 */    if (this.CL_AMD_fp64) buf.append("cl_amd_fp64 ");
/* 138:138 */    if (this.CL_AMD_media_ops) buf.append("cl_amd_media_ops ");
/* 139:139 */    if (this.CL_AMD_media_ops2) buf.append("cl_amd_media_ops2 ");
/* 140:140 */    if (this.CL_AMD_offline_devices) buf.append("cl_amd_offline_devices ");
/* 141:141 */    if (this.CL_AMD_popcnt) buf.append("cl_amd_popcnt ");
/* 142:142 */    if (this.CL_AMD_printf) buf.append("cl_amd_printf ");
/* 143:143 */    if (this.CL_AMD_vec3) buf.append("cl_amd_vec3 ");
/* 144:144 */    if (this.CL_APPLE_ContextLoggingFunctions) buf.append("cl_apple_contextloggingfunctions ");
/* 145:145 */    if (this.CL_APPLE_SetMemObjectDestructor) buf.append("cl_apple_setmemobjectdestructor ");
/* 146:146 */    if (this.CL_APPLE_gl_sharing) buf.append("cl_apple_gl_sharing ");
/* 147:147 */    if (this.CL_EXT_atomic_counters_32) buf.append("cl_ext_atomic_counters_32 ");
/* 148:148 */    if (this.CL_EXT_atomic_counters_64) buf.append("cl_ext_atomic_counters_64 ");
/* 149:149 */    if (this.CL_EXT_device_fission) buf.append("cl_ext_device_fission ");
/* 150:150 */    if (this.CL_EXT_migrate_memobject) buf.append("cl_ext_migrate_memobject ");
/* 151:151 */    if (this.CL_INTEL_immediate_execution) buf.append("cl_intel_immediate_execution ");
/* 152:152 */    if (this.CL_INTEL_printf) buf.append("cl_intel_printf ");
/* 153:153 */    if (this.CL_INTEL_thread_local_exec) buf.append("cl_intel_thread_local_exec ");
/* 154:154 */    if (this.CL_KHR_3d_image_writes) buf.append("cl_khr_3d_image_writes ");
/* 155:155 */    if (this.CL_KHR_byte_addressable_store) buf.append("cl_khr_byte_addressable_store ");
/* 156:156 */    if (this.CL_KHR_depth_images) buf.append("cl_khr_depth_images ");
/* 157:157 */    if (this.CL_KHR_fp16) buf.append("cl_khr_fp16 ");
/* 158:158 */    if (this.CL_KHR_fp64) buf.append("cl_khr_fp64 ");
/* 159:159 */    if (this.CL_KHR_gl_depth_images) buf.append("cl_khr_gl_depth_images ");
/* 160:160 */    if (this.CL_KHR_gl_event) buf.append("cl_khr_gl_event ");
/* 161:161 */    if (this.CL_KHR_gl_msaa_sharing) buf.append("cl_khr_gl_msaa_sharing ");
/* 162:162 */    if (this.CL_KHR_gl_sharing) buf.append("cl_khr_gl_sharing ");
/* 163:163 */    if (this.CL_KHR_global_int32_base_atomics) buf.append("cl_khr_global_int32_base_atomics ");
/* 164:164 */    if (this.CL_KHR_global_int32_extended_atomics) buf.append("cl_khr_global_int32_extended_atomics ");
/* 165:165 */    if (this.CL_KHR_image2d_from_buffer) buf.append("cl_khr_image2d_from_buffer ");
/* 166:166 */    if (this.CL_KHR_initialize_memory) buf.append("cl_khr_initialize_memory ");
/* 167:167 */    if (this.CL_KHR_int64_base_atomics) buf.append("cl_khr_int64_base_atomics ");
/* 168:168 */    if (this.CL_KHR_int64_extended_atomics) buf.append("cl_khr_int64_extended_atomics ");
/* 169:169 */    if (this.CL_KHR_local_int32_base_atomics) buf.append("cl_khr_local_int32_base_atomics ");
/* 170:170 */    if (this.CL_KHR_local_int32_extended_atomics) buf.append("cl_khr_local_int32_extended_atomics ");
/* 171:171 */    if (this.CL_KHR_select_fprounding_mode) buf.append("cl_khr_select_fprounding_mode ");
/* 172:172 */    if (this.CL_KHR_spir) buf.append("cl_khr_spir ");
/* 173:173 */    if (this.CL_KHR_terminate_context) buf.append("cl_khr_terminate_context ");
/* 174:174 */    if (this.CL_NV_compiler_options) buf.append("cl_nv_compiler_options ");
/* 175:175 */    if (this.CL_NV_device_attribute_query) buf.append("cl_nv_device_attribute_query ");
/* 176:176 */    if (this.CL_NV_pragma_unroll) { buf.append("cl_nv_pragma_unroll ");
/* 177:    */    }
/* 178:178 */    return buf.toString();
/* 179:    */  }
/* 180:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLDeviceCapabilities
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */