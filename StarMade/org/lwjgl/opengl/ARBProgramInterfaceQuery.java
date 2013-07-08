/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */
/*  22:    */public final class ARBProgramInterfaceQuery
/*  23:    */{
/*  24:    */  public static final int GL_UNIFORM = 37601;
/*  25:    */  public static final int GL_UNIFORM_BLOCK = 37602;
/*  26:    */  public static final int GL_PROGRAM_INPUT = 37603;
/*  27:    */  public static final int GL_PROGRAM_OUTPUT = 37604;
/*  28:    */  public static final int GL_BUFFER_VARIABLE = 37605;
/*  29:    */  public static final int GL_SHADER_STORAGE_BLOCK = 37606;
/*  30:    */  public static final int GL_VERTEX_SUBROUTINE = 37608;
/*  31:    */  public static final int GL_TESS_CONTROL_SUBROUTINE = 37609;
/*  32:    */  public static final int GL_TESS_EVALUATION_SUBROUTINE = 37610;
/*  33:    */  public static final int GL_GEOMETRY_SUBROUTINE = 37611;
/*  34:    */  public static final int GL_FRAGMENT_SUBROUTINE = 37612;
/*  35:    */  public static final int GL_COMPUTE_SUBROUTINE = 37613;
/*  36:    */  public static final int GL_VERTEX_SUBROUTINE_UNIFORM = 37614;
/*  37:    */  public static final int GL_TESS_CONTROL_SUBROUTINE_UNIFORM = 37615;
/*  38:    */  public static final int GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 37616;
/*  39:    */  public static final int GL_GEOMETRY_SUBROUTINE_UNIFORM = 37617;
/*  40:    */  public static final int GL_FRAGMENT_SUBROUTINE_UNIFORM = 37618;
/*  41:    */  public static final int GL_COMPUTE_SUBROUTINE_UNIFORM = 37619;
/*  42:    */  public static final int GL_TRANSFORM_FEEDBACK_VARYING = 37620;
/*  43:    */  public static final int GL_ACTIVE_RESOURCES = 37621;
/*  44:    */  public static final int GL_MAX_NAME_LENGTH = 37622;
/*  45:    */  public static final int GL_MAX_NUM_ACTIVE_VARIABLES = 37623;
/*  46:    */  public static final int GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 37624;
/*  47:    */  public static final int GL_NAME_LENGTH = 37625;
/*  48:    */  public static final int GL_TYPE = 37626;
/*  49:    */  public static final int GL_ARRAY_SIZE = 37627;
/*  50:    */  public static final int GL_OFFSET = 37628;
/*  51:    */  public static final int GL_BLOCK_INDEX = 37629;
/*  52:    */  public static final int GL_ARRAY_STRIDE = 37630;
/*  53:    */  public static final int GL_MATRIX_STRIDE = 37631;
/*  54:    */  public static final int GL_IS_ROW_MAJOR = 37632;
/*  55:    */  public static final int GL_ATOMIC_COUNTER_BUFFER_INDEX = 37633;
/*  56:    */  public static final int GL_BUFFER_BINDING = 37634;
/*  57:    */  public static final int GL_BUFFER_DATA_SIZE = 37635;
/*  58:    */  public static final int GL_NUM_ACTIVE_VARIABLES = 37636;
/*  59:    */  public static final int GL_ACTIVE_VARIABLES = 37637;
/*  60:    */  public static final int GL_REFERENCED_BY_VERTEX_SHADER = 37638;
/*  61:    */  public static final int GL_REFERENCED_BY_TESS_CONTROL_SHADER = 37639;
/*  62:    */  public static final int GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 37640;
/*  63:    */  public static final int GL_REFERENCED_BY_GEOMETRY_SHADER = 37641;
/*  64:    */  public static final int GL_REFERENCED_BY_FRAGMENT_SHADER = 37642;
/*  65:    */  public static final int GL_REFERENCED_BY_COMPUTE_SHADER = 37643;
/*  66:    */  public static final int GL_TOP_LEVEL_ARRAY_SIZE = 37644;
/*  67:    */  public static final int GL_TOP_LEVEL_ARRAY_STRIDE = 37645;
/*  68:    */  public static final int GL_LOCATION = 37646;
/*  69:    */  public static final int GL_LOCATION_INDEX = 37647;
/*  70:    */  public static final int GL_IS_PER_PATCH = 37607;
/*  71:    */  
/*  72:    */  public static void glGetProgramInterface(int program, int programInterface, int pname, IntBuffer params)
/*  73:    */  {
/*  74: 74 */    GL43.glGetProgramInterface(program, programInterface, pname, params);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public static int glGetProgramInterfacei(int program, int programInterface, int pname)
/*  78:    */  {
/*  79: 79 */    return GL43.glGetProgramInterfacei(program, programInterface, pname);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public static int glGetProgramResourceIndex(int program, int programInterface, ByteBuffer name) {
/*  83: 83 */    return GL43.glGetProgramResourceIndex(program, programInterface, name);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static int glGetProgramResourceIndex(int program, int programInterface, CharSequence name)
/*  87:    */  {
/*  88: 88 */    return GL43.glGetProgramResourceIndex(program, programInterface, name);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public static void glGetProgramResourceName(int program, int programInterface, int index, IntBuffer length, ByteBuffer name) {
/*  92: 92 */    GL43.glGetProgramResourceName(program, programInterface, index, length, name);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public static String glGetProgramResourceName(int program, int programInterface, int index, int bufSize)
/*  96:    */  {
/*  97: 97 */    return GL43.glGetProgramResourceName(program, programInterface, index, bufSize);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public static void glGetProgramResource(int program, int programInterface, int index, IntBuffer props, IntBuffer length, IntBuffer params) {
/* 101:101 */    GL43.glGetProgramResource(program, programInterface, index, props, length, params);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public static int glGetProgramResourceLocation(int program, int programInterface, ByteBuffer name) {
/* 105:105 */    return GL43.glGetProgramResourceLocation(program, programInterface, name);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public static int glGetProgramResourceLocation(int program, int programInterface, CharSequence name)
/* 109:    */  {
/* 110:110 */    return GL43.glGetProgramResourceLocation(program, programInterface, name);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public static int glGetProgramResourceLocationIndex(int program, int programInterface, ByteBuffer name) {
/* 114:114 */    return GL43.glGetProgramResourceLocationIndex(program, programInterface, name);
/* 115:    */  }
/* 116:    */  
/* 117:    */  public static int glGetProgramResourceLocationIndex(int program, int programInterface, CharSequence name)
/* 118:    */  {
/* 119:119 */    return GL43.glGetProgramResourceLocationIndex(program, programInterface, name);
/* 120:    */  }
/* 121:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBProgramInterfaceQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */