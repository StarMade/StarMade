/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.PointerWrapperAbstract;
/*  4:   */
/* 39:   */public abstract class CLMemObjectDestructorCallback
/* 40:   */  extends PointerWrapperAbstract
/* 41:   */{
/* 42:   */  protected CLMemObjectDestructorCallback()
/* 43:   */  {
/* 44:44 */    super(CallbackUtil.getMemObjectDestructorCallback());
/* 45:   */  }
/* 46:   */  
/* 47:   */  protected abstract void handleMessage(long paramLong);
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLMemObjectDestructorCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */