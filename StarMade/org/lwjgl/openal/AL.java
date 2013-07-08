/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*   3:    */import org.lwjgl.LWJGLException;
/*   4:    */import org.lwjgl.LWJGLUtil;
/*   5:    */import org.lwjgl.Sys;
/*   6:    */
/*  71:    */public final class AL
/*  72:    */{
/*  73:    */  static ALCdevice device;
/*  74:    */  static ALCcontext context;
/*  75:    */  private static boolean created;
/*  76:    */  
/*  77:    */  private static native void nCreate(String paramString)
/*  78:    */    throws LWJGLException;
/*  79:    */  
/*  80:    */  private static native void nCreateDefault()
/*  81:    */    throws LWJGLException;
/*  82:    */  
/*  83:    */  private static native void nDestroy();
/*  84:    */  
/*  85:    */  public static boolean isCreated()
/*  86:    */  {
/*  87: 87 */    return created;
/*  88:    */  }
/*  89:    */  
/*  99:    */  public static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized)
/* 100:    */    throws LWJGLException
/* 101:    */  {
/* 102:102 */    create(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, true);
/* 103:    */  }
/* 104:    */  
/* 109:    */  public static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice)
/* 110:    */    throws LWJGLException
/* 111:    */  {
/* 112:112 */    if (created)
/* 113:113 */      throw new IllegalStateException("Only one OpenAL context may be instantiated at any one time.");
/* 114:    */    String libname;
/* 115:    */    String[] library_names;
/* 116:116 */    switch (LWJGLUtil.getPlatform()) {
/* 117:    */    case 3: 
/* 118:118 */      libname = "OpenAL32";
/* 119:119 */      library_names = new String[] { "OpenAL64.dll", "OpenAL32.dll" };
/* 120:120 */      break;
/* 121:    */    case 1: 
/* 122:122 */      libname = "openal";
/* 123:123 */      library_names = new String[] { "libopenal64.so", "libopenal.so", "libopenal.so.0" };
/* 124:124 */      break;
/* 125:    */    case 2: 
/* 126:126 */      libname = "openal";
/* 127:127 */      library_names = new String[] { "openal.dylib" };
/* 128:128 */      break;
/* 129:    */    default: 
/* 130:130 */      throw new LWJGLException("Unknown platform: " + LWJGLUtil.getPlatform());
/* 131:    */    }
/* 132:132 */    String[] oalPaths = LWJGLUtil.getLibraryPaths(libname, library_names, AL.class.getClassLoader());
/* 133:133 */    LWJGLUtil.log("Found " + oalPaths.length + " OpenAL paths");
/* 134:134 */    for (String oalPath : oalPaths) {
/* 135:    */      try {
/* 136:136 */        nCreate(oalPath);
/* 137:137 */        created = true;
/* 138:138 */        init(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, openDevice);
/* 139:    */      }
/* 140:    */      catch (LWJGLException e) {
/* 141:141 */        LWJGLUtil.log("Failed to load " + oalPath + ": " + e.getMessage());
/* 142:    */      }
/* 143:    */    }
/* 144:144 */    if ((!created) && (LWJGLUtil.getPlatform() == 2))
/* 145:    */    {
/* 146:146 */      nCreateDefault();
/* 147:147 */      created = true;
/* 148:148 */      init(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, openDevice);
/* 149:    */    }
/* 150:150 */    if (!created)
/* 151:151 */      throw new LWJGLException("Could not locate OpenAL library.");
/* 152:    */  }
/* 153:    */  
/* 154:    */  private static void init(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice) throws LWJGLException {
/* 155:    */    try {
/* 156:156 */      AL10.initNativeStubs();
/* 157:157 */      ALC10.initNativeStubs();
/* 158:    */      
/* 159:159 */      if (openDevice) {
/* 160:160 */        device = ALC10.alcOpenDevice(deviceArguments);
/* 161:161 */        if (device == null) {
/* 162:162 */          throw new LWJGLException("Could not open ALC device");
/* 163:    */        }
/* 164:    */        
/* 165:165 */        if (contextFrequency == -1) {
/* 166:166 */          context = ALC10.alcCreateContext(device, null);
/* 167:    */        } else {
/* 168:168 */          context = ALC10.alcCreateContext(device, ALCcontext.createAttributeList(contextFrequency, contextRefresh, contextSynchronized ? 1 : 0));
/* 169:    */        }
/* 170:    */        
/* 172:172 */        ALC10.alcMakeContextCurrent(context);
/* 173:    */      }
/* 174:    */    } catch (LWJGLException e) {
/* 175:175 */      destroy();
/* 176:176 */      throw e;
/* 177:    */    }
/* 178:    */    
/* 179:179 */    ALC11.initialize();
/* 180:    */    
/* 188:188 */    if (ALC10.alcIsExtensionPresent(device, "ALC_EXT_EFX")) {
/* 189:189 */      EFX10.initNativeStubs();
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 198:    */  public static void create()
/* 199:    */    throws LWJGLException
/* 200:    */  {
/* 201:201 */    create(null, 44100, 60, false);
/* 202:    */  }
/* 203:    */  
/* 206:    */  public static void destroy()
/* 207:    */  {
/* 208:208 */    if (context != null) {
/* 209:209 */      ALC10.alcMakeContextCurrent(null);
/* 210:210 */      ALC10.alcDestroyContext(context);
/* 211:211 */      context = null;
/* 212:    */    }
/* 213:213 */    if (device != null) {
/* 214:214 */      boolean result = ALC10.alcCloseDevice(device);
/* 215:215 */      device = null;
/* 216:    */    }
/* 217:217 */    resetNativeStubs(AL10.class);
/* 218:218 */    resetNativeStubs(AL11.class);
/* 219:219 */    resetNativeStubs(ALC10.class);
/* 220:220 */    resetNativeStubs(ALC11.class);
/* 221:221 */    resetNativeStubs(EFX10.class);
/* 222:    */    
/* 223:223 */    if (created)
/* 224:224 */      nDestroy();
/* 225:225 */    created = false;
/* 226:    */  }
/* 227:    */  
/* 229:    */  private static native void resetNativeStubs(Class paramClass);
/* 230:    */  
/* 232:    */  public static ALCcontext getContext()
/* 233:    */  {
/* 234:234 */    return context;
/* 235:    */  }
/* 236:    */  
/* 239:    */  public static ALCdevice getDevice()
/* 240:    */  {
/* 241:241 */    return device;
/* 242:    */  }
/* 243:    */  
/* 244:    */  static {}
/* 245:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.AL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */