package org.w3c.tidy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.NumberFormat;

public class PPrint
{
  private static final short NORMAL = 0;
  private static final short PREFORMATTED = 1;
  private static final short COMMENT = 2;
  private static final short ATTRIBVALUE = 4;
  private static final short NOWRAP = 8;
  private static final short CDATA = 16;
  private static final String CDATA_START = "<![CDATA[";
  private static final String CDATA_END = "]]>";
  private static final String JS_COMMENT_START = "//";
  private static final String JS_COMMENT_END = "";
  private static final String VB_COMMENT_START = "'";
  private static final String VB_COMMENT_END = "";
  private static final String CSS_COMMENT_START = "/*";
  private static final String CSS_COMMENT_END = "*/";
  private static final String DEFAULT_COMMENT_START = "";
  private static final String DEFAULT_COMMENT_END = "";
  private int[] linebuf;
  private int lbufsize;
  private int linelen;
  private int wraphere;
  private boolean inAttVal;
  private boolean inString;
  private int slide;
  private int count;
  private Node slidecontent;
  private Configuration configuration;

  public PPrint(Configuration paramConfiguration)
  {
    this.configuration = paramConfiguration;
  }

  int cWrapLen(int paramInt)
  {
    if ("zh".equals(this.configuration.language))
      return paramInt + (this.configuration.wraplen - paramInt) / 2;
    if ("ja".equals(this.configuration.language))
      return paramInt + (this.configuration.wraplen - paramInt) * 7 / 10;
    return this.configuration.wraplen;
  }

  public static int getUTF8(byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt)
  {
    int[] arrayOfInt1 = new int[1];
    int[] arrayOfInt2 = { 0 };
    byte[] arrayOfByte = paramArrayOfByte;
    boolean bool = EncodingUtils.decodeUTF8BytesToChar(arrayOfInt1, TidyUtils.toUnsigned(paramArrayOfByte[paramInt]), arrayOfByte, null, arrayOfInt2, paramInt + 1);
    if (bool)
      arrayOfInt1[0] = 65533;
    paramArrayOfInt[0] = arrayOfInt1[0];
    return arrayOfInt2[0] - 1;
  }

  public static int putUTF8(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = { 0 };
    boolean bool = EncodingUtils.encodeCharToUTF8Bytes(paramInt2, paramArrayOfByte, null, arrayOfInt);
    if (bool)
    {
      paramArrayOfByte[0] = -17;
      paramArrayOfByte[1] = -65;
      paramArrayOfByte[2] = -67;
      arrayOfInt[0] = 3;
    }
    paramInt1 += arrayOfInt[0];
    return paramInt1;
  }

  private void addC(int paramInt1, int paramInt2)
  {
    if (paramInt2 + 1 >= this.lbufsize)
    {
      while (paramInt2 + 1 >= this.lbufsize)
        if (this.lbufsize == 0)
          this.lbufsize = 256;
        else
          this.lbufsize *= 2;
      int[] arrayOfInt = new int[this.lbufsize];
      if (this.linebuf != null)
        System.arraycopy(this.linebuf, 0, arrayOfInt, 0, paramInt2);
      this.linebuf = arrayOfInt;
    }
    this.linebuf[paramInt2] = paramInt1;
  }

  private int addAsciiString(String paramString, int paramInt)
  {
    int i = paramString.length();
    if (paramInt + i >= this.lbufsize)
    {
      while (paramInt + i >= this.lbufsize)
        if (this.lbufsize == 0)
          this.lbufsize = 256;
        else
          this.lbufsize *= 2;
      int[] arrayOfInt = new int[this.lbufsize];
      if (this.linebuf != null)
        System.arraycopy(this.linebuf, 0, arrayOfInt, 0, paramInt);
      this.linebuf = arrayOfInt;
    }
    for (int j = 0; j < i; j++)
      this.linebuf[(paramInt + j)] = paramString.charAt(j);
    return paramInt + i;
  }

  private void wrapLine(Out paramOut, int paramInt)
  {
    if (this.wraphere == 0)
      return;
    for (int i = 0; i < paramInt; i++)
      paramOut.outc(32);
    for (i = 0; i < this.wraphere; i++)
      paramOut.outc(this.linebuf[i]);
    if (this.inString)
    {
      paramOut.outc(32);
      paramOut.outc(92);
    }
    paramOut.newline();
    if (this.linelen > this.wraphere)
    {
      int j = 0;
      if (this.linebuf[this.wraphere] == 32)
        this.wraphere += 1;
      int k = this.wraphere;
      addC(0, this.linelen);
      while (true)
      {
        this.linebuf[j] = this.linebuf[k];
        if (this.linebuf[k] == 0)
          break;
        j++;
        k++;
      }
      this.linelen -= this.wraphere;
    }
    else
    {
      this.linelen = 0;
    }
    this.wraphere = 0;
  }

  private void wrapAttrVal(Out paramOut, int paramInt, boolean paramBoolean)
  {
    for (int i = 0; i < paramInt; i++)
      paramOut.outc(32);
    for (i = 0; i < this.wraphere; i++)
      paramOut.outc(this.linebuf[i]);
    paramOut.outc(32);
    if (paramBoolean)
      paramOut.outc(92);
    paramOut.newline();
    if (this.linelen > this.wraphere)
    {
      int j = 0;
      if (this.linebuf[this.wraphere] == 32)
        this.wraphere += 1;
      int k = this.wraphere;
      addC(0, this.linelen);
      while (true)
      {
        this.linebuf[j] = this.linebuf[k];
        if (this.linebuf[k] == 0)
          break;
        j++;
        k++;
      }
      this.linelen -= this.wraphere;
    }
    else
    {
      this.linelen = 0;
    }
    this.wraphere = 0;
  }

  public void flushLine(Out paramOut, int paramInt)
  {
    if (this.linelen > 0)
    {
      if (paramInt + this.linelen >= this.configuration.wraplen)
        wrapLine(paramOut, paramInt);
      if ((!this.inAttVal) || (this.configuration.indentAttributes))
        for (i = 0; i < paramInt; i++)
          paramOut.outc(32);
      for (int i = 0; i < this.linelen; i++)
        paramOut.outc(this.linebuf[i]);
    }
    paramOut.newline();
    this.linelen = 0;
    this.wraphere = 0;
    this.inAttVal = false;
  }

  public void condFlushLine(Out paramOut, int paramInt)
  {
    if (this.linelen > 0)
    {
      if (paramInt + this.linelen >= this.configuration.wraplen)
        wrapLine(paramOut, paramInt);
      if ((!this.inAttVal) || (this.configuration.indentAttributes))
        for (i = 0; i < paramInt; i++)
          paramOut.outc(32);
      for (int i = 0; i < this.linelen; i++)
        paramOut.outc(this.linebuf[i]);
      paramOut.newline();
      this.linelen = 0;
      this.wraphere = 0;
      this.inAttVal = false;
    }
  }

  private void printChar(int paramInt, short paramShort)
  {
    int i = 0;
    if ((paramInt == 32) && (!TidyUtils.toBoolean(paramShort & 0x17)))
    {
      if (TidyUtils.toBoolean(paramShort & 0x8))
      {
        if ((this.configuration.numEntities) || (this.configuration.xmlTags))
        {
          addC(38, this.linelen++);
          addC(35, this.linelen++);
          addC(49, this.linelen++);
          addC(54, this.linelen++);
          addC(48, this.linelen++);
          addC(59, this.linelen++);
        }
        else
        {
          addC(38, this.linelen++);
          addC(110, this.linelen++);
          addC(98, this.linelen++);
          addC(115, this.linelen++);
          addC(112, this.linelen++);
          addC(59, this.linelen++);
        }
        return;
      }
      this.wraphere = this.linelen;
    }
    if (TidyUtils.toBoolean(paramShort & 0x12))
    {
      addC(paramInt, this.linelen++);
      return;
    }
    if (!TidyUtils.toBoolean(paramShort & 0x10))
    {
      if (paramInt == 60)
      {
        addC(38, this.linelen++);
        addC(108, this.linelen++);
        addC(116, this.linelen++);
        addC(59, this.linelen++);
        return;
      }
      if (paramInt == 62)
      {
        addC(38, this.linelen++);
        addC(103, this.linelen++);
        addC(116, this.linelen++);
        addC(59, this.linelen++);
        return;
      }
      if ((paramInt == 38) && (this.configuration.quoteAmpersand))
      {
        addC(38, this.linelen++);
        addC(97, this.linelen++);
        addC(109, this.linelen++);
        addC(112, this.linelen++);
        addC(59, this.linelen++);
        return;
      }
      if ((paramInt == 34) && (this.configuration.quoteMarks))
      {
        addC(38, this.linelen++);
        addC(113, this.linelen++);
        addC(117, this.linelen++);
        addC(111, this.linelen++);
        addC(116, this.linelen++);
        addC(59, this.linelen++);
        return;
      }
      if ((paramInt == 39) && (this.configuration.quoteMarks))
      {
        addC(38, this.linelen++);
        addC(35, this.linelen++);
        addC(51, this.linelen++);
        addC(57, this.linelen++);
        addC(59, this.linelen++);
        return;
      }
      if ((paramInt == 160) && (!this.configuration.rawOut))
      {
        if (this.configuration.makeBare)
        {
          addC(32, this.linelen++);
        }
        else if (this.configuration.quoteNbsp)
        {
          addC(38, this.linelen++);
          if ((this.configuration.numEntities) || (this.configuration.xmlTags))
          {
            addC(35, this.linelen++);
            addC(49, this.linelen++);
            addC(54, this.linelen++);
            addC(48, this.linelen++);
          }
          else
          {
            addC(110, this.linelen++);
            addC(98, this.linelen++);
            addC(115, this.linelen++);
            addC(112, this.linelen++);
          }
          addC(59, this.linelen++);
        }
        else
        {
          addC(paramInt, this.linelen++);
        }
        return;
      }
    }
    if ("UTF8".equals(this.configuration.getOutCharEncodingName()))
      if ((paramInt >= 8192) && (!TidyUtils.toBoolean(paramShort & 0x1)))
      {
        if (((paramInt >= 8192) && (paramInt <= 8198)) || ((paramInt >= 8200) && (paramInt <= 8208)) || ((paramInt >= 8209) && (paramInt <= 8262)) || ((paramInt >= 8317) && (paramInt <= 8318)) || ((paramInt >= 8333) && (paramInt <= 8334)) || ((paramInt >= 9001) && (paramInt <= 9002)) || ((paramInt >= 12289) && (paramInt <= 12291)) || ((paramInt >= 12296) && (paramInt <= 12305)) || ((paramInt >= 12308) && (paramInt <= 12319)) || ((paramInt >= 64830) && (paramInt <= 64831)) || ((paramInt >= 65072) && (paramInt <= 65092)) || ((paramInt >= 65097) && (paramInt <= 65106)) || ((paramInt >= 65108) && (paramInt <= 65121)) || ((paramInt >= 65130) && (paramInt <= 65131)) || ((paramInt >= 65281) && (paramInt <= 65283)) || ((paramInt >= 65285) && (paramInt <= 65290)) || ((paramInt >= 65292) && (paramInt <= 65295)) || ((paramInt >= 65306) && (paramInt <= 65307)) || ((paramInt >= 65311) && (paramInt <= 65312)) || ((paramInt >= 65339) && (paramInt <= 65341)) || ((paramInt >= 65377) && (paramInt <= 65381)))
        {
          this.wraphere = (this.linelen + 2);
          i = 1;
        }
        else
        {
          switch (paramInt)
          {
          case 12336:
          case 12539:
          case 65123:
          case 65128:
          case 65343:
          case 65371:
          case 65373:
            this.wraphere = (this.linelen + 2);
            i = 1;
          }
        }
        if (i != 0)
          if (((paramInt >= 8218) && (paramInt <= 8220)) || ((paramInt >= 8222) && (paramInt <= 8223)))
            this.wraphere -= 1;
          else
            switch (paramInt)
            {
            case 8216:
            case 8249:
            case 8261:
            case 8317:
            case 8333:
            case 9001:
            case 12296:
            case 12298:
            case 12300:
            case 12302:
            case 12304:
            case 12308:
            case 12310:
            case 12312:
            case 12314:
            case 12317:
            case 64830:
            case 65077:
            case 65079:
            case 65081:
            case 65083:
            case 65085:
            case 65087:
            case 65089:
            case 65091:
            case 65113:
            case 65115:
            case 65117:
            case 65288:
            case 65339:
            case 65371:
            case 65378:
              this.wraphere -= 1;
            }
      }
      else
      {
        if ("BIG5".equals(this.configuration.getOutCharEncodingName()))
        {
          addC(paramInt, this.linelen++);
          if (((paramInt & 0xFF00) == 41216) && (!TidyUtils.toBoolean(paramShort & 0x1)))
          {
            this.wraphere = this.linelen;
            if ((paramInt > 92) && (paramInt < 173) && ((paramInt & 0x1) == 1))
              this.wraphere -= 1;
          }
          return;
        }
        if (("SHIFTJIS".equals(this.configuration.getOutCharEncodingName())) || ("ISO2022".equals(this.configuration.getOutCharEncodingName())))
        {
          addC(paramInt, this.linelen++);
          return;
        }
        if (this.configuration.rawOut)
        {
          addC(paramInt, this.linelen++);
          return;
        }
      }
    if ((paramInt == 160) && (TidyUtils.toBoolean(paramShort & 0x1)))
    {
      addC(32, this.linelen++);
      return;
    }
    if (((this.configuration.makeClean) && (this.configuration.asciiChars)) || ((this.configuration.makeBare) && (paramInt >= 8211) && (paramInt <= 8222)))
      switch (paramInt)
      {
      case 8211:
      case 8212:
        paramInt = 45;
        break;
      case 8216:
      case 8217:
      case 8218:
        paramInt = 39;
        break;
      case 8220:
      case 8221:
      case 8222:
        paramInt = 34;
      case 8213:
      case 8214:
      case 8215:
      case 8219:
      }
    String str;
    int j;
    if ("ISO8859_1".equals(this.configuration.getOutCharEncodingName()))
    {
      if (paramInt > 255)
      {
        if (!this.configuration.numEntities)
        {
          str = EntityTable.getDefaultEntityTable().entityName((short)paramInt);
          if (str != null)
            str = "&" + str + ";";
          else
            str = "&#" + paramInt + ";";
        }
        else
        {
          str = "&#" + paramInt + ";";
        }
        for (j = 0; j < str.length(); j++)
          addC(str.charAt(j), this.linelen++);
        return;
      }
      if ((paramInt > 126) && (paramInt < 160))
      {
        str = "&#" + paramInt + ";";
        for (j = 0; j < str.length(); j++)
          addC(str.charAt(j), this.linelen++);
        return;
      }
      addC(paramInt, this.linelen++);
      return;
    }
    if (this.configuration.getOutCharEncodingName().startsWith("UTF"))
    {
      addC(paramInt, this.linelen++);
      return;
    }
    if (this.configuration.xmlTags)
    {
      if ((paramInt > 127) && ("ASCII".equals(this.configuration.getOutCharEncodingName())))
      {
        str = "&#" + paramInt + ";";
        for (j = 0; j < str.length(); j++)
          addC(str.charAt(j), this.linelen++);
        return;
      }
      addC(paramInt, this.linelen++);
      return;
    }
    if (("ASCII".equals(this.configuration.getOutCharEncodingName())) && ((paramInt > 126) || ((paramInt < 32) && (paramInt != 9))))
    {
      if (!this.configuration.numEntities)
      {
        str = EntityTable.getDefaultEntityTable().entityName((short)paramInt);
        if (str != null)
          str = "&" + str + ";";
        else
          str = "&#" + paramInt + ";";
      }
      else
      {
        str = "&#" + paramInt + ";";
      }
      for (j = 0; j < str.length(); j++)
        addC(str.charAt(j), this.linelen++);
      return;
    }
    addC(paramInt, this.linelen++);
  }

  private void printText(Out paramOut, short paramShort, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    int[] arrayOfInt = new int[1];
    for (int i = paramInt2; i < paramInt3; i++)
    {
      if (paramInt1 + this.linelen >= this.configuration.wraplen)
        wrapLine(paramOut, paramInt1);
      int j = paramArrayOfByte[i] & 0xFF;
      if (j > 127)
      {
        i += getUTF8(paramArrayOfByte, i, arrayOfInt);
        j = arrayOfInt[0];
      }
      if (j == 10)
        flushLine(paramOut, paramInt1);
      else
        printChar(j, paramShort);
    }
  }

  private void printString(String paramString)
  {
    for (int i = 0; i < paramString.length(); i++)
      addC(paramString.charAt(i), this.linelen++);
  }

  private void printAttrValue(Out paramOut, int paramInt1, String paramString, int paramInt2, boolean paramBoolean)
  {
    int[] arrayOfInt = new int[1];
    boolean bool = false;
    byte[] arrayOfByte = null;
    short s = paramBoolean ? 4 : 5;
    if (paramString != null)
      arrayOfByte = TidyUtils.getBytes(paramString);
    if ((arrayOfByte != null) && (arrayOfByte.length >= 5) && (arrayOfByte[0] == 60) && ((arrayOfByte[1] == 37) || (arrayOfByte[1] == 64) || (new String(arrayOfByte, 0, 5).equals("<?php"))))
      s = (short)(s | 0x10);
    if (paramInt2 == 0)
      paramInt2 = 34;
    addC(61, this.linelen++);
    if (!this.configuration.xmlOut)
    {
      if (paramInt1 + this.linelen < this.configuration.wraplen)
        this.wraphere = this.linelen;
      if (paramInt1 + this.linelen >= this.configuration.wraplen)
        wrapLine(paramOut, paramInt1);
      if (paramInt1 + this.linelen < this.configuration.wraplen)
        this.wraphere = this.linelen;
      else
        condFlushLine(paramOut, paramInt1);
    }
    addC(paramInt2, this.linelen++);
    if (paramString != null)
    {
      this.inString = false;
      int j = 0;
      while (j < arrayOfByte.length)
      {
        int i = arrayOfByte[j] & 0xFF;
        if ((paramBoolean) && (i == 32) && (paramInt1 + this.linelen < this.configuration.wraplen))
        {
          this.wraphere = this.linelen;
          bool = this.inString;
        }
        if ((paramBoolean) && (this.wraphere > 0) && (paramInt1 + this.linelen >= this.configuration.wraplen))
          wrapAttrVal(paramOut, paramInt1, bool);
        if (i == paramInt2)
        {
          String str = i == 34 ? "&quot;" : "&#39;";
          for (int k = 0; k < str.length(); k++)
            addC(str.charAt(k), this.linelen++);
          j++;
        }
        else if (i == 34)
        {
          if (this.configuration.quoteMarks)
          {
            addC(38, this.linelen++);
            addC(113, this.linelen++);
            addC(117, this.linelen++);
            addC(111, this.linelen++);
            addC(116, this.linelen++);
            addC(59, this.linelen++);
          }
          else
          {
            addC(34, this.linelen++);
          }
          if (paramInt2 == 39)
            this.inString = (!this.inString);
          j++;
        }
        else if (i == 39)
        {
          if (this.configuration.quoteMarks)
          {
            addC(38, this.linelen++);
            addC(35, this.linelen++);
            addC(51, this.linelen++);
            addC(57, this.linelen++);
            addC(59, this.linelen++);
          }
          else
          {
            addC(39, this.linelen++);
          }
          if (paramInt2 == 34)
            this.inString = (!this.inString);
          j++;
        }
        else
        {
          if (i > 127)
          {
            j += getUTF8(arrayOfByte, j, arrayOfInt);
            i = arrayOfInt[0];
          }
          j++;
          if (i == 10)
            flushLine(paramOut, paramInt1);
          else
            printChar(i, s);
        }
      }
    }
    this.inString = false;
    addC(paramInt2, this.linelen++);
  }

  private void printAttribute(Out paramOut, int paramInt, Node paramNode, AttVal paramAttVal)
  {
    boolean bool = false;
    if (this.configuration.indentAttributes)
    {
      flushLine(paramOut, paramInt);
      paramInt += this.configuration.spaces;
    }
    String str = paramAttVal.attribute;
    if (paramInt + this.linelen >= this.configuration.wraplen)
      wrapLine(paramOut, paramInt);
    if ((!this.configuration.xmlTags) && (!this.configuration.xmlOut) && (paramAttVal.dict != null))
      if (AttributeTable.getDefaultAttributeTable().isScript(str))
        bool = this.configuration.wrapScriptlets;
      else if ((!paramAttVal.dict.isNowrap()) && (this.configuration.wrapAttVals))
        bool = true;
    if (paramInt + this.linelen < this.configuration.wraplen)
    {
      this.wraphere = this.linelen;
      addC(32, this.linelen++);
    }
    else
    {
      condFlushLine(paramOut, paramInt);
      addC(32, this.linelen++);
    }
    for (int i = 0; i < str.length(); i++)
      addC(TidyUtils.foldCase(str.charAt(i), this.configuration.upperCaseAttrs, this.configuration.xmlTags), this.linelen++);
    if (paramInt + this.linelen >= this.configuration.wraplen)
      wrapLine(paramOut, paramInt);
    if (paramAttVal.value == null)
    {
      if ((this.configuration.xmlTags) || (this.configuration.xmlOut))
        printAttrValue(paramOut, paramInt, paramAttVal.isBoolAttribute() ? paramAttVal.attribute : "", paramAttVal.delim, true);
      else if ((!paramAttVal.isBoolAttribute()) && (paramNode != null) && (!paramNode.isNewNode()))
        printAttrValue(paramOut, paramInt, "", paramAttVal.delim, true);
      else if (paramInt + this.linelen < this.configuration.wraplen)
        this.wraphere = this.linelen;
    }
    else
      printAttrValue(paramOut, paramInt, paramAttVal.value, paramAttVal.delim, bool);
  }

  private void printAttrs(Out paramOut, int paramInt, Node paramNode, AttVal paramAttVal)
  {
    if ((this.configuration.xmlOut) && (this.configuration.xmlSpace) && (ParserImpl.XMLPreserveWhiteSpace(paramNode, this.configuration.tt)) && (paramNode.getAttrByName("xml:space") == null))
    {
      paramNode.addAttribute("xml:space", "preserve");
      if (paramAttVal != null)
        paramAttVal = paramNode.attributes;
    }
    if (paramAttVal != null)
    {
      if (paramAttVal.next != null)
        printAttrs(paramOut, paramInt, paramNode, paramAttVal.next);
      if (paramAttVal.attribute != null)
      {
        Attribute localAttribute = paramAttVal.dict;
        if ((!this.configuration.dropProprietaryAttributes) || ((localAttribute != null) && (!TidyUtils.toBoolean(localAttribute.getVersions() & 0x1C0))))
          printAttribute(paramOut, paramInt, paramNode, paramAttVal);
      }
      else if (paramAttVal.asp != null)
      {
        addC(32, this.linelen++);
        printAsp(paramOut, paramInt, paramAttVal.asp);
      }
      else if (paramAttVal.php != null)
      {
        addC(32, this.linelen++);
        printPhp(paramOut, paramInt, paramAttVal.php);
      }
    }
  }

  private static boolean afterSpace(Node paramNode)
  {
    if ((paramNode == null) || (paramNode.tag == null) || (!TidyUtils.toBoolean(paramNode.tag.model & 0x10)))
      return true;
    Node localNode = paramNode.prev;
    if (localNode != null)
    {
      if ((localNode.type == 4) && (localNode.end > localNode.start))
      {
        int i = localNode.textarray[(localNode.end - 1)] & 0xFF;
        if ((i == 160) || (i == 32) || (i == 10))
          return true;
      }
      return false;
    }
    return afterSpace(paramNode.parent);
  }

  private void printTag(Lexer paramLexer, Out paramOut, short paramShort, int paramInt, Node paramNode)
  {
    TagTable localTagTable = this.configuration.tt;
    addC(60, this.linelen++);
    if (paramNode.type == 6)
      addC(47, this.linelen++);
    String str = paramNode.element;
    for (int i = 0; i < str.length(); i++)
      addC(TidyUtils.foldCase(str.charAt(i), this.configuration.upperCaseTags, this.configuration.xmlTags), this.linelen++);
    printAttrs(paramOut, paramInt, paramNode, paramNode.attributes);
    if (((this.configuration.xmlOut) || (this.configuration.xHTML)) && ((paramNode.type == 7) || (TidyUtils.toBoolean(paramNode.tag.model & 0x1))))
    {
      addC(32, this.linelen++);
      addC(47, this.linelen++);
    }
    addC(62, this.linelen++);
    if (((paramNode.type != 7) || (this.configuration.xHTML)) && (!TidyUtils.toBoolean(paramShort & 0x1)))
    {
      if (paramInt + this.linelen >= this.configuration.wraplen)
        wrapLine(paramOut, paramInt);
      if ((paramInt + this.linelen < this.configuration.wraplen) && (!TidyUtils.toBoolean(paramShort & 0x8)) && ((!TidyUtils.toBoolean(paramNode.tag.model & 0x10)) || (paramNode.tag == localTagTable.tagBr)) && (afterSpace(paramNode)))
        this.wraphere = this.linelen;
    }
    else
    {
      condFlushLine(paramOut, paramInt);
    }
  }

  private void printEndTag(short paramShort, int paramInt, Node paramNode)
  {
    addC(60, this.linelen++);
    addC(47, this.linelen++);
    String str = paramNode.element;
    for (int i = 0; i < str.length(); i++)
      addC(TidyUtils.foldCase(str.charAt(i), this.configuration.upperCaseTags, this.configuration.xmlTags), this.linelen++);
    addC(62, this.linelen++);
  }

  private void printComment(Out paramOut, int paramInt, Node paramNode)
  {
    if (this.configuration.hideComments)
      return;
    if (paramInt + this.linelen < this.configuration.wraplen)
      this.wraphere = this.linelen;
    addC(60, this.linelen++);
    addC(33, this.linelen++);
    addC(45, this.linelen++);
    addC(45, this.linelen++);
    printText(paramOut, (short)2, paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    addC(45, this.linelen++);
    addC(45, this.linelen++);
    addC(62, this.linelen++);
    if (paramNode.linebreak)
      flushLine(paramOut, paramInt);
  }

  private void printDocType(Out paramOut, int paramInt, Lexer paramLexer, Node paramNode)
  {
    int j = 0;
    short s = 0;
    boolean bool = this.configuration.quoteMarks;
    this.configuration.quoteMarks = false;
    if (paramInt + this.linelen < this.configuration.wraplen)
      this.wraphere = this.linelen;
    condFlushLine(paramOut, paramInt);
    addC(60, this.linelen++);
    addC(33, this.linelen++);
    addC(68, this.linelen++);
    addC(79, this.linelen++);
    addC(67, this.linelen++);
    addC(84, this.linelen++);
    addC(89, this.linelen++);
    addC(80, this.linelen++);
    addC(69, this.linelen++);
    addC(32, this.linelen++);
    if (paramInt + this.linelen < this.configuration.wraplen)
      this.wraphere = this.linelen;
    for (int i = paramNode.start; i < paramNode.end; i++)
    {
      if (paramInt + this.linelen >= this.configuration.wraplen)
        wrapLine(paramOut, paramInt);
      j = paramNode.textarray[i] & 0xFF;
      if (TidyUtils.toBoolean(s & 0x10))
      {
        if (j == 93)
          s = (short)(s & 0xFFFFFFEF);
      }
      else if (j == 91)
        s = (short)(s | 0x10);
      int[] arrayOfInt = new int[1];
      if (j > 127)
      {
        i += getUTF8(paramNode.textarray, i, arrayOfInt);
        j = arrayOfInt[0];
      }
      if (j == 10)
        flushLine(paramOut, paramInt);
      else
        printChar(j, s);
    }
    if (this.linelen < this.configuration.wraplen)
      this.wraphere = this.linelen;
    addC(62, this.linelen++);
    this.configuration.quoteMarks = bool;
    condFlushLine(paramOut, paramInt);
  }

  private void printPI(Out paramOut, int paramInt, Node paramNode)
  {
    if (paramInt + this.linelen < this.configuration.wraplen)
      this.wraphere = this.linelen;
    addC(60, this.linelen++);
    addC(63, this.linelen++);
    printText(paramOut, (short)16, paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    if ((paramNode.end <= 0) || (paramNode.textarray[(paramNode.end - 1)] != 63))
      addC(63, this.linelen++);
    addC(62, this.linelen++);
    condFlushLine(paramOut, paramInt);
  }

  private void printXmlDecl(Out paramOut, int paramInt, Node paramNode)
  {
    if (paramInt + this.linelen < this.configuration.wraplen)
      this.wraphere = this.linelen;
    addC(60, this.linelen++);
    addC(63, this.linelen++);
    addC(120, this.linelen++);
    addC(109, this.linelen++);
    addC(108, this.linelen++);
    printAttrs(paramOut, paramInt, paramNode, paramNode.attributes);
    if ((paramNode.end <= 0) || (paramNode.textarray[(paramNode.end - 1)] != 63))
      addC(63, this.linelen++);
    addC(62, this.linelen++);
    condFlushLine(paramOut, paramInt);
  }

  private void printAsp(Out paramOut, int paramInt, Node paramNode)
  {
    int i = this.configuration.wraplen;
    if ((!this.configuration.wrapAsp) || (!this.configuration.wrapJste))
      this.configuration.wraplen = 16777215;
    addC(60, this.linelen++);
    addC(37, this.linelen++);
    printText(paramOut, (short)(this.configuration.wrapAsp ? 16 : 2), paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    addC(37, this.linelen++);
    addC(62, this.linelen++);
    this.configuration.wraplen = i;
  }

  private void printJste(Out paramOut, int paramInt, Node paramNode)
  {
    int i = this.configuration.wraplen;
    if (!this.configuration.wrapJste)
      this.configuration.wraplen = 16777215;
    addC(60, this.linelen++);
    addC(35, this.linelen++);
    printText(paramOut, (short)(this.configuration.wrapJste ? 16 : 2), paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    addC(35, this.linelen++);
    addC(62, this.linelen++);
    this.configuration.wraplen = i;
  }

  private void printPhp(Out paramOut, int paramInt, Node paramNode)
  {
    int i = this.configuration.wraplen;
    if (!this.configuration.wrapPhp)
      this.configuration.wraplen = 16777215;
    addC(60, this.linelen++);
    addC(63, this.linelen++);
    printText(paramOut, (short)(this.configuration.wrapPhp ? 16 : 2), paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    addC(63, this.linelen++);
    addC(62, this.linelen++);
    this.configuration.wraplen = i;
  }

  private void printCDATA(Out paramOut, int paramInt, Node paramNode)
  {
    int i = this.configuration.wraplen;
    if (!this.configuration.indentCdata)
      paramInt = 0;
    condFlushLine(paramOut, paramInt);
    this.configuration.wraplen = 16777215;
    addC(60, this.linelen++);
    addC(33, this.linelen++);
    addC(91, this.linelen++);
    addC(67, this.linelen++);
    addC(68, this.linelen++);
    addC(65, this.linelen++);
    addC(84, this.linelen++);
    addC(65, this.linelen++);
    addC(91, this.linelen++);
    printText(paramOut, (short)2, paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    addC(93, this.linelen++);
    addC(93, this.linelen++);
    addC(62, this.linelen++);
    condFlushLine(paramOut, paramInt);
    this.configuration.wraplen = i;
  }

  private void printSection(Out paramOut, int paramInt, Node paramNode)
  {
    int i = this.configuration.wraplen;
    if (!this.configuration.wrapSection)
      this.configuration.wraplen = 16777215;
    addC(60, this.linelen++);
    addC(33, this.linelen++);
    addC(91, this.linelen++);
    printText(paramOut, (short)(this.configuration.wrapSection ? 16 : 2), paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    addC(93, this.linelen++);
    addC(62, this.linelen++);
    this.configuration.wraplen = i;
  }

  private boolean insideHead(Node paramNode)
  {
    if (paramNode.tag == this.configuration.tt.tagHead)
      return true;
    if (paramNode.parent != null)
      return insideHead(paramNode.parent);
    return false;
  }

  private int textEndsWithNewline(Lexer paramLexer, Node paramNode)
  {
    if ((paramNode.type == 4) && (paramNode.end > paramNode.start))
    {
      int i;
      for (int j = paramNode.end - 1; (j >= paramNode.start) && (TidyUtils.toBoolean(i = paramNode.textarray[j] & 0xFF)) && ((i == 32) || (i == 9) || (i == 13)); j--);
      if ((j >= 0) && (paramNode.textarray[j] == 10))
        return paramNode.end - j - 1;
    }
    return -1;
  }

  static boolean hasCDATA(Lexer paramLexer, Node paramNode)
  {
    if (paramNode.type != 4)
      return false;
    int i = paramNode.end - paramNode.start + 1;
    String str = TidyUtils.getString(paramNode.textarray, paramNode.start, i);
    int j = str.indexOf("<![CDATA[");
    return (j > -1) && (j <= i);
  }

  private void printScriptStyle(Out paramOut, short paramShort, int paramInt, Lexer paramLexer, Node paramNode)
  {
    String str1 = "";
    String str2 = "";
    boolean bool = false;
    int i = -1;
    if (insideHead(paramNode));
    paramInt = 0;
    printTag(paramLexer, paramOut, paramShort, paramInt, paramNode);
    int k;
    if ((paramLexer.configuration.xHTML) && (paramNode.content != null))
    {
      AttVal localAttVal = paramNode.getAttrByName("type");
      if (localAttVal != null)
        if ("text/javascript".equalsIgnoreCase(localAttVal.value))
        {
          str1 = "//";
          str2 = "";
        }
        else if ("text/css".equalsIgnoreCase(localAttVal.value))
        {
          str1 = "/*";
          str2 = "*/";
        }
        else if ("text/vbscript".equalsIgnoreCase(localAttVal.value))
        {
          str1 = "'";
          str2 = "";
        }
      bool = hasCDATA(paramLexer, paramNode.content);
      if (!bool)
      {
        k = paramLexer.configuration.wraplen;
        paramLexer.configuration.wraplen = 16777215;
        this.linelen = addAsciiString(str1, this.linelen);
        this.linelen = addAsciiString("<![CDATA[", this.linelen);
        this.linelen = addAsciiString(str2, this.linelen);
        condFlushLine(paramOut, paramInt);
        paramLexer.configuration.wraplen = k;
      }
    }
    for (Node localNode = paramNode.content; localNode != null; localNode = localNode.next)
    {
      printTree(paramOut, (short)(paramShort | 0x1 | 0x8 | 0x10), 0, paramLexer, localNode);
      if (localNode.next == null)
        i = textEndsWithNewline(paramLexer, localNode);
    }
    if (i < 0)
    {
      condFlushLine(paramOut, paramInt);
      i = 0;
    }
    if ((paramLexer.configuration.xHTML) && (paramNode.content != null) && (!bool))
    {
      k = paramLexer.configuration.wraplen;
      paramLexer.configuration.wraplen = 16777215;
      if ((i > 0) && (this.linelen < i))
        this.linelen = i;
      for (int j = 0; (i < paramInt) && (j < paramInt - i); j++)
        addC(32, this.linelen++);
      this.linelen = addAsciiString(str1, this.linelen);
      this.linelen = addAsciiString("]]>", this.linelen);
      this.linelen = addAsciiString(str2, this.linelen);
      paramLexer.configuration.wraplen = k;
      condFlushLine(paramOut, 0);
    }
    printEndTag(paramShort, paramInt, paramNode);
    if ((!paramLexer.configuration.indentContent) && (paramNode.next != null) && ((paramNode.tag == null) || (!TidyUtils.toBoolean(paramNode.tag.model & 0x10))) && (paramNode.type == 4))
      flushLine(paramOut, paramInt);
    flushLine(paramOut, paramInt);
  }

  private boolean shouldIndent(Node paramNode)
  {
    TagTable localTagTable = this.configuration.tt;
    if (!this.configuration.indentContent)
      return false;
    if (this.configuration.smartIndent)
    {
      if ((paramNode.content != null) && (TidyUtils.toBoolean(paramNode.tag.model & 0x40000)))
      {
        for (paramNode = paramNode.content; paramNode != null; paramNode = paramNode.next)
          if ((paramNode.tag != null) && (TidyUtils.toBoolean(paramNode.tag.model & 0x8)))
            return true;
        return false;
      }
      if (TidyUtils.toBoolean(paramNode.tag.model & 0x4000))
        return false;
      if (paramNode.tag == localTagTable.tagP)
        return false;
      if (paramNode.tag == localTagTable.tagTitle)
        return false;
    }
    if (TidyUtils.toBoolean(paramNode.tag.model & 0xC00))
      return true;
    if (paramNode.tag == localTagTable.tagMap)
      return true;
    return !TidyUtils.toBoolean(paramNode.tag.model & 0x10);
  }

  void printBody(Out paramOut, Lexer paramLexer, Node paramNode, boolean paramBoolean)
  {
    if (paramNode == null)
      return;
    Node localNode1 = paramNode.findBody(paramLexer.configuration.tt);
    if (localNode1 != null)
      for (Node localNode2 = localNode1.content; localNode2 != null; localNode2 = localNode2.next)
        if (paramBoolean)
          printXMLTree(paramOut, (short)0, 0, paramLexer, localNode2);
        else
          printTree(paramOut, (short)0, 0, paramLexer, localNode2);
  }

  public void printTree(Out paramOut, short paramShort, int paramInt, Lexer paramLexer, Node paramNode)
  {
    TagTable localTagTable = this.configuration.tt;
    if (paramNode == null)
      return;
    if ((paramNode.type == 4) || ((paramNode.type == 8) && (paramLexer.configuration.escapeCdata)))
    {
      printText(paramOut, paramShort, paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    }
    else if (paramNode.type == 2)
    {
      printComment(paramOut, paramInt, paramNode);
    }
    else
    {
      Node localNode1;
      if (paramNode.type == 0)
        for (localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next)
          printTree(paramOut, paramShort, paramInt, paramLexer, localNode1);
      if (paramNode.type == 1)
      {
        printDocType(paramOut, paramInt, paramLexer, paramNode);
      }
      else if (paramNode.type == 3)
      {
        printPI(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 13)
      {
        printXmlDecl(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 8)
      {
        printCDATA(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 9)
      {
        printSection(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 10)
      {
        printAsp(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 11)
      {
        printJste(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 12)
      {
        printPhp(paramOut, paramInt, paramNode);
      }
      else if ((TidyUtils.toBoolean(paramNode.tag.model & 0x1)) || ((paramNode.type == 7) && (!this.configuration.xHTML)))
      {
        if (!TidyUtils.toBoolean(paramNode.tag.model & 0x10))
          condFlushLine(paramOut, paramInt);
        if ((paramNode.tag == localTagTable.tagBr) && (paramNode.prev != null) && (paramNode.prev.tag != localTagTable.tagBr) && (this.configuration.breakBeforeBR))
          flushLine(paramOut, paramInt);
        if ((this.configuration.makeClean) && (paramNode.tag == localTagTable.tagWbr))
          printString(" ");
        else
          printTag(paramLexer, paramOut, paramShort, paramInt, paramNode);
        if ((paramNode.tag == localTagTable.tagParam) || (paramNode.tag == localTagTable.tagArea))
          condFlushLine(paramOut, paramInt);
        else if ((paramNode.tag == localTagTable.tagBr) || (paramNode.tag == localTagTable.tagHr))
          flushLine(paramOut, paramInt);
      }
      else
      {
        if (paramNode.type == 7)
          paramNode.type = 5;
        if ((paramNode.tag != null) && (paramNode.tag.getParser() == ParserImpl.PRE))
        {
          condFlushLine(paramOut, paramInt);
          paramInt = 0;
          condFlushLine(paramOut, paramInt);
          printTag(paramLexer, paramOut, paramShort, paramInt, paramNode);
          flushLine(paramOut, paramInt);
          for (localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next)
            printTree(paramOut, (short)(paramShort | 0x1 | 0x8), paramInt, paramLexer, localNode1);
          condFlushLine(paramOut, paramInt);
          printEndTag(paramShort, paramInt, paramNode);
          flushLine(paramOut, paramInt);
          if ((!this.configuration.indentContent) && (paramNode.next != null))
            flushLine(paramOut, paramInt);
        }
        else if ((paramNode.tag == localTagTable.tagStyle) || (paramNode.tag == localTagTable.tagScript))
        {
          printScriptStyle(paramOut, (short)(paramShort | 0x1 | 0x8 | 0x10), paramInt, paramLexer, paramNode);
        }
        else if (TidyUtils.toBoolean(paramNode.tag.model & 0x10))
        {
          if (this.configuration.makeClean)
          {
            if (paramNode.tag == localTagTable.tagFont)
            {
              for (localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next)
                printTree(paramOut, paramShort, paramInt, paramLexer, localNode1);
              return;
            }
            if (paramNode.tag == localTagTable.tagNobr)
            {
              for (localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next)
                printTree(paramOut, (short)(paramShort | 0x8), paramInt, paramLexer, localNode1);
              return;
            }
          }
          printTag(paramLexer, paramOut, paramShort, paramInt, paramNode);
          if (shouldIndent(paramNode))
          {
            condFlushLine(paramOut, paramInt);
            paramInt += this.configuration.spaces;
            for (localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next)
              printTree(paramOut, paramShort, paramInt, paramLexer, localNode1);
            condFlushLine(paramOut, paramInt);
            paramInt -= this.configuration.spaces;
            condFlushLine(paramOut, paramInt);
          }
          else
          {
            for (localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next)
              printTree(paramOut, paramShort, paramInt, paramLexer, localNode1);
          }
          printEndTag(paramShort, paramInt, paramNode);
        }
        else
        {
          condFlushLine(paramOut, paramInt);
          if ((this.configuration.smartIndent) && (paramNode.prev != null))
            flushLine(paramOut, paramInt);
          if ((!this.configuration.hideEndTags) || (paramNode.tag == null) || (!TidyUtils.toBoolean(paramNode.tag.model & 0x200000)) || (paramNode.attributes != null))
          {
            printTag(paramLexer, paramOut, paramShort, paramInt, paramNode);
            if (shouldIndent(paramNode))
              condFlushLine(paramOut, paramInt);
            else if ((TidyUtils.toBoolean(paramNode.tag.model & 0x2)) || (paramNode.tag == localTagTable.tagNoframes) || ((TidyUtils.toBoolean(paramNode.tag.model & 0x4)) && (paramNode.tag != localTagTable.tagTitle)))
              flushLine(paramOut, paramInt);
          }
          if ((paramNode.tag == localTagTable.tagBody) && (this.configuration.burstSlides))
          {
            printSlide(paramOut, paramShort, this.configuration.indentContent ? paramInt + this.configuration.spaces : paramInt, paramLexer);
          }
          else
          {
            Node localNode2 = null;
            for (localNode1 = paramNode.content; localNode1 != null; localNode1 = localNode1.next)
            {
              if ((localNode2 != null) && (!this.configuration.indentContent) && (localNode2.type == 4) && (localNode1.tag != null) && (!TidyUtils.toBoolean(localNode1.tag.model & 0x10)))
                flushLine(paramOut, paramInt);
              printTree(paramOut, paramShort, shouldIndent(paramNode) ? paramInt + this.configuration.spaces : paramInt, paramLexer, localNode1);
              localNode2 = localNode1;
            }
          }
          if ((shouldIndent(paramNode)) || (((TidyUtils.toBoolean(paramNode.tag.model & 0x2)) || (paramNode.tag == localTagTable.tagNoframes) || ((TidyUtils.toBoolean(paramNode.tag.model & 0x4)) && (paramNode.tag != localTagTable.tagTitle))) && (!this.configuration.hideEndTags)))
          {
            condFlushLine(paramOut, this.configuration.indentContent ? paramInt + this.configuration.spaces : paramInt);
            if ((!this.configuration.hideEndTags) || (!TidyUtils.toBoolean(paramNode.tag.model & 0x8000)))
            {
              printEndTag(paramShort, paramInt, paramNode);
              if (!paramLexer.seenEndHtml)
                flushLine(paramOut, paramInt);
            }
          }
          else
          {
            if ((!this.configuration.hideEndTags) || (!TidyUtils.toBoolean(paramNode.tag.model & 0x8000)))
              printEndTag(paramShort, paramInt, paramNode);
            flushLine(paramOut, paramInt);
          }
        }
      }
    }
  }

  public void printXMLTree(Out paramOut, short paramShort, int paramInt, Lexer paramLexer, Node paramNode)
  {
    TagTable localTagTable = this.configuration.tt;
    if (paramNode == null)
      return;
    if ((paramNode.type == 4) || ((paramNode.type == 8) && (paramLexer.configuration.escapeCdata)))
    {
      printText(paramOut, paramShort, paramInt, paramNode.textarray, paramNode.start, paramNode.end);
    }
    else if (paramNode.type == 2)
    {
      condFlushLine(paramOut, paramInt);
      printComment(paramOut, 0, paramNode);
      condFlushLine(paramOut, 0);
    }
    else
    {
      Node localNode;
      if (paramNode.type == 0)
      {
        for (localNode = paramNode.content; localNode != null; localNode = localNode.next)
          printXMLTree(paramOut, paramShort, paramInt, paramLexer, localNode);
      }
      else if (paramNode.type == 1)
      {
        printDocType(paramOut, paramInt, paramLexer, paramNode);
      }
      else if (paramNode.type == 3)
      {
        printPI(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 13)
      {
        printXmlDecl(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 8)
      {
        printCDATA(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 9)
      {
        printSection(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 10)
      {
        printAsp(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 11)
      {
        printJste(paramOut, paramInt, paramNode);
      }
      else if (paramNode.type == 12)
      {
        printPhp(paramOut, paramInt, paramNode);
      }
      else if ((TidyUtils.toBoolean(paramNode.tag.model & 0x1)) || ((paramNode.type == 7) && (!this.configuration.xHTML)))
      {
        condFlushLine(paramOut, paramInt);
        printTag(paramLexer, paramOut, paramShort, paramInt, paramNode);
      }
      else
      {
        int i = 0;
        for (localNode = paramNode.content; localNode != null; localNode = localNode.next)
          if (localNode.type == 4)
          {
            i = 1;
            break;
          }
        condFlushLine(paramOut, paramInt);
        int j;
        if (ParserImpl.XMLPreserveWhiteSpace(paramNode, localTagTable))
        {
          paramInt = 0;
          j = 0;
          i = 0;
        }
        else if (i != 0)
        {
          j = paramInt;
        }
        else
        {
          j = paramInt + this.configuration.spaces;
        }
        printTag(paramLexer, paramOut, paramShort, paramInt, paramNode);
        if ((i == 0) && (paramNode.content != null))
          flushLine(paramOut, paramInt);
        for (localNode = paramNode.content; localNode != null; localNode = localNode.next)
          printXMLTree(paramOut, paramShort, j, paramLexer, localNode);
        if ((i == 0) && (paramNode.content != null))
          condFlushLine(paramOut, j);
        printEndTag(paramShort, paramInt, paramNode);
      }
    }
  }

  public int countSlides(Node paramNode)
  {
    int i = 1;
    TagTable localTagTable = this.configuration.tt;
    if ((paramNode != null) && (paramNode.content != null) && (paramNode.content.tag == localTagTable.tagH2))
      i--;
    if (paramNode != null)
      for (paramNode = paramNode.content; paramNode != null; paramNode = paramNode.next)
        if (paramNode.tag == localTagTable.tagH2)
          i++;
    return i;
  }

  private void printNavBar(Out paramOut, int paramInt)
  {
    condFlushLine(paramOut, paramInt);
    printString("<center><small>");
    NumberFormat localNumberFormat = NumberFormat.getInstance();
    localNumberFormat.setMinimumIntegerDigits(3);
    String str;
    if (this.slide > 1)
    {
      str = "<a href=\"slide" + localNumberFormat.format(this.slide - 1) + ".html\">previous</a> | ";
      printString(str);
      condFlushLine(paramOut, paramInt);
      if (this.slide < this.count)
        printString("<a href=\"slide001.html\">start</a> | ");
      else
        printString("<a href=\"slide001.html\">start</a>");
      condFlushLine(paramOut, paramInt);
    }
    if (this.slide < this.count)
    {
      str = "<a href=\"slide" + localNumberFormat.format(this.slide + 1) + ".html\">next</a>";
      printString(str);
    }
    printString("</small></center>");
    condFlushLine(paramOut, paramInt);
  }

  public void printSlide(Out paramOut, short paramShort, int paramInt, Lexer paramLexer)
  {
    TagTable localTagTable = this.configuration.tt;
    NumberFormat localNumberFormat = NumberFormat.getInstance();
    localNumberFormat.setMinimumIntegerDigits(3);
    String str = "<div onclick=\"document.location='slide" + localNumberFormat.format(this.slide < this.count ? this.slide + 1 : 1L) + ".html'\">";
    printString(str);
    condFlushLine(paramOut, paramInt);
    if ((this.slidecontent != null) && (this.slidecontent.tag == localTagTable.tagH2))
    {
      printNavBar(paramOut, paramInt);
      addC(60, this.linelen++);
      addC(TidyUtils.foldCase('h', this.configuration.upperCaseTags, this.configuration.xmlTags), this.linelen++);
      addC(TidyUtils.foldCase('r', this.configuration.upperCaseTags, this.configuration.xmlTags), this.linelen++);
      if (this.configuration.xmlOut)
        printString(" />");
      else
        addC(62, this.linelen++);
      if (this.configuration.indentContent)
        condFlushLine(paramOut, paramInt);
      printTree(paramOut, paramShort, this.configuration.indentContent ? paramInt + this.configuration.spaces : paramInt, paramLexer, this.slidecontent);
      this.slidecontent = this.slidecontent.next;
    }
    Object localObject = null;
    for (Node localNode = this.slidecontent; (localNode != null) && (localNode.tag != localTagTable.tagH2); localNode = localNode.next)
    {
      if ((localObject != null) && (!this.configuration.indentContent) && (localObject.type == 4) && (localNode.tag != null) && (TidyUtils.toBoolean(localNode.tag.model & 0x8)))
      {
        flushLine(paramOut, paramInt);
        flushLine(paramOut, paramInt);
      }
      printTree(paramOut, paramShort, this.configuration.indentContent ? paramInt + this.configuration.spaces : paramInt, paramLexer, localNode);
      localObject = localNode;
    }
    this.slidecontent = localNode;
    condFlushLine(paramOut, paramInt);
    printString("<br clear=\"all\">");
    condFlushLine(paramOut, paramInt);
    addC(60, this.linelen++);
    addC(TidyUtils.foldCase('h', this.configuration.upperCaseTags, this.configuration.xmlTags), this.linelen++);
    addC(TidyUtils.foldCase('r', this.configuration.upperCaseTags, this.configuration.xmlTags), this.linelen++);
    if (this.configuration.xmlOut)
      printString(" />");
    else
      addC(62, this.linelen++);
    if (this.configuration.indentContent)
      condFlushLine(paramOut, paramInt);
    printNavBar(paramOut, paramInt);
    printString("</div>");
    condFlushLine(paramOut, paramInt);
  }

  public void addTransitionEffect(Lexer paramLexer, Node paramNode, double paramDouble)
  {
    Node localNode1 = paramNode.findHEAD(paramLexer.configuration.tt);
    String str = "blendTrans(Duration=" + new Double(paramDouble).toString() + ")";
    if (localNode1 != null)
    {
      Node localNode2 = paramLexer.inferredTag("meta");
      localNode2.addAttribute("http-equiv", "Page-Enter");
      localNode2.addAttribute("content", str);
      localNode1.insertNodeAtStart(localNode2);
    }
  }

  public void createSlides(Lexer paramLexer, Node paramNode)
  {
    NumberFormat localNumberFormat = NumberFormat.getInstance();
    localNumberFormat.setMinimumIntegerDigits(3);
    Node localNode = paramNode.findBody(paramLexer.configuration.tt);
    this.count = countSlides(localNode);
    this.slidecontent = localNode.content;
    addTransitionEffect(paramLexer, paramNode, 3.0D);
    for (this.slide = 1; this.slide <= this.count; this.slide += 1)
    {
      String str = "slide" + localNumberFormat.format(this.slide) + ".html";
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(str);
        Out localOut = OutFactory.getOut(this.configuration, localFileOutputStream);
        printTree(localOut, (short)0, 0, paramLexer, paramNode);
        flushLine(localOut, 0);
        localFileOutputStream.close();
      }
      catch (IOException localIOException)
      {
        System.err.println(str + localIOException.toString());
      }
    }
    while (new File("slide" + localNumberFormat.format(this.slide) + ".html").delete())
      this.slide += 1;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.PPrint
 * JD-Core Version:    0.6.2
 */