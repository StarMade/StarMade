package com.bulletphysics.collision.broadphase;

public enum DispatchFunc
{
  DISPATCH_DISCRETE(1),  DISPATCH_CONTINUOUS(2);
  
  private int value;
  
  private DispatchFunc(int value)
  {
    this.value = value;
  }
  
  public int getValue()
  {
    return this.value;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.DispatchFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */