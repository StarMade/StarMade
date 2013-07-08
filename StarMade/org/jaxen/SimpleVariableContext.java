/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Map;
/*   6:    */
/*  74:    */public class SimpleVariableContext
/*  75:    */  implements VariableContext, Serializable
/*  76:    */{
/*  77:    */  private static final long serialVersionUID = 961322093794516518L;
/*  78:    */  private Map variables;
/*  79:    */  
/*  80:    */  public SimpleVariableContext()
/*  81:    */  {
/*  82: 82 */    this.variables = new HashMap();
/*  83:    */  }
/*  84:    */  
/* 104:    */  public void setVariableValue(String namespaceURI, String localName, Object value)
/* 105:    */  {
/* 106:106 */    this.variables.put(new QualifiedName(namespaceURI, localName), value);
/* 107:    */  }
/* 108:    */  
/* 123:    */  public void setVariableValue(String localName, Object value)
/* 124:    */  {
/* 125:125 */    this.variables.put(new QualifiedName(null, localName), value);
/* 126:    */  }
/* 127:    */  
/* 130:    */  public Object getVariableValue(String namespaceURI, String prefix, String localName)
/* 131:    */    throws UnresolvableException
/* 132:    */  {
/* 133:133 */    QualifiedName key = new QualifiedName(namespaceURI, localName);
/* 134:    */    
/* 135:135 */    if (this.variables.containsKey(key))
/* 136:    */    {
/* 137:137 */      return this.variables.get(key);
/* 138:    */    }
/* 139:    */    
/* 141:141 */    throw new UnresolvableException("Variable " + key.getClarkForm());
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.SimpleVariableContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */