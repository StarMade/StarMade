/*   1:    */package paulscode.sound;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */
/*  56:    */public class SoundSystemLogger
/*  57:    */{
/*  58:    */  public void message(String message, int indent)
/*  59:    */  {
/*  60: 60 */    String spacer = "";
/*  61: 61 */    for (int x = 0; x < indent; x++)
/*  62:    */    {
/*  63: 63 */      spacer = spacer + "    ";
/*  64:    */    }
/*  65:    */    
/*  66: 66 */    String messageText = spacer + message;
/*  67:    */    
/*  69: 69 */    System.out.println(messageText);
/*  70:    */  }
/*  71:    */  
/*  79:    */  public void importantMessage(String message, int indent)
/*  80:    */  {
/*  81: 81 */    String spacer = "";
/*  82: 82 */    for (int x = 0; x < indent; x++)
/*  83:    */    {
/*  84: 84 */      spacer = spacer + "    ";
/*  85:    */    }
/*  86:    */    
/*  87: 87 */    String messageText = spacer + message;
/*  88:    */    
/*  90: 90 */    System.out.println(messageText);
/*  91:    */  }
/*  92:    */  
/* 102:    */  public boolean errorCheck(boolean error, String classname, String message, int indent)
/* 103:    */  {
/* 104:104 */    if (error)
/* 105:105 */      errorMessage(classname, message, indent);
/* 106:106 */    return error;
/* 107:    */  }
/* 108:    */  
/* 118:    */  public void errorMessage(String classname, String message, int indent)
/* 119:    */  {
/* 120:120 */    String spacer = "";
/* 121:121 */    for (int x = 0; x < indent; x++)
/* 122:    */    {
/* 123:123 */      spacer = spacer + "    ";
/* 124:    */    }
/* 125:    */    
/* 126:126 */    String headerLine = spacer + "Error in class '" + classname + "'";
/* 127:    */    
/* 128:128 */    String messageText = "    " + spacer + message;
/* 129:    */    
/* 131:131 */    System.out.println(headerLine);
/* 132:132 */    System.out.println(messageText);
/* 133:    */  }
/* 134:    */  
/* 140:    */  public void printStackTrace(Exception e, int indent)
/* 141:    */  {
/* 142:142 */    printExceptionMessage(e, indent);
/* 143:143 */    importantMessage("STACK TRACE:", indent);
/* 144:144 */    if (e == null) {
/* 145:145 */      return;
/* 146:    */    }
/* 147:147 */    StackTraceElement[] stack = e.getStackTrace();
/* 148:148 */    if (stack == null) {
/* 149:149 */      return;
/* 150:    */    }
/* 151:    */    
/* 152:152 */    for (int x = 0; x < stack.length; x++)
/* 153:    */    {
/* 154:154 */      StackTraceElement line = stack[x];
/* 155:155 */      if (line != null) {
/* 156:156 */        message(line.toString(), indent + 1);
/* 157:    */      }
/* 158:    */    }
/* 159:    */  }
/* 160:    */  
/* 165:    */  public void printExceptionMessage(Exception e, int indent)
/* 166:    */  {
/* 167:167 */    importantMessage("ERROR MESSAGE:", indent);
/* 168:168 */    if (e.getMessage() == null) {
/* 169:169 */      message("(none)", indent + 1);
/* 170:    */    } else {
/* 171:171 */      message(e.getMessage(), indent + 1);
/* 172:    */    }
/* 173:    */  }
/* 174:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.SoundSystemLogger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */