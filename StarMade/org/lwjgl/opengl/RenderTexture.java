/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */
/* 163:    */public final class RenderTexture
/* 164:    */{
/* 165:    */  private static final int WGL_BIND_TO_TEXTURE_RGB_ARB = 8304;
/* 166:    */  private static final int WGL_BIND_TO_TEXTURE_RGBA_ARB = 8305;
/* 167:    */  private static final int WGL_TEXTURE_FORMAT_ARB = 8306;
/* 168:    */  private static final int WGL_TEXTURE_TARGET_ARB = 8307;
/* 169:    */  private static final int WGL_MIPMAP_TEXTURE_ARB = 8308;
/* 170:    */  private static final int WGL_TEXTURE_RGB_ARB = 8309;
/* 171:    */  private static final int WGL_TEXTURE_RGBA_ARB = 8310;
/* 172:    */  private static final int WGL_TEXTURE_CUBE_MAP_ARB = 8312;
/* 173:    */  private static final int WGL_TEXTURE_1D_ARB = 8313;
/* 174:    */  private static final int WGL_TEXTURE_2D_ARB = 8314;
/* 175:    */  private static final int WGL_NO_TEXTURE_ARB = 8311;
/* 176:    */  static final int WGL_MIPMAP_LEVEL_ARB = 8315;
/* 177:    */  static final int WGL_CUBE_MAP_FACE_ARB = 8316;
/* 178:    */  static final int WGL_TEXTURE_CUBE_MAP_POSITIVE_X_ARB = 8317;
/* 179:    */  static final int WGL_TEXTURE_CUBE_MAP_NEGATIVE_X_ARB = 8318;
/* 180:    */  static final int WGL_TEXTURE_CUBE_MAP_POSITIVE_Y_ARB = 8319;
/* 181:    */  static final int WGL_TEXTURE_CUBE_MAP_NEGATIVE_Y_ARB = 8320;
/* 182:    */  static final int WGL_TEXTURE_CUBE_MAP_POSITIVE_Z_ARB = 8321;
/* 183:    */  static final int WGL_TEXTURE_CUBE_MAP_NEGATIVE_Z_ARB = 8322;
/* 184:    */  static final int WGL_FRONT_LEFT_ARB = 8323;
/* 185:    */  static final int WGL_FRONT_RIGHT_ARB = 8324;
/* 186:    */  static final int WGL_BACK_LEFT_ARB = 8325;
/* 187:    */  static final int WGL_BACK_RIGHT_ARB = 8326;
/* 188:    */  private static final int WGL_BIND_TO_TEXTURE_RECTANGLE_RGB_NV = 8352;
/* 189:    */  private static final int WGL_BIND_TO_TEXTURE_RECTANGLE_RGBA_NV = 8353;
/* 190:    */  private static final int WGL_TEXTURE_RECTANGLE_NV = 8354;
/* 191:    */  private static final int WGL_BIND_TO_TEXTURE_DEPTH_NV = 8355;
/* 192:    */  private static final int WGL_BIND_TO_TEXTURE_RECTANGLE_DEPTH_NV = 8356;
/* 193:    */  private static final int WGL_DEPTH_TEXTURE_FORMAT_NV = 8357;
/* 194:    */  private static final int WGL_TEXTURE_DEPTH_COMPONENT_NV = 8358;
/* 195:    */  static final int WGL_DEPTH_COMPONENT_NV = 8359;
/* 196:    */  public static final int RENDER_TEXTURE_1D = 8313;
/* 197:    */  public static final int RENDER_TEXTURE_2D = 8314;
/* 198:    */  public static final int RENDER_TEXTURE_RECTANGLE = 8354;
/* 199:    */  public static final int RENDER_TEXTURE_CUBE_MAP = 8312;
/* 200:    */  IntBuffer pixelFormatCaps;
/* 201:    */  IntBuffer pBufferAttribs;
/* 202:    */  
/* 203:    */  public RenderTexture(boolean useRGB, boolean useRGBA, boolean useDepth, boolean isRectangle, int target, int mipmaps)
/* 204:    */  {
/* 205:205 */    if ((useRGB) && (useRGBA)) {
/* 206:206 */      throw new IllegalArgumentException("A RenderTexture can't be both RGB and RGBA.");
/* 207:    */    }
/* 208:208 */    if (mipmaps < 0) {
/* 209:209 */      throw new IllegalArgumentException("The mipmap levels can't be negative.");
/* 210:    */    }
/* 211:211 */    if ((isRectangle) && (target != 8354)) {
/* 212:212 */      throw new IllegalArgumentException("When the RenderTexture is rectangle the target must be RENDER_TEXTURE_RECTANGLE.");
/* 213:    */    }
/* 214:214 */    this.pixelFormatCaps = BufferUtils.createIntBuffer(4);
/* 215:215 */    this.pBufferAttribs = BufferUtils.createIntBuffer(8);
/* 216:    */    
/* 217:217 */    if (useRGB) {
/* 218:218 */      this.pixelFormatCaps.put(isRectangle ? 8352 : 8304);
/* 219:219 */      this.pixelFormatCaps.put(1);
/* 220:    */      
/* 221:221 */      this.pBufferAttribs.put(8306);
/* 222:222 */      this.pBufferAttribs.put(8309);
/* 223:223 */    } else if (useRGBA) {
/* 224:224 */      this.pixelFormatCaps.put(isRectangle ? 8353 : 8305);
/* 225:225 */      this.pixelFormatCaps.put(1);
/* 226:    */      
/* 227:227 */      this.pBufferAttribs.put(8306);
/* 228:228 */      this.pBufferAttribs.put(8310);
/* 229:    */    }
/* 230:    */    
/* 231:231 */    if (useDepth) {
/* 232:232 */      this.pixelFormatCaps.put(isRectangle ? 8356 : 8355);
/* 233:233 */      this.pixelFormatCaps.put(1);
/* 234:    */      
/* 235:235 */      this.pBufferAttribs.put(8357);
/* 236:236 */      this.pBufferAttribs.put(8358);
/* 237:    */    }
/* 238:    */    
/* 239:239 */    this.pBufferAttribs.put(8307);
/* 240:240 */    this.pBufferAttribs.put(target);
/* 241:    */    
/* 242:242 */    if (mipmaps != 0) {
/* 243:243 */      this.pBufferAttribs.put(8308);
/* 244:244 */      this.pBufferAttribs.put(mipmaps);
/* 245:    */    }
/* 246:    */    
/* 247:247 */    this.pixelFormatCaps.flip();
/* 248:248 */    this.pBufferAttribs.flip();
/* 249:    */  }
/* 250:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.RenderTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */