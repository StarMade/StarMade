package it.unimi.dsi.fastutil.booleans;

public abstract interface BooleanHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(boolean paramBoolean);
    
    public abstract boolean equals(boolean paramBoolean1, boolean paramBoolean2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanHash
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */