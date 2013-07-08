package org.w3c.tidy;

public class Dict
{
  public static final int CM_UNKNOWN = 0;
  public static final int CM_EMPTY = 1;
  public static final int CM_HTML = 2;
  public static final int CM_HEAD = 4;
  public static final int CM_BLOCK = 8;
  public static final int CM_INLINE = 16;
  public static final int CM_LIST = 32;
  public static final int CM_DEFLIST = 64;
  public static final int CM_TABLE = 128;
  public static final int CM_ROWGRP = 256;
  public static final int CM_ROW = 512;
  public static final int CM_FIELD = 1024;
  public static final int CM_OBJECT = 2048;
  public static final int CM_PARAM = 4096;
  public static final int CM_FRAMES = 8192;
  public static final int CM_HEADING = 16384;
  public static final int CM_OPT = 32768;
  public static final int CM_IMG = 65536;
  public static final int CM_MIXED = 131072;
  public static final int CM_NO_INDENT = 262144;
  public static final int CM_OBSOLETE = 524288;
  public static final int CM_NEW = 1048576;
  public static final int CM_OMITST = 2097152;
  public static final short VERS_UNKNOWN = 0;
  public static final short VERS_HTML20 = 1;
  public static final short VERS_HTML32 = 2;
  public static final short VERS_HTML40_STRICT = 4;
  public static final short VERS_HTML40_LOOSE = 8;
  public static final short VERS_FRAMESET = 16;
  public static final short VERS_XML = 32;
  public static final short VERS_NETSCAPE = 64;
  public static final short VERS_MICROSOFT = 128;
  public static final short VERS_SUN = 256;
  public static final short VERS_MALFORMED = 512;
  public static final short VERS_XHTML11 = 1024;
  public static final short VERS_BASIC = 2048;
  public static final short VERS_PROPRIETARY = 448;
  public static final short VERS_HTML40 = 28;
  public static final short VERS_LOOSE = 26;
  public static final short VERS_IFRAME = 24;
  public static final short VERS_FROM32 = 30;
  public static final short VERS_EVENTS = 1052;
  public static final short VERS_ALL = 3103;
  public static final short TAGTYPE_EMPTY = 1;
  public static final short TAGTYPE_INLINE = 2;
  public static final short TAGTYPE_BLOCK = 4;
  public static final short TAGTYPE_PRE = 8;
  protected String name;
  protected short versions;
  protected int model;
  private Parser parser;
  private TagCheck chkattrs;
  
  public Dict(String paramString, short paramShort, int paramInt, Parser paramParser, TagCheck paramTagCheck)
  {
    this.name = paramString;
    this.versions = paramShort;
    this.model = paramInt;
    this.parser = paramParser;
    this.chkattrs = paramTagCheck;
  }
  
  public TagCheck getChkattrs()
  {
    return this.chkattrs;
  }
  
  public int getModel()
  {
    return this.model;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Parser getParser()
  {
    return this.parser;
  }
  
  public void setChkattrs(TagCheck paramTagCheck)
  {
    this.chkattrs = paramTagCheck;
  }
  
  public short getVersions()
  {
    return this.versions;
  }
  
  public void setParser(Parser paramParser)
  {
    this.parser = paramParser;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.Dict
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */