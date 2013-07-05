package org.hsqldb;

public class RowActionBase
{
  public static final byte ACTION_NONE = 0;
  public static final byte ACTION_INSERT = 1;
  public static final byte ACTION_DELETE = 2;
  public static final byte ACTION_DELETE_FINAL = 3;
  public static final byte ACTION_INSERT_DELETE = 4;
  public static final byte ACTION_REF = 5;
  public static final byte ACTION_CHECK = 6;
  public static final byte ACTION_DEBUG = 7;
  RowActionBase next;
  Session session;
  long actionTimestamp;
  long commitTimestamp;
  byte type;
  boolean deleteComplete;
  boolean rolledback;
  boolean prepared;
  int[] changeColumnMap;

  RowActionBase()
  {
  }

  RowActionBase(Session paramSession, byte paramByte)
  {
    this.session = paramSession;
    this.type = paramByte;
    this.actionTimestamp = paramSession.actionTimestamp;
  }

  void setAsAction(RowActionBase paramRowActionBase)
  {
    this.next = paramRowActionBase.next;
    this.session = paramRowActionBase.session;
    this.actionTimestamp = paramRowActionBase.actionTimestamp;
    this.commitTimestamp = paramRowActionBase.commitTimestamp;
    this.type = paramRowActionBase.type;
    this.deleteComplete = paramRowActionBase.deleteComplete;
    this.rolledback = paramRowActionBase.rolledback;
    this.prepared = paramRowActionBase.prepared;
    this.changeColumnMap = paramRowActionBase.changeColumnMap;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RowActionBase
 * JD-Core Version:    0.6.2
 */