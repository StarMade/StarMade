/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.Map;
/*   4:    */import org.dom4j.Element;
/*   5:    */
/*  33:    */public class DefaultProcessingInstruction
/*  34:    */  extends FlyweightProcessingInstruction
/*  35:    */{
/*  36:    */  private Element parent;
/*  37:    */  
/*  38:    */  public DefaultProcessingInstruction(String target, Map values)
/*  39:    */  {
/*  40: 40 */    super(target, values);
/*  41:    */  }
/*  42:    */  
/*  52:    */  public DefaultProcessingInstruction(String target, String values)
/*  53:    */  {
/*  54: 54 */    super(target, values);
/*  55:    */  }
/*  56:    */  
/*  69:    */  public DefaultProcessingInstruction(Element parent, String target, String values)
/*  70:    */  {
/*  71: 71 */    super(target, values);
/*  72: 72 */    this.parent = parent;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void setTarget(String target) {
/*  76: 76 */    this.target = target;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setText(String text) {
/*  80: 80 */    this.text = text;
/*  81: 81 */    this.values = parseValues(text);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public void setValues(Map values) {
/*  85: 85 */    this.values = values;
/*  86: 86 */    this.text = toString(values);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void setValue(String name, String value) {
/*  90: 90 */    this.values.put(name, value);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Element getParent() {
/*  94: 94 */    return this.parent;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public void setParent(Element parent) {
/*  98: 98 */    this.parent = parent;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public boolean supportsParent() {
/* 102:102 */    return true;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public boolean isReadOnly() {
/* 106:106 */    return false;
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultProcessingInstruction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */