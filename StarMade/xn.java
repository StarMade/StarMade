/*   1:    */import java.io.File;
/*   2:    */import java.io.IOException;
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.net.SocketException;
/*   5:    */import java.util.Arrays;
/*   6:    */import org.schema.schine.network.client.ClientState;
/*   7:    */import org.schema.schine.network.exception.DisconnectException;
/*   8:    */
/* 247:    */final class xn
/* 248:    */  implements Runnable
/* 249:    */{
/* 250:    */  xn(String[] paramArrayOfString) {}
/* 251:    */  
/* 252:    */  public final void run()
/* 253:    */  {
/* 254:    */    try
/* 255:    */    {
/* 256:256 */      localObject1 = "";
/* 257:257 */      for (int i = 0; i < this.a.length; i++) {
/* 258:258 */        localObject1 = (String)localObject1 + " " + this.a[i];
/* 259:    */      }
/* 260:260 */      Object localObject2 = "./CrashAndBugReport.jar";
/* 261:261 */      localObject2 = new File((String)localObject2);
/* 262:262 */      localObject1 = new String[] { "java", "-jar", ((File)localObject2).getAbsolutePath(), localObject1 };
/* 263:    */      
/* 265:265 */      System.err.println("[CRASHREPORTER] RUNNING COMMAND: " + Arrays.toString((Object[])localObject1));
/* 266:266 */      (
/* 267:267 */        localObject1 = new ProcessBuilder((String[])localObject1)).environment();
/* 268:    */      
/* 271:271 */      ((ProcessBuilder)localObject1).start();
/* 272:272 */      System.err.println("Exiting because of starting crash report");
/* 273:273 */      if (xm.a != null) {
/* 274:274 */        xm.a.exit();
/* 275:    */      }
/* 276:    */      
/* 277:    */      return;
/* 278:    */    }
/* 279:    */    catch (IOException localIOException)
/* 280:    */    {
/* 281:    */      Object localObject1;
/* 282:282 */      (localObject1 = 
/* 283:    */      
/* 288:288 */        localIOException).printStackTrace();
/* 289:283 */      if ((localObject1 instanceof SocketException)) {
/* 290:284 */        xm.a(new DisconnectException("You have been disconnected from the Server (either connection problem or server crash)\nActualException: " + localObject1.getClass().getSimpleName()));return;
/* 291:    */      }
/* 292:286 */      xm.a((Exception)localObject1);
/* 293:    */    }
/* 294:    */  }
/* 295:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */