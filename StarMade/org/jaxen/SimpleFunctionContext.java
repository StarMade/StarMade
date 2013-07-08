package org.jaxen;

import java.util.HashMap;

public class SimpleFunctionContext
  implements FunctionContext
{
  private HashMap functions = new HashMap();
  
  public void registerFunction(String namespaceURI, String localName, Function function)
  {
    this.functions.put(new QualifiedName(namespaceURI, localName), function);
  }
  
  public Function getFunction(String namespaceURI, String prefix, String localName)
    throws UnresolvableException
  {
    QualifiedName key = new QualifiedName(namespaceURI, localName);
    if (this.functions.containsKey(key)) {
      return (Function)this.functions.get(key);
    }
    throw new UnresolvableException("No Such Function " + key.getClarkForm());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.SimpleFunctionContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */