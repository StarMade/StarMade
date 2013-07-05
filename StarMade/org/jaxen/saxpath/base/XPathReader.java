/*      */ package org.jaxen.saxpath.base;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import org.jaxen.saxpath.Axis;
/*      */ import org.jaxen.saxpath.SAXPathException;
/*      */ import org.jaxen.saxpath.XPathHandler;
/*      */ import org.jaxen.saxpath.XPathSyntaxException;
/*      */ import org.jaxen.saxpath.helpers.DefaultXPathHandler;
/*      */ 
/*      */ public class XPathReader
/*      */   implements org.jaxen.saxpath.XPathReader
/*      */ {
/*      */   private ArrayList tokens;
/*      */   private XPathLexer lexer;
/*      */   private XPathHandler handler;
/*   73 */   private static XPathHandler defaultHandler = new DefaultXPathHandler();
/*      */ 
/*      */   public XPathReader()
/*      */   {
/*   81 */     setXPathHandler(defaultHandler);
/*      */   }
/*      */ 
/*      */   public void setXPathHandler(XPathHandler handler)
/*      */   {
/*   86 */     this.handler = handler;
/*      */   }
/*      */ 
/*      */   public XPathHandler getXPathHandler()
/*      */   {
/*   91 */     return this.handler;
/*      */   }
/*      */ 
/*      */   public void parse(String xpath) throws SAXPathException
/*      */   {
/*   96 */     setUpParse(xpath);
/*      */ 
/*   98 */     getXPathHandler().startXPath();
/*      */ 
/*  100 */     expr();
/*      */ 
/*  102 */     getXPathHandler().endXPath();
/*      */ 
/*  104 */     if (LA(1) != -1)
/*      */     {
/*  106 */       XPathSyntaxException ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
/*  107 */       throw ex;
/*      */     }
/*      */ 
/*  110 */     this.lexer = null;
/*  111 */     this.tokens = null;
/*      */   }
/*      */ 
/*      */   void setUpParse(String xpath)
/*      */   {
/*  116 */     this.tokens = new ArrayList();
/*  117 */     this.lexer = new XPathLexer(xpath);
/*      */   }
/*      */ 
/*      */   private void pathExpr() throws SAXPathException
/*      */   {
/*  122 */     getXPathHandler().startPathExpr();
/*      */ 
/*  124 */     switch (LA(1))
/*      */     {
/*      */     case 26:
/*      */     case 29:
/*  129 */       filterExpr();
/*      */ 
/*  131 */       if ((LA(1) == 12) || (LA(1) == 13))
/*      */       {
/*  133 */         XPathSyntaxException ex = createSyntaxException("Node-set expected");
/*  134 */         throw ex;
/*      */       }
/*      */ 
/*      */       break;
/*      */     case 23:
/*      */     case 25:
/*  142 */       filterExpr();
/*      */ 
/*  144 */       if ((LA(1) == 12) || (LA(1) == 13))
/*      */       {
/*  146 */         locationPath(false); } break;
/*      */     case 16:
/*  153 */       if (((LA(2) == 23) && (!isNodeTypeName(LT(1)))) || ((LA(2) == 19) && (LA(4) == 23)))
/*      */       {
/*  161 */         filterExpr();
/*      */ 
/*  163 */         if ((LA(1) == 12) || (LA(1) == 13))
/*      */         {
/*  165 */           locationPath(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  170 */         locationPath(false);
/*      */       }
/*  172 */       break;
/*      */     case 9:
/*      */     case 14:
/*      */     case 15:
/*      */     case 17:
/*  179 */       locationPath(false);
/*  180 */       break;
/*      */     case 12:
/*      */     case 13:
/*  185 */       locationPath(true);
/*  186 */       break;
/*      */     case 10:
/*      */     case 11:
/*      */     case 18:
/*      */     case 19:
/*      */     case 20:
/*      */     case 21:
/*      */     case 22:
/*      */     case 24:
/*      */     case 27:
/*      */     case 28:
/*      */     default:
/*  190 */       XPathSyntaxException ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
/*  191 */       throw ex;
/*      */     }
/*      */ 
/*  195 */     getXPathHandler().endPathExpr();
/*      */   }
/*      */ 
/*      */   private void literal() throws SAXPathException
/*      */   {
/*  200 */     Token token = match(26);
/*      */ 
/*  202 */     getXPathHandler().literal(token.getTokenText());
/*      */   }
/*      */ 
/*      */   private void functionCall() throws SAXPathException
/*      */   {
/*  207 */     String prefix = null;
/*  208 */     String functionName = null;
/*      */ 
/*  210 */     if (LA(2) == 19)
/*      */     {
/*  212 */       prefix = match(16).getTokenText();
/*  213 */       match(19);
/*      */     }
/*      */     else
/*      */     {
/*  217 */       prefix = "";
/*      */     }
/*      */ 
/*  220 */     functionName = match(16).getTokenText();
/*      */ 
/*  222 */     getXPathHandler().startFunction(prefix, functionName);
/*      */ 
/*  225 */     match(23);
/*      */ 
/*  227 */     arguments();
/*      */ 
/*  229 */     match(24);
/*      */ 
/*  231 */     getXPathHandler().endFunction();
/*      */   }
/*      */ 
/*      */   private void arguments() throws SAXPathException
/*      */   {
/*  236 */     while (LA(1) != 24)
/*      */     {
/*  238 */       expr();
/*      */ 
/*  240 */       if (LA(1) != 30)
/*      */         break;
/*  242 */       match(30);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void filterExpr()
/*      */     throws SAXPathException
/*      */   {
/*  254 */     getXPathHandler().startFilterExpr();
/*      */ 
/*  256 */     switch (LA(1))
/*      */     {
/*      */     case 29:
/*  260 */       Token token = match(29);
/*      */ 
/*  262 */       getXPathHandler().number(Double.parseDouble(token.getTokenText()));
/*  263 */       break;
/*      */     case 26:
/*  267 */       literal();
/*  268 */       break;
/*      */     case 23:
/*  272 */       match(23);
/*  273 */       expr();
/*  274 */       match(24);
/*  275 */       break;
/*      */     case 16:
/*  279 */       functionCall();
/*  280 */       break;
/*      */     case 25:
/*  284 */       variableReference();
/*      */     case 17:
/*      */     case 18:
/*      */     case 19:
/*      */     case 20:
/*      */     case 21:
/*      */     case 22:
/*      */     case 24:
/*      */     case 27:
/*  289 */     case 28: } predicates();
/*      */ 
/*  291 */     getXPathHandler().endFilterExpr();
/*      */   }
/*      */ 
/*      */   private void variableReference() throws SAXPathException
/*      */   {
/*  296 */     match(25);
/*      */ 
/*  298 */     String prefix = null;
/*  299 */     String variableName = null;
/*      */ 
/*  301 */     if (LA(2) == 19)
/*      */     {
/*  303 */       prefix = match(16).getTokenText();
/*  304 */       match(19);
/*      */     }
/*      */     else
/*      */     {
/*  308 */       prefix = "";
/*      */     }
/*      */ 
/*  311 */     variableName = match(16).getTokenText();
/*      */ 
/*  313 */     getXPathHandler().variableReference(prefix, variableName);
/*      */   }
/*      */ 
/*      */   void locationPath(boolean isAbsolute)
/*      */     throws SAXPathException
/*      */   {
/*  319 */     switch (LA(1))
/*      */     {
/*      */     case 12:
/*      */     case 13:
/*  324 */       if (isAbsolute)
/*      */       {
/*  326 */         absoluteLocationPath();
/*      */       }
/*      */       else
/*      */       {
/*  330 */         relativeLocationPath();
/*      */       }
/*  332 */       break;
/*      */     case 9:
/*      */     case 14:
/*      */     case 15:
/*      */     case 16:
/*      */     case 17:
/*  340 */       relativeLocationPath();
/*  341 */       break;
/*      */     case 10:
/*      */     case 11:
/*      */     default:
/*  345 */       XPathSyntaxException ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
/*  346 */       throw ex;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void absoluteLocationPath()
/*      */     throws SAXPathException
/*      */   {
/*  353 */     getXPathHandler().startAbsoluteLocationPath();
/*      */ 
/*  355 */     switch (LA(1))
/*      */     {
/*      */     case 12:
/*  359 */       match(12);
/*      */ 
/*  361 */       switch (LA(1))
/*      */       {
/*      */       case 9:
/*      */       case 14:
/*      */       case 15:
/*      */       case 16:
/*      */       case 17:
/*  370 */         steps();
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*  374 */       case 13: } break;
/*      */     case 13:
/*  378 */       getXPathHandler().startAllNodeStep(12);
/*  379 */       getXPathHandler().endAllNodeStep();
/*      */ 
/*  381 */       match(13);
/*  382 */       switch (LA(1))
/*      */       {
/*      */       case 9:
/*      */       case 14:
/*      */       case 15:
/*      */       case 16:
/*      */       case 17:
/*  390 */         steps();
/*  391 */         break;
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*      */       default:
/*  394 */         XPathSyntaxException ex = createSyntaxException("Location path cannot end with //");
/*  395 */         throw ex;
/*      */       }
/*      */ 
/*      */       break;
/*      */     }
/*      */ 
/*  401 */     getXPathHandler().endAbsoluteLocationPath();
/*      */   }
/*      */ 
/*      */   private void relativeLocationPath() throws SAXPathException
/*      */   {
/*  406 */     getXPathHandler().startRelativeLocationPath();
/*      */ 
/*  408 */     switch (LA(1))
/*      */     {
/*      */     case 12:
/*  412 */       match(12);
/*  413 */       break;
/*      */     case 13:
/*  417 */       getXPathHandler().startAllNodeStep(12);
/*  418 */       getXPathHandler().endAllNodeStep();
/*      */ 
/*  420 */       match(13);
/*      */     }
/*      */ 
/*  426 */     steps();
/*      */ 
/*  428 */     getXPathHandler().endRelativeLocationPath();
/*      */   }
/*      */ 
/*      */   private void steps() throws SAXPathException
/*      */   {
/*  433 */     switch (LA(1))
/*      */     {
/*      */     case 9:
/*      */     case 14:
/*      */     case 15:
/*      */     case 16:
/*      */     case 17:
/*  442 */       step();
/*  443 */       break;
/*      */     case -1:
/*  447 */       return;
/*      */     case 0:
/*      */     case 1:
/*      */     case 2:
/*      */     case 3:
/*      */     case 4:
/*      */     case 5:
/*      */     case 6:
/*      */     case 7:
/*      */     case 8:
/*      */     case 10:
/*      */     case 11:
/*      */     case 12:
/*      */     case 13:
/*      */     default:
/*  451 */       XPathSyntaxException ex = createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
/*  452 */       throw ex;
/*      */     }
/*      */ 
/*      */     while (true)
/*      */     {
/*  458 */       if ((LA(1) == 12) || (LA(1) == 13))
/*      */       {
/*  462 */         switch (LA(1))
/*      */         {
/*      */         case 12:
/*  466 */           match(12);
/*  467 */           break;
/*      */         case 13:
/*  471 */           getXPathHandler().startAllNodeStep(12);
/*  472 */           getXPathHandler().endAllNodeStep();
/*      */ 
/*  474 */           match(13);
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  481 */         return;
/*      */       }
/*      */ 
/*  484 */       switch (LA(1))
/*      */       {
/*      */       case 9:
/*      */       case 14:
/*      */       case 15:
/*      */       case 16:
/*      */       case 17:
/*  492 */         step();
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13: } 
/*  497 */     }XPathSyntaxException ex = createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
/*  498 */     throw ex;
/*      */   }
/*      */ 
/*      */   void step()
/*      */     throws SAXPathException
/*      */   {
/*  507 */     int axis = 0;
/*      */ 
/*  509 */     switch (LA(1))
/*      */     {
/*      */     case 14:
/*      */     case 15:
/*  514 */       abbrStep();
/*  515 */       return;
/*      */     case 17:
/*  519 */       axis = axisSpecifier();
/*  520 */       break;
/*      */     case 16:
/*  524 */       if (LA(2) == 20)
/*      */       {
/*  526 */         axis = axisSpecifier();
/*      */       }
/*      */       else
/*      */       {
/*  530 */         axis = 1;
/*      */       }
/*  532 */       break;
/*      */     case 9:
/*  536 */       axis = 1;
/*      */     case 10:
/*      */     case 11:
/*      */     case 12:
/*      */     case 13:
/*  541 */     }nodeTest(axis);
/*      */   }
/*      */ 
/*      */   private int axisSpecifier() throws SAXPathException
/*      */   {
/*  546 */     int axis = 0;
/*      */ 
/*  548 */     switch (LA(1))
/*      */     {
/*      */     case 17:
/*  552 */       match(17);
/*  553 */       axis = 9;
/*  554 */       break;
/*      */     case 16:
/*  558 */       Token token = LT(1);
/*      */ 
/*  560 */       axis = Axis.lookup(token.getTokenText());
/*      */ 
/*  562 */       if (axis == 0)
/*      */       {
/*  564 */         throwInvalidAxis(token.getTokenText());
/*      */       }
/*      */ 
/*  567 */       match(16);
/*  568 */       match(20);
/*      */ 
/*  570 */       break;
/*      */     }
/*      */ 
/*  574 */     return axis;
/*      */   }
/*      */ 
/*      */   private void nodeTest(int axis) throws SAXPathException
/*      */   {
/*  579 */     switch (LA(1))
/*      */     {
/*      */     case 16:
/*  583 */       switch (LA(2))
/*      */       {
/*      */       case 23:
/*  587 */         nodeTypeTest(axis);
/*  588 */         break;
/*      */       default:
/*  592 */         nameTest(axis);
/*  593 */       }break;
/*      */     case 9:
/*  600 */       nameTest(axis);
/*  601 */       break;
/*      */     default:
/*  604 */       XPathSyntaxException ex = createSyntaxException("Expected <QName> or *");
/*  605 */       throw ex;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void nodeTypeTest(int axis) throws SAXPathException
/*      */   {
/*  611 */     Token nodeTypeToken = match(16);
/*  612 */     String nodeType = nodeTypeToken.getTokenText();
/*      */ 
/*  614 */     match(23);
/*      */ 
/*  616 */     if ("processing-instruction".equals(nodeType))
/*      */     {
/*  618 */       String piName = "";
/*      */ 
/*  620 */       if (LA(1) == 26)
/*      */       {
/*  622 */         piName = match(26).getTokenText();
/*      */       }
/*      */ 
/*  625 */       match(24);
/*      */ 
/*  627 */       getXPathHandler().startProcessingInstructionNodeStep(axis, piName);
/*      */ 
/*  630 */       predicates();
/*      */ 
/*  632 */       getXPathHandler().endProcessingInstructionNodeStep();
/*      */     }
/*  634 */     else if ("node".equals(nodeType))
/*      */     {
/*  636 */       match(24);
/*      */ 
/*  638 */       getXPathHandler().startAllNodeStep(axis);
/*      */ 
/*  640 */       predicates();
/*      */ 
/*  642 */       getXPathHandler().endAllNodeStep();
/*      */     }
/*  644 */     else if ("text".equals(nodeType))
/*      */     {
/*  646 */       match(24);
/*      */ 
/*  648 */       getXPathHandler().startTextNodeStep(axis);
/*      */ 
/*  650 */       predicates();
/*      */ 
/*  652 */       getXPathHandler().endTextNodeStep();
/*      */     }
/*  654 */     else if ("comment".equals(nodeType))
/*      */     {
/*  656 */       match(24);
/*      */ 
/*  658 */       getXPathHandler().startCommentNodeStep(axis);
/*      */ 
/*  660 */       predicates();
/*      */ 
/*  662 */       getXPathHandler().endCommentNodeStep();
/*      */     }
/*      */     else
/*      */     {
/*  666 */       XPathSyntaxException ex = createSyntaxException("Expected node-type");
/*  667 */       throw ex;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void nameTest(int axis) throws SAXPathException
/*      */   {
/*  673 */     String prefix = null;
/*  674 */     String localName = null;
/*      */ 
/*  676 */     switch (LA(2))
/*      */     {
/*      */     case 19:
/*  680 */       switch (LA(1))
/*      */       {
/*      */       case 16:
/*  684 */         prefix = match(16).getTokenText();
/*  685 */         match(19);
/*      */       }
/*      */ 
/*      */       break;
/*      */     }
/*      */ 
/*  693 */     switch (LA(1))
/*      */     {
/*      */     case 16:
/*  697 */       localName = match(16).getTokenText();
/*  698 */       break;
/*      */     case 9:
/*  702 */       match(9);
/*  703 */       localName = "*";
/*      */     }
/*      */ 
/*  708 */     if (prefix == null)
/*      */     {
/*  710 */       prefix = "";
/*      */     }
/*      */ 
/*  713 */     getXPathHandler().startNameStep(axis, prefix, localName);
/*      */ 
/*  717 */     predicates();
/*      */ 
/*  719 */     getXPathHandler().endNameStep();
/*      */   }
/*      */ 
/*      */   private void abbrStep() throws SAXPathException
/*      */   {
/*  724 */     switch (LA(1))
/*      */     {
/*      */     case 14:
/*  728 */       match(14);
/*  729 */       getXPathHandler().startAllNodeStep(11);
/*  730 */       predicates();
/*  731 */       getXPathHandler().endAllNodeStep();
/*  732 */       break;
/*      */     case 15:
/*  736 */       match(15);
/*  737 */       getXPathHandler().startAllNodeStep(3);
/*  738 */       predicates();
/*  739 */       getXPathHandler().endAllNodeStep();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void predicates()
/*      */     throws SAXPathException
/*      */   {
/*  749 */     while (LA(1) == 21)
/*      */     {
/*  751 */       predicate();
/*      */     }
/*      */   }
/*      */ 
/*      */   void predicate()
/*      */     throws SAXPathException
/*      */   {
/*  762 */     getXPathHandler().startPredicate();
/*      */ 
/*  764 */     match(21);
/*      */ 
/*  766 */     predicateExpr();
/*      */ 
/*  768 */     match(22);
/*      */ 
/*  770 */     getXPathHandler().endPredicate();
/*      */   }
/*      */ 
/*      */   private void predicateExpr() throws SAXPathException
/*      */   {
/*  775 */     expr();
/*      */   }
/*      */ 
/*      */   private void expr() throws SAXPathException
/*      */   {
/*  780 */     orExpr();
/*      */   }
/*      */ 
/*      */   private void orExpr() throws SAXPathException
/*      */   {
/*  785 */     getXPathHandler().startOrExpr();
/*      */ 
/*  787 */     andExpr();
/*      */ 
/*  789 */     boolean create = false;
/*      */ 
/*  791 */     switch (LA(1))
/*      */     {
/*      */     case 28:
/*  795 */       create = true;
/*  796 */       match(28);
/*  797 */       orExpr();
/*      */     }
/*      */ 
/*  802 */     getXPathHandler().endOrExpr(create);
/*      */   }
/*      */ 
/*      */   private void andExpr() throws SAXPathException
/*      */   {
/*  807 */     getXPathHandler().startAndExpr();
/*      */ 
/*  809 */     equalityExpr();
/*      */ 
/*  811 */     boolean create = false;
/*      */ 
/*  813 */     switch (LA(1))
/*      */     {
/*      */     case 27:
/*  817 */       create = true;
/*  818 */       match(27);
/*  819 */       andExpr();
/*      */     }
/*      */ 
/*  824 */     getXPathHandler().endAndExpr(create);
/*      */   }
/*      */ 
/*      */   private void equalityExpr() throws SAXPathException
/*      */   {
/*  829 */     relationalExpr();
/*      */ 
/*  831 */     int la = LA(1);
/*  832 */     while ((la == 1) || (la == 2))
/*      */     {
/*  834 */       switch (la)
/*      */       {
/*      */       case 1:
/*  838 */         match(1);
/*  839 */         getXPathHandler().startEqualityExpr();
/*  840 */         relationalExpr();
/*  841 */         getXPathHandler().endEqualityExpr(1);
/*  842 */         break;
/*      */       case 2:
/*  846 */         match(2);
/*  847 */         getXPathHandler().startEqualityExpr();
/*  848 */         relationalExpr();
/*  849 */         getXPathHandler().endEqualityExpr(2);
/*      */       }
/*      */ 
/*  853 */       la = LA(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void relationalExpr()
/*      */     throws SAXPathException
/*      */   {
/*  860 */     additiveExpr();
/*      */ 
/*  862 */     int la = LA(1);
/*      */ 
/*  870 */     while ((la == 3) || (la == 5) || (la == 4) || (la == 6)) {
/*  871 */       switch (la)
/*      */       {
/*      */       case 3:
/*  875 */         match(3);
/*  876 */         getXPathHandler().startRelationalExpr();
/*  877 */         additiveExpr();
/*  878 */         getXPathHandler().endRelationalExpr(3);
/*  879 */         break;
/*      */       case 5:
/*  883 */         match(5);
/*  884 */         getXPathHandler().startRelationalExpr();
/*  885 */         additiveExpr();
/*  886 */         getXPathHandler().endRelationalExpr(5);
/*  887 */         break;
/*      */       case 6:
/*  891 */         match(6);
/*  892 */         getXPathHandler().startRelationalExpr();
/*  893 */         additiveExpr();
/*  894 */         getXPathHandler().endRelationalExpr(6);
/*  895 */         break;
/*      */       case 4:
/*  899 */         match(4);
/*  900 */         getXPathHandler().startRelationalExpr();
/*  901 */         additiveExpr();
/*  902 */         getXPathHandler().endRelationalExpr(4);
/*      */       }
/*      */ 
/*  906 */       la = LA(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void additiveExpr()
/*      */     throws SAXPathException
/*      */   {
/*  913 */     multiplicativeExpr();
/*      */ 
/*  915 */     int la = LA(1);
/*  916 */     while ((la == 7) || (la == 8))
/*      */     {
/*  918 */       switch (la)
/*      */       {
/*      */       case 7:
/*  922 */         match(7);
/*  923 */         getXPathHandler().startAdditiveExpr();
/*  924 */         multiplicativeExpr();
/*  925 */         getXPathHandler().endAdditiveExpr(7);
/*  926 */         break;
/*      */       case 8:
/*  930 */         match(8);
/*  931 */         getXPathHandler().startAdditiveExpr();
/*  932 */         multiplicativeExpr();
/*  933 */         getXPathHandler().endAdditiveExpr(8);
/*      */       }
/*      */ 
/*  937 */       la = LA(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void multiplicativeExpr() throws SAXPathException
/*      */   {
/*  943 */     unaryExpr();
/*      */ 
/*  945 */     int la = LA(1);
/*  946 */     while ((la == 31) || (la == 11) || (la == 10))
/*      */     {
/*  948 */       switch (la)
/*      */       {
/*      */       case 9:
/*      */       case 31:
/*  953 */         match(31);
/*  954 */         getXPathHandler().startMultiplicativeExpr();
/*  955 */         unaryExpr();
/*  956 */         getXPathHandler().endMultiplicativeExpr(9);
/*  957 */         break;
/*      */       case 11:
/*  961 */         match(11);
/*  962 */         getXPathHandler().startMultiplicativeExpr();
/*  963 */         unaryExpr();
/*  964 */         getXPathHandler().endMultiplicativeExpr(11);
/*  965 */         break;
/*      */       case 10:
/*  969 */         match(10);
/*  970 */         getXPathHandler().startMultiplicativeExpr();
/*  971 */         unaryExpr();
/*  972 */         getXPathHandler().endMultiplicativeExpr(10);
/*      */       }
/*      */ 
/*  976 */       la = LA(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void unaryExpr()
/*      */     throws SAXPathException
/*      */   {
/*  983 */     switch (LA(1))
/*      */     {
/*      */     case 8:
/*  987 */       getXPathHandler().startUnaryExpr();
/*  988 */       match(8);
/*  989 */       unaryExpr();
/*  990 */       getXPathHandler().endUnaryExpr(12);
/*  991 */       break;
/*      */     default:
/*  995 */       unionExpr();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void unionExpr()
/*      */     throws SAXPathException
/*      */   {
/* 1005 */     getXPathHandler().startUnionExpr();
/*      */ 
/* 1007 */     pathExpr();
/*      */ 
/* 1009 */     boolean create = false;
/*      */ 
/* 1011 */     switch (LA(1))
/*      */     {
/*      */     case 18:
/* 1015 */       match(18);
/* 1016 */       create = true;
/* 1017 */       expr();
/*      */     }
/*      */ 
/* 1022 */     getXPathHandler().endUnionExpr(create);
/*      */   }
/*      */ 
/*      */   private Token match(int tokenType) throws XPathSyntaxException
/*      */   {
/* 1027 */     LT(1);
/*      */ 
/* 1029 */     Token token = (Token)this.tokens.get(0);
/*      */ 
/* 1031 */     if (token.getTokenType() == tokenType)
/*      */     {
/* 1033 */       this.tokens.remove(0);
/* 1034 */       return token;
/*      */     }
/*      */ 
/* 1038 */     XPathSyntaxException ex = createSyntaxException("Expected: " + TokenTypes.getTokenText(tokenType));
/* 1039 */     throw ex;
/*      */   }
/*      */ 
/*      */   private int LA(int position)
/*      */   {
/* 1044 */     return LT(position).getTokenType();
/*      */   }
/*      */ 
/*      */   private Token LT(int position)
/*      */   {
/* 1051 */     if (this.tokens.size() <= position - 1)
/*      */     {
/* 1053 */       for (int i = 0; i < position; i++)
/*      */       {
/* 1055 */         this.tokens.add(this.lexer.nextToken());
/*      */       }
/*      */     }
/*      */ 
/* 1059 */     return (Token)this.tokens.get(position - 1);
/*      */   }
/*      */ 
/*      */   private boolean isNodeTypeName(Token name)
/*      */   {
/* 1064 */     String text = name.getTokenText();
/*      */ 
/* 1066 */     if (("node".equals(text)) || ("comment".equals(text)) || ("text".equals(text)) || ("processing-instruction".equals(text)))
/*      */     {
/* 1074 */       return true;
/*      */     }
/*      */ 
/* 1077 */     return false;
/*      */   }
/*      */ 
/*      */   private XPathSyntaxException createSyntaxException(String message)
/*      */   {
/* 1082 */     String xpath = this.lexer.getXPath();
/* 1083 */     int position = LT(1).getTokenBegin();
/*      */ 
/* 1085 */     return new XPathSyntaxException(xpath, position, message);
/*      */   }
/*      */ 
/*      */   private void throwInvalidAxis(String invalidAxis)
/*      */     throws SAXPathException
/*      */   {
/* 1092 */     String xpath = this.lexer.getXPath();
/* 1093 */     int position = LT(1).getTokenBegin();
/*      */ 
/* 1095 */     String message = "Expected valid axis name instead of [" + invalidAxis + "]";
/*      */ 
/* 1097 */     throw new XPathSyntaxException(xpath, position, message);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.XPathReader
 * JD-Core Version:    0.6.2
 */