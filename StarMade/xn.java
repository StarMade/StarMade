/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.SocketException;
/*     */ import java.util.Arrays;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.exception.DisconnectException;
/*     */ 
/*     */ final class xn
/*     */   implements Runnable
/*     */ {
/*     */   xn(String[] paramArrayOfString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 256 */       localObject1 = "";
/* 257 */       for (int i = 0; i < this.a.length; i++) {
/* 258 */         localObject1 = (String)localObject1 + " " + this.a[i];
/*     */       }
/* 260 */       Object localObject2 = "./CrashAndBugReport.jar";
/* 261 */       localObject2 = new File((String)localObject2);
/* 262 */       localObject1 = new String[] { "java", "-jar", ((File)localObject2).getAbsolutePath(), localObject1 };
/*     */ 
/* 265 */       System.err.println("[CRASHREPORTER] RUNNING COMMAND: " + Arrays.toString((Object[])localObject1));
/* 266 */       (
/* 267 */         localObject1 = new ProcessBuilder((String[])localObject1))
/* 267 */         .environment();
/*     */ 
/* 271 */       ((ProcessBuilder)localObject1).start();
/* 272 */       System.err.println("Exiting because of starting crash report");
/* 273 */       if (xm.a != null)
/* 274 */         xm.a.exit();
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */       Object localObject1;
/* 281 */       (
/* 282 */         localObject1 = 
/* 288 */         localIOException).printStackTrace();
/* 283 */       if ((localObject1 instanceof SocketException)) {
/* 284 */         xm.a(new DisconnectException("You have been disconnected from the Server (either connection problem or server crash)\nActualException: " + localObject1.getClass().getSimpleName())); return;
/*     */       }
/* 286 */       xm.a((Exception)localObject1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xn
 * JD-Core Version:    0.6.2
 */