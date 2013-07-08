/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.Function;
/*   7:    */import org.jaxen.FunctionCallException;
/*   8:    */import org.jaxen.Navigator;
/*   9:    */
/* 127:    */public class NumberFunction
/* 128:    */  implements Function
/* 129:    */{
/* 130:130 */  private static final Double NaN = new Double((0.0D / 0.0D));
/* 131:    */  
/* 151:    */  public Object call(Context context, List args)
/* 152:    */    throws FunctionCallException
/* 153:    */  {
/* 154:154 */    if (args.size() == 1)
/* 155:    */    {
/* 156:156 */      return evaluate(args.get(0), context.getNavigator());
/* 157:    */    }
/* 158:158 */    if (args.size() == 0)
/* 159:    */    {
/* 160:160 */      return evaluate(context.getNodeSet(), context.getNavigator());
/* 161:    */    }
/* 162:    */    
/* 163:163 */    throw new FunctionCallException("number() takes at most one argument.");
/* 164:    */  }
/* 165:    */  
/* 175:    */  public static Double evaluate(Object obj, Navigator nav)
/* 176:    */  {
/* 177:177 */    if ((obj instanceof Double))
/* 178:    */    {
/* 179:179 */      return (Double)obj;
/* 180:    */    }
/* 181:181 */    if ((obj instanceof String))
/* 182:    */    {
/* 183:183 */      String str = (String)obj;
/* 184:    */      try
/* 185:    */      {
/* 186:186 */        return new Double(str);
/* 188:    */      }
/* 189:    */      catch (NumberFormatException e)
/* 190:    */      {
/* 191:191 */        return NaN;
/* 192:    */      }
/* 193:    */    }
/* 194:194 */    if (((obj instanceof List)) || ((obj instanceof Iterator)))
/* 195:    */    {
/* 196:196 */      return evaluate(StringFunction.evaluate(obj, nav), nav);
/* 197:    */    }
/* 198:198 */    if ((nav.isElement(obj)) || (nav.isAttribute(obj)) || (nav.isText(obj)) || (nav.isComment(obj)) || (nav.isProcessingInstruction(obj)) || (nav.isDocument(obj)) || (nav.isNamespace(obj)))
/* 199:    */    {
/* 202:202 */      return evaluate(StringFunction.evaluate(obj, nav), nav);
/* 203:    */    }
/* 204:204 */    if ((obj instanceof Boolean))
/* 205:    */    {
/* 206:206 */      if (obj == Boolean.TRUE)
/* 207:    */      {
/* 208:208 */        return new Double(1.0D);
/* 209:    */      }
/* 210:    */      
/* 212:212 */      return new Double(0.0D);
/* 213:    */    }
/* 214:    */    
/* 215:215 */    return NaN;
/* 216:    */  }
/* 217:    */  
/* 225:    */  public static boolean isNaN(double val)
/* 226:    */  {
/* 227:227 */    return Double.isNaN(val);
/* 228:    */  }
/* 229:    */  
/* 237:    */  public static boolean isNaN(Double val)
/* 238:    */  {
/* 239:239 */    return val.equals(NaN);
/* 240:    */  }
/* 241:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NumberFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */