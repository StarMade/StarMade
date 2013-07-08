/*  1:   */import java.io.PrintStream;
/*  2:   */
/* 33:   */public final class jm
/* 34:   */{
/* 35:35 */  je a = new je();
/* 36:   */  
/* 37:37 */  public jm(String paramString) { Object localObject = new jn(this);
/* 38:   */    
/* 48:48 */    (
/* 49:49 */      localObject = new Thread((Runnable)localObject)).setName(paramString + "_SEGMENT_WRITER_THREAD");
/* 50:50 */    ((Thread)localObject).start();
/* 51:   */  }
/* 52:   */  
/* 53:   */  public final void a(mw parammw) {
/* 54:54 */    while ((parammw != null) && (this.a.a(parammw))) {
/* 55:   */      try {
/* 56:56 */        System.err.println("WAITING TO FINISH WRITING " + parammw);
/* 57:57 */        Thread.sleep(100L);
/* 58:58 */      } catch (InterruptedException localInterruptedException) { 
/* 59:   */        
/* 60:60 */          localInterruptedException;
/* 61:   */      }
/* 62:   */    }
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */