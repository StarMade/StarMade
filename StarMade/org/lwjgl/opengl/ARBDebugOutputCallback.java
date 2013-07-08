/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import org.lwjgl.PointerWrapperAbstract;
/*   6:    */
/*  52:    */public final class ARBDebugOutputCallback
/*  53:    */  extends PointerWrapperAbstract
/*  54:    */{
/*  55:    */  private static final int GL_DEBUG_SEVERITY_HIGH_ARB = 37190;
/*  56:    */  private static final int GL_DEBUG_SEVERITY_MEDIUM_ARB = 37191;
/*  57:    */  private static final int GL_DEBUG_SEVERITY_LOW_ARB = 37192;
/*  58:    */  private static final int GL_DEBUG_SOURCE_API_ARB = 33350;
/*  59:    */  private static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM_ARB = 33351;
/*  60:    */  private static final int GL_DEBUG_SOURCE_SHADER_COMPILER_ARB = 33352;
/*  61:    */  private static final int GL_DEBUG_SOURCE_THIRD_PARTY_ARB = 33353;
/*  62:    */  private static final int GL_DEBUG_SOURCE_APPLICATION_ARB = 33354;
/*  63:    */  private static final int GL_DEBUG_SOURCE_OTHER_ARB = 33355;
/*  64:    */  private static final int GL_DEBUG_TYPE_ERROR_ARB = 33356;
/*  65:    */  private static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR_ARB = 33357;
/*  66:    */  private static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR_ARB = 33358;
/*  67:    */  private static final int GL_DEBUG_TYPE_PORTABILITY_ARB = 33359;
/*  68:    */  private static final int GL_DEBUG_TYPE_PERFORMANCE_ARB = 33360;
/*  69:    */  private static final int GL_DEBUG_TYPE_OTHER_ARB = 33361;
/*  70:    */  
/*  71:    */  static
/*  72:    */  {
/*  73: 73 */    long pointer = 0L;
/*  74:    */    try
/*  75:    */    {
/*  76: 76 */      pointer = ((Long)Class.forName("org.lwjgl.opengl.CallbackUtil").getDeclaredMethod("getDebugOutputCallbackARB", new Class[0]).invoke(null, new Object[0])).longValue();
/*  77:    */    } catch (Exception e) {}
/*  78:    */  }
/*  79:    */  
/*  80: 80 */  private static final long CALLBACK_POINTER = pointer;
/*  81:    */  
/*  84:    */  private final Handler handler;
/*  85:    */  
/*  88:    */  public ARBDebugOutputCallback()
/*  89:    */  {
/*  90: 90 */    this(new Handler() {
/*  91:    */      public void handleMessage(int source, int type, int id, int severity, String message) {
/*  92: 92 */        System.err.println("[LWJGL] ARB_debug_output message");
/*  93: 93 */        System.err.println("\tID: " + id);
/*  94:    */        
/*  95:    */        String description;
/*  96: 96 */        switch (source) {
/*  97:    */        case 33350: 
/*  98: 98 */          description = "API";
/*  99: 99 */          break;
/* 100:    */        case 33351: 
/* 101:101 */          description = "WINDOW SYSTEM";
/* 102:102 */          break;
/* 103:    */        case 33352: 
/* 104:104 */          description = "SHADER COMPILER";
/* 105:105 */          break;
/* 106:    */        case 33353: 
/* 107:107 */          description = "THIRD PARTY";
/* 108:108 */          break;
/* 109:    */        case 33354: 
/* 110:110 */          description = "APPLICATION";
/* 111:111 */          break;
/* 112:    */        case 33355: 
/* 113:113 */          description = "OTHER";
/* 114:114 */          break;
/* 115:    */        default: 
/* 116:116 */          description = printUnknownToken(source);
/* 117:    */        }
/* 118:118 */        System.err.println("\tSource: " + description);
/* 119:    */        
/* 120:120 */        switch (type) {
/* 121:    */        case 33356: 
/* 122:122 */          description = "ERROR";
/* 123:123 */          break;
/* 124:    */        case 33357: 
/* 125:125 */          description = "DEPRECATED BEHAVIOR";
/* 126:126 */          break;
/* 127:    */        case 33358: 
/* 128:128 */          description = "UNDEFINED BEHAVIOR";
/* 129:129 */          break;
/* 130:    */        case 33359: 
/* 131:131 */          description = "PORTABILITY";
/* 132:132 */          break;
/* 133:    */        case 33360: 
/* 134:134 */          description = "PERFORMANCE";
/* 135:135 */          break;
/* 136:    */        case 33361: 
/* 137:137 */          description = "OTHER";
/* 138:138 */          break;
/* 139:    */        default: 
/* 140:140 */          description = printUnknownToken(type);
/* 141:    */        }
/* 142:142 */        System.err.println("\tType: " + description);
/* 143:    */        
/* 144:144 */        switch (severity) {
/* 145:    */        case 37190: 
/* 146:146 */          description = "HIGH";
/* 147:147 */          break;
/* 148:    */        case 37191: 
/* 149:149 */          description = "MEDIUM";
/* 150:150 */          break;
/* 151:    */        case 37192: 
/* 152:152 */          description = "LOW";
/* 153:153 */          break;
/* 154:    */        default: 
/* 155:155 */          description = printUnknownToken(severity);
/* 156:    */        }
/* 157:157 */        System.err.println("\tSeverity: " + description);
/* 158:    */        
/* 159:159 */        System.err.println("\tMessage: " + message);
/* 160:    */      }
/* 161:    */      
/* 162:    */      private String printUnknownToken(int token) {
/* 163:163 */        return "Unknown (0x" + Integer.toHexString(token).toUpperCase() + ")";
/* 164:    */      }
/* 165:    */    });
/* 166:    */  }
/* 167:    */  
/* 174:    */  public ARBDebugOutputCallback(Handler handler)
/* 175:    */  {
/* 176:176 */    super(CALLBACK_POINTER);
/* 177:    */    
/* 178:178 */    this.handler = handler;
/* 179:    */  }
/* 180:    */  
/* 181:    */  Handler getHandler() {
/* 182:182 */    return this.handler;
/* 183:    */  }
/* 184:    */  
/* 185:    */  public static abstract interface Handler
/* 186:    */  {
/* 187:    */    public abstract void handleMessage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString);
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDebugOutputCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */