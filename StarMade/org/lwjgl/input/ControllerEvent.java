/*   1:    */package org.lwjgl.input;
/*   2:    */
/*   8:    */class ControllerEvent
/*   9:    */{
/*  10:    */  public static final int BUTTON = 1;
/*  11:    */  
/*  16:    */  public static final int AXIS = 2;
/*  17:    */  
/*  22:    */  public static final int POVX = 3;
/*  23:    */  
/*  28:    */  public static final int POVY = 4;
/*  29:    */  
/*  34:    */  private Controller source;
/*  35:    */  
/*  40:    */  private int index;
/*  41:    */  
/*  46:    */  private int type;
/*  47:    */  
/*  52:    */  private boolean xaxis;
/*  53:    */  
/*  58:    */  private boolean yaxis;
/*  59:    */  
/*  64:    */  private long timeStamp;
/*  65:    */  
/*  71:    */  ControllerEvent(Controller source, long timeStamp, int type, int index, boolean xaxis, boolean yaxis)
/*  72:    */  {
/*  73: 73 */    this.source = source;
/*  74: 74 */    this.timeStamp = timeStamp;
/*  75: 75 */    this.type = type;
/*  76: 76 */    this.index = index;
/*  77: 77 */    this.xaxis = xaxis;
/*  78: 78 */    this.yaxis = yaxis;
/*  79:    */  }
/*  80:    */  
/*  86:    */  public long getTimeStamp()
/*  87:    */  {
/*  88: 88 */    return this.timeStamp;
/*  89:    */  }
/*  90:    */  
/*  95:    */  public Controller getSource()
/*  96:    */  {
/*  97: 97 */    return this.source;
/*  98:    */  }
/*  99:    */  
/* 104:    */  public int getControlIndex()
/* 105:    */  {
/* 106:106 */    return this.index;
/* 107:    */  }
/* 108:    */  
/* 113:    */  public boolean isButton()
/* 114:    */  {
/* 115:115 */    return this.type == 1;
/* 116:    */  }
/* 117:    */  
/* 122:    */  public boolean isAxis()
/* 123:    */  {
/* 124:124 */    return this.type == 2;
/* 125:    */  }
/* 126:    */  
/* 131:    */  public boolean isPovY()
/* 132:    */  {
/* 133:133 */    return this.type == 4;
/* 134:    */  }
/* 135:    */  
/* 140:    */  public boolean isPovX()
/* 141:    */  {
/* 142:142 */    return this.type == 3;
/* 143:    */  }
/* 144:    */  
/* 149:    */  public boolean isXAxis()
/* 150:    */  {
/* 151:151 */    return this.xaxis;
/* 152:    */  }
/* 153:    */  
/* 158:    */  public boolean isYAxis()
/* 159:    */  {
/* 160:160 */    return this.yaxis;
/* 161:    */  }
/* 162:    */  
/* 165:    */  public String toString()
/* 166:    */  {
/* 167:167 */    return "[" + this.source + " type=" + this.type + " xaxis=" + this.xaxis + " yaxis=" + this.yaxis + "]";
/* 168:    */  }
/* 169:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.ControllerEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */