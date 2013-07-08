package org.dom4j.tree;

import java.util.Collections;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;

public class FlyweightProcessingInstruction
  extends AbstractProcessingInstruction
{
  protected String target;
  protected String text;
  protected Map values;
  
  public FlyweightProcessingInstruction() {}
  
  public FlyweightProcessingInstruction(String target, Map values)
  {
    this.target = target;
    this.values = values;
    this.text = toString(values);
  }
  
  public FlyweightProcessingInstruction(String target, String text)
  {
    this.target = target;
    this.text = text;
    this.values = parseValues(text);
  }
  
  public String getTarget()
  {
    return this.target;
  }
  
  public void setTarget(String target)
  {
    throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public String getValue(String name)
  {
    String answer = (String)this.values.get(name);
    if (answer == null) {
      return "";
    }
    return answer;
  }
  
  public Map getValues()
  {
    return Collections.unmodifiableMap(this.values);
  }
  
  protected Node createXPathResult(Element parent)
  {
    return new DefaultProcessingInstruction(parent, getTarget(), getText());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.FlyweightProcessingInstruction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */