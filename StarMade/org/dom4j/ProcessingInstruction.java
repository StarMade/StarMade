package org.dom4j;

import java.util.Map;

public abstract interface ProcessingInstruction extends Node
{
  public abstract String getTarget();

  public abstract void setTarget(String paramString);

  public abstract String getText();

  public abstract String getValue(String paramString);

  public abstract Map getValues();

  public abstract void setValue(String paramString1, String paramString2);

  public abstract void setValues(Map paramMap);

  public abstract boolean removeValue(String paramString);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.ProcessingInstruction
 * JD-Core Version:    0.6.2
 */