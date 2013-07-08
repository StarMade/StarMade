/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.PointerWrapperAbstract;
/*  4:   */
/* 40:   */public abstract class CLPrintfCallback
/* 41:   */  extends PointerWrapperAbstract
/* 42:   */{
/* 43:   */  protected CLPrintfCallback()
/* 44:   */  {
/* 45:45 */    super(CallbackUtil.getPrintfCallback());
/* 46:   */  }
/* 47:   */  
/* 48:   */  protected abstract void handleMessage(String paramString);
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLPrintfCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */