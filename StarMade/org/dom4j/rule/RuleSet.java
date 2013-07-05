/*    */ package org.dom4j.rule;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import org.dom4j.Node;
/*    */ 
/*    */ public class RuleSet
/*    */ {
/* 28 */   private ArrayList rules = new ArrayList();
/*    */   private Rule[] ruleArray;
/*    */ 
/*    */   public String toString()
/*    */   {
/* 37 */     return super.toString() + " [RuleSet: " + this.rules + " ]";
/*    */   }
/*    */ 
/*    */   public Rule getMatchingRule(Node node)
/*    */   {
/* 50 */     Rule[] matches = getRuleArray();
/*    */ 
/* 52 */     for (int i = matches.length - 1; i >= 0; i--) {
/* 53 */       Rule rule = matches[i];
/*    */ 
/* 55 */       if (rule.matches(node)) {
/* 56 */         return rule;
/*    */       }
/*    */     }
/*    */ 
/* 60 */     return null;
/*    */   }
/*    */ 
/*    */   public void addRule(Rule rule) {
/* 64 */     this.rules.add(rule);
/* 65 */     this.ruleArray = null;
/*    */   }
/*    */ 
/*    */   public void removeRule(Rule rule) {
/* 69 */     this.rules.remove(rule);
/* 70 */     this.ruleArray = null;
/*    */   }
/*    */ 
/*    */   public void addAll(RuleSet that)
/*    */   {
/* 80 */     this.rules.addAll(that.rules);
/* 81 */     this.ruleArray = null;
/*    */   }
/*    */ 
/*    */   protected Rule[] getRuleArray()
/*    */   {
/* 91 */     if (this.ruleArray == null) {
/* 92 */       Collections.sort(this.rules);
/*    */ 
/* 94 */       int size = this.rules.size();
/* 95 */       this.ruleArray = new Rule[size];
/* 96 */       this.rules.toArray(this.ruleArray);
/*    */     }
/*    */ 
/* 99 */     return this.ruleArray;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.RuleSet
 * JD-Core Version:    0.6.2
 */