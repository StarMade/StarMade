/*   1:    */package org.jaxen.util;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.ListIterator;
/*   6:    */import java.util.NoSuchElementException;
/*   7:    */import org.jaxen.JaxenConstants;
/*   8:    */import org.jaxen.JaxenRuntimeException;
/*   9:    */import org.jaxen.Navigator;
/*  10:    */import org.jaxen.UnsupportedAxisException;
/*  11:    */
/*  97:    */public class PrecedingAxisIterator
/*  98:    */  implements Iterator
/*  99:    */{
/* 100:    */  private Iterator ancestorOrSelf;
/* 101:    */  private Iterator precedingSibling;
/* 102:    */  private ListIterator childrenOrSelf;
/* 103:    */  private ArrayList stack;
/* 104:    */  private Navigator navigator;
/* 105:    */  
/* 106:    */  public PrecedingAxisIterator(Object contextNode, Navigator navigator)
/* 107:    */    throws UnsupportedAxisException
/* 108:    */  {
/* 109:109 */    this.navigator = navigator;
/* 110:110 */    this.ancestorOrSelf = navigator.getAncestorOrSelfAxisIterator(contextNode);
/* 111:111 */    this.precedingSibling = JaxenConstants.EMPTY_ITERATOR;
/* 112:112 */    this.childrenOrSelf = JaxenConstants.EMPTY_LIST_ITERATOR;
/* 113:113 */    this.stack = new ArrayList();
/* 114:    */  }
/* 115:    */  
/* 124:    */  public boolean hasNext()
/* 125:    */  {
/* 126:    */    try
/* 127:    */    {
/* 128:128 */      while (!this.childrenOrSelf.hasPrevious())
/* 129:    */      {
/* 130:130 */        if (this.stack.isEmpty())
/* 131:    */        {
/* 132:132 */          while (!this.precedingSibling.hasNext())
/* 133:    */          {
/* 134:134 */            if (!this.ancestorOrSelf.hasNext())
/* 135:    */            {
/* 136:136 */              return false;
/* 137:    */            }
/* 138:138 */            Object contextNode = this.ancestorOrSelf.next();
/* 139:139 */            this.precedingSibling = new PrecedingSiblingAxisIterator(contextNode, this.navigator);
/* 140:    */          }
/* 141:141 */          Object node = this.precedingSibling.next();
/* 142:142 */          this.childrenOrSelf = childrenOrSelf(node);
/* 143:    */        }
/* 144:    */        else
/* 145:    */        {
/* 146:146 */          this.childrenOrSelf = ((ListIterator)this.stack.remove(this.stack.size() - 1));
/* 147:    */        }
/* 148:    */      }
/* 149:149 */      return true;
/* 150:    */    }
/* 151:    */    catch (UnsupportedAxisException e)
/* 152:    */    {
/* 153:153 */      throw new JaxenRuntimeException(e);
/* 154:    */    }
/* 155:    */  }
/* 156:    */  
/* 157:    */  private ListIterator childrenOrSelf(Object node)
/* 158:    */  {
/* 159:    */    try
/* 160:    */    {
/* 161:161 */      ArrayList reversed = new ArrayList();
/* 162:162 */      reversed.add(node);
/* 163:163 */      Iterator childAxisIterator = this.navigator.getChildAxisIterator(node);
/* 164:164 */      if (childAxisIterator != null)
/* 165:    */      {
/* 166:166 */        while (childAxisIterator.hasNext())
/* 167:    */        {
/* 168:168 */          reversed.add(childAxisIterator.next());
/* 169:    */        }
/* 170:    */      }
/* 171:171 */      return reversed.listIterator(reversed.size());
/* 172:    */    }
/* 173:    */    catch (UnsupportedAxisException e)
/* 174:    */    {
/* 175:175 */      throw new JaxenRuntimeException(e);
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 187:    */  public Object next()
/* 188:    */    throws NoSuchElementException
/* 189:    */  {
/* 190:190 */    if (!hasNext())
/* 191:    */    {
/* 192:192 */      throw new NoSuchElementException();
/* 193:    */    }
/* 194:    */    Object result;
/* 195:    */    for (;;) {
/* 196:196 */      result = this.childrenOrSelf.previous();
/* 197:197 */      if (!this.childrenOrSelf.hasPrevious()) {
/* 198:    */        break;
/* 199:    */      }
/* 200:200 */      this.stack.add(this.childrenOrSelf);
/* 201:201 */      this.childrenOrSelf = childrenOrSelf(result);
/* 202:    */    }
/* 203:    */    
/* 204:204 */    return result;
/* 205:    */  }
/* 206:    */  
/* 213:    */  public void remove()
/* 214:    */    throws UnsupportedOperationException
/* 215:    */  {
/* 216:216 */    throw new UnsupportedOperationException();
/* 217:    */  }
/* 218:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.PrecedingAxisIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */