/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.List;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ import org.lwjgl.opencl.api.Filter;
/*     */ 
/*     */ public final class CLPlatform extends CLObject
/*     */ {
/*  50 */   private static final CLPlatformUtil util = (CLPlatformUtil)getInfoUtilInstance(CLPlatform.class, "CL_PLATFORM_UTIL");
/*     */ 
/*  52 */   private static final FastLongMap<CLPlatform> clPlatforms = new FastLongMap();
/*     */   private final CLObjectRegistry<CLDevice> clDevices;
/*     */   private Object caps;
/*     */ 
/*     */   CLPlatform(long pointer)
/*     */   {
/*  59 */     super(pointer);
/*     */ 
/*  61 */     if (isValid()) {
/*  62 */       clPlatforms.put(pointer, this);
/*  63 */       this.clDevices = new CLObjectRegistry();
/*     */     } else {
/*  65 */       this.clDevices = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static CLPlatform getCLPlatform(long id)
/*     */   {
/*  75 */     return (CLPlatform)clPlatforms.get(id);
/*     */   }
/*     */ 
/*     */   public CLDevice getCLDevice(long id)
/*     */   {
/*  84 */     return (CLDevice)this.clDevices.getObject(id);
/*     */   }
/*     */ 
/*     */   static <T extends CLObject> InfoUtil<T> getInfoUtilInstance(Class<T> clazz, String fieldName)
/*     */   {
/*  90 */     InfoUtil instance = null;
/*     */     try {
/*  92 */       Class infoUtil = Class.forName("org.lwjgl.opencl.InfoUtilFactory");
/*  93 */       instance = (InfoUtil)infoUtil.getDeclaredField(fieldName).get(null);
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*  97 */     return instance;
/*     */   }
/*     */ 
/*     */   public static List<CLPlatform> getPlatforms()
/*     */   {
/* 106 */     return getPlatforms(null);
/*     */   }
/*     */ 
/*     */   public static List<CLPlatform> getPlatforms(Filter<CLPlatform> filter)
/*     */   {
/* 117 */     return util.getPlatforms(filter);
/*     */   }
/*     */ 
/*     */   public String getInfoString(int param_name)
/*     */   {
/* 128 */     return util.getInfoString(this, param_name);
/*     */   }
/*     */ 
/*     */   public List<CLDevice> getDevices(int device_type)
/*     */   {
/* 140 */     return getDevices(device_type, null);
/*     */   }
/*     */ 
/*     */   public List<CLDevice> getDevices(int device_type, Filter<CLDevice> filter)
/*     */   {
/* 153 */     return util.getDevices(this, device_type, filter);
/*     */   }
/*     */ 
/*     */   void setCapabilities(Object caps)
/*     */   {
/* 168 */     this.caps = caps;
/*     */   }
/*     */ 
/*     */   Object getCapabilities() {
/* 172 */     return this.caps;
/*     */   }
/*     */ 
/*     */   static void registerCLPlatforms(PointerBuffer platforms, IntBuffer num_platforms)
/*     */   {
/* 181 */     if (platforms == null) {
/* 182 */       return;
/*     */     }
/* 184 */     int pos = platforms.position();
/* 185 */     int count = Math.min(num_platforms.get(0), platforms.remaining());
/* 186 */     for (int i = 0; i < count; i++) {
/* 187 */       long id = platforms.get(pos + i);
/* 188 */       if (!clPlatforms.containsKey(id))
/* 189 */         new CLPlatform(id); 
/*     */     }
/*     */   }
/*     */ 
/* 193 */   CLObjectRegistry<CLDevice> getCLDeviceRegistry() { return this.clDevices; }
/*     */ 
/*     */ 
/*     */   void registerCLDevices(PointerBuffer devices, IntBuffer num_devices)
/*     */   {
/* 201 */     int pos = devices.position();
/* 202 */     int count = Math.min(num_devices.get(num_devices.position()), devices.remaining());
/* 203 */     for (int i = 0; i < count; i++) {
/* 204 */       long id = devices.get(pos + i);
/* 205 */       if (!this.clDevices.hasObject(id))
/* 206 */         new CLDevice(id, this);
/*     */     }
/*     */   }
/*     */ 
/*     */   void registerCLDevices(ByteBuffer devices, PointerBuffer num_devices)
/*     */   {
/* 216 */     int pos = devices.position();
/* 217 */     int count = Math.min((int)num_devices.get(num_devices.position()), devices.remaining()) / PointerBuffer.getPointerSize();
/* 218 */     for (int i = 0; i < count; i++) {
/* 219 */       int offset = pos + i * PointerBuffer.getPointerSize();
/* 220 */       long id = PointerBuffer.is64Bit() ? devices.getLong(offset) : devices.getInt(offset);
/* 221 */       if (!this.clDevices.hasObject(id))
/* 222 */         new CLDevice(id, this);
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface CLPlatformUtil extends InfoUtil<CLPlatform>
/*     */   {
/*     */     public abstract List<CLPlatform> getPlatforms(Filter<CLPlatform> paramFilter);
/*     */ 
/*     */     public abstract List<CLDevice> getDevices(CLPlatform paramCLPlatform, int paramInt, Filter<CLDevice> paramFilter);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLPlatform
 * JD-Core Version:    0.6.2
 */