/*     */ package org.lwjgl.util.mapped;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URLClassLoader;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ public class MappedObjectClassLoader extends URLClassLoader
/*     */ {
/*  50 */   static final String MAPPEDOBJECT_PACKAGE_PREFIX = MappedObjectClassLoader.class.getPackage().getName() + ".";
/*     */   static boolean FORKED;
/*     */   private static long total_time_transforming;
/*     */ 
/*     */   public static boolean fork(Class<?> mainClass, String[] args)
/*     */   {
/*  64 */     if (FORKED) {
/*  65 */       return false;
/*     */     }
/*     */ 
/*  68 */     FORKED = true;
/*     */     try
/*     */     {
/*  71 */       MappedObjectClassLoader loader = new MappedObjectClassLoader(mainClass);
/*  72 */       loader.loadMappedObject();
/*     */ 
/*  74 */       Class replacedMainClass = loader.loadClass(mainClass.getName());
/*  75 */       Method mainMethod = replacedMainClass.getMethod("main", new Class[] { [Ljava.lang.String.class });
/*  76 */       mainMethod.invoke(null, new Object[] { args });
/*     */     } catch (InvocationTargetException exc) {
/*  78 */       Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), exc.getCause());
/*     */     } catch (Throwable cause) {
/*  80 */       throw new Error("failed to fork", cause);
/*     */     }
/*     */ 
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   private MappedObjectClassLoader(Class<?> mainClass) {
/*  87 */     super(((URLClassLoader)mainClass.getClassLoader()).getURLs());
/*     */   }
/*     */ 
/*     */   protected synchronized Class<?> loadMappedObject() throws ClassNotFoundException {
/*  91 */     String name = MappedObject.class.getName();
/*  92 */     String className = name.replace('.', '/');
/*     */ 
/*  94 */     byte[] bytecode = readStream(getResourceAsStream(className.concat(".class")));
/*     */ 
/*  96 */     long t0 = System.nanoTime();
/*  97 */     bytecode = MappedObjectTransformer.transformMappedObject(bytecode);
/*  98 */     long t1 = System.nanoTime();
/*  99 */     total_time_transforming += t1 - t0;
/*     */ 
/* 101 */     if (MappedObjectTransformer.PRINT_ACTIVITY) {
/* 102 */       printActivity(className, t0, t1);
/*     */     }
/* 104 */     Class clazz = super.defineClass(name, bytecode, 0, bytecode.length);
/* 105 */     resolveClass(clazz);
/* 106 */     return clazz;
/*     */   }
/*     */ 
/*     */   protected synchronized Class<?> loadClass(String name, boolean resolve)
/*     */     throws ClassNotFoundException
/*     */   {
/* 113 */     if ((name.startsWith("java.")) || (name.startsWith("javax.")) || (name.startsWith("sun.")) || (name.startsWith("sunw.")) || (name.startsWith("org.objectweb.asm.")))
/*     */     {
/* 119 */       return super.loadClass(name, resolve);
/*     */     }
/* 121 */     String className = name.replace('.', '/');
/* 122 */     boolean inThisPackage = name.startsWith(MAPPEDOBJECT_PACKAGE_PREFIX);
/*     */ 
/* 124 */     if ((inThisPackage) && ((name.equals(MappedObjectClassLoader.class.getName())) || (name.equals(MappedObjectTransformer.class.getName())) || (name.equals(CacheUtil.class.getName()))))
/*     */     {
/* 129 */       return super.loadClass(name, resolve);
/*     */     }
/* 131 */     byte[] bytecode = readStream(getResourceAsStream(className.concat(".class")));
/*     */ 
/* 134 */     if ((!inThisPackage) || (name.substring(MAPPEDOBJECT_PACKAGE_PREFIX.length()).indexOf('.') != -1)) {
/* 135 */       long t0 = System.nanoTime();
/* 136 */       byte[] newBytecode = MappedObjectTransformer.transformMappedAPI(className, bytecode);
/* 137 */       long t1 = System.nanoTime();
/*     */ 
/* 139 */       total_time_transforming += t1 - t0;
/*     */ 
/* 141 */       if (bytecode != newBytecode) {
/* 142 */         bytecode = newBytecode;
/* 143 */         if (MappedObjectTransformer.PRINT_ACTIVITY) {
/* 144 */           printActivity(className, t0, t1);
/*     */         }
/*     */       }
/*     */     }
/* 148 */     Class clazz = super.defineClass(name, bytecode, 0, bytecode.length);
/* 149 */     if (resolve)
/* 150 */       resolveClass(clazz);
/* 151 */     return clazz;
/*     */   }
/*     */ 
/*     */   private static void printActivity(String className, long t0, long t1) {
/* 155 */     StringBuilder msg = new StringBuilder(MappedObjectClassLoader.class.getSimpleName() + ": " + className);
/*     */ 
/* 157 */     if (MappedObjectTransformer.PRINT_TIMING) {
/* 158 */       msg.append("\n\ttransforming took " + (t1 - t0) / 1000L + " micros (total: " + total_time_transforming / 1000L / 1000L + "ms)");
/*     */     }
/* 160 */     LWJGLUtil.log(msg);
/*     */   }
/*     */ 
/*     */   private static byte[] readStream(InputStream in) {
/* 164 */     byte[] bytecode = new byte[256];
/* 165 */     int len = 0;
/*     */     try {
/*     */       while (true) {
/* 168 */         if (bytecode.length == len)
/* 169 */           bytecode = copyOf(bytecode, len * 2);
/* 170 */         int got = in.read(bytecode, len, bytecode.length - len);
/* 171 */         if (got == -1)
/*     */           break;
/* 173 */         len += got;
/*     */       }
/*     */     } catch (IOException exc) {
/*     */     }
/*     */     finally {
/*     */       try {
/* 179 */         in.close();
/*     */       }
/*     */       catch (IOException exc) {
/*     */       }
/*     */     }
/* 184 */     return copyOf(bytecode, len);
/*     */   }
/*     */ 
/*     */   private static byte[] copyOf(byte[] original, int newLength) {
/* 188 */     byte[] copy = new byte[newLength];
/* 189 */     System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
/* 190 */     return copy;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObjectClassLoader
 * JD-Core Version:    0.6.2
 */