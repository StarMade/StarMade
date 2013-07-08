package org.lwjgl.opencl;

import org.lwjgl.PointerWrapperAbstract;

public abstract class CLPrintfCallback
  extends PointerWrapperAbstract
{
  protected CLPrintfCallback()
  {
    super(CallbackUtil.getPrintfCallback());
  }
  
  protected abstract void handleMessage(String paramString);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLPrintfCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */