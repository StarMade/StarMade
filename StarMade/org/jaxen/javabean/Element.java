package org.jaxen.javabean;

public class Element
{
  private Element parent;
  private String name;
  private Object object;
  
  public Element(Element parent, String name, Object object)
  {
    this.parent = parent;
    this.name = name;
    this.object = object;
  }
  
  public Element getParent()
  {
    return this.parent;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Object getObject()
  {
    return this.object;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.javabean.Element
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */