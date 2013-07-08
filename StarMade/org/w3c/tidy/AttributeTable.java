package org.w3c.tidy;

import java.util.Hashtable;
import java.util.Map;

public class AttributeTable
{
  protected static Attribute attrHref;
  protected static Attribute attrSrc;
  protected static Attribute attrId;
  protected static Attribute attrName;
  protected static Attribute attrSummary;
  protected static Attribute attrAlt;
  protected static Attribute attrLongdesc;
  protected static Attribute attrUsemap;
  protected static Attribute attrIsmap;
  protected static Attribute attrLanguage;
  protected static Attribute attrType;
  protected static Attribute attrTitle;
  protected static Attribute attrXmlns;
  protected static Attribute attrValue;
  protected static Attribute attrContent;
  protected static Attribute attrDatafld;
  protected static Attribute attrWidth;
  protected static Attribute attrHeight;
  private static AttributeTable defaultAttributeTable;
  private static final Attribute[] ATTRS = { new Attribute("abbr", 28, AttrCheckImpl.TEXT), new Attribute("accept-charset", 28, AttrCheckImpl.CHARSET), new Attribute("accept", 3103, AttrCheckImpl.TYPE), new Attribute("accesskey", 28, AttrCheckImpl.CHARACTER), new Attribute("action", 3103, AttrCheckImpl.URL), new Attribute("add_date", 64, AttrCheckImpl.TEXT), new Attribute("align", 3103, AttrCheckImpl.ALIGN), new Attribute("alink", 26, AttrCheckImpl.COLOR), new Attribute("alt", 3103, AttrCheckImpl.TEXT), new Attribute("archive", 28, AttrCheckImpl.URLS), new Attribute("axis", 28, AttrCheckImpl.TEXT), new Attribute("background", 26, AttrCheckImpl.URL), new Attribute("bgcolor", 26, AttrCheckImpl.COLOR), new Attribute("bgproperties", 448, AttrCheckImpl.TEXT), new Attribute("border", 3103, AttrCheckImpl.BOOL), new Attribute("bordercolor", 128, AttrCheckImpl.COLOR), new Attribute("bottommargin", 128, AttrCheckImpl.NUMBER), new Attribute("cellpadding", 30, AttrCheckImpl.LENGTH), new Attribute("cellspacing", 30, AttrCheckImpl.LENGTH), new Attribute("char", 28, AttrCheckImpl.CHARACTER), new Attribute("charoff", 28, AttrCheckImpl.LENGTH), new Attribute("charset", 28, AttrCheckImpl.CHARSET), new Attribute("checked", 3103, AttrCheckImpl.BOOL), new Attribute("cite", 28, AttrCheckImpl.URL), new Attribute("class", 28, AttrCheckImpl.TEXT), new Attribute("classid", 28, AttrCheckImpl.URL), new Attribute("clear", 26, AttrCheckImpl.CLEAR), new Attribute("code", 26, AttrCheckImpl.TEXT), new Attribute("codebase", 28, AttrCheckImpl.URL), new Attribute("codetype", 28, AttrCheckImpl.TYPE), new Attribute("color", 26, AttrCheckImpl.COLOR), new Attribute("cols", 24, AttrCheckImpl.COLS), new Attribute("colspan", 30, AttrCheckImpl.NUMBER), new Attribute("compact", 3103, AttrCheckImpl.BOOL), new Attribute("content", 3103, AttrCheckImpl.TEXT), new Attribute("coords", 30, AttrCheckImpl.COORDS), new Attribute("data", 28, AttrCheckImpl.URL), new Attribute("datafld", 128, AttrCheckImpl.TEXT), new Attribute("dataformatas", 128, AttrCheckImpl.TEXT), new Attribute("datapagesize", 128, AttrCheckImpl.NUMBER), new Attribute("datasrc", 128, AttrCheckImpl.URL), new Attribute("datetime", 28, AttrCheckImpl.DATE), new Attribute("declare", 28, AttrCheckImpl.BOOL), new Attribute("defer", 28, AttrCheckImpl.BOOL), new Attribute("dir", 28, AttrCheckImpl.TEXTDIR), new Attribute("disabled", 28, AttrCheckImpl.BOOL), new Attribute("enctype", 3103, AttrCheckImpl.TYPE), new Attribute("face", 26, AttrCheckImpl.TEXT), new Attribute("for", 28, AttrCheckImpl.IDREF), new Attribute("frame", 28, AttrCheckImpl.TFRAME), new Attribute("frameborder", 24, AttrCheckImpl.FBORDER), new Attribute("framespacing", 448, AttrCheckImpl.NUMBER), new Attribute("gridx", 448, AttrCheckImpl.NUMBER), new Attribute("gridy", 448, AttrCheckImpl.NUMBER), new Attribute("headers", 28, AttrCheckImpl.IDREF), new Attribute("height", 3103, AttrCheckImpl.LENGTH), new Attribute("href", 3103, AttrCheckImpl.URL), new Attribute("hreflang", 28, AttrCheckImpl.LANG), new Attribute("hspace", 3103, AttrCheckImpl.NUMBER), new Attribute("http-equiv", 3103, AttrCheckImpl.TEXT), new Attribute("id", 28, AttrCheckImpl.field_1759), new Attribute("ismap", 3103, AttrCheckImpl.BOOL), new Attribute("label", 28, AttrCheckImpl.TEXT), new Attribute("lang", 28, AttrCheckImpl.LANG), new Attribute("language", 26, AttrCheckImpl.TEXT), new Attribute("last_modified", 64, AttrCheckImpl.TEXT), new Attribute("last_visit", 64, AttrCheckImpl.TEXT), new Attribute("leftmargin", 128, AttrCheckImpl.NUMBER), new Attribute("link", 26, AttrCheckImpl.COLOR), new Attribute("longdesc", 28, AttrCheckImpl.URL), new Attribute("lowsrc", 448, AttrCheckImpl.URL), new Attribute("marginheight", 24, AttrCheckImpl.NUMBER), new Attribute("marginwidth", 24, AttrCheckImpl.NUMBER), new Attribute("maxlength", 3103, AttrCheckImpl.NUMBER), new Attribute("media", 28, AttrCheckImpl.MEDIA), new Attribute("method", 3103, AttrCheckImpl.FSUBMIT), new Attribute("multiple", 3103, AttrCheckImpl.BOOL), new Attribute("name", 3103, AttrCheckImpl.NAME), new Attribute("nohref", 30, AttrCheckImpl.BOOL), new Attribute("noresize", 16, AttrCheckImpl.BOOL), new Attribute("noshade", 26, AttrCheckImpl.BOOL), new Attribute("nowrap", 26, AttrCheckImpl.BOOL), new Attribute("object", 8, AttrCheckImpl.TEXT), new Attribute("onblur", 1052, AttrCheckImpl.SCRIPT), new Attribute("onchange", 1052, AttrCheckImpl.SCRIPT), new Attribute("onclick", 1052, AttrCheckImpl.SCRIPT), new Attribute("ondblclick", 1052, AttrCheckImpl.SCRIPT), new Attribute("onkeydown", 1052, AttrCheckImpl.SCRIPT), new Attribute("onkeypress", 1052, AttrCheckImpl.SCRIPT), new Attribute("onkeyup", 1052, AttrCheckImpl.SCRIPT), new Attribute("onload", 1052, AttrCheckImpl.SCRIPT), new Attribute("onmousedown", 1052, AttrCheckImpl.SCRIPT), new Attribute("onmousemove", 1052, AttrCheckImpl.SCRIPT), new Attribute("onmouseout", 1052, AttrCheckImpl.SCRIPT), new Attribute("onmouseover", 1052, AttrCheckImpl.SCRIPT), new Attribute("onmouseup", 1052, AttrCheckImpl.SCRIPT), new Attribute("onsubmit", 1052, AttrCheckImpl.SCRIPT), new Attribute("onreset", 1052, AttrCheckImpl.SCRIPT), new Attribute("onselect", 1052, AttrCheckImpl.SCRIPT), new Attribute("onunload", 1052, AttrCheckImpl.SCRIPT), new Attribute("onfocus", 1052, AttrCheckImpl.SCRIPT), new Attribute("onafterupdate", 128, AttrCheckImpl.SCRIPT), new Attribute("onbeforeupdate", 128, AttrCheckImpl.SCRIPT), new Attribute("onerrorupdate", 128, AttrCheckImpl.SCRIPT), new Attribute("onrowenter", 128, AttrCheckImpl.SCRIPT), new Attribute("onrowexit", 128, AttrCheckImpl.SCRIPT), new Attribute("onbeforeunload", 128, AttrCheckImpl.SCRIPT), new Attribute("ondatasetchanged", 128, AttrCheckImpl.SCRIPT), new Attribute("ondataavailable", 128, AttrCheckImpl.SCRIPT), new Attribute("ondatasetcomplete", 128, AttrCheckImpl.SCRIPT), new Attribute("profile", 28, AttrCheckImpl.URL), new Attribute("prompt", 26, AttrCheckImpl.TEXT), new Attribute("readonly", 28, AttrCheckImpl.BOOL), new Attribute("rel", 3103, AttrCheckImpl.LINKTYPES), new Attribute("rev", 3103, AttrCheckImpl.LINKTYPES), new Attribute("rightmargin", 128, AttrCheckImpl.NUMBER), new Attribute("rows", 3103, AttrCheckImpl.NUMBER), new Attribute("rowspan", 3103, AttrCheckImpl.NUMBER), new Attribute("rules", 28, AttrCheckImpl.TRULES), new Attribute("scheme", 28, AttrCheckImpl.TEXT), new Attribute("scope", 28, AttrCheckImpl.SCOPE), new Attribute("scrolling", 24, AttrCheckImpl.SCROLL), new Attribute("selected", 3103, AttrCheckImpl.BOOL), new Attribute("shape", 30, AttrCheckImpl.SHAPE), new Attribute("showgrid", 448, AttrCheckImpl.BOOL), new Attribute("showgridx", 448, AttrCheckImpl.BOOL), new Attribute("showgridy", 448, AttrCheckImpl.BOOL), new Attribute("size", 26, AttrCheckImpl.NUMBER), new Attribute("span", 28, AttrCheckImpl.NUMBER), new Attribute("src", 3103, AttrCheckImpl.URL), new Attribute("standby", 28, AttrCheckImpl.TEXT), new Attribute("start", 3103, AttrCheckImpl.NUMBER), new Attribute("style", 28, AttrCheckImpl.TEXT), new Attribute("summary", 28, AttrCheckImpl.TEXT), new Attribute("tabindex", 28, AttrCheckImpl.NUMBER), new Attribute("target", 28, AttrCheckImpl.TARGET), new Attribute("text", 26, AttrCheckImpl.COLOR), new Attribute("title", 28, AttrCheckImpl.TEXT), new Attribute("topmargin", 128, AttrCheckImpl.NUMBER), new Attribute("type", 30, AttrCheckImpl.TYPE), new Attribute("usemap", 3103, AttrCheckImpl.BOOL), new Attribute("valign", 30, AttrCheckImpl.VALIGN), new Attribute("value", 3103, AttrCheckImpl.TEXT), new Attribute("valuetype", 28, AttrCheckImpl.VTYPE), new Attribute("version", 3103, AttrCheckImpl.TEXT), new Attribute("vlink", 26, AttrCheckImpl.COLOR), new Attribute("vspace", 26, AttrCheckImpl.NUMBER), new Attribute("width", 3103, AttrCheckImpl.LENGTH), new Attribute("wrap", 64, AttrCheckImpl.TEXT), new Attribute("xml:lang", 32, AttrCheckImpl.TEXT), new Attribute("xml:space", 32, AttrCheckImpl.TEXT), new Attribute("xmlns", 3103, AttrCheckImpl.TEXT), new Attribute("rbspan", 1024, AttrCheckImpl.NUMBER) };
  private Map attributeHashtable = new Hashtable();
  
  public Attribute lookup(String paramString)
  {
    return (Attribute)this.attributeHashtable.get(paramString);
  }
  
  public Attribute install(Attribute paramAttribute)
  {
    return (Attribute)this.attributeHashtable.put(paramAttribute.getName(), paramAttribute);
  }
  
  public Attribute findAttribute(AttVal paramAttVal)
  {
    if (paramAttVal.attribute != null)
    {
      Attribute localAttribute = lookup(paramAttVal.attribute);
      return localAttribute;
    }
    return null;
  }
  
  public boolean isUrl(String paramString)
  {
    Attribute localAttribute = lookup(paramString);
    return (localAttribute != null) && (localAttribute.getAttrchk() == AttrCheckImpl.URL);
  }
  
  public boolean isScript(String paramString)
  {
    Attribute localAttribute = lookup(paramString);
    return (localAttribute != null) && (localAttribute.getAttrchk() == AttrCheckImpl.SCRIPT);
  }
  
  public boolean isLiteralAttribute(String paramString)
  {
    Attribute localAttribute = lookup(paramString);
    return (localAttribute != null) && (localAttribute.isLiteral());
  }
  
  public void declareLiteralAttrib(String paramString)
  {
    Attribute localAttribute = lookup(paramString);
    if (localAttribute == null) {
      localAttribute = install(new Attribute(paramString, (short)448, null));
    }
    localAttribute.setLiteral(true);
  }
  
  public static AttributeTable getDefaultAttributeTable()
  {
    if (defaultAttributeTable == null)
    {
      defaultAttributeTable = new AttributeTable();
      for (int i = 0; i < ATTRS.length; i++) {
        defaultAttributeTable.install(ATTRS[i]);
      }
      attrHref = defaultAttributeTable.lookup("href");
      attrSrc = defaultAttributeTable.lookup("src");
      attrId = defaultAttributeTable.lookup("id");
      attrName = defaultAttributeTable.lookup("name");
      attrSummary = defaultAttributeTable.lookup("summary");
      attrAlt = defaultAttributeTable.lookup("alt");
      attrLongdesc = defaultAttributeTable.lookup("longdesc");
      attrUsemap = defaultAttributeTable.lookup("usemap");
      attrIsmap = defaultAttributeTable.lookup("ismap");
      attrLanguage = defaultAttributeTable.lookup("language");
      attrType = defaultAttributeTable.lookup("type");
      attrTitle = defaultAttributeTable.lookup("title");
      attrXmlns = defaultAttributeTable.lookup("xmlns");
      attrValue = defaultAttributeTable.lookup("value");
      attrContent = defaultAttributeTable.lookup("content");
      attrDatafld = defaultAttributeTable.lookup("datafld");
      attrWidth = defaultAttributeTable.lookup("width");
      attrHeight = defaultAttributeTable.lookup("height");
      attrAlt.setNowrap(true);
      attrValue.setNowrap(true);
      attrContent.setNowrap(true);
    }
    return defaultAttributeTable;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.AttributeTable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */