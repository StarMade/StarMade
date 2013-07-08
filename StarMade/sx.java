/*   1:    */import java.io.File;
/*   2:    */import java.io.FileNotFoundException;
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */
/* 246:    */final class sx
/* 247:    */  implements Runnable
/* 248:    */{
/* 249:    */  sx(sr paramsr, String[] paramArrayOfString) {}
/* 250:    */  
/* 251:    */  public final void run()
/* 252:    */  {
/* 253:    */    try
/* 254:    */    {
/* 255:255 */      localObject1 = "";
/* 256:256 */      for (int i = 0; i < this.jdField_a_of_type_ArrayOfJavaLangString.length; i++) {
/* 257:257 */        localObject1 = (String)localObject1 + " " + this.jdField_a_of_type_ArrayOfJavaLangString[i];
/* 258:    */      }
/* 259:259 */      sr.a(this.jdField_a_of_type_Sr);Object localObject2 = sy.a();
/* 260:260 */      localObject2 = new File((String)localObject2);
/* 261:261 */      localObject1 = new String[] { sr.a(), "-Djava.net.preferIPv4Stack=true", "-Xmn" + sr.i + "M", "-Xms" + sr.h + "M", "-Xmx" + sr.g + "M", "-Xincgc", "-jar", ((File)localObject2).getAbsolutePath(), "-server", "-gui", "-port:" + sr.j, localObject1 };
/* 262:    */      
/* 264:264 */      System.err.println("RUNNING COMMAND: " + localObject1);
/* 265:265 */      (
/* 266:266 */        localObject1 = new ProcessBuilder((String[])localObject1)).environment();
/* 267:    */      
/* 268:268 */      if ((localObject2 = new File("./StarMade/")).exists()) {
/* 269:269 */        ((ProcessBuilder)localObject1).directory(((File)localObject2).getAbsoluteFile());
/* 270:270 */        ((ProcessBuilder)localObject1).start();
/* 271:271 */        System.exit(0);
/* 272:272 */        return; }
/* 273:273 */      throw new FileNotFoundException("Cannot find the Install Directory: ./StarMade/");
/* 274:    */    }
/* 275:    */    catch (IOException localIOException)
/* 276:    */    {
/* 277:    */      Object localObject1;
/* 278:278 */      
/* 279:    */      
/* 280:280 */        (localObject1 = localIOException).printStackTrace();d.a((Exception)localObject1);
/* 281:    */    }
/* 282:    */  }
/* 283:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */