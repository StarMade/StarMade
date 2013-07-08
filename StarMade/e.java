/*  1:   */import java.io.ByteArrayOutputStream;
/*  2:   */import java.io.PrintStream;
/*  3:   */import java.util.logging.Handler;
/*  4:   */import java.util.logging.Level;
/*  5:   */import java.util.logging.Logger;
/*  6:   */
/* 21:   */final class e
/* 22:   */  extends ByteArrayOutputStream
/* 23:   */{
/* 24:   */  private String jdField_a_of_type_JavaLangString;
/* 25:   */  private Logger jdField_a_of_type_JavaUtilLoggingLogger;
/* 26:   */  private Level jdField_a_of_type_JavaUtilLoggingLevel;
/* 27:   */  private PrintStream jdField_a_of_type_JavaIoPrintStream;
/* 28:   */  private String b;
/* 29:   */  
/* 30:   */  public e(PrintStream paramPrintStream, Logger paramLogger, Level paramLevel, String paramString)
/* 31:   */  {
/* 32:32 */    this.b = paramString;
/* 33:33 */    this.jdField_a_of_type_JavaUtilLoggingLogger = paramLogger;
/* 34:34 */    this.jdField_a_of_type_JavaUtilLoggingLevel = paramLevel;
/* 35:35 */    this.jdField_a_of_type_JavaLangString = System.getProperty("line.separator");
/* 36:36 */    this.jdField_a_of_type_JavaIoPrintStream = paramPrintStream;
/* 37:   */  }
/* 38:   */  
/* 43:   */  public final void close()
/* 44:   */  {
/* 45:45 */    super.close();
/* 46:46 */    Handler[] arrayOfHandler; int i = (arrayOfHandler = this.jdField_a_of_type_JavaUtilLoggingLogger.getHandlers()).length; for (int j = 0; j < i; j++) {
/* 47:47 */      arrayOfHandler[j].close();
/* 48:   */    }
/* 49:   */  }
/* 50:   */  
/* 59:   */  public final void flush()
/* 60:   */  {
/* 61:61 */    synchronized (this) {
/* 62:62 */      super.flush();
/* 63:63 */      String str = toString();
/* 64:64 */      super.reset();
/* 65:   */      
/* 66:66 */      if ((str.length() == 0) || (str.equals(this.jdField_a_of_type_JavaLangString)))
/* 67:   */      {
/* 68:68 */        return;
/* 69:   */      }
/* 70:   */      
/* 71:71 */      this.jdField_a_of_type_JavaUtilLoggingLogger.logp(this.jdField_a_of_type_JavaUtilLoggingLevel, "", "", "[" + this.b + "]" + str);
/* 72:72 */      if (this.jdField_a_of_type_JavaIoPrintStream != null) {
/* 73:73 */        this.jdField_a_of_type_JavaIoPrintStream.append(str);
/* 74:74 */        this.jdField_a_of_type_JavaIoPrintStream.append("\n");
/* 75:75 */        this.jdField_a_of_type_JavaIoPrintStream.flush();
/* 76:   */      }
/* 77:77 */      return;
/* 78:   */    }
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     e
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */