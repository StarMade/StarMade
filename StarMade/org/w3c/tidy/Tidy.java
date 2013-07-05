package org.w3c.tidy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.w3c.dom.Document;

public class Tidy
  implements Serializable
{
  static final long serialVersionUID = -2794371560623987718L;
  private static final Map CMDLINE_ALIAS = new HashMap();
  private PrintWriter errout;
  private PrintWriter stderr;
  private Configuration configuration = new Configuration(this.report);
  private String inputStreamName = "InputStream";
  private int parseErrors;
  private int parseWarnings;
  private Report report = new Report();

  public Tidy()
  {
    TagTable localTagTable = new TagTable();
    localTagTable.setConfiguration(this.configuration);
    this.configuration.tt = localTagTable;
    this.configuration.errfile = null;
    this.stderr = new PrintWriter(System.err, true);
    this.errout = this.stderr;
  }

  public Configuration getConfiguration()
  {
    return this.configuration;
  }

  public PrintWriter getStderr()
  {
    return this.stderr;
  }

  public int getParseErrors()
  {
    return this.parseErrors;
  }

  public int getParseWarnings()
  {
    return this.parseWarnings;
  }

  public void setInputStreamName(String paramString)
  {
    if (paramString != null)
      this.inputStreamName = paramString;
  }

  public String getInputStreamName()
  {
    return this.inputStreamName;
  }

  public PrintWriter getErrout()
  {
    return this.errout;
  }

  public void setErrout(PrintWriter paramPrintWriter)
  {
    this.errout = paramPrintWriter;
  }

  public void setConfigurationFromFile(String paramString)
  {
    this.configuration.parseFile(paramString);
  }

  public void setConfigurationFromProps(Properties paramProperties)
  {
    this.configuration.addProps(paramProperties);
  }

  public static Document createEmptyDocument()
  {
    Node localNode1 = new Node((short)0, new byte[0], 0, 0);
    Node localNode2 = new Node((short)5, new byte[0], 0, 0, "html", new TagTable());
    if ((localNode1 != null) && (localNode2 != null))
    {
      localNode1.insertNodeAtStart(localNode2);
      return (Document)localNode1.getAdapter();
    }
    return null;
  }

  public Node parse(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    StreamIn localStreamIn = StreamInFactory.getStreamIn(this.configuration, paramInputStream);
    Out localOut = null;
    if (paramOutputStream != null)
      localOut = OutFactory.getOut(this.configuration, paramOutputStream);
    return parse(localStreamIn, localOut);
  }

  public Node parse(Reader paramReader, OutputStream paramOutputStream)
  {
    StreamIn localStreamIn = StreamInFactory.getStreamIn(this.configuration, paramReader);
    Out localOut = null;
    if (paramOutputStream != null)
      localOut = OutFactory.getOut(this.configuration, paramOutputStream);
    return parse(localStreamIn, localOut);
  }

  public Node parse(Reader paramReader, Writer paramWriter)
  {
    StreamIn localStreamIn = StreamInFactory.getStreamIn(this.configuration, paramReader);
    Out localOut = null;
    if (paramWriter != null)
      localOut = OutFactory.getOut(this.configuration, paramWriter);
    return parse(localStreamIn, localOut);
  }

  public Node parse(InputStream paramInputStream, Writer paramWriter)
  {
    StreamIn localStreamIn = StreamInFactory.getStreamIn(this.configuration, paramInputStream);
    Out localOut = null;
    if (paramWriter != null)
      localOut = OutFactory.getOut(this.configuration, paramWriter);
    return parse(localStreamIn, localOut);
  }

  public Document parseDOM(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    Node localNode = parse(paramInputStream, paramOutputStream);
    if (localNode != null)
      return (Document)localNode.getAdapter();
    return null;
  }

  public Document parseDOM(Reader paramReader, Writer paramWriter)
  {
    Node localNode = parse(paramReader, paramWriter);
    if (localNode != null)
      return (Document)localNode.getAdapter();
    return null;
  }

  public void pprint(Document paramDocument, OutputStream paramOutputStream)
  {
    if (!(paramDocument instanceof DOMDocumentImpl))
      return;
    pprint(((DOMDocumentImpl)paramDocument).adaptee, paramOutputStream);
  }

  public void pprint(org.w3c.dom.Node paramNode, OutputStream paramOutputStream)
  {
    if (!(paramNode instanceof DOMNodeImpl))
      return;
    pprint(((DOMNodeImpl)paramNode).adaptee, paramOutputStream);
  }

  private Node parse(StreamIn paramStreamIn, Out paramOut)
  {
    Node localNode1 = null;
    if (this.errout == null)
      return null;
    this.configuration.adjust();
    this.parseErrors = 0;
    this.parseWarnings = 0;
    Lexer localLexer = new Lexer(paramStreamIn, this.configuration, this.report);
    localLexer.errout = this.errout;
    paramStreamIn.setLexer(localLexer);
    this.report.setFilename(this.inputStreamName);
    Object localObject;
    Node localNode2;
    if (this.configuration.xmlTags)
    {
      localNode1 = ParserImpl.parseXMLDocument(localLexer);
      if (!localNode1.checkNodeIntegrity())
      {
        if (!this.configuration.quiet)
          this.report.badTree(this.errout);
        return null;
      }
    }
    else
    {
      localLexer.warnings = 0;
      localNode1 = ParserImpl.parseDocument(localLexer);
      if (!localNode1.checkNodeIntegrity())
      {
        if (!this.configuration.quiet)
          this.report.badTree(this.errout);
        return null;
      }
      localObject = new Clean(this.configuration.tt);
      ((Clean)localObject).nestedEmphasis(localNode1);
      ((Clean)localObject).list2BQ(localNode1);
      ((Clean)localObject).bQ2Div(localNode1);
      if (this.configuration.logicalEmphasis)
        ((Clean)localObject).emFromI(localNode1);
      if ((this.configuration.word2000) && (((Clean)localObject).isWord2000(localNode1)))
      {
        ((Clean)localObject).dropSections(localLexer, localNode1);
        ((Clean)localObject).cleanWord2000(localLexer, localNode1);
      }
      if ((this.configuration.makeClean) || (this.configuration.dropFontTags))
        ((Clean)localObject).cleanTree(localLexer, localNode1);
      if (!localNode1.checkNodeIntegrity())
      {
        this.report.badTree(this.errout);
        return null;
      }
      localNode2 = localNode1.findDocType();
      if (localNode2 != null)
        localNode2 = localNode2.cloneNode(false);
      if (localNode1.content != null)
      {
        if (this.configuration.xHTML)
          localLexer.setXHTMLDocType(localNode1);
        else
          localLexer.fixDocType(localNode1);
        if (this.configuration.tidyMark)
          localLexer.addGenerator(localNode1);
      }
      if ((this.configuration.xmlOut) && (this.configuration.xmlPi))
        localLexer.fixXmlDecl(localNode1);
      if ((!this.configuration.quiet) && (localNode1.content != null))
        this.report.reportVersion(this.errout, localLexer, this.inputStreamName, localNode2);
    }
    if (!this.configuration.quiet)
    {
      this.parseWarnings = localLexer.warnings;
      this.parseErrors = localLexer.errors;
      this.report.reportNumWarnings(this.errout, localLexer);
    }
    if ((!this.configuration.quiet) && (localLexer.errors > 0) && (!this.configuration.forceOutput))
      this.report.needsAuthorIntervention(this.errout);
    if ((!this.configuration.onlyErrors) && ((localLexer.errors == 0) || (this.configuration.forceOutput)))
    {
      PPrint localPPrint;
      if (this.configuration.burstSlides)
      {
        localObject = null;
        localNode2 = localNode1.findDocType();
        if (localNode2 != null)
          Node.discardElement(localNode2);
        Lexer tmp570_569 = localLexer;
        tmp570_569.versions = ((short)(tmp570_569.versions | 0x8));
        if (this.configuration.xHTML)
          localLexer.setXHTMLDocType(localNode1);
        else
          localLexer.fixDocType(localNode1);
        localObject = localNode1.findBody(this.configuration.tt);
        if (localObject != null)
        {
          localPPrint = new PPrint(this.configuration);
          if (!this.configuration.quiet)
            this.report.reportNumberOfSlides(this.errout, localPPrint.countSlides((Node)localObject));
          localPPrint.createSlides(localLexer, localNode1);
        }
        else if (!this.configuration.quiet)
        {
          this.report.missingBody(this.errout);
        }
      }
      else if (paramOut != null)
      {
        localPPrint = new PPrint(this.configuration);
        if (localNode1.findDocType() == null)
          this.configuration.numEntities = true;
        if (this.configuration.bodyOnly)
          localPPrint.printBody(paramOut, localLexer, localNode1, this.configuration.xmlOut);
        else if ((this.configuration.xmlOut) && (!this.configuration.xHTML))
          localPPrint.printXMLTree(paramOut, (short)0, 0, localLexer, localNode1);
        else
          localPPrint.printTree(paramOut, (short)0, 0, localLexer, localNode1);
        localPPrint.flushLine(paramOut, 0);
        paramOut.flush();
      }
    }
    if (!this.configuration.quiet)
      this.report.errorSummary(localLexer);
    return localNode1;
  }

  private Node parse(InputStream paramInputStream, String paramString, OutputStream paramOutputStream)
    throws FileNotFoundException, IOException
  {
    Out localOut = null;
    int i = 0;
    int j = 0;
    if (paramString != null)
    {
      paramInputStream = new FileInputStream(paramString);
      i = 1;
      this.inputStreamName = paramString;
    }
    else if (paramInputStream == null)
    {
      paramInputStream = System.in;
      this.inputStreamName = "stdin";
    }
    StreamIn localStreamIn = StreamInFactory.getStreamIn(this.configuration, paramInputStream);
    if ((this.configuration.writeback) && (paramString != null))
    {
      paramOutputStream = new FileOutputStream(paramString);
      j = 1;
    }
    if (paramOutputStream != null)
      localOut = OutFactory.getOut(this.configuration, paramOutputStream);
    Node localNode = parse(localStreamIn, localOut);
    if (i != 0)
      try
      {
        paramInputStream.close();
      }
      catch (IOException localIOException1)
      {
      }
    if (j != 0)
      try
      {
        paramOutputStream.close();
      }
      catch (IOException localIOException2)
      {
      }
    return localNode;
  }

  private void pprint(Node paramNode, OutputStream paramOutputStream)
  {
    if (paramOutputStream != null)
    {
      Out localOut = OutFactory.getOut(this.configuration, paramOutputStream);
      Lexer localLexer = new Lexer(null, this.configuration, this.report);
      PPrint localPPrint = new PPrint(this.configuration);
      if (this.configuration.xmlTags)
        localPPrint.printXMLTree(localOut, (short)0, 0, localLexer, paramNode);
      else
        localPPrint.printTree(localOut, (short)0, 0, localLexer, paramNode);
      localPPrint.flushLine(localOut, 0);
      localOut.flush();
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    Tidy localTidy = new Tidy();
    int i = localTidy.mainExec(paramArrayOfString);
    System.exit(i);
  }

  protected int mainExec(String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    int j = 0;
    Properties localProperties = new Properties();
    while (i > 0)
    {
      Object localObject;
      if (paramArrayOfString[j].startsWith("-"))
      {
        for (localObject = paramArrayOfString[j].toLowerCase(); (((String)localObject).length() > 0) && (((String)localObject).charAt(0) == '-'); localObject = ((String)localObject).substring(1));
        if ((((String)localObject).equals("help")) || (((String)localObject).equals("h")) || (((String)localObject).equals("?")))
        {
          this.report.helpText(new PrintWriter(System.out, true));
          return 0;
        }
        if (((String)localObject).equals("help-config"))
        {
          this.configuration.printConfigOptions(new PrintWriter(System.out, true), false);
          return 0;
        }
        if (((String)localObject).equals("show-config"))
        {
          this.configuration.adjust();
          this.configuration.printConfigOptions(this.errout, true);
          return 0;
        }
        if ((((String)localObject).equals("version")) || (((String)localObject).equals("v")))
        {
          this.report.showVersion(this.errout);
          return 0;
        }
        String str2 = null;
        if ((i > 2) && (!paramArrayOfString[(j + 1)].startsWith("-")))
        {
          str2 = paramArrayOfString[(j + 1)];
          i--;
          j++;
        }
        String str3 = (String)CMDLINE_ALIAS.get(localObject);
        if (str3 != null)
          localObject = str3;
        if (Configuration.isKnownOption((String)localObject))
          localProperties.setProperty((String)localObject, str2 == null ? "" : str2);
        else if (((String)localObject).equals("config"))
        {
          if (str2 != null)
            this.configuration.parseFile(str2);
        }
        else if (TidyUtils.isCharEncodingSupported((String)localObject))
          localProperties.setProperty("char-encoding", (String)localObject);
        else
          for (int k = 0; k < ((String)localObject).length(); k++)
            switch (((String)localObject).charAt(k))
            {
            case 'i':
              this.configuration.indentContent = true;
              this.configuration.smartIndent = true;
              break;
            case 'o':
              this.configuration.hideEndTags = true;
              break;
            case 'u':
              this.configuration.upperCaseTags = true;
              break;
            case 'c':
              this.configuration.makeClean = true;
              break;
            case 'b':
              this.configuration.makeBare = true;
              break;
            case 'n':
              this.configuration.numEntities = true;
              break;
            case 'm':
              this.configuration.writeback = true;
              break;
            case 'e':
              this.configuration.onlyErrors = true;
              break;
            case 'q':
              this.configuration.quiet = true;
              break;
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'l':
            case 'p':
            case 'r':
            case 's':
            case 't':
            default:
              this.report.unknownOption(this.errout, ((String)localObject).charAt(k));
            }
        i--;
        j++;
      }
      else
      {
        this.configuration.addProps(localProperties);
        this.configuration.adjust();
        if (this.configuration.errfile != null)
        {
          localObject = "stderr";
          if (!this.configuration.errfile.equals(localObject))
          {
            if (this.errout != this.stderr)
              this.errout.close();
            try
            {
              setErrout(new PrintWriter(new FileWriter(this.configuration.errfile), true));
              localObject = this.configuration.errfile;
            }
            catch (IOException localIOException2)
            {
              localObject = "stderr";
              setErrout(this.stderr);
            }
          }
        }
        String str1;
        if (i > 0)
          str1 = paramArrayOfString[j];
        else
          str1 = "stdin";
        try
        {
          parse(null, str1, System.out);
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          this.report.unknownFile(this.errout, str1);
        }
        catch (IOException localIOException1)
        {
          this.report.unknownFile(this.errout, str1);
        }
        i--;
        j++;
        if (i <= 0)
          break;
      }
    }
    if ((this.parseErrors + this.parseWarnings > 0) && (!this.configuration.quiet))
      this.report.generalInfo(this.errout);
    if (this.errout != this.stderr)
      this.errout.close();
    if (this.parseErrors > 0)
      return 2;
    if (this.parseWarnings > 0)
      return 1;
    return 0;
  }

  public void setMessageListener(TidyMessageListener paramTidyMessageListener)
  {
    this.report.addMessageListener(paramTidyMessageListener);
  }

  public void setSpaces(int paramInt)
  {
    this.configuration.spaces = paramInt;
  }

  public int getSpaces()
  {
    return this.configuration.spaces;
  }

  public void setWraplen(int paramInt)
  {
    this.configuration.wraplen = paramInt;
  }

  public int getWraplen()
  {
    return this.configuration.wraplen;
  }

  public void setTabsize(int paramInt)
  {
    this.configuration.tabsize = paramInt;
  }

  public int getTabsize()
  {
    return this.configuration.tabsize;
  }

  public void setErrfile(String paramString)
  {
    this.configuration.errfile = paramString;
  }

  public String getErrfile()
  {
    return this.configuration.errfile;
  }

  public void setWriteback(boolean paramBoolean)
  {
    this.configuration.writeback = paramBoolean;
  }

  public boolean getWriteback()
  {
    return this.configuration.writeback;
  }

  public void setOnlyErrors(boolean paramBoolean)
  {
    this.configuration.onlyErrors = paramBoolean;
  }

  public boolean getOnlyErrors()
  {
    return this.configuration.onlyErrors;
  }

  public void setShowWarnings(boolean paramBoolean)
  {
    this.configuration.showWarnings = paramBoolean;
  }

  public boolean getShowWarnings()
  {
    return this.configuration.showWarnings;
  }

  public void setQuiet(boolean paramBoolean)
  {
    this.configuration.quiet = paramBoolean;
  }

  public boolean getQuiet()
  {
    return this.configuration.quiet;
  }

  public void setIndentContent(boolean paramBoolean)
  {
    this.configuration.indentContent = paramBoolean;
  }

  public boolean getIndentContent()
  {
    return this.configuration.indentContent;
  }

  public void setSmartIndent(boolean paramBoolean)
  {
    this.configuration.smartIndent = paramBoolean;
  }

  public boolean getSmartIndent()
  {
    return this.configuration.smartIndent;
  }

  public void setHideEndTags(boolean paramBoolean)
  {
    this.configuration.hideEndTags = paramBoolean;
  }

  public boolean getHideEndTags()
  {
    return this.configuration.hideEndTags;
  }

  public void setXmlTags(boolean paramBoolean)
  {
    this.configuration.xmlTags = paramBoolean;
  }

  public boolean getXmlTags()
  {
    return this.configuration.xmlTags;
  }

  public void setXmlOut(boolean paramBoolean)
  {
    this.configuration.xmlOut = paramBoolean;
  }

  public boolean getXmlOut()
  {
    return this.configuration.xmlOut;
  }

  public void setXHTML(boolean paramBoolean)
  {
    this.configuration.xHTML = paramBoolean;
  }

  public boolean getXHTML()
  {
    return this.configuration.xHTML;
  }

  public void setUpperCaseTags(boolean paramBoolean)
  {
    this.configuration.upperCaseTags = paramBoolean;
  }

  public boolean getUpperCaseTags()
  {
    return this.configuration.upperCaseTags;
  }

  public void setUpperCaseAttrs(boolean paramBoolean)
  {
    this.configuration.upperCaseAttrs = paramBoolean;
  }

  public boolean getUpperCaseAttrs()
  {
    return this.configuration.upperCaseAttrs;
  }

  public void setMakeClean(boolean paramBoolean)
  {
    this.configuration.makeClean = paramBoolean;
  }

  public boolean getMakeClean()
  {
    return this.configuration.makeClean;
  }

  public void setMakeBare(boolean paramBoolean)
  {
    this.configuration.makeBare = paramBoolean;
  }

  public boolean getMakeBare()
  {
    return this.configuration.makeBare;
  }

  public void setBreakBeforeBR(boolean paramBoolean)
  {
    this.configuration.breakBeforeBR = paramBoolean;
  }

  public boolean getBreakBeforeBR()
  {
    return this.configuration.breakBeforeBR;
  }

  public void setBurstSlides(boolean paramBoolean)
  {
    this.configuration.burstSlides = paramBoolean;
  }

  public boolean getBurstSlides()
  {
    return this.configuration.burstSlides;
  }

  public void setNumEntities(boolean paramBoolean)
  {
    this.configuration.numEntities = paramBoolean;
  }

  public boolean getNumEntities()
  {
    return this.configuration.numEntities;
  }

  public void setQuoteMarks(boolean paramBoolean)
  {
    this.configuration.quoteMarks = paramBoolean;
  }

  public boolean getQuoteMarks()
  {
    return this.configuration.quoteMarks;
  }

  public void setQuoteNbsp(boolean paramBoolean)
  {
    this.configuration.quoteNbsp = paramBoolean;
  }

  public boolean getQuoteNbsp()
  {
    return this.configuration.quoteNbsp;
  }

  public void setQuoteAmpersand(boolean paramBoolean)
  {
    this.configuration.quoteAmpersand = paramBoolean;
  }

  public boolean getQuoteAmpersand()
  {
    return this.configuration.quoteAmpersand;
  }

  public void setWrapAttVals(boolean paramBoolean)
  {
    this.configuration.wrapAttVals = paramBoolean;
  }

  public boolean getWrapAttVals()
  {
    return this.configuration.wrapAttVals;
  }

  public void setWrapScriptlets(boolean paramBoolean)
  {
    this.configuration.wrapScriptlets = paramBoolean;
  }

  public boolean getWrapScriptlets()
  {
    return this.configuration.wrapScriptlets;
  }

  public void setWrapSection(boolean paramBoolean)
  {
    this.configuration.wrapSection = paramBoolean;
  }

  public boolean getWrapSection()
  {
    return this.configuration.wrapSection;
  }

  public void setAltText(String paramString)
  {
    this.configuration.altText = paramString;
  }

  public String getAltText()
  {
    return this.configuration.altText;
  }

  public void setXmlPi(boolean paramBoolean)
  {
    this.configuration.xmlPi = paramBoolean;
  }

  public boolean getXmlPi()
  {
    return this.configuration.xmlPi;
  }

  public void setDropFontTags(boolean paramBoolean)
  {
    this.configuration.dropFontTags = paramBoolean;
  }

  public boolean getDropFontTags()
  {
    return this.configuration.dropFontTags;
  }

  public void setDropProprietaryAttributes(boolean paramBoolean)
  {
    this.configuration.dropProprietaryAttributes = paramBoolean;
  }

  public boolean getDropProprietaryAttributes()
  {
    return this.configuration.dropProprietaryAttributes;
  }

  public void setDropEmptyParas(boolean paramBoolean)
  {
    this.configuration.dropEmptyParas = paramBoolean;
  }

  public boolean getDropEmptyParas()
  {
    return this.configuration.dropEmptyParas;
  }

  public void setFixComments(boolean paramBoolean)
  {
    this.configuration.fixComments = paramBoolean;
  }

  public boolean getFixComments()
  {
    return this.configuration.fixComments;
  }

  public void setWrapAsp(boolean paramBoolean)
  {
    this.configuration.wrapAsp = paramBoolean;
  }

  public boolean getWrapAsp()
  {
    return this.configuration.wrapAsp;
  }

  public void setWrapJste(boolean paramBoolean)
  {
    this.configuration.wrapJste = paramBoolean;
  }

  public boolean getWrapJste()
  {
    return this.configuration.wrapJste;
  }

  public void setWrapPhp(boolean paramBoolean)
  {
    this.configuration.wrapPhp = paramBoolean;
  }

  public boolean getWrapPhp()
  {
    return this.configuration.wrapPhp;
  }

  public void setFixBackslash(boolean paramBoolean)
  {
    this.configuration.fixBackslash = paramBoolean;
  }

  public boolean getFixBackslash()
  {
    return this.configuration.fixBackslash;
  }

  public void setIndentAttributes(boolean paramBoolean)
  {
    this.configuration.indentAttributes = paramBoolean;
  }

  public boolean getIndentAttributes()
  {
    return this.configuration.indentAttributes;
  }

  public void setDocType(String paramString)
  {
    if (paramString != null)
      this.configuration.docTypeStr = ((String)ParsePropertyImpl.DOCTYPE.parse(paramString, "doctype", this.configuration));
  }

  public String getDocType()
  {
    String str = null;
    switch (this.configuration.docTypeMode)
    {
    case 0:
      str = "omit";
      break;
    case 1:
      str = "auto";
      break;
    case 2:
      str = "strict";
      break;
    case 3:
      str = "loose";
      break;
    case 4:
      str = this.configuration.docTypeStr;
    }
    return str;
  }

  public void setLogicalEmphasis(boolean paramBoolean)
  {
    this.configuration.logicalEmphasis = paramBoolean;
  }

  public boolean getLogicalEmphasis()
  {
    return this.configuration.logicalEmphasis;
  }

  public void setXmlPIs(boolean paramBoolean)
  {
    this.configuration.xmlPIs = paramBoolean;
  }

  public boolean getXmlPIs()
  {
    return this.configuration.xmlPIs;
  }

  public void setEncloseText(boolean paramBoolean)
  {
    this.configuration.encloseBodyText = paramBoolean;
  }

  public boolean getEncloseText()
  {
    return this.configuration.encloseBodyText;
  }

  public void setEncloseBlockText(boolean paramBoolean)
  {
    this.configuration.encloseBlockText = paramBoolean;
  }

  public boolean getEncloseBlockText()
  {
    return this.configuration.encloseBlockText;
  }

  public void setWord2000(boolean paramBoolean)
  {
    this.configuration.word2000 = paramBoolean;
  }

  public boolean getWord2000()
  {
    return this.configuration.word2000;
  }

  public void setTidyMark(boolean paramBoolean)
  {
    this.configuration.tidyMark = paramBoolean;
  }

  public boolean getTidyMark()
  {
    return this.configuration.tidyMark;
  }

  public void setXmlSpace(boolean paramBoolean)
  {
    this.configuration.xmlSpace = paramBoolean;
  }

  public boolean getXmlSpace()
  {
    return this.configuration.xmlSpace;
  }

  public void setEmacs(boolean paramBoolean)
  {
    this.configuration.emacs = paramBoolean;
  }

  public boolean getEmacs()
  {
    return this.configuration.emacs;
  }

  public void setLiteralAttribs(boolean paramBoolean)
  {
    this.configuration.literalAttribs = paramBoolean;
  }

  public boolean getLiteralAttribs()
  {
    return this.configuration.literalAttribs;
  }

  public void setPrintBodyOnly(boolean paramBoolean)
  {
    this.configuration.bodyOnly = paramBoolean;
  }

  public boolean getPrintBodyOnly()
  {
    return this.configuration.bodyOnly;
  }

  public void setFixUri(boolean paramBoolean)
  {
    this.configuration.fixUri = paramBoolean;
  }

  public boolean getFixUri()
  {
    return this.configuration.fixUri;
  }

  public void setLowerLiterals(boolean paramBoolean)
  {
    this.configuration.lowerLiterals = paramBoolean;
  }

  public boolean getLowerLiterals()
  {
    return this.configuration.lowerLiterals;
  }

  public void setHideComments(boolean paramBoolean)
  {
    this.configuration.hideComments = paramBoolean;
  }

  public boolean getHideComments()
  {
    return this.configuration.hideComments;
  }

  public void setIndentCdata(boolean paramBoolean)
  {
    this.configuration.indentCdata = paramBoolean;
  }

  public boolean getIndentCdata()
  {
    return this.configuration.indentCdata;
  }

  public void setForceOutput(boolean paramBoolean)
  {
    this.configuration.forceOutput = paramBoolean;
  }

  public boolean getForceOutput()
  {
    return this.configuration.forceOutput;
  }

  public void setShowErrors(int paramInt)
  {
    this.configuration.showErrors = paramInt;
  }

  public int getShowErrors()
  {
    return this.configuration.showErrors;
  }

  public void setAsciiChars(boolean paramBoolean)
  {
    this.configuration.asciiChars = paramBoolean;
  }

  public boolean getAsciiChars()
  {
    return this.configuration.asciiChars;
  }

  public void setJoinClasses(boolean paramBoolean)
  {
    this.configuration.joinClasses = paramBoolean;
  }

  public boolean getJoinClasses()
  {
    return this.configuration.joinClasses;
  }

  public void setJoinStyles(boolean paramBoolean)
  {
    this.configuration.joinStyles = paramBoolean;
  }

  public boolean getJoinStyles()
  {
    return this.configuration.joinStyles;
  }

  public void setTrimEmptyElements(boolean paramBoolean)
  {
    this.configuration.trimEmpty = paramBoolean;
  }

  public boolean getTrimEmptyElements()
  {
    return this.configuration.trimEmpty;
  }

  public void setReplaceColor(boolean paramBoolean)
  {
    this.configuration.replaceColor = paramBoolean;
  }

  public boolean getReplaceColor()
  {
    return this.configuration.replaceColor;
  }

  public void setEscapeCdata(boolean paramBoolean)
  {
    this.configuration.escapeCdata = paramBoolean;
  }

  public boolean getEscapeCdata()
  {
    return this.configuration.escapeCdata;
  }

  public void setRepeatedAttributes(int paramInt)
  {
    this.configuration.duplicateAttrs = paramInt;
  }

  public int getRepeatedAttributes()
  {
    return this.configuration.duplicateAttrs;
  }

  public void setKeepFileTimes(boolean paramBoolean)
  {
    this.configuration.keepFileTimes = paramBoolean;
  }

  public boolean getKeepFileTimes()
  {
    return this.configuration.keepFileTimes;
  }

  public void setRawOut(boolean paramBoolean)
  {
    this.configuration.rawOut = paramBoolean;
  }

  public boolean getRawOut()
  {
    return this.configuration.rawOut;
  }

  public void setInputEncoding(String paramString)
  {
    this.configuration.setInCharEncodingName(paramString);
  }

  public String getInputEncoding()
  {
    return this.configuration.getInCharEncodingName();
  }

  public void setOutputEncoding(String paramString)
  {
    this.configuration.setOutCharEncodingName(paramString);
  }

  public String getOutputEncoding()
  {
    return this.configuration.getOutCharEncodingName();
  }

  static
  {
    CMDLINE_ALIAS.put("xml", "input-xml");
    CMDLINE_ALIAS.put("xml", "output-xhtml");
    CMDLINE_ALIAS.put("asxml", "output-xhtml");
    CMDLINE_ALIAS.put("ashtml", "output-html");
    CMDLINE_ALIAS.put("omit", "hide-endtags");
    CMDLINE_ALIAS.put("upper", "uppercase-tags");
    CMDLINE_ALIAS.put("raw", "output-raw");
    CMDLINE_ALIAS.put("numeric", "numeric-entities");
    CMDLINE_ALIAS.put("change", "write-back");
    CMDLINE_ALIAS.put("update", "write-back");
    CMDLINE_ALIAS.put("modify", "write-back");
    CMDLINE_ALIAS.put("errors", "only-errors");
    CMDLINE_ALIAS.put("slides", "split");
    CMDLINE_ALIAS.put("lang", "language");
    CMDLINE_ALIAS.put("w", "wrap");
    CMDLINE_ALIAS.put("file", "error-file");
    CMDLINE_ALIAS.put("f", "error-file");
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.Tidy
 * JD-Core Version:    0.6.2
 */