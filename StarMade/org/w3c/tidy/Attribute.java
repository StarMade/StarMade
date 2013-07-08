package org.w3c.tidy;

public class Attribute
{
  private String name;
  private boolean nowrap;
  private boolean literal;
  private short versions;
  private AttrCheck attrchk;
  
  public Attribute(String paramString, short paramShort, AttrCheck paramAttrCheck)
  {
    this.name = paramString;
    this.versions = paramShort;
    this.attrchk = paramAttrCheck;
  }
  
  public void setLiteral(boolean paramBoolean)
  {
    this.literal = paramBoolean;
  }
  
  public void setNowrap(boolean paramBoolean)
  {
    this.nowrap = paramBoolean;
  }
  
  public AttrCheck getAttrchk()
  {
    return this.attrchk;
  }
  
  public boolean isLiteral()
  {
    return this.literal;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public boolean isNowrap()
  {
    return this.nowrap;
  }
  
  public short getVersions()
  {
    return this.versions;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.Attribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */