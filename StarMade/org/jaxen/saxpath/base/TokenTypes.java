/*   1:    */package org.jaxen.saxpath.base;
/*   2:    */
/*   5:    */class TokenTypes
/*   6:    */{
/*   7:    */  static final int EOF = -1;
/*   8:    */  
/*  10:    */  static final int SKIP = -2;
/*  11:    */  
/*  13:    */  static final int ERROR = -3;
/*  14:    */  
/*  16:    */  static final int EQUALS = 1;
/*  17:    */  
/*  19:    */  static final int NOT_EQUALS = 2;
/*  20:    */  
/*  22:    */  static final int LESS_THAN_SIGN = 3;
/*  23:    */  
/*  25:    */  static final int LESS_THAN_OR_EQUALS_SIGN = 4;
/*  26:    */  
/*  28:    */  static final int GREATER_THAN_SIGN = 5;
/*  29:    */  
/*  31:    */  static final int GREATER_THAN_OR_EQUALS_SIGN = 6;
/*  32:    */  
/*  34:    */  static final int PLUS = 7;
/*  35:    */  
/*  37:    */  static final int MINUS = 8;
/*  38:    */  
/*  40:    */  static final int STAR = 9;
/*  41:    */  
/*  43:    */  static final int MOD = 10;
/*  44:    */  
/*  46:    */  static final int DIV = 11;
/*  47:    */  
/*  49:    */  static final int SLASH = 12;
/*  50:    */  
/*  52:    */  static final int DOUBLE_SLASH = 13;
/*  53:    */  
/*  55:    */  static final int DOT = 14;
/*  56:    */  
/*  58:    */  static final int DOT_DOT = 15;
/*  59:    */  
/*  61:    */  static final int IDENTIFIER = 16;
/*  62:    */  
/*  64:    */  static final int AT = 17;
/*  65:    */  
/*  67:    */  static final int PIPE = 18;
/*  68:    */  
/*  70:    */  static final int COLON = 19;
/*  71:    */  
/*  73:    */  static final int DOUBLE_COLON = 20;
/*  74:    */  
/*  76:    */  static final int LEFT_BRACKET = 21;
/*  77:    */  
/*  79:    */  static final int RIGHT_BRACKET = 22;
/*  80:    */  
/*  82:    */  static final int LEFT_PAREN = 23;
/*  83:    */  
/*  85:    */  static final int RIGHT_PAREN = 24;
/*  86:    */  
/*  87:    */  static final int DOLLAR = 25;
/*  88:    */  
/*  89:    */  static final int LITERAL = 26;
/*  90:    */  
/*  91:    */  static final int AND = 27;
/*  92:    */  
/*  93:    */  static final int OR = 28;
/*  94:    */  
/*  95:    */  static final int DOUBLE = 29;
/*  96:    */  
/*  97:    */  static final int COMMA = 30;
/*  98:    */  
/*  99:    */  static final int STAR_OPERATOR = 31;
/* 100:    */  
/* 102:    */  static String getTokenText(int tokenType)
/* 103:    */  {
/* 104:104 */    switch (tokenType)
/* 105:    */    {
/* 106:    */    case -3: 
/* 107:107 */      return "(error)";
/* 108:    */    case -2: 
/* 109:109 */      return "(skip)";
/* 110:    */    case -1: 
/* 111:111 */      return "(eof)";
/* 112:    */    case 0: 
/* 113:113 */      return "Unrecognized token type: 0";
/* 114:    */    case 1: 
/* 115:115 */      return "=";
/* 116:    */    case 2: 
/* 117:117 */      return "!=";
/* 118:    */    case 3: 
/* 119:119 */      return "<";
/* 120:    */    case 4: 
/* 121:121 */      return "<=";
/* 122:    */    case 5: 
/* 123:123 */      return ">";
/* 124:    */    case 6: 
/* 125:125 */      return ">=";
/* 126:    */    case 7: 
/* 127:127 */      return "+";
/* 128:    */    case 8: 
/* 129:129 */      return "-";
/* 130:    */    case 9: 
/* 131:131 */      return "*";
/* 132:    */    case 31: 
/* 133:133 */      return "*";
/* 134:    */    case 11: 
/* 135:135 */      return "div";
/* 136:    */    case 10: 
/* 137:137 */      return "mod";
/* 138:    */    case 12: 
/* 139:139 */      return "/";
/* 140:    */    case 13: 
/* 141:141 */      return "//";
/* 142:    */    case 14: 
/* 143:143 */      return ".";
/* 144:    */    case 15: 
/* 145:145 */      return "..";
/* 146:    */    case 16: 
/* 147:147 */      return "(identifier)";
/* 148:    */    case 17: 
/* 149:149 */      return "@";
/* 150:    */    case 18: 
/* 151:151 */      return "|";
/* 152:    */    case 19: 
/* 153:153 */      return ":";
/* 154:    */    case 20: 
/* 155:155 */      return "::";
/* 156:    */    case 21: 
/* 157:157 */      return "[";
/* 158:    */    case 22: 
/* 159:159 */      return "]";
/* 160:    */    case 23: 
/* 161:161 */      return "(";
/* 162:    */    case 24: 
/* 163:163 */      return ")";
/* 164:    */    case 25: 
/* 165:165 */      return "$";
/* 166:    */    case 26: 
/* 167:167 */      return "(literal)";
/* 168:    */    case 27: 
/* 169:169 */      return "and";
/* 170:    */    case 28: 
/* 171:171 */      return "or";
/* 172:    */    case 29: 
/* 173:173 */      return "(double)";
/* 174:    */    case 30: 
/* 175:175 */      return ",";
/* 176:    */    }
/* 177:    */    
/* 178:    */    
/* 184:184 */    return "Unrecognized token type: " + tokenType;
/* 185:    */  }
/* 186:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.TokenTypes
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */