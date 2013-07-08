/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.Collections;
/*   4:    */import java.util.Map;
/*   5:    */import org.dom4j.Element;
/*   6:    */import org.dom4j.Node;
/*   7:    */
/*  47:    */public class FlyweightProcessingInstruction
/*  48:    */  extends AbstractProcessingInstruction
/*  49:    */{
/*  50:    */  protected String target;
/*  51:    */  protected String text;
/*  52:    */  protected Map values;
/*  53:    */  
/*  54:    */  public FlyweightProcessingInstruction() {}
/*  55:    */  
/*  56:    */  public FlyweightProcessingInstruction(String target, Map values)
/*  57:    */  {
/*  58: 58 */    this.target = target;
/*  59: 59 */    this.values = values;
/*  60: 60 */    this.text = toString(values);
/*  61:    */  }
/*  62:    */  
/*  72:    */  public FlyweightProcessingInstruction(String target, String text)
/*  73:    */  {
/*  74: 74 */    this.target = target;
/*  75: 75 */    this.text = text;
/*  76: 76 */    this.values = parseValues(text);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public String getTarget() {
/*  80: 80 */    return this.target;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void setTarget(String target) {
/*  84: 84 */    throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
/*  85:    */  }
/*  86:    */  
/*  87:    */  public String getText()
/*  88:    */  {
/*  89: 89 */    return this.text;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public String getValue(String name) {
/*  93: 93 */    String answer = (String)this.values.get(name);
/*  94:    */    
/*  95: 95 */    if (answer == null) {
/*  96: 96 */      return "";
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    return answer;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public Map getValues() {
/* 103:103 */    return Collections.unmodifiableMap(this.values);
/* 104:    */  }
/* 105:    */  
/* 106:    */  protected Node createXPathResult(Element parent) {
/* 107:107 */    return new DefaultProcessingInstruction(parent, getTarget(), getText());
/* 108:    */  }
/* 109:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightProcessingInstruction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */