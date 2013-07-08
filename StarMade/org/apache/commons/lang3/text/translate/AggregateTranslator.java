/*  1:   */package org.apache.commons.lang3.text.translate;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */import org.apache.commons.lang3.ArrayUtils;
/*  6:   */
/* 34:   */public class AggregateTranslator
/* 35:   */  extends CharSequenceTranslator
/* 36:   */{
/* 37:   */  private final CharSequenceTranslator[] translators;
/* 38:   */  
/* 39:   */  public AggregateTranslator(CharSequenceTranslator... translators)
/* 40:   */  {
/* 41:41 */    this.translators = ((CharSequenceTranslator[])ArrayUtils.clone(translators));
/* 42:   */  }
/* 43:   */  
/* 48:   */  public int translate(CharSequence input, int index, Writer out)
/* 49:   */    throws IOException
/* 50:   */  {
/* 51:51 */    for (CharSequenceTranslator translator : this.translators) {
/* 52:52 */      int consumed = translator.translate(input, index, out);
/* 53:53 */      if (consumed != 0) {
/* 54:54 */        return consumed;
/* 55:   */      }
/* 56:   */    }
/* 57:57 */    return 0;
/* 58:   */  }
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.AggregateTranslator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */