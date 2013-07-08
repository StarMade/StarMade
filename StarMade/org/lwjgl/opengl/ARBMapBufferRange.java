/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */
/* 26:   */public final class ARBMapBufferRange
/* 27:   */{
/* 28:   */  public static final int GL_MAP_READ_BIT = 1;
/* 29:   */  public static final int GL_MAP_WRITE_BIT = 2;
/* 30:   */  public static final int GL_MAP_INVALIDATE_RANGE_BIT = 4;
/* 31:   */  public static final int GL_MAP_INVALIDATE_BUFFER_BIT = 8;
/* 32:   */  public static final int GL_MAP_FLUSH_EXPLICIT_BIT = 16;
/* 33:   */  public static final int GL_MAP_UNSYNCHRONIZED_BIT = 32;
/* 34:   */  
/* 35:   */  public static ByteBuffer glMapBufferRange(int target, long offset, long length, int access, ByteBuffer old_buffer)
/* 36:   */  {
/* 37:37 */    return GL30.glMapBufferRange(target, offset, length, access, old_buffer);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public static void glFlushMappedBufferRange(int target, long offset, long length) {
/* 41:41 */    GL30.glFlushMappedBufferRange(target, offset, length);
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMapBufferRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */