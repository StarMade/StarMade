/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.JarURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import org.schema.schine.graphicsengine.core.ResourceException;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.CommandMap;
/*     */ 
/*     */ public final class k
/*     */ {
/*     */   public final HashMap a(String paramString)
/*     */   {
/*  92 */     HashMap localHashMap = new HashMap();
/*     */ 
/*  94 */     if (!(
/*  94 */       localObject = new String(paramString))
/*  94 */       .startsWith("/")) {
/*  95 */       localObject = "/" + (String)localObject;
/*     */     }
/*  97 */     Object localObject = ((String)localObject).replace('.', '/');
/*     */     try
/*     */     {
/* 106 */       localObject = a((String)localObject);
/*     */ 
/* 120 */       if (!(
/* 120 */         localObject = new File(((URL)localObject).getFile()))
/* 120 */         .exists())
/*     */       {
/* 122 */         throw new ResourceException("ERROR: no content in Resource: " + ((File)localObject).getAbsolutePath());
/*     */       }
/*     */       File[] arrayOfFile;
/* 127 */       int i = (arrayOfFile = ((File)localObject).listFiles()).length;
/*     */ 
/* 127 */       for (int j = 0; j < i; j++)
/*     */       {
/* 131 */         if ((
/* 131 */           localObject = arrayOfFile[j]
/* 128 */           .getName())
/* 131 */           .endsWith(".class"))
/*     */         {
/* 133 */           localObject = ((String)localObject).substring(0, ((String)localObject).length() - 6);
/*     */           try
/*     */           {
/* 137 */             if (((
/* 137 */               localObject = Class.forName(paramString + "." + (String)localObject).newInstance()) instanceof Command))
/*     */             {
/* 139 */               localObject = (Command)localObject;
/* 140 */               localHashMap.put(localObject.getClass(), localObject);
/*     */             }
/*     */           }
/*     */           catch (ClassNotFoundException localClassNotFoundException) {
/* 144 */             System.err.println(localClassNotFoundException);
/*     */           }
/*     */           catch (InstantiationException localInstantiationException)
/*     */           {
/*     */           }
/*     */           catch (IllegalAccessException localIllegalAccessException) {
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (ResourceException localResourceException) {
/* 155 */       System.err.println(localResourceException.getMessage());
/*     */     }
/*     */ 
/* 159 */     return localHashMap;
/*     */   }
/*     */ 
/*     */   public final List a(String paramString)
/*     */   {
/* 171 */     ArrayList localArrayList = new ArrayList();
/*     */ 
/* 173 */     paramString = paramString.replaceAll("\\.", "/");
/*     */     try
/*     */     {
/* 176 */       localObject1 = a(paramString);
/* 177 */       JarFile localJarFile = null;
/*     */       Object localObject2;
/* 179 */       if (((
/* 179 */         localObject1 = ((URL)localObject1).openConnection()) instanceof JarURLConnection))
/*     */       {
/* 182 */         localObject2 = null; localJarFile = ((JarURLConnection)localObject1)
/* 182 */           .getJarFile();
/*     */       }
/*     */       else {
/* 185 */         System.setSecurityManager(null);
/* 186 */         localObject2 = Class.forName("com.sun.jnlp.JNLPCachedJarURLConnection");
/*     */         try
/*     */         {
/* 193 */           for (Object localObject5 : ((Class)localObject2).getFields())
/*     */           {
/* 194 */             System.err.println(localObject5);
/*     */           }
/*     */ 
/* 198 */           for (??? : ((Class)localObject2).getMethods())
/*     */           {
/* 199 */             System.err.println(???);
/*     */           }
/* 201 */           ( = ((Class)localObject2).getDeclaredMethod("getJarFile", new Class[0]))
/* 206 */             .setAccessible(true);
/*     */ 
/* 209 */           localJarFile = (JarFile)((Method)???).invoke(((Class)localObject2).cast(localObject1), new Object[0]);
/*     */         }
/*     */         catch (Throwable localThrowable)
/*     */         {
/* 215 */           localThrowable.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 218 */       ??? = localJarFile.entries();
/* 219 */       while (((Enumeration)???).hasMoreElements())
/*     */       {
/* 221 */         if ((
/* 221 */           localObject2 = (JarEntry)((Enumeration)???).nextElement()) == null)
/*     */           break;
/* 222 */         if ((((JarEntry)localObject2).getName().startsWith(paramString)) && (((JarEntry)localObject2).getName().endsWith(".class")))
/*     */         {
/* 226 */           ??? = Thread.currentThread().getContextClassLoader().loadClass(((JarEntry)localObject2).getName().replaceAll("/", "\\.").replaceAll("\\.class", ""));
/* 227 */           localArrayList.add(???);
/*     */         }
/*     */       }
/*     */     } catch (Exception localException) { Object localObject1 = null;
/*     */ 
/* 232 */       localException.printStackTrace();
/*     */     }
/*     */ 
/* 233 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   public final List b(String paramString)
/*     */   {
/* 249 */     ArrayList localArrayList = new ArrayList();
/*     */ 
/* 251 */     paramString = paramString.replaceAll("\\.", "/");
/*     */     try
/*     */     {
/* 255 */       localObject1 = null;
/*     */       Object localObject2;
/* 268 */       if (((
/* 268 */         localObject1 = a(paramString)
/* 255 */         .openConnection()) instanceof JarURLConnection))
/*     */       {
/* 273 */         localObject2 = ((JarURLConnection)localObject1)
/* 271 */           .getJarFile()
/* 273 */           .entries();
/* 274 */         while (((Enumeration)localObject2).hasMoreElements())
/*     */         {
/* 276 */           if ((
/* 276 */             localObject1 = (JarEntry)((Enumeration)localObject2).nextElement()) == null)
/*     */             break;
/* 277 */           if (((JarEntry)localObject1).getName().startsWith(paramString))
/*     */           {
/* 280 */             localArrayList.add(((JarEntry)localObject1).getName());
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 285 */         localObject2 = Class.forName("com.sun.jnlp.JNLPCachedJarURLConnection");
/*     */         try
/*     */         {
/* 292 */           localObject1 = (JarFile)((Class)localObject2).getDeclaredMethod("getJarFile", new Class[0])
/* 292 */             .invoke(localObject1, new Object[0]);
/* 293 */           System.err.println(localObject1);
/*     */         }
/*     */         catch (Throwable localThrowable)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/* 301 */       Object localObject1 = null;
/*     */ 
/* 303 */       localException.printStackTrace();
/*     */     }
/*     */ 
/* 305 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   public final InputStream a(String paramString)
/*     */   {
/* 317 */     paramString = paramString.replaceAll("\\\\", "/")
/* 317 */       .replaceAll("\\./", "");
/* 318 */     while (paramString.contains("//"))
/* 319 */       paramString = paramString.replaceAll("//", "/");
/*     */     Object localObject;
/* 329 */     if ((
/* 329 */       localObject = getClass().getClassLoader().getResourceAsStream(paramString)) == null)
/*     */     {
/* 333 */       localObject = CommandMap.class.getResourceAsStream(paramString);
/*     */     }
/* 335 */     if (localObject == null)
/*     */     {
/* 339 */       localObject = ClassLoader.getSystemResourceAsStream(paramString);
/*     */     }
/* 341 */     if (localObject == null)
/*     */     {
/*     */       File localFile;
/* 343 */       if ((
/* 343 */         localFile = new File(paramString))
/* 343 */         .exists()) {
/*     */         try {
/* 345 */           localObject = new FileInputStream(localFile);
/*     */         }
/*     */         catch (FileNotFoundException localFileNotFoundException)
/*     */         {
/* 349 */           localFileNotFoundException.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 353 */     if (localObject == null) {
/* 354 */       throw new ResourceException("[WARNING][ResourceLoader]Resource not found " + paramString);
/*     */     }
/*     */ 
/* 357 */     return localObject;
/*     */   }
/*     */ 
/*     */   private URL a(String paramString)
/*     */   {
/* 368 */     paramString = paramString.replaceAll("\\./", "")
/* 368 */       .replaceAll("\\\\", "");
/* 369 */     while (paramString.contains("//"))
/* 370 */       paramString = paramString.replaceAll("//", "/");
/*     */     URL localURL;
/* 379 */     if ((
/* 379 */       localURL = getClass().getClassLoader().getResource(paramString)) == null)
/*     */     {
/* 383 */       localURL = CommandMap.class.getResource(paramString);
/*     */     }
/* 385 */     if (localURL == null)
/*     */     {
/* 389 */       localURL = ClassLoader.getSystemResource(paramString);
/*     */     }
/* 391 */     if (localURL == null)
/*     */     {
/*     */       File localFile;
/* 393 */       if ((
/* 393 */         localFile = new File(paramString))
/* 393 */         .exists()) {
/*     */         try {
/* 395 */           localURL = new URL("file:" + localFile.getAbsolutePath());
/*     */         }
/*     */         catch (MalformedURLException localMalformedURLException) {
/* 398 */           localMalformedURLException.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 401 */     if (localURL == null) {
/* 402 */       throw new ResourceException("[WARNING][ResourceLoader] Resource not found: " + paramString);
/*     */     }
/*     */ 
/* 405 */     return localURL;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     k
 * JD-Core Version:    0.6.2
 */