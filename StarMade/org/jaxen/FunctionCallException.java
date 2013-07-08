/*   1:    */package org.jaxen;
/*   2:    */
/*  35:    */public class FunctionCallException
/*  36:    */  extends JaxenException
/*  37:    */{
/*  38:    */  private static final long serialVersionUID = 7908649612495640943L;
/*  39:    */  
/*  72:    */  public FunctionCallException(String message)
/*  73:    */  {
/*  74: 74 */    super(message);
/*  75:    */  }
/*  76:    */  
/*  81:    */  public FunctionCallException(Throwable nestedException)
/*  82:    */  {
/*  83: 83 */    super(nestedException);
/*  84:    */  }
/*  85:    */  
/*  92:    */  public FunctionCallException(String message, Exception nestedException)
/*  93:    */  {
/*  94: 94 */    super(message, nestedException);
/*  95:    */  }
/*  96:    */  
/* 103:    */  /**
/* 104:    */   * @deprecated
/* 105:    */   */
/* 106:    */  public Throwable getNestedException()
/* 107:    */  {
/* 108:108 */    return getCause();
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.FunctionCallException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */