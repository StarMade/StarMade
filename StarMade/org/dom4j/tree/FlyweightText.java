package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

public class FlyweightText
  extends AbstractText
  implements Text
{
  protected String text;
  
  public FlyweightText(String text)
  {
    this.text = text;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  protected Node createXPathResult(Element parent)
  {
    return new DefaultText(parent, getText());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.FlyweightText
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */