package org.w3c.tidy;

public class StyleProp
{
  protected String name;
  protected String value;
  protected StyleProp next;
  
  public StyleProp(String paramString1, String paramString2, StyleProp paramStyleProp)
  {
    this.name = paramString1;
    this.value = paramString2;
    this.next = paramStyleProp;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.StyleProp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */