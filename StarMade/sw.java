/*   1:    */import java.io.File;
/*   2:    */import java.io.FileNotFoundException;
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */
/* 198:    */final class sw
/* 199:    */  implements Runnable
/* 200:    */{
/* 201:    */  sw(sr paramsr, String[] paramArrayOfString) {}
/* 202:    */  
/* 203:    */  public final void run()
/* 204:    */  {
/* 205:    */    try
/* 206:    */    {
/* 207:207 */      localObject1 = "";
/* 208:208 */      for (int i = 0; i < this.jdField_a_of_type_ArrayOfJavaLangString.length; i++) {
/* 209:209 */        localObject1 = (String)localObject1 + " " + this.jdField_a_of_type_ArrayOfJavaLangString[i];
/* 210:    */      }
/* 211:211 */      sr.a(this.jdField_a_of_type_Sr);Object localObject2 = sy.a();
/* 212:212 */      localObject2 = new File((String)localObject2);
/* 213:213 */      if (sr.a())
/* 214:    */      {
/* 216:216 */        localObject1 = new String[] { sr.a(), "-Djava.net.preferIPv4Stack=true", "-Xmn" + sr.c + "M", "-Xms" + sr.b + "M", "-Xmx" + sr.a + "M", "-Xincgc", "-jar", ((File)localObject2).getAbsolutePath(), "-force", "-port:" + sr.j, localObject1 };
/* 217:    */      }
/* 218:    */      else {
/* 219:219 */        localObject1 = new String[] { sr.a(), "-Djava.net.preferIPv4Stack=true", "-Xmn" + sr.f + "M", "-Xms" + sr.e + "M", "-Xmx" + sr.d + "M", "-Xincgc", "-jar", ((File)localObject2).getAbsolutePath(), "-force", "-port:" + sr.j, localObject1 };
/* 220:    */      }
/* 221:    */      
/* 223:223 */      System.err.println("RUNNING COMMAND: " + localObject1);
/* 224:224 */      (
/* 225:225 */        localObject1 = new ProcessBuilder((String[])localObject1)).environment();
/* 226:    */      
/* 227:227 */      if ((localObject2 = new File("./StarMade/")).exists()) {
/* 228:228 */        ((ProcessBuilder)localObject1).directory(((File)localObject2).getAbsoluteFile());
/* 229:229 */        ((ProcessBuilder)localObject1).start();
/* 230:230 */        System.err.println("Exiting because updater staring game");
/* 231:231 */        System.exit(0);
/* 232:232 */        return; }
/* 233:233 */      throw new FileNotFoundException("Cannot find the Install Directory: ./StarMade/");
/* 234:    */    }
/* 235:    */    catch (IOException localIOException)
/* 236:    */    {
/* 237:    */      Object localObject1;
/* 238:238 */      
/* 239:    */      
/* 240:240 */        (localObject1 = localIOException).printStackTrace();d.a((Exception)localObject1);
/* 241:    */    }
/* 242:    */  }
/* 243:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */