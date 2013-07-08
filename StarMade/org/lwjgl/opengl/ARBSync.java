/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import java.nio.LongBuffer;
/*   5:    */
/*  40:    */public final class ARBSync
/*  41:    */{
/*  42:    */  public static final int GL_MAX_SERVER_WAIT_TIMEOUT = 37137;
/*  43:    */  public static final int GL_OBJECT_TYPE = 37138;
/*  44:    */  public static final int GL_SYNC_CONDITION = 37139;
/*  45:    */  public static final int GL_SYNC_STATUS = 37140;
/*  46:    */  public static final int GL_SYNC_FLAGS = 37141;
/*  47:    */  public static final int GL_SYNC_FENCE = 37142;
/*  48:    */  public static final int GL_SYNC_GPU_COMMANDS_COMPLETE = 37143;
/*  49:    */  public static final int GL_UNSIGNALED = 37144;
/*  50:    */  public static final int GL_SIGNALED = 37145;
/*  51:    */  public static final int GL_SYNC_FLUSH_COMMANDS_BIT = 1;
/*  52:    */  public static final long GL_TIMEOUT_IGNORED = -1L;
/*  53:    */  public static final int GL_ALREADY_SIGNALED = 37146;
/*  54:    */  public static final int GL_TIMEOUT_EXPIRED = 37147;
/*  55:    */  public static final int GL_CONDITION_SATISFIED = 37148;
/*  56:    */  public static final int GL_WAIT_FAILED = 37149;
/*  57:    */  
/*  58:    */  public static GLSync glFenceSync(int condition, int flags)
/*  59:    */  {
/*  60: 60 */    return GL32.glFenceSync(condition, flags);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public static boolean glIsSync(GLSync sync) {
/*  64: 64 */    return GL32.glIsSync(sync);
/*  65:    */  }
/*  66:    */  
/*  67:    */  public static void glDeleteSync(GLSync sync) {
/*  68: 68 */    GL32.glDeleteSync(sync);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public static int glClientWaitSync(GLSync sync, int flags, long timeout) {
/*  72: 72 */    return GL32.glClientWaitSync(sync, flags, timeout);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public static void glWaitSync(GLSync sync, int flags, long timeout) {
/*  76: 76 */    GL32.glWaitSync(sync, flags, timeout);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public static void glGetInteger64(int pname, LongBuffer params) {
/*  80: 80 */    GL32.glGetInteger64(pname, params);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public static long glGetInteger64(int pname)
/*  84:    */  {
/*  85: 85 */    return GL32.glGetInteger64(pname);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public static void glGetSync(GLSync sync, int pname, IntBuffer length, IntBuffer values) {
/*  89: 89 */    GL32.glGetSync(sync, pname, length, values);
/*  90:    */  }
/*  91:    */  
/*  96:    */  @Deprecated
/*  97:    */  public static int glGetSync(GLSync sync, int pname)
/*  98:    */  {
/*  99: 99 */    return GL32.glGetSynci(sync, pname);
/* 100:    */  }
/* 101:    */  
/* 102:    */  public static int glGetSynci(GLSync sync, int pname)
/* 103:    */  {
/* 104:104 */    return GL32.glGetSynci(sync, pname);
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBSync
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */