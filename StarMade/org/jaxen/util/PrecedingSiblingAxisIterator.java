/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.LinkedList;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */import org.jaxen.JaxenConstants;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.UnsupportedAxisException;
/*   9:    */
/*  77:    */public class PrecedingSiblingAxisIterator
/*  78:    */  implements Iterator
/*  79:    */{
/*  80:    */  private Object contextNode;
/*  81:    */  private Navigator navigator;
/*  82:    */  private Iterator siblingIter;
/*  83:    */  private Object nextObj;
/*  84:    */  
/*  85:    */  public PrecedingSiblingAxisIterator(Object contextNode, Navigator navigator)
/*  86:    */    throws UnsupportedAxisException
/*  87:    */  {
/*  88: 88 */    this.contextNode = contextNode;
/*  89: 89 */    this.navigator = navigator;
/*  90:    */    
/*  91: 91 */    init();
/*  92: 92 */    if (this.siblingIter.hasNext())
/*  93:    */    {
/*  94: 94 */      this.nextObj = this.siblingIter.next();
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  98:    */  private void init()
/*  99:    */    throws UnsupportedAxisException
/* 100:    */  {
/* 101:101 */    Object parent = this.navigator.getParentNode(this.contextNode);
/* 102:    */    
/* 103:103 */    if (parent != null)
/* 104:    */    {
/* 105:105 */      Iterator childIter = this.navigator.getChildAxisIterator(parent);
/* 106:106 */      LinkedList siblings = new LinkedList();
/* 107:    */      
/* 108:108 */      while (childIter.hasNext())
/* 109:    */      {
/* 110:110 */        Object eachChild = childIter.next();
/* 111:111 */        if (eachChild.equals(this.contextNode)) {
/* 112:    */          break;
/* 113:    */        }
/* 114:    */        
/* 115:115 */        siblings.addFirst(eachChild);
/* 116:    */      }
/* 117:    */      
/* 118:118 */      this.siblingIter = siblings.iterator();
/* 119:    */    }
/* 120:    */    else
/* 121:    */    {
/* 122:122 */      this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 134:    */  public boolean hasNext()
/* 135:    */  {
/* 136:136 */    return this.nextObj != null;
/* 137:    */  }
/* 138:    */  
/* 147:    */  public Object next()
/* 148:    */    throws NoSuchElementException
/* 149:    */  {
/* 150:150 */    if (!hasNext())
/* 151:    */    {
/* 152:152 */      throw new NoSuchElementException();
/* 153:    */    }
/* 154:    */    
/* 155:155 */    Object obj = this.nextObj;
/* 156:156 */    if (this.siblingIter.hasNext())
/* 157:    */    {
/* 158:158 */      this.nextObj = this.siblingIter.next();
/* 159:    */    }
/* 160:    */    else {
/* 161:161 */      this.nextObj = null;
/* 162:    */    }
/* 163:163 */    return obj;
/* 164:    */  }
/* 165:    */  
/* 170:    */  public void remove()
/* 171:    */    throws UnsupportedOperationException
/* 172:    */  {
/* 173:173 */    throw new UnsupportedOperationException();
/* 174:    */  }
/* 175:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.PrecedingSiblingAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */