package org.dom4j.io;

public class OutputFormat
  implements Cloneable
{
  protected static final String STANDARD_INDENT = "  ";
  private boolean suppressDeclaration = false;
  private boolean newLineAfterDeclaration = true;
  private String encoding = "UTF-8";
  private boolean omitEncoding = false;
  private String indent = null;
  private boolean expandEmptyElements = false;
  private boolean newlines = false;
  private String lineSeparator = "\n";
  private boolean trimText = false;
  private boolean padText = false;
  private boolean doXHTML = false;
  private int newLineAfterNTags = 0;
  private char attributeQuoteChar = '"';
  
  public OutputFormat() {}
  
  public OutputFormat(String indent)
  {
    this.indent = indent;
  }
  
  public OutputFormat(String indent, boolean newlines)
  {
    this.indent = indent;
    this.newlines = newlines;
  }
  
  public OutputFormat(String indent, boolean newlines, String encoding)
  {
    this.indent = indent;
    this.newlines = newlines;
    this.encoding = encoding;
  }
  
  public String getLineSeparator()
  {
    return this.lineSeparator;
  }
  
  public void setLineSeparator(String separator)
  {
    this.lineSeparator = separator;
  }
  
  public boolean isNewlines()
  {
    return this.newlines;
  }
  
  public void setNewlines(boolean newlines)
  {
    this.newlines = newlines;
  }
  
  public String getEncoding()
  {
    return this.encoding;
  }
  
  public void setEncoding(String encoding)
  {
    if (encoding != null) {
      this.encoding = encoding;
    }
  }
  
  public boolean isOmitEncoding()
  {
    return this.omitEncoding;
  }
  
  public void setOmitEncoding(boolean omitEncoding)
  {
    this.omitEncoding = omitEncoding;
  }
  
  public void setSuppressDeclaration(boolean suppressDeclaration)
  {
    this.suppressDeclaration = suppressDeclaration;
  }
  
  public boolean isSuppressDeclaration()
  {
    return this.suppressDeclaration;
  }
  
  public void setNewLineAfterDeclaration(boolean newLineAfterDeclaration)
  {
    this.newLineAfterDeclaration = newLineAfterDeclaration;
  }
  
  public boolean isNewLineAfterDeclaration()
  {
    return this.newLineAfterDeclaration;
  }
  
  public boolean isExpandEmptyElements()
  {
    return this.expandEmptyElements;
  }
  
  public void setExpandEmptyElements(boolean expandEmptyElements)
  {
    this.expandEmptyElements = expandEmptyElements;
  }
  
  public boolean isTrimText()
  {
    return this.trimText;
  }
  
  public void setTrimText(boolean trimText)
  {
    this.trimText = trimText;
  }
  
  public boolean isPadText()
  {
    return this.padText;
  }
  
  public void setPadText(boolean padText)
  {
    this.padText = padText;
  }
  
  public String getIndent()
  {
    return this.indent;
  }
  
  public void setIndent(String indent)
  {
    if ((indent != null) && (indent.length() <= 0)) {
      indent = null;
    }
    this.indent = indent;
  }
  
  public void setIndent(boolean doIndent)
  {
    if (doIndent) {
      this.indent = "  ";
    } else {
      this.indent = null;
    }
  }
  
  public void setIndentSize(int indentSize)
  {
    StringBuffer indentBuffer = new StringBuffer();
    for (int local_i = 0; local_i < indentSize; local_i++) {
      indentBuffer.append(" ");
    }
    this.indent = indentBuffer.toString();
  }
  
  public boolean isXHTML()
  {
    return this.doXHTML;
  }
  
  public void setXHTML(boolean xhtml)
  {
    this.doXHTML = xhtml;
  }
  
  public int getNewLineAfterNTags()
  {
    return this.newLineAfterNTags;
  }
  
  public void setNewLineAfterNTags(int tagCount)
  {
    this.newLineAfterNTags = tagCount;
  }
  
  public char getAttributeQuoteCharacter()
  {
    return this.attributeQuoteChar;
  }
  
  public void setAttributeQuoteCharacter(char quoteChar)
  {
    if ((quoteChar == '\'') || (quoteChar == '"')) {
      this.attributeQuoteChar = quoteChar;
    } else {
      throw new IllegalArgumentException("Invalid attribute quote character (" + quoteChar + ")");
    }
  }
  
  public int parseOptions(String[] args, int local_i)
  {
    int size = args.length;
    while (local_i < size)
    {
      if (args[local_i].equals("-suppressDeclaration")) {
        setSuppressDeclaration(true);
      } else if (args[local_i].equals("-omitEncoding")) {
        setOmitEncoding(true);
      } else if (args[local_i].equals("-indent")) {
        setIndent(args[(++local_i)]);
      } else if (args[local_i].equals("-indentSize")) {
        setIndentSize(Integer.parseInt(args[(++local_i)]));
      } else if (args[local_i].startsWith("-expandEmpty")) {
        setExpandEmptyElements(true);
      } else if (args[local_i].equals("-encoding")) {
        setEncoding(args[(++local_i)]);
      } else if (args[local_i].equals("-newlines")) {
        setNewlines(true);
      } else if (args[local_i].equals("-lineSeparator")) {
        setLineSeparator(args[(++local_i)]);
      } else if (args[local_i].equals("-trimText")) {
        setTrimText(true);
      } else if (args[local_i].equals("-padText")) {
        setPadText(true);
      } else if (args[local_i].startsWith("-xhtml")) {
        setXHTML(true);
      } else {
        return local_i;
      }
      local_i++;
    }
    return local_i;
  }
  
  public static OutputFormat createPrettyPrint()
  {
    OutputFormat format = new OutputFormat();
    format.setIndentSize(2);
    format.setNewlines(true);
    format.setTrimText(true);
    format.setPadText(true);
    return format;
  }
  
  public static OutputFormat createCompactFormat()
  {
    OutputFormat format = new OutputFormat();
    format.setIndent(false);
    format.setNewlines(false);
    format.setTrimText(true);
    return format;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.OutputFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */