/*     */ import java.io.PrintStream;
/*     */ 
/*     */ final class y
/*     */   implements Runnable
/*     */ {
/*     */   y(x paramx)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/* 299 */     System.out.println("[CLIENT] CLIENT SHUTDOWN. Dumping client data!");
/*     */     try { this.a.g();
/* 302 */       System.out.println("[CLIENT] CLIENT SHUTDOWN. client data saved!");
/* 303 */       xu.b();
/*     */       return; } catch (Exception localException) {
/* 307 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     y
 * JD-Core Version:    0.6.2
 */