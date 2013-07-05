/*     */ package org.apache.commons.logging;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.impl.NoOpLog;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class LogSource
/*     */ {
/*  62 */   protected static Hashtable logs = new Hashtable();
/*     */ 
/*  65 */   protected static boolean log4jIsAvailable = false;
/*     */ 
/*  68 */   protected static boolean jdk14IsAvailable = false;
/*     */ 
/*  71 */   protected static Constructor logImplctor = null;
/*     */ 
/*     */   public static void setLogImplementation(String classname)
/*     */     throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException
/*     */   {
/*     */     try
/*     */     {
/* 169 */       Class logclass = Class.forName(classname);
/* 170 */       Class[] argtypes = new Class[1];
/* 171 */       argtypes[0] = "".getClass();
/* 172 */       logImplctor = logclass.getConstructor(argtypes);
/*     */     } catch (Throwable t) {
/* 174 */       logImplctor = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setLogImplementation(Class logclass)
/*     */     throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException
/*     */   {
/* 188 */     Class[] argtypes = new Class[1];
/* 189 */     argtypes[0] = "".getClass();
/* 190 */     logImplctor = logclass.getConstructor(argtypes);
/*     */   }
/*     */ 
/*     */   public static Log getInstance(String name)
/*     */   {
/* 196 */     Log log = (Log)logs.get(name);
/* 197 */     if (null == log) {
/* 198 */       log = makeNewLogInstance(name);
/* 199 */       logs.put(name, log);
/*     */     }
/* 201 */     return log;
/*     */   }
/*     */ 
/*     */   public static Log getInstance(Class clazz)
/*     */   {
/* 207 */     return getInstance(clazz.getName());
/*     */   }
/*     */ 
/*     */   public static Log makeNewLogInstance(String name)
/*     */   {
/* 237 */     Log log = null;
/*     */     try {
/* 239 */       Object[] args = new Object[1];
/* 240 */       args[0] = name;
/* 241 */       log = (Log)logImplctor.newInstance(args);
/*     */     } catch (Throwable t) {
/* 243 */       log = null;
/*     */     }
/* 245 */     if (null == log) {
/* 246 */       log = new NoOpLog(name);
/*     */     }
/* 248 */     return log;
/*     */   }
/*     */ 
/*     */   public static String[] getLogNames()
/*     */   {
/* 258 */     return (String[])logs.keySet().toArray(new String[logs.size()]);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  80 */       if (null != Class.forName("org.apache.log4j.Logger"))
/*  81 */         log4jIsAvailable = true;
/*     */       else
/*  83 */         log4jIsAvailable = false;
/*     */     }
/*     */     catch (Throwable t) {
/*  86 */       log4jIsAvailable = false;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  91 */       if ((null != Class.forName("java.util.logging.Logger")) && (null != Class.forName("org.apache.commons.logging.impl.Jdk14Logger")))
/*     */       {
/*  93 */         jdk14IsAvailable = true;
/*     */       }
/*  95 */       else jdk14IsAvailable = false; 
/*     */     }
/*     */     catch (Throwable t)
/*     */     {
/*  98 */       jdk14IsAvailable = false;
/*     */     }
/*     */ 
/* 102 */     String name = null;
/*     */     try {
/* 104 */       name = System.getProperty("org.apache.commons.logging.log");
/* 105 */       if (name == null)
/* 106 */         name = System.getProperty("org.apache.commons.logging.Log");
/*     */     }
/*     */     catch (Throwable t) {
/*     */     }
/* 110 */     if (name != null)
/*     */       try {
/* 112 */         setLogImplementation(name);
/*     */       } catch (Throwable t) {
/*     */         try {
/* 115 */           setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/*     */         }
/*     */         catch (Throwable u)
/*     */         {
/*     */         }
/*     */       }
/*     */     else
/*     */       try {
/* 123 */         if (log4jIsAvailable) {
/* 124 */           setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
/*     */         }
/* 126 */         else if (jdk14IsAvailable) {
/* 127 */           setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
/*     */         }
/*     */         else
/* 130 */           setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/*     */       }
/*     */       catch (Throwable t)
/*     */       {
/*     */         try {
/* 135 */           setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/*     */         }
/*     */         catch (Throwable u)
/*     */         {
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.LogSource
 * JD-Core Version:    0.6.2
 */