/*     */ package org.jaxen;
/*     */ 
/*     */ import org.jaxen.function.BooleanFunction;
/*     */ import org.jaxen.function.CeilingFunction;
/*     */ import org.jaxen.function.ConcatFunction;
/*     */ import org.jaxen.function.ContainsFunction;
/*     */ import org.jaxen.function.CountFunction;
/*     */ import org.jaxen.function.FalseFunction;
/*     */ import org.jaxen.function.FloorFunction;
/*     */ import org.jaxen.function.IdFunction;
/*     */ import org.jaxen.function.LangFunction;
/*     */ import org.jaxen.function.LastFunction;
/*     */ import org.jaxen.function.LocalNameFunction;
/*     */ import org.jaxen.function.NameFunction;
/*     */ import org.jaxen.function.NamespaceUriFunction;
/*     */ import org.jaxen.function.NormalizeSpaceFunction;
/*     */ import org.jaxen.function.NotFunction;
/*     */ import org.jaxen.function.NumberFunction;
/*     */ import org.jaxen.function.PositionFunction;
/*     */ import org.jaxen.function.RoundFunction;
/*     */ import org.jaxen.function.StartsWithFunction;
/*     */ import org.jaxen.function.StringFunction;
/*     */ import org.jaxen.function.StringLengthFunction;
/*     */ import org.jaxen.function.SubstringAfterFunction;
/*     */ import org.jaxen.function.SubstringBeforeFunction;
/*     */ import org.jaxen.function.SubstringFunction;
/*     */ import org.jaxen.function.SumFunction;
/*     */ import org.jaxen.function.TranslateFunction;
/*     */ import org.jaxen.function.TrueFunction;
/*     */ import org.jaxen.function.ext.EndsWithFunction;
/*     */ import org.jaxen.function.ext.EvaluateFunction;
/*     */ import org.jaxen.function.ext.LowerFunction;
/*     */ import org.jaxen.function.ext.UpperFunction;
/*     */ import org.jaxen.function.xslt.DocumentFunction;
/*     */ 
/*     */ public class XPathFunctionContext extends SimpleFunctionContext
/*     */ {
/* 121 */   private static XPathFunctionContext instance = new XPathFunctionContext();
/*     */ 
/*     */   public static FunctionContext getInstance()
/*     */   {
/* 129 */     return instance;
/*     */   }
/*     */ 
/*     */   public XPathFunctionContext()
/*     */   {
/* 137 */     this(true);
/*     */   }
/*     */ 
/*     */   public XPathFunctionContext(boolean includeExtensionFunctions)
/*     */   {
/* 148 */     registerXPathFunctions();
/* 149 */     if (includeExtensionFunctions) {
/* 150 */       registerXSLTFunctions();
/* 151 */       registerExtensionFunctions();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void registerXPathFunctions()
/*     */   {
/* 157 */     registerFunction(null, "boolean", new BooleanFunction());
/*     */ 
/* 161 */     registerFunction(null, "ceiling", new CeilingFunction());
/*     */ 
/* 165 */     registerFunction(null, "concat", new ConcatFunction());
/*     */ 
/* 169 */     registerFunction(null, "contains", new ContainsFunction());
/*     */ 
/* 173 */     registerFunction(null, "count", new CountFunction());
/*     */ 
/* 177 */     registerFunction(null, "false", new FalseFunction());
/*     */ 
/* 181 */     registerFunction(null, "floor", new FloorFunction());
/*     */ 
/* 185 */     registerFunction(null, "id", new IdFunction());
/*     */ 
/* 189 */     registerFunction(null, "lang", new LangFunction());
/*     */ 
/* 193 */     registerFunction(null, "last", new LastFunction());
/*     */ 
/* 197 */     registerFunction(null, "local-name", new LocalNameFunction());
/*     */ 
/* 201 */     registerFunction(null, "name", new NameFunction());
/*     */ 
/* 205 */     registerFunction(null, "namespace-uri", new NamespaceUriFunction());
/*     */ 
/* 209 */     registerFunction(null, "normalize-space", new NormalizeSpaceFunction());
/*     */ 
/* 213 */     registerFunction(null, "not", new NotFunction());
/*     */ 
/* 217 */     registerFunction(null, "number", new NumberFunction());
/*     */ 
/* 221 */     registerFunction(null, "position", new PositionFunction());
/*     */ 
/* 225 */     registerFunction(null, "round", new RoundFunction());
/*     */ 
/* 229 */     registerFunction(null, "starts-with", new StartsWithFunction());
/*     */ 
/* 233 */     registerFunction(null, "string", new StringFunction());
/*     */ 
/* 237 */     registerFunction(null, "string-length", new StringLengthFunction());
/*     */ 
/* 241 */     registerFunction(null, "substring-after", new SubstringAfterFunction());
/*     */ 
/* 245 */     registerFunction(null, "substring-before", new SubstringBeforeFunction());
/*     */ 
/* 249 */     registerFunction(null, "substring", new SubstringFunction());
/*     */ 
/* 253 */     registerFunction(null, "sum", new SumFunction());
/*     */ 
/* 257 */     registerFunction(null, "true", new TrueFunction());
/*     */ 
/* 261 */     registerFunction(null, "translate", new TranslateFunction());
/*     */   }
/*     */ 
/*     */   private void registerXSLTFunctions()
/*     */   {
/* 269 */     registerFunction(null, "document", new DocumentFunction());
/*     */   }
/*     */ 
/*     */   private void registerExtensionFunctions()
/*     */   {
/* 278 */     registerFunction(null, "evaluate", new EvaluateFunction());
/*     */ 
/* 282 */     registerFunction(null, "lower-case", new LowerFunction());
/*     */ 
/* 286 */     registerFunction(null, "upper-case", new UpperFunction());
/*     */ 
/* 290 */     registerFunction(null, "ends-with", new EndsWithFunction());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.XPathFunctionContext
 * JD-Core Version:    0.6.2
 */