/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import org.jaxen.saxpath.SAXPathException;
/*   4:    */
/*  62:    */public class JaxenException
/*  63:    */  extends SAXPathException
/*  64:    */{
/*  65:    */  private static final long serialVersionUID = 7132891439526672639L;
/*  66: 66 */  static double javaVersion = 1.4D;
/*  67:    */  
/*  68:    */  static {
/*  69:    */    try {
/*  70: 70 */      String versionString = System.getProperty("java.version");
/*  71: 71 */      versionString = versionString.substring(0, 3);
/*  72: 72 */      javaVersion = Double.valueOf(versionString).doubleValue();
/*  73:    */    }
/*  74:    */    catch (RuntimeException ex) {}
/*  75:    */  }
/*  76:    */  
/*  85:    */  public JaxenException(String message)
/*  86:    */  {
/*  87: 87 */    super(message);
/*  88:    */  }
/*  89:    */  
/*  95:    */  public JaxenException(Throwable rootCause)
/*  96:    */  {
/*  97: 97 */    super(rootCause);
/*  98:    */  }
/*  99:    */  
/* 106:    */  public JaxenException(String message, Throwable nestedException)
/* 107:    */  {
/* 108:108 */    super(message, nestedException);
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.JaxenException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */