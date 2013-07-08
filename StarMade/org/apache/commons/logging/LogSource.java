/*   1:    */package org.apache.commons.logging;
/*   2:    */
/*   3:    */import java.lang.reflect.Constructor;
/*   4:    */import java.util.Hashtable;
/*   5:    */import java.util.Set;
/*   6:    */import org.apache.commons.logging.impl.NoOpLog;
/*   7:    */
/*  57:    *//**
/*  58:    */ * @deprecated
/*  59:    */ */
/*  60:    */public class LogSource
/*  61:    */{
/*  62: 62 */  protected static Hashtable logs = new Hashtable();
/*  63:    */  
/*  65: 65 */  protected static boolean log4jIsAvailable = false;
/*  66:    */  
/*  68: 68 */  protected static boolean jdk14IsAvailable = false;
/*  69:    */  
/*  71: 71 */  protected static Constructor logImplctor = null;
/*  72:    */  
/*  76:    */  static
/*  77:    */  {
/*  78:    */    try
/*  79:    */    {
/*  80: 80 */      if (null != Class.forName("org.apache.log4j.Logger")) {
/*  81: 81 */        log4jIsAvailable = true;
/*  82:    */      } else {
/*  83: 83 */        log4jIsAvailable = false;
/*  84:    */      }
/*  85:    */    } catch (Throwable t) {
/*  86: 86 */      log4jIsAvailable = false;
/*  87:    */    }
/*  88:    */    
/*  89:    */    try
/*  90:    */    {
/*  91: 91 */      if ((null != Class.forName("java.util.logging.Logger")) && (null != Class.forName("org.apache.commons.logging.impl.Jdk14Logger")))
/*  92:    */      {
/*  93: 93 */        jdk14IsAvailable = true;
/*  94:    */      } else {
/*  95: 95 */        jdk14IsAvailable = false;
/*  96:    */      }
/*  97:    */    } catch (Throwable t) {
/*  98: 98 */      jdk14IsAvailable = false;
/*  99:    */    }
/* 100:    */    
/* 102:102 */    String name = null;
/* 103:    */    try {
/* 104:104 */      name = System.getProperty("org.apache.commons.logging.log");
/* 105:105 */      if (name == null) {
/* 106:106 */        name = System.getProperty("org.apache.commons.logging.Log");
/* 107:    */      }
/* 108:    */    }
/* 109:    */    catch (Throwable t) {}
/* 110:110 */    if (name != null) {
/* 111:    */      try {
/* 112:112 */        setLogImplementation(name);
/* 113:    */      } catch (Throwable t) {
/* 114:    */        try {
/* 115:115 */          setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/* 117:    */        }
/* 118:    */        catch (Throwable u) {}
/* 119:    */      }
/* 120:    */    } else {
/* 121:    */      try
/* 122:    */      {
/* 123:123 */        if (log4jIsAvailable) {
/* 124:124 */          setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
/* 125:    */        }
/* 126:126 */        else if (jdk14IsAvailable) {
/* 127:127 */          setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
/* 128:    */        }
/* 129:    */        else {
/* 130:130 */          setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/* 131:    */        }
/* 132:    */      }
/* 133:    */      catch (Throwable t) {
/* 134:    */        try {
/* 135:135 */          setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/* 136:    */        }
/* 137:    */        catch (Throwable u) {}
/* 138:    */      }
/* 139:    */    }
/* 140:    */  }
/* 141:    */  
/* 164:    */  public static void setLogImplementation(String classname)
/* 165:    */    throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException
/* 166:    */  {
/* 167:    */    try
/* 168:    */    {
/* 169:169 */      Class logclass = Class.forName(classname);
/* 170:170 */      Class[] argtypes = new Class[1];
/* 171:171 */      argtypes[0] = "".getClass();
/* 172:172 */      logImplctor = logclass.getConstructor(argtypes);
/* 173:    */    } catch (Throwable t) {
/* 174:174 */      logImplctor = null;
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 185:    */  public static void setLogImplementation(Class logclass)
/* 186:    */    throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException
/* 187:    */  {
/* 188:188 */    Class[] argtypes = new Class[1];
/* 189:189 */    argtypes[0] = "".getClass();
/* 190:190 */    logImplctor = logclass.getConstructor(argtypes);
/* 191:    */  }
/* 192:    */  
/* 194:    */  public static Log getInstance(String name)
/* 195:    */  {
/* 196:196 */    Log log = (Log)logs.get(name);
/* 197:197 */    if (null == log) {
/* 198:198 */      log = makeNewLogInstance(name);
/* 199:199 */      logs.put(name, log);
/* 200:    */    }
/* 201:201 */    return log;
/* 202:    */  }
/* 203:    */  
/* 205:    */  public static Log getInstance(Class clazz)
/* 206:    */  {
/* 207:207 */    return getInstance(clazz.getName());
/* 208:    */  }
/* 209:    */  
/* 235:    */  public static Log makeNewLogInstance(String name)
/* 236:    */  {
/* 237:237 */    Log log = null;
/* 238:    */    try {
/* 239:239 */      Object[] args = new Object[1];
/* 240:240 */      args[0] = name;
/* 241:241 */      log = (Log)logImplctor.newInstance(args);
/* 242:    */    } catch (Throwable t) {
/* 243:243 */      log = null;
/* 244:    */    }
/* 245:245 */    if (null == log) {
/* 246:246 */      log = new NoOpLog(name);
/* 247:    */    }
/* 248:248 */    return log;
/* 249:    */  }
/* 250:    */  
/* 256:    */  public static String[] getLogNames()
/* 257:    */  {
/* 258:258 */    return (String[])logs.keySet().toArray(new String[logs.size()]);
/* 259:    */  }
/* 260:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.LogSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */