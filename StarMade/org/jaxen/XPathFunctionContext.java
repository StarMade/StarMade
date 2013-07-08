/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import org.jaxen.function.BooleanFunction;
/*   4:    */import org.jaxen.function.CeilingFunction;
/*   5:    */import org.jaxen.function.ConcatFunction;
/*   6:    */import org.jaxen.function.ContainsFunction;
/*   7:    */import org.jaxen.function.CountFunction;
/*   8:    */import org.jaxen.function.FalseFunction;
/*   9:    */import org.jaxen.function.FloorFunction;
/*  10:    */import org.jaxen.function.IdFunction;
/*  11:    */import org.jaxen.function.LangFunction;
/*  12:    */import org.jaxen.function.LastFunction;
/*  13:    */import org.jaxen.function.LocalNameFunction;
/*  14:    */import org.jaxen.function.NameFunction;
/*  15:    */import org.jaxen.function.NamespaceUriFunction;
/*  16:    */import org.jaxen.function.NormalizeSpaceFunction;
/*  17:    */import org.jaxen.function.NotFunction;
/*  18:    */import org.jaxen.function.NumberFunction;
/*  19:    */import org.jaxen.function.PositionFunction;
/*  20:    */import org.jaxen.function.RoundFunction;
/*  21:    */import org.jaxen.function.StartsWithFunction;
/*  22:    */import org.jaxen.function.StringFunction;
/*  23:    */import org.jaxen.function.StringLengthFunction;
/*  24:    */import org.jaxen.function.SubstringAfterFunction;
/*  25:    */import org.jaxen.function.SubstringBeforeFunction;
/*  26:    */import org.jaxen.function.SubstringFunction;
/*  27:    */import org.jaxen.function.SumFunction;
/*  28:    */import org.jaxen.function.TranslateFunction;
/*  29:    */import org.jaxen.function.TrueFunction;
/*  30:    */import org.jaxen.function.ext.EndsWithFunction;
/*  31:    */import org.jaxen.function.ext.EvaluateFunction;
/*  32:    */import org.jaxen.function.ext.LowerFunction;
/*  33:    */import org.jaxen.function.ext.UpperFunction;
/*  34:    */import org.jaxen.function.xslt.DocumentFunction;
/*  35:    */
/* 118:    */public class XPathFunctionContext
/* 119:    */  extends SimpleFunctionContext
/* 120:    */{
/* 121:121 */  private static XPathFunctionContext instance = new XPathFunctionContext();
/* 122:    */  
/* 127:    */  public static FunctionContext getInstance()
/* 128:    */  {
/* 129:129 */    return instance;
/* 130:    */  }
/* 131:    */  
/* 135:    */  public XPathFunctionContext()
/* 136:    */  {
/* 137:137 */    this(true);
/* 138:    */  }
/* 139:    */  
/* 146:    */  public XPathFunctionContext(boolean includeExtensionFunctions)
/* 147:    */  {
/* 148:148 */    registerXPathFunctions();
/* 149:149 */    if (includeExtensionFunctions) {
/* 150:150 */      registerXSLTFunctions();
/* 151:151 */      registerExtensionFunctions();
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 155:    */  private void registerXPathFunctions()
/* 156:    */  {
/* 157:157 */    registerFunction(null, "boolean", new BooleanFunction());
/* 158:    */    
/* 161:161 */    registerFunction(null, "ceiling", new CeilingFunction());
/* 162:    */    
/* 165:165 */    registerFunction(null, "concat", new ConcatFunction());
/* 166:    */    
/* 169:169 */    registerFunction(null, "contains", new ContainsFunction());
/* 170:    */    
/* 173:173 */    registerFunction(null, "count", new CountFunction());
/* 174:    */    
/* 177:177 */    registerFunction(null, "false", new FalseFunction());
/* 178:    */    
/* 181:181 */    registerFunction(null, "floor", new FloorFunction());
/* 182:    */    
/* 185:185 */    registerFunction(null, "id", new IdFunction());
/* 186:    */    
/* 189:189 */    registerFunction(null, "lang", new LangFunction());
/* 190:    */    
/* 193:193 */    registerFunction(null, "last", new LastFunction());
/* 194:    */    
/* 197:197 */    registerFunction(null, "local-name", new LocalNameFunction());
/* 198:    */    
/* 201:201 */    registerFunction(null, "name", new NameFunction());
/* 202:    */    
/* 205:205 */    registerFunction(null, "namespace-uri", new NamespaceUriFunction());
/* 206:    */    
/* 209:209 */    registerFunction(null, "normalize-space", new NormalizeSpaceFunction());
/* 210:    */    
/* 213:213 */    registerFunction(null, "not", new NotFunction());
/* 214:    */    
/* 217:217 */    registerFunction(null, "number", new NumberFunction());
/* 218:    */    
/* 221:221 */    registerFunction(null, "position", new PositionFunction());
/* 222:    */    
/* 225:225 */    registerFunction(null, "round", new RoundFunction());
/* 226:    */    
/* 229:229 */    registerFunction(null, "starts-with", new StartsWithFunction());
/* 230:    */    
/* 233:233 */    registerFunction(null, "string", new StringFunction());
/* 234:    */    
/* 237:237 */    registerFunction(null, "string-length", new StringLengthFunction());
/* 238:    */    
/* 241:241 */    registerFunction(null, "substring-after", new SubstringAfterFunction());
/* 242:    */    
/* 245:245 */    registerFunction(null, "substring-before", new SubstringBeforeFunction());
/* 246:    */    
/* 249:249 */    registerFunction(null, "substring", new SubstringFunction());
/* 250:    */    
/* 253:253 */    registerFunction(null, "sum", new SumFunction());
/* 254:    */    
/* 257:257 */    registerFunction(null, "true", new TrueFunction());
/* 258:    */    
/* 261:261 */    registerFunction(null, "translate", new TranslateFunction());
/* 262:    */  }
/* 263:    */  
/* 267:    */  private void registerXSLTFunctions()
/* 268:    */  {
/* 269:269 */    registerFunction(null, "document", new DocumentFunction());
/* 270:    */  }
/* 271:    */  
/* 276:    */  private void registerExtensionFunctions()
/* 277:    */  {
/* 278:278 */    registerFunction(null, "evaluate", new EvaluateFunction());
/* 279:    */    
/* 282:282 */    registerFunction(null, "lower-case", new LowerFunction());
/* 283:    */    
/* 286:286 */    registerFunction(null, "upper-case", new UpperFunction());
/* 287:    */    
/* 290:290 */    registerFunction(null, "ends-with", new EndsWithFunction());
/* 291:    */  }
/* 292:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.XPathFunctionContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */