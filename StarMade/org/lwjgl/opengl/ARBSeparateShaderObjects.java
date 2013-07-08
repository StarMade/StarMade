/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */
/*  26:    */public final class ARBSeparateShaderObjects
/*  27:    */{
/*  28:    */  public static final int GL_VERTEX_SHADER_BIT = 1;
/*  29:    */  public static final int GL_FRAGMENT_SHADER_BIT = 2;
/*  30:    */  public static final int GL_GEOMETRY_SHADER_BIT = 4;
/*  31:    */  public static final int GL_TESS_CONTROL_SHADER_BIT = 8;
/*  32:    */  public static final int GL_TESS_EVALUATION_SHADER_BIT = 16;
/*  33:    */  public static final int GL_ALL_SHADER_BITS = -1;
/*  34:    */  public static final int GL_PROGRAM_SEPARABLE = 33368;
/*  35:    */  public static final int GL_ACTIVE_PROGRAM = 33369;
/*  36:    */  public static final int GL_PROGRAM_PIPELINE_BINDING = 33370;
/*  37:    */  
/*  38:    */  public static void glUseProgramStages(int pipeline, int stages, int program)
/*  39:    */  {
/*  40: 40 */    GL41.glUseProgramStages(pipeline, stages, program);
/*  41:    */  }
/*  42:    */  
/*  43:    */  public static void glActiveShaderProgram(int pipeline, int program) {
/*  44: 44 */    GL41.glActiveShaderProgram(pipeline, program);
/*  45:    */  }
/*  46:    */  
/*  49:    */  public static int glCreateShaderProgram(int type, ByteBuffer string)
/*  50:    */  {
/*  51: 51 */    return GL41.glCreateShaderProgram(type, string);
/*  52:    */  }
/*  53:    */  
/*  58:    */  public static int glCreateShaderProgram(int type, int count, ByteBuffer strings)
/*  59:    */  {
/*  60: 60 */    return GL41.glCreateShaderProgram(type, count, strings);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public static int glCreateShaderProgram(int type, ByteBuffer[] strings)
/*  64:    */  {
/*  65: 65 */    return GL41.glCreateShaderProgram(type, strings);
/*  66:    */  }
/*  67:    */  
/*  68:    */  public static int glCreateShaderProgram(int type, CharSequence string)
/*  69:    */  {
/*  70: 70 */    return GL41.glCreateShaderProgram(type, string);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public static int glCreateShaderProgram(int type, CharSequence[] strings)
/*  74:    */  {
/*  75: 75 */    return GL41.glCreateShaderProgram(type, strings);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public static void glBindProgramPipeline(int pipeline) {
/*  79: 79 */    GL41.glBindProgramPipeline(pipeline);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public static void glDeleteProgramPipelines(IntBuffer pipelines) {
/*  83: 83 */    GL41.glDeleteProgramPipelines(pipelines);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static void glDeleteProgramPipelines(int pipeline)
/*  87:    */  {
/*  88: 88 */    GL41.glDeleteProgramPipelines(pipeline);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public static void glGenProgramPipelines(IntBuffer pipelines) {
/*  92: 92 */    GL41.glGenProgramPipelines(pipelines);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public static int glGenProgramPipelines()
/*  96:    */  {
/*  97: 97 */    return GL41.glGenProgramPipelines();
/*  98:    */  }
/*  99:    */  
/* 100:    */  public static boolean glIsProgramPipeline(int pipeline) {
/* 101:101 */    return GL41.glIsProgramPipeline(pipeline);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public static void glProgramParameteri(int program, int pname, int value) {
/* 105:105 */    GL41.glProgramParameteri(program, pname, value);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public static void glGetProgramPipeline(int pipeline, int pname, IntBuffer params) {
/* 109:109 */    GL41.glGetProgramPipeline(pipeline, pname, params);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public static int glGetProgramPipelinei(int pipeline, int pname)
/* 113:    */  {
/* 114:114 */    return GL41.glGetProgramPipelinei(pipeline, pname);
/* 115:    */  }
/* 116:    */  
/* 117:    */  public static void glProgramUniform1i(int program, int location, int v0) {
/* 118:118 */    GL41.glProgramUniform1i(program, location, v0);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public static void glProgramUniform2i(int program, int location, int v0, int v1) {
/* 122:122 */    GL41.glProgramUniform2i(program, location, v0, v1);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public static void glProgramUniform3i(int program, int location, int v0, int v1, int v2) {
/* 126:126 */    GL41.glProgramUniform3i(program, location, v0, v1, v2);
/* 127:    */  }
/* 128:    */  
/* 129:    */  public static void glProgramUniform4i(int program, int location, int v0, int v1, int v2, int v3) {
/* 130:130 */    GL41.glProgramUniform4i(program, location, v0, v1, v2, v3);
/* 131:    */  }
/* 132:    */  
/* 133:    */  public static void glProgramUniform1f(int program, int location, float v0) {
/* 134:134 */    GL41.glProgramUniform1f(program, location, v0);
/* 135:    */  }
/* 136:    */  
/* 137:    */  public static void glProgramUniform2f(int program, int location, float v0, float v1) {
/* 138:138 */    GL41.glProgramUniform2f(program, location, v0, v1);
/* 139:    */  }
/* 140:    */  
/* 141:    */  public static void glProgramUniform3f(int program, int location, float v0, float v1, float v2) {
/* 142:142 */    GL41.glProgramUniform3f(program, location, v0, v1, v2);
/* 143:    */  }
/* 144:    */  
/* 145:    */  public static void glProgramUniform4f(int program, int location, float v0, float v1, float v2, float v3) {
/* 146:146 */    GL41.glProgramUniform4f(program, location, v0, v1, v2, v3);
/* 147:    */  }
/* 148:    */  
/* 149:    */  public static void glProgramUniform1d(int program, int location, double v0) {
/* 150:150 */    GL41.glProgramUniform1d(program, location, v0);
/* 151:    */  }
/* 152:    */  
/* 153:    */  public static void glProgramUniform2d(int program, int location, double v0, double v1) {
/* 154:154 */    GL41.glProgramUniform2d(program, location, v0, v1);
/* 155:    */  }
/* 156:    */  
/* 157:    */  public static void glProgramUniform3d(int program, int location, double v0, double v1, double v2) {
/* 158:158 */    GL41.glProgramUniform3d(program, location, v0, v1, v2);
/* 159:    */  }
/* 160:    */  
/* 161:    */  public static void glProgramUniform4d(int program, int location, double v0, double v1, double v2, double v3) {
/* 162:162 */    GL41.glProgramUniform4d(program, location, v0, v1, v2, v3);
/* 163:    */  }
/* 164:    */  
/* 165:    */  public static void glProgramUniform1(int program, int location, IntBuffer value) {
/* 166:166 */    GL41.glProgramUniform1(program, location, value);
/* 167:    */  }
/* 168:    */  
/* 169:    */  public static void glProgramUniform2(int program, int location, IntBuffer value) {
/* 170:170 */    GL41.glProgramUniform2(program, location, value);
/* 171:    */  }
/* 172:    */  
/* 173:    */  public static void glProgramUniform3(int program, int location, IntBuffer value) {
/* 174:174 */    GL41.glProgramUniform3(program, location, value);
/* 175:    */  }
/* 176:    */  
/* 177:    */  public static void glProgramUniform4(int program, int location, IntBuffer value) {
/* 178:178 */    GL41.glProgramUniform4(program, location, value);
/* 179:    */  }
/* 180:    */  
/* 181:    */  public static void glProgramUniform1(int program, int location, FloatBuffer value) {
/* 182:182 */    GL41.glProgramUniform1(program, location, value);
/* 183:    */  }
/* 184:    */  
/* 185:    */  public static void glProgramUniform2(int program, int location, FloatBuffer value) {
/* 186:186 */    GL41.glProgramUniform2(program, location, value);
/* 187:    */  }
/* 188:    */  
/* 189:    */  public static void glProgramUniform3(int program, int location, FloatBuffer value) {
/* 190:190 */    GL41.glProgramUniform3(program, location, value);
/* 191:    */  }
/* 192:    */  
/* 193:    */  public static void glProgramUniform4(int program, int location, FloatBuffer value) {
/* 194:194 */    GL41.glProgramUniform4(program, location, value);
/* 195:    */  }
/* 196:    */  
/* 197:    */  public static void glProgramUniform1(int program, int location, DoubleBuffer value) {
/* 198:198 */    GL41.glProgramUniform1(program, location, value);
/* 199:    */  }
/* 200:    */  
/* 201:    */  public static void glProgramUniform2(int program, int location, DoubleBuffer value) {
/* 202:202 */    GL41.glProgramUniform2(program, location, value);
/* 203:    */  }
/* 204:    */  
/* 205:    */  public static void glProgramUniform3(int program, int location, DoubleBuffer value) {
/* 206:206 */    GL41.glProgramUniform3(program, location, value);
/* 207:    */  }
/* 208:    */  
/* 209:    */  public static void glProgramUniform4(int program, int location, DoubleBuffer value) {
/* 210:210 */    GL41.glProgramUniform4(program, location, value);
/* 211:    */  }
/* 212:    */  
/* 213:    */  public static void glProgramUniform1ui(int program, int location, int v0) {
/* 214:214 */    GL41.glProgramUniform1ui(program, location, v0);
/* 215:    */  }
/* 216:    */  
/* 217:    */  public static void glProgramUniform2ui(int program, int location, int v0, int v1) {
/* 218:218 */    GL41.glProgramUniform2ui(program, location, v0, v1);
/* 219:    */  }
/* 220:    */  
/* 221:    */  public static void glProgramUniform3ui(int program, int location, int v0, int v1, int v2) {
/* 222:222 */    GL41.glProgramUniform3ui(program, location, v0, v1, v2);
/* 223:    */  }
/* 224:    */  
/* 225:    */  public static void glProgramUniform4ui(int program, int location, int v0, int v1, int v2, int v3) {
/* 226:226 */    GL41.glProgramUniform4ui(program, location, v0, v1, v2, v3);
/* 227:    */  }
/* 228:    */  
/* 229:    */  public static void glProgramUniform1u(int program, int location, IntBuffer value) {
/* 230:230 */    GL41.glProgramUniform1u(program, location, value);
/* 231:    */  }
/* 232:    */  
/* 233:    */  public static void glProgramUniform2u(int program, int location, IntBuffer value) {
/* 234:234 */    GL41.glProgramUniform2u(program, location, value);
/* 235:    */  }
/* 236:    */  
/* 237:    */  public static void glProgramUniform3u(int program, int location, IntBuffer value) {
/* 238:238 */    GL41.glProgramUniform3u(program, location, value);
/* 239:    */  }
/* 240:    */  
/* 241:    */  public static void glProgramUniform4u(int program, int location, IntBuffer value) {
/* 242:242 */    GL41.glProgramUniform4u(program, location, value);
/* 243:    */  }
/* 244:    */  
/* 245:    */  public static void glProgramUniformMatrix2(int program, int location, boolean transpose, FloatBuffer value) {
/* 246:246 */    GL41.glProgramUniformMatrix2(program, location, transpose, value);
/* 247:    */  }
/* 248:    */  
/* 249:    */  public static void glProgramUniformMatrix3(int program, int location, boolean transpose, FloatBuffer value) {
/* 250:250 */    GL41.glProgramUniformMatrix3(program, location, transpose, value);
/* 251:    */  }
/* 252:    */  
/* 253:    */  public static void glProgramUniformMatrix4(int program, int location, boolean transpose, FloatBuffer value) {
/* 254:254 */    GL41.glProgramUniformMatrix4(program, location, transpose, value);
/* 255:    */  }
/* 256:    */  
/* 257:    */  public static void glProgramUniformMatrix2(int program, int location, boolean transpose, DoubleBuffer value) {
/* 258:258 */    GL41.glProgramUniformMatrix2(program, location, transpose, value);
/* 259:    */  }
/* 260:    */  
/* 261:    */  public static void glProgramUniformMatrix3(int program, int location, boolean transpose, DoubleBuffer value) {
/* 262:262 */    GL41.glProgramUniformMatrix3(program, location, transpose, value);
/* 263:    */  }
/* 264:    */  
/* 265:    */  public static void glProgramUniformMatrix4(int program, int location, boolean transpose, DoubleBuffer value) {
/* 266:266 */    GL41.glProgramUniformMatrix4(program, location, transpose, value);
/* 267:    */  }
/* 268:    */  
/* 269:    */  public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, FloatBuffer value) {
/* 270:270 */    GL41.glProgramUniformMatrix2x3(program, location, transpose, value);
/* 271:    */  }
/* 272:    */  
/* 273:    */  public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, FloatBuffer value) {
/* 274:274 */    GL41.glProgramUniformMatrix3x2(program, location, transpose, value);
/* 275:    */  }
/* 276:    */  
/* 277:    */  public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, FloatBuffer value) {
/* 278:278 */    GL41.glProgramUniformMatrix2x4(program, location, transpose, value);
/* 279:    */  }
/* 280:    */  
/* 281:    */  public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, FloatBuffer value) {
/* 282:282 */    GL41.glProgramUniformMatrix4x2(program, location, transpose, value);
/* 283:    */  }
/* 284:    */  
/* 285:    */  public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, FloatBuffer value) {
/* 286:286 */    GL41.glProgramUniformMatrix3x4(program, location, transpose, value);
/* 287:    */  }
/* 288:    */  
/* 289:    */  public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, FloatBuffer value) {
/* 290:290 */    GL41.glProgramUniformMatrix4x3(program, location, transpose, value);
/* 291:    */  }
/* 292:    */  
/* 293:    */  public static void glProgramUniformMatrix2x3(int program, int location, boolean transpose, DoubleBuffer value) {
/* 294:294 */    GL41.glProgramUniformMatrix2x3(program, location, transpose, value);
/* 295:    */  }
/* 296:    */  
/* 297:    */  public static void glProgramUniformMatrix3x2(int program, int location, boolean transpose, DoubleBuffer value) {
/* 298:298 */    GL41.glProgramUniformMatrix3x2(program, location, transpose, value);
/* 299:    */  }
/* 300:    */  
/* 301:    */  public static void glProgramUniformMatrix2x4(int program, int location, boolean transpose, DoubleBuffer value) {
/* 302:302 */    GL41.glProgramUniformMatrix2x4(program, location, transpose, value);
/* 303:    */  }
/* 304:    */  
/* 305:    */  public static void glProgramUniformMatrix4x2(int program, int location, boolean transpose, DoubleBuffer value) {
/* 306:306 */    GL41.glProgramUniformMatrix4x2(program, location, transpose, value);
/* 307:    */  }
/* 308:    */  
/* 309:    */  public static void glProgramUniformMatrix3x4(int program, int location, boolean transpose, DoubleBuffer value) {
/* 310:310 */    GL41.glProgramUniformMatrix3x4(program, location, transpose, value);
/* 311:    */  }
/* 312:    */  
/* 313:    */  public static void glProgramUniformMatrix4x3(int program, int location, boolean transpose, DoubleBuffer value) {
/* 314:314 */    GL41.glProgramUniformMatrix4x3(program, location, transpose, value);
/* 315:    */  }
/* 316:    */  
/* 317:    */  public static void glValidateProgramPipeline(int pipeline) {
/* 318:318 */    GL41.glValidateProgramPipeline(pipeline);
/* 319:    */  }
/* 320:    */  
/* 321:    */  public static void glGetProgramPipelineInfoLog(int pipeline, IntBuffer length, ByteBuffer infoLog) {
/* 322:322 */    GL41.glGetProgramPipelineInfoLog(pipeline, length, infoLog);
/* 323:    */  }
/* 324:    */  
/* 325:    */  public static String glGetProgramPipelineInfoLog(int pipeline, int bufSize)
/* 326:    */  {
/* 327:327 */    return GL41.glGetProgramPipelineInfoLog(pipeline, bufSize);
/* 328:    */  }
/* 329:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBSeparateShaderObjects
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */