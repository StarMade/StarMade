/*   1:    */import java.io.BufferedReader;
/*   2:    */import java.io.FileNotFoundException;
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStreamReader;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.nio.ByteBuffer;
/*   7:    */import java.nio.FloatBuffer;
/*   8:    */import java.nio.IntBuffer;
/*   9:    */import java.util.HashMap;
/*  10:    */import java.util.Map;
/*  11:    */import org.lwjgl.BufferUtils;
/*  12:    */import org.lwjgl.opengl.GL20;
/*  13:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  14:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  15:    */
/* 106:    */public class zj
/* 107:    */{
/* 108:    */  public FloatBuffer a;
/* 109:    */  Map jdField_a_of_type_JavaUtilMap;
/* 110:    */  public zr a;
/* 111:    */  public String a;
/* 112:    */  private String b;
/* 113:    */  int jdField_a_of_type_Int;
/* 114:    */  public boolean a;
/* 115:    */  
/* 116:    */  public zj(String paramString1, String paramString2)
/* 117:    */  {
/* 118:118 */    this.jdField_a_of_type_JavaLangString = paramString1;
/* 119:119 */    this.b = paramString2;
/* 120:120 */    a();
/* 121:121 */    this.jdField_a_of_type_JavaUtilMap = new HashMap();
/* 122:    */  }
/* 123:    */  
/* 140:    */  public void a(int paramInt) {}
/* 141:    */  
/* 157:    */  public final void a()
/* 158:    */  {
/* 159:    */    try
/* 160:    */    {
/* 161:161 */      GlUtil.f();
/* 162:162 */      zj localzj = this;GlUtil.f();int k = GL20.glCreateShader(35633);GlUtil.f();Object localObject1 = new BufferedReader(new InputStreamReader(Ad.a.a(localzj.jdField_a_of_type_JavaLangString)));GlUtil.f(); String str; for (Object localObject2 = ""; (str = ((BufferedReader)localObject1).readLine()) != null; localObject2 = (String)localObject2 + str + "\n") {} (localObject2 = GlUtil.a((localObject1 = ((String)localObject2).getBytes()).length, 0)).put((byte[])localObject1);((ByteBuffer)localObject2).rewind();GL20.glShaderSource(k, (ByteBuffer)localObject2);GlUtil.f();GL20.glCompileShader(k);GlUtil.f(); if ((i = GL20.glGetShader(k, 35713)) != 1) { localObject2 = BufferUtils.createIntBuffer(1);GL20.glGetShader(k, 35716, (IntBuffer)localObject2);GlUtil.f();localObject2 = GL20.glGetShaderInfoLog(k, ((IntBuffer)localObject2).get(0));System.err.println("[SHADER] ERROR COMPILING VERTEX SHADER " + localzj.jdField_a_of_type_JavaLangString + " STATUS: " + i);System.err.println("LOG: " + (String)localObject2);throw new ErrorDialogException("\n" + localzj.jdField_a_of_type_JavaLangString + " \n\n" + (String)localObject2 + "\n"); } int i = this.jdField_a_of_type_JavaLangString == null ? -1 : k;
/* 163:163 */      GlUtil.f();
/* 164:164 */      int j = a();
/* 165:165 */      GlUtil.f();
/* 166:166 */      GlUtil.f();
/* 167:    */      
/* 170:170 */      if (this.jdField_a_of_type_Int != 0) {
/* 171:171 */        GL20.glDeleteProgram(this.jdField_a_of_type_Int);
/* 172:    */      }
/* 173:173 */      GlUtil.f();
/* 174:174 */      this.jdField_a_of_type_Int = GL20.glCreateProgram();
/* 175:175 */      GlUtil.f();
/* 176:    */      
/* 177:177 */      if (i > 0) {
/* 178:178 */        GL20.glAttachShader(this.jdField_a_of_type_Int, i);
/* 179:179 */        GlUtil.f();
/* 180:    */      }
/* 181:    */      
/* 184:184 */      if (j > 0)
/* 185:    */      {
/* 186:186 */        GL20.glAttachShader(this.jdField_a_of_type_Int, j);
/* 187:187 */        GlUtil.f();
/* 188:    */      }
/* 189:    */      
/* 191:191 */      GlUtil.f();
/* 192:    */      
/* 200:200 */      GL20.glLinkProgram(this.jdField_a_of_type_Int);
/* 201:    */      
/* 202:202 */      GlUtil.f();
/* 203:    */      
/* 204:204 */      a(this.jdField_a_of_type_Int);
/* 205:    */      
/* 206:206 */      GlUtil.f();
/* 207:    */      
/* 208:208 */      GL20.glLinkProgram(this.jdField_a_of_type_Int);
/* 209:209 */      GlUtil.f();
/* 210:210 */      localIntBuffer = BufferUtils.createIntBuffer(1);
/* 211:211 */      GlUtil.f();
/* 212:212 */      GL20.glGetProgram(this.jdField_a_of_type_Int, 35714, localIntBuffer);
/* 213:213 */      GlUtil.f();
/* 214:    */      Object localObject3;
/* 215:215 */      if (localIntBuffer.get(0) != 1)
/* 216:    */      {
/* 217:217 */        localObject3 = BufferUtils.createIntBuffer(1);
/* 218:218 */        GL20.glGetProgram(this.jdField_a_of_type_Int, 35716, (IntBuffer)localObject3);
/* 219:    */        
/* 220:220 */        localObject3 = GL20.glGetProgramInfoLog(this.jdField_a_of_type_Int, ((IntBuffer)localObject3).get(0));
/* 221:221 */        throw new ErrorDialogException("\n" + this.jdField_a_of_type_JavaLangString + ", \n" + this.b + " \n\n" + (String)localObject3 + "\nLINK STATUS: " + localIntBuffer.get(0));
/* 222:    */      }
/* 223:    */      
/* 225:225 */      GL20.glValidateProgram(this.jdField_a_of_type_Int);
/* 226:226 */      localIntBuffer.rewind();
/* 227:227 */      GL20.glGetProgram(this.jdField_a_of_type_Int, 35715, localIntBuffer);
/* 228:228 */      GlUtil.f();
/* 229:229 */      if (localIntBuffer.get(0) != 1) {
/* 230:230 */        localObject3 = BufferUtils.createIntBuffer(1);
/* 231:231 */        GL20.glGetProgram(this.jdField_a_of_type_Int, 35716, (IntBuffer)localObject3);
/* 232:232 */        localObject3 = GL20.glGetProgramInfoLog(this.jdField_a_of_type_Int, ((IntBuffer)localObject3).get(0));
/* 233:233 */        throw new ErrorDialogException("\n" + this.jdField_a_of_type_JavaLangString + ", \n" + this.b + " \n\n" + (String)localObject3 + "\nLINK STATUS: " + localIntBuffer.get(0));
/* 235:    */      }
/* 236:    */      
/* 239:    */    }
/* 240:    */    catch (FileNotFoundException localFileNotFoundException)
/* 241:    */    {
/* 243:243 */      localIntBuffer = null;
/* 244:    */      
/* 247:247 */      localFileNotFoundException.printStackTrace();
/* 248:    */    } catch (IOException localIOException) {
/* 249:245 */      IntBuffer localIntBuffer = null;
/* 250:    */      
/* 251:247 */      localIOException.printStackTrace();
/* 252:    */    }
/* 253:    */    
/* 254:248 */    GlUtil.f();
/* 255:249 */    this.jdField_a_of_type_Boolean = true;
/* 256:    */  }
/* 257:    */  
/* 258:252 */  private int a() { if (this.b == null) {
/* 259:253 */      return -1;
/* 260:    */    }
/* 261:255 */    int i = GL20.glCreateShader(35632);
/* 262:    */    
/* 265:259 */    Object localObject2 = new BufferedReader(new InputStreamReader(Ad.a.a(this.b)));
/* 266:    */    
/* 269:263 */    String str2 = "";
/* 270:264 */    Object localObject1; while ((localObject1 = ((BufferedReader)localObject2).readLine()) != null) {
/* 271:265 */      str2 = str2 + (String)localObject1 + "\n";
/* 272:    */    }
/* 273:    */    
/* 275:269 */    (localObject2 = GlUtil.a((localObject1 = str2.getBytes()).length, 0)).put((byte[])localObject1);
/* 276:270 */    ((ByteBuffer)localObject2).rewind();
/* 277:271 */    GL20.glShaderSource(i, (ByteBuffer)localObject2);
/* 278:    */    
/* 279:273 */    GL20.glCompileShader(i);
/* 280:    */    int j;
/* 281:275 */    String str1; if ((j = GL20.glGetShader(i, 35713)) != 1)
/* 282:    */    {
/* 283:277 */      localObject2 = BufferUtils.createIntBuffer(1);
/* 284:278 */      GL20.glGetShader(i, 35716, (IntBuffer)localObject2);
/* 285:    */      
/* 286:280 */      str1 = GL20.glGetShaderInfoLog(i, ((IntBuffer)localObject2).get(0));
/* 287:    */      
/* 288:282 */      System.err.println("[SHADER] ERROR COMPILING FRAGMENT SHADER " + this.jdField_a_of_type_JavaLangString + " STATUS: " + j);
/* 289:283 */      System.err.println("LOG: " + str1);
/* 290:284 */      throw new ErrorDialogException("\n" + this.b + " \n\n" + str1 + "\n");
/* 291:    */    }
/* 292:    */    
/* 293:287 */    return str1;
/* 294:    */  }
/* 295:    */  
/* 325:    */  public final int a(String paramString)
/* 326:    */  {
/* 327:    */    Integer localInteger;
/* 328:    */    
/* 356:    */    int i;
/* 357:    */    
/* 385:379 */    if ((localInteger = (Integer)this.jdField_a_of_type_JavaUtilMap.get(paramString)) == null) {
/* 386:380 */      i = GL20.glGetUniformLocation(this.jdField_a_of_type_Int, paramString);
/* 387:381 */      this.jdField_a_of_type_JavaUtilMap.put(paramString, Integer.valueOf(i));
/* 388:    */      
/* 389:383 */      return i;
/* 390:    */    }
/* 391:385 */    return i.intValue();
/* 392:    */  }
/* 393:    */  
/* 423:    */  public final void b()
/* 424:    */  {
/* 425:419 */    if ((!xu.w.b()) || (xu.A.b())) {
/* 426:420 */      return;
/* 427:    */    }
/* 428:422 */    GL20.glUseProgram(this.jdField_a_of_type_Int);
/* 429:423 */    if (this.jdField_a_of_type_Zr != null) {
/* 430:424 */      this.jdField_a_of_type_Zr.a(this);
/* 431:    */    }
/* 432:    */  }
/* 433:    */  
/* 434:    */  public final void c() {
/* 435:429 */    if ((!xu.w.b()) || (xu.A.b())) {
/* 436:430 */      return;
/* 437:    */    }
/* 438:432 */    GL20.glUseProgram(this.jdField_a_of_type_Int);
/* 439:    */  }
/* 440:    */  
/* 476:    */  public String toString()
/* 477:    */  {
/* 478:472 */    return this.jdField_a_of_type_JavaLangString + ", " + this.b + " bound to " + this.jdField_a_of_type_Int + " with interface " + this.jdField_a_of_type_Zr;
/* 479:    */  }
/* 480:    */  
/* 485:    */  public final void d()
/* 486:    */  {
/* 487:481 */    if ((!xu.w.b()) || (xu.A.b())) {
/* 488:482 */      return;
/* 489:    */    }
/* 490:484 */    if (this.jdField_a_of_type_Zr != null) {
/* 491:485 */      this.jdField_a_of_type_Zr.d();
/* 492:    */    }
/* 493:487 */    GL20.glUseProgram(0);
/* 494:    */  }
/* 495:    */  
/* 496:    */  public static void e() {
/* 497:491 */    if ((!xu.w.b()) || (xu.A.b())) {
/* 498:492 */      return;
/* 499:    */    }
/* 500:494 */    GL20.glUseProgram(0);
/* 501:    */  }
/* 502:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */