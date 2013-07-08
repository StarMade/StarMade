/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  95:    */public class NameFunction
/*  96:    */  implements Function
/*  97:    */{
/*  98:    */  public Object call(Context context, List args)
/*  99:    */    throws FunctionCallException
/* 100:    */  {
/* 101:101 */    if (args.size() == 0)
/* 102:    */    {
/* 103:103 */      return evaluate(context.getNodeSet(), context.getNavigator());
/* 104:    */    }
/* 105:    */    
/* 107:107 */    if (args.size() == 1)
/* 108:    */    {
/* 109:109 */      return evaluate(args, context.getNavigator());
/* 110:    */    }
/* 111:    */    
/* 113:113 */    throw new FunctionCallException("name() requires zero or one argument.");
/* 114:    */  }
/* 115:    */  
/* 126:    */  public static String evaluate(List list, Navigator nav)
/* 127:    */    throws FunctionCallException
/* 128:    */  {
/* 129:129 */    if (!list.isEmpty())
/* 130:    */    {
/* 131:131 */      Object first = list.get(0);
/* 132:    */      
/* 133:133 */      if ((first instanceof List))
/* 134:    */      {
/* 135:135 */        return evaluate((List)first, nav);
/* 136:    */      }
/* 137:    */      
/* 138:138 */      if (nav.isElement(first))
/* 139:    */      {
/* 140:140 */        return nav.getElementQName(first);
/* 141:    */      }
/* 142:142 */      if (nav.isAttribute(first))
/* 143:    */      {
/* 144:144 */        return nav.getAttributeQName(first);
/* 145:    */      }
/* 146:146 */      if (nav.isProcessingInstruction(first))
/* 147:    */      {
/* 148:148 */        return nav.getProcessingInstructionTarget(first);
/* 149:    */      }
/* 150:150 */      if (nav.isNamespace(first))
/* 151:    */      {
/* 152:152 */        return nav.getNamespacePrefix(first);
/* 153:    */      }
/* 154:154 */      if (nav.isDocument(first))
/* 155:    */      {
/* 156:156 */        return "";
/* 157:    */      }
/* 158:158 */      if (nav.isComment(first))
/* 159:    */      {
/* 160:160 */        return "";
/* 161:    */      }
/* 162:162 */      if (nav.isText(first))
/* 163:    */      {
/* 164:164 */        return "";
/* 165:    */      }
/* 166:    */      
/* 167:167 */      throw new FunctionCallException("The argument to the name function must be a node-set");
/* 168:    */    }
/* 169:    */    
/* 171:171 */    return "";
/* 172:    */  }
/* 173:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NameFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */