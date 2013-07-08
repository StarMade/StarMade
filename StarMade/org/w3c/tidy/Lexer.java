package org.w3c.tidy;

import java.io.PrintWriter;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Lexer
{
  public static final short IGNORE_WHITESPACE = 0;
  public static final short MIXED_CONTENT = 1;
  public static final short PREFORMATTED = 2;
  public static final short IGNORE_MARKUP = 3;
  private static final String VOYAGER_LOOSE = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";
  private static final String VOYAGER_STRICT = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
  private static final String VOYAGER_FRAMESET = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd";
  private static final String VOYAGER_11 = "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd";
  private static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";
  private static final W3CVersionInfo[] W3CVERSION = { new W3CVersionInfo("HTML 4.01", "XHTML 1.0 Strict", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd", 4), new W3CVersionInfo("HTML 4.01 Transitional", "XHTML 1.0 Transitional", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", 8), new W3CVersionInfo("HTML 4.01 Frameset", "XHTML 1.0 Frameset", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd", 16), new W3CVersionInfo("HTML 4.0", "XHTML 1.0 Strict", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd", 4), new W3CVersionInfo("HTML 4.0 Transitional", "XHTML 1.0 Transitional", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", 8), new W3CVersionInfo("HTML 4.0 Frameset", "XHTML 1.0 Frameset", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd", 16), new W3CVersionInfo("HTML 3.2", "XHTML 1.0 Transitional", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", 2), new W3CVersionInfo("HTML 3.2 Final", "XHTML 1.0 Transitional", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", 2), new W3CVersionInfo("HTML 3.2 Draft", "XHTML 1.0 Transitional", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd", 2), new W3CVersionInfo("HTML 2.0", "XHTML 1.0 Strict", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd", 1), new W3CVersionInfo("HTML 4.01", "XHTML 1.1", "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd", 1024) };
  private static final short LEX_CONTENT = 0;
  private static final short LEX_GT = 1;
  private static final short LEX_ENDTAG = 2;
  private static final short LEX_STARTTAG = 3;
  private static final short LEX_COMMENT = 4;
  private static final short LEX_DOCTYPE = 5;
  private static final short LEX_PROCINSTR = 6;
  private static final short LEX_CDATA = 8;
  private static final short LEX_SECTION = 9;
  private static final short LEX_ASP = 10;
  private static final short LEX_JSTE = 11;
  private static final short LEX_PHP = 12;
  private static final short LEX_XMLDECL = 13;
  protected StreamIn field_1662;
  protected PrintWriter errout;
  protected short badAccess;
  protected short badLayout;
  protected short badChars;
  protected short badForm;
  protected short warnings;
  protected short errors;
  protected int lines;
  protected int columns;
  protected boolean waswhite;
  protected boolean pushed;
  protected boolean insertspace;
  protected boolean excludeBlocks;
  protected boolean exiled;
  protected boolean isvoyager;
  protected short versions;
  protected int doctype;
  protected boolean badDoctype;
  protected int txtstart;
  protected int txtend;
  protected short state;
  protected Node token;
  protected byte[] lexbuf;
  protected int lexlength;
  protected int lexsize;
  protected Node inode;
  protected int insert;
  protected Stack istack;
  protected int istackbase;
  protected Style styles;
  protected Configuration configuration;
  protected boolean seenEndBody;
  protected boolean seenEndHtml;
  protected Report report;
  protected Node root;
  private List nodeList;
  private static final int CDATA_INTERMEDIATE = 0;
  private static final int CDATA_STARTTAG = 1;
  private static final int CDATA_ENDTAG = 2;
  
  public Lexer(StreamIn paramStreamIn, Configuration paramConfiguration, Report paramReport)
  {
    this.report = paramReport;
    this.field_1662 = paramStreamIn;
    this.lines = 1;
    this.columns = 1;
    this.state = 0;
    this.versions = 3551;
    this.doctype = 0;
    this.insert = -1;
    this.istack = new Stack();
    this.configuration = paramConfiguration;
    this.nodeList = new Vector();
  }
  
  public Node newNode()
  {
    Node localNode = new Node();
    this.nodeList.add(localNode);
    return localNode;
  }
  
  public Node newNode(short paramShort, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Node localNode = new Node(paramShort, paramArrayOfByte, paramInt1, paramInt2);
    this.nodeList.add(localNode);
    return localNode;
  }
  
  public Node newNode(short paramShort, byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    Node localNode = new Node(paramShort, paramArrayOfByte, paramInt1, paramInt2, paramString, this.configuration.field_1881);
    this.nodeList.add(localNode);
    return localNode;
  }
  
  public Node cloneNode(Node paramNode)
  {
    Node localNode = paramNode.cloneNode(false);
    this.nodeList.add(localNode);
    for (AttVal localAttVal = localNode.attributes; localAttVal != null; localAttVal = localAttVal.next)
    {
      if (localAttVal.asp != null) {
        this.nodeList.add(localAttVal.asp);
      }
      if (localAttVal.php != null) {
        this.nodeList.add(localAttVal.php);
      }
    }
    return localNode;
  }
  
  public AttVal cloneAttributes(AttVal paramAttVal)
  {
    AttVal localAttVal1 = (AttVal)paramAttVal.clone();
    for (AttVal localAttVal2 = localAttVal1; localAttVal2 != null; localAttVal2 = localAttVal2.next)
    {
      if (localAttVal2.asp != null) {
        this.nodeList.add(localAttVal2.asp);
      }
      if (localAttVal2.php != null) {
        this.nodeList.add(localAttVal2.php);
      }
    }
    return localAttVal1;
  }
  
  protected void updateNodeTextArrays(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    for (int i = 0; i < this.nodeList.size(); i++)
    {
      Node localNode = (Node)this.nodeList.get(i);
      if (localNode.textarray == paramArrayOfByte1) {
        localNode.textarray = paramArrayOfByte2;
      }
    }
  }
  
  public Node newLineNode()
  {
    Node localNode = newNode();
    localNode.textarray = this.lexbuf;
    localNode.start = this.lexsize;
    addCharToLexer(10);
    localNode.end = this.lexsize;
    return localNode;
  }
  
  public boolean endOfInput()
  {
    return this.field_1662.isEndOfStream();
  }
  
  public void addByte(int paramInt)
  {
    if (this.lexsize + 1 >= this.lexlength)
    {
      while (this.lexsize + 1 >= this.lexlength) {
        if (this.lexlength == 0) {
          this.lexlength = 8192;
        } else {
          this.lexlength *= 2;
        }
      }
      byte[] arrayOfByte = this.lexbuf;
      this.lexbuf = new byte[this.lexlength];
      if (arrayOfByte != null)
      {
        System.arraycopy(arrayOfByte, 0, this.lexbuf, 0, arrayOfByte.length);
        updateNodeTextArrays(arrayOfByte, this.lexbuf);
      }
    }
    this.lexbuf[(this.lexsize++)] = ((byte)paramInt);
    this.lexbuf[this.lexsize] = 0;
  }
  
  public void changeChar(byte paramByte)
  {
    if (this.lexsize > 0) {
      this.lexbuf[(this.lexsize - 1)] = paramByte;
    }
  }
  
  public void addCharToLexer(int paramInt)
  {
    if (((this.configuration.xmlOut) || (this.configuration.xHTML)) && ((paramInt < 32) || (paramInt > 55295)) && (paramInt != 9) && (paramInt != 10) && (paramInt != 13) && ((paramInt < 57344) || (paramInt > 65533)) && ((paramInt < 65536) || (paramInt > 1114111))) {
      return;
    }
    int i = 0;
    int[] arrayOfInt = { 0 };
    byte[] arrayOfByte = new byte[10];
    boolean bool = EncodingUtils.encodeCharToUTF8Bytes(paramInt, arrayOfByte, null, arrayOfInt);
    if (bool)
    {
      arrayOfByte[0] = -17;
      arrayOfByte[1] = -65;
      arrayOfByte[2] = -67;
      arrayOfInt[0] = 3;
    }
    for (i = 0; i < arrayOfInt[0]; i++) {
      addByte(arrayOfByte[i]);
    }
  }
  
  public void addStringToLexer(String paramString)
  {
    for (int i = 0; i < paramString.length(); i++) {
      addCharToLexer(paramString.charAt(i));
    }
  }
  
  public void parseEntity(short paramShort)
  {
    int j = 1;
    int k = 0;
    int i = this.lexsize - 1;
    int i1 = this.field_1662.getCurcol() - 1;
    int m;
    while ((m = this.field_1662.readChar()) != -1) {
      if (m == 59)
      {
        k = 1;
      }
      else if ((j != 0) && (m == 35))
      {
        if ((!this.configuration.ncr) || ("BIG5".equals(this.configuration.getInCharEncodingName())) || ("SHIFTJIS".equals(this.configuration.getInCharEncodingName())))
        {
          this.field_1662.ungetChar(m);
          return;
        }
        addCharToLexer(m);
        j = 0;
      }
      else
      {
        j = 0;
        if (TidyUtils.isNamechar((char)m)) {
          addCharToLexer(m);
        } else {
          this.field_1662.ungetChar(m);
        }
      }
    }
    String str = TidyUtils.getString(this.lexbuf, i, this.lexsize - i);
    if (("&apos".equals(str)) && (!this.configuration.xmlOut) && (!this.isvoyager) && (!this.configuration.xHTML)) {
      this.report.entityError(this, (short)5, str, 39);
    }
    int n = EntityTable.getDefaultEntityTable().entityCode(str);
    if ((n <= 0) || ((n >= 256) && (m != 59)))
    {
      this.lines = this.field_1662.getCurline();
      this.columns = i1;
      if (this.lexsize > i + 1)
      {
        if ((n >= 128) && (n <= 159))
        {
          int i2 = 0;
          if ("WIN1252".equals(this.configuration.replacementCharEncoding)) {
            i2 = EncodingUtils.decodeWin1252(n);
          } else if ("MACROMAN".equals(this.configuration.replacementCharEncoding)) {
            i2 = EncodingUtils.decodeMacRoman(n);
          }
          int i3 = i2 != 0 ? 0 : 1;
          if (m != 59) {
            this.report.entityError(this, (short)2, str, m);
          }
          this.report.encodingError(this, (short)(0x52 | i3), n);
          if (i2 != 0)
          {
            this.lexsize = i;
            addCharToLexer(i2);
            k = 0;
          }
          else
          {
            this.lexsize = i;
            k = 0;
          }
        }
        else
        {
          this.report.entityError(this, (short)3, str, n);
        }
        if (k != 0) {
          addCharToLexer(59);
        }
      }
      else
      {
        this.report.entityError(this, (short)4, str, n);
      }
    }
    else
    {
      if (m != 59)
      {
        this.lines = this.field_1662.getCurline();
        this.columns = i1;
        this.report.entityError(this, (short)1, str, m);
      }
      this.lexsize = i;
      if ((n == 160) && (TidyUtils.toBoolean(paramShort & 0x2))) {
        n = 32;
      }
      addCharToLexer(n);
      if ((n == 38) && (!this.configuration.quoteAmpersand))
      {
        addCharToLexer(97);
        addCharToLexer(109);
        addCharToLexer(112);
        addCharToLexer(59);
      }
    }
  }
  
  public char parseTagName()
  {
    int i = this.lexbuf[this.txtstart];
    if ((!this.configuration.xmlTags) && (TidyUtils.isUpper((char)i)))
    {
      i = TidyUtils.toLower((char)i);
      this.lexbuf[this.txtstart] = ((byte)i);
    }
    while (((i = this.field_1662.readChar()) != -1) && (TidyUtils.isNamechar((char)i)))
    {
      if ((!this.configuration.xmlTags) && (TidyUtils.isUpper((char)i))) {
        i = TidyUtils.toLower((char)i);
      }
      addCharToLexer(i);
    }
    this.txtend = this.lexsize;
    return (char)i;
  }
  
  public void addStringLiteral(String paramString)
  {
    int i = paramString.length();
    for (int j = 0; j < i; j++) {
      addCharToLexer(paramString.charAt(j));
    }
  }
  
  void addStringLiteralLen(String paramString, int paramInt)
  {
    int i = paramString.length();
    if (i < paramInt) {
      paramInt = i;
    }
    for (int j = 0; j < paramInt; j++) {
      addCharToLexer(paramString.charAt(j));
    }
  }
  
  public short htmlVersion()
  {
    if (TidyUtils.toBoolean(this.versions & 0x1)) {
      return 1;
    }
    if ((!(this.configuration.xmlOut | this.configuration.xmlTags | this.isvoyager)) && (TidyUtils.toBoolean(this.versions & 0x2))) {
      return 2;
    }
    if (TidyUtils.toBoolean(this.versions & 0x400)) {
      return 1024;
    }
    if (TidyUtils.toBoolean(this.versions & 0x4)) {
      return 4;
    }
    if (TidyUtils.toBoolean(this.versions & 0x8)) {
      return 8;
    }
    if (TidyUtils.toBoolean(this.versions & 0x10)) {
      return 16;
    }
    return 0;
  }
  
  public String htmlVersionName()
  {
    int i = apparentVersion();
    for (int j = 0; j < W3CVERSION.length; j++) {
      if (i == W3CVERSION[j].code)
      {
        if (this.isvoyager) {
          return W3CVERSION[j].voyagerName;
        }
        return W3CVERSION[j].name;
      }
    }
    return null;
  }
  
  public boolean addGenerator(Node paramNode)
  {
    Node localNode2 = paramNode.findHEAD(this.configuration.field_1881);
    if (localNode2 != null)
    {
      String str = "HTML Tidy for Java (vers. " + Report.RELEASE_DATE_STRING + "), see jtidy.sourceforge.net";
      for (Node localNode1 = localNode2.content; localNode1 != null; localNode1 = localNode1.next) {
        if (localNode1.tag == this.configuration.field_1881.tagMeta)
        {
          AttVal localAttVal = localNode1.getAttrByName("name");
          if ((localAttVal != null) && (localAttVal.value != null) && ("generator".equalsIgnoreCase(localAttVal.value)))
          {
            localAttVal = localNode1.getAttrByName("content");
            if ((localAttVal != null) && (localAttVal.value != null) && (localAttVal.value.length() >= 9) && ("HTML Tidy".equalsIgnoreCase(localAttVal.value.substring(0, 9))))
            {
              localAttVal.value = str;
              return false;
            }
          }
        }
      }
      localNode1 = inferredTag("meta");
      localNode1.addAttribute("content", str);
      localNode1.addAttribute("name", "generator");
      localNode2.insertNodeAtStart(localNode1);
      return true;
    }
    return false;
  }
  
  public boolean checkDocTypeKeyWords(Node paramNode)
  {
    int i = paramNode.end - paramNode.start;
    String str = TidyUtils.getString(this.lexbuf, paramNode.start, i);
    return (!TidyUtils.findBadSubString("SYSTEM", str, str.length())) && (!TidyUtils.findBadSubString("PUBLIC", str, str.length())) && (!TidyUtils.findBadSubString("//DTD", str, str.length())) && (!TidyUtils.findBadSubString("//W3C", str, str.length())) && (!TidyUtils.findBadSubString("//EN", str, str.length()));
  }
  
  public short findGivenVersion(Node paramNode)
  {
    String str3 = TidyUtils.getString(this.lexbuf, paramNode.start, 5);
    if (!"html ".equalsIgnoreCase(str3)) {
      return 0;
    }
    if (!checkDocTypeKeyWords(paramNode)) {
      this.report.warning(this, paramNode, null, (short)37);
    }
    str3 = TidyUtils.getString(this.lexbuf, paramNode.start + 5, 7);
    if ("SYSTEM ".equalsIgnoreCase(str3))
    {
      if (!str3.substring(0, 6).equals("SYSTEM")) {
        System.arraycopy(TidyUtils.getBytes("SYSTEM"), 0, this.lexbuf, paramNode.start + 5, 6);
      }
      return 0;
    }
    if ("PUBLIC ".equalsIgnoreCase(str3))
    {
      if (!str3.substring(0, 6).equals("PUBLIC")) {
        System.arraycopy(TidyUtils.getBytes("PUBLIC "), 0, this.lexbuf, paramNode.start + 5, 6);
      }
    }
    else {
      this.badDoctype = true;
    }
    for (int i = paramNode.start; i < paramNode.end; i++) {
      if (this.lexbuf[i] == 34)
      {
        str3 = TidyUtils.getString(this.lexbuf, i + 1, 12);
        String str4 = TidyUtils.getString(this.lexbuf, i + 1, 13);
        if (str3.equals("-//W3C//DTD "))
        {
          for (j = i + 13; (j < paramNode.end) && (this.lexbuf[j] != 47); j++) {}
          k = j - i - 13;
          str1 = TidyUtils.getString(this.lexbuf, i + 13, k);
          for (j = 1; j < W3CVERSION.length; j++)
          {
            str2 = W3CVERSION[j].name;
            if ((k == str2.length()) && (str2.equals(str1))) {
              return W3CVERSION[j].code;
            }
          }
        }
        if (!str4.equals("-//IETF//DTD ")) {
          break;
        }
        for (int j = i + 14; (j < paramNode.end) && (this.lexbuf[j] != 47); j++) {}
        int k = j - i - 14;
        String str1 = TidyUtils.getString(this.lexbuf, i + 14, k);
        String str2 = W3CVERSION[0].name;
        if ((k != str2.length()) || (!str2.equals(str1))) {
          break;
        }
        return W3CVERSION[0].code;
      }
    }
    return 0;
  }
  
  public void fixHTMLNameSpace(Node paramNode, String paramString)
  {
    for (Node localNode = paramNode.content; (localNode != null) && (localNode.tag != this.configuration.field_1881.tagHtml); localNode = localNode.next) {}
    if (localNode != null)
    {
      for (AttVal localAttVal = localNode.attributes; (localAttVal != null) && (!localAttVal.attribute.equals("xmlns")); localAttVal = localAttVal.next) {}
      if (localAttVal != null)
      {
        if (!localAttVal.value.equals(paramString))
        {
          this.report.warning(this, localNode, null, (short)33);
          localAttVal.value = paramString;
        }
      }
      else
      {
        localAttVal = new AttVal(localNode.attributes, null, 34, "xmlns", paramString);
        localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
        localNode.attributes = localAttVal;
      }
    }
  }
  
  Node newXhtmlDocTypeNode(Node paramNode)
  {
    Node localNode1 = paramNode.findHTML(this.configuration.field_1881);
    if (localNode1 == null) {
      return null;
    }
    Node localNode2 = newNode();
    localNode2.setType((short)1);
    localNode2.next = localNode1;
    localNode2.parent = paramNode;
    localNode2.prev = null;
    if (localNode1 == paramNode.content)
    {
      paramNode.content.prev = localNode2;
      paramNode.content = localNode2;
      localNode2.prev = null;
    }
    else
    {
      localNode2.prev = localNode1.prev;
      localNode2.prev.next = localNode2;
    }
    localNode1.prev = localNode2;
    return localNode2;
  }
  
  public boolean setXHTMLDocType(Node paramNode)
  {
    String str1 = " ";
    String str2 = "";
    String str3 = "http://www.w3.org/1999/xhtml";
    String str4 = null;
    int i = 0;
    Node localNode = paramNode.findDocType();
    fixHTMLNameSpace(paramNode, str3);
    if (this.configuration.docTypeMode == 0)
    {
      if (localNode != null) {
        Node.discardElement(localNode);
      }
      return true;
    }
    if (this.configuration.docTypeMode == 1)
    {
      if (TidyUtils.toBoolean(this.versions & 0x4))
      {
        str1 = "-//W3C//DTD XHTML 1.0 Strict//EN";
        str2 = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
      }
      else if (TidyUtils.toBoolean(this.versions & 0x10))
      {
        str1 = "-//W3C//DTD XHTML 1.0 Frameset//EN";
        str2 = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd";
      }
      else if (TidyUtils.toBoolean(this.versions & 0x1A))
      {
        str1 = "-//W3C//DTD XHTML 1.0 Transitional//EN";
        str2 = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";
      }
      else if (TidyUtils.toBoolean(this.versions & 0x400))
      {
        str1 = "-//W3C//DTD XHTML 1.1//EN";
        str2 = "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd";
      }
      else
      {
        str1 = null;
        str2 = "";
        if (localNode != null) {
          Node.discardElement(localNode);
        }
      }
    }
    else if (this.configuration.docTypeMode == 2)
    {
      str1 = "-//W3C//DTD XHTML 1.0 Strict//EN";
      str2 = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
    }
    else if (this.configuration.docTypeMode == 3)
    {
      str1 = "-//W3C//DTD XHTML 1.0 Transitional//EN";
      str2 = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";
    }
    if ((this.configuration.docTypeMode == 4) && (this.configuration.docTypeStr != null))
    {
      str1 = this.configuration.docTypeStr;
      str2 = "";
    }
    if (str1 == null) {
      return false;
    }
    if (localNode != null)
    {
      if ((this.configuration.xHTML) || (this.configuration.xmlOut))
      {
        j = localNode.end - localNode.start + 1;
        String str5 = TidyUtils.getString(this.lexbuf, localNode.start, j);
        int k = str5.indexOf('[');
        if (k >= 0)
        {
          int m = str5.substring(k).indexOf(']');
          if (m >= 0)
          {
            i = m + 1;
            str4 = str5.substring(k);
          }
        }
      }
    }
    else if ((localNode = newXhtmlDocTypeNode(paramNode)) == null) {
      return false;
    }
    this.txtstart = this.lexsize;
    this.txtend = this.lexsize;
    addStringLiteral("html PUBLIC ");
    if (str1.charAt(0) == '"')
    {
      addStringLiteral(str1);
    }
    else
    {
      addStringLiteral("\"");
      addStringLiteral(str1);
      addStringLiteral("\"");
    }
    if ((this.configuration.wraplen != 0) && (str2.length() + 6 >= this.configuration.wraplen)) {
      addStringLiteral("\n\"");
    } else {
      addStringLiteral(" \"");
    }
    addStringLiteral(str2);
    addStringLiteral("\"");
    if ((i > 0) && (str4 != null))
    {
      addCharToLexer(32);
      addStringLiteralLen(str4, i);
    }
    this.txtend = this.lexsize;
    int j = this.txtend - this.txtstart;
    localNode.textarray = new byte[j];
    System.arraycopy(this.lexbuf, this.txtstart, localNode.textarray, 0, j);
    localNode.start = 0;
    localNode.end = j;
    return false;
  }
  
  public short apparentVersion()
  {
    switch (this.doctype)
    {
    case 0: 
      return htmlVersion();
    case 1: 
      if (TidyUtils.toBoolean(this.versions & 0x1)) {
        return 1;
      }
      break;
    case 2: 
      if (TidyUtils.toBoolean(this.versions & 0x2)) {
        return 2;
      }
      break;
    case 4: 
      if (TidyUtils.toBoolean(this.versions & 0x4)) {
        return 4;
      }
      break;
    case 8: 
      if (TidyUtils.toBoolean(this.versions & 0x8)) {
        return 8;
      }
      break;
    case 16: 
      if (TidyUtils.toBoolean(this.versions & 0x10)) {
        return 16;
      }
      break;
    case 1024: 
      if (TidyUtils.toBoolean(this.versions & 0x400)) {
        return 1024;
      }
      break;
    }
    this.lines = 1;
    this.columns = 1;
    this.report.warning(this, null, null, (short)28);
    return htmlVersion();
  }
  
  public boolean fixDocType(Node paramNode)
  {
    int i = 4;
    if (this.badDoctype) {
      this.report.warning(this, null, null, (short)35);
    }
    Node localNode = paramNode.findDocType();
    if (this.configuration.docTypeMode == 0)
    {
      if (localNode != null) {
        Node.discardElement(localNode);
      }
      return true;
    }
    if (this.configuration.xmlOut) {
      return true;
    }
    if (this.configuration.docTypeMode == 2)
    {
      Node.discardElement(localNode);
      localNode = null;
      i = 4;
    }
    else if (this.configuration.docTypeMode == 3)
    {
      Node.discardElement(localNode);
      localNode = null;
      i = 8;
    }
    else if (this.configuration.docTypeMode == 1)
    {
      if (localNode != null)
      {
        if (this.doctype == 0) {
          return false;
        }
        switch (this.doctype)
        {
        case 0: 
          return false;
        case 1: 
          if (TidyUtils.toBoolean(this.versions & 0x1)) {
            return true;
          }
          break;
        case 2: 
          if (TidyUtils.toBoolean(this.versions & 0x2)) {
            return true;
          }
          break;
        case 4: 
          if (TidyUtils.toBoolean(this.versions & 0x4)) {
            return true;
          }
          break;
        case 8: 
          if (TidyUtils.toBoolean(this.versions & 0x8)) {
            return true;
          }
          break;
        case 16: 
          if (TidyUtils.toBoolean(this.versions & 0x10)) {
            return true;
          }
          break;
        case 1024: 
          if (TidyUtils.toBoolean(this.versions & 0x400)) {
            return true;
          }
          break;
        }
      }
      i = htmlVersion();
    }
    if (i == 0) {
      return false;
    }
    if ((this.configuration.xmlOut) || (this.configuration.xmlTags) || (this.isvoyager))
    {
      if (localNode != null) {
        Node.discardElement(localNode);
      }
      fixHTMLNameSpace(paramNode, "http://www.w3.org/1999/xhtml");
    }
    if ((localNode == null) && ((localNode = newXhtmlDocTypeNode(paramNode)) == null)) {
      return false;
    }
    this.txtstart = this.lexsize;
    this.txtend = this.lexsize;
    addStringLiteral("html PUBLIC ");
    if ((this.configuration.docTypeMode == 4) && (this.configuration.docTypeStr != null) && (this.configuration.docTypeStr.length() > 0))
    {
      if (this.configuration.docTypeStr.charAt(0) == '"')
      {
        addStringLiteral(this.configuration.docTypeStr);
      }
      else
      {
        addStringLiteral("\"");
        addStringLiteral(this.configuration.docTypeStr);
        addStringLiteral("\"");
      }
    }
    else if (i == 1)
    {
      addStringLiteral("\"-//IETF//DTD HTML 2.0//EN\"");
    }
    else
    {
      addStringLiteral("\"-//W3C//DTD ");
      for (int j = 0; j < W3CVERSION.length; j++) {
        if (i == W3CVERSION[j].code)
        {
          addStringLiteral(W3CVERSION[j].name);
          break;
        }
      }
      addStringLiteral("//EN\"");
    }
    this.txtend = this.lexsize;
    int k = this.txtend - this.txtstart;
    localNode.textarray = new byte[k];
    System.arraycopy(this.lexbuf, this.txtstart, localNode.textarray, 0, k);
    localNode.start = 0;
    localNode.end = k;
    return true;
  }
  
  public boolean fixXmlDecl(Node paramNode)
  {
    Node localNode;
    if ((paramNode.content != null) && (paramNode.content.type == 13))
    {
      localNode = paramNode.content;
    }
    else
    {
      localNode = newNode((short)13, this.lexbuf, 0, 0);
      localNode.next = paramNode.content;
      if (paramNode.content != null)
      {
        paramNode.content.prev = localNode;
        localNode.next = paramNode.content;
      }
      paramNode.content = localNode;
    }
    AttVal localAttVal1 = localNode.getAttrByName("version");
    AttVal localAttVal2 = localNode.getAttrByName("encoding");
    if ((localAttVal2 == null) && (!"UTF8".equals(this.configuration.getOutCharEncodingName())))
    {
      if ("ISO8859_1".equals(this.configuration.getOutCharEncodingName())) {
        localNode.addAttribute("encoding", "iso-8859-1");
      }
      if ("ISO2022".equals(this.configuration.getOutCharEncodingName())) {
        localNode.addAttribute("encoding", "iso-2022");
      }
    }
    if (localAttVal1 == null) {
      localNode.addAttribute("version", "1.0");
    }
    return true;
  }
  
  public Node inferredTag(String paramString)
  {
    Node localNode = newNode((short)5, this.lexbuf, this.txtstart, this.txtend, paramString);
    localNode.implicit = true;
    return localNode;
  }
  
  public Node getCDATA(Node paramNode)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    int n = 1;
    boolean bool = false;
    int i1 = paramNode.getAttrByName("src") != null ? 1 : 0;
    this.lines = this.field_1662.getCurline();
    this.columns = this.field_1662.getCurcol();
    this.waswhite = false;
    this.txtstart = this.lexsize;
    this.txtend = this.lexsize;
    int m;
    while ((m = this.field_1662.readChar()) != -1)
    {
      addCharToLexer(m);
      this.txtend = this.lexsize;
      if (k == 0)
      {
        if (m != 60)
        {
          if ((n != 0) && (!TidyUtils.isWhite((char)m))) {
            n = 0;
          }
        }
        else
        {
          m = this.field_1662.readChar();
          if (TidyUtils.isLetter((char)m))
          {
            if ((i1 != 0) && (n != 0) && (paramNode.tag == this.configuration.field_1881.tagScript))
            {
              this.lexsize = this.txtstart;
              this.field_1662.ungetChar(m);
              this.field_1662.ungetChar(60);
              return null;
            }
            addCharToLexer(m);
            i = this.lexsize - 1;
            k = 1;
          }
          else if (m == 47)
          {
            addCharToLexer(m);
            m = this.field_1662.readChar();
            if (!TidyUtils.isLetter((char)m))
            {
              this.field_1662.ungetChar(m);
            }
            else
            {
              this.field_1662.ungetChar(m);
              i = this.lexsize;
              k = 2;
            }
          }
          else if (m == 92)
          {
            addCharToLexer(m);
            m = this.field_1662.readChar();
            if (m != 47)
            {
              this.field_1662.ungetChar(m);
            }
            else
            {
              addCharToLexer(m);
              m = this.field_1662.readChar();
              if (!TidyUtils.isLetter((char)m))
              {
                this.field_1662.ungetChar(m);
              }
              else
              {
                this.field_1662.ungetChar(m);
                i = this.lexsize;
                k = 2;
              }
            }
          }
          else
          {
            this.field_1662.ungetChar(m);
          }
        }
      }
      else if (k == 1)
      {
        if (!TidyUtils.isLetter((char)m))
        {
          bool = paramNode.element.equalsIgnoreCase(TidyUtils.getString(this.lexbuf, i, paramNode.element.length()));
          if (bool) {
            j++;
          }
          k = 0;
        }
      }
      else if ((k == 2) && (!TidyUtils.isLetter((char)m)))
      {
        bool = paramNode.element.equalsIgnoreCase(TidyUtils.getString(this.lexbuf, i, paramNode.element.length()));
        int i2;
        if ((n != 0) && (!bool))
        {
          for (i2 = this.lexsize - 1; i2 >= i; i2--) {
            this.field_1662.ungetChar(this.lexbuf[i2]);
          }
          this.field_1662.ungetChar(47);
          this.field_1662.ungetChar(60);
          break;
        }
        if ((bool) && (j-- <= 0))
        {
          for (i2 = this.lexsize - 1; i2 >= i; i2--) {
            this.field_1662.ungetChar(this.lexbuf[i2]);
          }
          this.field_1662.ungetChar(47);
          this.field_1662.ungetChar(60);
          this.lexsize -= this.lexsize - i + 2;
          break;
        }
        if (this.lexbuf[(i - 2)] != 92)
        {
          this.lines = this.field_1662.getCurline();
          this.columns = this.field_1662.getCurcol();
          this.columns -= 3;
          this.report.error(this, null, null, (short)32);
          if (paramNode.isJavaScript())
          {
            for (i2 = this.lexsize; i2 > i - 1; i2--) {
              this.lexbuf[i2] = this.lexbuf[(i2 - 1)];
            }
            this.lexbuf[(i - 1)] = 92;
            this.lexsize += 1;
          }
        }
        k = 0;
      }
    }
    if (n != 0) {
      this.lexsize = (this.txtstart = this.txtend);
    } else {
      this.txtend = this.lexsize;
    }
    if (m == -1) {
      this.report.error(this, paramNode, null, (short)6);
    }
    return newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
  }
  
  public void ungetToken()
  {
    this.pushed = true;
  }
  
  public Node getToken(short paramShort)
  {
    int i = 0;
    int j = 0;
    boolean[] arrayOfBoolean = new boolean[1];
    int k = 0;
    Object localObject = null;
    if ((this.pushed) && ((this.token.type != 4) || ((this.insert == -1) && (this.inode == null))))
    {
      this.pushed = false;
      return this.token;
    }
    if ((this.insert != -1) || (this.inode != null)) {
      return insertedToken();
    }
    this.lines = this.field_1662.getCurline();
    this.columns = this.field_1662.getCurcol();
    this.waswhite = false;
    this.txtstart = this.lexsize;
    this.txtend = this.lexsize;
    while ((i = this.field_1662.readChar()) != -1)
    {
      if ((this.insertspace) && (paramShort != 0)) {
        addCharToLexer(32);
      }
      if ((this.insertspace) && (!TidyUtils.toBoolean(paramShort & 0x0)))
      {
        this.waswhite = true;
        this.insertspace = false;
      }
      if (i == 13)
      {
        i = this.field_1662.readChar();
        if (i != 10) {
          this.field_1662.ungetChar(i);
        }
        i = 10;
      }
      addCharToLexer(i);
      switch (this.state)
      {
      case 0: 
        if ((TidyUtils.isWhite((char)i)) && (paramShort == 0) && (this.lexsize == this.txtstart + 1))
        {
          this.lexsize -= 1;
          this.waswhite = false;
          this.lines = this.field_1662.getCurline();
          this.columns = this.field_1662.getCurcol();
        }
        else if (i == 60)
        {
          this.state = 1;
        }
        else if (TidyUtils.isWhite((char)i))
        {
          if (this.waswhite)
          {
            if ((paramShort != 2) && (paramShort != 3))
            {
              this.lexsize -= 1;
              this.lines = this.field_1662.getCurline();
              this.columns = this.field_1662.getCurcol();
            }
          }
          else
          {
            this.waswhite = true;
            if ((paramShort != 2) && (paramShort != 3) && (i != 32)) {
              changeChar((byte)32);
            }
          }
        }
        else
        {
          if ((i == 38) && (paramShort != 3)) {
            parseEntity(paramShort);
          }
          if (paramShort == 0) {
            paramShort = 1;
          }
          this.waswhite = false;
        }
        break;
      case 1: 
        if (i == 47)
        {
          i = this.field_1662.readChar();
          if (i == -1)
          {
            this.field_1662.ungetChar(i);
          }
          else
          {
            addCharToLexer(i);
            if (TidyUtils.isLetter((char)i))
            {
              this.lexsize -= 3;
              this.txtend = this.lexsize;
              this.field_1662.ungetChar(i);
              this.state = 2;
              this.lexbuf[this.lexsize] = 0;
              this.columns -= 2;
              if (this.txtend > this.txtstart)
              {
                if ((paramShort == 0) && (this.lexbuf[(this.lexsize - 1)] == 32))
                {
                  this.lexsize -= 1;
                  this.txtend = this.lexsize;
                }
                this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
                return this.token;
              }
            }
            else
            {
              this.waswhite = false;
              this.state = 0;
            }
          }
        }
        else if (paramShort == 3)
        {
          this.waswhite = false;
          this.state = 0;
        }
        else if (i == 33)
        {
          i = this.field_1662.readChar();
          if (i == 45)
          {
            i = this.field_1662.readChar();
            if (i == 45)
            {
              this.state = 4;
              this.lexsize -= 2;
              this.txtend = this.lexsize;
              if (this.txtend > this.txtstart)
              {
                this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
                return this.token;
              }
              this.txtstart = this.lexsize;
            }
            else
            {
              this.report.warning(this, null, null, (short)29);
            }
          }
          else if ((i == 100) || (i == 68))
          {
            this.state = 5;
            this.lexsize -= 2;
            this.txtend = this.lexsize;
            paramShort = 0;
            do
            {
              i = this.field_1662.readChar();
              if ((i == -1) || (i == 62))
              {
                this.field_1662.ungetChar(i);
                break;
              }
            } while (!TidyUtils.isWhite((char)i));
            do
            {
              i = this.field_1662.readChar();
              if ((i == -1) || (i == 62))
              {
                this.field_1662.ungetChar(i);
                break;
              }
            } while (TidyUtils.isWhite((char)i));
            this.field_1662.ungetChar(i);
            if (this.txtend > this.txtstart)
            {
              this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
              return this.token;
            }
            this.txtstart = this.lexsize;
          }
          else if (i == 91)
          {
            this.lexsize -= 2;
            this.state = 9;
            this.txtend = this.lexsize;
            if (this.txtend > this.txtstart)
            {
              this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
              return this.token;
            }
            this.txtstart = this.lexsize;
          }
          else
          {
            do
            {
              i = this.field_1662.readChar();
              if (i == 62) {
                break;
              }
            } while (i != -1);
            this.field_1662.ungetChar(i);
            this.lexsize -= 2;
            this.lexbuf[this.lexsize] = 0;
            this.state = 0;
          }
        }
        else if (i == 63)
        {
          this.lexsize -= 2;
          this.state = 6;
          this.txtend = this.lexsize;
          if (this.txtend > this.txtstart)
          {
            this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
          this.txtstart = this.lexsize;
        }
        else if (i == 37)
        {
          this.lexsize -= 2;
          this.state = 10;
          this.txtend = this.lexsize;
          if (this.txtend > this.txtstart)
          {
            this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
          this.txtstart = this.lexsize;
        }
        else if (i == 35)
        {
          this.lexsize -= 2;
          this.state = 11;
          this.txtend = this.lexsize;
          if (this.txtend > this.txtstart)
          {
            this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
          this.txtstart = this.lexsize;
        }
        else if (TidyUtils.isLetter((char)i))
        {
          this.field_1662.ungetChar(i);
          this.lexsize -= 2;
          this.txtend = this.lexsize;
          this.state = 3;
          if (this.txtend > this.txtstart)
          {
            this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
        }
        else
        {
          this.state = 0;
          this.waswhite = false;
        }
        break;
      case 2: 
        this.txtstart = (this.lexsize - 1);
        this.columns -= 2;
        i = parseTagName();
        this.token = newNode((short)6, this.lexbuf, this.txtstart, this.txtend, TidyUtils.getString(this.lexbuf, this.txtstart, this.txtend - this.txtstart));
        this.lexsize = this.txtstart;
        this.txtend = this.txtstart;
        while (TidyUtils.isWhite((char)i)) {
          i = this.field_1662.readChar();
        }
        if (i == -1)
        {
          this.field_1662.ungetChar(i);
          this.report.attrError(this, this.token, null, (short)52);
        }
        else
        {
          if (i != 62)
          {
            this.field_1662.ungetChar(i);
            i = 62;
            this.report.attrError(this, this.token, null, (short)52);
          }
          this.state = 0;
          this.waswhite = false;
          return this.token;
        }
        break;
      case 3: 
        this.txtstart = (this.lexsize - 1);
        i = parseTagName();
        arrayOfBoolean[0] = false;
        localObject = null;
        this.token = newNode((short)(arrayOfBoolean[0] != 0 ? 7 : 5), this.lexbuf, this.txtstart, this.txtend, TidyUtils.getString(this.lexbuf, this.txtstart, this.txtend - this.txtstart));
        if (i != 62)
        {
          if (i == 47) {
            this.field_1662.ungetChar(i);
          }
          localObject = parseAttrs(arrayOfBoolean);
        }
        if (arrayOfBoolean[0] != 0) {
          this.token.type = 7;
        }
        this.token.attributes = ((AttVal)localObject);
        this.lexsize = this.txtstart;
        this.txtend = this.txtstart;
        if (((paramShort != 2) || (preContent(this.token))) && ((this.token.expectsContent()) || (this.token.tag == this.configuration.field_1881.tagBr)))
        {
          i = this.field_1662.readChar();
          if (i == 13)
          {
            i = this.field_1662.readChar();
            if (i != 10) {
              this.field_1662.ungetChar(i);
            }
          }
          else if ((i != 10) && (i != 12))
          {
            this.field_1662.ungetChar(i);
          }
          this.waswhite = true;
        }
        else
        {
          this.waswhite = false;
        }
        this.state = 0;
        if (this.token.tag == null)
        {
          this.report.error(this, null, this.token, (short)22);
        }
        else if (!this.configuration.xmlTags)
        {
          constrainVersion(this.token.tag.versions);
          if (TidyUtils.toBoolean(this.token.tag.versions & 0x1C0)) {
            if ((this.configuration.makeClean) && (this.token.tag != this.configuration.field_1881.tagNobr) && (this.token.tag != this.configuration.field_1881.tagWbr)) {
              this.report.warning(this, null, this.token, (short)21);
            } else if (!this.configuration.makeClean) {
              this.report.warning(this, null, this.token, (short)21);
            }
          }
          if (this.token.tag.getChkattrs() != null) {
            this.token.tag.getChkattrs().check(this, this.token);
          } else {
            this.token.checkAttributes(this);
          }
          this.token.repairDuplicateAttributes(this);
        }
        return this.token;
      case 4: 
        if (i == 45)
        {
          i = this.field_1662.readChar();
          addCharToLexer(i);
          if (i == 45)
          {
            for (;;)
            {
              i = this.field_1662.readChar();
              if (i == 62)
              {
                if (j != 0) {
                  this.report.warning(this, null, null, (short)29);
                }
                this.txtend = (this.lexsize - 2);
                this.lexbuf[this.lexsize] = 0;
                this.state = 0;
                this.waswhite = false;
                this.token = newNode((short)2, this.lexbuf, this.txtstart, this.txtend);
                i = this.field_1662.readChar();
                if (i == 13)
                {
                  i = this.field_1662.readChar();
                  if (i != 10) {
                    this.token.linebreak = true;
                  }
                }
                if (i == 10) {
                  this.token.linebreak = true;
                } else {
                  this.field_1662.ungetChar(i);
                }
                return this.token;
              }
              if (j == 0)
              {
                this.lines = this.field_1662.getCurline();
                this.columns = (this.field_1662.getCurcol() - 3);
              }
              j++;
              if (this.configuration.fixComments) {
                this.lexbuf[(this.lexsize - 2)] = 61;
              }
              addCharToLexer(i);
              if (i != 45) {
                break;
              }
            }
            this.lexbuf[(this.lexsize - 2)] = 61;
          }
        }
        break;
      case 5: 
        if (TidyUtils.isWhite((char)i))
        {
          if (this.waswhite) {
            this.lexsize -= 1;
          }
          this.waswhite = true;
        }
        else
        {
          this.waswhite = false;
        }
        if (k != 0)
        {
          if (i == 93) {
            k = 0;
          }
        }
        else if (i == 91) {
          k = 1;
        }
        if ((k == 0) && (i == 62))
        {
          this.lexsize -= 1;
          this.txtend = this.lexsize;
          this.lexbuf[this.lexsize] = 0;
          this.state = 0;
          this.waswhite = false;
          this.token = newNode((short)1, this.lexbuf, this.txtstart, this.txtend);
          this.doctype = findGivenVersion(this.token);
          return this.token;
        }
        break;
      case 6: 
        if ((this.lexsize - this.txtstart == 3) && (TidyUtils.getString(this.lexbuf, this.txtstart, 3).equals("php")))
        {
          this.state = 12;
        }
        else if ((this.lexsize - this.txtstart == 4) && (TidyUtils.getString(this.lexbuf, this.txtstart, 3).equals("xml")) && (TidyUtils.isWhite((char)this.lexbuf[(this.txtstart + 3)])))
        {
          this.state = 13;
          localObject = null;
        }
        else if (this.configuration.xmlPIs)
        {
          if (i == 63)
          {
            i = this.field_1662.readChar();
            if (i == -1)
            {
              this.report.warning(this, null, null, (short)36);
              this.field_1662.ungetChar(i);
            }
            else
            {
              addCharToLexer(i);
            }
          }
        }
        else if (i == 62)
        {
          this.lexsize -= 1;
          this.txtend = this.lexsize;
          this.lexbuf[this.lexsize] = 0;
          this.state = 0;
          this.waswhite = false;
          this.token = newNode((short)3, this.lexbuf, this.txtstart, this.txtend);
          return this.token;
        }
        break;
      case 10: 
        if (i == 37)
        {
          i = this.field_1662.readChar();
          if (i != 62)
          {
            this.field_1662.ungetChar(i);
          }
          else
          {
            this.lexsize -= 1;
            this.txtend = this.lexsize;
            this.lexbuf[this.lexsize] = 0;
            this.state = 0;
            this.waswhite = false;
            this.token = newNode((short)10, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
        }
        break;
      case 11: 
        if (i == 35)
        {
          i = this.field_1662.readChar();
          if (i != 62)
          {
            this.field_1662.ungetChar(i);
          }
          else
          {
            this.lexsize -= 1;
            this.txtend = this.lexsize;
            this.lexbuf[this.lexsize] = 0;
            this.state = 0;
            this.waswhite = false;
            this.token = newNode((short)11, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
        }
        break;
      case 12: 
        if (i == 63)
        {
          i = this.field_1662.readChar();
          if (i != 62)
          {
            this.field_1662.ungetChar(i);
          }
          else
          {
            this.lexsize -= 1;
            this.txtend = this.lexsize;
            this.lexbuf[this.lexsize] = 0;
            this.state = 0;
            this.waswhite = false;
            this.token = newNode((short)12, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
        }
        break;
      case 13: 
        if ((!TidyUtils.isWhite((char)i)) || (i == 63))
        {
          if (i != 63)
          {
            Node[] arrayOfNode1 = new Node[1];
            Node[] arrayOfNode2 = new Node[1];
            AttVal localAttVal = new AttVal();
            int[] arrayOfInt = new int[1];
            arrayOfBoolean[0] = false;
            this.field_1662.ungetChar(i);
            String str = parseAttribute(arrayOfBoolean, arrayOfNode1, arrayOfNode2);
            localAttVal.attribute = str;
            localAttVal.value = parseValue(str, true, arrayOfBoolean, arrayOfInt);
            localAttVal.delim = arrayOfInt[0];
            localAttVal.next = ((AttVal)localObject);
            localObject = localAttVal;
          }
          i = this.field_1662.readChar();
          if (i != 62)
          {
            this.field_1662.ungetChar(i);
          }
          else
          {
            this.lexsize -= 1;
            this.txtend = this.txtstart;
            this.lexbuf[this.txtend] = 0;
            this.state = 0;
            this.waswhite = false;
            this.token = newNode((short)13, this.lexbuf, this.txtstart, this.txtend);
            this.token.attributes = ((AttVal)localObject);
            return this.token;
          }
        }
        break;
      case 9: 
        if ((i == 91) && (this.lexsize == this.txtstart + 6) && (TidyUtils.getString(this.lexbuf, this.txtstart, 6).equals("CDATA[")))
        {
          this.state = 8;
          this.lexsize -= 6;
        }
        else if (i == 93)
        {
          i = this.field_1662.readChar();
          if (i != 62)
          {
            this.field_1662.ungetChar(i);
          }
          else
          {
            this.lexsize -= 1;
            this.txtend = this.lexsize;
            this.lexbuf[this.lexsize] = 0;
            this.state = 0;
            this.waswhite = false;
            this.token = newNode((short)9, this.lexbuf, this.txtstart, this.txtend);
            return this.token;
          }
        }
        break;
      case 8: 
        if (i == 93)
        {
          i = this.field_1662.readChar();
          if (i != 93)
          {
            this.field_1662.ungetChar(i);
          }
          else
          {
            i = this.field_1662.readChar();
            if (i != 62)
            {
              this.field_1662.ungetChar(i);
            }
            else
            {
              this.lexsize -= 1;
              this.txtend = this.lexsize;
              this.lexbuf[this.lexsize] = 0;
              this.state = 0;
              this.waswhite = false;
              this.token = newNode((short)8, this.lexbuf, this.txtstart, this.txtend);
              return this.token;
            }
          }
        }
        break;
      }
    }
    if (this.state == 0)
    {
      this.txtend = this.lexsize;
      if (this.txtend > this.txtstart)
      {
        this.field_1662.ungetChar(i);
        if (this.lexbuf[(this.lexsize - 1)] == 32)
        {
          this.lexsize -= 1;
          this.txtend = this.lexsize;
        }
        this.token = newNode((short)4, this.lexbuf, this.txtstart, this.txtend);
        return this.token;
      }
    }
    else if (this.state == 4)
    {
      if (i == -1) {
        this.report.warning(this, null, null, (short)29);
      }
      this.txtend = this.lexsize;
      this.lexbuf[this.lexsize] = 0;
      this.state = 0;
      this.waswhite = false;
      this.token = newNode((short)2, this.lexbuf, this.txtstart, this.txtend);
      return this.token;
    }
    return null;
  }
  
  public Node parseAsp()
  {
    Node localNode = null;
    this.txtstart = this.lexsize;
    int i;
    while ((i = this.field_1662.readChar()) != -1)
    {
      addCharToLexer(i);
      if (i == 37) {
        if ((i = this.field_1662.readChar()) != -1)
        {
          addCharToLexer(i);
          if (i == 62) {
            break;
          }
        }
      }
    }
    this.lexsize -= 2;
    this.txtend = this.lexsize;
    if (this.txtend > this.txtstart) {
      localNode = newNode((short)10, this.lexbuf, this.txtstart, this.txtend);
    }
    this.txtstart = this.txtend;
    return localNode;
  }
  
  public Node parsePhp()
  {
    Node localNode = null;
    this.txtstart = this.lexsize;
    int i;
    while ((i = this.field_1662.readChar()) != -1)
    {
      addCharToLexer(i);
      if (i == 63) {
        if ((i = this.field_1662.readChar()) != -1)
        {
          addCharToLexer(i);
          if (i == 62) {
            break;
          }
        }
      }
    }
    this.lexsize -= 2;
    this.txtend = this.lexsize;
    if (this.txtend > this.txtstart) {
      localNode = newNode((short)12, this.lexbuf, this.txtstart, this.txtend);
    }
    this.txtstart = this.txtend;
    return localNode;
  }
  
  public String parseAttribute(boolean[] paramArrayOfBoolean, Node[] paramArrayOfNode1, Node[] paramArrayOfNode2)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    paramArrayOfNode1[0] = null;
    paramArrayOfNode2[0] = null;
    for (;;)
    {
      j = this.field_1662.readChar();
      if (j == 47)
      {
        j = this.field_1662.readChar();
        if (j == 62)
        {
          paramArrayOfBoolean[0] = true;
          return null;
        }
        this.field_1662.ungetChar(j);
        j = 47;
      }
      else
      {
        if (j == 62) {
          return null;
        }
        if (j == 60)
        {
          j = this.field_1662.readChar();
          if (j == 37)
          {
            paramArrayOfNode1[0] = parseAsp();
            return null;
          }
          if (j == 63)
          {
            paramArrayOfNode2[0] = parsePhp();
            return null;
          }
          this.field_1662.ungetChar(j);
          if (this.state != 13) {
            this.field_1662.ungetChar(60);
          }
          this.report.attrError(this, this.token, null, (short)52);
          return null;
        }
        if (j == 61)
        {
          this.report.attrError(this, this.token, null, (short)69);
        }
        else if ((j == 34) || (j == 39))
        {
          this.report.attrError(this, this.token, null, (short)59);
        }
        else
        {
          if (j == -1)
          {
            this.report.attrError(this, this.token, null, (short)36);
            this.field_1662.ungetChar(j);
            return null;
          }
          if (!TidyUtils.isWhite((char)j)) {
            break;
          }
        }
      }
    }
    i = this.lexsize;
    k = j;
    for (;;)
    {
      if ((j == 61) || (j == 62))
      {
        this.field_1662.ungetChar(j);
        break;
      }
      if ((j == 60) || (j == -1))
      {
        this.field_1662.ungetChar(j);
        break;
      }
      if ((k == 45) && ((j == 34) || (j == 39)))
      {
        this.lexsize -= 1;
        this.field_1662.ungetChar(j);
        break;
      }
      if (TidyUtils.isWhite((char)j)) {
        break;
      }
      if ((!this.configuration.xmlTags) && (TidyUtils.isUpper((char)j))) {
        j = TidyUtils.toLower((char)j);
      }
      addCharToLexer(j);
      k = j;
      j = this.field_1662.readChar();
    }
    int m = this.lexsize - i;
    String str = m > 0 ? TidyUtils.getString(this.lexbuf, i, m) : null;
    this.lexsize = i;
    return str;
  }
  
  public int parseServerInstruction()
  {
    int j = 34;
    int k = 0;
    int i = this.field_1662.readChar();
    addCharToLexer(i);
    if ((i == 37) || (i == 63) || (i == 64)) {
      k = 1;
    }
    for (;;)
    {
      i = this.field_1662.readChar();
      if (i == -1) {
        break;
      }
      if (i == 62)
      {
        if (k != 0)
        {
          addCharToLexer(i);
          break;
        }
        this.field_1662.ungetChar(i);
        break;
      }
      if ((k == 0) && (TidyUtils.isWhite((char)i))) {
        break;
      }
      addCharToLexer(i);
      if (i == 34)
      {
        do
        {
          i = this.field_1662.readChar();
          if (endOfInput())
          {
            this.report.attrError(this, this.token, null, (short)36);
            this.field_1662.ungetChar(i);
            return 0;
          }
          if (i == 62)
          {
            this.field_1662.ungetChar(i);
            this.report.attrError(this, this.token, null, (short)52);
            return 0;
          }
          addCharToLexer(i);
        } while (i != 34);
        j = 39;
      }
      else if (i == 39)
      {
        do
        {
          i = this.field_1662.readChar();
          if (endOfInput())
          {
            this.report.attrError(this, this.token, null, (short)36);
            this.field_1662.ungetChar(i);
            return 0;
          }
          if (i == 62)
          {
            this.field_1662.ungetChar(i);
            this.report.attrError(this, this.token, null, (short)52);
            return 0;
          }
          addCharToLexer(i);
        } while (i != 39);
      }
    }
    return j;
  }
  
  public String parseValue(String paramString, boolean paramBoolean, boolean[] paramArrayOfBoolean, int[] paramArrayOfInt)
  {
    int i = 0;
    int k = 0;
    int m = 1;
    int n = 0;
    int i2 = 0;
    paramArrayOfInt[0] = 34;
    if (this.configuration.literalAttribs) {
      m = 0;
    }
    for (;;)
    {
      n = this.field_1662.readChar();
      if (n == -1) {
        this.field_1662.ungetChar(n);
      } else if (!TidyUtils.isWhite((char)n)) {
        break;
      }
    }
    if ((n != 61) && (n != 34) && (n != 39))
    {
      this.field_1662.ungetChar(n);
      return null;
    }
    for (;;)
    {
      n = this.field_1662.readChar();
      if (n == -1) {
        this.field_1662.ungetChar(n);
      } else if (!TidyUtils.isWhite((char)n)) {
        break;
      }
    }
    if ((n == 34) || (n == 39))
    {
      i2 = n;
    }
    else
    {
      if (n == 60)
      {
        j = this.lexsize;
        addCharToLexer(n);
        paramArrayOfInt[0] = parseServerInstruction();
        i = this.lexsize - j;
        this.lexsize = j;
        return i > 0 ? TidyUtils.getString(this.lexbuf, j, i) : null;
      }
      this.field_1662.ungetChar(n);
    }
    int i3 = 0;
    int j = this.lexsize;
    n = 0;
    for (;;)
    {
      int i1 = n;
      n = this.field_1662.readChar();
      if (n == -1)
      {
        this.report.attrError(this, this.token, null, (short)36);
        this.field_1662.ungetChar(n);
        break;
      }
      if (i2 == 0)
      {
        if (n == 62)
        {
          this.field_1662.ungetChar(n);
          break;
        }
        if ((n == 34) || (n == 39))
        {
          int i4 = n;
          this.report.attrError(this, this.token, null, (short)59);
          n = this.field_1662.readChar();
          if (n == 62)
          {
            addCharToLexer(i4);
            this.field_1662.ungetChar(n);
            break;
          }
          this.field_1662.ungetChar(n);
          n = i4;
        }
        if (n == 60)
        {
          this.field_1662.ungetChar(n);
          n = 62;
          this.field_1662.ungetChar(n);
          this.report.attrError(this, this.token, null, (short)52);
          break;
        }
        if (n == 47)
        {
          n = this.field_1662.readChar();
          if ((n == 62) && (!AttributeTable.getDefaultAttributeTable().isUrl(paramString)))
          {
            paramArrayOfBoolean[0] = true;
            this.field_1662.ungetChar(n);
            break;
          }
          this.field_1662.ungetChar(n);
          n = 47;
        }
      }
      else
      {
        if (n == i2) {
          break;
        }
        if (n == 13)
        {
          n = this.field_1662.readChar();
          if (n != 10) {
            this.field_1662.ungetChar(n);
          }
          n = 10;
        }
        if ((n == 10) || (n == 60) || (n == 62)) {
          i3++;
        }
        if (n == 62) {
          k = 1;
        }
      }
      if (n == 38)
      {
        if ("id".equalsIgnoreCase(paramString))
        {
          this.report.attrError(this, null, null, (short)67);
        }
        else
        {
          addCharToLexer(n);
          parseEntity((short)0);
        }
      }
      else
      {
        if (n == 92)
        {
          n = this.field_1662.readChar();
          if (n != 10)
          {
            this.field_1662.ungetChar(n);
            n = 92;
          }
        }
        if (TidyUtils.isWhite((char)n))
        {
          if (i2 == 0) {
            break;
          }
          if (m != 0)
          {
            if ((n == 10) && (AttributeTable.getDefaultAttributeTable().isUrl(paramString)))
            {
              this.report.attrError(this, this.token, null, (short)65);
              continue;
            }
            n = 32;
            if (i1 != 32) {}
          }
        }
        else
        {
          if ((paramBoolean) && (TidyUtils.isUpper((char)n))) {
            n = TidyUtils.toLower((char)n);
          }
          addCharToLexer(n);
        }
      }
    }
    if ((i3 > 10) && (k != 0) && (m != 0) && (!AttributeTable.getDefaultAttributeTable().isScript(paramString)) && ((!AttributeTable.getDefaultAttributeTable().isUrl(paramString)) || (!"javascript:".equals(TidyUtils.getString(this.lexbuf, j, 11)))) && (!"<xml ".equals(TidyUtils.getString(this.lexbuf, j, 5)))) {
      this.report.error(this, null, null, (short)16);
    }
    i = this.lexsize - j;
    this.lexsize = j;
    String str;
    if ((i > 0) || (i2 != 0))
    {
      if (m != 0) {
        if (!TidyUtils.isInValuesIgnoreCase(new String[] { "alt", "title", "value", "prompt" }, paramString))
        {
          while (TidyUtils.isWhite((char)this.lexbuf[(j + i - 1)])) {
            i--;
          }
          while ((TidyUtils.isWhite((char)this.lexbuf[j])) && (j < i))
          {
            j++;
            i--;
          }
        }
      }
      str = TidyUtils.getString(this.lexbuf, j, i);
    }
    else
    {
      str = null;
    }
    if (i2 != 0) {
      paramArrayOfInt[0] = i2;
    } else {
      paramArrayOfInt[0] = 34;
    }
    return str;
  }
  
  public static boolean isValidAttrName(String paramString)
  {
    char c = paramString.charAt(0);
    if (!TidyUtils.isLetter(c)) {
      return false;
    }
    for (int i = 1; i < paramString.length(); i++)
    {
      c = paramString.charAt(i);
      if (!TidyUtils.isNamechar(c)) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isCSS1Selector(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    boolean bool = true;
    int i = 0;
    for (int j = 0; (bool) && (j < paramString.length()); j++)
    {
      char c = paramString.charAt(j);
      if (c == '\\')
      {
        i = 1;
      }
      else if (Character.isDigit(c))
      {
        if (i > 0)
        {
          i++;
          bool = i < 6;
        }
        if (bool) {
          bool = (j > 0) || (i > 0);
        }
      }
      else
      {
        bool = (i > 0) || ((j > 0) && (c == '-')) || (Character.isLetter(c)) || ((c >= '¡') && (c <= 'ÿ'));
        i = 0;
      }
    }
    return bool;
  }
  
  public AttVal parseAttrs(boolean[] paramArrayOfBoolean)
  {
    int[] arrayOfInt = new int[1];
    Node[] arrayOfNode1 = new Node[1];
    Node[] arrayOfNode2 = new Node[1];
    Object localObject = null;
    while (!endOfInput())
    {
      String str1 = parseAttribute(paramArrayOfBoolean, arrayOfNode1, arrayOfNode2);
      AttVal localAttVal;
      if (str1 == null)
      {
        if (arrayOfNode1[0] != null)
        {
          localAttVal = new AttVal((AttVal)localObject, null, arrayOfNode1[0], null, 0, null, null);
          localObject = localAttVal;
        }
        else
        {
          if (arrayOfNode2[0] == null) {
            break;
          }
          localAttVal = new AttVal((AttVal)localObject, null, null, arrayOfNode2[0], 0, null, null);
          localObject = localAttVal;
        }
      }
      else
      {
        String str2 = parseValue(str1, false, paramArrayOfBoolean, arrayOfInt);
        if ((str1 != null) && (isValidAttrName(str1)))
        {
          localAttVal = new AttVal((AttVal)localObject, null, null, null, arrayOfInt[0], str1, str2);
          localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
          localObject = localAttVal;
        }
        else
        {
          localAttVal = new AttVal(null, null, null, null, 0, str1, str2);
          if (str2 != null) {
            this.report.attrError(this, this.token, localAttVal, (short)51);
          } else if (TidyUtils.lastChar(str1) == 34) {
            this.report.attrError(this, this.token, localAttVal, (short)58);
          } else {
            this.report.attrError(this, this.token, localAttVal, (short)48);
          }
        }
      }
    }
    return localObject;
  }
  
  public void pushInline(Node paramNode)
  {
    if (paramNode.implicit) {
      return;
    }
    if (paramNode.tag == null) {
      return;
    }
    if (!TidyUtils.toBoolean(paramNode.tag.model & 0x10)) {
      return;
    }
    if (TidyUtils.toBoolean(paramNode.tag.model & 0x800)) {
      return;
    }
    if ((paramNode.tag != this.configuration.field_1881.tagFont) && (isPushed(paramNode))) {
      return;
    }
    IStack localIStack = new IStack();
    localIStack.tag = paramNode.tag;
    localIStack.element = paramNode.element;
    if (paramNode.attributes != null) {
      localIStack.attributes = cloneAttributes(paramNode.attributes);
    }
    this.istack.push(localIStack);
  }
  
  public void popInline(Node paramNode)
  {
    IStack localIStack;
    if (paramNode != null)
    {
      if (paramNode.tag == null) {
        return;
      }
      if (!TidyUtils.toBoolean(paramNode.tag.model & 0x10)) {
        return;
      }
      if (TidyUtils.toBoolean(paramNode.tag.model & 0x800)) {
        return;
      }
      if (paramNode.tag == this.configuration.field_1881.tagA)
      {
        while (this.istack.size() > 0)
        {
          localIStack = (IStack)this.istack.pop();
          if (localIStack.tag == this.configuration.field_1881.tagA) {
            break;
          }
        }
        if (this.insert >= this.istack.size()) {
          this.insert = -1;
        }
        return;
      }
    }
    if (this.istack.size() > 0)
    {
      localIStack = (IStack)this.istack.pop();
      if (this.insert >= this.istack.size()) {
        this.insert = -1;
      }
    }
  }
  
  public boolean isPushed(Node paramNode)
  {
    for (int i = this.istack.size() - 1; i >= 0; i--)
    {
      IStack localIStack = (IStack)this.istack.elementAt(i);
      if (localIStack.tag == paramNode.tag) {
        return true;
      }
    }
    return false;
  }
  
  public int inlineDup(Node paramNode)
  {
    int i = this.istack.size() - this.istackbase;
    if (i > 0)
    {
      this.insert = this.istackbase;
      this.inode = paramNode;
    }
    return i;
  }
  
  public Node insertedToken()
  {
    if (this.insert == -1)
    {
      localNode = this.inode;
      this.inode = null;
      return localNode;
    }
    if (this.inode == null)
    {
      this.lines = this.field_1662.getCurline();
      this.columns = this.field_1662.getCurcol();
    }
    Node localNode = newNode((short)5, this.lexbuf, this.txtstart, this.txtend);
    localNode.implicit = true;
    IStack localIStack = (IStack)this.istack.elementAt(this.insert);
    localNode.element = localIStack.element;
    localNode.tag = localIStack.tag;
    if (localIStack.attributes != null) {
      localNode.attributes = cloneAttributes(localIStack.attributes);
    }
    int i = this.insert;
    i++;
    if (i < this.istack.size()) {
      this.insert = i;
    } else {
      this.insert = -1;
    }
    return localNode;
  }
  
  public boolean canPrune(Node paramNode)
  {
    if (paramNode.type == 4) {
      return true;
    }
    if (paramNode.content != null) {
      return false;
    }
    if ((paramNode.tag == this.configuration.field_1881.tagA) && (paramNode.attributes != null)) {
      return false;
    }
    if ((paramNode.tag == this.configuration.field_1881.tagP) && (!this.configuration.dropEmptyParas)) {
      return false;
    }
    if (paramNode.tag == null) {
      return false;
    }
    if (TidyUtils.toBoolean(paramNode.tag.model & 0x200)) {
      return false;
    }
    if (TidyUtils.toBoolean(paramNode.tag.model & 0x1)) {
      return false;
    }
    if (paramNode.tag == this.configuration.field_1881.tagApplet) {
      return false;
    }
    if (paramNode.tag == this.configuration.field_1881.tagObject) {
      return false;
    }
    if ((paramNode.tag == this.configuration.field_1881.tagScript) && (paramNode.getAttrByName("src") != null)) {
      return false;
    }
    if (paramNode.tag == this.configuration.field_1881.tagTitle) {
      return false;
    }
    if (paramNode.tag == this.configuration.field_1881.tagIframe) {
      return false;
    }
    return (paramNode.getAttrByName("id") == null) && (paramNode.getAttrByName("name") == null);
  }
  
  public void fixId(Node paramNode)
  {
    AttVal localAttVal1 = paramNode.getAttrByName("name");
    AttVal localAttVal2 = paramNode.getAttrByName("id");
    if (localAttVal1 != null) {
      if (localAttVal2 != null)
      {
        if ((localAttVal2.value != null) && (!localAttVal2.value.equals(localAttVal1.value))) {
          this.report.attrError(this, paramNode, localAttVal1, (short)60);
        }
      }
      else if (this.configuration.xmlOut) {
        paramNode.addAttribute("id", localAttVal1.value);
      }
    }
  }
  
  public void deferDup()
  {
    this.insert = -1;
    this.inode = null;
  }
  
  void constrainVersion(int paramInt)
  {
    this.versions = ((short)(this.versions & (paramInt | 0x1C0)));
  }
  
  protected boolean preContent(Node paramNode)
  {
    if (paramNode.tag == this.configuration.field_1881.tagP) {
      return true;
    }
    return (paramNode.tag != null) && (paramNode.tag != this.configuration.field_1881.tagP) && (TidyUtils.toBoolean(paramNode.tag.model & 0x100010));
  }
  
  private static class W3CVersionInfo
  {
    String name;
    String voyagerName;
    String profile;
    short code;
    
    public W3CVersionInfo(String paramString1, String paramString2, String paramString3, short paramShort)
    {
      this.name = paramString1;
      this.voyagerName = paramString2;
      this.profile = paramString3;
      this.code = paramShort;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.Lexer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */