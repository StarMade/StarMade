package org.dom4j.tree;

import org.dom4j.Element;

public class DefaultEntity
  extends FlyweightEntity
{
  private Element parent;
  
  public DefaultEntity(String name)
  {
    super(name);
  }
  
  public DefaultEntity(String name, String text)
  {
    super(name, text);
  }
  
  public DefaultEntity(Element parent, String name, String text)
  {
    super(name, text);
    this.parent = parent;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setText(String text)
  {
    this.text = text;
  }
  
  public Element getParent()
  {
    return this.parent;
  }
  
  public void setParent(Element parent)
  {
    this.parent = parent;
  }
  
  public boolean supportsParent()
  {
    return true;
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.DefaultEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */