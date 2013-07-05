/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.PointerWrapperAbstract;
/*    */ 
/*    */ public abstract class CLEventCallback extends PointerWrapperAbstract
/*    */ {
/*    */   private CLObjectRegistry<CLEvent> eventRegistry;
/*    */ 
/*    */   protected CLEventCallback()
/*    */   {
/* 48 */     super(CallbackUtil.getEventCallback());
/*    */   }
/*    */ 
/*    */   void setRegistry(CLObjectRegistry<CLEvent> eventRegistry)
/*    */   {
/* 57 */     this.eventRegistry = eventRegistry;
/*    */   }
/*    */ 
/*    */   private void handleMessage(long event_address, int event_command_exec_status)
/*    */   {
/* 66 */     handleMessage((CLEvent)this.eventRegistry.getObject(event_address), event_command_exec_status);
/*    */   }
/*    */ 
/*    */   protected abstract void handleMessage(CLEvent paramCLEvent, int paramInt);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLEventCallback
 * JD-Core Version:    0.6.2
 */