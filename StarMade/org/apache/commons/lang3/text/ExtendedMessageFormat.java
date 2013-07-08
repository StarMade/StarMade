package org.apache.commons.lang3.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

public class ExtendedMessageFormat
  extends MessageFormat
{
  private static final long serialVersionUID = -2362048321261811743L;
  private static final int HASH_SEED = 31;
  private static final String DUMMY_PATTERN = "";
  private static final String ESCAPED_QUOTE = "''";
  private static final char START_FMT = ',';
  private static final char END_FE = '}';
  private static final char START_FE = '{';
  private static final char QUOTE = '\'';
  private String toPattern;
  private final Map<String, ? extends FormatFactory> registry;
  
  public ExtendedMessageFormat(String pattern)
  {
    this(pattern, Locale.getDefault());
  }
  
  public ExtendedMessageFormat(String pattern, Locale locale)
  {
    this(pattern, locale, null);
  }
  
  public ExtendedMessageFormat(String pattern, Map<String, ? extends FormatFactory> registry)
  {
    this(pattern, Locale.getDefault(), registry);
  }
  
  public ExtendedMessageFormat(String pattern, Locale locale, Map<String, ? extends FormatFactory> registry)
  {
    super("");
    setLocale(locale);
    this.registry = registry;
    applyPattern(pattern);
  }
  
  public String toPattern()
  {
    return this.toPattern;
  }
  
  public final void applyPattern(String pattern)
  {
    if (this.registry == null)
    {
      super.applyPattern(pattern);
      this.toPattern = super.toPattern();
      return;
    }
    ArrayList<Format> foundFormats = new ArrayList();
    ArrayList<String> foundDescriptions = new ArrayList();
    StringBuilder stripCustom = new StringBuilder(pattern.length());
    ParsePosition pos = new ParsePosition(0);
    char[] local_c = pattern.toCharArray();
    int fmtCount = 0;
    while (pos.getIndex() < pattern.length())
    {
      switch (local_c[pos.getIndex()])
      {
      case '\'': 
        appendQuotedString(pattern, pos, stripCustom, true);
        break;
      case '{': 
        fmtCount++;
        seekNonWs(pattern, pos);
        int start = pos.getIndex();
        int index = readArgumentIndex(pattern, next(pos));
        stripCustom.append('{').append(index);
        seekNonWs(pattern, pos);
        Format format = null;
        String formatDescription = null;
        if (local_c[pos.getIndex()] == ',')
        {
          formatDescription = parseFormatDescription(pattern, next(pos));
          format = getFormat(formatDescription);
          if (format == null) {
            stripCustom.append(',').append(formatDescription);
          }
        }
        foundFormats.add(format);
        foundDescriptions.add(format == null ? null : formatDescription);
        Validate.isTrue(foundFormats.size() == fmtCount);
        Validate.isTrue(foundDescriptions.size() == fmtCount);
        if (local_c[pos.getIndex()] != '}') {
          throw new IllegalArgumentException("Unreadable format element at position " + start);
        }
        break;
      }
      stripCustom.append(local_c[pos.getIndex()]);
      next(pos);
    }
    super.applyPattern(stripCustom.toString());
    this.toPattern = insertFormats(super.toPattern(), foundDescriptions);
    if (containsElements(foundFormats))
    {
      Format[] start = getFormats();
      int index = 0;
      Iterator<Format> format = foundFormats.iterator();
      while (format.hasNext())
      {
        Format formatDescription = (Format)format.next();
        if (formatDescription != null) {
          start[index] = formatDescription;
        }
        index++;
      }
      super.setFormats(start);
    }
  }
  
  public void setFormat(int formatElementIndex, Format newFormat)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setFormatByArgumentIndex(int argumentIndex, Format newFormat)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setFormats(Format[] newFormats)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setFormatsByArgumentIndex(Format[] newFormats)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (ObjectUtils.notEqual(getClass(), obj.getClass())) {
      return false;
    }
    ExtendedMessageFormat rhs = (ExtendedMessageFormat)obj;
    if (ObjectUtils.notEqual(this.toPattern, rhs.toPattern)) {
      return false;
    }
    return !ObjectUtils.notEqual(this.registry, rhs.registry);
  }
  
  public int hashCode()
  {
    int result = super.hashCode();
    result = 31 * result + ObjectUtils.hashCode(this.registry);
    result = 31 * result + ObjectUtils.hashCode(this.toPattern);
    return result;
  }
  
  private Format getFormat(String desc)
  {
    if (this.registry != null)
    {
      String name = desc;
      String args = null;
      int local_i = desc.indexOf(',');
      if (local_i > 0)
      {
        name = desc.substring(0, local_i).trim();
        args = desc.substring(local_i + 1).trim();
      }
      FormatFactory factory = (FormatFactory)this.registry.get(name);
      if (factory != null) {
        return factory.getFormat(name, args, getLocale());
      }
    }
    return null;
  }
  
  private int readArgumentIndex(String pattern, ParsePosition pos)
  {
    int start = pos.getIndex();
    seekNonWs(pattern, pos);
    StringBuffer result = new StringBuffer();
    boolean error = false;
    while ((!error) && (pos.getIndex() < pattern.length()))
    {
      char local_c = pattern.charAt(pos.getIndex());
      if (Character.isWhitespace(local_c))
      {
        seekNonWs(pattern, pos);
        local_c = pattern.charAt(pos.getIndex());
        if ((local_c != ',') && (local_c != '}'))
        {
          error = true;
          break label149;
        }
      }
      if (((local_c == ',') || (local_c == '}')) && (result.length() > 0)) {
        try
        {
          return Integer.parseInt(result.toString());
        }
        catch (NumberFormatException local_e) {}
      }
      error = !Character.isDigit(local_c);
      result.append(local_c);
      label149:
      next(pos);
    }
    if (error) {
      throw new IllegalArgumentException("Invalid format argument index at position " + start + ": " + pattern.substring(start, pos.getIndex()));
    }
    throw new IllegalArgumentException("Unterminated format element at position " + start);
  }
  
  private String parseFormatDescription(String pattern, ParsePosition pos)
  {
    int start = pos.getIndex();
    seekNonWs(pattern, pos);
    int text = pos.getIndex();
    int depth = 1;
    while (pos.getIndex() < pattern.length())
    {
      switch (pattern.charAt(pos.getIndex()))
      {
      case '{': 
        depth++;
        break;
      case '}': 
        depth--;
        if (depth == 0) {
          return pattern.substring(text, pos.getIndex());
        }
        break;
      case '\'': 
        getQuotedString(pattern, pos, false);
      }
      next(pos);
    }
    throw new IllegalArgumentException("Unterminated format element at position " + start);
  }
  
  private String insertFormats(String pattern, ArrayList<String> customPatterns)
  {
    if (!containsElements(customPatterns)) {
      return pattern;
    }
    StringBuilder local_sb = new StringBuilder(pattern.length() * 2);
    ParsePosition pos = new ParsePosition(0);
    int local_fe = -1;
    int depth = 0;
    while (pos.getIndex() < pattern.length())
    {
      char local_c = pattern.charAt(pos.getIndex());
      switch (local_c)
      {
      case '\'': 
        appendQuotedString(pattern, pos, local_sb, false);
        break;
      case '{': 
        depth++;
        if (depth == 1)
        {
          local_fe++;
          local_sb.append('{').append(readArgumentIndex(pattern, next(pos)));
          String customPattern = (String)customPatterns.get(local_fe);
          if (customPattern != null) {
            local_sb.append(',').append(customPattern);
          }
        }
        break;
      case '}': 
        depth--;
      default: 
        local_sb.append(local_c);
        next(pos);
      }
    }
    return local_sb.toString();
  }
  
  private void seekNonWs(String pattern, ParsePosition pos)
  {
    int len = 0;
    char[] buffer = pattern.toCharArray();
    do
    {
      len = StrMatcher.splitMatcher().isMatch(buffer, pos.getIndex());
      pos.setIndex(pos.getIndex() + len);
    } while ((len > 0) && (pos.getIndex() < pattern.length()));
  }
  
  private ParsePosition next(ParsePosition pos)
  {
    pos.setIndex(pos.getIndex() + 1);
    return pos;
  }
  
  private StringBuilder appendQuotedString(String pattern, ParsePosition pos, StringBuilder appendTo, boolean escapingOn)
  {
    int start = pos.getIndex();
    char[] local_c = pattern.toCharArray();
    if ((escapingOn) && (local_c[start] == '\''))
    {
      next(pos);
      return appendTo == null ? null : appendTo.append('\'');
    }
    int lastHold = start;
    for (int local_i = pos.getIndex(); local_i < pattern.length(); local_i++) {
      if ((escapingOn) && (pattern.substring(local_i).startsWith("''")))
      {
        appendTo.append(local_c, lastHold, pos.getIndex() - lastHold).append('\'');
        pos.setIndex(local_i + "''".length());
        lastHold = pos.getIndex();
      }
      else
      {
        switch (local_c[pos.getIndex()])
        {
        case '\'': 
          next(pos);
          return appendTo == null ? null : appendTo.append(local_c, lastHold, pos.getIndex() - lastHold);
        }
        next(pos);
      }
    }
    throw new IllegalArgumentException("Unterminated quoted string at position " + start);
  }
  
  private void getQuotedString(String pattern, ParsePosition pos, boolean escapingOn)
  {
    appendQuotedString(pattern, pos, null, escapingOn);
  }
  
  private boolean containsElements(Collection<?> coll)
  {
    if ((coll == null) || (coll.isEmpty())) {
      return false;
    }
    Iterator local_i$ = coll.iterator();
    while (local_i$.hasNext())
    {
      Object name = local_i$.next();
      if (name != null) {
        return true;
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.text.ExtendedMessageFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */