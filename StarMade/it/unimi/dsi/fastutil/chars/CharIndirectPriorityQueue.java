package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.IndirectPriorityQueue;

public abstract interface CharIndirectPriorityQueue
  extends IndirectPriorityQueue<Character>
{
  public abstract CharComparator comparator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */