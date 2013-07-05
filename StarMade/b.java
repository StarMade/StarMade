/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import java.text.DateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.logging.Formatter;
/*    */ import java.util.logging.LogRecord;
/*    */ 
/*    */ public final class b extends Formatter
/*    */ {
/*    */   private DateFormat jdField_a_of_type_JavaTextDateFormat;
/* 42 */   private static String jdField_a_of_type_JavaLangString = System.getProperty("line.separator");
/*    */ 
/*    */   public final String format(LogRecord paramLogRecord)
/*    */   {
/* 62 */     StringBuffer localStringBuffer = new StringBuffer(180);
/* 63 */     if (this.jdField_a_of_type_JavaTextDateFormat == null) {
/* 64 */       this.jdField_a_of_type_JavaTextDateFormat = DateFormat.getDateTimeInstance();
/*    */     }
/* 66 */     localStringBuffer.append('[');
/* 67 */     localStringBuffer.append(this.jdField_a_of_type_JavaTextDateFormat.format(new Date(paramLogRecord.getMillis())));
/* 68 */     localStringBuffer.append(paramLogRecord.getSourceClassName());
/* 69 */     localStringBuffer.append(paramLogRecord.getSourceMethodName());
/* 70 */     localStringBuffer.append(']');
/* 71 */     localStringBuffer.append(' ');
/*    */ 
/* 74 */     localStringBuffer.append(paramLogRecord.getLevel());
/* 75 */     localStringBuffer.append(": ");
/* 76 */     localStringBuffer.append(formatMessage(paramLogRecord).replaceFirst("\\]", "] "));
/*    */ 
/* 78 */     localStringBuffer.append(jdField_a_of_type_JavaLangString);
/*    */ 
/* 81 */     if ((
/* 81 */       paramLogRecord = paramLogRecord.getThrown()) != null)
/*    */     {
/* 83 */       StringWriter localStringWriter = new StringWriter();
/* 84 */       paramLogRecord.printStackTrace(new PrintWriter(localStringWriter, true));
/* 85 */       localStringBuffer.append(localStringWriter.toString());
/*    */     }
/*    */ 
/* 88 */     return localStringBuffer.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     b
 * JD-Core Version:    0.6.2
 */