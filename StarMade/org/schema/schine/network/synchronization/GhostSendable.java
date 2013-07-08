/*  1:   */package org.schema.schine.network.synchronization;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.Sendable;
/*  4:   */
/*  8:   */public class GhostSendable
/*  9:   */{
/* 10:   */  public final long timeDeleted;
/* 11:   */  public final Sendable sendable;
/* 12:   */  
/* 13:   */  public GhostSendable(long paramLong, Sendable paramSendable)
/* 14:   */  {
/* 15:15 */    this.timeDeleted = paramLong;
/* 16:16 */    this.sendable = paramSendable;
/* 17:   */  }
/* 18:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.synchronization.GhostSendable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */