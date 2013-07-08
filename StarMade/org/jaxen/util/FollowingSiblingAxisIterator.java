/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */import org.jaxen.JaxenConstants;
/*   6:    */import org.jaxen.Navigator;
/*   7:    */import org.jaxen.UnsupportedAxisException;
/*   8:    */
/*  75:    */public class FollowingSiblingAxisIterator
/*  76:    */  implements Iterator
/*  77:    */{
/*  78:    */  private Object contextNode;
/*  79:    */  private Navigator navigator;
/*  80:    */  private Iterator siblingIter;
/*  81:    */  
/*  82:    */  public FollowingSiblingAxisIterator(Object contextNode, Navigator navigator)
/*  83:    */    throws UnsupportedAxisException
/*  84:    */  {
/*  85: 85 */    this.contextNode = contextNode;
/*  86: 86 */    this.navigator = navigator;
/*  87: 87 */    init();
/*  88:    */  }
/*  89:    */  
/*  90:    */  private void init() throws UnsupportedAxisException
/*  91:    */  {
/*  92: 92 */    Object parent = this.navigator.getParentNode(this.contextNode);
/*  93:    */    
/*  94: 94 */    if (parent != null)
/*  95:    */    {
/*  96: 96 */      this.siblingIter = this.navigator.getChildAxisIterator(parent);
/*  97:    */      
/*  98: 98 */      while (this.siblingIter.hasNext())
/*  99:    */      {
/* 100:100 */        Object eachChild = this.siblingIter.next();
/* 101:101 */        if (eachChild.equals(this.contextNode))
/* 102:    */          break;
/* 103:    */      }
/* 104:    */    }
/* 105:105 */    this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
/* 106:    */  }
/* 107:    */  
/* 117:    */  public boolean hasNext()
/* 118:    */  {
/* 119:119 */    return this.siblingIter.hasNext();
/* 120:    */  }
/* 121:    */  
/* 130:    */  public Object next()
/* 131:    */    throws NoSuchElementException
/* 132:    */  {
/* 133:133 */    return this.siblingIter.next();
/* 134:    */  }
/* 135:    */  
/* 140:    */  public void remove()
/* 141:    */    throws UnsupportedOperationException
/* 142:    */  {
/* 143:143 */    throw new UnsupportedOperationException();
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.FollowingSiblingAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */