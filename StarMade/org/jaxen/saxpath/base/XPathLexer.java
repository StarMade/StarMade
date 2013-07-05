/*     */ package org.jaxen.saxpath.base;
/*     */ 
/*     */ class XPathLexer
/*     */ {
/*     */   private String xpath;
/*     */   private int currentPosition;
/*     */   private int endPosition;
/*  56 */   private boolean expectOperator = false;
/*     */ 
/*     */   XPathLexer(String xpath)
/*     */   {
/*  60 */     setXPath(xpath);
/*     */   }
/*     */ 
/*     */   private void setXPath(String xpath)
/*     */   {
/*  65 */     this.xpath = xpath;
/*  66 */     this.currentPosition = 0;
/*  67 */     this.endPosition = xpath.length();
/*     */   }
/*     */ 
/*     */   String getXPath()
/*     */   {
/*  72 */     return this.xpath;
/*     */   }
/*     */ 
/*     */   Token nextToken()
/*     */   {
/*  77 */     Token token = null;
/*     */     do
/*     */     {
/*  81 */       token = null;
/*     */ 
/*  83 */       switch (LA(1))
/*     */       {
/*     */       case '$':
/*  87 */         token = dollar();
/*  88 */         break;
/*     */       case '"':
/*     */       case '\'':
/*  94 */         token = literal();
/*  95 */         break;
/*     */       case '/':
/* 100 */         token = slashes();
/* 101 */         break;
/*     */       case ',':
/* 106 */         token = comma();
/* 107 */         break;
/*     */       case '(':
/* 112 */         token = leftParen();
/* 113 */         break;
/*     */       case ')':
/* 118 */         token = rightParen();
/* 119 */         break;
/*     */       case '[':
/* 124 */         token = leftBracket();
/* 125 */         break;
/*     */       case ']':
/* 130 */         token = rightBracket();
/* 131 */         break;
/*     */       case '+':
/* 136 */         token = plus();
/* 137 */         break;
/*     */       case '-':
/* 142 */         token = minus();
/* 143 */         break;
/*     */       case '<':
/*     */       case '>':
/* 149 */         token = relationalOperator();
/* 150 */         break;
/*     */       case '=':
/* 155 */         token = equals();
/* 156 */         break;
/*     */       case '!':
/* 161 */         if (LA(2) == '=')
/*     */         {
/* 163 */           token = notEquals(); } break;
/*     */       case '|':
/* 170 */         token = pipe();
/* 171 */         break;
/*     */       case '@':
/* 176 */         token = at();
/* 177 */         break;
/*     */       case ':':
/* 182 */         if (LA(2) == ':')
/*     */         {
/* 184 */           token = doubleColon();
/*     */         }
/*     */         else
/*     */         {
/* 188 */           token = colon();
/*     */         }
/* 190 */         break;
/*     */       case '*':
/* 195 */         token = star();
/* 196 */         break;
/*     */       case '.':
/* 201 */         switch (LA(2))
/*     */         {
/*     */         case '0':
/*     */         case '1':
/*     */         case '2':
/*     */         case '3':
/*     */         case '4':
/*     */         case '5':
/*     */         case '6':
/*     */         case '7':
/*     */         case '8':
/*     */         case '9':
/* 214 */           token = number();
/* 215 */           break;
/*     */         default:
/* 219 */           token = dots();
/* 220 */         }break;
/*     */       case '0':
/*     */       case '1':
/*     */       case '2':
/*     */       case '3':
/*     */       case '4':
/*     */       case '5':
/*     */       case '6':
/*     */       case '7':
/*     */       case '8':
/*     */       case '9':
/* 237 */         token = number();
/* 238 */         break;
/*     */       case '\t':
/*     */       case '\n':
/*     */       case '\r':
/*     */       case ' ':
/* 246 */         token = whitespace();
/* 247 */         break;
/*     */       case '\013':
/*     */       case '\f':
/*     */       case '\016':
/*     */       case '\017':
/*     */       case '\020':
/*     */       case '\021':
/*     */       case '\022':
/*     */       case '\023':
/*     */       case '\024':
/*     */       case '\025':
/*     */       case '\026':
/*     */       case '\027':
/*     */       case '\030':
/*     */       case '\031':
/*     */       case '\032':
/*     */       case '\033':
/*     */       case '\034':
/*     */       case '\035':
/*     */       case '\036':
/*     */       case '\037':
/*     */       case '#':
/*     */       case '%':
/*     */       case '&':
/*     */       case ';':
/*     */       case '?':
/*     */       case 'A':
/*     */       case 'B':
/*     */       case 'C':
/*     */       case 'D':
/*     */       case 'E':
/*     */       case 'F':
/*     */       case 'G':
/*     */       case 'H':
/*     */       case 'I':
/*     */       case 'J':
/*     */       case 'K':
/*     */       case 'L':
/*     */       case 'M':
/*     */       case 'N':
/*     */       case 'O':
/*     */       case 'P':
/*     */       case 'Q':
/*     */       case 'R':
/*     */       case 'S':
/*     */       case 'T':
/*     */       case 'U':
/*     */       case 'V':
/*     */       case 'W':
/*     */       case 'X':
/*     */       case 'Y':
/*     */       case 'Z':
/*     */       case '\\':
/*     */       case '^':
/*     */       case '_':
/*     */       case '`':
/*     */       case 'a':
/*     */       case 'b':
/*     */       case 'c':
/*     */       case 'd':
/*     */       case 'e':
/*     */       case 'f':
/*     */       case 'g':
/*     */       case 'h':
/*     */       case 'i':
/*     */       case 'j':
/*     */       case 'k':
/*     */       case 'l':
/*     */       case 'm':
/*     */       case 'n':
/*     */       case 'o':
/*     */       case 'p':
/*     */       case 'q':
/*     */       case 'r':
/*     */       case 's':
/*     */       case 't':
/*     */       case 'u':
/*     */       case 'v':
/*     */       case 'w':
/*     */       case 'x':
/*     */       case 'y':
/*     */       case 'z':
/*     */       case '{':
/*     */       default:
/* 252 */         if (Verifier.isXMLNCNameStartCharacter(LA(1)))
/*     */         {
/* 254 */           token = identifierOrOperatorName();
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/* 259 */       if (token == null)
/*     */       {
/* 261 */         if (!hasMoreChars())
/*     */         {
/* 263 */           token = new Token(-1, getXPath(), this.currentPosition, this.endPosition);
/*     */         }
/*     */         else
/*     */         {
/* 270 */           token = new Token(-3, getXPath(), this.currentPosition, this.endPosition);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 278 */     while (token.getTokenType() == -2);
/*     */ 
/* 291 */     switch (token.getTokenType())
/*     */     {
/*     */     case 1:
/*     */     case 2:
/*     */     case 3:
/*     */     case 4:
/*     */     case 5:
/*     */     case 6:
/*     */     case 7:
/*     */     case 8:
/*     */     case 10:
/*     */     case 11:
/*     */     case 12:
/*     */     case 13:
/*     */     case 17:
/*     */     case 18:
/*     */     case 19:
/*     */     case 20:
/*     */     case 21:
/*     */     case 23:
/*     */     case 25:
/*     */     case 27:
/*     */     case 28:
/*     */     case 30:
/*     */     case 31:
/* 317 */       this.expectOperator = false;
/* 318 */       break;
/*     */     case 9:
/*     */     case 14:
/*     */     case 15:
/*     */     case 16:
/*     */     case 22:
/*     */     case 24:
/*     */     case 26:
/*     */     case 29:
/*     */     default:
/* 322 */       this.expectOperator = true;
/*     */     }
/*     */ 
/* 327 */     return token;
/*     */   }
/*     */ 
/*     */   private Token identifierOrOperatorName()
/*     */   {
/* 332 */     Token token = null;
/* 333 */     if (this.expectOperator)
/* 334 */       token = operatorName();
/*     */     else {
/* 336 */       token = identifier();
/*     */     }
/* 338 */     return token;
/*     */   }
/*     */ 
/*     */   private Token identifier()
/*     */   {
/* 343 */     Token token = null;
/*     */ 
/* 345 */     int start = this.currentPosition;
/*     */ 
/* 347 */     while (hasMoreChars())
/*     */     {
/* 349 */       if (!Verifier.isXMLNCNameCharacter(LA(1)))
/*     */         break;
/* 351 */       consume();
/*     */     }
/*     */ 
/* 359 */     token = new Token(16, getXPath(), start, this.currentPosition);
/*     */ 
/* 364 */     return token;
/*     */   }
/*     */ 
/*     */   private Token operatorName()
/*     */   {
/* 369 */     Token token = null;
/*     */ 
/* 371 */     switch (LA(1))
/*     */     {
/*     */     case 'a':
/* 375 */       token = and();
/* 376 */       break;
/*     */     case 'o':
/* 381 */       token = or();
/* 382 */       break;
/*     */     case 'm':
/* 387 */       token = mod();
/* 388 */       break;
/*     */     case 'd':
/* 393 */       token = div();
/*     */     }
/*     */ 
/* 398 */     return token;
/*     */   }
/*     */ 
/*     */   private Token mod()
/*     */   {
/* 403 */     Token token = null;
/*     */ 
/* 405 */     if ((LA(1) == 'm') && (LA(2) == 'o') && (LA(3) == 'd'))
/*     */     {
/* 412 */       token = new Token(10, getXPath(), this.currentPosition, this.currentPosition + 3);
/*     */ 
/* 417 */       consume();
/* 418 */       consume();
/* 419 */       consume();
/*     */     }
/*     */ 
/* 422 */     return token;
/*     */   }
/*     */ 
/*     */   private Token div()
/*     */   {
/* 427 */     Token token = null;
/*     */ 
/* 429 */     if ((LA(1) == 'd') && (LA(2) == 'i') && (LA(3) == 'v'))
/*     */     {
/* 436 */       token = new Token(11, getXPath(), this.currentPosition, this.currentPosition + 3);
/*     */ 
/* 441 */       consume();
/* 442 */       consume();
/* 443 */       consume();
/*     */     }
/*     */ 
/* 446 */     return token;
/*     */   }
/*     */ 
/*     */   private Token and()
/*     */   {
/* 451 */     Token token = null;
/*     */ 
/* 453 */     if ((LA(1) == 'a') && (LA(2) == 'n') && (LA(3) == 'd'))
/*     */     {
/* 460 */       token = new Token(27, getXPath(), this.currentPosition, this.currentPosition + 3);
/*     */ 
/* 465 */       consume();
/* 466 */       consume();
/* 467 */       consume();
/*     */     }
/*     */ 
/* 470 */     return token;
/*     */   }
/*     */ 
/*     */   private Token or()
/*     */   {
/* 475 */     Token token = null;
/*     */ 
/* 477 */     if ((LA(1) == 'o') && (LA(2) == 'r'))
/*     */     {
/* 482 */       token = new Token(28, getXPath(), this.currentPosition, this.currentPosition + 2);
/*     */ 
/* 487 */       consume();
/* 488 */       consume();
/*     */     }
/*     */ 
/* 491 */     return token;
/*     */   }
/*     */ 
/*     */   private Token number()
/*     */   {
/* 496 */     int start = this.currentPosition;
/* 497 */     boolean periodAllowed = true;
/*     */     while (true)
/*     */     {
/* 502 */       switch (LA(1))
/*     */       {
/*     */       case '.':
/* 505 */         if (!periodAllowed)
/*     */           break label99;
/* 507 */         periodAllowed = false;
/* 508 */         consume(); break;
/*     */       case '0':
/*     */       case '1':
/*     */       case '2':
/*     */       case '3':
/*     */       case '4':
/*     */       case '5':
/*     */       case '6':
/*     */       case '7':
/*     */       case '8':
/*     */       case '9':
/* 525 */         consume();
/*     */       case '/':
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 532 */     label99: return new Token(29, getXPath(), start, this.currentPosition);
/*     */   }
/*     */ 
/*     */   private Token whitespace()
/*     */   {
/* 540 */     consume();
/*     */ 
/* 543 */     while (hasMoreChars())
/*     */     {
/* 545 */       switch (LA(1))
/*     */       {
/*     */       case '\t':
/*     */       case '\n':
/*     */       case '\r':
/*     */       case ' ':
/* 552 */         consume();
/* 553 */         break;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 563 */     return new Token(-2, getXPath(), 0, 0);
/*     */   }
/*     */ 
/*     */   private Token comma()
/*     */   {
/* 571 */     Token token = new Token(30, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 576 */     consume();
/*     */ 
/* 578 */     return token;
/*     */   }
/*     */ 
/*     */   private Token equals()
/*     */   {
/* 583 */     Token token = new Token(1, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 588 */     consume();
/*     */ 
/* 590 */     return token;
/*     */   }
/*     */ 
/*     */   private Token minus()
/*     */   {
/* 595 */     Token token = new Token(8, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 599 */     consume();
/*     */ 
/* 601 */     return token;
/*     */   }
/*     */ 
/*     */   private Token plus()
/*     */   {
/* 606 */     Token token = new Token(7, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 610 */     consume();
/*     */ 
/* 612 */     return token;
/*     */   }
/*     */ 
/*     */   private Token dollar()
/*     */   {
/* 617 */     Token token = new Token(25, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 621 */     consume();
/*     */ 
/* 623 */     return token;
/*     */   }
/*     */ 
/*     */   private Token pipe()
/*     */   {
/* 628 */     Token token = new Token(18, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 633 */     consume();
/*     */ 
/* 635 */     return token;
/*     */   }
/*     */ 
/*     */   private Token at()
/*     */   {
/* 640 */     Token token = new Token(17, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 645 */     consume();
/*     */ 
/* 647 */     return token;
/*     */   }
/*     */ 
/*     */   private Token colon()
/*     */   {
/* 652 */     Token token = new Token(19, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 656 */     consume();
/*     */ 
/* 658 */     return token;
/*     */   }
/*     */ 
/*     */   private Token doubleColon()
/*     */   {
/* 663 */     Token token = new Token(20, getXPath(), this.currentPosition, this.currentPosition + 2);
/*     */ 
/* 668 */     consume();
/* 669 */     consume();
/*     */ 
/* 671 */     return token;
/*     */   }
/*     */ 
/*     */   private Token notEquals()
/*     */   {
/* 676 */     Token token = new Token(2, getXPath(), this.currentPosition, this.currentPosition + 2);
/*     */ 
/* 681 */     consume();
/* 682 */     consume();
/*     */ 
/* 684 */     return token;
/*     */   }
/*     */ 
/*     */   private Token relationalOperator()
/*     */   {
/* 689 */     Token token = null;
/*     */ 
/* 691 */     switch (LA(1))
/*     */     {
/*     */     case '<':
/* 695 */       if (LA(2) == '=')
/*     */       {
/* 697 */         token = new Token(4, getXPath(), this.currentPosition, this.currentPosition + 2);
/*     */ 
/* 701 */         consume();
/*     */       }
/*     */       else
/*     */       {
/* 705 */         token = new Token(3, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */       }
/*     */ 
/* 711 */       consume();
/* 712 */       break;
/*     */     case '>':
/* 716 */       if (LA(2) == '=')
/*     */       {
/* 718 */         token = new Token(6, getXPath(), this.currentPosition, this.currentPosition + 2);
/*     */ 
/* 722 */         consume();
/*     */       }
/*     */       else
/*     */       {
/* 726 */         token = new Token(5, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */       }
/*     */ 
/* 732 */       consume();
/*     */     }
/*     */ 
/* 737 */     return token;
/*     */   }
/*     */ 
/*     */   private Token star()
/*     */   {
/* 744 */     int tokenType = this.expectOperator ? 31 : 9;
/* 745 */     Token token = new Token(tokenType, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 750 */     consume();
/*     */ 
/* 752 */     return token;
/*     */   }
/*     */ 
/*     */   private Token literal()
/*     */   {
/* 757 */     Token token = null;
/*     */ 
/* 759 */     char match = LA(1);
/*     */ 
/* 761 */     consume();
/*     */ 
/* 763 */     int start = this.currentPosition;
/*     */ 
/* 766 */     while ((token == null) && (hasMoreChars()))
/*     */     {
/* 769 */       if (LA(1) == match)
/*     */       {
/* 771 */         token = new Token(26, getXPath(), start, this.currentPosition);
/*     */       }
/*     */ 
/* 776 */       consume();
/*     */     }
/*     */ 
/* 779 */     return token;
/*     */   }
/*     */ 
/*     */   private Token dots()
/*     */   {
/* 784 */     Token token = null;
/*     */ 
/* 786 */     switch (LA(2))
/*     */     {
/*     */     case '.':
/* 790 */       token = new Token(15, getXPath(), this.currentPosition, this.currentPosition + 2);
/*     */ 
/* 794 */       consume();
/* 795 */       consume();
/* 796 */       break;
/*     */     default:
/* 800 */       token = new Token(14, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 804 */       consume();
/*     */     }
/*     */ 
/* 809 */     return token;
/*     */   }
/*     */ 
/*     */   private Token leftBracket()
/*     */   {
/* 814 */     Token token = new Token(21, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 819 */     consume();
/*     */ 
/* 821 */     return token;
/*     */   }
/*     */ 
/*     */   private Token rightBracket()
/*     */   {
/* 826 */     Token token = new Token(22, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 831 */     consume();
/*     */ 
/* 833 */     return token;
/*     */   }
/*     */ 
/*     */   private Token leftParen()
/*     */   {
/* 838 */     Token token = new Token(23, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 843 */     consume();
/*     */ 
/* 845 */     return token;
/*     */   }
/*     */ 
/*     */   private Token rightParen()
/*     */   {
/* 850 */     Token token = new Token(24, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 855 */     consume();
/*     */ 
/* 857 */     return token;
/*     */   }
/*     */ 
/*     */   private Token slashes()
/*     */   {
/* 862 */     Token token = null;
/*     */ 
/* 864 */     switch (LA(2))
/*     */     {
/*     */     case '/':
/* 868 */       token = new Token(13, getXPath(), this.currentPosition, this.currentPosition + 2);
/*     */ 
/* 872 */       consume();
/* 873 */       consume();
/* 874 */       break;
/*     */     default:
/* 878 */       token = new Token(12, getXPath(), this.currentPosition, this.currentPosition + 1);
/*     */ 
/* 882 */       consume();
/*     */     }
/*     */ 
/* 886 */     return token;
/*     */   }
/*     */ 
/*     */   private char LA(int i)
/*     */   {
/* 891 */     if (this.currentPosition + (i - 1) >= this.endPosition)
/*     */     {
/* 893 */       return 65535;
/*     */     }
/*     */ 
/* 896 */     return getXPath().charAt(this.currentPosition + (i - 1));
/*     */   }
/*     */ 
/*     */   private void consume()
/*     */   {
/* 901 */     this.currentPosition += 1;
/*     */   }
/*     */ 
/*     */   private boolean hasMoreChars()
/*     */   {
/* 906 */     return this.currentPosition < this.endPosition;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.XPathLexer
 * JD-Core Version:    0.6.2
 */