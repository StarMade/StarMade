package org.hsqldb.result;

public abstract interface ResultConstants
{
  public static final int HSQL_API_BASE = 0;
  public static final int NONE = 0;
  public static final int UPDATECOUNT = 1;
  public static final int ERROR = 2;
  public static final int DATA = 3;
  public static final int PREPARE_ACK = 4;
  public static final int SETSESSIONATTR = 6;
  public static final int GETSESSIONATTR = 7;
  public static final int BATCHEXECDIRECT = 8;
  public static final int BATCHEXECUTE = 9;
  public static final int RESETSESSION = 10;
  public static final int CONNECTACKNOWLEDGE = 11;
  public static final int PREPARECOMMIT = 12;
  public static final int REQUESTDATA = 13;
  public static final int DATAROWS = 14;
  public static final int DATAHEAD = 15;
  public static final int BATCHEXECRESPONSE = 16;
  public static final int PARAM_METADATA = 17;
  public static final int LARGE_OBJECT_OP = 18;
  public static final int WARNING = 19;
  public static final int GENERATED = 20;
  public static final int EXECUTE_INVALID = 21;
  public static final int CONNECT = 31;
  public static final int DISCONNECT = 32;
  public static final int ENDTRAN = 33;
  public static final int EXECDIRECT = 34;
  public static final int EXECUTE = 35;
  public static final int FREESTMT = 36;
  public static final int PREPARE = 37;
  public static final int SETCONNECTATTR = 38;
  public static final int STARTTRAN = 39;
  public static final int CLOSE_RESULT = 40;
  public static final int UPDATE_RESULT = 41;
  public static final int VALUE = 42;
  public static final int CALL_RESPONSE = 43;
  public static final int CHANGE_SET = 44;
  public static final int MODE_UPPER_LIMIT = 48;
  public static final int TX_COMMIT = 0;
  public static final int TX_ROLLBACK = 1;
  public static final int TX_SAVEPOINT_NAME_ROLLBACK = 2;
  public static final int TX_SAVEPOINT_NAME_RELEASE = 4;
  public static final int TX_COMMIT_AND_CHAIN = 6;
  public static final int TX_ROLLBACK_AND_CHAIN = 7;
  public static final int UPDATE_CURSOR = 81;
  public static final int DELETE_CURSOR = 18;
  public static final int INSERT_CURSOR = 50;
  public static final int SQL_ATTR_SAVEPOINT_NAME = 10027;
  public static final int EXECUTE_FAILED = -3;
  public static final int SUCCESS_NO_INFO = -2;
  public static final int SQL_ASENSITIVE = 0;
  public static final int SQL_INSENSITIVE = 1;
  public static final int SQL_SENSITIVE = 2;
  public static final int SQL_NONSCROLLABLE = 0;
  public static final int SQL_SCROLLABLE = 1;
  public static final int SQL_NONHOLDABLE = 0;
  public static final int SQL_HOLDABLE = 1;
  public static final int SQL_WITHOUT_RETURN = 0;
  public static final int SQL_WITH_RETURN = 1;
  public static final int SQL_NOT_UPDATABLE = 0;
  public static final int SQL_UPDATABLE = 1;
  public static final int TYPE_FORWARD_ONLY = 1003;
  public static final int TYPE_SCROLL_INSENSITIVE = 1004;
  public static final int TYPE_SCROLL_SENSITIVE = 1005;
  public static final int CONCUR_READ_ONLY = 1007;
  public static final int CONCUR_UPDATABLE = 1008;
  public static final int HOLD_CURSORS_OVER_COMMIT = 1;
  public static final int CLOSE_CURSORS_AT_COMMIT = 2;
  public static final int RETURN_GENERATED_KEYS = 1;
  public static final int RETURN_NO_GENERATED_KEYS = 2;
  public static final int RETURN_GENERATED_KEYS_COL_NAMES = 11;
  public static final int RETURN_GENERATED_KEYS_COL_INDEXES = 21;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.result.ResultConstants
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */