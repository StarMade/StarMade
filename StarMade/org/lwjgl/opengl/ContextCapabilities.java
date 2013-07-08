/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.util.HashSet;
/*    4:     */import java.util.Set;
/*    5:     */import org.lwjgl.LWJGLException;
/*    6:     */import org.lwjgl.LWJGLUtil;
/*    7:     */
/*    9:     */public class ContextCapabilities
/*   10:     */{
/*   11:     */  static final boolean DEBUG = false;
/*   12:  12 */  final APIUtil util = new APIUtil();
/*   13:  13 */  final StateTracker tracker = new StateTracker();
/*   14:     */  
/*   15:     */  public final boolean GL_AMD_blend_minmax_factor;
/*   16:     */  
/*   17:     */  public final boolean GL_AMD_conservative_depth;
/*   18:     */  
/*   19:     */  public final boolean GL_AMD_debug_output;
/*   20:     */  
/*   21:     */  public final boolean GL_AMD_depth_clamp_separate;
/*   22:     */  
/*   23:     */  public final boolean GL_AMD_draw_buffers_blend;
/*   24:     */  
/*   25:     */  public final boolean GL_AMD_multi_draw_indirect;
/*   26:     */  
/*   27:     */  public final boolean GL_AMD_name_gen_delete;
/*   28:     */  
/*   29:     */  public final boolean GL_AMD_performance_monitor;
/*   30:     */  
/*   31:     */  public final boolean GL_AMD_pinned_memory;
/*   32:     */  
/*   33:     */  public final boolean GL_AMD_query_buffer_object;
/*   34:     */  
/*   35:     */  public final boolean GL_AMD_sample_positions;
/*   36:     */  
/*   37:     */  public final boolean GL_AMD_seamless_cubemap_per_texture;
/*   38:     */  
/*   39:     */  public final boolean GL_AMD_shader_stencil_export;
/*   40:     */  
/*   41:     */  public final boolean GL_AMD_shader_trinary_minmax;
/*   42:     */  
/*   43:     */  public final boolean GL_AMD_sparse_texture;
/*   44:     */  
/*   45:     */  public final boolean GL_AMD_stencil_operation_extended;
/*   46:     */  
/*   47:     */  public final boolean GL_AMD_texture_texture4;
/*   48:     */  
/*   49:     */  public final boolean GL_AMD_transform_feedback3_lines_triangles;
/*   50:     */  
/*   51:     */  public final boolean GL_AMD_vertex_shader_layer;
/*   52:     */  
/*   53:     */  public final boolean GL_AMD_vertex_shader_tessellator;
/*   54:     */  
/*   55:     */  public final boolean GL_AMD_vertex_shader_viewport_index;
/*   56:     */  
/*   57:     */  public final boolean GL_APPLE_aux_depth_stencil;
/*   58:     */  
/*   59:     */  public final boolean GL_APPLE_client_storage;
/*   60:     */  
/*   61:     */  public final boolean GL_APPLE_element_array;
/*   62:     */  
/*   63:     */  public final boolean GL_APPLE_fence;
/*   64:     */  
/*   65:     */  public final boolean GL_APPLE_float_pixels;
/*   66:     */  
/*   67:     */  public final boolean GL_APPLE_flush_buffer_range;
/*   68:     */  
/*   69:     */  public final boolean GL_APPLE_object_purgeable;
/*   70:     */  
/*   71:     */  public final boolean GL_APPLE_packed_pixels;
/*   72:     */  
/*   73:     */  public final boolean GL_APPLE_rgb_422;
/*   74:     */  
/*   75:     */  public final boolean GL_APPLE_row_bytes;
/*   76:     */  
/*   77:     */  public final boolean GL_APPLE_texture_range;
/*   78:     */  
/*   79:     */  public final boolean GL_APPLE_vertex_array_object;
/*   80:     */  
/*   81:     */  public final boolean GL_APPLE_vertex_array_range;
/*   82:     */  
/*   83:     */  public final boolean GL_APPLE_vertex_program_evaluators;
/*   84:     */  
/*   85:     */  public final boolean GL_APPLE_ycbcr_422;
/*   86:     */  
/*   87:     */  public final boolean GL_ARB_ES2_compatibility;
/*   88:     */  
/*   89:     */  public final boolean GL_ARB_ES3_compatibility;
/*   90:     */  
/*   91:     */  public final boolean GL_ARB_arrays_of_arrays;
/*   92:     */  
/*   93:     */  public final boolean GL_ARB_base_instance;
/*   94:     */  
/*   95:     */  public final boolean GL_ARB_blend_func_extended;
/*   96:     */  
/*   97:     */  public final boolean GL_ARB_cl_event;
/*   98:     */  
/*   99:     */  public final boolean GL_ARB_clear_buffer_object;
/*  100:     */  
/*  101:     */  public final boolean GL_ARB_color_buffer_float;
/*  102:     */  
/*  103:     */  public final boolean GL_ARB_compatibility;
/*  104:     */  
/*  105:     */  public final boolean GL_ARB_compressed_texture_pixel_storage;
/*  106:     */  
/*  107:     */  public final boolean GL_ARB_compute_shader;
/*  108:     */  
/*  109:     */  public final boolean GL_ARB_conservative_depth;
/*  110:     */  
/*  111:     */  public final boolean GL_ARB_copy_buffer;
/*  112:     */  
/*  113:     */  public final boolean GL_ARB_copy_image;
/*  114:     */  
/*  115:     */  public final boolean GL_ARB_debug_output;
/*  116:     */  
/*  117:     */  public final boolean GL_ARB_depth_buffer_float;
/*  118:     */  
/*  119:     */  public final boolean GL_ARB_depth_clamp;
/*  120:     */  
/*  121:     */  public final boolean GL_ARB_depth_texture;
/*  122:     */  
/*  123:     */  public final boolean GL_ARB_draw_buffers;
/*  124:     */  
/*  125:     */  public final boolean GL_ARB_draw_buffers_blend;
/*  126:     */  
/*  127:     */  public final boolean GL_ARB_draw_elements_base_vertex;
/*  128:     */  
/*  129:     */  public final boolean GL_ARB_draw_indirect;
/*  130:     */  
/*  131:     */  public final boolean GL_ARB_draw_instanced;
/*  132:     */  
/*  133:     */  public final boolean GL_ARB_explicit_attrib_location;
/*  134:     */  
/*  135:     */  public final boolean GL_ARB_explicit_uniform_location;
/*  136:     */  
/*  137:     */  public final boolean GL_ARB_fragment_coord_conventions;
/*  138:     */  
/*  139:     */  public final boolean GL_ARB_fragment_layer_viewport;
/*  140:     */  
/*  141:     */  public final boolean GL_ARB_fragment_program;
/*  142:     */  
/*  143:     */  public final boolean GL_ARB_fragment_program_shadow;
/*  144:     */  
/*  145:     */  public final boolean GL_ARB_fragment_shader;
/*  146:     */  
/*  147:     */  public final boolean GL_ARB_framebuffer_no_attachments;
/*  148:     */  
/*  149:     */  public final boolean GL_ARB_framebuffer_object;
/*  150:     */  
/*  151:     */  public final boolean GL_ARB_framebuffer_sRGB;
/*  152:     */  
/*  153:     */  public final boolean GL_ARB_geometry_shader4;
/*  154:     */  
/*  155:     */  public final boolean GL_ARB_get_program_binary;
/*  156:     */  
/*  157:     */  public final boolean GL_ARB_gpu_shader5;
/*  158:     */  
/*  159:     */  public final boolean GL_ARB_gpu_shader_fp64;
/*  160:     */  
/*  161:     */  public final boolean GL_ARB_half_float_pixel;
/*  162:     */  
/*  163:     */  public final boolean GL_ARB_half_float_vertex;
/*  164:     */  
/*  165:     */  public final boolean GL_ARB_imaging;
/*  166:     */  
/*  167:     */  public final boolean GL_ARB_instanced_arrays;
/*  168:     */  
/*  169:     */  public final boolean GL_ARB_internalformat_query;
/*  170:     */  
/*  171:     */  public final boolean GL_ARB_internalformat_query2;
/*  172:     */  
/*  173:     */  public final boolean GL_ARB_invalidate_subdata;
/*  174:     */  
/*  175:     */  public final boolean GL_ARB_map_buffer_alignment;
/*  176:     */  
/*  177:     */  public final boolean GL_ARB_map_buffer_range;
/*  178:     */  
/*  179:     */  public final boolean GL_ARB_matrix_palette;
/*  180:     */  
/*  181:     */  public final boolean GL_ARB_multi_draw_indirect;
/*  182:     */  
/*  183:     */  public final boolean GL_ARB_multisample;
/*  184:     */  
/*  185:     */  public final boolean GL_ARB_multitexture;
/*  186:     */  
/*  187:     */  public final boolean GL_ARB_occlusion_query;
/*  188:     */  
/*  189:     */  public final boolean GL_ARB_occlusion_query2;
/*  190:     */  
/*  191:     */  public final boolean GL_ARB_pixel_buffer_object;
/*  192:     */  
/*  193:     */  public final boolean GL_ARB_point_parameters;
/*  194:     */  
/*  195:     */  public final boolean GL_ARB_point_sprite;
/*  196:     */  
/*  197:     */  public final boolean GL_ARB_program_interface_query;
/*  198:     */  
/*  199:     */  public final boolean GL_ARB_provoking_vertex;
/*  200:     */  
/*  201:     */  public final boolean GL_ARB_robust_buffer_access_behavior;
/*  202:     */  
/*  203:     */  public final boolean GL_ARB_robustness;
/*  204:     */  
/*  205:     */  public final boolean GL_ARB_robustness_isolation;
/*  206:     */  
/*  207:     */  public final boolean GL_ARB_sample_shading;
/*  208:     */  
/*  209:     */  public final boolean GL_ARB_sampler_objects;
/*  210:     */  
/*  211:     */  public final boolean GL_ARB_seamless_cube_map;
/*  212:     */  
/*  213:     */  public final boolean GL_ARB_separate_shader_objects;
/*  214:     */  
/*  215:     */  public final boolean GL_ARB_shader_atomic_counters;
/*  216:     */  
/*  217:     */  public final boolean GL_ARB_shader_bit_encoding;
/*  218:     */  
/*  219:     */  public final boolean GL_ARB_shader_image_load_store;
/*  220:     */  
/*  221:     */  public final boolean GL_ARB_shader_image_size;
/*  222:     */  
/*  223:     */  public final boolean GL_ARB_shader_objects;
/*  224:     */  
/*  225:     */  public final boolean GL_ARB_shader_precision;
/*  226:     */  
/*  227:     */  public final boolean GL_ARB_shader_stencil_export;
/*  228:     */  
/*  229:     */  public final boolean GL_ARB_shader_storage_buffer_object;
/*  230:     */  
/*  231:     */  public final boolean GL_ARB_shader_subroutine;
/*  232:     */  
/*  233:     */  public final boolean GL_ARB_shader_texture_lod;
/*  234:     */  
/*  235:     */  public final boolean GL_ARB_shading_language_100;
/*  236:     */  
/*  237:     */  public final boolean GL_ARB_shading_language_420pack;
/*  238:     */  
/*  239:     */  public final boolean GL_ARB_shading_language_include;
/*  240:     */  
/*  241:     */  public final boolean GL_ARB_shading_language_packing;
/*  242:     */  
/*  243:     */  public final boolean GL_ARB_shadow;
/*  244:     */  
/*  245:     */  public final boolean GL_ARB_shadow_ambient;
/*  246:     */  
/*  247:     */  public final boolean GL_ARB_stencil_texturing;
/*  248:     */  
/*  249:     */  public final boolean GL_ARB_sync;
/*  250:     */  
/*  251:     */  public final boolean GL_ARB_tessellation_shader;
/*  252:     */  
/*  253:     */  public final boolean GL_ARB_texture_border_clamp;
/*  254:     */  
/*  255:     */  public final boolean GL_ARB_texture_buffer_object;
/*  256:     */  
/*  257:     */  public final boolean GL_ARB_texture_buffer_object_rgb32;
/*  258:     */  
/*  259:     */  public final boolean GL_ARB_texture_buffer_range;
/*  260:     */  
/*  261:     */  public final boolean GL_ARB_texture_compression;
/*  262:     */  
/*  263:     */  public final boolean GL_ARB_texture_compression_bptc;
/*  264:     */  
/*  265:     */  public final boolean GL_ARB_texture_compression_rgtc;
/*  266:     */  
/*  267:     */  public final boolean GL_ARB_texture_cube_map;
/*  268:     */  
/*  269:     */  public final boolean GL_ARB_texture_cube_map_array;
/*  270:     */  
/*  271:     */  public final boolean GL_ARB_texture_env_add;
/*  272:     */  
/*  273:     */  public final boolean GL_ARB_texture_env_combine;
/*  274:     */  
/*  275:     */  public final boolean GL_ARB_texture_env_crossbar;
/*  276:     */  
/*  277:     */  public final boolean GL_ARB_texture_env_dot3;
/*  278:     */  
/*  279:     */  public final boolean GL_ARB_texture_float;
/*  280:     */  
/*  281:     */  public final boolean GL_ARB_texture_gather;
/*  282:     */  
/*  283:     */  public final boolean GL_ARB_texture_mirrored_repeat;
/*  284:     */  
/*  285:     */  public final boolean GL_ARB_texture_multisample;
/*  286:     */  
/*  287:     */  public final boolean GL_ARB_texture_non_power_of_two;
/*  288:     */  
/*  289:     */  public final boolean GL_ARB_texture_query_levels;
/*  290:     */  
/*  291:     */  public final boolean GL_ARB_texture_query_lod;
/*  292:     */  
/*  293:     */  public final boolean GL_ARB_texture_rectangle;
/*  294:     */  
/*  295:     */  public final boolean GL_ARB_texture_rg;
/*  296:     */  
/*  297:     */  public final boolean GL_ARB_texture_rgb10_a2ui;
/*  298:     */  
/*  299:     */  public final boolean GL_ARB_texture_storage;
/*  300:     */  
/*  301:     */  public final boolean GL_ARB_texture_storage_multisample;
/*  302:     */  
/*  303:     */  public final boolean GL_ARB_texture_swizzle;
/*  304:     */  
/*  305:     */  public final boolean GL_ARB_texture_view;
/*  306:     */  
/*  307:     */  public final boolean GL_ARB_timer_query;
/*  308:     */  public final boolean GL_ARB_transform_feedback2;
/*  309:     */  public final boolean GL_ARB_transform_feedback3;
/*  310:     */  public final boolean GL_ARB_transform_feedback_instanced;
/*  311:     */  public final boolean GL_ARB_transpose_matrix;
/*  312:     */  public final boolean GL_ARB_uniform_buffer_object;
/*  313:     */  public final boolean GL_ARB_vertex_array_bgra;
/*  314:     */  public final boolean GL_ARB_vertex_array_object;
/*  315:     */  public final boolean GL_ARB_vertex_attrib_64bit;
/*  316:     */  public final boolean GL_ARB_vertex_attrib_binding;
/*  317:     */  public final boolean GL_ARB_vertex_blend;
/*  318:     */  public final boolean GL_ARB_vertex_buffer_object;
/*  319:     */  public final boolean GL_ARB_vertex_program;
/*  320:     */  public final boolean GL_ARB_vertex_shader;
/*  321:     */  public final boolean GL_ARB_vertex_type_2_10_10_10_rev;
/*  322:     */  public final boolean GL_ARB_viewport_array;
/*  323:     */  public final boolean GL_ARB_window_pos;
/*  324:     */  public final boolean GL_ATI_draw_buffers;
/*  325:     */  public final boolean GL_ATI_element_array;
/*  326:     */  public final boolean GL_ATI_envmap_bumpmap;
/*  327:     */  public final boolean GL_ATI_fragment_shader;
/*  328:     */  public final boolean GL_ATI_map_object_buffer;
/*  329:     */  public final boolean GL_ATI_meminfo;
/*  330:     */  public final boolean GL_ATI_pn_triangles;
/*  331:     */  public final boolean GL_ATI_separate_stencil;
/*  332:     */  public final boolean GL_ATI_shader_texture_lod;
/*  333:     */  public final boolean GL_ATI_text_fragment_shader;
/*  334:     */  public final boolean GL_ATI_texture_compression_3dc;
/*  335:     */  public final boolean GL_ATI_texture_env_combine3;
/*  336:     */  public final boolean GL_ATI_texture_float;
/*  337:     */  public final boolean GL_ATI_texture_mirror_once;
/*  338:     */  public final boolean GL_ATI_vertex_array_object;
/*  339:     */  public final boolean GL_ATI_vertex_attrib_array_object;
/*  340:     */  public final boolean GL_ATI_vertex_streams;
/*  341:     */  public final boolean GL_EXT_abgr;
/*  342:     */  public final boolean GL_EXT_bgra;
/*  343:     */  public final boolean GL_EXT_bindable_uniform;
/*  344:     */  public final boolean GL_EXT_blend_color;
/*  345:     */  public final boolean GL_EXT_blend_equation_separate;
/*  346:     */  public final boolean GL_EXT_blend_func_separate;
/*  347:     */  public final boolean GL_EXT_blend_minmax;
/*  348:     */  public final boolean GL_EXT_blend_subtract;
/*  349:     */  public final boolean GL_EXT_Cg_shader;
/*  350:     */  public final boolean GL_EXT_compiled_vertex_array;
/*  351:     */  public final boolean GL_EXT_depth_bounds_test;
/*  352:     */  public final boolean GL_EXT_direct_state_access;
/*  353:     */  public final boolean GL_EXT_draw_buffers2;
/*  354:     */  public final boolean GL_EXT_draw_instanced;
/*  355:     */  public final boolean GL_EXT_draw_range_elements;
/*  356:     */  public final boolean GL_EXT_fog_coord;
/*  357:     */  public final boolean GL_EXT_framebuffer_blit;
/*  358:     */  public final boolean GL_EXT_framebuffer_multisample;
/*  359:     */  public final boolean GL_EXT_framebuffer_multisample_blit_scaled;
/*  360:     */  public final boolean GL_EXT_framebuffer_object;
/*  361:     */  public final boolean GL_EXT_framebuffer_sRGB;
/*  362:     */  public final boolean GL_EXT_geometry_shader4;
/*  363:     */  public final boolean GL_EXT_gpu_program_parameters;
/*  364:     */  public final boolean GL_EXT_gpu_shader4;
/*  365:     */  public final boolean GL_EXT_multi_draw_arrays;
/*  366:     */  public final boolean GL_EXT_packed_depth_stencil;
/*  367:     */  public final boolean GL_EXT_packed_float;
/*  368:     */  public final boolean GL_EXT_packed_pixels;
/*  369:     */  public final boolean GL_EXT_paletted_texture;
/*  370:     */  public final boolean GL_EXT_pixel_buffer_object;
/*  371:     */  public final boolean GL_EXT_point_parameters;
/*  372:     */  public final boolean GL_EXT_provoking_vertex;
/*  373:     */  public final boolean GL_EXT_rescale_normal;
/*  374:     */  public final boolean GL_EXT_secondary_color;
/*  375:     */  public final boolean GL_EXT_separate_shader_objects;
/*  376:     */  public final boolean GL_EXT_separate_specular_color;
/*  377:     */  public final boolean GL_EXT_shader_image_load_store;
/*  378:     */  public final boolean GL_EXT_shadow_funcs;
/*  379:     */  public final boolean GL_EXT_shared_texture_palette;
/*  380:     */  public final boolean GL_EXT_stencil_clear_tag;
/*  381:     */  public final boolean GL_EXT_stencil_two_side;
/*  382:     */  public final boolean GL_EXT_stencil_wrap;
/*  383:     */  public final boolean GL_EXT_texture_3d;
/*  384:     */  public final boolean GL_EXT_texture_array;
/*  385:     */  public final boolean GL_EXT_texture_buffer_object;
/*  386:     */  public final boolean GL_EXT_texture_compression_latc;
/*  387:     */  public final boolean GL_EXT_texture_compression_rgtc;
/*  388:     */  public final boolean GL_EXT_texture_compression_s3tc;
/*  389:     */  public final boolean GL_EXT_texture_env_combine;
/*  390:     */  public final boolean GL_EXT_texture_env_dot3;
/*  391:     */  public final boolean GL_EXT_texture_filter_anisotropic;
/*  392:     */  public final boolean GL_EXT_texture_integer;
/*  393:     */  public final boolean GL_EXT_texture_lod_bias;
/*  394:     */  public final boolean GL_EXT_texture_mirror_clamp;
/*  395:     */  public final boolean GL_EXT_texture_rectangle;
/*  396:     */  public final boolean GL_EXT_texture_sRGB;
/*  397:     */  public final boolean GL_EXT_texture_sRGB_decode;
/*  398:     */  public final boolean GL_EXT_texture_shared_exponent;
/*  399:     */  public final boolean GL_EXT_texture_snorm;
/*  400:     */  public final boolean GL_EXT_texture_swizzle;
/*  401:     */  public final boolean GL_EXT_timer_query;
/*  402:     */  public final boolean GL_EXT_transform_feedback;
/*  403:     */  public final boolean GL_EXT_vertex_array_bgra;
/*  404:     */  public final boolean GL_EXT_vertex_attrib_64bit;
/*  405:     */  public final boolean GL_EXT_vertex_shader;
/*  406:     */  public final boolean GL_EXT_vertex_weighting;
/*  407:     */  public final boolean OpenGL11;
/*  408:     */  public final boolean OpenGL12;
/*  409:     */  public final boolean OpenGL13;
/*  410:     */  public final boolean OpenGL14;
/*  411:     */  public final boolean OpenGL15;
/*  412:     */  public final boolean OpenGL20;
/*  413:     */  public final boolean OpenGL21;
/*  414:     */  public final boolean OpenGL30;
/*  415:     */  public final boolean OpenGL31;
/*  416:     */  public final boolean OpenGL32;
/*  417:     */  public final boolean OpenGL33;
/*  418:     */  public final boolean OpenGL40;
/*  419:     */  public final boolean OpenGL41;
/*  420:     */  public final boolean OpenGL42;
/*  421:     */  public final boolean OpenGL43;
/*  422:     */  public final boolean GL_GREMEDY_frame_terminator;
/*  423:     */  public final boolean GL_GREMEDY_string_marker;
/*  424:     */  public final boolean GL_HP_occlusion_test;
/*  425:     */  public final boolean GL_IBM_rasterpos_clip;
/*  426:     */  public final boolean GL_INTEL_map_texture;
/*  427:     */  public final boolean GL_KHR_debug;
/*  428:     */  public final boolean GL_KHR_texture_compression_astc_ldr;
/*  429:     */  public final boolean GL_NVX_gpu_memory_info;
/*  430:     */  public final boolean GL_NV_bindless_texture;
/*  431:     */  public final boolean GL_NV_blend_square;
/*  432:     */  public final boolean GL_NV_compute_program5;
/*  433:     */  public final boolean GL_NV_conditional_render;
/*  434:     */  public final boolean GL_NV_copy_depth_to_color;
/*  435:     */  public final boolean GL_NV_copy_image;
/*  436:     */  public final boolean GL_NV_deep_texture3D;
/*  437:     */  public final boolean GL_NV_depth_buffer_float;
/*  438:     */  public final boolean GL_NV_depth_clamp;
/*  439:     */  public final boolean GL_NV_draw_texture;
/*  440:     */  public final boolean GL_NV_evaluators;
/*  441:     */  public final boolean GL_NV_explicit_multisample;
/*  442:     */  public final boolean GL_NV_fence;
/*  443:     */  public final boolean GL_NV_float_buffer;
/*  444:     */  public final boolean GL_NV_fog_distance;
/*  445:     */  public final boolean GL_NV_fragment_program;
/*  446:     */  public final boolean GL_NV_fragment_program2;
/*  447:     */  public final boolean GL_NV_fragment_program4;
/*  448:     */  public final boolean GL_NV_fragment_program_option;
/*  449:     */  public final boolean GL_NV_framebuffer_multisample_coverage;
/*  450:     */  public final boolean GL_NV_geometry_program4;
/*  451:     */  public final boolean GL_NV_geometry_shader4;
/*  452:     */  public final boolean GL_NV_gpu_program4;
/*  453:     */  public final boolean GL_NV_gpu_program5;
/*  454:     */  public final boolean GL_NV_gpu_shader5;
/*  455:     */  public final boolean GL_NV_half_float;
/*  456:     */  public final boolean GL_NV_light_max_exponent;
/*  457:     */  public final boolean GL_NV_multisample_coverage;
/*  458:     */  public final boolean GL_NV_multisample_filter_hint;
/*  459:     */  public final boolean GL_NV_occlusion_query;
/*  460:     */  public final boolean GL_NV_packed_depth_stencil;
/*  461:     */  public final boolean GL_NV_parameter_buffer_object;
/*  462:     */  public final boolean GL_NV_parameter_buffer_object2;
/*  463:     */  public final boolean GL_NV_path_rendering;
/*  464:     */  public final boolean GL_NV_pixel_data_range;
/*  465:     */  public final boolean GL_NV_point_sprite;
/*  466:     */  public final boolean GL_NV_present_video;
/*  467:     */  public final boolean GL_NV_primitive_restart;
/*  468:     */  public final boolean GL_NV_register_combiners;
/*  469:     */  public final boolean GL_NV_register_combiners2;
/*  470:     */  public final boolean GL_NV_shader_atomic_counters;
/*  471:     */  public final boolean GL_NV_shader_atomic_float;
/*  472:     */  public final boolean GL_NV_shader_buffer_load;
/*  473:     */  public final boolean GL_NV_shader_buffer_store;
/*  474:     */  public final boolean GL_NV_shader_storage_buffer_object;
/*  475:     */  public final boolean GL_NV_tessellation_program5;
/*  476:     */  public final boolean GL_NV_texgen_reflection;
/*  477:     */  public final boolean GL_NV_texture_barrier;
/*  478:     */  public final boolean GL_NV_texture_compression_vtc;
/*  479:     */  public final boolean GL_NV_texture_env_combine4;
/*  480:     */  public final boolean GL_NV_texture_expand_normal;
/*  481:     */  public final boolean GL_NV_texture_multisample;
/*  482:     */  public final boolean GL_NV_texture_rectangle;
/*  483:     */  public final boolean GL_NV_texture_shader;
/*  484:     */  public final boolean GL_NV_texture_shader2;
/*  485:     */  public final boolean GL_NV_texture_shader3;
/*  486:     */  public final boolean GL_NV_transform_feedback;
/*  487:     */  public final boolean GL_NV_transform_feedback2;
/*  488:     */  public final boolean GL_NV_vertex_array_range;
/*  489:     */  public final boolean GL_NV_vertex_array_range2;
/*  490:     */  public final boolean GL_NV_vertex_attrib_integer_64bit;
/*  491:     */  public final boolean GL_NV_vertex_buffer_unified_memory;
/*  492:     */  public final boolean GL_NV_vertex_program;
/*  493:     */  public final boolean GL_NV_vertex_program1_1;
/*  494:     */  public final boolean GL_NV_vertex_program2;
/*  495:     */  public final boolean GL_NV_vertex_program2_option;
/*  496:     */  public final boolean GL_NV_vertex_program3;
/*  497:     */  public final boolean GL_NV_vertex_program4;
/*  498:     */  public final boolean GL_NV_video_capture;
/*  499:     */  public final boolean GL_SGIS_generate_mipmap;
/*  500:     */  public final boolean GL_SGIS_texture_lod;
/*  501:     */  public final boolean GL_SUN_slice_accum;
/*  502:     */  long glDebugMessageEnableAMD;
/*  503:     */  long glDebugMessageInsertAMD;
/*  504:     */  long glDebugMessageCallbackAMD;
/*  505:     */  long glGetDebugMessageLogAMD;
/*  506:     */  long glBlendFuncIndexedAMD;
/*  507:     */  long glBlendFuncSeparateIndexedAMD;
/*  508:     */  long glBlendEquationIndexedAMD;
/*  509:     */  long glBlendEquationSeparateIndexedAMD;
/*  510:     */  long glMultiDrawArraysIndirectAMD;
/*  511:     */  long glMultiDrawElementsIndirectAMD;
/*  512:     */  long glGenNamesAMD;
/*  513:     */  long glDeleteNamesAMD;
/*  514:     */  long glIsNameAMD;
/*  515:     */  long glGetPerfMonitorGroupsAMD;
/*  516:     */  long glGetPerfMonitorCountersAMD;
/*  517:     */  long glGetPerfMonitorGroupStringAMD;
/*  518:     */  long glGetPerfMonitorCounterStringAMD;
/*  519:     */  long glGetPerfMonitorCounterInfoAMD;
/*  520:     */  long glGenPerfMonitorsAMD;
/*  521:     */  long glDeletePerfMonitorsAMD;
/*  522:     */  long glSelectPerfMonitorCountersAMD;
/*  523:     */  long glBeginPerfMonitorAMD;
/*  524:     */  long glEndPerfMonitorAMD;
/*  525:     */  long glGetPerfMonitorCounterDataAMD;
/*  526:     */  long glSetMultisamplefvAMD;
/*  527:     */  long glTexStorageSparseAMD;
/*  528:     */  long glTextureStorageSparseAMD;
/*  529:     */  long glStencilOpValueAMD;
/*  530:     */  long glTessellationFactorAMD;
/*  531:     */  long glTessellationModeAMD;
/*  532:     */  long glElementPointerAPPLE;
/*  533:     */  long glDrawElementArrayAPPLE;
/*  534:     */  long glDrawRangeElementArrayAPPLE;
/*  535:     */  long glMultiDrawElementArrayAPPLE;
/*  536:     */  long glMultiDrawRangeElementArrayAPPLE;
/*  537:     */  long glGenFencesAPPLE;
/*  538:     */  long glDeleteFencesAPPLE;
/*  539:     */  long glSetFenceAPPLE;
/*  540:     */  long glIsFenceAPPLE;
/*  541:     */  long glTestFenceAPPLE;
/*  542:     */  long glFinishFenceAPPLE;
/*  543:     */  long glTestObjectAPPLE;
/*  544:     */  long glFinishObjectAPPLE;
/*  545:     */  long glBufferParameteriAPPLE;
/*  546:     */  long glFlushMappedBufferRangeAPPLE;
/*  547:     */  long glObjectPurgeableAPPLE;
/*  548:     */  long glObjectUnpurgeableAPPLE;
/*  549:     */  long glGetObjectParameterivAPPLE;
/*  550:     */  long glTextureRangeAPPLE;
/*  551:     */  long glGetTexParameterPointervAPPLE;
/*  552:     */  long glBindVertexArrayAPPLE;
/*  553:     */  long glDeleteVertexArraysAPPLE;
/*  554:     */  long glGenVertexArraysAPPLE;
/*  555:     */  long glIsVertexArrayAPPLE;
/*  556:     */  long glVertexArrayRangeAPPLE;
/*  557:     */  long glFlushVertexArrayRangeAPPLE;
/*  558:     */  long glVertexArrayParameteriAPPLE;
/*  559:     */  long glEnableVertexAttribAPPLE;
/*  560:     */  long glDisableVertexAttribAPPLE;
/*  561:     */  long glIsVertexAttribEnabledAPPLE;
/*  562:     */  long glMapVertexAttrib1dAPPLE;
/*  563:     */  long glMapVertexAttrib1fAPPLE;
/*  564:     */  long glMapVertexAttrib2dAPPLE;
/*  565:     */  long glMapVertexAttrib2fAPPLE;
/*  566:     */  long glBindBufferARB;
/*  567:     */  long glDeleteBuffersARB;
/*  568:     */  long glGenBuffersARB;
/*  569:     */  long glIsBufferARB;
/*  570:     */  long glBufferDataARB;
/*  571:     */  long glBufferSubDataARB;
/*  572:     */  long glGetBufferSubDataARB;
/*  573:     */  long glMapBufferARB;
/*  574:     */  long glUnmapBufferARB;
/*  575:     */  long glGetBufferParameterivARB;
/*  576:     */  long glGetBufferPointervARB;
/*  577:     */  long glCreateSyncFromCLeventARB;
/*  578:     */  long glClearNamedBufferDataEXT;
/*  579:     */  long glClearNamedBufferSubDataEXT;
/*  580:     */  long glClampColorARB;
/*  581:     */  long glDebugMessageControlARB;
/*  582:     */  long glDebugMessageInsertARB;
/*  583:     */  long glDebugMessageCallbackARB;
/*  584:     */  long glGetDebugMessageLogARB;
/*  585:     */  long glDrawBuffersARB;
/*  586:     */  long glBlendEquationiARB;
/*  587:     */  long glBlendEquationSeparateiARB;
/*  588:     */  long glBlendFunciARB;
/*  589:     */  long glBlendFuncSeparateiARB;
/*  590:     */  long glDrawArraysInstancedARB;
/*  591:     */  long glDrawElementsInstancedARB;
/*  592:     */  long glNamedFramebufferParameteriEXT;
/*  593:     */  long glGetNamedFramebufferParameterivEXT;
/*  594:     */  long glProgramParameteriARB;
/*  595:     */  long glFramebufferTextureARB;
/*  596:     */  long glFramebufferTextureLayerARB;
/*  597:     */  long glFramebufferTextureFaceARB;
/*  598:     */  long glProgramUniform1dEXT;
/*  599:     */  long glProgramUniform2dEXT;
/*  600:     */  long glProgramUniform3dEXT;
/*  601:     */  long glProgramUniform4dEXT;
/*  602:     */  long glProgramUniform1dvEXT;
/*  603:     */  long glProgramUniform2dvEXT;
/*  604:     */  long glProgramUniform3dvEXT;
/*  605:     */  long glProgramUniform4dvEXT;
/*  606:     */  long glProgramUniformMatrix2dvEXT;
/*  607:     */  long glProgramUniformMatrix3dvEXT;
/*  608:     */  long glProgramUniformMatrix4dvEXT;
/*  609:     */  long glProgramUniformMatrix2x3dvEXT;
/*  610:     */  long glProgramUniformMatrix2x4dvEXT;
/*  611:     */  long glProgramUniformMatrix3x2dvEXT;
/*  612:     */  long glProgramUniformMatrix3x4dvEXT;
/*  613:     */  long glProgramUniformMatrix4x2dvEXT;
/*  614:     */  long glProgramUniformMatrix4x3dvEXT;
/*  615:     */  long glColorTable;
/*  616:     */  long glColorSubTable;
/*  617:     */  long glColorTableParameteriv;
/*  618:     */  long glColorTableParameterfv;
/*  619:     */  long glCopyColorSubTable;
/*  620:     */  long glCopyColorTable;
/*  621:     */  long glGetColorTable;
/*  622:     */  long glGetColorTableParameteriv;
/*  623:     */  long glGetColorTableParameterfv;
/*  624:     */  long glHistogram;
/*  625:     */  long glResetHistogram;
/*  626:     */  long glGetHistogram;
/*  627:     */  long glGetHistogramParameterfv;
/*  628:     */  long glGetHistogramParameteriv;
/*  629:     */  long glMinmax;
/*  630:     */  long glResetMinmax;
/*  631:     */  long glGetMinmax;
/*  632:     */  long glGetMinmaxParameterfv;
/*  633:     */  long glGetMinmaxParameteriv;
/*  634:     */  long glConvolutionFilter1D;
/*  635:     */  long glConvolutionFilter2D;
/*  636:     */  long glConvolutionParameterf;
/*  637:     */  long glConvolutionParameterfv;
/*  638:     */  long glConvolutionParameteri;
/*  639:     */  long glConvolutionParameteriv;
/*  640:     */  long glCopyConvolutionFilter1D;
/*  641:     */  long glCopyConvolutionFilter2D;
/*  642:     */  long glGetConvolutionFilter;
/*  643:     */  long glGetConvolutionParameterfv;
/*  644:     */  long glGetConvolutionParameteriv;
/*  645:     */  long glSeparableFilter2D;
/*  646:     */  long glGetSeparableFilter;
/*  647:     */  long glVertexAttribDivisorARB;
/*  648:     */  long glCurrentPaletteMatrixARB;
/*  649:     */  long glMatrixIndexPointerARB;
/*  650:     */  long glMatrixIndexubvARB;
/*  651:     */  long glMatrixIndexusvARB;
/*  652:     */  long glMatrixIndexuivARB;
/*  653:     */  long glSampleCoverageARB;
/*  654:     */  long glClientActiveTextureARB;
/*  655:     */  long glActiveTextureARB;
/*  656:     */  long glMultiTexCoord1fARB;
/*  657:     */  long glMultiTexCoord1dARB;
/*  658:     */  long glMultiTexCoord1iARB;
/*  659:     */  long glMultiTexCoord1sARB;
/*  660:     */  long glMultiTexCoord2fARB;
/*  661:     */  long glMultiTexCoord2dARB;
/*  662:     */  long glMultiTexCoord2iARB;
/*  663:     */  long glMultiTexCoord2sARB;
/*  664:     */  long glMultiTexCoord3fARB;
/*  665:     */  long glMultiTexCoord3dARB;
/*  666:     */  long glMultiTexCoord3iARB;
/*  667:     */  long glMultiTexCoord3sARB;
/*  668:     */  long glMultiTexCoord4fARB;
/*  669:     */  long glMultiTexCoord4dARB;
/*  670:     */  long glMultiTexCoord4iARB;
/*  671:     */  long glMultiTexCoord4sARB;
/*  672:     */  long glGenQueriesARB;
/*  673:     */  long glDeleteQueriesARB;
/*  674:     */  long glIsQueryARB;
/*  675:     */  long glBeginQueryARB;
/*  676:     */  long glEndQueryARB;
/*  677:     */  long glGetQueryivARB;
/*  678:     */  long glGetQueryObjectivARB;
/*  679:     */  long glGetQueryObjectuivARB;
/*  680:     */  long glPointParameterfARB;
/*  681:     */  long glPointParameterfvARB;
/*  682:     */  long glProgramStringARB;
/*  683:     */  long glBindProgramARB;
/*  684:     */  long glDeleteProgramsARB;
/*  685:     */  long glGenProgramsARB;
/*  686:     */  long glProgramEnvParameter4fARB;
/*  687:     */  long glProgramEnvParameter4dARB;
/*  688:     */  long glProgramEnvParameter4fvARB;
/*  689:     */  long glProgramEnvParameter4dvARB;
/*  690:     */  long glProgramLocalParameter4fARB;
/*  691:     */  long glProgramLocalParameter4dARB;
/*  692:     */  long glProgramLocalParameter4fvARB;
/*  693:     */  long glProgramLocalParameter4dvARB;
/*  694:     */  long glGetProgramEnvParameterfvARB;
/*  695:     */  long glGetProgramEnvParameterdvARB;
/*  696:     */  long glGetProgramLocalParameterfvARB;
/*  697:     */  long glGetProgramLocalParameterdvARB;
/*  698:     */  long glGetProgramivARB;
/*  699:     */  long glGetProgramStringARB;
/*  700:     */  long glIsProgramARB;
/*  701:     */  long glGetGraphicsResetStatusARB;
/*  702:     */  long glGetnMapdvARB;
/*  703:     */  long glGetnMapfvARB;
/*  704:     */  long glGetnMapivARB;
/*  705:     */  long glGetnPixelMapfvARB;
/*  706:     */  long glGetnPixelMapuivARB;
/*  707:     */  long glGetnPixelMapusvARB;
/*  708:     */  long glGetnPolygonStippleARB;
/*  709:     */  long glGetnTexImageARB;
/*  710:     */  long glReadnPixelsARB;
/*  711:     */  long glGetnColorTableARB;
/*  712:     */  long glGetnConvolutionFilterARB;
/*  713:     */  long glGetnSeparableFilterARB;
/*  714:     */  long glGetnHistogramARB;
/*  715:     */  long glGetnMinmaxARB;
/*  716:     */  long glGetnCompressedTexImageARB;
/*  717:     */  long glGetnUniformfvARB;
/*  718:     */  long glGetnUniformivARB;
/*  719:     */  long glGetnUniformuivARB;
/*  720:     */  long glGetnUniformdvARB;
/*  721:     */  long glMinSampleShadingARB;
/*  722:     */  long glDeleteObjectARB;
/*  723:     */  long glGetHandleARB;
/*  724:     */  long glDetachObjectARB;
/*  725:     */  long glCreateShaderObjectARB;
/*  726:     */  long glShaderSourceARB;
/*  727:     */  long glCompileShaderARB;
/*  728:     */  long glCreateProgramObjectARB;
/*  729:     */  long glAttachObjectARB;
/*  730:     */  long glLinkProgramARB;
/*  731:     */  long glUseProgramObjectARB;
/*  732:     */  long glValidateProgramARB;
/*  733:     */  long glUniform1fARB;
/*  734:     */  long glUniform2fARB;
/*  735:     */  long glUniform3fARB;
/*  736:     */  long glUniform4fARB;
/*  737:     */  long glUniform1iARB;
/*  738:     */  long glUniform2iARB;
/*  739:     */  long glUniform3iARB;
/*  740:     */  long glUniform4iARB;
/*  741:     */  long glUniform1fvARB;
/*  742:     */  long glUniform2fvARB;
/*  743:     */  long glUniform3fvARB;
/*  744:     */  long glUniform4fvARB;
/*  745:     */  long glUniform1ivARB;
/*  746:     */  long glUniform2ivARB;
/*  747:     */  long glUniform3ivARB;
/*  748:     */  long glUniform4ivARB;
/*  749:     */  long glUniformMatrix2fvARB;
/*  750:     */  long glUniformMatrix3fvARB;
/*  751:     */  long glUniformMatrix4fvARB;
/*  752:     */  long glGetObjectParameterfvARB;
/*  753:     */  long glGetObjectParameterivARB;
/*  754:     */  long glGetInfoLogARB;
/*  755:     */  long glGetAttachedObjectsARB;
/*  756:     */  long glGetUniformLocationARB;
/*  757:     */  long glGetActiveUniformARB;
/*  758:     */  long glGetUniformfvARB;
/*  759:     */  long glGetUniformivARB;
/*  760:     */  long glGetShaderSourceARB;
/*  761:     */  long glNamedStringARB;
/*  762:     */  long glDeleteNamedStringARB;
/*  763:     */  long glCompileShaderIncludeARB;
/*  764:     */  long glIsNamedStringARB;
/*  765:     */  long glGetNamedStringARB;
/*  766:     */  long glGetNamedStringivARB;
/*  767:     */  long glTexBufferARB;
/*  768:     */  long glTextureBufferRangeEXT;
/*  769:     */  long glCompressedTexImage1DARB;
/*  770:     */  long glCompressedTexImage2DARB;
/*  771:     */  long glCompressedTexImage3DARB;
/*  772:     */  long glCompressedTexSubImage1DARB;
/*  773:     */  long glCompressedTexSubImage2DARB;
/*  774:     */  long glCompressedTexSubImage3DARB;
/*  775:     */  long glGetCompressedTexImageARB;
/*  776:     */  long glTextureStorage1DEXT;
/*  777:     */  long glTextureStorage2DEXT;
/*  778:     */  long glTextureStorage3DEXT;
/*  779:     */  long glTextureStorage2DMultisampleEXT;
/*  780:     */  long glTextureStorage3DMultisampleEXT;
/*  781:     */  long glLoadTransposeMatrixfARB;
/*  782:     */  long glMultTransposeMatrixfARB;
/*  783:     */  long glVertexArrayVertexAttribLOffsetEXT;
/*  784:     */  long glWeightbvARB;
/*  785:     */  long glWeightsvARB;
/*  786:     */  long glWeightivARB;
/*  787:     */  long glWeightfvARB;
/*  788:     */  long glWeightdvARB;
/*  789:     */  long glWeightubvARB;
/*  790:     */  long glWeightusvARB;
/*  791:     */  long glWeightuivARB;
/*  792:     */  long glWeightPointerARB;
/*  793:     */  long glVertexBlendARB;
/*  794:     */  long glVertexAttrib1sARB;
/*  795:     */  long glVertexAttrib1fARB;
/*  796:     */  long glVertexAttrib1dARB;
/*  797:     */  long glVertexAttrib2sARB;
/*  798:     */  long glVertexAttrib2fARB;
/*  799:     */  long glVertexAttrib2dARB;
/*  800:     */  long glVertexAttrib3sARB;
/*  801:     */  long glVertexAttrib3fARB;
/*  802:     */  long glVertexAttrib3dARB;
/*  803:     */  long glVertexAttrib4sARB;
/*  804:     */  long glVertexAttrib4fARB;
/*  805:     */  long glVertexAttrib4dARB;
/*  806:     */  long glVertexAttrib4NubARB;
/*  807:     */  long glVertexAttribPointerARB;
/*  808:     */  long glEnableVertexAttribArrayARB;
/*  809:     */  long glDisableVertexAttribArrayARB;
/*  810:     */  long glBindAttribLocationARB;
/*  811:     */  long glGetActiveAttribARB;
/*  812:     */  long glGetAttribLocationARB;
/*  813:     */  long glGetVertexAttribfvARB;
/*  814:     */  long glGetVertexAttribdvARB;
/*  815:     */  long glGetVertexAttribivARB;
/*  816:     */  long glGetVertexAttribPointervARB;
/*  817:     */  long glWindowPos2fARB;
/*  818:     */  long glWindowPos2dARB;
/*  819:     */  long glWindowPos2iARB;
/*  820:     */  long glWindowPos2sARB;
/*  821:     */  long glWindowPos3fARB;
/*  822:     */  long glWindowPos3dARB;
/*  823:     */  long glWindowPos3iARB;
/*  824:     */  long glWindowPos3sARB;
/*  825:     */  long glDrawBuffersATI;
/*  826:     */  long glElementPointerATI;
/*  827:     */  long glDrawElementArrayATI;
/*  828:     */  long glDrawRangeElementArrayATI;
/*  829:     */  long glTexBumpParameterfvATI;
/*  830:     */  long glTexBumpParameterivATI;
/*  831:     */  long glGetTexBumpParameterfvATI;
/*  832:     */  long glGetTexBumpParameterivATI;
/*  833:     */  long glGenFragmentShadersATI;
/*  834:     */  long glBindFragmentShaderATI;
/*  835:     */  long glDeleteFragmentShaderATI;
/*  836:     */  long glBeginFragmentShaderATI;
/*  837:     */  long glEndFragmentShaderATI;
/*  838:     */  long glPassTexCoordATI;
/*  839:     */  long glSampleMapATI;
/*  840:     */  long glColorFragmentOp1ATI;
/*  841:     */  long glColorFragmentOp2ATI;
/*  842:     */  long glColorFragmentOp3ATI;
/*  843:     */  long glAlphaFragmentOp1ATI;
/*  844:     */  long glAlphaFragmentOp2ATI;
/*  845:     */  long glAlphaFragmentOp3ATI;
/*  846:     */  long glSetFragmentShaderConstantATI;
/*  847:     */  long glMapObjectBufferATI;
/*  848:     */  long glUnmapObjectBufferATI;
/*  849:     */  long glPNTrianglesfATI;
/*  850:     */  long glPNTrianglesiATI;
/*  851:     */  long glStencilOpSeparateATI;
/*  852:     */  long glStencilFuncSeparateATI;
/*  853:     */  long glNewObjectBufferATI;
/*  854:     */  long glIsObjectBufferATI;
/*  855:     */  long glUpdateObjectBufferATI;
/*  856:     */  long glGetObjectBufferfvATI;
/*  857:     */  long glGetObjectBufferivATI;
/*  858:     */  long glFreeObjectBufferATI;
/*  859:     */  long glArrayObjectATI;
/*  860:     */  long glGetArrayObjectfvATI;
/*  861:     */  long glGetArrayObjectivATI;
/*  862:     */  long glVariantArrayObjectATI;
/*  863:     */  long glGetVariantArrayObjectfvATI;
/*  864:     */  long glGetVariantArrayObjectivATI;
/*  865:     */  long glVertexAttribArrayObjectATI;
/*  866:     */  long glGetVertexAttribArrayObjectfvATI;
/*  867:     */  long glGetVertexAttribArrayObjectivATI;
/*  868:     */  long glVertexStream2fATI;
/*  869:     */  long glVertexStream2dATI;
/*  870:     */  long glVertexStream2iATI;
/*  871:     */  long glVertexStream2sATI;
/*  872:     */  long glVertexStream3fATI;
/*  873:     */  long glVertexStream3dATI;
/*  874:     */  long glVertexStream3iATI;
/*  875:     */  long glVertexStream3sATI;
/*  876:     */  long glVertexStream4fATI;
/*  877:     */  long glVertexStream4dATI;
/*  878:     */  long glVertexStream4iATI;
/*  879:     */  long glVertexStream4sATI;
/*  880:     */  long glNormalStream3bATI;
/*  881:     */  long glNormalStream3fATI;
/*  882:     */  long glNormalStream3dATI;
/*  883:     */  long glNormalStream3iATI;
/*  884:     */  long glNormalStream3sATI;
/*  885:     */  long glClientActiveVertexStreamATI;
/*  886:     */  long glVertexBlendEnvfATI;
/*  887:     */  long glVertexBlendEnviATI;
/*  888:     */  long glUniformBufferEXT;
/*  889:     */  long glGetUniformBufferSizeEXT;
/*  890:     */  long glGetUniformOffsetEXT;
/*  891:     */  long glBlendColorEXT;
/*  892:     */  long glBlendEquationSeparateEXT;
/*  893:     */  long glBlendFuncSeparateEXT;
/*  894:     */  long glBlendEquationEXT;
/*  895:     */  long glLockArraysEXT;
/*  896:     */  long glUnlockArraysEXT;
/*  897:     */  long glDepthBoundsEXT;
/*  898:     */  long glClientAttribDefaultEXT;
/*  899:     */  long glPushClientAttribDefaultEXT;
/*  900:     */  long glMatrixLoadfEXT;
/*  901:     */  long glMatrixLoaddEXT;
/*  902:     */  long glMatrixMultfEXT;
/*  903:     */  long glMatrixMultdEXT;
/*  904:     */  long glMatrixLoadIdentityEXT;
/*  905:     */  long glMatrixRotatefEXT;
/*  906:     */  long glMatrixRotatedEXT;
/*  907:     */  long glMatrixScalefEXT;
/*  908:     */  long glMatrixScaledEXT;
/*  909:     */  long glMatrixTranslatefEXT;
/*  910:     */  long glMatrixTranslatedEXT;
/*  911:     */  long glMatrixOrthoEXT;
/*  912:     */  long glMatrixFrustumEXT;
/*  913:     */  long glMatrixPushEXT;
/*  914:     */  long glMatrixPopEXT;
/*  915:     */  long glTextureParameteriEXT;
/*  916:     */  long glTextureParameterivEXT;
/*  917:     */  long glTextureParameterfEXT;
/*  918:     */  long glTextureParameterfvEXT;
/*  919:     */  long glTextureImage1DEXT;
/*  920:     */  long glTextureImage2DEXT;
/*  921:     */  long glTextureSubImage1DEXT;
/*  922:     */  long glTextureSubImage2DEXT;
/*  923:     */  long glCopyTextureImage1DEXT;
/*  924:     */  long glCopyTextureImage2DEXT;
/*  925:     */  long glCopyTextureSubImage1DEXT;
/*  926:     */  long glCopyTextureSubImage2DEXT;
/*  927:     */  long glGetTextureImageEXT;
/*  928:     */  long glGetTextureParameterfvEXT;
/*  929:     */  long glGetTextureParameterivEXT;
/*  930:     */  long glGetTextureLevelParameterfvEXT;
/*  931:     */  long glGetTextureLevelParameterivEXT;
/*  932:     */  long glTextureImage3DEXT;
/*  933:     */  long glTextureSubImage3DEXT;
/*  934:     */  long glCopyTextureSubImage3DEXT;
/*  935:     */  long glBindMultiTextureEXT;
/*  936:     */  long glMultiTexCoordPointerEXT;
/*  937:     */  long glMultiTexEnvfEXT;
/*  938:     */  long glMultiTexEnvfvEXT;
/*  939:     */  long glMultiTexEnviEXT;
/*  940:     */  long glMultiTexEnvivEXT;
/*  941:     */  long glMultiTexGendEXT;
/*  942:     */  long glMultiTexGendvEXT;
/*  943:     */  long glMultiTexGenfEXT;
/*  944:     */  long glMultiTexGenfvEXT;
/*  945:     */  long glMultiTexGeniEXT;
/*  946:     */  long glMultiTexGenivEXT;
/*  947:     */  long glGetMultiTexEnvfvEXT;
/*  948:     */  long glGetMultiTexEnvivEXT;
/*  949:     */  long glGetMultiTexGendvEXT;
/*  950:     */  long glGetMultiTexGenfvEXT;
/*  951:     */  long glGetMultiTexGenivEXT;
/*  952:     */  long glMultiTexParameteriEXT;
/*  953:     */  long glMultiTexParameterivEXT;
/*  954:     */  long glMultiTexParameterfEXT;
/*  955:     */  long glMultiTexParameterfvEXT;
/*  956:     */  long glMultiTexImage1DEXT;
/*  957:     */  long glMultiTexImage2DEXT;
/*  958:     */  long glMultiTexSubImage1DEXT;
/*  959:     */  long glMultiTexSubImage2DEXT;
/*  960:     */  long glCopyMultiTexImage1DEXT;
/*  961:     */  long glCopyMultiTexImage2DEXT;
/*  962:     */  long glCopyMultiTexSubImage1DEXT;
/*  963:     */  long glCopyMultiTexSubImage2DEXT;
/*  964:     */  long glGetMultiTexImageEXT;
/*  965:     */  long glGetMultiTexParameterfvEXT;
/*  966:     */  long glGetMultiTexParameterivEXT;
/*  967:     */  long glGetMultiTexLevelParameterfvEXT;
/*  968:     */  long glGetMultiTexLevelParameterivEXT;
/*  969:     */  long glMultiTexImage3DEXT;
/*  970:     */  long glMultiTexSubImage3DEXT;
/*  971:     */  long glCopyMultiTexSubImage3DEXT;
/*  972:     */  long glEnableClientStateIndexedEXT;
/*  973:     */  long glDisableClientStateIndexedEXT;
/*  974:     */  long glEnableClientStateiEXT;
/*  975:     */  long glDisableClientStateiEXT;
/*  976:     */  long glGetFloatIndexedvEXT;
/*  977:     */  long glGetDoubleIndexedvEXT;
/*  978:     */  long glGetPointerIndexedvEXT;
/*  979:     */  long glGetFloati_vEXT;
/*  980:     */  long glGetDoublei_vEXT;
/*  981:     */  long glGetPointeri_vEXT;
/*  982:     */  long glNamedProgramStringEXT;
/*  983:     */  long glNamedProgramLocalParameter4dEXT;
/*  984:     */  long glNamedProgramLocalParameter4dvEXT;
/*  985:     */  long glNamedProgramLocalParameter4fEXT;
/*  986:     */  long glNamedProgramLocalParameter4fvEXT;
/*  987:     */  long glGetNamedProgramLocalParameterdvEXT;
/*  988:     */  long glGetNamedProgramLocalParameterfvEXT;
/*  989:     */  long glGetNamedProgramivEXT;
/*  990:     */  long glGetNamedProgramStringEXT;
/*  991:     */  long glCompressedTextureImage3DEXT;
/*  992:     */  long glCompressedTextureImage2DEXT;
/*  993:     */  long glCompressedTextureImage1DEXT;
/*  994:     */  long glCompressedTextureSubImage3DEXT;
/*  995:     */  long glCompressedTextureSubImage2DEXT;
/*  996:     */  long glCompressedTextureSubImage1DEXT;
/*  997:     */  long glGetCompressedTextureImageEXT;
/*  998:     */  long glCompressedMultiTexImage3DEXT;
/*  999:     */  long glCompressedMultiTexImage2DEXT;
/* 1000:     */  long glCompressedMultiTexImage1DEXT;
/* 1001:     */  long glCompressedMultiTexSubImage3DEXT;
/* 1002:     */  long glCompressedMultiTexSubImage2DEXT;
/* 1003:     */  long glCompressedMultiTexSubImage1DEXT;
/* 1004:     */  long glGetCompressedMultiTexImageEXT;
/* 1005:     */  long glMatrixLoadTransposefEXT;
/* 1006:     */  long glMatrixLoadTransposedEXT;
/* 1007:     */  long glMatrixMultTransposefEXT;
/* 1008:     */  long glMatrixMultTransposedEXT;
/* 1009:     */  long glNamedBufferDataEXT;
/* 1010:     */  long glNamedBufferSubDataEXT;
/* 1011:     */  long glMapNamedBufferEXT;
/* 1012:     */  long glUnmapNamedBufferEXT;
/* 1013:     */  long glGetNamedBufferParameterivEXT;
/* 1014:     */  long glGetNamedBufferPointervEXT;
/* 1015:     */  long glGetNamedBufferSubDataEXT;
/* 1016:     */  long glProgramUniform1fEXT;
/* 1017:     */  long glProgramUniform2fEXT;
/* 1018:     */  long glProgramUniform3fEXT;
/* 1019:     */  long glProgramUniform4fEXT;
/* 1020:     */  long glProgramUniform1iEXT;
/* 1021:     */  long glProgramUniform2iEXT;
/* 1022:     */  long glProgramUniform3iEXT;
/* 1023:     */  long glProgramUniform4iEXT;
/* 1024:     */  long glProgramUniform1fvEXT;
/* 1025:     */  long glProgramUniform2fvEXT;
/* 1026:     */  long glProgramUniform3fvEXT;
/* 1027:     */  long glProgramUniform4fvEXT;
/* 1028:     */  long glProgramUniform1ivEXT;
/* 1029:     */  long glProgramUniform2ivEXT;
/* 1030:     */  long glProgramUniform3ivEXT;
/* 1031:     */  long glProgramUniform4ivEXT;
/* 1032:     */  long glProgramUniformMatrix2fvEXT;
/* 1033:     */  long glProgramUniformMatrix3fvEXT;
/* 1034:     */  long glProgramUniformMatrix4fvEXT;
/* 1035:     */  long glProgramUniformMatrix2x3fvEXT;
/* 1036:     */  long glProgramUniformMatrix3x2fvEXT;
/* 1037:     */  long glProgramUniformMatrix2x4fvEXT;
/* 1038:     */  long glProgramUniformMatrix4x2fvEXT;
/* 1039:     */  long glProgramUniformMatrix3x4fvEXT;
/* 1040:     */  long glProgramUniformMatrix4x3fvEXT;
/* 1041:     */  long glTextureBufferEXT;
/* 1042:     */  long glMultiTexBufferEXT;
/* 1043:     */  long glTextureParameterIivEXT;
/* 1044:     */  long glTextureParameterIuivEXT;
/* 1045:     */  long glGetTextureParameterIivEXT;
/* 1046:     */  long glGetTextureParameterIuivEXT;
/* 1047:     */  long glMultiTexParameterIivEXT;
/* 1048:     */  long glMultiTexParameterIuivEXT;
/* 1049:     */  long glGetMultiTexParameterIivEXT;
/* 1050:     */  long glGetMultiTexParameterIuivEXT;
/* 1051:     */  long glProgramUniform1uiEXT;
/* 1052:     */  long glProgramUniform2uiEXT;
/* 1053:     */  long glProgramUniform3uiEXT;
/* 1054:     */  long glProgramUniform4uiEXT;
/* 1055:     */  long glProgramUniform1uivEXT;
/* 1056:     */  long glProgramUniform2uivEXT;
/* 1057:     */  long glProgramUniform3uivEXT;
/* 1058:     */  long glProgramUniform4uivEXT;
/* 1059:     */  long glNamedProgramLocalParameters4fvEXT;
/* 1060:     */  long glNamedProgramLocalParameterI4iEXT;
/* 1061:     */  long glNamedProgramLocalParameterI4ivEXT;
/* 1062:     */  long glNamedProgramLocalParametersI4ivEXT;
/* 1063:     */  long glNamedProgramLocalParameterI4uiEXT;
/* 1064:     */  long glNamedProgramLocalParameterI4uivEXT;
/* 1065:     */  long glNamedProgramLocalParametersI4uivEXT;
/* 1066:     */  long glGetNamedProgramLocalParameterIivEXT;
/* 1067:     */  long glGetNamedProgramLocalParameterIuivEXT;
/* 1068:     */  long glNamedRenderbufferStorageEXT;
/* 1069:     */  long glGetNamedRenderbufferParameterivEXT;
/* 1070:     */  long glNamedRenderbufferStorageMultisampleEXT;
/* 1071:     */  long glNamedRenderbufferStorageMultisampleCoverageEXT;
/* 1072:     */  long glCheckNamedFramebufferStatusEXT;
/* 1073:     */  long glNamedFramebufferTexture1DEXT;
/* 1074:     */  long glNamedFramebufferTexture2DEXT;
/* 1075:     */  long glNamedFramebufferTexture3DEXT;
/* 1076:     */  long glNamedFramebufferRenderbufferEXT;
/* 1077:     */  long glGetNamedFramebufferAttachmentParameterivEXT;
/* 1078:     */  long glGenerateTextureMipmapEXT;
/* 1079:     */  long glGenerateMultiTexMipmapEXT;
/* 1080:     */  long glFramebufferDrawBufferEXT;
/* 1081:     */  long glFramebufferDrawBuffersEXT;
/* 1082:     */  long glFramebufferReadBufferEXT;
/* 1083:     */  long glGetFramebufferParameterivEXT;
/* 1084:     */  long glNamedCopyBufferSubDataEXT;
/* 1085:     */  long glNamedFramebufferTextureEXT;
/* 1086:     */  long glNamedFramebufferTextureLayerEXT;
/* 1087:     */  long glNamedFramebufferTextureFaceEXT;
/* 1088:     */  long glTextureRenderbufferEXT;
/* 1089:     */  long glMultiTexRenderbufferEXT;
/* 1090:     */  long glVertexArrayVertexOffsetEXT;
/* 1091:     */  long glVertexArrayColorOffsetEXT;
/* 1092:     */  long glVertexArrayEdgeFlagOffsetEXT;
/* 1093:     */  long glVertexArrayIndexOffsetEXT;
/* 1094:     */  long glVertexArrayNormalOffsetEXT;
/* 1095:     */  long glVertexArrayTexCoordOffsetEXT;
/* 1096:     */  long glVertexArrayMultiTexCoordOffsetEXT;
/* 1097:     */  long glVertexArrayFogCoordOffsetEXT;
/* 1098:     */  long glVertexArraySecondaryColorOffsetEXT;
/* 1099:     */  long glVertexArrayVertexAttribOffsetEXT;
/* 1100:     */  long glVertexArrayVertexAttribIOffsetEXT;
/* 1101:     */  long glEnableVertexArrayEXT;
/* 1102:     */  long glDisableVertexArrayEXT;
/* 1103:     */  long glEnableVertexArrayAttribEXT;
/* 1104:     */  long glDisableVertexArrayAttribEXT;
/* 1105:     */  long glGetVertexArrayIntegervEXT;
/* 1106:     */  long glGetVertexArrayPointervEXT;
/* 1107:     */  long glGetVertexArrayIntegeri_vEXT;
/* 1108:     */  long glGetVertexArrayPointeri_vEXT;
/* 1109:     */  long glMapNamedBufferRangeEXT;
/* 1110:     */  long glFlushMappedNamedBufferRangeEXT;
/* 1111:     */  long glColorMaskIndexedEXT;
/* 1112:     */  long glGetBooleanIndexedvEXT;
/* 1113:     */  long glGetIntegerIndexedvEXT;
/* 1114:     */  long glEnableIndexedEXT;
/* 1115:     */  long glDisableIndexedEXT;
/* 1116:     */  long glIsEnabledIndexedEXT;
/* 1117:     */  long glDrawArraysInstancedEXT;
/* 1118:     */  long glDrawElementsInstancedEXT;
/* 1119:     */  long glDrawRangeElementsEXT;
/* 1120:     */  long glFogCoordfEXT;
/* 1121:     */  long glFogCoorddEXT;
/* 1122:     */  long glFogCoordPointerEXT;
/* 1123:     */  long glBlitFramebufferEXT;
/* 1124:     */  long glRenderbufferStorageMultisampleEXT;
/* 1125:     */  long glIsRenderbufferEXT;
/* 1126:     */  long glBindRenderbufferEXT;
/* 1127:     */  long glDeleteRenderbuffersEXT;
/* 1128:     */  long glGenRenderbuffersEXT;
/* 1129:     */  long glRenderbufferStorageEXT;
/* 1130:     */  long glGetRenderbufferParameterivEXT;
/* 1131:     */  long glIsFramebufferEXT;
/* 1132:     */  long glBindFramebufferEXT;
/* 1133:     */  long glDeleteFramebuffersEXT;
/* 1134:     */  long glGenFramebuffersEXT;
/* 1135:     */  long glCheckFramebufferStatusEXT;
/* 1136:     */  long glFramebufferTexture1DEXT;
/* 1137:     */  long glFramebufferTexture2DEXT;
/* 1138:     */  long glFramebufferTexture3DEXT;
/* 1139:     */  long glFramebufferRenderbufferEXT;
/* 1140:     */  long glGetFramebufferAttachmentParameterivEXT;
/* 1141:     */  long glGenerateMipmapEXT;
/* 1142:     */  long glProgramParameteriEXT;
/* 1143:     */  long glFramebufferTextureEXT;
/* 1144:     */  long glFramebufferTextureLayerEXT;
/* 1145:     */  long glFramebufferTextureFaceEXT;
/* 1146:     */  long glProgramEnvParameters4fvEXT;
/* 1147:     */  long glProgramLocalParameters4fvEXT;
/* 1148:     */  long glVertexAttribI1iEXT;
/* 1149:     */  long glVertexAttribI2iEXT;
/* 1150:     */  long glVertexAttribI3iEXT;
/* 1151:     */  long glVertexAttribI4iEXT;
/* 1152:     */  long glVertexAttribI1uiEXT;
/* 1153:     */  long glVertexAttribI2uiEXT;
/* 1154:     */  long glVertexAttribI3uiEXT;
/* 1155:     */  long glVertexAttribI4uiEXT;
/* 1156:     */  long glVertexAttribI1ivEXT;
/* 1157:     */  long glVertexAttribI2ivEXT;
/* 1158:     */  long glVertexAttribI3ivEXT;
/* 1159:     */  long glVertexAttribI4ivEXT;
/* 1160:     */  long glVertexAttribI1uivEXT;
/* 1161:     */  long glVertexAttribI2uivEXT;
/* 1162:     */  long glVertexAttribI3uivEXT;
/* 1163:     */  long glVertexAttribI4uivEXT;
/* 1164:     */  long glVertexAttribI4bvEXT;
/* 1165:     */  long glVertexAttribI4svEXT;
/* 1166:     */  long glVertexAttribI4ubvEXT;
/* 1167:     */  long glVertexAttribI4usvEXT;
/* 1168:     */  long glVertexAttribIPointerEXT;
/* 1169:     */  long glGetVertexAttribIivEXT;
/* 1170:     */  long glGetVertexAttribIuivEXT;
/* 1171:     */  long glUniform1uiEXT;
/* 1172:     */  long glUniform2uiEXT;
/* 1173:     */  long glUniform3uiEXT;
/* 1174:     */  long glUniform4uiEXT;
/* 1175:     */  long glUniform1uivEXT;
/* 1176:     */  long glUniform2uivEXT;
/* 1177:     */  long glUniform3uivEXT;
/* 1178:     */  long glUniform4uivEXT;
/* 1179:     */  long glGetUniformuivEXT;
/* 1180:     */  long glBindFragDataLocationEXT;
/* 1181:     */  long glGetFragDataLocationEXT;
/* 1182:     */  long glMultiDrawArraysEXT;
/* 1183:     */  long glColorTableEXT;
/* 1184:     */  long glColorSubTableEXT;
/* 1185:     */  long glGetColorTableEXT;
/* 1186:     */  long glGetColorTableParameterivEXT;
/* 1187:     */  long glGetColorTableParameterfvEXT;
/* 1188:     */  long glPointParameterfEXT;
/* 1189:     */  long glPointParameterfvEXT;
/* 1190:     */  long glProvokingVertexEXT;
/* 1191:     */  long glSecondaryColor3bEXT;
/* 1192:     */  long glSecondaryColor3fEXT;
/* 1193:     */  long glSecondaryColor3dEXT;
/* 1194:     */  long glSecondaryColor3ubEXT;
/* 1195:     */  long glSecondaryColorPointerEXT;
/* 1196:     */  long glUseShaderProgramEXT;
/* 1197:     */  long glActiveProgramEXT;
/* 1198:     */  long glCreateShaderProgramEXT;
/* 1199:     */  long glBindImageTextureEXT;
/* 1200:     */  long glMemoryBarrierEXT;
/* 1201:     */  long glStencilClearTagEXT;
/* 1202:     */  long glActiveStencilFaceEXT;
/* 1203:     */  long glTexBufferEXT;
/* 1204:     */  long glClearColorIiEXT;
/* 1205:     */  long glClearColorIuiEXT;
/* 1206:     */  long glTexParameterIivEXT;
/* 1207:     */  long glTexParameterIuivEXT;
/* 1208:     */  long glGetTexParameterIivEXT;
/* 1209:     */  long glGetTexParameterIuivEXT;
/* 1210:     */  long glGetQueryObjecti64vEXT;
/* 1211:     */  long glGetQueryObjectui64vEXT;
/* 1212:     */  long glBindBufferRangeEXT;
/* 1213:     */  long glBindBufferOffsetEXT;
/* 1214:     */  long glBindBufferBaseEXT;
/* 1215:     */  long glBeginTransformFeedbackEXT;
/* 1216:     */  long glEndTransformFeedbackEXT;
/* 1217:     */  long glTransformFeedbackVaryingsEXT;
/* 1218:     */  long glGetTransformFeedbackVaryingEXT;
/* 1219:     */  long glVertexAttribL1dEXT;
/* 1220:     */  long glVertexAttribL2dEXT;
/* 1221:     */  long glVertexAttribL3dEXT;
/* 1222:     */  long glVertexAttribL4dEXT;
/* 1223:     */  long glVertexAttribL1dvEXT;
/* 1224:     */  long glVertexAttribL2dvEXT;
/* 1225:     */  long glVertexAttribL3dvEXT;
/* 1226:     */  long glVertexAttribL4dvEXT;
/* 1227:     */  long glVertexAttribLPointerEXT;
/* 1228:     */  long glGetVertexAttribLdvEXT;
/* 1229:     */  long glBeginVertexShaderEXT;
/* 1230:     */  long glEndVertexShaderEXT;
/* 1231:     */  long glBindVertexShaderEXT;
/* 1232:     */  long glGenVertexShadersEXT;
/* 1233:     */  long glDeleteVertexShaderEXT;
/* 1234:     */  long glShaderOp1EXT;
/* 1235:     */  long glShaderOp2EXT;
/* 1236:     */  long glShaderOp3EXT;
/* 1237:     */  long glSwizzleEXT;
/* 1238:     */  long glWriteMaskEXT;
/* 1239:     */  long glInsertComponentEXT;
/* 1240:     */  long glExtractComponentEXT;
/* 1241:     */  long glGenSymbolsEXT;
/* 1242:     */  long glSetInvariantEXT;
/* 1243:     */  long glSetLocalConstantEXT;
/* 1244:     */  long glVariantbvEXT;
/* 1245:     */  long glVariantsvEXT;
/* 1246:     */  long glVariantivEXT;
/* 1247:     */  long glVariantfvEXT;
/* 1248:     */  long glVariantdvEXT;
/* 1249:     */  long glVariantubvEXT;
/* 1250:     */  long glVariantusvEXT;
/* 1251:     */  long glVariantuivEXT;
/* 1252:     */  long glVariantPointerEXT;
/* 1253:     */  long glEnableVariantClientStateEXT;
/* 1254:     */  long glDisableVariantClientStateEXT;
/* 1255:     */  long glBindLightParameterEXT;
/* 1256:     */  long glBindMaterialParameterEXT;
/* 1257:     */  long glBindTexGenParameterEXT;
/* 1258:     */  long glBindTextureUnitParameterEXT;
/* 1259:     */  long glBindParameterEXT;
/* 1260:     */  long glIsVariantEnabledEXT;
/* 1261:     */  long glGetVariantBooleanvEXT;
/* 1262:     */  long glGetVariantIntegervEXT;
/* 1263:     */  long glGetVariantFloatvEXT;
/* 1264:     */  long glGetVariantPointervEXT;
/* 1265:     */  long glGetInvariantBooleanvEXT;
/* 1266:     */  long glGetInvariantIntegervEXT;
/* 1267:     */  long glGetInvariantFloatvEXT;
/* 1268:     */  long glGetLocalConstantBooleanvEXT;
/* 1269:     */  long glGetLocalConstantIntegervEXT;
/* 1270:     */  long glGetLocalConstantFloatvEXT;
/* 1271:     */  long glVertexWeightfEXT;
/* 1272:     */  long glVertexWeightPointerEXT;
/* 1273:     */  long glAccum;
/* 1274:     */  long glAlphaFunc;
/* 1275:     */  long glClearColor;
/* 1276:     */  long glClearAccum;
/* 1277:     */  long glClear;
/* 1278:     */  long glCallLists;
/* 1279:     */  long glCallList;
/* 1280:     */  long glBlendFunc;
/* 1281:     */  long glBitmap;
/* 1282:     */  long glBindTexture;
/* 1283:     */  long glPrioritizeTextures;
/* 1284:     */  long glAreTexturesResident;
/* 1285:     */  long glBegin;
/* 1286:     */  long glEnd;
/* 1287:     */  long glArrayElement;
/* 1288:     */  long glClearDepth;
/* 1289:     */  long glDeleteLists;
/* 1290:     */  long glDeleteTextures;
/* 1291:     */  long glCullFace;
/* 1292:     */  long glCopyTexSubImage2D;
/* 1293:     */  long glCopyTexSubImage1D;
/* 1294:     */  long glCopyTexImage2D;
/* 1295:     */  long glCopyTexImage1D;
/* 1296:     */  long glCopyPixels;
/* 1297:     */  long glColorPointer;
/* 1298:     */  long glColorMaterial;
/* 1299:     */  long glColorMask;
/* 1300:     */  long glColor3b;
/* 1301:     */  long glColor3f;
/* 1302:     */  long glColor3d;
/* 1303:     */  long glColor3ub;
/* 1304:     */  long glColor4b;
/* 1305:     */  long glColor4f;
/* 1306:     */  long glColor4d;
/* 1307:     */  long glColor4ub;
/* 1308:     */  long glClipPlane;
/* 1309:     */  long glClearStencil;
/* 1310:     */  long glEvalPoint1;
/* 1311:     */  long glEvalPoint2;
/* 1312:     */  long glEvalMesh1;
/* 1313:     */  long glEvalMesh2;
/* 1314:     */  long glEvalCoord1f;
/* 1315:     */  long glEvalCoord1d;
/* 1316:     */  long glEvalCoord2f;
/* 1317:     */  long glEvalCoord2d;
/* 1318:     */  long glEnableClientState;
/* 1319:     */  long glDisableClientState;
/* 1320:     */  long glEnable;
/* 1321:     */  long glDisable;
/* 1322:     */  long glEdgeFlagPointer;
/* 1323:     */  long glEdgeFlag;
/* 1324:     */  long glDrawPixels;
/* 1325:     */  long glDrawElements;
/* 1326:     */  long glDrawBuffer;
/* 1327:     */  long glDrawArrays;
/* 1328:     */  long glDepthRange;
/* 1329:     */  long glDepthMask;
/* 1330:     */  long glDepthFunc;
/* 1331:     */  long glFeedbackBuffer;
/* 1332:     */  long glGetPixelMapfv;
/* 1333:     */  long glGetPixelMapuiv;
/* 1334:     */  long glGetPixelMapusv;
/* 1335:     */  long glGetMaterialfv;
/* 1336:     */  long glGetMaterialiv;
/* 1337:     */  long glGetMapfv;
/* 1338:     */  long glGetMapdv;
/* 1339:     */  long glGetMapiv;
/* 1340:     */  long glGetLightfv;
/* 1341:     */  long glGetLightiv;
/* 1342:     */  long glGetError;
/* 1343:     */  long glGetClipPlane;
/* 1344:     */  long glGetBooleanv;
/* 1345:     */  long glGetDoublev;
/* 1346:     */  long glGetFloatv;
/* 1347:     */  long glGetIntegerv;
/* 1348:     */  long glGenTextures;
/* 1349:     */  long glGenLists;
/* 1350:     */  long glFrustum;
/* 1351:     */  long glFrontFace;
/* 1352:     */  long glFogf;
/* 1353:     */  long glFogi;
/* 1354:     */  long glFogfv;
/* 1355:     */  long glFogiv;
/* 1356:     */  long glFlush;
/* 1357:     */  long glFinish;
/* 1358:     */  long glGetPointerv;
/* 1359:     */  long glIsEnabled;
/* 1360:     */  long glInterleavedArrays;
/* 1361:     */  long glInitNames;
/* 1362:     */  long glHint;
/* 1363:     */  long glGetTexParameterfv;
/* 1364:     */  long glGetTexParameteriv;
/* 1365:     */  long glGetTexLevelParameterfv;
/* 1366:     */  long glGetTexLevelParameteriv;
/* 1367:     */  long glGetTexImage;
/* 1368:     */  long glGetTexGeniv;
/* 1369:     */  long glGetTexGenfv;
/* 1370:     */  long glGetTexGendv;
/* 1371:     */  long glGetTexEnviv;
/* 1372:     */  long glGetTexEnvfv;
/* 1373:     */  long glGetString;
/* 1374:     */  long glGetPolygonStipple;
/* 1375:     */  long glIsList;
/* 1376:     */  long glMaterialf;
/* 1377:     */  long glMateriali;
/* 1378:     */  long glMaterialfv;
/* 1379:     */  long glMaterialiv;
/* 1380:     */  long glMapGrid1f;
/* 1381:     */  long glMapGrid1d;
/* 1382:     */  long glMapGrid2f;
/* 1383:     */  long glMapGrid2d;
/* 1384:     */  long glMap2f;
/* 1385:     */  long glMap2d;
/* 1386:     */  long glMap1f;
/* 1387:     */  long glMap1d;
/* 1388:     */  long glLogicOp;
/* 1389:     */  long glLoadName;
/* 1390:     */  long glLoadMatrixf;
/* 1391:     */  long glLoadMatrixd;
/* 1392:     */  long glLoadIdentity;
/* 1393:     */  long glListBase;
/* 1394:     */  long glLineWidth;
/* 1395:     */  long glLineStipple;
/* 1396:     */  long glLightModelf;
/* 1397:     */  long glLightModeli;
/* 1398:     */  long glLightModelfv;
/* 1399:     */  long glLightModeliv;
/* 1400:     */  long glLightf;
/* 1401:     */  long glLighti;
/* 1402:     */  long glLightfv;
/* 1403:     */  long glLightiv;
/* 1404:     */  long glIsTexture;
/* 1405:     */  long glMatrixMode;
/* 1406:     */  long glPolygonStipple;
/* 1407:     */  long glPolygonOffset;
/* 1408:     */  long glPolygonMode;
/* 1409:     */  long glPointSize;
/* 1410:     */  long glPixelZoom;
/* 1411:     */  long glPixelTransferf;
/* 1412:     */  long glPixelTransferi;
/* 1413:     */  long glPixelStoref;
/* 1414:     */  long glPixelStorei;
/* 1415:     */  long glPixelMapfv;
/* 1416:     */  long glPixelMapuiv;
/* 1417:     */  long glPixelMapusv;
/* 1418:     */  long glPassThrough;
/* 1419:     */  long glOrtho;
/* 1420:     */  long glNormalPointer;
/* 1421:     */  long glNormal3b;
/* 1422:     */  long glNormal3f;
/* 1423:     */  long glNormal3d;
/* 1424:     */  long glNormal3i;
/* 1425:     */  long glNewList;
/* 1426:     */  long glEndList;
/* 1427:     */  long glMultMatrixf;
/* 1428:     */  long glMultMatrixd;
/* 1429:     */  long glShadeModel;
/* 1430:     */  long glSelectBuffer;
/* 1431:     */  long glScissor;
/* 1432:     */  long glScalef;
/* 1433:     */  long glScaled;
/* 1434:     */  long glRotatef;
/* 1435:     */  long glRotated;
/* 1436:     */  long glRenderMode;
/* 1437:     */  long glRectf;
/* 1438:     */  long glRectd;
/* 1439:     */  long glRecti;
/* 1440:     */  long glReadPixels;
/* 1441:     */  long glReadBuffer;
/* 1442:     */  long glRasterPos2f;
/* 1443:     */  long glRasterPos2d;
/* 1444:     */  long glRasterPos2i;
/* 1445:     */  long glRasterPos3f;
/* 1446:     */  long glRasterPos3d;
/* 1447:     */  long glRasterPos3i;
/* 1448:     */  long glRasterPos4f;
/* 1449:     */  long glRasterPos4d;
/* 1450:     */  long glRasterPos4i;
/* 1451:     */  long glPushName;
/* 1452:     */  long glPopName;
/* 1453:     */  long glPushMatrix;
/* 1454:     */  long glPopMatrix;
/* 1455:     */  long glPushClientAttrib;
/* 1456:     */  long glPopClientAttrib;
/* 1457:     */  long glPushAttrib;
/* 1458:     */  long glPopAttrib;
/* 1459:     */  long glStencilFunc;
/* 1460:     */  long glVertexPointer;
/* 1461:     */  long glVertex2f;
/* 1462:     */  long glVertex2d;
/* 1463:     */  long glVertex2i;
/* 1464:     */  long glVertex3f;
/* 1465:     */  long glVertex3d;
/* 1466:     */  long glVertex3i;
/* 1467:     */  long glVertex4f;
/* 1468:     */  long glVertex4d;
/* 1469:     */  long glVertex4i;
/* 1470:     */  long glTranslatef;
/* 1471:     */  long glTranslated;
/* 1472:     */  long glTexImage1D;
/* 1473:     */  long glTexImage2D;
/* 1474:     */  long glTexSubImage1D;
/* 1475:     */  long glTexSubImage2D;
/* 1476:     */  long glTexParameterf;
/* 1477:     */  long glTexParameteri;
/* 1478:     */  long glTexParameterfv;
/* 1479:     */  long glTexParameteriv;
/* 1480:     */  long glTexGenf;
/* 1481:     */  long glTexGend;
/* 1482:     */  long glTexGenfv;
/* 1483:     */  long glTexGendv;
/* 1484:     */  long glTexGeni;
/* 1485:     */  long glTexGeniv;
/* 1486:     */  long glTexEnvf;
/* 1487:     */  long glTexEnvi;
/* 1488:     */  long glTexEnvfv;
/* 1489:     */  long glTexEnviv;
/* 1490:     */  long glTexCoordPointer;
/* 1491:     */  long glTexCoord1f;
/* 1492:     */  long glTexCoord1d;
/* 1493:     */  long glTexCoord2f;
/* 1494:     */  long glTexCoord2d;
/* 1495:     */  long glTexCoord3f;
/* 1496:     */  long glTexCoord3d;
/* 1497:     */  long glTexCoord4f;
/* 1498:     */  long glTexCoord4d;
/* 1499:     */  long glStencilOp;
/* 1500:     */  long glStencilMask;
/* 1501:     */  long glViewport;
/* 1502:     */  long glDrawRangeElements;
/* 1503:     */  long glTexImage3D;
/* 1504:     */  long glTexSubImage3D;
/* 1505:     */  long glCopyTexSubImage3D;
/* 1506:     */  long glActiveTexture;
/* 1507:     */  long glClientActiveTexture;
/* 1508:     */  long glCompressedTexImage1D;
/* 1509:     */  long glCompressedTexImage2D;
/* 1510:     */  long glCompressedTexImage3D;
/* 1511:     */  long glCompressedTexSubImage1D;
/* 1512:     */  long glCompressedTexSubImage2D;
/* 1513:     */  long glCompressedTexSubImage3D;
/* 1514:     */  long glGetCompressedTexImage;
/* 1515:     */  long glMultiTexCoord1f;
/* 1516:     */  long glMultiTexCoord1d;
/* 1517:     */  long glMultiTexCoord2f;
/* 1518:     */  long glMultiTexCoord2d;
/* 1519:     */  long glMultiTexCoord3f;
/* 1520:     */  long glMultiTexCoord3d;
/* 1521:     */  long glMultiTexCoord4f;
/* 1522:     */  long glMultiTexCoord4d;
/* 1523:     */  long glLoadTransposeMatrixf;
/* 1524:     */  long glLoadTransposeMatrixd;
/* 1525:     */  long glMultTransposeMatrixf;
/* 1526:     */  long glMultTransposeMatrixd;
/* 1527:     */  long glSampleCoverage;
/* 1528:     */  long glBlendEquation;
/* 1529:     */  long glBlendColor;
/* 1530:     */  long glFogCoordf;
/* 1531:     */  long glFogCoordd;
/* 1532:     */  long glFogCoordPointer;
/* 1533:     */  long glMultiDrawArrays;
/* 1534:     */  long glPointParameteri;
/* 1535:     */  long glPointParameterf;
/* 1536:     */  long glPointParameteriv;
/* 1537:     */  long glPointParameterfv;
/* 1538:     */  long glSecondaryColor3b;
/* 1539:     */  long glSecondaryColor3f;
/* 1540:     */  long glSecondaryColor3d;
/* 1541:     */  long glSecondaryColor3ub;
/* 1542:     */  long glSecondaryColorPointer;
/* 1543:     */  long glBlendFuncSeparate;
/* 1544:     */  long glWindowPos2f;
/* 1545:     */  long glWindowPos2d;
/* 1546:     */  long glWindowPos2i;
/* 1547:     */  long glWindowPos3f;
/* 1548:     */  long glWindowPos3d;
/* 1549:     */  long glWindowPos3i;
/* 1550:     */  long glBindBuffer;
/* 1551:     */  long glDeleteBuffers;
/* 1552:     */  long glGenBuffers;
/* 1553:     */  long glIsBuffer;
/* 1554:     */  long glBufferData;
/* 1555:     */  long glBufferSubData;
/* 1556:     */  long glGetBufferSubData;
/* 1557:     */  long glMapBuffer;
/* 1558:     */  long glUnmapBuffer;
/* 1559:     */  long glGetBufferParameteriv;
/* 1560:     */  long glGetBufferPointerv;
/* 1561:     */  long glGenQueries;
/* 1562:     */  long glDeleteQueries;
/* 1563:     */  long glIsQuery;
/* 1564:     */  long glBeginQuery;
/* 1565:     */  long glEndQuery;
/* 1566:     */  long glGetQueryiv;
/* 1567:     */  long glGetQueryObjectiv;
/* 1568:     */  long glGetQueryObjectuiv;
/* 1569:     */  long glShaderSource;
/* 1570:     */  long glCreateShader;
/* 1571:     */  long glIsShader;
/* 1572:     */  long glCompileShader;
/* 1573:     */  long glDeleteShader;
/* 1574:     */  long glCreateProgram;
/* 1575:     */  long glIsProgram;
/* 1576:     */  long glAttachShader;
/* 1577:     */  long glDetachShader;
/* 1578:     */  long glLinkProgram;
/* 1579:     */  long glUseProgram;
/* 1580:     */  long glValidateProgram;
/* 1581:     */  long glDeleteProgram;
/* 1582:     */  long glUniform1f;
/* 1583:     */  long glUniform2f;
/* 1584:     */  long glUniform3f;
/* 1585:     */  long glUniform4f;
/* 1586:     */  long glUniform1i;
/* 1587:     */  long glUniform2i;
/* 1588:     */  long glUniform3i;
/* 1589:     */  long glUniform4i;
/* 1590:     */  long glUniform1fv;
/* 1591:     */  long glUniform2fv;
/* 1592:     */  long glUniform3fv;
/* 1593:     */  long glUniform4fv;
/* 1594:     */  long glUniform1iv;
/* 1595:     */  long glUniform2iv;
/* 1596:     */  long glUniform3iv;
/* 1597:     */  long glUniform4iv;
/* 1598:     */  long glUniformMatrix2fv;
/* 1599:     */  long glUniformMatrix3fv;
/* 1600:     */  long glUniformMatrix4fv;
/* 1601:     */  long glGetShaderiv;
/* 1602:     */  long glGetProgramiv;
/* 1603:     */  long glGetShaderInfoLog;
/* 1604:     */  long glGetProgramInfoLog;
/* 1605:     */  long glGetAttachedShaders;
/* 1606:     */  long glGetUniformLocation;
/* 1607:     */  long glGetActiveUniform;
/* 1608:     */  long glGetUniformfv;
/* 1609:     */  long glGetUniformiv;
/* 1610:     */  long glGetShaderSource;
/* 1611:     */  long glVertexAttrib1s;
/* 1612:     */  long glVertexAttrib1f;
/* 1613:     */  long glVertexAttrib1d;
/* 1614:     */  long glVertexAttrib2s;
/* 1615:     */  long glVertexAttrib2f;
/* 1616:     */  long glVertexAttrib2d;
/* 1617:     */  long glVertexAttrib3s;
/* 1618:     */  long glVertexAttrib3f;
/* 1619:     */  long glVertexAttrib3d;
/* 1620:     */  long glVertexAttrib4s;
/* 1621:     */  long glVertexAttrib4f;
/* 1622:     */  long glVertexAttrib4d;
/* 1623:     */  long glVertexAttrib4Nub;
/* 1624:     */  long glVertexAttribPointer;
/* 1625:     */  long glEnableVertexAttribArray;
/* 1626:     */  long glDisableVertexAttribArray;
/* 1627:     */  long glGetVertexAttribfv;
/* 1628:     */  long glGetVertexAttribdv;
/* 1629:     */  long glGetVertexAttribiv;
/* 1630:     */  long glGetVertexAttribPointerv;
/* 1631:     */  long glBindAttribLocation;
/* 1632:     */  long glGetActiveAttrib;
/* 1633:     */  long glGetAttribLocation;
/* 1634:     */  long glDrawBuffers;
/* 1635:     */  long glStencilOpSeparate;
/* 1636:     */  long glStencilFuncSeparate;
/* 1637:     */  long glStencilMaskSeparate;
/* 1638:     */  long glBlendEquationSeparate;
/* 1639:     */  long glUniformMatrix2x3fv;
/* 1640:     */  long glUniformMatrix3x2fv;
/* 1641:     */  long glUniformMatrix2x4fv;
/* 1642:     */  long glUniformMatrix4x2fv;
/* 1643:     */  long glUniformMatrix3x4fv;
/* 1644:     */  long glUniformMatrix4x3fv;
/* 1645:     */  long glGetStringi;
/* 1646:     */  long glClearBufferfv;
/* 1647:     */  long glClearBufferiv;
/* 1648:     */  long glClearBufferuiv;
/* 1649:     */  long glClearBufferfi;
/* 1650:     */  long glVertexAttribI1i;
/* 1651:     */  long glVertexAttribI2i;
/* 1652:     */  long glVertexAttribI3i;
/* 1653:     */  long glVertexAttribI4i;
/* 1654:     */  long glVertexAttribI1ui;
/* 1655:     */  long glVertexAttribI2ui;
/* 1656:     */  long glVertexAttribI3ui;
/* 1657:     */  long glVertexAttribI4ui;
/* 1658:     */  long glVertexAttribI1iv;
/* 1659:     */  long glVertexAttribI2iv;
/* 1660:     */  long glVertexAttribI3iv;
/* 1661:     */  long glVertexAttribI4iv;
/* 1662:     */  long glVertexAttribI1uiv;
/* 1663:     */  long glVertexAttribI2uiv;
/* 1664:     */  long glVertexAttribI3uiv;
/* 1665:     */  long glVertexAttribI4uiv;
/* 1666:     */  long glVertexAttribI4bv;
/* 1667:     */  long glVertexAttribI4sv;
/* 1668:     */  long glVertexAttribI4ubv;
/* 1669:     */  long glVertexAttribI4usv;
/* 1670:     */  long glVertexAttribIPointer;
/* 1671:     */  long glGetVertexAttribIiv;
/* 1672:     */  long glGetVertexAttribIuiv;
/* 1673:     */  long glUniform1ui;
/* 1674:     */  long glUniform2ui;
/* 1675:     */  long glUniform3ui;
/* 1676:     */  long glUniform4ui;
/* 1677:     */  long glUniform1uiv;
/* 1678:     */  long glUniform2uiv;
/* 1679:     */  long glUniform3uiv;
/* 1680:     */  long glUniform4uiv;
/* 1681:     */  long glGetUniformuiv;
/* 1682:     */  long glBindFragDataLocation;
/* 1683:     */  long glGetFragDataLocation;
/* 1684:     */  long glBeginConditionalRender;
/* 1685:     */  long glEndConditionalRender;
/* 1686:     */  long glMapBufferRange;
/* 1687:     */  long glFlushMappedBufferRange;
/* 1688:     */  long glClampColor;
/* 1689:     */  long glIsRenderbuffer;
/* 1690:     */  long glBindRenderbuffer;
/* 1691:     */  long glDeleteRenderbuffers;
/* 1692:     */  long glGenRenderbuffers;
/* 1693:     */  long glRenderbufferStorage;
/* 1694:     */  long glGetRenderbufferParameteriv;
/* 1695:     */  long glIsFramebuffer;
/* 1696:     */  long glBindFramebuffer;
/* 1697:     */  long glDeleteFramebuffers;
/* 1698:     */  long glGenFramebuffers;
/* 1699:     */  long glCheckFramebufferStatus;
/* 1700:     */  long glFramebufferTexture1D;
/* 1701:     */  long glFramebufferTexture2D;
/* 1702:     */  long glFramebufferTexture3D;
/* 1703:     */  long glFramebufferRenderbuffer;
/* 1704:     */  long glGetFramebufferAttachmentParameteriv;
/* 1705:     */  long glGenerateMipmap;
/* 1706:     */  long glRenderbufferStorageMultisample;
/* 1707:     */  long glBlitFramebuffer;
/* 1708:     */  long glTexParameterIiv;
/* 1709:     */  long glTexParameterIuiv;
/* 1710:     */  long glGetTexParameterIiv;
/* 1711:     */  long glGetTexParameterIuiv;
/* 1712:     */  long glFramebufferTextureLayer;
/* 1713:     */  long glColorMaski;
/* 1714:     */  long glGetBooleani_v;
/* 1715:     */  long glGetIntegeri_v;
/* 1716:     */  long glEnablei;
/* 1717:     */  long glDisablei;
/* 1718:     */  long glIsEnabledi;
/* 1719:     */  long glBindBufferRange;
/* 1720:     */  long glBindBufferBase;
/* 1721:     */  long glBeginTransformFeedback;
/* 1722:     */  long glEndTransformFeedback;
/* 1723:     */  long glTransformFeedbackVaryings;
/* 1724:     */  long glGetTransformFeedbackVarying;
/* 1725:     */  long glBindVertexArray;
/* 1726:     */  long glDeleteVertexArrays;
/* 1727:     */  long glGenVertexArrays;
/* 1728:     */  long glIsVertexArray;
/* 1729:     */  long glDrawArraysInstanced;
/* 1730:     */  long glDrawElementsInstanced;
/* 1731:     */  long glCopyBufferSubData;
/* 1732:     */  long glPrimitiveRestartIndex;
/* 1733:     */  long glTexBuffer;
/* 1734:     */  long glGetUniformIndices;
/* 1735:     */  long glGetActiveUniformsiv;
/* 1736:     */  long glGetActiveUniformName;
/* 1737:     */  long glGetUniformBlockIndex;
/* 1738:     */  long glGetActiveUniformBlockiv;
/* 1739:     */  long glGetActiveUniformBlockName;
/* 1740:     */  long glUniformBlockBinding;
/* 1741:     */  long glGetBufferParameteri64v;
/* 1742:     */  long glDrawElementsBaseVertex;
/* 1743:     */  long glDrawRangeElementsBaseVertex;
/* 1744:     */  long glDrawElementsInstancedBaseVertex;
/* 1745:     */  long glProvokingVertex;
/* 1746:     */  long glTexImage2DMultisample;
/* 1747:     */  long glTexImage3DMultisample;
/* 1748:     */  long glGetMultisamplefv;
/* 1749:     */  long glSampleMaski;
/* 1750:     */  long glFramebufferTexture;
/* 1751:     */  long glFenceSync;
/* 1752:     */  long glIsSync;
/* 1753:     */  long glDeleteSync;
/* 1754:     */  long glClientWaitSync;
/* 1755:     */  long glWaitSync;
/* 1756:     */  long glGetInteger64v;
/* 1757:     */  long glGetInteger64i_v;
/* 1758:     */  long glGetSynciv;
/* 1759:     */  long glBindFragDataLocationIndexed;
/* 1760:     */  long glGetFragDataIndex;
/* 1761:     */  long glGenSamplers;
/* 1762:     */  long glDeleteSamplers;
/* 1763:     */  long glIsSampler;
/* 1764:     */  long glBindSampler;
/* 1765:     */  long glSamplerParameteri;
/* 1766:     */  long glSamplerParameterf;
/* 1767:     */  long glSamplerParameteriv;
/* 1768:     */  long glSamplerParameterfv;
/* 1769:     */  long glSamplerParameterIiv;
/* 1770:     */  long glSamplerParameterIuiv;
/* 1771:     */  long glGetSamplerParameteriv;
/* 1772:     */  long glGetSamplerParameterfv;
/* 1773:     */  long glGetSamplerParameterIiv;
/* 1774:     */  long glGetSamplerParameterIuiv;
/* 1775:     */  long glQueryCounter;
/* 1776:     */  long glGetQueryObjecti64v;
/* 1777:     */  long glGetQueryObjectui64v;
/* 1778:     */  long glVertexAttribDivisor;
/* 1779:     */  long glVertexP2ui;
/* 1780:     */  long glVertexP3ui;
/* 1781:     */  long glVertexP4ui;
/* 1782:     */  long glVertexP2uiv;
/* 1783:     */  long glVertexP3uiv;
/* 1784:     */  long glVertexP4uiv;
/* 1785:     */  long glTexCoordP1ui;
/* 1786:     */  long glTexCoordP2ui;
/* 1787:     */  long glTexCoordP3ui;
/* 1788:     */  long glTexCoordP4ui;
/* 1789:     */  long glTexCoordP1uiv;
/* 1790:     */  long glTexCoordP2uiv;
/* 1791:     */  long glTexCoordP3uiv;
/* 1792:     */  long glTexCoordP4uiv;
/* 1793:     */  long glMultiTexCoordP1ui;
/* 1794:     */  long glMultiTexCoordP2ui;
/* 1795:     */  long glMultiTexCoordP3ui;
/* 1796:     */  long glMultiTexCoordP4ui;
/* 1797:     */  long glMultiTexCoordP1uiv;
/* 1798:     */  long glMultiTexCoordP2uiv;
/* 1799:     */  long glMultiTexCoordP3uiv;
/* 1800:     */  long glMultiTexCoordP4uiv;
/* 1801:     */  long glNormalP3ui;
/* 1802:     */  long glNormalP3uiv;
/* 1803:     */  long glColorP3ui;
/* 1804:     */  long glColorP4ui;
/* 1805:     */  long glColorP3uiv;
/* 1806:     */  long glColorP4uiv;
/* 1807:     */  long glSecondaryColorP3ui;
/* 1808:     */  long glSecondaryColorP3uiv;
/* 1809:     */  long glVertexAttribP1ui;
/* 1810:     */  long glVertexAttribP2ui;
/* 1811:     */  long glVertexAttribP3ui;
/* 1812:     */  long glVertexAttribP4ui;
/* 1813:     */  long glVertexAttribP1uiv;
/* 1814:     */  long glVertexAttribP2uiv;
/* 1815:     */  long glVertexAttribP3uiv;
/* 1816:     */  long glVertexAttribP4uiv;
/* 1817:     */  long glBlendEquationi;
/* 1818:     */  long glBlendEquationSeparatei;
/* 1819:     */  long glBlendFunci;
/* 1820:     */  long glBlendFuncSeparatei;
/* 1821:     */  long glDrawArraysIndirect;
/* 1822:     */  long glDrawElementsIndirect;
/* 1823:     */  long glUniform1d;
/* 1824:     */  long glUniform2d;
/* 1825:     */  long glUniform3d;
/* 1826:     */  long glUniform4d;
/* 1827:     */  long glUniform1dv;
/* 1828:     */  long glUniform2dv;
/* 1829:     */  long glUniform3dv;
/* 1830:     */  long glUniform4dv;
/* 1831:     */  long glUniformMatrix2dv;
/* 1832:     */  long glUniformMatrix3dv;
/* 1833:     */  long glUniformMatrix4dv;
/* 1834:     */  long glUniformMatrix2x3dv;
/* 1835:     */  long glUniformMatrix2x4dv;
/* 1836:     */  long glUniformMatrix3x2dv;
/* 1837:     */  long glUniformMatrix3x4dv;
/* 1838:     */  long glUniformMatrix4x2dv;
/* 1839:     */  long glUniformMatrix4x3dv;
/* 1840:     */  long glGetUniformdv;
/* 1841:     */  long glMinSampleShading;
/* 1842:     */  long glGetSubroutineUniformLocation;
/* 1843:     */  long glGetSubroutineIndex;
/* 1844:     */  long glGetActiveSubroutineUniformiv;
/* 1845:     */  long glGetActiveSubroutineUniformName;
/* 1846:     */  long glGetActiveSubroutineName;
/* 1847:     */  long glUniformSubroutinesuiv;
/* 1848:     */  long glGetUniformSubroutineuiv;
/* 1849:     */  long glGetProgramStageiv;
/* 1850:     */  long glPatchParameteri;
/* 1851:     */  long glPatchParameterfv;
/* 1852:     */  long glBindTransformFeedback;
/* 1853:     */  long glDeleteTransformFeedbacks;
/* 1854:     */  long glGenTransformFeedbacks;
/* 1855:     */  long glIsTransformFeedback;
/* 1856:     */  long glPauseTransformFeedback;
/* 1857:     */  long glResumeTransformFeedback;
/* 1858:     */  long glDrawTransformFeedback;
/* 1859:     */  long glDrawTransformFeedbackStream;
/* 1860:     */  long glBeginQueryIndexed;
/* 1861:     */  long glEndQueryIndexed;
/* 1862:     */  long glGetQueryIndexediv;
/* 1863:     */  long glReleaseShaderCompiler;
/* 1864:     */  long glShaderBinary;
/* 1865:     */  long glGetShaderPrecisionFormat;
/* 1866:     */  long glDepthRangef;
/* 1867:     */  long glClearDepthf;
/* 1868:     */  long glGetProgramBinary;
/* 1869:     */  long glProgramBinary;
/* 1870:     */  long glProgramParameteri;
/* 1871:     */  long glUseProgramStages;
/* 1872:     */  long glActiveShaderProgram;
/* 1873:     */  long glCreateShaderProgramv;
/* 1874:     */  long glBindProgramPipeline;
/* 1875:     */  long glDeleteProgramPipelines;
/* 1876:     */  long glGenProgramPipelines;
/* 1877:     */  long glIsProgramPipeline;
/* 1878:     */  long glGetProgramPipelineiv;
/* 1879:     */  long glProgramUniform1i;
/* 1880:     */  long glProgramUniform2i;
/* 1881:     */  long glProgramUniform3i;
/* 1882:     */  long glProgramUniform4i;
/* 1883:     */  long glProgramUniform1f;
/* 1884:     */  long glProgramUniform2f;
/* 1885:     */  long glProgramUniform3f;
/* 1886:     */  long glProgramUniform4f;
/* 1887:     */  long glProgramUniform1d;
/* 1888:     */  long glProgramUniform2d;
/* 1889:     */  long glProgramUniform3d;
/* 1890:     */  long glProgramUniform4d;
/* 1891:     */  long glProgramUniform1iv;
/* 1892:     */  long glProgramUniform2iv;
/* 1893:     */  long glProgramUniform3iv;
/* 1894:     */  long glProgramUniform4iv;
/* 1895:     */  long glProgramUniform1fv;
/* 1896:     */  long glProgramUniform2fv;
/* 1897:     */  long glProgramUniform3fv;
/* 1898:     */  long glProgramUniform4fv;
/* 1899:     */  long glProgramUniform1dv;
/* 1900:     */  long glProgramUniform2dv;
/* 1901:     */  long glProgramUniform3dv;
/* 1902:     */  long glProgramUniform4dv;
/* 1903:     */  long glProgramUniform1ui;
/* 1904:     */  long glProgramUniform2ui;
/* 1905:     */  long glProgramUniform3ui;
/* 1906:     */  long glProgramUniform4ui;
/* 1907:     */  long glProgramUniform1uiv;
/* 1908:     */  long glProgramUniform2uiv;
/* 1909:     */  long glProgramUniform3uiv;
/* 1910:     */  long glProgramUniform4uiv;
/* 1911:     */  long glProgramUniformMatrix2fv;
/* 1912:     */  long glProgramUniformMatrix3fv;
/* 1913:     */  long glProgramUniformMatrix4fv;
/* 1914:     */  long glProgramUniformMatrix2dv;
/* 1915:     */  long glProgramUniformMatrix3dv;
/* 1916:     */  long glProgramUniformMatrix4dv;
/* 1917:     */  long glProgramUniformMatrix2x3fv;
/* 1918:     */  long glProgramUniformMatrix3x2fv;
/* 1919:     */  long glProgramUniformMatrix2x4fv;
/* 1920:     */  long glProgramUniformMatrix4x2fv;
/* 1921:     */  long glProgramUniformMatrix3x4fv;
/* 1922:     */  long glProgramUniformMatrix4x3fv;
/* 1923:     */  long glProgramUniformMatrix2x3dv;
/* 1924:     */  long glProgramUniformMatrix3x2dv;
/* 1925:     */  long glProgramUniformMatrix2x4dv;
/* 1926:     */  long glProgramUniformMatrix4x2dv;
/* 1927:     */  long glProgramUniformMatrix3x4dv;
/* 1928:     */  long glProgramUniformMatrix4x3dv;
/* 1929:     */  long glValidateProgramPipeline;
/* 1930:     */  long glGetProgramPipelineInfoLog;
/* 1931:     */  long glVertexAttribL1d;
/* 1932:     */  long glVertexAttribL2d;
/* 1933:     */  long glVertexAttribL3d;
/* 1934:     */  long glVertexAttribL4d;
/* 1935:     */  long glVertexAttribL1dv;
/* 1936:     */  long glVertexAttribL2dv;
/* 1937:     */  long glVertexAttribL3dv;
/* 1938:     */  long glVertexAttribL4dv;
/* 1939:     */  long glVertexAttribLPointer;
/* 1940:     */  long glGetVertexAttribLdv;
/* 1941:     */  long glViewportArrayv;
/* 1942:     */  long glViewportIndexedf;
/* 1943:     */  long glViewportIndexedfv;
/* 1944:     */  long glScissorArrayv;
/* 1945:     */  long glScissorIndexed;
/* 1946:     */  long glScissorIndexedv;
/* 1947:     */  long glDepthRangeArrayv;
/* 1948:     */  long glDepthRangeIndexed;
/* 1949:     */  long glGetFloati_v;
/* 1950:     */  long glGetDoublei_v;
/* 1951:     */  long glGetActiveAtomicCounterBufferiv;
/* 1952:     */  long glTexStorage1D;
/* 1953:     */  long glTexStorage2D;
/* 1954:     */  long glTexStorage3D;
/* 1955:     */  long glDrawTransformFeedbackInstanced;
/* 1956:     */  long glDrawTransformFeedbackStreamInstanced;
/* 1957:     */  long glDrawArraysInstancedBaseInstance;
/* 1958:     */  long glDrawElementsInstancedBaseInstance;
/* 1959:     */  long glDrawElementsInstancedBaseVertexBaseInstance;
/* 1960:     */  long glBindImageTexture;
/* 1961:     */  long glMemoryBarrier;
/* 1962:     */  long glGetInternalformativ;
/* 1963:     */  long glClearBufferData;
/* 1964:     */  long glClearBufferSubData;
/* 1965:     */  long glDispatchCompute;
/* 1966:     */  long glDispatchComputeIndirect;
/* 1967:     */  long glCopyImageSubData;
/* 1968:     */  long glDebugMessageControl;
/* 1969:     */  long glDebugMessageInsert;
/* 1970:     */  long glDebugMessageCallback;
/* 1971:     */  long glGetDebugMessageLog;
/* 1972:     */  long glPushDebugGroup;
/* 1973:     */  long glPopDebugGroup;
/* 1974:     */  long glObjectLabel;
/* 1975:     */  long glGetObjectLabel;
/* 1976:     */  long glObjectPtrLabel;
/* 1977:     */  long glGetObjectPtrLabel;
/* 1978:     */  long glFramebufferParameteri;
/* 1979:     */  long glGetFramebufferParameteriv;
/* 1980:     */  long glGetInternalformati64v;
/* 1981:     */  long glInvalidateTexSubImage;
/* 1982:     */  long glInvalidateTexImage;
/* 1983:     */  long glInvalidateBufferSubData;
/* 1984:     */  long glInvalidateBufferData;
/* 1985:     */  long glInvalidateFramebuffer;
/* 1986:     */  long glInvalidateSubFramebuffer;
/* 1987:     */  long glMultiDrawArraysIndirect;
/* 1988:     */  long glMultiDrawElementsIndirect;
/* 1989:     */  long glGetProgramInterfaceiv;
/* 1990:     */  long glGetProgramResourceIndex;
/* 1991:     */  long glGetProgramResourceName;
/* 1992:     */  long glGetProgramResourceiv;
/* 1993:     */  long glGetProgramResourceLocation;
/* 1994:     */  long glGetProgramResourceLocationIndex;
/* 1995:     */  long glShaderStorageBlockBinding;
/* 1996:     */  long glTexBufferRange;
/* 1997:     */  long glTexStorage2DMultisample;
/* 1998:     */  long glTexStorage3DMultisample;
/* 1999:     */  long glTextureView;
/* 2000:     */  long glBindVertexBuffer;
/* 2001:     */  long glVertexAttribFormat;
/* 2002:     */  long glVertexAttribIFormat;
/* 2003:     */  long glVertexAttribLFormat;
/* 2004:     */  long glVertexAttribBinding;
/* 2005:     */  long glVertexBindingDivisor;
/* 2006:     */  long glFrameTerminatorGREMEDY;
/* 2007:     */  long glStringMarkerGREMEDY;
/* 2008:     */  long glMapTexture2DINTEL;
/* 2009:     */  long glUnmapTexture2DINTEL;
/* 2010:     */  long glSyncTextureINTEL;
/* 2011:     */  long glGetTextureHandleNV;
/* 2012:     */  long glGetTextureSamplerHandleNV;
/* 2013:     */  long glMakeTextureHandleResidentNV;
/* 2014:     */  long glMakeTextureHandleNonResidentNV;
/* 2015:     */  long glGetImageHandleNV;
/* 2016:     */  long glMakeImageHandleResidentNV;
/* 2017:     */  long glMakeImageHandleNonResidentNV;
/* 2018:     */  long glUniformHandleui64NV;
/* 2019:     */  long glUniformHandleui64vNV;
/* 2020:     */  long glProgramUniformHandleui64NV;
/* 2021:     */  long glProgramUniformHandleui64vNV;
/* 2022:     */  long glIsTextureHandleResidentNV;
/* 2023:     */  long glIsImageHandleResidentNV;
/* 2024:     */  long glBeginConditionalRenderNV;
/* 2025:     */  long glEndConditionalRenderNV;
/* 2026:     */  long glCopyImageSubDataNV;
/* 2027:     */  long glDepthRangedNV;
/* 2028:     */  long glClearDepthdNV;
/* 2029:     */  long glDepthBoundsdNV;
/* 2030:     */  long glDrawTextureNV;
/* 2031:     */  long glGetMapControlPointsNV;
/* 2032:     */  long glMapControlPointsNV;
/* 2033:     */  long glMapParameterfvNV;
/* 2034:     */  long glMapParameterivNV;
/* 2035:     */  long glGetMapParameterfvNV;
/* 2036:     */  long glGetMapParameterivNV;
/* 2037:     */  long glGetMapAttribParameterfvNV;
/* 2038:     */  long glGetMapAttribParameterivNV;
/* 2039:     */  long glEvalMapsNV;
/* 2040:     */  long glGetMultisamplefvNV;
/* 2041:     */  long glSampleMaskIndexedNV;
/* 2042:     */  long glTexRenderbufferNV;
/* 2043:     */  long glGenFencesNV;
/* 2044:     */  long glDeleteFencesNV;
/* 2045:     */  long glSetFenceNV;
/* 2046:     */  long glTestFenceNV;
/* 2047:     */  long glFinishFenceNV;
/* 2048:     */  long glIsFenceNV;
/* 2049:     */  long glGetFenceivNV;
/* 2050:     */  long glProgramNamedParameter4fNV;
/* 2051:     */  long glProgramNamedParameter4dNV;
/* 2052:     */  long glGetProgramNamedParameterfvNV;
/* 2053:     */  long glGetProgramNamedParameterdvNV;
/* 2054:     */  long glRenderbufferStorageMultisampleCoverageNV;
/* 2055:     */  long glProgramVertexLimitNV;
/* 2056:     */  long glProgramLocalParameterI4iNV;
/* 2057:     */  long glProgramLocalParameterI4ivNV;
/* 2058:     */  long glProgramLocalParametersI4ivNV;
/* 2059:     */  long glProgramLocalParameterI4uiNV;
/* 2060:     */  long glProgramLocalParameterI4uivNV;
/* 2061:     */  long glProgramLocalParametersI4uivNV;
/* 2062:     */  long glProgramEnvParameterI4iNV;
/* 2063:     */  long glProgramEnvParameterI4ivNV;
/* 2064:     */  long glProgramEnvParametersI4ivNV;
/* 2065:     */  long glProgramEnvParameterI4uiNV;
/* 2066:     */  long glProgramEnvParameterI4uivNV;
/* 2067:     */  long glProgramEnvParametersI4uivNV;
/* 2068:     */  long glGetProgramLocalParameterIivNV;
/* 2069:     */  long glGetProgramLocalParameterIuivNV;
/* 2070:     */  long glGetProgramEnvParameterIivNV;
/* 2071:     */  long glGetProgramEnvParameterIuivNV;
/* 2072:     */  long glUniform1i64NV;
/* 2073:     */  long glUniform2i64NV;
/* 2074:     */  long glUniform3i64NV;
/* 2075:     */  long glUniform4i64NV;
/* 2076:     */  long glUniform1i64vNV;
/* 2077:     */  long glUniform2i64vNV;
/* 2078:     */  long glUniform3i64vNV;
/* 2079:     */  long glUniform4i64vNV;
/* 2080:     */  long glUniform1ui64NV;
/* 2081:     */  long glUniform2ui64NV;
/* 2082:     */  long glUniform3ui64NV;
/* 2083:     */  long glUniform4ui64NV;
/* 2084:     */  long glUniform1ui64vNV;
/* 2085:     */  long glUniform2ui64vNV;
/* 2086:     */  long glUniform3ui64vNV;
/* 2087:     */  long glUniform4ui64vNV;
/* 2088:     */  long glGetUniformi64vNV;
/* 2089:     */  long glGetUniformui64vNV;
/* 2090:     */  long glProgramUniform1i64NV;
/* 2091:     */  long glProgramUniform2i64NV;
/* 2092:     */  long glProgramUniform3i64NV;
/* 2093:     */  long glProgramUniform4i64NV;
/* 2094:     */  long glProgramUniform1i64vNV;
/* 2095:     */  long glProgramUniform2i64vNV;
/* 2096:     */  long glProgramUniform3i64vNV;
/* 2097:     */  long glProgramUniform4i64vNV;
/* 2098:     */  long glProgramUniform1ui64NV;
/* 2099:     */  long glProgramUniform2ui64NV;
/* 2100:     */  long glProgramUniform3ui64NV;
/* 2101:     */  long glProgramUniform4ui64NV;
/* 2102:     */  long glProgramUniform1ui64vNV;
/* 2103:     */  long glProgramUniform2ui64vNV;
/* 2104:     */  long glProgramUniform3ui64vNV;
/* 2105:     */  long glProgramUniform4ui64vNV;
/* 2106:     */  long glVertex2hNV;
/* 2107:     */  long glVertex3hNV;
/* 2108:     */  long glVertex4hNV;
/* 2109:     */  long glNormal3hNV;
/* 2110:     */  long glColor3hNV;
/* 2111:     */  long glColor4hNV;
/* 2112:     */  long glTexCoord1hNV;
/* 2113:     */  long glTexCoord2hNV;
/* 2114:     */  long glTexCoord3hNV;
/* 2115:     */  long glTexCoord4hNV;
/* 2116:     */  long glMultiTexCoord1hNV;
/* 2117:     */  long glMultiTexCoord2hNV;
/* 2118:     */  long glMultiTexCoord3hNV;
/* 2119:     */  long glMultiTexCoord4hNV;
/* 2120:     */  long glFogCoordhNV;
/* 2121:     */  long glSecondaryColor3hNV;
/* 2122:     */  long glVertexWeighthNV;
/* 2123:     */  long glVertexAttrib1hNV;
/* 2124:     */  long glVertexAttrib2hNV;
/* 2125:     */  long glVertexAttrib3hNV;
/* 2126:     */  long glVertexAttrib4hNV;
/* 2127:     */  long glVertexAttribs1hvNV;
/* 2128:     */  long glVertexAttribs2hvNV;
/* 2129:     */  long glVertexAttribs3hvNV;
/* 2130:     */  long glVertexAttribs4hvNV;
/* 2131:     */  long glGenOcclusionQueriesNV;
/* 2132:     */  long glDeleteOcclusionQueriesNV;
/* 2133:     */  long glIsOcclusionQueryNV;
/* 2134:     */  long glBeginOcclusionQueryNV;
/* 2135:     */  long glEndOcclusionQueryNV;
/* 2136:     */  long glGetOcclusionQueryuivNV;
/* 2137:     */  long glGetOcclusionQueryivNV;
/* 2138:     */  long glProgramBufferParametersfvNV;
/* 2139:     */  long glProgramBufferParametersIivNV;
/* 2140:     */  long glProgramBufferParametersIuivNV;
/* 2141:     */  long glPathCommandsNV;
/* 2142:     */  long glPathCoordsNV;
/* 2143:     */  long glPathSubCommandsNV;
/* 2144:     */  long glPathSubCoordsNV;
/* 2145:     */  long glPathStringNV;
/* 2146:     */  long glPathGlyphsNV;
/* 2147:     */  long glPathGlyphRangeNV;
/* 2148:     */  long glWeightPathsNV;
/* 2149:     */  long glCopyPathNV;
/* 2150:     */  long glInterpolatePathsNV;
/* 2151:     */  long glTransformPathNV;
/* 2152:     */  long glPathParameterivNV;
/* 2153:     */  long glPathParameteriNV;
/* 2154:     */  long glPathParameterfvNV;
/* 2155:     */  long glPathParameterfNV;
/* 2156:     */  long glPathDashArrayNV;
/* 2157:     */  long glGenPathsNV;
/* 2158:     */  long glDeletePathsNV;
/* 2159:     */  long glIsPathNV;
/* 2160:     */  long glPathStencilFuncNV;
/* 2161:     */  long glPathStencilDepthOffsetNV;
/* 2162:     */  long glStencilFillPathNV;
/* 2163:     */  long glStencilStrokePathNV;
/* 2164:     */  long glStencilFillPathInstancedNV;
/* 2165:     */  long glStencilStrokePathInstancedNV;
/* 2166:     */  long glPathCoverDepthFuncNV;
/* 2167:     */  long glPathColorGenNV;
/* 2168:     */  long glPathTexGenNV;
/* 2169:     */  long glPathFogGenNV;
/* 2170:     */  long glCoverFillPathNV;
/* 2171:     */  long glCoverStrokePathNV;
/* 2172:     */  long glCoverFillPathInstancedNV;
/* 2173:     */  long glCoverStrokePathInstancedNV;
/* 2174:     */  long glGetPathParameterivNV;
/* 2175:     */  long glGetPathParameterfvNV;
/* 2176:     */  long glGetPathCommandsNV;
/* 2177:     */  long glGetPathCoordsNV;
/* 2178:     */  long glGetPathDashArrayNV;
/* 2179:     */  long glGetPathMetricsNV;
/* 2180:     */  long glGetPathMetricRangeNV;
/* 2181:     */  long glGetPathSpacingNV;
/* 2182:     */  long glGetPathColorGenivNV;
/* 2183:     */  long glGetPathColorGenfvNV;
/* 2184:     */  long glGetPathTexGenivNV;
/* 2185:     */  long glGetPathTexGenfvNV;
/* 2186:     */  long glIsPointInFillPathNV;
/* 2187:     */  long glIsPointInStrokePathNV;
/* 2188:     */  long glGetPathLengthNV;
/* 2189:     */  long glPointAlongPathNV;
/* 2190:     */  long glPixelDataRangeNV;
/* 2191:     */  long glFlushPixelDataRangeNV;
/* 2192:     */  long glPointParameteriNV;
/* 2193:     */  long glPointParameterivNV;
/* 2194:     */  long glPresentFrameKeyedNV;
/* 2195:     */  long glPresentFrameDualFillNV;
/* 2196:     */  long glGetVideoivNV;
/* 2197:     */  long glGetVideouivNV;
/* 2198:     */  long glGetVideoi64vNV;
/* 2199:     */  long glGetVideoui64vNV;
/* 2200:     */  long glPrimitiveRestartNV;
/* 2201:     */  long glPrimitiveRestartIndexNV;
/* 2202:     */  long glLoadProgramNV;
/* 2203:     */  long glBindProgramNV;
/* 2204:     */  long glDeleteProgramsNV;
/* 2205:     */  long glGenProgramsNV;
/* 2206:     */  long glGetProgramivNV;
/* 2207:     */  long glGetProgramStringNV;
/* 2208:     */  long glIsProgramNV;
/* 2209:     */  long glAreProgramsResidentNV;
/* 2210:     */  long glRequestResidentProgramsNV;
/* 2211:     */  long glCombinerParameterfNV;
/* 2212:     */  long glCombinerParameterfvNV;
/* 2213:     */  long glCombinerParameteriNV;
/* 2214:     */  long glCombinerParameterivNV;
/* 2215:     */  long glCombinerInputNV;
/* 2216:     */  long glCombinerOutputNV;
/* 2217:     */  long glFinalCombinerInputNV;
/* 2218:     */  long glGetCombinerInputParameterfvNV;
/* 2219:     */  long glGetCombinerInputParameterivNV;
/* 2220:     */  long glGetCombinerOutputParameterfvNV;
/* 2221:     */  long glGetCombinerOutputParameterivNV;
/* 2222:     */  long glGetFinalCombinerInputParameterfvNV;
/* 2223:     */  long glGetFinalCombinerInputParameterivNV;
/* 2224:     */  long glCombinerStageParameterfvNV;
/* 2225:     */  long glGetCombinerStageParameterfvNV;
/* 2226:     */  long glMakeBufferResidentNV;
/* 2227:     */  long glMakeBufferNonResidentNV;
/* 2228:     */  long glIsBufferResidentNV;
/* 2229:     */  long glMakeNamedBufferResidentNV;
/* 2230:     */  long glMakeNamedBufferNonResidentNV;
/* 2231:     */  long glIsNamedBufferResidentNV;
/* 2232:     */  long glGetBufferParameterui64vNV;
/* 2233:     */  long glGetNamedBufferParameterui64vNV;
/* 2234:     */  long glGetIntegerui64vNV;
/* 2235:     */  long glUniformui64NV;
/* 2236:     */  long glUniformui64vNV;
/* 2237:     */  long glProgramUniformui64NV;
/* 2238:     */  long glProgramUniformui64vNV;
/* 2239:     */  long glTextureBarrierNV;
/* 2240:     */  long glTexImage2DMultisampleCoverageNV;
/* 2241:     */  long glTexImage3DMultisampleCoverageNV;
/* 2242:     */  long glTextureImage2DMultisampleNV;
/* 2243:     */  long glTextureImage3DMultisampleNV;
/* 2244:     */  long glTextureImage2DMultisampleCoverageNV;
/* 2245:     */  long glTextureImage3DMultisampleCoverageNV;
/* 2246:     */  long glBindBufferRangeNV;
/* 2247:     */  long glBindBufferOffsetNV;
/* 2248:     */  long glBindBufferBaseNV;
/* 2249:     */  long glTransformFeedbackAttribsNV;
/* 2250:     */  long glTransformFeedbackVaryingsNV;
/* 2251:     */  long glBeginTransformFeedbackNV;
/* 2252:     */  long glEndTransformFeedbackNV;
/* 2253:     */  long glGetVaryingLocationNV;
/* 2254:     */  long glGetActiveVaryingNV;
/* 2255:     */  long glActiveVaryingNV;
/* 2256:     */  long glGetTransformFeedbackVaryingNV;
/* 2257:     */  long glBindTransformFeedbackNV;
/* 2258:     */  long glDeleteTransformFeedbacksNV;
/* 2259:     */  long glGenTransformFeedbacksNV;
/* 2260:     */  long glIsTransformFeedbackNV;
/* 2261:     */  long glPauseTransformFeedbackNV;
/* 2262:     */  long glResumeTransformFeedbackNV;
/* 2263:     */  long glDrawTransformFeedbackNV;
/* 2264:     */  long glVertexArrayRangeNV;
/* 2265:     */  long glFlushVertexArrayRangeNV;
/* 2266:     */  long glAllocateMemoryNV;
/* 2267:     */  long glFreeMemoryNV;
/* 2268:     */  long glVertexAttribL1i64NV;
/* 2269:     */  long glVertexAttribL2i64NV;
/* 2270:     */  long glVertexAttribL3i64NV;
/* 2271:     */  long glVertexAttribL4i64NV;
/* 2272:     */  long glVertexAttribL1i64vNV;
/* 2273:     */  long glVertexAttribL2i64vNV;
/* 2274:     */  long glVertexAttribL3i64vNV;
/* 2275:     */  long glVertexAttribL4i64vNV;
/* 2276:     */  long glVertexAttribL1ui64NV;
/* 2277:     */  long glVertexAttribL2ui64NV;
/* 2278:     */  long glVertexAttribL3ui64NV;
/* 2279:     */  long glVertexAttribL4ui64NV;
/* 2280:     */  long glVertexAttribL1ui64vNV;
/* 2281:     */  long glVertexAttribL2ui64vNV;
/* 2282:     */  long glVertexAttribL3ui64vNV;
/* 2283:     */  long glVertexAttribL4ui64vNV;
/* 2284:     */  long glGetVertexAttribLi64vNV;
/* 2285:     */  long glGetVertexAttribLui64vNV;
/* 2286:     */  long glVertexAttribLFormatNV;
/* 2287:     */  long glBufferAddressRangeNV;
/* 2288:     */  long glVertexFormatNV;
/* 2289:     */  long glNormalFormatNV;
/* 2290:     */  long glColorFormatNV;
/* 2291:     */  long glIndexFormatNV;
/* 2292:     */  long glTexCoordFormatNV;
/* 2293:     */  long glEdgeFlagFormatNV;
/* 2294:     */  long glSecondaryColorFormatNV;
/* 2295:     */  long glFogCoordFormatNV;
/* 2296:     */  long glVertexAttribFormatNV;
/* 2297:     */  long glVertexAttribIFormatNV;
/* 2298:     */  long glGetIntegerui64i_vNV;
/* 2299:     */  long glExecuteProgramNV;
/* 2300:     */  long glGetProgramParameterfvNV;
/* 2301:     */  long glGetProgramParameterdvNV;
/* 2302:     */  long glGetTrackMatrixivNV;
/* 2303:     */  long glGetVertexAttribfvNV;
/* 2304:     */  long glGetVertexAttribdvNV;
/* 2305:     */  long glGetVertexAttribivNV;
/* 2306:     */  long glGetVertexAttribPointervNV;
/* 2307:     */  long glProgramParameter4fNV;
/* 2308:     */  long glProgramParameter4dNV;
/* 2309:     */  long glProgramParameters4fvNV;
/* 2310:     */  long glProgramParameters4dvNV;
/* 2311:     */  long glTrackMatrixNV;
/* 2312:     */  long glVertexAttribPointerNV;
/* 2313:     */  long glVertexAttrib1sNV;
/* 2314:     */  long glVertexAttrib1fNV;
/* 2315:     */  long glVertexAttrib1dNV;
/* 2316:     */  long glVertexAttrib2sNV;
/* 2317:     */  long glVertexAttrib2fNV;
/* 2318:     */  long glVertexAttrib2dNV;
/* 2319:     */  long glVertexAttrib3sNV;
/* 2320:     */  long glVertexAttrib3fNV;
/* 2321:     */  long glVertexAttrib3dNV;
/* 2322:     */  long glVertexAttrib4sNV;
/* 2323:     */  long glVertexAttrib4fNV;
/* 2324:     */  long glVertexAttrib4dNV;
/* 2325:     */  long glVertexAttrib4ubNV;
/* 2326:     */  long glVertexAttribs1svNV;
/* 2327:     */  long glVertexAttribs1fvNV;
/* 2328:     */  long glVertexAttribs1dvNV;
/* 2329:     */  long glVertexAttribs2svNV;
/* 2330:     */  long glVertexAttribs2fvNV;
/* 2331:     */  long glVertexAttribs2dvNV;
/* 2332:     */  long glVertexAttribs3svNV;
/* 2333:     */  long glVertexAttribs3fvNV;
/* 2334:     */  long glVertexAttribs3dvNV;
/* 2335:     */  long glVertexAttribs4svNV;
/* 2336:     */  long glVertexAttribs4fvNV;
/* 2337:     */  long glVertexAttribs4dvNV;
/* 2338:     */  long glBeginVideoCaptureNV;
/* 2339:     */  long glBindVideoCaptureStreamBufferNV;
/* 2340:     */  long glBindVideoCaptureStreamTextureNV;
/* 2341:     */  long glEndVideoCaptureNV;
/* 2342:     */  long glGetVideoCaptureivNV;
/* 2343:     */  long glGetVideoCaptureStreamivNV;
/* 2344:     */  long glGetVideoCaptureStreamfvNV;
/* 2345:     */  long glGetVideoCaptureStreamdvNV;
/* 2346:     */  long glVideoCaptureNV;
/* 2347:     */  long glVideoCaptureStreamParameterivNV;
/* 2348:     */  long glVideoCaptureStreamParameterfvNV;
/* 2349:     */  long glVideoCaptureStreamParameterdvNV;
/* 2350:     */  
/* 2351:     */  private boolean AMD_debug_output_initNativeFunctionAddresses()
/* 2352:     */  {
/* 2353:2353 */    return ((this.glDebugMessageEnableAMD = GLContext.getFunctionAddress(new String[] { "glDebugMessageEnableAMD", "glDebugMessageEnableAMDX" })) != 0L ? 1 : 0) & ((this.glDebugMessageInsertAMD = GLContext.getFunctionAddress(new String[] { "glDebugMessageInsertAMD", "glDebugMessageInsertAMDX" })) != 0L ? 1 : 0) & ((this.glDebugMessageCallbackAMD = GLContext.getFunctionAddress(new String[] { "glDebugMessageCallbackAMD", "glDebugMessageCallbackAMDX" })) != 0L ? 1 : 0) & ((this.glGetDebugMessageLogAMD = GLContext.getFunctionAddress(new String[] { "glGetDebugMessageLogAMD", "glGetDebugMessageLogAMDX" })) != 0L ? 1 : 0);
/* 2354:     */  }
/* 2355:     */  
/* 2359:     */  private boolean AMD_draw_buffers_blend_initNativeFunctionAddresses()
/* 2360:     */  {
/* 2361:2361 */    return ((this.glBlendFuncIndexedAMD = GLContext.getFunctionAddress("glBlendFuncIndexedAMD")) != 0L ? 1 : 0) & ((this.glBlendFuncSeparateIndexedAMD = GLContext.getFunctionAddress("glBlendFuncSeparateIndexedAMD")) != 0L ? 1 : 0) & ((this.glBlendEquationIndexedAMD = GLContext.getFunctionAddress("glBlendEquationIndexedAMD")) != 0L ? 1 : 0) & ((this.glBlendEquationSeparateIndexedAMD = GLContext.getFunctionAddress("glBlendEquationSeparateIndexedAMD")) != 0L ? 1 : 0);
/* 2362:     */  }
/* 2363:     */  
/* 2367:     */  private boolean AMD_multi_draw_indirect_initNativeFunctionAddresses()
/* 2368:     */  {
/* 2369:2369 */    return ((this.glMultiDrawArraysIndirectAMD = GLContext.getFunctionAddress("glMultiDrawArraysIndirectAMD")) != 0L ? 1 : 0) & ((this.glMultiDrawElementsIndirectAMD = GLContext.getFunctionAddress("glMultiDrawElementsIndirectAMD")) != 0L ? 1 : 0);
/* 2370:     */  }
/* 2371:     */  
/* 2373:     */  private boolean AMD_name_gen_delete_initNativeFunctionAddresses()
/* 2374:     */  {
/* 2375:2375 */    return ((this.glGenNamesAMD = GLContext.getFunctionAddress("glGenNamesAMD")) != 0L ? 1 : 0) & ((this.glDeleteNamesAMD = GLContext.getFunctionAddress("glDeleteNamesAMD")) != 0L ? 1 : 0) & ((this.glIsNameAMD = GLContext.getFunctionAddress("glIsNameAMD")) != 0L ? 1 : 0);
/* 2376:     */  }
/* 2377:     */  
/* 2380:     */  private boolean AMD_performance_monitor_initNativeFunctionAddresses()
/* 2381:     */  {
/* 2382:2382 */    return ((this.glGetPerfMonitorGroupsAMD = GLContext.getFunctionAddress("glGetPerfMonitorGroupsAMD")) != 0L ? 1 : 0) & ((this.glGetPerfMonitorCountersAMD = GLContext.getFunctionAddress("glGetPerfMonitorCountersAMD")) != 0L ? 1 : 0) & ((this.glGetPerfMonitorGroupStringAMD = GLContext.getFunctionAddress("glGetPerfMonitorGroupStringAMD")) != 0L ? 1 : 0) & ((this.glGetPerfMonitorCounterStringAMD = GLContext.getFunctionAddress("glGetPerfMonitorCounterStringAMD")) != 0L ? 1 : 0) & ((this.glGetPerfMonitorCounterInfoAMD = GLContext.getFunctionAddress("glGetPerfMonitorCounterInfoAMD")) != 0L ? 1 : 0) & ((this.glGenPerfMonitorsAMD = GLContext.getFunctionAddress("glGenPerfMonitorsAMD")) != 0L ? 1 : 0) & ((this.glDeletePerfMonitorsAMD = GLContext.getFunctionAddress("glDeletePerfMonitorsAMD")) != 0L ? 1 : 0) & ((this.glSelectPerfMonitorCountersAMD = GLContext.getFunctionAddress("glSelectPerfMonitorCountersAMD")) != 0L ? 1 : 0) & ((this.glBeginPerfMonitorAMD = GLContext.getFunctionAddress("glBeginPerfMonitorAMD")) != 0L ? 1 : 0) & ((this.glEndPerfMonitorAMD = GLContext.getFunctionAddress("glEndPerfMonitorAMD")) != 0L ? 1 : 0) & ((this.glGetPerfMonitorCounterDataAMD = GLContext.getFunctionAddress("glGetPerfMonitorCounterDataAMD")) != 0L ? 1 : 0);
/* 2383:     */  }
/* 2384:     */  
/* 2395:     */  private boolean AMD_sample_positions_initNativeFunctionAddresses()
/* 2396:     */  {
/* 2397:2397 */    return (this.glSetMultisamplefvAMD = GLContext.getFunctionAddress("glSetMultisamplefvAMD")) != 0L;
/* 2398:     */  }
/* 2399:     */  
/* 2400:     */  private boolean AMD_sparse_texture_initNativeFunctionAddresses()
/* 2401:     */  {
/* 2402:2402 */    return ((this.glTexStorageSparseAMD = GLContext.getFunctionAddress("glTexStorageSparseAMD")) != 0L ? 1 : 0) & ((this.glTextureStorageSparseAMD = GLContext.getFunctionAddress("glTextureStorageSparseAMD")) != 0L ? 1 : 0);
/* 2403:     */  }
/* 2404:     */  
/* 2406:     */  private boolean AMD_stencil_operation_extended_initNativeFunctionAddresses()
/* 2407:     */  {
/* 2408:2408 */    return (this.glStencilOpValueAMD = GLContext.getFunctionAddress("glStencilOpValueAMD")) != 0L;
/* 2409:     */  }
/* 2410:     */  
/* 2411:     */  private boolean AMD_vertex_shader_tessellator_initNativeFunctionAddresses()
/* 2412:     */  {
/* 2413:2413 */    return ((this.glTessellationFactorAMD = GLContext.getFunctionAddress("glTessellationFactorAMD")) != 0L ? 1 : 0) & ((this.glTessellationModeAMD = GLContext.getFunctionAddress("glTessellationModeAMD")) != 0L ? 1 : 0);
/* 2414:     */  }
/* 2415:     */  
/* 2417:     */  private boolean APPLE_element_array_initNativeFunctionAddresses()
/* 2418:     */  {
/* 2419:2419 */    return ((this.glElementPointerAPPLE = GLContext.getFunctionAddress("glElementPointerAPPLE")) != 0L ? 1 : 0) & ((this.glDrawElementArrayAPPLE = GLContext.getFunctionAddress("glDrawElementArrayAPPLE")) != 0L ? 1 : 0) & ((this.glDrawRangeElementArrayAPPLE = GLContext.getFunctionAddress("glDrawRangeElementArrayAPPLE")) != 0L ? 1 : 0) & ((this.glMultiDrawElementArrayAPPLE = GLContext.getFunctionAddress("glMultiDrawElementArrayAPPLE")) != 0L ? 1 : 0) & ((this.glMultiDrawRangeElementArrayAPPLE = GLContext.getFunctionAddress("glMultiDrawRangeElementArrayAPPLE")) != 0L ? 1 : 0);
/* 2420:     */  }
/* 2421:     */  
/* 2426:     */  private boolean APPLE_fence_initNativeFunctionAddresses()
/* 2427:     */  {
/* 2428:2428 */    return ((this.glGenFencesAPPLE = GLContext.getFunctionAddress("glGenFencesAPPLE")) != 0L ? 1 : 0) & ((this.glDeleteFencesAPPLE = GLContext.getFunctionAddress("glDeleteFencesAPPLE")) != 0L ? 1 : 0) & ((this.glSetFenceAPPLE = GLContext.getFunctionAddress("glSetFenceAPPLE")) != 0L ? 1 : 0) & ((this.glIsFenceAPPLE = GLContext.getFunctionAddress("glIsFenceAPPLE")) != 0L ? 1 : 0) & ((this.glTestFenceAPPLE = GLContext.getFunctionAddress("glTestFenceAPPLE")) != 0L ? 1 : 0) & ((this.glFinishFenceAPPLE = GLContext.getFunctionAddress("glFinishFenceAPPLE")) != 0L ? 1 : 0) & ((this.glTestObjectAPPLE = GLContext.getFunctionAddress("glTestObjectAPPLE")) != 0L ? 1 : 0) & ((this.glFinishObjectAPPLE = GLContext.getFunctionAddress("glFinishObjectAPPLE")) != 0L ? 1 : 0);
/* 2429:     */  }
/* 2430:     */  
/* 2438:     */  private boolean APPLE_flush_buffer_range_initNativeFunctionAddresses()
/* 2439:     */  {
/* 2440:2440 */    return ((this.glBufferParameteriAPPLE = GLContext.getFunctionAddress("glBufferParameteriAPPLE")) != 0L ? 1 : 0) & ((this.glFlushMappedBufferRangeAPPLE = GLContext.getFunctionAddress("glFlushMappedBufferRangeAPPLE")) != 0L ? 1 : 0);
/* 2441:     */  }
/* 2442:     */  
/* 2444:     */  private boolean APPLE_object_purgeable_initNativeFunctionAddresses()
/* 2445:     */  {
/* 2446:2446 */    return ((this.glObjectPurgeableAPPLE = GLContext.getFunctionAddress("glObjectPurgeableAPPLE")) != 0L ? 1 : 0) & ((this.glObjectUnpurgeableAPPLE = GLContext.getFunctionAddress("glObjectUnpurgeableAPPLE")) != 0L ? 1 : 0) & ((this.glGetObjectParameterivAPPLE = GLContext.getFunctionAddress("glGetObjectParameterivAPPLE")) != 0L ? 1 : 0);
/* 2447:     */  }
/* 2448:     */  
/* 2451:     */  private boolean APPLE_texture_range_initNativeFunctionAddresses()
/* 2452:     */  {
/* 2453:2453 */    return ((this.glTextureRangeAPPLE = GLContext.getFunctionAddress("glTextureRangeAPPLE")) != 0L ? 1 : 0) & ((this.glGetTexParameterPointervAPPLE = GLContext.getFunctionAddress("glGetTexParameterPointervAPPLE")) != 0L ? 1 : 0);
/* 2454:     */  }
/* 2455:     */  
/* 2457:     */  private boolean APPLE_vertex_array_object_initNativeFunctionAddresses()
/* 2458:     */  {
/* 2459:2459 */    return ((this.glBindVertexArrayAPPLE = GLContext.getFunctionAddress("glBindVertexArrayAPPLE")) != 0L ? 1 : 0) & ((this.glDeleteVertexArraysAPPLE = GLContext.getFunctionAddress("glDeleteVertexArraysAPPLE")) != 0L ? 1 : 0) & ((this.glGenVertexArraysAPPLE = GLContext.getFunctionAddress("glGenVertexArraysAPPLE")) != 0L ? 1 : 0) & ((this.glIsVertexArrayAPPLE = GLContext.getFunctionAddress("glIsVertexArrayAPPLE")) != 0L ? 1 : 0);
/* 2460:     */  }
/* 2461:     */  
/* 2465:     */  private boolean APPLE_vertex_array_range_initNativeFunctionAddresses()
/* 2466:     */  {
/* 2467:2467 */    return ((this.glVertexArrayRangeAPPLE = GLContext.getFunctionAddress("glVertexArrayRangeAPPLE")) != 0L ? 1 : 0) & ((this.glFlushVertexArrayRangeAPPLE = GLContext.getFunctionAddress("glFlushVertexArrayRangeAPPLE")) != 0L ? 1 : 0) & ((this.glVertexArrayParameteriAPPLE = GLContext.getFunctionAddress("glVertexArrayParameteriAPPLE")) != 0L ? 1 : 0);
/* 2468:     */  }
/* 2469:     */  
/* 2472:     */  private boolean APPLE_vertex_program_evaluators_initNativeFunctionAddresses()
/* 2473:     */  {
/* 2474:2474 */    return ((this.glEnableVertexAttribAPPLE = GLContext.getFunctionAddress("glEnableVertexAttribAPPLE")) != 0L ? 1 : 0) & ((this.glDisableVertexAttribAPPLE = GLContext.getFunctionAddress("glDisableVertexAttribAPPLE")) != 0L ? 1 : 0) & ((this.glIsVertexAttribEnabledAPPLE = GLContext.getFunctionAddress("glIsVertexAttribEnabledAPPLE")) != 0L ? 1 : 0) & ((this.glMapVertexAttrib1dAPPLE = GLContext.getFunctionAddress("glMapVertexAttrib1dAPPLE")) != 0L ? 1 : 0) & ((this.glMapVertexAttrib1fAPPLE = GLContext.getFunctionAddress("glMapVertexAttrib1fAPPLE")) != 0L ? 1 : 0) & ((this.glMapVertexAttrib2dAPPLE = GLContext.getFunctionAddress("glMapVertexAttrib2dAPPLE")) != 0L ? 1 : 0) & ((this.glMapVertexAttrib2fAPPLE = GLContext.getFunctionAddress("glMapVertexAttrib2fAPPLE")) != 0L ? 1 : 0);
/* 2475:     */  }
/* 2476:     */  
/* 2483:     */  private boolean ARB_ES2_compatibility_initNativeFunctionAddresses()
/* 2484:     */  {
/* 2485:2485 */    return ((this.glReleaseShaderCompiler = GLContext.getFunctionAddress("glReleaseShaderCompiler")) != 0L ? 1 : 0) & ((this.glShaderBinary = GLContext.getFunctionAddress("glShaderBinary")) != 0L ? 1 : 0) & ((this.glGetShaderPrecisionFormat = GLContext.getFunctionAddress("glGetShaderPrecisionFormat")) != 0L ? 1 : 0) & ((this.glDepthRangef = GLContext.getFunctionAddress("glDepthRangef")) != 0L ? 1 : 0) & ((this.glClearDepthf = GLContext.getFunctionAddress("glClearDepthf")) != 0L ? 1 : 0);
/* 2486:     */  }
/* 2487:     */  
/* 2492:     */  private boolean ARB_base_instance_initNativeFunctionAddresses()
/* 2493:     */  {
/* 2494:2494 */    return ((this.glDrawArraysInstancedBaseInstance = GLContext.getFunctionAddress("glDrawArraysInstancedBaseInstance")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedBaseInstance = GLContext.getFunctionAddress("glDrawElementsInstancedBaseInstance")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedBaseVertexBaseInstance = GLContext.getFunctionAddress("glDrawElementsInstancedBaseVertexBaseInstance")) != 0L ? 1 : 0);
/* 2495:     */  }
/* 2496:     */  
/* 2499:     */  private boolean ARB_blend_func_extended_initNativeFunctionAddresses()
/* 2500:     */  {
/* 2501:2501 */    return ((this.glBindFragDataLocationIndexed = GLContext.getFunctionAddress("glBindFragDataLocationIndexed")) != 0L ? 1 : 0) & ((this.glGetFragDataIndex = GLContext.getFunctionAddress("glGetFragDataIndex")) != 0L ? 1 : 0);
/* 2502:     */  }
/* 2503:     */  
/* 2505:     */  private boolean ARB_buffer_object_initNativeFunctionAddresses()
/* 2506:     */  {
/* 2507:2507 */    return ((this.glBindBufferARB = GLContext.getFunctionAddress("glBindBufferARB")) != 0L ? 1 : 0) & ((this.glDeleteBuffersARB = GLContext.getFunctionAddress("glDeleteBuffersARB")) != 0L ? 1 : 0) & ((this.glGenBuffersARB = GLContext.getFunctionAddress("glGenBuffersARB")) != 0L ? 1 : 0) & ((this.glIsBufferARB = GLContext.getFunctionAddress("glIsBufferARB")) != 0L ? 1 : 0) & ((this.glBufferDataARB = GLContext.getFunctionAddress("glBufferDataARB")) != 0L ? 1 : 0) & ((this.glBufferSubDataARB = GLContext.getFunctionAddress("glBufferSubDataARB")) != 0L ? 1 : 0) & ((this.glGetBufferSubDataARB = GLContext.getFunctionAddress("glGetBufferSubDataARB")) != 0L ? 1 : 0) & ((this.glMapBufferARB = GLContext.getFunctionAddress("glMapBufferARB")) != 0L ? 1 : 0) & ((this.glUnmapBufferARB = GLContext.getFunctionAddress("glUnmapBufferARB")) != 0L ? 1 : 0) & ((this.glGetBufferParameterivARB = GLContext.getFunctionAddress("glGetBufferParameterivARB")) != 0L ? 1 : 0) & ((this.glGetBufferPointervARB = GLContext.getFunctionAddress("glGetBufferPointervARB")) != 0L ? 1 : 0);
/* 2508:     */  }
/* 2509:     */  
/* 2520:     */  private boolean ARB_cl_event_initNativeFunctionAddresses()
/* 2521:     */  {
/* 2522:2522 */    return (this.glCreateSyncFromCLeventARB = GLContext.getFunctionAddress("glCreateSyncFromCLeventARB")) != 0L;
/* 2523:     */  }
/* 2524:     */  
/* 2525:     */  private boolean ARB_clear_buffer_object_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 2526:     */  {
/* 2527:2527 */    return ((this.glClearBufferData = GLContext.getFunctionAddress("glClearBufferData")) != 0L ? 1 : 0) & ((this.glClearBufferSubData = GLContext.getFunctionAddress("glClearBufferSubData")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glClearNamedBufferDataEXT = GLContext.getFunctionAddress("glClearNamedBufferDataEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glClearNamedBufferSubDataEXT = GLContext.getFunctionAddress("glClearNamedBufferSubDataEXT")) != 0L) ? 1 : 0);
/* 2528:     */  }
/* 2529:     */  
/* 2533:     */  private boolean ARB_color_buffer_float_initNativeFunctionAddresses()
/* 2534:     */  {
/* 2535:2535 */    return (this.glClampColorARB = GLContext.getFunctionAddress("glClampColorARB")) != 0L;
/* 2536:     */  }
/* 2537:     */  
/* 2538:     */  private boolean ARB_compute_shader_initNativeFunctionAddresses()
/* 2539:     */  {
/* 2540:2540 */    return ((this.glDispatchCompute = GLContext.getFunctionAddress("glDispatchCompute")) != 0L ? 1 : 0) & ((this.glDispatchComputeIndirect = GLContext.getFunctionAddress("glDispatchComputeIndirect")) != 0L ? 1 : 0);
/* 2541:     */  }
/* 2542:     */  
/* 2544:     */  private boolean ARB_copy_buffer_initNativeFunctionAddresses()
/* 2545:     */  {
/* 2546:2546 */    return (this.glCopyBufferSubData = GLContext.getFunctionAddress("glCopyBufferSubData")) != 0L;
/* 2547:     */  }
/* 2548:     */  
/* 2549:     */  private boolean ARB_copy_image_initNativeFunctionAddresses()
/* 2550:     */  {
/* 2551:2551 */    return (this.glCopyImageSubData = GLContext.getFunctionAddress("glCopyImageSubData")) != 0L;
/* 2552:     */  }
/* 2553:     */  
/* 2554:     */  private boolean ARB_debug_output_initNativeFunctionAddresses()
/* 2555:     */  {
/* 2556:2556 */    return ((this.glDebugMessageControlARB = GLContext.getFunctionAddress("glDebugMessageControlARB")) != 0L ? 1 : 0) & ((this.glDebugMessageInsertARB = GLContext.getFunctionAddress("glDebugMessageInsertARB")) != 0L ? 1 : 0) & ((this.glDebugMessageCallbackARB = GLContext.getFunctionAddress("glDebugMessageCallbackARB")) != 0L ? 1 : 0) & ((this.glGetDebugMessageLogARB = GLContext.getFunctionAddress("glGetDebugMessageLogARB")) != 0L ? 1 : 0);
/* 2557:     */  }
/* 2558:     */  
/* 2562:     */  private boolean ARB_draw_buffers_initNativeFunctionAddresses()
/* 2563:     */  {
/* 2564:2564 */    return (this.glDrawBuffersARB = GLContext.getFunctionAddress("glDrawBuffersARB")) != 0L;
/* 2565:     */  }
/* 2566:     */  
/* 2567:     */  private boolean ARB_draw_buffers_blend_initNativeFunctionAddresses()
/* 2568:     */  {
/* 2569:2569 */    return ((this.glBlendEquationiARB = GLContext.getFunctionAddress("glBlendEquationiARB")) != 0L ? 1 : 0) & ((this.glBlendEquationSeparateiARB = GLContext.getFunctionAddress("glBlendEquationSeparateiARB")) != 0L ? 1 : 0) & ((this.glBlendFunciARB = GLContext.getFunctionAddress("glBlendFunciARB")) != 0L ? 1 : 0) & ((this.glBlendFuncSeparateiARB = GLContext.getFunctionAddress("glBlendFuncSeparateiARB")) != 0L ? 1 : 0);
/* 2570:     */  }
/* 2571:     */  
/* 2575:     */  private boolean ARB_draw_elements_base_vertex_initNativeFunctionAddresses()
/* 2576:     */  {
/* 2577:2577 */    return ((this.glDrawElementsBaseVertex = GLContext.getFunctionAddress("glDrawElementsBaseVertex")) != 0L ? 1 : 0) & ((this.glDrawRangeElementsBaseVertex = GLContext.getFunctionAddress("glDrawRangeElementsBaseVertex")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedBaseVertex = GLContext.getFunctionAddress("glDrawElementsInstancedBaseVertex")) != 0L ? 1 : 0);
/* 2578:     */  }
/* 2579:     */  
/* 2582:     */  private boolean ARB_draw_indirect_initNativeFunctionAddresses()
/* 2583:     */  {
/* 2584:2584 */    return ((this.glDrawArraysIndirect = GLContext.getFunctionAddress("glDrawArraysIndirect")) != 0L ? 1 : 0) & ((this.glDrawElementsIndirect = GLContext.getFunctionAddress("glDrawElementsIndirect")) != 0L ? 1 : 0);
/* 2585:     */  }
/* 2586:     */  
/* 2588:     */  private boolean ARB_draw_instanced_initNativeFunctionAddresses()
/* 2589:     */  {
/* 2590:2590 */    return ((this.glDrawArraysInstancedARB = GLContext.getFunctionAddress("glDrawArraysInstancedARB")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedARB = GLContext.getFunctionAddress("glDrawElementsInstancedARB")) != 0L ? 1 : 0);
/* 2591:     */  }
/* 2592:     */  
/* 2594:     */  private boolean ARB_framebuffer_no_attachments_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 2595:     */  {
/* 2596:2596 */    return ((this.glFramebufferParameteri = GLContext.getFunctionAddress("glFramebufferParameteri")) != 0L ? 1 : 0) & ((this.glGetFramebufferParameteriv = GLContext.getFunctionAddress("glGetFramebufferParameteriv")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glNamedFramebufferParameteriEXT = GLContext.getFunctionAddress("glNamedFramebufferParameteriEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glGetNamedFramebufferParameterivEXT = GLContext.getFunctionAddress("glGetNamedFramebufferParameterivEXT")) != 0L) ? 1 : 0);
/* 2597:     */  }
/* 2598:     */  
/* 2602:     */  private boolean ARB_framebuffer_object_initNativeFunctionAddresses()
/* 2603:     */  {
/* 2604:2604 */    return ((this.glIsRenderbuffer = GLContext.getFunctionAddress("glIsRenderbuffer")) != 0L ? 1 : 0) & ((this.glBindRenderbuffer = GLContext.getFunctionAddress("glBindRenderbuffer")) != 0L ? 1 : 0) & ((this.glDeleteRenderbuffers = GLContext.getFunctionAddress("glDeleteRenderbuffers")) != 0L ? 1 : 0) & ((this.glGenRenderbuffers = GLContext.getFunctionAddress("glGenRenderbuffers")) != 0L ? 1 : 0) & ((this.glRenderbufferStorage = GLContext.getFunctionAddress("glRenderbufferStorage")) != 0L ? 1 : 0) & ((this.glRenderbufferStorageMultisample = GLContext.getFunctionAddress("glRenderbufferStorageMultisample")) != 0L ? 1 : 0) & ((this.glGetRenderbufferParameteriv = GLContext.getFunctionAddress("glGetRenderbufferParameteriv")) != 0L ? 1 : 0) & ((this.glIsFramebuffer = GLContext.getFunctionAddress("glIsFramebuffer")) != 0L ? 1 : 0) & ((this.glBindFramebuffer = GLContext.getFunctionAddress("glBindFramebuffer")) != 0L ? 1 : 0) & ((this.glDeleteFramebuffers = GLContext.getFunctionAddress("glDeleteFramebuffers")) != 0L ? 1 : 0) & ((this.glGenFramebuffers = GLContext.getFunctionAddress("glGenFramebuffers")) != 0L ? 1 : 0) & ((this.glCheckFramebufferStatus = GLContext.getFunctionAddress("glCheckFramebufferStatus")) != 0L ? 1 : 0) & ((this.glFramebufferTexture1D = GLContext.getFunctionAddress("glFramebufferTexture1D")) != 0L ? 1 : 0) & ((this.glFramebufferTexture2D = GLContext.getFunctionAddress("glFramebufferTexture2D")) != 0L ? 1 : 0) & ((this.glFramebufferTexture3D = GLContext.getFunctionAddress("glFramebufferTexture3D")) != 0L ? 1 : 0) & ((this.glFramebufferTextureLayer = GLContext.getFunctionAddress("glFramebufferTextureLayer")) != 0L ? 1 : 0) & ((this.glFramebufferRenderbuffer = GLContext.getFunctionAddress("glFramebufferRenderbuffer")) != 0L ? 1 : 0) & ((this.glGetFramebufferAttachmentParameteriv = GLContext.getFunctionAddress("glGetFramebufferAttachmentParameteriv")) != 0L ? 1 : 0) & ((this.glBlitFramebuffer = GLContext.getFunctionAddress("glBlitFramebuffer")) != 0L ? 1 : 0) & ((this.glGenerateMipmap = GLContext.getFunctionAddress("glGenerateMipmap")) != 0L ? 1 : 0);
/* 2605:     */  }
/* 2606:     */  
/* 2626:     */  private boolean ARB_geometry_shader4_initNativeFunctionAddresses()
/* 2627:     */  {
/* 2628:2628 */    return ((this.glProgramParameteriARB = GLContext.getFunctionAddress("glProgramParameteriARB")) != 0L ? 1 : 0) & ((this.glFramebufferTextureARB = GLContext.getFunctionAddress("glFramebufferTextureARB")) != 0L ? 1 : 0) & ((this.glFramebufferTextureLayerARB = GLContext.getFunctionAddress("glFramebufferTextureLayerARB")) != 0L ? 1 : 0) & ((this.glFramebufferTextureFaceARB = GLContext.getFunctionAddress("glFramebufferTextureFaceARB")) != 0L ? 1 : 0);
/* 2629:     */  }
/* 2630:     */  
/* 2634:     */  private boolean ARB_get_program_binary_initNativeFunctionAddresses()
/* 2635:     */  {
/* 2636:2636 */    return ((this.glGetProgramBinary = GLContext.getFunctionAddress("glGetProgramBinary")) != 0L ? 1 : 0) & ((this.glProgramBinary = GLContext.getFunctionAddress("glProgramBinary")) != 0L ? 1 : 0) & ((this.glProgramParameteri = GLContext.getFunctionAddress("glProgramParameteri")) != 0L ? 1 : 0);
/* 2637:     */  }
/* 2638:     */  
/* 2641:     */  private boolean ARB_gpu_shader_fp64_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 2642:     */  {
/* 2643:2643 */    return ((this.glUniform1d = GLContext.getFunctionAddress("glUniform1d")) != 0L ? 1 : 0) & ((this.glUniform2d = GLContext.getFunctionAddress("glUniform2d")) != 0L ? 1 : 0) & ((this.glUniform3d = GLContext.getFunctionAddress("glUniform3d")) != 0L ? 1 : 0) & ((this.glUniform4d = GLContext.getFunctionAddress("glUniform4d")) != 0L ? 1 : 0) & ((this.glUniform1dv = GLContext.getFunctionAddress("glUniform1dv")) != 0L ? 1 : 0) & ((this.glUniform2dv = GLContext.getFunctionAddress("glUniform2dv")) != 0L ? 1 : 0) & ((this.glUniform3dv = GLContext.getFunctionAddress("glUniform3dv")) != 0L ? 1 : 0) & ((this.glUniform4dv = GLContext.getFunctionAddress("glUniform4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2dv = GLContext.getFunctionAddress("glUniformMatrix2dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3dv = GLContext.getFunctionAddress("glUniformMatrix3dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4dv = GLContext.getFunctionAddress("glUniformMatrix4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2x3dv = GLContext.getFunctionAddress("glUniformMatrix2x3dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2x4dv = GLContext.getFunctionAddress("glUniformMatrix2x4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3x2dv = GLContext.getFunctionAddress("glUniformMatrix3x2dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3x4dv = GLContext.getFunctionAddress("glUniformMatrix3x4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4x2dv = GLContext.getFunctionAddress("glUniformMatrix4x2dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4x3dv = GLContext.getFunctionAddress("glUniformMatrix4x3dv")) != 0L ? 1 : 0) & ((this.glGetUniformdv = GLContext.getFunctionAddress("glGetUniformdv")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform1dEXT = GLContext.getFunctionAddress("glProgramUniform1dEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform2dEXT = GLContext.getFunctionAddress("glProgramUniform2dEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform3dEXT = GLContext.getFunctionAddress("glProgramUniform3dEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform4dEXT = GLContext.getFunctionAddress("glProgramUniform4dEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform1dvEXT = GLContext.getFunctionAddress("glProgramUniform1dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform2dvEXT = GLContext.getFunctionAddress("glProgramUniform2dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform3dvEXT = GLContext.getFunctionAddress("glProgramUniform3dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform4dvEXT = GLContext.getFunctionAddress("glProgramUniform4dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix2dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix2dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix3dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix3dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix4dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix4dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix2x3dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix2x3dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix2x4dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix2x4dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix3x2dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix3x2dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix3x4dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix3x4dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix4x2dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix4x2dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniformMatrix4x3dvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix4x3dvEXT")) != 0L) ? 1 : 0);
/* 2644:     */  }
/* 2645:     */  
/* 2680:     */  private boolean ARB_imaging_initNativeFunctionAddresses(boolean forwardCompatible)
/* 2681:     */  {
/* 2682:2682 */    return ((forwardCompatible) || ((this.glColorTable = GLContext.getFunctionAddress("glColorTable")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorSubTable = GLContext.getFunctionAddress("glColorSubTable")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorTableParameteriv = GLContext.getFunctionAddress("glColorTableParameteriv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorTableParameterfv = GLContext.getFunctionAddress("glColorTableParameterfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glCopyColorSubTable = GLContext.getFunctionAddress("glCopyColorSubTable")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glCopyColorTable = GLContext.getFunctionAddress("glCopyColorTable")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetColorTable = GLContext.getFunctionAddress("glGetColorTable")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetColorTableParameteriv = GLContext.getFunctionAddress("glGetColorTableParameteriv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetColorTableParameterfv = GLContext.getFunctionAddress("glGetColorTableParameterfv")) != 0L) ? 1 : 0) & ((this.glBlendEquation = GLContext.getFunctionAddress("glBlendEquation")) != 0L ? 1 : 0) & ((this.glBlendColor = GLContext.getFunctionAddress("glBlendColor")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glHistogram = GLContext.getFunctionAddress("glHistogram")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glResetHistogram = GLContext.getFunctionAddress("glResetHistogram")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetHistogram = GLContext.getFunctionAddress("glGetHistogram")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetHistogramParameterfv = GLContext.getFunctionAddress("glGetHistogramParameterfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetHistogramParameteriv = GLContext.getFunctionAddress("glGetHistogramParameteriv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMinmax = GLContext.getFunctionAddress("glMinmax")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glResetMinmax = GLContext.getFunctionAddress("glResetMinmax")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMinmax = GLContext.getFunctionAddress("glGetMinmax")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMinmaxParameterfv = GLContext.getFunctionAddress("glGetMinmaxParameterfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMinmaxParameteriv = GLContext.getFunctionAddress("glGetMinmaxParameteriv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glConvolutionFilter1D = GLContext.getFunctionAddress("glConvolutionFilter1D")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glConvolutionFilter2D = GLContext.getFunctionAddress("glConvolutionFilter2D")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glConvolutionParameterf = GLContext.getFunctionAddress("glConvolutionParameterf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glConvolutionParameterfv = GLContext.getFunctionAddress("glConvolutionParameterfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glConvolutionParameteri = GLContext.getFunctionAddress("glConvolutionParameteri")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glConvolutionParameteriv = GLContext.getFunctionAddress("glConvolutionParameteriv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glCopyConvolutionFilter1D = GLContext.getFunctionAddress("glCopyConvolutionFilter1D")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glCopyConvolutionFilter2D = GLContext.getFunctionAddress("glCopyConvolutionFilter2D")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetConvolutionFilter = GLContext.getFunctionAddress("glGetConvolutionFilter")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetConvolutionParameterfv = GLContext.getFunctionAddress("glGetConvolutionParameterfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetConvolutionParameteriv = GLContext.getFunctionAddress("glGetConvolutionParameteriv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glSeparableFilter2D = GLContext.getFunctionAddress("glSeparableFilter2D")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetSeparableFilter = GLContext.getFunctionAddress("glGetSeparableFilter")) != 0L) ? 1 : 0);
/* 2683:     */  }
/* 2684:     */  
/* 2718:     */  private boolean ARB_instanced_arrays_initNativeFunctionAddresses()
/* 2719:     */  {
/* 2720:2720 */    return (this.glVertexAttribDivisorARB = GLContext.getFunctionAddress("glVertexAttribDivisorARB")) != 0L;
/* 2721:     */  }
/* 2722:     */  
/* 2723:     */  private boolean ARB_internalformat_query_initNativeFunctionAddresses()
/* 2724:     */  {
/* 2725:2725 */    return (this.glGetInternalformativ = GLContext.getFunctionAddress("glGetInternalformativ")) != 0L;
/* 2726:     */  }
/* 2727:     */  
/* 2728:     */  private boolean ARB_internalformat_query2_initNativeFunctionAddresses()
/* 2729:     */  {
/* 2730:2730 */    return (this.glGetInternalformati64v = GLContext.getFunctionAddress("glGetInternalformati64v")) != 0L;
/* 2731:     */  }
/* 2732:     */  
/* 2733:     */  private boolean ARB_invalidate_subdata_initNativeFunctionAddresses()
/* 2734:     */  {
/* 2735:2735 */    return ((this.glInvalidateTexSubImage = GLContext.getFunctionAddress("glInvalidateTexSubImage")) != 0L ? 1 : 0) & ((this.glInvalidateTexImage = GLContext.getFunctionAddress("glInvalidateTexImage")) != 0L ? 1 : 0) & ((this.glInvalidateBufferSubData = GLContext.getFunctionAddress("glInvalidateBufferSubData")) != 0L ? 1 : 0) & ((this.glInvalidateBufferData = GLContext.getFunctionAddress("glInvalidateBufferData")) != 0L ? 1 : 0) & ((this.glInvalidateFramebuffer = GLContext.getFunctionAddress("glInvalidateFramebuffer")) != 0L ? 1 : 0) & ((this.glInvalidateSubFramebuffer = GLContext.getFunctionAddress("glInvalidateSubFramebuffer")) != 0L ? 1 : 0);
/* 2736:     */  }
/* 2737:     */  
/* 2743:     */  private boolean ARB_map_buffer_range_initNativeFunctionAddresses()
/* 2744:     */  {
/* 2745:2745 */    return ((this.glMapBufferRange = GLContext.getFunctionAddress("glMapBufferRange")) != 0L ? 1 : 0) & ((this.glFlushMappedBufferRange = GLContext.getFunctionAddress("glFlushMappedBufferRange")) != 0L ? 1 : 0);
/* 2746:     */  }
/* 2747:     */  
/* 2749:     */  private boolean ARB_matrix_palette_initNativeFunctionAddresses()
/* 2750:     */  {
/* 2751:2751 */    return ((this.glCurrentPaletteMatrixARB = GLContext.getFunctionAddress("glCurrentPaletteMatrixARB")) != 0L ? 1 : 0) & ((this.glMatrixIndexPointerARB = GLContext.getFunctionAddress("glMatrixIndexPointerARB")) != 0L ? 1 : 0) & ((this.glMatrixIndexubvARB = GLContext.getFunctionAddress("glMatrixIndexubvARB")) != 0L ? 1 : 0) & ((this.glMatrixIndexusvARB = GLContext.getFunctionAddress("glMatrixIndexusvARB")) != 0L ? 1 : 0) & ((this.glMatrixIndexuivARB = GLContext.getFunctionAddress("glMatrixIndexuivARB")) != 0L ? 1 : 0);
/* 2752:     */  }
/* 2753:     */  
/* 2758:     */  private boolean ARB_multi_draw_indirect_initNativeFunctionAddresses()
/* 2759:     */  {
/* 2760:2760 */    return ((this.glMultiDrawArraysIndirect = GLContext.getFunctionAddress("glMultiDrawArraysIndirect")) != 0L ? 1 : 0) & ((this.glMultiDrawElementsIndirect = GLContext.getFunctionAddress("glMultiDrawElementsIndirect")) != 0L ? 1 : 0);
/* 2761:     */  }
/* 2762:     */  
/* 2764:     */  private boolean ARB_multisample_initNativeFunctionAddresses()
/* 2765:     */  {
/* 2766:2766 */    return (this.glSampleCoverageARB = GLContext.getFunctionAddress("glSampleCoverageARB")) != 0L;
/* 2767:     */  }
/* 2768:     */  
/* 2769:     */  private boolean ARB_multitexture_initNativeFunctionAddresses()
/* 2770:     */  {
/* 2771:2771 */    return ((this.glClientActiveTextureARB = GLContext.getFunctionAddress("glClientActiveTextureARB")) != 0L ? 1 : 0) & ((this.glActiveTextureARB = GLContext.getFunctionAddress("glActiveTextureARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord1fARB = GLContext.getFunctionAddress("glMultiTexCoord1fARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord1dARB = GLContext.getFunctionAddress("glMultiTexCoord1dARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord1iARB = GLContext.getFunctionAddress("glMultiTexCoord1iARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord1sARB = GLContext.getFunctionAddress("glMultiTexCoord1sARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord2fARB = GLContext.getFunctionAddress("glMultiTexCoord2fARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord2dARB = GLContext.getFunctionAddress("glMultiTexCoord2dARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord2iARB = GLContext.getFunctionAddress("glMultiTexCoord2iARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord2sARB = GLContext.getFunctionAddress("glMultiTexCoord2sARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord3fARB = GLContext.getFunctionAddress("glMultiTexCoord3fARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord3dARB = GLContext.getFunctionAddress("glMultiTexCoord3dARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord3iARB = GLContext.getFunctionAddress("glMultiTexCoord3iARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord3sARB = GLContext.getFunctionAddress("glMultiTexCoord3sARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord4fARB = GLContext.getFunctionAddress("glMultiTexCoord4fARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord4dARB = GLContext.getFunctionAddress("glMultiTexCoord4dARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord4iARB = GLContext.getFunctionAddress("glMultiTexCoord4iARB")) != 0L ? 1 : 0) & ((this.glMultiTexCoord4sARB = GLContext.getFunctionAddress("glMultiTexCoord4sARB")) != 0L ? 1 : 0);
/* 2772:     */  }
/* 2773:     */  
/* 2791:     */  private boolean ARB_occlusion_query_initNativeFunctionAddresses()
/* 2792:     */  {
/* 2793:2793 */    return ((this.glGenQueriesARB = GLContext.getFunctionAddress("glGenQueriesARB")) != 0L ? 1 : 0) & ((this.glDeleteQueriesARB = GLContext.getFunctionAddress("glDeleteQueriesARB")) != 0L ? 1 : 0) & ((this.glIsQueryARB = GLContext.getFunctionAddress("glIsQueryARB")) != 0L ? 1 : 0) & ((this.glBeginQueryARB = GLContext.getFunctionAddress("glBeginQueryARB")) != 0L ? 1 : 0) & ((this.glEndQueryARB = GLContext.getFunctionAddress("glEndQueryARB")) != 0L ? 1 : 0) & ((this.glGetQueryivARB = GLContext.getFunctionAddress("glGetQueryivARB")) != 0L ? 1 : 0) & ((this.glGetQueryObjectivARB = GLContext.getFunctionAddress("glGetQueryObjectivARB")) != 0L ? 1 : 0) & ((this.glGetQueryObjectuivARB = GLContext.getFunctionAddress("glGetQueryObjectuivARB")) != 0L ? 1 : 0);
/* 2794:     */  }
/* 2795:     */  
/* 2803:     */  private boolean ARB_point_parameters_initNativeFunctionAddresses()
/* 2804:     */  {
/* 2805:2805 */    return ((this.glPointParameterfARB = GLContext.getFunctionAddress("glPointParameterfARB")) != 0L ? 1 : 0) & ((this.glPointParameterfvARB = GLContext.getFunctionAddress("glPointParameterfvARB")) != 0L ? 1 : 0);
/* 2806:     */  }
/* 2807:     */  
/* 2809:     */  private boolean ARB_program_initNativeFunctionAddresses()
/* 2810:     */  {
/* 2811:2811 */    return ((this.glProgramStringARB = GLContext.getFunctionAddress("glProgramStringARB")) != 0L ? 1 : 0) & ((this.glBindProgramARB = GLContext.getFunctionAddress("glBindProgramARB")) != 0L ? 1 : 0) & ((this.glDeleteProgramsARB = GLContext.getFunctionAddress("glDeleteProgramsARB")) != 0L ? 1 : 0) & ((this.glGenProgramsARB = GLContext.getFunctionAddress("glGenProgramsARB")) != 0L ? 1 : 0) & ((this.glProgramEnvParameter4fARB = GLContext.getFunctionAddress("glProgramEnvParameter4fARB")) != 0L ? 1 : 0) & ((this.glProgramEnvParameter4dARB = GLContext.getFunctionAddress("glProgramEnvParameter4dARB")) != 0L ? 1 : 0) & ((this.glProgramEnvParameter4fvARB = GLContext.getFunctionAddress("glProgramEnvParameter4fvARB")) != 0L ? 1 : 0) & ((this.glProgramEnvParameter4dvARB = GLContext.getFunctionAddress("glProgramEnvParameter4dvARB")) != 0L ? 1 : 0) & ((this.glProgramLocalParameter4fARB = GLContext.getFunctionAddress("glProgramLocalParameter4fARB")) != 0L ? 1 : 0) & ((this.glProgramLocalParameter4dARB = GLContext.getFunctionAddress("glProgramLocalParameter4dARB")) != 0L ? 1 : 0) & ((this.glProgramLocalParameter4fvARB = GLContext.getFunctionAddress("glProgramLocalParameter4fvARB")) != 0L ? 1 : 0) & ((this.glProgramLocalParameter4dvARB = GLContext.getFunctionAddress("glProgramLocalParameter4dvARB")) != 0L ? 1 : 0) & ((this.glGetProgramEnvParameterfvARB = GLContext.getFunctionAddress("glGetProgramEnvParameterfvARB")) != 0L ? 1 : 0) & ((this.glGetProgramEnvParameterdvARB = GLContext.getFunctionAddress("glGetProgramEnvParameterdvARB")) != 0L ? 1 : 0) & ((this.glGetProgramLocalParameterfvARB = GLContext.getFunctionAddress("glGetProgramLocalParameterfvARB")) != 0L ? 1 : 0) & ((this.glGetProgramLocalParameterdvARB = GLContext.getFunctionAddress("glGetProgramLocalParameterdvARB")) != 0L ? 1 : 0) & ((this.glGetProgramivARB = GLContext.getFunctionAddress("glGetProgramivARB")) != 0L ? 1 : 0) & ((this.glGetProgramStringARB = GLContext.getFunctionAddress("glGetProgramStringARB")) != 0L ? 1 : 0) & ((this.glIsProgramARB = GLContext.getFunctionAddress("glIsProgramARB")) != 0L ? 1 : 0);
/* 2812:     */  }
/* 2813:     */  
/* 2832:     */  private boolean ARB_program_interface_query_initNativeFunctionAddresses()
/* 2833:     */  {
/* 2834:2834 */    return ((this.glGetProgramInterfaceiv = GLContext.getFunctionAddress("glGetProgramInterfaceiv")) != 0L ? 1 : 0) & ((this.glGetProgramResourceIndex = GLContext.getFunctionAddress("glGetProgramResourceIndex")) != 0L ? 1 : 0) & ((this.glGetProgramResourceName = GLContext.getFunctionAddress("glGetProgramResourceName")) != 0L ? 1 : 0) & ((this.glGetProgramResourceiv = GLContext.getFunctionAddress("glGetProgramResourceiv")) != 0L ? 1 : 0) & ((this.glGetProgramResourceLocation = GLContext.getFunctionAddress("glGetProgramResourceLocation")) != 0L ? 1 : 0) & ((this.glGetProgramResourceLocationIndex = GLContext.getFunctionAddress("glGetProgramResourceLocationIndex")) != 0L ? 1 : 0);
/* 2835:     */  }
/* 2836:     */  
/* 2842:     */  private boolean ARB_provoking_vertex_initNativeFunctionAddresses()
/* 2843:     */  {
/* 2844:2844 */    return (this.glProvokingVertex = GLContext.getFunctionAddress("glProvokingVertex")) != 0L;
/* 2845:     */  }
/* 2846:     */  
/* 2847:     */  private boolean ARB_robustness_initNativeFunctionAddresses(boolean forwardCompatible, Set<String> supported_extensions)
/* 2848:     */  {
/* 2849:2849 */    return ((this.glGetGraphicsResetStatusARB = GLContext.getFunctionAddress("glGetGraphicsResetStatusARB")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glGetnMapdvARB = GLContext.getFunctionAddress("glGetnMapdvARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetnMapfvARB = GLContext.getFunctionAddress("glGetnMapfvARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetnMapivARB = GLContext.getFunctionAddress("glGetnMapivARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetnPixelMapfvARB = GLContext.getFunctionAddress("glGetnPixelMapfvARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetnPixelMapuivARB = GLContext.getFunctionAddress("glGetnPixelMapuivARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetnPixelMapusvARB = GLContext.getFunctionAddress("glGetnPixelMapusvARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetnPolygonStippleARB = GLContext.getFunctionAddress("glGetnPolygonStippleARB")) != 0L) ? 1 : 0) & ((this.glGetnTexImageARB = GLContext.getFunctionAddress("glGetnTexImageARB")) != 0L ? 1 : 0) & ((this.glReadnPixelsARB = GLContext.getFunctionAddress("glReadnPixelsARB")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_imaging")) || ((this.glGetnColorTableARB = GLContext.getFunctionAddress("glGetnColorTableARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_imaging")) || ((this.glGetnConvolutionFilterARB = GLContext.getFunctionAddress("glGetnConvolutionFilterARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_imaging")) || ((this.glGetnSeparableFilterARB = GLContext.getFunctionAddress("glGetnSeparableFilterARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_imaging")) || ((this.glGetnHistogramARB = GLContext.getFunctionAddress("glGetnHistogramARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_imaging")) || ((this.glGetnMinmaxARB = GLContext.getFunctionAddress("glGetnMinmaxARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetnCompressedTexImageARB = GLContext.getFunctionAddress("glGetnCompressedTexImageARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glGetnUniformfvARB = GLContext.getFunctionAddress("glGetnUniformfvARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glGetnUniformivARB = GLContext.getFunctionAddress("glGetnUniformivARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glGetnUniformuivARB = GLContext.getFunctionAddress("glGetnUniformuivARB")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glGetnUniformdvARB = GLContext.getFunctionAddress("glGetnUniformdvARB")) != 0L) ? 1 : 0);
/* 2850:     */  }
/* 2851:     */  
/* 2871:     */  private boolean ARB_sample_shading_initNativeFunctionAddresses()
/* 2872:     */  {
/* 2873:2873 */    return (this.glMinSampleShadingARB = GLContext.getFunctionAddress("glMinSampleShadingARB")) != 0L;
/* 2874:     */  }
/* 2875:     */  
/* 2876:     */  private boolean ARB_sampler_objects_initNativeFunctionAddresses()
/* 2877:     */  {
/* 2878:2878 */    return ((this.glGenSamplers = GLContext.getFunctionAddress("glGenSamplers")) != 0L ? 1 : 0) & ((this.glDeleteSamplers = GLContext.getFunctionAddress("glDeleteSamplers")) != 0L ? 1 : 0) & ((this.glIsSampler = GLContext.getFunctionAddress("glIsSampler")) != 0L ? 1 : 0) & ((this.glBindSampler = GLContext.getFunctionAddress("glBindSampler")) != 0L ? 1 : 0) & ((this.glSamplerParameteri = GLContext.getFunctionAddress("glSamplerParameteri")) != 0L ? 1 : 0) & ((this.glSamplerParameterf = GLContext.getFunctionAddress("glSamplerParameterf")) != 0L ? 1 : 0) & ((this.glSamplerParameteriv = GLContext.getFunctionAddress("glSamplerParameteriv")) != 0L ? 1 : 0) & ((this.glSamplerParameterfv = GLContext.getFunctionAddress("glSamplerParameterfv")) != 0L ? 1 : 0) & ((this.glSamplerParameterIiv = GLContext.getFunctionAddress("glSamplerParameterIiv")) != 0L ? 1 : 0) & ((this.glSamplerParameterIuiv = GLContext.getFunctionAddress("glSamplerParameterIuiv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameteriv = GLContext.getFunctionAddress("glGetSamplerParameteriv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameterfv = GLContext.getFunctionAddress("glGetSamplerParameterfv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameterIiv = GLContext.getFunctionAddress("glGetSamplerParameterIiv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameterIuiv = GLContext.getFunctionAddress("glGetSamplerParameterIuiv")) != 0L ? 1 : 0);
/* 2879:     */  }
/* 2880:     */  
/* 2894:     */  private boolean ARB_separate_shader_objects_initNativeFunctionAddresses()
/* 2895:     */  {
/* 2896:2896 */    return ((this.glUseProgramStages = GLContext.getFunctionAddress("glUseProgramStages")) != 0L ? 1 : 0) & ((this.glActiveShaderProgram = GLContext.getFunctionAddress("glActiveShaderProgram")) != 0L ? 1 : 0) & ((this.glCreateShaderProgramv = GLContext.getFunctionAddress("glCreateShaderProgramv")) != 0L ? 1 : 0) & ((this.glBindProgramPipeline = GLContext.getFunctionAddress("glBindProgramPipeline")) != 0L ? 1 : 0) & ((this.glDeleteProgramPipelines = GLContext.getFunctionAddress("glDeleteProgramPipelines")) != 0L ? 1 : 0) & ((this.glGenProgramPipelines = GLContext.getFunctionAddress("glGenProgramPipelines")) != 0L ? 1 : 0) & ((this.glIsProgramPipeline = GLContext.getFunctionAddress("glIsProgramPipeline")) != 0L ? 1 : 0) & ((this.glProgramParameteri = GLContext.getFunctionAddress("glProgramParameteri")) != 0L ? 1 : 0) & ((this.glGetProgramPipelineiv = GLContext.getFunctionAddress("glGetProgramPipelineiv")) != 0L ? 1 : 0) & ((this.glProgramUniform1i = GLContext.getFunctionAddress("glProgramUniform1i")) != 0L ? 1 : 0) & ((this.glProgramUniform2i = GLContext.getFunctionAddress("glProgramUniform2i")) != 0L ? 1 : 0) & ((this.glProgramUniform3i = GLContext.getFunctionAddress("glProgramUniform3i")) != 0L ? 1 : 0) & ((this.glProgramUniform4i = GLContext.getFunctionAddress("glProgramUniform4i")) != 0L ? 1 : 0) & ((this.glProgramUniform1f = GLContext.getFunctionAddress("glProgramUniform1f")) != 0L ? 1 : 0) & ((this.glProgramUniform2f = GLContext.getFunctionAddress("glProgramUniform2f")) != 0L ? 1 : 0) & ((this.glProgramUniform3f = GLContext.getFunctionAddress("glProgramUniform3f")) != 0L ? 1 : 0) & ((this.glProgramUniform4f = GLContext.getFunctionAddress("glProgramUniform4f")) != 0L ? 1 : 0) & ((this.glProgramUniform1d = GLContext.getFunctionAddress("glProgramUniform1d")) != 0L ? 1 : 0) & ((this.glProgramUniform2d = GLContext.getFunctionAddress("glProgramUniform2d")) != 0L ? 1 : 0) & ((this.glProgramUniform3d = GLContext.getFunctionAddress("glProgramUniform3d")) != 0L ? 1 : 0) & ((this.glProgramUniform4d = GLContext.getFunctionAddress("glProgramUniform4d")) != 0L ? 1 : 0) & ((this.glProgramUniform1iv = GLContext.getFunctionAddress("glProgramUniform1iv")) != 0L ? 1 : 0) & ((this.glProgramUniform2iv = GLContext.getFunctionAddress("glProgramUniform2iv")) != 0L ? 1 : 0) & ((this.glProgramUniform3iv = GLContext.getFunctionAddress("glProgramUniform3iv")) != 0L ? 1 : 0) & ((this.glProgramUniform4iv = GLContext.getFunctionAddress("glProgramUniform4iv")) != 0L ? 1 : 0) & ((this.glProgramUniform1fv = GLContext.getFunctionAddress("glProgramUniform1fv")) != 0L ? 1 : 0) & ((this.glProgramUniform2fv = GLContext.getFunctionAddress("glProgramUniform2fv")) != 0L ? 1 : 0) & ((this.glProgramUniform3fv = GLContext.getFunctionAddress("glProgramUniform3fv")) != 0L ? 1 : 0) & ((this.glProgramUniform4fv = GLContext.getFunctionAddress("glProgramUniform4fv")) != 0L ? 1 : 0) & ((this.glProgramUniform1dv = GLContext.getFunctionAddress("glProgramUniform1dv")) != 0L ? 1 : 0) & ((this.glProgramUniform2dv = GLContext.getFunctionAddress("glProgramUniform2dv")) != 0L ? 1 : 0) & ((this.glProgramUniform3dv = GLContext.getFunctionAddress("glProgramUniform3dv")) != 0L ? 1 : 0) & ((this.glProgramUniform4dv = GLContext.getFunctionAddress("glProgramUniform4dv")) != 0L ? 1 : 0) & ((this.glProgramUniform1ui = GLContext.getFunctionAddress("glProgramUniform1ui")) != 0L ? 1 : 0) & ((this.glProgramUniform2ui = GLContext.getFunctionAddress("glProgramUniform2ui")) != 0L ? 1 : 0) & ((this.glProgramUniform3ui = GLContext.getFunctionAddress("glProgramUniform3ui")) != 0L ? 1 : 0) & ((this.glProgramUniform4ui = GLContext.getFunctionAddress("glProgramUniform4ui")) != 0L ? 1 : 0) & ((this.glProgramUniform1uiv = GLContext.getFunctionAddress("glProgramUniform1uiv")) != 0L ? 1 : 0) & ((this.glProgramUniform2uiv = GLContext.getFunctionAddress("glProgramUniform2uiv")) != 0L ? 1 : 0) & ((this.glProgramUniform3uiv = GLContext.getFunctionAddress("glProgramUniform3uiv")) != 0L ? 1 : 0) & ((this.glProgramUniform4uiv = GLContext.getFunctionAddress("glProgramUniform4uiv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2fv = GLContext.getFunctionAddress("glProgramUniformMatrix2fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3fv = GLContext.getFunctionAddress("glProgramUniformMatrix3fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4fv = GLContext.getFunctionAddress("glProgramUniformMatrix4fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2dv = GLContext.getFunctionAddress("glProgramUniformMatrix2dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3dv = GLContext.getFunctionAddress("glProgramUniformMatrix3dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4dv = GLContext.getFunctionAddress("glProgramUniformMatrix4dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x3fv = GLContext.getFunctionAddress("glProgramUniformMatrix2x3fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x2fv = GLContext.getFunctionAddress("glProgramUniformMatrix3x2fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x4fv = GLContext.getFunctionAddress("glProgramUniformMatrix2x4fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x2fv = GLContext.getFunctionAddress("glProgramUniformMatrix4x2fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x4fv = GLContext.getFunctionAddress("glProgramUniformMatrix3x4fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x3fv = GLContext.getFunctionAddress("glProgramUniformMatrix4x3fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x3dv = GLContext.getFunctionAddress("glProgramUniformMatrix2x3dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x2dv = GLContext.getFunctionAddress("glProgramUniformMatrix3x2dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x4dv = GLContext.getFunctionAddress("glProgramUniformMatrix2x4dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x2dv = GLContext.getFunctionAddress("glProgramUniformMatrix4x2dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x4dv = GLContext.getFunctionAddress("glProgramUniformMatrix3x4dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x3dv = GLContext.getFunctionAddress("glProgramUniformMatrix4x3dv")) != 0L ? 1 : 0) & ((this.glValidateProgramPipeline = GLContext.getFunctionAddress("glValidateProgramPipeline")) != 0L ? 1 : 0) & ((this.glGetProgramPipelineInfoLog = GLContext.getFunctionAddress("glGetProgramPipelineInfoLog")) != 0L ? 1 : 0);
/* 2897:     */  }
/* 2898:     */  
/* 2959:     */  private boolean ARB_shader_atomic_counters_initNativeFunctionAddresses()
/* 2960:     */  {
/* 2961:2961 */    return (this.glGetActiveAtomicCounterBufferiv = GLContext.getFunctionAddress("glGetActiveAtomicCounterBufferiv")) != 0L;
/* 2962:     */  }
/* 2963:     */  
/* 2964:     */  private boolean ARB_shader_image_load_store_initNativeFunctionAddresses()
/* 2965:     */  {
/* 2966:2966 */    return ((this.glBindImageTexture = GLContext.getFunctionAddress("glBindImageTexture")) != 0L ? 1 : 0) & ((this.glMemoryBarrier = GLContext.getFunctionAddress("glMemoryBarrier")) != 0L ? 1 : 0);
/* 2967:     */  }
/* 2968:     */  
/* 2970:     */  private boolean ARB_shader_objects_initNativeFunctionAddresses()
/* 2971:     */  {
/* 2972:2972 */    return ((this.glDeleteObjectARB = GLContext.getFunctionAddress("glDeleteObjectARB")) != 0L ? 1 : 0) & ((this.glGetHandleARB = GLContext.getFunctionAddress("glGetHandleARB")) != 0L ? 1 : 0) & ((this.glDetachObjectARB = GLContext.getFunctionAddress("glDetachObjectARB")) != 0L ? 1 : 0) & ((this.glCreateShaderObjectARB = GLContext.getFunctionAddress("glCreateShaderObjectARB")) != 0L ? 1 : 0) & ((this.glShaderSourceARB = GLContext.getFunctionAddress("glShaderSourceARB")) != 0L ? 1 : 0) & ((this.glCompileShaderARB = GLContext.getFunctionAddress("glCompileShaderARB")) != 0L ? 1 : 0) & ((this.glCreateProgramObjectARB = GLContext.getFunctionAddress("glCreateProgramObjectARB")) != 0L ? 1 : 0) & ((this.glAttachObjectARB = GLContext.getFunctionAddress("glAttachObjectARB")) != 0L ? 1 : 0) & ((this.glLinkProgramARB = GLContext.getFunctionAddress("glLinkProgramARB")) != 0L ? 1 : 0) & ((this.glUseProgramObjectARB = GLContext.getFunctionAddress("glUseProgramObjectARB")) != 0L ? 1 : 0) & ((this.glValidateProgramARB = GLContext.getFunctionAddress("glValidateProgramARB")) != 0L ? 1 : 0) & ((this.glUniform1fARB = GLContext.getFunctionAddress("glUniform1fARB")) != 0L ? 1 : 0) & ((this.glUniform2fARB = GLContext.getFunctionAddress("glUniform2fARB")) != 0L ? 1 : 0) & ((this.glUniform3fARB = GLContext.getFunctionAddress("glUniform3fARB")) != 0L ? 1 : 0) & ((this.glUniform4fARB = GLContext.getFunctionAddress("glUniform4fARB")) != 0L ? 1 : 0) & ((this.glUniform1iARB = GLContext.getFunctionAddress("glUniform1iARB")) != 0L ? 1 : 0) & ((this.glUniform2iARB = GLContext.getFunctionAddress("glUniform2iARB")) != 0L ? 1 : 0) & ((this.glUniform3iARB = GLContext.getFunctionAddress("glUniform3iARB")) != 0L ? 1 : 0) & ((this.glUniform4iARB = GLContext.getFunctionAddress("glUniform4iARB")) != 0L ? 1 : 0) & ((this.glUniform1fvARB = GLContext.getFunctionAddress("glUniform1fvARB")) != 0L ? 1 : 0) & ((this.glUniform2fvARB = GLContext.getFunctionAddress("glUniform2fvARB")) != 0L ? 1 : 0) & ((this.glUniform3fvARB = GLContext.getFunctionAddress("glUniform3fvARB")) != 0L ? 1 : 0) & ((this.glUniform4fvARB = GLContext.getFunctionAddress("glUniform4fvARB")) != 0L ? 1 : 0) & ((this.glUniform1ivARB = GLContext.getFunctionAddress("glUniform1ivARB")) != 0L ? 1 : 0) & ((this.glUniform2ivARB = GLContext.getFunctionAddress("glUniform2ivARB")) != 0L ? 1 : 0) & ((this.glUniform3ivARB = GLContext.getFunctionAddress("glUniform3ivARB")) != 0L ? 1 : 0) & ((this.glUniform4ivARB = GLContext.getFunctionAddress("glUniform4ivARB")) != 0L ? 1 : 0) & ((this.glUniformMatrix2fvARB = GLContext.getFunctionAddress("glUniformMatrix2fvARB")) != 0L ? 1 : 0) & ((this.glUniformMatrix3fvARB = GLContext.getFunctionAddress("glUniformMatrix3fvARB")) != 0L ? 1 : 0) & ((this.glUniformMatrix4fvARB = GLContext.getFunctionAddress("glUniformMatrix4fvARB")) != 0L ? 1 : 0) & ((this.glGetObjectParameterfvARB = GLContext.getFunctionAddress("glGetObjectParameterfvARB")) != 0L ? 1 : 0) & ((this.glGetObjectParameterivARB = GLContext.getFunctionAddress("glGetObjectParameterivARB")) != 0L ? 1 : 0) & ((this.glGetInfoLogARB = GLContext.getFunctionAddress("glGetInfoLogARB")) != 0L ? 1 : 0) & ((this.glGetAttachedObjectsARB = GLContext.getFunctionAddress("glGetAttachedObjectsARB")) != 0L ? 1 : 0) & ((this.glGetUniformLocationARB = GLContext.getFunctionAddress("glGetUniformLocationARB")) != 0L ? 1 : 0) & ((this.glGetActiveUniformARB = GLContext.getFunctionAddress("glGetActiveUniformARB")) != 0L ? 1 : 0) & ((this.glGetUniformfvARB = GLContext.getFunctionAddress("glGetUniformfvARB")) != 0L ? 1 : 0) & ((this.glGetUniformivARB = GLContext.getFunctionAddress("glGetUniformivARB")) != 0L ? 1 : 0) & ((this.glGetShaderSourceARB = GLContext.getFunctionAddress("glGetShaderSourceARB")) != 0L ? 1 : 0);
/* 2973:     */  }
/* 2974:     */  
/* 3013:     */  private boolean ARB_shader_storage_buffer_object_initNativeFunctionAddresses()
/* 3014:     */  {
/* 3015:3015 */    return (this.glShaderStorageBlockBinding = GLContext.getFunctionAddress("glShaderStorageBlockBinding")) != 0L;
/* 3016:     */  }
/* 3017:     */  
/* 3018:     */  private boolean ARB_shader_subroutine_initNativeFunctionAddresses()
/* 3019:     */  {
/* 3020:3020 */    return ((this.glGetSubroutineUniformLocation = GLContext.getFunctionAddress("glGetSubroutineUniformLocation")) != 0L ? 1 : 0) & ((this.glGetSubroutineIndex = GLContext.getFunctionAddress("glGetSubroutineIndex")) != 0L ? 1 : 0) & ((this.glGetActiveSubroutineUniformiv = GLContext.getFunctionAddress("glGetActiveSubroutineUniformiv")) != 0L ? 1 : 0) & ((this.glGetActiveSubroutineUniformName = GLContext.getFunctionAddress("glGetActiveSubroutineUniformName")) != 0L ? 1 : 0) & ((this.glGetActiveSubroutineName = GLContext.getFunctionAddress("glGetActiveSubroutineName")) != 0L ? 1 : 0) & ((this.glUniformSubroutinesuiv = GLContext.getFunctionAddress("glUniformSubroutinesuiv")) != 0L ? 1 : 0) & ((this.glGetUniformSubroutineuiv = GLContext.getFunctionAddress("glGetUniformSubroutineuiv")) != 0L ? 1 : 0) & ((this.glGetProgramStageiv = GLContext.getFunctionAddress("glGetProgramStageiv")) != 0L ? 1 : 0);
/* 3021:     */  }
/* 3022:     */  
/* 3030:     */  private boolean ARB_shading_language_include_initNativeFunctionAddresses()
/* 3031:     */  {
/* 3032:3032 */    return ((this.glNamedStringARB = GLContext.getFunctionAddress("glNamedStringARB")) != 0L ? 1 : 0) & ((this.glDeleteNamedStringARB = GLContext.getFunctionAddress("glDeleteNamedStringARB")) != 0L ? 1 : 0) & ((this.glCompileShaderIncludeARB = GLContext.getFunctionAddress("glCompileShaderIncludeARB")) != 0L ? 1 : 0) & ((this.glIsNamedStringARB = GLContext.getFunctionAddress("glIsNamedStringARB")) != 0L ? 1 : 0) & ((this.glGetNamedStringARB = GLContext.getFunctionAddress("glGetNamedStringARB")) != 0L ? 1 : 0) & ((this.glGetNamedStringivARB = GLContext.getFunctionAddress("glGetNamedStringivARB")) != 0L ? 1 : 0);
/* 3033:     */  }
/* 3034:     */  
/* 3040:     */  private boolean ARB_sync_initNativeFunctionAddresses()
/* 3041:     */  {
/* 3042:3042 */    return ((this.glFenceSync = GLContext.getFunctionAddress("glFenceSync")) != 0L ? 1 : 0) & ((this.glIsSync = GLContext.getFunctionAddress("glIsSync")) != 0L ? 1 : 0) & ((this.glDeleteSync = GLContext.getFunctionAddress("glDeleteSync")) != 0L ? 1 : 0) & ((this.glClientWaitSync = GLContext.getFunctionAddress("glClientWaitSync")) != 0L ? 1 : 0) & ((this.glWaitSync = GLContext.getFunctionAddress("glWaitSync")) != 0L ? 1 : 0) & ((this.glGetInteger64v = GLContext.getFunctionAddress("glGetInteger64v")) != 0L ? 1 : 0) & ((this.glGetSynciv = GLContext.getFunctionAddress("glGetSynciv")) != 0L ? 1 : 0);
/* 3043:     */  }
/* 3044:     */  
/* 3051:     */  private boolean ARB_tessellation_shader_initNativeFunctionAddresses()
/* 3052:     */  {
/* 3053:3053 */    return ((this.glPatchParameteri = GLContext.getFunctionAddress("glPatchParameteri")) != 0L ? 1 : 0) & ((this.glPatchParameterfv = GLContext.getFunctionAddress("glPatchParameterfv")) != 0L ? 1 : 0);
/* 3054:     */  }
/* 3055:     */  
/* 3057:     */  private boolean ARB_texture_buffer_object_initNativeFunctionAddresses()
/* 3058:     */  {
/* 3059:3059 */    return (this.glTexBufferARB = GLContext.getFunctionAddress("glTexBufferARB")) != 0L;
/* 3060:     */  }
/* 3061:     */  
/* 3062:     */  private boolean ARB_texture_buffer_range_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 3063:     */  {
/* 3064:3064 */    return ((this.glTexBufferRange = GLContext.getFunctionAddress("glTexBufferRange")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glTextureBufferRangeEXT = GLContext.getFunctionAddress("glTextureBufferRangeEXT")) != 0L) ? 1 : 0);
/* 3065:     */  }
/* 3066:     */  
/* 3068:     */  private boolean ARB_texture_compression_initNativeFunctionAddresses()
/* 3069:     */  {
/* 3070:3070 */    return ((this.glCompressedTexImage1DARB = GLContext.getFunctionAddress("glCompressedTexImage1DARB")) != 0L ? 1 : 0) & ((this.glCompressedTexImage2DARB = GLContext.getFunctionAddress("glCompressedTexImage2DARB")) != 0L ? 1 : 0) & ((this.glCompressedTexImage3DARB = GLContext.getFunctionAddress("glCompressedTexImage3DARB")) != 0L ? 1 : 0) & ((this.glCompressedTexSubImage1DARB = GLContext.getFunctionAddress("glCompressedTexSubImage1DARB")) != 0L ? 1 : 0) & ((this.glCompressedTexSubImage2DARB = GLContext.getFunctionAddress("glCompressedTexSubImage2DARB")) != 0L ? 1 : 0) & ((this.glCompressedTexSubImage3DARB = GLContext.getFunctionAddress("glCompressedTexSubImage3DARB")) != 0L ? 1 : 0) & ((this.glGetCompressedTexImageARB = GLContext.getFunctionAddress("glGetCompressedTexImageARB")) != 0L ? 1 : 0);
/* 3071:     */  }
/* 3072:     */  
/* 3079:     */  private boolean ARB_texture_multisample_initNativeFunctionAddresses()
/* 3080:     */  {
/* 3081:3081 */    return ((this.glTexImage2DMultisample = GLContext.getFunctionAddress("glTexImage2DMultisample")) != 0L ? 1 : 0) & ((this.glTexImage3DMultisample = GLContext.getFunctionAddress("glTexImage3DMultisample")) != 0L ? 1 : 0) & ((this.glGetMultisamplefv = GLContext.getFunctionAddress("glGetMultisamplefv")) != 0L ? 1 : 0) & ((this.glSampleMaski = GLContext.getFunctionAddress("glSampleMaski")) != 0L ? 1 : 0);
/* 3082:     */  }
/* 3083:     */  
/* 3087:     */  private boolean ARB_texture_storage_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 3088:     */  {
/* 3089:3089 */    if (supported_extensions.contains("GL_EXT_direct_state_access")) {} if (supported_extensions.contains("GL_EXT_direct_state_access")) {} if (supported_extensions.contains("GL_EXT_direct_state_access")) {} return ((this.glTexStorage1D = GLContext.getFunctionAddress(new String[] { "glTexStorage1D", "glTexStorage1DEXT" })) != 0L ? 1 : 0) & ((this.glTexStorage2D = GLContext.getFunctionAddress(new String[] { "glTexStorage2D", "glTexStorage2DEXT" })) != 0L ? 1 : 0) & ((this.glTexStorage3D = GLContext.getFunctionAddress(new String[] { "glTexStorage3D", "glTexStorage3DEXT" })) != 0L ? 1 : 0) & ((this.glTextureStorage1DEXT = GLContext.getFunctionAddress(new String[] { "glTextureStorage1DEXT", "glTextureStorage1DEXTEXT" })) != 0L ? 1 : 0) & ((this.glTextureStorage2DEXT = GLContext.getFunctionAddress(new String[] { "glTextureStorage2DEXT", "glTextureStorage2DEXTEXT" })) != 0L ? 1 : 0) & ((this.glTextureStorage3DEXT = GLContext.getFunctionAddress(new String[] { "glTextureStorage3DEXT", "glTextureStorage3DEXTEXT" })) != 0L ? 1 : 0);
/* 3090:     */  }
/* 3091:     */  
/* 3097:     */  private boolean ARB_texture_storage_multisample_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 3098:     */  {
/* 3099:3099 */    return ((this.glTexStorage2DMultisample = GLContext.getFunctionAddress("glTexStorage2DMultisample")) != 0L ? 1 : 0) & ((this.glTexStorage3DMultisample = GLContext.getFunctionAddress("glTexStorage3DMultisample")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glTextureStorage2DMultisampleEXT = GLContext.getFunctionAddress("glTextureStorage2DMultisampleEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glTextureStorage3DMultisampleEXT = GLContext.getFunctionAddress("glTextureStorage3DMultisampleEXT")) != 0L) ? 1 : 0);
/* 3100:     */  }
/* 3101:     */  
/* 3105:     */  private boolean ARB_texture_view_initNativeFunctionAddresses()
/* 3106:     */  {
/* 3107:3107 */    return (this.glTextureView = GLContext.getFunctionAddress("glTextureView")) != 0L;
/* 3108:     */  }
/* 3109:     */  
/* 3110:     */  private boolean ARB_timer_query_initNativeFunctionAddresses()
/* 3111:     */  {
/* 3112:3112 */    return ((this.glQueryCounter = GLContext.getFunctionAddress("glQueryCounter")) != 0L ? 1 : 0) & ((this.glGetQueryObjecti64v = GLContext.getFunctionAddress("glGetQueryObjecti64v")) != 0L ? 1 : 0) & ((this.glGetQueryObjectui64v = GLContext.getFunctionAddress("glGetQueryObjectui64v")) != 0L ? 1 : 0);
/* 3113:     */  }
/* 3114:     */  
/* 3117:     */  private boolean ARB_transform_feedback2_initNativeFunctionAddresses()
/* 3118:     */  {
/* 3119:3119 */    return ((this.glBindTransformFeedback = GLContext.getFunctionAddress("glBindTransformFeedback")) != 0L ? 1 : 0) & ((this.glDeleteTransformFeedbacks = GLContext.getFunctionAddress("glDeleteTransformFeedbacks")) != 0L ? 1 : 0) & ((this.glGenTransformFeedbacks = GLContext.getFunctionAddress("glGenTransformFeedbacks")) != 0L ? 1 : 0) & ((this.glIsTransformFeedback = GLContext.getFunctionAddress("glIsTransformFeedback")) != 0L ? 1 : 0) & ((this.glPauseTransformFeedback = GLContext.getFunctionAddress("glPauseTransformFeedback")) != 0L ? 1 : 0) & ((this.glResumeTransformFeedback = GLContext.getFunctionAddress("glResumeTransformFeedback")) != 0L ? 1 : 0) & ((this.glDrawTransformFeedback = GLContext.getFunctionAddress("glDrawTransformFeedback")) != 0L ? 1 : 0);
/* 3120:     */  }
/* 3121:     */  
/* 3128:     */  private boolean ARB_transform_feedback3_initNativeFunctionAddresses()
/* 3129:     */  {
/* 3130:3130 */    return ((this.glDrawTransformFeedbackStream = GLContext.getFunctionAddress("glDrawTransformFeedbackStream")) != 0L ? 1 : 0) & ((this.glBeginQueryIndexed = GLContext.getFunctionAddress("glBeginQueryIndexed")) != 0L ? 1 : 0) & ((this.glEndQueryIndexed = GLContext.getFunctionAddress("glEndQueryIndexed")) != 0L ? 1 : 0) & ((this.glGetQueryIndexediv = GLContext.getFunctionAddress("glGetQueryIndexediv")) != 0L ? 1 : 0);
/* 3131:     */  }
/* 3132:     */  
/* 3136:     */  private boolean ARB_transform_feedback_instanced_initNativeFunctionAddresses()
/* 3137:     */  {
/* 3138:3138 */    return ((this.glDrawTransformFeedbackInstanced = GLContext.getFunctionAddress("glDrawTransformFeedbackInstanced")) != 0L ? 1 : 0) & ((this.glDrawTransformFeedbackStreamInstanced = GLContext.getFunctionAddress("glDrawTransformFeedbackStreamInstanced")) != 0L ? 1 : 0);
/* 3139:     */  }
/* 3140:     */  
/* 3142:     */  private boolean ARB_transpose_matrix_initNativeFunctionAddresses()
/* 3143:     */  {
/* 3144:3144 */    return ((this.glLoadTransposeMatrixfARB = GLContext.getFunctionAddress("glLoadTransposeMatrixfARB")) != 0L ? 1 : 0) & ((this.glMultTransposeMatrixfARB = GLContext.getFunctionAddress("glMultTransposeMatrixfARB")) != 0L ? 1 : 0);
/* 3145:     */  }
/* 3146:     */  
/* 3148:     */  private boolean ARB_uniform_buffer_object_initNativeFunctionAddresses()
/* 3149:     */  {
/* 3150:3150 */    return ((this.glGetUniformIndices = GLContext.getFunctionAddress("glGetUniformIndices")) != 0L ? 1 : 0) & ((this.glGetActiveUniformsiv = GLContext.getFunctionAddress("glGetActiveUniformsiv")) != 0L ? 1 : 0) & ((this.glGetActiveUniformName = GLContext.getFunctionAddress("glGetActiveUniformName")) != 0L ? 1 : 0) & ((this.glGetUniformBlockIndex = GLContext.getFunctionAddress("glGetUniformBlockIndex")) != 0L ? 1 : 0) & ((this.glGetActiveUniformBlockiv = GLContext.getFunctionAddress("glGetActiveUniformBlockiv")) != 0L ? 1 : 0) & ((this.glGetActiveUniformBlockName = GLContext.getFunctionAddress("glGetActiveUniformBlockName")) != 0L ? 1 : 0) & ((this.glBindBufferRange = GLContext.getFunctionAddress("glBindBufferRange")) != 0L ? 1 : 0) & ((this.glBindBufferBase = GLContext.getFunctionAddress("glBindBufferBase")) != 0L ? 1 : 0) & ((this.glGetIntegeri_v = GLContext.getFunctionAddress("glGetIntegeri_v")) != 0L ? 1 : 0) & ((this.glUniformBlockBinding = GLContext.getFunctionAddress("glUniformBlockBinding")) != 0L ? 1 : 0);
/* 3151:     */  }
/* 3152:     */  
/* 3162:     */  private boolean ARB_vertex_array_object_initNativeFunctionAddresses()
/* 3163:     */  {
/* 3164:3164 */    return ((this.glBindVertexArray = GLContext.getFunctionAddress("glBindVertexArray")) != 0L ? 1 : 0) & ((this.glDeleteVertexArrays = GLContext.getFunctionAddress("glDeleteVertexArrays")) != 0L ? 1 : 0) & ((this.glGenVertexArrays = GLContext.getFunctionAddress("glGenVertexArrays")) != 0L ? 1 : 0) & ((this.glIsVertexArray = GLContext.getFunctionAddress("glIsVertexArray")) != 0L ? 1 : 0);
/* 3165:     */  }
/* 3166:     */  
/* 3170:     */  private boolean ARB_vertex_attrib_64bit_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 3171:     */  {
/* 3172:3172 */    return ((this.glVertexAttribL1d = GLContext.getFunctionAddress("glVertexAttribL1d")) != 0L ? 1 : 0) & ((this.glVertexAttribL2d = GLContext.getFunctionAddress("glVertexAttribL2d")) != 0L ? 1 : 0) & ((this.glVertexAttribL3d = GLContext.getFunctionAddress("glVertexAttribL3d")) != 0L ? 1 : 0) & ((this.glVertexAttribL4d = GLContext.getFunctionAddress("glVertexAttribL4d")) != 0L ? 1 : 0) & ((this.glVertexAttribL1dv = GLContext.getFunctionAddress("glVertexAttribL1dv")) != 0L ? 1 : 0) & ((this.glVertexAttribL2dv = GLContext.getFunctionAddress("glVertexAttribL2dv")) != 0L ? 1 : 0) & ((this.glVertexAttribL3dv = GLContext.getFunctionAddress("glVertexAttribL3dv")) != 0L ? 1 : 0) & ((this.glVertexAttribL4dv = GLContext.getFunctionAddress("glVertexAttribL4dv")) != 0L ? 1 : 0) & ((this.glVertexAttribLPointer = GLContext.getFunctionAddress("glVertexAttribLPointer")) != 0L ? 1 : 0) & ((this.glGetVertexAttribLdv = GLContext.getFunctionAddress("glGetVertexAttribLdv")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glVertexArrayVertexAttribLOffsetEXT = GLContext.getFunctionAddress("glVertexArrayVertexAttribLOffsetEXT")) != 0L) ? 1 : 0);
/* 3173:     */  }
/* 3174:     */  
/* 3185:     */  private boolean ARB_vertex_attrib_binding_initNativeFunctionAddresses()
/* 3186:     */  {
/* 3187:3187 */    return ((this.glBindVertexBuffer = GLContext.getFunctionAddress("glBindVertexBuffer")) != 0L ? 1 : 0) & ((this.glVertexAttribFormat = GLContext.getFunctionAddress("glVertexAttribFormat")) != 0L ? 1 : 0) & ((this.glVertexAttribIFormat = GLContext.getFunctionAddress("glVertexAttribIFormat")) != 0L ? 1 : 0) & ((this.glVertexAttribLFormat = GLContext.getFunctionAddress("glVertexAttribLFormat")) != 0L ? 1 : 0) & ((this.glVertexAttribBinding = GLContext.getFunctionAddress("glVertexAttribBinding")) != 0L ? 1 : 0) & ((this.glVertexBindingDivisor = GLContext.getFunctionAddress("glVertexBindingDivisor")) != 0L ? 1 : 0);
/* 3188:     */  }
/* 3189:     */  
/* 3195:     */  private boolean ARB_vertex_blend_initNativeFunctionAddresses()
/* 3196:     */  {
/* 3197:3197 */    return ((this.glWeightbvARB = GLContext.getFunctionAddress("glWeightbvARB")) != 0L ? 1 : 0) & ((this.glWeightsvARB = GLContext.getFunctionAddress("glWeightsvARB")) != 0L ? 1 : 0) & ((this.glWeightivARB = GLContext.getFunctionAddress("glWeightivARB")) != 0L ? 1 : 0) & ((this.glWeightfvARB = GLContext.getFunctionAddress("glWeightfvARB")) != 0L ? 1 : 0) & ((this.glWeightdvARB = GLContext.getFunctionAddress("glWeightdvARB")) != 0L ? 1 : 0) & ((this.glWeightubvARB = GLContext.getFunctionAddress("glWeightubvARB")) != 0L ? 1 : 0) & ((this.glWeightusvARB = GLContext.getFunctionAddress("glWeightusvARB")) != 0L ? 1 : 0) & ((this.glWeightuivARB = GLContext.getFunctionAddress("glWeightuivARB")) != 0L ? 1 : 0) & ((this.glWeightPointerARB = GLContext.getFunctionAddress("glWeightPointerARB")) != 0L ? 1 : 0) & ((this.glVertexBlendARB = GLContext.getFunctionAddress("glVertexBlendARB")) != 0L ? 1 : 0);
/* 3198:     */  }
/* 3199:     */  
/* 3209:     */  private boolean ARB_vertex_program_initNativeFunctionAddresses()
/* 3210:     */  {
/* 3211:3211 */    return ((this.glVertexAttrib1sARB = GLContext.getFunctionAddress("glVertexAttrib1sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib1fARB = GLContext.getFunctionAddress("glVertexAttrib1fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib1dARB = GLContext.getFunctionAddress("glVertexAttrib1dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib2sARB = GLContext.getFunctionAddress("glVertexAttrib2sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib2fARB = GLContext.getFunctionAddress("glVertexAttrib2fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib2dARB = GLContext.getFunctionAddress("glVertexAttrib2dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib3sARB = GLContext.getFunctionAddress("glVertexAttrib3sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib3fARB = GLContext.getFunctionAddress("glVertexAttrib3fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib3dARB = GLContext.getFunctionAddress("glVertexAttrib3dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4sARB = GLContext.getFunctionAddress("glVertexAttrib4sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4fARB = GLContext.getFunctionAddress("glVertexAttrib4fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4dARB = GLContext.getFunctionAddress("glVertexAttrib4dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4NubARB = GLContext.getFunctionAddress("glVertexAttrib4NubARB")) != 0L ? 1 : 0) & ((this.glVertexAttribPointerARB = GLContext.getFunctionAddress("glVertexAttribPointerARB")) != 0L ? 1 : 0) & ((this.glEnableVertexAttribArrayARB = GLContext.getFunctionAddress("glEnableVertexAttribArrayARB")) != 0L ? 1 : 0) & ((this.glDisableVertexAttribArrayARB = GLContext.getFunctionAddress("glDisableVertexAttribArrayARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribfvARB = GLContext.getFunctionAddress("glGetVertexAttribfvARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribdvARB = GLContext.getFunctionAddress("glGetVertexAttribdvARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribivARB = GLContext.getFunctionAddress("glGetVertexAttribivARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribPointervARB = GLContext.getFunctionAddress("glGetVertexAttribPointervARB")) != 0L ? 1 : 0);
/* 3212:     */  }
/* 3213:     */  
/* 3233:     */  private boolean ARB_vertex_shader_initNativeFunctionAddresses()
/* 3234:     */  {
/* 3235:3235 */    return ((this.glVertexAttrib1sARB = GLContext.getFunctionAddress("glVertexAttrib1sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib1fARB = GLContext.getFunctionAddress("glVertexAttrib1fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib1dARB = GLContext.getFunctionAddress("glVertexAttrib1dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib2sARB = GLContext.getFunctionAddress("glVertexAttrib2sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib2fARB = GLContext.getFunctionAddress("glVertexAttrib2fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib2dARB = GLContext.getFunctionAddress("glVertexAttrib2dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib3sARB = GLContext.getFunctionAddress("glVertexAttrib3sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib3fARB = GLContext.getFunctionAddress("glVertexAttrib3fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib3dARB = GLContext.getFunctionAddress("glVertexAttrib3dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4sARB = GLContext.getFunctionAddress("glVertexAttrib4sARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4fARB = GLContext.getFunctionAddress("glVertexAttrib4fARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4dARB = GLContext.getFunctionAddress("glVertexAttrib4dARB")) != 0L ? 1 : 0) & ((this.glVertexAttrib4NubARB = GLContext.getFunctionAddress("glVertexAttrib4NubARB")) != 0L ? 1 : 0) & ((this.glVertexAttribPointerARB = GLContext.getFunctionAddress("glVertexAttribPointerARB")) != 0L ? 1 : 0) & ((this.glEnableVertexAttribArrayARB = GLContext.getFunctionAddress("glEnableVertexAttribArrayARB")) != 0L ? 1 : 0) & ((this.glDisableVertexAttribArrayARB = GLContext.getFunctionAddress("glDisableVertexAttribArrayARB")) != 0L ? 1 : 0) & ((this.glBindAttribLocationARB = GLContext.getFunctionAddress("glBindAttribLocationARB")) != 0L ? 1 : 0) & ((this.glGetActiveAttribARB = GLContext.getFunctionAddress("glGetActiveAttribARB")) != 0L ? 1 : 0) & ((this.glGetAttribLocationARB = GLContext.getFunctionAddress("glGetAttribLocationARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribfvARB = GLContext.getFunctionAddress("glGetVertexAttribfvARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribdvARB = GLContext.getFunctionAddress("glGetVertexAttribdvARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribivARB = GLContext.getFunctionAddress("glGetVertexAttribivARB")) != 0L ? 1 : 0) & ((this.glGetVertexAttribPointervARB = GLContext.getFunctionAddress("glGetVertexAttribPointervARB")) != 0L ? 1 : 0);
/* 3236:     */  }
/* 3237:     */  
/* 3260:     */  private boolean ARB_vertex_type_2_10_10_10_rev_initNativeFunctionAddresses()
/* 3261:     */  {
/* 3262:3262 */    return ((this.glVertexP2ui = GLContext.getFunctionAddress("glVertexP2ui")) != 0L ? 1 : 0) & ((this.glVertexP3ui = GLContext.getFunctionAddress("glVertexP3ui")) != 0L ? 1 : 0) & ((this.glVertexP4ui = GLContext.getFunctionAddress("glVertexP4ui")) != 0L ? 1 : 0) & ((this.glVertexP2uiv = GLContext.getFunctionAddress("glVertexP2uiv")) != 0L ? 1 : 0) & ((this.glVertexP3uiv = GLContext.getFunctionAddress("glVertexP3uiv")) != 0L ? 1 : 0) & ((this.glVertexP4uiv = GLContext.getFunctionAddress("glVertexP4uiv")) != 0L ? 1 : 0) & ((this.glTexCoordP1ui = GLContext.getFunctionAddress("glTexCoordP1ui")) != 0L ? 1 : 0) & ((this.glTexCoordP2ui = GLContext.getFunctionAddress("glTexCoordP2ui")) != 0L ? 1 : 0) & ((this.glTexCoordP3ui = GLContext.getFunctionAddress("glTexCoordP3ui")) != 0L ? 1 : 0) & ((this.glTexCoordP4ui = GLContext.getFunctionAddress("glTexCoordP4ui")) != 0L ? 1 : 0) & ((this.glTexCoordP1uiv = GLContext.getFunctionAddress("glTexCoordP1uiv")) != 0L ? 1 : 0) & ((this.glTexCoordP2uiv = GLContext.getFunctionAddress("glTexCoordP2uiv")) != 0L ? 1 : 0) & ((this.glTexCoordP3uiv = GLContext.getFunctionAddress("glTexCoordP3uiv")) != 0L ? 1 : 0) & ((this.glTexCoordP4uiv = GLContext.getFunctionAddress("glTexCoordP4uiv")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP1ui = GLContext.getFunctionAddress("glMultiTexCoordP1ui")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP2ui = GLContext.getFunctionAddress("glMultiTexCoordP2ui")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP3ui = GLContext.getFunctionAddress("glMultiTexCoordP3ui")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP4ui = GLContext.getFunctionAddress("glMultiTexCoordP4ui")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP1uiv = GLContext.getFunctionAddress("glMultiTexCoordP1uiv")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP2uiv = GLContext.getFunctionAddress("glMultiTexCoordP2uiv")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP3uiv = GLContext.getFunctionAddress("glMultiTexCoordP3uiv")) != 0L ? 1 : 0) & ((this.glMultiTexCoordP4uiv = GLContext.getFunctionAddress("glMultiTexCoordP4uiv")) != 0L ? 1 : 0) & ((this.glNormalP3ui = GLContext.getFunctionAddress("glNormalP3ui")) != 0L ? 1 : 0) & ((this.glNormalP3uiv = GLContext.getFunctionAddress("glNormalP3uiv")) != 0L ? 1 : 0) & ((this.glColorP3ui = GLContext.getFunctionAddress("glColorP3ui")) != 0L ? 1 : 0) & ((this.glColorP4ui = GLContext.getFunctionAddress("glColorP4ui")) != 0L ? 1 : 0) & ((this.glColorP3uiv = GLContext.getFunctionAddress("glColorP3uiv")) != 0L ? 1 : 0) & ((this.glColorP4uiv = GLContext.getFunctionAddress("glColorP4uiv")) != 0L ? 1 : 0) & ((this.glSecondaryColorP3ui = GLContext.getFunctionAddress("glSecondaryColorP3ui")) != 0L ? 1 : 0) & ((this.glSecondaryColorP3uiv = GLContext.getFunctionAddress("glSecondaryColorP3uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribP1ui = GLContext.getFunctionAddress("glVertexAttribP1ui")) != 0L ? 1 : 0) & ((this.glVertexAttribP2ui = GLContext.getFunctionAddress("glVertexAttribP2ui")) != 0L ? 1 : 0) & ((this.glVertexAttribP3ui = GLContext.getFunctionAddress("glVertexAttribP3ui")) != 0L ? 1 : 0) & ((this.glVertexAttribP4ui = GLContext.getFunctionAddress("glVertexAttribP4ui")) != 0L ? 1 : 0) & ((this.glVertexAttribP1uiv = GLContext.getFunctionAddress("glVertexAttribP1uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribP2uiv = GLContext.getFunctionAddress("glVertexAttribP2uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribP3uiv = GLContext.getFunctionAddress("glVertexAttribP3uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribP4uiv = GLContext.getFunctionAddress("glVertexAttribP4uiv")) != 0L ? 1 : 0);
/* 3263:     */  }
/* 3264:     */  
/* 3302:     */  private boolean ARB_viewport_array_initNativeFunctionAddresses()
/* 3303:     */  {
/* 3304:3304 */    return ((this.glViewportArrayv = GLContext.getFunctionAddress("glViewportArrayv")) != 0L ? 1 : 0) & ((this.glViewportIndexedf = GLContext.getFunctionAddress("glViewportIndexedf")) != 0L ? 1 : 0) & ((this.glViewportIndexedfv = GLContext.getFunctionAddress("glViewportIndexedfv")) != 0L ? 1 : 0) & ((this.glScissorArrayv = GLContext.getFunctionAddress("glScissorArrayv")) != 0L ? 1 : 0) & ((this.glScissorIndexed = GLContext.getFunctionAddress("glScissorIndexed")) != 0L ? 1 : 0) & ((this.glScissorIndexedv = GLContext.getFunctionAddress("glScissorIndexedv")) != 0L ? 1 : 0) & ((this.glDepthRangeArrayv = GLContext.getFunctionAddress("glDepthRangeArrayv")) != 0L ? 1 : 0) & ((this.glDepthRangeIndexed = GLContext.getFunctionAddress("glDepthRangeIndexed")) != 0L ? 1 : 0) & ((this.glGetFloati_v = GLContext.getFunctionAddress("glGetFloati_v")) != 0L ? 1 : 0) & ((this.glGetDoublei_v = GLContext.getFunctionAddress("glGetDoublei_v")) != 0L ? 1 : 0) & ((this.glGetIntegerIndexedvEXT = GLContext.getFunctionAddress("glGetIntegerIndexedvEXT")) != 0L ? 1 : 0) & ((this.glEnableIndexedEXT = GLContext.getFunctionAddress("glEnableIndexedEXT")) != 0L ? 1 : 0) & ((this.glDisableIndexedEXT = GLContext.getFunctionAddress("glDisableIndexedEXT")) != 0L ? 1 : 0) & ((this.glIsEnabledIndexedEXT = GLContext.getFunctionAddress("glIsEnabledIndexedEXT")) != 0L ? 1 : 0);
/* 3305:     */  }
/* 3306:     */  
/* 3320:     */  private boolean ARB_window_pos_initNativeFunctionAddresses(boolean forwardCompatible)
/* 3321:     */  {
/* 3322:3322 */    return ((forwardCompatible) || ((this.glWindowPos2fARB = GLContext.getFunctionAddress("glWindowPos2fARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos2dARB = GLContext.getFunctionAddress("glWindowPos2dARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos2iARB = GLContext.getFunctionAddress("glWindowPos2iARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos2sARB = GLContext.getFunctionAddress("glWindowPos2sARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos3fARB = GLContext.getFunctionAddress("glWindowPos3fARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos3dARB = GLContext.getFunctionAddress("glWindowPos3dARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos3iARB = GLContext.getFunctionAddress("glWindowPos3iARB")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos3sARB = GLContext.getFunctionAddress("glWindowPos3sARB")) != 0L) ? 1 : 0);
/* 3323:     */  }
/* 3324:     */  
/* 3332:     */  private boolean ATI_draw_buffers_initNativeFunctionAddresses()
/* 3333:     */  {
/* 3334:3334 */    return (this.glDrawBuffersATI = GLContext.getFunctionAddress("glDrawBuffersATI")) != 0L;
/* 3335:     */  }
/* 3336:     */  
/* 3337:     */  private boolean ATI_element_array_initNativeFunctionAddresses()
/* 3338:     */  {
/* 3339:3339 */    return ((this.glElementPointerATI = GLContext.getFunctionAddress("glElementPointerATI")) != 0L ? 1 : 0) & ((this.glDrawElementArrayATI = GLContext.getFunctionAddress("glDrawElementArrayATI")) != 0L ? 1 : 0) & ((this.glDrawRangeElementArrayATI = GLContext.getFunctionAddress("glDrawRangeElementArrayATI")) != 0L ? 1 : 0);
/* 3340:     */  }
/* 3341:     */  
/* 3344:     */  private boolean ATI_envmap_bumpmap_initNativeFunctionAddresses()
/* 3345:     */  {
/* 3346:3346 */    return ((this.glTexBumpParameterfvATI = GLContext.getFunctionAddress("glTexBumpParameterfvATI")) != 0L ? 1 : 0) & ((this.glTexBumpParameterivATI = GLContext.getFunctionAddress("glTexBumpParameterivATI")) != 0L ? 1 : 0) & ((this.glGetTexBumpParameterfvATI = GLContext.getFunctionAddress("glGetTexBumpParameterfvATI")) != 0L ? 1 : 0) & ((this.glGetTexBumpParameterivATI = GLContext.getFunctionAddress("glGetTexBumpParameterivATI")) != 0L ? 1 : 0);
/* 3347:     */  }
/* 3348:     */  
/* 3352:     */  private boolean ATI_fragment_shader_initNativeFunctionAddresses()
/* 3353:     */  {
/* 3354:3354 */    return ((this.glGenFragmentShadersATI = GLContext.getFunctionAddress("glGenFragmentShadersATI")) != 0L ? 1 : 0) & ((this.glBindFragmentShaderATI = GLContext.getFunctionAddress("glBindFragmentShaderATI")) != 0L ? 1 : 0) & ((this.glDeleteFragmentShaderATI = GLContext.getFunctionAddress("glDeleteFragmentShaderATI")) != 0L ? 1 : 0) & ((this.glBeginFragmentShaderATI = GLContext.getFunctionAddress("glBeginFragmentShaderATI")) != 0L ? 1 : 0) & ((this.glEndFragmentShaderATI = GLContext.getFunctionAddress("glEndFragmentShaderATI")) != 0L ? 1 : 0) & ((this.glPassTexCoordATI = GLContext.getFunctionAddress("glPassTexCoordATI")) != 0L ? 1 : 0) & ((this.glSampleMapATI = GLContext.getFunctionAddress("glSampleMapATI")) != 0L ? 1 : 0) & ((this.glColorFragmentOp1ATI = GLContext.getFunctionAddress("glColorFragmentOp1ATI")) != 0L ? 1 : 0) & ((this.glColorFragmentOp2ATI = GLContext.getFunctionAddress("glColorFragmentOp2ATI")) != 0L ? 1 : 0) & ((this.glColorFragmentOp3ATI = GLContext.getFunctionAddress("glColorFragmentOp3ATI")) != 0L ? 1 : 0) & ((this.glAlphaFragmentOp1ATI = GLContext.getFunctionAddress("glAlphaFragmentOp1ATI")) != 0L ? 1 : 0) & ((this.glAlphaFragmentOp2ATI = GLContext.getFunctionAddress("glAlphaFragmentOp2ATI")) != 0L ? 1 : 0) & ((this.glAlphaFragmentOp3ATI = GLContext.getFunctionAddress("glAlphaFragmentOp3ATI")) != 0L ? 1 : 0) & ((this.glSetFragmentShaderConstantATI = GLContext.getFunctionAddress("glSetFragmentShaderConstantATI")) != 0L ? 1 : 0);
/* 3355:     */  }
/* 3356:     */  
/* 3370:     */  private boolean ATI_map_object_buffer_initNativeFunctionAddresses()
/* 3371:     */  {
/* 3372:3372 */    return ((this.glMapObjectBufferATI = GLContext.getFunctionAddress("glMapObjectBufferATI")) != 0L ? 1 : 0) & ((this.glUnmapObjectBufferATI = GLContext.getFunctionAddress("glUnmapObjectBufferATI")) != 0L ? 1 : 0);
/* 3373:     */  }
/* 3374:     */  
/* 3376:     */  private boolean ATI_pn_triangles_initNativeFunctionAddresses()
/* 3377:     */  {
/* 3378:3378 */    return ((this.glPNTrianglesfATI = GLContext.getFunctionAddress("glPNTrianglesfATI")) != 0L ? 1 : 0) & ((this.glPNTrianglesiATI = GLContext.getFunctionAddress("glPNTrianglesiATI")) != 0L ? 1 : 0);
/* 3379:     */  }
/* 3380:     */  
/* 3382:     */  private boolean ATI_separate_stencil_initNativeFunctionAddresses()
/* 3383:     */  {
/* 3384:3384 */    return ((this.glStencilOpSeparateATI = GLContext.getFunctionAddress("glStencilOpSeparateATI")) != 0L ? 1 : 0) & ((this.glStencilFuncSeparateATI = GLContext.getFunctionAddress("glStencilFuncSeparateATI")) != 0L ? 1 : 0);
/* 3385:     */  }
/* 3386:     */  
/* 3388:     */  private boolean ATI_vertex_array_object_initNativeFunctionAddresses()
/* 3389:     */  {
/* 3390:3390 */    return ((this.glNewObjectBufferATI = GLContext.getFunctionAddress("glNewObjectBufferATI")) != 0L ? 1 : 0) & ((this.glIsObjectBufferATI = GLContext.getFunctionAddress("glIsObjectBufferATI")) != 0L ? 1 : 0) & ((this.glUpdateObjectBufferATI = GLContext.getFunctionAddress("glUpdateObjectBufferATI")) != 0L ? 1 : 0) & ((this.glGetObjectBufferfvATI = GLContext.getFunctionAddress("glGetObjectBufferfvATI")) != 0L ? 1 : 0) & ((this.glGetObjectBufferivATI = GLContext.getFunctionAddress("glGetObjectBufferivATI")) != 0L ? 1 : 0) & ((this.glFreeObjectBufferATI = GLContext.getFunctionAddress("glFreeObjectBufferATI")) != 0L ? 1 : 0) & ((this.glArrayObjectATI = GLContext.getFunctionAddress("glArrayObjectATI")) != 0L ? 1 : 0) & ((this.glGetArrayObjectfvATI = GLContext.getFunctionAddress("glGetArrayObjectfvATI")) != 0L ? 1 : 0) & ((this.glGetArrayObjectivATI = GLContext.getFunctionAddress("glGetArrayObjectivATI")) != 0L ? 1 : 0) & ((this.glVariantArrayObjectATI = GLContext.getFunctionAddress("glVariantArrayObjectATI")) != 0L ? 1 : 0) & ((this.glGetVariantArrayObjectfvATI = GLContext.getFunctionAddress("glGetVariantArrayObjectfvATI")) != 0L ? 1 : 0) & ((this.glGetVariantArrayObjectivATI = GLContext.getFunctionAddress("glGetVariantArrayObjectivATI")) != 0L ? 1 : 0);
/* 3391:     */  }
/* 3392:     */  
/* 3404:     */  private boolean ATI_vertex_attrib_array_object_initNativeFunctionAddresses()
/* 3405:     */  {
/* 3406:3406 */    return ((this.glVertexAttribArrayObjectATI = GLContext.getFunctionAddress("glVertexAttribArrayObjectATI")) != 0L ? 1 : 0) & ((this.glGetVertexAttribArrayObjectfvATI = GLContext.getFunctionAddress("glGetVertexAttribArrayObjectfvATI")) != 0L ? 1 : 0) & ((this.glGetVertexAttribArrayObjectivATI = GLContext.getFunctionAddress("glGetVertexAttribArrayObjectivATI")) != 0L ? 1 : 0);
/* 3407:     */  }
/* 3408:     */  
/* 3411:     */  private boolean ATI_vertex_streams_initNativeFunctionAddresses()
/* 3412:     */  {
/* 3413:3413 */    return ((this.glVertexStream2fATI = GLContext.getFunctionAddress("glVertexStream2fATI")) != 0L ? 1 : 0) & ((this.glVertexStream2dATI = GLContext.getFunctionAddress("glVertexStream2dATI")) != 0L ? 1 : 0) & ((this.glVertexStream2iATI = GLContext.getFunctionAddress("glVertexStream2iATI")) != 0L ? 1 : 0) & ((this.glVertexStream2sATI = GLContext.getFunctionAddress("glVertexStream2sATI")) != 0L ? 1 : 0) & ((this.glVertexStream3fATI = GLContext.getFunctionAddress("glVertexStream3fATI")) != 0L ? 1 : 0) & ((this.glVertexStream3dATI = GLContext.getFunctionAddress("glVertexStream3dATI")) != 0L ? 1 : 0) & ((this.glVertexStream3iATI = GLContext.getFunctionAddress("glVertexStream3iATI")) != 0L ? 1 : 0) & ((this.glVertexStream3sATI = GLContext.getFunctionAddress("glVertexStream3sATI")) != 0L ? 1 : 0) & ((this.glVertexStream4fATI = GLContext.getFunctionAddress("glVertexStream4fATI")) != 0L ? 1 : 0) & ((this.glVertexStream4dATI = GLContext.getFunctionAddress("glVertexStream4dATI")) != 0L ? 1 : 0) & ((this.glVertexStream4iATI = GLContext.getFunctionAddress("glVertexStream4iATI")) != 0L ? 1 : 0) & ((this.glVertexStream4sATI = GLContext.getFunctionAddress("glVertexStream4sATI")) != 0L ? 1 : 0) & ((this.glNormalStream3bATI = GLContext.getFunctionAddress("glNormalStream3bATI")) != 0L ? 1 : 0) & ((this.glNormalStream3fATI = GLContext.getFunctionAddress("glNormalStream3fATI")) != 0L ? 1 : 0) & ((this.glNormalStream3dATI = GLContext.getFunctionAddress("glNormalStream3dATI")) != 0L ? 1 : 0) & ((this.glNormalStream3iATI = GLContext.getFunctionAddress("glNormalStream3iATI")) != 0L ? 1 : 0) & ((this.glNormalStream3sATI = GLContext.getFunctionAddress("glNormalStream3sATI")) != 0L ? 1 : 0) & ((this.glClientActiveVertexStreamATI = GLContext.getFunctionAddress("glClientActiveVertexStreamATI")) != 0L ? 1 : 0) & ((this.glVertexBlendEnvfATI = GLContext.getFunctionAddress("glVertexBlendEnvfATI")) != 0L ? 1 : 0) & ((this.glVertexBlendEnviATI = GLContext.getFunctionAddress("glVertexBlendEnviATI")) != 0L ? 1 : 0);
/* 3414:     */  }
/* 3415:     */  
/* 3435:     */  private boolean EXT_bindable_uniform_initNativeFunctionAddresses()
/* 3436:     */  {
/* 3437:3437 */    return ((this.glUniformBufferEXT = GLContext.getFunctionAddress("glUniformBufferEXT")) != 0L ? 1 : 0) & ((this.glGetUniformBufferSizeEXT = GLContext.getFunctionAddress("glGetUniformBufferSizeEXT")) != 0L ? 1 : 0) & ((this.glGetUniformOffsetEXT = GLContext.getFunctionAddress("glGetUniformOffsetEXT")) != 0L ? 1 : 0);
/* 3438:     */  }
/* 3439:     */  
/* 3442:     */  private boolean EXT_blend_color_initNativeFunctionAddresses()
/* 3443:     */  {
/* 3444:3444 */    return (this.glBlendColorEXT = GLContext.getFunctionAddress("glBlendColorEXT")) != 0L;
/* 3445:     */  }
/* 3446:     */  
/* 3447:     */  private boolean EXT_blend_equation_separate_initNativeFunctionAddresses()
/* 3448:     */  {
/* 3449:3449 */    return (this.glBlendEquationSeparateEXT = GLContext.getFunctionAddress("glBlendEquationSeparateEXT")) != 0L;
/* 3450:     */  }
/* 3451:     */  
/* 3452:     */  private boolean EXT_blend_func_separate_initNativeFunctionAddresses()
/* 3453:     */  {
/* 3454:3454 */    return (this.glBlendFuncSeparateEXT = GLContext.getFunctionAddress("glBlendFuncSeparateEXT")) != 0L;
/* 3455:     */  }
/* 3456:     */  
/* 3457:     */  private boolean EXT_blend_minmax_initNativeFunctionAddresses()
/* 3458:     */  {
/* 3459:3459 */    return (this.glBlendEquationEXT = GLContext.getFunctionAddress("glBlendEquationEXT")) != 0L;
/* 3460:     */  }
/* 3461:     */  
/* 3462:     */  private boolean EXT_compiled_vertex_array_initNativeFunctionAddresses()
/* 3463:     */  {
/* 3464:3464 */    return ((this.glLockArraysEXT = GLContext.getFunctionAddress("glLockArraysEXT")) != 0L ? 1 : 0) & ((this.glUnlockArraysEXT = GLContext.getFunctionAddress("glUnlockArraysEXT")) != 0L ? 1 : 0);
/* 3465:     */  }
/* 3466:     */  
/* 3468:     */  private boolean EXT_depth_bounds_test_initNativeFunctionAddresses()
/* 3469:     */  {
/* 3470:3470 */    return (this.glDepthBoundsEXT = GLContext.getFunctionAddress("glDepthBoundsEXT")) != 0L;
/* 3471:     */  }
/* 3472:     */  
/* 3473:     */  private boolean EXT_direct_state_access_initNativeFunctionAddresses(boolean forwardCompatible, Set<String> supported_extensions)
/* 3474:     */  {
/* 3475:3475 */    return ((forwardCompatible) || ((this.glClientAttribDefaultEXT = GLContext.getFunctionAddress("glClientAttribDefaultEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPushClientAttribDefaultEXT = GLContext.getFunctionAddress("glPushClientAttribDefaultEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixLoadfEXT = GLContext.getFunctionAddress("glMatrixLoadfEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixLoaddEXT = GLContext.getFunctionAddress("glMatrixLoaddEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixMultfEXT = GLContext.getFunctionAddress("glMatrixMultfEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixMultdEXT = GLContext.getFunctionAddress("glMatrixMultdEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixLoadIdentityEXT = GLContext.getFunctionAddress("glMatrixLoadIdentityEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixRotatefEXT = GLContext.getFunctionAddress("glMatrixRotatefEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixRotatedEXT = GLContext.getFunctionAddress("glMatrixRotatedEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixScalefEXT = GLContext.getFunctionAddress("glMatrixScalefEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixScaledEXT = GLContext.getFunctionAddress("glMatrixScaledEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixTranslatefEXT = GLContext.getFunctionAddress("glMatrixTranslatefEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixTranslatedEXT = GLContext.getFunctionAddress("glMatrixTranslatedEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixOrthoEXT = GLContext.getFunctionAddress("glMatrixOrthoEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixFrustumEXT = GLContext.getFunctionAddress("glMatrixFrustumEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixPushEXT = GLContext.getFunctionAddress("glMatrixPushEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixPopEXT = GLContext.getFunctionAddress("glMatrixPopEXT")) != 0L) ? 1 : 0) & ((this.glTextureParameteriEXT = GLContext.getFunctionAddress("glTextureParameteriEXT")) != 0L ? 1 : 0) & ((this.glTextureParameterivEXT = GLContext.getFunctionAddress("glTextureParameterivEXT")) != 0L ? 1 : 0) & ((this.glTextureParameterfEXT = GLContext.getFunctionAddress("glTextureParameterfEXT")) != 0L ? 1 : 0) & ((this.glTextureParameterfvEXT = GLContext.getFunctionAddress("glTextureParameterfvEXT")) != 0L ? 1 : 0) & ((this.glTextureImage1DEXT = GLContext.getFunctionAddress("glTextureImage1DEXT")) != 0L ? 1 : 0) & ((this.glTextureImage2DEXT = GLContext.getFunctionAddress("glTextureImage2DEXT")) != 0L ? 1 : 0) & ((this.glTextureSubImage1DEXT = GLContext.getFunctionAddress("glTextureSubImage1DEXT")) != 0L ? 1 : 0) & ((this.glTextureSubImage2DEXT = GLContext.getFunctionAddress("glTextureSubImage2DEXT")) != 0L ? 1 : 0) & ((this.glCopyTextureImage1DEXT = GLContext.getFunctionAddress("glCopyTextureImage1DEXT")) != 0L ? 1 : 0) & ((this.glCopyTextureImage2DEXT = GLContext.getFunctionAddress("glCopyTextureImage2DEXT")) != 0L ? 1 : 0) & ((this.glCopyTextureSubImage1DEXT = GLContext.getFunctionAddress("glCopyTextureSubImage1DEXT")) != 0L ? 1 : 0) & ((this.glCopyTextureSubImage2DEXT = GLContext.getFunctionAddress("glCopyTextureSubImage2DEXT")) != 0L ? 1 : 0) & ((this.glGetTextureImageEXT = GLContext.getFunctionAddress("glGetTextureImageEXT")) != 0L ? 1 : 0) & ((this.glGetTextureParameterfvEXT = GLContext.getFunctionAddress("glGetTextureParameterfvEXT")) != 0L ? 1 : 0) & ((this.glGetTextureParameterivEXT = GLContext.getFunctionAddress("glGetTextureParameterivEXT")) != 0L ? 1 : 0) & ((this.glGetTextureLevelParameterfvEXT = GLContext.getFunctionAddress("glGetTextureLevelParameterfvEXT")) != 0L ? 1 : 0) & ((this.glGetTextureLevelParameterivEXT = GLContext.getFunctionAddress("glGetTextureLevelParameterivEXT")) != 0L ? 1 : 0) & ((!supported_extensions.contains("OpenGL12")) || ((this.glTextureImage3DEXT = GLContext.getFunctionAddress("glTextureImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL12")) || ((this.glTextureSubImage3DEXT = GLContext.getFunctionAddress("glTextureSubImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL12")) || ((this.glCopyTextureSubImage3DEXT = GLContext.getFunctionAddress("glCopyTextureSubImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glBindMultiTextureEXT = GLContext.getFunctionAddress("glBindMultiTextureEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexCoordPointerEXT = GLContext.getFunctionAddress("glMultiTexCoordPointerEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexEnvfEXT = GLContext.getFunctionAddress("glMultiTexEnvfEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexEnvfvEXT = GLContext.getFunctionAddress("glMultiTexEnvfvEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexEnviEXT = GLContext.getFunctionAddress("glMultiTexEnviEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexEnvivEXT = GLContext.getFunctionAddress("glMultiTexEnvivEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexGendEXT = GLContext.getFunctionAddress("glMultiTexGendEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexGendvEXT = GLContext.getFunctionAddress("glMultiTexGendvEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexGenfEXT = GLContext.getFunctionAddress("glMultiTexGenfEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexGenfvEXT = GLContext.getFunctionAddress("glMultiTexGenfvEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexGeniEXT = GLContext.getFunctionAddress("glMultiTexGeniEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexGenivEXT = GLContext.getFunctionAddress("glMultiTexGenivEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexEnvfvEXT = GLContext.getFunctionAddress("glGetMultiTexEnvfvEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexEnvivEXT = GLContext.getFunctionAddress("glGetMultiTexEnvivEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexGendvEXT = GLContext.getFunctionAddress("glGetMultiTexGendvEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexGenfvEXT = GLContext.getFunctionAddress("glGetMultiTexGenfvEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexGenivEXT = GLContext.getFunctionAddress("glGetMultiTexGenivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexParameteriEXT = GLContext.getFunctionAddress("glMultiTexParameteriEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexParameterivEXT = GLContext.getFunctionAddress("glMultiTexParameterivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexParameterfEXT = GLContext.getFunctionAddress("glMultiTexParameterfEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexParameterfvEXT = GLContext.getFunctionAddress("glMultiTexParameterfvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexImage1DEXT = GLContext.getFunctionAddress("glMultiTexImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexImage2DEXT = GLContext.getFunctionAddress("glMultiTexImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexSubImage1DEXT = GLContext.getFunctionAddress("glMultiTexSubImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexSubImage2DEXT = GLContext.getFunctionAddress("glMultiTexSubImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCopyMultiTexImage1DEXT = GLContext.getFunctionAddress("glCopyMultiTexImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCopyMultiTexImage2DEXT = GLContext.getFunctionAddress("glCopyMultiTexImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCopyMultiTexSubImage1DEXT = GLContext.getFunctionAddress("glCopyMultiTexSubImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCopyMultiTexSubImage2DEXT = GLContext.getFunctionAddress("glCopyMultiTexSubImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexImageEXT = GLContext.getFunctionAddress("glGetMultiTexImageEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexParameterfvEXT = GLContext.getFunctionAddress("glGetMultiTexParameterfvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexParameterivEXT = GLContext.getFunctionAddress("glGetMultiTexParameterivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexLevelParameterfvEXT = GLContext.getFunctionAddress("glGetMultiTexLevelParameterfvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetMultiTexLevelParameterivEXT = GLContext.getFunctionAddress("glGetMultiTexLevelParameterivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexImage3DEXT = GLContext.getFunctionAddress("glMultiTexImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glMultiTexSubImage3DEXT = GLContext.getFunctionAddress("glMultiTexSubImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCopyMultiTexSubImage3DEXT = GLContext.getFunctionAddress("glCopyMultiTexSubImage3DEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glEnableClientStateIndexedEXT = GLContext.getFunctionAddress("glEnableClientStateIndexedEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glDisableClientStateIndexedEXT = GLContext.getFunctionAddress("glDisableClientStateIndexedEXT")) != 0L) ? 1 : 0) & 0x1 & 0x1 & (((!supported_extensions.contains("OpenGL30")) || ((this.glEnableClientStateiEXT = GLContext.getFunctionAddress("glEnableClientStateiEXT")) != 0L)) || (((!supported_extensions.contains("OpenGL30")) || ((this.glDisableClientStateiEXT = GLContext.getFunctionAddress("glDisableClientStateiEXT")) != 0L)) || ((!supported_extensions.contains("OpenGL13")) || ((this.glGetFloatIndexedvEXT = GLContext.getFunctionAddress("glGetFloatIndexedvEXT")) != 0L))) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetDoubleIndexedvEXT = GLContext.getFunctionAddress("glGetDoubleIndexedvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetPointerIndexedvEXT = GLContext.getFunctionAddress("glGetPointerIndexedvEXT")) != 0L) ? 1 : 0) & 0x1 & 0x1 & 0x1 & (((!supported_extensions.contains("OpenGL30")) || ((this.glGetFloati_vEXT = GLContext.getFunctionAddress("glGetFloati_vEXT")) != 0L)) || (((!supported_extensions.contains("OpenGL30")) || ((this.glGetDoublei_vEXT = GLContext.getFunctionAddress("glGetDoublei_vEXT")) != 0L)) || (((!supported_extensions.contains("OpenGL30")) || ((this.glGetPointeri_vEXT = GLContext.getFunctionAddress("glGetPointeri_vEXT")) != 0L)) || ((!supported_extensions.contains("OpenGL13")) || ((this.glEnableIndexedEXT = GLContext.getFunctionAddress("glEnableIndexedEXT")) != 0L)))) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glDisableIndexedEXT = GLContext.getFunctionAddress("glDisableIndexedEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glIsEnabledIndexedEXT = GLContext.getFunctionAddress("glIsEnabledIndexedEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetIntegerIndexedvEXT = GLContext.getFunctionAddress("glGetIntegerIndexedvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetBooleanIndexedvEXT = GLContext.getFunctionAddress("glGetBooleanIndexedvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glNamedProgramStringEXT = GLContext.getFunctionAddress("glNamedProgramStringEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glNamedProgramLocalParameter4dEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameter4dEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glNamedProgramLocalParameter4dvEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameter4dvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glNamedProgramLocalParameter4fEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameter4fEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glNamedProgramLocalParameter4fvEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameter4fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glGetNamedProgramLocalParameterdvEXT = GLContext.getFunctionAddress("glGetNamedProgramLocalParameterdvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glGetNamedProgramLocalParameterfvEXT = GLContext.getFunctionAddress("glGetNamedProgramLocalParameterfvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glGetNamedProgramivEXT = GLContext.getFunctionAddress("glGetNamedProgramivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_ARB_vertex_program")) || ((this.glGetNamedProgramStringEXT = GLContext.getFunctionAddress("glGetNamedProgramStringEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedTextureImage3DEXT = GLContext.getFunctionAddress("glCompressedTextureImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedTextureImage2DEXT = GLContext.getFunctionAddress("glCompressedTextureImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedTextureImage1DEXT = GLContext.getFunctionAddress("glCompressedTextureImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedTextureSubImage3DEXT = GLContext.getFunctionAddress("glCompressedTextureSubImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedTextureSubImage2DEXT = GLContext.getFunctionAddress("glCompressedTextureSubImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedTextureSubImage1DEXT = GLContext.getFunctionAddress("glCompressedTextureSubImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetCompressedTextureImageEXT = GLContext.getFunctionAddress("glGetCompressedTextureImageEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedMultiTexImage3DEXT = GLContext.getFunctionAddress("glCompressedMultiTexImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedMultiTexImage2DEXT = GLContext.getFunctionAddress("glCompressedMultiTexImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedMultiTexImage1DEXT = GLContext.getFunctionAddress("glCompressedMultiTexImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedMultiTexSubImage3DEXT = GLContext.getFunctionAddress("glCompressedMultiTexSubImage3DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedMultiTexSubImage2DEXT = GLContext.getFunctionAddress("glCompressedMultiTexSubImage2DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glCompressedMultiTexSubImage1DEXT = GLContext.getFunctionAddress("glCompressedMultiTexSubImage1DEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL13")) || ((this.glGetCompressedMultiTexImageEXT = GLContext.getFunctionAddress("glGetCompressedMultiTexImageEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMatrixLoadTransposefEXT = GLContext.getFunctionAddress("glMatrixLoadTransposefEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMatrixLoadTransposedEXT = GLContext.getFunctionAddress("glMatrixLoadTransposedEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMatrixMultTransposefEXT = GLContext.getFunctionAddress("glMatrixMultTransposefEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL13")) || ((this.glMatrixMultTransposedEXT = GLContext.getFunctionAddress("glMatrixMultTransposedEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL15")) || ((this.glNamedBufferDataEXT = GLContext.getFunctionAddress("glNamedBufferDataEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL15")) || ((this.glNamedBufferSubDataEXT = GLContext.getFunctionAddress("glNamedBufferSubDataEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL15")) || ((this.glMapNamedBufferEXT = GLContext.getFunctionAddress("glMapNamedBufferEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL15")) || ((this.glUnmapNamedBufferEXT = GLContext.getFunctionAddress("glUnmapNamedBufferEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL15")) || ((this.glGetNamedBufferParameterivEXT = GLContext.getFunctionAddress("glGetNamedBufferParameterivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL15")) || ((this.glGetNamedBufferPointervEXT = GLContext.getFunctionAddress("glGetNamedBufferPointervEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL15")) || ((this.glGetNamedBufferSubDataEXT = GLContext.getFunctionAddress("glGetNamedBufferSubDataEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform1fEXT = GLContext.getFunctionAddress("glProgramUniform1fEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform2fEXT = GLContext.getFunctionAddress("glProgramUniform2fEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform3fEXT = GLContext.getFunctionAddress("glProgramUniform3fEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform4fEXT = GLContext.getFunctionAddress("glProgramUniform4fEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform1iEXT = GLContext.getFunctionAddress("glProgramUniform1iEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform2iEXT = GLContext.getFunctionAddress("glProgramUniform2iEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform3iEXT = GLContext.getFunctionAddress("glProgramUniform3iEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform4iEXT = GLContext.getFunctionAddress("glProgramUniform4iEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform1fvEXT = GLContext.getFunctionAddress("glProgramUniform1fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform2fvEXT = GLContext.getFunctionAddress("glProgramUniform2fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform3fvEXT = GLContext.getFunctionAddress("glProgramUniform3fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform4fvEXT = GLContext.getFunctionAddress("glProgramUniform4fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform1ivEXT = GLContext.getFunctionAddress("glProgramUniform1ivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform2ivEXT = GLContext.getFunctionAddress("glProgramUniform2ivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform3ivEXT = GLContext.getFunctionAddress("glProgramUniform3ivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniform4ivEXT = GLContext.getFunctionAddress("glProgramUniform4ivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniformMatrix2fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix2fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniformMatrix3fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix3fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL20")) || ((this.glProgramUniformMatrix4fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix4fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL21")) || ((this.glProgramUniformMatrix2x3fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix2x3fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL21")) || ((this.glProgramUniformMatrix3x2fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix3x2fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL21")) || ((this.glProgramUniformMatrix2x4fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix2x4fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL21")) || ((this.glProgramUniformMatrix4x2fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix4x2fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL21")) || ((this.glProgramUniformMatrix3x4fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix3x4fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL21")) || ((this.glProgramUniformMatrix4x3fvEXT = GLContext.getFunctionAddress("glProgramUniformMatrix4x3fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_buffer_object")) || ((this.glTextureBufferEXT = GLContext.getFunctionAddress("glTextureBufferEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_buffer_object")) || ((this.glMultiTexBufferEXT = GLContext.getFunctionAddress("glMultiTexBufferEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glTextureParameterIivEXT = GLContext.getFunctionAddress("glTextureParameterIivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glTextureParameterIuivEXT = GLContext.getFunctionAddress("glTextureParameterIuivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glGetTextureParameterIivEXT = GLContext.getFunctionAddress("glGetTextureParameterIivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glGetTextureParameterIuivEXT = GLContext.getFunctionAddress("glGetTextureParameterIuivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glMultiTexParameterIivEXT = GLContext.getFunctionAddress("glMultiTexParameterIivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glMultiTexParameterIuivEXT = GLContext.getFunctionAddress("glMultiTexParameterIuivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glGetMultiTexParameterIivEXT = GLContext.getFunctionAddress("glGetMultiTexParameterIivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_texture_integer")) || ((this.glGetMultiTexParameterIuivEXT = GLContext.getFunctionAddress("glGetMultiTexParameterIuivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform1uiEXT = GLContext.getFunctionAddress("glProgramUniform1uiEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform2uiEXT = GLContext.getFunctionAddress("glProgramUniform2uiEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform3uiEXT = GLContext.getFunctionAddress("glProgramUniform3uiEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform4uiEXT = GLContext.getFunctionAddress("glProgramUniform4uiEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform1uivEXT = GLContext.getFunctionAddress("glProgramUniform1uivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform2uivEXT = GLContext.getFunctionAddress("glProgramUniform2uivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform3uivEXT = GLContext.getFunctionAddress("glProgramUniform3uivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_shader4")) || ((this.glProgramUniform4uivEXT = GLContext.getFunctionAddress("glProgramUniform4uivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_gpu_program_parameters")) || ((this.glNamedProgramLocalParameters4fvEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameters4fvEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glNamedProgramLocalParameterI4iEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameterI4iEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glNamedProgramLocalParameterI4ivEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameterI4ivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glNamedProgramLocalParametersI4ivEXT = GLContext.getFunctionAddress("glNamedProgramLocalParametersI4ivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glNamedProgramLocalParameterI4uiEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameterI4uiEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glNamedProgramLocalParameterI4uivEXT = GLContext.getFunctionAddress("glNamedProgramLocalParameterI4uivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glNamedProgramLocalParametersI4uivEXT = GLContext.getFunctionAddress("glNamedProgramLocalParametersI4uivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glGetNamedProgramLocalParameterIivEXT = GLContext.getFunctionAddress("glGetNamedProgramLocalParameterIivEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_gpu_program4")) || ((this.glGetNamedProgramLocalParameterIuivEXT = GLContext.getFunctionAddress("glGetNamedProgramLocalParameterIuivEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glNamedRenderbufferStorageEXT = GLContext.getFunctionAddress("glNamedRenderbufferStorageEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glGetNamedRenderbufferParameterivEXT = GLContext.getFunctionAddress("glGetNamedRenderbufferParameterivEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_multisample"))) || ((this.glNamedRenderbufferStorageMultisampleEXT = GLContext.getFunctionAddress("glNamedRenderbufferStorageMultisampleEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_framebuffer_multisample_coverage")) || ((this.glNamedRenderbufferStorageMultisampleCoverageEXT = GLContext.getFunctionAddress("glNamedRenderbufferStorageMultisampleCoverageEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glCheckNamedFramebufferStatusEXT = GLContext.getFunctionAddress("glCheckNamedFramebufferStatusEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glNamedFramebufferTexture1DEXT = GLContext.getFunctionAddress("glNamedFramebufferTexture1DEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glNamedFramebufferTexture2DEXT = GLContext.getFunctionAddress("glNamedFramebufferTexture2DEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glNamedFramebufferTexture3DEXT = GLContext.getFunctionAddress("glNamedFramebufferTexture3DEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glNamedFramebufferRenderbufferEXT = GLContext.getFunctionAddress("glNamedFramebufferRenderbufferEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glGetNamedFramebufferAttachmentParameterivEXT = GLContext.getFunctionAddress("glGetNamedFramebufferAttachmentParameterivEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glGenerateTextureMipmapEXT = GLContext.getFunctionAddress("glGenerateTextureMipmapEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glGenerateMultiTexMipmapEXT = GLContext.getFunctionAddress("glGenerateMultiTexMipmapEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glFramebufferDrawBufferEXT = GLContext.getFunctionAddress("glFramebufferDrawBufferEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glFramebufferDrawBuffersEXT = GLContext.getFunctionAddress("glFramebufferDrawBuffersEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glFramebufferReadBufferEXT = GLContext.getFunctionAddress("glFramebufferReadBufferEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL30")) && (!supported_extensions.contains("GL_EXT_framebuffer_object"))) || ((this.glGetFramebufferParameterivEXT = GLContext.getFunctionAddress("glGetFramebufferParameterivEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("OpenGL31")) && (!supported_extensions.contains("GL_ARB_copy_buffer"))) || ((this.glNamedCopyBufferSubDataEXT = GLContext.getFunctionAddress("glNamedCopyBufferSubDataEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("GL_EXT_geometry_shader4")) && (!supported_extensions.contains("GL_NV_geometry_program4"))) || ((this.glNamedFramebufferTextureEXT = GLContext.getFunctionAddress("glNamedFramebufferTextureEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("GL_EXT_geometry_shader4")) && (!supported_extensions.contains("GL_NV_geometry_program4"))) || ((this.glNamedFramebufferTextureLayerEXT = GLContext.getFunctionAddress("glNamedFramebufferTextureLayerEXT")) != 0L) ? 1 : 0) & (((!supported_extensions.contains("GL_EXT_geometry_shader4")) && (!supported_extensions.contains("GL_NV_geometry_program4"))) || ((this.glNamedFramebufferTextureFaceEXT = GLContext.getFunctionAddress("glNamedFramebufferTextureFaceEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_explicit_multisample")) || ((this.glTextureRenderbufferEXT = GLContext.getFunctionAddress("glTextureRenderbufferEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_NV_explicit_multisample")) || ((this.glMultiTexRenderbufferEXT = GLContext.getFunctionAddress("glMultiTexRenderbufferEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayVertexOffsetEXT = GLContext.getFunctionAddress("glVertexArrayVertexOffsetEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayColorOffsetEXT = GLContext.getFunctionAddress("glVertexArrayColorOffsetEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayEdgeFlagOffsetEXT = GLContext.getFunctionAddress("glVertexArrayEdgeFlagOffsetEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayIndexOffsetEXT = GLContext.getFunctionAddress("glVertexArrayIndexOffsetEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayNormalOffsetEXT = GLContext.getFunctionAddress("glVertexArrayNormalOffsetEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayTexCoordOffsetEXT = GLContext.getFunctionAddress("glVertexArrayTexCoordOffsetEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayMultiTexCoordOffsetEXT = GLContext.getFunctionAddress("glVertexArrayMultiTexCoordOffsetEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayFogCoordOffsetEXT = GLContext.getFunctionAddress("glVertexArrayFogCoordOffsetEXT")) != 0L) ? 1 : 0) & ((forwardCompatible) || (!supported_extensions.contains("OpenGL30")) || ((this.glVertexArraySecondaryColorOffsetEXT = GLContext.getFunctionAddress("glVertexArraySecondaryColorOffsetEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayVertexAttribOffsetEXT = GLContext.getFunctionAddress("glVertexArrayVertexAttribOffsetEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glVertexArrayVertexAttribIOffsetEXT = GLContext.getFunctionAddress("glVertexArrayVertexAttribIOffsetEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glEnableVertexArrayEXT = GLContext.getFunctionAddress("glEnableVertexArrayEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glDisableVertexArrayEXT = GLContext.getFunctionAddress("glDisableVertexArrayEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glEnableVertexArrayAttribEXT = GLContext.getFunctionAddress("glEnableVertexArrayAttribEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glDisableVertexArrayAttribEXT = GLContext.getFunctionAddress("glDisableVertexArrayAttribEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glGetVertexArrayIntegervEXT = GLContext.getFunctionAddress("glGetVertexArrayIntegervEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glGetVertexArrayPointervEXT = GLContext.getFunctionAddress("glGetVertexArrayPointervEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glGetVertexArrayIntegeri_vEXT = GLContext.getFunctionAddress("glGetVertexArrayIntegeri_vEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glGetVertexArrayPointeri_vEXT = GLContext.getFunctionAddress("glGetVertexArrayPointeri_vEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glMapNamedBufferRangeEXT = GLContext.getFunctionAddress("glMapNamedBufferRangeEXT")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("OpenGL30")) || ((this.glFlushMappedNamedBufferRangeEXT = GLContext.getFunctionAddress("glFlushMappedNamedBufferRangeEXT")) != 0L) ? 1 : 0);
/* 3476:     */  }
/* 3477:     */  
/* 3695:     */  private boolean EXT_draw_buffers2_initNativeFunctionAddresses()
/* 3696:     */  {
/* 3697:3697 */    return ((this.glColorMaskIndexedEXT = GLContext.getFunctionAddress("glColorMaskIndexedEXT")) != 0L ? 1 : 0) & ((this.glGetBooleanIndexedvEXT = GLContext.getFunctionAddress("glGetBooleanIndexedvEXT")) != 0L ? 1 : 0) & ((this.glGetIntegerIndexedvEXT = GLContext.getFunctionAddress("glGetIntegerIndexedvEXT")) != 0L ? 1 : 0) & ((this.glEnableIndexedEXT = GLContext.getFunctionAddress("glEnableIndexedEXT")) != 0L ? 1 : 0) & ((this.glDisableIndexedEXT = GLContext.getFunctionAddress("glDisableIndexedEXT")) != 0L ? 1 : 0) & ((this.glIsEnabledIndexedEXT = GLContext.getFunctionAddress("glIsEnabledIndexedEXT")) != 0L ? 1 : 0);
/* 3698:     */  }
/* 3699:     */  
/* 3705:     */  private boolean EXT_draw_instanced_initNativeFunctionAddresses()
/* 3706:     */  {
/* 3707:3707 */    return ((this.glDrawArraysInstancedEXT = GLContext.getFunctionAddress("glDrawArraysInstancedEXT")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedEXT = GLContext.getFunctionAddress("glDrawElementsInstancedEXT")) != 0L ? 1 : 0);
/* 3708:     */  }
/* 3709:     */  
/* 3711:     */  private boolean EXT_draw_range_elements_initNativeFunctionAddresses()
/* 3712:     */  {
/* 3713:3713 */    return (this.glDrawRangeElementsEXT = GLContext.getFunctionAddress("glDrawRangeElementsEXT")) != 0L;
/* 3714:     */  }
/* 3715:     */  
/* 3716:     */  private boolean EXT_fog_coord_initNativeFunctionAddresses()
/* 3717:     */  {
/* 3718:3718 */    return ((this.glFogCoordfEXT = GLContext.getFunctionAddress("glFogCoordfEXT")) != 0L ? 1 : 0) & ((this.glFogCoorddEXT = GLContext.getFunctionAddress("glFogCoorddEXT")) != 0L ? 1 : 0) & ((this.glFogCoordPointerEXT = GLContext.getFunctionAddress("glFogCoordPointerEXT")) != 0L ? 1 : 0);
/* 3719:     */  }
/* 3720:     */  
/* 3723:     */  private boolean EXT_framebuffer_blit_initNativeFunctionAddresses()
/* 3724:     */  {
/* 3725:3725 */    return (this.glBlitFramebufferEXT = GLContext.getFunctionAddress("glBlitFramebufferEXT")) != 0L;
/* 3726:     */  }
/* 3727:     */  
/* 3728:     */  private boolean EXT_framebuffer_multisample_initNativeFunctionAddresses()
/* 3729:     */  {
/* 3730:3730 */    return (this.glRenderbufferStorageMultisampleEXT = GLContext.getFunctionAddress("glRenderbufferStorageMultisampleEXT")) != 0L;
/* 3731:     */  }
/* 3732:     */  
/* 3733:     */  private boolean EXT_framebuffer_object_initNativeFunctionAddresses()
/* 3734:     */  {
/* 3735:3735 */    return ((this.glIsRenderbufferEXT = GLContext.getFunctionAddress("glIsRenderbufferEXT")) != 0L ? 1 : 0) & ((this.glBindRenderbufferEXT = GLContext.getFunctionAddress("glBindRenderbufferEXT")) != 0L ? 1 : 0) & ((this.glDeleteRenderbuffersEXT = GLContext.getFunctionAddress("glDeleteRenderbuffersEXT")) != 0L ? 1 : 0) & ((this.glGenRenderbuffersEXT = GLContext.getFunctionAddress("glGenRenderbuffersEXT")) != 0L ? 1 : 0) & ((this.glRenderbufferStorageEXT = GLContext.getFunctionAddress("glRenderbufferStorageEXT")) != 0L ? 1 : 0) & ((this.glGetRenderbufferParameterivEXT = GLContext.getFunctionAddress("glGetRenderbufferParameterivEXT")) != 0L ? 1 : 0) & ((this.glIsFramebufferEXT = GLContext.getFunctionAddress("glIsFramebufferEXT")) != 0L ? 1 : 0) & ((this.glBindFramebufferEXT = GLContext.getFunctionAddress("glBindFramebufferEXT")) != 0L ? 1 : 0) & ((this.glDeleteFramebuffersEXT = GLContext.getFunctionAddress("glDeleteFramebuffersEXT")) != 0L ? 1 : 0) & ((this.glGenFramebuffersEXT = GLContext.getFunctionAddress("glGenFramebuffersEXT")) != 0L ? 1 : 0) & ((this.glCheckFramebufferStatusEXT = GLContext.getFunctionAddress("glCheckFramebufferStatusEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTexture1DEXT = GLContext.getFunctionAddress("glFramebufferTexture1DEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTexture2DEXT = GLContext.getFunctionAddress("glFramebufferTexture2DEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTexture3DEXT = GLContext.getFunctionAddress("glFramebufferTexture3DEXT")) != 0L ? 1 : 0) & ((this.glFramebufferRenderbufferEXT = GLContext.getFunctionAddress("glFramebufferRenderbufferEXT")) != 0L ? 1 : 0) & ((this.glGetFramebufferAttachmentParameterivEXT = GLContext.getFunctionAddress("glGetFramebufferAttachmentParameterivEXT")) != 0L ? 1 : 0) & ((this.glGenerateMipmapEXT = GLContext.getFunctionAddress("glGenerateMipmapEXT")) != 0L ? 1 : 0);
/* 3736:     */  }
/* 3737:     */  
/* 3754:     */  private boolean EXT_geometry_shader4_initNativeFunctionAddresses()
/* 3755:     */  {
/* 3756:3756 */    return ((this.glProgramParameteriEXT = GLContext.getFunctionAddress("glProgramParameteriEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTextureEXT = GLContext.getFunctionAddress("glFramebufferTextureEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTextureLayerEXT = GLContext.getFunctionAddress("glFramebufferTextureLayerEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTextureFaceEXT = GLContext.getFunctionAddress("glFramebufferTextureFaceEXT")) != 0L ? 1 : 0);
/* 3757:     */  }
/* 3758:     */  
/* 3762:     */  private boolean EXT_gpu_program_parameters_initNativeFunctionAddresses()
/* 3763:     */  {
/* 3764:3764 */    return ((this.glProgramEnvParameters4fvEXT = GLContext.getFunctionAddress("glProgramEnvParameters4fvEXT")) != 0L ? 1 : 0) & ((this.glProgramLocalParameters4fvEXT = GLContext.getFunctionAddress("glProgramLocalParameters4fvEXT")) != 0L ? 1 : 0);
/* 3765:     */  }
/* 3766:     */  
/* 3768:     */  private boolean EXT_gpu_shader4_initNativeFunctionAddresses()
/* 3769:     */  {
/* 3770:3770 */    return ((this.glVertexAttribI1iEXT = GLContext.getFunctionAddress("glVertexAttribI1iEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI2iEXT = GLContext.getFunctionAddress("glVertexAttribI2iEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI3iEXT = GLContext.getFunctionAddress("glVertexAttribI3iEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4iEXT = GLContext.getFunctionAddress("glVertexAttribI4iEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI1uiEXT = GLContext.getFunctionAddress("glVertexAttribI1uiEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI2uiEXT = GLContext.getFunctionAddress("glVertexAttribI2uiEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI3uiEXT = GLContext.getFunctionAddress("glVertexAttribI3uiEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4uiEXT = GLContext.getFunctionAddress("glVertexAttribI4uiEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI1ivEXT = GLContext.getFunctionAddress("glVertexAttribI1ivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI2ivEXT = GLContext.getFunctionAddress("glVertexAttribI2ivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI3ivEXT = GLContext.getFunctionAddress("glVertexAttribI3ivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4ivEXT = GLContext.getFunctionAddress("glVertexAttribI4ivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI1uivEXT = GLContext.getFunctionAddress("glVertexAttribI1uivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI2uivEXT = GLContext.getFunctionAddress("glVertexAttribI2uivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI3uivEXT = GLContext.getFunctionAddress("glVertexAttribI3uivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4uivEXT = GLContext.getFunctionAddress("glVertexAttribI4uivEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4bvEXT = GLContext.getFunctionAddress("glVertexAttribI4bvEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4svEXT = GLContext.getFunctionAddress("glVertexAttribI4svEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4ubvEXT = GLContext.getFunctionAddress("glVertexAttribI4ubvEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribI4usvEXT = GLContext.getFunctionAddress("glVertexAttribI4usvEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribIPointerEXT = GLContext.getFunctionAddress("glVertexAttribIPointerEXT")) != 0L ? 1 : 0) & ((this.glGetVertexAttribIivEXT = GLContext.getFunctionAddress("glGetVertexAttribIivEXT")) != 0L ? 1 : 0) & ((this.glGetVertexAttribIuivEXT = GLContext.getFunctionAddress("glGetVertexAttribIuivEXT")) != 0L ? 1 : 0) & ((this.glUniform1uiEXT = GLContext.getFunctionAddress("glUniform1uiEXT")) != 0L ? 1 : 0) & ((this.glUniform2uiEXT = GLContext.getFunctionAddress("glUniform2uiEXT")) != 0L ? 1 : 0) & ((this.glUniform3uiEXT = GLContext.getFunctionAddress("glUniform3uiEXT")) != 0L ? 1 : 0) & ((this.glUniform4uiEXT = GLContext.getFunctionAddress("glUniform4uiEXT")) != 0L ? 1 : 0) & ((this.glUniform1uivEXT = GLContext.getFunctionAddress("glUniform1uivEXT")) != 0L ? 1 : 0) & ((this.glUniform2uivEXT = GLContext.getFunctionAddress("glUniform2uivEXT")) != 0L ? 1 : 0) & ((this.glUniform3uivEXT = GLContext.getFunctionAddress("glUniform3uivEXT")) != 0L ? 1 : 0) & ((this.glUniform4uivEXT = GLContext.getFunctionAddress("glUniform4uivEXT")) != 0L ? 1 : 0) & ((this.glGetUniformuivEXT = GLContext.getFunctionAddress("glGetUniformuivEXT")) != 0L ? 1 : 0) & ((this.glBindFragDataLocationEXT = GLContext.getFunctionAddress("glBindFragDataLocationEXT")) != 0L ? 1 : 0) & ((this.glGetFragDataLocationEXT = GLContext.getFunctionAddress("glGetFragDataLocationEXT")) != 0L ? 1 : 0);
/* 3771:     */  }
/* 3772:     */  
/* 3806:     */  private boolean EXT_multi_draw_arrays_initNativeFunctionAddresses()
/* 3807:     */  {
/* 3808:3808 */    return (this.glMultiDrawArraysEXT = GLContext.getFunctionAddress("glMultiDrawArraysEXT")) != 0L;
/* 3809:     */  }
/* 3810:     */  
/* 3811:     */  private boolean EXT_paletted_texture_initNativeFunctionAddresses()
/* 3812:     */  {
/* 3813:3813 */    return ((this.glColorTableEXT = GLContext.getFunctionAddress("glColorTableEXT")) != 0L ? 1 : 0) & ((this.glColorSubTableEXT = GLContext.getFunctionAddress("glColorSubTableEXT")) != 0L ? 1 : 0) & ((this.glGetColorTableEXT = GLContext.getFunctionAddress("glGetColorTableEXT")) != 0L ? 1 : 0) & ((this.glGetColorTableParameterivEXT = GLContext.getFunctionAddress("glGetColorTableParameterivEXT")) != 0L ? 1 : 0) & ((this.glGetColorTableParameterfvEXT = GLContext.getFunctionAddress("glGetColorTableParameterfvEXT")) != 0L ? 1 : 0);
/* 3814:     */  }
/* 3815:     */  
/* 3820:     */  private boolean EXT_point_parameters_initNativeFunctionAddresses()
/* 3821:     */  {
/* 3822:3822 */    return ((this.glPointParameterfEXT = GLContext.getFunctionAddress("glPointParameterfEXT")) != 0L ? 1 : 0) & ((this.glPointParameterfvEXT = GLContext.getFunctionAddress("glPointParameterfvEXT")) != 0L ? 1 : 0);
/* 3823:     */  }
/* 3824:     */  
/* 3826:     */  private boolean EXT_provoking_vertex_initNativeFunctionAddresses()
/* 3827:     */  {
/* 3828:3828 */    return (this.glProvokingVertexEXT = GLContext.getFunctionAddress("glProvokingVertexEXT")) != 0L;
/* 3829:     */  }
/* 3830:     */  
/* 3831:     */  private boolean EXT_secondary_color_initNativeFunctionAddresses()
/* 3832:     */  {
/* 3833:3833 */    return ((this.glSecondaryColor3bEXT = GLContext.getFunctionAddress("glSecondaryColor3bEXT")) != 0L ? 1 : 0) & ((this.glSecondaryColor3fEXT = GLContext.getFunctionAddress("glSecondaryColor3fEXT")) != 0L ? 1 : 0) & ((this.glSecondaryColor3dEXT = GLContext.getFunctionAddress("glSecondaryColor3dEXT")) != 0L ? 1 : 0) & ((this.glSecondaryColor3ubEXT = GLContext.getFunctionAddress("glSecondaryColor3ubEXT")) != 0L ? 1 : 0) & ((this.glSecondaryColorPointerEXT = GLContext.getFunctionAddress("glSecondaryColorPointerEXT")) != 0L ? 1 : 0);
/* 3834:     */  }
/* 3835:     */  
/* 3840:     */  private boolean EXT_separate_shader_objects_initNativeFunctionAddresses()
/* 3841:     */  {
/* 3842:3842 */    return ((this.glUseShaderProgramEXT = GLContext.getFunctionAddress("glUseShaderProgramEXT")) != 0L ? 1 : 0) & ((this.glActiveProgramEXT = GLContext.getFunctionAddress("glActiveProgramEXT")) != 0L ? 1 : 0) & ((this.glCreateShaderProgramEXT = GLContext.getFunctionAddress("glCreateShaderProgramEXT")) != 0L ? 1 : 0);
/* 3843:     */  }
/* 3844:     */  
/* 3847:     */  private boolean EXT_shader_image_load_store_initNativeFunctionAddresses()
/* 3848:     */  {
/* 3849:3849 */    return ((this.glBindImageTextureEXT = GLContext.getFunctionAddress("glBindImageTextureEXT")) != 0L ? 1 : 0) & ((this.glMemoryBarrierEXT = GLContext.getFunctionAddress("glMemoryBarrierEXT")) != 0L ? 1 : 0);
/* 3850:     */  }
/* 3851:     */  
/* 3853:     */  private boolean EXT_stencil_clear_tag_initNativeFunctionAddresses()
/* 3854:     */  {
/* 3855:3855 */    return (this.glStencilClearTagEXT = GLContext.getFunctionAddress("glStencilClearTagEXT")) != 0L;
/* 3856:     */  }
/* 3857:     */  
/* 3858:     */  private boolean EXT_stencil_two_side_initNativeFunctionAddresses()
/* 3859:     */  {
/* 3860:3860 */    return (this.glActiveStencilFaceEXT = GLContext.getFunctionAddress("glActiveStencilFaceEXT")) != 0L;
/* 3861:     */  }
/* 3862:     */  
/* 3863:     */  private boolean EXT_texture_array_initNativeFunctionAddresses()
/* 3864:     */  {
/* 3865:3865 */    return (this.glFramebufferTextureLayerEXT = GLContext.getFunctionAddress("glFramebufferTextureLayerEXT")) != 0L;
/* 3866:     */  }
/* 3867:     */  
/* 3868:     */  private boolean EXT_texture_buffer_object_initNativeFunctionAddresses()
/* 3869:     */  {
/* 3870:3870 */    return (this.glTexBufferEXT = GLContext.getFunctionAddress("glTexBufferEXT")) != 0L;
/* 3871:     */  }
/* 3872:     */  
/* 3873:     */  private boolean EXT_texture_integer_initNativeFunctionAddresses()
/* 3874:     */  {
/* 3875:3875 */    return ((this.glClearColorIiEXT = GLContext.getFunctionAddress("glClearColorIiEXT")) != 0L ? 1 : 0) & ((this.glClearColorIuiEXT = GLContext.getFunctionAddress("glClearColorIuiEXT")) != 0L ? 1 : 0) & ((this.glTexParameterIivEXT = GLContext.getFunctionAddress("glTexParameterIivEXT")) != 0L ? 1 : 0) & ((this.glTexParameterIuivEXT = GLContext.getFunctionAddress("glTexParameterIuivEXT")) != 0L ? 1 : 0) & ((this.glGetTexParameterIivEXT = GLContext.getFunctionAddress("glGetTexParameterIivEXT")) != 0L ? 1 : 0) & ((this.glGetTexParameterIuivEXT = GLContext.getFunctionAddress("glGetTexParameterIuivEXT")) != 0L ? 1 : 0);
/* 3876:     */  }
/* 3877:     */  
/* 3883:     */  private boolean EXT_timer_query_initNativeFunctionAddresses()
/* 3884:     */  {
/* 3885:3885 */    return ((this.glGetQueryObjecti64vEXT = GLContext.getFunctionAddress("glGetQueryObjecti64vEXT")) != 0L ? 1 : 0) & ((this.glGetQueryObjectui64vEXT = GLContext.getFunctionAddress("glGetQueryObjectui64vEXT")) != 0L ? 1 : 0);
/* 3886:     */  }
/* 3887:     */  
/* 3889:     */  private boolean EXT_transform_feedback_initNativeFunctionAddresses()
/* 3890:     */  {
/* 3891:3891 */    return ((this.glBindBufferRangeEXT = GLContext.getFunctionAddress("glBindBufferRangeEXT")) != 0L ? 1 : 0) & ((this.glBindBufferOffsetEXT = GLContext.getFunctionAddress("glBindBufferOffsetEXT")) != 0L ? 1 : 0) & ((this.glBindBufferBaseEXT = GLContext.getFunctionAddress("glBindBufferBaseEXT")) != 0L ? 1 : 0) & ((this.glBeginTransformFeedbackEXT = GLContext.getFunctionAddress("glBeginTransformFeedbackEXT")) != 0L ? 1 : 0) & ((this.glEndTransformFeedbackEXT = GLContext.getFunctionAddress("glEndTransformFeedbackEXT")) != 0L ? 1 : 0) & ((this.glTransformFeedbackVaryingsEXT = GLContext.getFunctionAddress("glTransformFeedbackVaryingsEXT")) != 0L ? 1 : 0) & ((this.glGetTransformFeedbackVaryingEXT = GLContext.getFunctionAddress("glGetTransformFeedbackVaryingEXT")) != 0L ? 1 : 0);
/* 3892:     */  }
/* 3893:     */  
/* 3900:     */  private boolean EXT_vertex_attrib_64bit_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 3901:     */  {
/* 3902:3902 */    return ((this.glVertexAttribL1dEXT = GLContext.getFunctionAddress("glVertexAttribL1dEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribL2dEXT = GLContext.getFunctionAddress("glVertexAttribL2dEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribL3dEXT = GLContext.getFunctionAddress("glVertexAttribL3dEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribL4dEXT = GLContext.getFunctionAddress("glVertexAttribL4dEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribL1dvEXT = GLContext.getFunctionAddress("glVertexAttribL1dvEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribL2dvEXT = GLContext.getFunctionAddress("glVertexAttribL2dvEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribL3dvEXT = GLContext.getFunctionAddress("glVertexAttribL3dvEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribL4dvEXT = GLContext.getFunctionAddress("glVertexAttribL4dvEXT")) != 0L ? 1 : 0) & ((this.glVertexAttribLPointerEXT = GLContext.getFunctionAddress("glVertexAttribLPointerEXT")) != 0L ? 1 : 0) & ((this.glGetVertexAttribLdvEXT = GLContext.getFunctionAddress("glGetVertexAttribLdvEXT")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glVertexArrayVertexAttribLOffsetEXT = GLContext.getFunctionAddress("glVertexArrayVertexAttribLOffsetEXT")) != 0L) ? 1 : 0);
/* 3903:     */  }
/* 3904:     */  
/* 3915:     */  private boolean EXT_vertex_shader_initNativeFunctionAddresses()
/* 3916:     */  {
/* 3917:3917 */    return ((this.glBeginVertexShaderEXT = GLContext.getFunctionAddress("glBeginVertexShaderEXT")) != 0L ? 1 : 0) & ((this.glEndVertexShaderEXT = GLContext.getFunctionAddress("glEndVertexShaderEXT")) != 0L ? 1 : 0) & ((this.glBindVertexShaderEXT = GLContext.getFunctionAddress("glBindVertexShaderEXT")) != 0L ? 1 : 0) & ((this.glGenVertexShadersEXT = GLContext.getFunctionAddress("glGenVertexShadersEXT")) != 0L ? 1 : 0) & ((this.glDeleteVertexShaderEXT = GLContext.getFunctionAddress("glDeleteVertexShaderEXT")) != 0L ? 1 : 0) & ((this.glShaderOp1EXT = GLContext.getFunctionAddress("glShaderOp1EXT")) != 0L ? 1 : 0) & ((this.glShaderOp2EXT = GLContext.getFunctionAddress("glShaderOp2EXT")) != 0L ? 1 : 0) & ((this.glShaderOp3EXT = GLContext.getFunctionAddress("glShaderOp3EXT")) != 0L ? 1 : 0) & ((this.glSwizzleEXT = GLContext.getFunctionAddress("glSwizzleEXT")) != 0L ? 1 : 0) & ((this.glWriteMaskEXT = GLContext.getFunctionAddress("glWriteMaskEXT")) != 0L ? 1 : 0) & ((this.glInsertComponentEXT = GLContext.getFunctionAddress("glInsertComponentEXT")) != 0L ? 1 : 0) & ((this.glExtractComponentEXT = GLContext.getFunctionAddress("glExtractComponentEXT")) != 0L ? 1 : 0) & ((this.glGenSymbolsEXT = GLContext.getFunctionAddress("glGenSymbolsEXT")) != 0L ? 1 : 0) & ((this.glSetInvariantEXT = GLContext.getFunctionAddress("glSetInvariantEXT")) != 0L ? 1 : 0) & ((this.glSetLocalConstantEXT = GLContext.getFunctionAddress("glSetLocalConstantEXT")) != 0L ? 1 : 0) & ((this.glVariantbvEXT = GLContext.getFunctionAddress("glVariantbvEXT")) != 0L ? 1 : 0) & ((this.glVariantsvEXT = GLContext.getFunctionAddress("glVariantsvEXT")) != 0L ? 1 : 0) & ((this.glVariantivEXT = GLContext.getFunctionAddress("glVariantivEXT")) != 0L ? 1 : 0) & ((this.glVariantfvEXT = GLContext.getFunctionAddress("glVariantfvEXT")) != 0L ? 1 : 0) & ((this.glVariantdvEXT = GLContext.getFunctionAddress("glVariantdvEXT")) != 0L ? 1 : 0) & ((this.glVariantubvEXT = GLContext.getFunctionAddress("glVariantubvEXT")) != 0L ? 1 : 0) & ((this.glVariantusvEXT = GLContext.getFunctionAddress("glVariantusvEXT")) != 0L ? 1 : 0) & ((this.glVariantuivEXT = GLContext.getFunctionAddress("glVariantuivEXT")) != 0L ? 1 : 0) & ((this.glVariantPointerEXT = GLContext.getFunctionAddress("glVariantPointerEXT")) != 0L ? 1 : 0) & ((this.glEnableVariantClientStateEXT = GLContext.getFunctionAddress("glEnableVariantClientStateEXT")) != 0L ? 1 : 0) & ((this.glDisableVariantClientStateEXT = GLContext.getFunctionAddress("glDisableVariantClientStateEXT")) != 0L ? 1 : 0) & ((this.glBindLightParameterEXT = GLContext.getFunctionAddress("glBindLightParameterEXT")) != 0L ? 1 : 0) & ((this.glBindMaterialParameterEXT = GLContext.getFunctionAddress("glBindMaterialParameterEXT")) != 0L ? 1 : 0) & ((this.glBindTexGenParameterEXT = GLContext.getFunctionAddress("glBindTexGenParameterEXT")) != 0L ? 1 : 0) & ((this.glBindTextureUnitParameterEXT = GLContext.getFunctionAddress("glBindTextureUnitParameterEXT")) != 0L ? 1 : 0) & ((this.glBindParameterEXT = GLContext.getFunctionAddress("glBindParameterEXT")) != 0L ? 1 : 0) & ((this.glIsVariantEnabledEXT = GLContext.getFunctionAddress("glIsVariantEnabledEXT")) != 0L ? 1 : 0) & ((this.glGetVariantBooleanvEXT = GLContext.getFunctionAddress("glGetVariantBooleanvEXT")) != 0L ? 1 : 0) & ((this.glGetVariantIntegervEXT = GLContext.getFunctionAddress("glGetVariantIntegervEXT")) != 0L ? 1 : 0) & ((this.glGetVariantFloatvEXT = GLContext.getFunctionAddress("glGetVariantFloatvEXT")) != 0L ? 1 : 0) & ((this.glGetVariantPointervEXT = GLContext.getFunctionAddress("glGetVariantPointervEXT")) != 0L ? 1 : 0) & ((this.glGetInvariantBooleanvEXT = GLContext.getFunctionAddress("glGetInvariantBooleanvEXT")) != 0L ? 1 : 0) & ((this.glGetInvariantIntegervEXT = GLContext.getFunctionAddress("glGetInvariantIntegervEXT")) != 0L ? 1 : 0) & ((this.glGetInvariantFloatvEXT = GLContext.getFunctionAddress("glGetInvariantFloatvEXT")) != 0L ? 1 : 0) & ((this.glGetLocalConstantBooleanvEXT = GLContext.getFunctionAddress("glGetLocalConstantBooleanvEXT")) != 0L ? 1 : 0) & ((this.glGetLocalConstantIntegervEXT = GLContext.getFunctionAddress("glGetLocalConstantIntegervEXT")) != 0L ? 1 : 0) & ((this.glGetLocalConstantFloatvEXT = GLContext.getFunctionAddress("glGetLocalConstantFloatvEXT")) != 0L ? 1 : 0);
/* 3918:     */  }
/* 3919:     */  
/* 3961:     */  private boolean EXT_vertex_weighting_initNativeFunctionAddresses()
/* 3962:     */  {
/* 3963:3963 */    return ((this.glVertexWeightfEXT = GLContext.getFunctionAddress("glVertexWeightfEXT")) != 0L ? 1 : 0) & ((this.glVertexWeightPointerEXT = GLContext.getFunctionAddress("glVertexWeightPointerEXT")) != 0L ? 1 : 0);
/* 3964:     */  }
/* 3965:     */  
/* 3967:     */  private boolean GL11_initNativeFunctionAddresses(boolean forwardCompatible)
/* 3968:     */  {
/* 3969:3969 */    return ((forwardCompatible) || ((this.glAccum = GLContext.getFunctionAddress("glAccum")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glAlphaFunc = GLContext.getFunctionAddress("glAlphaFunc")) != 0L) ? 1 : 0) & ((this.glClearColor = GLContext.getFunctionAddress("glClearColor")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glClearAccum = GLContext.getFunctionAddress("glClearAccum")) != 0L) ? 1 : 0) & ((this.glClear = GLContext.getFunctionAddress("glClear")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glCallLists = GLContext.getFunctionAddress("glCallLists")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glCallList = GLContext.getFunctionAddress("glCallList")) != 0L) ? 1 : 0) & ((this.glBlendFunc = GLContext.getFunctionAddress("glBlendFunc")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glBitmap = GLContext.getFunctionAddress("glBitmap")) != 0L) ? 1 : 0) & ((this.glBindTexture = GLContext.getFunctionAddress("glBindTexture")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glPrioritizeTextures = GLContext.getFunctionAddress("glPrioritizeTextures")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glAreTexturesResident = GLContext.getFunctionAddress("glAreTexturesResident")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glBegin = GLContext.getFunctionAddress("glBegin")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEnd = GLContext.getFunctionAddress("glEnd")) != 0L) ? 1 : 0) & ((this.glArrayElement = GLContext.getFunctionAddress("glArrayElement")) != 0L ? 1 : 0) & ((this.glClearDepth = GLContext.getFunctionAddress("glClearDepth")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glDeleteLists = GLContext.getFunctionAddress("glDeleteLists")) != 0L) ? 1 : 0) & ((this.glDeleteTextures = GLContext.getFunctionAddress("glDeleteTextures")) != 0L ? 1 : 0) & ((this.glCullFace = GLContext.getFunctionAddress("glCullFace")) != 0L ? 1 : 0) & ((this.glCopyTexSubImage2D = GLContext.getFunctionAddress("glCopyTexSubImage2D")) != 0L ? 1 : 0) & ((this.glCopyTexSubImage1D = GLContext.getFunctionAddress("glCopyTexSubImage1D")) != 0L ? 1 : 0) & ((this.glCopyTexImage2D = GLContext.getFunctionAddress("glCopyTexImage2D")) != 0L ? 1 : 0) & ((this.glCopyTexImage1D = GLContext.getFunctionAddress("glCopyTexImage1D")) != 0L ? 1 : 0) & ((this.glCopyPixels = GLContext.getFunctionAddress("glCopyPixels")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glColorPointer = GLContext.getFunctionAddress("glColorPointer")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorMaterial = GLContext.getFunctionAddress("glColorMaterial")) != 0L) ? 1 : 0) & ((this.glColorMask = GLContext.getFunctionAddress("glColorMask")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glColor3b = GLContext.getFunctionAddress("glColor3b")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColor3f = GLContext.getFunctionAddress("glColor3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColor3d = GLContext.getFunctionAddress("glColor3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColor3ub = GLContext.getFunctionAddress("glColor3ub")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColor4b = GLContext.getFunctionAddress("glColor4b")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColor4f = GLContext.getFunctionAddress("glColor4f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColor4d = GLContext.getFunctionAddress("glColor4d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColor4ub = GLContext.getFunctionAddress("glColor4ub")) != 0L) ? 1 : 0) & ((this.glClipPlane = GLContext.getFunctionAddress("glClipPlane")) != 0L ? 1 : 0) & ((this.glClearStencil = GLContext.getFunctionAddress("glClearStencil")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glEvalPoint1 = GLContext.getFunctionAddress("glEvalPoint1")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEvalPoint2 = GLContext.getFunctionAddress("glEvalPoint2")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEvalMesh1 = GLContext.getFunctionAddress("glEvalMesh1")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEvalMesh2 = GLContext.getFunctionAddress("glEvalMesh2")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEvalCoord1f = GLContext.getFunctionAddress("glEvalCoord1f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEvalCoord1d = GLContext.getFunctionAddress("glEvalCoord1d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEvalCoord2f = GLContext.getFunctionAddress("glEvalCoord2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEvalCoord2d = GLContext.getFunctionAddress("glEvalCoord2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEnableClientState = GLContext.getFunctionAddress("glEnableClientState")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glDisableClientState = GLContext.getFunctionAddress("glDisableClientState")) != 0L) ? 1 : 0) & ((this.glEnable = GLContext.getFunctionAddress("glEnable")) != 0L ? 1 : 0) & ((this.glDisable = GLContext.getFunctionAddress("glDisable")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glEdgeFlagPointer = GLContext.getFunctionAddress("glEdgeFlagPointer")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEdgeFlag = GLContext.getFunctionAddress("glEdgeFlag")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glDrawPixels = GLContext.getFunctionAddress("glDrawPixels")) != 0L) ? 1 : 0) & ((this.glDrawElements = GLContext.getFunctionAddress("glDrawElements")) != 0L ? 1 : 0) & ((this.glDrawBuffer = GLContext.getFunctionAddress("glDrawBuffer")) != 0L ? 1 : 0) & ((this.glDrawArrays = GLContext.getFunctionAddress("glDrawArrays")) != 0L ? 1 : 0) & ((this.glDepthRange = GLContext.getFunctionAddress("glDepthRange")) != 0L ? 1 : 0) & ((this.glDepthMask = GLContext.getFunctionAddress("glDepthMask")) != 0L ? 1 : 0) & ((this.glDepthFunc = GLContext.getFunctionAddress("glDepthFunc")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glFeedbackBuffer = GLContext.getFunctionAddress("glFeedbackBuffer")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetPixelMapfv = GLContext.getFunctionAddress("glGetPixelMapfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetPixelMapuiv = GLContext.getFunctionAddress("glGetPixelMapuiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetPixelMapusv = GLContext.getFunctionAddress("glGetPixelMapusv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMaterialfv = GLContext.getFunctionAddress("glGetMaterialfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMaterialiv = GLContext.getFunctionAddress("glGetMaterialiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMapfv = GLContext.getFunctionAddress("glGetMapfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMapdv = GLContext.getFunctionAddress("glGetMapdv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetMapiv = GLContext.getFunctionAddress("glGetMapiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetLightfv = GLContext.getFunctionAddress("glGetLightfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetLightiv = GLContext.getFunctionAddress("glGetLightiv")) != 0L) ? 1 : 0) & ((this.glGetError = GLContext.getFunctionAddress("glGetError")) != 0L ? 1 : 0) & ((this.glGetClipPlane = GLContext.getFunctionAddress("glGetClipPlane")) != 0L ? 1 : 0) & ((this.glGetBooleanv = GLContext.getFunctionAddress("glGetBooleanv")) != 0L ? 1 : 0) & ((this.glGetDoublev = GLContext.getFunctionAddress("glGetDoublev")) != 0L ? 1 : 0) & ((this.glGetFloatv = GLContext.getFunctionAddress("glGetFloatv")) != 0L ? 1 : 0) & ((this.glGetIntegerv = GLContext.getFunctionAddress("glGetIntegerv")) != 0L ? 1 : 0) & ((this.glGenTextures = GLContext.getFunctionAddress("glGenTextures")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glGenLists = GLContext.getFunctionAddress("glGenLists")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glFrustum = GLContext.getFunctionAddress("glFrustum")) != 0L) ? 1 : 0) & ((this.glFrontFace = GLContext.getFunctionAddress("glFrontFace")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glFogf = GLContext.getFunctionAddress("glFogf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glFogi = GLContext.getFunctionAddress("glFogi")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glFogfv = GLContext.getFunctionAddress("glFogfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glFogiv = GLContext.getFunctionAddress("glFogiv")) != 0L) ? 1 : 0) & ((this.glFlush = GLContext.getFunctionAddress("glFlush")) != 0L ? 1 : 0) & ((this.glFinish = GLContext.getFunctionAddress("glFinish")) != 0L ? 1 : 0) & ((this.glGetPointerv = GLContext.getFunctionAddress("glGetPointerv")) != 0L ? 1 : 0) & ((this.glIsEnabled = GLContext.getFunctionAddress("glIsEnabled")) != 0L ? 1 : 0) & ((this.glInterleavedArrays = GLContext.getFunctionAddress("glInterleavedArrays")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glInitNames = GLContext.getFunctionAddress("glInitNames")) != 0L) ? 1 : 0) & ((this.glHint = GLContext.getFunctionAddress("glHint")) != 0L ? 1 : 0) & ((this.glGetTexParameterfv = GLContext.getFunctionAddress("glGetTexParameterfv")) != 0L ? 1 : 0) & ((this.glGetTexParameteriv = GLContext.getFunctionAddress("glGetTexParameteriv")) != 0L ? 1 : 0) & ((this.glGetTexLevelParameterfv = GLContext.getFunctionAddress("glGetTexLevelParameterfv")) != 0L ? 1 : 0) & ((this.glGetTexLevelParameteriv = GLContext.getFunctionAddress("glGetTexLevelParameteriv")) != 0L ? 1 : 0) & ((this.glGetTexImage = GLContext.getFunctionAddress("glGetTexImage")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glGetTexGeniv = GLContext.getFunctionAddress("glGetTexGeniv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetTexGenfv = GLContext.getFunctionAddress("glGetTexGenfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glGetTexGendv = GLContext.getFunctionAddress("glGetTexGendv")) != 0L) ? 1 : 0) & ((this.glGetTexEnviv = GLContext.getFunctionAddress("glGetTexEnviv")) != 0L ? 1 : 0) & ((this.glGetTexEnvfv = GLContext.getFunctionAddress("glGetTexEnvfv")) != 0L ? 1 : 0) & ((this.glGetString = GLContext.getFunctionAddress("glGetString")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glGetPolygonStipple = GLContext.getFunctionAddress("glGetPolygonStipple")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glIsList = GLContext.getFunctionAddress("glIsList")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMaterialf = GLContext.getFunctionAddress("glMaterialf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMateriali = GLContext.getFunctionAddress("glMateriali")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMaterialfv = GLContext.getFunctionAddress("glMaterialfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMaterialiv = GLContext.getFunctionAddress("glMaterialiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMapGrid1f = GLContext.getFunctionAddress("glMapGrid1f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMapGrid1d = GLContext.getFunctionAddress("glMapGrid1d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMapGrid2f = GLContext.getFunctionAddress("glMapGrid2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMapGrid2d = GLContext.getFunctionAddress("glMapGrid2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMap2f = GLContext.getFunctionAddress("glMap2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMap2d = GLContext.getFunctionAddress("glMap2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMap1f = GLContext.getFunctionAddress("glMap1f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMap1d = GLContext.getFunctionAddress("glMap1d")) != 0L) ? 1 : 0) & ((this.glLogicOp = GLContext.getFunctionAddress("glLogicOp")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glLoadName = GLContext.getFunctionAddress("glLoadName")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLoadMatrixf = GLContext.getFunctionAddress("glLoadMatrixf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLoadMatrixd = GLContext.getFunctionAddress("glLoadMatrixd")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLoadIdentity = GLContext.getFunctionAddress("glLoadIdentity")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glListBase = GLContext.getFunctionAddress("glListBase")) != 0L) ? 1 : 0) & ((this.glLineWidth = GLContext.getFunctionAddress("glLineWidth")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glLineStipple = GLContext.getFunctionAddress("glLineStipple")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLightModelf = GLContext.getFunctionAddress("glLightModelf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLightModeli = GLContext.getFunctionAddress("glLightModeli")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLightModelfv = GLContext.getFunctionAddress("glLightModelfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLightModeliv = GLContext.getFunctionAddress("glLightModeliv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLightf = GLContext.getFunctionAddress("glLightf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLighti = GLContext.getFunctionAddress("glLighti")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLightfv = GLContext.getFunctionAddress("glLightfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLightiv = GLContext.getFunctionAddress("glLightiv")) != 0L) ? 1 : 0) & ((this.glIsTexture = GLContext.getFunctionAddress("glIsTexture")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glMatrixMode = GLContext.getFunctionAddress("glMatrixMode")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPolygonStipple = GLContext.getFunctionAddress("glPolygonStipple")) != 0L) ? 1 : 0) & ((this.glPolygonOffset = GLContext.getFunctionAddress("glPolygonOffset")) != 0L ? 1 : 0) & ((this.glPolygonMode = GLContext.getFunctionAddress("glPolygonMode")) != 0L ? 1 : 0) & ((this.glPointSize = GLContext.getFunctionAddress("glPointSize")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glPixelZoom = GLContext.getFunctionAddress("glPixelZoom")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPixelTransferf = GLContext.getFunctionAddress("glPixelTransferf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPixelTransferi = GLContext.getFunctionAddress("glPixelTransferi")) != 0L) ? 1 : 0) & ((this.glPixelStoref = GLContext.getFunctionAddress("glPixelStoref")) != 0L ? 1 : 0) & ((this.glPixelStorei = GLContext.getFunctionAddress("glPixelStorei")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glPixelMapfv = GLContext.getFunctionAddress("glPixelMapfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPixelMapuiv = GLContext.getFunctionAddress("glPixelMapuiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPixelMapusv = GLContext.getFunctionAddress("glPixelMapusv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPassThrough = GLContext.getFunctionAddress("glPassThrough")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glOrtho = GLContext.getFunctionAddress("glOrtho")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNormalPointer = GLContext.getFunctionAddress("glNormalPointer")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNormal3b = GLContext.getFunctionAddress("glNormal3b")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNormal3f = GLContext.getFunctionAddress("glNormal3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNormal3d = GLContext.getFunctionAddress("glNormal3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNormal3i = GLContext.getFunctionAddress("glNormal3i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNewList = GLContext.getFunctionAddress("glNewList")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glEndList = GLContext.getFunctionAddress("glEndList")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultMatrixf = GLContext.getFunctionAddress("glMultMatrixf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultMatrixd = GLContext.getFunctionAddress("glMultMatrixd")) != 0L) ? 1 : 0) & ((this.glShadeModel = GLContext.getFunctionAddress("glShadeModel")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glSelectBuffer = GLContext.getFunctionAddress("glSelectBuffer")) != 0L) ? 1 : 0) & ((this.glScissor = GLContext.getFunctionAddress("glScissor")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glScalef = GLContext.getFunctionAddress("glScalef")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glScaled = GLContext.getFunctionAddress("glScaled")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRotatef = GLContext.getFunctionAddress("glRotatef")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRotated = GLContext.getFunctionAddress("glRotated")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRenderMode = GLContext.getFunctionAddress("glRenderMode")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRectf = GLContext.getFunctionAddress("glRectf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRectd = GLContext.getFunctionAddress("glRectd")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRecti = GLContext.getFunctionAddress("glRecti")) != 0L) ? 1 : 0) & ((this.glReadPixels = GLContext.getFunctionAddress("glReadPixels")) != 0L ? 1 : 0) & ((this.glReadBuffer = GLContext.getFunctionAddress("glReadBuffer")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos2f = GLContext.getFunctionAddress("glRasterPos2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos2d = GLContext.getFunctionAddress("glRasterPos2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos2i = GLContext.getFunctionAddress("glRasterPos2i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos3f = GLContext.getFunctionAddress("glRasterPos3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos3d = GLContext.getFunctionAddress("glRasterPos3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos3i = GLContext.getFunctionAddress("glRasterPos3i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos4f = GLContext.getFunctionAddress("glRasterPos4f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos4d = GLContext.getFunctionAddress("glRasterPos4d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glRasterPos4i = GLContext.getFunctionAddress("glRasterPos4i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPushName = GLContext.getFunctionAddress("glPushName")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPopName = GLContext.getFunctionAddress("glPopName")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPushMatrix = GLContext.getFunctionAddress("glPushMatrix")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPopMatrix = GLContext.getFunctionAddress("glPopMatrix")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPushClientAttrib = GLContext.getFunctionAddress("glPushClientAttrib")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPopClientAttrib = GLContext.getFunctionAddress("glPopClientAttrib")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPushAttrib = GLContext.getFunctionAddress("glPushAttrib")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glPopAttrib = GLContext.getFunctionAddress("glPopAttrib")) != 0L) ? 1 : 0) & ((this.glStencilFunc = GLContext.getFunctionAddress("glStencilFunc")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glVertexPointer = GLContext.getFunctionAddress("glVertexPointer")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex2f = GLContext.getFunctionAddress("glVertex2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex2d = GLContext.getFunctionAddress("glVertex2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex2i = GLContext.getFunctionAddress("glVertex2i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex3f = GLContext.getFunctionAddress("glVertex3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex3d = GLContext.getFunctionAddress("glVertex3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex3i = GLContext.getFunctionAddress("glVertex3i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex4f = GLContext.getFunctionAddress("glVertex4f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex4d = GLContext.getFunctionAddress("glVertex4d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertex4i = GLContext.getFunctionAddress("glVertex4i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTranslatef = GLContext.getFunctionAddress("glTranslatef")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTranslated = GLContext.getFunctionAddress("glTranslated")) != 0L) ? 1 : 0) & ((this.glTexImage1D = GLContext.getFunctionAddress("glTexImage1D")) != 0L ? 1 : 0) & ((this.glTexImage2D = GLContext.getFunctionAddress("glTexImage2D")) != 0L ? 1 : 0) & ((this.glTexSubImage1D = GLContext.getFunctionAddress("glTexSubImage1D")) != 0L ? 1 : 0) & ((this.glTexSubImage2D = GLContext.getFunctionAddress("glTexSubImage2D")) != 0L ? 1 : 0) & ((this.glTexParameterf = GLContext.getFunctionAddress("glTexParameterf")) != 0L ? 1 : 0) & ((this.glTexParameteri = GLContext.getFunctionAddress("glTexParameteri")) != 0L ? 1 : 0) & ((this.glTexParameterfv = GLContext.getFunctionAddress("glTexParameterfv")) != 0L ? 1 : 0) & ((this.glTexParameteriv = GLContext.getFunctionAddress("glTexParameteriv")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glTexGenf = GLContext.getFunctionAddress("glTexGenf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexGend = GLContext.getFunctionAddress("glTexGend")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexGenfv = GLContext.getFunctionAddress("glTexGenfv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexGendv = GLContext.getFunctionAddress("glTexGendv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexGeni = GLContext.getFunctionAddress("glTexGeni")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexGeniv = GLContext.getFunctionAddress("glTexGeniv")) != 0L) ? 1 : 0) & ((this.glTexEnvf = GLContext.getFunctionAddress("glTexEnvf")) != 0L ? 1 : 0) & ((this.glTexEnvi = GLContext.getFunctionAddress("glTexEnvi")) != 0L ? 1 : 0) & ((this.glTexEnvfv = GLContext.getFunctionAddress("glTexEnvfv")) != 0L ? 1 : 0) & ((this.glTexEnviv = GLContext.getFunctionAddress("glTexEnviv")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordPointer = GLContext.getFunctionAddress("glTexCoordPointer")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord1f = GLContext.getFunctionAddress("glTexCoord1f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord1d = GLContext.getFunctionAddress("glTexCoord1d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord2f = GLContext.getFunctionAddress("glTexCoord2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord2d = GLContext.getFunctionAddress("glTexCoord2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord3f = GLContext.getFunctionAddress("glTexCoord3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord3d = GLContext.getFunctionAddress("glTexCoord3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord4f = GLContext.getFunctionAddress("glTexCoord4f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoord4d = GLContext.getFunctionAddress("glTexCoord4d")) != 0L) ? 1 : 0) & ((this.glStencilOp = GLContext.getFunctionAddress("glStencilOp")) != 0L ? 1 : 0) & ((this.glStencilMask = GLContext.getFunctionAddress("glStencilMask")) != 0L ? 1 : 0) & ((this.glViewport = GLContext.getFunctionAddress("glViewport")) != 0L ? 1 : 0);
/* 3970:     */  }
/* 3971:     */  
/* 4200:     */  private boolean GL12_initNativeFunctionAddresses()
/* 4201:     */  {
/* 4202:4202 */    return ((this.glDrawRangeElements = GLContext.getFunctionAddress("glDrawRangeElements")) != 0L ? 1 : 0) & ((this.glTexImage3D = GLContext.getFunctionAddress("glTexImage3D")) != 0L ? 1 : 0) & ((this.glTexSubImage3D = GLContext.getFunctionAddress("glTexSubImage3D")) != 0L ? 1 : 0) & ((this.glCopyTexSubImage3D = GLContext.getFunctionAddress("glCopyTexSubImage3D")) != 0L ? 1 : 0);
/* 4203:     */  }
/* 4204:     */  
/* 4208:     */  private boolean GL13_initNativeFunctionAddresses(boolean forwardCompatible)
/* 4209:     */  {
/* 4210:4210 */    return ((this.glActiveTexture = GLContext.getFunctionAddress("glActiveTexture")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glClientActiveTexture = GLContext.getFunctionAddress("glClientActiveTexture")) != 0L) ? 1 : 0) & ((this.glCompressedTexImage1D = GLContext.getFunctionAddress("glCompressedTexImage1D")) != 0L ? 1 : 0) & ((this.glCompressedTexImage2D = GLContext.getFunctionAddress("glCompressedTexImage2D")) != 0L ? 1 : 0) & ((this.glCompressedTexImage3D = GLContext.getFunctionAddress("glCompressedTexImage3D")) != 0L ? 1 : 0) & ((this.glCompressedTexSubImage1D = GLContext.getFunctionAddress("glCompressedTexSubImage1D")) != 0L ? 1 : 0) & ((this.glCompressedTexSubImage2D = GLContext.getFunctionAddress("glCompressedTexSubImage2D")) != 0L ? 1 : 0) & ((this.glCompressedTexSubImage3D = GLContext.getFunctionAddress("glCompressedTexSubImage3D")) != 0L ? 1 : 0) & ((this.glGetCompressedTexImage = GLContext.getFunctionAddress("glGetCompressedTexImage")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord1f = GLContext.getFunctionAddress("glMultiTexCoord1f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord1d = GLContext.getFunctionAddress("glMultiTexCoord1d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord2f = GLContext.getFunctionAddress("glMultiTexCoord2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord2d = GLContext.getFunctionAddress("glMultiTexCoord2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord3f = GLContext.getFunctionAddress("glMultiTexCoord3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord3d = GLContext.getFunctionAddress("glMultiTexCoord3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord4f = GLContext.getFunctionAddress("glMultiTexCoord4f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoord4d = GLContext.getFunctionAddress("glMultiTexCoord4d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLoadTransposeMatrixf = GLContext.getFunctionAddress("glLoadTransposeMatrixf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glLoadTransposeMatrixd = GLContext.getFunctionAddress("glLoadTransposeMatrixd")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultTransposeMatrixf = GLContext.getFunctionAddress("glMultTransposeMatrixf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultTransposeMatrixd = GLContext.getFunctionAddress("glMultTransposeMatrixd")) != 0L) ? 1 : 0) & ((this.glSampleCoverage = GLContext.getFunctionAddress("glSampleCoverage")) != 0L ? 1 : 0);
/* 4211:     */  }
/* 4212:     */  
/* 4234:     */  private boolean GL14_initNativeFunctionAddresses(boolean forwardCompatible)
/* 4235:     */  {
/* 4236:4236 */    return ((this.glBlendEquation = GLContext.getFunctionAddress("glBlendEquation")) != 0L ? 1 : 0) & ((this.glBlendColor = GLContext.getFunctionAddress("glBlendColor")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glFogCoordf = GLContext.getFunctionAddress("glFogCoordf")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glFogCoordd = GLContext.getFunctionAddress("glFogCoordd")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glFogCoordPointer = GLContext.getFunctionAddress("glFogCoordPointer")) != 0L) ? 1 : 0) & ((this.glMultiDrawArrays = GLContext.getFunctionAddress("glMultiDrawArrays")) != 0L ? 1 : 0) & ((this.glPointParameteri = GLContext.getFunctionAddress("glPointParameteri")) != 0L ? 1 : 0) & ((this.glPointParameterf = GLContext.getFunctionAddress("glPointParameterf")) != 0L ? 1 : 0) & ((this.glPointParameteriv = GLContext.getFunctionAddress("glPointParameteriv")) != 0L ? 1 : 0) & ((this.glPointParameterfv = GLContext.getFunctionAddress("glPointParameterfv")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glSecondaryColor3b = GLContext.getFunctionAddress("glSecondaryColor3b")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glSecondaryColor3f = GLContext.getFunctionAddress("glSecondaryColor3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glSecondaryColor3d = GLContext.getFunctionAddress("glSecondaryColor3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glSecondaryColor3ub = GLContext.getFunctionAddress("glSecondaryColor3ub")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glSecondaryColorPointer = GLContext.getFunctionAddress("glSecondaryColorPointer")) != 0L) ? 1 : 0) & ((this.glBlendFuncSeparate = GLContext.getFunctionAddress("glBlendFuncSeparate")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos2f = GLContext.getFunctionAddress("glWindowPos2f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos2d = GLContext.getFunctionAddress("glWindowPos2d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos2i = GLContext.getFunctionAddress("glWindowPos2i")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos3f = GLContext.getFunctionAddress("glWindowPos3f")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos3d = GLContext.getFunctionAddress("glWindowPos3d")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glWindowPos3i = GLContext.getFunctionAddress("glWindowPos3i")) != 0L) ? 1 : 0);
/* 4237:     */  }
/* 4238:     */  
/* 4260:     */  private boolean GL15_initNativeFunctionAddresses()
/* 4261:     */  {
/* 4262:4262 */    return ((this.glBindBuffer = GLContext.getFunctionAddress("glBindBuffer")) != 0L ? 1 : 0) & ((this.glDeleteBuffers = GLContext.getFunctionAddress("glDeleteBuffers")) != 0L ? 1 : 0) & ((this.glGenBuffers = GLContext.getFunctionAddress("glGenBuffers")) != 0L ? 1 : 0) & ((this.glIsBuffer = GLContext.getFunctionAddress("glIsBuffer")) != 0L ? 1 : 0) & ((this.glBufferData = GLContext.getFunctionAddress("glBufferData")) != 0L ? 1 : 0) & ((this.glBufferSubData = GLContext.getFunctionAddress("glBufferSubData")) != 0L ? 1 : 0) & ((this.glGetBufferSubData = GLContext.getFunctionAddress("glGetBufferSubData")) != 0L ? 1 : 0) & ((this.glMapBuffer = GLContext.getFunctionAddress("glMapBuffer")) != 0L ? 1 : 0) & ((this.glUnmapBuffer = GLContext.getFunctionAddress("glUnmapBuffer")) != 0L ? 1 : 0) & ((this.glGetBufferParameteriv = GLContext.getFunctionAddress("glGetBufferParameteriv")) != 0L ? 1 : 0) & ((this.glGetBufferPointerv = GLContext.getFunctionAddress("glGetBufferPointerv")) != 0L ? 1 : 0) & ((this.glGenQueries = GLContext.getFunctionAddress("glGenQueries")) != 0L ? 1 : 0) & ((this.glDeleteQueries = GLContext.getFunctionAddress("glDeleteQueries")) != 0L ? 1 : 0) & ((this.glIsQuery = GLContext.getFunctionAddress("glIsQuery")) != 0L ? 1 : 0) & ((this.glBeginQuery = GLContext.getFunctionAddress("glBeginQuery")) != 0L ? 1 : 0) & ((this.glEndQuery = GLContext.getFunctionAddress("glEndQuery")) != 0L ? 1 : 0) & ((this.glGetQueryiv = GLContext.getFunctionAddress("glGetQueryiv")) != 0L ? 1 : 0) & ((this.glGetQueryObjectiv = GLContext.getFunctionAddress("glGetQueryObjectiv")) != 0L ? 1 : 0) & ((this.glGetQueryObjectuiv = GLContext.getFunctionAddress("glGetQueryObjectuiv")) != 0L ? 1 : 0);
/* 4263:     */  }
/* 4264:     */  
/* 4283:     */  private boolean GL20_initNativeFunctionAddresses()
/* 4284:     */  {
/* 4285:4285 */    return ((this.glShaderSource = GLContext.getFunctionAddress("glShaderSource")) != 0L ? 1 : 0) & ((this.glCreateShader = GLContext.getFunctionAddress("glCreateShader")) != 0L ? 1 : 0) & ((this.glIsShader = GLContext.getFunctionAddress("glIsShader")) != 0L ? 1 : 0) & ((this.glCompileShader = GLContext.getFunctionAddress("glCompileShader")) != 0L ? 1 : 0) & ((this.glDeleteShader = GLContext.getFunctionAddress("glDeleteShader")) != 0L ? 1 : 0) & ((this.glCreateProgram = GLContext.getFunctionAddress("glCreateProgram")) != 0L ? 1 : 0) & ((this.glIsProgram = GLContext.getFunctionAddress("glIsProgram")) != 0L ? 1 : 0) & ((this.glAttachShader = GLContext.getFunctionAddress("glAttachShader")) != 0L ? 1 : 0) & ((this.glDetachShader = GLContext.getFunctionAddress("glDetachShader")) != 0L ? 1 : 0) & ((this.glLinkProgram = GLContext.getFunctionAddress("glLinkProgram")) != 0L ? 1 : 0) & ((this.glUseProgram = GLContext.getFunctionAddress("glUseProgram")) != 0L ? 1 : 0) & ((this.glValidateProgram = GLContext.getFunctionAddress("glValidateProgram")) != 0L ? 1 : 0) & ((this.glDeleteProgram = GLContext.getFunctionAddress("glDeleteProgram")) != 0L ? 1 : 0) & ((this.glUniform1f = GLContext.getFunctionAddress("glUniform1f")) != 0L ? 1 : 0) & ((this.glUniform2f = GLContext.getFunctionAddress("glUniform2f")) != 0L ? 1 : 0) & ((this.glUniform3f = GLContext.getFunctionAddress("glUniform3f")) != 0L ? 1 : 0) & ((this.glUniform4f = GLContext.getFunctionAddress("glUniform4f")) != 0L ? 1 : 0) & ((this.glUniform1i = GLContext.getFunctionAddress("glUniform1i")) != 0L ? 1 : 0) & ((this.glUniform2i = GLContext.getFunctionAddress("glUniform2i")) != 0L ? 1 : 0) & ((this.glUniform3i = GLContext.getFunctionAddress("glUniform3i")) != 0L ? 1 : 0) & ((this.glUniform4i = GLContext.getFunctionAddress("glUniform4i")) != 0L ? 1 : 0) & ((this.glUniform1fv = GLContext.getFunctionAddress("glUniform1fv")) != 0L ? 1 : 0) & ((this.glUniform2fv = GLContext.getFunctionAddress("glUniform2fv")) != 0L ? 1 : 0) & ((this.glUniform3fv = GLContext.getFunctionAddress("glUniform3fv")) != 0L ? 1 : 0) & ((this.glUniform4fv = GLContext.getFunctionAddress("glUniform4fv")) != 0L ? 1 : 0) & ((this.glUniform1iv = GLContext.getFunctionAddress("glUniform1iv")) != 0L ? 1 : 0) & ((this.glUniform2iv = GLContext.getFunctionAddress("glUniform2iv")) != 0L ? 1 : 0) & ((this.glUniform3iv = GLContext.getFunctionAddress("glUniform3iv")) != 0L ? 1 : 0) & ((this.glUniform4iv = GLContext.getFunctionAddress("glUniform4iv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2fv = GLContext.getFunctionAddress("glUniformMatrix2fv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3fv = GLContext.getFunctionAddress("glUniformMatrix3fv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4fv = GLContext.getFunctionAddress("glUniformMatrix4fv")) != 0L ? 1 : 0) & ((this.glGetShaderiv = GLContext.getFunctionAddress("glGetShaderiv")) != 0L ? 1 : 0) & ((this.glGetProgramiv = GLContext.getFunctionAddress("glGetProgramiv")) != 0L ? 1 : 0) & ((this.glGetShaderInfoLog = GLContext.getFunctionAddress("glGetShaderInfoLog")) != 0L ? 1 : 0) & ((this.glGetProgramInfoLog = GLContext.getFunctionAddress("glGetProgramInfoLog")) != 0L ? 1 : 0) & ((this.glGetAttachedShaders = GLContext.getFunctionAddress("glGetAttachedShaders")) != 0L ? 1 : 0) & ((this.glGetUniformLocation = GLContext.getFunctionAddress("glGetUniformLocation")) != 0L ? 1 : 0) & ((this.glGetActiveUniform = GLContext.getFunctionAddress("glGetActiveUniform")) != 0L ? 1 : 0) & ((this.glGetUniformfv = GLContext.getFunctionAddress("glGetUniformfv")) != 0L ? 1 : 0) & ((this.glGetUniformiv = GLContext.getFunctionAddress("glGetUniformiv")) != 0L ? 1 : 0) & ((this.glGetShaderSource = GLContext.getFunctionAddress("glGetShaderSource")) != 0L ? 1 : 0) & ((this.glVertexAttrib1s = GLContext.getFunctionAddress("glVertexAttrib1s")) != 0L ? 1 : 0) & ((this.glVertexAttrib1f = GLContext.getFunctionAddress("glVertexAttrib1f")) != 0L ? 1 : 0) & ((this.glVertexAttrib1d = GLContext.getFunctionAddress("glVertexAttrib1d")) != 0L ? 1 : 0) & ((this.glVertexAttrib2s = GLContext.getFunctionAddress("glVertexAttrib2s")) != 0L ? 1 : 0) & ((this.glVertexAttrib2f = GLContext.getFunctionAddress("glVertexAttrib2f")) != 0L ? 1 : 0) & ((this.glVertexAttrib2d = GLContext.getFunctionAddress("glVertexAttrib2d")) != 0L ? 1 : 0) & ((this.glVertexAttrib3s = GLContext.getFunctionAddress("glVertexAttrib3s")) != 0L ? 1 : 0) & ((this.glVertexAttrib3f = GLContext.getFunctionAddress("glVertexAttrib3f")) != 0L ? 1 : 0) & ((this.glVertexAttrib3d = GLContext.getFunctionAddress("glVertexAttrib3d")) != 0L ? 1 : 0) & ((this.glVertexAttrib4s = GLContext.getFunctionAddress("glVertexAttrib4s")) != 0L ? 1 : 0) & ((this.glVertexAttrib4f = GLContext.getFunctionAddress("glVertexAttrib4f")) != 0L ? 1 : 0) & ((this.glVertexAttrib4d = GLContext.getFunctionAddress("glVertexAttrib4d")) != 0L ? 1 : 0) & ((this.glVertexAttrib4Nub = GLContext.getFunctionAddress("glVertexAttrib4Nub")) != 0L ? 1 : 0) & ((this.glVertexAttribPointer = GLContext.getFunctionAddress("glVertexAttribPointer")) != 0L ? 1 : 0) & ((this.glEnableVertexAttribArray = GLContext.getFunctionAddress("glEnableVertexAttribArray")) != 0L ? 1 : 0) & ((this.glDisableVertexAttribArray = GLContext.getFunctionAddress("glDisableVertexAttribArray")) != 0L ? 1 : 0) & ((this.glGetVertexAttribfv = GLContext.getFunctionAddress("glGetVertexAttribfv")) != 0L ? 1 : 0) & ((this.glGetVertexAttribdv = GLContext.getFunctionAddress("glGetVertexAttribdv")) != 0L ? 1 : 0) & ((this.glGetVertexAttribiv = GLContext.getFunctionAddress("glGetVertexAttribiv")) != 0L ? 1 : 0) & ((this.glGetVertexAttribPointerv = GLContext.getFunctionAddress("glGetVertexAttribPointerv")) != 0L ? 1 : 0) & ((this.glBindAttribLocation = GLContext.getFunctionAddress("glBindAttribLocation")) != 0L ? 1 : 0) & ((this.glGetActiveAttrib = GLContext.getFunctionAddress("glGetActiveAttrib")) != 0L ? 1 : 0) & ((this.glGetAttribLocation = GLContext.getFunctionAddress("glGetAttribLocation")) != 0L ? 1 : 0) & ((this.glDrawBuffers = GLContext.getFunctionAddress("glDrawBuffers")) != 0L ? 1 : 0) & ((this.glStencilOpSeparate = GLContext.getFunctionAddress("glStencilOpSeparate")) != 0L ? 1 : 0) & ((this.glStencilFuncSeparate = GLContext.getFunctionAddress("glStencilFuncSeparate")) != 0L ? 1 : 0) & ((this.glStencilMaskSeparate = GLContext.getFunctionAddress("glStencilMaskSeparate")) != 0L ? 1 : 0) & ((this.glBlendEquationSeparate = GLContext.getFunctionAddress("glBlendEquationSeparate")) != 0L ? 1 : 0);
/* 4286:     */  }
/* 4287:     */  
/* 4357:     */  private boolean GL21_initNativeFunctionAddresses()
/* 4358:     */  {
/* 4359:4359 */    return ((this.glUniformMatrix2x3fv = GLContext.getFunctionAddress("glUniformMatrix2x3fv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3x2fv = GLContext.getFunctionAddress("glUniformMatrix3x2fv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2x4fv = GLContext.getFunctionAddress("glUniformMatrix2x4fv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4x2fv = GLContext.getFunctionAddress("glUniformMatrix4x2fv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3x4fv = GLContext.getFunctionAddress("glUniformMatrix3x4fv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4x3fv = GLContext.getFunctionAddress("glUniformMatrix4x3fv")) != 0L ? 1 : 0);
/* 4360:     */  }
/* 4361:     */  
/* 4367:     */  private boolean GL30_initNativeFunctionAddresses()
/* 4368:     */  {
/* 4369:4369 */    return ((this.glGetStringi = GLContext.getFunctionAddress("glGetStringi")) != 0L ? 1 : 0) & ((this.glClearBufferfv = GLContext.getFunctionAddress("glClearBufferfv")) != 0L ? 1 : 0) & ((this.glClearBufferiv = GLContext.getFunctionAddress("glClearBufferiv")) != 0L ? 1 : 0) & ((this.glClearBufferuiv = GLContext.getFunctionAddress("glClearBufferuiv")) != 0L ? 1 : 0) & ((this.glClearBufferfi = GLContext.getFunctionAddress("glClearBufferfi")) != 0L ? 1 : 0) & ((this.glVertexAttribI1i = GLContext.getFunctionAddress("glVertexAttribI1i")) != 0L ? 1 : 0) & ((this.glVertexAttribI2i = GLContext.getFunctionAddress("glVertexAttribI2i")) != 0L ? 1 : 0) & ((this.glVertexAttribI3i = GLContext.getFunctionAddress("glVertexAttribI3i")) != 0L ? 1 : 0) & ((this.glVertexAttribI4i = GLContext.getFunctionAddress("glVertexAttribI4i")) != 0L ? 1 : 0) & ((this.glVertexAttribI1ui = GLContext.getFunctionAddress("glVertexAttribI1ui")) != 0L ? 1 : 0) & ((this.glVertexAttribI2ui = GLContext.getFunctionAddress("glVertexAttribI2ui")) != 0L ? 1 : 0) & ((this.glVertexAttribI3ui = GLContext.getFunctionAddress("glVertexAttribI3ui")) != 0L ? 1 : 0) & ((this.glVertexAttribI4ui = GLContext.getFunctionAddress("glVertexAttribI4ui")) != 0L ? 1 : 0) & ((this.glVertexAttribI1iv = GLContext.getFunctionAddress("glVertexAttribI1iv")) != 0L ? 1 : 0) & ((this.glVertexAttribI2iv = GLContext.getFunctionAddress("glVertexAttribI2iv")) != 0L ? 1 : 0) & ((this.glVertexAttribI3iv = GLContext.getFunctionAddress("glVertexAttribI3iv")) != 0L ? 1 : 0) & ((this.glVertexAttribI4iv = GLContext.getFunctionAddress("glVertexAttribI4iv")) != 0L ? 1 : 0) & ((this.glVertexAttribI1uiv = GLContext.getFunctionAddress("glVertexAttribI1uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribI2uiv = GLContext.getFunctionAddress("glVertexAttribI2uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribI3uiv = GLContext.getFunctionAddress("glVertexAttribI3uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribI4uiv = GLContext.getFunctionAddress("glVertexAttribI4uiv")) != 0L ? 1 : 0) & ((this.glVertexAttribI4bv = GLContext.getFunctionAddress("glVertexAttribI4bv")) != 0L ? 1 : 0) & ((this.glVertexAttribI4sv = GLContext.getFunctionAddress("glVertexAttribI4sv")) != 0L ? 1 : 0) & ((this.glVertexAttribI4ubv = GLContext.getFunctionAddress("glVertexAttribI4ubv")) != 0L ? 1 : 0) & ((this.glVertexAttribI4usv = GLContext.getFunctionAddress("glVertexAttribI4usv")) != 0L ? 1 : 0) & ((this.glVertexAttribIPointer = GLContext.getFunctionAddress("glVertexAttribIPointer")) != 0L ? 1 : 0) & ((this.glGetVertexAttribIiv = GLContext.getFunctionAddress("glGetVertexAttribIiv")) != 0L ? 1 : 0) & ((this.glGetVertexAttribIuiv = GLContext.getFunctionAddress("glGetVertexAttribIuiv")) != 0L ? 1 : 0) & ((this.glUniform1ui = GLContext.getFunctionAddress("glUniform1ui")) != 0L ? 1 : 0) & ((this.glUniform2ui = GLContext.getFunctionAddress("glUniform2ui")) != 0L ? 1 : 0) & ((this.glUniform3ui = GLContext.getFunctionAddress("glUniform3ui")) != 0L ? 1 : 0) & ((this.glUniform4ui = GLContext.getFunctionAddress("glUniform4ui")) != 0L ? 1 : 0) & ((this.glUniform1uiv = GLContext.getFunctionAddress("glUniform1uiv")) != 0L ? 1 : 0) & ((this.glUniform2uiv = GLContext.getFunctionAddress("glUniform2uiv")) != 0L ? 1 : 0) & ((this.glUniform3uiv = GLContext.getFunctionAddress("glUniform3uiv")) != 0L ? 1 : 0) & ((this.glUniform4uiv = GLContext.getFunctionAddress("glUniform4uiv")) != 0L ? 1 : 0) & ((this.glGetUniformuiv = GLContext.getFunctionAddress("glGetUniformuiv")) != 0L ? 1 : 0) & ((this.glBindFragDataLocation = GLContext.getFunctionAddress("glBindFragDataLocation")) != 0L ? 1 : 0) & ((this.glGetFragDataLocation = GLContext.getFunctionAddress("glGetFragDataLocation")) != 0L ? 1 : 0) & ((this.glBeginConditionalRender = GLContext.getFunctionAddress("glBeginConditionalRender")) != 0L ? 1 : 0) & ((this.glEndConditionalRender = GLContext.getFunctionAddress("glEndConditionalRender")) != 0L ? 1 : 0) & ((this.glMapBufferRange = GLContext.getFunctionAddress("glMapBufferRange")) != 0L ? 1 : 0) & ((this.glFlushMappedBufferRange = GLContext.getFunctionAddress("glFlushMappedBufferRange")) != 0L ? 1 : 0) & ((this.glClampColor = GLContext.getFunctionAddress("glClampColor")) != 0L ? 1 : 0) & ((this.glIsRenderbuffer = GLContext.getFunctionAddress("glIsRenderbuffer")) != 0L ? 1 : 0) & ((this.glBindRenderbuffer = GLContext.getFunctionAddress("glBindRenderbuffer")) != 0L ? 1 : 0) & ((this.glDeleteRenderbuffers = GLContext.getFunctionAddress("glDeleteRenderbuffers")) != 0L ? 1 : 0) & ((this.glGenRenderbuffers = GLContext.getFunctionAddress("glGenRenderbuffers")) != 0L ? 1 : 0) & ((this.glRenderbufferStorage = GLContext.getFunctionAddress("glRenderbufferStorage")) != 0L ? 1 : 0) & ((this.glGetRenderbufferParameteriv = GLContext.getFunctionAddress("glGetRenderbufferParameteriv")) != 0L ? 1 : 0) & ((this.glIsFramebuffer = GLContext.getFunctionAddress("glIsFramebuffer")) != 0L ? 1 : 0) & ((this.glBindFramebuffer = GLContext.getFunctionAddress("glBindFramebuffer")) != 0L ? 1 : 0) & ((this.glDeleteFramebuffers = GLContext.getFunctionAddress("glDeleteFramebuffers")) != 0L ? 1 : 0) & ((this.glGenFramebuffers = GLContext.getFunctionAddress("glGenFramebuffers")) != 0L ? 1 : 0) & ((this.glCheckFramebufferStatus = GLContext.getFunctionAddress("glCheckFramebufferStatus")) != 0L ? 1 : 0) & ((this.glFramebufferTexture1D = GLContext.getFunctionAddress("glFramebufferTexture1D")) != 0L ? 1 : 0) & ((this.glFramebufferTexture2D = GLContext.getFunctionAddress("glFramebufferTexture2D")) != 0L ? 1 : 0) & ((this.glFramebufferTexture3D = GLContext.getFunctionAddress("glFramebufferTexture3D")) != 0L ? 1 : 0) & ((this.glFramebufferRenderbuffer = GLContext.getFunctionAddress("glFramebufferRenderbuffer")) != 0L ? 1 : 0) & ((this.glGetFramebufferAttachmentParameteriv = GLContext.getFunctionAddress("glGetFramebufferAttachmentParameteriv")) != 0L ? 1 : 0) & ((this.glGenerateMipmap = GLContext.getFunctionAddress("glGenerateMipmap")) != 0L ? 1 : 0) & ((this.glRenderbufferStorageMultisample = GLContext.getFunctionAddress("glRenderbufferStorageMultisample")) != 0L ? 1 : 0) & ((this.glBlitFramebuffer = GLContext.getFunctionAddress("glBlitFramebuffer")) != 0L ? 1 : 0) & ((this.glTexParameterIiv = GLContext.getFunctionAddress("glTexParameterIiv")) != 0L ? 1 : 0) & ((this.glTexParameterIuiv = GLContext.getFunctionAddress("glTexParameterIuiv")) != 0L ? 1 : 0) & ((this.glGetTexParameterIiv = GLContext.getFunctionAddress("glGetTexParameterIiv")) != 0L ? 1 : 0) & ((this.glGetTexParameterIuiv = GLContext.getFunctionAddress("glGetTexParameterIuiv")) != 0L ? 1 : 0) & ((this.glFramebufferTextureLayer = GLContext.getFunctionAddress("glFramebufferTextureLayer")) != 0L ? 1 : 0) & ((this.glColorMaski = GLContext.getFunctionAddress("glColorMaski")) != 0L ? 1 : 0) & ((this.glGetBooleani_v = GLContext.getFunctionAddress("glGetBooleani_v")) != 0L ? 1 : 0) & ((this.glGetIntegeri_v = GLContext.getFunctionAddress("glGetIntegeri_v")) != 0L ? 1 : 0) & ((this.glEnablei = GLContext.getFunctionAddress("glEnablei")) != 0L ? 1 : 0) & ((this.glDisablei = GLContext.getFunctionAddress("glDisablei")) != 0L ? 1 : 0) & ((this.glIsEnabledi = GLContext.getFunctionAddress("glIsEnabledi")) != 0L ? 1 : 0) & ((this.glBindBufferRange = GLContext.getFunctionAddress("glBindBufferRange")) != 0L ? 1 : 0) & ((this.glBindBufferBase = GLContext.getFunctionAddress("glBindBufferBase")) != 0L ? 1 : 0) & ((this.glBeginTransformFeedback = GLContext.getFunctionAddress("glBeginTransformFeedback")) != 0L ? 1 : 0) & ((this.glEndTransformFeedback = GLContext.getFunctionAddress("glEndTransformFeedback")) != 0L ? 1 : 0) & ((this.glTransformFeedbackVaryings = GLContext.getFunctionAddress("glTransformFeedbackVaryings")) != 0L ? 1 : 0) & ((this.glGetTransformFeedbackVarying = GLContext.getFunctionAddress("glGetTransformFeedbackVarying")) != 0L ? 1 : 0) & ((this.glBindVertexArray = GLContext.getFunctionAddress("glBindVertexArray")) != 0L ? 1 : 0) & ((this.glDeleteVertexArrays = GLContext.getFunctionAddress("glDeleteVertexArrays")) != 0L ? 1 : 0) & ((this.glGenVertexArrays = GLContext.getFunctionAddress("glGenVertexArrays")) != 0L ? 1 : 0) & ((this.glIsVertexArray = GLContext.getFunctionAddress("glIsVertexArray")) != 0L ? 1 : 0);
/* 4370:     */  }
/* 4371:     */  
/* 4455:     */  private boolean GL31_initNativeFunctionAddresses()
/* 4456:     */  {
/* 4457:4457 */    return ((this.glDrawArraysInstanced = GLContext.getFunctionAddress("glDrawArraysInstanced")) != 0L ? 1 : 0) & ((this.glDrawElementsInstanced = GLContext.getFunctionAddress("glDrawElementsInstanced")) != 0L ? 1 : 0) & ((this.glCopyBufferSubData = GLContext.getFunctionAddress("glCopyBufferSubData")) != 0L ? 1 : 0) & ((this.glPrimitiveRestartIndex = GLContext.getFunctionAddress("glPrimitiveRestartIndex")) != 0L ? 1 : 0) & ((this.glTexBuffer = GLContext.getFunctionAddress("glTexBuffer")) != 0L ? 1 : 0) & ((this.glGetUniformIndices = GLContext.getFunctionAddress("glGetUniformIndices")) != 0L ? 1 : 0) & ((this.glGetActiveUniformsiv = GLContext.getFunctionAddress("glGetActiveUniformsiv")) != 0L ? 1 : 0) & ((this.glGetActiveUniformName = GLContext.getFunctionAddress("glGetActiveUniformName")) != 0L ? 1 : 0) & ((this.glGetUniformBlockIndex = GLContext.getFunctionAddress("glGetUniformBlockIndex")) != 0L ? 1 : 0) & ((this.glGetActiveUniformBlockiv = GLContext.getFunctionAddress("glGetActiveUniformBlockiv")) != 0L ? 1 : 0) & ((this.glGetActiveUniformBlockName = GLContext.getFunctionAddress("glGetActiveUniformBlockName")) != 0L ? 1 : 0) & ((this.glUniformBlockBinding = GLContext.getFunctionAddress("glUniformBlockBinding")) != 0L ? 1 : 0);
/* 4458:     */  }
/* 4459:     */  
/* 4471:     */  private boolean GL32_initNativeFunctionAddresses()
/* 4472:     */  {
/* 4473:4473 */    return ((this.glGetBufferParameteri64v = GLContext.getFunctionAddress("glGetBufferParameteri64v")) != 0L ? 1 : 0) & ((this.glDrawElementsBaseVertex = GLContext.getFunctionAddress("glDrawElementsBaseVertex")) != 0L ? 1 : 0) & ((this.glDrawRangeElementsBaseVertex = GLContext.getFunctionAddress("glDrawRangeElementsBaseVertex")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedBaseVertex = GLContext.getFunctionAddress("glDrawElementsInstancedBaseVertex")) != 0L ? 1 : 0) & ((this.glProvokingVertex = GLContext.getFunctionAddress("glProvokingVertex")) != 0L ? 1 : 0) & ((this.glTexImage2DMultisample = GLContext.getFunctionAddress("glTexImage2DMultisample")) != 0L ? 1 : 0) & ((this.glTexImage3DMultisample = GLContext.getFunctionAddress("glTexImage3DMultisample")) != 0L ? 1 : 0) & ((this.glGetMultisamplefv = GLContext.getFunctionAddress("glGetMultisamplefv")) != 0L ? 1 : 0) & ((this.glSampleMaski = GLContext.getFunctionAddress("glSampleMaski")) != 0L ? 1 : 0) & ((this.glFramebufferTexture = GLContext.getFunctionAddress("glFramebufferTexture")) != 0L ? 1 : 0) & ((this.glFenceSync = GLContext.getFunctionAddress("glFenceSync")) != 0L ? 1 : 0) & ((this.glIsSync = GLContext.getFunctionAddress("glIsSync")) != 0L ? 1 : 0) & ((this.glDeleteSync = GLContext.getFunctionAddress("glDeleteSync")) != 0L ? 1 : 0) & ((this.glClientWaitSync = GLContext.getFunctionAddress("glClientWaitSync")) != 0L ? 1 : 0) & ((this.glWaitSync = GLContext.getFunctionAddress("glWaitSync")) != 0L ? 1 : 0) & ((this.glGetInteger64v = GLContext.getFunctionAddress("glGetInteger64v")) != 0L ? 1 : 0) & 0x1 & (((this.glGetInteger64i_v = GLContext.getFunctionAddress("glGetInteger64i_v")) != 0L) || ((this.glGetSynciv = GLContext.getFunctionAddress("glGetSynciv")) != 0L) ? 1 : 0);
/* 4474:     */  }
/* 4475:     */  
/* 4493:     */  private boolean GL33_initNativeFunctionAddresses(boolean forwardCompatible)
/* 4494:     */  {
/* 4495:4495 */    return ((this.glBindFragDataLocationIndexed = GLContext.getFunctionAddress("glBindFragDataLocationIndexed")) != 0L ? 1 : 0) & ((this.glGetFragDataIndex = GLContext.getFunctionAddress("glGetFragDataIndex")) != 0L ? 1 : 0) & ((this.glGenSamplers = GLContext.getFunctionAddress("glGenSamplers")) != 0L ? 1 : 0) & ((this.glDeleteSamplers = GLContext.getFunctionAddress("glDeleteSamplers")) != 0L ? 1 : 0) & ((this.glIsSampler = GLContext.getFunctionAddress("glIsSampler")) != 0L ? 1 : 0) & ((this.glBindSampler = GLContext.getFunctionAddress("glBindSampler")) != 0L ? 1 : 0) & ((this.glSamplerParameteri = GLContext.getFunctionAddress("glSamplerParameteri")) != 0L ? 1 : 0) & ((this.glSamplerParameterf = GLContext.getFunctionAddress("glSamplerParameterf")) != 0L ? 1 : 0) & ((this.glSamplerParameteriv = GLContext.getFunctionAddress("glSamplerParameteriv")) != 0L ? 1 : 0) & ((this.glSamplerParameterfv = GLContext.getFunctionAddress("glSamplerParameterfv")) != 0L ? 1 : 0) & ((this.glSamplerParameterIiv = GLContext.getFunctionAddress("glSamplerParameterIiv")) != 0L ? 1 : 0) & ((this.glSamplerParameterIuiv = GLContext.getFunctionAddress("glSamplerParameterIuiv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameteriv = GLContext.getFunctionAddress("glGetSamplerParameteriv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameterfv = GLContext.getFunctionAddress("glGetSamplerParameterfv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameterIiv = GLContext.getFunctionAddress("glGetSamplerParameterIiv")) != 0L ? 1 : 0) & ((this.glGetSamplerParameterIuiv = GLContext.getFunctionAddress("glGetSamplerParameterIuiv")) != 0L ? 1 : 0) & ((this.glQueryCounter = GLContext.getFunctionAddress("glQueryCounter")) != 0L ? 1 : 0) & ((this.glGetQueryObjecti64v = GLContext.getFunctionAddress("glGetQueryObjecti64v")) != 0L ? 1 : 0) & ((this.glGetQueryObjectui64v = GLContext.getFunctionAddress("glGetQueryObjectui64v")) != 0L ? 1 : 0) & ((this.glVertexAttribDivisor = GLContext.getFunctionAddress("glVertexAttribDivisor")) != 0L ? 1 : 0) & ((forwardCompatible) || ((this.glVertexP2ui = GLContext.getFunctionAddress("glVertexP2ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexP3ui = GLContext.getFunctionAddress("glVertexP3ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexP4ui = GLContext.getFunctionAddress("glVertexP4ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexP2uiv = GLContext.getFunctionAddress("glVertexP2uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexP3uiv = GLContext.getFunctionAddress("glVertexP3uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexP4uiv = GLContext.getFunctionAddress("glVertexP4uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP1ui = GLContext.getFunctionAddress("glTexCoordP1ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP2ui = GLContext.getFunctionAddress("glTexCoordP2ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP3ui = GLContext.getFunctionAddress("glTexCoordP3ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP4ui = GLContext.getFunctionAddress("glTexCoordP4ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP1uiv = GLContext.getFunctionAddress("glTexCoordP1uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP2uiv = GLContext.getFunctionAddress("glTexCoordP2uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP3uiv = GLContext.getFunctionAddress("glTexCoordP3uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glTexCoordP4uiv = GLContext.getFunctionAddress("glTexCoordP4uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP1ui = GLContext.getFunctionAddress("glMultiTexCoordP1ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP2ui = GLContext.getFunctionAddress("glMultiTexCoordP2ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP3ui = GLContext.getFunctionAddress("glMultiTexCoordP3ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP4ui = GLContext.getFunctionAddress("glMultiTexCoordP4ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP1uiv = GLContext.getFunctionAddress("glMultiTexCoordP1uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP2uiv = GLContext.getFunctionAddress("glMultiTexCoordP2uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP3uiv = GLContext.getFunctionAddress("glMultiTexCoordP3uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glMultiTexCoordP4uiv = GLContext.getFunctionAddress("glMultiTexCoordP4uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNormalP3ui = GLContext.getFunctionAddress("glNormalP3ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glNormalP3uiv = GLContext.getFunctionAddress("glNormalP3uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorP3ui = GLContext.getFunctionAddress("glColorP3ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorP4ui = GLContext.getFunctionAddress("glColorP4ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorP3uiv = GLContext.getFunctionAddress("glColorP3uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glColorP4uiv = GLContext.getFunctionAddress("glColorP4uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glSecondaryColorP3ui = GLContext.getFunctionAddress("glSecondaryColorP3ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glSecondaryColorP3uiv = GLContext.getFunctionAddress("glSecondaryColorP3uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP1ui = GLContext.getFunctionAddress("glVertexAttribP1ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP2ui = GLContext.getFunctionAddress("glVertexAttribP2ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP3ui = GLContext.getFunctionAddress("glVertexAttribP3ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP4ui = GLContext.getFunctionAddress("glVertexAttribP4ui")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP1uiv = GLContext.getFunctionAddress("glVertexAttribP1uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP2uiv = GLContext.getFunctionAddress("glVertexAttribP2uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP3uiv = GLContext.getFunctionAddress("glVertexAttribP3uiv")) != 0L) ? 1 : 0) & ((forwardCompatible) || ((this.glVertexAttribP4uiv = GLContext.getFunctionAddress("glVertexAttribP4uiv")) != 0L) ? 1 : 0);
/* 4496:     */  }
/* 4497:     */  
/* 4555:     */  private boolean GL40_initNativeFunctionAddresses()
/* 4556:     */  {
/* 4557:4557 */    return ((this.glBlendEquationi = GLContext.getFunctionAddress("glBlendEquationi")) != 0L ? 1 : 0) & ((this.glBlendEquationSeparatei = GLContext.getFunctionAddress("glBlendEquationSeparatei")) != 0L ? 1 : 0) & ((this.glBlendFunci = GLContext.getFunctionAddress("glBlendFunci")) != 0L ? 1 : 0) & ((this.glBlendFuncSeparatei = GLContext.getFunctionAddress("glBlendFuncSeparatei")) != 0L ? 1 : 0) & ((this.glDrawArraysIndirect = GLContext.getFunctionAddress("glDrawArraysIndirect")) != 0L ? 1 : 0) & ((this.glDrawElementsIndirect = GLContext.getFunctionAddress("glDrawElementsIndirect")) != 0L ? 1 : 0) & ((this.glUniform1d = GLContext.getFunctionAddress("glUniform1d")) != 0L ? 1 : 0) & ((this.glUniform2d = GLContext.getFunctionAddress("glUniform2d")) != 0L ? 1 : 0) & ((this.glUniform3d = GLContext.getFunctionAddress("glUniform3d")) != 0L ? 1 : 0) & ((this.glUniform4d = GLContext.getFunctionAddress("glUniform4d")) != 0L ? 1 : 0) & ((this.glUniform1dv = GLContext.getFunctionAddress("glUniform1dv")) != 0L ? 1 : 0) & ((this.glUniform2dv = GLContext.getFunctionAddress("glUniform2dv")) != 0L ? 1 : 0) & ((this.glUniform3dv = GLContext.getFunctionAddress("glUniform3dv")) != 0L ? 1 : 0) & ((this.glUniform4dv = GLContext.getFunctionAddress("glUniform4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2dv = GLContext.getFunctionAddress("glUniformMatrix2dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3dv = GLContext.getFunctionAddress("glUniformMatrix3dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4dv = GLContext.getFunctionAddress("glUniformMatrix4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2x3dv = GLContext.getFunctionAddress("glUniformMatrix2x3dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix2x4dv = GLContext.getFunctionAddress("glUniformMatrix2x4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3x2dv = GLContext.getFunctionAddress("glUniformMatrix3x2dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix3x4dv = GLContext.getFunctionAddress("glUniformMatrix3x4dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4x2dv = GLContext.getFunctionAddress("glUniformMatrix4x2dv")) != 0L ? 1 : 0) & ((this.glUniformMatrix4x3dv = GLContext.getFunctionAddress("glUniformMatrix4x3dv")) != 0L ? 1 : 0) & ((this.glGetUniformdv = GLContext.getFunctionAddress("glGetUniformdv")) != 0L ? 1 : 0) & ((this.glMinSampleShading = GLContext.getFunctionAddress("glMinSampleShading")) != 0L ? 1 : 0) & ((this.glGetSubroutineUniformLocation = GLContext.getFunctionAddress("glGetSubroutineUniformLocation")) != 0L ? 1 : 0) & ((this.glGetSubroutineIndex = GLContext.getFunctionAddress("glGetSubroutineIndex")) != 0L ? 1 : 0) & ((this.glGetActiveSubroutineUniformiv = GLContext.getFunctionAddress("glGetActiveSubroutineUniformiv")) != 0L ? 1 : 0) & ((this.glGetActiveSubroutineUniformName = GLContext.getFunctionAddress("glGetActiveSubroutineUniformName")) != 0L ? 1 : 0) & ((this.glGetActiveSubroutineName = GLContext.getFunctionAddress("glGetActiveSubroutineName")) != 0L ? 1 : 0) & ((this.glUniformSubroutinesuiv = GLContext.getFunctionAddress("glUniformSubroutinesuiv")) != 0L ? 1 : 0) & ((this.glGetUniformSubroutineuiv = GLContext.getFunctionAddress("glGetUniformSubroutineuiv")) != 0L ? 1 : 0) & ((this.glGetProgramStageiv = GLContext.getFunctionAddress("glGetProgramStageiv")) != 0L ? 1 : 0) & ((this.glPatchParameteri = GLContext.getFunctionAddress("glPatchParameteri")) != 0L ? 1 : 0) & ((this.glPatchParameterfv = GLContext.getFunctionAddress("glPatchParameterfv")) != 0L ? 1 : 0) & ((this.glBindTransformFeedback = GLContext.getFunctionAddress("glBindTransformFeedback")) != 0L ? 1 : 0) & ((this.glDeleteTransformFeedbacks = GLContext.getFunctionAddress("glDeleteTransformFeedbacks")) != 0L ? 1 : 0) & ((this.glGenTransformFeedbacks = GLContext.getFunctionAddress("glGenTransformFeedbacks")) != 0L ? 1 : 0) & ((this.glIsTransformFeedback = GLContext.getFunctionAddress("glIsTransformFeedback")) != 0L ? 1 : 0) & ((this.glPauseTransformFeedback = GLContext.getFunctionAddress("glPauseTransformFeedback")) != 0L ? 1 : 0) & ((this.glResumeTransformFeedback = GLContext.getFunctionAddress("glResumeTransformFeedback")) != 0L ? 1 : 0) & ((this.glDrawTransformFeedback = GLContext.getFunctionAddress("glDrawTransformFeedback")) != 0L ? 1 : 0) & ((this.glDrawTransformFeedbackStream = GLContext.getFunctionAddress("glDrawTransformFeedbackStream")) != 0L ? 1 : 0) & ((this.glBeginQueryIndexed = GLContext.getFunctionAddress("glBeginQueryIndexed")) != 0L ? 1 : 0) & ((this.glEndQueryIndexed = GLContext.getFunctionAddress("glEndQueryIndexed")) != 0L ? 1 : 0) & ((this.glGetQueryIndexediv = GLContext.getFunctionAddress("glGetQueryIndexediv")) != 0L ? 1 : 0);
/* 4558:     */  }
/* 4559:     */  
/* 4605:     */  private boolean GL41_initNativeFunctionAddresses()
/* 4606:     */  {
/* 4607:4607 */    return ((this.glReleaseShaderCompiler = GLContext.getFunctionAddress("glReleaseShaderCompiler")) != 0L ? 1 : 0) & ((this.glShaderBinary = GLContext.getFunctionAddress("glShaderBinary")) != 0L ? 1 : 0) & ((this.glGetShaderPrecisionFormat = GLContext.getFunctionAddress("glGetShaderPrecisionFormat")) != 0L ? 1 : 0) & ((this.glDepthRangef = GLContext.getFunctionAddress("glDepthRangef")) != 0L ? 1 : 0) & ((this.glClearDepthf = GLContext.getFunctionAddress("glClearDepthf")) != 0L ? 1 : 0) & ((this.glGetProgramBinary = GLContext.getFunctionAddress("glGetProgramBinary")) != 0L ? 1 : 0) & ((this.glProgramBinary = GLContext.getFunctionAddress("glProgramBinary")) != 0L ? 1 : 0) & ((this.glProgramParameteri = GLContext.getFunctionAddress("glProgramParameteri")) != 0L ? 1 : 0) & ((this.glUseProgramStages = GLContext.getFunctionAddress("glUseProgramStages")) != 0L ? 1 : 0) & ((this.glActiveShaderProgram = GLContext.getFunctionAddress("glActiveShaderProgram")) != 0L ? 1 : 0) & ((this.glCreateShaderProgramv = GLContext.getFunctionAddress("glCreateShaderProgramv")) != 0L ? 1 : 0) & ((this.glBindProgramPipeline = GLContext.getFunctionAddress("glBindProgramPipeline")) != 0L ? 1 : 0) & ((this.glDeleteProgramPipelines = GLContext.getFunctionAddress("glDeleteProgramPipelines")) != 0L ? 1 : 0) & ((this.glGenProgramPipelines = GLContext.getFunctionAddress("glGenProgramPipelines")) != 0L ? 1 : 0) & ((this.glIsProgramPipeline = GLContext.getFunctionAddress("glIsProgramPipeline")) != 0L ? 1 : 0) & ((this.glGetProgramPipelineiv = GLContext.getFunctionAddress("glGetProgramPipelineiv")) != 0L ? 1 : 0) & ((this.glProgramUniform1i = GLContext.getFunctionAddress("glProgramUniform1i")) != 0L ? 1 : 0) & ((this.glProgramUniform2i = GLContext.getFunctionAddress("glProgramUniform2i")) != 0L ? 1 : 0) & ((this.glProgramUniform3i = GLContext.getFunctionAddress("glProgramUniform3i")) != 0L ? 1 : 0) & ((this.glProgramUniform4i = GLContext.getFunctionAddress("glProgramUniform4i")) != 0L ? 1 : 0) & ((this.glProgramUniform1f = GLContext.getFunctionAddress("glProgramUniform1f")) != 0L ? 1 : 0) & ((this.glProgramUniform2f = GLContext.getFunctionAddress("glProgramUniform2f")) != 0L ? 1 : 0) & ((this.glProgramUniform3f = GLContext.getFunctionAddress("glProgramUniform3f")) != 0L ? 1 : 0) & ((this.glProgramUniform4f = GLContext.getFunctionAddress("glProgramUniform4f")) != 0L ? 1 : 0) & ((this.glProgramUniform1d = GLContext.getFunctionAddress("glProgramUniform1d")) != 0L ? 1 : 0) & ((this.glProgramUniform2d = GLContext.getFunctionAddress("glProgramUniform2d")) != 0L ? 1 : 0) & ((this.glProgramUniform3d = GLContext.getFunctionAddress("glProgramUniform3d")) != 0L ? 1 : 0) & ((this.glProgramUniform4d = GLContext.getFunctionAddress("glProgramUniform4d")) != 0L ? 1 : 0) & ((this.glProgramUniform1iv = GLContext.getFunctionAddress("glProgramUniform1iv")) != 0L ? 1 : 0) & ((this.glProgramUniform2iv = GLContext.getFunctionAddress("glProgramUniform2iv")) != 0L ? 1 : 0) & ((this.glProgramUniform3iv = GLContext.getFunctionAddress("glProgramUniform3iv")) != 0L ? 1 : 0) & ((this.glProgramUniform4iv = GLContext.getFunctionAddress("glProgramUniform4iv")) != 0L ? 1 : 0) & ((this.glProgramUniform1fv = GLContext.getFunctionAddress("glProgramUniform1fv")) != 0L ? 1 : 0) & ((this.glProgramUniform2fv = GLContext.getFunctionAddress("glProgramUniform2fv")) != 0L ? 1 : 0) & ((this.glProgramUniform3fv = GLContext.getFunctionAddress("glProgramUniform3fv")) != 0L ? 1 : 0) & ((this.glProgramUniform4fv = GLContext.getFunctionAddress("glProgramUniform4fv")) != 0L ? 1 : 0) & ((this.glProgramUniform1dv = GLContext.getFunctionAddress("glProgramUniform1dv")) != 0L ? 1 : 0) & ((this.glProgramUniform2dv = GLContext.getFunctionAddress("glProgramUniform2dv")) != 0L ? 1 : 0) & ((this.glProgramUniform3dv = GLContext.getFunctionAddress("glProgramUniform3dv")) != 0L ? 1 : 0) & ((this.glProgramUniform4dv = GLContext.getFunctionAddress("glProgramUniform4dv")) != 0L ? 1 : 0) & ((this.glProgramUniform1ui = GLContext.getFunctionAddress("glProgramUniform1ui")) != 0L ? 1 : 0) & ((this.glProgramUniform2ui = GLContext.getFunctionAddress("glProgramUniform2ui")) != 0L ? 1 : 0) & ((this.glProgramUniform3ui = GLContext.getFunctionAddress("glProgramUniform3ui")) != 0L ? 1 : 0) & ((this.glProgramUniform4ui = GLContext.getFunctionAddress("glProgramUniform4ui")) != 0L ? 1 : 0) & ((this.glProgramUniform1uiv = GLContext.getFunctionAddress("glProgramUniform1uiv")) != 0L ? 1 : 0) & ((this.glProgramUniform2uiv = GLContext.getFunctionAddress("glProgramUniform2uiv")) != 0L ? 1 : 0) & ((this.glProgramUniform3uiv = GLContext.getFunctionAddress("glProgramUniform3uiv")) != 0L ? 1 : 0) & ((this.glProgramUniform4uiv = GLContext.getFunctionAddress("glProgramUniform4uiv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2fv = GLContext.getFunctionAddress("glProgramUniformMatrix2fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3fv = GLContext.getFunctionAddress("glProgramUniformMatrix3fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4fv = GLContext.getFunctionAddress("glProgramUniformMatrix4fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2dv = GLContext.getFunctionAddress("glProgramUniformMatrix2dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3dv = GLContext.getFunctionAddress("glProgramUniformMatrix3dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4dv = GLContext.getFunctionAddress("glProgramUniformMatrix4dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x3fv = GLContext.getFunctionAddress("glProgramUniformMatrix2x3fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x2fv = GLContext.getFunctionAddress("glProgramUniformMatrix3x2fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x4fv = GLContext.getFunctionAddress("glProgramUniformMatrix2x4fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x2fv = GLContext.getFunctionAddress("glProgramUniformMatrix4x2fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x4fv = GLContext.getFunctionAddress("glProgramUniformMatrix3x4fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x3fv = GLContext.getFunctionAddress("glProgramUniformMatrix4x3fv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x3dv = GLContext.getFunctionAddress("glProgramUniformMatrix2x3dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x2dv = GLContext.getFunctionAddress("glProgramUniformMatrix3x2dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix2x4dv = GLContext.getFunctionAddress("glProgramUniformMatrix2x4dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x2dv = GLContext.getFunctionAddress("glProgramUniformMatrix4x2dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix3x4dv = GLContext.getFunctionAddress("glProgramUniformMatrix3x4dv")) != 0L ? 1 : 0) & ((this.glProgramUniformMatrix4x3dv = GLContext.getFunctionAddress("glProgramUniformMatrix4x3dv")) != 0L ? 1 : 0) & ((this.glValidateProgramPipeline = GLContext.getFunctionAddress("glValidateProgramPipeline")) != 0L ? 1 : 0) & ((this.glGetProgramPipelineInfoLog = GLContext.getFunctionAddress("glGetProgramPipelineInfoLog")) != 0L ? 1 : 0) & ((this.glVertexAttribL1d = GLContext.getFunctionAddress("glVertexAttribL1d")) != 0L ? 1 : 0) & ((this.glVertexAttribL2d = GLContext.getFunctionAddress("glVertexAttribL2d")) != 0L ? 1 : 0) & ((this.glVertexAttribL3d = GLContext.getFunctionAddress("glVertexAttribL3d")) != 0L ? 1 : 0) & ((this.glVertexAttribL4d = GLContext.getFunctionAddress("glVertexAttribL4d")) != 0L ? 1 : 0) & ((this.glVertexAttribL1dv = GLContext.getFunctionAddress("glVertexAttribL1dv")) != 0L ? 1 : 0) & ((this.glVertexAttribL2dv = GLContext.getFunctionAddress("glVertexAttribL2dv")) != 0L ? 1 : 0) & ((this.glVertexAttribL3dv = GLContext.getFunctionAddress("glVertexAttribL3dv")) != 0L ? 1 : 0) & ((this.glVertexAttribL4dv = GLContext.getFunctionAddress("glVertexAttribL4dv")) != 0L ? 1 : 0) & ((this.glVertexAttribLPointer = GLContext.getFunctionAddress("glVertexAttribLPointer")) != 0L ? 1 : 0) & ((this.glGetVertexAttribLdv = GLContext.getFunctionAddress("glGetVertexAttribLdv")) != 0L ? 1 : 0) & ((this.glViewportArrayv = GLContext.getFunctionAddress("glViewportArrayv")) != 0L ? 1 : 0) & ((this.glViewportIndexedf = GLContext.getFunctionAddress("glViewportIndexedf")) != 0L ? 1 : 0) & ((this.glViewportIndexedfv = GLContext.getFunctionAddress("glViewportIndexedfv")) != 0L ? 1 : 0) & ((this.glScissorArrayv = GLContext.getFunctionAddress("glScissorArrayv")) != 0L ? 1 : 0) & ((this.glScissorIndexed = GLContext.getFunctionAddress("glScissorIndexed")) != 0L ? 1 : 0) & ((this.glScissorIndexedv = GLContext.getFunctionAddress("glScissorIndexedv")) != 0L ? 1 : 0) & ((this.glDepthRangeArrayv = GLContext.getFunctionAddress("glDepthRangeArrayv")) != 0L ? 1 : 0) & ((this.glDepthRangeIndexed = GLContext.getFunctionAddress("glDepthRangeIndexed")) != 0L ? 1 : 0) & ((this.glGetFloati_v = GLContext.getFunctionAddress("glGetFloati_v")) != 0L ? 1 : 0) & ((this.glGetDoublei_v = GLContext.getFunctionAddress("glGetDoublei_v")) != 0L ? 1 : 0);
/* 4608:     */  }
/* 4609:     */  
/* 4697:     */  private boolean GL42_initNativeFunctionAddresses()
/* 4698:     */  {
/* 4699:4699 */    return 0x1 & (((this.glGetActiveAtomicCounterBufferiv = GLContext.getFunctionAddress("glGetActiveAtomicCounterBufferiv")) != 0L) || ((this.glTexStorage1D = GLContext.getFunctionAddress("glTexStorage1D")) != 0L) ? 1 : 0) & ((this.glTexStorage2D = GLContext.getFunctionAddress("glTexStorage2D")) != 0L ? 1 : 0) & ((this.glTexStorage3D = GLContext.getFunctionAddress("glTexStorage3D")) != 0L ? 1 : 0) & ((this.glDrawTransformFeedbackInstanced = GLContext.getFunctionAddress("glDrawTransformFeedbackInstanced")) != 0L ? 1 : 0) & ((this.glDrawTransformFeedbackStreamInstanced = GLContext.getFunctionAddress("glDrawTransformFeedbackStreamInstanced")) != 0L ? 1 : 0) & ((this.glDrawArraysInstancedBaseInstance = GLContext.getFunctionAddress("glDrawArraysInstancedBaseInstance")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedBaseInstance = GLContext.getFunctionAddress("glDrawElementsInstancedBaseInstance")) != 0L ? 1 : 0) & ((this.glDrawElementsInstancedBaseVertexBaseInstance = GLContext.getFunctionAddress("glDrawElementsInstancedBaseVertexBaseInstance")) != 0L ? 1 : 0) & ((this.glBindImageTexture = GLContext.getFunctionAddress("glBindImageTexture")) != 0L ? 1 : 0) & ((this.glMemoryBarrier = GLContext.getFunctionAddress("glMemoryBarrier")) != 0L ? 1 : 0) & ((this.glGetInternalformativ = GLContext.getFunctionAddress("glGetInternalformativ")) != 0L ? 1 : 0);
/* 4700:     */  }
/* 4701:     */  
/* 4713:     */  private boolean GL43_initNativeFunctionAddresses()
/* 4714:     */  {
/* 4715:4715 */    return ((this.glClearBufferData = GLContext.getFunctionAddress("glClearBufferData")) != 0L ? 1 : 0) & ((this.glClearBufferSubData = GLContext.getFunctionAddress("glClearBufferSubData")) != 0L ? 1 : 0) & ((this.glDispatchCompute = GLContext.getFunctionAddress("glDispatchCompute")) != 0L ? 1 : 0) & ((this.glDispatchComputeIndirect = GLContext.getFunctionAddress("glDispatchComputeIndirect")) != 0L ? 1 : 0) & ((this.glCopyImageSubData = GLContext.getFunctionAddress("glCopyImageSubData")) != 0L ? 1 : 0) & ((this.glDebugMessageControl = GLContext.getFunctionAddress("glDebugMessageControl")) != 0L ? 1 : 0) & ((this.glDebugMessageInsert = GLContext.getFunctionAddress("glDebugMessageInsert")) != 0L ? 1 : 0) & ((this.glDebugMessageCallback = GLContext.getFunctionAddress("glDebugMessageCallback")) != 0L ? 1 : 0) & ((this.glGetDebugMessageLog = GLContext.getFunctionAddress("glGetDebugMessageLog")) != 0L ? 1 : 0) & ((this.glPushDebugGroup = GLContext.getFunctionAddress("glPushDebugGroup")) != 0L ? 1 : 0) & ((this.glPopDebugGroup = GLContext.getFunctionAddress("glPopDebugGroup")) != 0L ? 1 : 0) & ((this.glObjectLabel = GLContext.getFunctionAddress("glObjectLabel")) != 0L ? 1 : 0) & ((this.glGetObjectLabel = GLContext.getFunctionAddress("glGetObjectLabel")) != 0L ? 1 : 0) & ((this.glObjectPtrLabel = GLContext.getFunctionAddress("glObjectPtrLabel")) != 0L ? 1 : 0) & ((this.glGetObjectPtrLabel = GLContext.getFunctionAddress("glGetObjectPtrLabel")) != 0L ? 1 : 0) & ((this.glFramebufferParameteri = GLContext.getFunctionAddress("glFramebufferParameteri")) != 0L ? 1 : 0) & ((this.glGetFramebufferParameteriv = GLContext.getFunctionAddress("glGetFramebufferParameteriv")) != 0L ? 1 : 0) & ((this.glGetInternalformati64v = GLContext.getFunctionAddress("glGetInternalformati64v")) != 0L ? 1 : 0) & ((this.glInvalidateTexSubImage = GLContext.getFunctionAddress("glInvalidateTexSubImage")) != 0L ? 1 : 0) & ((this.glInvalidateTexImage = GLContext.getFunctionAddress("glInvalidateTexImage")) != 0L ? 1 : 0) & ((this.glInvalidateBufferSubData = GLContext.getFunctionAddress("glInvalidateBufferSubData")) != 0L ? 1 : 0) & ((this.glInvalidateBufferData = GLContext.getFunctionAddress("glInvalidateBufferData")) != 0L ? 1 : 0) & ((this.glInvalidateFramebuffer = GLContext.getFunctionAddress("glInvalidateFramebuffer")) != 0L ? 1 : 0) & ((this.glInvalidateSubFramebuffer = GLContext.getFunctionAddress("glInvalidateSubFramebuffer")) != 0L ? 1 : 0) & ((this.glMultiDrawArraysIndirect = GLContext.getFunctionAddress("glMultiDrawArraysIndirect")) != 0L ? 1 : 0) & ((this.glMultiDrawElementsIndirect = GLContext.getFunctionAddress("glMultiDrawElementsIndirect")) != 0L ? 1 : 0) & ((this.glGetProgramInterfaceiv = GLContext.getFunctionAddress("glGetProgramInterfaceiv")) != 0L ? 1 : 0) & ((this.glGetProgramResourceIndex = GLContext.getFunctionAddress("glGetProgramResourceIndex")) != 0L ? 1 : 0) & ((this.glGetProgramResourceName = GLContext.getFunctionAddress("glGetProgramResourceName")) != 0L ? 1 : 0) & ((this.glGetProgramResourceiv = GLContext.getFunctionAddress("glGetProgramResourceiv")) != 0L ? 1 : 0) & ((this.glGetProgramResourceLocation = GLContext.getFunctionAddress("glGetProgramResourceLocation")) != 0L ? 1 : 0) & ((this.glGetProgramResourceLocationIndex = GLContext.getFunctionAddress("glGetProgramResourceLocationIndex")) != 0L ? 1 : 0) & ((this.glShaderStorageBlockBinding = GLContext.getFunctionAddress("glShaderStorageBlockBinding")) != 0L ? 1 : 0) & ((this.glTexBufferRange = GLContext.getFunctionAddress("glTexBufferRange")) != 0L ? 1 : 0) & ((this.glTexStorage2DMultisample = GLContext.getFunctionAddress("glTexStorage2DMultisample")) != 0L ? 1 : 0) & ((this.glTexStorage3DMultisample = GLContext.getFunctionAddress("glTexStorage3DMultisample")) != 0L ? 1 : 0) & ((this.glTextureView = GLContext.getFunctionAddress("glTextureView")) != 0L ? 1 : 0) & ((this.glBindVertexBuffer = GLContext.getFunctionAddress("glBindVertexBuffer")) != 0L ? 1 : 0) & ((this.glVertexAttribFormat = GLContext.getFunctionAddress("glVertexAttribFormat")) != 0L ? 1 : 0) & ((this.glVertexAttribIFormat = GLContext.getFunctionAddress("glVertexAttribIFormat")) != 0L ? 1 : 0) & ((this.glVertexAttribLFormat = GLContext.getFunctionAddress("glVertexAttribLFormat")) != 0L ? 1 : 0) & ((this.glVertexAttribBinding = GLContext.getFunctionAddress("glVertexAttribBinding")) != 0L ? 1 : 0) & ((this.glVertexBindingDivisor = GLContext.getFunctionAddress("glVertexBindingDivisor")) != 0L ? 1 : 0);
/* 4716:     */  }
/* 4717:     */  
/* 4760:     */  private boolean GREMEDY_frame_terminator_initNativeFunctionAddresses()
/* 4761:     */  {
/* 4762:4762 */    return (this.glFrameTerminatorGREMEDY = GLContext.getFunctionAddress("glFrameTerminatorGREMEDY")) != 0L;
/* 4763:     */  }
/* 4764:     */  
/* 4765:     */  private boolean GREMEDY_string_marker_initNativeFunctionAddresses()
/* 4766:     */  {
/* 4767:4767 */    return (this.glStringMarkerGREMEDY = GLContext.getFunctionAddress("glStringMarkerGREMEDY")) != 0L;
/* 4768:     */  }
/* 4769:     */  
/* 4770:     */  private boolean INTEL_map_texture_initNativeFunctionAddresses()
/* 4771:     */  {
/* 4772:4772 */    return ((this.glMapTexture2DINTEL = GLContext.getFunctionAddress("glMapTexture2DINTEL")) != 0L ? 1 : 0) & ((this.glUnmapTexture2DINTEL = GLContext.getFunctionAddress("glUnmapTexture2DINTEL")) != 0L ? 1 : 0) & ((this.glSyncTextureINTEL = GLContext.getFunctionAddress("glSyncTextureINTEL")) != 0L ? 1 : 0);
/* 4773:     */  }
/* 4774:     */  
/* 4777:     */  private boolean KHR_debug_initNativeFunctionAddresses()
/* 4778:     */  {
/* 4779:4779 */    return ((this.glDebugMessageControl = GLContext.getFunctionAddress("glDebugMessageControl")) != 0L ? 1 : 0) & ((this.glDebugMessageInsert = GLContext.getFunctionAddress("glDebugMessageInsert")) != 0L ? 1 : 0) & ((this.glDebugMessageCallback = GLContext.getFunctionAddress("glDebugMessageCallback")) != 0L ? 1 : 0) & ((this.glGetDebugMessageLog = GLContext.getFunctionAddress("glGetDebugMessageLog")) != 0L ? 1 : 0) & ((this.glPushDebugGroup = GLContext.getFunctionAddress("glPushDebugGroup")) != 0L ? 1 : 0) & ((this.glPopDebugGroup = GLContext.getFunctionAddress("glPopDebugGroup")) != 0L ? 1 : 0) & ((this.glObjectLabel = GLContext.getFunctionAddress("glObjectLabel")) != 0L ? 1 : 0) & ((this.glGetObjectLabel = GLContext.getFunctionAddress("glGetObjectLabel")) != 0L ? 1 : 0) & ((this.glObjectPtrLabel = GLContext.getFunctionAddress("glObjectPtrLabel")) != 0L ? 1 : 0) & ((this.glGetObjectPtrLabel = GLContext.getFunctionAddress("glGetObjectPtrLabel")) != 0L ? 1 : 0);
/* 4780:     */  }
/* 4781:     */  
/* 4791:     */  private boolean NV_bindless_texture_initNativeFunctionAddresses()
/* 4792:     */  {
/* 4793:4793 */    return ((this.glGetTextureHandleNV = GLContext.getFunctionAddress("glGetTextureHandleNV")) != 0L ? 1 : 0) & ((this.glGetTextureSamplerHandleNV = GLContext.getFunctionAddress("glGetTextureSamplerHandleNV")) != 0L ? 1 : 0) & ((this.glMakeTextureHandleResidentNV = GLContext.getFunctionAddress("glMakeTextureHandleResidentNV")) != 0L ? 1 : 0) & ((this.glMakeTextureHandleNonResidentNV = GLContext.getFunctionAddress("glMakeTextureHandleNonResidentNV")) != 0L ? 1 : 0) & ((this.glGetImageHandleNV = GLContext.getFunctionAddress("glGetImageHandleNV")) != 0L ? 1 : 0) & ((this.glMakeImageHandleResidentNV = GLContext.getFunctionAddress("glMakeImageHandleResidentNV")) != 0L ? 1 : 0) & ((this.glMakeImageHandleNonResidentNV = GLContext.getFunctionAddress("glMakeImageHandleNonResidentNV")) != 0L ? 1 : 0) & ((this.glUniformHandleui64NV = GLContext.getFunctionAddress("glUniformHandleui64NV")) != 0L ? 1 : 0) & ((this.glUniformHandleui64vNV = GLContext.getFunctionAddress("glUniformHandleui64vNV")) != 0L ? 1 : 0) & ((this.glProgramUniformHandleui64NV = GLContext.getFunctionAddress("glProgramUniformHandleui64NV")) != 0L ? 1 : 0) & ((this.glProgramUniformHandleui64vNV = GLContext.getFunctionAddress("glProgramUniformHandleui64vNV")) != 0L ? 1 : 0) & ((this.glIsTextureHandleResidentNV = GLContext.getFunctionAddress("glIsTextureHandleResidentNV")) != 0L ? 1 : 0) & ((this.glIsImageHandleResidentNV = GLContext.getFunctionAddress("glIsImageHandleResidentNV")) != 0L ? 1 : 0);
/* 4794:     */  }
/* 4795:     */  
/* 4808:     */  private boolean NV_conditional_render_initNativeFunctionAddresses()
/* 4809:     */  {
/* 4810:4810 */    return ((this.glBeginConditionalRenderNV = GLContext.getFunctionAddress("glBeginConditionalRenderNV")) != 0L ? 1 : 0) & ((this.glEndConditionalRenderNV = GLContext.getFunctionAddress("glEndConditionalRenderNV")) != 0L ? 1 : 0);
/* 4811:     */  }
/* 4812:     */  
/* 4814:     */  private boolean NV_copy_image_initNativeFunctionAddresses()
/* 4815:     */  {
/* 4816:4816 */    return (this.glCopyImageSubDataNV = GLContext.getFunctionAddress("glCopyImageSubDataNV")) != 0L;
/* 4817:     */  }
/* 4818:     */  
/* 4819:     */  private boolean NV_depth_buffer_float_initNativeFunctionAddresses()
/* 4820:     */  {
/* 4821:4821 */    return ((this.glDepthRangedNV = GLContext.getFunctionAddress("glDepthRangedNV")) != 0L ? 1 : 0) & ((this.glClearDepthdNV = GLContext.getFunctionAddress("glClearDepthdNV")) != 0L ? 1 : 0) & ((this.glDepthBoundsdNV = GLContext.getFunctionAddress("glDepthBoundsdNV")) != 0L ? 1 : 0);
/* 4822:     */  }
/* 4823:     */  
/* 4826:     */  private boolean NV_draw_texture_initNativeFunctionAddresses()
/* 4827:     */  {
/* 4828:4828 */    return (this.glDrawTextureNV = GLContext.getFunctionAddress("glDrawTextureNV")) != 0L;
/* 4829:     */  }
/* 4830:     */  
/* 4831:     */  private boolean NV_evaluators_initNativeFunctionAddresses()
/* 4832:     */  {
/* 4833:4833 */    return ((this.glGetMapControlPointsNV = GLContext.getFunctionAddress("glGetMapControlPointsNV")) != 0L ? 1 : 0) & ((this.glMapControlPointsNV = GLContext.getFunctionAddress("glMapControlPointsNV")) != 0L ? 1 : 0) & ((this.glMapParameterfvNV = GLContext.getFunctionAddress("glMapParameterfvNV")) != 0L ? 1 : 0) & ((this.glMapParameterivNV = GLContext.getFunctionAddress("glMapParameterivNV")) != 0L ? 1 : 0) & ((this.glGetMapParameterfvNV = GLContext.getFunctionAddress("glGetMapParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetMapParameterivNV = GLContext.getFunctionAddress("glGetMapParameterivNV")) != 0L ? 1 : 0) & ((this.glGetMapAttribParameterfvNV = GLContext.getFunctionAddress("glGetMapAttribParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetMapAttribParameterivNV = GLContext.getFunctionAddress("glGetMapAttribParameterivNV")) != 0L ? 1 : 0) & ((this.glEvalMapsNV = GLContext.getFunctionAddress("glEvalMapsNV")) != 0L ? 1 : 0);
/* 4834:     */  }
/* 4835:     */  
/* 4844:     */  private boolean NV_explicit_multisample_initNativeFunctionAddresses()
/* 4845:     */  {
/* 4846:4846 */    return ((this.glGetBooleanIndexedvEXT = GLContext.getFunctionAddress("glGetBooleanIndexedvEXT")) != 0L ? 1 : 0) & ((this.glGetIntegerIndexedvEXT = GLContext.getFunctionAddress("glGetIntegerIndexedvEXT")) != 0L ? 1 : 0) & ((this.glGetMultisamplefvNV = GLContext.getFunctionAddress("glGetMultisamplefvNV")) != 0L ? 1 : 0) & ((this.glSampleMaskIndexedNV = GLContext.getFunctionAddress("glSampleMaskIndexedNV")) != 0L ? 1 : 0) & ((this.glTexRenderbufferNV = GLContext.getFunctionAddress("glTexRenderbufferNV")) != 0L ? 1 : 0);
/* 4847:     */  }
/* 4848:     */  
/* 4853:     */  private boolean NV_fence_initNativeFunctionAddresses()
/* 4854:     */  {
/* 4855:4855 */    return ((this.glGenFencesNV = GLContext.getFunctionAddress("glGenFencesNV")) != 0L ? 1 : 0) & ((this.glDeleteFencesNV = GLContext.getFunctionAddress("glDeleteFencesNV")) != 0L ? 1 : 0) & ((this.glSetFenceNV = GLContext.getFunctionAddress("glSetFenceNV")) != 0L ? 1 : 0) & ((this.glTestFenceNV = GLContext.getFunctionAddress("glTestFenceNV")) != 0L ? 1 : 0) & ((this.glFinishFenceNV = GLContext.getFunctionAddress("glFinishFenceNV")) != 0L ? 1 : 0) & ((this.glIsFenceNV = GLContext.getFunctionAddress("glIsFenceNV")) != 0L ? 1 : 0) & ((this.glGetFenceivNV = GLContext.getFunctionAddress("glGetFenceivNV")) != 0L ? 1 : 0);
/* 4856:     */  }
/* 4857:     */  
/* 4864:     */  private boolean NV_fragment_program_initNativeFunctionAddresses()
/* 4865:     */  {
/* 4866:4866 */    return ((this.glProgramNamedParameter4fNV = GLContext.getFunctionAddress("glProgramNamedParameter4fNV")) != 0L ? 1 : 0) & ((this.glProgramNamedParameter4dNV = GLContext.getFunctionAddress("glProgramNamedParameter4dNV")) != 0L ? 1 : 0) & ((this.glGetProgramNamedParameterfvNV = GLContext.getFunctionAddress("glGetProgramNamedParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetProgramNamedParameterdvNV = GLContext.getFunctionAddress("glGetProgramNamedParameterdvNV")) != 0L ? 1 : 0);
/* 4867:     */  }
/* 4868:     */  
/* 4872:     */  private boolean NV_framebuffer_multisample_coverage_initNativeFunctionAddresses()
/* 4873:     */  {
/* 4874:4874 */    return (this.glRenderbufferStorageMultisampleCoverageNV = GLContext.getFunctionAddress("glRenderbufferStorageMultisampleCoverageNV")) != 0L;
/* 4875:     */  }
/* 4876:     */  
/* 4877:     */  private boolean NV_geometry_program4_initNativeFunctionAddresses()
/* 4878:     */  {
/* 4879:4879 */    return ((this.glProgramVertexLimitNV = GLContext.getFunctionAddress("glProgramVertexLimitNV")) != 0L ? 1 : 0) & ((this.glFramebufferTextureEXT = GLContext.getFunctionAddress("glFramebufferTextureEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTextureLayerEXT = GLContext.getFunctionAddress("glFramebufferTextureLayerEXT")) != 0L ? 1 : 0) & ((this.glFramebufferTextureFaceEXT = GLContext.getFunctionAddress("glFramebufferTextureFaceEXT")) != 0L ? 1 : 0);
/* 4880:     */  }
/* 4881:     */  
/* 4885:     */  private boolean NV_gpu_program4_initNativeFunctionAddresses()
/* 4886:     */  {
/* 4887:4887 */    return ((this.glProgramLocalParameterI4iNV = GLContext.getFunctionAddress("glProgramLocalParameterI4iNV")) != 0L ? 1 : 0) & ((this.glProgramLocalParameterI4ivNV = GLContext.getFunctionAddress("glProgramLocalParameterI4ivNV")) != 0L ? 1 : 0) & ((this.glProgramLocalParametersI4ivNV = GLContext.getFunctionAddress("glProgramLocalParametersI4ivNV")) != 0L ? 1 : 0) & ((this.glProgramLocalParameterI4uiNV = GLContext.getFunctionAddress("glProgramLocalParameterI4uiNV")) != 0L ? 1 : 0) & ((this.glProgramLocalParameterI4uivNV = GLContext.getFunctionAddress("glProgramLocalParameterI4uivNV")) != 0L ? 1 : 0) & ((this.glProgramLocalParametersI4uivNV = GLContext.getFunctionAddress("glProgramLocalParametersI4uivNV")) != 0L ? 1 : 0) & ((this.glProgramEnvParameterI4iNV = GLContext.getFunctionAddress("glProgramEnvParameterI4iNV")) != 0L ? 1 : 0) & ((this.glProgramEnvParameterI4ivNV = GLContext.getFunctionAddress("glProgramEnvParameterI4ivNV")) != 0L ? 1 : 0) & ((this.glProgramEnvParametersI4ivNV = GLContext.getFunctionAddress("glProgramEnvParametersI4ivNV")) != 0L ? 1 : 0) & ((this.glProgramEnvParameterI4uiNV = GLContext.getFunctionAddress("glProgramEnvParameterI4uiNV")) != 0L ? 1 : 0) & ((this.glProgramEnvParameterI4uivNV = GLContext.getFunctionAddress("glProgramEnvParameterI4uivNV")) != 0L ? 1 : 0) & ((this.glProgramEnvParametersI4uivNV = GLContext.getFunctionAddress("glProgramEnvParametersI4uivNV")) != 0L ? 1 : 0) & ((this.glGetProgramLocalParameterIivNV = GLContext.getFunctionAddress("glGetProgramLocalParameterIivNV")) != 0L ? 1 : 0) & ((this.glGetProgramLocalParameterIuivNV = GLContext.getFunctionAddress("glGetProgramLocalParameterIuivNV")) != 0L ? 1 : 0) & ((this.glGetProgramEnvParameterIivNV = GLContext.getFunctionAddress("glGetProgramEnvParameterIivNV")) != 0L ? 1 : 0) & ((this.glGetProgramEnvParameterIuivNV = GLContext.getFunctionAddress("glGetProgramEnvParameterIuivNV")) != 0L ? 1 : 0);
/* 4888:     */  }
/* 4889:     */  
/* 4905:     */  private boolean NV_gpu_shader5_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 4906:     */  {
/* 4907:4907 */    return ((this.glUniform1i64NV = GLContext.getFunctionAddress("glUniform1i64NV")) != 0L ? 1 : 0) & ((this.glUniform2i64NV = GLContext.getFunctionAddress("glUniform2i64NV")) != 0L ? 1 : 0) & ((this.glUniform3i64NV = GLContext.getFunctionAddress("glUniform3i64NV")) != 0L ? 1 : 0) & ((this.glUniform4i64NV = GLContext.getFunctionAddress("glUniform4i64NV")) != 0L ? 1 : 0) & ((this.glUniform1i64vNV = GLContext.getFunctionAddress("glUniform1i64vNV")) != 0L ? 1 : 0) & ((this.glUniform2i64vNV = GLContext.getFunctionAddress("glUniform2i64vNV")) != 0L ? 1 : 0) & ((this.glUniform3i64vNV = GLContext.getFunctionAddress("glUniform3i64vNV")) != 0L ? 1 : 0) & ((this.glUniform4i64vNV = GLContext.getFunctionAddress("glUniform4i64vNV")) != 0L ? 1 : 0) & ((this.glUniform1ui64NV = GLContext.getFunctionAddress("glUniform1ui64NV")) != 0L ? 1 : 0) & ((this.glUniform2ui64NV = GLContext.getFunctionAddress("glUniform2ui64NV")) != 0L ? 1 : 0) & ((this.glUniform3ui64NV = GLContext.getFunctionAddress("glUniform3ui64NV")) != 0L ? 1 : 0) & ((this.glUniform4ui64NV = GLContext.getFunctionAddress("glUniform4ui64NV")) != 0L ? 1 : 0) & ((this.glUniform1ui64vNV = GLContext.getFunctionAddress("glUniform1ui64vNV")) != 0L ? 1 : 0) & ((this.glUniform2ui64vNV = GLContext.getFunctionAddress("glUniform2ui64vNV")) != 0L ? 1 : 0) & ((this.glUniform3ui64vNV = GLContext.getFunctionAddress("glUniform3ui64vNV")) != 0L ? 1 : 0) & ((this.glUniform4ui64vNV = GLContext.getFunctionAddress("glUniform4ui64vNV")) != 0L ? 1 : 0) & ((this.glGetUniformi64vNV = GLContext.getFunctionAddress("glGetUniformi64vNV")) != 0L ? 1 : 0) & ((this.glGetUniformui64vNV = GLContext.getFunctionAddress("glGetUniformui64vNV")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform1i64NV = GLContext.getFunctionAddress("glProgramUniform1i64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform2i64NV = GLContext.getFunctionAddress("glProgramUniform2i64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform3i64NV = GLContext.getFunctionAddress("glProgramUniform3i64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform4i64NV = GLContext.getFunctionAddress("glProgramUniform4i64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform1i64vNV = GLContext.getFunctionAddress("glProgramUniform1i64vNV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform2i64vNV = GLContext.getFunctionAddress("glProgramUniform2i64vNV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform3i64vNV = GLContext.getFunctionAddress("glProgramUniform3i64vNV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform4i64vNV = GLContext.getFunctionAddress("glProgramUniform4i64vNV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform1ui64NV = GLContext.getFunctionAddress("glProgramUniform1ui64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform2ui64NV = GLContext.getFunctionAddress("glProgramUniform2ui64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform3ui64NV = GLContext.getFunctionAddress("glProgramUniform3ui64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform4ui64NV = GLContext.getFunctionAddress("glProgramUniform4ui64NV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform1ui64vNV = GLContext.getFunctionAddress("glProgramUniform1ui64vNV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform2ui64vNV = GLContext.getFunctionAddress("glProgramUniform2ui64vNV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform3ui64vNV = GLContext.getFunctionAddress("glProgramUniform3ui64vNV")) != 0L) ? 1 : 0) & ((!supported_extensions.contains("GL_EXT_direct_state_access")) || ((this.glProgramUniform4ui64vNV = GLContext.getFunctionAddress("glProgramUniform4ui64vNV")) != 0L) ? 1 : 0);
/* 4908:     */  }
/* 4909:     */  
/* 4943:     */  private boolean NV_half_float_initNativeFunctionAddresses()
/* 4944:     */  {
/* 4945:4945 */    if (((this.glVertexWeighthNV = GLContext.getFunctionAddress("glVertexWeighthNV")) != 0L) || (((this.glVertexAttrib1hNV = GLContext.getFunctionAddress("glVertexAttrib1hNV")) != 0L) || (((this.glVertexAttrib2hNV = GLContext.getFunctionAddress("glVertexAttrib2hNV")) != 0L) || (((this.glVertexAttrib3hNV = GLContext.getFunctionAddress("glVertexAttrib3hNV")) != 0L) || (((this.glVertexAttrib4hNV = GLContext.getFunctionAddress("glVertexAttrib4hNV")) != 0L) || (((this.glVertexAttribs1hvNV = GLContext.getFunctionAddress("glVertexAttribs1hvNV")) != 0L) || (((this.glVertexAttribs2hvNV = GLContext.getFunctionAddress("glVertexAttribs2hvNV")) != 0L) || (((this.glVertexAttribs3hvNV = GLContext.getFunctionAddress("glVertexAttribs3hvNV")) != 0L) || ((this.glVertexAttribs4hvNV = GLContext.getFunctionAddress("glVertexAttribs4hvNV")) == 0L))))))))) {} return ((this.glVertex2hNV = GLContext.getFunctionAddress("glVertex2hNV")) != 0L ? 1 : 0) & ((this.glVertex3hNV = GLContext.getFunctionAddress("glVertex3hNV")) != 0L ? 1 : 0) & ((this.glVertex4hNV = GLContext.getFunctionAddress("glVertex4hNV")) != 0L ? 1 : 0) & ((this.glNormal3hNV = GLContext.getFunctionAddress("glNormal3hNV")) != 0L ? 1 : 0) & ((this.glColor3hNV = GLContext.getFunctionAddress("glColor3hNV")) != 0L ? 1 : 0) & ((this.glColor4hNV = GLContext.getFunctionAddress("glColor4hNV")) != 0L ? 1 : 0) & ((this.glTexCoord1hNV = GLContext.getFunctionAddress("glTexCoord1hNV")) != 0L ? 1 : 0) & ((this.glTexCoord2hNV = GLContext.getFunctionAddress("glTexCoord2hNV")) != 0L ? 1 : 0) & ((this.glTexCoord3hNV = GLContext.getFunctionAddress("glTexCoord3hNV")) != 0L ? 1 : 0) & ((this.glTexCoord4hNV = GLContext.getFunctionAddress("glTexCoord4hNV")) != 0L ? 1 : 0) & ((this.glMultiTexCoord1hNV = GLContext.getFunctionAddress("glMultiTexCoord1hNV")) != 0L ? 1 : 0) & ((this.glMultiTexCoord2hNV = GLContext.getFunctionAddress("glMultiTexCoord2hNV")) != 0L ? 1 : 0) & ((this.glMultiTexCoord3hNV = GLContext.getFunctionAddress("glMultiTexCoord3hNV")) != 0L ? 1 : 0) & ((this.glMultiTexCoord4hNV = GLContext.getFunctionAddress("glMultiTexCoord4hNV")) != 0L ? 1 : 0) & ((this.glFogCoordhNV = GLContext.getFunctionAddress("glFogCoordhNV")) != 0L ? 1 : 0) & ((this.glSecondaryColor3hNV = GLContext.getFunctionAddress("glSecondaryColor3hNV")) != 0L ? 1 : 0) & 0x1 & 0x1 & 0x1 & 0x1 & 0x1 & 0x1 & 0x1 & 0x1 & 0x1;
/* 4946:     */  }
/* 4947:     */  
/* 4972:     */  private boolean NV_occlusion_query_initNativeFunctionAddresses()
/* 4973:     */  {
/* 4974:4974 */    return ((this.glGenOcclusionQueriesNV = GLContext.getFunctionAddress("glGenOcclusionQueriesNV")) != 0L ? 1 : 0) & ((this.glDeleteOcclusionQueriesNV = GLContext.getFunctionAddress("glDeleteOcclusionQueriesNV")) != 0L ? 1 : 0) & ((this.glIsOcclusionQueryNV = GLContext.getFunctionAddress("glIsOcclusionQueryNV")) != 0L ? 1 : 0) & ((this.glBeginOcclusionQueryNV = GLContext.getFunctionAddress("glBeginOcclusionQueryNV")) != 0L ? 1 : 0) & ((this.glEndOcclusionQueryNV = GLContext.getFunctionAddress("glEndOcclusionQueryNV")) != 0L ? 1 : 0) & ((this.glGetOcclusionQueryuivNV = GLContext.getFunctionAddress("glGetOcclusionQueryuivNV")) != 0L ? 1 : 0) & ((this.glGetOcclusionQueryivNV = GLContext.getFunctionAddress("glGetOcclusionQueryivNV")) != 0L ? 1 : 0);
/* 4975:     */  }
/* 4976:     */  
/* 4983:     */  private boolean NV_parameter_buffer_object_initNativeFunctionAddresses()
/* 4984:     */  {
/* 4985:4985 */    return ((this.glProgramBufferParametersfvNV = GLContext.getFunctionAddress("glProgramBufferParametersfvNV")) != 0L ? 1 : 0) & ((this.glProgramBufferParametersIivNV = GLContext.getFunctionAddress("glProgramBufferParametersIivNV")) != 0L ? 1 : 0) & ((this.glProgramBufferParametersIuivNV = GLContext.getFunctionAddress("glProgramBufferParametersIuivNV")) != 0L ? 1 : 0);
/* 4986:     */  }
/* 4987:     */  
/* 4990:     */  private boolean NV_path_rendering_initNativeFunctionAddresses()
/* 4991:     */  {
/* 4992:4992 */    return ((this.glPathCommandsNV = GLContext.getFunctionAddress("glPathCommandsNV")) != 0L ? 1 : 0) & ((this.glPathCoordsNV = GLContext.getFunctionAddress("glPathCoordsNV")) != 0L ? 1 : 0) & ((this.glPathSubCommandsNV = GLContext.getFunctionAddress("glPathSubCommandsNV")) != 0L ? 1 : 0) & ((this.glPathSubCoordsNV = GLContext.getFunctionAddress("glPathSubCoordsNV")) != 0L ? 1 : 0) & ((this.glPathStringNV = GLContext.getFunctionAddress("glPathStringNV")) != 0L ? 1 : 0) & ((this.glPathGlyphsNV = GLContext.getFunctionAddress("glPathGlyphsNV")) != 0L ? 1 : 0) & ((this.glPathGlyphRangeNV = GLContext.getFunctionAddress("glPathGlyphRangeNV")) != 0L ? 1 : 0) & ((this.glWeightPathsNV = GLContext.getFunctionAddress("glWeightPathsNV")) != 0L ? 1 : 0) & ((this.glCopyPathNV = GLContext.getFunctionAddress("glCopyPathNV")) != 0L ? 1 : 0) & ((this.glInterpolatePathsNV = GLContext.getFunctionAddress("glInterpolatePathsNV")) != 0L ? 1 : 0) & ((this.glTransformPathNV = GLContext.getFunctionAddress("glTransformPathNV")) != 0L ? 1 : 0) & ((this.glPathParameterivNV = GLContext.getFunctionAddress("glPathParameterivNV")) != 0L ? 1 : 0) & ((this.glPathParameteriNV = GLContext.getFunctionAddress("glPathParameteriNV")) != 0L ? 1 : 0) & ((this.glPathParameterfvNV = GLContext.getFunctionAddress("glPathParameterfvNV")) != 0L ? 1 : 0) & ((this.glPathParameterfNV = GLContext.getFunctionAddress("glPathParameterfNV")) != 0L ? 1 : 0) & ((this.glPathDashArrayNV = GLContext.getFunctionAddress("glPathDashArrayNV")) != 0L ? 1 : 0) & ((this.glGenPathsNV = GLContext.getFunctionAddress("glGenPathsNV")) != 0L ? 1 : 0) & ((this.glDeletePathsNV = GLContext.getFunctionAddress("glDeletePathsNV")) != 0L ? 1 : 0) & ((this.glIsPathNV = GLContext.getFunctionAddress("glIsPathNV")) != 0L ? 1 : 0) & ((this.glPathStencilFuncNV = GLContext.getFunctionAddress("glPathStencilFuncNV")) != 0L ? 1 : 0) & ((this.glPathStencilDepthOffsetNV = GLContext.getFunctionAddress("glPathStencilDepthOffsetNV")) != 0L ? 1 : 0) & ((this.glStencilFillPathNV = GLContext.getFunctionAddress("glStencilFillPathNV")) != 0L ? 1 : 0) & ((this.glStencilStrokePathNV = GLContext.getFunctionAddress("glStencilStrokePathNV")) != 0L ? 1 : 0) & ((this.glStencilFillPathInstancedNV = GLContext.getFunctionAddress("glStencilFillPathInstancedNV")) != 0L ? 1 : 0) & ((this.glStencilStrokePathInstancedNV = GLContext.getFunctionAddress("glStencilStrokePathInstancedNV")) != 0L ? 1 : 0) & ((this.glPathCoverDepthFuncNV = GLContext.getFunctionAddress("glPathCoverDepthFuncNV")) != 0L ? 1 : 0) & ((this.glPathColorGenNV = GLContext.getFunctionAddress("glPathColorGenNV")) != 0L ? 1 : 0) & ((this.glPathTexGenNV = GLContext.getFunctionAddress("glPathTexGenNV")) != 0L ? 1 : 0) & ((this.glPathFogGenNV = GLContext.getFunctionAddress("glPathFogGenNV")) != 0L ? 1 : 0) & ((this.glCoverFillPathNV = GLContext.getFunctionAddress("glCoverFillPathNV")) != 0L ? 1 : 0) & ((this.glCoverStrokePathNV = GLContext.getFunctionAddress("glCoverStrokePathNV")) != 0L ? 1 : 0) & ((this.glCoverFillPathInstancedNV = GLContext.getFunctionAddress("glCoverFillPathInstancedNV")) != 0L ? 1 : 0) & ((this.glCoverStrokePathInstancedNV = GLContext.getFunctionAddress("glCoverStrokePathInstancedNV")) != 0L ? 1 : 0) & ((this.glGetPathParameterivNV = GLContext.getFunctionAddress("glGetPathParameterivNV")) != 0L ? 1 : 0) & ((this.glGetPathParameterfvNV = GLContext.getFunctionAddress("glGetPathParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetPathCommandsNV = GLContext.getFunctionAddress("glGetPathCommandsNV")) != 0L ? 1 : 0) & ((this.glGetPathCoordsNV = GLContext.getFunctionAddress("glGetPathCoordsNV")) != 0L ? 1 : 0) & ((this.glGetPathDashArrayNV = GLContext.getFunctionAddress("glGetPathDashArrayNV")) != 0L ? 1 : 0) & ((this.glGetPathMetricsNV = GLContext.getFunctionAddress("glGetPathMetricsNV")) != 0L ? 1 : 0) & ((this.glGetPathMetricRangeNV = GLContext.getFunctionAddress("glGetPathMetricRangeNV")) != 0L ? 1 : 0) & ((this.glGetPathSpacingNV = GLContext.getFunctionAddress("glGetPathSpacingNV")) != 0L ? 1 : 0) & ((this.glGetPathColorGenivNV = GLContext.getFunctionAddress("glGetPathColorGenivNV")) != 0L ? 1 : 0) & ((this.glGetPathColorGenfvNV = GLContext.getFunctionAddress("glGetPathColorGenfvNV")) != 0L ? 1 : 0) & ((this.glGetPathTexGenivNV = GLContext.getFunctionAddress("glGetPathTexGenivNV")) != 0L ? 1 : 0) & ((this.glGetPathTexGenfvNV = GLContext.getFunctionAddress("glGetPathTexGenfvNV")) != 0L ? 1 : 0) & ((this.glIsPointInFillPathNV = GLContext.getFunctionAddress("glIsPointInFillPathNV")) != 0L ? 1 : 0) & ((this.glIsPointInStrokePathNV = GLContext.getFunctionAddress("glIsPointInStrokePathNV")) != 0L ? 1 : 0) & ((this.glGetPathLengthNV = GLContext.getFunctionAddress("glGetPathLengthNV")) != 0L ? 1 : 0) & ((this.glPointAlongPathNV = GLContext.getFunctionAddress("glPointAlongPathNV")) != 0L ? 1 : 0);
/* 4993:     */  }
/* 4994:     */  
/* 5043:     */  private boolean NV_pixel_data_range_initNativeFunctionAddresses()
/* 5044:     */  {
/* 5045:5045 */    return ((this.glPixelDataRangeNV = GLContext.getFunctionAddress("glPixelDataRangeNV")) != 0L ? 1 : 0) & ((this.glFlushPixelDataRangeNV = GLContext.getFunctionAddress("glFlushPixelDataRangeNV")) != 0L ? 1 : 0);
/* 5046:     */  }
/* 5047:     */  
/* 5049:     */  private boolean NV_point_sprite_initNativeFunctionAddresses()
/* 5050:     */  {
/* 5051:5051 */    return ((this.glPointParameteriNV = GLContext.getFunctionAddress("glPointParameteriNV")) != 0L ? 1 : 0) & ((this.glPointParameterivNV = GLContext.getFunctionAddress("glPointParameterivNV")) != 0L ? 1 : 0);
/* 5052:     */  }
/* 5053:     */  
/* 5055:     */  private boolean NV_present_video_initNativeFunctionAddresses()
/* 5056:     */  {
/* 5057:5057 */    return ((this.glPresentFrameKeyedNV = GLContext.getFunctionAddress("glPresentFrameKeyedNV")) != 0L ? 1 : 0) & ((this.glPresentFrameDualFillNV = GLContext.getFunctionAddress("glPresentFrameDualFillNV")) != 0L ? 1 : 0) & ((this.glGetVideoivNV = GLContext.getFunctionAddress("glGetVideoivNV")) != 0L ? 1 : 0) & ((this.glGetVideouivNV = GLContext.getFunctionAddress("glGetVideouivNV")) != 0L ? 1 : 0) & ((this.glGetVideoi64vNV = GLContext.getFunctionAddress("glGetVideoi64vNV")) != 0L ? 1 : 0) & ((this.glGetVideoui64vNV = GLContext.getFunctionAddress("glGetVideoui64vNV")) != 0L ? 1 : 0);
/* 5058:     */  }
/* 5059:     */  
/* 5065:     */  private boolean NV_primitive_restart_initNativeFunctionAddresses()
/* 5066:     */  {
/* 5067:5067 */    return ((this.glPrimitiveRestartNV = GLContext.getFunctionAddress("glPrimitiveRestartNV")) != 0L ? 1 : 0) & ((this.glPrimitiveRestartIndexNV = GLContext.getFunctionAddress("glPrimitiveRestartIndexNV")) != 0L ? 1 : 0);
/* 5068:     */  }
/* 5069:     */  
/* 5071:     */  private boolean NV_program_initNativeFunctionAddresses()
/* 5072:     */  {
/* 5073:5073 */    return ((this.glLoadProgramNV = GLContext.getFunctionAddress("glLoadProgramNV")) != 0L ? 1 : 0) & ((this.glBindProgramNV = GLContext.getFunctionAddress("glBindProgramNV")) != 0L ? 1 : 0) & ((this.glDeleteProgramsNV = GLContext.getFunctionAddress("glDeleteProgramsNV")) != 0L ? 1 : 0) & ((this.glGenProgramsNV = GLContext.getFunctionAddress("glGenProgramsNV")) != 0L ? 1 : 0) & ((this.glGetProgramivNV = GLContext.getFunctionAddress("glGetProgramivNV")) != 0L ? 1 : 0) & ((this.glGetProgramStringNV = GLContext.getFunctionAddress("glGetProgramStringNV")) != 0L ? 1 : 0) & ((this.glIsProgramNV = GLContext.getFunctionAddress("glIsProgramNV")) != 0L ? 1 : 0) & ((this.glAreProgramsResidentNV = GLContext.getFunctionAddress("glAreProgramsResidentNV")) != 0L ? 1 : 0) & ((this.glRequestResidentProgramsNV = GLContext.getFunctionAddress("glRequestResidentProgramsNV")) != 0L ? 1 : 0);
/* 5074:     */  }
/* 5075:     */  
/* 5084:     */  private boolean NV_register_combiners_initNativeFunctionAddresses()
/* 5085:     */  {
/* 5086:5086 */    return ((this.glCombinerParameterfNV = GLContext.getFunctionAddress("glCombinerParameterfNV")) != 0L ? 1 : 0) & ((this.glCombinerParameterfvNV = GLContext.getFunctionAddress("glCombinerParameterfvNV")) != 0L ? 1 : 0) & ((this.glCombinerParameteriNV = GLContext.getFunctionAddress("glCombinerParameteriNV")) != 0L ? 1 : 0) & ((this.glCombinerParameterivNV = GLContext.getFunctionAddress("glCombinerParameterivNV")) != 0L ? 1 : 0) & ((this.glCombinerInputNV = GLContext.getFunctionAddress("glCombinerInputNV")) != 0L ? 1 : 0) & ((this.glCombinerOutputNV = GLContext.getFunctionAddress("glCombinerOutputNV")) != 0L ? 1 : 0) & ((this.glFinalCombinerInputNV = GLContext.getFunctionAddress("glFinalCombinerInputNV")) != 0L ? 1 : 0) & ((this.glGetCombinerInputParameterfvNV = GLContext.getFunctionAddress("glGetCombinerInputParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetCombinerInputParameterivNV = GLContext.getFunctionAddress("glGetCombinerInputParameterivNV")) != 0L ? 1 : 0) & ((this.glGetCombinerOutputParameterfvNV = GLContext.getFunctionAddress("glGetCombinerOutputParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetCombinerOutputParameterivNV = GLContext.getFunctionAddress("glGetCombinerOutputParameterivNV")) != 0L ? 1 : 0) & ((this.glGetFinalCombinerInputParameterfvNV = GLContext.getFunctionAddress("glGetFinalCombinerInputParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetFinalCombinerInputParameterivNV = GLContext.getFunctionAddress("glGetFinalCombinerInputParameterivNV")) != 0L ? 1 : 0);
/* 5087:     */  }
/* 5088:     */  
/* 5101:     */  private boolean NV_register_combiners2_initNativeFunctionAddresses()
/* 5102:     */  {
/* 5103:5103 */    return ((this.glCombinerStageParameterfvNV = GLContext.getFunctionAddress("glCombinerStageParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetCombinerStageParameterfvNV = GLContext.getFunctionAddress("glGetCombinerStageParameterfvNV")) != 0L ? 1 : 0);
/* 5104:     */  }
/* 5105:     */  
/* 5107:     */  private boolean NV_shader_buffer_load_initNativeFunctionAddresses()
/* 5108:     */  {
/* 5109:5109 */    return ((this.glMakeBufferResidentNV = GLContext.getFunctionAddress("glMakeBufferResidentNV")) != 0L ? 1 : 0) & ((this.glMakeBufferNonResidentNV = GLContext.getFunctionAddress("glMakeBufferNonResidentNV")) != 0L ? 1 : 0) & ((this.glIsBufferResidentNV = GLContext.getFunctionAddress("glIsBufferResidentNV")) != 0L ? 1 : 0) & ((this.glMakeNamedBufferResidentNV = GLContext.getFunctionAddress("glMakeNamedBufferResidentNV")) != 0L ? 1 : 0) & ((this.glMakeNamedBufferNonResidentNV = GLContext.getFunctionAddress("glMakeNamedBufferNonResidentNV")) != 0L ? 1 : 0) & ((this.glIsNamedBufferResidentNV = GLContext.getFunctionAddress("glIsNamedBufferResidentNV")) != 0L ? 1 : 0) & ((this.glGetBufferParameterui64vNV = GLContext.getFunctionAddress("glGetBufferParameterui64vNV")) != 0L ? 1 : 0) & ((this.glGetNamedBufferParameterui64vNV = GLContext.getFunctionAddress("glGetNamedBufferParameterui64vNV")) != 0L ? 1 : 0) & ((this.glGetIntegerui64vNV = GLContext.getFunctionAddress("glGetIntegerui64vNV")) != 0L ? 1 : 0) & ((this.glUniformui64NV = GLContext.getFunctionAddress("glUniformui64NV")) != 0L ? 1 : 0) & ((this.glUniformui64vNV = GLContext.getFunctionAddress("glUniformui64vNV")) != 0L ? 1 : 0) & ((this.glGetUniformui64vNV = GLContext.getFunctionAddress("glGetUniformui64vNV")) != 0L ? 1 : 0) & ((this.glProgramUniformui64NV = GLContext.getFunctionAddress("glProgramUniformui64NV")) != 0L ? 1 : 0) & ((this.glProgramUniformui64vNV = GLContext.getFunctionAddress("glProgramUniformui64vNV")) != 0L ? 1 : 0);
/* 5110:     */  }
/* 5111:     */  
/* 5125:     */  private boolean NV_texture_barrier_initNativeFunctionAddresses()
/* 5126:     */  {
/* 5127:5127 */    return (this.glTextureBarrierNV = GLContext.getFunctionAddress("glTextureBarrierNV")) != 0L;
/* 5128:     */  }
/* 5129:     */  
/* 5130:     */  private boolean NV_texture_multisample_initNativeFunctionAddresses()
/* 5131:     */  {
/* 5132:5132 */    return ((this.glTexImage2DMultisampleCoverageNV = GLContext.getFunctionAddress("glTexImage2DMultisampleCoverageNV")) != 0L ? 1 : 0) & ((this.glTexImage3DMultisampleCoverageNV = GLContext.getFunctionAddress("glTexImage3DMultisampleCoverageNV")) != 0L ? 1 : 0) & ((this.glTextureImage2DMultisampleNV = GLContext.getFunctionAddress("glTextureImage2DMultisampleNV")) != 0L ? 1 : 0) & ((this.glTextureImage3DMultisampleNV = GLContext.getFunctionAddress("glTextureImage3DMultisampleNV")) != 0L ? 1 : 0) & ((this.glTextureImage2DMultisampleCoverageNV = GLContext.getFunctionAddress("glTextureImage2DMultisampleCoverageNV")) != 0L ? 1 : 0) & ((this.glTextureImage3DMultisampleCoverageNV = GLContext.getFunctionAddress("glTextureImage3DMultisampleCoverageNV")) != 0L ? 1 : 0);
/* 5133:     */  }
/* 5134:     */  
/* 5140:     */  private boolean NV_transform_feedback_initNativeFunctionAddresses()
/* 5141:     */  {
/* 5142:5142 */    return ((this.glBindBufferRangeNV = GLContext.getFunctionAddress("glBindBufferRangeNV")) != 0L ? 1 : 0) & ((this.glBindBufferOffsetNV = GLContext.getFunctionAddress("glBindBufferOffsetNV")) != 0L ? 1 : 0) & ((this.glBindBufferBaseNV = GLContext.getFunctionAddress("glBindBufferBaseNV")) != 0L ? 1 : 0) & ((this.glTransformFeedbackAttribsNV = GLContext.getFunctionAddress("glTransformFeedbackAttribsNV")) != 0L ? 1 : 0) & ((this.glTransformFeedbackVaryingsNV = GLContext.getFunctionAddress("glTransformFeedbackVaryingsNV")) != 0L ? 1 : 0) & ((this.glBeginTransformFeedbackNV = GLContext.getFunctionAddress("glBeginTransformFeedbackNV")) != 0L ? 1 : 0) & ((this.glEndTransformFeedbackNV = GLContext.getFunctionAddress("glEndTransformFeedbackNV")) != 0L ? 1 : 0) & ((this.glGetVaryingLocationNV = GLContext.getFunctionAddress("glGetVaryingLocationNV")) != 0L ? 1 : 0) & ((this.glGetActiveVaryingNV = GLContext.getFunctionAddress("glGetActiveVaryingNV")) != 0L ? 1 : 0) & ((this.glActiveVaryingNV = GLContext.getFunctionAddress("glActiveVaryingNV")) != 0L ? 1 : 0) & ((this.glGetTransformFeedbackVaryingNV = GLContext.getFunctionAddress("glGetTransformFeedbackVaryingNV")) != 0L ? 1 : 0);
/* 5143:     */  }
/* 5144:     */  
/* 5155:     */  private boolean NV_transform_feedback2_initNativeFunctionAddresses()
/* 5156:     */  {
/* 5157:5157 */    return ((this.glBindTransformFeedbackNV = GLContext.getFunctionAddress("glBindTransformFeedbackNV")) != 0L ? 1 : 0) & ((this.glDeleteTransformFeedbacksNV = GLContext.getFunctionAddress("glDeleteTransformFeedbacksNV")) != 0L ? 1 : 0) & ((this.glGenTransformFeedbacksNV = GLContext.getFunctionAddress("glGenTransformFeedbacksNV")) != 0L ? 1 : 0) & ((this.glIsTransformFeedbackNV = GLContext.getFunctionAddress("glIsTransformFeedbackNV")) != 0L ? 1 : 0) & ((this.glPauseTransformFeedbackNV = GLContext.getFunctionAddress("glPauseTransformFeedbackNV")) != 0L ? 1 : 0) & ((this.glResumeTransformFeedbackNV = GLContext.getFunctionAddress("glResumeTransformFeedbackNV")) != 0L ? 1 : 0) & ((this.glDrawTransformFeedbackNV = GLContext.getFunctionAddress("glDrawTransformFeedbackNV")) != 0L ? 1 : 0);
/* 5158:     */  }
/* 5159:     */  
/* 5166:     */  private boolean NV_vertex_array_range_initNativeFunctionAddresses()
/* 5167:     */  {
/* 5168:5168 */    return ((this.glVertexArrayRangeNV = GLContext.getFunctionAddress("glVertexArrayRangeNV")) != 0L ? 1 : 0) & ((this.glFlushVertexArrayRangeNV = GLContext.getFunctionAddress("glFlushVertexArrayRangeNV")) != 0L ? 1 : 0) & ((this.glAllocateMemoryNV = GLContext.getPlatformSpecificFunctionAddress("gl", new String[] { "Windows", "Linux" }, new String[] { "wgl", "glX" }, "glAllocateMemoryNV")) != 0L ? 1 : 0) & ((this.glFreeMemoryNV = GLContext.getPlatformSpecificFunctionAddress("gl", new String[] { "Windows", "Linux" }, new String[] { "wgl", "glX" }, "glFreeMemoryNV")) != 0L ? 1 : 0);
/* 5169:     */  }
/* 5170:     */  
/* 5174:     */  private boolean NV_vertex_attrib_integer_64bit_initNativeFunctionAddresses(Set<String> supported_extensions)
/* 5175:     */  {
/* 5176:5176 */    return ((this.glVertexAttribL1i64NV = GLContext.getFunctionAddress("glVertexAttribL1i64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL2i64NV = GLContext.getFunctionAddress("glVertexAttribL2i64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL3i64NV = GLContext.getFunctionAddress("glVertexAttribL3i64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL4i64NV = GLContext.getFunctionAddress("glVertexAttribL4i64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL1i64vNV = GLContext.getFunctionAddress("glVertexAttribL1i64vNV")) != 0L ? 1 : 0) & ((this.glVertexAttribL2i64vNV = GLContext.getFunctionAddress("glVertexAttribL2i64vNV")) != 0L ? 1 : 0) & ((this.glVertexAttribL3i64vNV = GLContext.getFunctionAddress("glVertexAttribL3i64vNV")) != 0L ? 1 : 0) & ((this.glVertexAttribL4i64vNV = GLContext.getFunctionAddress("glVertexAttribL4i64vNV")) != 0L ? 1 : 0) & ((this.glVertexAttribL1ui64NV = GLContext.getFunctionAddress("glVertexAttribL1ui64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL2ui64NV = GLContext.getFunctionAddress("glVertexAttribL2ui64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL3ui64NV = GLContext.getFunctionAddress("glVertexAttribL3ui64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL4ui64NV = GLContext.getFunctionAddress("glVertexAttribL4ui64NV")) != 0L ? 1 : 0) & ((this.glVertexAttribL1ui64vNV = GLContext.getFunctionAddress("glVertexAttribL1ui64vNV")) != 0L ? 1 : 0) & ((this.glVertexAttribL2ui64vNV = GLContext.getFunctionAddress("glVertexAttribL2ui64vNV")) != 0L ? 1 : 0) & ((this.glVertexAttribL3ui64vNV = GLContext.getFunctionAddress("glVertexAttribL3ui64vNV")) != 0L ? 1 : 0) & ((this.glVertexAttribL4ui64vNV = GLContext.getFunctionAddress("glVertexAttribL4ui64vNV")) != 0L ? 1 : 0) & ((this.glGetVertexAttribLi64vNV = GLContext.getFunctionAddress("glGetVertexAttribLi64vNV")) != 0L ? 1 : 0) & ((this.glGetVertexAttribLui64vNV = GLContext.getFunctionAddress("glGetVertexAttribLui64vNV")) != 0L ? 1 : 0) & ((!supported_extensions.contains("GL_NV_vertex_buffer_unified_memory")) || ((this.glVertexAttribLFormatNV = GLContext.getFunctionAddress("glVertexAttribLFormatNV")) != 0L) ? 1 : 0);
/* 5177:     */  }
/* 5178:     */  
/* 5197:     */  private boolean NV_vertex_buffer_unified_memory_initNativeFunctionAddresses()
/* 5198:     */  {
/* 5199:5199 */    return ((this.glBufferAddressRangeNV = GLContext.getFunctionAddress("glBufferAddressRangeNV")) != 0L ? 1 : 0) & ((this.glVertexFormatNV = GLContext.getFunctionAddress("glVertexFormatNV")) != 0L ? 1 : 0) & ((this.glNormalFormatNV = GLContext.getFunctionAddress("glNormalFormatNV")) != 0L ? 1 : 0) & ((this.glColorFormatNV = GLContext.getFunctionAddress("glColorFormatNV")) != 0L ? 1 : 0) & ((this.glIndexFormatNV = GLContext.getFunctionAddress("glIndexFormatNV")) != 0L ? 1 : 0) & ((this.glTexCoordFormatNV = GLContext.getFunctionAddress("glTexCoordFormatNV")) != 0L ? 1 : 0) & ((this.glEdgeFlagFormatNV = GLContext.getFunctionAddress("glEdgeFlagFormatNV")) != 0L ? 1 : 0) & ((this.glSecondaryColorFormatNV = GLContext.getFunctionAddress("glSecondaryColorFormatNV")) != 0L ? 1 : 0) & ((this.glFogCoordFormatNV = GLContext.getFunctionAddress("glFogCoordFormatNV")) != 0L ? 1 : 0) & ((this.glVertexAttribFormatNV = GLContext.getFunctionAddress("glVertexAttribFormatNV")) != 0L ? 1 : 0) & ((this.glVertexAttribIFormatNV = GLContext.getFunctionAddress("glVertexAttribIFormatNV")) != 0L ? 1 : 0) & ((this.glGetIntegerui64i_vNV = GLContext.getFunctionAddress("glGetIntegerui64i_vNV")) != 0L ? 1 : 0);
/* 5200:     */  }
/* 5201:     */  
/* 5213:     */  private boolean NV_vertex_program_initNativeFunctionAddresses()
/* 5214:     */  {
/* 5215:5215 */    return ((this.glExecuteProgramNV = GLContext.getFunctionAddress("glExecuteProgramNV")) != 0L ? 1 : 0) & ((this.glGetProgramParameterfvNV = GLContext.getFunctionAddress("glGetProgramParameterfvNV")) != 0L ? 1 : 0) & ((this.glGetProgramParameterdvNV = GLContext.getFunctionAddress("glGetProgramParameterdvNV")) != 0L ? 1 : 0) & ((this.glGetTrackMatrixivNV = GLContext.getFunctionAddress("glGetTrackMatrixivNV")) != 0L ? 1 : 0) & ((this.glGetVertexAttribfvNV = GLContext.getFunctionAddress("glGetVertexAttribfvNV")) != 0L ? 1 : 0) & ((this.glGetVertexAttribdvNV = GLContext.getFunctionAddress("glGetVertexAttribdvNV")) != 0L ? 1 : 0) & ((this.glGetVertexAttribivNV = GLContext.getFunctionAddress("glGetVertexAttribivNV")) != 0L ? 1 : 0) & ((this.glGetVertexAttribPointervNV = GLContext.getFunctionAddress("glGetVertexAttribPointervNV")) != 0L ? 1 : 0) & ((this.glProgramParameter4fNV = GLContext.getFunctionAddress("glProgramParameter4fNV")) != 0L ? 1 : 0) & ((this.glProgramParameter4dNV = GLContext.getFunctionAddress("glProgramParameter4dNV")) != 0L ? 1 : 0) & ((this.glProgramParameters4fvNV = GLContext.getFunctionAddress("glProgramParameters4fvNV")) != 0L ? 1 : 0) & ((this.glProgramParameters4dvNV = GLContext.getFunctionAddress("glProgramParameters4dvNV")) != 0L ? 1 : 0) & ((this.glTrackMatrixNV = GLContext.getFunctionAddress("glTrackMatrixNV")) != 0L ? 1 : 0) & ((this.glVertexAttribPointerNV = GLContext.getFunctionAddress("glVertexAttribPointerNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib1sNV = GLContext.getFunctionAddress("glVertexAttrib1sNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib1fNV = GLContext.getFunctionAddress("glVertexAttrib1fNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib1dNV = GLContext.getFunctionAddress("glVertexAttrib1dNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib2sNV = GLContext.getFunctionAddress("glVertexAttrib2sNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib2fNV = GLContext.getFunctionAddress("glVertexAttrib2fNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib2dNV = GLContext.getFunctionAddress("glVertexAttrib2dNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib3sNV = GLContext.getFunctionAddress("glVertexAttrib3sNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib3fNV = GLContext.getFunctionAddress("glVertexAttrib3fNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib3dNV = GLContext.getFunctionAddress("glVertexAttrib3dNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib4sNV = GLContext.getFunctionAddress("glVertexAttrib4sNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib4fNV = GLContext.getFunctionAddress("glVertexAttrib4fNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib4dNV = GLContext.getFunctionAddress("glVertexAttrib4dNV")) != 0L ? 1 : 0) & ((this.glVertexAttrib4ubNV = GLContext.getFunctionAddress("glVertexAttrib4ubNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs1svNV = GLContext.getFunctionAddress("glVertexAttribs1svNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs1fvNV = GLContext.getFunctionAddress("glVertexAttribs1fvNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs1dvNV = GLContext.getFunctionAddress("glVertexAttribs1dvNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs2svNV = GLContext.getFunctionAddress("glVertexAttribs2svNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs2fvNV = GLContext.getFunctionAddress("glVertexAttribs2fvNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs2dvNV = GLContext.getFunctionAddress("glVertexAttribs2dvNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs3svNV = GLContext.getFunctionAddress("glVertexAttribs3svNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs3fvNV = GLContext.getFunctionAddress("glVertexAttribs3fvNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs3dvNV = GLContext.getFunctionAddress("glVertexAttribs3dvNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs4svNV = GLContext.getFunctionAddress("glVertexAttribs4svNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs4fvNV = GLContext.getFunctionAddress("glVertexAttribs4fvNV")) != 0L ? 1 : 0) & ((this.glVertexAttribs4dvNV = GLContext.getFunctionAddress("glVertexAttribs4dvNV")) != 0L ? 1 : 0);
/* 5216:     */  }
/* 5217:     */  
/* 5256:     */  private boolean NV_video_capture_initNativeFunctionAddresses()
/* 5257:     */  {
/* 5258:5258 */    return ((this.glBeginVideoCaptureNV = GLContext.getFunctionAddress("glBeginVideoCaptureNV")) != 0L ? 1 : 0) & ((this.glBindVideoCaptureStreamBufferNV = GLContext.getFunctionAddress("glBindVideoCaptureStreamBufferNV")) != 0L ? 1 : 0) & ((this.glBindVideoCaptureStreamTextureNV = GLContext.getFunctionAddress("glBindVideoCaptureStreamTextureNV")) != 0L ? 1 : 0) & ((this.glEndVideoCaptureNV = GLContext.getFunctionAddress("glEndVideoCaptureNV")) != 0L ? 1 : 0) & ((this.glGetVideoCaptureivNV = GLContext.getFunctionAddress("glGetVideoCaptureivNV")) != 0L ? 1 : 0) & ((this.glGetVideoCaptureStreamivNV = GLContext.getFunctionAddress("glGetVideoCaptureStreamivNV")) != 0L ? 1 : 0) & ((this.glGetVideoCaptureStreamfvNV = GLContext.getFunctionAddress("glGetVideoCaptureStreamfvNV")) != 0L ? 1 : 0) & ((this.glGetVideoCaptureStreamdvNV = GLContext.getFunctionAddress("glGetVideoCaptureStreamdvNV")) != 0L ? 1 : 0) & ((this.glVideoCaptureNV = GLContext.getFunctionAddress("glVideoCaptureNV")) != 0L ? 1 : 0) & ((this.glVideoCaptureStreamParameterivNV = GLContext.getFunctionAddress("glVideoCaptureStreamParameterivNV")) != 0L ? 1 : 0) & ((this.glVideoCaptureStreamParameterfvNV = GLContext.getFunctionAddress("glVideoCaptureStreamParameterfvNV")) != 0L ? 1 : 0) & ((this.glVideoCaptureStreamParameterdvNV = GLContext.getFunctionAddress("glVideoCaptureStreamParameterdvNV")) != 0L ? 1 : 0);
/* 5259:     */  }
/* 5260:     */  
/* 5273:     */  private static void remove(Set supported_extensions, String extension)
/* 5274:     */  {
/* 5275:5275 */    LWJGLUtil.log(extension + " was reported as available but an entry point is missing");
/* 5276:5276 */    supported_extensions.remove(extension);
/* 5277:     */  }
/* 5278:     */  
/* 5279:     */  private Set<String> initAllStubs(boolean forwardCompatible) throws LWJGLException {
/* 5280:5280 */    this.glGetError = GLContext.getFunctionAddress("glGetError");
/* 5281:5281 */    this.glGetString = GLContext.getFunctionAddress("glGetString");
/* 5282:5282 */    this.glGetIntegerv = GLContext.getFunctionAddress("glGetIntegerv");
/* 5283:5283 */    this.glGetStringi = GLContext.getFunctionAddress("glGetStringi");
/* 5284:5284 */    GLContext.setCapabilities(this);
/* 5285:5285 */    Set<String> supported_extensions = new HashSet(256);
/* 5286:5286 */    int profileMask = GLContext.getSupportedExtensions(supported_extensions);
/* 5287:5287 */    if ((supported_extensions.contains("OpenGL31")) && (!supported_extensions.contains("GL_ARB_compatibility")) && ((profileMask & 0x2) == 0))
/* 5288:5288 */      forwardCompatible = true;
/* 5289:5289 */    if (!GL11_initNativeFunctionAddresses(forwardCompatible))
/* 5290:5290 */      throw new LWJGLException("GL11 not supported");
/* 5291:5291 */    if (supported_extensions.contains("GL_ARB_fragment_program"))
/* 5292:5292 */      supported_extensions.add("GL_ARB_program");
/* 5293:5293 */    if (supported_extensions.contains("GL_ARB_pixel_buffer_object"))
/* 5294:5294 */      supported_extensions.add("GL_ARB_buffer_object");
/* 5295:5295 */    if (supported_extensions.contains("GL_ARB_vertex_buffer_object"))
/* 5296:5296 */      supported_extensions.add("GL_ARB_buffer_object");
/* 5297:5297 */    if (supported_extensions.contains("GL_ARB_vertex_program"))
/* 5298:5298 */      supported_extensions.add("GL_ARB_program");
/* 5299:5299 */    if (supported_extensions.contains("GL_EXT_pixel_buffer_object"))
/* 5300:5300 */      supported_extensions.add("GL_ARB_buffer_object");
/* 5301:5301 */    if (supported_extensions.contains("GL_NV_fragment_program"))
/* 5302:5302 */      supported_extensions.add("GL_NV_program");
/* 5303:5303 */    if (supported_extensions.contains("GL_NV_vertex_program"))
/* 5304:5304 */      supported_extensions.add("GL_NV_program");
/* 5305:5305 */    if (((supported_extensions.contains("GL_AMD_debug_output")) || (supported_extensions.contains("GL_AMDX_debug_output"))) && (!AMD_debug_output_initNativeFunctionAddresses())) {
/* 5306:5306 */      remove(supported_extensions, "GL_AMDX_debug_output");
/* 5307:5307 */      remove(supported_extensions, "GL_AMD_debug_output");
/* 5308:     */    }
/* 5309:5309 */    if ((supported_extensions.contains("GL_AMD_draw_buffers_blend")) && (!AMD_draw_buffers_blend_initNativeFunctionAddresses()))
/* 5310:5310 */      remove(supported_extensions, "GL_AMD_draw_buffers_blend");
/* 5311:5311 */    if ((supported_extensions.contains("GL_AMD_multi_draw_indirect")) && (!AMD_multi_draw_indirect_initNativeFunctionAddresses()))
/* 5312:5312 */      remove(supported_extensions, "GL_AMD_multi_draw_indirect");
/* 5313:5313 */    if ((supported_extensions.contains("GL_AMD_name_gen_delete")) && (!AMD_name_gen_delete_initNativeFunctionAddresses()))
/* 5314:5314 */      remove(supported_extensions, "GL_AMD_name_gen_delete");
/* 5315:5315 */    if ((supported_extensions.contains("GL_AMD_performance_monitor")) && (!AMD_performance_monitor_initNativeFunctionAddresses()))
/* 5316:5316 */      remove(supported_extensions, "GL_AMD_performance_monitor");
/* 5317:5317 */    if ((supported_extensions.contains("GL_AMD_sample_positions")) && (!AMD_sample_positions_initNativeFunctionAddresses()))
/* 5318:5318 */      remove(supported_extensions, "GL_AMD_sample_positions");
/* 5319:5319 */    if ((supported_extensions.contains("GL_AMD_sparse_texture")) && (!AMD_sparse_texture_initNativeFunctionAddresses()))
/* 5320:5320 */      remove(supported_extensions, "GL_AMD_sparse_texture");
/* 5321:5321 */    if ((supported_extensions.contains("GL_AMD_stencil_operation_extended")) && (!AMD_stencil_operation_extended_initNativeFunctionAddresses()))
/* 5322:5322 */      remove(supported_extensions, "GL_AMD_stencil_operation_extended");
/* 5323:5323 */    if ((supported_extensions.contains("GL_AMD_vertex_shader_tessellator")) && (!AMD_vertex_shader_tessellator_initNativeFunctionAddresses()))
/* 5324:5324 */      remove(supported_extensions, "GL_AMD_vertex_shader_tessellator");
/* 5325:5325 */    if ((supported_extensions.contains("GL_APPLE_element_array")) && (!APPLE_element_array_initNativeFunctionAddresses()))
/* 5326:5326 */      remove(supported_extensions, "GL_APPLE_element_array");
/* 5327:5327 */    if ((supported_extensions.contains("GL_APPLE_fence")) && (!APPLE_fence_initNativeFunctionAddresses()))
/* 5328:5328 */      remove(supported_extensions, "GL_APPLE_fence");
/* 5329:5329 */    if ((supported_extensions.contains("GL_APPLE_flush_buffer_range")) && (!APPLE_flush_buffer_range_initNativeFunctionAddresses()))
/* 5330:5330 */      remove(supported_extensions, "GL_APPLE_flush_buffer_range");
/* 5331:5331 */    if ((supported_extensions.contains("GL_APPLE_object_purgeable")) && (!APPLE_object_purgeable_initNativeFunctionAddresses()))
/* 5332:5332 */      remove(supported_extensions, "GL_APPLE_object_purgeable");
/* 5333:5333 */    if ((supported_extensions.contains("GL_APPLE_texture_range")) && (!APPLE_texture_range_initNativeFunctionAddresses()))
/* 5334:5334 */      remove(supported_extensions, "GL_APPLE_texture_range");
/* 5335:5335 */    if ((supported_extensions.contains("GL_APPLE_vertex_array_object")) && (!APPLE_vertex_array_object_initNativeFunctionAddresses()))
/* 5336:5336 */      remove(supported_extensions, "GL_APPLE_vertex_array_object");
/* 5337:5337 */    if ((supported_extensions.contains("GL_APPLE_vertex_array_range")) && (!APPLE_vertex_array_range_initNativeFunctionAddresses()))
/* 5338:5338 */      remove(supported_extensions, "GL_APPLE_vertex_array_range");
/* 5339:5339 */    if ((supported_extensions.contains("GL_APPLE_vertex_program_evaluators")) && (!APPLE_vertex_program_evaluators_initNativeFunctionAddresses()))
/* 5340:5340 */      remove(supported_extensions, "GL_APPLE_vertex_program_evaluators");
/* 5341:5341 */    if ((supported_extensions.contains("GL_ARB_ES2_compatibility")) && (!ARB_ES2_compatibility_initNativeFunctionAddresses()))
/* 5342:5342 */      remove(supported_extensions, "GL_ARB_ES2_compatibility");
/* 5343:5343 */    if ((supported_extensions.contains("GL_ARB_base_instance")) && (!ARB_base_instance_initNativeFunctionAddresses()))
/* 5344:5344 */      remove(supported_extensions, "GL_ARB_base_instance");
/* 5345:5345 */    if ((supported_extensions.contains("GL_ARB_blend_func_extended")) && (!ARB_blend_func_extended_initNativeFunctionAddresses()))
/* 5346:5346 */      remove(supported_extensions, "GL_ARB_blend_func_extended");
/* 5347:5347 */    if ((supported_extensions.contains("GL_ARB_buffer_object")) && (!ARB_buffer_object_initNativeFunctionAddresses()))
/* 5348:5348 */      remove(supported_extensions, "GL_ARB_buffer_object");
/* 5349:5349 */    if ((supported_extensions.contains("GL_ARB_cl_event")) && (!ARB_cl_event_initNativeFunctionAddresses()))
/* 5350:5350 */      remove(supported_extensions, "GL_ARB_cl_event");
/* 5351:5351 */    if ((supported_extensions.contains("GL_ARB_clear_buffer_object")) && (!ARB_clear_buffer_object_initNativeFunctionAddresses(supported_extensions)))
/* 5352:5352 */      remove(supported_extensions, "GL_ARB_clear_buffer_object");
/* 5353:5353 */    if ((supported_extensions.contains("GL_ARB_color_buffer_float")) && (!ARB_color_buffer_float_initNativeFunctionAddresses()))
/* 5354:5354 */      remove(supported_extensions, "GL_ARB_color_buffer_float");
/* 5355:5355 */    if ((supported_extensions.contains("GL_ARB_compute_shader")) && (!ARB_compute_shader_initNativeFunctionAddresses()))
/* 5356:5356 */      remove(supported_extensions, "GL_ARB_compute_shader");
/* 5357:5357 */    if ((supported_extensions.contains("GL_ARB_copy_buffer")) && (!ARB_copy_buffer_initNativeFunctionAddresses()))
/* 5358:5358 */      remove(supported_extensions, "GL_ARB_copy_buffer");
/* 5359:5359 */    if ((supported_extensions.contains("GL_ARB_copy_image")) && (!ARB_copy_image_initNativeFunctionAddresses()))
/* 5360:5360 */      remove(supported_extensions, "GL_ARB_copy_image");
/* 5361:5361 */    if ((supported_extensions.contains("GL_ARB_debug_output")) && (!ARB_debug_output_initNativeFunctionAddresses()))
/* 5362:5362 */      remove(supported_extensions, "GL_ARB_debug_output");
/* 5363:5363 */    if ((supported_extensions.contains("GL_ARB_draw_buffers")) && (!ARB_draw_buffers_initNativeFunctionAddresses()))
/* 5364:5364 */      remove(supported_extensions, "GL_ARB_draw_buffers");
/* 5365:5365 */    if ((supported_extensions.contains("GL_ARB_draw_buffers_blend")) && (!ARB_draw_buffers_blend_initNativeFunctionAddresses()))
/* 5366:5366 */      remove(supported_extensions, "GL_ARB_draw_buffers_blend");
/* 5367:5367 */    if ((supported_extensions.contains("GL_ARB_draw_elements_base_vertex")) && (!ARB_draw_elements_base_vertex_initNativeFunctionAddresses()))
/* 5368:5368 */      remove(supported_extensions, "GL_ARB_draw_elements_base_vertex");
/* 5369:5369 */    if ((supported_extensions.contains("GL_ARB_draw_indirect")) && (!ARB_draw_indirect_initNativeFunctionAddresses()))
/* 5370:5370 */      remove(supported_extensions, "GL_ARB_draw_indirect");
/* 5371:5371 */    if ((supported_extensions.contains("GL_ARB_draw_instanced")) && (!ARB_draw_instanced_initNativeFunctionAddresses()))
/* 5372:5372 */      remove(supported_extensions, "GL_ARB_draw_instanced");
/* 5373:5373 */    if ((supported_extensions.contains("GL_ARB_framebuffer_no_attachments")) && (!ARB_framebuffer_no_attachments_initNativeFunctionAddresses(supported_extensions)))
/* 5374:5374 */      remove(supported_extensions, "GL_ARB_framebuffer_no_attachments");
/* 5375:5375 */    if ((supported_extensions.contains("GL_ARB_framebuffer_object")) && (!ARB_framebuffer_object_initNativeFunctionAddresses()))
/* 5376:5376 */      remove(supported_extensions, "GL_ARB_framebuffer_object");
/* 5377:5377 */    if ((supported_extensions.contains("GL_ARB_geometry_shader4")) && (!ARB_geometry_shader4_initNativeFunctionAddresses()))
/* 5378:5378 */      remove(supported_extensions, "GL_ARB_geometry_shader4");
/* 5379:5379 */    if ((supported_extensions.contains("GL_ARB_get_program_binary")) && (!ARB_get_program_binary_initNativeFunctionAddresses()))
/* 5380:5380 */      remove(supported_extensions, "GL_ARB_get_program_binary");
/* 5381:5381 */    if ((supported_extensions.contains("GL_ARB_gpu_shader_fp64")) && (!ARB_gpu_shader_fp64_initNativeFunctionAddresses(supported_extensions)))
/* 5382:5382 */      remove(supported_extensions, "GL_ARB_gpu_shader_fp64");
/* 5383:5383 */    if ((supported_extensions.contains("GL_ARB_imaging")) && (!ARB_imaging_initNativeFunctionAddresses(forwardCompatible)))
/* 5384:5384 */      remove(supported_extensions, "GL_ARB_imaging");
/* 5385:5385 */    if ((supported_extensions.contains("GL_ARB_instanced_arrays")) && (!ARB_instanced_arrays_initNativeFunctionAddresses()))
/* 5386:5386 */      remove(supported_extensions, "GL_ARB_instanced_arrays");
/* 5387:5387 */    if ((supported_extensions.contains("GL_ARB_internalformat_query")) && (!ARB_internalformat_query_initNativeFunctionAddresses()))
/* 5388:5388 */      remove(supported_extensions, "GL_ARB_internalformat_query");
/* 5389:5389 */    if ((supported_extensions.contains("GL_ARB_internalformat_query2")) && (!ARB_internalformat_query2_initNativeFunctionAddresses()))
/* 5390:5390 */      remove(supported_extensions, "GL_ARB_internalformat_query2");
/* 5391:5391 */    if ((supported_extensions.contains("GL_ARB_invalidate_subdata")) && (!ARB_invalidate_subdata_initNativeFunctionAddresses()))
/* 5392:5392 */      remove(supported_extensions, "GL_ARB_invalidate_subdata");
/* 5393:5393 */    if ((supported_extensions.contains("GL_ARB_map_buffer_range")) && (!ARB_map_buffer_range_initNativeFunctionAddresses()))
/* 5394:5394 */      remove(supported_extensions, "GL_ARB_map_buffer_range");
/* 5395:5395 */    if ((supported_extensions.contains("GL_ARB_matrix_palette")) && (!ARB_matrix_palette_initNativeFunctionAddresses()))
/* 5396:5396 */      remove(supported_extensions, "GL_ARB_matrix_palette");
/* 5397:5397 */    if ((supported_extensions.contains("GL_ARB_multi_draw_indirect")) && (!ARB_multi_draw_indirect_initNativeFunctionAddresses()))
/* 5398:5398 */      remove(supported_extensions, "GL_ARB_multi_draw_indirect");
/* 5399:5399 */    if ((supported_extensions.contains("GL_ARB_multisample")) && (!ARB_multisample_initNativeFunctionAddresses()))
/* 5400:5400 */      remove(supported_extensions, "GL_ARB_multisample");
/* 5401:5401 */    if ((supported_extensions.contains("GL_ARB_multitexture")) && (!ARB_multitexture_initNativeFunctionAddresses()))
/* 5402:5402 */      remove(supported_extensions, "GL_ARB_multitexture");
/* 5403:5403 */    if ((supported_extensions.contains("GL_ARB_occlusion_query")) && (!ARB_occlusion_query_initNativeFunctionAddresses()))
/* 5404:5404 */      remove(supported_extensions, "GL_ARB_occlusion_query");
/* 5405:5405 */    if ((supported_extensions.contains("GL_ARB_point_parameters")) && (!ARB_point_parameters_initNativeFunctionAddresses()))
/* 5406:5406 */      remove(supported_extensions, "GL_ARB_point_parameters");
/* 5407:5407 */    if ((supported_extensions.contains("GL_ARB_program")) && (!ARB_program_initNativeFunctionAddresses()))
/* 5408:5408 */      remove(supported_extensions, "GL_ARB_program");
/* 5409:5409 */    if ((supported_extensions.contains("GL_ARB_program_interface_query")) && (!ARB_program_interface_query_initNativeFunctionAddresses()))
/* 5410:5410 */      remove(supported_extensions, "GL_ARB_program_interface_query");
/* 5411:5411 */    if ((supported_extensions.contains("GL_ARB_provoking_vertex")) && (!ARB_provoking_vertex_initNativeFunctionAddresses()))
/* 5412:5412 */      remove(supported_extensions, "GL_ARB_provoking_vertex");
/* 5413:5413 */    if ((supported_extensions.contains("GL_ARB_robustness")) && (!ARB_robustness_initNativeFunctionAddresses(forwardCompatible, supported_extensions)))
/* 5414:5414 */      remove(supported_extensions, "GL_ARB_robustness");
/* 5415:5415 */    if ((supported_extensions.contains("GL_ARB_sample_shading")) && (!ARB_sample_shading_initNativeFunctionAddresses()))
/* 5416:5416 */      remove(supported_extensions, "GL_ARB_sample_shading");
/* 5417:5417 */    if ((supported_extensions.contains("GL_ARB_sampler_objects")) && (!ARB_sampler_objects_initNativeFunctionAddresses()))
/* 5418:5418 */      remove(supported_extensions, "GL_ARB_sampler_objects");
/* 5419:5419 */    if ((supported_extensions.contains("GL_ARB_separate_shader_objects")) && (!ARB_separate_shader_objects_initNativeFunctionAddresses()))
/* 5420:5420 */      remove(supported_extensions, "GL_ARB_separate_shader_objects");
/* 5421:5421 */    if ((supported_extensions.contains("GL_ARB_shader_atomic_counters")) && (!ARB_shader_atomic_counters_initNativeFunctionAddresses()))
/* 5422:5422 */      remove(supported_extensions, "GL_ARB_shader_atomic_counters");
/* 5423:5423 */    if ((supported_extensions.contains("GL_ARB_shader_image_load_store")) && (!ARB_shader_image_load_store_initNativeFunctionAddresses()))
/* 5424:5424 */      remove(supported_extensions, "GL_ARB_shader_image_load_store");
/* 5425:5425 */    if ((supported_extensions.contains("GL_ARB_shader_objects")) && (!ARB_shader_objects_initNativeFunctionAddresses()))
/* 5426:5426 */      remove(supported_extensions, "GL_ARB_shader_objects");
/* 5427:5427 */    if ((supported_extensions.contains("GL_ARB_shader_storage_buffer_object")) && (!ARB_shader_storage_buffer_object_initNativeFunctionAddresses()))
/* 5428:5428 */      remove(supported_extensions, "GL_ARB_shader_storage_buffer_object");
/* 5429:5429 */    if ((supported_extensions.contains("GL_ARB_shader_subroutine")) && (!ARB_shader_subroutine_initNativeFunctionAddresses()))
/* 5430:5430 */      remove(supported_extensions, "GL_ARB_shader_subroutine");
/* 5431:5431 */    if ((supported_extensions.contains("GL_ARB_shading_language_include")) && (!ARB_shading_language_include_initNativeFunctionAddresses()))
/* 5432:5432 */      remove(supported_extensions, "GL_ARB_shading_language_include");
/* 5433:5433 */    if ((supported_extensions.contains("GL_ARB_sync")) && (!ARB_sync_initNativeFunctionAddresses()))
/* 5434:5434 */      remove(supported_extensions, "GL_ARB_sync");
/* 5435:5435 */    if ((supported_extensions.contains("GL_ARB_tessellation_shader")) && (!ARB_tessellation_shader_initNativeFunctionAddresses()))
/* 5436:5436 */      remove(supported_extensions, "GL_ARB_tessellation_shader");
/* 5437:5437 */    if ((supported_extensions.contains("GL_ARB_texture_buffer_object")) && (!ARB_texture_buffer_object_initNativeFunctionAddresses()))
/* 5438:5438 */      remove(supported_extensions, "GL_ARB_texture_buffer_object");
/* 5439:5439 */    if ((supported_extensions.contains("GL_ARB_texture_buffer_range")) && (!ARB_texture_buffer_range_initNativeFunctionAddresses(supported_extensions)))
/* 5440:5440 */      remove(supported_extensions, "GL_ARB_texture_buffer_range");
/* 5441:5441 */    if ((supported_extensions.contains("GL_ARB_texture_compression")) && (!ARB_texture_compression_initNativeFunctionAddresses()))
/* 5442:5442 */      remove(supported_extensions, "GL_ARB_texture_compression");
/* 5443:5443 */    if ((supported_extensions.contains("GL_ARB_texture_multisample")) && (!ARB_texture_multisample_initNativeFunctionAddresses()))
/* 5444:5444 */      remove(supported_extensions, "GL_ARB_texture_multisample");
/* 5445:5445 */    if (((supported_extensions.contains("GL_ARB_texture_storage")) || (supported_extensions.contains("GL_EXT_texture_storage"))) && (!ARB_texture_storage_initNativeFunctionAddresses(supported_extensions))) {
/* 5446:5446 */      remove(supported_extensions, "GL_EXT_texture_storage");
/* 5447:5447 */      remove(supported_extensions, "GL_ARB_texture_storage");
/* 5448:     */    }
/* 5449:5449 */    if ((supported_extensions.contains("GL_ARB_texture_storage_multisample")) && (!ARB_texture_storage_multisample_initNativeFunctionAddresses(supported_extensions)))
/* 5450:5450 */      remove(supported_extensions, "GL_ARB_texture_storage_multisample");
/* 5451:5451 */    if ((supported_extensions.contains("GL_ARB_texture_view")) && (!ARB_texture_view_initNativeFunctionAddresses()))
/* 5452:5452 */      remove(supported_extensions, "GL_ARB_texture_view");
/* 5453:5453 */    if ((supported_extensions.contains("GL_ARB_timer_query")) && (!ARB_timer_query_initNativeFunctionAddresses()))
/* 5454:5454 */      remove(supported_extensions, "GL_ARB_timer_query");
/* 5455:5455 */    if ((supported_extensions.contains("GL_ARB_transform_feedback2")) && (!ARB_transform_feedback2_initNativeFunctionAddresses()))
/* 5456:5456 */      remove(supported_extensions, "GL_ARB_transform_feedback2");
/* 5457:5457 */    if ((supported_extensions.contains("GL_ARB_transform_feedback3")) && (!ARB_transform_feedback3_initNativeFunctionAddresses()))
/* 5458:5458 */      remove(supported_extensions, "GL_ARB_transform_feedback3");
/* 5459:5459 */    if ((supported_extensions.contains("GL_ARB_transform_feedback_instanced")) && (!ARB_transform_feedback_instanced_initNativeFunctionAddresses()))
/* 5460:5460 */      remove(supported_extensions, "GL_ARB_transform_feedback_instanced");
/* 5461:5461 */    if ((supported_extensions.contains("GL_ARB_transpose_matrix")) && (!ARB_transpose_matrix_initNativeFunctionAddresses()))
/* 5462:5462 */      remove(supported_extensions, "GL_ARB_transpose_matrix");
/* 5463:5463 */    if ((supported_extensions.contains("GL_ARB_uniform_buffer_object")) && (!ARB_uniform_buffer_object_initNativeFunctionAddresses()))
/* 5464:5464 */      remove(supported_extensions, "GL_ARB_uniform_buffer_object");
/* 5465:5465 */    if ((supported_extensions.contains("GL_ARB_vertex_array_object")) && (!ARB_vertex_array_object_initNativeFunctionAddresses()))
/* 5466:5466 */      remove(supported_extensions, "GL_ARB_vertex_array_object");
/* 5467:5467 */    if ((supported_extensions.contains("GL_ARB_vertex_attrib_64bit")) && (!ARB_vertex_attrib_64bit_initNativeFunctionAddresses(supported_extensions)))
/* 5468:5468 */      remove(supported_extensions, "GL_ARB_vertex_attrib_64bit");
/* 5469:5469 */    if ((supported_extensions.contains("GL_ARB_vertex_attrib_binding")) && (!ARB_vertex_attrib_binding_initNativeFunctionAddresses()))
/* 5470:5470 */      remove(supported_extensions, "GL_ARB_vertex_attrib_binding");
/* 5471:5471 */    if ((supported_extensions.contains("GL_ARB_vertex_blend")) && (!ARB_vertex_blend_initNativeFunctionAddresses()))
/* 5472:5472 */      remove(supported_extensions, "GL_ARB_vertex_blend");
/* 5473:5473 */    if ((supported_extensions.contains("GL_ARB_vertex_program")) && (!ARB_vertex_program_initNativeFunctionAddresses()))
/* 5474:5474 */      remove(supported_extensions, "GL_ARB_vertex_program");
/* 5475:5475 */    if ((supported_extensions.contains("GL_ARB_vertex_shader")) && (!ARB_vertex_shader_initNativeFunctionAddresses()))
/* 5476:5476 */      remove(supported_extensions, "GL_ARB_vertex_shader");
/* 5477:5477 */    if ((supported_extensions.contains("GL_ARB_vertex_type_2_10_10_10_rev")) && (!ARB_vertex_type_2_10_10_10_rev_initNativeFunctionAddresses()))
/* 5478:5478 */      remove(supported_extensions, "GL_ARB_vertex_type_2_10_10_10_rev");
/* 5479:5479 */    if ((supported_extensions.contains("GL_ARB_viewport_array")) && (!ARB_viewport_array_initNativeFunctionAddresses()))
/* 5480:5480 */      remove(supported_extensions, "GL_ARB_viewport_array");
/* 5481:5481 */    if ((supported_extensions.contains("GL_ARB_window_pos")) && (!ARB_window_pos_initNativeFunctionAddresses(forwardCompatible)))
/* 5482:5482 */      remove(supported_extensions, "GL_ARB_window_pos");
/* 5483:5483 */    if ((supported_extensions.contains("GL_ATI_draw_buffers")) && (!ATI_draw_buffers_initNativeFunctionAddresses()))
/* 5484:5484 */      remove(supported_extensions, "GL_ATI_draw_buffers");
/* 5485:5485 */    if ((supported_extensions.contains("GL_ATI_element_array")) && (!ATI_element_array_initNativeFunctionAddresses()))
/* 5486:5486 */      remove(supported_extensions, "GL_ATI_element_array");
/* 5487:5487 */    if ((supported_extensions.contains("GL_ATI_envmap_bumpmap")) && (!ATI_envmap_bumpmap_initNativeFunctionAddresses()))
/* 5488:5488 */      remove(supported_extensions, "GL_ATI_envmap_bumpmap");
/* 5489:5489 */    if ((supported_extensions.contains("GL_ATI_fragment_shader")) && (!ATI_fragment_shader_initNativeFunctionAddresses()))
/* 5490:5490 */      remove(supported_extensions, "GL_ATI_fragment_shader");
/* 5491:5491 */    if ((supported_extensions.contains("GL_ATI_map_object_buffer")) && (!ATI_map_object_buffer_initNativeFunctionAddresses()))
/* 5492:5492 */      remove(supported_extensions, "GL_ATI_map_object_buffer");
/* 5493:5493 */    if ((supported_extensions.contains("GL_ATI_pn_triangles")) && (!ATI_pn_triangles_initNativeFunctionAddresses()))
/* 5494:5494 */      remove(supported_extensions, "GL_ATI_pn_triangles");
/* 5495:5495 */    if ((supported_extensions.contains("GL_ATI_separate_stencil")) && (!ATI_separate_stencil_initNativeFunctionAddresses()))
/* 5496:5496 */      remove(supported_extensions, "GL_ATI_separate_stencil");
/* 5497:5497 */    if ((supported_extensions.contains("GL_ATI_vertex_array_object")) && (!ATI_vertex_array_object_initNativeFunctionAddresses()))
/* 5498:5498 */      remove(supported_extensions, "GL_ATI_vertex_array_object");
/* 5499:5499 */    if ((supported_extensions.contains("GL_ATI_vertex_attrib_array_object")) && (!ATI_vertex_attrib_array_object_initNativeFunctionAddresses()))
/* 5500:5500 */      remove(supported_extensions, "GL_ATI_vertex_attrib_array_object");
/* 5501:5501 */    if ((supported_extensions.contains("GL_ATI_vertex_streams")) && (!ATI_vertex_streams_initNativeFunctionAddresses()))
/* 5502:5502 */      remove(supported_extensions, "GL_ATI_vertex_streams");
/* 5503:5503 */    if ((supported_extensions.contains("GL_EXT_bindable_uniform")) && (!EXT_bindable_uniform_initNativeFunctionAddresses()))
/* 5504:5504 */      remove(supported_extensions, "GL_EXT_bindable_uniform");
/* 5505:5505 */    if ((supported_extensions.contains("GL_EXT_blend_color")) && (!EXT_blend_color_initNativeFunctionAddresses()))
/* 5506:5506 */      remove(supported_extensions, "GL_EXT_blend_color");
/* 5507:5507 */    if ((supported_extensions.contains("GL_EXT_blend_equation_separate")) && (!EXT_blend_equation_separate_initNativeFunctionAddresses()))
/* 5508:5508 */      remove(supported_extensions, "GL_EXT_blend_equation_separate");
/* 5509:5509 */    if ((supported_extensions.contains("GL_EXT_blend_func_separate")) && (!EXT_blend_func_separate_initNativeFunctionAddresses()))
/* 5510:5510 */      remove(supported_extensions, "GL_EXT_blend_func_separate");
/* 5511:5511 */    if ((supported_extensions.contains("GL_EXT_blend_minmax")) && (!EXT_blend_minmax_initNativeFunctionAddresses()))
/* 5512:5512 */      remove(supported_extensions, "GL_EXT_blend_minmax");
/* 5513:5513 */    if ((supported_extensions.contains("GL_EXT_compiled_vertex_array")) && (!EXT_compiled_vertex_array_initNativeFunctionAddresses()))
/* 5514:5514 */      remove(supported_extensions, "GL_EXT_compiled_vertex_array");
/* 5515:5515 */    if ((supported_extensions.contains("GL_EXT_depth_bounds_test")) && (!EXT_depth_bounds_test_initNativeFunctionAddresses()))
/* 5516:5516 */      remove(supported_extensions, "GL_EXT_depth_bounds_test");
/* 5517:5517 */    supported_extensions.add("GL_EXT_direct_state_access");
/* 5518:5518 */    if ((supported_extensions.contains("GL_EXT_direct_state_access")) && (!EXT_direct_state_access_initNativeFunctionAddresses(forwardCompatible, supported_extensions)))
/* 5519:5519 */      remove(supported_extensions, "GL_EXT_direct_state_access");
/* 5520:5520 */    if ((supported_extensions.contains("GL_EXT_draw_buffers2")) && (!EXT_draw_buffers2_initNativeFunctionAddresses()))
/* 5521:5521 */      remove(supported_extensions, "GL_EXT_draw_buffers2");
/* 5522:5522 */    if ((supported_extensions.contains("GL_EXT_draw_instanced")) && (!EXT_draw_instanced_initNativeFunctionAddresses()))
/* 5523:5523 */      remove(supported_extensions, "GL_EXT_draw_instanced");
/* 5524:5524 */    if ((supported_extensions.contains("GL_EXT_draw_range_elements")) && (!EXT_draw_range_elements_initNativeFunctionAddresses()))
/* 5525:5525 */      remove(supported_extensions, "GL_EXT_draw_range_elements");
/* 5526:5526 */    if ((supported_extensions.contains("GL_EXT_fog_coord")) && (!EXT_fog_coord_initNativeFunctionAddresses()))
/* 5527:5527 */      remove(supported_extensions, "GL_EXT_fog_coord");
/* 5528:5528 */    if ((supported_extensions.contains("GL_EXT_framebuffer_blit")) && (!EXT_framebuffer_blit_initNativeFunctionAddresses()))
/* 5529:5529 */      remove(supported_extensions, "GL_EXT_framebuffer_blit");
/* 5530:5530 */    if ((supported_extensions.contains("GL_EXT_framebuffer_multisample")) && (!EXT_framebuffer_multisample_initNativeFunctionAddresses()))
/* 5531:5531 */      remove(supported_extensions, "GL_EXT_framebuffer_multisample");
/* 5532:5532 */    if ((supported_extensions.contains("GL_EXT_framebuffer_object")) && (!EXT_framebuffer_object_initNativeFunctionAddresses()))
/* 5533:5533 */      remove(supported_extensions, "GL_EXT_framebuffer_object");
/* 5534:5534 */    if ((supported_extensions.contains("GL_EXT_geometry_shader4")) && (!EXT_geometry_shader4_initNativeFunctionAddresses()))
/* 5535:5535 */      remove(supported_extensions, "GL_EXT_geometry_shader4");
/* 5536:5536 */    if ((supported_extensions.contains("GL_EXT_gpu_program_parameters")) && (!EXT_gpu_program_parameters_initNativeFunctionAddresses()))
/* 5537:5537 */      remove(supported_extensions, "GL_EXT_gpu_program_parameters");
/* 5538:5538 */    if ((supported_extensions.contains("GL_EXT_gpu_shader4")) && (!EXT_gpu_shader4_initNativeFunctionAddresses()))
/* 5539:5539 */      remove(supported_extensions, "GL_EXT_gpu_shader4");
/* 5540:5540 */    if ((supported_extensions.contains("GL_EXT_multi_draw_arrays")) && (!EXT_multi_draw_arrays_initNativeFunctionAddresses()))
/* 5541:5541 */      remove(supported_extensions, "GL_EXT_multi_draw_arrays");
/* 5542:5542 */    if ((supported_extensions.contains("GL_EXT_paletted_texture")) && (!EXT_paletted_texture_initNativeFunctionAddresses()))
/* 5543:5543 */      remove(supported_extensions, "GL_EXT_paletted_texture");
/* 5544:5544 */    if ((supported_extensions.contains("GL_EXT_point_parameters")) && (!EXT_point_parameters_initNativeFunctionAddresses()))
/* 5545:5545 */      remove(supported_extensions, "GL_EXT_point_parameters");
/* 5546:5546 */    if ((supported_extensions.contains("GL_EXT_provoking_vertex")) && (!EXT_provoking_vertex_initNativeFunctionAddresses()))
/* 5547:5547 */      remove(supported_extensions, "GL_EXT_provoking_vertex");
/* 5548:5548 */    if ((supported_extensions.contains("GL_EXT_secondary_color")) && (!EXT_secondary_color_initNativeFunctionAddresses()))
/* 5549:5549 */      remove(supported_extensions, "GL_EXT_secondary_color");
/* 5550:5550 */    if ((supported_extensions.contains("GL_EXT_separate_shader_objects")) && (!EXT_separate_shader_objects_initNativeFunctionAddresses()))
/* 5551:5551 */      remove(supported_extensions, "GL_EXT_separate_shader_objects");
/* 5552:5552 */    if ((supported_extensions.contains("GL_EXT_shader_image_load_store")) && (!EXT_shader_image_load_store_initNativeFunctionAddresses()))
/* 5553:5553 */      remove(supported_extensions, "GL_EXT_shader_image_load_store");
/* 5554:5554 */    if ((supported_extensions.contains("GL_EXT_stencil_clear_tag")) && (!EXT_stencil_clear_tag_initNativeFunctionAddresses()))
/* 5555:5555 */      remove(supported_extensions, "GL_EXT_stencil_clear_tag");
/* 5556:5556 */    if ((supported_extensions.contains("GL_EXT_stencil_two_side")) && (!EXT_stencil_two_side_initNativeFunctionAddresses()))
/* 5557:5557 */      remove(supported_extensions, "GL_EXT_stencil_two_side");
/* 5558:5558 */    if ((supported_extensions.contains("GL_EXT_texture_array")) && (!EXT_texture_array_initNativeFunctionAddresses()))
/* 5559:5559 */      remove(supported_extensions, "GL_EXT_texture_array");
/* 5560:5560 */    if ((supported_extensions.contains("GL_EXT_texture_buffer_object")) && (!EXT_texture_buffer_object_initNativeFunctionAddresses()))
/* 5561:5561 */      remove(supported_extensions, "GL_EXT_texture_buffer_object");
/* 5562:5562 */    if ((supported_extensions.contains("GL_EXT_texture_integer")) && (!EXT_texture_integer_initNativeFunctionAddresses()))
/* 5563:5563 */      remove(supported_extensions, "GL_EXT_texture_integer");
/* 5564:5564 */    if ((supported_extensions.contains("GL_EXT_timer_query")) && (!EXT_timer_query_initNativeFunctionAddresses()))
/* 5565:5565 */      remove(supported_extensions, "GL_EXT_timer_query");
/* 5566:5566 */    if ((supported_extensions.contains("GL_EXT_transform_feedback")) && (!EXT_transform_feedback_initNativeFunctionAddresses()))
/* 5567:5567 */      remove(supported_extensions, "GL_EXT_transform_feedback");
/* 5568:5568 */    if ((supported_extensions.contains("GL_EXT_vertex_attrib_64bit")) && (!EXT_vertex_attrib_64bit_initNativeFunctionAddresses(supported_extensions)))
/* 5569:5569 */      remove(supported_extensions, "GL_EXT_vertex_attrib_64bit");
/* 5570:5570 */    if ((supported_extensions.contains("GL_EXT_vertex_shader")) && (!EXT_vertex_shader_initNativeFunctionAddresses()))
/* 5571:5571 */      remove(supported_extensions, "GL_EXT_vertex_shader");
/* 5572:5572 */    if ((supported_extensions.contains("GL_EXT_vertex_weighting")) && (!EXT_vertex_weighting_initNativeFunctionAddresses()))
/* 5573:5573 */      remove(supported_extensions, "GL_EXT_vertex_weighting");
/* 5574:5574 */    if ((supported_extensions.contains("OpenGL12")) && (!GL12_initNativeFunctionAddresses()))
/* 5575:5575 */      remove(supported_extensions, "OpenGL12");
/* 5576:5576 */    if ((supported_extensions.contains("OpenGL13")) && (!GL13_initNativeFunctionAddresses(forwardCompatible)))
/* 5577:5577 */      remove(supported_extensions, "OpenGL13");
/* 5578:5578 */    if ((supported_extensions.contains("OpenGL14")) && (!GL14_initNativeFunctionAddresses(forwardCompatible)))
/* 5579:5579 */      remove(supported_extensions, "OpenGL14");
/* 5580:5580 */    if ((supported_extensions.contains("OpenGL15")) && (!GL15_initNativeFunctionAddresses()))
/* 5581:5581 */      remove(supported_extensions, "OpenGL15");
/* 5582:5582 */    if ((supported_extensions.contains("OpenGL20")) && (!GL20_initNativeFunctionAddresses()))
/* 5583:5583 */      remove(supported_extensions, "OpenGL20");
/* 5584:5584 */    if ((supported_extensions.contains("OpenGL21")) && (!GL21_initNativeFunctionAddresses()))
/* 5585:5585 */      remove(supported_extensions, "OpenGL21");
/* 5586:5586 */    if ((supported_extensions.contains("OpenGL30")) && (!GL30_initNativeFunctionAddresses()))
/* 5587:5587 */      remove(supported_extensions, "OpenGL30");
/* 5588:5588 */    if ((supported_extensions.contains("OpenGL31")) && (!GL31_initNativeFunctionAddresses()))
/* 5589:5589 */      remove(supported_extensions, "OpenGL31");
/* 5590:5590 */    if ((supported_extensions.contains("OpenGL32")) && (!GL32_initNativeFunctionAddresses()))
/* 5591:5591 */      remove(supported_extensions, "OpenGL32");
/* 5592:5592 */    if ((supported_extensions.contains("OpenGL33")) && (!GL33_initNativeFunctionAddresses(forwardCompatible)))
/* 5593:5593 */      remove(supported_extensions, "OpenGL33");
/* 5594:5594 */    if ((supported_extensions.contains("OpenGL40")) && (!GL40_initNativeFunctionAddresses()))
/* 5595:5595 */      remove(supported_extensions, "OpenGL40");
/* 5596:5596 */    if ((supported_extensions.contains("OpenGL41")) && (!GL41_initNativeFunctionAddresses()))
/* 5597:5597 */      remove(supported_extensions, "OpenGL41");
/* 5598:5598 */    if ((supported_extensions.contains("OpenGL42")) && (!GL42_initNativeFunctionAddresses()))
/* 5599:5599 */      remove(supported_extensions, "OpenGL42");
/* 5600:5600 */    if ((supported_extensions.contains("OpenGL43")) && (!GL43_initNativeFunctionAddresses()))
/* 5601:5601 */      remove(supported_extensions, "OpenGL43");
/* 5602:5602 */    if ((supported_extensions.contains("GL_GREMEDY_frame_terminator")) && (!GREMEDY_frame_terminator_initNativeFunctionAddresses()))
/* 5603:5603 */      remove(supported_extensions, "GL_GREMEDY_frame_terminator");
/* 5604:5604 */    if ((supported_extensions.contains("GL_GREMEDY_string_marker")) && (!GREMEDY_string_marker_initNativeFunctionAddresses()))
/* 5605:5605 */      remove(supported_extensions, "GL_GREMEDY_string_marker");
/* 5606:5606 */    if ((supported_extensions.contains("GL_INTEL_map_texture")) && (!INTEL_map_texture_initNativeFunctionAddresses()))
/* 5607:5607 */      remove(supported_extensions, "GL_INTEL_map_texture");
/* 5608:5608 */    if ((supported_extensions.contains("GL_KHR_debug")) && (!KHR_debug_initNativeFunctionAddresses()))
/* 5609:5609 */      remove(supported_extensions, "GL_KHR_debug");
/* 5610:5610 */    if ((supported_extensions.contains("GL_NV_bindless_texture")) && (!NV_bindless_texture_initNativeFunctionAddresses()))
/* 5611:5611 */      remove(supported_extensions, "GL_NV_bindless_texture");
/* 5612:5612 */    if ((supported_extensions.contains("GL_NV_conditional_render")) && (!NV_conditional_render_initNativeFunctionAddresses()))
/* 5613:5613 */      remove(supported_extensions, "GL_NV_conditional_render");
/* 5614:5614 */    if ((supported_extensions.contains("GL_NV_copy_image")) && (!NV_copy_image_initNativeFunctionAddresses()))
/* 5615:5615 */      remove(supported_extensions, "GL_NV_copy_image");
/* 5616:5616 */    if ((supported_extensions.contains("GL_NV_depth_buffer_float")) && (!NV_depth_buffer_float_initNativeFunctionAddresses()))
/* 5617:5617 */      remove(supported_extensions, "GL_NV_depth_buffer_float");
/* 5618:5618 */    if ((supported_extensions.contains("GL_NV_draw_texture")) && (!NV_draw_texture_initNativeFunctionAddresses()))
/* 5619:5619 */      remove(supported_extensions, "GL_NV_draw_texture");
/* 5620:5620 */    if ((supported_extensions.contains("GL_NV_evaluators")) && (!NV_evaluators_initNativeFunctionAddresses()))
/* 5621:5621 */      remove(supported_extensions, "GL_NV_evaluators");
/* 5622:5622 */    if ((supported_extensions.contains("GL_NV_explicit_multisample")) && (!NV_explicit_multisample_initNativeFunctionAddresses()))
/* 5623:5623 */      remove(supported_extensions, "GL_NV_explicit_multisample");
/* 5624:5624 */    if ((supported_extensions.contains("GL_NV_fence")) && (!NV_fence_initNativeFunctionAddresses()))
/* 5625:5625 */      remove(supported_extensions, "GL_NV_fence");
/* 5626:5626 */    if ((supported_extensions.contains("GL_NV_fragment_program")) && (!NV_fragment_program_initNativeFunctionAddresses()))
/* 5627:5627 */      remove(supported_extensions, "GL_NV_fragment_program");
/* 5628:5628 */    if ((supported_extensions.contains("GL_NV_framebuffer_multisample_coverage")) && (!NV_framebuffer_multisample_coverage_initNativeFunctionAddresses()))
/* 5629:5629 */      remove(supported_extensions, "GL_NV_framebuffer_multisample_coverage");
/* 5630:5630 */    if ((supported_extensions.contains("GL_NV_geometry_program4")) && (!NV_geometry_program4_initNativeFunctionAddresses()))
/* 5631:5631 */      remove(supported_extensions, "GL_NV_geometry_program4");
/* 5632:5632 */    if ((supported_extensions.contains("GL_NV_gpu_program4")) && (!NV_gpu_program4_initNativeFunctionAddresses()))
/* 5633:5633 */      remove(supported_extensions, "GL_NV_gpu_program4");
/* 5634:5634 */    if ((supported_extensions.contains("GL_NV_gpu_shader5")) && (!NV_gpu_shader5_initNativeFunctionAddresses(supported_extensions)))
/* 5635:5635 */      remove(supported_extensions, "GL_NV_gpu_shader5");
/* 5636:5636 */    if ((supported_extensions.contains("GL_NV_half_float")) && (!NV_half_float_initNativeFunctionAddresses()))
/* 5637:5637 */      remove(supported_extensions, "GL_NV_half_float");
/* 5638:5638 */    if ((supported_extensions.contains("GL_NV_occlusion_query")) && (!NV_occlusion_query_initNativeFunctionAddresses()))
/* 5639:5639 */      remove(supported_extensions, "GL_NV_occlusion_query");
/* 5640:5640 */    if ((supported_extensions.contains("GL_NV_parameter_buffer_object")) && (!NV_parameter_buffer_object_initNativeFunctionAddresses()))
/* 5641:5641 */      remove(supported_extensions, "GL_NV_parameter_buffer_object");
/* 5642:5642 */    if ((supported_extensions.contains("GL_NV_path_rendering")) && (!NV_path_rendering_initNativeFunctionAddresses()))
/* 5643:5643 */      remove(supported_extensions, "GL_NV_path_rendering");
/* 5644:5644 */    if ((supported_extensions.contains("GL_NV_pixel_data_range")) && (!NV_pixel_data_range_initNativeFunctionAddresses()))
/* 5645:5645 */      remove(supported_extensions, "GL_NV_pixel_data_range");
/* 5646:5646 */    if ((supported_extensions.contains("GL_NV_point_sprite")) && (!NV_point_sprite_initNativeFunctionAddresses()))
/* 5647:5647 */      remove(supported_extensions, "GL_NV_point_sprite");
/* 5648:5648 */    if ((supported_extensions.contains("GL_NV_present_video")) && (!NV_present_video_initNativeFunctionAddresses()))
/* 5649:5649 */      remove(supported_extensions, "GL_NV_present_video");
/* 5650:5650 */    supported_extensions.add("GL_NV_primitive_restart");
/* 5651:5651 */    if ((supported_extensions.contains("GL_NV_primitive_restart")) && (!NV_primitive_restart_initNativeFunctionAddresses()))
/* 5652:5652 */      remove(supported_extensions, "GL_NV_primitive_restart");
/* 5653:5653 */    if ((supported_extensions.contains("GL_NV_program")) && (!NV_program_initNativeFunctionAddresses()))
/* 5654:5654 */      remove(supported_extensions, "GL_NV_program");
/* 5655:5655 */    if ((supported_extensions.contains("GL_NV_register_combiners")) && (!NV_register_combiners_initNativeFunctionAddresses()))
/* 5656:5656 */      remove(supported_extensions, "GL_NV_register_combiners");
/* 5657:5657 */    if ((supported_extensions.contains("GL_NV_register_combiners2")) && (!NV_register_combiners2_initNativeFunctionAddresses()))
/* 5658:5658 */      remove(supported_extensions, "GL_NV_register_combiners2");
/* 5659:5659 */    if ((supported_extensions.contains("GL_NV_shader_buffer_load")) && (!NV_shader_buffer_load_initNativeFunctionAddresses()))
/* 5660:5660 */      remove(supported_extensions, "GL_NV_shader_buffer_load");
/* 5661:5661 */    if ((supported_extensions.contains("GL_NV_texture_barrier")) && (!NV_texture_barrier_initNativeFunctionAddresses()))
/* 5662:5662 */      remove(supported_extensions, "GL_NV_texture_barrier");
/* 5663:5663 */    if ((supported_extensions.contains("GL_NV_texture_multisample")) && (!NV_texture_multisample_initNativeFunctionAddresses()))
/* 5664:5664 */      remove(supported_extensions, "GL_NV_texture_multisample");
/* 5665:5665 */    if ((supported_extensions.contains("GL_NV_transform_feedback")) && (!NV_transform_feedback_initNativeFunctionAddresses()))
/* 5666:5666 */      remove(supported_extensions, "GL_NV_transform_feedback");
/* 5667:5667 */    if ((supported_extensions.contains("GL_NV_transform_feedback2")) && (!NV_transform_feedback2_initNativeFunctionAddresses()))
/* 5668:5668 */      remove(supported_extensions, "GL_NV_transform_feedback2");
/* 5669:5669 */    if ((supported_extensions.contains("GL_NV_vertex_array_range")) && (!NV_vertex_array_range_initNativeFunctionAddresses()))
/* 5670:5670 */      remove(supported_extensions, "GL_NV_vertex_array_range");
/* 5671:5671 */    if ((supported_extensions.contains("GL_NV_vertex_attrib_integer_64bit")) && (!NV_vertex_attrib_integer_64bit_initNativeFunctionAddresses(supported_extensions)))
/* 5672:5672 */      remove(supported_extensions, "GL_NV_vertex_attrib_integer_64bit");
/* 5673:5673 */    if ((supported_extensions.contains("GL_NV_vertex_buffer_unified_memory")) && (!NV_vertex_buffer_unified_memory_initNativeFunctionAddresses()))
/* 5674:5674 */      remove(supported_extensions, "GL_NV_vertex_buffer_unified_memory");
/* 5675:5675 */    if ((supported_extensions.contains("GL_NV_vertex_program")) && (!NV_vertex_program_initNativeFunctionAddresses()))
/* 5676:5676 */      remove(supported_extensions, "GL_NV_vertex_program");
/* 5677:5677 */    if ((supported_extensions.contains("GL_NV_video_capture")) && (!NV_video_capture_initNativeFunctionAddresses()))
/* 5678:5678 */      remove(supported_extensions, "GL_NV_video_capture");
/* 5679:5679 */    return supported_extensions;
/* 5680:     */  }
/* 5681:     */  
/* 5682:     */  static void unloadAllStubs() {}
/* 5683:     */  
/* 5684:     */  ContextCapabilities(boolean forwardCompatible) throws LWJGLException
/* 5685:     */  {
/* 5686:5686 */    Set<String> supported_extensions = initAllStubs(forwardCompatible);
/* 5687:5687 */    this.GL_AMD_blend_minmax_factor = supported_extensions.contains("GL_AMD_blend_minmax_factor");
/* 5688:5688 */    this.GL_AMD_conservative_depth = supported_extensions.contains("GL_AMD_conservative_depth");
/* 5689:5689 */    this.GL_AMD_debug_output = ((supported_extensions.contains("GL_AMD_debug_output")) || (supported_extensions.contains("GL_AMDX_debug_output")));
/* 5690:     */    
/* 5691:5691 */    this.GL_AMD_depth_clamp_separate = supported_extensions.contains("GL_AMD_depth_clamp_separate");
/* 5692:5692 */    this.GL_AMD_draw_buffers_blend = supported_extensions.contains("GL_AMD_draw_buffers_blend");
/* 5693:5693 */    this.GL_AMD_multi_draw_indirect = supported_extensions.contains("GL_AMD_multi_draw_indirect");
/* 5694:5694 */    this.GL_AMD_name_gen_delete = supported_extensions.contains("GL_AMD_name_gen_delete");
/* 5695:5695 */    this.GL_AMD_performance_monitor = supported_extensions.contains("GL_AMD_performance_monitor");
/* 5696:5696 */    this.GL_AMD_pinned_memory = supported_extensions.contains("GL_AMD_pinned_memory");
/* 5697:5697 */    this.GL_AMD_query_buffer_object = supported_extensions.contains("GL_AMD_query_buffer_object");
/* 5698:5698 */    this.GL_AMD_sample_positions = supported_extensions.contains("GL_AMD_sample_positions");
/* 5699:5699 */    this.GL_AMD_seamless_cubemap_per_texture = supported_extensions.contains("GL_AMD_seamless_cubemap_per_texture");
/* 5700:5700 */    this.GL_AMD_shader_stencil_export = supported_extensions.contains("GL_AMD_shader_stencil_export");
/* 5701:5701 */    this.GL_AMD_shader_trinary_minmax = supported_extensions.contains("GL_AMD_shader_trinary_minmax");
/* 5702:5702 */    this.GL_AMD_sparse_texture = supported_extensions.contains("GL_AMD_sparse_texture");
/* 5703:5703 */    this.GL_AMD_stencil_operation_extended = supported_extensions.contains("GL_AMD_stencil_operation_extended");
/* 5704:5704 */    this.GL_AMD_texture_texture4 = supported_extensions.contains("GL_AMD_texture_texture4");
/* 5705:5705 */    this.GL_AMD_transform_feedback3_lines_triangles = supported_extensions.contains("GL_AMD_transform_feedback3_lines_triangles");
/* 5706:5706 */    this.GL_AMD_vertex_shader_layer = supported_extensions.contains("GL_AMD_vertex_shader_layer");
/* 5707:5707 */    this.GL_AMD_vertex_shader_tessellator = supported_extensions.contains("GL_AMD_vertex_shader_tessellator");
/* 5708:5708 */    this.GL_AMD_vertex_shader_viewport_index = supported_extensions.contains("GL_AMD_vertex_shader_viewport_index");
/* 5709:5709 */    this.GL_APPLE_aux_depth_stencil = supported_extensions.contains("GL_APPLE_aux_depth_stencil");
/* 5710:5710 */    this.GL_APPLE_client_storage = supported_extensions.contains("GL_APPLE_client_storage");
/* 5711:5711 */    this.GL_APPLE_element_array = supported_extensions.contains("GL_APPLE_element_array");
/* 5712:5712 */    this.GL_APPLE_fence = supported_extensions.contains("GL_APPLE_fence");
/* 5713:5713 */    this.GL_APPLE_float_pixels = supported_extensions.contains("GL_APPLE_float_pixels");
/* 5714:5714 */    this.GL_APPLE_flush_buffer_range = supported_extensions.contains("GL_APPLE_flush_buffer_range");
/* 5715:5715 */    this.GL_APPLE_object_purgeable = supported_extensions.contains("GL_APPLE_object_purgeable");
/* 5716:5716 */    this.GL_APPLE_packed_pixels = supported_extensions.contains("GL_APPLE_packed_pixels");
/* 5717:5717 */    this.GL_APPLE_rgb_422 = supported_extensions.contains("GL_APPLE_rgb_422");
/* 5718:5718 */    this.GL_APPLE_row_bytes = supported_extensions.contains("GL_APPLE_row_bytes");
/* 5719:5719 */    this.GL_APPLE_texture_range = supported_extensions.contains("GL_APPLE_texture_range");
/* 5720:5720 */    this.GL_APPLE_vertex_array_object = supported_extensions.contains("GL_APPLE_vertex_array_object");
/* 5721:5721 */    this.GL_APPLE_vertex_array_range = supported_extensions.contains("GL_APPLE_vertex_array_range");
/* 5722:5722 */    this.GL_APPLE_vertex_program_evaluators = supported_extensions.contains("GL_APPLE_vertex_program_evaluators");
/* 5723:5723 */    this.GL_APPLE_ycbcr_422 = supported_extensions.contains("GL_APPLE_ycbcr_422");
/* 5724:5724 */    this.GL_ARB_ES2_compatibility = supported_extensions.contains("GL_ARB_ES2_compatibility");
/* 5725:5725 */    this.GL_ARB_ES3_compatibility = supported_extensions.contains("GL_ARB_ES3_compatibility");
/* 5726:5726 */    this.GL_ARB_arrays_of_arrays = supported_extensions.contains("GL_ARB_arrays_of_arrays");
/* 5727:5727 */    this.GL_ARB_base_instance = supported_extensions.contains("GL_ARB_base_instance");
/* 5728:5728 */    this.GL_ARB_blend_func_extended = supported_extensions.contains("GL_ARB_blend_func_extended");
/* 5729:5729 */    this.GL_ARB_cl_event = supported_extensions.contains("GL_ARB_cl_event");
/* 5730:5730 */    this.GL_ARB_clear_buffer_object = supported_extensions.contains("GL_ARB_clear_buffer_object");
/* 5731:5731 */    this.GL_ARB_color_buffer_float = supported_extensions.contains("GL_ARB_color_buffer_float");
/* 5732:5732 */    this.GL_ARB_compatibility = supported_extensions.contains("GL_ARB_compatibility");
/* 5733:5733 */    this.GL_ARB_compressed_texture_pixel_storage = supported_extensions.contains("GL_ARB_compressed_texture_pixel_storage");
/* 5734:5734 */    this.GL_ARB_compute_shader = supported_extensions.contains("GL_ARB_compute_shader");
/* 5735:5735 */    this.GL_ARB_conservative_depth = supported_extensions.contains("GL_ARB_conservative_depth");
/* 5736:5736 */    this.GL_ARB_copy_buffer = supported_extensions.contains("GL_ARB_copy_buffer");
/* 5737:5737 */    this.GL_ARB_copy_image = supported_extensions.contains("GL_ARB_copy_image");
/* 5738:5738 */    this.GL_ARB_debug_output = supported_extensions.contains("GL_ARB_debug_output");
/* 5739:5739 */    this.GL_ARB_depth_buffer_float = supported_extensions.contains("GL_ARB_depth_buffer_float");
/* 5740:5740 */    this.GL_ARB_depth_clamp = supported_extensions.contains("GL_ARB_depth_clamp");
/* 5741:5741 */    this.GL_ARB_depth_texture = supported_extensions.contains("GL_ARB_depth_texture");
/* 5742:5742 */    this.GL_ARB_draw_buffers = supported_extensions.contains("GL_ARB_draw_buffers");
/* 5743:5743 */    this.GL_ARB_draw_buffers_blend = supported_extensions.contains("GL_ARB_draw_buffers_blend");
/* 5744:5744 */    this.GL_ARB_draw_elements_base_vertex = supported_extensions.contains("GL_ARB_draw_elements_base_vertex");
/* 5745:5745 */    this.GL_ARB_draw_indirect = supported_extensions.contains("GL_ARB_draw_indirect");
/* 5746:5746 */    this.GL_ARB_draw_instanced = supported_extensions.contains("GL_ARB_draw_instanced");
/* 5747:5747 */    this.GL_ARB_explicit_attrib_location = supported_extensions.contains("GL_ARB_explicit_attrib_location");
/* 5748:5748 */    this.GL_ARB_explicit_uniform_location = supported_extensions.contains("GL_ARB_explicit_uniform_location");
/* 5749:5749 */    this.GL_ARB_fragment_coord_conventions = supported_extensions.contains("GL_ARB_fragment_coord_conventions");
/* 5750:5750 */    this.GL_ARB_fragment_layer_viewport = supported_extensions.contains("GL_ARB_fragment_layer_viewport");
/* 5751:5751 */    this.GL_ARB_fragment_program = ((supported_extensions.contains("GL_ARB_fragment_program")) && (supported_extensions.contains("GL_ARB_program")));
/* 5752:     */    
/* 5753:5753 */    this.GL_ARB_fragment_program_shadow = supported_extensions.contains("GL_ARB_fragment_program_shadow");
/* 5754:5754 */    this.GL_ARB_fragment_shader = supported_extensions.contains("GL_ARB_fragment_shader");
/* 5755:5755 */    this.GL_ARB_framebuffer_no_attachments = supported_extensions.contains("GL_ARB_framebuffer_no_attachments");
/* 5756:5756 */    this.GL_ARB_framebuffer_object = supported_extensions.contains("GL_ARB_framebuffer_object");
/* 5757:5757 */    this.GL_ARB_framebuffer_sRGB = supported_extensions.contains("GL_ARB_framebuffer_sRGB");
/* 5758:5758 */    this.GL_ARB_geometry_shader4 = supported_extensions.contains("GL_ARB_geometry_shader4");
/* 5759:5759 */    this.GL_ARB_get_program_binary = supported_extensions.contains("GL_ARB_get_program_binary");
/* 5760:5760 */    this.GL_ARB_gpu_shader5 = supported_extensions.contains("GL_ARB_gpu_shader5");
/* 5761:5761 */    this.GL_ARB_gpu_shader_fp64 = supported_extensions.contains("GL_ARB_gpu_shader_fp64");
/* 5762:5762 */    this.GL_ARB_half_float_pixel = supported_extensions.contains("GL_ARB_half_float_pixel");
/* 5763:5763 */    this.GL_ARB_half_float_vertex = supported_extensions.contains("GL_ARB_half_float_vertex");
/* 5764:5764 */    this.GL_ARB_imaging = supported_extensions.contains("GL_ARB_imaging");
/* 5765:5765 */    this.GL_ARB_instanced_arrays = supported_extensions.contains("GL_ARB_instanced_arrays");
/* 5766:5766 */    this.GL_ARB_internalformat_query = supported_extensions.contains("GL_ARB_internalformat_query");
/* 5767:5767 */    this.GL_ARB_internalformat_query2 = supported_extensions.contains("GL_ARB_internalformat_query2");
/* 5768:5768 */    this.GL_ARB_invalidate_subdata = supported_extensions.contains("GL_ARB_invalidate_subdata");
/* 5769:5769 */    this.GL_ARB_map_buffer_alignment = supported_extensions.contains("GL_ARB_map_buffer_alignment");
/* 5770:5770 */    this.GL_ARB_map_buffer_range = supported_extensions.contains("GL_ARB_map_buffer_range");
/* 5771:5771 */    this.GL_ARB_matrix_palette = supported_extensions.contains("GL_ARB_matrix_palette");
/* 5772:5772 */    this.GL_ARB_multi_draw_indirect = supported_extensions.contains("GL_ARB_multi_draw_indirect");
/* 5773:5773 */    this.GL_ARB_multisample = supported_extensions.contains("GL_ARB_multisample");
/* 5774:5774 */    this.GL_ARB_multitexture = supported_extensions.contains("GL_ARB_multitexture");
/* 5775:5775 */    this.GL_ARB_occlusion_query = supported_extensions.contains("GL_ARB_occlusion_query");
/* 5776:5776 */    this.GL_ARB_occlusion_query2 = supported_extensions.contains("GL_ARB_occlusion_query2");
/* 5777:5777 */    this.GL_ARB_pixel_buffer_object = ((supported_extensions.contains("GL_ARB_pixel_buffer_object")) && (supported_extensions.contains("GL_ARB_buffer_object")));
/* 5778:     */    
/* 5779:5779 */    this.GL_ARB_point_parameters = supported_extensions.contains("GL_ARB_point_parameters");
/* 5780:5780 */    this.GL_ARB_point_sprite = supported_extensions.contains("GL_ARB_point_sprite");
/* 5781:5781 */    this.GL_ARB_program_interface_query = supported_extensions.contains("GL_ARB_program_interface_query");
/* 5782:5782 */    this.GL_ARB_provoking_vertex = supported_extensions.contains("GL_ARB_provoking_vertex");
/* 5783:5783 */    this.GL_ARB_robust_buffer_access_behavior = supported_extensions.contains("GL_ARB_robust_buffer_access_behavior");
/* 5784:5784 */    this.GL_ARB_robustness = supported_extensions.contains("GL_ARB_robustness");
/* 5785:5785 */    this.GL_ARB_robustness_isolation = supported_extensions.contains("GL_ARB_robustness_isolation");
/* 5786:5786 */    this.GL_ARB_sample_shading = supported_extensions.contains("GL_ARB_sample_shading");
/* 5787:5787 */    this.GL_ARB_sampler_objects = supported_extensions.contains("GL_ARB_sampler_objects");
/* 5788:5788 */    this.GL_ARB_seamless_cube_map = supported_extensions.contains("GL_ARB_seamless_cube_map");
/* 5789:5789 */    this.GL_ARB_separate_shader_objects = supported_extensions.contains("GL_ARB_separate_shader_objects");
/* 5790:5790 */    this.GL_ARB_shader_atomic_counters = supported_extensions.contains("GL_ARB_shader_atomic_counters");
/* 5791:5791 */    this.GL_ARB_shader_bit_encoding = supported_extensions.contains("GL_ARB_shader_bit_encoding");
/* 5792:5792 */    this.GL_ARB_shader_image_load_store = supported_extensions.contains("GL_ARB_shader_image_load_store");
/* 5793:5793 */    this.GL_ARB_shader_image_size = supported_extensions.contains("GL_ARB_shader_image_size");
/* 5794:5794 */    this.GL_ARB_shader_objects = supported_extensions.contains("GL_ARB_shader_objects");
/* 5795:5795 */    this.GL_ARB_shader_precision = supported_extensions.contains("GL_ARB_shader_precision");
/* 5796:5796 */    this.GL_ARB_shader_stencil_export = supported_extensions.contains("GL_ARB_shader_stencil_export");
/* 5797:5797 */    this.GL_ARB_shader_storage_buffer_object = supported_extensions.contains("GL_ARB_shader_storage_buffer_object");
/* 5798:5798 */    this.GL_ARB_shader_subroutine = supported_extensions.contains("GL_ARB_shader_subroutine");
/* 5799:5799 */    this.GL_ARB_shader_texture_lod = supported_extensions.contains("GL_ARB_shader_texture_lod");
/* 5800:5800 */    this.GL_ARB_shading_language_100 = supported_extensions.contains("GL_ARB_shading_language_100");
/* 5801:5801 */    this.GL_ARB_shading_language_420pack = supported_extensions.contains("GL_ARB_shading_language_420pack");
/* 5802:5802 */    this.GL_ARB_shading_language_include = supported_extensions.contains("GL_ARB_shading_language_include");
/* 5803:5803 */    this.GL_ARB_shading_language_packing = supported_extensions.contains("GL_ARB_shading_language_packing");
/* 5804:5804 */    this.GL_ARB_shadow = supported_extensions.contains("GL_ARB_shadow");
/* 5805:5805 */    this.GL_ARB_shadow_ambient = supported_extensions.contains("GL_ARB_shadow_ambient");
/* 5806:5806 */    this.GL_ARB_stencil_texturing = supported_extensions.contains("GL_ARB_stencil_texturing");
/* 5807:5807 */    this.GL_ARB_sync = supported_extensions.contains("GL_ARB_sync");
/* 5808:5808 */    this.GL_ARB_tessellation_shader = supported_extensions.contains("GL_ARB_tessellation_shader");
/* 5809:5809 */    this.GL_ARB_texture_border_clamp = supported_extensions.contains("GL_ARB_texture_border_clamp");
/* 5810:5810 */    this.GL_ARB_texture_buffer_object = supported_extensions.contains("GL_ARB_texture_buffer_object");
/* 5811:5811 */    this.GL_ARB_texture_buffer_object_rgb32 = ((supported_extensions.contains("GL_ARB_texture_buffer_object_rgb32")) || (supported_extensions.contains("GL_EXT_texture_buffer_object_rgb32")));
/* 5812:     */    
/* 5813:5813 */    this.GL_ARB_texture_buffer_range = supported_extensions.contains("GL_ARB_texture_buffer_range");
/* 5814:5814 */    this.GL_ARB_texture_compression = supported_extensions.contains("GL_ARB_texture_compression");
/* 5815:5815 */    this.GL_ARB_texture_compression_bptc = ((supported_extensions.contains("GL_ARB_texture_compression_bptc")) || (supported_extensions.contains("GL_EXT_texture_compression_bptc")));
/* 5816:     */    
/* 5817:5817 */    this.GL_ARB_texture_compression_rgtc = supported_extensions.contains("GL_ARB_texture_compression_rgtc");
/* 5818:5818 */    this.GL_ARB_texture_cube_map = supported_extensions.contains("GL_ARB_texture_cube_map");
/* 5819:5819 */    this.GL_ARB_texture_cube_map_array = supported_extensions.contains("GL_ARB_texture_cube_map_array");
/* 5820:5820 */    this.GL_ARB_texture_env_add = supported_extensions.contains("GL_ARB_texture_env_add");
/* 5821:5821 */    this.GL_ARB_texture_env_combine = supported_extensions.contains("GL_ARB_texture_env_combine");
/* 5822:5822 */    this.GL_ARB_texture_env_crossbar = supported_extensions.contains("GL_ARB_texture_env_crossbar");
/* 5823:5823 */    this.GL_ARB_texture_env_dot3 = supported_extensions.contains("GL_ARB_texture_env_dot3");
/* 5824:5824 */    this.GL_ARB_texture_float = supported_extensions.contains("GL_ARB_texture_float");
/* 5825:5825 */    this.GL_ARB_texture_gather = supported_extensions.contains("GL_ARB_texture_gather");
/* 5826:5826 */    this.GL_ARB_texture_mirrored_repeat = supported_extensions.contains("GL_ARB_texture_mirrored_repeat");
/* 5827:5827 */    this.GL_ARB_texture_multisample = supported_extensions.contains("GL_ARB_texture_multisample");
/* 5828:5828 */    this.GL_ARB_texture_non_power_of_two = supported_extensions.contains("GL_ARB_texture_non_power_of_two");
/* 5829:5829 */    this.GL_ARB_texture_query_levels = supported_extensions.contains("GL_ARB_texture_query_levels");
/* 5830:5830 */    this.GL_ARB_texture_query_lod = supported_extensions.contains("GL_ARB_texture_query_lod");
/* 5831:5831 */    this.GL_ARB_texture_rectangle = supported_extensions.contains("GL_ARB_texture_rectangle");
/* 5832:5832 */    this.GL_ARB_texture_rg = supported_extensions.contains("GL_ARB_texture_rg");
/* 5833:5833 */    this.GL_ARB_texture_rgb10_a2ui = supported_extensions.contains("GL_ARB_texture_rgb10_a2ui");
/* 5834:5834 */    this.GL_ARB_texture_storage = ((supported_extensions.contains("GL_ARB_texture_storage")) || (supported_extensions.contains("GL_EXT_texture_storage")));
/* 5835:     */    
/* 5836:5836 */    this.GL_ARB_texture_storage_multisample = supported_extensions.contains("GL_ARB_texture_storage_multisample");
/* 5837:5837 */    this.GL_ARB_texture_swizzle = supported_extensions.contains("GL_ARB_texture_swizzle");
/* 5838:5838 */    this.GL_ARB_texture_view = supported_extensions.contains("GL_ARB_texture_view");
/* 5839:5839 */    this.GL_ARB_timer_query = supported_extensions.contains("GL_ARB_timer_query");
/* 5840:5840 */    this.GL_ARB_transform_feedback2 = supported_extensions.contains("GL_ARB_transform_feedback2");
/* 5841:5841 */    this.GL_ARB_transform_feedback3 = supported_extensions.contains("GL_ARB_transform_feedback3");
/* 5842:5842 */    this.GL_ARB_transform_feedback_instanced = supported_extensions.contains("GL_ARB_transform_feedback_instanced");
/* 5843:5843 */    this.GL_ARB_transpose_matrix = supported_extensions.contains("GL_ARB_transpose_matrix");
/* 5844:5844 */    this.GL_ARB_uniform_buffer_object = supported_extensions.contains("GL_ARB_uniform_buffer_object");
/* 5845:5845 */    this.GL_ARB_vertex_array_bgra = supported_extensions.contains("GL_ARB_vertex_array_bgra");
/* 5846:5846 */    this.GL_ARB_vertex_array_object = supported_extensions.contains("GL_ARB_vertex_array_object");
/* 5847:5847 */    this.GL_ARB_vertex_attrib_64bit = supported_extensions.contains("GL_ARB_vertex_attrib_64bit");
/* 5848:5848 */    this.GL_ARB_vertex_attrib_binding = supported_extensions.contains("GL_ARB_vertex_attrib_binding");
/* 5849:5849 */    this.GL_ARB_vertex_blend = supported_extensions.contains("GL_ARB_vertex_blend");
/* 5850:5850 */    this.GL_ARB_vertex_buffer_object = ((supported_extensions.contains("GL_ARB_vertex_buffer_object")) && (supported_extensions.contains("GL_ARB_buffer_object")));
/* 5851:     */    
/* 5852:5852 */    this.GL_ARB_vertex_program = ((supported_extensions.contains("GL_ARB_vertex_program")) && (supported_extensions.contains("GL_ARB_program")));
/* 5853:     */    
/* 5854:5854 */    this.GL_ARB_vertex_shader = supported_extensions.contains("GL_ARB_vertex_shader");
/* 5855:5855 */    this.GL_ARB_vertex_type_2_10_10_10_rev = supported_extensions.contains("GL_ARB_vertex_type_2_10_10_10_rev");
/* 5856:5856 */    this.GL_ARB_viewport_array = supported_extensions.contains("GL_ARB_viewport_array");
/* 5857:5857 */    this.GL_ARB_window_pos = supported_extensions.contains("GL_ARB_window_pos");
/* 5858:5858 */    this.GL_ATI_draw_buffers = supported_extensions.contains("GL_ATI_draw_buffers");
/* 5859:5859 */    this.GL_ATI_element_array = supported_extensions.contains("GL_ATI_element_array");
/* 5860:5860 */    this.GL_ATI_envmap_bumpmap = supported_extensions.contains("GL_ATI_envmap_bumpmap");
/* 5861:5861 */    this.GL_ATI_fragment_shader = supported_extensions.contains("GL_ATI_fragment_shader");
/* 5862:5862 */    this.GL_ATI_map_object_buffer = supported_extensions.contains("GL_ATI_map_object_buffer");
/* 5863:5863 */    this.GL_ATI_meminfo = supported_extensions.contains("GL_ATI_meminfo");
/* 5864:5864 */    this.GL_ATI_pn_triangles = supported_extensions.contains("GL_ATI_pn_triangles");
/* 5865:5865 */    this.GL_ATI_separate_stencil = supported_extensions.contains("GL_ATI_separate_stencil");
/* 5866:5866 */    this.GL_ATI_shader_texture_lod = supported_extensions.contains("GL_ATI_shader_texture_lod");
/* 5867:5867 */    this.GL_ATI_text_fragment_shader = supported_extensions.contains("GL_ATI_text_fragment_shader");
/* 5868:5868 */    this.GL_ATI_texture_compression_3dc = supported_extensions.contains("GL_ATI_texture_compression_3dc");
/* 5869:5869 */    this.GL_ATI_texture_env_combine3 = supported_extensions.contains("GL_ATI_texture_env_combine3");
/* 5870:5870 */    this.GL_ATI_texture_float = supported_extensions.contains("GL_ATI_texture_float");
/* 5871:5871 */    this.GL_ATI_texture_mirror_once = supported_extensions.contains("GL_ATI_texture_mirror_once");
/* 5872:5872 */    this.GL_ATI_vertex_array_object = supported_extensions.contains("GL_ATI_vertex_array_object");
/* 5873:5873 */    this.GL_ATI_vertex_attrib_array_object = supported_extensions.contains("GL_ATI_vertex_attrib_array_object");
/* 5874:5874 */    this.GL_ATI_vertex_streams = supported_extensions.contains("GL_ATI_vertex_streams");
/* 5875:5875 */    this.GL_EXT_abgr = supported_extensions.contains("GL_EXT_abgr");
/* 5876:5876 */    this.GL_EXT_bgra = supported_extensions.contains("GL_EXT_bgra");
/* 5877:5877 */    this.GL_EXT_bindable_uniform = supported_extensions.contains("GL_EXT_bindable_uniform");
/* 5878:5878 */    this.GL_EXT_blend_color = supported_extensions.contains("GL_EXT_blend_color");
/* 5879:5879 */    this.GL_EXT_blend_equation_separate = supported_extensions.contains("GL_EXT_blend_equation_separate");
/* 5880:5880 */    this.GL_EXT_blend_func_separate = supported_extensions.contains("GL_EXT_blend_func_separate");
/* 5881:5881 */    this.GL_EXT_blend_minmax = supported_extensions.contains("GL_EXT_blend_minmax");
/* 5882:5882 */    this.GL_EXT_blend_subtract = supported_extensions.contains("GL_EXT_blend_subtract");
/* 5883:5883 */    this.GL_EXT_Cg_shader = supported_extensions.contains("GL_EXT_Cg_shader");
/* 5884:5884 */    this.GL_EXT_compiled_vertex_array = supported_extensions.contains("GL_EXT_compiled_vertex_array");
/* 5885:5885 */    this.GL_EXT_depth_bounds_test = supported_extensions.contains("GL_EXT_depth_bounds_test");
/* 5886:5886 */    this.GL_EXT_direct_state_access = supported_extensions.contains("GL_EXT_direct_state_access");
/* 5887:5887 */    this.GL_EXT_draw_buffers2 = supported_extensions.contains("GL_EXT_draw_buffers2");
/* 5888:5888 */    this.GL_EXT_draw_instanced = supported_extensions.contains("GL_EXT_draw_instanced");
/* 5889:5889 */    this.GL_EXT_draw_range_elements = supported_extensions.contains("GL_EXT_draw_range_elements");
/* 5890:5890 */    this.GL_EXT_fog_coord = supported_extensions.contains("GL_EXT_fog_coord");
/* 5891:5891 */    this.GL_EXT_framebuffer_blit = supported_extensions.contains("GL_EXT_framebuffer_blit");
/* 5892:5892 */    this.GL_EXT_framebuffer_multisample = supported_extensions.contains("GL_EXT_framebuffer_multisample");
/* 5893:5893 */    this.GL_EXT_framebuffer_multisample_blit_scaled = supported_extensions.contains("GL_EXT_framebuffer_multisample_blit_scaled");
/* 5894:5894 */    this.GL_EXT_framebuffer_object = supported_extensions.contains("GL_EXT_framebuffer_object");
/* 5895:5895 */    this.GL_EXT_framebuffer_sRGB = supported_extensions.contains("GL_EXT_framebuffer_sRGB");
/* 5896:5896 */    this.GL_EXT_geometry_shader4 = supported_extensions.contains("GL_EXT_geometry_shader4");
/* 5897:5897 */    this.GL_EXT_gpu_program_parameters = supported_extensions.contains("GL_EXT_gpu_program_parameters");
/* 5898:5898 */    this.GL_EXT_gpu_shader4 = supported_extensions.contains("GL_EXT_gpu_shader4");
/* 5899:5899 */    this.GL_EXT_multi_draw_arrays = supported_extensions.contains("GL_EXT_multi_draw_arrays");
/* 5900:5900 */    this.GL_EXT_packed_depth_stencil = supported_extensions.contains("GL_EXT_packed_depth_stencil");
/* 5901:5901 */    this.GL_EXT_packed_float = supported_extensions.contains("GL_EXT_packed_float");
/* 5902:5902 */    this.GL_EXT_packed_pixels = supported_extensions.contains("GL_EXT_packed_pixels");
/* 5903:5903 */    this.GL_EXT_paletted_texture = supported_extensions.contains("GL_EXT_paletted_texture");
/* 5904:5904 */    this.GL_EXT_pixel_buffer_object = ((supported_extensions.contains("GL_EXT_pixel_buffer_object")) && (supported_extensions.contains("GL_ARB_buffer_object")));
/* 5905:     */    
/* 5906:5906 */    this.GL_EXT_point_parameters = supported_extensions.contains("GL_EXT_point_parameters");
/* 5907:5907 */    this.GL_EXT_provoking_vertex = supported_extensions.contains("GL_EXT_provoking_vertex");
/* 5908:5908 */    this.GL_EXT_rescale_normal = supported_extensions.contains("GL_EXT_rescale_normal");
/* 5909:5909 */    this.GL_EXT_secondary_color = supported_extensions.contains("GL_EXT_secondary_color");
/* 5910:5910 */    this.GL_EXT_separate_shader_objects = supported_extensions.contains("GL_EXT_separate_shader_objects");
/* 5911:5911 */    this.GL_EXT_separate_specular_color = supported_extensions.contains("GL_EXT_separate_specular_color");
/* 5912:5912 */    this.GL_EXT_shader_image_load_store = supported_extensions.contains("GL_EXT_shader_image_load_store");
/* 5913:5913 */    this.GL_EXT_shadow_funcs = supported_extensions.contains("GL_EXT_shadow_funcs");
/* 5914:5914 */    this.GL_EXT_shared_texture_palette = supported_extensions.contains("GL_EXT_shared_texture_palette");
/* 5915:5915 */    this.GL_EXT_stencil_clear_tag = supported_extensions.contains("GL_EXT_stencil_clear_tag");
/* 5916:5916 */    this.GL_EXT_stencil_two_side = supported_extensions.contains("GL_EXT_stencil_two_side");
/* 5917:5917 */    this.GL_EXT_stencil_wrap = supported_extensions.contains("GL_EXT_stencil_wrap");
/* 5918:5918 */    this.GL_EXT_texture_3d = supported_extensions.contains("GL_EXT_texture_3d");
/* 5919:5919 */    this.GL_EXT_texture_array = supported_extensions.contains("GL_EXT_texture_array");
/* 5920:5920 */    this.GL_EXT_texture_buffer_object = supported_extensions.contains("GL_EXT_texture_buffer_object");
/* 5921:5921 */    this.GL_EXT_texture_compression_latc = supported_extensions.contains("GL_EXT_texture_compression_latc");
/* 5922:5922 */    this.GL_EXT_texture_compression_rgtc = supported_extensions.contains("GL_EXT_texture_compression_rgtc");
/* 5923:5923 */    this.GL_EXT_texture_compression_s3tc = supported_extensions.contains("GL_EXT_texture_compression_s3tc");
/* 5924:5924 */    this.GL_EXT_texture_env_combine = supported_extensions.contains("GL_EXT_texture_env_combine");
/* 5925:5925 */    this.GL_EXT_texture_env_dot3 = supported_extensions.contains("GL_EXT_texture_env_dot3");
/* 5926:5926 */    this.GL_EXT_texture_filter_anisotropic = supported_extensions.contains("GL_EXT_texture_filter_anisotropic");
/* 5927:5927 */    this.GL_EXT_texture_integer = supported_extensions.contains("GL_EXT_texture_integer");
/* 5928:5928 */    this.GL_EXT_texture_lod_bias = supported_extensions.contains("GL_EXT_texture_lod_bias");
/* 5929:5929 */    this.GL_EXT_texture_mirror_clamp = supported_extensions.contains("GL_EXT_texture_mirror_clamp");
/* 5930:5930 */    this.GL_EXT_texture_rectangle = supported_extensions.contains("GL_EXT_texture_rectangle");
/* 5931:5931 */    this.GL_EXT_texture_sRGB = supported_extensions.contains("GL_EXT_texture_sRGB");
/* 5932:5932 */    this.GL_EXT_texture_sRGB_decode = supported_extensions.contains("GL_EXT_texture_sRGB_decode");
/* 5933:5933 */    this.GL_EXT_texture_shared_exponent = supported_extensions.contains("GL_EXT_texture_shared_exponent");
/* 5934:5934 */    this.GL_EXT_texture_snorm = supported_extensions.contains("GL_EXT_texture_snorm");
/* 5935:5935 */    this.GL_EXT_texture_swizzle = supported_extensions.contains("GL_EXT_texture_swizzle");
/* 5936:5936 */    this.GL_EXT_timer_query = supported_extensions.contains("GL_EXT_timer_query");
/* 5937:5937 */    this.GL_EXT_transform_feedback = supported_extensions.contains("GL_EXT_transform_feedback");
/* 5938:5938 */    this.GL_EXT_vertex_array_bgra = supported_extensions.contains("GL_EXT_vertex_array_bgra");
/* 5939:5939 */    this.GL_EXT_vertex_attrib_64bit = supported_extensions.contains("GL_EXT_vertex_attrib_64bit");
/* 5940:5940 */    this.GL_EXT_vertex_shader = supported_extensions.contains("GL_EXT_vertex_shader");
/* 5941:5941 */    this.GL_EXT_vertex_weighting = supported_extensions.contains("GL_EXT_vertex_weighting");
/* 5942:5942 */    this.OpenGL11 = supported_extensions.contains("OpenGL11");
/* 5943:5943 */    this.OpenGL12 = supported_extensions.contains("OpenGL12");
/* 5944:5944 */    this.OpenGL13 = supported_extensions.contains("OpenGL13");
/* 5945:5945 */    this.OpenGL14 = supported_extensions.contains("OpenGL14");
/* 5946:5946 */    this.OpenGL15 = supported_extensions.contains("OpenGL15");
/* 5947:5947 */    this.OpenGL20 = supported_extensions.contains("OpenGL20");
/* 5948:5948 */    this.OpenGL21 = supported_extensions.contains("OpenGL21");
/* 5949:5949 */    this.OpenGL30 = supported_extensions.contains("OpenGL30");
/* 5950:5950 */    this.OpenGL31 = supported_extensions.contains("OpenGL31");
/* 5951:5951 */    this.OpenGL32 = supported_extensions.contains("OpenGL32");
/* 5952:5952 */    this.OpenGL33 = supported_extensions.contains("OpenGL33");
/* 5953:5953 */    this.OpenGL40 = supported_extensions.contains("OpenGL40");
/* 5954:5954 */    this.OpenGL41 = supported_extensions.contains("OpenGL41");
/* 5955:5955 */    this.OpenGL42 = supported_extensions.contains("OpenGL42");
/* 5956:5956 */    this.OpenGL43 = supported_extensions.contains("OpenGL43");
/* 5957:5957 */    this.GL_GREMEDY_frame_terminator = supported_extensions.contains("GL_GREMEDY_frame_terminator");
/* 5958:5958 */    this.GL_GREMEDY_string_marker = supported_extensions.contains("GL_GREMEDY_string_marker");
/* 5959:5959 */    this.GL_HP_occlusion_test = supported_extensions.contains("GL_HP_occlusion_test");
/* 5960:5960 */    this.GL_IBM_rasterpos_clip = supported_extensions.contains("GL_IBM_rasterpos_clip");
/* 5961:5961 */    this.GL_INTEL_map_texture = supported_extensions.contains("GL_INTEL_map_texture");
/* 5962:5962 */    this.GL_KHR_debug = supported_extensions.contains("GL_KHR_debug");
/* 5963:5963 */    this.GL_KHR_texture_compression_astc_ldr = supported_extensions.contains("GL_KHR_texture_compression_astc_ldr");
/* 5964:5964 */    this.GL_NVX_gpu_memory_info = supported_extensions.contains("GL_NVX_gpu_memory_info");
/* 5965:5965 */    this.GL_NV_bindless_texture = supported_extensions.contains("GL_NV_bindless_texture");
/* 5966:5966 */    this.GL_NV_blend_square = supported_extensions.contains("GL_NV_blend_square");
/* 5967:5967 */    this.GL_NV_compute_program5 = supported_extensions.contains("GL_NV_compute_program5");
/* 5968:5968 */    this.GL_NV_conditional_render = supported_extensions.contains("GL_NV_conditional_render");
/* 5969:5969 */    this.GL_NV_copy_depth_to_color = supported_extensions.contains("GL_NV_copy_depth_to_color");
/* 5970:5970 */    this.GL_NV_copy_image = supported_extensions.contains("GL_NV_copy_image");
/* 5971:5971 */    this.GL_NV_deep_texture3D = supported_extensions.contains("GL_NV_deep_texture3D");
/* 5972:5972 */    this.GL_NV_depth_buffer_float = supported_extensions.contains("GL_NV_depth_buffer_float");
/* 5973:5973 */    this.GL_NV_depth_clamp = supported_extensions.contains("GL_NV_depth_clamp");
/* 5974:5974 */    this.GL_NV_draw_texture = supported_extensions.contains("GL_NV_draw_texture");
/* 5975:5975 */    this.GL_NV_evaluators = supported_extensions.contains("GL_NV_evaluators");
/* 5976:5976 */    this.GL_NV_explicit_multisample = supported_extensions.contains("GL_NV_explicit_multisample");
/* 5977:5977 */    this.GL_NV_fence = supported_extensions.contains("GL_NV_fence");
/* 5978:5978 */    this.GL_NV_float_buffer = supported_extensions.contains("GL_NV_float_buffer");
/* 5979:5979 */    this.GL_NV_fog_distance = supported_extensions.contains("GL_NV_fog_distance");
/* 5980:5980 */    this.GL_NV_fragment_program = ((supported_extensions.contains("GL_NV_fragment_program")) && (supported_extensions.contains("GL_NV_program")));
/* 5981:     */    
/* 5982:5982 */    this.GL_NV_fragment_program2 = supported_extensions.contains("GL_NV_fragment_program2");
/* 5983:5983 */    this.GL_NV_fragment_program4 = supported_extensions.contains("GL_NV_fragment_program4");
/* 5984:5984 */    this.GL_NV_fragment_program_option = supported_extensions.contains("GL_NV_fragment_program_option");
/* 5985:5985 */    this.GL_NV_framebuffer_multisample_coverage = supported_extensions.contains("GL_NV_framebuffer_multisample_coverage");
/* 5986:5986 */    this.GL_NV_geometry_program4 = supported_extensions.contains("GL_NV_geometry_program4");
/* 5987:5987 */    this.GL_NV_geometry_shader4 = supported_extensions.contains("GL_NV_geometry_shader4");
/* 5988:5988 */    this.GL_NV_gpu_program4 = supported_extensions.contains("GL_NV_gpu_program4");
/* 5989:5989 */    this.GL_NV_gpu_program5 = supported_extensions.contains("GL_NV_gpu_program5");
/* 5990:5990 */    this.GL_NV_gpu_shader5 = supported_extensions.contains("GL_NV_gpu_shader5");
/* 5991:5991 */    this.GL_NV_half_float = supported_extensions.contains("GL_NV_half_float");
/* 5992:5992 */    this.GL_NV_light_max_exponent = supported_extensions.contains("GL_NV_light_max_exponent");
/* 5993:5993 */    this.GL_NV_multisample_coverage = supported_extensions.contains("GL_NV_multisample_coverage");
/* 5994:5994 */    this.GL_NV_multisample_filter_hint = supported_extensions.contains("GL_NV_multisample_filter_hint");
/* 5995:5995 */    this.GL_NV_occlusion_query = supported_extensions.contains("GL_NV_occlusion_query");
/* 5996:5996 */    this.GL_NV_packed_depth_stencil = supported_extensions.contains("GL_NV_packed_depth_stencil");
/* 5997:5997 */    this.GL_NV_parameter_buffer_object = supported_extensions.contains("GL_NV_parameter_buffer_object");
/* 5998:5998 */    this.GL_NV_parameter_buffer_object2 = supported_extensions.contains("GL_NV_parameter_buffer_object2");
/* 5999:5999 */    this.GL_NV_path_rendering = supported_extensions.contains("GL_NV_path_rendering");
/* 6000:6000 */    this.GL_NV_pixel_data_range = supported_extensions.contains("GL_NV_pixel_data_range");
/* 6001:6001 */    this.GL_NV_point_sprite = supported_extensions.contains("GL_NV_point_sprite");
/* 6002:6002 */    this.GL_NV_present_video = supported_extensions.contains("GL_NV_present_video");
/* 6003:6003 */    this.GL_NV_primitive_restart = supported_extensions.contains("GL_NV_primitive_restart");
/* 6004:6004 */    this.GL_NV_register_combiners = supported_extensions.contains("GL_NV_register_combiners");
/* 6005:6005 */    this.GL_NV_register_combiners2 = supported_extensions.contains("GL_NV_register_combiners2");
/* 6006:6006 */    this.GL_NV_shader_atomic_counters = supported_extensions.contains("GL_NV_shader_atomic_counters");
/* 6007:6007 */    this.GL_NV_shader_atomic_float = supported_extensions.contains("GL_NV_shader_atomic_float");
/* 6008:6008 */    this.GL_NV_shader_buffer_load = supported_extensions.contains("GL_NV_shader_buffer_load");
/* 6009:6009 */    this.GL_NV_shader_buffer_store = supported_extensions.contains("GL_NV_shader_buffer_store");
/* 6010:6010 */    this.GL_NV_shader_storage_buffer_object = supported_extensions.contains("GL_NV_shader_storage_buffer_object");
/* 6011:6011 */    this.GL_NV_tessellation_program5 = supported_extensions.contains("GL_NV_tessellation_program5");
/* 6012:6012 */    this.GL_NV_texgen_reflection = supported_extensions.contains("GL_NV_texgen_reflection");
/* 6013:6013 */    this.GL_NV_texture_barrier = supported_extensions.contains("GL_NV_texture_barrier");
/* 6014:6014 */    this.GL_NV_texture_compression_vtc = supported_extensions.contains("GL_NV_texture_compression_vtc");
/* 6015:6015 */    this.GL_NV_texture_env_combine4 = supported_extensions.contains("GL_NV_texture_env_combine4");
/* 6016:6016 */    this.GL_NV_texture_expand_normal = supported_extensions.contains("GL_NV_texture_expand_normal");
/* 6017:6017 */    this.GL_NV_texture_multisample = supported_extensions.contains("GL_NV_texture_multisample");
/* 6018:6018 */    this.GL_NV_texture_rectangle = supported_extensions.contains("GL_NV_texture_rectangle");
/* 6019:6019 */    this.GL_NV_texture_shader = supported_extensions.contains("GL_NV_texture_shader");
/* 6020:6020 */    this.GL_NV_texture_shader2 = supported_extensions.contains("GL_NV_texture_shader2");
/* 6021:6021 */    this.GL_NV_texture_shader3 = supported_extensions.contains("GL_NV_texture_shader3");
/* 6022:6022 */    this.GL_NV_transform_feedback = supported_extensions.contains("GL_NV_transform_feedback");
/* 6023:6023 */    this.GL_NV_transform_feedback2 = supported_extensions.contains("GL_NV_transform_feedback2");
/* 6024:6024 */    this.GL_NV_vertex_array_range = supported_extensions.contains("GL_NV_vertex_array_range");
/* 6025:6025 */    this.GL_NV_vertex_array_range2 = supported_extensions.contains("GL_NV_vertex_array_range2");
/* 6026:6026 */    this.GL_NV_vertex_attrib_integer_64bit = supported_extensions.contains("GL_NV_vertex_attrib_integer_64bit");
/* 6027:6027 */    this.GL_NV_vertex_buffer_unified_memory = supported_extensions.contains("GL_NV_vertex_buffer_unified_memory");
/* 6028:6028 */    this.GL_NV_vertex_program = ((supported_extensions.contains("GL_NV_vertex_program")) && (supported_extensions.contains("GL_NV_program")));
/* 6029:     */    
/* 6030:6030 */    this.GL_NV_vertex_program1_1 = supported_extensions.contains("GL_NV_vertex_program1_1");
/* 6031:6031 */    this.GL_NV_vertex_program2 = supported_extensions.contains("GL_NV_vertex_program2");
/* 6032:6032 */    this.GL_NV_vertex_program2_option = supported_extensions.contains("GL_NV_vertex_program2_option");
/* 6033:6033 */    this.GL_NV_vertex_program3 = supported_extensions.contains("GL_NV_vertex_program3");
/* 6034:6034 */    this.GL_NV_vertex_program4 = supported_extensions.contains("GL_NV_vertex_program4");
/* 6035:6035 */    this.GL_NV_video_capture = supported_extensions.contains("GL_NV_video_capture");
/* 6036:6036 */    this.GL_SGIS_generate_mipmap = supported_extensions.contains("GL_SGIS_generate_mipmap");
/* 6037:6037 */    this.GL_SGIS_texture_lod = supported_extensions.contains("GL_SGIS_texture_lod");
/* 6038:6038 */    this.GL_SUN_slice_accum = supported_extensions.contains("GL_SUN_slice_accum");
/* 6039:6039 */    this.tracker.init();
/* 6040:     */  }
/* 6041:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ContextCapabilities
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */