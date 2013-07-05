package org.w3c.tidy;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public final class Report
{
  public static final String ACCESS_URL = "http://www.w3.org/WAI/GL";
  public static final String RELEASE_DATE_STRING = readReleaseDate();
  public static final short MISSING_SEMICOLON = 1;
  public static final short MISSING_SEMICOLON_NCR = 2;
  public static final short UNKNOWN_ENTITY = 3;
  public static final short UNESCAPED_AMPERSAND = 4;
  public static final short APOS_UNDEFINED = 5;
  public static final short MISSING_ENDTAG_FOR = 6;
  public static final short MISSING_ENDTAG_BEFORE = 7;
  public static final short DISCARDING_UNEXPECTED = 8;
  public static final short NESTED_EMPHASIS = 9;
  public static final short NON_MATCHING_ENDTAG = 10;
  public static final short TAG_NOT_ALLOWED_IN = 11;
  public static final short MISSING_STARTTAG = 12;
  public static final short UNEXPECTED_ENDTAG = 13;
  public static final short USING_BR_INPLACE_OF = 14;
  public static final short INSERTING_TAG = 15;
  public static final short SUSPECTED_MISSING_QUOTE = 16;
  public static final short MISSING_TITLE_ELEMENT = 17;
  public static final short DUPLICATE_FRAMESET = 18;
  public static final short CANT_BE_NESTED = 19;
  public static final short OBSOLETE_ELEMENT = 20;
  public static final short PROPRIETARY_ELEMENT = 21;
  public static final short UNKNOWN_ELEMENT = 22;
  public static final short TRIM_EMPTY_ELEMENT = 23;
  public static final short COERCE_TO_ENDTAG = 24;
  public static final short ILLEGAL_NESTING = 25;
  public static final short NOFRAMES_CONTENT = 26;
  public static final short CONTENT_AFTER_BODY = 27;
  public static final short INCONSISTENT_VERSION = 28;
  public static final short MALFORMED_COMMENT = 29;
  public static final short BAD_COMMENT_CHARS = 30;
  public static final short BAD_XML_COMMENT = 31;
  public static final short BAD_CDATA_CONTENT = 32;
  public static final short INCONSISTENT_NAMESPACE = 33;
  public static final short DOCTYPE_AFTER_TAGS = 34;
  public static final short MALFORMED_DOCTYPE = 35;
  public static final short UNEXPECTED_END_OF_FILE = 36;
  public static final short DTYPE_NOT_UPPER_CASE = 37;
  public static final short TOO_MANY_ELEMENTS = 38;
  public static final short UNESCAPED_ELEMENT = 39;
  public static final short NESTED_QUOTATION = 40;
  public static final short ELEMENT_NOT_EMPTY = 41;
  public static final short ENCODING_IO_CONFLICT = 42;
  public static final short MIXED_CONTENT_IN_BLOCK = 43;
  public static final short MISSING_DOCTYPE = 44;
  public static final short SPACE_PRECEDING_XMLDECL = 45;
  public static final short TOO_MANY_ELEMENTS_IN = 46;
  public static final short UNEXPECTED_ENDTAG_IN = 47;
  public static final short REPLACING_ELEMENT = 83;
  public static final short REPLACING_UNEX_ELEMENT = 84;
  public static final short COERCE_TO_ENDTAG_WARN = 85;
  public static final short UNKNOWN_ATTRIBUTE = 48;
  public static final short MISSING_ATTRIBUTE = 49;
  public static final short MISSING_ATTR_VALUE = 50;
  public static final short BAD_ATTRIBUTE_VALUE = 51;
  public static final short UNEXPECTED_GT = 52;
  public static final short PROPRIETARY_ATTRIBUTE = 53;
  public static final short PROPRIETARY_ATTR_VALUE = 54;
  public static final short REPEATED_ATTRIBUTE = 55;
  public static final short MISSING_IMAGEMAP = 56;
  public static final short XML_ATTRIBUTE_VALUE = 57;
  public static final short MISSING_QUOTEMARK = 58;
  public static final short UNEXPECTED_QUOTEMARK = 59;
  public static final short ID_NAME_MISMATCH = 60;
  public static final short BACKSLASH_IN_URI = 61;
  public static final short FIXED_BACKSLASH = 62;
  public static final short ILLEGAL_URI_REFERENCE = 63;
  public static final short ESCAPED_ILLEGAL_URI = 64;
  public static final short NEWLINE_IN_URI = 65;
  public static final short ANCHOR_NOT_UNIQUE = 66;
  public static final short ENTITY_IN_ID = 67;
  public static final short JOINING_ATTRIBUTE = 68;
  public static final short UNEXPECTED_EQUALSIGN = 69;
  public static final short ATTR_VALUE_NOT_LCASE = 70;
  public static final short XML_ID_SYNTAX = 71;
  public static final short INVALID_ATTRIBUTE = 72;
  public static final short BAD_ATTRIBUTE_VALUE_REPLACED = 73;
  public static final short INVALID_XML_ID = 74;
  public static final short UNEXPECTED_END_OF_FILE_ATTR = 75;
  public static final short VENDOR_SPECIFIC_CHARS = 76;
  public static final short INVALID_SGML_CHARS = 77;
  public static final short INVALID_UTF8 = 78;
  public static final short INVALID_UTF16 = 79;
  public static final short ENCODING_MISMATCH = 80;
  public static final short INVALID_URI = 81;
  public static final short INVALID_NCR = 82;
  public static final short DOCTYPE_GIVEN_SUMMARY = 110;
  public static final short REPORT_VERSION_SUMMARY = 111;
  public static final short BADACCESS_SUMMARY = 112;
  public static final short BADFORM_SUMMARY = 113;
  public static final short MISSING_IMAGE_ALT = 1;
  public static final short MISSING_LINK_ALT = 2;
  public static final short MISSING_SUMMARY = 4;
  public static final short MISSING_IMAGE_MAP = 8;
  public static final short USING_FRAMES = 16;
  public static final short USING_NOFRAMES = 32;
  public static final short USING_SPACER = 1;
  public static final short USING_LAYER = 2;
  public static final short USING_NOBR = 4;
  public static final short USING_FONT = 8;
  public static final short USING_BODY = 16;
  public static final short WINDOWS_CHARS = 1;
  public static final short NON_ASCII = 2;
  public static final short FOUND_UTF16 = 4;
  public static final short REPLACED_CHAR = 0;
  public static final short DISCARDED_CHAR = 1;
  private static ResourceBundle res;
  private String currentFile;
  private TidyMessageListener listener;

  private static String readReleaseDate()
  {
    Properties localProperties = new Properties();
    try
    {
      InputStream localInputStream = Report.class.getResourceAsStream("/jtidy.properties");
      localProperties.load(localInputStream);
      localInputStream.close();
    }
    catch (Exception localException)
    {
      throw new RuntimeException("Failed to load jtidy.properties", localException);
    }
    return localProperties.getProperty("date");
  }

  protected String getMessage(int paramInt, Lexer paramLexer, String paramString, Object[] paramArrayOfObject, TidyMessage.Level paramLevel)
    throws MissingResourceException
  {
    String str1 = res.getString(paramString);
    String str2;
    if ((paramLexer != null) && (paramLevel != TidyMessage.Level.SUMMARY))
      str2 = getPosition(paramLexer);
    else
      str2 = "";
    String str3;
    if (paramLevel == TidyMessage.Level.ERROR)
      str3 = res.getString("error");
    else if (paramLevel == TidyMessage.Level.WARNING)
      str3 = res.getString("warning");
    else
      str3 = "";
    String str4;
    if (paramArrayOfObject != null)
      str4 = MessageFormat.format(str1, paramArrayOfObject);
    else
      str4 = str1;
    if (this.listener != null)
    {
      TidyMessage localTidyMessage = new TidyMessage(paramInt, paramLexer != null ? paramLexer.lines : 0, paramLexer != null ? paramLexer.columns : 0, paramLevel, str4);
      this.listener.messageReceived(localTidyMessage);
    }
    return str2 + str3 + str4;
  }

  private void printMessage(int paramInt, Lexer paramLexer, String paramString, Object[] paramArrayOfObject, TidyMessage.Level paramLevel)
  {
    String str;
    try
    {
      str = getMessage(paramInt, paramLexer, paramString, paramArrayOfObject, paramLevel);
    }
    catch (MissingResourceException localMissingResourceException)
    {
      paramLexer.errout.println(localMissingResourceException.toString());
      return;
    }
    paramLexer.errout.println(str);
  }

  private void printMessage(PrintWriter paramPrintWriter, String paramString, Object[] paramArrayOfObject, TidyMessage.Level paramLevel)
  {
    String str;
    try
    {
      str = getMessage(-1, null, paramString, paramArrayOfObject, paramLevel);
    }
    catch (MissingResourceException localMissingResourceException)
    {
      paramPrintWriter.println(localMissingResourceException.toString());
      return;
    }
    paramPrintWriter.println(str);
  }

  public void showVersion(PrintWriter paramPrintWriter)
  {
    printMessage(paramPrintWriter, "version_summary", new Object[] { RELEASE_DATE_STRING }, TidyMessage.Level.SUMMARY);
  }

  private String getTagName(Node paramNode)
  {
    if (paramNode != null)
    {
      if (paramNode.type == 5)
        return "<" + paramNode.element + ">";
      if (paramNode.type == 6)
        return "</" + paramNode.element + ">";
      if (paramNode.type == 1)
        return "<!DOCTYPE>";
      if (paramNode.type == 4)
        return "plain text";
      return paramNode.element;
    }
    return "";
  }

  public void unknownOption(String paramString)
  {
    try
    {
      System.err.println(MessageFormat.format(res.getString("unknown_option"), new Object[] { paramString }));
    }
    catch (MissingResourceException localMissingResourceException)
    {
      System.err.println(localMissingResourceException.toString());
    }
  }

  public void badArgument(String paramString1, String paramString2)
  {
    try
    {
      System.err.println(MessageFormat.format(res.getString("bad_argument"), new Object[] { paramString2, paramString1 }));
    }
    catch (MissingResourceException localMissingResourceException)
    {
      System.err.println(localMissingResourceException.toString());
    }
  }

  private String getPosition(Lexer paramLexer)
  {
    try
    {
      if (paramLexer.configuration.emacs)
        return MessageFormat.format(res.getString("emacs_format"), new Object[] { this.currentFile, new Integer(paramLexer.lines), new Integer(paramLexer.columns) }) + " ";
      return MessageFormat.format(res.getString("line_column"), new Object[] { new Integer(paramLexer.lines), new Integer(paramLexer.columns) });
    }
    catch (MissingResourceException localMissingResourceException)
    {
      paramLexer.errout.println(localMissingResourceException.toString());
    }
    return "";
  }

  public void encodingError(Lexer paramLexer, int paramInt1, int paramInt2)
  {
    Lexer tmp1_0 = paramLexer;
    tmp1_0.warnings = ((short)(tmp1_0.warnings + 1));
    if (paramLexer.errors > paramLexer.configuration.showErrors)
      return;
    if (paramLexer.configuration.showWarnings)
    {
      String str = Integer.toHexString(paramInt2);
      if ((paramInt1 & 0xFFFFFFFE) == 80)
      {
        Lexer tmp52_51 = paramLexer;
        tmp52_51.badChars = ((short)(tmp52_51.badChars | 0x50));
        printMessage(paramInt1, paramLexer, "encoding_mismatch", new Object[] { paramLexer.configuration.getInCharEncodingName(), ParsePropertyImpl.CHAR_ENCODING.getFriendlyName(null, new Integer(paramInt2), paramLexer.configuration) }, TidyMessage.Level.WARNING);
      }
      else if ((paramInt1 & 0xFFFFFFFE) == 76)
      {
        Lexer tmp125_124 = paramLexer;
        tmp125_124.badChars = ((short)(tmp125_124.badChars | 0x4C));
        printMessage(paramInt1, paramLexer, "invalid_char", new Object[] { new Integer(paramInt1 & 0x1), str }, TidyMessage.Level.WARNING);
      }
      else if ((paramInt1 & 0xFFFFFFFE) == 77)
      {
        Lexer tmp182_181 = paramLexer;
        tmp182_181.badChars = ((short)(tmp182_181.badChars | 0x4D));
        printMessage(paramInt1, paramLexer, "invalid_char", new Object[] { new Integer(paramInt1 & 0x1), str }, TidyMessage.Level.WARNING);
      }
      else if ((paramInt1 & 0xFFFFFFFE) == 78)
      {
        Lexer tmp239_238 = paramLexer;
        tmp239_238.badChars = ((short)(tmp239_238.badChars | 0x4E));
        printMessage(paramInt1, paramLexer, "invalid_utf8", new Object[] { new Integer(paramInt1 & 0x1), str }, TidyMessage.Level.WARNING);
      }
      else if ((paramInt1 & 0xFFFFFFFE) == 79)
      {
        Lexer tmp296_295 = paramLexer;
        tmp296_295.badChars = ((short)(tmp296_295.badChars | 0x4F));
        printMessage(paramInt1, paramLexer, "invalid_utf16", new Object[] { new Integer(paramInt1 & 0x1), str }, TidyMessage.Level.WARNING);
      }
      else if ((paramInt1 & 0xFFFFFFFE) == 82)
      {
        Lexer tmp353_352 = paramLexer;
        tmp353_352.badChars = ((short)(tmp353_352.badChars | 0x52));
        printMessage(paramInt1, paramLexer, "invalid_ncr", new Object[] { new Integer(paramInt1 & 0x1), str }, TidyMessage.Level.WARNING);
      }
    }
  }

  public void entityError(Lexer paramLexer, short paramShort, String paramString, int paramInt)
  {
    Lexer tmp1_0 = paramLexer;
    tmp1_0.warnings = ((short)(tmp1_0.warnings + 1));
    if (paramLexer.errors > paramLexer.configuration.showErrors)
      return;
    if (paramLexer.configuration.showWarnings)
      switch (paramShort)
      {
      case 1:
        printMessage(paramShort, paramLexer, "missing_semicolon", new Object[] { paramString }, TidyMessage.Level.WARNING);
        break;
      case 2:
        printMessage(paramShort, paramLexer, "missing_semicolon_ncr", new Object[] { paramString }, TidyMessage.Level.WARNING);
        break;
      case 3:
        printMessage(paramShort, paramLexer, "unknown_entity", new Object[] { paramString }, TidyMessage.Level.WARNING);
        break;
      case 4:
        printMessage(paramShort, paramLexer, "unescaped_ampersand", null, TidyMessage.Level.WARNING);
        break;
      case 5:
        printMessage(paramShort, paramLexer, "apos_undefined", null, TidyMessage.Level.WARNING);
        break;
      }
  }

  public void attrError(Lexer paramLexer, Node paramNode, AttVal paramAttVal, short paramShort)
  {
    if (paramShort == 52)
    {
      Lexer tmp8_7 = paramLexer;
      tmp8_7.errors = ((short)(tmp8_7.errors + 1));
    }
    else
    {
      Lexer tmp22_21 = paramLexer;
      tmp22_21.warnings = ((short)(tmp22_21.warnings + 1));
    }
    if (paramLexer.errors > paramLexer.configuration.showErrors)
      return;
    if (paramShort == 52)
      printMessage(paramShort, paramLexer, "unexpected_gt", new Object[] { getTagName(paramNode) }, TidyMessage.Level.ERROR);
    if (!paramLexer.configuration.showWarnings)
      return;
    switch (paramShort)
    {
    case 48:
      printMessage(paramShort, paramLexer, "unknown_attribute", new Object[] { paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 49:
      printMessage(paramShort, paramLexer, "missing_attribute", new Object[] { getTagName(paramNode), paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 50:
      printMessage(paramShort, paramLexer, "missing_attr_value", new Object[] { getTagName(paramNode), paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 56:
      printMessage(paramShort, paramLexer, "missing_imagemap", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      Lexer tmp367_366 = paramLexer;
      tmp367_366.badAccess = ((short)(tmp367_366.badAccess | 0x8));
      break;
    case 51:
      printMessage(paramShort, paramLexer, "bad_attribute_value", new Object[] { getTagName(paramNode), paramAttVal.attribute, paramAttVal.value }, TidyMessage.Level.WARNING);
      break;
    case 71:
      printMessage(paramShort, paramLexer, "xml_id_sintax", new Object[] { getTagName(paramNode), paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 57:
      printMessage(paramShort, paramLexer, "xml_attribute_value", new Object[] { getTagName(paramNode), paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 59:
      printMessage(paramShort, paramLexer, "unexpected_quotemark", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 58:
      printMessage(paramShort, paramLexer, "missing_quotemark", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 55:
      printMessage(paramShort, paramLexer, "repeated_attribute", new Object[] { getTagName(paramNode), paramAttVal.value, paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 54:
      printMessage(paramShort, paramLexer, "proprietary_attr_value", new Object[] { getTagName(paramNode), paramAttVal.value }, TidyMessage.Level.WARNING);
      break;
    case 53:
      printMessage(paramShort, paramLexer, "proprietary_attribute", new Object[] { getTagName(paramNode), paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 36:
      paramLexer.lines = paramLexer.in.getCurline();
      paramLexer.columns = paramLexer.in.getCurcol();
      printMessage(paramShort, paramLexer, "unexpected_end_of_file", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 60:
      printMessage(paramShort, paramLexer, "id_name_mismatch", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 61:
      printMessage(paramShort, paramLexer, "backslash_in_uri", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 62:
      printMessage(paramShort, paramLexer, "fixed_backslash", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 63:
      printMessage(paramShort, paramLexer, "illegal_uri_reference", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 64:
      printMessage(paramShort, paramLexer, "escaped_illegal_uri", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 65:
      printMessage(paramShort, paramLexer, "newline_in_uri", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 66:
      printMessage(paramShort, paramLexer, "anchor_not_unique", new Object[] { getTagName(paramNode), paramAttVal.value }, TidyMessage.Level.WARNING);
      break;
    case 67:
      printMessage(paramShort, paramLexer, "entity_in_id", null, TidyMessage.Level.WARNING);
      break;
    case 68:
      printMessage(paramShort, paramLexer, "joining_attribute", new Object[] { getTagName(paramNode), paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 69:
      printMessage(paramShort, paramLexer, "expected_equalsign", new Object[] { getTagName(paramNode) }, TidyMessage.Level.WARNING);
      break;
    case 70:
      printMessage(paramShort, paramLexer, "attr_value_not_lcase", new Object[] { getTagName(paramNode), paramAttVal.value, paramAttVal.attribute }, TidyMessage.Level.WARNING);
      break;
    case 37:
    case 38:
    case 39:
    case 40:
    case 41:
    case 42:
    case 43:
    case 44:
    case 45:
    case 46:
    case 47:
    case 52:
    }
  }

  public void warning(Lexer paramLexer, Node paramNode1, Node paramNode2, short paramShort)
  {
    TagTable localTagTable = paramLexer.configuration.tt;
    if ((paramShort != 8) || (paramLexer.badForm == 0))
    {
      Lexer tmp24_23 = paramLexer;
      tmp24_23.warnings = ((short)(tmp24_23.warnings + 1));
    }
    if (paramLexer.errors > paramLexer.configuration.showErrors)
      return;
    if (paramLexer.configuration.showWarnings)
      switch (paramShort)
      {
      case 6:
        printMessage(paramShort, paramLexer, "missing_endtag_for", new Object[] { paramNode1.element }, TidyMessage.Level.WARNING);
        break;
      case 7:
        printMessage(paramShort, paramLexer, "missing_endtag_before", new Object[] { paramNode1.element, getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        break;
      case 8:
        if (paramLexer.badForm == 0)
          printMessage(paramShort, paramLexer, "discarding_unexpected", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        break;
      case 9:
        printMessage(paramShort, paramLexer, "nested_emphasis", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.INFO);
        break;
      case 24:
        printMessage(paramShort, paramLexer, "coerce_to_endtag", new Object[] { paramNode1.element }, TidyMessage.Level.INFO);
        break;
      case 10:
        printMessage(paramShort, paramLexer, "non_matching_endtag", new Object[] { getTagName(paramNode2), paramNode1.element }, TidyMessage.Level.WARNING);
        break;
      case 11:
        printMessage(paramShort, paramLexer, "tag_not_allowed_in", new Object[] { getTagName(paramNode2), paramNode1.element }, TidyMessage.Level.WARNING);
        break;
      case 34:
        printMessage(paramShort, paramLexer, "doctype_after_tags", null, TidyMessage.Level.WARNING);
        break;
      case 12:
        printMessage(paramShort, paramLexer, "missing_starttag", new Object[] { paramNode2.element }, TidyMessage.Level.WARNING);
        break;
      case 13:
        if (paramNode1 != null)
          printMessage(paramShort, paramLexer, "unexpected_endtag_in", new Object[] { paramNode2.element, paramNode1.element }, TidyMessage.Level.WARNING);
        else
          printMessage(paramShort, paramLexer, "unexpected_endtag", new Object[] { paramNode2.element }, TidyMessage.Level.WARNING);
        break;
      case 38:
        if (paramNode1 != null)
          printMessage(paramShort, paramLexer, "too_many_elements_in", new Object[] { paramNode2.element, paramNode1.element }, TidyMessage.Level.WARNING);
        else
          printMessage(paramShort, paramLexer, "too_many_elements", new Object[] { paramNode2.element }, TidyMessage.Level.WARNING);
        break;
      case 14:
        printMessage(paramShort, paramLexer, "using_br_inplace_of", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        break;
      case 15:
        printMessage(paramShort, paramLexer, "inserting_tag", new Object[] { paramNode2.element }, TidyMessage.Level.WARNING);
        break;
      case 19:
        printMessage(paramShort, paramLexer, "cant_be_nested", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        break;
      case 21:
        printMessage(paramShort, paramLexer, "proprietary_element", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        if (paramNode2.tag == localTagTable.tagLayer)
        {
          Lexer tmp732_731 = paramLexer;
          tmp732_731.badLayout = ((short)(tmp732_731.badLayout | 0x2));
        }
        else if (paramNode2.tag == localTagTable.tagSpacer)
        {
          Lexer tmp758_757 = paramLexer;
          tmp758_757.badLayout = ((short)(tmp758_757.badLayout | 0x1));
        }
        else if (paramNode2.tag == localTagTable.tagNobr)
        {
          Lexer tmp784_783 = paramLexer;
          tmp784_783.badLayout = ((short)(tmp784_783.badLayout | 0x4));
        }
        break;
      case 20:
        if ((paramNode1.tag != null) && ((paramNode1.tag.model & 0x80000) != 0))
          printMessage(paramShort, paramLexer, "obsolete_element", new Object[] { getTagName(paramNode1), getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        else
          printMessage(paramShort, paramLexer, "replacing_element", new Object[] { getTagName(paramNode1), getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        break;
      case 39:
        printMessage(paramShort, paramLexer, "unescaped_element", new Object[] { getTagName(paramNode1) }, TidyMessage.Level.WARNING);
        break;
      case 23:
        printMessage(paramShort, paramLexer, "trim_empty_element", new Object[] { getTagName(paramNode1) }, TidyMessage.Level.WARNING);
        break;
      case 17:
        printMessage(paramShort, paramLexer, "missing_title_element", null, TidyMessage.Level.WARNING);
        break;
      case 25:
        printMessage(paramShort, paramLexer, "illegal_nesting", new Object[] { getTagName(paramNode1) }, TidyMessage.Level.WARNING);
        break;
      case 26:
        printMessage(paramShort, paramLexer, "noframes_content", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.WARNING);
        break;
      case 28:
        printMessage(paramShort, paramLexer, "inconsistent_version", null, TidyMessage.Level.WARNING);
        break;
      case 35:
        printMessage(paramShort, paramLexer, "malformed_doctype", null, TidyMessage.Level.WARNING);
        break;
      case 27:
        printMessage(paramShort, paramLexer, "content_after_body", null, TidyMessage.Level.WARNING);
        break;
      case 29:
        printMessage(paramShort, paramLexer, "malformed_comment", null, TidyMessage.Level.WARNING);
        break;
      case 30:
        printMessage(paramShort, paramLexer, "bad_comment_chars", null, TidyMessage.Level.WARNING);
        break;
      case 31:
        printMessage(paramShort, paramLexer, "bad_xml_comment", null, TidyMessage.Level.WARNING);
        break;
      case 32:
        printMessage(paramShort, paramLexer, "bad_cdata_content", null, TidyMessage.Level.WARNING);
        break;
      case 33:
        printMessage(paramShort, paramLexer, "inconsistent_namespace", null, TidyMessage.Level.WARNING);
        break;
      case 37:
        printMessage(paramShort, paramLexer, "dtype_not_upper_case", null, TidyMessage.Level.WARNING);
        break;
      case 36:
        paramLexer.lines = paramLexer.in.getCurline();
        paramLexer.columns = paramLexer.in.getCurcol();
        printMessage(paramShort, paramLexer, "unexpected_end_of_file", new Object[] { getTagName(paramNode1) }, TidyMessage.Level.WARNING);
        break;
      case 40:
        printMessage(paramShort, paramLexer, "nested_quotation", null, TidyMessage.Level.WARNING);
        break;
      case 41:
        printMessage(paramShort, paramLexer, "element_not_empty", new Object[] { getTagName(paramNode1) }, TidyMessage.Level.WARNING);
        break;
      case 44:
        printMessage(paramShort, paramLexer, "missing_doctype", null, TidyMessage.Level.WARNING);
        break;
      case 16:
      case 18:
      case 22:
      case 42:
      case 43:
      }
    if ((paramShort == 8) && (paramLexer.badForm != 0))
      printMessage(paramShort, paramLexer, "discarding_unexpected", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.ERROR);
  }

  public void error(Lexer paramLexer, Node paramNode1, Node paramNode2, short paramShort)
  {
    Lexer tmp1_0 = paramLexer;
    tmp1_0.errors = ((short)(tmp1_0.errors + 1));
    if (paramLexer.errors > paramLexer.configuration.showErrors)
      return;
    if (paramShort == 16)
      printMessage(paramShort, paramLexer, "suspected_missing_quote", null, TidyMessage.Level.ERROR);
    else if (paramShort == 18)
      printMessage(paramShort, paramLexer, "duplicate_frameset", null, TidyMessage.Level.ERROR);
    else if (paramShort == 22)
      printMessage(paramShort, paramLexer, "unknown_element", new Object[] { getTagName(paramNode2) }, TidyMessage.Level.ERROR);
    else if (paramShort == 13)
      if (paramNode1 != null)
        printMessage(paramShort, paramLexer, "unexpected_endtag_in", new Object[] { paramNode2.element, paramNode1.element }, TidyMessage.Level.ERROR);
      else
        printMessage(paramShort, paramLexer, "unexpected_endtag", new Object[] { paramNode2.element }, TidyMessage.Level.ERROR);
  }

  public void errorSummary(Lexer paramLexer)
  {
    if (((paramLexer.badAccess & 0x30) != 0) && (((paramLexer.badAccess & 0x10) == 0) || ((paramLexer.badAccess & 0x20) != 0)))
    {
      Lexer tmp31_30 = paramLexer;
      tmp31_30.badAccess = ((short)(tmp31_30.badAccess & 0xFFFFFFCF));
    }
    if (paramLexer.badChars != 0)
    {
      int i;
      if ((paramLexer.badChars & 0x4C) != 0)
      {
        i = 0;
        if ("Cp1252".equals(paramLexer.configuration.getInCharEncodingName()))
          i = 1;
        else if ("MacRoman".equals(paramLexer.configuration.getInCharEncodingName()))
          i = 2;
        printMessage(76, paramLexer, "vendor_specific_chars_summary", new Object[] { new Integer(i) }, TidyMessage.Level.SUMMARY);
      }
      if (((paramLexer.badChars & 0x4D) != 0) || ((paramLexer.badChars & 0x52) != 0))
      {
        i = 0;
        if ("Cp1252".equals(paramLexer.configuration.getInCharEncodingName()))
          i = 1;
        else if ("MacRoman".equals(paramLexer.configuration.getInCharEncodingName()))
          i = 2;
        printMessage(77, paramLexer, "invalid_sgml_chars_summary", new Object[] { new Integer(i) }, TidyMessage.Level.SUMMARY);
      }
      if ((paramLexer.badChars & 0x4E) != 0)
        printMessage(78, paramLexer, "invalid_utf8_summary", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badChars & 0x4F) != 0)
        printMessage(79, paramLexer, "invalid_utf16_summary", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badChars & 0x51) != 0)
        printMessage(81, paramLexer, "invaliduri_summary", null, TidyMessage.Level.SUMMARY);
    }
    if (paramLexer.badForm != 0)
      printMessage(113, paramLexer, "badform_summary", null, TidyMessage.Level.SUMMARY);
    if (paramLexer.badAccess != 0)
    {
      if ((paramLexer.badAccess & 0x4) != 0)
        printMessage(4, paramLexer, "badaccess_missing_summary", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badAccess & 0x1) != 0)
        printMessage(1, paramLexer, "badaccess_missing_image_alt", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badAccess & 0x8) != 0)
        printMessage(8, paramLexer, "badaccess_missing_image_map", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badAccess & 0x2) != 0)
        printMessage(2, paramLexer, "badaccess_missing_link_alt", null, TidyMessage.Level.SUMMARY);
      if (((paramLexer.badAccess & 0x10) != 0) && ((paramLexer.badAccess & 0x20) == 0))
        printMessage(16, paramLexer, "badaccess_frames", null, TidyMessage.Level.SUMMARY);
      printMessage(112, paramLexer, "badaccess_summary", new Object[] { "http://www.w3.org/WAI/GL" }, TidyMessage.Level.SUMMARY);
    }
    if (paramLexer.badLayout != 0)
    {
      if ((paramLexer.badLayout & 0x2) != 0)
        printMessage(2, paramLexer, "badlayout_using_layer", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badLayout & 0x1) != 0)
        printMessage(1, paramLexer, "badlayout_using_spacer", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badLayout & 0x8) != 0)
        printMessage(8, paramLexer, "badlayout_using_font", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badLayout & 0x4) != 0)
        printMessage(4, paramLexer, "badlayout_using_nobr", null, TidyMessage.Level.SUMMARY);
      if ((paramLexer.badLayout & 0x10) != 0)
        printMessage(16, paramLexer, "badlayout_using_body", null, TidyMessage.Level.SUMMARY);
    }
  }

  public void unknownOption(PrintWriter paramPrintWriter, char paramChar)
  {
    printMessage(paramPrintWriter, "unrecognized_option", new Object[] { new String(new char[] { paramChar }) }, TidyMessage.Level.ERROR);
  }

  public void unknownFile(PrintWriter paramPrintWriter, String paramString)
  {
    printMessage(paramPrintWriter, "unknown_file", new Object[] { "Tidy", paramString }, TidyMessage.Level.ERROR);
  }

  public void needsAuthorIntervention(PrintWriter paramPrintWriter)
  {
    printMessage(paramPrintWriter, "needs_author_intervention", null, TidyMessage.Level.SUMMARY);
  }

  public void missingBody(PrintWriter paramPrintWriter)
  {
    printMessage(paramPrintWriter, "missing_body", null, TidyMessage.Level.ERROR);
  }

  public void reportNumberOfSlides(PrintWriter paramPrintWriter, int paramInt)
  {
    printMessage(paramPrintWriter, "slides_found", new Object[] { new Integer(paramInt) }, TidyMessage.Level.SUMMARY);
  }

  public void generalInfo(PrintWriter paramPrintWriter)
  {
    printMessage(paramPrintWriter, "general_info", null, TidyMessage.Level.SUMMARY);
  }

  public void setFilename(String paramString)
  {
    this.currentFile = paramString;
  }

  public void reportVersion(PrintWriter paramPrintWriter, Lexer paramLexer, String paramString, Node paramNode)
  {
    int k = 0;
    String str = paramLexer.htmlVersionName();
    int[] arrayOfInt = new int[1];
    paramLexer.lines = 1;
    paramLexer.columns = 1;
    if (paramNode != null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = paramNode.start; i < paramNode.end; i++)
      {
        int j = paramNode.textarray[i];
        if (j < 0)
        {
          i += PPrint.getUTF8(paramNode.textarray, i, arrayOfInt);
          j = arrayOfInt[0];
        }
        if (j == 34)
          k++;
        else if (k == 1)
          localStringBuffer.append((char)j);
      }
      printMessage(110, paramLexer, "doctype_given", new Object[] { paramString, localStringBuffer }, TidyMessage.Level.SUMMARY);
    }
    printMessage(111, paramLexer, "report_version", new Object[] { paramString, str != null ? str : "HTML proprietary" }, TidyMessage.Level.SUMMARY);
  }

  public void reportNumWarnings(PrintWriter paramPrintWriter, Lexer paramLexer)
  {
    if ((paramLexer.warnings > 0) || (paramLexer.errors > 0))
      printMessage(paramPrintWriter, "num_warnings", new Object[] { new Integer(paramLexer.warnings), new Integer(paramLexer.errors) }, TidyMessage.Level.SUMMARY);
    else
      printMessage(paramPrintWriter, "no_warnings", null, TidyMessage.Level.SUMMARY);
  }

  public void helpText(PrintWriter paramPrintWriter)
  {
    printMessage(paramPrintWriter, "help_text", new Object[] { "Tidy", RELEASE_DATE_STRING }, TidyMessage.Level.SUMMARY);
  }

  public void badTree(PrintWriter paramPrintWriter)
  {
    printMessage(paramPrintWriter, "bad_tree", null, TidyMessage.Level.ERROR);
  }

  public void addMessageListener(TidyMessageListener paramTidyMessageListener)
  {
    this.listener = paramTidyMessageListener;
  }

  static
  {
    try
    {
      res = ResourceBundle.getBundle("org/w3c/tidy/TidyMessages");
    }
    catch (MissingResourceException localMissingResourceException)
    {
      throw new Error(localMissingResourceException.toString());
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.Report
 * JD-Core Version:    0.6.2
 */