/*   1:    */package org.jaxen;
/*   2:    */
/*  20:    */public class XPathSyntaxException
/*  21:    */  extends JaxenException
/*  22:    */{
/*  23:    */  private static final long serialVersionUID = 1980601567207604059L;
/*  24:    */  
/*  40:    */  private String xpath;
/*  41:    */  
/*  57:    */  private int position;
/*  58:    */  
/*  75:    */  public XPathSyntaxException(org.jaxen.saxpath.XPathSyntaxException e)
/*  76:    */  {
/*  77: 77 */    super(e);
/*  78:    */    
/*  79: 79 */    this.xpath = e.getXPath();
/*  80: 80 */    this.position = e.getPosition();
/*  81:    */  }
/*  82:    */  
/*  91:    */  public XPathSyntaxException(String xpath, int position, String message)
/*  92:    */  {
/*  93: 93 */    super(message);
/*  94:    */    
/*  95: 95 */    this.xpath = xpath;
/*  96: 96 */    this.position = position;
/*  97:    */  }
/*  98:    */  
/* 103:    */  public int getPosition()
/* 104:    */  {
/* 105:105 */    return this.position;
/* 106:    */  }
/* 107:    */  
/* 112:    */  public String getXPath()
/* 113:    */  {
/* 114:114 */    return this.xpath;
/* 115:    */  }
/* 116:    */  
/* 129:    */  public String getPositionMarker()
/* 130:    */  {
/* 131:131 */    StringBuffer buf = new StringBuffer();
/* 132:    */    
/* 133:133 */    int pos = getPosition();
/* 134:    */    
/* 135:135 */    for (int i = 0; i < pos; i++)
/* 136:    */    {
/* 137:137 */      buf.append(" ");
/* 138:    */    }
/* 139:    */    
/* 140:140 */    buf.append("^");
/* 141:    */    
/* 142:142 */    return buf.toString();
/* 143:    */  }
/* 144:    */  
/* 157:    */  public String getMultilineMessage()
/* 158:    */  {
/* 159:159 */    StringBuffer buf = new StringBuffer(getMessage());
/* 160:160 */    buf.append("\n");
/* 161:161 */    buf.append(getXPath());
/* 162:162 */    buf.append("\n");
/* 163:    */    
/* 164:164 */    buf.append(getPositionMarker());
/* 165:    */    
/* 166:166 */    return buf.toString();
/* 167:    */  }
/* 168:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.XPathSyntaxException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */