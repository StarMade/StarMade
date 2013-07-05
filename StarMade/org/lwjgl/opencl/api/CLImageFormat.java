/*    */ package org.lwjgl.opencl.api;
/*    */ 
/*    */ public final class CLImageFormat
/*    */ {
/*    */   public static final int STRUCT_SIZE = 8;
/*    */   private final int channelOrder;
/*    */   private final int channelType;
/*    */ 
/*    */   public CLImageFormat(int channelOrder, int channelType)
/*    */   {
/* 48 */     this.channelOrder = channelOrder;
/* 49 */     this.channelType = channelType;
/*    */   }
/*    */ 
/*    */   public int getChannelOrder() {
/* 53 */     return this.channelOrder;
/*    */   }
/*    */ 
/*    */   public int getChannelType() {
/* 57 */     return this.channelType;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.api.CLImageFormat
 * JD-Core Version:    0.6.2
 */