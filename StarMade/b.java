/*  1:   */import java.io.PrintWriter;
/*  2:   */import java.io.StringWriter;
/*  3:   */import java.text.DateFormat;
/*  4:   */import java.util.Date;
/*  5:   */import java.util.logging.Formatter;
/*  6:   */import java.util.logging.LogRecord;
/*  7:   */
/* 38:   */public final class b
/* 39:   */  extends Formatter
/* 40:   */{
/* 41:   */  private DateFormat jdField_a_of_type_JavaTextDateFormat;
/* 42:42 */  private static String jdField_a_of_type_JavaLangString = System.getProperty("line.separator");
/* 43:   */  
/* 60:   */  public final String format(LogRecord paramLogRecord)
/* 61:   */  {
/* 62:62 */    StringBuffer localStringBuffer = new StringBuffer(180);
/* 63:63 */    if (this.jdField_a_of_type_JavaTextDateFormat == null) {
/* 64:64 */      this.jdField_a_of_type_JavaTextDateFormat = DateFormat.getDateTimeInstance();
/* 65:   */    }
/* 66:66 */    localStringBuffer.append('[');
/* 67:67 */    localStringBuffer.append(this.jdField_a_of_type_JavaTextDateFormat.format(new Date(paramLogRecord.getMillis())));
/* 68:68 */    localStringBuffer.append(paramLogRecord.getSourceClassName());
/* 69:69 */    localStringBuffer.append(paramLogRecord.getSourceMethodName());
/* 70:70 */    localStringBuffer.append(']');
/* 71:71 */    localStringBuffer.append(' ');
/* 72:   */    
/* 74:74 */    localStringBuffer.append(paramLogRecord.getLevel());
/* 75:75 */    localStringBuffer.append(": ");
/* 76:76 */    localStringBuffer.append(formatMessage(paramLogRecord).replaceFirst("\\]", "] "));
/* 77:   */    
/* 78:78 */    localStringBuffer.append(jdField_a_of_type_JavaLangString);
/* 79:   */    
/* 81:81 */    if ((paramLogRecord = paramLogRecord.getThrown()) != null)
/* 82:   */    {
/* 83:83 */      StringWriter localStringWriter = new StringWriter();
/* 84:84 */      paramLogRecord.printStackTrace(new PrintWriter(localStringWriter, true));
/* 85:85 */      localStringBuffer.append(localStringWriter.toString());
/* 86:   */    }
/* 87:   */    
/* 88:88 */    return localStringBuffer.toString();
/* 89:   */  }
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */