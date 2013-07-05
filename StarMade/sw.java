/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ final class sw
/*     */   implements Runnable
/*     */ {
/*     */   sw(sr paramsr, String[] paramArrayOfString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 207 */       localObject1 = "";
/* 208 */       for (int i = 0; i < this.jdField_a_of_type_ArrayOfJavaLangString.length; i++) {
/* 209 */         localObject1 = (String)localObject1 + " " + this.jdField_a_of_type_ArrayOfJavaLangString[i];
/*     */       }
/* 211 */       sr.a(this.jdField_a_of_type_Sr); Object localObject2 = sy.a();
/* 212 */       localObject2 = new File((String)localObject2);
/* 213 */       if (sr.a())
/*     */       {
/* 216 */         localObject1 = new String[] { sr.a(), "-Djava.net.preferIPv4Stack=true", "-Xmn" + sr.c + "M", "-Xms" + sr.b + "M", "-Xmx" + sr.a + "M", "-Xincgc", "-jar", ((File)localObject2).getAbsolutePath(), "-force", "-port:" + sr.j, localObject1 };
/*     */       }
/*     */       else {
/* 219 */         localObject1 = new String[] { sr.a(), "-Djava.net.preferIPv4Stack=true", "-Xmn" + sr.f + "M", "-Xms" + sr.e + "M", "-Xmx" + sr.d + "M", "-Xincgc", "-jar", ((File)localObject2).getAbsolutePath(), "-force", "-port:" + sr.j, localObject1 };
/*     */       }
/*     */ 
/* 223 */       System.err.println("RUNNING COMMAND: " + localObject1);
/* 224 */       (
/* 225 */         localObject1 = new ProcessBuilder((String[])localObject1))
/* 225 */         .environment();
/*     */ 
/* 227 */       if ((
/* 227 */         localObject2 = new File("./StarMade/"))
/* 227 */         .exists()) {
/* 228 */         ((ProcessBuilder)localObject1).directory(((File)localObject2).getAbsoluteFile());
/* 229 */         ((ProcessBuilder)localObject1).start();
/* 230 */         System.err.println("Exiting because updater staring game");
/* 231 */         System.exit(0);
/* 232 */         return;
/* 233 */       }throw new FileNotFoundException("Cannot find the Install Directory: ./StarMade/");
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */       Object localObject1;
/* 240 */       (localObject1
/* 240 */          = localIOException)
/* 238 */         .printStackTrace();
/* 239 */       d.a((Exception)localObject1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sw
 * JD-Core Version:    0.6.2
 */