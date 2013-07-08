package org.w3c.tidy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class TagTable
{
  public static final Dict XML_TAGS = new Dict(null, (short)3103, 8, null, null);
  private static final Dict[] TAGS = { new Dict("html", 3103, 2129922, ParserImpl.HTML, TagCheckImpl.HTML), new Dict("head", 3103, 2129922, ParserImpl.HEAD, null), new Dict("title", 3103, 4, ParserImpl.TITLE, null), new Dict("base", 3103, 5, ParserImpl.EMPTY, null), new Dict("link", 3103, 5, ParserImpl.EMPTY, TagCheckImpl.LINK), new Dict("meta", 3103, 5, ParserImpl.EMPTY, TagCheckImpl.META), new Dict("style", 28, 4, ParserImpl.SCRIPT, TagCheckImpl.STYLE), new Dict("script", 28, 131100, ParserImpl.SCRIPT, TagCheckImpl.SCRIPT), new Dict("server", 64, 131100, ParserImpl.SCRIPT, null), new Dict("body", 3103, 2129922, ParserImpl.BODY, null), new Dict("frameset", 16, 8194, ParserImpl.FRAMESET, null), new Dict("p", 3103, 32776, ParserImpl.INLINE, null), new Dict("h1", 3103, 16392, ParserImpl.INLINE, null), new Dict("h2", 3103, 16392, ParserImpl.INLINE, null), new Dict("h3", 3103, 16392, ParserImpl.INLINE, null), new Dict("h4", 3103, 16392, ParserImpl.INLINE, null), new Dict("h5", 3103, 16392, ParserImpl.INLINE, null), new Dict("h6", 3103, 16392, ParserImpl.INLINE, null), new Dict("ul", 3103, 8, ParserImpl.LIST, null), new Dict("ol", 3103, 8, ParserImpl.LIST, null), new Dict("dl", 3103, 8, ParserImpl.DEFLIST, null), new Dict("dir", 26, 524296, ParserImpl.LIST, null), new Dict("menu", 26, 524296, ParserImpl.LIST, null), new Dict("pre", 3103, 8, ParserImpl.PRE, null), new Dict("listing", 3103, 524296, ParserImpl.PRE, null), new Dict("xmp", 3103, 524296, ParserImpl.PRE, null), new Dict("plaintext", 3103, 524296, ParserImpl.PRE, null), new Dict("address", 3103, 8, ParserImpl.BLOCK, null), new Dict("blockquote", 3103, 8, ParserImpl.BLOCK, null), new Dict("form", 3103, 8, ParserImpl.BLOCK, TagCheckImpl.FORM), new Dict("isindex", 26, 9, ParserImpl.EMPTY, null), new Dict("fieldset", 28, 8, ParserImpl.BLOCK, null), new Dict("table", 30, 8, ParserImpl.TABLETAG, TagCheckImpl.TABLE), new Dict("hr", 1055, 9, ParserImpl.EMPTY, TagCheckImpl.field_1907), new Dict("div", 30, 8, ParserImpl.BLOCK, null), new Dict("multicol", 64, 8, ParserImpl.BLOCK, null), new Dict("nosave", 64, 8, ParserImpl.BLOCK, null), new Dict("layer", 64, 8, ParserImpl.BLOCK, null), new Dict("ilayer", 64, 16, ParserImpl.INLINE, null), new Dict("nolayer", 64, 131096, ParserImpl.BLOCK, null), new Dict("align", 64, 8, ParserImpl.BLOCK, null), new Dict("center", 26, 8, ParserImpl.BLOCK, null), new Dict("ins", 28, 131096, ParserImpl.INLINE, null), new Dict("del", 28, 131096, ParserImpl.INLINE, null), new Dict("li", 3103, 294944, ParserImpl.BLOCK, null), new Dict("dt", 3103, 294976, ParserImpl.INLINE, null), new Dict("dd", 3103, 294976, ParserImpl.BLOCK, null), new Dict("caption", 30, 128, ParserImpl.INLINE, TagCheckImpl.CAPTION), new Dict("colgroup", 28, 32896, ParserImpl.COLGROUP, null), new Dict("col", 28, 129, ParserImpl.EMPTY, null), new Dict("thead", 28, 33152, ParserImpl.ROWGROUP, null), new Dict("tfoot", 28, 33152, ParserImpl.ROWGROUP, null), new Dict("tbody", 28, 33152, ParserImpl.ROWGROUP, null), new Dict("tr", 30, 32896, ParserImpl.ROW, null), new Dict("td", 30, 295424, ParserImpl.BLOCK, TagCheckImpl.TABLECELL), new Dict("th", 30, 295424, ParserImpl.BLOCK, TagCheckImpl.TABLECELL), new Dict("q", 28, 16, ParserImpl.INLINE, null), new Dict("a", 3103, 16, ParserImpl.INLINE, TagCheckImpl.ANCHOR), new Dict("br", 3103, 17, ParserImpl.EMPTY, null), new Dict("img", 3103, 65553, ParserImpl.EMPTY, TagCheckImpl.IMG), new Dict("object", 28, 71700, ParserImpl.BLOCK, null), new Dict("applet", 26, 71696, ParserImpl.BLOCK, null), new Dict("servlet", 256, 71696, ParserImpl.BLOCK, null), new Dict("param", 30, 17, ParserImpl.EMPTY, null), new Dict("embed", 64, 65553, ParserImpl.EMPTY, null), new Dict("noembed", 64, 16, ParserImpl.INLINE, null), new Dict("iframe", 8, 16, ParserImpl.BLOCK, null), new Dict("frame", 16, 8193, ParserImpl.EMPTY, null), new Dict("noframes", 24, 8200, ParserImpl.NOFRAMES, null), new Dict("noscript", 28, 131096, ParserImpl.BLOCK, null), new Dict("b", 1055, 16, ParserImpl.INLINE, null), new Dict("i", 1055, 16, ParserImpl.INLINE, null), new Dict("u", 26, 16, ParserImpl.INLINE, null), new Dict("tt", 1055, 16, ParserImpl.INLINE, null), new Dict("s", 26, 16, ParserImpl.INLINE, null), new Dict("strike", 26, 16, ParserImpl.INLINE, null), new Dict("big", 28, 16, ParserImpl.INLINE, null), new Dict("small", 28, 16, ParserImpl.INLINE, null), new Dict("sub", 28, 16, ParserImpl.INLINE, null), new Dict("sup", 28, 16, ParserImpl.INLINE, null), new Dict("em", 3103, 16, ParserImpl.INLINE, null), new Dict("strong", 3103, 16, ParserImpl.INLINE, null), new Dict("dfn", 3103, 16, ParserImpl.INLINE, null), new Dict("code", 3103, 16, ParserImpl.INLINE, null), new Dict("samp", 3103, 16, ParserImpl.INLINE, null), new Dict("kbd", 3103, 16, ParserImpl.INLINE, null), new Dict("var", 3103, 16, ParserImpl.INLINE, null), new Dict("cite", 3103, 16, ParserImpl.INLINE, null), new Dict("abbr", 28, 16, ParserImpl.INLINE, null), new Dict("acronym", 28, 16, ParserImpl.INLINE, null), new Dict("span", 30, 16, ParserImpl.INLINE, null), new Dict("blink", 448, 16, ParserImpl.INLINE, null), new Dict("nobr", 448, 16, ParserImpl.INLINE, null), new Dict("wbr", 448, 17, ParserImpl.EMPTY, null), new Dict("marquee", 128, 32784, ParserImpl.INLINE, null), new Dict("bgsound", 128, 5, ParserImpl.EMPTY, null), new Dict("comment", 128, 16, ParserImpl.INLINE, null), new Dict("spacer", 64, 17, ParserImpl.EMPTY, null), new Dict("keygen", 64, 17, ParserImpl.EMPTY, null), new Dict("nolayer", 64, 131096, ParserImpl.BLOCK, null), new Dict("ilayer", 64, 16, ParserImpl.INLINE, null), new Dict("map", 28, 16, ParserImpl.BLOCK, TagCheckImpl.MAP), new Dict("area", 1055, 9, ParserImpl.EMPTY, TagCheckImpl.AREA), new Dict("input", 3103, 65553, ParserImpl.EMPTY, null), new Dict("select", 3103, 1040, ParserImpl.SELECT, null), new Dict("option", 3103, 33792, ParserImpl.TEXT, null), new Dict("optgroup", 28, 33792, ParserImpl.OPTGROUP, null), new Dict("textarea", 3103, 1040, ParserImpl.TEXT, null), new Dict("label", 28, 16, ParserImpl.INLINE, null), new Dict("legend", 28, 16, ParserImpl.INLINE, null), new Dict("button", 28, 16, ParserImpl.INLINE, null), new Dict("basefont", 26, 17, ParserImpl.EMPTY, null), new Dict("font", 26, 16, ParserImpl.INLINE, null), new Dict("bdo", 28, 16, ParserImpl.INLINE, null), new Dict("ruby", 1024, 16, ParserImpl.INLINE, null), new Dict("rbc", 1024, 16, ParserImpl.INLINE, null), new Dict("rtc", 1024, 16, ParserImpl.INLINE, null), new Dict("rb", 1024, 16, ParserImpl.INLINE, null), new Dict("rt", 1024, 16, ParserImpl.INLINE, null), new Dict("", 1024, 16, ParserImpl.INLINE, null), new Dict("rp", 1024, 16, ParserImpl.INLINE, null) };
  protected Dict tagHtml;
  protected Dict tagHead;
  protected Dict tagBody;
  protected Dict tagFrameset;
  protected Dict tagFrame;
  protected Dict tagIframe;
  protected Dict tagNoframes;
  protected Dict tagMeta;
  protected Dict tagTitle;
  protected Dict tagBase;
  protected Dict tagHr;
  protected Dict tagPre;
  protected Dict tagListing;
  protected Dict tagH1;
  protected Dict tagH2;
  protected Dict tagP;
  protected Dict tagUl;
  protected Dict tagOl;
  protected Dict tagDir;
  protected Dict tagLi;
  protected Dict tagDt;
  protected Dict tagDd;
  protected Dict tagDl;
  protected Dict tagTd;
  protected Dict tagTh;
  protected Dict tagTr;
  protected Dict tagCol;
  protected Dict tagColgroup;
  protected Dict tagBr;
  protected Dict tagA;
  protected Dict tagLink;
  protected Dict tagB;
  protected Dict tagI;
  protected Dict tagStrong;
  protected Dict tagEm;
  protected Dict tagBig;
  protected Dict tagSmall;
  protected Dict tagParam;
  protected Dict tagOption;
  protected Dict tagOptgroup;
  protected Dict tagImg;
  protected Dict tagMap;
  protected Dict tagArea;
  protected Dict tagNobr;
  protected Dict tagWbr;
  protected Dict tagFont;
  protected Dict tagSpacer;
  protected Dict tagLayer;
  protected Dict tagCenter;
  protected Dict tagStyle;
  protected Dict tagScript;
  protected Dict tagNoscript;
  protected Dict tagTable;
  protected Dict tagCaption;
  protected Dict tagForm;
  protected Dict tagTextarea;
  protected Dict tagBlockquote;
  protected Dict tagApplet;
  protected Dict tagObject;
  protected Dict tagDiv;
  protected Dict tagSpan;
  protected Dict tagInput;
  protected Dict tagQ;
  protected Dict tagBlink;
  protected Anchor anchorList;
  private Configuration configuration;
  private Map tagHashtable = new Hashtable();
  
  protected TagTable()
  {
    for (int i = 0; i < TAGS.length; i++) {
      install(TAGS[i]);
    }
    this.tagHtml = lookup("html");
    this.tagHead = lookup("head");
    this.tagBody = lookup("body");
    this.tagFrameset = lookup("frameset");
    this.tagFrame = lookup("frame");
    this.tagIframe = lookup("iframe");
    this.tagNoframes = lookup("noframes");
    this.tagMeta = lookup("meta");
    this.tagTitle = lookup("title");
    this.tagBase = lookup("base");
    this.tagHr = lookup("hr");
    this.tagPre = lookup("pre");
    this.tagListing = lookup("listing");
    this.tagH1 = lookup("h1");
    this.tagH2 = lookup("h2");
    this.tagP = lookup("p");
    this.tagUl = lookup("ul");
    this.tagOl = lookup("ol");
    this.tagDir = lookup("dir");
    this.tagLi = lookup("li");
    this.tagDt = lookup("dt");
    this.tagDd = lookup("dd");
    this.tagDl = lookup("dl");
    this.tagTd = lookup("td");
    this.tagTh = lookup("th");
    this.tagTr = lookup("tr");
    this.tagCol = lookup("col");
    this.tagColgroup = lookup("colgroup");
    this.tagBr = lookup("br");
    this.tagA = lookup("a");
    this.tagLink = lookup("link");
    this.tagB = lookup("b");
    this.tagI = lookup("i");
    this.tagStrong = lookup("strong");
    this.tagEm = lookup("em");
    this.tagBig = lookup("big");
    this.tagSmall = lookup("small");
    this.tagParam = lookup("param");
    this.tagOption = lookup("option");
    this.tagOptgroup = lookup("optgroup");
    this.tagImg = lookup("img");
    this.tagMap = lookup("map");
    this.tagArea = lookup("area");
    this.tagNobr = lookup("nobr");
    this.tagWbr = lookup("wbr");
    this.tagFont = lookup("font");
    this.tagSpacer = lookup("spacer");
    this.tagLayer = lookup("layer");
    this.tagCenter = lookup("center");
    this.tagStyle = lookup("style");
    this.tagScript = lookup("script");
    this.tagNoscript = lookup("noscript");
    this.tagTable = lookup("table");
    this.tagCaption = lookup("caption");
    this.tagForm = lookup("form");
    this.tagTextarea = lookup("textarea");
    this.tagBlockquote = lookup("blockquote");
    this.tagApplet = lookup("applet");
    this.tagObject = lookup("object");
    this.tagDiv = lookup("div");
    this.tagSpan = lookup("span");
    this.tagInput = lookup("input");
    this.tagQ = lookup("q");
    this.tagBlink = lookup("blink");
  }
  
  public void setConfiguration(Configuration paramConfiguration)
  {
    this.configuration = paramConfiguration;
  }
  
  public Dict lookup(String paramString)
  {
    return (Dict)this.tagHashtable.get(paramString);
  }
  
  public Dict install(Dict paramDict)
  {
    Dict localDict = (Dict)this.tagHashtable.get(paramDict.name);
    if (localDict != null)
    {
      localDict.versions = paramDict.versions;
      localDict.model |= paramDict.model;
      localDict.setParser(paramDict.getParser());
      localDict.setChkattrs(paramDict.getChkattrs());
      return localDict;
    }
    this.tagHashtable.put(paramDict.name, paramDict);
    return paramDict;
  }
  
  public boolean findTag(Node paramNode)
  {
    if ((this.configuration != null) && (this.configuration.xmlTags))
    {
      paramNode.tag = XML_TAGS;
      return true;
    }
    if (paramNode.element != null)
    {
      Dict localDict = lookup(paramNode.element);
      if (localDict != null)
      {
        paramNode.tag = localDict;
        return true;
      }
    }
    return false;
  }
  
  public Parser findParser(Node paramNode)
  {
    if (paramNode.element != null)
    {
      Dict localDict = lookup(paramNode.element);
      if (localDict != null) {
        return localDict.getParser();
      }
    }
    return null;
  }
  
  boolean isAnchorElement(Node paramNode)
  {
    return (paramNode.tag == this.tagA) || (paramNode.tag == this.tagApplet) || (paramNode.tag == this.tagForm) || (paramNode.tag == this.tagFrame) || (paramNode.tag == this.tagIframe) || (paramNode.tag == this.tagImg) || (paramNode.tag == this.tagMap);
  }
  
  public void defineTag(short paramShort, String paramString)
  {
    int i;
    Parser localParser;
    switch (paramShort)
    {
    case 4: 
      i = 8;
      localParser = ParserImpl.BLOCK;
      break;
    case 1: 
      i = 1;
      localParser = ParserImpl.BLOCK;
      break;
    case 8: 
      i = 8;
      localParser = ParserImpl.PRE;
      break;
    case 2: 
    case 3: 
    case 5: 
    case 6: 
    case 7: 
    default: 
      i = 16;
      localParser = ParserImpl.INLINE;
    }
    install(new Dict(paramString, (short)448, i, localParser, null));
  }
  
  List findAllDefinedTag(short paramShort)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.tagHashtable.values().iterator();
    while (localIterator.hasNext())
    {
      Dict localDict = (Dict)localIterator.next();
      if (localDict != null) {
        switch (paramShort)
        {
        case 1: 
          if ((localDict.versions == 448) && ((localDict.model & 0x1) == 1) && (localDict != this.tagWbr)) {
            localArrayList.add(localDict.name);
          }
          break;
        case 2: 
          if ((localDict.versions == 448) && ((localDict.model & 0x10) == 16) && (localDict != this.tagBlink) && (localDict != this.tagNobr) && (localDict != this.tagWbr)) {
            localArrayList.add(localDict.name);
          }
          break;
        case 4: 
          if ((localDict.versions == 448) && ((localDict.model & 0x8) == 8) && (localDict.getParser() == ParserImpl.BLOCK)) {
            localArrayList.add(localDict.name);
          }
          break;
        case 8: 
          if ((localDict.versions == 448) && ((localDict.model & 0x8) == 8) && (localDict.getParser() == ParserImpl.PRE)) {
            localArrayList.add(localDict.name);
          }
          break;
        }
      }
    }
    return localArrayList;
  }
  
  public void freeAttrs(Node paramNode)
  {
    while (paramNode.attributes != null)
    {
      AttVal localAttVal = paramNode.attributes;
      if (("id".equalsIgnoreCase(localAttVal.attribute)) || (("name".equalsIgnoreCase(localAttVal.attribute)) && (isAnchorElement(paramNode)))) {
        removeAnchorByNode(paramNode);
      }
      paramNode.attributes = localAttVal.next;
    }
  }
  
  void removeAnchorByNode(Node paramNode)
  {
    Object localObject = null;
    Anchor localAnchor1 = null;
    Anchor localAnchor2 = null;
    Anchor localAnchor3 = null;
    for (localAnchor1 = this.anchorList; localAnchor1 != null; localAnchor1 = localAnchor1.next)
    {
      localAnchor3 = localAnchor1.next;
      if (localAnchor1.node == paramNode)
      {
        if (localAnchor2 != null) {
          localAnchor2.next = localAnchor3;
        } else {
          this.anchorList = localAnchor3;
        }
        localObject = localAnchor1;
      }
      else
      {
        localAnchor2 = localAnchor1;
      }
    }
    if (localObject != null) {
      localObject = null;
    }
  }
  
  Anchor newAnchor()
  {
    Anchor localAnchor = new Anchor();
    return localAnchor;
  }
  
  Anchor addAnchor(String paramString, Node paramNode)
  {
    Anchor localAnchor1 = newAnchor();
    localAnchor1.name = paramString;
    localAnchor1.node = paramNode;
    if (this.anchorList == null)
    {
      this.anchorList = localAnchor1;
    }
    else
    {
      for (Anchor localAnchor2 = this.anchorList; localAnchor2.next != null; localAnchor2 = localAnchor2.next) {}
      localAnchor2.next = localAnchor1;
    }
    return this.anchorList;
  }
  
  Node getNodeByAnchor(String paramString)
  {
    for (Anchor localAnchor = this.anchorList; (localAnchor != null) && (!paramString.equalsIgnoreCase(localAnchor.name)); localAnchor = localAnchor.next) {}
    if (localAnchor != null) {
      return localAnchor.node;
    }
    return null;
  }
  
  void freeAnchors()
  {
    this.anchorList = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.TagTable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */