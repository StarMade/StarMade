/*     */ package org.dom4j.rule;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class Stylesheet
/*     */ {
/*  29 */   private RuleManager ruleManager = new RuleManager();
/*     */   private String modeName;
/*     */ 
/*     */   public void addRule(Rule rule)
/*     */   {
/*  47 */     this.ruleManager.addRule(rule);
/*     */   }
/*     */ 
/*     */   public void removeRule(Rule rule)
/*     */   {
/*  57 */     this.ruleManager.removeRule(rule);
/*     */   }
/*     */ 
/*     */   public void run(Object input)
/*     */     throws Exception
/*     */   {
/*  71 */     run(input, this.modeName);
/*     */   }
/*     */ 
/*     */   public void run(Object input, String mode) throws Exception {
/*  75 */     if ((input instanceof Node))
/*  76 */       run((Node)input, mode);
/*  77 */     else if ((input instanceof List))
/*  78 */       run((List)input, mode);
/*     */   }
/*     */ 
/*     */   public void run(List list) throws Exception
/*     */   {
/*  83 */     run(list, this.modeName);
/*     */   }
/*     */ 
/*     */   public void run(List list, String mode) throws Exception {
/*  87 */     int i = 0; for (int size = list.size(); i < size; i++) {
/*  88 */       Object object = list.get(i);
/*     */ 
/*  90 */       if ((object instanceof Node))
/*  91 */         run((Node)object, mode);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void run(Node node) throws Exception
/*     */   {
/*  97 */     run(node, this.modeName);
/*     */   }
/*     */ 
/*     */   public void run(Node node, String mode) throws Exception {
/* 101 */     Mode mod = this.ruleManager.getMode(mode);
/* 102 */     mod.fireRule(node);
/*     */   }
/*     */ 
/*     */   public void applyTemplates(Object input, org.dom4j.XPath xpath)
/*     */     throws Exception
/*     */   {
/* 117 */     applyTemplates(input, xpath, this.modeName);
/*     */   }
/*     */ 
/*     */   public void applyTemplates(Object input, org.dom4j.XPath xpath, String mode)
/*     */     throws Exception
/*     */   {
/* 135 */     Mode mod = this.ruleManager.getMode(mode);
/*     */ 
/* 137 */     List list = xpath.selectNodes(input);
/* 138 */     Iterator it = list.iterator();
/* 139 */     while (it.hasNext()) {
/* 140 */       Node current = (Node)it.next();
/* 141 */       mod.fireRule(current);
/*     */     }
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public void applyTemplates(Object input, org.jaxen.XPath xpath)
/*     */     throws Exception
/*     */   {
/* 159 */     applyTemplates(input, xpath, this.modeName);
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public void applyTemplates(Object input, org.jaxen.XPath xpath, String mode)
/*     */     throws Exception
/*     */   {
/* 179 */     Mode mod = this.ruleManager.getMode(mode);
/*     */ 
/* 181 */     List list = xpath.selectNodes(input);
/* 182 */     Iterator it = list.iterator();
/* 183 */     while (it.hasNext()) {
/* 184 */       Node current = (Node)it.next();
/* 185 */       mod.fireRule(current);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void applyTemplates(Object input)
/*     */     throws Exception
/*     */   {
/* 202 */     applyTemplates(input, this.modeName);
/*     */   }
/*     */ 
/*     */   public void applyTemplates(Object input, String mode)
/*     */     throws Exception
/*     */   {
/* 220 */     Mode mod = this.ruleManager.getMode(mode);
/*     */ 
/* 222 */     if ((input instanceof Element))
/*     */     {
/* 224 */       Element element = (Element)input;
/* 225 */       int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/* 226 */         Node node = element.node(i);
/* 227 */         mod.fireRule(node);
/*     */       }
/* 229 */     } else if ((input instanceof Document))
/*     */     {
/* 231 */       Document document = (Document)input;
/* 232 */       int i = 0; for (int size = document.nodeCount(); i < size; i++) {
/* 233 */         Node node = document.node(i);
/* 234 */         mod.fireRule(node);
/*     */       }
/* 236 */     } else if ((input instanceof List)) {
/* 237 */       List list = (List)input;
/*     */ 
/* 239 */       int i = 0; for (int size = list.size(); i < size; i++) {
/* 240 */         Object object = list.get(i);
/*     */ 
/* 242 */         if ((object instanceof Element))
/* 243 */           applyTemplates((Element)object, mode);
/* 244 */         else if ((object instanceof Document))
/* 245 */           applyTemplates((Document)object, mode);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 252 */     this.ruleManager.clear();
/*     */   }
/*     */ 
/*     */   public String getModeName()
/*     */   {
/* 264 */     return this.modeName;
/*     */   }
/*     */ 
/*     */   public void setModeName(String modeName)
/*     */   {
/* 274 */     this.modeName = modeName;
/*     */   }
/*     */ 
/*     */   public Action getValueOfAction()
/*     */   {
/* 284 */     return this.ruleManager.getValueOfAction();
/*     */   }
/*     */ 
/*     */   public void setValueOfAction(Action valueOfAction)
/*     */   {
/* 295 */     this.ruleManager.setValueOfAction(valueOfAction);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.Stylesheet
 * JD-Core Version:    0.6.2
 */