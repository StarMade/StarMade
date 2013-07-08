/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.dom4j.Document;
/*   5:    */import org.dom4j.Element;
/*   6:    */import org.dom4j.ElementHandler;
/*   7:    */import org.dom4j.ElementPath;
/*   8:    */import org.dom4j.Node;
/*   9:    */
/*  26:    */class SAXModifyElementHandler
/*  27:    */  implements ElementHandler
/*  28:    */{
/*  29:    */  private ElementModifier elemModifier;
/*  30:    */  private Element modifiedElement;
/*  31:    */  
/*  32:    */  public SAXModifyElementHandler(ElementModifier elemModifier)
/*  33:    */  {
/*  34: 34 */    this.elemModifier = elemModifier;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public void onStart(ElementPath elementPath) {
/*  38: 38 */    this.modifiedElement = elementPath.getCurrent();
/*  39:    */  }
/*  40:    */  
/*  41:    */  public void onEnd(ElementPath elementPath) {
/*  42:    */    try {
/*  43: 43 */      Element origElement = elementPath.getCurrent();
/*  44: 44 */      Element currentParent = origElement.getParent();
/*  45:    */      
/*  46: 46 */      if (currentParent != null)
/*  47:    */      {
/*  48: 48 */        Element clonedElem = (Element)origElement.clone();
/*  49:    */        
/*  51: 51 */        this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
/*  52:    */        
/*  53: 53 */        if (this.modifiedElement != null)
/*  54:    */        {
/*  55: 55 */          this.modifiedElement.setParent(origElement.getParent());
/*  56: 56 */          this.modifiedElement.setDocument(origElement.getDocument());
/*  57:    */          
/*  59: 59 */          int contentIndex = currentParent.indexOf(origElement);
/*  60: 60 */          currentParent.content().set(contentIndex, this.modifiedElement);
/*  61:    */        }
/*  62:    */        
/*  64: 64 */        origElement.detach();
/*  65:    */      }
/*  66: 66 */      else if (origElement.isRootElement())
/*  67:    */      {
/*  68: 68 */        Element clonedElem = (Element)origElement.clone();
/*  69:    */        
/*  71: 71 */        this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
/*  72:    */        
/*  73: 73 */        if (this.modifiedElement != null)
/*  74:    */        {
/*  75: 75 */          this.modifiedElement.setDocument(origElement.getDocument());
/*  76:    */          
/*  78: 78 */          Document doc = origElement.getDocument();
/*  79: 79 */          doc.setRootElement(this.modifiedElement);
/*  80:    */        }
/*  81:    */        
/*  83: 83 */        origElement.detach();
/*  84:    */      }
/*  85:    */      
/*  89: 89 */      if ((elementPath instanceof ElementStack)) {
/*  90: 90 */        ElementStack elementStack = (ElementStack)elementPath;
/*  91: 91 */        elementStack.popElement();
/*  92: 92 */        elementStack.pushElement(this.modifiedElement);
/*  93:    */      }
/*  94:    */    } catch (Exception ex) {
/*  95: 95 */      throw new SAXModifyException(ex);
/*  96:    */    }
/*  97:    */  }
/*  98:    */  
/* 103:    */  protected Element getModifiedElement()
/* 104:    */  {
/* 105:105 */    return this.modifiedElement;
/* 106:    */  }
/* 107:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifyElementHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */