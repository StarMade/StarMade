/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.io.PrintWriter;
/*   5:    */
/*  61:    */public class JaxenRuntimeException
/*  62:    */  extends RuntimeException
/*  63:    */{
/*  64:    */  private static final long serialVersionUID = -930309761511911193L;
/*  65:    */  private Throwable cause;
/*  66: 66 */  private boolean causeSet = false;
/*  67:    */  
/*  74:    */  public JaxenRuntimeException(Throwable cause)
/*  75:    */  {
/*  76: 76 */    super(cause.getMessage());
/*  77: 77 */    initCause(cause);
/*  78:    */  }
/*  79:    */  
/*  84:    */  public JaxenRuntimeException(String message)
/*  85:    */  {
/*  86: 86 */    super(message);
/*  87:    */  }
/*  88:    */  
/*  95:    */  public Throwable getCause()
/*  96:    */  {
/*  97: 97 */    return this.cause;
/*  98:    */  }
/*  99:    */  
/* 109:    */  public Throwable initCause(Throwable cause)
/* 110:    */  {
/* 111:111 */    if (this.causeSet) throw new IllegalStateException("Cause cannot be reset");
/* 112:112 */    if (cause == this) throw new IllegalArgumentException("Exception cannot be its own cause");
/* 113:113 */    this.causeSet = true;
/* 114:114 */    this.cause = cause;
/* 115:115 */    return this;
/* 116:    */  }
/* 117:    */  
/* 123:    */  public void printStackTrace(PrintStream s)
/* 124:    */  {
/* 125:125 */    super.printStackTrace(s);
/* 126:126 */    if ((JaxenException.javaVersion < 1.4D) && (getCause() != null)) {
/* 127:127 */      s.print("Caused by: ");
/* 128:128 */      getCause().printStackTrace(s);
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 137:    */  public void printStackTrace(PrintWriter s)
/* 138:    */  {
/* 139:139 */    super.printStackTrace(s);
/* 140:140 */    if ((JaxenException.javaVersion < 1.4D) && (getCause() != null)) {
/* 141:141 */      s.print("Caused by: ");
/* 142:142 */      getCause().printStackTrace(s);
/* 143:    */    }
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.JaxenRuntimeException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */