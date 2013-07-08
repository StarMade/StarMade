/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import org.lwjgl.PointerWrapperAbstract;
/*   6:    */
/*  52:    */public final class KHRDebugCallback
/*  53:    */  extends PointerWrapperAbstract
/*  54:    */{
/*  55:    */  private static final int GL_DEBUG_SEVERITY_HIGH = 37190;
/*  56:    */  private static final int GL_DEBUG_SEVERITY_MEDIUM = 37191;
/*  57:    */  private static final int GL_DEBUG_SEVERITY_LOW = 37192;
/*  58:    */  private static final int GL_DEBUG_SEVERITY_NOTIFICATION = 33387;
/*  59:    */  private static final int GL_DEBUG_SOURCE_API = 33350;
/*  60:    */  private static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM = 33351;
/*  61:    */  private static final int GL_DEBUG_SOURCE_SHADER_COMPILER = 33352;
/*  62:    */  private static final int GL_DEBUG_SOURCE_THIRD_PARTY = 33353;
/*  63:    */  private static final int GL_DEBUG_SOURCE_APPLICATION = 33354;
/*  64:    */  private static final int GL_DEBUG_SOURCE_OTHER = 33355;
/*  65:    */  private static final int GL_DEBUG_TYPE_ERROR = 33356;
/*  66:    */  private static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357;
/*  67:    */  private static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 33358;
/*  68:    */  private static final int GL_DEBUG_TYPE_PORTABILITY = 33359;
/*  69:    */  private static final int GL_DEBUG_TYPE_PERFORMANCE = 33360;
/*  70:    */  private static final int GL_DEBUG_TYPE_OTHER = 33361;
/*  71:    */  private static final int GL_DEBUG_TYPE_MARKER = 33384;
/*  72:    */  
/*  73:    */  static
/*  74:    */  {
/*  75: 75 */    long pointer = 0L;
/*  76:    */    try
/*  77:    */    {
/*  78: 78 */      pointer = ((Long)Class.forName("org.lwjgl.opengl.CallbackUtil").getDeclaredMethod("getDebugCallbackKHR", new Class[0]).invoke(null, new Object[0])).longValue();
/*  79:    */    } catch (Exception e) {}
/*  80:    */  }
/*  81:    */  
/*  82: 82 */  private static final long CALLBACK_POINTER = pointer;
/*  83:    */  
/*  86:    */  private final Handler handler;
/*  87:    */  
/*  90:    */  public KHRDebugCallback()
/*  91:    */  {
/*  92: 92 */    this(new Handler() {
/*  93:    */      public void handleMessage(int source, int type, int id, int severity, String message) {
/*  94: 94 */        System.err.println("[LWJGL] KHR_debug message");
/*  95: 95 */        System.err.println("\tID: " + id);
/*  96:    */        
/*  97:    */        String description;
/*  98: 98 */        switch (source) {
/*  99:    */        case 33350: 
/* 100:100 */          description = "API";
/* 101:101 */          break;
/* 102:    */        case 33351: 
/* 103:103 */          description = "WINDOW SYSTEM";
/* 104:104 */          break;
/* 105:    */        case 33352: 
/* 106:106 */          description = "SHADER COMPILER";
/* 107:107 */          break;
/* 108:    */        case 33353: 
/* 109:109 */          description = "THIRD PARTY";
/* 110:110 */          break;
/* 111:    */        case 33354: 
/* 112:112 */          description = "APPLICATION";
/* 113:113 */          break;
/* 114:    */        case 33355: 
/* 115:115 */          description = "OTHER";
/* 116:116 */          break;
/* 117:    */        default: 
/* 118:118 */          description = printUnknownToken(source);
/* 119:    */        }
/* 120:120 */        System.err.println("\tSource: " + description);
/* 121:    */        
/* 122:122 */        switch (type) {
/* 123:    */        case 33356: 
/* 124:124 */          description = "ERROR";
/* 125:125 */          break;
/* 126:    */        case 33357: 
/* 127:127 */          description = "DEPRECATED BEHAVIOR";
/* 128:128 */          break;
/* 129:    */        case 33358: 
/* 130:130 */          description = "UNDEFINED BEHAVIOR";
/* 131:131 */          break;
/* 132:    */        case 33359: 
/* 133:133 */          description = "PORTABILITY";
/* 134:134 */          break;
/* 135:    */        case 33360: 
/* 136:136 */          description = "PERFORMANCE";
/* 137:137 */          break;
/* 138:    */        case 33361: 
/* 139:139 */          description = "OTHER";
/* 140:140 */          break;
/* 141:    */        case 33384: 
/* 142:142 */          description = "MARKER";
/* 143:143 */          break;
/* 144:    */        default: 
/* 145:145 */          description = printUnknownToken(type);
/* 146:    */        }
/* 147:147 */        System.err.println("\tType: " + description);
/* 148:    */        
/* 149:149 */        switch (severity) {
/* 150:    */        case 37190: 
/* 151:151 */          description = "HIGH";
/* 152:152 */          break;
/* 153:    */        case 37191: 
/* 154:154 */          description = "MEDIUM";
/* 155:155 */          break;
/* 156:    */        case 37192: 
/* 157:157 */          description = "LOW";
/* 158:158 */          break;
/* 159:    */        case 33387: 
/* 160:160 */          description = "NOTIFICATION";
/* 161:161 */          break;
/* 162:    */        default: 
/* 163:163 */          description = printUnknownToken(severity);
/* 164:    */        }
/* 165:165 */        System.err.println("\tSeverity: " + description);
/* 166:    */        
/* 167:167 */        System.err.println("\tMessage: " + message);
/* 168:    */      }
/* 169:    */      
/* 170:    */      private String printUnknownToken(int token) {
/* 171:171 */        return "Unknown (0x" + Integer.toHexString(token).toUpperCase() + ")";
/* 172:    */      }
/* 173:    */    });
/* 174:    */  }
/* 175:    */  
/* 182:    */  public KHRDebugCallback(Handler handler)
/* 183:    */  {
/* 184:184 */    super(CALLBACK_POINTER);
/* 185:    */    
/* 186:186 */    this.handler = handler;
/* 187:    */  }
/* 188:    */  
/* 189:    */  Handler getHandler() {
/* 190:190 */    return this.handler;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public static abstract interface Handler
/* 194:    */  {
/* 195:    */    public abstract void handleMessage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString);
/* 196:    */  }
/* 197:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.KHRDebugCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */