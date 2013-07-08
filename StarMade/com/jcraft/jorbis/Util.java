/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/*  3:   */class Util {
/*  4:   */  static int ilog(int v) {
/*  5: 5 */    int ret = 0;
/*  6: 6 */    while (v != 0) {
/*  7: 7 */      ret++;
/*  8: 8 */      v >>>= 1;
/*  9:   */    }
/* 10:10 */    return ret;
/* 11:   */  }
/* 12:   */  
/* 13:   */  static int ilog2(int v) {
/* 14:14 */    int ret = 0;
/* 15:15 */    while (v > 1) {
/* 16:16 */      ret++;
/* 17:17 */      v >>>= 1;
/* 18:   */    }
/* 19:19 */    return ret;
/* 20:   */  }
/* 21:   */  
/* 22:   */  static int icount(int v) {
/* 23:23 */    int ret = 0;
/* 24:24 */    while (v != 0) {
/* 25:25 */      ret += (v & 0x1);
/* 26:26 */      v >>>= 1;
/* 27:   */    }
/* 28:28 */    return ret;
/* 29:   */  }
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */