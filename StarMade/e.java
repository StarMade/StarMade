/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.logging.Handler;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ final class e extends ByteArrayOutputStream
/*    */ {
/*    */   private String jdField_a_of_type_JavaLangString;
/*    */   private Logger jdField_a_of_type_JavaUtilLoggingLogger;
/*    */   private Level jdField_a_of_type_JavaUtilLoggingLevel;
/*    */   private PrintStream jdField_a_of_type_JavaIoPrintStream;
/*    */   private String b;
/*    */ 
/*    */   public e(PrintStream paramPrintStream, Logger paramLogger, Level paramLevel, String paramString)
/*    */   {
/* 32 */     this.b = paramString;
/* 33 */     this.jdField_a_of_type_JavaUtilLoggingLogger = paramLogger;
/* 34 */     this.jdField_a_of_type_JavaUtilLoggingLevel = paramLevel;
/* 35 */     this.jdField_a_of_type_JavaLangString = System.getProperty("line.separator");
/* 36 */     this.jdField_a_of_type_JavaIoPrintStream = paramPrintStream;
/*    */   }
/*    */ 
/*    */   public final void close()
/*    */   {
/* 45 */     super.close();
/*    */     Handler[] arrayOfHandler;
/* 46 */     int i = (arrayOfHandler = this.jdField_a_of_type_JavaUtilLoggingLogger.getHandlers()).length; for (int j = 0; j < i; j++) arrayOfHandler[j]
/* 47 */         .close();
/*    */   }
/*    */ 
/*    */   public final void flush()
/*    */   {
/* 61 */     synchronized (this) {
/* 62 */       super.flush();
/* 63 */       String str = toString();
/* 64 */       super.reset();
/*    */ 
/* 66 */       if ((str.length() == 0) || (str.equals(this.jdField_a_of_type_JavaLangString)))
/*    */       {
/* 68 */         return;
/*    */       }
/*    */ 
/* 71 */       this.jdField_a_of_type_JavaUtilLoggingLogger.logp(this.jdField_a_of_type_JavaUtilLoggingLevel, "", "", "[" + this.b + "]" + str);
/* 72 */       if (this.jdField_a_of_type_JavaIoPrintStream != null) {
/* 73 */         this.jdField_a_of_type_JavaIoPrintStream.append(str);
/* 74 */         this.jdField_a_of_type_JavaIoPrintStream.append("\n");
/* 75 */         this.jdField_a_of_type_JavaIoPrintStream.flush();
/*    */       }
/* 77 */       return;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     e
 * JD-Core Version:    0.6.2
 */