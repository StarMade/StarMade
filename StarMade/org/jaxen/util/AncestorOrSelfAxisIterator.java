/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */import org.jaxen.JaxenRuntimeException;
/*   6:    */import org.jaxen.Navigator;
/*   7:    */import org.jaxen.UnsupportedAxisException;
/*   8:    */
/*  76:    */public class AncestorOrSelfAxisIterator
/*  77:    */  implements Iterator
/*  78:    */{
/*  79:    */  private Object contextNode;
/*  80:    */  private Navigator navigator;
/*  81:    */  
/*  82:    */  public AncestorOrSelfAxisIterator(Object contextNode, Navigator navigator)
/*  83:    */  {
/*  84: 84 */    this.contextNode = contextNode;
/*  85: 85 */    this.navigator = navigator;
/*  86:    */  }
/*  87:    */  
/*  96:    */  public boolean hasNext()
/*  97:    */  {
/*  98: 98 */    return this.contextNode != null;
/*  99:    */  }
/* 100:    */  
/* 110:    */  public Object next()
/* 111:    */  {
/* 112:    */    try
/* 113:    */    {
/* 114:114 */      if (hasNext()) {
/* 115:115 */        Object result = this.contextNode;
/* 116:116 */        this.contextNode = this.navigator.getParentNode(this.contextNode);
/* 117:117 */        return result;
/* 118:    */      }
/* 119:119 */      throw new NoSuchElementException("Exhausted ancestor-or-self axis");
/* 120:    */    }
/* 121:    */    catch (UnsupportedAxisException e)
/* 122:    */    {
/* 123:123 */      throw new JaxenRuntimeException(e);
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 132:    */  public void remove()
/* 133:    */  {
/* 134:134 */    throw new UnsupportedOperationException();
/* 135:    */  }
/* 136:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.AncestorOrSelfAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */