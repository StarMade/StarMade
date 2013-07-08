/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import org.lwjgl.PointerBuffer;
/*   4:    */
/*  40:    */public final class CLDevice
/*  41:    */  extends CLObjectChild<CLDevice>
/*  42:    */{
/*  43: 43 */  private static final InfoUtil<CLDevice> util = CLPlatform.getInfoUtilInstance(CLDevice.class, "CL_DEVICE_UTIL");
/*  44:    */  
/*  45:    */  private final CLPlatform platform;
/*  46:    */  private final CLObjectRegistry<CLDevice> subCLDevices;
/*  47:    */  private Object caps;
/*  48:    */  
/*  49:    */  CLDevice(long pointer, CLPlatform platform)
/*  50:    */  {
/*  51: 51 */    this(pointer, null, platform);
/*  52:    */  }
/*  53:    */  
/*  59:    */  CLDevice(long pointer, CLDevice parent)
/*  60:    */  {
/*  61: 61 */    this(pointer, parent, parent.getPlatform());
/*  62:    */  }
/*  63:    */  
/*  64:    */  CLDevice(long pointer, CLDevice parent, CLPlatform platform) {
/*  65: 65 */    super(pointer, parent);
/*  66:    */    
/*  67: 67 */    if (isValid()) {
/*  68: 68 */      this.platform = platform;
/*  69: 69 */      platform.getCLDeviceRegistry().registerObject(this);
/*  70:    */      
/*  71: 71 */      this.subCLDevices = new CLObjectRegistry();
/*  72: 72 */      if (parent != null)
/*  73: 73 */        parent.subCLDevices.registerObject(this);
/*  74:    */    } else {
/*  75: 75 */      this.platform = null;
/*  76: 76 */      this.subCLDevices = null;
/*  77:    */    }
/*  78:    */  }
/*  79:    */  
/*  80:    */  public CLPlatform getPlatform() {
/*  81: 81 */    return this.platform;
/*  82:    */  }
/*  83:    */  
/*  89:    */  public CLDevice getSubCLDevice(long id)
/*  90:    */  {
/*  91: 91 */    return (CLDevice)this.subCLDevices.getObject(id);
/*  92:    */  }
/*  93:    */  
/* 101:    */  public String getInfoString(int param_name)
/* 102:    */  {
/* 103:103 */    return util.getInfoString(this, param_name);
/* 104:    */  }
/* 105:    */  
/* 112:    */  public int getInfoInt(int param_name)
/* 113:    */  {
/* 114:114 */    return util.getInfoInt(this, param_name);
/* 115:    */  }
/* 116:    */  
/* 123:    */  public boolean getInfoBoolean(int param_name)
/* 124:    */  {
/* 125:125 */    return util.getInfoInt(this, param_name) != 0;
/* 126:    */  }
/* 127:    */  
/* 134:    */  public long getInfoSize(int param_name)
/* 135:    */  {
/* 136:136 */    return util.getInfoSize(this, param_name);
/* 137:    */  }
/* 138:    */  
/* 145:    */  public long[] getInfoSizeArray(int param_name)
/* 146:    */  {
/* 147:147 */    return util.getInfoSizeArray(this, param_name);
/* 148:    */  }
/* 149:    */  
/* 157:    */  public long getInfoLong(int param_name)
/* 158:    */  {
/* 159:159 */    return util.getInfoLong(this, param_name);
/* 160:    */  }
/* 161:    */  
/* 163:    */  void setCapabilities(Object caps)
/* 164:    */  {
/* 165:165 */    this.caps = caps;
/* 166:    */  }
/* 167:    */  
/* 168:    */  Object getCapabilities() {
/* 169:169 */    return this.caps;
/* 170:    */  }
/* 171:    */  
/* 172:    */  int retain() {
/* 173:173 */    if (getParent() == null) {
/* 174:174 */      return getReferenceCount();
/* 175:    */    }
/* 176:176 */    return super.retain();
/* 177:    */  }
/* 178:    */  
/* 179:    */  int release() {
/* 180:180 */    if (getParent() == null) {
/* 181:181 */      return getReferenceCount();
/* 182:    */    }
/* 183:    */    try {
/* 184:184 */      return super.release();
/* 185:    */    } finally {
/* 186:186 */      if (!isValid())
/* 187:187 */        ((CLDevice)getParent()).subCLDevices.unregisterObject(this);
/* 188:    */    }
/* 189:    */  }
/* 190:    */  
/* 191:191 */  CLObjectRegistry<CLDevice> getSubCLDeviceRegistry() { return this.subCLDevices; }
/* 192:    */  
/* 197:    */  void registerSubCLDevices(PointerBuffer devices)
/* 198:    */  {
/* 199:199 */    for (int i = devices.position(); i < devices.limit(); i++) {
/* 200:200 */      long pointer = devices.get(i);
/* 201:201 */      if (pointer != 0L) {
/* 202:202 */        new CLDevice(pointer, this);
/* 203:    */      }
/* 204:    */    }
/* 205:    */  }
/* 206:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLDevice
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */