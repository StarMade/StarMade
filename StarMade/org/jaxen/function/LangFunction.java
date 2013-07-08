/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.Function;
/*   7:    */import org.jaxen.FunctionCallException;
/*   8:    */import org.jaxen.Navigator;
/*   9:    */import org.jaxen.UnsupportedAxisException;
/*  10:    */
/* 121:    */public class LangFunction
/* 122:    */  implements Function
/* 123:    */{
/* 124:    */  private static final String LANG_LOCALNAME = "lang";
/* 125:    */  private static final String XMLNS_URI = "http://www.w3.org/XML/1998/namespace";
/* 126:    */  
/* 127:    */  public Object call(Context context, List args)
/* 128:    */    throws FunctionCallException
/* 129:    */  {
/* 130:130 */    if (args.size() != 1) {
/* 131:131 */      throw new FunctionCallException("lang() requires exactly one argument.");
/* 132:    */    }
/* 133:    */    
/* 134:134 */    Object arg = args.get(0);
/* 135:    */    try
/* 136:    */    {
/* 137:137 */      return evaluate(context.getNodeSet(), arg, context.getNavigator());
/* 138:    */    }
/* 139:    */    catch (UnsupportedAxisException e) {
/* 140:140 */      throw new FunctionCallException("Can't evaluate lang()", e);
/* 141:    */    }
/* 142:    */  }
/* 143:    */  
/* 146:    */  private static Boolean evaluate(List contextNodes, Object lang, Navigator nav)
/* 147:    */    throws UnsupportedAxisException
/* 148:    */  {
/* 149:149 */    return evaluate(contextNodes.get(0), StringFunction.evaluate(lang, nav), nav) ? Boolean.TRUE : Boolean.FALSE;
/* 150:    */  }
/* 151:    */  
/* 155:    */  private static boolean evaluate(Object node, String lang, Navigator nav)
/* 156:    */    throws UnsupportedAxisException
/* 157:    */  {
/* 158:158 */    Object element = node;
/* 159:159 */    if (!nav.isElement(element)) {
/* 160:160 */      element = nav.getParentNode(node);
/* 161:    */    }
/* 162:162 */    while ((element != null) && (nav.isElement(element)))
/* 163:    */    {
/* 164:164 */      Iterator attrs = nav.getAttributeAxisIterator(element);
/* 165:165 */      while (attrs.hasNext())
/* 166:    */      {
/* 167:167 */        Object attr = attrs.next();
/* 168:168 */        if (("lang".equals(nav.getAttributeName(attr))) && ("http://www.w3.org/XML/1998/namespace".equals(nav.getAttributeNamespaceUri(attr))))
/* 169:    */        {
/* 171:171 */          return isSublang(nav.getAttributeStringValue(attr), lang);
/* 172:    */        }
/* 173:    */      }
/* 174:    */      
/* 175:175 */      element = nav.getParentNode(element);
/* 176:    */    }
/* 177:177 */    return false;
/* 178:    */  }
/* 179:    */  
/* 180:    */  private static boolean isSublang(String sublang, String lang)
/* 181:    */  {
/* 182:182 */    if (sublang.equalsIgnoreCase(lang))
/* 183:    */    {
/* 184:184 */      return true;
/* 185:    */    }
/* 186:186 */    int ll = lang.length();
/* 187:187 */    return (sublang.length() > ll) && (sublang.charAt(ll) == '-') && (sublang.substring(0, ll).equalsIgnoreCase(lang));
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.LangFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */