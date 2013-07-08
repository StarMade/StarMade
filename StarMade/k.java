/*   1:    */import java.io.File;
/*   2:    */import java.io.FileInputStream;
/*   3:    */import java.io.FileNotFoundException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.lang.reflect.Method;
/*   7:    */import java.net.JarURLConnection;
/*   8:    */import java.net.MalformedURLException;
/*   9:    */import java.net.URL;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.Enumeration;
/*  12:    */import java.util.HashMap;
/*  13:    */import java.util.List;
/*  14:    */import java.util.jar.JarEntry;
/*  15:    */import java.util.jar.JarFile;
/*  16:    */import org.schema.schine.graphicsengine.core.ResourceException;
/*  17:    */import org.schema.schine.network.Command;
/*  18:    */import org.schema.schine.network.CommandMap;
/*  19:    */
/*  88:    */public final class k
/*  89:    */{
/*  90:    */  public final HashMap a(String paramString)
/*  91:    */  {
/*  92: 92 */    HashMap localHashMap = new HashMap();
/*  93:    */    
/*  94: 94 */    if (!(localObject = new String(paramString)).startsWith("/")) {
/*  95: 95 */      localObject = "/" + (String)localObject;
/*  96:    */    }
/*  97: 97 */    Object localObject = ((String)localObject).replace('.', '/');
/*  98:    */    
/* 104:    */    try
/* 105:    */    {
/* 106:106 */      localObject = a((String)localObject);
/* 107:    */      
/* 120:120 */      if (!(localObject = new File(((URL)localObject).getFile())).exists())
/* 121:    */      {
/* 122:122 */        throw new ResourceException("ERROR: no content in Resource: " + ((File)localObject).getAbsolutePath());
/* 123:    */      }
/* 124:    */      
/* 125:    */      File[] arrayOfFile;
/* 126:    */      
/* 127:127 */      int i = (arrayOfFile = ((File)localObject).listFiles()).length; for (int j = 0; j < i; j++)
/* 128:    */      {
/* 131:131 */        if ((localObject = arrayOfFile[j].getName()).endsWith(".class"))
/* 132:    */        {
/* 133:133 */          localObject = ((String)localObject).substring(0, ((String)localObject).length() - 6);
/* 134:    */          
/* 135:    */          try
/* 136:    */          {
/* 137:137 */            if (((localObject = Class.forName(paramString + "." + (String)localObject).newInstance()) instanceof Command))
/* 138:    */            {
/* 139:139 */              localObject = (Command)localObject;
/* 140:140 */              localHashMap.put(localObject.getClass(), localObject);
/* 141:    */            }
/* 142:    */          }
/* 143:    */          catch (ClassNotFoundException localClassNotFoundException) {
/* 144:144 */            System.err.println(localClassNotFoundException);
/* 146:    */          }
/* 147:    */          catch (InstantiationException localInstantiationException) {}catch (IllegalAccessException localIllegalAccessException) {}
/* 148:    */        }
/* 149:    */        
/* 150:    */      }
/* 151:    */      
/* 152:    */    }
/* 153:    */    catch (ResourceException localResourceException)
/* 154:    */    {
/* 155:155 */      System.err.println(localResourceException.getMessage());
/* 156:    */    }
/* 157:    */    
/* 159:159 */    return localHashMap;
/* 160:    */  }
/* 161:    */  
/* 169:    */  public final List a(String paramString)
/* 170:    */  {
/* 171:171 */    ArrayList localArrayList = new ArrayList();
/* 172:    */    
/* 173:173 */    paramString = paramString.replaceAll("\\.", "/");
/* 174:    */    try
/* 175:    */    {
/* 176:176 */      localObject1 = a(paramString);
/* 177:177 */      JarFile localJarFile = null;
/* 178:    */      Object localObject2;
/* 179:179 */      if (((localObject1 = ((URL)localObject1).openConnection()) instanceof JarURLConnection))
/* 180:    */      {
/* 182:182 */        localObject2 = null;localJarFile = ((JarURLConnection)localObject1).getJarFile();
/* 183:    */      }
/* 184:    */      else {
/* 185:185 */        System.setSecurityManager(null);
/* 186:186 */        localObject2 = Class.forName("com.sun.jnlp.JNLPCachedJarURLConnection");
/* 187:    */        
/* 191:    */        try
/* 192:    */        {
/* 193:193 */          for (Object localObject5 : ((Class)localObject2).getFields())
/* 194:    */          {
/* 195:194 */            System.err.println(localObject5);
/* 196:    */          }
/* 197:    */          
/* 199:198 */          for (??? : ((Class)localObject2).getMethods())
/* 200:    */          {
/* 201:199 */            System.err.println(???);
/* 202:    */          }
/* 203:    */          
/* 208:206 */          (??? = ((Class)localObject2).getDeclaredMethod("getJarFile", new Class[0])).setAccessible(true);
/* 209:    */          
/* 211:209 */          localJarFile = (JarFile)((Method)???).invoke(((Class)localObject2).cast(localObject1), new Object[0]);
/* 212:    */        }
/* 213:    */        catch (Throwable localThrowable)
/* 214:    */        {
/* 215:213 */          
/* 216:    */          
/* 217:215 */            localThrowable;
/* 218:    */        }
/* 219:    */      }
/* 220:    */      
/* 222:218 */      ??? = localJarFile.entries();
/* 223:219 */      while (((Enumeration)???).hasMoreElements())
/* 224:    */      {
/* 225:221 */        if ((localObject2 = (JarEntry)((Enumeration)???).nextElement()) == null) break;
/* 226:222 */        if ((((JarEntry)localObject2).getName().startsWith(paramString)) && (((JarEntry)localObject2).getName().endsWith(".class")))
/* 227:    */        {
/* 230:226 */          ??? = Thread.currentThread().getContextClassLoader().loadClass(((JarEntry)localObject2).getName().replaceAll("/", "\\.").replaceAll("\\.class", ""));
/* 231:227 */          localArrayList.add(???);
/* 232:    */        }
/* 233:    */      }
/* 234:230 */    } catch (Exception localException) { Object localObject1 = null;
/* 235:    */      
/* 236:232 */      localException.printStackTrace();
/* 237:    */    }
/* 238:    */    
/* 239:233 */    return localArrayList;
/* 240:    */  }
/* 241:    */  
/* 253:    */  public final List b(String paramString)
/* 254:    */  {
/* 255:249 */    localArrayList = new ArrayList();
/* 256:    */    
/* 257:251 */    paramString = paramString.replaceAll("\\.", "/");
/* 258:    */    
/* 259:    */    try
/* 260:    */    {
/* 261:255 */      Object localObject1 = null;
/* 262:    */      
/* 268:    */      Object localObject2;
/* 269:    */      
/* 274:268 */      if (((localObject1 = a(paramString).openConnection()) instanceof JarURLConnection))
/* 275:    */      {
/* 279:273 */        localObject2 = ((JarURLConnection)localObject1).getJarFile().entries();
/* 280:274 */        while (((Enumeration)localObject2).hasMoreElements())
/* 281:    */        {
/* 282:276 */          if ((localObject1 = (JarEntry)((Enumeration)localObject2).nextElement()) == null) break;
/* 283:277 */          if (((JarEntry)localObject1).getName().startsWith(paramString))
/* 284:    */          {
/* 286:280 */            localArrayList.add(((JarEntry)localObject1).getName());
/* 287:    */          }
/* 288:    */        }
/* 289:    */      }
/* 290:    */      else {
/* 291:285 */        localObject2 = Class.forName("com.sun.jnlp.JNLPCachedJarURLConnection");
/* 292:    */        
/* 296:    */        try
/* 297:    */        {
/* 298:292 */          localObject1 = (JarFile)((Class)localObject2).getDeclaredMethod("getJarFile", new Class[0]).invoke(localObject1, new Object[0]);
/* 299:293 */          System.err.println(localObject1);
/* 300:    */        }
/* 301:    */        catch (Throwable localThrowable) {}
/* 302:    */      }
/* 303:    */      
/* 311:305 */      return localArrayList;
/* 312:    */    }
/* 313:    */    catch (Exception localException)
/* 314:    */    {
/* 315:301 */      localObject1 = null;
/* 316:    */      
/* 317:303 */      localException.printStackTrace();
/* 318:    */    }
/* 319:    */  }
/* 320:    */  
/* 331:    */  public final InputStream a(String paramString)
/* 332:    */  {
/* 333:317 */    paramString = paramString.replaceAll("\\\\", "/").replaceAll("\\./", "");
/* 334:318 */    while (paramString.contains("//")) {
/* 335:319 */      paramString = paramString.replaceAll("//", "/");
/* 336:    */    }
/* 337:    */    
/* 341:    */    Object localObject;
/* 342:    */    
/* 345:329 */    if ((localObject = getClass().getClassLoader().getResourceAsStream(paramString)) == null)
/* 346:    */    {
/* 349:333 */      localObject = CommandMap.class.getResourceAsStream(paramString);
/* 350:    */    }
/* 351:335 */    if (localObject == null)
/* 352:    */    {
/* 355:339 */      localObject = ClassLoader.getSystemResourceAsStream(paramString);
/* 356:    */    }
/* 357:341 */    if (localObject == null) {
/* 358:    */      File localFile;
/* 359:343 */      if ((localFile = new File(paramString)).exists()) {
/* 360:    */        try {
/* 361:345 */          localObject = new FileInputStream(localFile);
/* 362:346 */        } catch (FileNotFoundException localFileNotFoundException) { 
/* 363:    */          
/* 365:349 */            localFileNotFoundException;
/* 366:    */        }
/* 367:    */      }
/* 368:    */    }
/* 369:    */    
/* 372:353 */    if (localObject == null) {
/* 373:354 */      throw new ResourceException("[WARNING][ResourceLoader]Resource not found " + paramString);
/* 374:    */    }
/* 375:    */    
/* 376:357 */    return localObject;
/* 377:    */  }
/* 378:    */  
/* 385:    */  private URL a(String paramString)
/* 386:    */  {
/* 387:368 */    paramString = paramString.replaceAll("\\./", "").replaceAll("\\\\", "");
/* 388:369 */    while (paramString.contains("//")) {
/* 389:370 */      paramString = paramString.replaceAll("//", "/");
/* 390:    */    }
/* 391:    */    
/* 394:    */    URL localURL;
/* 395:    */    
/* 398:379 */    if ((localURL = getClass().getClassLoader().getResource(paramString)) == null)
/* 399:    */    {
/* 402:383 */      localURL = CommandMap.class.getResource(paramString);
/* 403:    */    }
/* 404:385 */    if (localURL == null)
/* 405:    */    {
/* 408:389 */      localURL = ClassLoader.getSystemResource(paramString);
/* 409:    */    }
/* 410:391 */    if (localURL == null) {
/* 411:    */      File localFile;
/* 412:393 */      if ((localFile = new File(paramString)).exists()) {
/* 413:    */        try {
/* 414:395 */          localURL = new URL("file:" + localFile.getAbsolutePath());
/* 415:396 */        } catch (MalformedURLException localMalformedURLException) { 
/* 416:    */          
/* 417:398 */            localMalformedURLException;
/* 418:    */        }
/* 419:    */      }
/* 420:    */    }
/* 421:    */    
/* 422:401 */    if (localURL == null) {
/* 423:402 */      throw new ResourceException("[WARNING][ResourceLoader] Resource not found: " + paramString);
/* 424:    */    }
/* 425:    */    
/* 426:405 */    return localURL;
/* 427:    */  }
/* 428:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     k
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */