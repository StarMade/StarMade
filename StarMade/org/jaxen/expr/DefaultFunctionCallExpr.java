/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import org.jaxen.Context;
/*   7:    */import org.jaxen.Function;
/*   8:    */import org.jaxen.JaxenException;
/*   9:    */
/*  61:    *//**
/*  62:    */ * @deprecated
/*  63:    */ */
/*  64:    */public class DefaultFunctionCallExpr
/*  65:    */  extends DefaultExpr
/*  66:    */  implements FunctionCallExpr
/*  67:    */{
/*  68:    */  private static final long serialVersionUID = -4747789292572193708L;
/*  69:    */  private String prefix;
/*  70:    */  private String functionName;
/*  71:    */  private List parameters;
/*  72:    */  
/*  73:    */  public DefaultFunctionCallExpr(String prefix, String functionName)
/*  74:    */  {
/*  75: 75 */    this.prefix = prefix;
/*  76: 76 */    this.functionName = functionName;
/*  77: 77 */    this.parameters = new ArrayList();
/*  78:    */  }
/*  79:    */  
/*  80:    */  public void addParameter(Expr parameter)
/*  81:    */  {
/*  82: 82 */    this.parameters.add(parameter);
/*  83:    */  }
/*  84:    */  
/*  86:    */  public List getParameters()
/*  87:    */  {
/*  88: 88 */    return this.parameters;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String getPrefix()
/*  92:    */  {
/*  93: 93 */    return this.prefix;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public String getFunctionName()
/*  97:    */  {
/*  98: 98 */    return this.functionName;
/*  99:    */  }
/* 100:    */  
/* 102:    */  public String getText()
/* 103:    */  {
/* 104:104 */    StringBuffer buf = new StringBuffer();
/* 105:105 */    String prefix = getPrefix();
/* 106:    */    
/* 107:107 */    if ((prefix != null) && (prefix.length() > 0))
/* 108:    */    {
/* 110:110 */      buf.append(prefix);
/* 111:111 */      buf.append(":");
/* 112:    */    }
/* 113:    */    
/* 114:114 */    buf.append(getFunctionName());
/* 115:115 */    buf.append("(");
/* 116:    */    
/* 117:117 */    Iterator paramIter = getParameters().iterator();
/* 118:    */    
/* 119:119 */    while (paramIter.hasNext()) {
/* 120:120 */      Expr eachParam = (Expr)paramIter.next();
/* 121:    */      
/* 122:122 */      buf.append(eachParam.getText());
/* 123:    */      
/* 124:124 */      if (paramIter.hasNext())
/* 125:    */      {
/* 126:126 */        buf.append(", ");
/* 127:    */      }
/* 128:    */    }
/* 129:    */    
/* 130:130 */    buf.append(")");
/* 131:    */    
/* 132:132 */    return buf.toString();
/* 133:    */  }
/* 134:    */  
/* 135:    */  public Expr simplify()
/* 136:    */  {
/* 137:137 */    List paramExprs = getParameters();
/* 138:138 */    int paramSize = paramExprs.size();
/* 139:    */    
/* 140:140 */    List newParams = new ArrayList(paramSize);
/* 141:    */    
/* 142:142 */    for (int i = 0; i < paramSize; i++)
/* 143:    */    {
/* 144:144 */      Expr eachParam = (Expr)paramExprs.get(i);
/* 145:    */      
/* 146:146 */      newParams.add(eachParam.simplify());
/* 147:    */    }
/* 148:    */    
/* 149:149 */    this.parameters = newParams;
/* 150:    */    
/* 151:151 */    return this;
/* 152:    */  }
/* 153:    */  
/* 155:    */  public String toString()
/* 156:    */  {
/* 157:157 */    String prefix = getPrefix();
/* 158:    */    
/* 159:159 */    if (prefix == null)
/* 160:    */    {
/* 161:161 */      return "[(DefaultFunctionCallExpr): " + getFunctionName() + "(" + getParameters() + ") ]";
/* 162:    */    }
/* 163:    */    
/* 164:164 */    return "[(DefaultFunctionCallExpr): " + getPrefix() + ":" + getFunctionName() + "(" + getParameters() + ") ]";
/* 165:    */  }
/* 166:    */  
/* 167:    */  public Object evaluate(Context context) throws JaxenException
/* 168:    */  {
/* 169:169 */    String prefix = getPrefix();
/* 170:170 */    String namespaceURI = null;
/* 171:    */    
/* 172:172 */    if ((prefix != null) && (!"".equals(prefix))) {
/* 173:173 */      namespaceURI = context.translateNamespacePrefixToUri(prefix);
/* 174:    */    }
/* 175:    */    
/* 176:176 */    Function func = context.getFunction(namespaceURI, prefix, getFunctionName());
/* 177:    */    
/* 179:179 */    List paramValues = evaluateParams(context);
/* 180:    */    
/* 181:181 */    return func.call(context, paramValues);
/* 182:    */  }
/* 183:    */  
/* 184:    */  public List evaluateParams(Context context) throws JaxenException
/* 185:    */  {
/* 186:186 */    List paramExprs = getParameters();
/* 187:187 */    int paramSize = paramExprs.size();
/* 188:    */    
/* 189:189 */    List paramValues = new ArrayList(paramSize);
/* 190:    */    
/* 191:191 */    for (int i = 0; i < paramSize; i++)
/* 192:    */    {
/* 193:193 */      Expr eachParam = (Expr)paramExprs.get(i);
/* 194:    */      
/* 195:195 */      Object eachValue = eachParam.evaluate(context);
/* 196:    */      
/* 197:197 */      paramValues.add(eachValue);
/* 198:    */    }
/* 199:199 */    return paramValues;
/* 200:    */  }
/* 201:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultFunctionCallExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */