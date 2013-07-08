package org.apache.commons.lang3.exception;

import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public abstract interface ExceptionContext
{
  public abstract ExceptionContext addContextValue(String paramString, Object paramObject);
  
  public abstract ExceptionContext setContextValue(String paramString, Object paramObject);
  
  public abstract List<Object> getContextValues(String paramString);
  
  public abstract Object getFirstContextValue(String paramString);
  
  public abstract Set<String> getContextLabels();
  
  public abstract List<Pair<String, Object>> getContextEntries();
  
  public abstract String getFormattedExceptionMessage(String paramString);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.ExceptionContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */