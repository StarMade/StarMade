/*   1:    */package org.jaxen.function.ext;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import java.util.Locale;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */import org.jaxen.function.StringFunction;
/*   9:    */
/*  74:    */public class UpperFunction
/*  75:    */  extends LocaleFunctionSupport
/*  76:    */{
/*  77:    */  public Object call(Context context, List args)
/*  78:    */    throws FunctionCallException
/*  79:    */  {
/*  80: 80 */    Navigator navigator = context.getNavigator();
/*  81: 81 */    int size = args.size();
/*  82: 82 */    if (size > 0)
/*  83:    */    {
/*  84: 84 */      Object text = args.get(0);
/*  85: 85 */      Locale locale = null;
/*  86: 86 */      if (size > 1)
/*  87:    */      {
/*  88: 88 */        locale = getLocale(args.get(1), navigator);
/*  89:    */      }
/*  90: 90 */      return evaluate(text, locale, navigator);
/*  91:    */    }
/*  92: 92 */    throw new FunctionCallException("upper-case() requires at least one argument.");
/*  93:    */  }
/*  94:    */  
/* 106:    */  public static String evaluate(Object strArg, Locale locale, Navigator nav)
/* 107:    */  {
/* 108:108 */    String str = StringFunction.evaluate(strArg, nav);
/* 109:    */    
/* 112:112 */    if (locale == null) locale = Locale.ENGLISH;
/* 113:113 */    return str.toUpperCase(locale);
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.ext.UpperFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */