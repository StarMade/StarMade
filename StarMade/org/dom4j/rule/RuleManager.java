/*     */ package org.dom4j.rule;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.rule.pattern.NodeTypePattern;
/*     */ 
/*     */ public class RuleManager
/*     */ {
/*  28 */   private HashMap modes = new HashMap();
/*     */   private int appearenceCount;
/*     */   private Action valueOfAction;
/*     */ 
/*     */   public Mode getMode(String modeName)
/*     */   {
/*  52 */     Mode mode = (Mode)this.modes.get(modeName);
/*     */ 
/*  54 */     if (mode == null) {
/*  55 */       mode = createMode();
/*  56 */       this.modes.put(modeName, mode);
/*     */     }
/*     */ 
/*  59 */     return mode;
/*     */   }
/*     */ 
/*     */   public void addRule(Rule rule) {
/*  63 */     rule.setAppearenceCount(++this.appearenceCount);
/*     */ 
/*  65 */     Mode mode = getMode(rule.getMode());
/*  66 */     Rule[] childRules = rule.getUnionRules();
/*     */ 
/*  68 */     if (childRules != null) {
/*  69 */       int i = 0; for (int size = childRules.length; i < size; i++)
/*  70 */         mode.addRule(childRules[i]);
/*     */     }
/*     */     else {
/*  73 */       mode.addRule(rule);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeRule(Rule rule) {
/*  78 */     Mode mode = getMode(rule.getMode());
/*  79 */     Rule[] childRules = rule.getUnionRules();
/*     */ 
/*  81 */     if (childRules != null) {
/*  82 */       int i = 0; for (int size = childRules.length; i < size; i++)
/*  83 */         mode.removeRule(childRules[i]);
/*     */     }
/*     */     else {
/*  86 */       mode.removeRule(rule);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Rule getMatchingRule(String modeName, Node node)
/*     */   {
/* 102 */     Mode mode = (Mode)this.modes.get(modeName);
/*     */ 
/* 104 */     if (mode != null) {
/* 105 */       return mode.getMatchingRule(node);
/*     */     }
/* 107 */     System.out.println("Warning: No Mode for mode: " + mode);
/*     */ 
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 114 */     this.modes.clear();
/* 115 */     this.appearenceCount = 0;
/*     */   }
/*     */ 
/*     */   public Action getValueOfAction()
/*     */   {
/* 128 */     return this.valueOfAction;
/*     */   }
/*     */ 
/*     */   public void setValueOfAction(Action valueOfAction)
/*     */   {
/* 139 */     this.valueOfAction = valueOfAction;
/*     */   }
/*     */ 
/*     */   protected Mode createMode()
/*     */   {
/* 152 */     Mode mode = new Mode();
/* 153 */     addDefaultRules(mode);
/*     */ 
/* 155 */     return mode;
/*     */   }
/*     */ 
/*     */   protected void addDefaultRules(Mode mode)
/*     */   {
/* 166 */     Action applyTemplates = new Action() { private final Mode val$mode;
/*     */ 
/* 168 */       public void run(Node node) throws Exception { if ((node instanceof Element))
/* 169 */           this.val$mode.applyTemplates((Element)node);
/* 170 */         else if ((node instanceof Document))
/* 171 */           this.val$mode.applyTemplates((Document)node);
/*     */       }
/*     */     };
/* 176 */     Action valueOf = getValueOfAction();
/*     */ 
/* 178 */     addDefaultRule(mode, NodeTypePattern.ANY_DOCUMENT, applyTemplates);
/* 179 */     addDefaultRule(mode, NodeTypePattern.ANY_ELEMENT, applyTemplates);
/*     */ 
/* 181 */     if (valueOf != null) {
/* 182 */       addDefaultRule(mode, NodeTypePattern.ANY_ATTRIBUTE, valueOf);
/* 183 */       addDefaultRule(mode, NodeTypePattern.ANY_TEXT, valueOf);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void addDefaultRule(Mode mode, Pattern pattern, Action action) {
/* 188 */     Rule rule = createDefaultRule(pattern, action);
/* 189 */     mode.addRule(rule);
/*     */   }
/*     */ 
/*     */   protected Rule createDefaultRule(Pattern pattern, Action action) {
/* 193 */     Rule rule = new Rule(pattern, action);
/* 194 */     rule.setImportPrecedence(-1);
/*     */ 
/* 196 */     return rule;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.RuleManager
 * JD-Core Version:    0.6.2
 */