package org.w3c.tidy;

public class Node
{
  public static final short ROOT_NODE = 0;
  public static final short DOCTYPE_TAG = 1;
  public static final short COMMENT_TAG = 2;
  public static final short PROC_INS_TAG = 3;
  public static final short TEXT_NODE = 4;
  public static final short START_TAG = 5;
  public static final short END_TAG = 6;
  public static final short START_END_TAG = 7;
  public static final short CDATA_TAG = 8;
  public static final short SECTION_TAG = 9;
  public static final short ASP_TAG = 10;
  public static final short JSTE_TAG = 11;
  public static final short PHP_TAG = 12;
  public static final short XML_DECL = 13;
  private static final String[] NODETYPE_STRING = { "RootNode", "DocTypeTag", "CommentTag", "ProcInsTag", "TextNode", "StartTag", "EndTag", "StartEndTag", "SectionTag", "AspTag", "PhpTag", "XmlDecl" };
  protected Node parent = null;
  protected Node prev = null;
  protected Node next = null;
  protected Node last = null;
  protected int start;
  protected int end;
  protected byte[] textarray;
  protected short type;
  protected boolean closed;
  protected boolean implicit;
  protected boolean linebreak;
  protected Dict was;
  protected Dict tag;
  protected String element;
  protected AttVal attributes;
  protected Node content;
  protected org.w3c.dom.Node adapter;
  
  public Node()
  {
    this((short)4, null, 0, 0);
  }
  
  public Node(short paramShort, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.start = paramInt1;
    this.end = paramInt2;
    this.textarray = paramArrayOfByte;
    this.type = paramShort;
    this.closed = false;
    this.implicit = false;
    this.linebreak = false;
    this.was = null;
    this.tag = null;
    this.element = null;
    this.attributes = null;
    this.content = null;
  }
  
  public Node(short paramShort, byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString, TagTable paramTagTable)
  {
    this.start = paramInt1;
    this.end = paramInt2;
    this.textarray = paramArrayOfByte;
    this.type = paramShort;
    this.closed = false;
    this.implicit = false;
    this.linebreak = false;
    this.was = null;
    this.tag = null;
    this.element = paramString;
    this.attributes = null;
    this.content = null;
    if ((paramShort == 5) || (paramShort == 7) || (paramShort == 6)) {
      paramTagTable.findTag(this);
    }
  }
  
  public AttVal getAttrByName(String paramString)
  {
    for (AttVal localAttVal = this.attributes; (localAttVal != null) && ((paramString == null) || (localAttVal.attribute == null) || (!localAttVal.attribute.equals(paramString))); localAttVal = localAttVal.next) {}
    return localAttVal;
  }
  
  public void checkAttributes(Lexer paramLexer)
  {
    for (AttVal localAttVal = this.attributes; localAttVal != null; localAttVal = localAttVal.next) {
      localAttVal.checkAttribute(paramLexer, this);
    }
  }
  
  public void repairDuplicateAttributes(Lexer paramLexer)
  {
    Object localObject1 = this.attributes;
    while (localObject1 != null) {
      if ((((AttVal)localObject1).asp == null) && (((AttVal)localObject1).php == null))
      {
        Object localObject2 = ((AttVal)localObject1).next;
        while (localObject2 != null) {
          if ((((AttVal)localObject2).asp == null) && (((AttVal)localObject2).php == null) && (((AttVal)localObject1).attribute != null) && (((AttVal)localObject1).attribute.equalsIgnoreCase(((AttVal)localObject2).attribute)))
          {
            AttVal localAttVal;
            if (("class".equalsIgnoreCase(((AttVal)localObject2).attribute)) && (paramLexer.configuration.joinClasses))
            {
              ((AttVal)localObject2).value = (((AttVal)localObject2).value + " " + ((AttVal)localObject1).value);
              localAttVal = ((AttVal)localObject1).next;
              if (localAttVal.next == null) {
                localObject2 = null;
              } else {
                localObject2 = ((AttVal)localObject2).next;
              }
              paramLexer.report.attrError(paramLexer, this, (AttVal)localObject1, (short)68);
              removeAttribute((AttVal)localObject1);
              localObject1 = localAttVal;
            }
            else if (("style".equalsIgnoreCase(((AttVal)localObject2).attribute)) && (paramLexer.configuration.joinStyles))
            {
              int i = ((AttVal)localObject2).value.length() - 1;
              if (((AttVal)localObject2).value.charAt(i) == ';') {
                ((AttVal)localObject2).value = (((AttVal)localObject2).value + " " + ((AttVal)localObject1).value);
              } else if (((AttVal)localObject2).value.charAt(i) == '}') {
                ((AttVal)localObject2).value = (((AttVal)localObject2).value + " { " + ((AttVal)localObject1).value + " }");
              } else {
                ((AttVal)localObject2).value = (((AttVal)localObject2).value + "; " + ((AttVal)localObject1).value);
              }
              localAttVal = ((AttVal)localObject1).next;
              if (localAttVal.next == null) {
                localObject2 = null;
              } else {
                localObject2 = ((AttVal)localObject2).next;
              }
              paramLexer.report.attrError(paramLexer, this, (AttVal)localObject1, (short)68);
              removeAttribute((AttVal)localObject1);
              localObject1 = localAttVal;
            }
            else if (paramLexer.configuration.duplicateAttrs == 0)
            {
              localAttVal = ((AttVal)localObject2).next;
              paramLexer.report.attrError(paramLexer, this, (AttVal)localObject2, (short)55);
              removeAttribute((AttVal)localObject2);
              localObject2 = localAttVal;
            }
            else
            {
              localAttVal = ((AttVal)localObject1).next;
              if (((AttVal)localObject1).next == null) {
                localObject2 = null;
              } else {
                localObject2 = ((AttVal)localObject2).next;
              }
              paramLexer.report.attrError(paramLexer, this, (AttVal)localObject1, (short)55);
              removeAttribute((AttVal)localObject1);
              localObject1 = localAttVal;
            }
          }
          else
          {
            localObject2 = ((AttVal)localObject2).next;
          }
        }
        localObject1 = ((AttVal)localObject1).next;
      }
      else
      {
        localObject1 = ((AttVal)localObject1).next;
      }
    }
  }
  
  public void addAttribute(String paramString1, String paramString2)
  {
    AttVal localAttVal1 = new AttVal(null, null, null, null, 34, paramString1, paramString2);
    localAttVal1.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal1);
    if (this.attributes == null)
    {
      this.attributes = localAttVal1;
    }
    else
    {
      for (AttVal localAttVal2 = this.attributes; localAttVal2.next != null; localAttVal2 = localAttVal2.next) {}
      localAttVal2.next = localAttVal1;
    }
  }
  
  public void removeAttribute(AttVal paramAttVal)
  {
    Object localObject2 = null;
    AttVal localAttVal;
    for (Object localObject1 = this.attributes; localObject1 != null; localObject1 = localAttVal)
    {
      localAttVal = ((AttVal)localObject1).next;
      if (localObject1 == paramAttVal)
      {
        if (localObject2 != null) {
          localObject2.next = localAttVal;
        } else {
          this.attributes = localAttVal;
        }
      }
      else {
        localObject2 = localObject1;
      }
    }
  }
  
  public Node findDocType()
  {
    for (Node localNode = this.content; (localNode != null) && (localNode.type != 1); localNode = localNode.next) {}
    return localNode;
  }
  
  public void discardDocType()
  {
    Node localNode = findDocType();
    if (localNode != null)
    {
      if (localNode.prev != null) {
        localNode.prev.next = localNode.next;
      } else {
        localNode.parent.content = localNode.next;
      }
      if (localNode.next != null) {
        localNode.next.prev = localNode.prev;
      }
      localNode.next = null;
    }
  }
  
  public static Node discardElement(Node paramNode)
  {
    Node localNode = null;
    if (paramNode != null)
    {
      localNode = paramNode.next;
      paramNode.removeNode();
    }
    return localNode;
  }
  
  public void insertNodeAtStart(Node paramNode)
  {
    paramNode.parent = this;
    if (this.content == null) {
      this.last = paramNode;
    } else {
      this.content.prev = paramNode;
    }
    paramNode.next = this.content;
    paramNode.prev = null;
    this.content = paramNode;
  }
  
  public void insertNodeAtEnd(Node paramNode)
  {
    paramNode.parent = this;
    paramNode.prev = this.last;
    if (this.last != null) {
      this.last.next = paramNode;
    } else {
      this.content = paramNode;
    }
    this.last = paramNode;
  }
  
  public static void insertNodeAsParent(Node paramNode1, Node paramNode2)
  {
    paramNode2.content = paramNode1;
    paramNode2.last = paramNode1;
    paramNode2.parent = paramNode1.parent;
    paramNode1.parent = paramNode2;
    if (paramNode2.parent.content == paramNode1) {
      paramNode2.parent.content = paramNode2;
    }
    if (paramNode2.parent.last == paramNode1) {
      paramNode2.parent.last = paramNode2;
    }
    paramNode2.prev = paramNode1.prev;
    paramNode1.prev = null;
    if (paramNode2.prev != null) {
      paramNode2.prev.next = paramNode2;
    }
    paramNode2.next = paramNode1.next;
    paramNode1.next = null;
    if (paramNode2.next != null) {
      paramNode2.next.prev = paramNode2;
    }
  }
  
  public static void insertNodeBeforeElement(Node paramNode1, Node paramNode2)
  {
    Node localNode = paramNode1.parent;
    paramNode2.parent = localNode;
    paramNode2.next = paramNode1;
    paramNode2.prev = paramNode1.prev;
    paramNode1.prev = paramNode2;
    if (paramNode2.prev != null) {
      paramNode2.prev.next = paramNode2;
    }
    if ((localNode != null) && (localNode.content == paramNode1)) {
      localNode.content = paramNode2;
    }
  }
  
  public void insertNodeAfterElement(Node paramNode)
  {
    Node localNode = this.parent;
    paramNode.parent = localNode;
    if ((localNode != null) && (localNode.last == this))
    {
      localNode.last = paramNode;
    }
    else
    {
      paramNode.next = this.next;
      if (paramNode.next != null) {
        paramNode.next.prev = paramNode;
      }
    }
    this.next = paramNode;
    paramNode.prev = this;
  }
  
  public static void trimEmptyElement(Lexer paramLexer, Node paramNode)
  {
    if (paramLexer.configuration.trimEmpty)
    {
      TagTable localTagTable = paramLexer.configuration.tt;
      if (paramLexer.canPrune(paramNode))
      {
        if (paramNode.type != 4) {
          paramLexer.report.warning(paramLexer, paramNode, null, (short)23);
        }
        discardElement(paramNode);
      }
      else if ((paramNode.tag == localTagTable.tagP) && (paramNode.content == null))
      {
        Node localNode = paramLexer.inferredTag("br");
        coerceNode(paramLexer, paramNode, localTagTable.tagBr);
        paramNode.insertNodeAfterElement(localNode);
      }
    }
  }
  
  public static void trimTrailingSpace(Lexer paramLexer, Node paramNode1, Node paramNode2)
  {
    TagTable localTagTable = paramLexer.configuration.tt;
    if ((paramNode2 != null) && (paramNode2.type == 4))
    {
      if (paramNode2.end > paramNode2.start)
      {
        int i = paramLexer.lexbuf[(paramNode2.end - 1)];
        if ((i == 160) || (i == 32)) {
          if ((i == 160) && ((paramNode1.tag == localTagTable.tagTd) || (paramNode1.tag == localTagTable.tagTh)))
          {
            if (paramNode2.end > paramNode2.start + 1) {
              paramNode2.end -= 1;
            }
          }
          else
          {
            paramNode2.end -= 1;
            if ((TidyUtils.toBoolean(paramNode1.tag.model & 0x10)) && (!TidyUtils.toBoolean(paramNode1.tag.model & 0x400))) {
              paramLexer.insertspace = true;
            }
          }
        }
      }
      if (paramNode2.start == paramNode2.end) {
        trimEmptyElement(paramLexer, paramNode2);
      }
    }
  }
  
  protected static Node escapeTag(Lexer paramLexer, Node paramNode)
  {
    Node localNode = paramLexer.newNode();
    localNode.start = paramLexer.lexsize;
    localNode.textarray = paramNode.textarray;
    paramLexer.addByte(60);
    if (paramNode.type == 6) {
      paramLexer.addByte(47);
    }
    if (paramNode.element != null)
    {
      paramLexer.addStringLiteral(paramNode.element);
    }
    else if (paramNode.type == 1)
    {
      paramLexer.addByte(33);
      paramLexer.addByte(68);
      paramLexer.addByte(79);
      paramLexer.addByte(67);
      paramLexer.addByte(84);
      paramLexer.addByte(89);
      paramLexer.addByte(80);
      paramLexer.addByte(69);
      paramLexer.addByte(32);
      for (int i = paramNode.start; i < paramNode.end; i++) {
        paramLexer.addByte(paramLexer.lexbuf[i]);
      }
    }
    if (paramNode.type == 7) {
      paramLexer.addByte(47);
    }
    paramLexer.addByte(62);
    localNode.end = paramLexer.lexsize;
    return localNode;
  }
  
  public boolean isBlank(Lexer paramLexer)
  {
    if (this.type == 4)
    {
      if (this.end == this.start) {
        return true;
      }
      if ((this.end == this.start + 1) && (paramLexer.lexbuf[(this.end - 1)] == 32)) {
        return true;
      }
    }
    return false;
  }
  
  public static void trimInitialSpace(Lexer paramLexer, Node paramNode1, Node paramNode2)
  {
    if ((paramNode2.type == 4) && (paramNode2.textarray[paramNode2.start] == 32) && (paramNode2.start < paramNode2.end))
    {
      if ((TidyUtils.toBoolean(paramNode1.tag.model & 0x10)) && (!TidyUtils.toBoolean(paramNode1.tag.model & 0x400)) && (paramNode1.parent.content != paramNode1))
      {
        Node localNode1 = paramNode1.prev;
        if ((localNode1 != null) && (localNode1.type == 4))
        {
          if (localNode1.textarray[(localNode1.end - 1)] != 32) {
            localNode1.textarray[(localNode1.end++)] = 32;
          }
          paramNode1.start += 1;
        }
        else
        {
          Node localNode2 = paramLexer.newNode();
          if (paramNode1.start >= paramNode1.end)
          {
            localNode2.start = 0;
            localNode2.end = 1;
            localNode2.textarray = new byte[1];
          }
          else
          {
            localNode2.start = (paramNode1.start++);
            localNode2.end = paramNode1.start;
            localNode2.textarray = paramNode1.textarray;
          }
          localNode2.textarray[localNode2.start] = 32;
          localNode2.prev = localNode1;
          if (localNode1 != null) {
            localNode1.next = localNode2;
          }
          localNode2.next = paramNode1;
          paramNode1.prev = localNode2;
          localNode2.parent = paramNode1.parent;
        }
      }
      paramNode2.start += 1;
    }
  }
  
  public static void trimSpaces(Lexer paramLexer, Node paramNode)
  {
    Node localNode = paramNode.content;
    TagTable localTagTable = paramLexer.configuration.tt;
    if ((localNode != null) && (localNode.type == 4) && (paramNode.tag != localTagTable.tagPre)) {
      trimInitialSpace(paramLexer, paramNode, localNode);
    }
    localNode = paramNode.last;
    if ((localNode != null) && (localNode.type == 4)) {
      trimTrailingSpace(paramLexer, paramNode, localNode);
    }
  }
  
  public boolean isDescendantOf(Dict paramDict)
  {
    for (Node localNode = this.parent; localNode != null; localNode = localNode.parent) {
      if (localNode.tag == paramDict) {
        return true;
      }
    }
    return false;
  }
  
  public static void insertDocType(Lexer paramLexer, Node paramNode1, Node paramNode2)
  {
    TagTable localTagTable = paramLexer.configuration.tt;
    paramLexer.report.warning(paramLexer, paramNode1, paramNode2, (short)34);
    while (paramNode1.tag != localTagTable.tagHtml) {
      paramNode1 = paramNode1.parent;
    }
    insertNodeBeforeElement(paramNode1, paramNode2);
  }
  
  public Node findBody(TagTable paramTagTable)
  {
    for (Node localNode = this.content; (localNode != null) && (localNode.tag != paramTagTable.tagHtml); localNode = localNode.next) {}
    if (localNode == null) {
      return null;
    }
    for (localNode = localNode.content; (localNode != null) && (localNode.tag != paramTagTable.tagBody) && (localNode.tag != paramTagTable.tagFrameset); localNode = localNode.next) {}
    if (localNode.tag == paramTagTable.tagFrameset)
    {
      for (localNode = localNode.content; (localNode != null) && (localNode.tag != paramTagTable.tagNoframes); localNode = localNode.next) {}
      if (localNode != null) {
        for (localNode = localNode.content; (localNode != null) && (localNode.tag != paramTagTable.tagBody); localNode = localNode.next) {}
      }
    }
    return localNode;
  }
  
  public boolean isElement()
  {
    return (this.type == 5) || (this.type == 7);
  }
  
  public static void moveBeforeTable(Node paramNode1, Node paramNode2, TagTable paramTagTable)
  {
    for (Node localNode = paramNode1.parent; localNode != null; localNode = localNode.parent) {
      if (localNode.tag == paramTagTable.tagTable)
      {
        if (localNode.parent.content == localNode) {
          localNode.parent.content = paramNode2;
        }
        paramNode2.prev = localNode.prev;
        paramNode2.next = localNode;
        localNode.prev = paramNode2;
        paramNode2.parent = localNode.parent;
        if (paramNode2.prev == null) {
          break;
        }
        paramNode2.prev.next = paramNode2;
        break;
      }
    }
  }
  
  public static void fixEmptyRow(Lexer paramLexer, Node paramNode)
  {
    if (paramNode.content == null)
    {
      Node localNode = paramLexer.inferredTag("td");
      paramNode.insertNodeAtEnd(localNode);
      paramLexer.report.warning(paramLexer, paramNode, localNode, (short)12);
    }
  }
  
  public static void coerceNode(Lexer paramLexer, Node paramNode, Dict paramDict)
  {
    Node localNode = paramLexer.inferredTag(paramDict.name);
    paramLexer.report.warning(paramLexer, paramNode, localNode, (short)20);
    paramNode.was = paramNode.tag;
    paramNode.tag = paramDict;
    paramNode.type = 5;
    paramNode.implicit = true;
    paramNode.element = paramDict.name;
  }
  
  public void removeNode()
  {
    if (this.prev != null) {
      this.prev.next = this.next;
    }
    if (this.next != null) {
      this.next.prev = this.prev;
    }
    if (this.parent != null)
    {
      if (this.parent.content == this) {
        this.parent.content = this.next;
      }
      if (this.parent.last == this) {
        this.parent.last = this.prev;
      }
    }
    this.parent = null;
    this.prev = null;
    this.next = null;
  }
  
  public static boolean insertMisc(Node paramNode1, Node paramNode2)
  {
    if ((paramNode2.type == 2) || (paramNode2.type == 3) || (paramNode2.type == 8) || (paramNode2.type == 9) || (paramNode2.type == 10) || (paramNode2.type == 11) || (paramNode2.type == 12) || (paramNode2.type == 13))
    {
      paramNode1.insertNodeAtEnd(paramNode2);
      return true;
    }
    return false;
  }
  
  public boolean isNewNode()
  {
    if (this.tag != null) {
      return TidyUtils.toBoolean(this.tag.model & 0x100000);
    }
    return true;
  }
  
  public boolean hasOneChild()
  {
    return (this.content != null) && (this.content.next == null);
  }
  
  public Node findHTML(TagTable paramTagTable)
  {
    for (Node localNode = this.content; (localNode != null) && (localNode.tag != paramTagTable.tagHtml); localNode = localNode.next) {}
    return localNode;
  }
  
  public Node findHEAD(TagTable paramTagTable)
  {
    Node localNode = findHTML(paramTagTable);
    if (localNode != null) {
      for (localNode = localNode.content; (localNode != null) && (localNode.tag != paramTagTable.tagHead); localNode = localNode.next) {}
    }
    return localNode;
  }
  
  public Node findTITLE(TagTable paramTagTable)
  {
    Node localNode = findHEAD(paramTagTable);
    if (localNode != null) {
      for (localNode = localNode.content; (localNode != null) && (localNode.tag != paramTagTable.tagTitle); localNode = localNode.next) {}
    }
    return localNode;
  }
  
  public boolean checkNodeIntegrity()
  {
    if ((this.prev != null) && (this.prev.next != this)) {
      return false;
    }
    if ((this.next != null) && ((this.next == this) || (this.next.prev != this))) {
      return false;
    }
    if (this.parent != null)
    {
      if ((this.prev == null) && (this.parent.content != this)) {
        return false;
      }
      if ((this.next == null) && (this.parent.last != this)) {
        return false;
      }
    }
    for (Node localNode = this.content; localNode != null; localNode = localNode.next) {
      if ((localNode.parent != this) || (!localNode.checkNodeIntegrity())) {
        return false;
      }
    }
    return true;
  }
  
  public void addClass(String paramString)
  {
    AttVal localAttVal = getAttrByName("class");
    if (localAttVal != null) {
      localAttVal.value = (localAttVal.value + " " + paramString);
    } else {
      addAttribute("class", paramString);
    }
  }
  
  public String toString()
  {
    String str = "";
    for (Node localNode = this; localNode != null; localNode = localNode.next)
    {
      str = str + "[Node type=";
      str = str + NODETYPE_STRING[localNode.type];
      str = str + ",element=";
      if (localNode.element != null) {
        str = str + localNode.element;
      } else {
        str = str + "null";
      }
      if ((localNode.type == 4) || (localNode.type == 2) || (localNode.type == 3))
      {
        str = str + ",text=";
        if ((localNode.textarray != null) && (localNode.start <= localNode.end))
        {
          str = str + "\"";
          str = str + TidyUtils.getString(localNode.textarray, localNode.start, localNode.end - localNode.start);
          str = str + "\"";
        }
        else
        {
          str = str + "null";
        }
      }
      str = str + ",content=";
      if (localNode.content != null) {
        str = str + localNode.content.toString();
      } else {
        str = str + "null";
      }
      str = str + "]";
      if (localNode.next != null) {
        str = str + ",";
      }
    }
    return str;
  }
  
  protected org.w3c.dom.Node getAdapter()
  {
    if (this.adapter == null) {
      switch (this.type)
      {
      case 0: 
        this.adapter = new DOMDocumentImpl(this);
        break;
      case 5: 
      case 7: 
        this.adapter = new DOMElementImpl(this);
        break;
      case 1: 
        this.adapter = new DOMDocumentTypeImpl(this);
        break;
      case 2: 
        this.adapter = new DOMCommentImpl(this);
        break;
      case 4: 
        this.adapter = new DOMTextImpl(this);
        break;
      case 8: 
        this.adapter = new DOMCDATASectionImpl(this);
        break;
      case 3: 
        this.adapter = new DOMProcessingInstructionImpl(this);
        break;
      case 6: 
      default: 
        this.adapter = new DOMNodeImpl(this);
      }
    }
    return this.adapter;
  }
  
  protected Node cloneNode(boolean paramBoolean)
  {
    Node localNode1 = new Node(this.type, this.textarray, this.start, this.end);
    localNode1.parent = this.parent;
    localNode1.closed = this.closed;
    localNode1.implicit = this.implicit;
    localNode1.tag = this.tag;
    localNode1.element = this.element;
    if (this.attributes != null) {
      localNode1.attributes = ((AttVal)this.attributes.clone());
    }
    if (paramBoolean) {
      for (Node localNode2 = this.content; localNode2 != null; localNode2 = localNode2.next)
      {
        Node localNode3 = localNode2.cloneNode(paramBoolean);
        localNode1.insertNodeAtEnd(localNode3);
      }
    }
    return localNode1;
  }
  
  protected void setType(short paramShort)
  {
    this.type = paramShort;
  }
  
  public boolean isJavaScript()
  {
    boolean bool = false;
    if (this.attributes == null) {
      return true;
    }
    for (AttVal localAttVal = this.attributes; localAttVal != null; localAttVal = localAttVal.next) {
      if ((("language".equalsIgnoreCase(localAttVal.attribute)) || ("type".equalsIgnoreCase(localAttVal.attribute))) && ("javascript".equalsIgnoreCase(localAttVal.value))) {
        bool = true;
      }
    }
    return bool;
  }
  
  public boolean expectsContent()
  {
    if (this.type != 5) {
      return false;
    }
    if (this.tag == null) {
      return true;
    }
    return !TidyUtils.toBoolean(this.tag.model & 0x1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.Node
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */