package org.dom4j.dtd;

public class InternalEntityDecl
{
  private String name;
  private String value;
  
  public InternalEntityDecl() {}
  
  public InternalEntityDecl(String name, String value)
  {
    this.name = name;
    this.value = value;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
  
  public String toString()
  {
    StringBuffer buffer = new StringBuffer("<!ENTITY ");
    if (this.name.startsWith("%"))
    {
      buffer.append("% ");
      buffer.append(this.name.substring(1));
    }
    else
    {
      buffer.append(this.name);
    }
    buffer.append(" \"");
    buffer.append(escapeEntityValue(this.value));
    buffer.append("\">");
    return buffer.toString();
  }
  
  private String escapeEntityValue(String text)
  {
    StringBuffer result = new StringBuffer();
    for (int local_i = 0; local_i < text.length(); local_i++)
    {
      char local_c = text.charAt(local_i);
      switch (local_c)
      {
      case '<': 
        result.append("&#38;#60;");
        break;
      case '>': 
        result.append("&#62;");
        break;
      case '&': 
        result.append("&#38;#38;");
        break;
      case '\'': 
        result.append("&#39;");
        break;
      case '"': 
        result.append("&#34;");
        break;
      default: 
        if (local_c < ' ') {
          result.append("&#" + local_c + ";");
        } else {
          result.append(local_c);
        }
        break;
      }
    }
    return result.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dtd.InternalEntityDecl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */