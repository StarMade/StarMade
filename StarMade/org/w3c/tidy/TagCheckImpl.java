package org.w3c.tidy;

public final class TagCheckImpl
{
  public static final TagCheck HTML = new CheckHTML();
  public static final TagCheck SCRIPT = new CheckSCRIPT();
  public static final TagCheck TABLE = new CheckTABLE();
  public static final TagCheck CAPTION = new CheckCaption();
  public static final TagCheck IMG = new CheckIMG();
  public static final TagCheck AREA = new CheckAREA();
  public static final TagCheck ANCHOR = new CheckAnchor();
  public static final TagCheck MAP = new CheckMap();
  public static final TagCheck STYLE = new CheckSTYLE();
  public static final TagCheck TABLECELL = new CheckTableCell();
  public static final TagCheck LINK = new CheckLINK();
  public static final TagCheck field_1907 = new CheckHR();
  public static final TagCheck FORM = new CheckForm();
  public static final TagCheck META = new CheckMeta();
  
  public static class CheckLINK
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      AttVal localAttVal1 = paramNode.getAttrByName("rel");
      paramNode.checkAttributes(paramLexer);
      if ((localAttVal1 != null) && (localAttVal1.value != null) && (localAttVal1.value.equals("stylesheet")))
      {
        AttVal localAttVal2 = paramNode.getAttrByName("type");
        if (localAttVal2 == null)
        {
          AttVal localAttVal3 = new AttVal(null, null, 34, "type", "");
          paramLexer.report.attrError(paramLexer, paramNode, localAttVal3, (short)49);
          paramNode.addAttribute("type", "text/css");
        }
      }
    }
  }
  
  public static class CheckTableCell
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      paramNode.checkAttributes(paramLexer);
      if ((paramNode.getAttrByName("width") != null) || (paramNode.getAttrByName("height") != null)) {
        paramLexer.constrainVersion(-5);
      }
    }
  }
  
  public static class CheckMeta
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      AttVal localAttVal1 = paramNode.getAttrByName("content");
      paramNode.checkAttributes(paramLexer);
      if (localAttVal1 == null)
      {
        AttVal localAttVal2 = new AttVal(null, null, 34, "content", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)49);
      }
    }
  }
  
  public static class CheckForm
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      AttVal localAttVal1 = paramNode.getAttrByName("action");
      paramNode.checkAttributes(paramLexer);
      if (localAttVal1 == null)
      {
        AttVal localAttVal2 = new AttVal(null, null, 34, "action", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)49);
      }
    }
  }
  
  public static class CheckSTYLE
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      AttVal localAttVal1 = paramNode.getAttrByName("type");
      paramNode.checkAttributes(paramLexer);
      if (localAttVal1 == null)
      {
        AttVal localAttVal2 = new AttVal(null, null, 34, "type", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)49);
        paramNode.addAttribute("type", "text/css");
      }
    }
  }
  
  public static class CheckMap
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      paramNode.checkAttributes(paramLexer);
      paramLexer.fixId(paramNode);
    }
  }
  
  public static class CheckAnchor
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      paramNode.checkAttributes(paramLexer);
      paramLexer.fixId(paramNode);
    }
  }
  
  public static class CheckAREA
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      int i = 0;
      int j = 0;
      for (AttVal localAttVal1 = paramNode.attributes; localAttVal1 != null; localAttVal1 = localAttVal1.next)
      {
        Attribute localAttribute = localAttVal1.checkAttribute(paramLexer, paramNode);
        if (localAttribute == AttributeTable.attrAlt) {
          i = 1;
        } else if (localAttribute == AttributeTable.attrHref) {
          j = 1;
        }
      }
      AttVal localAttVal2;
      if (i == 0)
      {
        Lexer tmp62_61 = paramLexer;
        tmp62_61.badAccess = ((short)(tmp62_61.badAccess | 0x2));
        localAttVal2 = new AttVal(null, null, 34, "alt", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)49);
      }
      if (j == 0)
      {
        localAttVal2 = new AttVal(null, null, 34, "href", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)49);
      }
    }
  }
  
  public static class CheckIMG
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      int i = 0;
      int j = 0;
      int k = 0;
      int m = 0;
      int n = 0;
      for (AttVal localAttVal1 = paramNode.attributes; localAttVal1 != null; localAttVal1 = localAttVal1.next)
      {
        Attribute localAttribute = localAttVal1.checkAttribute(paramLexer, paramNode);
        if (localAttribute == AttributeTable.attrAlt) {
          i = 1;
        } else if (localAttribute == AttributeTable.attrSrc) {
          j = 1;
        } else if (localAttribute == AttributeTable.attrUsemap) {
          k = 1;
        } else if (localAttribute == AttributeTable.attrIsmap) {
          m = 1;
        } else if (localAttribute == AttributeTable.attrDatafld) {
          n = 1;
        } else if ((localAttribute == AttributeTable.attrWidth) || (localAttribute == AttributeTable.attrHeight)) {
          paramLexer.constrainVersion(-2);
        }
      }
      AttVal localAttVal2;
      if (i == 0)
      {
        Lexer tmp138_137 = paramLexer;
        tmp138_137.badAccess = ((short)(tmp138_137.badAccess | 0x1));
        localAttVal2 = new AttVal(null, null, 34, "alt", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)49);
        if (paramLexer.configuration.altText != null) {
          paramNode.addAttribute("alt", paramLexer.configuration.altText);
        }
      }
      if ((j == 0) && (n == 0))
      {
        localAttVal2 = new AttVal(null, null, 34, "src", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)49);
      }
      if ((m != 0) && (k == 0))
      {
        localAttVal2 = new AttVal(null, null, 34, "ismap", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal2, (short)56);
      }
    }
  }
  
  public static class CheckHR
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      AttVal localAttVal = paramNode.getAttrByName("src");
      paramNode.checkAttributes(paramLexer);
      if (localAttVal != null) {
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal, (short)54);
      }
    }
  }
  
  public static class CheckCaption
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      String str = null;
      paramNode.checkAttributes(paramLexer);
      for (AttVal localAttVal = paramNode.attributes; localAttVal != null; localAttVal = localAttVal.next) {
        if ("align".equalsIgnoreCase(localAttVal.attribute))
        {
          str = localAttVal.value;
          break;
        }
      }
      if (str != null) {
        if (("left".equalsIgnoreCase(str)) || ("right".equalsIgnoreCase(str))) {
          paramLexer.constrainVersion(8);
        } else if (("top".equalsIgnoreCase(str)) || ("bottom".equalsIgnoreCase(str))) {
          paramLexer.constrainVersion(-4);
        } else {
          paramLexer.report.attrError(paramLexer, paramNode, localAttVal, (short)51);
        }
      }
    }
  }
  
  public static class CheckTABLE
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      int i = 0;
      for (AttVal localAttVal = paramNode.attributes; localAttVal != null; localAttVal = localAttVal.next)
      {
        Attribute localAttribute = localAttVal.checkAttribute(paramLexer, paramNode);
        if (localAttribute == AttributeTable.attrSummary) {
          i = 1;
        }
      }
      if ((i == 0) && (paramLexer.doctype != 1) && (paramLexer.doctype != 2))
      {
        Lexer tmp61_60 = paramLexer;
        tmp61_60.badAccess = ((short)(tmp61_60.badAccess | 0x4));
      }
      if (paramLexer.configuration.xmlOut)
      {
        localAttVal = paramNode.getAttrByName("border");
        if ((localAttVal != null) && (localAttVal.value == null)) {
          localAttVal.value = "1";
        }
      }
      if ((localAttVal = paramNode.getAttrByName("height")) != null)
      {
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal, (short)53);
        Lexer tmp129_128 = paramLexer;
        tmp129_128.versions = ((short)(tmp129_128.versions & 0x1C0));
      }
    }
  }
  
  public static class CheckSCRIPT
    implements TagCheck
  {
    public void check(Lexer paramLexer, Node paramNode)
    {
      paramNode.checkAttributes(paramLexer);
      AttVal localAttVal1 = paramNode.getAttrByName("language");
      AttVal localAttVal2 = paramNode.getAttrByName("type");
      if (localAttVal2 == null)
      {
        AttVal localAttVal3 = new AttVal(null, null, 34, "type", "");
        paramLexer.report.attrError(paramLexer, paramNode, localAttVal3, (short)49);
        if (localAttVal1 != null)
        {
          String str = localAttVal1.value;
          if (("javascript".equalsIgnoreCase(str)) || ("jscript".equalsIgnoreCase(str))) {
            paramNode.addAttribute("type", "text/javascript");
          } else if ("vbscript".equalsIgnoreCase(str)) {
            paramNode.addAttribute("type", "text/vbscript");
          }
        }
        else
        {
          paramNode.addAttribute("type", "text/javascript");
        }
      }
    }
  }
  
  public static class CheckHTML
    implements TagCheck
  {
    private static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";
    
    public void check(Lexer paramLexer, Node paramNode)
    {
      AttVal localAttVal2 = paramNode.getAttrByName("xmlns");
      if ((localAttVal2 != null) && ("http://www.w3.org/1999/xhtml".equals(localAttVal2.value)))
      {
        paramLexer.isvoyager = true;
        if (!paramLexer.configuration.htmlOut) {
          paramLexer.configuration.xHTML = true;
        }
        paramLexer.configuration.xmlOut = true;
        paramLexer.configuration.upperCaseTags = false;
        paramLexer.configuration.upperCaseAttrs = false;
      }
      for (AttVal localAttVal1 = paramNode.attributes; localAttVal1 != null; localAttVal1 = localAttVal1.next) {
        localAttVal1.checkAttribute(paramLexer, paramNode);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.TagCheckImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */