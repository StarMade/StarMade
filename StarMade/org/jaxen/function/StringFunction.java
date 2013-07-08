/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.text.DecimalFormat;
/*   4:    */import java.text.DecimalFormatSymbols;
/*   5:    */import java.text.NumberFormat;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Locale;
/*   9:    */import org.jaxen.Context;
/*  10:    */import org.jaxen.Function;
/*  11:    */import org.jaxen.FunctionCallException;
/*  12:    */import org.jaxen.JaxenRuntimeException;
/*  13:    */import org.jaxen.Navigator;
/*  14:    */import org.jaxen.UnsupportedAxisException;
/*  15:    */
/* 183:    */public class StringFunction
/* 184:    */  implements Function
/* 185:    */{
/* 186:186 */  private static DecimalFormat format = (DecimalFormat)NumberFormat.getInstance(Locale.ENGLISH);
/* 187:    */  
/* 188:    */  static {
/* 189:189 */    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
/* 190:190 */    symbols.setNaN("NaN");
/* 191:191 */    symbols.setInfinity("Infinity");
/* 192:192 */    format.setGroupingUsed(false);
/* 193:193 */    format.setMaximumFractionDigits(32);
/* 194:194 */    format.setDecimalFormatSymbols(symbols);
/* 195:    */  }
/* 196:    */  
/* 214:    */  public Object call(Context context, List args)
/* 215:    */    throws FunctionCallException
/* 216:    */  {
/* 217:217 */    int size = args.size();
/* 218:    */    
/* 219:219 */    if (size == 0)
/* 220:    */    {
/* 221:221 */      return evaluate(context.getNodeSet(), context.getNavigator());
/* 222:    */    }
/* 223:    */    
/* 224:224 */    if (size == 1)
/* 225:    */    {
/* 226:226 */      return evaluate(args.get(0), context.getNavigator());
/* 227:    */    }
/* 228:    */    
/* 230:230 */    throw new FunctionCallException("string() takes at most argument.");
/* 231:    */  }
/* 232:    */  
/* 249:    */  public static String evaluate(Object obj, Navigator nav)
/* 250:    */  {
/* 251:    */    try
/* 252:    */    {
/* 253:253 */      if ((nav != null) && (nav.isText(obj)))
/* 254:    */      {
/* 255:255 */        return nav.getTextStringValue(obj);
/* 256:    */      }
/* 257:    */      
/* 258:258 */      if ((obj instanceof List))
/* 259:    */      {
/* 260:260 */        List list = (List)obj;
/* 261:261 */        if (list.isEmpty())
/* 262:    */        {
/* 263:263 */          return "";
/* 264:    */        }
/* 265:    */        
/* 266:266 */        obj = list.get(0);
/* 267:    */      }
/* 268:    */      
/* 269:269 */      if (nav != null)
/* 270:    */      {
/* 272:272 */        if (nav.isElement(obj))
/* 273:    */        {
/* 274:274 */          return nav.getElementStringValue(obj);
/* 275:    */        }
/* 276:276 */        if (nav.isAttribute(obj))
/* 277:    */        {
/* 278:278 */          return nav.getAttributeStringValue(obj);
/* 279:    */        }
/* 280:    */        
/* 281:281 */        if (nav.isDocument(obj))
/* 282:    */        {
/* 283:283 */          Iterator childAxisIterator = nav.getChildAxisIterator(obj);
/* 284:284 */          while (childAxisIterator.hasNext())
/* 285:    */          {
/* 286:286 */            Object descendant = childAxisIterator.next();
/* 287:287 */            if (nav.isElement(descendant))
/* 288:    */            {
/* 289:289 */              return nav.getElementStringValue(descendant);
/* 290:    */            }
/* 291:    */          }
/* 292:    */        } else {
/* 293:293 */          if (nav.isProcessingInstruction(obj))
/* 294:    */          {
/* 295:295 */            return nav.getProcessingInstructionData(obj);
/* 296:    */          }
/* 297:297 */          if (nav.isComment(obj))
/* 298:    */          {
/* 299:299 */            return nav.getCommentStringValue(obj);
/* 300:    */          }
/* 301:301 */          if (nav.isText(obj))
/* 302:    */          {
/* 303:303 */            return nav.getTextStringValue(obj);
/* 304:    */          }
/* 305:305 */          if (nav.isNamespace(obj))
/* 306:    */          {
/* 307:307 */            return nav.getNamespaceStringValue(obj);
/* 308:    */          }
/* 309:    */        }
/* 310:    */      }
/* 311:311 */      if ((obj instanceof String))
/* 312:    */      {
/* 313:313 */        return (String)obj;
/* 314:    */      }
/* 315:315 */      if ((obj instanceof Boolean))
/* 316:    */      {
/* 317:317 */        return stringValue(((Boolean)obj).booleanValue());
/* 318:    */      }
/* 319:319 */      if ((obj instanceof Number))
/* 320:    */      {
/* 321:321 */        return stringValue(((Number)obj).doubleValue());
/* 322:    */      }
/* 323:    */      
/* 324:    */    }
/* 325:    */    catch (UnsupportedAxisException e)
/* 326:    */    {
/* 327:327 */      throw new JaxenRuntimeException(e);
/* 328:    */    }
/* 329:    */    
/* 330:330 */    return "";
/* 331:    */  }
/* 332:    */  
/* 337:    */  private static String stringValue(double value)
/* 338:    */  {
/* 339:339 */    if (value == 0.0D) { return "0";
/* 340:    */    }
/* 341:    */    
/* 342:342 */    String result = null;
/* 343:343 */    synchronized (format) {
/* 344:344 */      result = format.format(value);
/* 345:    */    }
/* 346:346 */    return result;
/* 347:    */  }
/* 348:    */  
/* 350:    */  private static String stringValue(boolean value)
/* 351:    */  {
/* 352:352 */    return value ? "true" : "false";
/* 353:    */  }
/* 354:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.StringFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */