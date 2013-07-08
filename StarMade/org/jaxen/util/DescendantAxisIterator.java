/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */import org.jaxen.JaxenRuntimeException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.UnsupportedAxisException;
/*   9:    */
/*  67:    */public class DescendantAxisIterator
/*  68:    */  implements Iterator
/*  69:    */{
/*  70: 70 */  private ArrayList stack = new ArrayList();
/*  71:    */  
/*  73:    */  private Iterator children;
/*  74:    */  
/*  76:    */  private Navigator navigator;
/*  77:    */  
/*  80:    */  public DescendantAxisIterator(Object contextNode, Navigator navigator)
/*  81:    */    throws UnsupportedAxisException
/*  82:    */  {
/*  83: 83 */    this(navigator, navigator.getChildAxisIterator(contextNode));
/*  84:    */  }
/*  85:    */  
/*  87:    */  public DescendantAxisIterator(Navigator navigator, Iterator iterator)
/*  88:    */  {
/*  89: 89 */    this.navigator = navigator;
/*  90: 90 */    this.children = iterator;
/*  91:    */  }
/*  92:    */  
/*  99:    */  public boolean hasNext()
/* 100:    */  {
/* 101:101 */    while (!this.children.hasNext())
/* 102:    */    {
/* 103:103 */      if (this.stack.isEmpty())
/* 104:    */      {
/* 105:105 */        return false;
/* 106:    */      }
/* 107:107 */      this.children = ((Iterator)this.stack.remove(this.stack.size() - 1));
/* 108:    */    }
/* 109:109 */    return true;
/* 110:    */  }
/* 111:    */  
/* 121:    */  public Object next()
/* 122:    */  {
/* 123:    */    try
/* 124:    */    {
/* 125:125 */      if (hasNext())
/* 126:    */      {
/* 127:127 */        Object node = this.children.next();
/* 128:128 */        this.stack.add(this.children);
/* 129:129 */        this.children = this.navigator.getChildAxisIterator(node);
/* 130:130 */        return node;
/* 131:    */      }
/* 132:132 */      throw new NoSuchElementException();
/* 133:    */    }
/* 134:    */    catch (UnsupportedAxisException e)
/* 135:    */    {
/* 136:136 */      throw new JaxenRuntimeException(e);
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 145:    */  public void remove()
/* 146:    */  {
/* 147:147 */    throw new UnsupportedOperationException();
/* 148:    */  }
/* 149:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.DescendantAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */