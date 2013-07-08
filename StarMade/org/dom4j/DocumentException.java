/*  1:   */package org.dom4j;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.io.PrintWriter;
/*  5:   */
/* 20:   */public class DocumentException
/* 21:   */  extends Exception
/* 22:   */{
/* 23:   */  private Throwable nestedException;
/* 24:   */  
/* 25:   */  public DocumentException()
/* 26:   */  {
/* 27:27 */    super("Error occurred in DOM4J application.");
/* 28:   */  }
/* 29:   */  
/* 30:   */  public DocumentException(String message) {
/* 31:31 */    super(message);
/* 32:   */  }
/* 33:   */  
/* 34:   */  public DocumentException(Throwable nestedException) {
/* 35:35 */    super(nestedException.getMessage());
/* 36:36 */    this.nestedException = nestedException;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public DocumentException(String message, Throwable nestedException) {
/* 40:40 */    super(message);
/* 41:41 */    this.nestedException = nestedException;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public Throwable getNestedException() {
/* 45:45 */    return this.nestedException;
/* 46:   */  }
/* 47:   */  
/* 48:   */  public String getMessage() {
/* 49:49 */    if (this.nestedException != null) {
/* 50:50 */      return super.getMessage() + " Nested exception: " + this.nestedException.getMessage();
/* 51:   */    }
/* 52:   */    
/* 53:53 */    return super.getMessage();
/* 54:   */  }
/* 55:   */  
/* 56:   */  public void printStackTrace()
/* 57:   */  {
/* 58:58 */    super.printStackTrace();
/* 59:   */    
/* 60:60 */    if (this.nestedException != null) {
/* 61:61 */      System.err.print("Nested exception: ");
/* 62:62 */      this.nestedException.printStackTrace();
/* 63:   */    }
/* 64:   */  }
/* 65:   */  
/* 66:   */  public void printStackTrace(PrintStream out) {
/* 67:67 */    super.printStackTrace(out);
/* 68:   */    
/* 69:69 */    if (this.nestedException != null) {
/* 70:70 */      out.println("Nested exception: ");
/* 71:71 */      this.nestedException.printStackTrace(out);
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 75:   */  public void printStackTrace(PrintWriter writer) {
/* 76:76 */    super.printStackTrace(writer);
/* 77:   */    
/* 78:78 */    if (this.nestedException != null) {
/* 79:79 */      writer.println("Nested exception: ");
/* 80:80 */      this.nestedException.printStackTrace(writer);
/* 81:   */    }
/* 82:   */  }
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.DocumentException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */