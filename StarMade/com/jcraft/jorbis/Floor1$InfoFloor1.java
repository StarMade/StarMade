package com.jcraft.jorbis;

class Floor1$InfoFloor1
{
  static final int VIF_POSIT = 63;
  static final int VIF_CLASS = 16;
  static final int VIF_PARTS = 31;
  int partitions;
  int[] partitionclass = new int[31];
  int[] class_dim = new int[16];
  int[] class_subs = new int[16];
  int[] class_book = new int[16];
  int[][] class_subbook = new int[16][];
  int mult;
  int[] postlist = new int[65];
  float maxover;
  float maxunder;
  float maxerr;
  int twofitminsize;
  int twofitminused;
  int twofitweight;
  float twofitatten;
  int unusedminsize;
  int unusedmin_n;
  int field_1770;
  
  Floor1$InfoFloor1(Floor1 arg1)
  {
    for (int local_i = 0; local_i < this.class_subbook.length; local_i++) {
      this.class_subbook[local_i] = new int[8];
    }
  }
  
  void free()
  {
    this.partitionclass = null;
    this.class_dim = null;
    this.class_subs = null;
    this.class_book = null;
    this.class_subbook = ((int[][])null);
    this.postlist = null;
  }
  
  Object copy_info()
  {
    InfoFloor1 info = this;
    InfoFloor1 ret = new InfoFloor1(this.this$0);
    ret.partitions = info.partitions;
    System.arraycopy(info.partitionclass, 0, ret.partitionclass, 0, 31);
    System.arraycopy(info.class_dim, 0, ret.class_dim, 0, 16);
    System.arraycopy(info.class_subs, 0, ret.class_subs, 0, 16);
    System.arraycopy(info.class_book, 0, ret.class_book, 0, 16);
    for (int local_j = 0; local_j < 16; local_j++) {
      System.arraycopy(info.class_subbook[local_j], 0, ret.class_subbook[local_j], 0, 8);
    }
    ret.mult = info.mult;
    System.arraycopy(info.postlist, 0, ret.postlist, 0, 65);
    ret.maxover = info.maxover;
    ret.maxunder = info.maxunder;
    ret.maxerr = info.maxerr;
    ret.twofitminsize = info.twofitminsize;
    ret.twofitminused = info.twofitminused;
    ret.twofitweight = info.twofitweight;
    ret.twofitatten = info.twofitatten;
    ret.unusedminsize = info.unusedminsize;
    ret.unusedmin_n = info.unusedmin_n;
    ret.field_1770 = info.field_1770;
    return ret;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Floor1.InfoFloor1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */