/*   1:    */package org.lwjgl.util.mapped;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.lang.reflect.InvocationTargetException;
/*   6:    */import java.lang.reflect.Method;
/*   7:    */import java.net.URLClassLoader;
/*   8:    */import org.lwjgl.LWJGLUtil;
/*   9:    */
/*  47:    */public class MappedObjectClassLoader
/*  48:    */  extends URLClassLoader
/*  49:    */{
/*  50: 50 */  static final String MAPPEDOBJECT_PACKAGE_PREFIX = MappedObjectClassLoader.class.getPackage().getName() + ".";
/*  51:    */  
/*  54:    */  static boolean FORKED;
/*  55:    */  
/*  58:    */  private static long total_time_transforming;
/*  59:    */  
/*  62:    */  public static boolean fork(Class<?> mainClass, String[] args)
/*  63:    */  {
/*  64: 64 */    if (FORKED) {
/*  65: 65 */      return false;
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    FORKED = true;
/*  69:    */    try
/*  70:    */    {
/*  71: 71 */      MappedObjectClassLoader loader = new MappedObjectClassLoader(mainClass);
/*  72: 72 */      loader.loadMappedObject();
/*  73:    */      
/*  74: 74 */      Class<?> replacedMainClass = loader.loadClass(mainClass.getName());
/*  75: 75 */      Method mainMethod = replacedMainClass.getMethod("main", new Class[] { [Ljava.lang.String.class });
/*  76: 76 */      mainMethod.invoke(null, new Object[] { args });
/*  77:    */    } catch (InvocationTargetException exc) {
/*  78: 78 */      Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), exc.getCause());
/*  79:    */    } catch (Throwable cause) {
/*  80: 80 */      throw new Error("failed to fork", cause);
/*  81:    */    }
/*  82:    */    
/*  83: 83 */    return true;
/*  84:    */  }
/*  85:    */  
/*  86:    */  private MappedObjectClassLoader(Class<?> mainClass) {
/*  87: 87 */    super(((URLClassLoader)mainClass.getClassLoader()).getURLs());
/*  88:    */  }
/*  89:    */  
/*  90:    */  protected synchronized Class<?> loadMappedObject() throws ClassNotFoundException {
/*  91: 91 */    String name = MappedObject.class.getName();
/*  92: 92 */    String className = name.replace('.', '/');
/*  93:    */    
/*  94: 94 */    byte[] bytecode = readStream(getResourceAsStream(className.concat(".class")));
/*  95:    */    
/*  96: 96 */    long t0 = System.nanoTime();
/*  97: 97 */    bytecode = MappedObjectTransformer.transformMappedObject(bytecode);
/*  98: 98 */    long t1 = System.nanoTime();
/*  99: 99 */    total_time_transforming += t1 - t0;
/* 100:    */    
/* 101:101 */    if (MappedObjectTransformer.PRINT_ACTIVITY) {
/* 102:102 */      printActivity(className, t0, t1);
/* 103:    */    }
/* 104:104 */    Class<?> clazz = super.defineClass(name, bytecode, 0, bytecode.length);
/* 105:105 */    resolveClass(clazz);
/* 106:106 */    return clazz;
/* 107:    */  }
/* 108:    */  
/* 110:    */  protected synchronized Class<?> loadClass(String name, boolean resolve)
/* 111:    */    throws ClassNotFoundException
/* 112:    */  {
/* 113:113 */    if ((name.startsWith("java.")) || (name.startsWith("javax.")) || (name.startsWith("sun.")) || (name.startsWith("sunw.")) || (name.startsWith("org.objectweb.asm.")))
/* 114:    */    {
/* 119:119 */      return super.loadClass(name, resolve);
/* 120:    */    }
/* 121:121 */    String className = name.replace('.', '/');
/* 122:122 */    boolean inThisPackage = name.startsWith(MAPPEDOBJECT_PACKAGE_PREFIX);
/* 123:    */    
/* 124:124 */    if ((inThisPackage) && ((name.equals(MappedObjectClassLoader.class.getName())) || (name.equals(MappedObjectTransformer.class.getName())) || (name.equals(CacheUtil.class.getName()))))
/* 125:    */    {
/* 129:129 */      return super.loadClass(name, resolve);
/* 130:    */    }
/* 131:131 */    byte[] bytecode = readStream(getResourceAsStream(className.concat(".class")));
/* 132:    */    
/* 134:134 */    if ((!inThisPackage) || (name.substring(MAPPEDOBJECT_PACKAGE_PREFIX.length()).indexOf('.') != -1)) {
/* 135:135 */      long t0 = System.nanoTime();
/* 136:136 */      byte[] newBytecode = MappedObjectTransformer.transformMappedAPI(className, bytecode);
/* 137:137 */      long t1 = System.nanoTime();
/* 138:    */      
/* 139:139 */      total_time_transforming += t1 - t0;
/* 140:    */      
/* 141:141 */      if (bytecode != newBytecode) {
/* 142:142 */        bytecode = newBytecode;
/* 143:143 */        if (MappedObjectTransformer.PRINT_ACTIVITY) {
/* 144:144 */          printActivity(className, t0, t1);
/* 145:    */        }
/* 146:    */      }
/* 147:    */    }
/* 148:148 */    Class<?> clazz = super.defineClass(name, bytecode, 0, bytecode.length);
/* 149:149 */    if (resolve)
/* 150:150 */      resolveClass(clazz);
/* 151:151 */    return clazz;
/* 152:    */  }
/* 153:    */  
/* 154:    */  private static void printActivity(String className, long t0, long t1) {
/* 155:155 */    StringBuilder msg = new StringBuilder(MappedObjectClassLoader.class.getSimpleName() + ": " + className);
/* 156:    */    
/* 157:157 */    if (MappedObjectTransformer.PRINT_TIMING) {
/* 158:158 */      msg.append("\n\ttransforming took " + (t1 - t0) / 1000L + " micros (total: " + total_time_transforming / 1000L / 1000L + "ms)");
/* 159:    */    }
/* 160:160 */    LWJGLUtil.log(msg);
/* 161:    */  }
/* 162:    */  
/* 163:    */  private static byte[] readStream(InputStream in) {
/* 164:164 */    bytecode = new byte[256];
/* 165:165 */    len = 0;
/* 166:    */    try {
/* 167:    */      for (;;) {
/* 168:168 */        if (bytecode.length == len)
/* 169:169 */          bytecode = copyOf(bytecode, len * 2);
/* 170:170 */        int got = in.read(bytecode, len, bytecode.length - len);
/* 171:171 */        if (got == -1)
/* 172:    */          break;
/* 173:173 */        len += got;
/* 174:    */      }
/* 175:    */      
/* 184:184 */      return copyOf(bytecode, len);
/* 185:    */    }
/* 186:    */    catch (IOException exc) {}finally
/* 187:    */    {
/* 188:    */      try
/* 189:    */      {
/* 190:179 */        in.close();
/* 191:    */      }
/* 192:    */      catch (IOException exc) {}
/* 193:    */    }
/* 194:    */  }
/* 195:    */  
/* 197:    */  private static byte[] copyOf(byte[] original, int newLength)
/* 198:    */  {
/* 199:188 */    byte[] copy = new byte[newLength];
/* 200:189 */    System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
/* 201:190 */    return copy;
/* 202:    */  }
/* 203:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObjectClassLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */