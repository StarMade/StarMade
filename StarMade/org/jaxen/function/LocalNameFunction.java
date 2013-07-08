/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  90:    */public class LocalNameFunction
/*  91:    */  implements Function
/*  92:    */{
/*  93:    */  public Object call(Context context, List args)
/*  94:    */    throws FunctionCallException
/*  95:    */  {
/*  96: 96 */    if (args.size() == 0)
/*  97:    */    {
/*  98: 98 */      return evaluate(context.getNodeSet(), context.getNavigator());
/*  99:    */    }
/* 100:    */    
/* 102:102 */    if (args.size() == 1)
/* 103:    */    {
/* 104:104 */      return evaluate(args, context.getNavigator());
/* 105:    */    }
/* 106:    */    
/* 108:108 */    throw new FunctionCallException("local-name() requires zero or one argument.");
/* 109:    */  }
/* 110:    */  
/* 121:    */  public static String evaluate(List list, Navigator nav)
/* 122:    */    throws FunctionCallException
/* 123:    */  {
/* 124:124 */    if (!list.isEmpty())
/* 125:    */    {
/* 126:126 */      Object first = list.get(0);
/* 127:    */      
/* 128:128 */      if ((first instanceof List))
/* 129:    */      {
/* 130:130 */        return evaluate((List)first, nav);
/* 131:    */      }
/* 132:    */      
/* 133:133 */      if (nav.isElement(first))
/* 134:    */      {
/* 135:135 */        return nav.getElementName(first);
/* 136:    */      }
/* 137:137 */      if (nav.isAttribute(first))
/* 138:    */      {
/* 139:139 */        return nav.getAttributeName(first);
/* 140:    */      }
/* 141:141 */      if (nav.isProcessingInstruction(first))
/* 142:    */      {
/* 143:143 */        return nav.getProcessingInstructionTarget(first);
/* 144:    */      }
/* 145:145 */      if (nav.isNamespace(first))
/* 146:    */      {
/* 147:147 */        return nav.getNamespacePrefix(first);
/* 148:    */      }
/* 149:149 */      if (nav.isDocument(first))
/* 150:    */      {
/* 151:151 */        return "";
/* 152:    */      }
/* 153:153 */      if (nav.isComment(first))
/* 154:    */      {
/* 155:155 */        return "";
/* 156:    */      }
/* 157:157 */      if (nav.isText(first))
/* 158:    */      {
/* 159:159 */        return "";
/* 160:    */      }
/* 161:    */      
/* 162:162 */      throw new FunctionCallException("The argument to the local-name function must be a node-set");
/* 163:    */    }
/* 164:    */    
/* 166:166 */    return "";
/* 167:    */  }
/* 168:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.LocalNameFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */