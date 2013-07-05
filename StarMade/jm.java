/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public final class jm
/*    */ {
/* 35 */   je a = new je();
/*    */ 
/* 37 */   public jm(String paramString) { Object localObject = new jn(this);
/*    */ 
/* 48 */     (
/* 49 */       localObject = new Thread((Runnable)localObject))
/* 49 */       .setName(paramString + "_SEGMENT_WRITER_THREAD");
/* 50 */     ((Thread)localObject).start(); }
/*    */ 
/*    */   public final void a(mw parammw)
/*    */   {
/* 54 */     while ((parammw != null) && (this.a.a(parammw)))
/*    */       try {
/* 56 */         System.err.println("WAITING TO FINISH WRITING " + parammw);
/* 57 */         Thread.sleep(100L);
/*    */       }
/*    */       catch (InterruptedException localInterruptedException) {
/* 60 */         localInterruptedException.printStackTrace();
/*    */       }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jm
 * JD-Core Version:    0.6.2
 */