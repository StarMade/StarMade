/*     */ package org.dom4j.rule;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class Mode
/*     */ {
/*  30 */   private RuleSet[] ruleSets = new RuleSet[14];
/*     */   private Map elementNameRuleSets;
/*     */   private Map attributeNameRuleSets;
/*     */ 
/*     */   public void fireRule(Node node)
/*     */     throws Exception
/*     */   {
/*  51 */     if (node != null) {
/*  52 */       Rule rule = getMatchingRule(node);
/*     */ 
/*  54 */       if (rule != null) {
/*  55 */         Action action = rule.getAction();
/*     */ 
/*  57 */         if (action != null)
/*  58 */           action.run(node);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void applyTemplates(Element element) throws Exception
/*     */   {
/*  65 */     int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/*  66 */       Attribute attribute = element.attribute(i);
/*  67 */       fireRule(attribute);
/*     */     }
/*     */ 
/*  70 */     int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/*  71 */       Node node = element.node(i);
/*  72 */       fireRule(node);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void applyTemplates(Document document) throws Exception {
/*  77 */     int i = 0; for (int size = document.nodeCount(); i < size; i++) {
/*  78 */       Node node = document.node(i);
/*  79 */       fireRule(node);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addRule(Rule rule) {
/*  84 */     int matchType = rule.getMatchType();
/*  85 */     String name = rule.getMatchesNodeName();
/*     */ 
/*  87 */     if (name != null) {
/*  88 */       if (matchType == 1) {
/*  89 */         this.elementNameRuleSets = addToNameMap(this.elementNameRuleSets, name, rule);
/*     */       }
/*  91 */       else if (matchType == 2) {
/*  92 */         this.attributeNameRuleSets = addToNameMap(this.attributeNameRuleSets, name, rule);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  97 */     if (matchType >= 14) {
/*  98 */       matchType = 0;
/*     */     }
/*     */ 
/* 101 */     if (matchType == 0)
/*     */     {
/* 103 */       int i = 1; for (int size = this.ruleSets.length; i < size; i++) {
/* 104 */         RuleSet ruleSet = this.ruleSets[i];
/*     */ 
/* 106 */         if (ruleSet != null) {
/* 107 */           ruleSet.addRule(rule);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 112 */     getRuleSet(matchType).addRule(rule);
/*     */   }
/*     */ 
/*     */   public void removeRule(Rule rule) {
/* 116 */     int matchType = rule.getMatchType();
/* 117 */     String name = rule.getMatchesNodeName();
/*     */ 
/* 119 */     if (name != null) {
/* 120 */       if (matchType == 1)
/* 121 */         removeFromNameMap(this.elementNameRuleSets, name, rule);
/* 122 */       else if (matchType == 2) {
/* 123 */         removeFromNameMap(this.attributeNameRuleSets, name, rule);
/*     */       }
/*     */     }
/*     */ 
/* 127 */     if (matchType >= 14) {
/* 128 */       matchType = 0;
/*     */     }
/*     */ 
/* 131 */     getRuleSet(matchType).removeRule(rule);
/*     */ 
/* 133 */     if (matchType != 0)
/* 134 */       getRuleSet(0).removeRule(rule);
/*     */   }
/*     */ 
/*     */   public Rule getMatchingRule(Node node)
/*     */   {
/* 148 */     int matchType = node.getNodeType();
/*     */ 
/* 150 */     if (matchType == 1) {
/* 151 */       if (this.elementNameRuleSets != null) {
/* 152 */         String name = node.getName();
/* 153 */         RuleSet ruleSet = (RuleSet)this.elementNameRuleSets.get(name);
/*     */ 
/* 155 */         if (ruleSet != null) {
/* 156 */           Rule answer = ruleSet.getMatchingRule(node);
/*     */ 
/* 158 */           if (answer != null)
/* 159 */             return answer;
/*     */         }
/*     */       }
/*     */     }
/* 163 */     else if ((matchType == 2) && 
/* 164 */       (this.attributeNameRuleSets != null)) {
/* 165 */       String name = node.getName();
/* 166 */       RuleSet ruleSet = (RuleSet)this.attributeNameRuleSets.get(name);
/*     */ 
/* 168 */       if (ruleSet != null) {
/* 169 */         Rule answer = ruleSet.getMatchingRule(node);
/*     */ 
/* 171 */         if (answer != null) {
/* 172 */           return answer;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 178 */     if ((matchType < 0) || (matchType >= this.ruleSets.length)) {
/* 179 */       matchType = 0;
/*     */     }
/*     */ 
/* 182 */     Rule answer = null;
/* 183 */     RuleSet ruleSet = this.ruleSets[matchType];
/*     */ 
/* 185 */     if (ruleSet != null)
/*     */     {
/* 187 */       answer = ruleSet.getMatchingRule(node);
/*     */     }
/*     */ 
/* 190 */     if ((answer == null) && (matchType != 0))
/*     */     {
/* 192 */       ruleSet = this.ruleSets[0];
/*     */ 
/* 194 */       if (ruleSet != null) {
/* 195 */         answer = ruleSet.getMatchingRule(node);
/*     */       }
/*     */     }
/*     */ 
/* 199 */     return answer;
/*     */   }
/*     */ 
/*     */   protected RuleSet getRuleSet(int matchType)
/*     */   {
/* 212 */     RuleSet ruleSet = this.ruleSets[matchType];
/*     */ 
/* 214 */     if (ruleSet == null) {
/* 215 */       ruleSet = new RuleSet();
/* 216 */       this.ruleSets[matchType] = ruleSet;
/*     */ 
/* 219 */       if (matchType != 0) {
/* 220 */         RuleSet allRules = this.ruleSets[0];
/*     */ 
/* 222 */         if (allRules != null) {
/* 223 */           ruleSet.addAll(allRules);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 228 */     return ruleSet;
/*     */   }
/*     */ 
/*     */   protected Map addToNameMap(Map map, String name, Rule rule)
/*     */   {
/* 244 */     if (map == null) {
/* 245 */       map = new HashMap();
/*     */     }
/*     */ 
/* 248 */     RuleSet ruleSet = (RuleSet)map.get(name);
/*     */ 
/* 250 */     if (ruleSet == null) {
/* 251 */       ruleSet = new RuleSet();
/* 252 */       map.put(name, ruleSet);
/*     */     }
/*     */ 
/* 255 */     ruleSet.addRule(rule);
/*     */ 
/* 257 */     return map;
/*     */   }
/*     */ 
/*     */   protected void removeFromNameMap(Map map, String name, Rule rule) {
/* 261 */     if (map != null) {
/* 262 */       RuleSet ruleSet = (RuleSet)map.get(name);
/*     */ 
/* 264 */       if (ruleSet != null)
/* 265 */         ruleSet.removeRule(rule);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.Mode
 * JD-Core Version:    0.6.2
 */