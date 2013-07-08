package org.dom4j.tree;

import org.dom4j.Element;

public class DefaultText
  extends FlyweightText
{
  private Element parent;
  
  public DefaultText(String text)
  {
    super(text);
  }
  
  public DefaultText(Element parent, String text)
  {
    super(text);
    this.parent = parent;
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
 * Qualified Name:     org.dom4j.tree.DefaultText
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */