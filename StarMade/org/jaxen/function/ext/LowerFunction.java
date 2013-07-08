/*   1:    */package org.jaxen.function.ext;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import java.util.Locale;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.function.StringFunction;
/*   9:    */
/*  75:    */public class LowerFunction
/*  76:    */  extends LocaleFunctionSupport
/*  77:    */{
/*  78:    */  public Object call(Context context, List args)
/*  79:    */    throws FunctionCallException
/*  80:    */  {
/*  81: 81 */    Navigator navigator = context.getNavigator();
/*  82: 82 */    int size = args.size();
/*  83: 83 */    if (size > 0)
/*  84:    */    {
/*  85: 85 */      Object text = args.get(0);
/*  86: 86 */      Locale locale = null;
/*  87: 87 */      if (size > 1)
/*  88:    */      {
/*  89: 89 */        locale = getLocale(args.get(1), navigator);
/*  90:    */      }
/*  91: 91 */      return evaluate(text, locale, navigator);
/*  92:    */    }
/*  93: 93 */    throw new FunctionCallException("lower-case() requires at least one argument.");
/*  94:    */  }
/*  95:    */  
/* 107:    */  public static String evaluate(Object strArg, Locale locale, Navigator nav)
/* 108:    */  {
/* 109:109 */    String str = StringFunction.evaluate(strArg, nav);
/* 110:    */    
/* 113:113 */    if (locale == null) locale = Locale.ENGLISH;
/* 114:114 */    return str.toLowerCase(locale);
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.LowerFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */