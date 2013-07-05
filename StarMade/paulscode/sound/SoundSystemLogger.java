/*     */ package paulscode.sound;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class SoundSystemLogger
/*     */ {
/*     */   public void message(String message, int indent)
/*     */   {
/*  60 */     String spacer = "";
/*  61 */     for (int x = 0; x < indent; x++)
/*     */     {
/*  63 */       spacer = spacer + "    ";
/*     */     }
/*     */ 
/*  66 */     String messageText = spacer + message;
/*     */ 
/*  69 */     System.out.println(messageText);
/*     */   }
/*     */ 
/*     */   public void importantMessage(String message, int indent)
/*     */   {
/*  81 */     String spacer = "";
/*  82 */     for (int x = 0; x < indent; x++)
/*     */     {
/*  84 */       spacer = spacer + "    ";
/*     */     }
/*     */ 
/*  87 */     String messageText = spacer + message;
/*     */ 
/*  90 */     System.out.println(messageText);
/*     */   }
/*     */ 
/*     */   public boolean errorCheck(boolean error, String classname, String message, int indent)
/*     */   {
/* 104 */     if (error)
/* 105 */       errorMessage(classname, message, indent);
/* 106 */     return error;
/*     */   }
/*     */ 
/*     */   public void errorMessage(String classname, String message, int indent)
/*     */   {
/* 120 */     String spacer = "";
/* 121 */     for (int x = 0; x < indent; x++)
/*     */     {
/* 123 */       spacer = spacer + "    ";
/*     */     }
/*     */ 
/* 126 */     String headerLine = spacer + "Error in class '" + classname + "'";
/*     */ 
/* 128 */     String messageText = "    " + spacer + message;
/*     */ 
/* 131 */     System.out.println(headerLine);
/* 132 */     System.out.println(messageText);
/*     */   }
/*     */ 
/*     */   public void printStackTrace(Exception e, int indent)
/*     */   {
/* 142 */     printExceptionMessage(e, indent);
/* 143 */     importantMessage("STACK TRACE:", indent);
/* 144 */     if (e == null) {
/* 145 */       return;
/*     */     }
/* 147 */     StackTraceElement[] stack = e.getStackTrace();
/* 148 */     if (stack == null) {
/* 149 */       return;
/*     */     }
/*     */ 
/* 152 */     for (int x = 0; x < stack.length; x++)
/*     */     {
/* 154 */       StackTraceElement line = stack[x];
/* 155 */       if (line != null)
/* 156 */         message(line.toString(), indent + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void printExceptionMessage(Exception e, int indent)
/*     */   {
/* 167 */     importantMessage("ERROR MESSAGE:", indent);
/* 168 */     if (e.getMessage() == null)
/* 169 */       message("(none)", indent + 1);
/*     */     else
/* 171 */       message(e.getMessage(), indent + 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystemLogger
 * JD-Core Version:    0.6.2
 */