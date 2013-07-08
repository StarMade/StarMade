package org.lwjgl.opencl;

abstract interface InfoUtil<T extends CLObject>
{
  public abstract int getInfoInt(T paramT, int paramInt);
  
  public abstract long getInfoSize(T paramT, int paramInt);
  
  public abstract long[] getInfoSizeArray(T paramT, int paramInt);
  
  public abstract long getInfoLong(T paramT, int paramInt);
  
  public abstract String getInfoString(T paramT, int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.InfoUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */