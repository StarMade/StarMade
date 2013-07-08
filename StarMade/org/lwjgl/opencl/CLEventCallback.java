/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.PointerWrapperAbstract;
/*  4:   */
/* 41:   */public abstract class CLEventCallback
/* 42:   */  extends PointerWrapperAbstract
/* 43:   */{
/* 44:   */  private CLObjectRegistry<CLEvent> eventRegistry;
/* 45:   */  
/* 46:   */  protected CLEventCallback()
/* 47:   */  {
/* 48:48 */    super(CallbackUtil.getEventCallback());
/* 49:   */  }
/* 50:   */  
/* 55:   */  void setRegistry(CLObjectRegistry<CLEvent> eventRegistry)
/* 56:   */  {
/* 57:57 */    this.eventRegistry = eventRegistry;
/* 58:   */  }
/* 59:   */  
/* 64:   */  private void handleMessage(long event_address, int event_command_exec_status)
/* 65:   */  {
/* 66:66 */    handleMessage((CLEvent)this.eventRegistry.getObject(event_address), event_command_exec_status);
/* 67:   */  }
/* 68:   */  
/* 69:   */  protected abstract void handleMessage(CLEvent paramCLEvent, int paramInt);
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLEventCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */