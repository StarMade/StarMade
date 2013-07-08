/*  1:   */
/* 10:   */public final class bh
/* 11:   */{
/* 12:12 */  public long a = 117L;
/* 13:   */  
/* 25:   */  public bh(bh parambh)
/* 26:   */  {
/* 27:27 */    this.a = parambh.a;
/* 28:   */  }
/* 29:   */  
/* 41:   */  public bh() {}
/* 42:   */  
/* 53:   */  public final void a(boolean paramBoolean, long paramLong)
/* 54:   */  {
/* 55:55 */    if (paramBoolean) {
/* 56:56 */      this.a |= paramLong;return;
/* 57:   */    }
/* 58:58 */    this.a &= (paramLong ^ 0xFFFFFFFF);
/* 59:   */  }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */