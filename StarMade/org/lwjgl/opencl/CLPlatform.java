/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.lang.reflect.Field;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.util.List;
/*   7:    */import org.lwjgl.PointerBuffer;
/*   8:    */import org.lwjgl.opencl.api.Filter;
/*   9:    */
/*  47:    */public final class CLPlatform
/*  48:    */  extends CLObject
/*  49:    */{
/*  50: 50 */  private static final CLPlatformUtil util = (CLPlatformUtil)getInfoUtilInstance(CLPlatform.class, "CL_PLATFORM_UTIL");
/*  51:    */  
/*  52: 52 */  private static final FastLongMap<CLPlatform> clPlatforms = new FastLongMap();
/*  53:    */  
/*  54:    */  private final CLObjectRegistry<CLDevice> clDevices;
/*  55:    */  private Object caps;
/*  56:    */  
/*  57:    */  CLPlatform(long pointer)
/*  58:    */  {
/*  59: 59 */    super(pointer);
/*  60:    */    
/*  61: 61 */    if (isValid()) {
/*  62: 62 */      clPlatforms.put(pointer, this);
/*  63: 63 */      this.clDevices = new CLObjectRegistry();
/*  64:    */    } else {
/*  65: 65 */      this.clDevices = null;
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  73:    */  public static CLPlatform getCLPlatform(long id)
/*  74:    */  {
/*  75: 75 */    return (CLPlatform)clPlatforms.get(id);
/*  76:    */  }
/*  77:    */  
/*  82:    */  public CLDevice getCLDevice(long id)
/*  83:    */  {
/*  84: 84 */    return (CLDevice)this.clDevices.getObject(id);
/*  85:    */  }
/*  86:    */  
/*  88:    */  static <T extends CLObject> InfoUtil<T> getInfoUtilInstance(Class<T> clazz, String fieldName)
/*  89:    */  {
/*  90: 90 */    InfoUtil<T> instance = null;
/*  91:    */    try {
/*  92: 92 */      Class<?> infoUtil = Class.forName("org.lwjgl.opencl.InfoUtilFactory");
/*  93: 93 */      instance = (InfoUtil)infoUtil.getDeclaredField(fieldName).get(null);
/*  94:    */    }
/*  95:    */    catch (Exception e) {}
/*  96:    */    
/*  97: 97 */    return instance;
/*  98:    */  }
/*  99:    */  
/* 104:    */  public static List<CLPlatform> getPlatforms()
/* 105:    */  {
/* 106:106 */    return getPlatforms(null);
/* 107:    */  }
/* 108:    */  
/* 115:    */  public static List<CLPlatform> getPlatforms(Filter<CLPlatform> filter)
/* 116:    */  {
/* 117:117 */    return util.getPlatforms(filter);
/* 118:    */  }
/* 119:    */  
/* 126:    */  public String getInfoString(int param_name)
/* 127:    */  {
/* 128:128 */    return util.getInfoString(this, param_name);
/* 129:    */  }
/* 130:    */  
/* 138:    */  public List<CLDevice> getDevices(int device_type)
/* 139:    */  {
/* 140:140 */    return getDevices(device_type, null);
/* 141:    */  }
/* 142:    */  
/* 151:    */  public List<CLDevice> getDevices(int device_type, Filter<CLDevice> filter)
/* 152:    */  {
/* 153:153 */    return util.getDevices(this, device_type, filter);
/* 154:    */  }
/* 155:    */  
/* 166:    */  void setCapabilities(Object caps)
/* 167:    */  {
/* 168:168 */    this.caps = caps;
/* 169:    */  }
/* 170:    */  
/* 171:    */  Object getCapabilities() {
/* 172:172 */    return this.caps;
/* 173:    */  }
/* 174:    */  
/* 179:    */  static void registerCLPlatforms(PointerBuffer platforms, IntBuffer num_platforms)
/* 180:    */  {
/* 181:181 */    if (platforms == null) {
/* 182:182 */      return;
/* 183:    */    }
/* 184:184 */    int pos = platforms.position();
/* 185:185 */    int count = Math.min(num_platforms.get(0), platforms.remaining());
/* 186:186 */    for (int i = 0; i < count; i++) {
/* 187:187 */      long id = platforms.get(pos + i);
/* 188:188 */      if (!clPlatforms.containsKey(id))
/* 189:189 */        new CLPlatform(id);
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 193:193 */  CLObjectRegistry<CLDevice> getCLDeviceRegistry() { return this.clDevices; }
/* 194:    */  
/* 199:    */  void registerCLDevices(PointerBuffer devices, IntBuffer num_devices)
/* 200:    */  {
/* 201:201 */    int pos = devices.position();
/* 202:202 */    int count = Math.min(num_devices.get(num_devices.position()), devices.remaining());
/* 203:203 */    for (int i = 0; i < count; i++) {
/* 204:204 */      long id = devices.get(pos + i);
/* 205:205 */      if (!this.clDevices.hasObject(id)) {
/* 206:206 */        new CLDevice(id, this);
/* 207:    */      }
/* 208:    */    }
/* 209:    */  }
/* 210:    */  
/* 214:    */  void registerCLDevices(ByteBuffer devices, PointerBuffer num_devices)
/* 215:    */  {
/* 216:216 */    int pos = devices.position();
/* 217:217 */    int count = Math.min((int)num_devices.get(num_devices.position()), devices.remaining()) / PointerBuffer.getPointerSize();
/* 218:218 */    for (int i = 0; i < count; i++) {
/* 219:219 */      int offset = pos + i * PointerBuffer.getPointerSize();
/* 220:220 */      long id = PointerBuffer.is64Bit() ? devices.getLong(offset) : devices.getInt(offset);
/* 221:221 */      if (!this.clDevices.hasObject(id)) {
/* 222:222 */        new CLDevice(id, this);
/* 223:    */      }
/* 224:    */    }
/* 225:    */  }
/* 226:    */  
/* 227:    */  static abstract interface CLPlatformUtil
/* 228:    */    extends InfoUtil<CLPlatform>
/* 229:    */  {
/* 230:    */    public abstract List<CLPlatform> getPlatforms(Filter<CLPlatform> paramFilter);
/* 231:    */    
/* 232:    */    public abstract List<CLDevice> getDevices(CLPlatform paramCLPlatform, int paramInt, Filter<CLDevice> paramFilter);
/* 233:    */  }
/* 234:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLPlatform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */