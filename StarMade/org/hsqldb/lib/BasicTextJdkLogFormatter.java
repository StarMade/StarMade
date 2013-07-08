package org.hsqldb.lib;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class BasicTextJdkLogFormatter
  extends Formatter
{
  protected boolean withTime = true;
  public static String field_1773 = System.getProperty("line.separator");
  protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  
  public BasicTextJdkLogFormatter(boolean paramBoolean)
  {
    this.withTime = paramBoolean;
  }
  
  public BasicTextJdkLogFormatter() {}
  
  public String format(LogRecord paramLogRecord)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.withTime) {
      localStringBuilder.append(this.sdf.format(new Date(paramLogRecord.getMillis())) + "  ");
    }
    localStringBuilder.append(paramLogRecord.getLevel() + "  " + formatMessage(paramLogRecord));
    if (paramLogRecord.getThrown() != null)
    {
      StringWriter localStringWriter = new StringWriter();
      paramLogRecord.getThrown().printStackTrace(new PrintWriter(localStringWriter));
      localStringBuilder.append(field_1773 + localStringWriter);
    }
    return localStringBuilder.toString() + field_1773;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.BasicTextJdkLogFormatter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */