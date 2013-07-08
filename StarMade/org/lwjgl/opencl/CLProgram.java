/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import org.lwjgl.PointerBuffer;
/*   5:    */
/*  42:    */public final class CLProgram
/*  43:    */  extends CLObjectChild<CLContext>
/*  44:    */{
/*  45: 45 */  private static final CLProgramUtil util = (CLProgramUtil)CLPlatform.getInfoUtilInstance(CLProgram.class, "CL_PROGRAM_UTIL");
/*  46:    */  private final CLObjectRegistry<CLKernel> clKernels;
/*  47:    */  
/*  48:    */  CLProgram(long pointer, CLContext context)
/*  49:    */  {
/*  50: 50 */    super(pointer, context);
/*  51:    */    
/*  52: 52 */    if (isValid()) {
/*  53: 53 */      context.getCLProgramRegistry().registerObject(this);
/*  54: 54 */      this.clKernels = new CLObjectRegistry();
/*  55:    */    } else {
/*  56: 56 */      this.clKernels = null;
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  65:    */  public CLKernel getCLKernel(long id)
/*  66:    */  {
/*  67: 67 */    return (CLKernel)this.clKernels.getObject(id);
/*  68:    */  }
/*  69:    */  
/*  76:    */  public CLKernel[] createKernelsInProgram()
/*  77:    */  {
/*  78: 78 */    return util.createKernelsInProgram(this);
/*  79:    */  }
/*  80:    */  
/*  87:    */  public String getInfoString(int param_name)
/*  88:    */  {
/*  89: 89 */    return util.getInfoString(this, param_name);
/*  90:    */  }
/*  91:    */  
/*  98:    */  public int getInfoInt(int param_name)
/*  99:    */  {
/* 100:100 */    return util.getInfoInt(this, param_name);
/* 101:    */  }
/* 102:    */  
/* 109:    */  public long[] getInfoSizeArray(int param_name)
/* 110:    */  {
/* 111:111 */    return util.getInfoSizeArray(this, param_name);
/* 112:    */  }
/* 113:    */  
/* 118:    */  public CLDevice[] getInfoDevices()
/* 119:    */  {
/* 120:120 */    return util.getInfoDevices(this);
/* 121:    */  }
/* 122:    */  
/* 133:    */  public ByteBuffer getInfoBinaries(ByteBuffer target)
/* 134:    */  {
/* 135:135 */    return util.getInfoBinaries(this, target);
/* 136:    */  }
/* 137:    */  
/* 148:    */  public ByteBuffer[] getInfoBinaries(ByteBuffer[] target)
/* 149:    */  {
/* 150:150 */    return util.getInfoBinaries(this, target);
/* 151:    */  }
/* 152:    */  
/* 161:    */  public String getBuildInfoString(CLDevice device, int param_name)
/* 162:    */  {
/* 163:163 */    return util.getBuildInfoString(this, device, param_name);
/* 164:    */  }
/* 165:    */  
/* 172:    */  public int getBuildInfoInt(CLDevice device, int param_name)
/* 173:    */  {
/* 174:174 */    return util.getBuildInfoInt(this, device, param_name);
/* 175:    */  }
/* 176:    */  
/* 194:    */  CLObjectRegistry<CLKernel> getCLKernelRegistry()
/* 195:    */  {
/* 196:196 */    return this.clKernels;
/* 197:    */  }
/* 198:    */  
/* 202:    */  void registerCLKernels(PointerBuffer kernels)
/* 203:    */  {
/* 204:204 */    for (int i = kernels.position(); i < kernels.limit(); i++) {
/* 205:205 */      long pointer = kernels.get(i);
/* 206:206 */      if (pointer != 0L)
/* 207:207 */        new CLKernel(pointer, this);
/* 208:    */    }
/* 209:    */  }
/* 210:    */  
/* 211:    */  int release() {
/* 212:    */    try {
/* 213:213 */      return super.release();
/* 214:    */    } finally {
/* 215:215 */      if (!isValid()) {
/* 216:216 */        ((CLContext)getParent()).getCLProgramRegistry().unregisterObject(this);
/* 217:    */      }
/* 218:    */    }
/* 219:    */  }
/* 220:    */  
/* 221:    */  static abstract interface CLProgramUtil
/* 222:    */    extends InfoUtil<CLProgram>
/* 223:    */  {
/* 224:    */    public abstract CLKernel[] createKernelsInProgram(CLProgram paramCLProgram);
/* 225:    */    
/* 226:    */    public abstract CLDevice[] getInfoDevices(CLProgram paramCLProgram);
/* 227:    */    
/* 228:    */    public abstract ByteBuffer getInfoBinaries(CLProgram paramCLProgram, ByteBuffer paramByteBuffer);
/* 229:    */    
/* 230:    */    public abstract ByteBuffer[] getInfoBinaries(CLProgram paramCLProgram, ByteBuffer[] paramArrayOfByteBuffer);
/* 231:    */    
/* 232:    */    public abstract String getBuildInfoString(CLProgram paramCLProgram, CLDevice paramCLDevice, int paramInt);
/* 233:    */    
/* 234:    */    public abstract int getBuildInfoInt(CLProgram paramCLProgram, CLDevice paramCLDevice, int paramInt);
/* 235:    */  }
/* 236:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */