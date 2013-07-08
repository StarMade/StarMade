package org.dom4j;

public abstract interface ElementPath
{
  public abstract int size();
  
  public abstract Element getElement(int paramInt);
  
  public abstract String getPath();
  
  public abstract Element getCurrent();
  
  public abstract void addHandler(String paramString, ElementHandler paramElementHandler);
  
  public abstract void removeHandler(String paramString);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.ElementPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */