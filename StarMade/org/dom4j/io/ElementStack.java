package org.dom4j.io;

import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;

class ElementStack
  implements ElementPath
{
  protected Element[] stack;
  protected int lastElementIndex = -1;
  private DispatchHandler handler = null;
  
  public ElementStack()
  {
    this(50);
  }
  
  public ElementStack(int defaultCapacity)
  {
    this.stack = new Element[defaultCapacity];
  }
  
  public void setDispatchHandler(DispatchHandler dispatchHandler)
  {
    this.handler = dispatchHandler;
  }
  
  public DispatchHandler getDispatchHandler()
  {
    return this.handler;
  }
  
  public void clear()
  {
    this.lastElementIndex = -1;
  }
  
  public Element peekElement()
  {
    if (this.lastElementIndex < 0) {
      return null;
    }
    return this.stack[this.lastElementIndex];
  }
  
  public Element popElement()
  {
    if (this.lastElementIndex < 0) {
      return null;
    }
    return this.stack[(this.lastElementIndex--)];
  }
  
  public void pushElement(Element element)
  {
    int length = this.stack.length;
    if (++this.lastElementIndex >= length) {
      reallocate(length * 2);
    }
    this.stack[this.lastElementIndex] = element;
  }
  
  protected void reallocate(int size)
  {
    Element[] oldStack = this.stack;
    this.stack = new Element[size];
    System.arraycopy(oldStack, 0, this.stack, 0, oldStack.length);
  }
  
  public int size()
  {
    return this.lastElementIndex + 1;
  }
  
  public Element getElement(int depth)
  {
    Element element;
    try
    {
      element = this.stack[depth];
    }
    catch (ArrayIndexOutOfBoundsException local_e)
    {
      Element element;
      element = null;
    }
    return element;
  }
  
  public String getPath()
  {
    if (this.handler == null) {
      setDispatchHandler(new DispatchHandler());
    }
    return this.handler.getPath();
  }
  
  public Element getCurrent()
  {
    return peekElement();
  }
  
  public void addHandler(String path, ElementHandler elementHandler)
  {
    this.handler.addHandler(getHandlerPath(path), elementHandler);
  }
  
  public void removeHandler(String path)
  {
    this.handler.removeHandler(getHandlerPath(path));
  }
  
  public boolean containsHandler(String path)
  {
    return this.handler.containsHandler(path);
  }
  
  private String getHandlerPath(String path)
  {
    if (this.handler == null) {
      setDispatchHandler(new DispatchHandler());
    }
    String handlerPath;
    String handlerPath;
    if (path.startsWith("/"))
    {
      handlerPath = path;
    }
    else
    {
      String handlerPath;
      if (getPath().equals("/")) {
        handlerPath = getPath() + path;
      } else {
        handlerPath = getPath() + "/" + path;
      }
    }
    return handlerPath;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.ElementStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */