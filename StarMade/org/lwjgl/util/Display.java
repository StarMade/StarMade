/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.opengl.DisplayMode;
/*     */ 
/*     */ public final class Display
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */ 
/*     */   public static DisplayMode[] getAvailableDisplayModes(int minWidth, int minHeight, int maxWidth, int maxHeight, int minBPP, int maxBPP, int minFreq, int maxFreq)
/*     */     throws LWJGLException
/*     */   {
/*  71 */     DisplayMode[] modes = org.lwjgl.opengl.Display.getAvailableDisplayModes();
/*     */ 
/*  73 */     if (LWJGLUtil.DEBUG) {
/*  74 */       System.out.println("Available screen modes:");
/*  75 */       for (DisplayMode mode : modes) {
/*  76 */         System.out.println(mode);
/*     */       }
/*     */     }
/*     */ 
/*  80 */     ArrayList matches = new ArrayList(modes.length);
/*     */ 
/*  82 */     for (int i = 0; i < modes.length; i++) {
/*  83 */       assert (modes[i] != null) : ("" + i + " " + modes.length);
/*  84 */       if ((minWidth == -1) || (modes[i].getWidth() >= minWidth))
/*     */       {
/*  86 */         if ((maxWidth == -1) || (modes[i].getWidth() <= maxWidth))
/*     */         {
/*  88 */           if ((minHeight == -1) || (modes[i].getHeight() >= minHeight))
/*     */           {
/*  90 */             if ((maxHeight == -1) || (modes[i].getHeight() <= maxHeight))
/*     */             {
/*  92 */               if ((minBPP == -1) || (modes[i].getBitsPerPixel() >= minBPP))
/*     */               {
/*  94 */                 if ((maxBPP == -1) || (modes[i].getBitsPerPixel() <= maxBPP))
/*     */                 {
/*  98 */                   if (modes[i].getFrequency() != 0) {
/*  99 */                     if ((minFreq == -1) || (modes[i].getFrequency() >= minFreq))
/*     */                     {
/* 101 */                       if ((maxFreq != -1) && (modes[i].getFrequency() > maxFreq));
/*     */                     }
/*     */                   }
/* 104 */                   else matches.add(modes[i]); 
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 107 */     DisplayMode[] ret = new DisplayMode[matches.size()];
/* 108 */     matches.toArray(ret);
/* 109 */     if (LWJGLUtil.DEBUG);
/* 116 */     return ret;
/*     */   }
/*     */ 
/*     */   public static DisplayMode setDisplayMode(DisplayMode[] dm, String[] param)
/*     */     throws Exception
/*     */   {
/* 217 */     Arrays.sort(dm, new Comparator()
/*     */     {
/*     */       final Display.1FieldAccessor[] accessors;
/*     */ 
/*     */       public int compare(DisplayMode dm1, DisplayMode dm2)
/*     */       {
/* 184 */         for (Display.1FieldAccessor accessor : this.accessors) {
/* 185 */           int f1 = accessor.getInt(dm1);
/* 186 */           int f2 = accessor.getInt(dm2);
/*     */ 
/* 188 */           if ((accessor.usePreferred) && (f1 != f2)) {
/* 189 */             if (f1 == accessor.preferred)
/* 190 */               return -1;
/* 191 */             if (f2 == accessor.preferred) {
/* 192 */               return 1;
/*     */             }
/*     */ 
/* 195 */             int absf1 = Math.abs(f1 - accessor.preferred);
/* 196 */             int absf2 = Math.abs(f2 - accessor.preferred);
/* 197 */             if (absf1 < absf2)
/* 198 */               return -1;
/* 199 */             if (absf1 > absf2)
/* 200 */               return 1;
/*     */           }
/*     */           else
/*     */           {
/* 204 */             if (f1 < f2)
/* 205 */               return accessor.order;
/* 206 */             if (f1 != f2)
/*     */             {
/* 209 */               return -accessor.order;
/*     */             }
/*     */           }
/*     */         }
/* 212 */         return 0;
/*     */       }
/*     */     });
/* 220 */     if (LWJGLUtil.DEBUG) {
/* 221 */       System.out.println("Sorted display modes:");
/* 222 */       for (DisplayMode aDm : dm) {
/* 223 */         System.out.println(aDm);
/*     */       }
/*     */     }
/* 226 */     for (DisplayMode aDm : dm) {
/*     */       try {
/* 228 */         if (LWJGLUtil.DEBUG)
/* 229 */           System.out.println("Attempting to set displaymode: " + aDm);
/* 230 */         org.lwjgl.opengl.Display.setDisplayMode(aDm);
/* 231 */         return aDm;
/*     */       } catch (Exception e) {
/* 233 */         if (LWJGLUtil.DEBUG) {
/* 234 */           System.out.println("Failed to set display mode to " + aDm);
/* 235 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 240 */     throw new Exception("Failed to set display mode.");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Display
 * JD-Core Version:    0.6.2
 */