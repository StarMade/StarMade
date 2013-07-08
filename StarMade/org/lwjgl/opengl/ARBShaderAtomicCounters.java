/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */
/* 43:   */public final class ARBShaderAtomicCounters
/* 44:   */{
/* 45:   */  public static final int GL_ATOMIC_COUNTER_BUFFER = 37568;
/* 46:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_BINDING = 37569;
/* 47:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_START = 37570;
/* 48:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_SIZE = 37571;
/* 49:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE = 37572;
/* 50:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS = 37573;
/* 51:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES = 37574;
/* 52:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER = 37575;
/* 53:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER = 37576;
/* 54:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER = 37577;
/* 55:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER = 37578;
/* 56:   */  public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER = 37579;
/* 57:   */  public static final int GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS = 37580;
/* 58:   */  public static final int GL_MAX_TESS_CONTROL_ATOMIC_COUNTER_BUFFERS = 37581;
/* 59:   */  public static final int GL_MAX_TESS_EVALUATION_ATOMIC_COUNTER_BUFFERS = 37582;
/* 60:   */  public static final int GL_MAX_GEOMETRY_ATOMIC_COUNTER_BUFFERS = 37583;
/* 61:   */  public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS = 37584;
/* 62:   */  public static final int GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS = 37585;
/* 63:   */  public static final int GL_MAX_VERTEX_ATOMIC_COUNTERS = 37586;
/* 64:   */  public static final int GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS = 37587;
/* 65:   */  public static final int GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS = 37588;
/* 66:   */  public static final int GL_MAX_GEOMETRY_ATOMIC_COUNTERS = 37589;
/* 67:   */  public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTERS = 37590;
/* 68:   */  public static final int GL_MAX_COMBINED_ATOMIC_COUNTERS = 37591;
/* 69:   */  public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE = 37592;
/* 70:   */  public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS = 37596;
/* 71:   */  public static final int GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 37593;
/* 72:   */  public static final int GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX = 37594;
/* 73:   */  public static final int GL_UNSIGNED_INT_ATOMIC_COUNTER = 37595;
/* 74:   */  
/* 75:   */  public static void glGetActiveAtomicCounterBuffer(int program, int bufferIndex, int pname, IntBuffer params)
/* 76:   */  {
/* 77:77 */    GL42.glGetActiveAtomicCounterBuffer(program, bufferIndex, pname, params);
/* 78:   */  }
/* 79:   */  
/* 80:   */  public static int glGetActiveAtomicCounterBuffer(int program, int bufferIndex, int pname)
/* 81:   */  {
/* 82:82 */    return GL42.glGetActiveAtomicCounterBuffer(program, bufferIndex, pname);
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShaderAtomicCounters
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */