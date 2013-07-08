/*   1:    */package org.apache.commons.lang3.builder;
/*   2:    */
/*  26:    */public class StandardToStringStyle
/*  27:    */  extends ToStringStyle
/*  28:    */{
/*  29:    */  private static final long serialVersionUID = 1L;
/*  30:    */  
/*  54:    */  public boolean isUseClassName()
/*  55:    */  {
/*  56: 56 */    return super.isUseClassName();
/*  57:    */  }
/*  58:    */  
/*  64:    */  public void setUseClassName(boolean useClassName)
/*  65:    */  {
/*  66: 66 */    super.setUseClassName(useClassName);
/*  67:    */  }
/*  68:    */  
/*  77:    */  public boolean isUseShortClassName()
/*  78:    */  {
/*  79: 79 */    return super.isUseShortClassName();
/*  80:    */  }
/*  81:    */  
/*  88:    */  public void setUseShortClassName(boolean useShortClassName)
/*  89:    */  {
/*  90: 90 */    super.setUseShortClassName(useShortClassName);
/*  91:    */  }
/*  92:    */  
/*  99:    */  public boolean isUseIdentityHashCode()
/* 100:    */  {
/* 101:101 */    return super.isUseIdentityHashCode();
/* 102:    */  }
/* 103:    */  
/* 109:    */  public void setUseIdentityHashCode(boolean useIdentityHashCode)
/* 110:    */  {
/* 111:111 */    super.setUseIdentityHashCode(useIdentityHashCode);
/* 112:    */  }
/* 113:    */  
/* 121:    */  public boolean isUseFieldNames()
/* 122:    */  {
/* 123:123 */    return super.isUseFieldNames();
/* 124:    */  }
/* 125:    */  
/* 131:    */  public void setUseFieldNames(boolean useFieldNames)
/* 132:    */  {
/* 133:133 */    super.setUseFieldNames(useFieldNames);
/* 134:    */  }
/* 135:    */  
/* 144:    */  public boolean isDefaultFullDetail()
/* 145:    */  {
/* 146:146 */    return super.isDefaultFullDetail();
/* 147:    */  }
/* 148:    */  
/* 155:    */  public void setDefaultFullDetail(boolean defaultFullDetail)
/* 156:    */  {
/* 157:157 */    super.setDefaultFullDetail(defaultFullDetail);
/* 158:    */  }
/* 159:    */  
/* 167:    */  public boolean isArrayContentDetail()
/* 168:    */  {
/* 169:169 */    return super.isArrayContentDetail();
/* 170:    */  }
/* 171:    */  
/* 177:    */  public void setArrayContentDetail(boolean arrayContentDetail)
/* 178:    */  {
/* 179:179 */    super.setArrayContentDetail(arrayContentDetail);
/* 180:    */  }
/* 181:    */  
/* 189:    */  public String getArrayStart()
/* 190:    */  {
/* 191:191 */    return super.getArrayStart();
/* 192:    */  }
/* 193:    */  
/* 202:    */  public void setArrayStart(String arrayStart)
/* 203:    */  {
/* 204:204 */    super.setArrayStart(arrayStart);
/* 205:    */  }
/* 206:    */  
/* 214:    */  public String getArrayEnd()
/* 215:    */  {
/* 216:216 */    return super.getArrayEnd();
/* 217:    */  }
/* 218:    */  
/* 227:    */  public void setArrayEnd(String arrayEnd)
/* 228:    */  {
/* 229:229 */    super.setArrayEnd(arrayEnd);
/* 230:    */  }
/* 231:    */  
/* 239:    */  public String getArraySeparator()
/* 240:    */  {
/* 241:241 */    return super.getArraySeparator();
/* 242:    */  }
/* 243:    */  
/* 252:    */  public void setArraySeparator(String arraySeparator)
/* 253:    */  {
/* 254:254 */    super.setArraySeparator(arraySeparator);
/* 255:    */  }
/* 256:    */  
/* 264:    */  public String getContentStart()
/* 265:    */  {
/* 266:266 */    return super.getContentStart();
/* 267:    */  }
/* 268:    */  
/* 277:    */  public void setContentStart(String contentStart)
/* 278:    */  {
/* 279:279 */    super.setContentStart(contentStart);
/* 280:    */  }
/* 281:    */  
/* 289:    */  public String getContentEnd()
/* 290:    */  {
/* 291:291 */    return super.getContentEnd();
/* 292:    */  }
/* 293:    */  
/* 302:    */  public void setContentEnd(String contentEnd)
/* 303:    */  {
/* 304:304 */    super.setContentEnd(contentEnd);
/* 305:    */  }
/* 306:    */  
/* 314:    */  public String getFieldNameValueSeparator()
/* 315:    */  {
/* 316:316 */    return super.getFieldNameValueSeparator();
/* 317:    */  }
/* 318:    */  
/* 327:    */  public void setFieldNameValueSeparator(String fieldNameValueSeparator)
/* 328:    */  {
/* 329:329 */    super.setFieldNameValueSeparator(fieldNameValueSeparator);
/* 330:    */  }
/* 331:    */  
/* 339:    */  public String getFieldSeparator()
/* 340:    */  {
/* 341:341 */    return super.getFieldSeparator();
/* 342:    */  }
/* 343:    */  
/* 352:    */  public void setFieldSeparator(String fieldSeparator)
/* 353:    */  {
/* 354:354 */    super.setFieldSeparator(fieldSeparator);
/* 355:    */  }
/* 356:    */  
/* 366:    */  public boolean isFieldSeparatorAtStart()
/* 367:    */  {
/* 368:368 */    return super.isFieldSeparatorAtStart();
/* 369:    */  }
/* 370:    */  
/* 378:    */  public void setFieldSeparatorAtStart(boolean fieldSeparatorAtStart)
/* 379:    */  {
/* 380:380 */    super.setFieldSeparatorAtStart(fieldSeparatorAtStart);
/* 381:    */  }
/* 382:    */  
/* 392:    */  public boolean isFieldSeparatorAtEnd()
/* 393:    */  {
/* 394:394 */    return super.isFieldSeparatorAtEnd();
/* 395:    */  }
/* 396:    */  
/* 404:    */  public void setFieldSeparatorAtEnd(boolean fieldSeparatorAtEnd)
/* 405:    */  {
/* 406:406 */    super.setFieldSeparatorAtEnd(fieldSeparatorAtEnd);
/* 407:    */  }
/* 408:    */  
/* 416:    */  public String getNullText()
/* 417:    */  {
/* 418:418 */    return super.getNullText();
/* 419:    */  }
/* 420:    */  
/* 429:    */  public void setNullText(String nullText)
/* 430:    */  {
/* 431:431 */    super.setNullText(nullText);
/* 432:    */  }
/* 433:    */  
/* 444:    */  public String getSizeStartText()
/* 445:    */  {
/* 446:446 */    return super.getSizeStartText();
/* 447:    */  }
/* 448:    */  
/* 460:    */  public void setSizeStartText(String sizeStartText)
/* 461:    */  {
/* 462:462 */    super.setSizeStartText(sizeStartText);
/* 463:    */  }
/* 464:    */  
/* 475:    */  public String getSizeEndText()
/* 476:    */  {
/* 477:477 */    return super.getSizeEndText();
/* 478:    */  }
/* 479:    */  
/* 491:    */  public void setSizeEndText(String sizeEndText)
/* 492:    */  {
/* 493:493 */    super.setSizeEndText(sizeEndText);
/* 494:    */  }
/* 495:    */  
/* 506:    */  public String getSummaryObjectStartText()
/* 507:    */  {
/* 508:508 */    return super.getSummaryObjectStartText();
/* 509:    */  }
/* 510:    */  
/* 522:    */  public void setSummaryObjectStartText(String summaryObjectStartText)
/* 523:    */  {
/* 524:524 */    super.setSummaryObjectStartText(summaryObjectStartText);
/* 525:    */  }
/* 526:    */  
/* 537:    */  public String getSummaryObjectEndText()
/* 538:    */  {
/* 539:539 */    return super.getSummaryObjectEndText();
/* 540:    */  }
/* 541:    */  
/* 553:    */  public void setSummaryObjectEndText(String summaryObjectEndText)
/* 554:    */  {
/* 555:555 */    super.setSummaryObjectEndText(summaryObjectEndText);
/* 556:    */  }
/* 557:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.StandardToStringStyle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */