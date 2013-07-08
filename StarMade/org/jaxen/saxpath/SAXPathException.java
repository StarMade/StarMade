/*   1:    */package org.jaxen.saxpath;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.io.PrintWriter;
/*   5:    */
/*  62:    */public class SAXPathException
/*  63:    */  extends Exception
/*  64:    */{
/*  65:    */  private static final long serialVersionUID = 4826444568928720706L;
/*  66: 66 */  private static double javaVersion = 1.4D;
/*  67:    */  private Throwable cause;
/*  68:    */  
/*  69:    */  static {
/*  70: 70 */    try { String versionString = System.getProperty("java.version");
/*  71: 71 */      versionString = versionString.substring(0, 3);
/*  72: 72 */      javaVersion = Double.valueOf(versionString).doubleValue();
/*  73:    */    }
/*  74:    */    catch (Exception ex) {}
/*  75:    */  }
/*  76:    */  
/*  84:    */  public SAXPathException(String message)
/*  85:    */  {
/*  86: 86 */    super(message);
/*  87:    */  }
/*  88:    */  
/*  93:    */  public SAXPathException(Throwable cause)
/*  94:    */  {
/*  95: 95 */    super(cause.getMessage());
/*  96: 96 */    initCause(cause);
/*  97:    */  }
/*  98:    */  
/* 105:    */  public SAXPathException(String message, Throwable cause)
/* 106:    */  {
/* 107:107 */    super(message);
/* 108:108 */    initCause(cause);
/* 109:    */  }
/* 110:    */  
/* 113:113 */  private boolean causeSet = false;
/* 114:    */  
/* 121:    */  public Throwable getCause()
/* 122:    */  {
/* 123:123 */    return this.cause;
/* 124:    */  }
/* 125:    */  
/* 135:    */  public Throwable initCause(Throwable cause)
/* 136:    */  {
/* 137:137 */    if (this.causeSet) throw new IllegalStateException("Cause cannot be reset");
/* 138:138 */    if (cause == this) throw new IllegalArgumentException("Exception cannot be its own cause");
/* 139:139 */    this.causeSet = true;
/* 140:140 */    this.cause = cause;
/* 141:141 */    return this;
/* 142:    */  }
/* 143:    */  
/* 149:    */  public void printStackTrace(PrintStream s)
/* 150:    */  {
/* 151:151 */    super.printStackTrace(s);
/* 152:152 */    if ((javaVersion < 1.4D) && (getCause() != null)) {
/* 153:153 */      s.print("Caused by: ");
/* 154:154 */      getCause().printStackTrace(s);
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 163:    */  public void printStackTrace(PrintWriter s)
/* 164:    */  {
/* 165:165 */    super.printStackTrace(s);
/* 166:166 */    if ((javaVersion < 1.4D) && (getCause() != null)) {
/* 167:167 */      s.print("Caused by: ");
/* 168:168 */      getCause().printStackTrace(s);
/* 169:    */    }
/* 170:    */  }
/* 171:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.SAXPathException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */