package org.w3c.tidy;

public class Style
{
  protected String tag;
  protected String tagClass;
  protected String properties;
  protected Style next;
  
  public Style(String paramString1, String paramString2, String paramString3, Style paramStyle)
  {
    this.tag = paramString1;
    this.tagClass = paramString2;
    this.properties = paramString3;
    this.next = paramStyle;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.Style
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */