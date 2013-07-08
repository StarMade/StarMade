/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*  38:    */public final class CLKernel
/*  39:    */  extends CLObjectChild<CLProgram>
/*  40:    */{
/*  41: 41 */  private static final CLKernelUtil util = (CLKernelUtil)CLPlatform.getInfoUtilInstance(CLKernel.class, "CL_KERNEL_UTIL");
/*  42:    */  
/*  43:    */  CLKernel(long pointer, CLProgram program) {
/*  44: 44 */    super(pointer, program);
/*  45: 45 */    if (isValid()) {
/*  46: 46 */      program.getCLKernelRegistry().registerObject(this);
/*  47:    */    }
/*  48:    */  }
/*  49:    */  
/*  61:    */  public CLKernel setArg(int index, byte value)
/*  62:    */  {
/*  63: 63 */    util.setArg(this, index, value);
/*  64: 64 */    return this;
/*  65:    */  }
/*  66:    */  
/*  75:    */  public CLKernel setArg(int index, short value)
/*  76:    */  {
/*  77: 77 */    util.setArg(this, index, value);
/*  78: 78 */    return this;
/*  79:    */  }
/*  80:    */  
/*  89:    */  public CLKernel setArg(int index, int value)
/*  90:    */  {
/*  91: 91 */    util.setArg(this, index, value);
/*  92: 92 */    return this;
/*  93:    */  }
/*  94:    */  
/* 103:    */  public CLKernel setArg(int index, long value)
/* 104:    */  {
/* 105:105 */    util.setArg(this, index, value);
/* 106:106 */    return this;
/* 107:    */  }
/* 108:    */  
/* 117:    */  public CLKernel setArg(int index, float value)
/* 118:    */  {
/* 119:119 */    util.setArg(this, index, value);
/* 120:120 */    return this;
/* 121:    */  }
/* 122:    */  
/* 131:    */  public CLKernel setArg(int index, double value)
/* 132:    */  {
/* 133:133 */    util.setArg(this, index, value);
/* 134:134 */    return this;
/* 135:    */  }
/* 136:    */  
/* 145:    */  public CLKernel setArg(int index, CLObject value)
/* 146:    */  {
/* 147:147 */    util.setArg(this, index, value);
/* 148:148 */    return this;
/* 149:    */  }
/* 150:    */  
/* 158:    */  public CLKernel setArgSize(int index, long size)
/* 159:    */  {
/* 160:160 */    util.setArgSize(this, index, size);
/* 161:161 */    return this;
/* 162:    */  }
/* 163:    */  
/* 172:    */  public String getInfoString(int param_name)
/* 173:    */  {
/* 174:174 */    return util.getInfoString(this, param_name);
/* 175:    */  }
/* 176:    */  
/* 183:    */  public int getInfoInt(int param_name)
/* 184:    */  {
/* 185:185 */    return util.getInfoInt(this, param_name);
/* 186:    */  }
/* 187:    */  
/* 196:    */  public long getWorkGroupInfoSize(CLDevice device, int param_name)
/* 197:    */  {
/* 198:198 */    return util.getWorkGroupInfoSize(this, device, param_name);
/* 199:    */  }
/* 200:    */  
/* 207:    */  public long[] getWorkGroupInfoSizeArray(CLDevice device, int param_name)
/* 208:    */  {
/* 209:209 */    return util.getWorkGroupInfoSizeArray(this, device, param_name);
/* 210:    */  }
/* 211:    */  
/* 219:    */  public long getWorkGroupInfoLong(CLDevice device, int param_name)
/* 220:    */  {
/* 221:221 */    return util.getWorkGroupInfoLong(this, device, param_name);
/* 222:    */  }
/* 223:    */  
/* 251:    */  int release()
/* 252:    */  {
/* 253:    */    try
/* 254:    */    {
/* 255:255 */      return super.release();
/* 256:    */    } finally {
/* 257:257 */      if (!isValid()) {
/* 258:258 */        ((CLProgram)getParent()).getCLKernelRegistry().unregisterObject(this);
/* 259:    */      }
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 263:    */  static abstract interface CLKernelUtil
/* 264:    */    extends InfoUtil<CLKernel>
/* 265:    */  {
/* 266:    */    public abstract void setArg(CLKernel paramCLKernel, int paramInt, byte paramByte);
/* 267:    */    
/* 268:    */    public abstract void setArg(CLKernel paramCLKernel, int paramInt, short paramShort);
/* 269:    */    
/* 270:    */    public abstract void setArg(CLKernel paramCLKernel, int paramInt1, int paramInt2);
/* 271:    */    
/* 272:    */    public abstract void setArg(CLKernel paramCLKernel, int paramInt, long paramLong);
/* 273:    */    
/* 274:    */    public abstract void setArg(CLKernel paramCLKernel, int paramInt, float paramFloat);
/* 275:    */    
/* 276:    */    public abstract void setArg(CLKernel paramCLKernel, int paramInt, double paramDouble);
/* 277:    */    
/* 278:    */    public abstract void setArg(CLKernel paramCLKernel, int paramInt, CLObject paramCLObject);
/* 279:    */    
/* 280:    */    public abstract void setArgSize(CLKernel paramCLKernel, int paramInt, long paramLong);
/* 281:    */    
/* 282:    */    public abstract long getWorkGroupInfoSize(CLKernel paramCLKernel, CLDevice paramCLDevice, int paramInt);
/* 283:    */    
/* 284:    */    public abstract long[] getWorkGroupInfoSizeArray(CLKernel paramCLKernel, CLDevice paramCLDevice, int paramInt);
/* 285:    */    
/* 286:    */    public abstract long getWorkGroupInfoLong(CLKernel paramCLKernel, CLDevice paramCLDevice, int paramInt);
/* 287:    */  }
/* 288:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLKernel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */