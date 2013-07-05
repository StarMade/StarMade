/*     */ package org.apache.commons.lang3.builder;
/*     */ 
/*     */ public class StandardToStringStyle extends ToStringStyle
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public boolean isUseClassName()
/*     */   {
/*  56 */     return super.isUseClassName();
/*     */   }
/*     */ 
/*     */   public void setUseClassName(boolean useClassName)
/*     */   {
/*  66 */     super.setUseClassName(useClassName);
/*     */   }
/*     */ 
/*     */   public boolean isUseShortClassName()
/*     */   {
/*  79 */     return super.isUseShortClassName();
/*     */   }
/*     */ 
/*     */   public void setUseShortClassName(boolean useShortClassName)
/*     */   {
/*  90 */     super.setUseShortClassName(useShortClassName);
/*     */   }
/*     */ 
/*     */   public boolean isUseIdentityHashCode()
/*     */   {
/* 101 */     return super.isUseIdentityHashCode();
/*     */   }
/*     */ 
/*     */   public void setUseIdentityHashCode(boolean useIdentityHashCode)
/*     */   {
/* 111 */     super.setUseIdentityHashCode(useIdentityHashCode);
/*     */   }
/*     */ 
/*     */   public boolean isUseFieldNames()
/*     */   {
/* 123 */     return super.isUseFieldNames();
/*     */   }
/*     */ 
/*     */   public void setUseFieldNames(boolean useFieldNames)
/*     */   {
/* 133 */     super.setUseFieldNames(useFieldNames);
/*     */   }
/*     */ 
/*     */   public boolean isDefaultFullDetail()
/*     */   {
/* 146 */     return super.isDefaultFullDetail();
/*     */   }
/*     */ 
/*     */   public void setDefaultFullDetail(boolean defaultFullDetail)
/*     */   {
/* 157 */     super.setDefaultFullDetail(defaultFullDetail);
/*     */   }
/*     */ 
/*     */   public boolean isArrayContentDetail()
/*     */   {
/* 169 */     return super.isArrayContentDetail();
/*     */   }
/*     */ 
/*     */   public void setArrayContentDetail(boolean arrayContentDetail)
/*     */   {
/* 179 */     super.setArrayContentDetail(arrayContentDetail);
/*     */   }
/*     */ 
/*     */   public String getArrayStart()
/*     */   {
/* 191 */     return super.getArrayStart();
/*     */   }
/*     */ 
/*     */   public void setArrayStart(String arrayStart)
/*     */   {
/* 204 */     super.setArrayStart(arrayStart);
/*     */   }
/*     */ 
/*     */   public String getArrayEnd()
/*     */   {
/* 216 */     return super.getArrayEnd();
/*     */   }
/*     */ 
/*     */   public void setArrayEnd(String arrayEnd)
/*     */   {
/* 229 */     super.setArrayEnd(arrayEnd);
/*     */   }
/*     */ 
/*     */   public String getArraySeparator()
/*     */   {
/* 241 */     return super.getArraySeparator();
/*     */   }
/*     */ 
/*     */   public void setArraySeparator(String arraySeparator)
/*     */   {
/* 254 */     super.setArraySeparator(arraySeparator);
/*     */   }
/*     */ 
/*     */   public String getContentStart()
/*     */   {
/* 266 */     return super.getContentStart();
/*     */   }
/*     */ 
/*     */   public void setContentStart(String contentStart)
/*     */   {
/* 279 */     super.setContentStart(contentStart);
/*     */   }
/*     */ 
/*     */   public String getContentEnd()
/*     */   {
/* 291 */     return super.getContentEnd();
/*     */   }
/*     */ 
/*     */   public void setContentEnd(String contentEnd)
/*     */   {
/* 304 */     super.setContentEnd(contentEnd);
/*     */   }
/*     */ 
/*     */   public String getFieldNameValueSeparator()
/*     */   {
/* 316 */     return super.getFieldNameValueSeparator();
/*     */   }
/*     */ 
/*     */   public void setFieldNameValueSeparator(String fieldNameValueSeparator)
/*     */   {
/* 329 */     super.setFieldNameValueSeparator(fieldNameValueSeparator);
/*     */   }
/*     */ 
/*     */   public String getFieldSeparator()
/*     */   {
/* 341 */     return super.getFieldSeparator();
/*     */   }
/*     */ 
/*     */   public void setFieldSeparator(String fieldSeparator)
/*     */   {
/* 354 */     super.setFieldSeparator(fieldSeparator);
/*     */   }
/*     */ 
/*     */   public boolean isFieldSeparatorAtStart()
/*     */   {
/* 368 */     return super.isFieldSeparatorAtStart();
/*     */   }
/*     */ 
/*     */   public void setFieldSeparatorAtStart(boolean fieldSeparatorAtStart)
/*     */   {
/* 380 */     super.setFieldSeparatorAtStart(fieldSeparatorAtStart);
/*     */   }
/*     */ 
/*     */   public boolean isFieldSeparatorAtEnd()
/*     */   {
/* 394 */     return super.isFieldSeparatorAtEnd();
/*     */   }
/*     */ 
/*     */   public void setFieldSeparatorAtEnd(boolean fieldSeparatorAtEnd)
/*     */   {
/* 406 */     super.setFieldSeparatorAtEnd(fieldSeparatorAtEnd);
/*     */   }
/*     */ 
/*     */   public String getNullText()
/*     */   {
/* 418 */     return super.getNullText();
/*     */   }
/*     */ 
/*     */   public void setNullText(String nullText)
/*     */   {
/* 431 */     super.setNullText(nullText);
/*     */   }
/*     */ 
/*     */   public String getSizeStartText()
/*     */   {
/* 446 */     return super.getSizeStartText();
/*     */   }
/*     */ 
/*     */   public void setSizeStartText(String sizeStartText)
/*     */   {
/* 462 */     super.setSizeStartText(sizeStartText);
/*     */   }
/*     */ 
/*     */   public String getSizeEndText()
/*     */   {
/* 477 */     return super.getSizeEndText();
/*     */   }
/*     */ 
/*     */   public void setSizeEndText(String sizeEndText)
/*     */   {
/* 493 */     super.setSizeEndText(sizeEndText);
/*     */   }
/*     */ 
/*     */   public String getSummaryObjectStartText()
/*     */   {
/* 508 */     return super.getSummaryObjectStartText();
/*     */   }
/*     */ 
/*     */   public void setSummaryObjectStartText(String summaryObjectStartText)
/*     */   {
/* 524 */     super.setSummaryObjectStartText(summaryObjectStartText);
/*     */   }
/*     */ 
/*     */   public String getSummaryObjectEndText()
/*     */   {
/* 539 */     return super.getSummaryObjectEndText();
/*     */   }
/*     */ 
/*     */   public void setSummaryObjectEndText(String summaryObjectEndText)
/*     */   {
/* 555 */     super.setSummaryObjectEndText(summaryObjectEndText);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.StandardToStringStyle
 * JD-Core Version:    0.6.2
 */