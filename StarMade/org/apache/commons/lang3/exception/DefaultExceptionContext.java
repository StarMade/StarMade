/*   1:    */package org.apache.commons.lang3.exception;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.HashSet;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Set;
/*   9:    */import org.apache.commons.lang3.StringUtils;
/*  10:    */import org.apache.commons.lang3.tuple.ImmutablePair;
/*  11:    */import org.apache.commons.lang3.tuple.Pair;
/*  12:    */
/*  43:    */public class DefaultExceptionContext
/*  44:    */  implements ExceptionContext, Serializable
/*  45:    */{
/*  46:    */  private static final long serialVersionUID = 20110706L;
/*  47: 47 */  private final List<Pair<String, Object>> contextValues = new ArrayList();
/*  48:    */  
/*  51:    */  public DefaultExceptionContext addContextValue(String label, Object value)
/*  52:    */  {
/*  53: 53 */    this.contextValues.add(new ImmutablePair(label, value));
/*  54: 54 */    return this;
/*  55:    */  }
/*  56:    */  
/*  59:    */  public DefaultExceptionContext setContextValue(String label, Object value)
/*  60:    */  {
/*  61: 61 */    for (Iterator<Pair<String, Object>> iter = this.contextValues.iterator(); iter.hasNext();) {
/*  62: 62 */      Pair<String, Object> p = (Pair)iter.next();
/*  63: 63 */      if (StringUtils.equals(label, (CharSequence)p.getKey())) {
/*  64: 64 */        iter.remove();
/*  65:    */      }
/*  66:    */    }
/*  67: 67 */    addContextValue(label, value);
/*  68: 68 */    return this;
/*  69:    */  }
/*  70:    */  
/*  73:    */  public List<Object> getContextValues(String label)
/*  74:    */  {
/*  75: 75 */    List<Object> values = new ArrayList();
/*  76: 76 */    for (Pair<String, Object> pair : this.contextValues) {
/*  77: 77 */      if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
/*  78: 78 */        values.add(pair.getValue());
/*  79:    */      }
/*  80:    */    }
/*  81: 81 */    return values;
/*  82:    */  }
/*  83:    */  
/*  86:    */  public Object getFirstContextValue(String label)
/*  87:    */  {
/*  88: 88 */    for (Pair<String, Object> pair : this.contextValues) {
/*  89: 89 */      if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
/*  90: 90 */        return pair.getValue();
/*  91:    */      }
/*  92:    */    }
/*  93: 93 */    return null;
/*  94:    */  }
/*  95:    */  
/*  98:    */  public Set<String> getContextLabels()
/*  99:    */  {
/* 100:100 */    Set<String> labels = new HashSet();
/* 101:101 */    for (Pair<String, Object> pair : this.contextValues) {
/* 102:102 */      labels.add(pair.getKey());
/* 103:    */    }
/* 104:104 */    return labels;
/* 105:    */  }
/* 106:    */  
/* 109:    */  public List<Pair<String, Object>> getContextEntries()
/* 110:    */  {
/* 111:111 */    return this.contextValues;
/* 112:    */  }
/* 113:    */  
/* 119:    */  public String getFormattedExceptionMessage(String baseMessage)
/* 120:    */  {
/* 121:121 */    StringBuilder buffer = new StringBuilder(256);
/* 122:122 */    if (baseMessage != null) {
/* 123:123 */      buffer.append(baseMessage);
/* 124:    */    }
/* 125:    */    
/* 126:126 */    if (this.contextValues.size() > 0) {
/* 127:127 */      if (buffer.length() > 0) {
/* 128:128 */        buffer.append('\n');
/* 129:    */      }
/* 130:130 */      buffer.append("Exception Context:\n");
/* 131:    */      
/* 132:132 */      int i = 0;
/* 133:133 */      for (Pair<String, Object> pair : this.contextValues) {
/* 134:134 */        buffer.append("\t[");
/* 135:135 */        buffer.append(++i);
/* 136:136 */        buffer.append(':');
/* 137:137 */        buffer.append((String)pair.getKey());
/* 138:138 */        buffer.append("=");
/* 139:139 */        Object value = pair.getValue();
/* 140:140 */        if (value == null) {
/* 141:141 */          buffer.append("null");
/* 142:    */        } else {
/* 143:    */          String valueStr;
/* 144:    */          try {
/* 145:145 */            valueStr = value.toString();
/* 146:    */          } catch (Exception e) {
/* 147:147 */            valueStr = "Exception thrown on toString(): " + ExceptionUtils.getStackTrace(e);
/* 148:    */          }
/* 149:149 */          buffer.append(valueStr);
/* 150:    */        }
/* 151:151 */        buffer.append("]\n");
/* 152:    */      }
/* 153:153 */      buffer.append("---------------------------------");
/* 154:    */    }
/* 155:155 */    return buffer.toString();
/* 156:    */  }
/* 157:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.DefaultExceptionContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */