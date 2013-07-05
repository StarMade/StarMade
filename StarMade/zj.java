/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL20;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ 
/*     */ public class zj
/*     */ {
/*     */   public FloatBuffer a;
/*     */   public Map a;
/*     */   public zn a;
/*     */   public String a;
/*     */   private String b;
/*     */   public int a;
/*     */ 
/*     */   public zj(String paramString1, String paramString2)
/*     */   {
/* 116 */     this.jdField_a_of_type_JavaLangString = paramString1;
/* 117 */     this.b = paramString2;
/* 118 */     a();
/* 119 */     this.jdField_a_of_type_JavaUtilMap = new HashMap();
/*     */   }
/*     */ 
/*     */   public void a(int paramInt)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */     try
/*     */     {
/* 159 */       GlUtil.f();
/* 160 */       zj localzj = this; GlUtil.f(); int k = GL20.glCreateShader(35633); GlUtil.f(); Object localObject1 = new BufferedReader(new InputStreamReader(zZ.a.a(localzj.jdField_a_of_type_JavaLangString))); GlUtil.f();
/*     */       String str;
/* 160 */       for (Object localObject2 = ""; (str = ((BufferedReader)localObject1).readLine()) != null; localObject2 = (String)localObject2 + str + "\n");
/* 160 */       (localObject2 = GlUtil.a((localObject1 = ((String)localObject2).getBytes()).length, 0)).put((byte[])localObject1); ((ByteBuffer)localObject2).rewind(); GL20.glShaderSource(k, (ByteBuffer)localObject2); GlUtil.f(); GL20.glCompileShader(k); GlUtil.f(); if ((i = GL20.glGetShader(k, 35713)) != 1) { localObject2 = BufferUtils.createIntBuffer(1); GL20.glGetShader(k, 35716, (IntBuffer)localObject2); GlUtil.f(); localObject2 = GL20.glGetShaderInfoLog(k, ((IntBuffer)localObject2).get(0)); System.err.println("[SHADER] ERROR COMPILING VERTEX SHADER " + localzj.jdField_a_of_type_JavaLangString + " STATUS: " + i); System.err.println("LOG: " + (String)localObject2); throw new ErrorDialogException("\n" + localzj.jdField_a_of_type_JavaLangString + " \n\n" + (String)localObject2 + "\n"); } int i = this.jdField_a_of_type_JavaLangString == null ? -1 : k;
/* 161 */       GlUtil.f();
/* 162 */       int j = a();
/* 163 */       GlUtil.f();
/* 164 */       GlUtil.f();
/*     */ 
/* 168 */       if (this.jdField_a_of_type_Int != 0) {
/* 169 */         GL20.glDeleteProgram(this.jdField_a_of_type_Int);
/*     */       }
/* 171 */       GlUtil.f();
/* 172 */       this.jdField_a_of_type_Int = GL20.glCreateProgram();
/* 173 */       GlUtil.f();
/*     */ 
/* 175 */       if (i > 0) {
/* 176 */         GL20.glAttachShader(this.jdField_a_of_type_Int, i);
/* 177 */         GlUtil.f();
/*     */       }
/*     */ 
/* 182 */       if (j > 0)
/*     */       {
/* 184 */         GL20.glAttachShader(this.jdField_a_of_type_Int, j);
/* 185 */         GlUtil.f();
/*     */       }
/*     */ 
/* 189 */       GlUtil.f();
/*     */ 
/* 198 */       GL20.glLinkProgram(this.jdField_a_of_type_Int);
/*     */ 
/* 200 */       GlUtil.f();
/*     */ 
/* 202 */       a(this.jdField_a_of_type_Int);
/*     */ 
/* 204 */       GlUtil.f();
/*     */ 
/* 206 */       GL20.glLinkProgram(this.jdField_a_of_type_Int);
/* 207 */       GlUtil.f();
/* 208 */       localIntBuffer = BufferUtils.createIntBuffer(1);
/* 209 */       GlUtil.f();
/* 210 */       GL20.glGetProgram(this.jdField_a_of_type_Int, 35714, localIntBuffer);
/* 211 */       GlUtil.f();
/*     */       Object localObject3;
/* 213 */       if (localIntBuffer.get(0) != 1)
/*     */       {
/* 215 */         localObject3 = BufferUtils.createIntBuffer(1);
/* 216 */         GL20.glGetProgram(this.jdField_a_of_type_Int, 35716, (IntBuffer)localObject3);
/*     */ 
/* 218 */         localObject3 = GL20.glGetProgramInfoLog(this.jdField_a_of_type_Int, ((IntBuffer)localObject3).get(0));
/* 219 */         throw new ErrorDialogException("\n" + this.jdField_a_of_type_JavaLangString + ", \n" + this.b + " \n\n" + (String)localObject3 + "\nLINK STATUS: " + localIntBuffer.get(0));
/*     */       }
/*     */ 
/* 223 */       GL20.glValidateProgram(this.jdField_a_of_type_Int);
/* 224 */       localIntBuffer.rewind();
/* 225 */       GL20.glGetProgram(this.jdField_a_of_type_Int, 35715, localIntBuffer);
/* 226 */       GlUtil.f();
/* 227 */       if (localIntBuffer.get(0) != 1) {
/* 228 */         localObject3 = BufferUtils.createIntBuffer(1);
/* 229 */         GL20.glGetProgram(this.jdField_a_of_type_Int, 35716, (IntBuffer)localObject3);
/* 230 */         localObject3 = GL20.glGetProgramInfoLog(this.jdField_a_of_type_Int, ((IntBuffer)localObject3).get(0));
/* 231 */         throw new ErrorDialogException("\n" + this.jdField_a_of_type_JavaLangString + ", \n" + this.b + " \n\n" + (String)localObject3 + "\nLINK STATUS: " + localIntBuffer.get(0));
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (FileNotFoundException localFileNotFoundException)
/*     */     {
/* 241 */       localIntBuffer = null;
/*     */ 
/* 245 */       localFileNotFoundException.printStackTrace();
/*     */     } catch (IOException localIOException) {
/* 243 */       IntBuffer localIntBuffer = null;
/*     */ 
/* 245 */       localIOException.printStackTrace();
/*     */     }
/*     */ 
/* 246 */     GlUtil.f();
/*     */   }
/*     */   private int a() {
/* 249 */     if (this.b == null) {
/* 250 */       return -1;
/*     */     }
/* 252 */     int i = GL20.glCreateShader(35632);
/*     */ 
/* 256 */     Object localObject2 = new BufferedReader(new InputStreamReader(zZ.a.a(this.b)));
/*     */ 
/* 260 */     String str2 = "";
/*     */     Object localObject1;
/* 261 */     while ((localObject1 = ((BufferedReader)localObject2).readLine()) != null) {
/* 262 */       str2 = str2 + (String)localObject1 + "\n";
/*     */     }
/*     */ 
/* 265 */     (
/* 266 */       localObject2 = GlUtil.a((
/* 265 */       localObject1 = str2.getBytes()).length, 
/* 265 */       0))
/* 266 */       .put((byte[])localObject1);
/* 267 */     ((ByteBuffer)localObject2).rewind();
/* 268 */     GL20.glShaderSource(i, (ByteBuffer)localObject2);
/*     */ 
/* 270 */     GL20.glCompileShader(i);
/*     */     int j;
/*     */     String str1;
/* 272 */     if ((
/* 272 */       j = GL20.glGetShader(i, 35713)) != 
/* 272 */       1)
/*     */     {
/* 274 */       localObject2 = BufferUtils.createIntBuffer(1);
/* 275 */       GL20.glGetShader(i, 35716, (IntBuffer)localObject2);
/*     */ 
/* 277 */       str1 = GL20.glGetShaderInfoLog(i, ((IntBuffer)localObject2).get(0));
/*     */ 
/* 279 */       System.err.println("[SHADER] ERROR COMPILING FRAGMENT SHADER " + this.jdField_a_of_type_JavaLangString + " STATUS: " + j);
/* 280 */       System.err.println("LOG: " + str1);
/* 281 */       throw new ErrorDialogException("\n" + this.b + " \n\n" + str1 + "\n");
/*     */     }
/*     */ 
/* 284 */     return str1;
/*     */   }
/*     */ 
/*     */   public final int a(String paramString)
/*     */   {
/*     */     Integer localInteger;
/*     */     int i;
/* 376 */     if ((
/* 376 */       localInteger = (Integer)this.jdField_a_of_type_JavaUtilMap.get(paramString)) == null)
/*     */     {
/* 377 */       i = GL20.glGetUniformLocation(this.jdField_a_of_type_Int, paramString);
/* 378 */       this.jdField_a_of_type_JavaUtilMap.put(paramString, Integer.valueOf(i));
/*     */ 
/* 380 */       return i;
/*     */     }
/* 382 */     return i.intValue();
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 416 */     if ((!xu.w.b()) || (xu.A.b())) {
/* 417 */       return;
/*     */     }
/* 419 */     GL20.glUseProgram(this.jdField_a_of_type_Int);
/* 420 */     if (this.jdField_a_of_type_Zn != null)
/* 421 */       this.jdField_a_of_type_Zn.a(this);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 426 */     if ((!xu.w.b()) || (xu.A.b())) {
/* 427 */       return;
/*     */     }
/* 429 */     GL20.glUseProgram(this.jdField_a_of_type_Int);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 469 */     return this.jdField_a_of_type_JavaLangString + ", " + this.b + " bound to " + this.jdField_a_of_type_Int + " with interface " + this.jdField_a_of_type_Zn;
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 478 */     if ((!xu.w.b()) || (xu.A.b())) {
/* 479 */       return;
/*     */     }
/* 481 */     if (this.jdField_a_of_type_Zn != null) {
/* 482 */       this.jdField_a_of_type_Zn.d();
/*     */     }
/* 484 */     GL20.glUseProgram(0);
/*     */   }
/*     */ 
/*     */   public static void e() {
/* 488 */     if ((!xu.w.b()) || (xu.A.b())) {
/* 489 */       return;
/*     */     }
/* 491 */     GL20.glUseProgram(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zj
 * JD-Core Version:    0.6.2
 */