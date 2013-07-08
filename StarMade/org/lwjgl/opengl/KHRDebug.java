/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.PointerWrapper;
/*   6:    */
/*  59:    */public final class KHRDebug
/*  60:    */{
/*  61:    */  public static final int GL_DEBUG_OUTPUT = 37600;
/*  62:    */  public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS = 33346;
/*  63:    */  public static final int GL_CONTEXT_FLAG_DEBUG_BIT = 2;
/*  64:    */  public static final int GL_MAX_DEBUG_MESSAGE_LENGTH = 37187;
/*  65:    */  public static final int GL_MAX_DEBUG_LOGGED_MESSAGES = 37188;
/*  66:    */  public static final int GL_DEBUG_LOGGED_MESSAGES = 37189;
/*  67:    */  public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 33347;
/*  68:    */  public static final int GL_MAX_DEBUG_GROUP_STACK_DEPTH = 33388;
/*  69:    */  public static final int GL_DEBUG_GROUP_STACK_DEPTH = 33389;
/*  70:    */  public static final int GL_MAX_LABEL_LENGTH = 33512;
/*  71:    */  public static final int GL_DEBUG_CALLBACK_FUNCTION = 33348;
/*  72:    */  public static final int GL_DEBUG_CALLBACK_USER_PARAM = 33349;
/*  73:    */  public static final int GL_DEBUG_SOURCE_API = 33350;
/*  74:    */  public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM = 33351;
/*  75:    */  public static final int GL_DEBUG_SOURCE_SHADER_COMPILER = 33352;
/*  76:    */  public static final int GL_DEBUG_SOURCE_THIRD_PARTY = 33353;
/*  77:    */  public static final int GL_DEBUG_SOURCE_APPLICATION = 33354;
/*  78:    */  public static final int GL_DEBUG_SOURCE_OTHER = 33355;
/*  79:    */  public static final int GL_DEBUG_TYPE_ERROR = 33356;
/*  80:    */  public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357;
/*  81:    */  public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 33358;
/*  82:    */  public static final int GL_DEBUG_TYPE_PORTABILITY = 33359;
/*  83:    */  public static final int GL_DEBUG_TYPE_PERFORMANCE = 33360;
/*  84:    */  public static final int GL_DEBUG_TYPE_OTHER = 33361;
/*  85:    */  public static final int GL_DEBUG_TYPE_MARKER = 33384;
/*  86:    */  public static final int GL_DEBUG_TYPE_PUSH_GROUP = 33385;
/*  87:    */  public static final int GL_DEBUG_TYPE_POP_GROUP = 33386;
/*  88:    */  public static final int GL_DEBUG_SEVERITY_HIGH = 37190;
/*  89:    */  public static final int GL_DEBUG_SEVERITY_MEDIUM = 37191;
/*  90:    */  public static final int GL_DEBUG_SEVERITY_LOW = 37192;
/*  91:    */  public static final int GL_DEBUG_SEVERITY_NOTIFICATION = 33387;
/*  92:    */  public static final int GL_STACK_UNDERFLOW = 1284;
/*  93:    */  public static final int GL_STACK_OVERFLOW = 1283;
/*  94:    */  public static final int GL_BUFFER = 33504;
/*  95:    */  public static final int GL_SHADER = 33505;
/*  96:    */  public static final int GL_PROGRAM = 33506;
/*  97:    */  public static final int GL_QUERY = 33507;
/*  98:    */  public static final int GL_PROGRAM_PIPELINE = 33508;
/*  99:    */  public static final int GL_SAMPLER = 33510;
/* 100:    */  public static final int GL_DISPLAY_LIST = 33511;
/* 101:    */  
/* 102:    */  public static void glDebugMessageControl(int source, int type, int severity, IntBuffer ids, boolean enabled)
/* 103:    */  {
/* 104:104 */    GL43.glDebugMessageControl(source, type, severity, ids, enabled);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public static void glDebugMessageInsert(int source, int type, int id, int severity, ByteBuffer buf) {
/* 108:108 */    GL43.glDebugMessageInsert(source, type, id, severity, buf);
/* 109:    */  }
/* 110:    */  
/* 111:    */  public static void glDebugMessageInsert(int source, int type, int id, int severity, CharSequence buf)
/* 112:    */  {
/* 113:113 */    GL43.glDebugMessageInsert(source, type, id, severity, buf);
/* 114:    */  }
/* 115:    */  
/* 122:    */  public static void glDebugMessageCallback(KHRDebugCallback callback)
/* 123:    */  {
/* 124:124 */    GL43.glDebugMessageCallback(callback);
/* 125:    */  }
/* 126:    */  
/* 127:    */  public static int glGetDebugMessageLog(int count, IntBuffer sources, IntBuffer types, IntBuffer ids, IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) {
/* 128:128 */    return GL43.glGetDebugMessageLog(count, sources, types, ids, severities, lengths, messageLog);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public static void glPushDebugGroup(int source, int id, ByteBuffer message) {
/* 132:132 */    GL43.glPushDebugGroup(source, id, message);
/* 133:    */  }
/* 134:    */  
/* 135:    */  public static void glPushDebugGroup(int source, int id, CharSequence message)
/* 136:    */  {
/* 137:137 */    GL43.glPushDebugGroup(source, id, message);
/* 138:    */  }
/* 139:    */  
/* 141:    */  public static void glPopDebugGroup() {}
/* 142:    */  
/* 143:    */  public static void glObjectLabel(int identifier, int name, ByteBuffer label)
/* 144:    */  {
/* 145:145 */    GL43.glObjectLabel(identifier, name, label);
/* 146:    */  }
/* 147:    */  
/* 148:    */  public static void glObjectLabel(int identifier, int name, CharSequence label)
/* 149:    */  {
/* 150:150 */    GL43.glObjectLabel(identifier, name, label);
/* 151:    */  }
/* 152:    */  
/* 153:    */  public static void glGetObjectLabel(int identifier, int name, IntBuffer length, ByteBuffer label) {
/* 154:154 */    GL43.glGetObjectLabel(identifier, name, length, label);
/* 155:    */  }
/* 156:    */  
/* 157:    */  public static String glGetObjectLabel(int identifier, int name, int bufSize)
/* 158:    */  {
/* 159:159 */    return GL43.glGetObjectLabel(identifier, name, bufSize);
/* 160:    */  }
/* 161:    */  
/* 162:    */  public static void glObjectPtrLabel(PointerWrapper ptr, ByteBuffer label) {
/* 163:163 */    GL43.glObjectPtrLabel(ptr, label);
/* 164:    */  }
/* 165:    */  
/* 166:    */  public static void glObjectPtrLabel(PointerWrapper ptr, CharSequence label)
/* 167:    */  {
/* 168:168 */    GL43.glObjectPtrLabel(ptr, label);
/* 169:    */  }
/* 170:    */  
/* 171:    */  public static void glGetObjectPtrLabel(PointerWrapper ptr, IntBuffer length, ByteBuffer label) {
/* 172:172 */    GL43.glGetObjectPtrLabel(ptr, length, label);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public static String glGetObjectPtrLabel(PointerWrapper ptr, int bufSize)
/* 176:    */  {
/* 177:177 */    return GL43.glGetObjectPtrLabel(ptr, bufSize);
/* 178:    */  }
/* 179:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.KHRDebug
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */