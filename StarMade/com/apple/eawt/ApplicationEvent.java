package com.apple.eawt;

import java.util.EventObject;

public class ApplicationEvent
  extends EventObject
{
  ApplicationEvent(Object paramObject)
  {
    super(paramObject);
  }
  
  ApplicationEvent(Object paramObject, String paramString)
  {
    super(paramObject);
  }
  
  public boolean isHandled()
  {
    return false;
  }
  
  public void setHandled(boolean paramBoolean) {}
  
  public String getFilename()
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.apple.eawt.ApplicationEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */