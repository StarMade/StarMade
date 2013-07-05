/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ public class XRandR
/*     */ {
/*     */   private static Screen[] current;
/*     */   private static Map<String, Screen[]> screens;
/* 201 */   private static final Pattern SCREEN_PATTERN1 = Pattern.compile("^(\\d+)x(\\d+)\\+(\\d+)\\+(\\d+)$");
/*     */ 
/* 204 */   private static final Pattern SCREEN_PATTERN2 = Pattern.compile("^(\\d+)x(\\d+)$");
/*     */ 
/*     */   private static void populate()
/*     */   {
/*  56 */     if (screens == null)
/*     */     {
/*  58 */       screens = new HashMap();
/*     */       try
/*     */       {
/*  65 */         Process p = Runtime.getRuntime().exec(new String[] { "xrandr", "-q" });
/*     */ 
/*  67 */         List currentList = new ArrayList();
/*  68 */         List possibles = new ArrayList();
/*  69 */         String name = null;
/*     */ 
/*  71 */         BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*     */         String line;
/*  73 */         while ((line = br.readLine()) != null)
/*     */         {
/*  75 */           line = line.trim();
/*  76 */           String[] sa = line.split("\\s+");
/*     */ 
/*  78 */           if ("connected".equals(sa[1]))
/*     */           {
/*  81 */             if (name != null)
/*     */             {
/*  83 */               screens.put(name, possibles.toArray(new Screen[possibles.size()]));
/*  84 */               possibles.clear();
/*     */             }
/*  86 */             name = sa[0];
/*     */ 
/*  89 */             parseScreen(currentList, name, sa[2]);
/*     */           }
/*  91 */           else if (Pattern.matches("\\d*x\\d*", sa[0]))
/*     */           {
/*  94 */             parseScreen(possibles, name, sa[0]);
/*     */           }
/*     */         }
/*     */ 
/*  98 */         screens.put(name, possibles.toArray(new Screen[possibles.size()]));
/*     */ 
/* 100 */         current = (Screen[])currentList.toArray(new Screen[currentList.size()]);
/*     */       }
/*     */       catch (Throwable e)
/*     */       {
/* 104 */         LWJGLUtil.log("Exception in XRandR.populate(): " + e.getMessage());
/* 105 */         screens.clear();
/* 106 */         current = new Screen[0];
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Screen[] getConfiguration()
/*     */   {
/* 117 */     populate();
/*     */ 
/* 119 */     return (Screen[])current.clone();
/*     */   }
/*     */ 
/*     */   public static void setConfiguration(Screen[] screens)
/*     */   {
/* 130 */     if (screens.length == 0) {
/* 131 */       throw new IllegalArgumentException("Must specify at least one screen");
/*     */     }
/* 133 */     List cmd = new ArrayList();
/* 134 */     cmd.add("xrandr");
/*     */ 
/* 137 */     for (Screen screen : current) {
/* 138 */       boolean found = false;
/* 139 */       for (Screen screen1 : screens) {
/* 140 */         if (screen1.name.equals(screen.name)) {
/* 141 */           found = true;
/* 142 */           break;
/*     */         }
/*     */       }
/*     */ 
/* 146 */       if (!found) {
/* 147 */         cmd.add("--output");
/* 148 */         cmd.add(screen.name);
/* 149 */         cmd.add("--off");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 154 */     for (Screen screen : screens) {
/* 155 */       screen.getArgs(cmd);
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 162 */       Process p = Runtime.getRuntime().exec((String[])cmd.toArray(new String[cmd.size()]));
/*     */ 
/* 165 */       BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*     */       String line;
/* 167 */       while ((line = br.readLine()) != null)
/*     */       {
/* 169 */         LWJGLUtil.log("Unexpected output from xrandr process: " + line);
/*     */       }
/* 171 */       current = screens;
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 175 */       LWJGLUtil.log("XRandR exception in setConfiguration(): " + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String[] getScreenNames()
/*     */   {
/* 185 */     populate();
/* 186 */     return (String[])screens.keySet().toArray(new String[screens.size()]);
/*     */   }
/*     */ 
/*     */   public static Screen[] getResolutions(String name)
/*     */   {
/* 196 */     populate();
/*     */ 
/* 198 */     return (Screen[])((Screen[])screens.get(name)).clone();
/*     */   }
/*     */ 
/*     */   private static void parseScreen(List<Screen> list, String name, String what)
/*     */   {
/* 220 */     Matcher m = SCREEN_PATTERN1.matcher(what);
/* 221 */     if (!m.matches())
/*     */     {
/* 223 */       m = SCREEN_PATTERN2.matcher(what);
/* 224 */       if (!m.matches())
/*     */       {
/* 226 */         LWJGLUtil.log("Did not match: " + what);
/* 227 */         return;
/*     */       }
/*     */     }
/* 230 */     int width = Integer.parseInt(m.group(1));
/* 231 */     int height = Integer.parseInt(m.group(2));
/*     */     int ypos;
/*     */     int xpos;
/*     */     int ypos;
/* 233 */     if (m.groupCount() > 3)
/*     */     {
/* 235 */       int xpos = Integer.parseInt(m.group(3));
/* 236 */       ypos = Integer.parseInt(m.group(4));
/*     */     }
/*     */     else
/*     */     {
/* 240 */       xpos = 0;
/* 241 */       ypos = 0;
/*     */     }
/* 243 */     list.add(new Screen(name, width, height, xpos, ypos, null));
/*     */   }
/*     */ 
/*     */   public static class Screen
/*     */     implements Cloneable
/*     */   {
/*     */     public final String name;
/*     */     public final int width;
/*     */     public final int height;
/*     */     public int xPos;
/*     */     public int yPos;
/*     */ 
/*     */     private Screen(String name, int width, int height, int xPos, int yPos)
/*     */     {
/* 281 */       this.name = name;
/* 282 */       this.width = width;
/* 283 */       this.height = height;
/* 284 */       this.xPos = xPos;
/* 285 */       this.yPos = yPos;
/*     */     }
/*     */ 
/*     */     private void getArgs(List<String> argList)
/*     */     {
/* 290 */       argList.add("--output");
/* 291 */       argList.add(this.name);
/* 292 */       argList.add("--mode");
/* 293 */       argList.add(this.width + "x" + this.height);
/* 294 */       argList.add("--pos");
/* 295 */       argList.add(this.xPos + "x" + this.yPos);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 301 */       return this.name + " " + this.width + "x" + this.height + " @ " + this.xPos + "x" + this.yPos;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.XRandR
 * JD-Core Version:    0.6.2
 */