package com.jcraft.jorbis;

class InfoFloor1
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
  int n;

  InfoFloor1()
  {
    for (int i = 0; i < this.class_subbook.length; i++)
      this.class_subbook[i] = new int[8];
  }

  void free()
  {
    this.partitionclass = null;
    this.class_dim = null;
    this.class_subs = null;
    this.class_book = null;
    this.class_subbook = null;
    this.postlist = null;
  }

  Object copy_info()
  {
    InfoFloor1 localInfoFloor11 = this;
    InfoFloor1 localInfoFloor12 = new InfoFloor1();
    localInfoFloor12.partitions = localInfoFloor11.partitions;
    System.arraycopy(localInfoFloor11.partitionclass, 0, localInfoFloor12.partitionclass, 0, 31);
    System.arraycopy(localInfoFloor11.class_dim, 0, localInfoFloor12.class_dim, 0, 16);
    System.arraycopy(localInfoFloor11.class_subs, 0, localInfoFloor12.class_subs, 0, 16);
    System.arraycopy(localInfoFloor11.class_book, 0, localInfoFloor12.class_book, 0, 16);
    for (int i = 0; i < 16; i++)
      System.arraycopy(localInfoFloor11.class_subbook[i], 0, localInfoFloor12.class_subbook[i], 0, 8);
    localInfoFloor12.mult = localInfoFloor11.mult;
    System.arraycopy(localInfoFloor11.postlist, 0, localInfoFloor12.postlist, 0, 65);
    localInfoFloor12.maxover = localInfoFloor11.maxover;
    localInfoFloor12.maxunder = localInfoFloor11.maxunder;
    localInfoFloor12.maxerr = localInfoFloor11.maxerr;
    localInfoFloor12.twofitminsize = localInfoFloor11.twofitminsize;
    localInfoFloor12.twofitminused = localInfoFloor11.twofitminused;
    localInfoFloor12.twofitweight = localInfoFloor11.twofitweight;
    localInfoFloor12.twofitatten = localInfoFloor11.twofitatten;
    localInfoFloor12.unusedminsize = localInfoFloor11.unusedminsize;
    localInfoFloor12.unusedmin_n = localInfoFloor11.unusedmin_n;
    localInfoFloor12.n = localInfoFloor11.n;
    return localInfoFloor12;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.InfoFloor1
 * JD-Core Version:    0.6.2
 */