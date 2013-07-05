package org.w3c.tidy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class AttrCheckImpl
{
  public static final AttrCheck URL = new CheckUrl();
  public static final AttrCheck SCRIPT = new CheckScript();
  public static final AttrCheck NAME = new CheckName();
  public static final AttrCheck ID = new CheckId();
  public static final AttrCheck ALIGN = new CheckAlign();
  public static final AttrCheck VALIGN = new CheckValign();
  public static final AttrCheck BOOL = new CheckBool();
  public static final AttrCheck LENGTH = new CheckLength();
  public static final AttrCheck TARGET = new CheckTarget();
  public static final AttrCheck FSUBMIT = new CheckFsubmit();
  public static final AttrCheck CLEAR = new CheckClear();
  public static final AttrCheck SHAPE = new CheckShape();
  public static final AttrCheck NUMBER = new CheckNumber();
  public static final AttrCheck SCOPE = new CheckScope();
  public static final AttrCheck COLOR = new CheckColor();
  public static final AttrCheck VTYPE = new CheckVType();
  public static final AttrCheck SCROLL = new CheckScroll();
  public static final AttrCheck TEXTDIR = new CheckTextDir();
  public static final AttrCheck LANG = new CheckLang();
  public static final AttrCheck TEXT = null;
  public static final AttrCheck CHARSET = null;
  public static final AttrCheck TYPE = null;
  public static final AttrCheck CHARACTER = null;
  public static final AttrCheck URLS = null;
  public static final AttrCheck COLS = null;
  public static final AttrCheck COORDS = null;
  public static final AttrCheck DATE = null;
  public static final AttrCheck IDREF = null;
  public static final AttrCheck TFRAME = null;
  public static final AttrCheck FBORDER = null;
  public static final AttrCheck MEDIA = null;
  public static final AttrCheck LINKTYPES = null;
  public static final AttrCheck TRULES = null;

  public static class CheckLang
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if ("lang".equals(paramAttVal.attribute))
        paramLexer.constrainVersion(-1025);
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
    }
  }

  public static class CheckTextDir
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "rtl", "ltr" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckScroll
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "no", "yes", "auto" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckVType
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "data", "object", "ref" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckColor
    implements AttrCheck
  {
    private static final Map COLORS = new HashMap();

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      int i = 1;
      int j = 0;
      int k = 0;
      if ((paramAttVal.value == null) || (paramAttVal.value.length() == 0))
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      String str = paramAttVal.value;
      Iterator localIterator = COLORS.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (str.charAt(0) == '#')
        {
          if (str.length() != 7)
          {
            paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
            j = 1;
            break;
          }
          if (str.equalsIgnoreCase((String)localEntry.getValue()))
          {
            if (paramLexer.configuration.replaceColor)
              paramAttVal.value = ((String)localEntry.getKey());
            k = 1;
            break;
          }
        }
        else if (TidyUtils.isLetter(str.charAt(0)))
        {
          if (str.equalsIgnoreCase((String)localEntry.getKey()))
          {
            if (paramLexer.configuration.replaceColor)
              paramAttVal.value = ((String)localEntry.getKey());
            k = 1;
            break;
          }
        }
        else
        {
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
          j = 1;
          break;
        }
      }
      if ((k == 0) && (j == 0))
        if (str.charAt(0) == '#')
        {
          for (int m = 1; m < 7; m++)
            if ((!TidyUtils.isDigit(str.charAt(m))) && ("abcdef".indexOf(Character.toLowerCase(str.charAt(m))) == -1))
            {
              paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
              j = 1;
              break;
            }
          if ((j == 0) && (i != 0))
            for (m = 1; m < 7; m++)
              paramAttVal.value = str.toUpperCase();
        }
        else
        {
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
          j = 1;
        }
    }

    static
    {
      COLORS.put("black", "#000000");
      COLORS.put("green", "#008000");
      COLORS.put("silver", "#C0C0C0");
      COLORS.put("lime", "#00FF00");
      COLORS.put("gray", "#808080");
      COLORS.put("olive", "#808000");
      COLORS.put("white", "#FFFFFF");
      COLORS.put("yellow", "#FFFF00");
      COLORS.put("maroon", "#800000");
      COLORS.put("navy", "#000080");
      COLORS.put("red", "#FF0000");
      COLORS.put("blue", "#0000FF");
      COLORS.put("purple", "#800080");
      COLORS.put("teal", "#008080");
      COLORS.put("fuchsia", "#FF00FF");
      COLORS.put("aqua", "#00FFFF");
    }
  }

  public static class CheckName
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      if (paramLexer.configuration.tt.isAnchorElement(paramNode))
      {
        paramLexer.constrainVersion(-1025);
        Node localNode;
        if (((localNode = paramLexer.configuration.tt.getNodeByAnchor(paramAttVal.value)) != null) && (localNode != paramNode))
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)66);
        else
          paramLexer.configuration.tt.anchorList = paramLexer.configuration.tt.addAnchor(paramAttVal.value, paramNode);
      }
    }
  }

  public static class CheckId
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if ((paramAttVal.value == null) || (paramAttVal.value.length() == 0))
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      String str = paramAttVal.value;
      char c = str.charAt(0);
      if ((str.length() == 0) || (!Character.isLetter(str.charAt(0))))
      {
        if ((paramLexer.isvoyager) && ((TidyUtils.isXMLLetter(c)) || (c == '_') || (c == ':')))
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)71);
        else
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
      }
      else
        for (int i = 1; i < str.length(); i++)
        {
          c = str.charAt(i);
          if (!TidyUtils.isNamechar(c))
          {
            if ((paramLexer.isvoyager) && (TidyUtils.isXMLNamechar(c)))
            {
              paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)71);
              break;
            }
            paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
            break;
          }
        }
      Node localNode;
      if (((localNode = paramLexer.configuration.tt.getNodeByAnchor(paramAttVal.value)) != null) && (localNode != paramNode))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)66);
      else
        paramLexer.configuration.tt.anchorList = paramLexer.configuration.tt.addAnchor(paramAttVal.value, paramNode);
    }
  }

  public static class CheckNumber
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      if ((("cols".equalsIgnoreCase(paramAttVal.attribute)) || ("rows".equalsIgnoreCase(paramAttVal.attribute))) && (paramNode.tag == paramLexer.configuration.tt.tagFrameset))
        return;
      String str = paramAttVal.value;
      int i = 0;
      if ((paramNode.tag == paramLexer.configuration.tt.tagFont) && ((str.startsWith("+")) || (str.startsWith("-"))))
        i++;
      while (i < str.length())
      {
        char c = str.charAt(i);
        if (!Character.isDigit(c))
        {
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
          break;
        }
        i++;
      }
    }
  }

  public static class CheckScope
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "row", "rowgroup", "col", "colgroup" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckShape
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "rect", "default", "circle", "poly" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckClear
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "none", "left", "right", "all" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        paramAttVal.value = VALID_VALUES[0];
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckFsubmit
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "get", "post" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckTarget
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "_blank", "_self", "_parent", "_top" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      paramLexer.constrainVersion(-5);
      if ((paramAttVal.value == null) || (paramAttVal.value.length() == 0))
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      String str = paramAttVal.value;
      if (Character.isLetter(str.charAt(0)))
        return;
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, str))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckLength
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      if (("width".equalsIgnoreCase(paramAttVal.attribute)) && ((paramNode.tag == paramLexer.configuration.tt.tagCol) || (paramNode.tag == paramLexer.configuration.tt.tagColgroup)))
        return;
      String str = paramAttVal.value;
      if ((str.length() == 0) || ((!Character.isDigit(str.charAt(0))) && ('%' != str.charAt(0))))
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
      }
      else
      {
        TagTable localTagTable = paramLexer.configuration.tt;
        for (int i = 1; i < str.length(); i++)
          if (((!Character.isDigit(str.charAt(i))) && ((paramNode.tag == localTagTable.tagTd) || (paramNode.tag == localTagTable.tagTh))) || ((!Character.isDigit(str.charAt(i))) && (str.charAt(i) != '%')))
          {
            paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
            break;
          }
      }
    }
  }

  public static class CheckBool
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
        return;
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
    }
  }

  public static class CheckValign
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "top", "middle", "bottom", "baseline" };
    private static final String[] VALID_VALUES_IMG = { "left", "right" };
    private static final String[] VALID_VALUES_PROPRIETARY = { "texttop", "absmiddle", "absbottom", "textbottom" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      String str = paramAttVal.value;
      if (TidyUtils.isInValuesIgnoreCase(VALID_VALUES, str))
        return;
      if (TidyUtils.isInValuesIgnoreCase(VALID_VALUES_IMG, str))
      {
        if ((paramNode.tag == null) || ((paramNode.tag.model & 0x10000) == 0))
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
      }
      else if (TidyUtils.isInValuesIgnoreCase(VALID_VALUES_PROPRIETARY, str))
      {
        paramLexer.constrainVersion(448);
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)54);
      }
      else
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
      }
    }
  }

  public static class CheckAlign
    implements AttrCheck
  {
    private static final String[] VALID_VALUES = { "left", "center", "right", "justify" };

    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      if ((paramNode.tag != null) && ((paramNode.tag.model & 0x10000) != 0))
      {
        AttrCheckImpl.VALIGN.check(paramLexer, paramNode, paramAttVal);
        return;
      }
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      paramAttVal.checkLowerCaseAttrValue(paramLexer, paramNode);
      if (!TidyUtils.isInValuesIgnoreCase(VALID_VALUES, paramAttVal.value))
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)51);
    }
  }

  public static class CheckScript
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
    }
  }

  public static class CheckUrl
    implements AttrCheck
  {
    public void check(Lexer paramLexer, Node paramNode, AttVal paramAttVal)
    {
      int i = 0;
      int j = 0;
      int k = 0;
      if (paramAttVal.value == null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)50);
        return;
      }
      String str = paramAttVal.value;
      char c;
      for (k = 0; k < str.length(); k++)
      {
        c = str.charAt(k);
        if (c == '\\')
          j = 1;
        else if ((c > '~') || (c <= ' ') || (c == '<') || (c == '>'))
          i = 1;
      }
      if ((paramLexer.configuration.fixBackslash) && (j != 0))
      {
        paramAttVal.value = paramAttVal.value.replace('\\', '/');
        str = paramAttVal.value;
      }
      if ((paramLexer.configuration.fixUri) && (i != 0))
      {
        StringBuffer localStringBuffer = new StringBuffer();
        for (k = 0; k < str.length(); k++)
        {
          c = str.charAt(k);
          if ((c > '~') || (c <= ' ') || (c == '<') || (c == '>'))
          {
            localStringBuffer.append('%');
            localStringBuffer.append(Integer.toHexString(c).toUpperCase());
          }
          else
          {
            localStringBuffer.append(c);
          }
        }
        paramAttVal.value = localStringBuffer.toString();
      }
      if (j != 0)
        if (paramLexer.configuration.fixBackslash)
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)62);
        else
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)61);
      if (i != 0)
      {
        if (paramLexer.configuration.fixUri)
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)64);
        else
          paramLexer.report.attrError(paramLexer, paramNode, paramAttVal, (short)63);
        Lexer tmp350_349 = paramLexer;
        tmp350_349.badChars = ((short)(tmp350_349.badChars | 0x51));
      }
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.AttrCheckImpl
 * JD-Core Version:    0.6.2
 */