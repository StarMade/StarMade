package org.lwjgl.opencl;

import java.nio.ByteBuffer;
import org.lwjgl.PointerBuffer;

public final class CLProgram
  extends CLObjectChild<CLContext>
{
  private static final CLProgramUtil util = (CLProgramUtil)CLPlatform.getInfoUtilInstance(CLProgram.class, "CL_PROGRAM_UTIL");
  private final CLObjectRegistry<CLKernel> clKernels;
  
  CLProgram(long pointer, CLContext context)
  {
    super(pointer, context);
    if (isValid())
    {
      context.getCLProgramRegistry().registerObject(this);
      this.clKernels = new CLObjectRegistry();
    }
    else
    {
      this.clKernels = null;
    }
  }
  
  public CLKernel getCLKernel(long local_id)
  {
    return (CLKernel)this.clKernels.getObject(local_id);
  }
  
  public CLKernel[] createKernelsInProgram()
  {
    return util.createKernelsInProgram(this);
  }
  
  public String getInfoString(int param_name)
  {
    return util.getInfoString(this, param_name);
  }
  
  public int getInfoInt(int param_name)
  {
    return util.getInfoInt(this, param_name);
  }
  
  public long[] getInfoSizeArray(int param_name)
  {
    return util.getInfoSizeArray(this, param_name);
  }
  
  public CLDevice[] getInfoDevices()
  {
    return util.getInfoDevices(this);
  }
  
  public ByteBuffer getInfoBinaries(ByteBuffer target)
  {
    return util.getInfoBinaries(this, target);
  }
  
  public ByteBuffer[] getInfoBinaries(ByteBuffer[] target)
  {
    return util.getInfoBinaries(this, target);
  }
  
  public String getBuildInfoString(CLDevice device, int param_name)
  {
    return util.getBuildInfoString(this, device, param_name);
  }
  
  public int getBuildInfoInt(CLDevice device, int param_name)
  {
    return util.getBuildInfoInt(this, device, param_name);
  }
  
  CLObjectRegistry<CLKernel> getCLKernelRegistry()
  {
    return this.clKernels;
  }
  
  void registerCLKernels(PointerBuffer kernels)
  {
    for (int local_i = kernels.position(); local_i < kernels.limit(); local_i++)
    {
      long pointer = kernels.get(local_i);
      if (pointer != 0L) {
        new CLKernel(pointer, this);
      }
    }
  }
  
  int release()
  {
    try
    {
      int i = super.release();
      return i;
    }
    finally
    {
      if (!isValid()) {
        ((CLContext)getParent()).getCLProgramRegistry().unregisterObject(this);
      }
    }
  }
  
  static abstract interface CLProgramUtil
    extends InfoUtil<CLProgram>
  {
    public abstract CLKernel[] createKernelsInProgram(CLProgram paramCLProgram);
    
    public abstract CLDevice[] getInfoDevices(CLProgram paramCLProgram);
    
    public abstract ByteBuffer getInfoBinaries(CLProgram paramCLProgram, ByteBuffer paramByteBuffer);
    
    public abstract ByteBuffer[] getInfoBinaries(CLProgram paramCLProgram, ByteBuffer[] paramArrayOfByteBuffer);
    
    public abstract String getBuildInfoString(CLProgram paramCLProgram, CLDevice paramCLDevice, int paramInt);
    
    public abstract int getBuildInfoInt(CLProgram paramCLProgram, CLDevice paramCLDevice, int paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */