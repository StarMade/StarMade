import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

final class class_30
  extends ByteArrayOutputStream
{
  private String jdField_field_444_of_type_JavaLangString;
  private Logger jdField_field_444_of_type_JavaUtilLoggingLogger;
  private Level jdField_field_444_of_type_JavaUtilLoggingLevel;
  private PrintStream jdField_field_444_of_type_JavaIoPrintStream;
  private String field_445;
  
  public class_30(PrintStream paramPrintStream, Logger paramLogger, Level paramLevel, String paramString)
  {
    this.field_445 = paramString;
    this.jdField_field_444_of_type_JavaUtilLoggingLogger = paramLogger;
    this.jdField_field_444_of_type_JavaUtilLoggingLevel = paramLevel;
    this.jdField_field_444_of_type_JavaLangString = System.getProperty("line.separator");
    this.jdField_field_444_of_type_JavaIoPrintStream = paramPrintStream;
  }
  
  public final void close()
  {
    super.close();
    Handler[] arrayOfHandler;
    int i = (arrayOfHandler = this.jdField_field_444_of_type_JavaUtilLoggingLogger.getHandlers()).length;
    for (int j = 0; j < i; j++) {
      arrayOfHandler[j].close();
    }
  }
  
  public final void flush()
  {
    synchronized (this)
    {
      super.flush();
      String str = toString();
      super.reset();
      if ((str.length() == 0) || (str.equals(this.jdField_field_444_of_type_JavaLangString))) {
        return;
      }
      this.jdField_field_444_of_type_JavaUtilLoggingLogger.logp(this.jdField_field_444_of_type_JavaUtilLoggingLevel, "", "", "[" + this.field_445 + "]" + str);
      if (this.jdField_field_444_of_type_JavaIoPrintStream != null)
      {
        this.jdField_field_444_of_type_JavaIoPrintStream.append(str);
        this.jdField_field_444_of_type_JavaIoPrintStream.append("\n");
        this.jdField_field_444_of_type_JavaIoPrintStream.flush();
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_30
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */