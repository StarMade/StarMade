/*   1:    */import java.io.IOException;
/*   2:    */import org.schema.game.common.crashreporter.CrashReporter;
/*   3:    */
/* 258:    */public final class la
/* 259:    */  implements Runnable
/* 260:    */{
/* 261:    */  public la(CrashReporter paramCrashReporter) {}
/* 262:    */  
/* 263:    */  public final void run()
/* 264:    */  {
/* 265:    */    try
/* 266:    */    {
/* 267:267 */      CrashReporter.a(this.a); return;
/* 268:    */    } catch (IOException localIOException2) { IOException localIOException1;
/* 269:269 */      (localIOException1 = 
/* 270:    */      
/* 271:271 */        localIOException2).printStackTrace();d.a(localIOException1, true);
/* 272:    */    }
/* 273:    */  }
/* 274:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     la
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */