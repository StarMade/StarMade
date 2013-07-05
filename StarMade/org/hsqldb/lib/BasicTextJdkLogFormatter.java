package org.hsqldb.lib;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class BasicTextJdkLogFormatter extends Formatter
{
  protected boolean withTime = true;
  public static String LS = System.getProperty("line.separator");
  protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

  public BasicTextJdkLogFormatter(boolean paramBoolean)
  {
    this.withTime = paramBoolean;
  }

  public BasicTextJdkLogFormatter()
  {
  }

  public String format(LogRecord paramLogRecord)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.withTime)
      localStringBuilder.append(new StringBuilder().append(this.sdf.format(new Date(paramLogRecord.getMillis()))).append("  ").toString());
    localStringBuilder.append(new StringBuilder().append(paramLogRecord.getLevel()).append("  ").append(formatMessage(paramLogRecord)).toString());
    if (paramLogRecord.getThrown() != null)
    {
      StringWriter localStringWriter = new StringWriter();
      paramLogRecord.getThrown().printStackTrace(new PrintWriter(localStringWriter));
      localStringBuilder.append(new StringBuilder().append(LS).append(localStringWriter).toString());
    }
    return new StringBuilder().append(localStringBuilder.toString()).append(LS).toString();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.BasicTextJdkLogFormatter
 * JD-Core Version:    0.6.2
 */