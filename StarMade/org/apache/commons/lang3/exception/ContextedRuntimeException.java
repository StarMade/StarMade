/*   1:    */package org.apache.commons.lang3.exception;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import java.util.Set;
/*   5:    */import org.apache.commons.lang3.tuple.Pair;
/*   6:    */
/*  89:    */public class ContextedRuntimeException
/*  90:    */  extends RuntimeException
/*  91:    */  implements ExceptionContext
/*  92:    */{
/*  93:    */  private static final long serialVersionUID = 20110706L;
/*  94:    */  private final ExceptionContext exceptionContext;
/*  95:    */  
/*  96:    */  public ContextedRuntimeException()
/*  97:    */  {
/*  98: 98 */    this.exceptionContext = new DefaultExceptionContext();
/*  99:    */  }
/* 100:    */  
/* 107:    */  public ContextedRuntimeException(String message)
/* 108:    */  {
/* 109:109 */    super(message);
/* 110:110 */    this.exceptionContext = new DefaultExceptionContext();
/* 111:    */  }
/* 112:    */  
/* 119:    */  public ContextedRuntimeException(Throwable cause)
/* 120:    */  {
/* 121:121 */    super(cause);
/* 122:122 */    this.exceptionContext = new DefaultExceptionContext();
/* 123:    */  }
/* 124:    */  
/* 132:    */  public ContextedRuntimeException(String message, Throwable cause)
/* 133:    */  {
/* 134:134 */    super(message, cause);
/* 135:135 */    this.exceptionContext = new DefaultExceptionContext();
/* 136:    */  }
/* 137:    */  
/* 144:    */  public ContextedRuntimeException(String message, Throwable cause, ExceptionContext context)
/* 145:    */  {
/* 146:146 */    super(message, cause);
/* 147:147 */    if (context == null) {
/* 148:148 */      context = new DefaultExceptionContext();
/* 149:    */    }
/* 150:150 */    this.exceptionContext = context;
/* 151:    */  }
/* 152:    */  
/* 166:    */  public ContextedRuntimeException addContextValue(String label, Object value)
/* 167:    */  {
/* 168:168 */    this.exceptionContext.addContextValue(label, value);
/* 169:169 */    return this;
/* 170:    */  }
/* 171:    */  
/* 184:    */  public ContextedRuntimeException setContextValue(String label, Object value)
/* 185:    */  {
/* 186:186 */    this.exceptionContext.setContextValue(label, value);
/* 187:187 */    return this;
/* 188:    */  }
/* 189:    */  
/* 192:    */  public List<Object> getContextValues(String label)
/* 193:    */  {
/* 194:194 */    return this.exceptionContext.getContextValues(label);
/* 195:    */  }
/* 196:    */  
/* 199:    */  public Object getFirstContextValue(String label)
/* 200:    */  {
/* 201:201 */    return this.exceptionContext.getFirstContextValue(label);
/* 202:    */  }
/* 203:    */  
/* 206:    */  public List<Pair<String, Object>> getContextEntries()
/* 207:    */  {
/* 208:208 */    return this.exceptionContext.getContextEntries();
/* 209:    */  }
/* 210:    */  
/* 213:    */  public Set<String> getContextLabels()
/* 214:    */  {
/* 215:215 */    return this.exceptionContext.getContextLabels();
/* 216:    */  }
/* 217:    */  
/* 224:    */  public String getMessage()
/* 225:    */  {
/* 226:226 */    return getFormattedExceptionMessage(super.getMessage());
/* 227:    */  }
/* 228:    */  
/* 235:    */  public String getRawMessage()
/* 236:    */  {
/* 237:237 */    return super.getMessage();
/* 238:    */  }
/* 239:    */  
/* 242:    */  public String getFormattedExceptionMessage(String baseMessage)
/* 243:    */  {
/* 244:244 */    return this.exceptionContext.getFormattedExceptionMessage(baseMessage);
/* 245:    */  }
/* 246:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.ContextedRuntimeException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */