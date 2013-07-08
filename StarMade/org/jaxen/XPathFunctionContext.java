package org.jaxen;

import org.jaxen.function.BooleanFunction;
import org.jaxen.function.CeilingFunction;
import org.jaxen.function.ConcatFunction;
import org.jaxen.function.ContainsFunction;
import org.jaxen.function.CountFunction;
import org.jaxen.function.FalseFunction;
import org.jaxen.function.FloorFunction;
import org.jaxen.function.IdFunction;
import org.jaxen.function.LangFunction;
import org.jaxen.function.LastFunction;
import org.jaxen.function.LocalNameFunction;
import org.jaxen.function.NameFunction;
import org.jaxen.function.NamespaceUriFunction;
import org.jaxen.function.NormalizeSpaceFunction;
import org.jaxen.function.NotFunction;
import org.jaxen.function.NumberFunction;
import org.jaxen.function.PositionFunction;
import org.jaxen.function.RoundFunction;
import org.jaxen.function.StartsWithFunction;
import org.jaxen.function.StringFunction;
import org.jaxen.function.StringLengthFunction;
import org.jaxen.function.SubstringAfterFunction;
import org.jaxen.function.SubstringBeforeFunction;
import org.jaxen.function.SubstringFunction;
import org.jaxen.function.SumFunction;
import org.jaxen.function.TranslateFunction;
import org.jaxen.function.TrueFunction;
import org.jaxen.function.ext.EndsWithFunction;
import org.jaxen.function.ext.EvaluateFunction;
import org.jaxen.function.ext.LowerFunction;
import org.jaxen.function.ext.UpperFunction;
import org.jaxen.function.xslt.DocumentFunction;

public class XPathFunctionContext
  extends SimpleFunctionContext
{
  private static XPathFunctionContext instance = new XPathFunctionContext();
  
  public static FunctionContext getInstance()
  {
    return instance;
  }
  
  public XPathFunctionContext()
  {
    this(true);
  }
  
  public XPathFunctionContext(boolean includeExtensionFunctions)
  {
    registerXPathFunctions();
    if (includeExtensionFunctions)
    {
      registerXSLTFunctions();
      registerExtensionFunctions();
    }
  }
  
  private void registerXPathFunctions()
  {
    registerFunction(null, "boolean", new BooleanFunction());
    registerFunction(null, "ceiling", new CeilingFunction());
    registerFunction(null, "concat", new ConcatFunction());
    registerFunction(null, "contains", new ContainsFunction());
    registerFunction(null, "count", new CountFunction());
    registerFunction(null, "false", new FalseFunction());
    registerFunction(null, "floor", new FloorFunction());
    registerFunction(null, "id", new IdFunction());
    registerFunction(null, "lang", new LangFunction());
    registerFunction(null, "last", new LastFunction());
    registerFunction(null, "local-name", new LocalNameFunction());
    registerFunction(null, "name", new NameFunction());
    registerFunction(null, "namespace-uri", new NamespaceUriFunction());
    registerFunction(null, "normalize-space", new NormalizeSpaceFunction());
    registerFunction(null, "not", new NotFunction());
    registerFunction(null, "number", new NumberFunction());
    registerFunction(null, "position", new PositionFunction());
    registerFunction(null, "round", new RoundFunction());
    registerFunction(null, "starts-with", new StartsWithFunction());
    registerFunction(null, "string", new StringFunction());
    registerFunction(null, "string-length", new StringLengthFunction());
    registerFunction(null, "substring-after", new SubstringAfterFunction());
    registerFunction(null, "substring-before", new SubstringBeforeFunction());
    registerFunction(null, "substring", new SubstringFunction());
    registerFunction(null, "sum", new SumFunction());
    registerFunction(null, "true", new TrueFunction());
    registerFunction(null, "translate", new TranslateFunction());
  }
  
  private void registerXSLTFunctions()
  {
    registerFunction(null, "document", new DocumentFunction());
  }
  
  private void registerExtensionFunctions()
  {
    registerFunction(null, "evaluate", new EvaluateFunction());
    registerFunction(null, "lower-case", new LowerFunction());
    registerFunction(null, "upper-case", new UpperFunction());
    registerFunction(null, "ends-with", new EndsWithFunction());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.XPathFunctionContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */