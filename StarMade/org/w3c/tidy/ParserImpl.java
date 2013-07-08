package org.w3c.tidy;

import java.util.Stack;

public final class ParserImpl
{
  public static final Parser HTML = new ParseHTML();
  public static final Parser HEAD = new ParseHead();
  public static final Parser TITLE = new ParseTitle();
  public static final Parser SCRIPT = new ParseScript();
  public static final Parser BODY = new ParseBody();
  public static final Parser FRAMESET = new ParseFrameSet();
  public static final Parser INLINE = new ParseInline();
  public static final Parser LIST = new ParseList();
  public static final Parser DEFLIST = new ParseDefList();
  public static final Parser PRE = new ParsePre();
  public static final Parser BLOCK = new ParseBlock();
  public static final Parser TABLETAG = new ParseTableTag();
  public static final Parser COLGROUP = new ParseColGroup();
  public static final Parser ROWGROUP = new ParseRowGroup();
  public static final Parser ROW = new ParseRow();
  public static final Parser NOFRAMES = new ParseNoFrames();
  public static final Parser SELECT = new ParseSelect();
  public static final Parser TEXT = new ParseText();
  public static final Parser EMPTY = new ParseEmpty();
  public static final Parser OPTGROUP = new ParseOptGroup();
  
  protected static void parseTag(Lexer paramLexer, Node paramNode, short paramShort)
  {
    if ((paramNode.tag.model & 0x1) != 0) {
      paramLexer.waswhite = false;
    } else if ((paramNode.tag.model & 0x10) == 0) {
      paramLexer.insertspace = false;
    }
    if (paramNode.tag.getParser() == null) {
      return;
    }
    if (paramNode.type == 7)
    {
      Node.trimEmptyElement(paramLexer, paramNode);
      return;
    }
    paramNode.tag.getParser().parse(paramLexer, paramNode, paramShort);
  }
  
  protected static void moveToHead(Lexer paramLexer, Node paramNode1, Node paramNode2)
  {
    paramNode2.removeNode();
    TagTable localTagTable = paramLexer.configuration.field_1881;
    if ((paramNode2.type == 5) || (paramNode2.type == 7))
    {
      paramLexer.report.warning(paramLexer, paramNode1, paramNode2, (short)11);
      while (paramNode1.tag != localTagTable.tagHtml) {
        paramNode1 = paramNode1.parent;
      }
      for (Node localNode = paramNode1.content; localNode != null; localNode = localNode.next) {
        if (localNode.tag == localTagTable.tagHead)
        {
          localNode.insertNodeAtEnd(paramNode2);
          break;
        }
      }
      if (paramNode2.tag.getParser() != null) {
        parseTag(paramLexer, paramNode2, (short)0);
      }
    }
    else
    {
      paramLexer.report.warning(paramLexer, paramNode1, paramNode2, (short)8);
    }
  }
  
  static void moveNodeToBody(Lexer paramLexer, Node paramNode)
  {
    paramNode.removeNode();
    Node localNode = paramLexer.root.findBody(paramLexer.configuration.field_1881);
    localNode.insertNodeAtEnd(paramNode);
  }
  
  public static Node parseDocument(Lexer paramLexer)
  {
    Object localObject = null;
    TagTable localTagTable = paramLexer.configuration.field_1881;
    Node localNode2 = paramLexer.newNode();
    localNode2.type = 0;
    paramLexer.root = localNode2;
    Node localNode1;
    Node localNode3;
    while ((localNode1 = paramLexer.getToken((short)0)) != null) {
      if (!Node.insertMisc(localNode2, localNode1)) {
        if (localNode1.type == 1)
        {
          if (localObject == null)
          {
            localNode2.insertNodeAtEnd(localNode1);
            localObject = localNode1;
          }
          else
          {
            paramLexer.report.warning(paramLexer, localNode2, localNode1, (short)8);
          }
        }
        else if (localNode1.type == 6)
        {
          paramLexer.report.warning(paramLexer, localNode2, localNode1, (short)8);
        }
        else
        {
          if ((localNode1.type != 5) || (localNode1.tag != localTagTable.tagHtml))
          {
            paramLexer.ungetToken();
            localNode3 = paramLexer.inferredTag("html");
          }
          else
          {
            localNode3 = localNode1;
          }
          if ((localNode2.findDocType() == null) && (!paramLexer.configuration.bodyOnly)) {
            paramLexer.report.warning(paramLexer, null, null, (short)44);
          }
          localNode2.insertNodeAtEnd(localNode3);
          HTML.parse(paramLexer, localNode3, (short)0);
        }
      }
    }
    if (paramLexer.root.findHTML(paramLexer.configuration.field_1881) == null)
    {
      localNode3 = paramLexer.inferredTag("html");
      paramLexer.root.insertNodeAtEnd(localNode3);
      HTML.parse(paramLexer, localNode3, (short)0);
    }
    if (paramLexer.root.findTITLE(paramLexer.configuration.field_1881) == null)
    {
      Node localNode4 = paramLexer.root.findHEAD(paramLexer.configuration.field_1881);
      paramLexer.report.warning(paramLexer, localNode4, null, (short)17);
      localNode4.insertNodeAtEnd(paramLexer.inferredTag("title"));
    }
    return localNode2;
  }
  
  public static boolean XMLPreserveWhiteSpace(Node paramNode, TagTable paramTagTable)
  {
    for (AttVal localAttVal = paramNode.attributes; localAttVal != null; localAttVal = localAttVal.next) {
      if (localAttVal.attribute.equals("xml:space")) {
        return localAttVal.value.equals("preserve");
      }
    }
    if (paramNode.element == null) {
      return false;
    }
    if (("pre".equalsIgnoreCase(paramNode.element)) || ("script".equalsIgnoreCase(paramNode.element)) || ("style".equalsIgnoreCase(paramNode.element))) {
      return true;
    }
    if ((paramTagTable != null) && (paramTagTable.findParser(paramNode) == PRE)) {
      return true;
    }
    return "xsl:text".equalsIgnoreCase(paramNode.element);
  }
  
  public static void parseXMLElement(Lexer paramLexer, Node paramNode, short paramShort)
  {
    if (XMLPreserveWhiteSpace(paramNode, paramLexer.configuration.field_1881)) {
      paramShort = 2;
    }
    while ((localNode = paramLexer.getToken(paramShort)) != null)
    {
      if ((localNode.type == 6) && (localNode.element.equals(paramNode.element)))
      {
        paramNode.closed = true;
        break;
      }
      if (localNode.type == 6)
      {
        paramLexer.report.error(paramLexer, paramNode, localNode, (short)13);
      }
      else
      {
        if (localNode.type == 5) {
          parseXMLElement(paramLexer, localNode, paramShort);
        }
        paramNode.insertNodeAtEnd(localNode);
      }
    }
    Node localNode = paramNode.content;
    if ((localNode != null) && (localNode.type == 4) && (paramShort != 2) && (localNode.textarray[localNode.start] == 32))
    {
      localNode.start += 1;
      if (localNode.start >= localNode.end) {
        Node.discardElement(localNode);
      }
    }
    localNode = paramNode.last;
    if ((localNode != null) && (localNode.type == 4) && (paramShort != 2) && (localNode.textarray[(localNode.end - 1)] == 32))
    {
      localNode.end -= 1;
      if (localNode.start >= localNode.end) {
        Node.discardElement(localNode);
      }
    }
  }
  
  public static Node parseXMLDocument(Lexer paramLexer)
  {
    Node localNode2 = paramLexer.newNode();
    localNode2.type = 0;
    Object localObject = null;
    paramLexer.configuration.xmlTags = true;
    Node localNode1;
    while ((localNode1 = paramLexer.getToken((short)0)) != null) {
      if (localNode1.type == 6) {
        paramLexer.report.warning(paramLexer, null, localNode1, (short)13);
      } else if (!Node.insertMisc(localNode2, localNode1)) {
        if (localNode1.type == 1)
        {
          if (localObject == null)
          {
            localNode2.insertNodeAtEnd(localNode1);
            localObject = localNode1;
          }
          else
          {
            paramLexer.report.warning(paramLexer, localNode2, localNode1, (short)8);
          }
        }
        else if (localNode1.type == 7)
        {
          localNode2.insertNodeAtEnd(localNode1);
        }
        else if (localNode1.type == 5)
        {
          localNode2.insertNodeAtEnd(localNode1);
          parseXMLElement(paramLexer, localNode1, (short)0);
        }
      }
    }
    if ((localObject != null) && (!paramLexer.checkDocTypeKeyWords(localObject))) {
      paramLexer.report.warning(paramLexer, localObject, null, (short)37);
    }
    if (paramLexer.configuration.xmlPi) {
      paramLexer.fixXmlDecl(localNode2);
    }
    return localNode2;
  }
  
  static void badForm(Lexer paramLexer)
  {
    paramLexer.badForm = 1;
    paramLexer.errors = ((short)(paramLexer.errors + 1));
  }
  
  public static class ParseOptGroup
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      paramLexer.insert = -1;
      Node localNode;
      while ((localNode = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode.tag == paramNode.tag) && (localNode.type == 6))
        {
          paramNode.closed = true;
          Node.trimSpaces(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode)) {
          if ((localNode.type == 5) && ((localNode.tag == localTagTable.tagOption) || (localNode.tag == localTagTable.tagOptgroup)))
          {
            if (localNode.tag == localTagTable.tagOptgroup) {
              paramLexer.report.warning(paramLexer, paramNode, localNode, (short)19);
            }
            paramNode.insertNodeAtEnd(localNode);
            ParserImpl.parseTag(paramLexer, localNode, (short)1);
          }
          else
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
          }
        }
      }
    }
  }
  
  public static class ParseText
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      paramLexer.insert = -1;
      if (paramNode.tag == localTagTable.tagTextarea) {
        paramShort = 2;
      } else {
        paramShort = 1;
      }
      Node localNode;
      while ((localNode = paramLexer.getToken(paramShort)) != null)
      {
        if ((localNode.tag == paramNode.tag) && (localNode.type == 6))
        {
          paramNode.closed = true;
          Node.trimSpaces(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode)) {
          if (localNode.type == 4)
          {
            if ((paramNode.content == null) && ((paramShort & 0x2) == 0)) {
              Node.trimSpaces(paramLexer, paramNode);
            }
            if (localNode.start < localNode.end) {
              paramNode.insertNodeAtEnd(localNode);
            }
          }
          else if ((localNode.tag != null) && ((localNode.tag.model & 0x10) != 0) && ((localNode.tag.model & 0x400) == 0))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
          }
          else
          {
            if ((paramNode.tag.model & 0x8000) == 0) {
              paramLexer.report.warning(paramLexer, paramNode, localNode, (short)7);
            }
            paramLexer.ungetToken();
            Node.trimSpaces(paramLexer, paramNode);
            return;
          }
        }
      }
      if ((paramNode.tag.model & 0x8000) == 0) {
        paramLexer.report.warning(paramLexer, paramNode, localNode, (short)6);
      }
    }
  }
  
  public static class ParseSelect
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      paramLexer.insert = -1;
      Node localNode;
      while ((localNode = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode.tag == paramNode.tag) && (localNode.type == 6))
        {
          paramNode.closed = true;
          Node.trimSpaces(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode)) {
          if ((localNode.type == 5) && ((localNode.tag == localTagTable.tagOption) || (localNode.tag == localTagTable.tagOptgroup) || (localNode.tag == localTagTable.tagScript)))
          {
            paramNode.insertNodeAtEnd(localNode);
            ParserImpl.parseTag(paramLexer, localNode, (short)0);
          }
          else
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
          }
        }
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode, (short)6);
    }
  }
  
  public static class ParseNoFrames
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      Lexer tmp10_9 = paramLexer;
      tmp10_9.badAccess = ((short)(tmp10_9.badAccess | 0x20));
      paramShort = 0;
      Node localNode1;
      while ((localNode1 = paramLexer.getToken(paramShort)) != null)
      {
        if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
        {
          paramNode.closed = true;
          Node.trimSpaces(paramLexer, paramNode);
          return;
        }
        if ((localNode1.tag == localTagTable.tagFrame) || (localNode1.tag == localTagTable.tagFrameset))
        {
          Node.trimSpaces(paramLexer, paramNode);
          if (localNode1.type == 6)
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
            paramLexer.ungetToken();
          }
          return;
        }
        if (localNode1.tag == localTagTable.tagHtml)
        {
          if ((localNode1.type == 5) || (localNode1.type == 7)) {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
        }
        else if (!Node.insertMisc(paramNode, localNode1)) {
          if ((localNode1.tag == localTagTable.tagBody) && (localNode1.type == 5))
          {
            boolean bool = paramLexer.seenEndBody;
            paramNode.insertNodeAtEnd(localNode1);
            ParserImpl.parseTag(paramLexer, localNode1, (short)0);
            if (bool)
            {
              Node.coerceNode(paramLexer, localNode1, localTagTable.tagDiv);
              ParserImpl.moveNodeToBody(paramLexer, localNode1);
            }
          }
          else if ((localNode1.type == 4) || ((localNode1.tag != null) && (localNode1.type != 6)))
          {
            if (paramLexer.seenEndBody)
            {
              Node localNode2 = paramLexer.root.findBody(localTagTable);
              if (localNode1.type == 4)
              {
                paramLexer.ungetToken();
                localNode1 = paramLexer.inferredTag("p");
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)27);
              }
              localNode2.insertNodeAtEnd(localNode1);
            }
            else
            {
              paramLexer.ungetToken();
              localNode1 = paramLexer.inferredTag("body");
              if (paramLexer.configuration.xmlOut) {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)15);
              }
              paramNode.insertNodeAtEnd(localNode1);
            }
            ParserImpl.parseTag(paramLexer, localNode1, (short)0);
          }
          else
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
        }
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)6);
    }
  }
  
  public static class ParseRow
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      if ((paramNode.tag.model & 0x1) != 0) {
        return;
      }
      Node localNode1;
      while ((localNode1 = paramLexer.getToken((short)0)) != null)
      {
        if (localNode1.tag == paramNode.tag)
        {
          if (localNode1.type == 6)
          {
            paramNode.closed = true;
            Node.fixEmptyRow(paramLexer, paramNode);
            return;
          }
          paramLexer.ungetToken();
          Node.fixEmptyRow(paramLexer, paramNode);
          return;
        }
        if (localNode1.type == 6)
        {
          if (((localNode1.tag != null) && ((localNode1.tag.model & 0x82) != 0)) || ((localNode1.tag == localTagTable.tagTable) && (paramNode.isDescendantOf(localNode1.tag))))
          {
            paramLexer.ungetToken();
            return;
          }
          if ((localNode1.tag == localTagTable.tagForm) || ((localNode1.tag != null) && ((localNode1.tag.model & 0x18) != 0)))
          {
            if (localNode1.tag == localTagTable.tagForm) {
              ParserImpl.badForm(paramLexer);
            }
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if ((localNode1.tag == localTagTable.tagTd) || (localNode1.tag == localTagTable.tagTh))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else
          {
            for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
              if (localNode1.tag == localNode2.tag)
              {
                paramLexer.ungetToken();
                Node.trimEmptyElement(paramLexer, paramNode);
                return;
              }
            }
          }
        }
        else if (!Node.insertMisc(paramNode, localNode1))
        {
          if ((localNode1.tag == null) && (localNode1.type != 4))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if (localNode1.tag == localTagTable.tagTable)
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else
          {
            if ((localNode1.tag != null) && ((localNode1.tag.model & 0x100) != 0))
            {
              paramLexer.ungetToken();
              Node.trimEmptyElement(paramLexer, paramNode);
              return;
            }
            if (localNode1.type == 6)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else if (localNode1.type != 6)
            {
              if (localNode1.tag == localTagTable.tagForm)
              {
                paramLexer.ungetToken();
                localNode1 = paramLexer.inferredTag("td");
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)12);
              }
              else
              {
                if ((localNode1.type == 4) || ((localNode1.tag.model & 0x18) != 0))
                {
                  Node.moveBeforeTable(paramNode, localNode1, localTagTable);
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                  paramLexer.exiled = true;
                  if (localNode1.type != 4) {
                    ParserImpl.parseTag(paramLexer, localNode1, (short)0);
                  }
                  paramLexer.exiled = false;
                  continue;
                }
                if ((localNode1.tag.model & 0x4) != 0)
                {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                  ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
                }
              }
            }
            else if ((localNode1.tag != localTagTable.tagTd) && (localNode1.tag != localTagTable.tagTh))
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
            }
            else
            {
              paramNode.insertNodeAtEnd(localNode1);
              boolean bool = paramLexer.excludeBlocks;
              paramLexer.excludeBlocks = false;
              ParserImpl.parseTag(paramLexer, localNode1, (short)0);
              paramLexer.excludeBlocks = bool;
              while (paramLexer.istack.size() > paramLexer.istackbase) {
                paramLexer.popInline(null);
              }
            }
          }
        }
      }
      Node.trimEmptyElement(paramLexer, paramNode);
    }
  }
  
  public static class ParseRowGroup
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      if ((paramNode.tag.model & 0x1) != 0) {
        return;
      }
      Node localNode1;
      while ((localNode1 = paramLexer.getToken((short)0)) != null)
      {
        if (localNode1.tag == paramNode.tag)
        {
          if (localNode1.type == 6)
          {
            paramNode.closed = true;
            Node.trimEmptyElement(paramLexer, paramNode);
            return;
          }
          paramLexer.ungetToken();
          return;
        }
        if ((localNode1.tag == localTagTable.tagTable) && (localNode1.type == 6))
        {
          paramLexer.ungetToken();
          Node.trimEmptyElement(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode1)) {
          if ((localNode1.tag == null) && (localNode1.type != 4))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if (localNode1.type != 6)
          {
            if ((localNode1.tag == localTagTable.tagTd) || (localNode1.tag == localTagTable.tagTh))
            {
              paramLexer.ungetToken();
              localNode1 = paramLexer.inferredTag("tr");
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)12);
            }
            else
            {
              if ((localNode1.type == 4) || ((localNode1.tag.model & 0x18) != 0))
              {
                Node.moveBeforeTable(paramNode, localNode1, localTagTable);
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                paramLexer.exiled = true;
                if (localNode1.type != 4) {
                  ParserImpl.parseTag(paramLexer, localNode1, (short)0);
                }
                paramLexer.exiled = false;
                continue;
              }
              if ((localNode1.tag.model & 0x4) != 0)
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
              }
            }
          }
          else if (localNode1.type == 6)
          {
            if ((localNode1.tag == localTagTable.tagForm) || ((localNode1.tag != null) && ((localNode1.tag.model & 0x18) != 0)))
            {
              if (localNode1.tag == localTagTable.tagForm) {
                ParserImpl.badForm(paramLexer);
              }
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else if ((localNode1.tag == localTagTable.tagTr) || (localNode1.tag == localTagTable.tagTd) || (localNode1.tag == localTagTable.tagTh))
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
                if (localNode1.tag == localNode2.tag)
                {
                  paramLexer.ungetToken();
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
              }
            }
          }
          else
          {
            if ((localNode1.tag.model & 0x100) != 0)
            {
              if (localNode1.type != 6) {
                paramLexer.ungetToken();
              }
              Node.trimEmptyElement(paramLexer, paramNode);
              return;
            }
            if (localNode1.type == 6)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              if (localNode1.tag != localTagTable.tagTr)
              {
                localNode1 = paramLexer.inferredTag("tr");
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)12);
                paramLexer.ungetToken();
              }
              paramNode.insertNodeAtEnd(localNode1);
              ParserImpl.parseTag(paramLexer, localNode1, (short)0);
            }
          }
        }
      }
      Node.trimEmptyElement(paramLexer, paramNode);
    }
  }
  
  public static class ParseColGroup
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      if ((paramNode.tag.model & 0x1) != 0) {
        return;
      }
      Node localNode1;
      while ((localNode1 = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
        {
          paramNode.closed = true;
          return;
        }
        if (localNode1.type == 6)
        {
          if (localNode1.tag == localTagTable.tagForm)
          {
            ParserImpl.badForm(paramLexer);
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else
          {
            for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
              if (localNode1.tag == localNode2.tag)
              {
                paramLexer.ungetToken();
                return;
              }
            }
          }
        }
        else
        {
          if (localNode1.type == 4)
          {
            paramLexer.ungetToken();
            return;
          }
          if (!Node.insertMisc(paramNode, localNode1)) {
            if (localNode1.tag == null)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              if (localNode1.tag != localTagTable.tagCol)
              {
                paramLexer.ungetToken();
                return;
              }
              if (localNode1.type == 6)
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
              }
              else
              {
                paramNode.insertNodeAtEnd(localNode1);
                ParserImpl.parseTag(paramLexer, localNode1, (short)0);
              }
            }
          }
        }
      }
    }
  }
  
  public static class ParseTableTag
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      paramLexer.deferDup();
      int i = paramLexer.istackbase;
      paramLexer.istackbase = paramLexer.istack.size();
      Node localNode1;
      while ((localNode1 = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
        {
          paramLexer.istackbase = i;
          paramNode.closed = true;
          Node.trimEmptyElement(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode1)) {
          if ((localNode1.tag == null) && (localNode1.type != 4))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if (localNode1.type != 6)
          {
            if ((localNode1.tag == localTagTable.tagTd) || (localNode1.tag == localTagTable.tagTh) || (localNode1.tag == localTagTable.tagTable))
            {
              paramLexer.ungetToken();
              localNode1 = paramLexer.inferredTag("tr");
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)12);
            }
            else
            {
              if ((localNode1.type == 4) || ((localNode1.tag.model & 0x18) != 0))
              {
                Node.insertNodeBeforeElement(paramNode, localNode1);
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                paramLexer.exiled = true;
                if (localNode1.type != 4) {
                  ParserImpl.parseTag(paramLexer, localNode1, (short)0);
                }
                paramLexer.exiled = false;
                continue;
              }
              if ((localNode1.tag.model & 0x4) != 0) {
                ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
              }
            }
          }
          else if (localNode1.type == 6)
          {
            if ((localNode1.tag == localTagTable.tagForm) || ((localNode1.tag != null) && ((localNode1.tag.model & 0x18) != 0)))
            {
              ParserImpl.badForm(paramLexer);
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else if (((localNode1.tag != null) && ((localNode1.tag.model & 0x280) != 0)) || ((localNode1.tag != null) && ((localNode1.tag.model & 0x18) != 0)))
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
                if (localNode1.tag == localNode2.tag)
                {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                  paramLexer.ungetToken();
                  paramLexer.istackbase = i;
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
              }
            }
          }
          else
          {
            if ((localNode1.tag.model & 0x80) == 0)
            {
              paramLexer.ungetToken();
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
              paramLexer.istackbase = i;
              Node.trimEmptyElement(paramLexer, paramNode);
              return;
            }
            if ((localNode1.type == 5) || (localNode1.type == 7))
            {
              paramNode.insertNodeAtEnd(localNode1);
              ParserImpl.parseTag(paramLexer, localNode1, (short)0);
            }
            else
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
          }
        }
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)6);
      Node.trimEmptyElement(paramLexer, paramNode);
      paramLexer.istackbase = i;
    }
  }
  
  public static class ParseBlock
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      int j = 0;
      TagTable localTagTable = paramLexer.configuration.field_1881;
      int i = 1;
      if ((paramNode.tag.model & 0x1) != 0) {
        return;
      }
      if ((paramNode.tag == localTagTable.tagForm) && (paramNode.isDescendantOf(localTagTable.tagForm))) {
        paramLexer.report.warning(paramLexer, paramNode, null, (short)25);
      }
      if ((paramNode.tag.model & 0x800) != 0)
      {
        j = paramLexer.istackbase;
        paramLexer.istackbase = paramLexer.istack.size();
      }
      if ((paramNode.tag.model & 0x20000) == 0) {
        paramLexer.inlineDup(null);
      }
      paramShort = 0;
      Node localNode1;
      while ((localNode1 = paramLexer.getToken(paramShort)) != null)
      {
        if ((localNode1.type == 6) && (localNode1.tag != null) && ((localNode1.tag == paramNode.tag) || (paramNode.was == localNode1.tag)))
        {
          if ((paramNode.tag.model & 0x800) != 0)
          {
            while (paramLexer.istack.size() > paramLexer.istackbase) {
              paramLexer.popInline(null);
            }
            paramLexer.istackbase = j;
          }
          paramNode.closed = true;
          Node.trimSpaces(paramLexer, paramNode);
          Node.trimEmptyElement(paramLexer, paramNode);
          return;
        }
        if ((localNode1.tag == localTagTable.tagHtml) || (localNode1.tag == localTagTable.tagHead) || (localNode1.tag == localTagTable.tagBody))
        {
          if ((localNode1.type == 5) || (localNode1.type == 7)) {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
        }
        else if (localNode1.type == 6)
        {
          if (localNode1.tag == null)
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if (localNode1.tag == localTagTable.tagBr)
          {
            localNode1.type = 5;
          }
          else if (localNode1.tag == localTagTable.tagP)
          {
            Node.coerceNode(paramLexer, localNode1, localTagTable.tagBr);
            paramNode.insertNodeAtEnd(localNode1);
            localNode1 = paramLexer.inferredTag("br");
          }
          else
          {
            for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
              if (localNode1.tag == localNode2.tag)
              {
                if ((paramNode.tag.model & 0x8000) == 0) {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                }
                paramLexer.ungetToken();
                if ((paramNode.tag.model & 0x800) != 0)
                {
                  while (paramLexer.istack.size() > paramLexer.istackbase) {
                    paramLexer.popInline(null);
                  }
                  paramLexer.istackbase = j;
                }
                Node.trimSpaces(paramLexer, paramNode);
                Node.trimEmptyElement(paramLexer, paramNode);
                return;
              }
            }
            if ((paramLexer.exiled) && (localNode1.tag.model != 0) && ((localNode1.tag.model & 0x80) != 0))
            {
              paramLexer.ungetToken();
              Node.trimSpaces(paramLexer, paramNode);
              Node.trimEmptyElement(paramLexer, paramNode);
            }
          }
        }
        else if (localNode1.type == 4)
        {
          int k = 0;
          if ((localNode1.type == 4) && (localNode1.end <= localNode1.start + 1) && (paramLexer.lexbuf[localNode1.start] == 32)) {
            k = 1;
          }
          if ((paramLexer.configuration.encloseBlockText) && (k == 0))
          {
            paramLexer.ungetToken();
            localNode1 = paramLexer.inferredTag("p");
            paramNode.insertNodeAtEnd(localNode1);
            ParserImpl.parseTag(paramLexer, localNode1, (short)1);
          }
          else if (i != 0)
          {
            i = 0;
            if (((paramNode.tag.model & 0x20000) == 0) && (paramLexer.inlineDup(localNode1) > 0)) {}
          }
          else
          {
            paramNode.insertNodeAtEnd(localNode1);
            paramShort = 1;
            if ((paramNode.tag == localTagTable.tagBody) || (paramNode.tag == localTagTable.tagMap) || (paramNode.tag == localTagTable.tagBlockquote) || (paramNode.tag == localTagTable.tagForm) || (paramNode.tag == localTagTable.tagNoscript)) {
              paramLexer.constrainVersion(-5);
            }
          }
        }
        else if (!Node.insertMisc(paramNode, localNode1))
        {
          if (localNode1.tag == localTagTable.tagParam)
          {
            if (((paramNode.tag.model & 0x1000) != 0) && ((localNode1.type == 5) || (localNode1.type == 7))) {
              paramNode.insertNodeAtEnd(localNode1);
            } else {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
          }
          else if (localNode1.tag == localTagTable.tagArea)
          {
            if ((paramNode.tag == localTagTable.tagMap) && ((localNode1.type == 5) || (localNode1.type == 7))) {
              paramNode.insertNodeAtEnd(localNode1);
            } else {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
          }
          else if (localNode1.tag == null)
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if ((localNode1.tag.model & 0x10) == 0)
          {
            if ((localNode1.type != 5) && (localNode1.type != 7))
            {
              if (localNode1.tag == localTagTable.tagForm) {
                ParserImpl.badForm(paramLexer);
              }
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else if ((paramNode.tag == localTagTable.tagLi) && ((localNode1.tag == localTagTable.tagFrame) || (localNode1.tag == localTagTable.tagFrameset) || (localNode1.tag == localTagTable.tagOptgroup) || (localNode1.tag == localTagTable.tagOption)))
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else if ((paramNode.tag == localTagTable.tagTd) || (paramNode.tag == localTagTable.tagTh))
            {
              if ((localNode1.tag.model & 0x4) != 0)
              {
                ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
              }
              else
              {
                if ((localNode1.tag.model & 0x20) != 0)
                {
                  paramLexer.ungetToken();
                  localNode1 = paramLexer.inferredTag("ul");
                  localNode1.addClass("noindent");
                  paramLexer.excludeBlocks = true;
                }
                else if ((localNode1.tag.model & 0x40) != 0)
                {
                  paramLexer.ungetToken();
                  localNode1 = paramLexer.inferredTag("dl");
                  paramLexer.excludeBlocks = true;
                }
                if ((localNode1.tag.model & 0x8) == 0)
                {
                  paramLexer.ungetToken();
                  Node.trimSpaces(paramLexer, paramNode);
                  Node.trimEmptyElement(paramLexer, paramNode);
                }
              }
            }
            else if ((localNode1.tag.model & 0x8) != 0)
            {
              if (paramLexer.excludeBlocks)
              {
                if ((paramNode.tag.model & 0x8000) == 0) {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                }
                paramLexer.ungetToken();
                if ((paramNode.tag.model & 0x800) != 0) {
                  paramLexer.istackbase = j;
                }
                Node.trimSpaces(paramLexer, paramNode);
                Node.trimEmptyElement(paramLexer, paramNode);
              }
            }
            else
            {
              if ((localNode1.tag.model & 0x4) != 0)
              {
                ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
                continue;
              }
              if ((paramNode.tag == localTagTable.tagForm) && (paramNode.parent.tag == localTagTable.tagTd) && (paramNode.parent.implicit))
              {
                if (localNode1.tag == localTagTable.tagTd)
                {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                  continue;
                }
                if (localNode1.tag == localTagTable.tagTh)
                {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                  localNode1 = paramNode.parent;
                  localNode1.element = "th";
                  localNode1.tag = localTagTable.tagTh;
                  continue;
                }
              }
              if (((paramNode.tag.model & 0x8000) == 0) && (!paramNode.implicit)) {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
              }
              paramLexer.ungetToken();
              if ((localNode1.tag.model & 0x20) != 0)
              {
                if ((paramNode.parent != null) && (paramNode.parent.tag != null) && (paramNode.parent.tag.getParser() == ParserImpl.LIST))
                {
                  Node.trimSpaces(paramLexer, paramNode);
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
                localNode1 = paramLexer.inferredTag("ul");
                localNode1.addClass("noindent");
              }
              else if ((localNode1.tag.model & 0x40) != 0)
              {
                if (paramNode.parent.tag == localTagTable.tagDl)
                {
                  Node.trimSpaces(paramLexer, paramNode);
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
                localNode1 = paramLexer.inferredTag("dl");
              }
              else if (((localNode1.tag.model & 0x80) != 0) || ((localNode1.tag.model & 0x200) != 0))
              {
                localNode1 = paramLexer.inferredTag("table");
              }
              else
              {
                if ((paramNode.tag.model & 0x800) != 0)
                {
                  while (paramLexer.istack.size() > paramLexer.istackbase) {
                    paramLexer.popInline(null);
                  }
                  paramLexer.istackbase = j;
                  Node.trimSpaces(paramLexer, paramNode);
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
                Node.trimSpaces(paramLexer, paramNode);
                Node.trimEmptyElement(paramLexer, paramNode);
              }
            }
          }
          else if ((localNode1.type == 5) || (localNode1.type == 7))
          {
            if (TidyUtils.toBoolean(localNode1.tag.model & 0x10))
            {
              if ((i != 0) && (!localNode1.implicit))
              {
                i = 0;
                if ((!TidyUtils.toBoolean(paramNode.tag.model & 0x20000)) && (paramLexer.inlineDup(localNode1) > 0)) {}
              }
              else
              {
                paramShort = 1;
              }
            }
            else
            {
              i = 1;
              paramShort = 0;
              if (localNode1.tag == localTagTable.tagBr) {
                Node.trimSpaces(paramLexer, paramNode);
              }
              paramNode.insertNodeAtEnd(localNode1);
              if (localNode1.implicit) {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)15);
              }
              ParserImpl.parseTag(paramLexer, localNode1, (short)0);
            }
          }
          else
          {
            if (localNode1.type == 6) {
              paramLexer.popInline(localNode1);
            }
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
        }
      }
      if ((paramNode.tag.model & 0x8000) == 0) {
        paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)6);
      }
      if ((paramNode.tag.model & 0x800) != 0)
      {
        while (paramLexer.istack.size() > paramLexer.istackbase) {
          paramLexer.popInline(null);
        }
        paramLexer.istackbase = j;
      }
      Node.trimSpaces(paramLexer, paramNode);
      Node.trimEmptyElement(paramLexer, paramNode);
    }
  }
  
  public static class ParsePre
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      if ((paramNode.tag.model & 0x1) != 0) {
        return;
      }
      if ((paramNode.tag.model & 0x80000) != 0) {
        Node.coerceNode(paramLexer, paramNode, localTagTable.tagPre);
      }
      paramLexer.inlineDup(null);
      Node localNode1;
      while ((localNode1 = paramLexer.getToken((short)2)) != null)
      {
        if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
        {
          Node.trimSpaces(paramLexer, paramNode);
          paramNode.closed = true;
          Node.trimEmptyElement(paramLexer, paramNode);
          return;
        }
        if (localNode1.tag == localTagTable.tagHtml)
        {
          if ((localNode1.type == 5) || (localNode1.type == 7)) {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
        }
        else if (localNode1.type == 4)
        {
          if (paramNode.content == null)
          {
            if (localNode1.textarray[localNode1.start] == 10) {
              localNode1.start += 1;
            }
            if (localNode1.start >= localNode1.end) {}
          }
          else
          {
            paramNode.insertNodeAtEnd(localNode1);
          }
        }
        else if (!Node.insertMisc(paramNode, localNode1)) {
          if (!paramLexer.preContent(localNode1))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)39);
            Node localNode2 = Node.escapeTag(paramLexer, localNode1);
            paramNode.insertNodeAtEnd(localNode2);
          }
          else if (localNode1.tag == localTagTable.tagP)
          {
            if (localNode1.type == 5)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)14);
              Node.trimSpaces(paramLexer, paramNode);
              Node.coerceNode(paramLexer, localNode1, localTagTable.tagBr);
              paramNode.insertNodeAtEnd(localNode1);
            }
            else
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
          }
          else if ((localNode1.type == 5) || (localNode1.type == 7))
          {
            if (localNode1.tag == localTagTable.tagBr) {
              Node.trimSpaces(paramLexer, paramNode);
            }
            paramNode.insertNodeAtEnd(localNode1);
            ParserImpl.parseTag(paramLexer, localNode1, (short)2);
          }
          else
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
        }
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)6);
      Node.trimEmptyElement(paramLexer, paramNode);
    }
  }
  
  public static class ParseDefList
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      if ((paramNode.tag.model & 0x1) != 0) {
        return;
      }
      paramLexer.insert = -1;
      Node localNode1;
      while ((localNode1 = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
        {
          paramNode.closed = true;
          Node.trimEmptyElement(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode1))
        {
          if (localNode1.type == 4)
          {
            paramLexer.ungetToken();
            localNode1 = paramLexer.inferredTag("dt");
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)12);
          }
          if (localNode1.tag == null)
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if (localNode1.type == 6)
          {
            if (localNode1.tag == localTagTable.tagForm)
            {
              ParserImpl.badForm(paramLexer);
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
                if (localNode1.tag == localNode2.tag)
                {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                  paramLexer.ungetToken();
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
              }
            }
          }
          else if (localNode1.tag == localTagTable.tagCenter)
          {
            if (paramNode.content != null)
            {
              paramNode.insertNodeAfterElement(localNode1);
            }
            else
            {
              Node.insertNodeBeforeElement(paramNode, localNode1);
              Node.discardElement(paramNode);
            }
            ParserImpl.parseTag(paramLexer, localNode1, paramShort);
            paramNode = paramLexer.inferredTag("dl");
            localNode1.insertNodeAfterElement(paramNode);
          }
          else
          {
            if ((localNode1.tag != localTagTable.tagDt) && (localNode1.tag != localTagTable.tagDd))
            {
              paramLexer.ungetToken();
              if ((localNode1.tag.model & 0x18) == 0)
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                Node.trimEmptyElement(paramLexer, paramNode);
                return;
              }
              if (((localNode1.tag.model & 0x10) == 0) && (paramLexer.excludeBlocks))
              {
                Node.trimEmptyElement(paramLexer, paramNode);
                return;
              }
              localNode1 = paramLexer.inferredTag("dd");
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)12);
            }
            if (localNode1.type == 6)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              paramNode.insertNodeAtEnd(localNode1);
              ParserImpl.parseTag(paramLexer, localNode1, (short)0);
            }
          }
        }
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)6);
      Node.trimEmptyElement(paramLexer, paramNode);
    }
  }
  
  public static class ParseEmpty
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      if (paramLexer.isvoyager)
      {
        Node localNode = paramLexer.getToken(paramShort);
        if ((localNode != null) && ((localNode.type != 6) || (localNode.tag != paramNode.tag)))
        {
          paramLexer.report.warning(paramLexer, paramNode, localNode, (short)41);
          paramLexer.ungetToken();
        }
      }
    }
  }
  
  public static class ParseList
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      if ((paramNode.tag.model & 0x1) != 0) {
        return;
      }
      paramLexer.insert = -1;
      Node localNode1;
      while ((localNode1 = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
        {
          if ((paramNode.tag.model & 0x80000) != 0) {
            Node.coerceNode(paramLexer, paramNode, localTagTable.tagUl);
          }
          paramNode.closed = true;
          Node.trimEmptyElement(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode1)) {
          if ((localNode1.type != 4) && (localNode1.tag == null))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          }
          else if (localNode1.type == 6)
          {
            if (localNode1.tag == localTagTable.tagForm)
            {
              ParserImpl.badForm(paramLexer);
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else if ((localNode1.tag != null) && ((localNode1.tag.model & 0x10) != 0))
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
              paramLexer.popInline(localNode1);
            }
            else
            {
              for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
                if (localNode1.tag == localNode2.tag)
                {
                  paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                  paramLexer.ungetToken();
                  if ((paramNode.tag.model & 0x80000) != 0) {
                    Node.coerceNode(paramLexer, paramNode, localTagTable.tagUl);
                  }
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
              }
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
          }
          else
          {
            if (localNode1.tag != localTagTable.tagLi)
            {
              paramLexer.ungetToken();
              if ((localNode1.tag != null) && ((localNode1.tag.model & 0x8) != 0) && (paramLexer.excludeBlocks))
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                Node.trimEmptyElement(paramLexer, paramNode);
                return;
              }
              localNode1 = paramLexer.inferredTag("li");
              localNode1.addAttribute("style", "list-style: none");
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)12);
            }
            paramNode.insertNodeAtEnd(localNode1);
            ParserImpl.parseTag(paramLexer, localNode1, (short)0);
          }
        }
      }
      if ((paramNode.tag.model & 0x80000) != 0) {
        Node.coerceNode(paramLexer, paramNode, localTagTable.tagUl);
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)6);
      Node.trimEmptyElement(paramLexer, paramNode);
    }
  }
  
  public static class ParseInline
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      if (TidyUtils.toBoolean(paramNode.tag.model & 0x1)) {
        return;
      }
      if ((TidyUtils.toBoolean(paramNode.tag.model & 0x8)) || (paramNode.tag == localTagTable.tagDt)) {
        paramLexer.inlineDup(null);
      } else if ((TidyUtils.toBoolean(paramNode.tag.model & 0x10)) && (paramNode.tag != localTagTable.tagA) && (paramNode.tag != localTagTable.tagSpan)) {
        paramLexer.pushInline(paramNode);
      }
      if (paramNode.tag == localTagTable.tagNobr)
      {
        Lexer tmp120_119 = paramLexer;
        tmp120_119.badLayout = ((short)(tmp120_119.badLayout | 0x4));
      }
      else if (paramNode.tag == localTagTable.tagFont)
      {
        Lexer tmp146_145 = paramLexer;
        tmp146_145.badLayout = ((short)(tmp146_145.badLayout | 0x8));
      }
      if (paramShort != 2) {
        paramShort = 1;
      }
      Node localNode1;
      while ((localNode1 = paramLexer.getToken(paramShort)) != null)
      {
        Node localNode3;
        if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
        {
          if (TidyUtils.toBoolean(paramNode.tag.model & 0x10)) {
            paramLexer.popInline(localNode1);
          }
          if (!TidyUtils.toBoolean(paramShort & 0x2)) {
            Node.trimSpaces(paramLexer, paramNode);
          }
          if ((paramNode.tag == localTagTable.tagFont) && (paramNode.content != null) && (paramNode.content == paramNode.last))
          {
            localNode3 = paramNode.content;
            if (localNode3.tag == localTagTable.tagA)
            {
              localNode3.parent = paramNode.parent;
              localNode3.next = paramNode.next;
              localNode3.prev = paramNode.prev;
              if (localNode3.prev != null) {
                localNode3.prev.next = localNode3;
              } else {
                localNode3.parent.content = localNode3;
              }
              if (localNode3.next != null) {
                localNode3.next.prev = localNode3;
              } else {
                localNode3.parent.last = localNode3;
              }
              paramNode.next = null;
              paramNode.prev = null;
              paramNode.parent = localNode3;
              paramNode.content = localNode3.content;
              paramNode.last = localNode3.last;
              localNode3.content = paramNode;
              localNode3.last = paramNode;
              for (localNode3 = paramNode.content; localNode3 != null; localNode3 = localNode3.next) {
                localNode3.parent = paramNode;
              }
            }
          }
          paramNode.closed = true;
          Node.trimSpaces(paramLexer, paramNode);
          Node.trimEmptyElement(paramLexer, paramNode);
          return;
        }
        if ((localNode1.type == 5) && (localNode1.tag == paramNode.tag) && (paramLexer.isPushed(localNode1)) && (!localNode1.implicit) && (!paramNode.implicit) && (localNode1.tag != null) && ((localNode1.tag.model & 0x10) != 0) && (localNode1.tag != localTagTable.tagA) && (localNode1.tag != localTagTable.tagFont) && (localNode1.tag != localTagTable.tagBig) && (localNode1.tag != localTagTable.tagSmall) && (localNode1.tag != localTagTable.tagQ))
        {
          if ((paramNode.content != null) && (localNode1.attributes == null))
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)24);
            localNode1.type = 6;
            paramLexer.ungetToken();
          }
          else
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)9);
          }
        }
        else
        {
          if ((paramLexer.isPushed(localNode1)) && (localNode1.type == 5) && (localNode1.tag == localTagTable.tagQ)) {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)40);
          }
          if (localNode1.type == 4)
          {
            if ((paramNode.content == null) && (!TidyUtils.toBoolean(paramShort & 0x2))) {
              Node.trimSpaces(paramLexer, paramNode);
            }
            if (localNode1.start < localNode1.end) {
              paramNode.insertNodeAtEnd(localNode1);
            }
          }
          else if (!Node.insertMisc(paramNode, localNode1))
          {
            if (localNode1.tag == localTagTable.tagHtml)
            {
              if ((localNode1.type == 5) || (localNode1.type == 7))
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
              }
              else
              {
                paramLexer.ungetToken();
                if ((paramShort & 0x2) == 0) {
                  Node.trimSpaces(paramLexer, paramNode);
                }
                Node.trimEmptyElement(paramLexer, paramNode);
              }
            }
            else if ((localNode1.tag == localTagTable.tagP) && (localNode1.type == 5) && (((paramShort & 0x2) != 0) || (paramNode.tag == localTagTable.tagDt) || (paramNode.isDescendantOf(localTagTable.tagDt))))
            {
              localNode1.tag = localTagTable.tagBr;
              localNode1.element = "br";
              Node.trimSpaces(paramLexer, paramNode);
              paramNode.insertNodeAtEnd(localNode1);
            }
            else if ((localNode1.tag == null) || (localNode1.tag == localTagTable.tagParam))
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              if ((localNode1.tag == localTagTable.tagBr) && (localNode1.type == 6)) {
                localNode1.type = 5;
              }
              if (localNode1.type == 6)
              {
                if (localNode1.tag == localTagTable.tagBr)
                {
                  localNode1.type = 5;
                }
                else
                {
                  if (localNode1.tag == localTagTable.tagP)
                  {
                    if (paramNode.isDescendantOf(localTagTable.tagP)) {
                      break label1280;
                    }
                    Node.coerceNode(paramLexer, localNode1, localTagTable.tagBr);
                    Node.trimSpaces(paramLexer, paramNode);
                    paramNode.insertNodeAtEnd(localNode1);
                    localNode1 = paramLexer.inferredTag("br");
                    continue;
                  }
                  if (((localNode1.tag.model & 0x10) != 0) && (localNode1.tag != localTagTable.tagA) && ((localNode1.tag.model & 0x800) == 0) && ((paramNode.tag.model & 0x10) != 0))
                  {
                    paramLexer.popInline(paramNode);
                    if (paramNode.tag != localTagTable.tagA)
                    {
                      if ((localNode1.tag == localTagTable.tagA) && (localNode1.tag != paramNode.tag))
                      {
                        paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                        paramLexer.ungetToken();
                      }
                      else
                      {
                        paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)10);
                      }
                      if ((paramShort & 0x2) == 0) {
                        Node.trimSpaces(paramLexer, paramNode);
                      }
                      Node.trimEmptyElement(paramLexer, paramNode);
                      return;
                    }
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                    continue;
                  }
                  if ((paramLexer.exiled) && (localNode1.tag.model != 0) && ((localNode1.tag.model & 0x80) != 0))
                  {
                    paramLexer.ungetToken();
                    Node.trimSpaces(paramLexer, paramNode);
                    Node.trimEmptyElement(paramLexer, paramNode);
                  }
                }
              }
              else
              {
                label1280:
                if (((localNode1.tag.model & 0x4000) != 0) && ((paramNode.tag.model & 0x4000) != 0))
                {
                  if (localNode1.tag == paramNode.tag)
                  {
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)10);
                  }
                  else
                  {
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                    paramLexer.ungetToken();
                  }
                  if ((paramShort & 0x2) == 0) {
                    Node.trimSpaces(paramLexer, paramNode);
                  }
                  Node.trimEmptyElement(paramLexer, paramNode);
                  return;
                }
                if ((localNode1.tag == localTagTable.tagA) && (!localNode1.implicit) && ((paramNode.tag == localTagTable.tagA) || (paramNode.isDescendantOf(localTagTable.tagA))))
                {
                  if ((localNode1.type != 6) && (localNode1.attributes == null))
                  {
                    localNode1.type = 6;
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)24);
                    paramLexer.ungetToken();
                  }
                  else
                  {
                    paramLexer.ungetToken();
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                    if ((paramShort & 0x2) == 0) {
                      Node.trimSpaces(paramLexer, paramNode);
                    }
                    Node.trimEmptyElement(paramLexer, paramNode);
                  }
                }
                else if ((paramNode.tag.model & 0x4000) != 0)
                {
                  if ((localNode1.tag == localTagTable.tagCenter) || (localNode1.tag == localTagTable.tagDiv))
                  {
                    if ((localNode1.type != 5) && (localNode1.type != 7))
                    {
                      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                    }
                    else
                    {
                      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                      if (paramNode.content == null)
                      {
                        Node.insertNodeAsParent(paramNode, localNode1);
                      }
                      else
                      {
                        paramNode.insertNodeAfterElement(localNode1);
                        if ((paramShort & 0x2) == 0) {
                          Node.trimSpaces(paramLexer, paramNode);
                        }
                        paramNode = paramLexer.cloneNode(paramNode);
                        paramNode.start = paramLexer.lexsize;
                        paramNode.end = paramLexer.lexsize;
                        localNode1.insertNodeAtEnd(paramNode);
                      }
                    }
                  }
                  else if (localNode1.tag == localTagTable.tagHr)
                  {
                    if ((localNode1.type != 5) && (localNode1.type != 7))
                    {
                      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                      continue;
                    }
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                    if (paramNode.content == null)
                    {
                      Node.insertNodeBeforeElement(paramNode, localNode1);
                      continue;
                    }
                    paramNode.insertNodeAfterElement(localNode1);
                    if ((paramShort & 0x2) == 0) {
                      Node.trimSpaces(paramLexer, paramNode);
                    }
                    paramNode = paramLexer.cloneNode(paramNode);
                    paramNode.start = paramLexer.lexsize;
                    paramNode.end = paramLexer.lexsize;
                    localNode1.insertNodeAfterElement(paramNode);
                  }
                }
                else if ((paramNode.tag == localTagTable.tagDt) && (localNode1.tag == localTagTable.tagHr))
                {
                  if ((localNode1.type != 5) && (localNode1.type != 7))
                  {
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                  }
                  else
                  {
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                    localNode3 = paramLexer.inferredTag("dd");
                    if (paramNode.content == null)
                    {
                      Node.insertNodeBeforeElement(paramNode, localNode3);
                      localNode3.insertNodeAtEnd(localNode1);
                    }
                    else
                    {
                      paramNode.insertNodeAfterElement(localNode3);
                      localNode3.insertNodeAtEnd(localNode1);
                      if ((paramShort & 0x2) == 0) {
                        Node.trimSpaces(paramLexer, paramNode);
                      }
                      paramNode = paramLexer.cloneNode(paramNode);
                      paramNode.start = paramLexer.lexsize;
                      paramNode.end = paramLexer.lexsize;
                      localNode3.insertNodeAfterElement(paramNode);
                    }
                  }
                }
                else
                {
                  if (localNode1.type == 6) {
                    for (Node localNode2 = paramNode.parent; localNode2 != null; localNode2 = localNode2.parent) {
                      if (localNode1.tag == localNode2.tag)
                      {
                        if (((paramNode.tag.model & 0x8000) == 0) && (!paramNode.implicit)) {
                          paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                        }
                        if (paramNode.tag == localTagTable.tagA) {
                          paramLexer.popInline(paramNode);
                        }
                        paramLexer.ungetToken();
                        if ((paramShort & 0x2) == 0) {
                          Node.trimSpaces(paramLexer, paramNode);
                        }
                        Node.trimEmptyElement(paramLexer, paramNode);
                        return;
                      }
                    }
                  }
                  if ((localNode1.tag.model & 0x10) == 0)
                  {
                    if (localNode1.type != 5)
                    {
                      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                    }
                    else
                    {
                      if ((paramNode.tag.model & 0x8000) == 0) {
                        paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)7);
                      }
                      if (((localNode1.tag.model & 0x4) != 0) && ((localNode1.tag.model & 0x8) == 0))
                      {
                        ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
                      }
                      else
                      {
                        if (paramNode.tag == localTagTable.tagA) {
                          if ((localNode1.tag != null) && ((localNode1.tag.model & 0x4000) == 0))
                          {
                            paramLexer.popInline(paramNode);
                          }
                          else if (paramNode.content == null)
                          {
                            Node.discardElement(paramNode);
                            paramLexer.ungetToken();
                            return;
                          }
                        }
                        paramLexer.ungetToken();
                        if ((paramShort & 0x2) == 0) {
                          Node.trimSpaces(paramLexer, paramNode);
                        }
                        Node.trimEmptyElement(paramLexer, paramNode);
                      }
                    }
                  }
                  else if ((localNode1.type == 5) || (localNode1.type == 7))
                  {
                    if (localNode1.implicit) {
                      paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)15);
                    }
                    if (localNode1.tag == localTagTable.tagBr) {
                      Node.trimSpaces(paramLexer, paramNode);
                    }
                    paramNode.insertNodeAtEnd(localNode1);
                    ParserImpl.parseTag(paramLexer, localNode1, paramShort);
                  }
                  else
                  {
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                  }
                }
              }
            }
          }
        }
      }
      if ((paramNode.tag.model & 0x8000) == 0) {
        paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)6);
      }
      Node.trimEmptyElement(paramLexer, paramNode);
    }
  }
  
  public static class ParseFrameSet
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      TagTable localTagTable = paramLexer.configuration.field_1881;
      Lexer tmp10_9 = paramLexer;
      tmp10_9.badAccess = ((short)(tmp10_9.badAccess | 0x10));
      Node localNode;
      while ((localNode = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode.tag == paramNode.tag) && (localNode.type == 6))
        {
          paramNode.closed = true;
          Node.trimSpaces(paramLexer, paramNode);
          return;
        }
        if (!Node.insertMisc(paramNode, localNode)) {
          if (localNode.tag == null)
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
          }
          else if (((localNode.type == 5) || (localNode.type == 7)) && (localNode.tag != null) && ((localNode.tag.model & 0x4) != 0))
          {
            ParserImpl.moveToHead(paramLexer, paramNode, localNode);
          }
          else
          {
            if (localNode.tag == localTagTable.tagBody)
            {
              paramLexer.ungetToken();
              localNode = paramLexer.inferredTag("noframes");
              paramLexer.report.warning(paramLexer, paramNode, localNode, (short)15);
            }
            if ((localNode.type == 5) && ((localNode.tag.model & 0x2000) != 0))
            {
              paramNode.insertNodeAtEnd(localNode);
              paramLexer.excludeBlocks = false;
              ParserImpl.parseTag(paramLexer, localNode, (short)1);
            }
            else if ((localNode.type == 7) && ((localNode.tag.model & 0x2000) != 0))
            {
              paramNode.insertNodeAtEnd(localNode);
            }
            else
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
            }
          }
        }
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode, (short)6);
    }
  }
  
  public static class ParseBody
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      paramShort = 0;
      int i = 1;
      TagTable localTagTable = paramLexer.configuration.field_1881;
      Clean.bumpObject(paramLexer, paramNode.parent);
      Node localNode1;
      while ((localNode1 = paramLexer.getToken(paramShort)) != null) {
        if (localNode1.tag == localTagTable.tagHtml)
        {
          if ((localNode1.type == 5) || (localNode1.type == 7) || (paramLexer.seenEndHtml)) {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          } else {
            paramLexer.seenEndHtml = true;
          }
        }
        else
        {
          if ((paramLexer.seenEndBody) && ((localNode1.type == 5) || (localNode1.type == 6) || (localNode1.type == 7))) {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)27);
          }
          if ((localNode1.tag == paramNode.tag) && (localNode1.type == 6))
          {
            paramNode.closed = true;
            Node.trimSpaces(paramLexer, paramNode);
            paramLexer.seenEndBody = true;
            paramShort = 0;
            if (paramNode.parent.tag == localTagTable.tagNoframes) {
              break;
            }
          }
          else if (localNode1.tag == localTagTable.tagNoframes)
          {
            if (localNode1.type == 5)
            {
              paramNode.insertNodeAtEnd(localNode1);
              ParserImpl.BLOCK.parse(paramLexer, localNode1, paramShort);
            }
            else if ((localNode1.type == 6) && (paramNode.parent.tag == localTagTable.tagNoframes))
            {
              Node.trimSpaces(paramLexer, paramNode);
              paramLexer.ungetToken();
              break;
            }
          }
          else
          {
            if (((localNode1.tag == localTagTable.tagFrame) || (localNode1.tag == localTagTable.tagFrameset)) && (paramNode.parent.tag == localTagTable.tagNoframes))
            {
              Node.trimSpaces(paramLexer, paramNode);
              paramLexer.ungetToken();
              break;
            }
            int j = 0;
            if ((localNode1.type == 4) && (localNode1.end <= localNode1.start + 1) && (localNode1.textarray[localNode1.start] == 32)) {
              j = 1;
            }
            if (!Node.insertMisc(paramNode, localNode1)) {
              if (localNode1.type == 4)
              {
                if ((j == 0) || (paramShort != 0)) {
                  if ((paramLexer.configuration.encloseBodyText) && (j == 0))
                  {
                    paramLexer.ungetToken();
                    Node localNode2 = paramLexer.inferredTag("p");
                    paramNode.insertNodeAtEnd(localNode2);
                    ParserImpl.parseTag(paramLexer, localNode2, paramShort);
                    paramShort = 1;
                  }
                  else
                  {
                    paramLexer.constrainVersion(-6);
                    if (i != 0)
                    {
                      i = 0;
                      if (paramLexer.inlineDup(localNode1) > 0) {}
                    }
                    else
                    {
                      paramNode.insertNodeAtEnd(localNode1);
                      paramShort = 1;
                    }
                  }
                }
              }
              else if (localNode1.type == 1)
              {
                Node.insertDocType(paramLexer, paramNode, localNode1);
              }
              else if ((localNode1.tag == null) || (localNode1.tag == localTagTable.tagParam))
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
              }
              else
              {
                paramLexer.excludeBlocks = false;
                if ((((localNode1.tag.model & 0x8) == 0) && ((localNode1.tag.model & 0x10) == 0)) || (localNode1.tag == localTagTable.tagInput))
                {
                  if ((localNode1.tag.model & 0x4) == 0) {
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)11);
                  }
                  if ((localNode1.tag.model & 0x2) != 0)
                  {
                    if ((localNode1.tag == localTagTable.tagBody) && (paramNode.implicit) && (paramNode.attributes == null))
                    {
                      paramNode.attributes = localNode1.attributes;
                      localNode1.attributes = null;
                    }
                  }
                  else if ((localNode1.tag.model & 0x4) != 0)
                  {
                    ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
                  }
                  else if ((localNode1.tag.model & 0x20) != 0)
                  {
                    paramLexer.ungetToken();
                    localNode1 = paramLexer.inferredTag("ul");
                    localNode1.addClass("noindent");
                    paramLexer.excludeBlocks = true;
                  }
                  else if ((localNode1.tag.model & 0x40) != 0)
                  {
                    paramLexer.ungetToken();
                    localNode1 = paramLexer.inferredTag("dl");
                    paramLexer.excludeBlocks = true;
                  }
                  else if ((localNode1.tag.model & 0x380) != 0)
                  {
                    if (localNode1.type != 6)
                    {
                      paramLexer.ungetToken();
                      localNode1 = paramLexer.inferredTag("table");
                    }
                    paramLexer.excludeBlocks = true;
                  }
                  else if (localNode1.tag == localTagTable.tagInput)
                  {
                    paramLexer.ungetToken();
                    localNode1 = paramLexer.inferredTag("form");
                    paramLexer.excludeBlocks = true;
                  }
                  else
                  {
                    if ((localNode1.tag.model & 0x600) != 0) {
                      continue;
                    }
                    paramLexer.ungetToken();
                  }
                }
                else
                {
                  if (localNode1.type == 6) {
                    if (localNode1.tag == localTagTable.tagBr)
                    {
                      localNode1.type = 5;
                    }
                    else if (localNode1.tag == localTagTable.tagP)
                    {
                      Node.coerceNode(paramLexer, localNode1, localTagTable.tagBr);
                      paramNode.insertNodeAtEnd(localNode1);
                      localNode1 = paramLexer.inferredTag("br");
                    }
                    else if ((localNode1.tag.model & 0x10) != 0)
                    {
                      paramLexer.popInline(localNode1);
                    }
                  }
                  if ((localNode1.type == 5) || (localNode1.type == 7))
                  {
                    if (((localNode1.tag.model & 0x10) != 0) && ((localNode1.tag.model & 0x20000) == 0))
                    {
                      if (localNode1.tag == localTagTable.tagImg) {
                        paramLexer.constrainVersion(-5);
                      } else {
                        paramLexer.constrainVersion(-6);
                      }
                      if ((i != 0) && (!localNode1.implicit))
                      {
                        i = 0;
                        if (paramLexer.inlineDup(localNode1) > 0) {}
                      }
                      else
                      {
                        paramShort = 1;
                      }
                    }
                    else
                    {
                      i = 1;
                      paramShort = 0;
                      if (localNode1.implicit) {
                        paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)15);
                      }
                      paramNode.insertNodeAtEnd(localNode1);
                      ParserImpl.parseTag(paramLexer, localNode1, paramShort);
                    }
                  }
                  else {
                    paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static class ParseScript
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      Node localNode = paramLexer.getCDATA(paramNode);
      if (localNode != null)
      {
        paramNode.insertNodeAtEnd(localNode);
      }
      else
      {
        paramLexer.report.error(paramLexer, paramNode, null, (short)6);
        return;
      }
      localNode = paramLexer.getToken((short)0);
      if ((localNode == null) || (localNode.type != 6) || (localNode.tag == null) || (!localNode.tag.name.equalsIgnoreCase(paramNode.tag.name)))
      {
        paramLexer.report.error(paramLexer, paramNode, localNode, (short)6);
        if (localNode != null) {
          paramLexer.ungetToken();
        }
      }
    }
  }
  
  public static class ParseTitle
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      Node localNode;
      while ((localNode = paramLexer.getToken((short)1)) != null) {
        if ((localNode.tag == paramNode.tag) && (localNode.type == 5))
        {
          paramLexer.report.warning(paramLexer, paramNode, localNode, (short)24);
          localNode.type = 6;
        }
        else
        {
          if ((localNode.tag == paramNode.tag) && (localNode.type == 6))
          {
            paramNode.closed = true;
            Node.trimSpaces(paramLexer, paramNode);
            return;
          }
          if (localNode.type == 4)
          {
            if (paramNode.content == null) {
              Node.trimInitialSpace(paramLexer, paramNode, localNode);
            }
            if (localNode.start < localNode.end) {
              paramNode.insertNodeAtEnd(localNode);
            }
          }
          else if (!Node.insertMisc(paramNode, localNode))
          {
            if (localNode.tag == null)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
            }
            else
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode, (short)7);
              paramLexer.ungetToken();
              Node.trimSpaces(paramLexer, paramNode);
              return;
            }
          }
        }
      }
      paramLexer.report.warning(paramLexer, paramNode, localNode, (short)6);
    }
  }
  
  public static class ParseHead
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      int i = 0;
      int j = 0;
      TagTable localTagTable = paramLexer.configuration.field_1881;
      Node localNode;
      while ((localNode = paramLexer.getToken((short)0)) != null)
      {
        if ((localNode.tag == paramNode.tag) && (localNode.type == 6))
        {
          paramNode.closed = true;
          break;
        }
        if (localNode.type == 4)
        {
          paramLexer.report.warning(paramLexer, paramNode, localNode, (short)11);
          paramLexer.ungetToken();
          break;
        }
        if (!Node.insertMisc(paramNode, localNode)) {
          if (localNode.type == 1)
          {
            Node.insertDocType(paramLexer, paramNode, localNode);
          }
          else if (localNode.tag == null)
          {
            paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
          }
          else
          {
            if (!TidyUtils.toBoolean(localNode.tag.model & 0x4))
            {
              if (paramLexer.isvoyager) {
                paramLexer.report.warning(paramLexer, paramNode, localNode, (short)11);
              }
              paramLexer.ungetToken();
              break;
            }
            if ((localNode.type == 5) || (localNode.type == 7))
            {
              if (localNode.tag == localTagTable.tagTitle)
              {
                i++;
                if (i > 1) {
                  paramLexer.report.warning(paramLexer, paramNode, localNode, (short)38);
                }
              }
              else if (localNode.tag == localTagTable.tagBase)
              {
                j++;
                if (j > 1) {
                  paramLexer.report.warning(paramLexer, paramNode, localNode, (short)38);
                }
              }
              else if (localNode.tag == localTagTable.tagNoscript)
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode, (short)11);
              }
              paramNode.insertNodeAtEnd(localNode);
              ParserImpl.parseTag(paramLexer, localNode, (short)0);
            }
            else
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode, (short)8);
            }
          }
        }
      }
    }
  }
  
  public static class ParseHTML
    implements Parser
  {
    public void parse(Lexer paramLexer, Node paramNode, short paramShort)
    {
      Object localObject1 = null;
      Object localObject2 = null;
      paramLexer.configuration.xmlTags = false;
      paramLexer.seenEndBody = false;
      TagTable localTagTable = paramLexer.configuration.field_1881;
      do
      {
        for (;;)
        {
          localNode1 = paramLexer.getToken((short)0);
          if (localNode1 == null)
          {
            localNode1 = paramLexer.inferredTag("head");
            break label132;
          }
          if (localNode1.tag == localTagTable.tagHead) {
            break label132;
          }
          if ((localNode1.tag != paramNode.tag) || (localNode1.type != 6)) {
            break;
          }
          paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
        }
      } while (Node.insertMisc(paramNode, localNode1));
      paramLexer.ungetToken();
      Node localNode1 = paramLexer.inferredTag("head");
      label132:
      Node localNode2 = localNode1;
      paramNode.insertNodeAtEnd(localNode2);
      ParserImpl.HEAD.parse(paramLexer, localNode2, paramShort);
      for (;;)
      {
        localNode1 = paramLexer.getToken((short)0);
        if (localNode1 == null)
        {
          if (localObject1 == null)
          {
            localNode1 = paramLexer.inferredTag("body");
            paramNode.insertNodeAtEnd(localNode1);
            ParserImpl.BODY.parse(paramLexer, localNode1, paramShort);
          }
          return;
        }
        if (localNode1.tag == paramNode.tag)
        {
          if ((localNode1.type != 5) && (localObject1 == null)) {
            paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
          } else if (localNode1.type == 6) {
            paramLexer.seenEndHtml = true;
          }
        }
        else if (!Node.insertMisc(paramNode, localNode1)) {
          if (localNode1.tag == localTagTable.tagBody)
          {
            if (localNode1.type != 5)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else if (localObject1 != null)
            {
              paramLexer.ungetToken();
              if (localObject2 == null)
              {
                localObject2 = paramLexer.inferredTag("noframes");
                localObject1.insertNodeAtEnd((Node)localObject2);
                paramLexer.report.warning(paramLexer, paramNode, (Node)localObject2, (short)15);
              }
              ParserImpl.parseTag(paramLexer, (Node)localObject2, paramShort);
            }
            else
            {
              paramLexer.constrainVersion(-17);
              break label739;
            }
          }
          else if (localNode1.tag == localTagTable.tagFrameset)
          {
            if (localNode1.type != 5)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              if (localObject1 != null) {
                paramLexer.report.error(paramLexer, paramNode, localNode1, (short)18);
              } else {
                localObject1 = localNode1;
              }
              paramNode.insertNodeAtEnd(localNode1);
              ParserImpl.parseTag(paramLexer, localNode1, paramShort);
              for (localNode1 = localObject1.content; localNode1 != null; localNode1 = localNode1.next) {
                if (localNode1.tag == localTagTable.tagNoframes) {
                  localObject2 = localNode1;
                }
              }
            }
          }
          else if (localNode1.tag == localTagTable.tagNoframes)
          {
            if (localNode1.type != 5)
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
            else
            {
              if (localObject1 == null)
              {
                paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
                localNode1 = paramLexer.inferredTag("body");
                break label739;
              }
              if (localObject2 == null)
              {
                localObject2 = localNode1;
                localObject1.insertNodeAtEnd((Node)localObject2);
              }
              ParserImpl.parseTag(paramLexer, (Node)localObject2, paramShort);
            }
          }
          else if ((localNode1.type == 5) || (localNode1.type == 7))
          {
            if ((localNode1.tag != null) && ((localNode1.tag.model & 0x4) != 0)) {
              ParserImpl.moveToHead(paramLexer, paramNode, localNode1);
            } else if ((localObject1 != null) && (localNode1.tag == localTagTable.tagFrame)) {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)8);
            }
          }
          else
          {
            paramLexer.ungetToken();
            if (localObject1 == null) {
              break;
            }
            if (localObject2 == null)
            {
              localObject2 = paramLexer.inferredTag("noframes");
              localObject1.insertNodeAtEnd((Node)localObject2);
            }
            else
            {
              paramLexer.report.warning(paramLexer, paramNode, localNode1, (short)26);
            }
            paramLexer.constrainVersion(16);
            ParserImpl.parseTag(paramLexer, (Node)localObject2, paramShort);
          }
        }
      }
      localNode1 = paramLexer.inferredTag("body");
      paramLexer.constrainVersion(-17);
      label739:
      paramNode.insertNodeAtEnd(localNode1);
      ParserImpl.parseTag(paramLexer, localNode1, paramShort);
      paramLexer.seenEndHtml = true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.ParserImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */