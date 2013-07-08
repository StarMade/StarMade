/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.ElementHandler;
/*   5:    */
/*  35:    */class PruningElementStack
/*  36:    */  extends ElementStack
/*  37:    */{
/*  38:    */  private ElementHandler elementHandler;
/*  39:    */  private String[] path;
/*  40:    */  private int matchingElementIndex;
/*  41:    */  
/*  42:    */  public PruningElementStack(String[] path, ElementHandler elementHandler)
/*  43:    */  {
/*  44: 44 */    this.path = path;
/*  45: 45 */    this.elementHandler = elementHandler;
/*  46: 46 */    checkPath();
/*  47:    */  }
/*  48:    */  
/*  49:    */  public PruningElementStack(String[] path, ElementHandler elementHandler, int defaultCapacity)
/*  50:    */  {
/*  51: 51 */    super(defaultCapacity);
/*  52: 52 */    this.path = path;
/*  53: 53 */    this.elementHandler = elementHandler;
/*  54: 54 */    checkPath();
/*  55:    */  }
/*  56:    */  
/*  57:    */  public Element popElement() {
/*  58: 58 */    Element answer = super.popElement();
/*  59:    */    
/*  60: 60 */    if ((this.lastElementIndex == this.matchingElementIndex) && (this.lastElementIndex >= 0))
/*  61:    */    {
/*  67: 67 */      if (validElement(answer, this.lastElementIndex + 1)) {
/*  68: 68 */        Element parent = null;
/*  69:    */        
/*  70: 70 */        for (int i = 0; i <= this.lastElementIndex; i++) {
/*  71: 71 */          parent = this.stack[i];
/*  72:    */          
/*  73: 73 */          if (!validElement(parent, i)) {
/*  74: 74 */            parent = null;
/*  75:    */            
/*  76: 76 */            break;
/*  77:    */          }
/*  78:    */        }
/*  79:    */        
/*  80: 80 */        if (parent != null) {
/*  81: 81 */          pathMatches(parent, answer);
/*  82:    */        }
/*  83:    */      }
/*  84:    */    }
/*  85:    */    
/*  86: 86 */    return answer;
/*  87:    */  }
/*  88:    */  
/*  89:    */  protected void pathMatches(Element parent, Element selectedNode) {
/*  90: 90 */    this.elementHandler.onEnd(this);
/*  91: 91 */    parent.remove(selectedNode);
/*  92:    */  }
/*  93:    */  
/*  94:    */  protected boolean validElement(Element element, int index) {
/*  95: 95 */    String requiredName = this.path[index];
/*  96: 96 */    String name = element.getName();
/*  97:    */    
/*  98: 98 */    if (requiredName == name) {
/*  99: 99 */      return true;
/* 100:    */    }
/* 101:    */    
/* 102:102 */    if ((requiredName != null) && (name != null)) {
/* 103:103 */      return requiredName.equals(name);
/* 104:    */    }
/* 105:    */    
/* 106:106 */    return false;
/* 107:    */  }
/* 108:    */  
/* 109:    */  private void checkPath() {
/* 110:110 */    if (this.path.length < 2) {
/* 111:111 */      throw new RuntimeException("Invalid path of length: " + this.path.length + " it must be greater than 2");
/* 112:    */    }
/* 113:    */    
/* 115:115 */    this.matchingElementIndex = (this.path.length - 2);
/* 116:    */  }
/* 117:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.PruningElementStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */