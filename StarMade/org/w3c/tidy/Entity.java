package org.w3c.tidy;

public class Entity
{
  private String name;
  private short code;
  
  public Entity(String paramString, int paramInt)
  {
    this.name = paramString;
    this.code = ((short)paramInt);
  }
  
  public short getCode()
  {
    return this.code;
  }
  
  public String getName()
  {
    return this.name;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.Entity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */