package org.dom4j.dtd;

public class ElementDecl
{
  private String name;
  private String model;
  
  public ElementDecl() {}
  
  public ElementDecl(String name, String model)
  {
    this.name = name;
    this.model = model;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getModel()
  {
    return this.model;
  }
  
  public void setModel(String model)
  {
    this.model = model;
  }
  
  public String toString()
  {
    return "<!ELEMENT " + this.name + " " + this.model + ">";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dtd.ElementDecl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */