/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.HashSet;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.LinkedList;
/*   6:    */import java.util.NoSuchElementException;
/*   7:    */import java.util.Set;
/*   8:    */import org.jaxen.Navigator;
/*   9:    */
/*  64:    *//**
/*  65:    */ * @deprecated
/*  66:    */ */
/*  67:    */public abstract class StackedIterator
/*  68:    */  implements Iterator
/*  69:    */{
/*  70:    */  private LinkedList iteratorStack;
/*  71:    */  private Navigator navigator;
/*  72:    */  private Set created;
/*  73:    */  
/*  74:    */  public StackedIterator(Object contextNode, Navigator navigator)
/*  75:    */  {
/*  76: 76 */    this.iteratorStack = new LinkedList();
/*  77: 77 */    this.created = new HashSet();
/*  78:    */    
/*  79: 79 */    init(contextNode, navigator);
/*  80:    */  }
/*  81:    */  
/*  83:    */  protected StackedIterator()
/*  84:    */  {
/*  85: 85 */    this.iteratorStack = new LinkedList();
/*  86: 86 */    this.created = new HashSet();
/*  87:    */  }
/*  88:    */  
/*  90:    */  protected void init(Object contextNode, Navigator navigator)
/*  91:    */  {
/*  92: 92 */    this.navigator = navigator;
/*  93:    */  }
/*  94:    */  
/*  97:    */  protected Iterator internalCreateIterator(Object contextNode)
/*  98:    */  {
/*  99: 99 */    if (this.created.contains(contextNode))
/* 100:    */    {
/* 101:101 */      return null;
/* 102:    */    }
/* 103:    */    
/* 104:104 */    this.created.add(contextNode);
/* 105:    */    
/* 106:106 */    return createIterator(contextNode);
/* 107:    */  }
/* 108:    */  
/* 109:    */  public boolean hasNext()
/* 110:    */  {
/* 111:111 */    Iterator curIter = currentIterator();
/* 112:    */    
/* 113:113 */    if (curIter == null)
/* 114:    */    {
/* 115:115 */      return false;
/* 116:    */    }
/* 117:    */    
/* 118:118 */    return curIter.hasNext();
/* 119:    */  }
/* 120:    */  
/* 121:    */  public Object next() throws NoSuchElementException
/* 122:    */  {
/* 123:123 */    if (!hasNext())
/* 124:    */    {
/* 125:125 */      throw new NoSuchElementException();
/* 126:    */    }
/* 127:    */    
/* 128:128 */    Iterator curIter = currentIterator();
/* 129:129 */    Object object = curIter.next();
/* 130:    */    
/* 131:131 */    pushIterator(internalCreateIterator(object));
/* 132:    */    
/* 133:133 */    return object;
/* 134:    */  }
/* 135:    */  
/* 136:    */  public void remove() throws UnsupportedOperationException
/* 137:    */  {
/* 138:138 */    throw new UnsupportedOperationException();
/* 139:    */  }
/* 140:    */  
/* 141:    */  protected abstract Iterator createIterator(Object paramObject);
/* 142:    */  
/* 143:    */  protected void pushIterator(Iterator iter)
/* 144:    */  {
/* 145:145 */    if (iter != null)
/* 146:    */    {
/* 147:147 */      this.iteratorStack.addFirst(iter);
/* 148:    */    }
/* 149:    */  }
/* 150:    */  
/* 151:    */  private Iterator currentIterator()
/* 152:    */  {
/* 153:153 */    while (this.iteratorStack.size() > 0)
/* 154:    */    {
/* 155:155 */      Iterator curIter = (Iterator)this.iteratorStack.getFirst();
/* 156:    */      
/* 157:157 */      if (curIter.hasNext())
/* 158:    */      {
/* 159:159 */        return curIter;
/* 160:    */      }
/* 161:    */      
/* 162:162 */      this.iteratorStack.removeFirst();
/* 163:    */    }
/* 164:    */    
/* 165:165 */    return null;
/* 166:    */  }
/* 167:    */  
/* 168:    */  protected Navigator getNavigator()
/* 169:    */  {
/* 170:170 */    return this.navigator;
/* 171:    */  }
/* 172:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.StackedIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */