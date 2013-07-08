/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  85:    */public class ContextSupport
/*  86:    */  implements Serializable
/*  87:    */{
/*  88:    */  private static final long serialVersionUID = 4494082174713652559L;
/*  89:    */  private transient FunctionContext functionContext;
/*  90:    */  private NamespaceContext namespaceContext;
/*  91:    */  private VariableContext variableContext;
/*  92:    */  private Navigator navigator;
/*  93:    */  
/*  94:    */  public ContextSupport() {}
/*  95:    */  
/*  96:    */  public ContextSupport(NamespaceContext namespaceContext, FunctionContext functionContext, VariableContext variableContext, Navigator navigator)
/*  97:    */  {
/*  98: 98 */    setNamespaceContext(namespaceContext);
/*  99: 99 */    setFunctionContext(functionContext);
/* 100:100 */    setVariableContext(variableContext);
/* 101:    */    
/* 102:102 */    this.navigator = navigator;
/* 103:    */  }
/* 104:    */  
/* 113:    */  public void setNamespaceContext(NamespaceContext namespaceContext)
/* 114:    */  {
/* 115:115 */    this.namespaceContext = namespaceContext;
/* 116:    */  }
/* 117:    */  
/* 122:    */  public NamespaceContext getNamespaceContext()
/* 123:    */  {
/* 124:124 */    return this.namespaceContext;
/* 125:    */  }
/* 126:    */  
/* 131:    */  public void setFunctionContext(FunctionContext functionContext)
/* 132:    */  {
/* 133:133 */    this.functionContext = functionContext;
/* 134:    */  }
/* 135:    */  
/* 140:    */  public FunctionContext getFunctionContext()
/* 141:    */  {
/* 142:142 */    return this.functionContext;
/* 143:    */  }
/* 144:    */  
/* 149:    */  public void setVariableContext(VariableContext variableContext)
/* 150:    */  {
/* 151:151 */    this.variableContext = variableContext;
/* 152:    */  }
/* 153:    */  
/* 158:    */  public VariableContext getVariableContext()
/* 159:    */  {
/* 160:160 */    return this.variableContext;
/* 161:    */  }
/* 162:    */  
/* 167:    */  public Navigator getNavigator()
/* 168:    */  {
/* 169:169 */    return this.navigator;
/* 170:    */  }
/* 171:    */  
/* 181:    */  public String translateNamespacePrefixToUri(String prefix)
/* 182:    */  {
/* 183:183 */    if ("xml".equals(prefix)) {
/* 184:184 */      return "http://www.w3.org/XML/1998/namespace";
/* 185:    */    }
/* 186:186 */    NamespaceContext context = getNamespaceContext();
/* 187:    */    
/* 188:188 */    if (context != null)
/* 189:    */    {
/* 190:190 */      return context.translateNamespacePrefixToUri(prefix);
/* 191:    */    }
/* 192:    */    
/* 193:193 */    return null;
/* 194:    */  }
/* 195:    */  
/* 208:    */  public Object getVariableValue(String namespaceURI, String prefix, String localName)
/* 209:    */    throws UnresolvableException
/* 210:    */  {
/* 211:211 */    VariableContext context = getVariableContext();
/* 212:    */    
/* 213:213 */    if (context != null)
/* 214:    */    {
/* 215:215 */      return context.getVariableValue(namespaceURI, prefix, localName);
/* 216:    */    }
/* 217:    */    
/* 219:219 */    throw new UnresolvableException("No variable context installed");
/* 220:    */  }
/* 221:    */  
/* 235:    */  public Function getFunction(String namespaceURI, String prefix, String localName)
/* 236:    */    throws UnresolvableException
/* 237:    */  {
/* 238:238 */    FunctionContext context = getFunctionContext();
/* 239:    */    
/* 240:240 */    if (context != null)
/* 241:    */    {
/* 242:242 */      return context.getFunction(namespaceURI, prefix, localName);
/* 243:    */    }
/* 244:    */    
/* 246:246 */    throw new UnresolvableException("No function context installed");
/* 247:    */  }
/* 248:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.ContextSupport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */