/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */import org.jaxen.JaxenConstants;
/*   6:    */import org.jaxen.JaxenRuntimeException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.UnsupportedAxisException;
/*   9:    */
/*  75:    */public class FollowingAxisIterator
/*  76:    */  implements Iterator
/*  77:    */{
/*  78:    */  private Object contextNode;
/*  79:    */  private Navigator navigator;
/*  80:    */  private Iterator siblings;
/*  81:    */  private Iterator currentSibling;
/*  82:    */  
/*  83:    */  public FollowingAxisIterator(Object contextNode, Navigator navigator)
/*  84:    */    throws UnsupportedAxisException
/*  85:    */  {
/*  86: 86 */    this.contextNode = contextNode;
/*  87: 87 */    this.navigator = navigator;
/*  88: 88 */    this.siblings = navigator.getFollowingSiblingAxisIterator(contextNode);
/*  89: 89 */    this.currentSibling = JaxenConstants.EMPTY_ITERATOR;
/*  90:    */  }
/*  91:    */  
/*  92:    */  private boolean goForward()
/*  93:    */  {
/*  94: 94 */    while (!this.siblings.hasNext())
/*  95:    */    {
/*  96: 96 */      if (!goUp())
/*  97:    */      {
/*  98: 98 */        return false;
/*  99:    */      }
/* 100:    */    }
/* 101:    */    
/* 102:102 */    Object nextSibling = this.siblings.next();
/* 103:    */    
/* 104:104 */    this.currentSibling = new DescendantOrSelfAxisIterator(nextSibling, this.navigator);
/* 105:    */    
/* 106:106 */    return true;
/* 107:    */  }
/* 108:    */  
/* 109:    */  private boolean goUp()
/* 110:    */  {
/* 111:111 */    if ((this.contextNode == null) || (this.navigator.isDocument(this.contextNode)))
/* 112:    */    {
/* 115:115 */      return false;
/* 116:    */    }
/* 117:    */    
/* 118:    */    try
/* 119:    */    {
/* 120:120 */      this.contextNode = this.navigator.getParentNode(this.contextNode);
/* 121:    */      
/* 122:122 */      if ((this.contextNode != null) && (!this.navigator.isDocument(this.contextNode)))
/* 123:    */      {
/* 126:126 */        this.siblings = this.navigator.getFollowingSiblingAxisIterator(this.contextNode);
/* 127:127 */        return true;
/* 128:    */      }
/* 129:    */      
/* 131:131 */      return false;
/* 133:    */    }
/* 134:    */    catch (UnsupportedAxisException e)
/* 135:    */    {
/* 136:136 */      throw new JaxenRuntimeException(e);
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 148:    */  public boolean hasNext()
/* 149:    */  {
/* 150:150 */    while (!this.currentSibling.hasNext())
/* 151:    */    {
/* 152:152 */      if (!goForward())
/* 153:    */      {
/* 154:154 */        return false;
/* 155:    */      }
/* 156:    */    }
/* 157:    */    
/* 158:158 */    return true;
/* 159:    */  }
/* 160:    */  
/* 169:    */  public Object next()
/* 170:    */    throws NoSuchElementException
/* 171:    */  {
/* 172:172 */    if (!hasNext())
/* 173:    */    {
/* 174:174 */      throw new NoSuchElementException();
/* 175:    */    }
/* 176:    */    
/* 177:177 */    return this.currentSibling.next();
/* 178:    */  }
/* 179:    */  
/* 184:    */  public void remove()
/* 185:    */    throws UnsupportedOperationException
/* 186:    */  {
/* 187:187 */    throw new UnsupportedOperationException();
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.FollowingAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */