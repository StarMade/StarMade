/*     */ package org.jaxen.saxpath.base;
/*     */ 
/*     */ class TokenTypes
/*     */ {
/*     */   static final int EOF = -1;
/*     */   static final int SKIP = -2;
/*     */   static final int ERROR = -3;
/*     */   static final int EQUALS = 1;
/*     */   static final int NOT_EQUALS = 2;
/*     */   static final int LESS_THAN_SIGN = 3;
/*     */   static final int LESS_THAN_OR_EQUALS_SIGN = 4;
/*     */   static final int GREATER_THAN_SIGN = 5;
/*     */   static final int GREATER_THAN_OR_EQUALS_SIGN = 6;
/*     */   static final int PLUS = 7;
/*     */   static final int MINUS = 8;
/*     */   static final int STAR = 9;
/*     */   static final int MOD = 10;
/*     */   static final int DIV = 11;
/*     */   static final int SLASH = 12;
/*     */   static final int DOUBLE_SLASH = 13;
/*     */   static final int DOT = 14;
/*     */   static final int DOT_DOT = 15;
/*     */   static final int IDENTIFIER = 16;
/*     */   static final int AT = 17;
/*     */   static final int PIPE = 18;
/*     */   static final int COLON = 19;
/*     */   static final int DOUBLE_COLON = 20;
/*     */   static final int LEFT_BRACKET = 21;
/*     */   static final int RIGHT_BRACKET = 22;
/*     */   static final int LEFT_PAREN = 23;
/*     */   static final int RIGHT_PAREN = 24;
/*     */   static final int DOLLAR = 25;
/*     */   static final int LITERAL = 26;
/*     */   static final int AND = 27;
/*     */   static final int OR = 28;
/*     */   static final int DOUBLE = 29;
/*     */   static final int COMMA = 30;
/*     */   static final int STAR_OPERATOR = 31;
/*     */ 
/*     */   static String getTokenText(int tokenType)
/*     */   {
/* 104 */     switch (tokenType)
/*     */     {
/*     */     case -3:
/* 107 */       return "(error)";
/*     */     case -2:
/* 109 */       return "(skip)";
/*     */     case -1:
/* 111 */       return "(eof)";
/*     */     case 0:
/* 113 */       return "Unrecognized token type: 0";
/*     */     case 1:
/* 115 */       return "=";
/*     */     case 2:
/* 117 */       return "!=";
/*     */     case 3:
/* 119 */       return "<";
/*     */     case 4:
/* 121 */       return "<=";
/*     */     case 5:
/* 123 */       return ">";
/*     */     case 6:
/* 125 */       return ">=";
/*     */     case 7:
/* 127 */       return "+";
/*     */     case 8:
/* 129 */       return "-";
/*     */     case 9:
/* 131 */       return "*";
/*     */     case 31:
/* 133 */       return "*";
/*     */     case 11:
/* 135 */       return "div";
/*     */     case 10:
/* 137 */       return "mod";
/*     */     case 12:
/* 139 */       return "/";
/*     */     case 13:
/* 141 */       return "//";
/*     */     case 14:
/* 143 */       return ".";
/*     */     case 15:
/* 145 */       return "..";
/*     */     case 16:
/* 147 */       return "(identifier)";
/*     */     case 17:
/* 149 */       return "@";
/*     */     case 18:
/* 151 */       return "|";
/*     */     case 19:
/* 153 */       return ":";
/*     */     case 20:
/* 155 */       return "::";
/*     */     case 21:
/* 157 */       return "[";
/*     */     case 22:
/* 159 */       return "]";
/*     */     case 23:
/* 161 */       return "(";
/*     */     case 24:
/* 163 */       return ")";
/*     */     case 25:
/* 165 */       return "$";
/*     */     case 26:
/* 167 */       return "(literal)";
/*     */     case 27:
/* 169 */       return "and";
/*     */     case 28:
/* 171 */       return "or";
/*     */     case 29:
/* 173 */       return "(double)";
/*     */     case 30:
/* 175 */       return ",";
/*     */     }
/*     */ 
/* 184 */     return "Unrecognized token type: " + tokenType;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.TokenTypes
 * JD-Core Version:    0.6.2
 */