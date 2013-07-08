package org.lwjgl.opencl.api;

public final class CLImageFormat
{
  public static final int STRUCT_SIZE = 8;
  private final int channelOrder;
  private final int channelType;
  
  public CLImageFormat(int channelOrder, int channelType)
  {
    this.channelOrder = channelOrder;
    this.channelType = channelType;
  }
  
  public int getChannelOrder()
  {
    return this.channelOrder;
  }
  
  public int getChannelType()
  {
    return this.channelType;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.api.CLImageFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */