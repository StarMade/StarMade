/*   1:    */package org.jaxen.function;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.List;
/*   5:    */import java.util.Map;
/*   6:    */import org.jaxen.Context;
/*   7:    */import org.jaxen.Function;
/*   8:    */import org.jaxen.FunctionCallException;
/*   9:    */import org.jaxen.Navigator;
/*  10:    */
/* 121:    */public class TranslateFunction
/* 122:    */  implements Function
/* 123:    */{
/* 124:    */  public Object call(Context context, List args)
/* 125:    */    throws FunctionCallException
/* 126:    */  {
/* 127:127 */    if (args.size() == 3) {
/* 128:128 */      return evaluate(args.get(0), args.get(1), args.get(2), context.getNavigator());
/* 129:    */    }
/* 130:    */    
/* 134:134 */    throw new FunctionCallException("translate() requires three arguments.");
/* 135:    */  }
/* 136:    */  
/* 159:    */  public static String evaluate(Object strArg, Object fromArg, Object toArg, Navigator nav)
/* 160:    */    throws FunctionCallException
/* 161:    */  {
/* 162:162 */    String inStr = StringFunction.evaluate(strArg, nav);
/* 163:163 */    String fromStr = StringFunction.evaluate(fromArg, nav);
/* 164:164 */    String toStr = StringFunction.evaluate(toArg, nav);
/* 165:    */    
/* 167:167 */    Map characterMap = new HashMap();
/* 168:168 */    String[] fromCharacters = toUnicodeCharacters(fromStr);
/* 169:169 */    String[] toCharacters = toUnicodeCharacters(toStr);
/* 170:170 */    int fromLen = fromCharacters.length;
/* 171:171 */    int toLen = toCharacters.length;
/* 172:172 */    for (int i = 0; i < fromLen; i++) {
/* 173:173 */      String cFrom = fromCharacters[i];
/* 174:174 */      if (!characterMap.containsKey(cFrom))
/* 175:    */      {
/* 179:179 */        if (i < toLen)
/* 180:    */        {
/* 181:181 */          characterMap.put(cFrom, toCharacters[i]);
/* 182:    */        }
/* 183:    */        else
/* 184:    */        {
/* 185:185 */          characterMap.put(cFrom, null);
/* 186:    */        }
/* 187:    */      }
/* 188:    */    }
/* 189:    */    
/* 190:190 */    StringBuffer outStr = new StringBuffer(inStr.length());
/* 191:191 */    String[] inCharacters = toUnicodeCharacters(inStr);
/* 192:192 */    int inLen = inCharacters.length;
/* 193:193 */    for (int i = 0; i < inLen; i++) {
/* 194:194 */      String cIn = inCharacters[i];
/* 195:195 */      if (characterMap.containsKey(cIn)) {
/* 196:196 */        String cTo = (String)characterMap.get(cIn);
/* 197:197 */        if (cTo != null) {
/* 198:198 */          outStr.append(cTo);
/* 199:    */        }
/* 200:    */      }
/* 201:    */      else {
/* 202:202 */        outStr.append(cIn);
/* 203:    */      }
/* 204:    */    }
/* 205:    */    
/* 206:206 */    return outStr.toString();
/* 207:    */  }
/* 208:    */  
/* 209:    */  private static String[] toUnicodeCharacters(String s) throws FunctionCallException
/* 210:    */  {
/* 211:211 */    String[] result = new String[s.length()];
/* 212:212 */    int stringLength = 0;
/* 213:213 */    for (int i = 0; i < s.length(); i++) {
/* 214:214 */      char c1 = s.charAt(i);
/* 215:215 */      if (isHighSurrogate(c1)) {
/* 216:    */        try {
/* 217:217 */          char c2 = s.charAt(i + 1);
/* 218:218 */          if (isLowSurrogate(c2)) {
/* 219:219 */            result[stringLength] = (c1 + "" + c2).intern();
/* 220:220 */            i++;
/* 221:    */          }
/* 222:    */          else {
/* 223:223 */            throw new FunctionCallException("Mismatched surrogate pair in translate function");
/* 224:    */          }
/* 225:    */        }
/* 226:    */        catch (StringIndexOutOfBoundsException ex) {
/* 227:227 */          throw new FunctionCallException("High surrogate without low surrogate at end of string passed to translate function");
/* 228:    */        }
/* 229:    */        
/* 230:    */      } else {
/* 231:231 */        result[stringLength] = String.valueOf(c1).intern();
/* 232:    */      }
/* 233:233 */      stringLength++;
/* 234:    */    }
/* 235:    */    
/* 236:236 */    if (stringLength == result.length) { return result;
/* 237:    */    }
/* 238:    */    
/* 239:239 */    String[] trimmed = new String[stringLength];
/* 240:240 */    System.arraycopy(result, 0, trimmed, 0, stringLength);
/* 241:241 */    return trimmed;
/* 242:    */  }
/* 243:    */  
/* 244:    */  private static boolean isHighSurrogate(char c)
/* 245:    */  {
/* 246:246 */    return (c >= 55296) && (c <= 56319);
/* 247:    */  }
/* 248:    */  
/* 249:    */  private static boolean isLowSurrogate(char c) {
/* 250:250 */    return (c >= 56320) && (c <= 57343);
/* 251:    */  }
/* 252:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.function.TranslateFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */