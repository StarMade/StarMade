/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ public final class CLDevice extends CLObjectChild<CLDevice>
/*     */ {
/*  43 */   private static final InfoUtil<CLDevice> util = CLPlatform.getInfoUtilInstance(CLDevice.class, "CL_DEVICE_UTIL");
/*     */   private final CLPlatform platform;
/*     */   private final CLObjectRegistry<CLDevice> subCLDevices;
/*     */   private Object caps;
/*     */ 
/*     */   CLDevice(long pointer, CLPlatform platform)
/*     */   {
/*  51 */     this(pointer, null, platform);
/*     */   }
/*     */ 
/*     */   CLDevice(long pointer, CLDevice parent)
/*     */   {
/*  61 */     this(pointer, parent, parent.getPlatform());
/*     */   }
/*     */ 
/*     */   CLDevice(long pointer, CLDevice parent, CLPlatform platform) {
/*  65 */     super(pointer, parent);
/*     */ 
/*  67 */     if (isValid()) {
/*  68 */       this.platform = platform;
/*  69 */       platform.getCLDeviceRegistry().registerObject(this);
/*     */ 
/*  71 */       this.subCLDevices = new CLObjectRegistry();
/*  72 */       if (parent != null)
/*  73 */         parent.subCLDevices.registerObject(this);
/*     */     } else {
/*  75 */       this.platform = null;
/*  76 */       this.subCLDevices = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public CLPlatform getPlatform() {
/*  81 */     return this.platform;
/*     */   }
/*     */ 
/*     */   public CLDevice getSubCLDevice(long id)
/*     */   {
/*  91 */     return (CLDevice)this.subCLDevices.getObject(id);
/*     */   }
/*     */ 
/*     */   public String getInfoString(int param_name)
/*     */   {
/* 103 */     return util.getInfoString(this, param_name);
/*     */   }
/*     */ 
/*     */   public int getInfoInt(int param_name)
/*     */   {
/* 114 */     return util.getInfoInt(this, param_name);
/*     */   }
/*     */ 
/*     */   public boolean getInfoBoolean(int param_name)
/*     */   {
/* 125 */     return util.getInfoInt(this, param_name) != 0;
/*     */   }
/*     */ 
/*     */   public long getInfoSize(int param_name)
/*     */   {
/* 136 */     return util.getInfoSize(this, param_name);
/*     */   }
/*     */ 
/*     */   public long[] getInfoSizeArray(int param_name)
/*     */   {
/* 147 */     return util.getInfoSizeArray(this, param_name);
/*     */   }
/*     */ 
/*     */   public long getInfoLong(int param_name)
/*     */   {
/* 159 */     return util.getInfoLong(this, param_name);
/*     */   }
/*     */ 
/*     */   void setCapabilities(Object caps)
/*     */   {
/* 165 */     this.caps = caps;
/*     */   }
/*     */ 
/*     */   Object getCapabilities() {
/* 169 */     return this.caps;
/*     */   }
/*     */ 
/*     */   int retain() {
/* 173 */     if (getParent() == null) {
/* 174 */       return getReferenceCount();
/*     */     }
/* 176 */     return super.retain();
/*     */   }
/*     */ 
/*     */   int release() {
/* 180 */     if (getParent() == null)
/* 181 */       return getReferenceCount();
/*     */     try
/*     */     {
/* 184 */       return super.release();
/*     */     } finally {
/* 186 */       if (!isValid())
/* 187 */         ((CLDevice)getParent()).subCLDevices.unregisterObject(this); 
/*     */     }
/*     */   }
/*     */ 
/* 191 */   CLObjectRegistry<CLDevice> getSubCLDeviceRegistry() { return this.subCLDevices; }
/*     */ 
/*     */ 
/*     */   void registerSubCLDevices(PointerBuffer devices)
/*     */   {
/* 199 */     for (int i = devices.position(); i < devices.limit(); i++) {
/* 200 */       long pointer = devices.get(i);
/* 201 */       if (pointer != 0L)
/* 202 */         new CLDevice(pointer, this);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLDevice
 * JD-Core Version:    0.6.2
 */