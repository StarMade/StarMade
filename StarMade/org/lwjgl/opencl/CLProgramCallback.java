package org.lwjgl.opencl;

import org.lwjgl.PointerWrapperAbstract;

abstract class CLProgramCallback
  extends PointerWrapperAbstract
{
  private CLContext context;
  
  protected CLProgramCallback()
  {
    super(CallbackUtil.getProgramCallback());
  }
  
  final void setContext(CLContext context)
  {
    this.context = context;
  }
  
  private void handleMessage(long program_address)
  {
    handleMessage(this.context.getCLProgram(program_address));
  }
  
  protected abstract void handleMessage(CLProgram paramCLProgram);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLProgramCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */