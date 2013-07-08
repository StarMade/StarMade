/*   1:    */package org.dom4j.xpath;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.dom4j.InvalidXPathException;
/*   5:    */import org.dom4j.Node;
/*   6:    */import org.dom4j.XPathException;
/*   7:    */import org.jaxen.Context;
/*   8:    */import org.jaxen.ContextSupport;
/*   9:    */import org.jaxen.JaxenException;
/*  10:    */import org.jaxen.SimpleNamespaceContext;
/*  11:    */import org.jaxen.SimpleVariableContext;
/*  12:    */import org.jaxen.VariableContext;
/*  13:    */import org.jaxen.XPathFunctionContext;
/*  14:    */import org.jaxen.dom4j.DocumentNavigator;
/*  15:    */import org.jaxen.pattern.PatternParser;
/*  16:    */import org.jaxen.saxpath.SAXPathException;
/*  17:    */
/*  36:    */public class XPathPattern
/*  37:    */  implements org.dom4j.rule.Pattern
/*  38:    */{
/*  39:    */  private String text;
/*  40:    */  private org.jaxen.pattern.Pattern pattern;
/*  41:    */  private Context context;
/*  42:    */  
/*  43:    */  public XPathPattern(org.jaxen.pattern.Pattern pattern)
/*  44:    */  {
/*  45: 45 */    this.pattern = pattern;
/*  46: 46 */    this.text = pattern.getText();
/*  47: 47 */    this.context = new Context(getContextSupport());
/*  48:    */  }
/*  49:    */  
/*  50:    */  public XPathPattern(String text) {
/*  51: 51 */    this.text = text;
/*  52: 52 */    this.context = new Context(getContextSupport());
/*  53:    */    try
/*  54:    */    {
/*  55: 55 */      this.pattern = PatternParser.parse(text);
/*  56:    */    } catch (SAXPathException e) {
/*  57: 57 */      throw new InvalidXPathException(text, e.getMessage());
/*  58:    */    } catch (Throwable t) {
/*  59: 59 */      throw new InvalidXPathException(text, t);
/*  60:    */    }
/*  61:    */  }
/*  62:    */  
/*  63:    */  public boolean matches(Node node) {
/*  64:    */    try {
/*  65: 65 */      ArrayList list = new ArrayList(1);
/*  66: 66 */      list.add(node);
/*  67: 67 */      this.context.setNodeSet(list);
/*  68:    */      
/*  69: 69 */      return this.pattern.matches(node, this.context);
/*  70:    */    } catch (JaxenException e) {
/*  71: 71 */      handleJaxenException(e);
/*  72:    */    }
/*  73: 73 */    return false;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public String getText()
/*  77:    */  {
/*  78: 78 */    return this.text;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public double getPriority() {
/*  82: 82 */    return this.pattern.getPriority();
/*  83:    */  }
/*  84:    */  
/*  85:    */  public org.dom4j.rule.Pattern[] getUnionPatterns() {
/*  86: 86 */    org.jaxen.pattern.Pattern[] patterns = this.pattern.getUnionPatterns();
/*  87:    */    
/*  88: 88 */    if (patterns != null) {
/*  89: 89 */      int size = patterns.length;
/*  90: 90 */      XPathPattern[] answer = new XPathPattern[size];
/*  91:    */      
/*  92: 92 */      for (int i = 0; i < size; i++) {
/*  93: 93 */        answer[i] = new XPathPattern(patterns[i]);
/*  94:    */      }
/*  95:    */      
/*  96: 96 */      return answer;
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    return null;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public short getMatchType() {
/* 103:103 */    return this.pattern.getMatchType();
/* 104:    */  }
/* 105:    */  
/* 106:    */  public String getMatchesNodeName() {
/* 107:107 */    return this.pattern.getMatchesNodeName();
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setVariableContext(VariableContext variableContext) {
/* 111:111 */    this.context.getContextSupport().setVariableContext(variableContext);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public String toString() {
/* 115:115 */    return "[XPathPattern: text: " + this.text + " Pattern: " + this.pattern + "]";
/* 116:    */  }
/* 117:    */  
/* 118:    */  protected ContextSupport getContextSupport() {
/* 119:119 */    return new ContextSupport(new SimpleNamespaceContext(), XPathFunctionContext.getInstance(), new SimpleVariableContext(), DocumentNavigator.getInstance());
/* 120:    */  }
/* 121:    */  
/* 123:    */  protected void handleJaxenException(JaxenException exception)
/* 124:    */    throws XPathException
/* 125:    */  {
/* 126:126 */    throw new XPathException(this.text, exception);
/* 127:    */  }
/* 128:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpath.XPathPattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */