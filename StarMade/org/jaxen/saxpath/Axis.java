package org.jaxen.saxpath;

import org.jaxen.JaxenRuntimeException;

public class Axis
{
  public static final int INVALID_AXIS = 0;
  public static final int CHILD = 1;
  public static final int DESCENDANT = 2;
  public static final int PARENT = 3;
  public static final int ANCESTOR = 4;
  public static final int FOLLOWING_SIBLING = 5;
  public static final int PRECEDING_SIBLING = 6;
  public static final int FOLLOWING = 7;
  public static final int PRECEDING = 8;
  public static final int ATTRIBUTE = 9;
  public static final int NAMESPACE = 10;
  public static final int SELF = 11;
  public static final int DESCENDANT_OR_SELF = 12;
  public static final int ANCESTOR_OR_SELF = 13;
  
  public static String lookup(int axisNum)
  {
    switch (axisNum)
    {
    case 1: 
      return "child";
    case 2: 
      return "descendant";
    case 3: 
      return "parent";
    case 4: 
      return "ancestor";
    case 5: 
      return "following-sibling";
    case 6: 
      return "preceding-sibling";
    case 7: 
      return "following";
    case 8: 
      return "preceding";
    case 9: 
      return "attribute";
    case 10: 
      return "namespace";
    case 11: 
      return "self";
    case 12: 
      return "descendant-or-self";
    case 13: 
      return "ancestor-or-self";
    }
    throw new JaxenRuntimeException("Illegal Axis Number");
  }
  
  public static int lookup(String axisName)
  {
    if ("child".equals(axisName)) {
      return 1;
    }
    if ("descendant".equals(axisName)) {
      return 2;
    }
    if ("parent".equals(axisName)) {
      return 3;
    }
    if ("ancestor".equals(axisName)) {
      return 4;
    }
    if ("following-sibling".equals(axisName)) {
      return 5;
    }
    if ("preceding-sibling".equals(axisName)) {
      return 6;
    }
    if ("following".equals(axisName)) {
      return 7;
    }
    if ("preceding".equals(axisName)) {
      return 8;
    }
    if ("attribute".equals(axisName)) {
      return 9;
    }
    if ("namespace".equals(axisName)) {
      return 10;
    }
    if ("self".equals(axisName)) {
      return 11;
    }
    if ("descendant-or-self".equals(axisName)) {
      return 12;
    }
    if ("ancestor-or-self".equals(axisName)) {
      return 13;
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.Axis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */