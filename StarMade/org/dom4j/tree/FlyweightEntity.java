package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Node;

public class FlyweightEntity
  extends AbstractEntity
{
  protected String name;
  protected String text;
  
  protected FlyweightEntity() {}
  
  public FlyweightEntity(String name)
  {
    this.name = name;
  }
  
  public FlyweightEntity(String name, String text)
  {
    this.name = name;
    this.text = text;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public void setText(String text)
  {
    if (this.text != null) {
      this.text = text;
    } else {
      throw new UnsupportedOperationException("This Entity is read-only. It cannot be modified");
    }
  }
  
  protected Node createXPathResult(Element parent)
  {
    return new DefaultEntity(parent, getName(), getText());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.FlyweightEntity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */