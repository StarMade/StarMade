package org.w3c.tidy;

import org.w3c.dom.Attr;

public class AttVal
  implements Cloneable
{
  protected AttVal next;
  protected Attribute dict;
  protected Node asp;
  protected Node php;
  protected int delim;
  protected String attribute;
  protected String value;
  protected Attr adapter;
  
  public AttVal() {}
  
  public AttVal(AttVal paramAttVal, Attribute paramAttribute, int paramInt, String paramString1, String paramString2)
  {
    this.next = paramAttVal;
    this.dict = paramAttribute;
    this.delim = paramInt;
    this.attribute = paramString1;
    this.value = paramString2;
  }
  
  public AttVal(AttVal paramAttVal, Attribute paramAttribute, Node paramNode1, Node paramNode2, int paramInt, String paramString1, String paramString2)
  {
    this.next = paramAttVal;
    this.dict = paramAttribute;
    this.asp = paramNode1;
    this.php = paramNode2;
    this.delim = paramInt;
    this.attribute = paramString1;
    this.value = paramString2;
  }
  
  protected Object clone()
  {
    AttVal localAttVal = null;
    try
    {
      localAttVal = (AttVal)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException) {}
    if (this.next != null) {
      localAttVal.next = ((AttVal)this.next.clone());
    }
    if (this.asp != null) {
      localAttVal.asp = this.asp.cloneNode(false);
    }
    if (this.php != null) {
      localAttVal.php = this.php.cloneNode(false);
    }
    return localAttVal;
  }
  
  public boolean isBoolAttribute()
  {
    Attribute localAttribute = this.dict;
    return (localAttribute != null) && (localAttribute.getAttrchk() == AttrCheckImpl.BOOL);
  }
  
  void checkLowerCaseAttrValue(Lexer paramLexer, Node paramNode)
  {
    if (this.value == null) {
      return;
    }
    String str = this.value.toLowerCase();
    if (!this.value.equals(str))
    {
      if (paramLexer.isvoyager) {
        paramLexer.report.attrError(paramLexer, paramNode, this, (short)70);
      }
      if ((paramLexer.isvoyager) || (paramLexer.configuration.lowerLiterals)) {
        this.value = str;
      }
    }
  }
  
  public Attribute checkAttribute(Lexer paramLexer, Node paramNode)
  {
    TagTable localTagTable = paramLexer.configuration.field_1881;
    Attribute localAttribute = this.dict;
    if (localAttribute != null)
    {
      if (TidyUtils.toBoolean(localAttribute.getVersions() & 0x20))
      {
        if ((!paramLexer.configuration.xmlTags) && (!paramLexer.configuration.xmlOut)) {
          paramLexer.report.attrError(paramLexer, paramNode, this, (short)57);
        }
      }
      else if ((localAttribute != AttributeTable.attrTitle) || ((paramNode.tag != localTagTable.tagA) && (paramNode.tag != localTagTable.tagLink))) {
        paramLexer.constrainVersion(localAttribute.getVersions());
      }
      if (localAttribute.getAttrchk() != null) {
        localAttribute.getAttrchk().check(paramLexer, paramNode, this);
      } else if (TidyUtils.toBoolean(this.dict.getVersions() & 0x1C0)) {
        paramLexer.report.attrError(paramLexer, paramNode, this, (short)53);
      }
    }
    else if ((!paramLexer.configuration.xmlTags) && (paramNode.tag != null) && (this.asp == null) && ((paramNode.tag == null) || (!TidyUtils.toBoolean(paramNode.tag.versions & 0x1C0))))
    {
      paramLexer.report.attrError(paramLexer, paramNode, this, (short)48);
    }
    return localAttribute;
  }
  
  protected Attr getAdapter()
  {
    if (this.adapter == null) {
      this.adapter = new DOMAttrImpl(this);
    }
    return this.adapter;
  }
  
  public Node getAsp()
  {
    return this.asp;
  }
  
  public void setAsp(Node paramNode)
  {
    this.asp = paramNode;
  }
  
  public String getAttribute()
  {
    return this.attribute;
  }
  
  public void setAttribute(String paramString)
  {
    this.attribute = paramString;
  }
  
  public int getDelim()
  {
    return this.delim;
  }
  
  public void setDelim(int paramInt)
  {
    this.delim = paramInt;
  }
  
  public Attribute getDict()
  {
    return this.dict;
  }
  
  public void setDict(Attribute paramAttribute)
  {
    this.dict = paramAttribute;
  }
  
  public AttVal getNext()
  {
    return this.next;
  }
  
  public void setNext(AttVal paramAttVal)
  {
    this.next = paramAttVal;
  }
  
  public Node getPhp()
  {
    return this.php;
  }
  
  public void setPhp(Node paramNode)
  {
    this.php = paramNode;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public void setValue(String paramString)
  {
    this.value = paramString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.AttVal
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */