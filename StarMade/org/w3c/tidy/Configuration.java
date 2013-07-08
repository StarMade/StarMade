package org.w3c.tidy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Configuration
  implements Serializable
{
  /**
   * @deprecated
   */
  public static final int RAW = 0;
  /**
   * @deprecated
   */
  public static final int ASCII = 1;
  /**
   * @deprecated
   */
  public static final int LATIN1 = 2;
  /**
   * @deprecated
   */
  public static final int UTF8 = 3;
  /**
   * @deprecated
   */
  public static final int ISO2022 = 4;
  /**
   * @deprecated
   */
  public static final int MACROMAN = 5;
  /**
   * @deprecated
   */
  public static final int UTF16LE = 6;
  /**
   * @deprecated
   */
  public static final int UTF16BE = 7;
  /**
   * @deprecated
   */
  public static final int UTF16 = 8;
  /**
   * @deprecated
   */
  public static final int WIN1252 = 9;
  /**
   * @deprecated
   */
  public static final int BIG5 = 10;
  /**
   * @deprecated
   */
  public static final int SHIFTJIS = 11;
  private final String[] ENCODING_NAMES = { "raw", "ASCII", "ISO8859_1", "UTF8", "JIS", "MacRoman", "UnicodeLittle", "UnicodeBig", "Unicode", "Cp1252", "Big5", "SJIS" };
  public static final int DOCTYPE_OMIT = 0;
  public static final int DOCTYPE_AUTO = 1;
  public static final int DOCTYPE_STRICT = 2;
  public static final int DOCTYPE_LOOSE = 3;
  public static final int DOCTYPE_USER = 4;
  public static final int KEEP_LAST = 0;
  public static final int KEEP_FIRST = 1;
  private static final Map OPTIONS = new HashMap();
  private static final long serialVersionUID = -4955155037138560842L;
  protected int spaces = 2;
  protected int wraplen = 68;
  protected int tabsize = 8;
  protected int docTypeMode = 1;
  protected int duplicateAttrs = 0;
  protected String altText;
  /**
   * @deprecated
   */
  protected String slidestyle;
  protected String language;
  protected String docTypeStr;
  protected String errfile;
  protected boolean writeback;
  protected boolean onlyErrors;
  protected boolean showWarnings = true;
  protected boolean quiet;
  protected boolean indentContent;
  protected boolean smartIndent;
  protected boolean hideEndTags;
  protected boolean xmlTags;
  protected boolean xmlOut;
  protected boolean xHTML;
  protected boolean htmlOut;
  protected boolean xmlPi;
  protected boolean upperCaseTags;
  protected boolean upperCaseAttrs;
  protected boolean makeClean;
  protected boolean makeBare;
  protected boolean logicalEmphasis;
  protected boolean dropFontTags;
  protected boolean dropProprietaryAttributes;
  protected boolean dropEmptyParas = true;
  protected boolean fixComments = true;
  protected boolean trimEmpty = true;
  protected boolean breakBeforeBR;
  protected boolean burstSlides;
  protected boolean numEntities;
  protected boolean quoteMarks;
  protected boolean quoteNbsp = true;
  protected boolean quoteAmpersand = true;
  protected boolean wrapAttVals;
  protected boolean wrapScriptlets;
  protected boolean wrapSection = true;
  protected boolean wrapAsp = true;
  protected boolean wrapJste = true;
  protected boolean wrapPhp = true;
  protected boolean fixBackslash = true;
  protected boolean indentAttributes;
  protected boolean xmlPIs;
  protected boolean xmlSpace;
  protected boolean encloseBodyText;
  protected boolean encloseBlockText;
  protected boolean keepFileTimes = true;
  protected boolean word2000;
  protected boolean tidyMark = true;
  protected boolean emacs;
  protected boolean literalAttribs;
  protected boolean bodyOnly;
  protected boolean fixUri = true;
  protected boolean lowerLiterals = true;
  protected boolean replaceColor;
  protected boolean hideComments;
  protected boolean indentCdata;
  protected boolean forceOutput;
  protected int showErrors = 6;
  protected boolean asciiChars = true;
  protected boolean joinClasses;
  protected boolean joinStyles = true;
  protected boolean escapeCdata = true;
  protected boolean ncr = true;
  protected String cssPrefix;
  protected String replacementCharEncoding = "WIN1252";
  protected TagTable tt;
  protected Report report;
  protected int definedTags;
  protected char[] newline = System.getProperty("line.separator").toCharArray();
  private String inCharEncoding = "ISO8859_1";
  private String outCharEncoding = "ASCII";
  protected boolean rawOut;
  private transient Properties properties = new Properties();
  
  protected Configuration(Report paramReport)
  {
    this.report = paramReport;
  }
  
  private static void addConfigOption(Flag paramFlag)
  {
    OPTIONS.put(paramFlag.getName(), paramFlag);
  }
  
  public void addProps(Properties paramProperties)
  {
    Enumeration localEnumeration = paramProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      String str2 = paramProperties.getProperty(str1);
      this.properties.put(str1, str2);
    }
    parseProps();
  }
  
  public void parseFile(String paramString)
  {
    try
    {
      this.properties.load(new FileInputStream(paramString));
    }
    catch (IOException localIOException)
    {
      System.err.println(paramString + " " + localIOException.toString());
      return;
    }
    parseProps();
  }
  
  public static boolean isKnownOption(String paramString)
  {
    return (paramString != null) && (OPTIONS.containsKey(paramString));
  }
  
  private void parseProps()
  {
    Iterator localIterator = this.properties.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      Flag localFlag = (Flag)OPTIONS.get(str1);
      if (localFlag == null)
      {
        this.report.unknownOption(str1);
      }
      else
      {
        String str2 = this.properties.getProperty(str1);
        Object localObject = localFlag.getParser().parse(str2, str1, this);
        if (localFlag.getLocation() != null) {
          try
          {
            localFlag.getLocation().set(this, localObject);
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            throw new RuntimeException("IllegalArgumentException during config initialization for field " + str1 + "with value [" + localObject + "]: " + localIllegalArgumentException.getMessage());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new RuntimeException("IllegalArgumentException during config initialization for field " + str1 + "with value [" + localObject + "]: " + localIllegalAccessException.getMessage());
          }
        }
      }
    }
  }
  
  public void adjust()
  {
    if (this.encloseBlockText) {
      this.encloseBodyText = true;
    }
    if (this.smartIndent) {
      this.indentContent = true;
    }
    if (this.wraplen == 0) {
      this.wraplen = 2147483647;
    }
    if (this.word2000)
    {
      this.definedTags |= 2;
      this.tt.defineTag((short)2, "o:p");
    }
    if (this.xmlTags) {
      this.xHTML = false;
    }
    if (this.xHTML)
    {
      this.xmlOut = true;
      this.upperCaseTags = false;
      this.upperCaseAttrs = false;
    }
    if (this.xmlTags)
    {
      this.xmlOut = true;
      this.xmlPIs = true;
    }
    if ((!"UTF8".equals(getOutCharEncodingName())) && (!"ASCII".equals(getOutCharEncodingName())) && (this.xmlOut)) {
      this.xmlPi = true;
    }
    if (this.xmlOut)
    {
      this.quoteAmpersand = true;
      this.hideEndTags = false;
    }
  }
  
  public void printConfigOptions(Writer paramWriter, boolean paramBoolean)
  {
    String str = "                                                                               ";
    try
    {
      paramWriter.write("\nConfiguration File Settings:\n\n");
      if (paramBoolean) {
        paramWriter.write("Name                        Type       Current Value\n");
      } else {
        paramWriter.write("Name                        Type       Allowable values\n");
      }
      paramWriter.write("=========================== =========  ========================================\n");
      ArrayList localArrayList = new ArrayList(OPTIONS.values());
      Collections.sort(localArrayList);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        Flag localFlag = (Flag)localIterator.next();
        paramWriter.write(localFlag.getName());
        paramWriter.write(str, 0, 28 - localFlag.getName().length());
        paramWriter.write(localFlag.getParser().getType());
        paramWriter.write(str, 0, 11 - localFlag.getParser().getType().length());
        if (paramBoolean)
        {
          Field localField = localFlag.getLocation();
          Object localObject = null;
          if (localField != null) {
            try
            {
              localObject = localField.get(this);
            }
            catch (IllegalArgumentException localIllegalArgumentException)
            {
              throw new RuntimeException("IllegalArgument when reading field " + localField.getName());
            }
            catch (IllegalAccessException localIllegalAccessException)
            {
              throw new RuntimeException("IllegalAccess when reading field " + localField.getName());
            }
          }
          paramWriter.write(localFlag.getParser().getFriendlyName(localFlag.getName(), localObject, this));
        }
        else
        {
          paramWriter.write(localFlag.getParser().getOptionValues());
        }
        paramWriter.write("\n");
      }
      paramWriter.flush();
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException.getMessage());
    }
  }
  
  protected String getInCharEncodingName()
  {
    return this.inCharEncoding;
  }
  
  protected void setInCharEncodingName(String paramString)
  {
    String str = EncodingNameMapper.toJava(paramString);
    if (str != null) {
      this.inCharEncoding = str;
    }
  }
  
  protected String getOutCharEncodingName()
  {
    return this.outCharEncoding;
  }
  
  protected void setOutCharEncodingName(String paramString)
  {
    String str = EncodingNameMapper.toJava(paramString);
    if (str != null) {
      this.outCharEncoding = str;
    }
  }
  
  protected void setInOutEncodingName(String paramString)
  {
    setInCharEncodingName(paramString);
    setOutCharEncodingName(paramString);
  }
  
  /**
   * @deprecated
   */
  protected void setOutCharEncoding(int paramInt)
  {
    setOutCharEncodingName(convertCharEncoding(paramInt));
  }
  
  /**
   * @deprecated
   */
  protected void setInCharEncoding(int paramInt)
  {
    setInCharEncodingName(convertCharEncoding(paramInt));
  }
  
  protected String convertCharEncoding(int paramInt)
  {
    if ((paramInt != 0) && (paramInt < this.ENCODING_NAMES.length)) {
      return this.ENCODING_NAMES[paramInt];
    }
    return null;
  }
  
  static
  {
    addConfigOption(new Flag("indent-spaces", "spaces", ParsePropertyImpl.INT));
    addConfigOption(new Flag("wrap", "wraplen", ParsePropertyImpl.INT));
    addConfigOption(new Flag("show-errors", "showErrors", ParsePropertyImpl.INT));
    addConfigOption(new Flag("tab-size", "tabsize", ParsePropertyImpl.INT));
    addConfigOption(new Flag("wrap-attributes", "wrapAttVals", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("wrap-script-literals", "wrapScriptlets", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("wrap-sections", "wrapSection", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("wrap-asp", "wrapAsp", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("wrap-jste", "wrapJste", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("wrap-php", "wrapPhp", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("literal-attributes", "literalAttribs", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("show-body-only", "bodyOnly", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("fix-uri", "fixUri", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("lower-literals", "lowerLiterals", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("hide-comments", "hideComments", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("indent-cdata", "indentCdata", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("force-output", "forceOutput", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("ascii-chars", "asciiChars", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("join-classes", "joinClasses", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("join-styles", "joinStyles", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("escape-cdata", "escapeCdata", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("replace-color", "replaceColor", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("quiet", "quiet", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("tidy-mark", "tidyMark", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("indent-attributes", "indentAttributes", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("hide-endtags", "hideEndTags", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("input-xml", "xmlTags", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("output-xml", "xmlOut", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("output-html", "htmlOut", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("output-xhtml", "xHTML", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("add-xml-pi", "xmlPi", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("add-xml-decl", "xmlPi", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("assume-xml-procins", "xmlPIs", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("uppercase-tags", "upperCaseTags", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("uppercase-attributes", "upperCaseAttrs", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("bare", "makeBare", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("clean", "makeClean", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("logical-emphasis", "logicalEmphasis", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("word-2000", "word2000", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("drop-empty-paras", "dropEmptyParas", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("drop-font-tags", "dropFontTags", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("drop-proprietary-attributes", "dropProprietaryAttributes", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("enclose-text", "encloseBodyText", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("enclose-block-text", "encloseBlockText", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("add-xml-space", "xmlSpace", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("fix-bad-comments", "fixComments", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("split", "burstSlides", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("break-before-br", "breakBeforeBR", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("numeric-entities", "numEntities", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("quote-marks", "quoteMarks", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("quote-nbsp", "quoteNbsp", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("quote-ampersand", "quoteAmpersand", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("write-back", "writeback", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("keep-time", "keepFileTimes", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("show-warnings", "showWarnings", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("ncr", "ncr", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("fix-backslash", "fixBackslash", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("gnu-emacs", "emacs", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("only-errors", "onlyErrors", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("output-raw", "rawOut", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("trim-empty-elements", "trimEmpty", ParsePropertyImpl.BOOL));
    addConfigOption(new Flag("markup", "onlyErrors", ParsePropertyImpl.INVBOOL));
    addConfigOption(new Flag("char-encoding", null, ParsePropertyImpl.CHAR_ENCODING));
    addConfigOption(new Flag("input-encoding", null, ParsePropertyImpl.CHAR_ENCODING));
    addConfigOption(new Flag("output-encoding", null, ParsePropertyImpl.CHAR_ENCODING));
    addConfigOption(new Flag("error-file", "errfile", ParsePropertyImpl.NAME));
    addConfigOption(new Flag("slide-style", "slidestyle", ParsePropertyImpl.NAME));
    addConfigOption(new Flag("language", "language", ParsePropertyImpl.NAME));
    addConfigOption(new Flag("new-inline-tags", null, ParsePropertyImpl.TAGNAMES));
    addConfigOption(new Flag("new-blocklevel-tags", null, ParsePropertyImpl.TAGNAMES));
    addConfigOption(new Flag("new-empty-tags", null, ParsePropertyImpl.TAGNAMES));
    addConfigOption(new Flag("new-pre-tags", null, ParsePropertyImpl.TAGNAMES));
    addConfigOption(new Flag("doctype", "docTypeStr", ParsePropertyImpl.DOCTYPE));
    addConfigOption(new Flag("repeated-attributes", "duplicateAttrs", ParsePropertyImpl.REPEATED_ATTRIBUTES));
    addConfigOption(new Flag("alt-text", "altText", ParsePropertyImpl.STRING));
    addConfigOption(new Flag("indent", "indentContent", ParsePropertyImpl.INDENT));
    addConfigOption(new Flag("css-prefix", "cssPrefix", ParsePropertyImpl.CSS1SELECTOR));
    addConfigOption(new Flag("newline", null, ParsePropertyImpl.NEWLINE));
  }
  
  static class Flag
    implements Comparable
  {
    private String name;
    private String fieldName;
    private Field location;
    private ParseProperty parser;
    
    Flag(String paramString1, String paramString2, ParseProperty paramParseProperty)
    {
      this.fieldName = paramString2;
      this.name = paramString1;
      this.parser = paramParseProperty;
    }
    
    public Field getLocation()
    {
      if ((this.fieldName != null) && (this.location == null)) {
        try
        {
          this.location = Configuration.class.getDeclaredField(this.fieldName);
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          throw new RuntimeException("NoSuchField exception during config initialization for field " + this.fieldName);
        }
        catch (SecurityException localSecurityException)
        {
          throw new RuntimeException("Security exception during config initialization for field " + this.fieldName + ": " + localSecurityException.getMessage());
        }
      }
      return this.location;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public ParseProperty getParser()
    {
      return this.parser;
    }
    
    public boolean equals(Object paramObject)
    {
      return this.name.equals(((Flag)paramObject).name);
    }
    
    public int hashCode()
    {
      return this.name.hashCode();
    }
    
    public int compareTo(Object paramObject)
    {
      return this.name.compareTo(((Flag)paramObject).name);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.Configuration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */