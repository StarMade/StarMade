/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Method;
/*     */ import org.lwjgl.PointerWrapperAbstract;
/*     */ 
/*     */ public final class KHRDebugCallback extends PointerWrapperAbstract
/*     */ {
/*     */   private static final int GL_DEBUG_SEVERITY_HIGH = 37190;
/*     */   private static final int GL_DEBUG_SEVERITY_MEDIUM = 37191;
/*     */   private static final int GL_DEBUG_SEVERITY_LOW = 37192;
/*     */   private static final int GL_DEBUG_SEVERITY_NOTIFICATION = 33387;
/*     */   private static final int GL_DEBUG_SOURCE_API = 33350;
/*     */   private static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM = 33351;
/*     */   private static final int GL_DEBUG_SOURCE_SHADER_COMPILER = 33352;
/*     */   private static final int GL_DEBUG_SOURCE_THIRD_PARTY = 33353;
/*     */   private static final int GL_DEBUG_SOURCE_APPLICATION = 33354;
/*     */   private static final int GL_DEBUG_SOURCE_OTHER = 33355;
/*     */   private static final int GL_DEBUG_TYPE_ERROR = 33356;
/*     */   private static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357;
/*     */   private static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 33358;
/*     */   private static final int GL_DEBUG_TYPE_PORTABILITY = 33359;
/*     */   private static final int GL_DEBUG_TYPE_PERFORMANCE = 33360;
/*     */   private static final int GL_DEBUG_TYPE_OTHER = 33361;
/*     */   private static final int GL_DEBUG_TYPE_MARKER = 33384;
/*  82 */   private static final long CALLBACK_POINTER = pointer;
/*     */   private final Handler handler;
/*     */ 
/*     */   public KHRDebugCallback()
/*     */   {
/*  92 */     this(new Handler() {
/*     */       public void handleMessage(int source, int type, int id, int severity, String message) {
/*  94 */         System.err.println("[LWJGL] KHR_debug message");
/*  95 */         System.err.println("\tID: " + id);
/*     */         String description;
/*  98 */         switch (source) {
/*     */         case 33350:
/* 100 */           description = "API";
/* 101 */           break;
/*     */         case 33351:
/* 103 */           description = "WINDOW SYSTEM";
/* 104 */           break;
/*     */         case 33352:
/* 106 */           description = "SHADER COMPILER";
/* 107 */           break;
/*     */         case 33353:
/* 109 */           description = "THIRD PARTY";
/* 110 */           break;
/*     */         case 33354:
/* 112 */           description = "APPLICATION";
/* 113 */           break;
/*     */         case 33355:
/* 115 */           description = "OTHER";
/* 116 */           break;
/*     */         default:
/* 118 */           description = printUnknownToken(source);
/*     */         }
/* 120 */         System.err.println("\tSource: " + description);
/*     */ 
/* 122 */         switch (type) {
/*     */         case 33356:
/* 124 */           description = "ERROR";
/* 125 */           break;
/*     */         case 33357:
/* 127 */           description = "DEPRECATED BEHAVIOR";
/* 128 */           break;
/*     */         case 33358:
/* 130 */           description = "UNDEFINED BEHAVIOR";
/* 131 */           break;
/*     */         case 33359:
/* 133 */           description = "PORTABILITY";
/* 134 */           break;
/*     */         case 33360:
/* 136 */           description = "PERFORMANCE";
/* 137 */           break;
/*     */         case 33361:
/* 139 */           description = "OTHER";
/* 140 */           break;
/*     */         case 33384:
/* 142 */           description = "MARKER";
/* 143 */           break;
/*     */         default:
/* 145 */           description = printUnknownToken(type);
/*     */         }
/* 147 */         System.err.println("\tType: " + description);
/*     */ 
/* 149 */         switch (severity) {
/*     */         case 37190:
/* 151 */           description = "HIGH";
/* 152 */           break;
/*     */         case 37191:
/* 154 */           description = "MEDIUM";
/* 155 */           break;
/*     */         case 37192:
/* 157 */           description = "LOW";
/* 158 */           break;
/*     */         case 33387:
/* 160 */           description = "NOTIFICATION";
/* 161 */           break;
/*     */         default:
/* 163 */           description = printUnknownToken(severity);
/*     */         }
/* 165 */         System.err.println("\tSeverity: " + description);
/*     */ 
/* 167 */         System.err.println("\tMessage: " + message);
/*     */       }
/*     */ 
/*     */       private String printUnknownToken(int token) {
/* 171 */         return "Unknown (0x" + Integer.toHexString(token).toUpperCase() + ")";
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public KHRDebugCallback(Handler handler)
/*     */   {
/* 184 */     super(CALLBACK_POINTER);
/*     */ 
/* 186 */     this.handler = handler;
/*     */   }
/*     */ 
/*     */   Handler getHandler() {
/* 190 */     return this.handler;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  75 */     long pointer = 0L;
/*     */     try
/*     */     {
/*  78 */       pointer = ((Long)Class.forName("org.lwjgl.opengl.CallbackUtil").getDeclaredMethod("getDebugCallbackKHR", new Class[0]).invoke(null, new Object[0])).longValue();
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
 * Qualified Name:     org.lwjgl.opengl.KHRDebugCallback
 * JD-Core Version:    0.6.2
 */