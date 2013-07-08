/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/* 101:    */public class NormalizeSpaceFunction
/* 102:    */  implements Function
/* 103:    */{
/* 104:    */  public Object call(Context context, List args)
/* 105:    */    throws FunctionCallException
/* 106:    */  {
/* 107:107 */    if (args.size() == 0) {
/* 108:108 */      return evaluate(context.getNodeSet(), context.getNavigator());
/* 109:    */    }
/* 110:    */    
/* 111:111 */    if (args.size() == 1)
/* 112:    */    {
/* 113:113 */      return evaluate(args.get(0), context.getNavigator());
/* 114:    */    }
/* 115:    */    
/* 117:117 */    throw new FunctionCallException("normalize-space() cannot have more than one argument");
/* 118:    */  }
/* 119:    */  
/* 134:    */  public static String evaluate(Object strArg, Navigator nav)
/* 135:    */  {
/* 136:136 */    String str = StringFunction.evaluate(strArg, nav);
/* 137:    */    
/* 139:139 */    char[] buffer = str.toCharArray();
/* 140:140 */    int write = 0;
/* 141:141 */    int lastWrite = 0;
/* 142:142 */    boolean wroteOne = false;
/* 143:143 */    int read = 0;
/* 144:144 */    for (;;) { if (read >= buffer.length)
/* 145:    */        break label101;
/* 146:146 */      if (isXMLSpace(buffer[read]))
/* 147:    */      {
/* 148:148 */        if (wroteOne)
/* 149:    */        {
/* 150:150 */          buffer[(write++)] = ' ';
/* 151:    */        }
/* 152:    */        
/* 154:154 */        read++;
/* 155:    */        
/* 156:156 */        if (read >= buffer.length) continue; if (isXMLSpace(buffer[read])) break; continue;
/* 157:    */      }
/* 158:    */      
/* 160:160 */      buffer[(write++)] = buffer[(read++)];
/* 161:161 */      wroteOne = true;
/* 162:162 */      lastWrite = write;
/* 163:    */    }
/* 164:    */    
/* 165:    */    label101:
/* 166:166 */    return new String(buffer, 0, lastWrite);
/* 167:    */  }
/* 168:    */  
/* 169:    */  private static boolean isXMLSpace(char c)
/* 170:    */  {
/* 171:171 */    return (c == ' ') || (c == '\n') || (c == '\r') || (c == '\t');
/* 172:    */  }
/* 173:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.NormalizeSpaceFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */