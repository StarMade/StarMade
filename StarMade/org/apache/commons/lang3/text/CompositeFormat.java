/*     */ package org.apache.commons.lang3.text;
/*     */ 
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ 
/*     */ public class CompositeFormat extends Format
/*     */ {
/*     */   private static final long serialVersionUID = -4329119827877627683L;
/*     */   private final Format parser;
/*     */   private final Format formatter;
/*     */ 
/*     */   public CompositeFormat(Format parser, Format formatter)
/*     */   {
/*  53 */     this.parser = parser;
/*  54 */     this.formatter = formatter;
/*     */   }
/*     */ 
/*     */   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos)
/*     */   {
/*  69 */     return this.formatter.format(obj, toAppendTo, pos);
/*     */   }
/*     */ 
/*     */   public Object parseObject(String source, ParsePosition pos)
/*     */   {
/*  84 */     return this.parser.parseObject(source, pos);
/*     */   }
/*     */ 
/*     */   public Format getParser()
/*     */   {
/*  93 */     return this.parser;
/*     */   }
/*     */ 
/*     */   public Format getFormatter()
/*     */   {
/* 102 */     return this.formatter;
/*     */   }
/*     */ 
/*     */   public String reformat(String input)
/*     */     throws ParseException
/*     */   {
/* 113 */     return format(parseObject(input));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.CompositeFormat
 * JD-Core Version:    0.6.2
 */