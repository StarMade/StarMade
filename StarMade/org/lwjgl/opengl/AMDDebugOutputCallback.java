/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import org.lwjgl.PointerWrapperAbstract;
/*   6:    */
/*  47:    */public final class AMDDebugOutputCallback
/*  48:    */  extends PointerWrapperAbstract
/*  49:    */{
/*  50:    */  private static final int GL_DEBUG_SEVERITY_HIGH_AMD = 37190;
/*  51:    */  private static final int GL_DEBUG_SEVERITY_MEDIUM_AMD = 37191;
/*  52:    */  private static final int GL_DEBUG_SEVERITY_LOW_AMD = 37192;
/*  53:    */  private static final int GL_DEBUG_CATEGORY_API_ERROR_AMD = 37193;
/*  54:    */  private static final int GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD = 37194;
/*  55:    */  private static final int GL_DEBUG_CATEGORY_DEPRECATION_AMD = 37195;
/*  56:    */  private static final int GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD = 37196;
/*  57:    */  private static final int GL_DEBUG_CATEGORY_PERFORMANCE_AMD = 37197;
/*  58:    */  private static final int GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD = 37198;
/*  59:    */  private static final int GL_DEBUG_CATEGORY_APPLICATION_AMD = 37199;
/*  60:    */  private static final int GL_DEBUG_CATEGORY_OTHER_AMD = 37200;
/*  61:    */  
/*  62:    */  static
/*  63:    */  {
/*  64: 64 */    long pointer = 0L;
/*  65:    */    try
/*  66:    */    {
/*  67: 67 */      pointer = ((Long)Class.forName("org.lwjgl.opengl.CallbackUtil").getDeclaredMethod("getDebugOutputCallbackAMD", new Class[0]).invoke(null, new Object[0])).longValue();
/*  68:    */    } catch (Exception e) {}
/*  69:    */  }
/*  70:    */  
/*  71: 71 */  private static final long CALLBACK_POINTER = pointer;
/*  72:    */  
/*  75:    */  private final Handler handler;
/*  76:    */  
/*  79:    */  public AMDDebugOutputCallback()
/*  80:    */  {
/*  81: 81 */    this(new Handler() {
/*  82:    */      public void handleMessage(int id, int category, int severity, String message) {
/*  83: 83 */        System.err.println("[LWJGL] AMD_debug_output message");
/*  84: 84 */        System.err.println("\tID: " + id);
/*  85:    */        
/*  86:    */        String description;
/*  87: 87 */        switch (category) {
/*  88:    */        case 37193: 
/*  89: 89 */          description = "API ERROR";
/*  90: 90 */          break;
/*  91:    */        case 37194: 
/*  92: 92 */          description = "WINDOW SYSTEM";
/*  93: 93 */          break;
/*  94:    */        case 37195: 
/*  95: 95 */          description = "DEPRECATION";
/*  96: 96 */          break;
/*  97:    */        case 37196: 
/*  98: 98 */          description = "UNDEFINED BEHAVIOR";
/*  99: 99 */          break;
/* 100:    */        case 37197: 
/* 101:101 */          description = "PERFORMANCE";
/* 102:102 */          break;
/* 103:    */        case 37198: 
/* 104:104 */          description = "SHADER COMPILER";
/* 105:105 */          break;
/* 106:    */        case 37199: 
/* 107:107 */          description = "APPLICATION";
/* 108:108 */          break;
/* 109:    */        case 37200: 
/* 110:110 */          description = "OTHER";
/* 111:111 */          break;
/* 112:    */        default: 
/* 113:113 */          description = printUnknownToken(category);
/* 114:    */        }
/* 115:115 */        System.err.println("\tCategory: " + description);
/* 116:    */        
/* 117:117 */        switch (severity) {
/* 118:    */        case 37190: 
/* 119:119 */          description = "HIGH";
/* 120:120 */          break;
/* 121:    */        case 37191: 
/* 122:122 */          description = "MEDIUM";
/* 123:123 */          break;
/* 124:    */        case 37192: 
/* 125:125 */          description = "LOW";
/* 126:126 */          break;
/* 127:    */        default: 
/* 128:128 */          description = printUnknownToken(severity);
/* 129:    */        }
/* 130:130 */        System.err.println("\tSeverity: " + description);
/* 131:    */        
/* 132:132 */        System.err.println("\tMessage: " + message);
/* 133:    */      }
/* 134:    */      
/* 135:    */      private String printUnknownToken(int token) {
/* 136:136 */        return "Unknown (0x" + Integer.toHexString(token).toUpperCase() + ")";
/* 137:    */      }
/* 138:    */    });
/* 139:    */  }
/* 140:    */  
/* 147:    */  public AMDDebugOutputCallback(Handler handler)
/* 148:    */  {
/* 149:149 */    super(CALLBACK_POINTER);
/* 150:    */    
/* 151:151 */    this.handler = handler;
/* 152:    */  }
/* 153:    */  
/* 154:    */  Handler getHandler() {
/* 155:155 */    return this.handler;
/* 156:    */  }
/* 157:    */  
/* 158:    */  public static abstract interface Handler
/* 159:    */  {
/* 160:    */    public abstract void handleMessage(int paramInt1, int paramInt2, int paramInt3, String paramString);
/* 161:    */  }
/* 162:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDDebugOutputCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */