package org.w3c.tidy;

public class Clean
{
  private int classNum = 1;
  private TagTable field_1747;
  
  public Clean(TagTable paramTagTable)
  {
    this.field_1747 = paramTagTable;
  }
  
  private StyleProp insertProperty(StyleProp paramStyleProp, String paramString1, String paramString2)
  {
    StyleProp localStyleProp1 = null;
    Object localObject = paramStyleProp;
    while (paramStyleProp != null)
    {
      int i = paramStyleProp.name.compareTo(paramString1);
      if (i == 0) {
        return localObject;
      }
      if (i > 0)
      {
        localStyleProp2 = new StyleProp(paramString1, paramString2, paramStyleProp);
        if (localStyleProp1 != null) {
          localStyleProp1.next = localStyleProp2;
        } else {
          localObject = localStyleProp2;
        }
        return localObject;
      }
      localStyleProp1 = paramStyleProp;
      paramStyleProp = paramStyleProp.next;
    }
    StyleProp localStyleProp2 = new StyleProp(paramString1, paramString2, null);
    if (localStyleProp1 != null) {
      localStyleProp1.next = localStyleProp2;
    } else {
      localObject = localStyleProp2;
    }
    return localObject;
  }
  
  private StyleProp createProps(StyleProp paramStyleProp, String paramString)
  {
    int k = 0;
    int m = 0;
    int j;
    for (m = 0; m < paramString.length(); m = j + 1)
    {
      while ((m < paramString.length()) && (paramString.charAt(m) == ' ')) {
        m++;
      }
      for (int i = m; i < paramString.length(); i++) {
        if (paramString.charAt(i) == ':')
        {
          k = i + 1;
          break;
        }
      }
      if ((i >= paramString.length()) || (paramString.charAt(i) != ':')) {
        break;
      }
      while ((k < paramString.length()) && (paramString.charAt(k) == ' ')) {
        k++;
      }
      j = k;
      int n = 0;
      while (j < paramString.length())
      {
        if (paramString.charAt(j) == ';')
        {
          n = 1;
          break;
        }
        j++;
      }
      paramStyleProp = insertProperty(paramStyleProp, paramString.substring(m, i), paramString.substring(k, j));
      if (n == 0) {
        break;
      }
    }
    return paramStyleProp;
  }
  
  private String createPropString(StyleProp paramStyleProp)
  {
    String str = "";
    int i = 0;
    for (StyleProp localStyleProp = paramStyleProp; localStyleProp != null; localStyleProp = localStyleProp.next)
    {
      i += localStyleProp.name.length() + 2;
      i += localStyleProp.value.length() + 2;
    }
    for (localStyleProp = paramStyleProp; localStyleProp != null; localStyleProp = localStyleProp.next)
    {
      str = str.concat(localStyleProp.name);
      str = str.concat(": ");
      str = str.concat(localStyleProp.value);
      if (localStyleProp.next == null) {
        break;
      }
      str = str.concat("; ");
    }
    return str;
  }
  
  private String addProperty(String paramString1, String paramString2)
  {
    StyleProp localStyleProp = createProps(null, paramString1);
    localStyleProp = createProps(localStyleProp, paramString2);
    paramString1 = createPropString(localStyleProp);
    return paramString1;
  }
  
  private String gensymClass(Lexer paramLexer, String paramString)
  {
    String str = "c" + this.classNum;
    this.classNum += 1;
    return str;
  }
  
  private String findStyle(Lexer paramLexer, String paramString1, String paramString2)
  {
    for (Style localStyle = paramLexer.styles; localStyle != null; localStyle = localStyle.next) {
      if ((localStyle.tag.equals(paramString1)) && (localStyle.properties.equals(paramString2))) {
        return localStyle.tagClass;
      }
    }
    localStyle = new Style(paramString1, gensymClass(paramLexer, paramString1), paramString2, paramLexer.styles);
    paramLexer.styles = localStyle;
    return localStyle.tagClass;
  }
  
  private void style2Rule(Lexer paramLexer, Node paramNode)
  {
    AttVal localAttVal1 = paramNode.getAttrByName("style");
    if (localAttVal1 != null)
    {
      String str = findStyle(paramLexer, paramNode.element, localAttVal1.value);
      AttVal localAttVal2 = paramNode.getAttrByName("class");
      if (localAttVal2 != null)
      {
        localAttVal2.value = (localAttVal2.value + " " + str);
        paramNode.removeAttribute(localAttVal1);
      }
      else
      {
        localAttVal1.attribute = "class";
        localAttVal1.value = str;
      }
    }
  }
  
  private void addColorRule(Lexer paramLexer, String paramString1, String paramString2)
  {
    if (paramString2 != null)
    {
      paramLexer.addStringLiteral(paramString1);
      paramLexer.addStringLiteral(" { color: ");
      paramLexer.addStringLiteral(paramString2);
      paramLexer.addStringLiteral(" }\n");
    }
  }
  
  private void cleanBodyAttrs(Lexer paramLexer, Node paramNode)
  {
    String str1 = null;
    String str2 = null;
    String str3 = null;
    AttVal localAttVal = paramNode.getAttrByName("background");
    if (localAttVal != null)
    {
      str1 = localAttVal.value;
      localAttVal.value = null;
      paramNode.removeAttribute(localAttVal);
    }
    localAttVal = paramNode.getAttrByName("bgcolor");
    if (localAttVal != null)
    {
      str2 = localAttVal.value;
      localAttVal.value = null;
      paramNode.removeAttribute(localAttVal);
    }
    localAttVal = paramNode.getAttrByName("text");
    if (localAttVal != null)
    {
      str3 = localAttVal.value;
      localAttVal.value = null;
      paramNode.removeAttribute(localAttVal);
    }
    if ((str1 != null) || (str2 != null) || (str3 != null))
    {
      paramLexer.addStringLiteral(" body {\n");
      if (str1 != null)
      {
        paramLexer.addStringLiteral("  background-image: url(");
        paramLexer.addStringLiteral(str1);
        paramLexer.addStringLiteral(");\n");
      }
      if (str2 != null)
      {
        paramLexer.addStringLiteral("  background-color: ");
        paramLexer.addStringLiteral(str2);
        paramLexer.addStringLiteral(";\n");
      }
      if (str3 != null)
      {
        paramLexer.addStringLiteral("  color: ");
        paramLexer.addStringLiteral(str3);
        paramLexer.addStringLiteral(";\n");
      }
      paramLexer.addStringLiteral(" }\n");
    }
    localAttVal = paramNode.getAttrByName("link");
    if (localAttVal != null)
    {
      addColorRule(paramLexer, " :link", localAttVal.value);
      paramNode.removeAttribute(localAttVal);
    }
    localAttVal = paramNode.getAttrByName("vlink");
    if (localAttVal != null)
    {
      addColorRule(paramLexer, " :visited", localAttVal.value);
      paramNode.removeAttribute(localAttVal);
    }
    localAttVal = paramNode.getAttrByName("alink");
    if (localAttVal != null)
    {
      addColorRule(paramLexer, " :active", localAttVal.value);
      paramNode.removeAttribute(localAttVal);
    }
  }
  
  private boolean niceBody(Lexer paramLexer, Node paramNode)
  {
    Node localNode = paramNode.findBody(paramLexer.configuration.field_1881);
    if ((localNode != null) && ((localNode.getAttrByName("background") != null) || (localNode.getAttrByName("bgcolor") != null) || (localNode.getAttrByName("text") != null) || (localNode.getAttrByName("link") != null) || (localNode.getAttrByName("vlink") != null) || (localNode.getAttrByName("alink") != null)))
    {
      Lexer tmp71_70 = paramLexer;
      tmp71_70.badLayout = ((short)(tmp71_70.badLayout | 0x10));
      return false;
    }
    return true;
  }
  
  private void createStyleElement(Lexer paramLexer, Node paramNode)
  {
    if ((paramLexer.styles == null) && (niceBody(paramLexer, paramNode))) {
      return;
    }
    Node localNode1 = paramLexer.newNode((short)5, null, 0, 0, "style");
    localNode1.implicit = true;
    AttVal localAttVal = new AttVal(null, null, 34, "type", "text/css");
    localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
    localNode1.attributes = localAttVal;
    Node localNode3 = paramNode.findBody(paramLexer.configuration.field_1881);
    paramLexer.txtstart = paramLexer.lexsize;
    if (localNode3 != null) {
      cleanBodyAttrs(paramLexer, localNode3);
    }
    for (Style localStyle = paramLexer.styles; localStyle != null; localStyle = localStyle.next)
    {
      paramLexer.addCharToLexer(32);
      paramLexer.addStringLiteral(localStyle.tag);
      paramLexer.addCharToLexer(46);
      paramLexer.addStringLiteral(localStyle.tagClass);
      paramLexer.addCharToLexer(32);
      paramLexer.addCharToLexer(123);
      paramLexer.addStringLiteral(localStyle.properties);
      paramLexer.addCharToLexer(125);
      paramLexer.addCharToLexer(10);
    }
    paramLexer.txtend = paramLexer.lexsize;
    localNode1.insertNodeAtEnd(paramLexer.newNode((short)4, paramLexer.lexbuf, paramLexer.txtstart, paramLexer.txtend));
    Node localNode2 = paramNode.findHEAD(paramLexer.configuration.field_1881);
    if (localNode2 != null) {
      localNode2.insertNodeAtEnd(localNode1);
    }
  }
  
  private void fixNodeLinks(Node paramNode)
  {
    if (paramNode.prev != null) {
      paramNode.prev.next = paramNode;
    } else {
      paramNode.parent.content = paramNode;
    }
    if (paramNode.next != null) {
      paramNode.next.prev = paramNode;
    } else {
      paramNode.parent.last = paramNode;
    }
    for (Node localNode = paramNode.content; localNode != null; localNode = localNode.next) {
      localNode.parent = paramNode;
    }
  }
  
  private void stripOnlyChild(Node paramNode)
  {
    Node localNode = paramNode.content;
    paramNode.content = localNode.content;
    paramNode.last = localNode.last;
    localNode.content = null;
    for (localNode = paramNode.content; localNode != null; localNode = localNode.next) {
      localNode.parent = paramNode;
    }
  }
  
  private void discardContainer(Node paramNode, Node[] paramArrayOfNode)
  {
    Node localNode2 = paramNode.parent;
    if (paramNode.content != null)
    {
      paramNode.last.next = paramNode.next;
      if (paramNode.next != null)
      {
        paramNode.next.prev = paramNode.last;
        paramNode.last.next = paramNode.next;
      }
      else
      {
        localNode2.last = paramNode.last;
      }
      if (paramNode.prev != null)
      {
        paramNode.content.prev = paramNode.prev;
        paramNode.prev.next = paramNode.content;
      }
      else
      {
        localNode2.content = paramNode.content;
      }
      for (Node localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next) {
        localNode1.parent = localNode2;
      }
      paramArrayOfNode[0] = paramNode.content;
    }
    else
    {
      if (paramNode.next != null) {
        paramNode.next.prev = paramNode.prev;
      } else {
        localNode2.last = paramNode.prev;
      }
      if (paramNode.prev != null) {
        paramNode.prev.next = paramNode.next;
      } else {
        localNode2.content = paramNode.next;
      }
      paramArrayOfNode[0] = paramNode.next;
    }
    paramNode.next = null;
    paramNode.content = null;
  }
  
  private void addStyleProperty(Node paramNode, String paramString)
  {
    for (AttVal localAttVal = paramNode.attributes; (localAttVal != null) && (!localAttVal.attribute.equals("style")); localAttVal = localAttVal.next) {}
    if (localAttVal != null)
    {
      String str = addProperty(localAttVal.value, paramString);
      localAttVal.value = str;
    }
    else
    {
      localAttVal = new AttVal(paramNode.attributes, null, 34, "style", paramString);
      localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
      paramNode.attributes = localAttVal;
    }
  }
  
  private String mergeProperties(String paramString1, String paramString2)
  {
    StyleProp localStyleProp = createProps(null, paramString1);
    localStyleProp = createProps(localStyleProp, paramString2);
    String str = createPropString(localStyleProp);
    return str;
  }
  
  private void mergeClasses(Node paramNode1, Node paramNode2)
  {
    String str2 = null;
    for (AttVal localAttVal = paramNode2.attributes; localAttVal != null; localAttVal = localAttVal.next) {
      if ("class".equals(localAttVal.attribute))
      {
        str2 = localAttVal.value;
        break;
      }
    }
    String str1 = null;
    for (localAttVal = paramNode1.attributes; localAttVal != null; localAttVal = localAttVal.next) {
      if ("class".equals(localAttVal.attribute))
      {
        str1 = localAttVal.value;
        break;
      }
    }
    if (str1 != null)
    {
      if (str2 != null)
      {
        String str3 = str1 + ' ' + str2;
        localAttVal.value = str3;
      }
    }
    else if (str2 != null)
    {
      localAttVal = new AttVal(paramNode1.attributes, null, 34, "class", str2);
      localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
      paramNode1.attributes = localAttVal;
    }
  }
  
  private void mergeStyles(Node paramNode1, Node paramNode2)
  {
    mergeClasses(paramNode1, paramNode2);
    String str2 = null;
    for (AttVal localAttVal = paramNode2.attributes; localAttVal != null; localAttVal = localAttVal.next) {
      if (localAttVal.attribute.equals("style"))
      {
        str2 = localAttVal.value;
        break;
      }
    }
    String str1 = null;
    for (localAttVal = paramNode1.attributes; localAttVal != null; localAttVal = localAttVal.next) {
      if (localAttVal.attribute.equals("style"))
      {
        str1 = localAttVal.value;
        break;
      }
    }
    if (str1 != null)
    {
      if (str2 != null)
      {
        String str3 = mergeProperties(str1, str2);
        localAttVal.value = str3;
      }
    }
    else if (str2 != null)
    {
      localAttVal = new AttVal(paramNode1.attributes, null, 34, "style", str2);
      localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
      paramNode1.attributes = localAttVal;
    }
  }
  
  private String fontSize2Name(String paramString)
  {
    String[] arrayOfString = { "60%", "70%", "80%", null, "120%", "150%", "200%" };
    int i;
    if ((paramString.length() > 0) && ('0' <= paramString.charAt(0)) && (paramString.charAt(0) <= '6'))
    {
      i = paramString.charAt(0) - '0';
      return arrayOfString[i];
    }
    double d;
    String str;
    if ((paramString.length() > 0) && (paramString.charAt(0) == '-'))
    {
      if ((paramString.length() > 1) && ('0' <= paramString.charAt(1)) && (paramString.charAt(1) <= '6'))
      {
        i = paramString.charAt(1) - '0';
        d = 1.0D;
        while (i > 0)
        {
          d *= 0.8D;
          i--;
        }
        d *= 100.0D;
        str = "" + (int)d + "%";
        return str;
      }
      return "smaller";
    }
    if ((paramString.length() > 1) && ('0' <= paramString.charAt(1)) && (paramString.charAt(1) <= '6'))
    {
      i = paramString.charAt(1) - '0';
      d = 1.0D;
      while (i > 0)
      {
        d *= 1.2D;
        i--;
      }
      d *= 100.0D;
      str = "" + (int)d + "%";
      return str;
    }
    return "larger";
  }
  
  private void addFontFace(Node paramNode, String paramString)
  {
    addStyleProperty(paramNode, "font-family: " + paramString);
  }
  
  private void addFontSize(Node paramNode, String paramString)
  {
    if (paramString == null) {
      return;
    }
    if (("6".equals(paramString)) && (paramNode.tag == this.field_1747.tagP))
    {
      paramNode.element = "h1";
      this.field_1747.findTag(paramNode);
      return;
    }
    if (("5".equals(paramString)) && (paramNode.tag == this.field_1747.tagP))
    {
      paramNode.element = "h2";
      this.field_1747.findTag(paramNode);
      return;
    }
    if (("4".equals(paramString)) && (paramNode.tag == this.field_1747.tagP))
    {
      paramNode.element = "h3";
      this.field_1747.findTag(paramNode);
      return;
    }
    String str = fontSize2Name(paramString);
    if (str != null) {
      addStyleProperty(paramNode, "font-size: " + str);
    }
  }
  
  private void addFontColor(Node paramNode, String paramString)
  {
    addStyleProperty(paramNode, "color: " + paramString);
  }
  
  private void addAlign(Node paramNode, String paramString)
  {
    addStyleProperty(paramNode, "text-align: " + paramString.toLowerCase());
  }
  
  private void addFontStyles(Node paramNode, AttVal paramAttVal)
  {
    while (paramAttVal != null)
    {
      if (paramAttVal.attribute.equals("face")) {
        addFontFace(paramNode, paramAttVal.value);
      } else if (paramAttVal.attribute.equals("size")) {
        addFontSize(paramNode, paramAttVal.value);
      } else if (paramAttVal.attribute.equals("color")) {
        addFontColor(paramNode, paramAttVal.value);
      }
      paramAttVal = paramAttVal.next;
    }
  }
  
  private void textAlign(Lexer paramLexer, Node paramNode)
  {
    Object localObject = null;
    for (AttVal localAttVal = paramNode.attributes; localAttVal != null; localAttVal = localAttVal.next)
    {
      if (localAttVal.attribute.equals("align"))
      {
        if (localObject != null) {
          localObject.next = localAttVal.next;
        } else {
          paramNode.attributes = localAttVal.next;
        }
        if (localAttVal.value == null) {
          break;
        }
        addAlign(paramNode, localAttVal.value);
        break;
      }
      localObject = localAttVal;
    }
  }
  
  private boolean dir2Div(Lexer paramLexer, Node paramNode)
  {
    if ((paramNode.tag == this.field_1747.tagDir) || (paramNode.tag == this.field_1747.tagUl) || (paramNode.tag == this.field_1747.tagOl))
    {
      Node localNode = paramNode.content;
      if (localNode == null) {
        return false;
      }
      if (localNode.next != null) {
        return false;
      }
      if (localNode.tag != this.field_1747.tagLi) {
        return false;
      }
      if (!localNode.implicit) {
        return false;
      }
      paramNode.tag = this.field_1747.tagDiv;
      paramNode.element = "div";
      addStyleProperty(paramNode, "margin-left: 2em");
      stripOnlyChild(paramNode);
      return true;
    }
    return false;
  }
  
  private boolean center2Div(Lexer paramLexer, Node paramNode, Node[] paramArrayOfNode)
  {
    if (paramNode.tag == this.field_1747.tagCenter)
    {
      if (paramLexer.configuration.dropFontTags)
      {
        Node localNode1;
        Node localNode2;
        if (paramNode.content != null)
        {
          localNode1 = paramNode.last;
          localNode2 = paramNode.parent;
          discardContainer(paramNode, paramArrayOfNode);
          paramNode = paramLexer.inferredTag("br");
          if (localNode1.next != null) {
            localNode1.next.prev = paramNode;
          }
          paramNode.next = localNode1.next;
          localNode1.next = paramNode;
          paramNode.prev = localNode1;
          if (localNode2.last == localNode1) {
            localNode2.last = paramNode;
          }
          paramNode.parent = localNode2;
        }
        else
        {
          localNode1 = paramNode.prev;
          localNode2 = paramNode.next;
          Node localNode3 = paramNode.parent;
          discardContainer(paramNode, paramArrayOfNode);
          paramNode = paramLexer.inferredTag("br");
          paramNode.next = localNode2;
          paramNode.prev = localNode1;
          paramNode.parent = localNode3;
          if (localNode2 != null) {
            localNode2.prev = paramNode;
          } else {
            localNode3.last = paramNode;
          }
          if (localNode1 != null) {
            localNode1.next = paramNode;
          } else {
            localNode3.content = paramNode;
          }
        }
        return true;
      }
      paramNode.tag = this.field_1747.tagDiv;
      paramNode.element = "div";
      addStyleProperty(paramNode, "text-align: center");
      return true;
    }
    return false;
  }
  
  private boolean mergeDivs(Lexer paramLexer, Node paramNode)
  {
    if (paramNode.tag != this.field_1747.tagDiv) {
      return false;
    }
    Node localNode = paramNode.content;
    if (localNode == null) {
      return false;
    }
    if (localNode.tag != this.field_1747.tagDiv) {
      return false;
    }
    if (localNode.next != null) {
      return false;
    }
    mergeStyles(paramNode, localNode);
    stripOnlyChild(paramNode);
    return true;
  }
  
  private boolean nestedList(Lexer paramLexer, Node paramNode, Node[] paramArrayOfNode)
  {
    if ((paramNode.tag == this.field_1747.tagUl) || (paramNode.tag == this.field_1747.tagOl))
    {
      Node localNode1 = paramNode.content;
      if (localNode1 == null) {
        return false;
      }
      if (localNode1.next != null) {
        return false;
      }
      Node localNode2 = localNode1.content;
      if (localNode2 == null) {
        return false;
      }
      if (localNode2.tag != paramNode.tag) {
        return false;
      }
      paramArrayOfNode[0] = localNode2;
      localNode2.prev = paramNode.prev;
      localNode2.next = paramNode.next;
      localNode2.parent = paramNode.parent;
      fixNodeLinks(localNode2);
      localNode1.content = null;
      paramNode.content = null;
      paramNode.next = null;
      paramNode = null;
      if ((localNode2.prev != null) && ((localNode2.prev.tag == this.field_1747.tagUl) || (localNode2.prev.tag == this.field_1747.tagOl)))
      {
        paramNode = localNode2;
        localNode2 = paramNode.prev;
        localNode2.next = paramNode.next;
        if (localNode2.next != null) {
          localNode2.next.prev = localNode2;
        }
        localNode1 = localNode2.last;
        paramNode.parent = localNode1;
        paramNode.next = null;
        paramNode.prev = localNode1.last;
        fixNodeLinks(paramNode);
        cleanNode(paramLexer, paramNode);
      }
      return true;
    }
    return false;
  }
  
  private boolean blockStyle(Lexer paramLexer, Node paramNode)
  {
    if (((paramNode.tag.model & 0xE8) != 0) && (paramNode.tag != this.field_1747.tagTable) && (paramNode.tag != this.field_1747.tagTr) && (paramNode.tag != this.field_1747.tagLi))
    {
      if (paramNode.tag != this.field_1747.tagCaption) {
        textAlign(paramLexer, paramNode);
      }
      Node localNode = paramNode.content;
      if (localNode == null) {
        return false;
      }
      if (localNode.next != null) {
        return false;
      }
      if (localNode.tag == this.field_1747.tagB)
      {
        mergeStyles(paramNode, localNode);
        addStyleProperty(paramNode, "font-weight: bold");
        stripOnlyChild(paramNode);
        return true;
      }
      if (localNode.tag == this.field_1747.tagI)
      {
        mergeStyles(paramNode, localNode);
        addStyleProperty(paramNode, "font-style: italic");
        stripOnlyChild(paramNode);
        return true;
      }
      if (localNode.tag == this.field_1747.tagFont)
      {
        mergeStyles(paramNode, localNode);
        addFontStyles(paramNode, localNode.attributes);
        stripOnlyChild(paramNode);
        return true;
      }
    }
    return false;
  }
  
  private boolean inlineStyle(Lexer paramLexer, Node paramNode, Node[] paramArrayOfNode)
  {
    if ((paramNode.tag != this.field_1747.tagFont) && ((paramNode.tag.model & 0x210) != 0))
    {
      Node localNode = paramNode.content;
      if (localNode == null) {
        return false;
      }
      if (localNode.next != null) {
        return false;
      }
      if ((localNode.tag == this.field_1747.tagB) && (paramLexer.configuration.logicalEmphasis))
      {
        mergeStyles(paramNode, localNode);
        addStyleProperty(paramNode, "font-weight: bold");
        stripOnlyChild(paramNode);
        return true;
      }
      if ((localNode.tag == this.field_1747.tagI) && (paramLexer.configuration.logicalEmphasis))
      {
        mergeStyles(paramNode, localNode);
        addStyleProperty(paramNode, "font-style: italic");
        stripOnlyChild(paramNode);
        return true;
      }
      if (localNode.tag == this.field_1747.tagFont)
      {
        mergeStyles(paramNode, localNode);
        addFontStyles(paramNode, localNode.attributes);
        stripOnlyChild(paramNode);
        return true;
      }
    }
    return false;
  }
  
  private boolean font2Span(Lexer paramLexer, Node paramNode, Node[] paramArrayOfNode)
  {
    if (paramNode.tag == this.field_1747.tagFont)
    {
      if (paramLexer.configuration.dropFontTags)
      {
        discardContainer(paramNode, paramArrayOfNode);
        return false;
      }
      if ((paramNode.parent.content == paramNode) && (paramNode.next == null)) {
        return false;
      }
      addFontStyles(paramNode, paramNode.attributes);
      Object localObject1 = paramNode.attributes;
      Object localObject2 = null;
      while (localObject1 != null)
      {
        AttVal localAttVal = ((AttVal)localObject1).next;
        if (((AttVal)localObject1).attribute.equals("style"))
        {
          ((AttVal)localObject1).next = null;
          localObject2 = localObject1;
        }
        localObject1 = localAttVal;
      }
      paramNode.attributes = localObject2;
      paramNode.tag = this.field_1747.tagSpan;
      paramNode.element = "span";
      return true;
    }
    return false;
  }
  
  private Node cleanNode(Lexer paramLexer, Node paramNode)
  {
    Node localNode = null;
    Node[] arrayOfNode = new Node[1];
    boolean bool = false;
    localNode = paramNode;
    while ((paramNode != null) && (paramNode.isElement()))
    {
      arrayOfNode[0] = localNode;
      bool = dir2Div(paramLexer, paramNode);
      localNode = arrayOfNode[0];
      if (!bool)
      {
        bool = nestedList(paramLexer, paramNode, arrayOfNode);
        localNode = arrayOfNode[0];
        if (bool) {
          return localNode;
        }
        bool = center2Div(paramLexer, paramNode, arrayOfNode);
        localNode = arrayOfNode[0];
        if (!bool)
        {
          bool = mergeDivs(paramLexer, paramNode);
          localNode = arrayOfNode[0];
          if (!bool)
          {
            bool = blockStyle(paramLexer, paramNode);
            localNode = arrayOfNode[0];
            if (!bool)
            {
              bool = inlineStyle(paramLexer, paramNode, arrayOfNode);
              localNode = arrayOfNode[0];
              if (!bool)
              {
                bool = font2Span(paramLexer, paramNode, arrayOfNode);
                localNode = arrayOfNode[0];
                if (!bool) {
                  break;
                }
              }
            }
          }
        }
      }
      paramNode = localNode;
    }
    return localNode;
  }
  
  private Node createStyleProperties(Lexer paramLexer, Node paramNode, Node[] paramArrayOfNode)
  {
    Node localNode = paramNode.content;
    if (localNode != null)
    {
      Node[] arrayOfNode = new Node[1];
      arrayOfNode[0] = paramNode;
      while (localNode != null)
      {
        localNode = createStyleProperties(paramLexer, localNode, arrayOfNode);
        if (arrayOfNode[0] != paramNode) {
          return arrayOfNode[0];
        }
        if (localNode != null) {
          localNode = localNode.next;
        }
      }
    }
    return cleanNode(paramLexer, paramNode);
  }
  
  private void defineStyleRules(Lexer paramLexer, Node paramNode)
  {
    if (paramNode.content != null) {
      for (Node localNode = paramNode.content; localNode != null; localNode = localNode.next) {
        defineStyleRules(paramLexer, localNode);
      }
    }
    style2Rule(paramLexer, paramNode);
  }
  
  public void cleanTree(Lexer paramLexer, Node paramNode)
  {
    Node[] arrayOfNode = new Node[1];
    arrayOfNode[0] = paramNode;
    paramNode = createStyleProperties(paramLexer, paramNode, arrayOfNode);
    if (!paramLexer.configuration.makeClean)
    {
      defineStyleRules(paramLexer, paramNode);
      createStyleElement(paramLexer, paramNode);
    }
  }
  
  public void nestedEmphasis(Node paramNode)
  {
    Node[] arrayOfNode = new Node[1];
    while (paramNode != null)
    {
      Node localNode = paramNode.next;
      if (((paramNode.tag == this.field_1747.tagB) || (paramNode.tag == this.field_1747.tagI)) && (paramNode.parent != null) && (paramNode.parent.tag == paramNode.tag))
      {
        arrayOfNode[0] = localNode;
        discardContainer(paramNode, arrayOfNode);
        localNode = arrayOfNode[0];
        paramNode = localNode;
      }
      else
      {
        if (paramNode.content != null) {
          nestedEmphasis(paramNode.content);
        }
        paramNode = localNode;
      }
    }
  }
  
  public void emFromI(Node paramNode)
  {
    while (paramNode != null)
    {
      if (paramNode.tag == this.field_1747.tagI)
      {
        paramNode.element = this.field_1747.tagEm.name;
        paramNode.tag = this.field_1747.tagEm;
      }
      else if (paramNode.tag == this.field_1747.tagB)
      {
        paramNode.element = this.field_1747.tagStrong.name;
        paramNode.tag = this.field_1747.tagStrong;
      }
      if (paramNode.content != null) {
        emFromI(paramNode.content);
      }
      paramNode = paramNode.next;
    }
  }
  
  public void list2BQ(Node paramNode)
  {
    while (paramNode != null)
    {
      if (paramNode.content != null) {
        list2BQ(paramNode.content);
      }
      if ((paramNode.tag != null) && (paramNode.tag.getParser() == ParserImpl.LIST) && (paramNode.hasOneChild()) && (paramNode.content.implicit))
      {
        stripOnlyChild(paramNode);
        paramNode.element = this.field_1747.tagBlockquote.name;
        paramNode.tag = this.field_1747.tagBlockquote;
        paramNode.implicit = true;
      }
      paramNode = paramNode.next;
    }
  }
  
  public void bQ2Div(Node paramNode)
  {
    while (paramNode != null)
    {
      if ((paramNode.tag == this.field_1747.tagBlockquote) && (paramNode.implicit))
      {
        int i = 1;
        while ((paramNode.hasOneChild()) && (paramNode.content.tag == this.field_1747.tagBlockquote) && (paramNode.implicit))
        {
          i++;
          stripOnlyChild(paramNode);
        }
        if (paramNode.content != null) {
          bQ2Div(paramNode.content);
        }
        String str = "margin-left: " + new Integer(2 * i).toString() + "em";
        paramNode.element = this.field_1747.tagDiv.name;
        paramNode.tag = this.field_1747.tagDiv;
        AttVal localAttVal = paramNode.getAttrByName("style");
        if ((localAttVal != null) && (localAttVal.value != null)) {
          localAttVal.value = (str + "; " + localAttVal.value);
        } else {
          paramNode.addAttribute("style", str);
        }
      }
      else if (paramNode.content != null)
      {
        bQ2Div(paramNode.content);
      }
      paramNode = paramNode.next;
    }
  }
  
  Node findEnclosingCell(Node paramNode)
  {
    for (Node localNode = paramNode; localNode != null; localNode = localNode.parent) {
      if (localNode.tag == this.field_1747.tagTd) {
        return localNode;
      }
    }
    return null;
  }
  
  public Node pruneSection(Lexer paramLexer, Node paramNode)
  {
    do
    {
      for (;;)
      {
        paramNode = Node.discardElement(paramNode);
        if (paramNode == null) {
          return null;
        }
        if (paramNode.type == 9)
        {
          if (!TidyUtils.getString(paramNode.textarray, paramNode.start, 2).equals("if")) {
            break;
          }
          paramNode = pruneSection(paramLexer, paramNode);
        }
      }
    } while (!TidyUtils.getString(paramNode.textarray, paramNode.start, 5).equals("endif"));
    paramNode = Node.discardElement(paramNode);
    return paramNode;
  }
  
  public void dropSections(Lexer paramLexer, Node paramNode)
  {
    while (paramNode != null) {
      if (paramNode.type == 9)
      {
        if ((TidyUtils.getString(paramNode.textarray, paramNode.start, 2).equals("if")) && (!TidyUtils.getString(paramNode.textarray, paramNode.start, 7).equals("if !vml"))) {
          paramNode = pruneSection(paramLexer, paramNode);
        } else {
          paramNode = Node.discardElement(paramNode);
        }
      }
      else
      {
        if (paramNode.content != null) {
          dropSections(paramLexer, paramNode.content);
        }
        paramNode = paramNode.next;
      }
    }
  }
  
  public void purgeWord2000Attributes(Node paramNode)
  {
    Object localObject1 = null;
    AttVal localAttVal = null;
    Object localObject2 = null;
    for (localObject1 = paramNode.attributes; localObject1 != null; localObject1 = localAttVal)
    {
      localAttVal = ((AttVal)localObject1).next;
      if ((((AttVal)localObject1).attribute != null) && (((AttVal)localObject1).value != null) && (((AttVal)localObject1).attribute.equals("class")) && ((((AttVal)localObject1).value.equals("Code")) || (!((AttVal)localObject1).value.startsWith("Mso")))) {
        localObject2 = localObject1;
      } else if ((((AttVal)localObject1).attribute != null) && ((((AttVal)localObject1).attribute.equals("class")) || (((AttVal)localObject1).attribute.equals("style")) || (((AttVal)localObject1).attribute.equals("lang")) || (((AttVal)localObject1).attribute.startsWith("x:")) || (((((AttVal)localObject1).attribute.equals("height")) || (((AttVal)localObject1).attribute.equals("width"))) && ((paramNode.tag == this.field_1747.tagTd) || (paramNode.tag == this.field_1747.tagTr) || (paramNode.tag == this.field_1747.tagTh)))))
      {
        if (localObject2 != null) {
          localObject2.next = localAttVal;
        } else {
          paramNode.attributes = localAttVal;
        }
      }
      else {
        localObject2 = localObject1;
      }
    }
  }
  
  public Node stripSpan(Lexer paramLexer, Node paramNode)
  {
    Object localObject = null;
    cleanWord2000(paramLexer, paramNode.content);
    Node localNode2 = paramNode.content;
    if (paramNode.prev != null)
    {
      localObject = paramNode.prev;
    }
    else if (localNode2 != null)
    {
      localNode1 = localNode2;
      localNode2 = localNode2.next;
      localNode1.removeNode();
      Node.insertNodeBeforeElement(paramNode, localNode1);
    }
    for (localObject = localNode1; localNode2 != null; localObject = localNode1)
    {
      localNode1 = localNode2;
      localNode2 = localNode2.next;
      localNode1.removeNode();
      ((Node)localObject).insertNodeAfterElement(localNode1);
    }
    if (paramNode.next == null) {
      paramNode.parent.last = ((Node)localObject);
    }
    Node localNode1 = paramNode.next;
    paramNode.content = null;
    Node.discardElement(paramNode);
    return localNode1;
  }
  
  private void normalizeSpaces(Lexer paramLexer, Node paramNode)
  {
    while (paramNode != null)
    {
      if (paramNode.content != null) {
        normalizeSpaces(paramLexer, paramNode.content);
      }
      if (paramNode.type == 4)
      {
        int[] arrayOfInt = new int[1];
        int j = paramNode.start;
        for (int i = paramNode.start; i < paramNode.end; i++)
        {
          arrayOfInt[0] = paramNode.textarray[i];
          if (arrayOfInt[0] > 127) {
            i += PPrint.getUTF8(paramNode.textarray, i, arrayOfInt);
          }
          if (arrayOfInt[0] == 160) {
            arrayOfInt[0] = 32;
          }
          j = PPrint.putUTF8(paramNode.textarray, j, arrayOfInt[0]);
        }
      }
      paramNode = paramNode.next;
    }
  }
  
  boolean noMargins(Node paramNode)
  {
    AttVal localAttVal = paramNode.getAttrByName("style");
    if ((localAttVal == null) || (localAttVal.value == null)) {
      return false;
    }
    if (localAttVal.value.indexOf("margin-top: 0") == -1) {
      return false;
    }
    return localAttVal.value.indexOf("margin-bottom: 0") != -1;
  }
  
  boolean singleSpace(Lexer paramLexer, Node paramNode)
  {
    if (paramNode.content != null)
    {
      paramNode = paramNode.content;
      if (paramNode.next != null) {
        return false;
      }
      if (paramNode.type != 4) {
        return false;
      }
      if ((paramNode.end - paramNode.start == 1) && (paramLexer.lexbuf[paramNode.start] == 32)) {
        return true;
      }
      if (paramNode.end - paramNode.start == 2)
      {
        int[] arrayOfInt = new int[1];
        PPrint.getUTF8(paramLexer.lexbuf, paramNode.start, arrayOfInt);
        if (arrayOfInt[0] == 160) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void cleanWord2000(Lexer paramLexer, Node paramNode)
  {
    Node localNode = null;
    while (paramNode != null)
    {
      if (paramNode.tag == this.field_1747.tagHtml)
      {
        if (paramNode.getAttrByName("xmlns:o") == null) {
          return;
        }
        paramLexer.configuration.field_1881.freeAttrs(paramNode);
      }
      Object localObject1;
      Object localObject2;
      if ((paramNode.tag == this.field_1747.tagP) && (noMargins(paramNode)))
      {
        Node.coerceNode(paramLexer, paramNode, this.field_1747.tagPre);
        purgeWord2000Attributes(paramNode);
        if (paramNode.content != null) {
          cleanWord2000(paramLexer, paramNode.content);
        }
        localObject1 = paramNode;
        for (paramNode = paramNode.next; (paramNode.tag == this.field_1747.tagP) && (noMargins(paramNode)); paramNode = (Node)localObject2)
        {
          localObject2 = paramNode.next;
          paramNode.removeNode();
          ((Node)localObject1).insertNodeAtEnd(paramLexer.newLineNode());
          ((Node)localObject1).insertNodeAtEnd(paramNode);
          stripSpan(paramLexer, paramNode);
        }
        if (paramNode == null) {
          break;
        }
      }
      if ((paramNode.tag != null) && (TidyUtils.toBoolean(paramNode.tag.model & 0x8)) && (singleSpace(paramLexer, paramNode)))
      {
        paramNode = stripSpan(paramLexer, paramNode);
      }
      else if ((paramNode.tag == this.field_1747.tagStyle) || (paramNode.tag == this.field_1747.tagMeta) || (paramNode.type == 2))
      {
        paramNode = Node.discardElement(paramNode);
      }
      else if ((paramNode.tag == this.field_1747.tagSpan) || (paramNode.tag == this.field_1747.tagFont))
      {
        paramNode = stripSpan(paramLexer, paramNode);
      }
      else if (paramNode.tag == this.field_1747.tagLink)
      {
        localObject1 = paramNode.getAttrByName("rel");
        if ((localObject1 != null) && (((AttVal)localObject1).value != null) && (((AttVal)localObject1).value.equals("File-List"))) {
          paramNode = Node.discardElement(paramNode);
        }
      }
      else if ((paramNode.content == null) && (paramNode.tag == this.field_1747.tagP))
      {
        paramNode = Node.discardElement(paramNode);
      }
      else
      {
        if (paramNode.tag == this.field_1747.tagP)
        {
          localObject1 = paramNode.getAttrByName("class");
          localObject2 = paramNode.getAttrByName("style");
          Object localObject3;
          if ((localObject1 != null) && (((AttVal)localObject1).value != null) && ((((AttVal)localObject1).value.equals("MsoListBullet")) || (((AttVal)localObject1).value.equals("MsoListNumber")) || ((localObject2 != null) && (((AttVal)localObject2).value.indexOf("mso-list:") != -1))))
          {
            localObject3 = this.field_1747.tagUl;
            if (((AttVal)localObject1).value.equals("MsoListNumber")) {
              localObject3 = this.field_1747.tagOl;
            }
            Node.coerceNode(paramLexer, paramNode, this.field_1747.tagLi);
            if ((localNode == null) || (localNode.tag != localObject3))
            {
              localNode = paramLexer.inferredTag(((Dict)localObject3).name);
              Node.insertNodeBeforeElement(paramNode, localNode);
            }
            purgeWord2000Attributes(paramNode);
            if (paramNode.content != null) {
              cleanWord2000(paramLexer, paramNode.content);
            }
            paramNode.removeNode();
            localNode.insertNodeAtEnd(paramNode);
            paramNode = localNode;
          }
          else if ((localObject1 != null) && (((AttVal)localObject1).value != null) && (((AttVal)localObject1).value.equals("Code")))
          {
            localObject3 = paramLexer.newLineNode();
            normalizeSpaces(paramLexer, paramNode);
            if ((localNode == null) || (localNode.tag != this.field_1747.tagPre))
            {
              localNode = paramLexer.inferredTag("pre");
              Node.insertNodeBeforeElement(paramNode, localNode);
            }
            paramNode.removeNode();
            localNode.insertNodeAtEnd(paramNode);
            stripSpan(paramLexer, paramNode);
            localNode.insertNodeAtEnd((Node)localObject3);
            paramNode = localNode.next;
          }
          else
          {
            localNode = null;
          }
        }
        else
        {
          localNode = null;
        }
        if ((paramNode.type == 5) || (paramNode.type == 7)) {
          purgeWord2000Attributes(paramNode);
        }
        if (paramNode.content != null) {
          cleanWord2000(paramLexer, paramNode.content);
        }
        paramNode = paramNode.next;
      }
    }
  }
  
  public boolean isWord2000(Node paramNode)
  {
    Node localNode3 = paramNode.findHTML(this.field_1747);
    if ((localNode3 != null) && (localNode3.getAttrByName("xmlns:o") != null)) {
      return true;
    }
    Node localNode2 = paramNode.findHEAD(this.field_1747);
    if (localNode2 != null) {
      for (Node localNode1 = localNode2.content; localNode1 != null; localNode1 = localNode1.next) {
        if (localNode1.tag == this.field_1747.tagMeta)
        {
          AttVal localAttVal = localNode1.getAttrByName("name");
          if ((localAttVal != null) && (localAttVal.value != null) && ("generator".equals(localAttVal.value)))
          {
            localAttVal = localNode1.getAttrByName("content");
            if ((localAttVal != null) && (localAttVal.value != null) && (localAttVal.value.indexOf("Microsoft") != -1)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  static void bumpObject(Lexer paramLexer, Node paramNode)
  {
    if (paramNode == null) {
      return;
    }
    Object localObject2 = null;
    Object localObject3 = null;
    TagTable localTagTable = paramLexer.configuration.field_1881;
    for (Object localObject1 = paramNode.content; localObject1 != null; localObject1 = ((Node)localObject1).next)
    {
      if (((Node)localObject1).tag == localTagTable.tagHead) {
        localObject2 = localObject1;
      }
      if (((Node)localObject1).tag == localTagTable.tagBody) {
        localObject3 = localObject1;
      }
    }
    if ((localObject2 != null) && (localObject3 != null))
    {
      Node localNode1;
      for (localObject1 = localObject2.content; localObject1 != null; localObject1 = localNode1)
      {
        localNode1 = ((Node)localObject1).next;
        if (((Node)localObject1).tag == localTagTable.tagObject)
        {
          int i = 0;
          for (Node localNode2 = ((Node)localObject1).content; localNode2 != null; localNode2 = localNode2.next) {
            if (((localNode2.type == 4) && (!((Node)localObject1).isBlank(paramLexer))) || (localNode2.tag != localTagTable.tagParam))
            {
              i = 1;
              break;
            }
          }
          if (i != 0)
          {
            ((Node)localObject1).removeNode();
            localObject3.insertNodeAtStart((Node)localObject1);
          }
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.Clean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */