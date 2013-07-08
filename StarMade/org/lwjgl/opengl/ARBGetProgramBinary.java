/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */
/* 22:   */public final class ARBGetProgramBinary
/* 23:   */{
/* 24:   */  public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 33367;
/* 25:   */  public static final int GL_PROGRAM_BINARY_LENGTH = 34625;
/* 26:   */  public static final int GL_NUM_PROGRAM_BINARY_FORMATS = 34814;
/* 27:   */  public static final int GL_PROGRAM_BINARY_FORMATS = 34815;
/* 28:   */  
/* 29:   */  public static void glGetProgramBinary(int program, IntBuffer length, IntBuffer binaryFormat, ByteBuffer binary)
/* 30:   */  {
/* 31:31 */    GL41.glGetProgramBinary(program, length, binaryFormat, binary);
/* 32:   */  }
/* 33:   */  
/* 34:   */  public static void glProgramBinary(int program, int binaryFormat, ByteBuffer binary) {
/* 35:35 */    GL41.glProgramBinary(program, binaryFormat, binary);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public static void glProgramParameteri(int program, int pname, int value) {
/* 39:39 */    GL41.glProgramParameteri(program, pname, value);
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBGetProgramBinary
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */