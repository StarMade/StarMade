package org.dom4j.io;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.Node;

class SAXModifyElementHandler
  implements ElementHandler
{
  private ElementModifier elemModifier;
  private Element modifiedElement;
  
  public SAXModifyElementHandler(ElementModifier elemModifier)
  {
    this.elemModifier = elemModifier;
  }
  
  public void onStart(ElementPath elementPath)
  {
    this.modifiedElement = elementPath.getCurrent();
  }
  
  public void onEnd(ElementPath elementPath)
  {
    try
    {
      Element origElement = elementPath.getCurrent();
      Element currentParent = origElement.getParent();
      if (currentParent != null)
      {
        Element clonedElem = (Element)origElement.clone();
        this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
        if (this.modifiedElement != null)
        {
          this.modifiedElement.setParent(origElement.getParent());
          this.modifiedElement.setDocument(origElement.getDocument());
          int contentIndex = currentParent.indexOf(origElement);
          currentParent.content().set(contentIndex, this.modifiedElement);
        }
        origElement.detach();
      }
      else if (origElement.isRootElement())
      {
        Element clonedElem = (Element)origElement.clone();
        this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
        if (this.modifiedElement != null)
        {
          this.modifiedElement.setDocument(origElement.getDocument());
          Document contentIndex = origElement.getDocument();
          contentIndex.setRootElement(this.modifiedElement);
        }
        origElement.detach();
      }
      if ((elementPath instanceof ElementStack))
      {
        ElementStack clonedElem = (ElementStack)elementPath;
        clonedElem.popElement();
        clonedElem.pushElement(this.modifiedElement);
      }
    }
    catch (Exception origElement)
    {
      throw new SAXModifyException(origElement);
    }
  }
  
  protected Element getModifiedElement()
  {
    return this.modifiedElement;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.SAXModifyElementHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */