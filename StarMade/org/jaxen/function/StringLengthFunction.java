/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/*  96:    */public class StringLengthFunction
/*  97:    */  implements Function
/*  98:    */{
/*  99:    */  public Object call(Context context, List args)
/* 100:    */    throws FunctionCallException
/* 101:    */  {
/* 102:102 */    if (args.size() == 0)
/* 103:    */    {
/* 104:104 */      return evaluate(context.getNodeSet(), context.getNavigator());
/* 105:    */    }
/* 106:    */    
/* 107:107 */    if (args.size() == 1)
/* 108:    */    {
/* 109:109 */      return evaluate(args.get(0), context.getNavigator());
/* 110:    */    }
/* 111:    */    
/* 113:113 */    throw new FunctionCallException("string-length() requires one argument.");
/* 114:    */  }
/* 115:    */  
/* 128:    */  public static Double evaluate(Object obj, Navigator nav)
/* 129:    */    throws FunctionCallException
/* 130:    */  {
/* 131:131 */    String str = StringFunction.evaluate(obj, nav);
/* 132:    */    
/* 133:133 */    char[] data = str.toCharArray();
/* 134:134 */    int length = 0;
/* 135:135 */    for (int i = 0; i < data.length; i++) {
/* 136:136 */      char c = data[i];
/* 137:137 */      length++;
/* 138:    */      
/* 140:140 */      if ((c >= 55296) && (c <= 57343)) {
/* 141:    */        try {
/* 142:142 */          char low = data[(i + 1)];
/* 143:143 */          if ((low < 56320) || (low > 57343)) {
/* 144:144 */            throw new FunctionCallException("Bad surrogate pair in string " + str);
/* 145:    */          }
/* 146:146 */          i++;
/* 147:    */        }
/* 148:    */        catch (ArrayIndexOutOfBoundsException ex) {
/* 149:149 */          throw new FunctionCallException("Bad surrogate pair in string " + str);
/* 150:    */        }
/* 151:    */      }
/* 152:    */    }
/* 153:153 */    return new Double(length);
/* 154:    */  }
/* 155:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.StringLengthFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */