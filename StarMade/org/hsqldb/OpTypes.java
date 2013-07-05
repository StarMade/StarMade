package org.hsqldb;

public abstract interface OpTypes
{
  public static final int NONE = 0;
  public static final int VALUE = 1;
  public static final int COLUMN = 2;
  public static final int COALESCE = 3;
  public static final int DEFAULT = 4;
  public static final int SIMPLE_COLUMN = 5;
  public static final int VARIABLE = 6;
  public static final int PARAMETER = 7;
  public static final int DYNAMIC_PARAM = 8;
  public static final int TRANSITION_VARIABLE = 9;
  public static final int DIAGNOSTICS_VARIABLE = 10;
  public static final int ASTERISK = 11;
  public static final int SEQUENCE = 12;
  public static final int SEQUENCE_CURRENT = 13;
  public static final int ROWNUM = 14;
  public static final int ARRAY = 19;
  public static final int MULTISET = 20;
  public static final int SCALAR_SUBQUERY = 21;
  public static final int ROW_SUBQUERY = 22;
  public static final int TABLE_SUBQUERY = 23;
  public static final int RECURSIVE_SUBQUERY = 24;
  public static final int ROW = 25;
  public static final int VALUELIST = 26;
  public static final int FUNCTION = 27;
  public static final int SQL_FUNCTION = 28;
  public static final int ROUTINE_FUNCTION = 29;
  public static final int TABLE = 30;
  public static final int NEGATE = 31;
  public static final int ADD = 32;
  public static final int SUBTRACT = 33;
  public static final int MULTIPLY = 34;
  public static final int DIVIDE = 35;
  public static final int CONCAT = 36;
  public static final int LIKE_ARG = 37;
  public static final int CASEWHEN_COALESCE = 38;
  public static final int EQUAL = 41;
  public static final int GREATER_EQUAL = 42;
  public static final int GREATER = 43;
  public static final int SMALLER = 44;
  public static final int SMALLER_EQUAL = 45;
  public static final int NOT_EQUAL = 46;
  public static final int IS_NULL = 47;
  public static final int NOT = 48;
  public static final int AND = 49;
  public static final int OR = 50;
  public static final int ALL_QUANTIFIED = 51;
  public static final int ANY_QUANTIFIED = 52;
  public static final int LIKE = 53;
  public static final int IN = 54;
  public static final int EXISTS = 55;
  public static final int OVERLAPS = 56;
  public static final int UNIQUE = 57;
  public static final int NOT_DISTINCT = 58;
  public static final int MATCH_SIMPLE = 59;
  public static final int MATCH_PARTIAL = 60;
  public static final int MATCH_FULL = 61;
  public static final int MATCH_UNIQUE_SIMPLE = 62;
  public static final int MATCH_UNIQUE_PARTIAL = 63;
  public static final int MATCH_UNIQUE_FULL = 64;
  public static final int CONTAINS = 65;
  public static final int COUNT = 71;
  public static final int SUM = 72;
  public static final int MIN = 73;
  public static final int MAX = 74;
  public static final int AVG = 75;
  public static final int EVERY = 76;
  public static final int SOME = 77;
  public static final int STDDEV_POP = 78;
  public static final int STDDEV_SAMP = 79;
  public static final int VAR_POP = 80;
  public static final int VAR_SAMP = 81;
  public static final int ARRAY_AGG = 82;
  public static final int GROUP_CONCAT = 83;
  public static final int PREFIX = 84;
  public static final int MEDIAN = 85;
  public static final int CAST = 91;
  public static final int ZONE_MODIFIER = 92;
  public static final int CASEWHEN = 93;
  public static final int ORDER_BY = 94;
  public static final int LIMIT = 95;
  public static final int ALTERNATIVE = 96;
  public static final int MULTICOLUMN = 97;
  public static final int USER_AGGREGATE = 98;
  public static final int ARRAY_ACCESS = 99;
  public static final int ARRAY_SUBQUERY = 100;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.OpTypes
 * JD-Core Version:    0.6.2
 */