package org.apache.commons.lang3.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class DefaultExceptionContext
  implements ExceptionContext, Serializable
{
  private static final long serialVersionUID = 20110706L;
  private final List<Pair<String, Object>> contextValues = new ArrayList();
  
  public DefaultExceptionContext addContextValue(String label, Object value)
  {
    this.contextValues.add(new ImmutablePair(label, value));
    return this;
  }
  
  public DefaultExceptionContext setContextValue(String label, Object value)
  {
    Iterator<Pair<String, Object>> iter = this.contextValues.iterator();
    while (iter.hasNext())
    {
      Pair<String, Object> local_p = (Pair)iter.next();
      if (StringUtils.equals(label, (CharSequence)local_p.getKey())) {
        iter.remove();
      }
    }
    addContextValue(label, value);
    return this;
  }
  
  public List<Object> getContextValues(String label)
  {
    List<Object> values = new ArrayList();
    Iterator local_i$ = this.contextValues.iterator();
    while (local_i$.hasNext())
    {
      Pair<String, Object> pair = (Pair)local_i$.next();
      if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
        values.add(pair.getValue());
      }
    }
    return values;
  }
  
  public Object getFirstContextValue(String label)
  {
    Iterator local_i$ = this.contextValues.iterator();
    while (local_i$.hasNext())
    {
      Pair<String, Object> pair = (Pair)local_i$.next();
      if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
        return pair.getValue();
      }
    }
    return null;
  }
  
  public Set<String> getContextLabels()
  {
    Set<String> labels = new HashSet();
    Iterator local_i$ = this.contextValues.iterator();
    while (local_i$.hasNext())
    {
      Pair<String, Object> pair = (Pair)local_i$.next();
      labels.add(pair.getKey());
    }
    return labels;
  }
  
  public List<Pair<String, Object>> getContextEntries()
  {
    return this.contextValues;
  }
  
  public String getFormattedExceptionMessage(String baseMessage)
  {
    StringBuilder buffer = new StringBuilder(256);
    if (baseMessage != null) {
      buffer.append(baseMessage);
    }
    if (this.contextValues.size() > 0)
    {
      if (buffer.length() > 0) {
        buffer.append('\n');
      }
      buffer.append("Exception Context:\n");
      int local_i = 0;
      Iterator local_i$ = this.contextValues.iterator();
      while (local_i$.hasNext())
      {
        Pair<String, Object> pair = (Pair)local_i$.next();
        buffer.append("\t[");
        buffer.append(++local_i);
        buffer.append(':');
        buffer.append((String)pair.getKey());
        buffer.append("=");
        Object value = pair.getValue();
        if (value == null)
        {
          buffer.append("null");
        }
        else
        {
          String valueStr;
          try
          {
            valueStr = value.toString();
          }
          catch (Exception local_e)
          {
            valueStr = "Exception thrown on toString(): " + ExceptionUtils.getStackTrace(local_e);
          }
          buffer.append(valueStr);
        }
        buffer.append("]\n");
      }
      buffer.append("---------------------------------");
    }
    return buffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.exception.DefaultExceptionContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */