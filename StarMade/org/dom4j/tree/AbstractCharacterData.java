package org.dom4j.tree;

import org.dom4j.CharacterData;
import org.dom4j.Element;

public abstract class AbstractCharacterData
  extends AbstractNode
  implements CharacterData
{
  public String getPath(Element context)
  {
    Element parent = getParent();
    return (parent != null) && (parent != context) ? parent.getPath(context) + "/text()" : "text()";
  }
  
  public String getUniquePath(Element context)
  {
    Element parent = getParent();
    return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/text()" : "text()";
  }
  
  public void appendText(String text)
  {
    setText(getText() + text);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractCharacterData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */