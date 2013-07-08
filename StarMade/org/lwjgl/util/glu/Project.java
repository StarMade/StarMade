/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferUtils;
/*   6:    */import org.lwjgl.opengl.GL11;
/*   7:    */
/*  49:    */public class Project
/*  50:    */  extends Util
/*  51:    */{
/*  52: 52 */  private static final float[] IDENTITY_MATRIX = { 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F };
/*  53:    */  
/*  59: 59 */  private static final FloatBuffer matrix = BufferUtils.createFloatBuffer(16);
/*  60: 60 */  private static final FloatBuffer finalMatrix = BufferUtils.createFloatBuffer(16);
/*  61:    */  
/*  62: 62 */  private static final FloatBuffer tempMatrix = BufferUtils.createFloatBuffer(16);
/*  63: 63 */  private static final float[] in = new float[4];
/*  64: 64 */  private static final float[] out = new float[4];
/*  65:    */  
/*  66: 66 */  private static final float[] forward = new float[3];
/*  67: 67 */  private static final float[] side = new float[3];
/*  68: 68 */  private static final float[] up = new float[3];
/*  69:    */  
/*  72:    */  private static void __gluMakeIdentityf(FloatBuffer m)
/*  73:    */  {
/*  74: 74 */    int oldPos = m.position();
/*  75: 75 */    m.put(IDENTITY_MATRIX);
/*  76: 76 */    m.position(oldPos);
/*  77:    */  }
/*  78:    */  
/*  85:    */  private static void __gluMultMatrixVecf(FloatBuffer m, float[] in, float[] out)
/*  86:    */  {
/*  87: 87 */    for (int i = 0; i < 4; i++) {
/*  88: 88 */      out[i] = (in[0] * m.get(m.position() + 0 + i) + in[1] * m.get(m.position() + 4 + i) + in[2] * m.get(m.position() + 8 + i) + in[3] * m.get(m.position() + 12 + i));
/*  89:    */    }
/*  90:    */  }
/*  91:    */  
/* 104:    */  private static boolean __gluInvertMatrixf(FloatBuffer src, FloatBuffer inverse)
/* 105:    */  {
/* 106:106 */    FloatBuffer temp = tempMatrix;
/* 107:    */    
/* 109:109 */    for (int i = 0; i < 16; i++) {
/* 110:110 */      temp.put(i, src.get(i + src.position()));
/* 111:    */    }
/* 112:112 */    __gluMakeIdentityf(inverse);
/* 113:    */    
/* 114:114 */    for (i = 0; i < 4; i++)
/* 115:    */    {
/* 118:118 */      int swap = i;
/* 119:119 */      for (int j = i + 1; j < 4; j++)
/* 120:    */      {
/* 123:123 */        if (Math.abs(temp.get(j * 4 + i)) > Math.abs(temp.get(i * 4 + i))) {
/* 124:124 */          swap = j;
/* 125:    */        }
/* 126:    */      }
/* 127:    */      
/* 128:128 */      if (swap != i)
/* 129:    */      {
/* 132:132 */        for (int k = 0; k < 4; k++) {
/* 133:133 */          float t = temp.get(i * 4 + k);
/* 134:134 */          temp.put(i * 4 + k, temp.get(swap * 4 + k));
/* 135:135 */          temp.put(swap * 4 + k, t);
/* 136:    */          
/* 137:137 */          t = inverse.get(i * 4 + k);
/* 138:138 */          inverse.put(i * 4 + k, inverse.get(swap * 4 + k));
/* 139:    */          
/* 140:140 */          inverse.put(swap * 4 + k, t);
/* 141:    */        }
/* 142:    */      }
/* 143:    */      
/* 145:145 */      if (temp.get(i * 4 + i) == 0.0F)
/* 146:    */      {
/* 150:150 */        return false;
/* 151:    */      }
/* 152:    */      
/* 153:153 */      float t = temp.get(i * 4 + i);
/* 154:154 */      for (int k = 0; k < 4; k++) {
/* 155:155 */        temp.put(i * 4 + k, temp.get(i * 4 + k) / t);
/* 156:156 */        inverse.put(i * 4 + k, inverse.get(i * 4 + k) / t);
/* 157:    */      }
/* 158:158 */      for (j = 0; j < 4; j++) {
/* 159:159 */        if (j != i) {
/* 160:160 */          t = temp.get(j * 4 + i);
/* 161:161 */          for (k = 0; k < 4; k++) {
/* 162:162 */            temp.put(j * 4 + k, temp.get(j * 4 + k) - temp.get(i * 4 + k) * t);
/* 163:163 */            inverse.put(j * 4 + k, inverse.get(j * 4 + k) - inverse.get(i * 4 + k) * t);
/* 164:    */          }
/* 165:    */        }
/* 166:    */      }
/* 167:    */    }
/* 168:    */    
/* 171:171 */    return true;
/* 172:    */  }
/* 173:    */  
/* 178:    */  private static void __gluMultMatricesf(FloatBuffer a, FloatBuffer b, FloatBuffer r)
/* 179:    */  {
/* 180:180 */    for (int i = 0; i < 4; i++) {
/* 181:181 */      for (int j = 0; j < 4; j++) {
/* 182:182 */        r.put(r.position() + i * 4 + j, a.get(a.position() + i * 4 + 0) * b.get(b.position() + 0 + j) + a.get(a.position() + i * 4 + 1) * b.get(b.position() + 4 + j) + a.get(a.position() + i * 4 + 2) * b.get(b.position() + 8 + j) + a.get(a.position() + i * 4 + 3) * b.get(b.position() + 12 + j));
/* 183:    */      }
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 196:    */  public static void gluPerspective(float fovy, float aspect, float zNear, float zFar)
/* 197:    */  {
/* 198:198 */    float radians = fovy / 2.0F * 3.141593F / 180.0F;
/* 199:    */    
/* 200:200 */    float deltaZ = zFar - zNear;
/* 201:201 */    float sine = (float)Math.sin(radians);
/* 202:    */    
/* 203:203 */    if ((deltaZ == 0.0F) || (sine == 0.0F) || (aspect == 0.0F)) {
/* 204:204 */      return;
/* 205:    */    }
/* 206:    */    
/* 207:207 */    float cotangent = (float)Math.cos(radians) / sine;
/* 208:    */    
/* 209:209 */    __gluMakeIdentityf(matrix);
/* 210:    */    
/* 211:211 */    matrix.put(0, cotangent / aspect);
/* 212:212 */    matrix.put(5, cotangent);
/* 213:213 */    matrix.put(10, -(zFar + zNear) / deltaZ);
/* 214:214 */    matrix.put(11, -1.0F);
/* 215:215 */    matrix.put(14, -2.0F * zNear * zFar / deltaZ);
/* 216:216 */    matrix.put(15, 0.0F);
/* 217:    */    
/* 218:218 */    GL11.glMultMatrix(matrix);
/* 219:    */  }
/* 220:    */  
/* 242:    */  public static void gluLookAt(float eyex, float eyey, float eyez, float centerx, float centery, float centerz, float upx, float upy, float upz)
/* 243:    */  {
/* 244:244 */    float[] forward = forward;
/* 245:245 */    float[] side = side;
/* 246:246 */    float[] up = up;
/* 247:    */    
/* 248:248 */    forward[0] = (centerx - eyex);
/* 249:249 */    forward[1] = (centery - eyey);
/* 250:250 */    forward[2] = (centerz - eyez);
/* 251:    */    
/* 252:252 */    up[0] = upx;
/* 253:253 */    up[1] = upy;
/* 254:254 */    up[2] = upz;
/* 255:    */    
/* 256:256 */    normalize(forward);
/* 257:    */    
/* 259:259 */    cross(forward, up, side);
/* 260:260 */    normalize(side);
/* 261:    */    
/* 263:263 */    cross(side, forward, up);
/* 264:    */    
/* 265:265 */    __gluMakeIdentityf(matrix);
/* 266:266 */    matrix.put(0, side[0]);
/* 267:267 */    matrix.put(4, side[1]);
/* 268:268 */    matrix.put(8, side[2]);
/* 269:    */    
/* 270:270 */    matrix.put(1, up[0]);
/* 271:271 */    matrix.put(5, up[1]);
/* 272:272 */    matrix.put(9, up[2]);
/* 273:    */    
/* 274:274 */    matrix.put(2, -forward[0]);
/* 275:275 */    matrix.put(6, -forward[1]);
/* 276:276 */    matrix.put(10, -forward[2]);
/* 277:    */    
/* 278:278 */    GL11.glMultMatrix(matrix);
/* 279:279 */    GL11.glTranslatef(-eyex, -eyey, -eyez);
/* 280:    */  }
/* 281:    */  
/* 300:    */  public static boolean gluProject(float objx, float objy, float objz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer win_pos)
/* 301:    */  {
/* 302:302 */    float[] in = in;
/* 303:303 */    float[] out = out;
/* 304:    */    
/* 305:305 */    in[0] = objx;
/* 306:306 */    in[1] = objy;
/* 307:307 */    in[2] = objz;
/* 308:308 */    in[3] = 1.0F;
/* 309:    */    
/* 310:310 */    __gluMultMatrixVecf(modelMatrix, in, out);
/* 311:311 */    __gluMultMatrixVecf(projMatrix, out, in);
/* 312:    */    
/* 313:313 */    if (in[3] == 0.0D) {
/* 314:314 */      return false;
/* 315:    */    }
/* 316:316 */    in[3] = (1.0F / in[3] * 0.5F);
/* 317:    */    
/* 319:319 */    in[0] = (in[0] * in[3] + 0.5F);
/* 320:320 */    in[1] = (in[1] * in[3] + 0.5F);
/* 321:321 */    in[2] = (in[2] * in[3] + 0.5F);
/* 322:    */    
/* 324:324 */    win_pos.put(0, in[0] * viewport.get(viewport.position() + 2) + viewport.get(viewport.position() + 0));
/* 325:325 */    win_pos.put(1, in[1] * viewport.get(viewport.position() + 3) + viewport.get(viewport.position() + 1));
/* 326:326 */    win_pos.put(2, in[2]);
/* 327:    */    
/* 328:328 */    return true;
/* 329:    */  }
/* 330:    */  
/* 348:    */  public static boolean gluUnProject(float winx, float winy, float winz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer obj_pos)
/* 349:    */  {
/* 350:350 */    float[] in = in;
/* 351:351 */    float[] out = out;
/* 352:    */    
/* 353:353 */    __gluMultMatricesf(modelMatrix, projMatrix, finalMatrix);
/* 354:    */    
/* 355:355 */    if (!__gluInvertMatrixf(finalMatrix, finalMatrix)) {
/* 356:356 */      return false;
/* 357:    */    }
/* 358:358 */    in[0] = winx;
/* 359:359 */    in[1] = winy;
/* 360:360 */    in[2] = winz;
/* 361:361 */    in[3] = 1.0F;
/* 362:    */    
/* 364:364 */    in[0] = ((in[0] - viewport.get(viewport.position() + 0)) / viewport.get(viewport.position() + 2));
/* 365:365 */    in[1] = ((in[1] - viewport.get(viewport.position() + 1)) / viewport.get(viewport.position() + 3));
/* 366:    */    
/* 368:368 */    in[0] = (in[0] * 2.0F - 1.0F);
/* 369:369 */    in[1] = (in[1] * 2.0F - 1.0F);
/* 370:370 */    in[2] = (in[2] * 2.0F - 1.0F);
/* 371:    */    
/* 372:372 */    __gluMultMatrixVecf(finalMatrix, in, out);
/* 373:    */    
/* 374:374 */    if (out[3] == 0.0D) {
/* 375:375 */      return false;
/* 376:    */    }
/* 377:377 */    out[3] = (1.0F / out[3]);
/* 378:    */    
/* 379:379 */    obj_pos.put(obj_pos.position() + 0, out[0] * out[3]);
/* 380:380 */    obj_pos.put(obj_pos.position() + 1, out[1] * out[3]);
/* 381:381 */    obj_pos.put(obj_pos.position() + 2, out[2] * out[3]);
/* 382:    */    
/* 383:383 */    return true;
/* 384:    */  }
/* 385:    */  
/* 399:    */  public static void gluPickMatrix(float x, float y, float deltaX, float deltaY, IntBuffer viewport)
/* 400:    */  {
/* 401:401 */    if ((deltaX <= 0.0F) || (deltaY <= 0.0F)) {
/* 402:402 */      return;
/* 403:    */    }
/* 404:    */    
/* 406:406 */    GL11.glTranslatef((viewport.get(viewport.position() + 2) - 2.0F * (x - viewport.get(viewport.position() + 0))) / deltaX, (viewport.get(viewport.position() + 3) - 2.0F * (y - viewport.get(viewport.position() + 1))) / deltaY, 0.0F);
/* 407:    */    
/* 410:410 */    GL11.glScalef(viewport.get(viewport.position() + 2) / deltaX, viewport.get(viewport.position() + 3) / deltaY, 1.0F);
/* 411:    */  }
/* 412:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Project
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */