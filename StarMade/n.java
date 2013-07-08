/*   1:    */
/* 366:    */public final class n
/* 367:    */{
/* 368:    */  public float a;
/* 369:    */  public float b;
/* 370:    */  public float c;
/* 371:    */  
/* 372:    */  public n() {}
/* 373:    */  
/* 374:    */  public n(float paramFloat1, float paramFloat2, float paramFloat3)
/* 375:    */  {
/* 376:376 */    this.a = paramFloat1;
/* 377:377 */    this.b = paramFloat2;
/* 378:378 */    this.c = paramFloat3;
/* 379:    */  }
/* 380:    */  
/* 453:    */  public final boolean equals(Object paramObject)
/* 454:    */  {
/* 455:455 */    if ((paramObject instanceof n)) {
/* 456:456 */      paramObject = (n)paramObject;
/* 457:457 */      return (this.a == paramObject.a) && (this.b == paramObject.b) && (this.c == paramObject.c);
/* 458:    */    }
/* 459:459 */    return false;
/* 460:    */  }
/* 461:    */  
/* 726:    */  public final String toString()
/* 727:    */  {
/* 728:728 */    return "[" + this.a + ", " + this.b + ", " + this.c + "]";
/* 729:    */  }
/* 730:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     n
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */