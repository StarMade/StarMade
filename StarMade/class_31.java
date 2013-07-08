import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public final class class_31
  extends Formatter
{
  private DateFormat jdField_field_446_of_type_JavaTextDateFormat;
  private static String jdField_field_446_of_type_JavaLangString = System.getProperty("line.separator");
  
  public final String format(LogRecord paramLogRecord)
  {
    StringBuffer localStringBuffer = new StringBuffer(180);
    if (this.jdField_field_446_of_type_JavaTextDateFormat == null) {
      this.jdField_field_446_of_type_JavaTextDateFormat = DateFormat.getDateTimeInstance();
    }
    localStringBuffer.append('[');
    localStringBuffer.append(this.jdField_field_446_of_type_JavaTextDateFormat.format(new Date(paramLogRecord.getMillis())));
    localStringBuffer.append(paramLogRecord.getSourceClassName());
    localStringBuffer.append(paramLogRecord.getSourceMethodName());
    localStringBuffer.append(']');
    localStringBuffer.append(' ');
    localStringBuffer.append(paramLogRecord.getLevel());
    localStringBuffer.append(": ");
    localStringBuffer.append(formatMessage(paramLogRecord).replaceFirst("\\]", "] "));
    localStringBuffer.append(jdField_field_446_of_type_JavaLangString);
    if ((paramLogRecord = paramLogRecord.getThrown()) != null)
    {
      StringWriter localStringWriter = new StringWriter();
      paramLogRecord.printStackTrace(new PrintWriter(localStringWriter, true));
      localStringBuffer.append(localStringWriter.toString());
    }
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_31
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */