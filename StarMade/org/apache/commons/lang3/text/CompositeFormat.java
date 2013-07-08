/*   1:    */package org.apache.commons.lang3.text;
/*   2:    */
/*   3:    */import java.text.FieldPosition;
/*   4:    */import java.text.Format;
/*   5:    */import java.text.ParseException;
/*   6:    */import java.text.ParsePosition;
/*   7:    */
/*  44:    */public class CompositeFormat
/*  45:    */  extends Format
/*  46:    */{
/*  47:    */  private static final long serialVersionUID = -4329119827877627683L;
/*  48:    */  private final Format parser;
/*  49:    */  private final Format formatter;
/*  50:    */  
/*  51:    */  public CompositeFormat(Format parser, Format formatter)
/*  52:    */  {
/*  53: 53 */    this.parser = parser;
/*  54: 54 */    this.formatter = formatter;
/*  55:    */  }
/*  56:    */  
/*  67:    */  public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
/*  68:    */  {
/*  69: 69 */    return this.formatter.format(obj, toAppendTo, pos);
/*  70:    */  }
/*  71:    */  
/*  82:    */  public Object parseObject(String source, ParsePosition pos)
/*  83:    */  {
/*  84: 84 */    return this.parser.parseObject(source, pos);
/*  85:    */  }
/*  86:    */  
/*  91:    */  public Format getParser()
/*  92:    */  {
/*  93: 93 */    return this.parser;
/*  94:    */  }
/*  95:    */  
/* 100:    */  public Format getFormatter()
/* 101:    */  {
/* 102:102 */    return this.formatter;
/* 103:    */  }
/* 104:    */  
/* 110:    */  public String reformat(String input)
/* 111:    */    throws ParseException
/* 112:    */  {
/* 113:113 */    return format(parseObject(input));
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.CompositeFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */