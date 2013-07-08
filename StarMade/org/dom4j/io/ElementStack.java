/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import org.dom4j.Element;
/*   4:    */import org.dom4j.ElementHandler;
/*   5:    */import org.dom4j.ElementPath;
/*   6:    */
/*  26:    */class ElementStack
/*  27:    */  implements ElementPath
/*  28:    */{
/*  29:    */  protected Element[] stack;
/*  30: 30 */  protected int lastElementIndex = -1;
/*  31:    */  
/*  32: 32 */  private DispatchHandler handler = null;
/*  33:    */  
/*  34:    */  public ElementStack() {
/*  35: 35 */    this(50);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public ElementStack(int defaultCapacity) {
/*  39: 39 */    this.stack = new Element[defaultCapacity];
/*  40:    */  }
/*  41:    */  
/*  42:    */  public void setDispatchHandler(DispatchHandler dispatchHandler) {
/*  43: 43 */    this.handler = dispatchHandler;
/*  44:    */  }
/*  45:    */  
/*  46:    */  public DispatchHandler getDispatchHandler() {
/*  47: 47 */    return this.handler;
/*  48:    */  }
/*  49:    */  
/*  53:    */  public void clear()
/*  54:    */  {
/*  55: 55 */    this.lastElementIndex = -1;
/*  56:    */  }
/*  57:    */  
/*  63:    */  public Element peekElement()
/*  64:    */  {
/*  65: 65 */    if (this.lastElementIndex < 0) {
/*  66: 66 */      return null;
/*  67:    */    }
/*  68:    */    
/*  69: 69 */    return this.stack[this.lastElementIndex];
/*  70:    */  }
/*  71:    */  
/*  76:    */  public Element popElement()
/*  77:    */  {
/*  78: 78 */    if (this.lastElementIndex < 0) {
/*  79: 79 */      return null;
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    return this.stack[(this.lastElementIndex--)];
/*  83:    */  }
/*  84:    */  
/*  90:    */  public void pushElement(Element element)
/*  91:    */  {
/*  92: 92 */    int length = this.stack.length;
/*  93:    */    
/*  94: 94 */    if (++this.lastElementIndex >= length) {
/*  95: 95 */      reallocate(length * 2);
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    this.stack[this.lastElementIndex] = element;
/*  99:    */  }
/* 100:    */  
/* 106:    */  protected void reallocate(int size)
/* 107:    */  {
/* 108:108 */    Element[] oldStack = this.stack;
/* 109:109 */    this.stack = new Element[size];
/* 110:110 */    System.arraycopy(oldStack, 0, this.stack, 0, oldStack.length);
/* 111:    */  }
/* 112:    */  
/* 114:    */  public int size()
/* 115:    */  {
/* 116:116 */    return this.lastElementIndex + 1;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public Element getElement(int depth)
/* 120:    */  {
/* 121:    */    Element element;
/* 122:    */    try {
/* 123:123 */      element = this.stack[depth];
/* 124:    */    } catch (ArrayIndexOutOfBoundsException e) { Element element;
/* 125:125 */      element = null;
/* 126:    */    }
/* 127:    */    
/* 128:128 */    return element;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public String getPath() {
/* 132:132 */    if (this.handler == null) {
/* 133:133 */      setDispatchHandler(new DispatchHandler());
/* 134:    */    }
/* 135:    */    
/* 136:136 */    return this.handler.getPath();
/* 137:    */  }
/* 138:    */  
/* 139:    */  public Element getCurrent() {
/* 140:140 */    return peekElement();
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void addHandler(String path, ElementHandler elementHandler) {
/* 144:144 */    this.handler.addHandler(getHandlerPath(path), elementHandler);
/* 145:    */  }
/* 146:    */  
/* 147:    */  public void removeHandler(String path) {
/* 148:148 */    this.handler.removeHandler(getHandlerPath(path));
/* 149:    */  }
/* 150:    */  
/* 159:    */  public boolean containsHandler(String path)
/* 160:    */  {
/* 161:161 */    return this.handler.containsHandler(path);
/* 162:    */  }
/* 163:    */  
/* 165:    */  private String getHandlerPath(String path)
/* 166:    */  {
/* 167:167 */    if (this.handler == null)
/* 168:168 */      setDispatchHandler(new DispatchHandler());
/* 169:    */    String handlerPath;
/* 170:    */    String handlerPath;
/* 171:171 */    if (path.startsWith("/")) {
/* 172:172 */      handlerPath = path; } else { String handlerPath;
/* 173:173 */      if (getPath().equals("/")) {
/* 174:174 */        handlerPath = getPath() + path;
/* 175:    */      } else {
/* 176:176 */        handlerPath = getPath() + "/" + path;
/* 177:    */      }
/* 178:    */    }
/* 179:179 */    return handlerPath;
/* 180:    */  }
/* 181:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.ElementStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */