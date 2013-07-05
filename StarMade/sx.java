/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ final class sx
/*     */   implements Runnable
/*     */ {
/*     */   sx(sr paramsr, String[] paramArrayOfString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 255 */       localObject1 = "";
/* 256 */       for (int i = 0; i < this.jdField_a_of_type_ArrayOfJavaLangString.length; i++) {
/* 257 */         localObject1 = (String)localObject1 + " " + this.jdField_a_of_type_ArrayOfJavaLangString[i];
/*     */       }
/* 259 */       sr.a(this.jdField_a_of_type_Sr); Object localObject2 = sy.a();
/* 260 */       localObject2 = new File((String)localObject2);
/* 261 */       localObject1 = new String[] { sr.a(), "-Djava.net.preferIPv4Stack=true", "-Xmn" + sr.i + "M", "-Xms" + sr.h + "M", "-Xmx" + sr.g + "M", "-Xincgc", "-jar", ((File)localObject2).getAbsolutePath(), "-server", "-gui", "-port:" + sr.j, localObject1 };
/*     */ 
/* 264 */       System.err.println("RUNNING COMMAND: " + localObject1);
/* 265 */       (
/* 266 */         localObject1 = new ProcessBuilder((String[])localObject1))
/* 266 */         .environment();
/*     */ 
/* 268 */       if ((
/* 268 */         localObject2 = new File("./StarMade/"))
/* 268 */         .exists()) {
/* 269 */         ((ProcessBuilder)localObject1).directory(((File)localObject2).getAbsoluteFile());
/* 270 */         ((ProcessBuilder)localObject1).start();
/* 271 */         System.exit(0);
/* 272 */         return;
/* 273 */       }throw new FileNotFoundException("Cannot find the Install Directory: ./StarMade/");
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */       Object localObject1;
/* 280 */       (localObject1
/* 280 */          = localIOException)
/* 278 */         .printStackTrace();
/* 279 */       d.a((Exception)localObject1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sx
 * JD-Core Version:    0.6.2
 */