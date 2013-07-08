/*  1:   */package org.lwjgl.opencl.api;
/*  2:   */
/* 13:   */public final class CLImageFormat
/* 14:   */{
/* 15:   */  public static final int STRUCT_SIZE = 8;
/* 16:   */  
/* 25:   */  private final int channelOrder;
/* 26:   */  
/* 35:   */  private final int channelType;
/* 36:   */  
/* 46:   */  public CLImageFormat(int channelOrder, int channelType)
/* 47:   */  {
/* 48:48 */    this.channelOrder = channelOrder;
/* 49:49 */    this.channelType = channelType;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public int getChannelOrder() {
/* 53:53 */    return this.channelOrder;
/* 54:   */  }
/* 55:   */  
/* 56:   */  public int getChannelType() {
/* 57:57 */    return this.channelType;
/* 58:   */  }
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.api.CLImageFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */