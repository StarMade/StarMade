package org.w3c.tidy;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public final class ParsePropertyImpl
{
  static final ParseProperty INT = new ParseInt();
  static final ParseProperty BOOL = new ParseBoolean();
  static final ParseProperty INVBOOL = new ParseInvBoolean();
  static final ParseProperty CHAR_ENCODING = new ParseCharEncoding();
  static final ParseProperty NAME = new ParseName();
  static final ParseProperty TAGNAMES = new ParseTagNames();
  static final ParseProperty DOCTYPE = new ParseDocType();
  static final ParseProperty REPEATED_ATTRIBUTES = new ParseRepeatedAttribute();
  static final ParseProperty STRING = new ParseString();
  static final ParseProperty INDENT = new ParseIndent();
  static final ParseProperty CSS1SELECTOR = new ParseCSS1Selector();
  static final ParseProperty NEWLINE = new ParseNewLine();
  
  static class ParseNewLine
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      if ("lf".equalsIgnoreCase(paramString1)) {
        paramConfiguration.newline = new char[] { '\n' };
      } else if ("cr".equalsIgnoreCase(paramString1)) {
        paramConfiguration.newline = new char[] { '\r' };
      } else if ("crlf".equalsIgnoreCase(paramString1)) {
        paramConfiguration.newline = new char[] { '\r', '\n' };
      } else {
        paramConfiguration.report.badArgument(paramString1, paramString2);
      }
      return null;
    }
    
    public String getType()
    {
      return "Enum";
    }
    
    public String getOptionValues()
    {
      return "lf, crlf, cr";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      if (paramConfiguration.newline.length == 1) {
        return paramConfiguration.newline[0] == '\n' ? "lf" : "cr";
      }
      return "crlf";
    }
  }
  
  static class ParseCSS1Selector
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString1);
      String str = null;
      if (localStringTokenizer.countTokens() >= 1) {
        str = localStringTokenizer.nextToken() + "-";
      } else {
        paramConfiguration.report.badArgument(paramString1, paramString2);
      }
      if (!Lexer.isCSS1Selector(paramString1)) {
        paramConfiguration.report.badArgument(paramString1, paramString2);
      }
      return str;
    }
    
    public String getType()
    {
      return "Name";
    }
    
    public String getOptionValues()
    {
      return "CSS1 selector";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      return paramObject == null ? "" : (String)paramObject;
    }
  }
  
  static class ParseIndent
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      boolean bool = paramConfiguration.indentContent;
      if ("yes".equalsIgnoreCase(paramString1))
      {
        bool = true;
        paramConfiguration.smartIndent = false;
      }
      else if ("true".equalsIgnoreCase(paramString1))
      {
        bool = true;
        paramConfiguration.smartIndent = false;
      }
      else if ("no".equalsIgnoreCase(paramString1))
      {
        bool = false;
        paramConfiguration.smartIndent = false;
      }
      else if ("false".equalsIgnoreCase(paramString1))
      {
        bool = false;
        paramConfiguration.smartIndent = false;
      }
      else if ("auto".equalsIgnoreCase(paramString1))
      {
        bool = true;
        paramConfiguration.smartIndent = true;
      }
      else
      {
        paramConfiguration.report.badArgument(paramString1, paramString2);
      }
      return bool ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public String getType()
    {
      return "Indent";
    }
    
    public String getOptionValues()
    {
      return "auto, y/n, yes/no, t/f, true/false, 1/0";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      return paramObject == null ? "" : paramObject.toString();
    }
  }
  
  static class ParseString
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      return paramString1;
    }
    
    public String getType()
    {
      return "String";
    }
    
    public String getOptionValues()
    {
      return "-";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      return paramObject == null ? "" : (String)paramObject;
    }
  }
  
  static class ParseRepeatedAttribute
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      int i;
      if ("keep-first".equalsIgnoreCase(paramString1))
      {
        i = 1;
      }
      else if ("keep-last".equalsIgnoreCase(paramString1))
      {
        i = 0;
      }
      else
      {
        paramConfiguration.report.badArgument(paramString1, paramString2);
        i = -1;
      }
      return new Integer(i);
    }
    
    public String getType()
    {
      return "Enum";
    }
    
    public String getOptionValues()
    {
      return "keep-first, keep-last";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      if (paramObject == null) {
        return "";
      }
      int i = ((Integer)paramObject).intValue();
      String str;
      switch (i)
      {
      case 1: 
        str = "keep-first";
        break;
      case 0: 
        str = "keep-last";
        break;
      default: 
        str = "unknown";
      }
      return str;
    }
  }
  
  static class ParseDocType
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      paramString1 = paramString1.trim();
      if (paramString1.startsWith("\""))
      {
        paramConfiguration.docTypeMode = 4;
        return paramString1;
      }
      String str = "";
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, " \t\n\r,");
      if (localStringTokenizer.hasMoreTokens()) {
        str = localStringTokenizer.nextToken();
      }
      if ("auto".equalsIgnoreCase(str)) {
        paramConfiguration.docTypeMode = 1;
      } else if ("omit".equalsIgnoreCase(str)) {
        paramConfiguration.docTypeMode = 0;
      } else if ("strict".equalsIgnoreCase(str)) {
        paramConfiguration.docTypeMode = 2;
      } else if (("loose".equalsIgnoreCase(str)) || ("transitional".equalsIgnoreCase(str))) {
        paramConfiguration.docTypeMode = 3;
      } else {
        paramConfiguration.report.badArgument(paramString1, paramString2);
      }
      return null;
    }
    
    public String getType()
    {
      return "DocType";
    }
    
    public String getOptionValues()
    {
      return "omit | auto | strict | loose | [fpi]";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      String str;
      switch (paramConfiguration.docTypeMode)
      {
      case 1: 
        str = "auto";
        break;
      case 0: 
        str = "omit";
        break;
      case 2: 
        str = "strict";
        break;
      case 3: 
        str = "transitional";
        break;
      case 4: 
        str = paramConfiguration.docTypeStr;
        break;
      default: 
        str = "unknown";
      }
      return str;
    }
  }
  
  static class ParseTagNames
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      int i = 2;
      if ("new-inline-tags".equals(paramString2)) {
        i = 2;
      } else if ("new-blocklevel-tags".equals(paramString2)) {
        i = 4;
      } else if ("new-empty-tags".equals(paramString2)) {
        i = 1;
      } else if ("new-pre-tags".equals(paramString2)) {
        i = 8;
      }
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, " \t\n\r,");
      while (localStringTokenizer.hasMoreTokens())
      {
        paramConfiguration.definedTags |= i;
        paramConfiguration.field_1881.defineTag(i, localStringTokenizer.nextToken());
      }
      return null;
    }
    
    public String getType()
    {
      return "Tag names";
    }
    
    public String getOptionValues()
    {
      return "tagX, tagY, ...";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      short s;
      if ("new-inline-tags".equals(paramString)) {
        s = 2;
      } else if ("new-blocklevel-tags".equals(paramString)) {
        s = 4;
      } else if ("new-empty-tags".equals(paramString)) {
        s = 1;
      } else if ("new-pre-tags".equals(paramString)) {
        s = 8;
      } else {
        return "";
      }
      List localList = paramConfiguration.field_1881.findAllDefinedTag(s);
      if (localList.isEmpty()) {
        return "";
      }
      StringBuffer localStringBuffer = new StringBuffer();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        localStringBuffer.append(localIterator.next());
        localStringBuffer.append(" ");
      }
      return localStringBuffer.toString();
    }
  }
  
  static class ParseName
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(paramString1);
      String str = null;
      if (localStringTokenizer.countTokens() >= 1) {
        str = localStringTokenizer.nextToken();
      } else {
        paramConfiguration.report.badArgument(paramString1, paramString2);
      }
      return str;
    }
    
    public String getType()
    {
      return "Name";
    }
    
    public String getOptionValues()
    {
      return "-";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      return paramObject == null ? "" : paramObject.toString();
    }
  }
  
  static class ParseCharEncoding
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      if ("raw".equalsIgnoreCase(paramString1))
      {
        paramConfiguration.rawOut = true;
      }
      else if (!TidyUtils.isCharEncodingSupported(paramString1))
      {
        paramConfiguration.report.badArgument(paramString1, paramString2);
      }
      else if ("input-encoding".equalsIgnoreCase(paramString2))
      {
        paramConfiguration.setInCharEncodingName(paramString1);
      }
      else if ("output-encoding".equalsIgnoreCase(paramString2))
      {
        paramConfiguration.setOutCharEncodingName(paramString1);
      }
      else if ("char-encoding".equalsIgnoreCase(paramString2))
      {
        paramConfiguration.setInCharEncodingName(paramString1);
        paramConfiguration.setOutCharEncodingName(paramString1);
      }
      return null;
    }
    
    public String getType()
    {
      return "Encoding";
    }
    
    public String getOptionValues()
    {
      return "Any valid java char encoding name";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      if ("output-encoding".equalsIgnoreCase(paramString)) {
        return paramConfiguration.getOutCharEncodingName();
      }
      return paramConfiguration.getInCharEncodingName();
    }
  }
  
  static class ParseInvBoolean
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      return ((Boolean)ParsePropertyImpl.BOOL.parse(paramString1, paramString2, paramConfiguration)).booleanValue() ? Boolean.FALSE : Boolean.TRUE;
    }
    
    public String getType()
    {
      return "Boolean";
    }
    
    public String getOptionValues()
    {
      return "yes, no, true, false";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      if (paramObject == null) {
        return "";
      }
      return ((Boolean)paramObject).booleanValue() ? "no" : "yes";
    }
  }
  
  static class ParseBoolean
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      Boolean localBoolean = Boolean.TRUE;
      if ((paramString1 != null) && (paramString1.length() > 0))
      {
        int i = paramString1.charAt(0);
        if ((i == 116) || (i == 84) || (i == 89) || (i == 121) || (i == 49)) {
          localBoolean = Boolean.TRUE;
        } else if ((i == 102) || (i == 70) || (i == 78) || (i == 110) || (i == 48)) {
          localBoolean = Boolean.FALSE;
        } else {
          paramConfiguration.report.badArgument(paramString1, paramString2);
        }
      }
      return localBoolean;
    }
    
    public String getType()
    {
      return "Boolean";
    }
    
    public String getOptionValues()
    {
      return "y/n, yes/no, t/f, true/false, 1/0";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      if (paramObject == null) {
        return "";
      }
      return ((Boolean)paramObject).booleanValue() ? "yes" : "no";
    }
  }
  
  static class ParseInt
    implements ParseProperty
  {
    public Object parse(String paramString1, String paramString2, Configuration paramConfiguration)
    {
      int i = 0;
      try
      {
        i = Integer.parseInt(paramString1);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        paramConfiguration.report.badArgument(paramString1, paramString2);
        i = -1;
      }
      return new Integer(i);
    }
    
    public String getType()
    {
      return "Integer";
    }
    
    public String getOptionValues()
    {
      return "0, 1, 2, ...";
    }
    
    public String getFriendlyName(String paramString, Object paramObject, Configuration paramConfiguration)
    {
      return paramObject == null ? "" : paramObject.toString();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.ParsePropertyImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */