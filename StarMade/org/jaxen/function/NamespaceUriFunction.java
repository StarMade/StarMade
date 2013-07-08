/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/* 104:    */public class NamespaceUriFunction
/* 105:    */  implements Function
/* 106:    */{
/* 107:    */  public Object call(Context context, List args)
/* 108:    */    throws FunctionCallException
/* 109:    */  {
/* 110:110 */    if (args.size() == 0)
/* 111:    */    {
/* 112:112 */      return evaluate(context.getNodeSet(), context.getNavigator());
/* 113:    */    }
/* 114:    */    
/* 116:116 */    if (args.size() == 1)
/* 117:    */    {
/* 118:118 */      return evaluate(args, context.getNavigator());
/* 119:    */    }
/* 120:    */    
/* 122:122 */    throw new FunctionCallException("namespace-uri() requires zero or one argument.");
/* 123:    */  }
/* 124:    */  
/* 135:    */  public static String evaluate(List list, Navigator nav)
/* 136:    */    throws FunctionCallException
/* 137:    */  {
/* 138:138 */    if (!list.isEmpty())
/* 139:    */    {
/* 140:140 */      Object first = list.get(0);
/* 141:    */      
/* 142:142 */      if ((first instanceof List))
/* 143:    */      {
/* 144:144 */        return evaluate((List)first, nav);
/* 145:    */      }
/* 146:    */      
/* 147:147 */      if (nav.isElement(first))
/* 148:    */      {
/* 149:149 */        return nav.getElementNamespaceUri(first);
/* 150:    */      }
/* 151:151 */      if (nav.isAttribute(first))
/* 152:    */      {
/* 153:153 */        String uri = nav.getAttributeNamespaceUri(first);
/* 154:154 */        if (uri == null) return "";
/* 155:155 */        return uri;
/* 156:    */      }
/* 157:157 */      if (nav.isProcessingInstruction(first))
/* 158:    */      {
/* 159:159 */        return "";
/* 160:    */      }
/* 161:161 */      if (nav.isNamespace(first))
/* 162:    */      {
/* 163:163 */        return "";
/* 164:    */      }
/* 165:165 */      if (nav.isDocument(first))
/* 166:    */      {
/* 167:167 */        return "";
/* 168:    */      }
/* 169:169 */      if (nav.isComment(first))
/* 170:    */      {
/* 171:171 */        return "";
/* 172:    */      }
/* 173:173 */      if (nav.isText(first))
/* 174:    */      {
/* 175:175 */        return "";
/* 176:    */      }
/* 177:    */      
/* 178:178 */      throw new FunctionCallException("The argument to the namespace-uri function must be a node-set");
/* 179:    */    }
/* 180:    */    
/* 184:184 */    return "";
/* 185:    */  }
/* 186:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NamespaceUriFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */