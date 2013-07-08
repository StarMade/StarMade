/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  6:   */public final class ARBComputeShader
/*  7:   */{
/*  8:   */  public static final int GL_COMPUTE_SHADER = 37305;
/*  9:   */  
/* 12:   */  public static final int GL_MAX_COMPUTE_UNIFORM_BLOCKS = 37307;
/* 13:   */  
/* 16:   */  public static final int GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 37308;
/* 17:   */  
/* 20:   */  public static final int GL_MAX_COMPUTE_IMAGE_UNIFORMS = 37309;
/* 21:   */  
/* 24:   */  public static final int GL_MAX_COMPUTE_SHARED_MEMORY_SIZE = 33378;
/* 25:   */  
/* 28:   */  public static final int GL_MAX_COMPUTE_UNIFORM_COMPONENTS = 33379;
/* 29:   */  
/* 32:   */  public static final int GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 33380;
/* 33:   */  
/* 36:   */  public static final int GL_MAX_COMPUTE_ATOMIC_COUNTERS = 33381;
/* 37:   */  
/* 40:   */  public static final int GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 33382;
/* 41:   */  
/* 43:   */  public static final int GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 37099;
/* 44:   */  
/* 46:   */  public static final int GL_MAX_COMPUTE_WORK_GROUP_COUNT = 37310;
/* 47:   */  
/* 49:   */  public static final int GL_MAX_COMPUTE_WORK_GROUP_SIZE = 37311;
/* 50:   */  
/* 52:   */  public static final int GL_COMPUTE_WORK_GROUP_SIZE = 33383;
/* 53:   */  
/* 55:   */  public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 37100;
/* 56:   */  
/* 58:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 37101;
/* 59:   */  
/* 61:   */  public static final int GL_DISPATCH_INDIRECT_BUFFER = 37102;
/* 62:   */  
/* 64:   */  public static final int GL_DISPATCH_INDIRECT_BUFFER_BINDING = 37103;
/* 65:   */  
/* 67:   */  public static final int GL_COMPUTE_SHADER_BIT = 32;
/* 68:   */  
/* 71:   */  public static void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z)
/* 72:   */  {
/* 73:73 */    GL43.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z);
/* 74:   */  }
/* 75:   */  
/* 76:   */  public static void glDispatchComputeIndirect(long indirect) {
/* 77:77 */    GL43.glDispatchComputeIndirect(indirect);
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBComputeShader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */