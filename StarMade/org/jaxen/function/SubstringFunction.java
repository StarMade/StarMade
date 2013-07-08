/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.Function;
/*   6:    */import org.jaxen.FunctionCallException;
/*   7:    */import org.jaxen.Navigator;
/*   8:    */
/* 162:    */public class SubstringFunction
/* 163:    */  implements Function
/* 164:    */{
/* 165:    */  public Object call(Context context, List args)
/* 166:    */    throws FunctionCallException
/* 167:    */  {
/* 168:168 */    int argc = args.size();
/* 169:169 */    if ((argc < 2) || (argc > 3)) {
/* 170:170 */      throw new FunctionCallException("substring() requires two or three arguments.");
/* 171:    */    }
/* 172:    */    
/* 173:173 */    Navigator nav = context.getNavigator();
/* 174:    */    
/* 175:175 */    String str = StringFunction.evaluate(args.get(0), nav);
/* 176:    */    
/* 177:177 */    if (str == null) {
/* 178:178 */      return "";
/* 179:    */    }
/* 180:    */    
/* 181:181 */    int stringLength = StringLengthFunction.evaluate(args.get(0), nav).intValue();
/* 182:    */    
/* 183:183 */    if (stringLength == 0) {
/* 184:184 */      return "";
/* 185:    */    }
/* 186:    */    
/* 187:187 */    Double d1 = NumberFunction.evaluate(args.get(1), nav);
/* 188:    */    
/* 189:189 */    if (d1.isNaN()) {
/* 190:190 */      return "";
/* 191:    */    }
/* 192:    */    
/* 193:193 */    int start = RoundFunction.evaluate(d1, nav).intValue() - 1;
/* 194:    */    
/* 195:195 */    int substringLength = stringLength;
/* 196:196 */    if (argc == 3) {
/* 197:197 */      Double d2 = NumberFunction.evaluate(args.get(2), nav);
/* 198:    */      
/* 199:199 */      if (!d2.isNaN()) {
/* 200:200 */        substringLength = RoundFunction.evaluate(d2, nav).intValue();
/* 201:    */      }
/* 202:    */      else {
/* 203:203 */        substringLength = 0;
/* 204:    */      }
/* 205:    */    }
/* 206:    */    
/* 207:207 */    if (substringLength < 0) { return "";
/* 208:    */    }
/* 209:209 */    int end = start + substringLength;
/* 210:210 */    if (argc == 2) { end = stringLength;
/* 211:    */    }
/* 212:    */    
/* 213:213 */    if (start < 0) {
/* 214:214 */      start = 0;
/* 215:    */    }
/* 216:216 */    else if (start > stringLength) {
/* 217:217 */      return "";
/* 218:    */    }
/* 219:    */    
/* 220:220 */    if (end > stringLength) {
/* 221:221 */      end = stringLength;
/* 222:    */    }
/* 223:223 */    else if (end < start) { return "";
/* 224:    */    }
/* 225:225 */    if (stringLength == str.length())
/* 226:    */    {
/* 227:227 */      return str.substring(start, end);
/* 228:    */    }
/* 229:    */    
/* 230:230 */    return unicodeSubstring(str, start, end);
/* 231:    */  }
/* 232:    */  
/* 235:    */  private static String unicodeSubstring(String s, int start, int end)
/* 236:    */  {
/* 237:237 */    StringBuffer result = new StringBuffer(s.length());
/* 238:238 */    int jChar = 0; for (int uChar = 0; uChar < end; uChar++) {
/* 239:239 */      char c = s.charAt(jChar);
/* 240:240 */      if (uChar >= start) result.append(c);
/* 241:241 */      if (c >= 55296)
/* 242:    */      {
/* 244:244 */        jChar++;
/* 245:245 */        if (uChar >= start) result.append(s.charAt(jChar));
/* 246:    */      }
/* 247:238 */      jChar++;
/* 248:    */    }
/* 249:    */    
/* 257:248 */    return result.toString();
/* 258:    */  }
/* 259:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.SubstringFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */