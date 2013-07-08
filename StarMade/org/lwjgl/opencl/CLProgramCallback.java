/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.PointerWrapperAbstract;
/*  4:   */
/* 39:   */abstract class CLProgramCallback
/* 40:   */  extends PointerWrapperAbstract
/* 41:   */{
/* 42:   */  private CLContext context;
/* 43:   */  
/* 44:   */  protected CLProgramCallback()
/* 45:   */  {
/* 46:46 */    super(CallbackUtil.getProgramCallback());
/* 47:   */  }
/* 48:   */  
/* 53:   */  final void setContext(CLContext context)
/* 54:   */  {
/* 55:55 */    this.context = context;
/* 56:   */  }
/* 57:   */  
/* 62:   */  private void handleMessage(long program_address)
/* 63:   */  {
/* 64:64 */    handleMessage(this.context.getCLProgram(program_address));
/* 65:   */  }
/* 66:   */  
/* 67:   */  protected abstract void handleMessage(CLProgram paramCLProgram);
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLProgramCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */