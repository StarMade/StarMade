/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import org.lwjgl.PointerWrapperAbstract;
/*     */ 
/*     */ public final class ARBDebugOutputCallback extends PointerWrapperAbstract
/*     */ {
/*     */   private static final int GL_DEBUG_SEVERITY_HIGH_ARB = 37190;
/*     */   private static final int GL_DEBUG_SEVERITY_MEDIUM_ARB = 37191;
/*     */   private static final int GL_DEBUG_SEVERITY_LOW_ARB = 37192;
/*     */   private static final int GL_DEBUG_SOURCE_API_ARB = 33350;
/*     */   private static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM_ARB = 33351;
/*     */   private static final int GL_DEBUG_SOURCE_SHADER_COMPILER_ARB = 33352;
/*     */   private static final int GL_DEBUG_SOURCE_THIRD_PARTY_ARB = 33353;
/*     */   private static final int GL_DEBUG_SOURCE_APPLICATION_ARB = 33354;
/*     */   private static final int GL_DEBUG_SOURCE_OTHER_ARB = 33355;
/*     */   private static final int GL_DEBUG_TYPE_ERROR_ARB = 33356;
/*     */   private static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR_ARB = 33357;
/*     */   private static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR_ARB = 33358;
/*     */   private static final int GL_DEBUG_TYPE_PORTABILITY_ARB = 33359;
/*     */   private static final int GL_DEBUG_TYPE_PERFORMANCE_ARB = 33360;
/*     */   private static final int GL_DEBUG_TYPE_OTHER_ARB = 33361;
/*  80 */   private static final long CALLBACK_POINTER = pointer;
/*     */   private final Handler handler;
/*     */ 
/*     */   public ARBDebugOutputCallback()
/*     */   {
/*  90 */     this(new Handler() {
/*     */       public void handleMessage(int source, int type, int id, int severity, String message) {
/*  92 */         System.err.println("[LWJGL] ARB_debug_output message");
/*  93 */         System.err.println("\tID: " + id);
/*     */         String description;
/*  96 */         switch (source) {
/*     */         case 33350:
/*  98 */           description = "API";
/*  99 */           break;
/*     */         case 33351:
/* 101 */           description = "WINDOW SYSTEM";
/* 102 */           break;
/*     */         case 33352:
/* 104 */           description = "SHADER COMPILER";
/* 105 */           break;
/*     */         case 33353:
/* 107 */           description = "THIRD PARTY";
/* 108 */           break;
/*     */         case 33354:
/* 110 */           description = "APPLICATION";
/* 111 */           break;
/*     */         case 33355:
/* 113 */           description = "OTHER";
/* 114 */           break;
/*     */         default:
/* 116 */           description = printUnknownToken(source);
/*     */         }
/* 118 */         System.err.println("\tSource: " + description);
/*     */ 
/* 120 */         switch (type) {
/*     */         case 33356:
/* 122 */           description = "ERROR";
/* 123 */           break;
/*     */         case 33357:
/* 125 */           description = "DEPRECATED BEHAVIOR";
/* 126 */           break;
/*     */         case 33358:
/* 128 */           description = "UNDEFINED BEHAVIOR";
/* 129 */           break;
/*     */         case 33359:
/* 131 */           description = "PORTABILITY";
/* 132 */           break;
/*     */         case 33360:
/* 134 */           description = "PERFORMANCE";
/* 135 */           break;
/*     */         case 33361:
/* 137 */           description = "OTHER";
/* 138 */           break;
/*     */         default:
/* 140 */           description = printUnknownToken(type);
/*     */         }
/* 142 */         System.err.println("\tType: " + description);
/*     */ 
/* 144 */         switch (severity) {
/*     */         case 37190:
/* 146 */           description = "HIGH";
/* 147 */           break;
/*     */         case 37191:
/* 149 */           description = "MEDIUM";
/* 150 */           break;
/*     */         case 37192:
/* 152 */           description = "LOW";
/* 153 */           break;
/*     */         default:
/* 155 */           description = printUnknownToken(severity);
/*     */         }
/* 157 */         System.err.println("\tSeverity: " + description);
/*     */ 
/* 159 */         System.err.println("\tMessage: " + message);
/*     */       }
/*     */ 
/*     */       private String printUnknownToken(int token) {
/* 163 */         return "Unknown (0x" + Integer.toHexString(token).toUpperCase() + ")";
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public ARBDebugOutputCallback(Handler handler)
/*     */   {
/* 176 */     super(CALLBACK_POINTER);
/*     */ 
/* 178 */     this.handler = handler;
/*     */   }
/*     */ 
/*     */   Handler getHandler() {
/* 182 */     return this.handler;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  73 */     long pointer = 0L;
/*     */     try
/*     */     {
/*  76 */       pointer = ((Long)Class.forName("org.lwjgl.opengl.CallbackUtil").getDeclaredMethod("getDebugOutputCallbackARB", new Class[0]).invoke(null, new Object[0])).longValue();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract interface Handler
/*     */   {
/*     */     public abstract void handleMessage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDebugOutputCallback
 * JD-Core Version:    0.6.2
 */