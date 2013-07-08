/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */
/*  70:    */public class SimpleFunctionContext
/*  71:    */  implements FunctionContext
/*  72:    */{
/*  73:    */  private HashMap functions;
/*  74:    */  
/*  75:    */  public SimpleFunctionContext()
/*  76:    */  {
/*  77: 77 */    this.functions = new HashMap();
/*  78:    */  }
/*  79:    */  
/* 110:    */  public void registerFunction(String namespaceURI, String localName, Function function)
/* 111:    */  {
/* 112:112 */    this.functions.put(new QualifiedName(namespaceURI, localName), function);
/* 113:    */  }
/* 114:    */  
/* 118:    */  public Function getFunction(String namespaceURI, String prefix, String localName)
/* 119:    */    throws UnresolvableException
/* 120:    */  {
/* 121:121 */    QualifiedName key = new QualifiedName(namespaceURI, localName);
/* 122:    */    
/* 123:123 */    if (this.functions.containsKey(key)) {
/* 124:124 */      return (Function)this.functions.get(key);
/* 125:    */    }
/* 126:    */    
/* 127:127 */    throw new UnresolvableException("No Such Function " + key.getClarkForm());
/* 128:    */  }
/* 129:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.SimpleFunctionContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */