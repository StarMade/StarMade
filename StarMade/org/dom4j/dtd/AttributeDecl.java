package org.dom4j.dtd;

public class AttributeDecl
{
  private String elementName;
  private String attributeName;
  private String type;
  private String value;
  private String valueDefault;
  
  public AttributeDecl() {}
  
  public AttributeDecl(String elementName, String attributeName, String type, String valueDefault, String value)
  {
    this.elementName = elementName;
    this.attributeName = attributeName;
    this.type = type;
    this.value = value;
    this.valueDefault = valueDefault;
  }
  
  public String getElementName()
  {
    return this.elementName;
  }
  
  public void setElementName(String elementName)
  {
    this.elementName = elementName;
  }
  
  public String getAttributeName()
  {
    return this.attributeName;
  }
  
  public void setAttributeName(String attributeName)
  {
    this.attributeName = attributeName;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
  
  public String getValueDefault()
  {
    return this.valueDefault;
  }
  
  public void setValueDefault(String valueDefault)
  {
    this.valueDefault = valueDefault;
  }
  
  public String toString()
  {
    StringBuffer buffer = new StringBuffer("<!ATTLIST ");
    buffer.append(this.elementName);
    buffer.append(" ");
    buffer.append(this.attributeName);
    buffer.append(" ");
    buffer.append(this.type);
    buffer.append(" ");
    if (this.valueDefault != null)
    {
      buffer.append(this.valueDefault);
      if (this.valueDefault.equals("#FIXED"))
      {
        buffer.append(" \"");
        buffer.append(this.value);
        buffer.append("\"");
      }
    }
    else
    {
      buffer.append("\"");
      buffer.append(this.value);
      buffer.append("\"");
    }
    buffer.append(">");
    return buffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dtd.AttributeDecl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */