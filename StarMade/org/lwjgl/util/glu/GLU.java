/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import org.lwjgl.opengl.GL11;
/*   7:    */import org.lwjgl.opengl.Util;
/*   8:    */import org.lwjgl.util.glu.tessellation.GLUtessellatorImpl;
/*   9:    */
/* 133:    */public class GLU
/* 134:    */{
/* 135:    */  static final float PI = 3.141593F;
/* 136:    */  public static final int GLU_INVALID_ENUM = 100900;
/* 137:    */  public static final int GLU_INVALID_VALUE = 100901;
/* 138:    */  public static final int GLU_OUT_OF_MEMORY = 100902;
/* 139:    */  public static final int GLU_INCOMPATIBLE_GL_VERSION = 100903;
/* 140:    */  public static final int GLU_VERSION = 100800;
/* 141:    */  public static final int GLU_EXTENSIONS = 100801;
/* 142:    */  public static final boolean GLU_TRUE = true;
/* 143:    */  public static final boolean GLU_FALSE = false;
/* 144:    */  public static final int GLU_SMOOTH = 100000;
/* 145:    */  public static final int GLU_FLAT = 100001;
/* 146:    */  public static final int GLU_NONE = 100002;
/* 147:    */  public static final int GLU_POINT = 100010;
/* 148:    */  public static final int GLU_LINE = 100011;
/* 149:    */  public static final int GLU_FILL = 100012;
/* 150:    */  public static final int GLU_SILHOUETTE = 100013;
/* 151:    */  public static final int GLU_OUTSIDE = 100020;
/* 152:    */  public static final int GLU_INSIDE = 100021;
/* 153:    */  public static final double GLU_TESS_MAX_COORD = 1.0E+150D;
/* 154:    */  public static final double TESS_MAX_COORD = 1.0E+150D;
/* 155:    */  public static final int GLU_TESS_WINDING_RULE = 100140;
/* 156:    */  public static final int GLU_TESS_BOUNDARY_ONLY = 100141;
/* 157:    */  public static final int GLU_TESS_TOLERANCE = 100142;
/* 158:    */  public static final int GLU_TESS_WINDING_ODD = 100130;
/* 159:    */  public static final int GLU_TESS_WINDING_NONZERO = 100131;
/* 160:    */  public static final int GLU_TESS_WINDING_POSITIVE = 100132;
/* 161:    */  public static final int GLU_TESS_WINDING_NEGATIVE = 100133;
/* 162:    */  public static final int GLU_TESS_WINDING_ABS_GEQ_TWO = 100134;
/* 163:    */  public static final int GLU_TESS_BEGIN = 100100;
/* 164:    */  public static final int GLU_TESS_VERTEX = 100101;
/* 165:    */  public static final int GLU_TESS_END = 100102;
/* 166:    */  public static final int GLU_TESS_ERROR = 100103;
/* 167:    */  public static final int GLU_TESS_EDGE_FLAG = 100104;
/* 168:    */  public static final int GLU_TESS_COMBINE = 100105;
/* 169:    */  public static final int GLU_TESS_BEGIN_DATA = 100106;
/* 170:    */  public static final int GLU_TESS_VERTEX_DATA = 100107;
/* 171:    */  public static final int GLU_TESS_END_DATA = 100108;
/* 172:    */  public static final int GLU_TESS_ERROR_DATA = 100109;
/* 173:    */  public static final int GLU_TESS_EDGE_FLAG_DATA = 100110;
/* 174:    */  public static final int GLU_TESS_COMBINE_DATA = 100111;
/* 175:    */  public static final int GLU_TESS_ERROR1 = 100151;
/* 176:    */  public static final int GLU_TESS_ERROR2 = 100152;
/* 177:    */  public static final int GLU_TESS_ERROR3 = 100153;
/* 178:    */  public static final int GLU_TESS_ERROR4 = 100154;
/* 179:    */  public static final int GLU_TESS_ERROR5 = 100155;
/* 180:    */  public static final int GLU_TESS_ERROR6 = 100156;
/* 181:    */  public static final int GLU_TESS_ERROR7 = 100157;
/* 182:    */  public static final int GLU_TESS_ERROR8 = 100158;
/* 183:    */  public static final int GLU_TESS_MISSING_BEGIN_POLYGON = 100151;
/* 184:    */  public static final int GLU_TESS_MISSING_BEGIN_CONTOUR = 100152;
/* 185:    */  public static final int GLU_TESS_MISSING_END_POLYGON = 100153;
/* 186:    */  public static final int GLU_TESS_MISSING_END_CONTOUR = 100154;
/* 187:    */  public static final int GLU_TESS_COORD_TOO_LARGE = 100155;
/* 188:    */  public static final int GLU_TESS_NEED_COMBINE_CALLBACK = 100156;
/* 189:    */  public static final int GLU_AUTO_LOAD_MATRIX = 100200;
/* 190:    */  public static final int GLU_CULLING = 100201;
/* 191:    */  public static final int GLU_SAMPLING_TOLERANCE = 100203;
/* 192:    */  public static final int GLU_DISPLAY_MODE = 100204;
/* 193:    */  public static final int GLU_PARAMETRIC_TOLERANCE = 100202;
/* 194:    */  public static final int GLU_SAMPLING_METHOD = 100205;
/* 195:    */  public static final int GLU_U_STEP = 100206;
/* 196:    */  public static final int GLU_V_STEP = 100207;
/* 197:    */  public static final int GLU_PATH_LENGTH = 100215;
/* 198:    */  public static final int GLU_PARAMETRIC_ERROR = 100216;
/* 199:    */  public static final int GLU_DOMAIN_DISTANCE = 100217;
/* 200:    */  public static final int GLU_MAP1_TRIM_2 = 100210;
/* 201:    */  public static final int GLU_MAP1_TRIM_3 = 100211;
/* 202:    */  public static final int GLU_OUTLINE_POLYGON = 100240;
/* 203:    */  public static final int GLU_OUTLINE_PATCH = 100241;
/* 204:    */  public static final int GLU_NURBS_ERROR1 = 100251;
/* 205:    */  public static final int GLU_NURBS_ERROR2 = 100252;
/* 206:    */  public static final int GLU_NURBS_ERROR3 = 100253;
/* 207:    */  public static final int GLU_NURBS_ERROR4 = 100254;
/* 208:    */  public static final int GLU_NURBS_ERROR5 = 100255;
/* 209:    */  public static final int GLU_NURBS_ERROR6 = 100256;
/* 210:    */  public static final int GLU_NURBS_ERROR7 = 100257;
/* 211:    */  public static final int GLU_NURBS_ERROR8 = 100258;
/* 212:    */  public static final int GLU_NURBS_ERROR9 = 100259;
/* 213:    */  public static final int GLU_NURBS_ERROR10 = 100260;
/* 214:    */  public static final int GLU_NURBS_ERROR11 = 100261;
/* 215:    */  public static final int GLU_NURBS_ERROR12 = 100262;
/* 216:    */  public static final int GLU_NURBS_ERROR13 = 100263;
/* 217:    */  public static final int GLU_NURBS_ERROR14 = 100264;
/* 218:    */  public static final int GLU_NURBS_ERROR15 = 100265;
/* 219:    */  public static final int GLU_NURBS_ERROR16 = 100266;
/* 220:    */  public static final int GLU_NURBS_ERROR17 = 100267;
/* 221:    */  public static final int GLU_NURBS_ERROR18 = 100268;
/* 222:    */  public static final int GLU_NURBS_ERROR19 = 100269;
/* 223:    */  public static final int GLU_NURBS_ERROR20 = 100270;
/* 224:    */  public static final int GLU_NURBS_ERROR21 = 100271;
/* 225:    */  public static final int GLU_NURBS_ERROR22 = 100272;
/* 226:    */  public static final int GLU_NURBS_ERROR23 = 100273;
/* 227:    */  public static final int GLU_NURBS_ERROR24 = 100274;
/* 228:    */  public static final int GLU_NURBS_ERROR25 = 100275;
/* 229:    */  public static final int GLU_NURBS_ERROR26 = 100276;
/* 230:    */  public static final int GLU_NURBS_ERROR27 = 100277;
/* 231:    */  public static final int GLU_NURBS_ERROR28 = 100278;
/* 232:    */  public static final int GLU_NURBS_ERROR29 = 100279;
/* 233:    */  public static final int GLU_NURBS_ERROR30 = 100280;
/* 234:    */  public static final int GLU_NURBS_ERROR31 = 100281;
/* 235:    */  public static final int GLU_NURBS_ERROR32 = 100282;
/* 236:    */  public static final int GLU_NURBS_ERROR33 = 100283;
/* 237:    */  public static final int GLU_NURBS_ERROR34 = 100284;
/* 238:    */  public static final int GLU_NURBS_ERROR35 = 100285;
/* 239:    */  public static final int GLU_NURBS_ERROR36 = 100286;
/* 240:    */  public static final int GLU_NURBS_ERROR37 = 100287;
/* 241:    */  public static final int GLU_CW = 100120;
/* 242:    */  public static final int GLU_CCW = 100121;
/* 243:    */  public static final int GLU_INTERIOR = 100122;
/* 244:    */  public static final int GLU_EXTERIOR = 100123;
/* 245:    */  public static final int GLU_UNKNOWN = 100124;
/* 246:    */  public static final int GLU_BEGIN = 100100;
/* 247:    */  public static final int GLU_VERTEX = 100101;
/* 248:    */  public static final int GLU_END = 100102;
/* 249:    */  public static final int GLU_ERROR = 100103;
/* 250:    */  public static final int GLU_EDGE_FLAG = 100104;
/* 251:    */  
/* 252:    */  public static void gluLookAt(float eyex, float eyey, float eyez, float centerx, float centery, float centerz, float upx, float upy, float upz)
/* 253:    */  {
/* 254:254 */    Project.gluLookAt(eyex, eyey, eyez, centerx, centery, centerz, upx, upy, upz);
/* 255:    */  }
/* 256:    */  
/* 268:    */  public static void gluOrtho2D(float left, float right, float bottom, float top)
/* 269:    */  {
/* 270:270 */    GL11.glOrtho(left, right, bottom, top, -1.0D, 1.0D);
/* 271:    */  }
/* 272:    */  
/* 284:    */  public static void gluPerspective(float fovy, float aspect, float zNear, float zFar)
/* 285:    */  {
/* 286:286 */    Project.gluPerspective(fovy, aspect, zNear, zFar);
/* 287:    */  }
/* 288:    */  
/* 303:    */  public static boolean gluProject(float objx, float objy, float objz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer win_pos)
/* 304:    */  {
/* 305:305 */    return Project.gluProject(objx, objy, objz, modelMatrix, projMatrix, viewport, win_pos);
/* 306:    */  }
/* 307:    */  
/* 322:    */  public static boolean gluUnProject(float winx, float winy, float winz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer obj_pos)
/* 323:    */  {
/* 324:324 */    return Project.gluUnProject(winx, winy, winz, modelMatrix, projMatrix, viewport, obj_pos);
/* 325:    */  }
/* 326:    */  
/* 340:    */  public static void gluPickMatrix(float x, float y, float width, float height, IntBuffer viewport)
/* 341:    */  {
/* 342:342 */    Project.gluPickMatrix(x, y, width, height, viewport);
/* 343:    */  }
/* 344:    */  
/* 349:    */  public static String gluGetString(int name)
/* 350:    */  {
/* 351:351 */    return Registry.gluGetString(name);
/* 352:    */  }
/* 353:    */  
/* 359:    */  public static boolean gluCheckExtension(String extName, String extString)
/* 360:    */  {
/* 361:361 */    return Registry.gluCheckExtension(extName, extString);
/* 362:    */  }
/* 363:    */  
/* 382:    */  public static int gluBuild2DMipmaps(int target, int components, int width, int height, int format, int type, ByteBuffer data)
/* 383:    */  {
/* 384:384 */    return MipMap.gluBuild2DMipmaps(target, components, width, height, format, type, data);
/* 385:    */  }
/* 386:    */  
/* 409:    */  public static int gluScaleImage(int format, int widthIn, int heightIn, int typeIn, ByteBuffer dataIn, int widthOut, int heightOut, int typeOut, ByteBuffer dataOut)
/* 410:    */  {
/* 411:411 */    return MipMap.gluScaleImage(format, widthIn, heightIn, typeIn, dataIn, widthOut, heightOut, typeOut, dataOut);
/* 412:    */  }
/* 413:    */  
/* 414:    */  public static String gluErrorString(int error_code) {
/* 415:415 */    switch (error_code) {
/* 416:    */    case 100900: 
/* 417:417 */      return "Invalid enum (glu)";
/* 418:    */    case 100901: 
/* 419:419 */      return "Invalid value (glu)";
/* 420:    */    case 100902: 
/* 421:421 */      return "Out of memory (glu)";
/* 422:    */    }
/* 423:423 */    return Util.translateGLErrorString(error_code);
/* 424:    */  }
/* 425:    */  
/* 426:    */  public static GLUtessellator gluNewTess()
/* 427:    */  {
/* 428:428 */    return new GLUtessellatorImpl();
/* 429:    */  }
/* 430:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.GLU
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */