package org.hsqldb;

import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Charset;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

public class TypeInvariants
{
  public static final Charset SQL_TEXT;
  public static final Charset SQL_IDENTIFIER_CHARSET;
  public static final Charset SQL_CHARACTER;
  public static final Charset ASCII_GRAPHIC;
  public static final Charset GRAPHIC_IRV;
  public static final Charset ASCII_FULL;
  public static final Charset ISO8BIT;
  public static final Charset LATIN1;
  public static final Charset UTF32;
  public static final Charset UTF16;
  public static final Charset UTF8;
  public static final Type CARDINAL_NUMBER;
  public static final Type YES_OR_NO;
  public static final Type CHARACTER_DATA;
  public static final Type SQL_IDENTIFIER;
  public static final Type TIME_STAMP;
  public static final Type SQL_VARCHAR = Type.SQL_VARCHAR;
  
  static
  {
    HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_TEXT", false, 14);
    SQL_TEXT = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_IDENTIFIER", false, 14);
    SQL_IDENTIFIER_CHARSET = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_CHARACTER", false, 14);
    SQL_CHARACTER = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("LATIN1", false, 14);
    LATIN1 = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("ASCII_GRAPHIC", false, 14);
    ASCII_GRAPHIC = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("GRAPHIC_IRV", false, 14);
    GRAPHIC_IRV = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("ASCII_FULL", false, 14);
    ASCII_FULL = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("ISO8BIT", false, 14);
    ISO8BIT = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("UTF32", false, 14);
    UTF32 = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("UTF16", false, 14);
    UTF16 = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("UTF8", false, 14);
    UTF8 = new Charset(localHsqlName);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("CARDINAL_NUMBER", false, 13);
    CARDINAL_NUMBER = new NumberType(25, 0L, 0);
    CARDINAL_NUMBER.userTypeModifier = new UserTypeModifier(localHsqlName, 13, CARDINAL_NUMBER);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("YES_OR_NO", false, 13);
    YES_OR_NO = new CharacterType(12, 3L);
    YES_OR_NO.userTypeModifier = new UserTypeModifier(localHsqlName, 13, YES_OR_NO);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("CHARACTER_DATA", false, 13);
    CHARACTER_DATA = new CharacterType(12, 65536L);
    CHARACTER_DATA.userTypeModifier = new UserTypeModifier(localHsqlName, 13, CHARACTER_DATA);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_IDENTIFIER", false, 13);
    SQL_IDENTIFIER = new CharacterType(12, 128L);
    SQL_IDENTIFIER.userTypeModifier = new UserTypeModifier(localHsqlName, 13, SQL_IDENTIFIER);
    localHsqlName = HsqlNameManager.newInfoSchemaObjectName("TIME_STAMP", false, 13);
    TIME_STAMP = new DateTimeType(93, 93, 6);
    TIME_STAMP.userTypeModifier = new UserTypeModifier(localHsqlName, 13, TIME_STAMP);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.TypeInvariants
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */