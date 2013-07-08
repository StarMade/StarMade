/*   1:    */package org.jaxen.saxpath;
/*   2:    */
/*  17:    */public class XPathSyntaxException
/*  18:    */  extends SAXPathException
/*  19:    */{
/*  20:    */  private static final long serialVersionUID = 3567675610742422397L;
/*  21:    */  
/*  35:    */  private String xpath;
/*  36:    */  
/*  50:    */  private int position;
/*  51:    */  
/*  65: 65 */  private static final String lineSeparator = System.getProperty("line.separator");
/*  66:    */  
/*  76:    */  public XPathSyntaxException(String xpath, int position, String message)
/*  77:    */  {
/*  78: 78 */    super(message);
/*  79: 79 */    this.position = position;
/*  80: 80 */    this.xpath = xpath;
/*  81:    */  }
/*  82:    */  
/*  92:    */  public int getPosition()
/*  93:    */  {
/*  94: 94 */    return this.position;
/*  95:    */  }
/*  96:    */  
/* 104:    */  public String getXPath()
/* 105:    */  {
/* 106:106 */    return this.xpath;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public String toString()
/* 110:    */  {
/* 111:111 */    return getClass() + ": " + getXPath() + ": " + getPosition() + ": " + getMessage();
/* 112:    */  }
/* 113:    */  
/* 123:    */  private String getPositionMarker()
/* 124:    */  {
/* 125:125 */    int pos = getPosition();
/* 126:126 */    StringBuffer buf = new StringBuffer(pos + 1);
/* 127:127 */    for (int i = 0; i < pos; i++)
/* 128:    */    {
/* 129:129 */      buf.append(" ");
/* 130:    */    }
/* 131:    */    
/* 132:132 */    buf.append("^");
/* 133:    */    
/* 134:134 */    return buf.toString();
/* 135:    */  }
/* 136:    */  
/* 146:    */  public String getMultilineMessage()
/* 147:    */  {
/* 148:148 */    StringBuffer buf = new StringBuffer();
/* 149:    */    
/* 150:150 */    buf.append(getMessage());
/* 151:151 */    buf.append(lineSeparator);
/* 152:152 */    buf.append(getXPath());
/* 153:153 */    buf.append(lineSeparator);
/* 154:    */    
/* 155:155 */    buf.append(getPositionMarker());
/* 156:    */    
/* 157:157 */    return buf.toString();
/* 158:    */  }
/* 159:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.XPathSyntaxException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */