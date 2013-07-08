/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/* 120:    */public class BooleanFunction
/* 121:    */  implements Function
/* 122:    */{
/* 123:    */  public Object call(Context context, List args)
/* 124:    */    throws FunctionCallException
/* 125:    */  {
/* 126:126 */    if (args.size() == 1)
/* 127:    */    {
/* 128:128 */      return evaluate(args.get(0), context.getNavigator());
/* 129:    */    }
/* 130:    */    
/* 131:131 */    throw new FunctionCallException("boolean() requires one argument");
/* 132:    */  }
/* 133:    */  
/* 151:    */  public static Boolean evaluate(Object obj, Navigator nav)
/* 152:    */  {
/* 153:153 */    if ((obj instanceof List))
/* 154:    */    {
/* 155:155 */      List list = (List)obj;
/* 156:    */      
/* 158:158 */      if (list.size() == 0)
/* 159:    */      {
/* 160:160 */        return Boolean.FALSE;
/* 161:    */      }
/* 162:    */      
/* 164:164 */      obj = list.get(0);
/* 165:    */    }
/* 166:    */    
/* 171:171 */    if ((obj instanceof Boolean))
/* 172:    */    {
/* 173:173 */      return (Boolean)obj;
/* 174:    */    }
/* 175:    */    
/* 176:176 */    if ((obj instanceof Number))
/* 177:    */    {
/* 178:178 */      double d = ((Number)obj).doubleValue();
/* 179:179 */      if ((d == 0.0D) || (Double.isNaN(d)))
/* 180:    */      {
/* 181:181 */        return Boolean.FALSE;
/* 182:    */      }
/* 183:183 */      return Boolean.TRUE;
/* 184:    */    }
/* 185:    */    
/* 186:186 */    if ((obj instanceof String))
/* 187:    */    {
/* 188:188 */      return ((String)obj).length() > 0 ? Boolean.TRUE : Boolean.FALSE;
/* 189:    */    }
/* 190:    */    
/* 196:196 */    return obj != null ? Boolean.TRUE : Boolean.FALSE;
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */