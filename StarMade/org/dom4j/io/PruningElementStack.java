package org.dom4j.io;

import org.dom4j.Element;
import org.dom4j.ElementHandler;

class PruningElementStack
  extends ElementStack
{
  private ElementHandler elementHandler;
  private String[] path;
  private int matchingElementIndex;
  
  public PruningElementStack(String[] path, ElementHandler elementHandler)
  {
    this.path = path;
    this.elementHandler = elementHandler;
    checkPath();
  }
  
  public PruningElementStack(String[] path, ElementHandler elementHandler, int defaultCapacity)
  {
    super(defaultCapacity);
    this.path = path;
    this.elementHandler = elementHandler;
    checkPath();
  }
  
  public Element popElement()
  {
    Element answer = super.popElement();
    if ((this.lastElementIndex == this.matchingElementIndex) && (this.lastElementIndex >= 0) && (validElement(answer, this.lastElementIndex + 1)))
    {
      Element parent = null;
      for (int local_i = 0; local_i <= this.lastElementIndex; local_i++)
      {
        parent = this.stack[local_i];
        if (!validElement(parent, local_i))
        {
          parent = null;
          break;
        }
      }
      if (parent != null) {
        pathMatches(parent, answer);
      }
    }
    return answer;
  }
  
  protected void pathMatches(Element parent, Element selectedNode)
  {
    this.elementHandler.onEnd(this);
    parent.remove(selectedNode);
  }
  
  protected boolean validElement(Element element, int index)
  {
    String requiredName = this.path[index];
    String name = element.getName();
    if (requiredName == name) {
      return true;
    }
    if ((requiredName != null) && (name != null)) {
      return requiredName.equals(name);
    }
    return false;
  }
  
  private void checkPath()
  {
    if (this.path.length < 2) {
      throw new RuntimeException("Invalid path of length: " + this.path.length + " it must be greater than 2");
    }
    this.matchingElementIndex = (this.path.length - 2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.PruningElementStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */