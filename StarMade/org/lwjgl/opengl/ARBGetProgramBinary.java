/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ 
/*    */ public final class ARBGetProgramBinary
/*    */ {
/*    */   public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 33367;
/*    */   public static final int GL_PROGRAM_BINARY_LENGTH = 34625;
/*    */   public static final int GL_NUM_PROGRAM_BINARY_FORMATS = 34814;
/*    */   public static final int GL_PROGRAM_BINARY_FORMATS = 34815;
/*    */ 
/*    */   public static void glGetProgramBinary(int program, IntBuffer length, IntBuffer binaryFormat, ByteBuffer binary)
/*    */   {
/* 31 */     GL41.glGetProgramBinary(program, length, binaryFormat, binary);
/*    */   }
/*    */ 
/*    */   public static void glProgramBinary(int program, int binaryFormat, ByteBuffer binary) {
/* 35 */     GL41.glProgramBinary(program, binaryFormat, binary);
/*    */   }
/*    */ 
/*    */   public static void glProgramParameteri(int program, int pname, int value) {
/* 39 */     GL41.glProgramParameteri(program, pname, value);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBGetProgramBinary
 * JD-Core Version:    0.6.2
 */