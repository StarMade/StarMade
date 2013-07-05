/*     */ package org.jaxen.saxpath;
/*     */ 
/*     */ import org.jaxen.JaxenRuntimeException;
/*     */ 
/*     */ public class Axis
/*     */ {
/*     */   public static final int INVALID_AXIS = 0;
/*     */   public static final int CHILD = 1;
/*     */   public static final int DESCENDANT = 2;
/*     */   public static final int PARENT = 3;
/*     */   public static final int ANCESTOR = 4;
/*     */   public static final int FOLLOWING_SIBLING = 5;
/*     */   public static final int PRECEDING_SIBLING = 6;
/*     */   public static final int FOLLOWING = 7;
/*     */   public static final int PRECEDING = 8;
/*     */   public static final int ATTRIBUTE = 9;
/*     */   public static final int NAMESPACE = 10;
/*     */   public static final int SELF = 11;
/*     */   public static final int DESCENDANT_OR_SELF = 12;
/*     */   public static final int ANCESTOR_OR_SELF = 13;
/*     */ 
/*     */   public static String lookup(int axisNum)
/*     */   {
/* 122 */     switch (axisNum)
/*     */     {
/*     */     case 1:
/* 125 */       return "child";
/*     */     case 2:
/* 128 */       return "descendant";
/*     */     case 3:
/* 131 */       return "parent";
/*     */     case 4:
/* 134 */       return "ancestor";
/*     */     case 5:
/* 137 */       return "following-sibling";
/*     */     case 6:
/* 140 */       return "preceding-sibling";
/*     */     case 7:
/* 143 */       return "following";
/*     */     case 8:
/* 146 */       return "preceding";
/*     */     case 9:
/* 149 */       return "attribute";
/*     */     case 10:
/* 152 */       return "namespace";
/*     */     case 11:
/* 155 */       return "self";
/*     */     case 12:
/* 158 */       return "descendant-or-self";
/*     */     case 13:
/* 161 */       return "ancestor-or-self";
/*     */     }
/*     */ 
/* 164 */     throw new JaxenRuntimeException("Illegal Axis Number");
/*     */   }
/*     */ 
/*     */   public static int lookup(String axisName)
/*     */   {
/* 180 */     if ("child".equals(axisName))
/*     */     {
/* 182 */       return 1;
/*     */     }
/*     */ 
/* 185 */     if ("descendant".equals(axisName))
/*     */     {
/* 187 */       return 2;
/*     */     }
/*     */ 
/* 190 */     if ("parent".equals(axisName))
/*     */     {
/* 192 */       return 3;
/*     */     }
/*     */ 
/* 195 */     if ("ancestor".equals(axisName))
/*     */     {
/* 197 */       return 4;
/*     */     }
/*     */ 
/* 200 */     if ("following-sibling".equals(axisName))
/*     */     {
/* 202 */       return 5;
/*     */     }
/*     */ 
/* 205 */     if ("preceding-sibling".equals(axisName))
/*     */     {
/* 207 */       return 6;
/*     */     }
/*     */ 
/* 210 */     if ("following".equals(axisName))
/*     */     {
/* 212 */       return 7;
/*     */     }
/*     */ 
/* 215 */     if ("preceding".equals(axisName))
/*     */     {
/* 217 */       return 8;
/*     */     }
/*     */ 
/* 220 */     if ("attribute".equals(axisName))
/*     */     {
/* 222 */       return 9;
/*     */     }
/*     */ 
/* 225 */     if ("namespace".equals(axisName))
/*     */     {
/* 227 */       return 10;
/*     */     }
/*     */ 
/* 230 */     if ("self".equals(axisName))
/*     */     {
/* 232 */       return 11;
/*     */     }
/*     */ 
/* 235 */     if ("descendant-or-self".equals(axisName))
/*     */     {
/* 237 */       return 12;
/*     */     }
/*     */ 
/* 240 */     if ("ancestor-or-self".equals(axisName))
/*     */     {
/* 242 */       return 13;
/*     */     }
/*     */ 
/* 245 */     return 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.Axis
 * JD-Core Version:    0.6.2
 */