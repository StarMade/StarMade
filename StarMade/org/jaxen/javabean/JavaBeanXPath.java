/*   1:    */package org.jaxen.javabean;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import org.jaxen.BaseXPath;
/*   8:    */import org.jaxen.Context;
/*   9:    */import org.jaxen.JaxenException;
/*  10:    */
/*  86:    */public class JavaBeanXPath
/*  87:    */  extends BaseXPath
/*  88:    */{
/*  89:    */  private static final long serialVersionUID = -1567521943360266313L;
/*  90:    */  
/*  91:    */  public JavaBeanXPath(String xpathExpr)
/*  92:    */    throws JaxenException
/*  93:    */  {
/*  94: 94 */    super(xpathExpr, DocumentNavigator.getInstance());
/*  95:    */  }
/*  96:    */  
/*  97:    */  protected Context getContext(Object node)
/*  98:    */  {
/*  99: 99 */    if ((node instanceof Context))
/* 100:    */    {
/* 101:101 */      return (Context)node;
/* 102:    */    }
/* 103:    */    
/* 104:104 */    if ((node instanceof Element))
/* 105:    */    {
/* 106:106 */      return super.getContext(node);
/* 107:    */    }
/* 108:    */    
/* 109:109 */    if ((node instanceof List))
/* 110:    */    {
/* 111:111 */      List newList = new ArrayList();
/* 112:    */      
/* 113:113 */      Iterator listIter = ((List)node).iterator();
/* 114:114 */      while (listIter.hasNext())
/* 115:    */      {
/* 116:116 */        newList.add(new Element(null, "root", listIter.next()));
/* 117:    */      }
/* 118:    */      
/* 119:119 */      return super.getContext(newList);
/* 120:    */    }
/* 121:    */    
/* 122:122 */    return super.getContext(new Element(null, "root", node));
/* 123:    */  }
/* 124:    */  
/* 125:    */  public Object evaluate(Object node)
/* 126:    */    throws JaxenException
/* 127:    */  {
/* 128:128 */    Object result = super.evaluate(node);
/* 129:    */    
/* 130:130 */    if ((result instanceof Element))
/* 131:    */    {
/* 132:132 */      return ((Element)result).getObject();
/* 133:    */    }
/* 134:134 */    if ((result instanceof Collection))
/* 135:    */    {
/* 136:136 */      List newList = new ArrayList();
/* 137:    */      
/* 138:138 */      Iterator listIter = ((Collection)result).iterator();
/* 139:139 */      while (listIter.hasNext())
/* 140:    */      {
/* 141:141 */        Object member = listIter.next();
/* 142:    */        
/* 143:143 */        if ((member instanceof Element))
/* 144:    */        {
/* 145:145 */          newList.add(((Element)member).getObject());
/* 146:    */        }
/* 147:    */        else
/* 148:    */        {
/* 149:149 */          newList.add(member);
/* 150:    */        }
/* 151:    */      }
/* 152:    */      
/* 153:153 */      return newList;
/* 154:    */    }
/* 155:    */    
/* 156:156 */    return result;
/* 157:    */  }
/* 158:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.JavaBeanXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */