/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import org.lwjgl.PointerWrapperAbstract;
/*     */ 
/*     */ public final class AMDDebugOutputCallback extends PointerWrapperAbstract
/*     */ {
/*     */   private static final int GL_DEBUG_SEVERITY_HIGH_AMD = 37190;
/*     */   private static final int GL_DEBUG_SEVERITY_MEDIUM_AMD = 37191;
/*     */   private static final int GL_DEBUG_SEVERITY_LOW_AMD = 37192;
/*     */   private static final int GL_DEBUG_CATEGORY_API_ERROR_AMD = 37193;
/*     */   private static final int GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD = 37194;
/*     */   private static final int GL_DEBUG_CATEGORY_DEPRECATION_AMD = 37195;
/*     */   private static final int GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD = 37196;
/*     */   private static final int GL_DEBUG_CATEGORY_PERFORMANCE_AMD = 37197;
/*     */   private static final int GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD = 37198;
/*     */   private static final int GL_DEBUG_CATEGORY_APPLICATION_AMD = 37199;
/*     */   private static final int GL_DEBUG_CATEGORY_OTHER_AMD = 37200;
/*  71 */   private static final long CALLBACK_POINTER = pointer;
/*     */   private final Handler handler;
/*     */ 
/*     */   public AMDDebugOutputCallback()
/*     */   {
/*  81 */     this(new Handler() {
/*     */       public void handleMessage(int id, int category, int severity, String message) {
/*  83 */         System.err.println("[LWJGL] AMD_debug_output message");
/*  84 */         System.err.println("\tID: " + id);
/*     */         String description;
/*  87 */         switch (category) {
/*     */         case 37193:
/*  89 */           description = "API ERROR";
/*  90 */           break;
/*     */         case 37194:
/*  92 */           description = "WINDOW SYSTEM";
/*  93 */           break;
/*     */         case 37195:
/*  95 */           description = "DEPRECATION";
/*  96 */           break;
/*     */         case 37196:
/*  98 */           description = "UNDEFINED BEHAVIOR";
/*  99 */           break;
/*     */         case 37197:
/* 101 */           description = "PERFORMANCE";
/* 102 */           break;
/*     */         case 37198:
/* 104 */           description = "SHADER COMPILER";
/* 105 */           break;
/*     */         case 37199:
/* 107 */           description = "APPLICATION";
/* 108 */           break;
/*     */         case 37200:
/* 110 */           description = "OTHER";
/* 111 */           break;
/*     */         default:
/* 113 */           description = printUnknownToken(category);
/*     */         }
/* 115 */         System.err.println("\tCategory: " + description);
/*     */ 
/* 117 */         switch (severity) {
/*     */         case 37190:
/* 119 */           description = "HIGH";
/* 120 */           break;
/*     */         case 37191:
/* 122 */           description = "MEDIUM";
/* 123 */           break;
/*     */         case 37192:
/* 125 */           description = "LOW";
/* 126 */           break;
/*     */         default:
/* 128 */           description = printUnknownToken(severity);
/*     */         }
/* 130 */         System.err.println("\tSeverity: " + description);
/*     */ 
/* 132 */         System.err.println("\tMessage: " + message);
/*     */       }
/*     */ 
/*     */       private String printUnknownToken(int token) {
/* 136 */         return "Unknown (0x" + Integer.toHexString(token).toUpperCase() + ")";
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public AMDDebugOutputCallback(Handler handler)
/*     */   {
/* 149 */     super(CALLBACK_POINTER);
/*     */ 
/* 151 */     this.handler = handler;
/*     */   }
/*     */ 
/*     */   Handler getHandler() {
/* 155 */     return this.handler;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  64 */     long pointer = 0L;
/*     */     try
/*     */     {
/*  67 */       pointer = ((Long)Class.forName("org.lwjgl.opengl.CallbackUtil").getDeclaredMethod("getDebugOutputCallbackAMD", new Class[0]).invoke(null, new Object[0])).longValue();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract interface Handler
/*     */   {
/*     */     public abstract void handleMessage(int paramInt1, int paramInt2, int paramInt3, String paramString);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDDebugOutputCallback
 * JD-Core Version:    0.6.2
 */