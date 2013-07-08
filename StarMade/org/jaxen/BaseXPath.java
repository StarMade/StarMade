/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.expr.Expr;
/*   6:    */import org.jaxen.expr.XPathExpr;
/*   7:    */import org.jaxen.function.BooleanFunction;
/*   8:    */import org.jaxen.function.NumberFunction;
/*   9:    */import org.jaxen.function.StringFunction;
/*  10:    */import org.jaxen.saxpath.SAXPathException;
/*  11:    */import org.jaxen.saxpath.XPathReader;
/*  12:    */import org.jaxen.saxpath.helpers.XPathReaderFactory;
/*  13:    */import org.jaxen.util.SingletonList;
/*  14:    */
/*  99:    */public class BaseXPath
/* 100:    */  implements XPath, Serializable
/* 101:    */{
/* 102:    */  private static final long serialVersionUID = -1993731281300293168L;
/* 103:    */  private final String exprText;
/* 104:    */  private final XPathExpr xpath;
/* 105:    */  private ContextSupport support;
/* 106:    */  private Navigator navigator;
/* 107:    */  
/* 108:    */  protected BaseXPath(String xpathExpr)
/* 109:    */    throws JaxenException
/* 110:    */  {
/* 111:    */    try
/* 112:    */    {
/* 113:113 */      XPathReader reader = XPathReaderFactory.createReader();
/* 114:114 */      JaxenHandler handler = new JaxenHandler();
/* 115:115 */      reader.setXPathHandler(handler);
/* 116:116 */      reader.parse(xpathExpr);
/* 117:117 */      this.xpath = handler.getXPathExpr();
/* 118:    */    }
/* 119:    */    catch (org.jaxen.saxpath.XPathSyntaxException e)
/* 120:    */    {
/* 121:121 */      throw new XPathSyntaxException(e);
/* 122:    */    }
/* 123:    */    catch (SAXPathException e)
/* 124:    */    {
/* 125:125 */      throw new JaxenException(e);
/* 126:    */    }
/* 127:    */    
/* 128:128 */    this.exprText = xpathExpr;
/* 129:    */  }
/* 130:    */  
/* 139:    */  public BaseXPath(String xpathExpr, Navigator navigator)
/* 140:    */    throws JaxenException
/* 141:    */  {
/* 142:142 */    this(xpathExpr);
/* 143:143 */    this.navigator = navigator;
/* 144:    */  }
/* 145:    */  
/* 172:    */  public Object evaluate(Object context)
/* 173:    */    throws JaxenException
/* 174:    */  {
/* 175:175 */    List answer = selectNodes(context);
/* 176:    */    
/* 177:177 */    if ((answer != null) && (answer.size() == 1))
/* 178:    */    {
/* 181:181 */      Object first = answer.get(0);
/* 182:    */      
/* 183:183 */      if (((first instanceof String)) || ((first instanceof Number)) || ((first instanceof Boolean)))
/* 184:    */      {
/* 189:189 */        return first;
/* 190:    */      }
/* 191:    */    }
/* 192:192 */    return answer;
/* 193:    */  }
/* 194:    */  
/* 212:    */  public List selectNodes(Object node)
/* 213:    */    throws JaxenException
/* 214:    */  {
/* 215:215 */    Context context = getContext(node);
/* 216:216 */    return selectNodesForContext(context);
/* 217:    */  }
/* 218:    */  
/* 234:    */  public Object selectSingleNode(Object node)
/* 235:    */    throws JaxenException
/* 236:    */  {
/* 237:237 */    List results = selectNodes(node);
/* 238:    */    
/* 239:239 */    if (results.isEmpty())
/* 240:    */    {
/* 241:241 */      return null;
/* 242:    */    }
/* 243:    */    
/* 244:244 */    return results.get(0);
/* 245:    */  }
/* 246:    */  
/* 251:    */  /**
/* 252:    */   * @deprecated
/* 253:    */   */
/* 254:    */  public String valueOf(Object node)
/* 255:    */    throws JaxenException
/* 256:    */  {
/* 257:257 */    return stringValueOf(node);
/* 258:    */  }
/* 259:    */  
/* 277:    */  public String stringValueOf(Object node)
/* 278:    */    throws JaxenException
/* 279:    */  {
/* 280:280 */    Context context = getContext(node);
/* 281:    */    
/* 282:282 */    Object result = selectSingleNodeForContext(context);
/* 283:    */    
/* 284:284 */    if (result == null)
/* 285:    */    {
/* 286:286 */      return "";
/* 287:    */    }
/* 288:    */    
/* 289:289 */    return StringFunction.evaluate(result, context.getNavigator());
/* 290:    */  }
/* 291:    */  
/* 309:    */  public boolean booleanValueOf(Object node)
/* 310:    */    throws JaxenException
/* 311:    */  {
/* 312:312 */    Context context = getContext(node);
/* 313:313 */    List result = selectNodesForContext(context);
/* 314:314 */    if (result == null) return false;
/* 315:315 */    return BooleanFunction.evaluate(result, context.getNavigator()).booleanValue();
/* 316:    */  }
/* 317:    */  
/* 334:    */  public Number numberValueOf(Object node)
/* 335:    */    throws JaxenException
/* 336:    */  {
/* 337:337 */    Context context = getContext(node);
/* 338:338 */    Object result = selectSingleNodeForContext(context);
/* 339:339 */    return NumberFunction.evaluate(result, context.getNavigator());
/* 340:    */  }
/* 341:    */  
/* 369:    */  public void addNamespace(String prefix, String uri)
/* 370:    */    throws JaxenException
/* 371:    */  {
/* 372:372 */    NamespaceContext nsContext = getNamespaceContext();
/* 373:373 */    if ((nsContext instanceof SimpleNamespaceContext))
/* 374:    */    {
/* 375:375 */      ((SimpleNamespaceContext)nsContext).addNamespace(prefix, uri);
/* 376:    */      
/* 377:377 */      return;
/* 378:    */    }
/* 379:    */    
/* 380:380 */    throw new JaxenException("Operation not permitted while using a non-simple namespace context.");
/* 381:    */  }
/* 382:    */  
/* 405:    */  public void setNamespaceContext(NamespaceContext namespaceContext)
/* 406:    */  {
/* 407:407 */    getContextSupport().setNamespaceContext(namespaceContext);
/* 408:    */  }
/* 409:    */  
/* 424:    */  public void setFunctionContext(FunctionContext functionContext)
/* 425:    */  {
/* 426:426 */    getContextSupport().setFunctionContext(functionContext);
/* 427:    */  }
/* 428:    */  
/* 443:    */  public void setVariableContext(VariableContext variableContext)
/* 444:    */  {
/* 445:445 */    getContextSupport().setVariableContext(variableContext);
/* 446:    */  }
/* 447:    */  
/* 466:    */  public NamespaceContext getNamespaceContext()
/* 467:    */  {
/* 468:468 */    return getContextSupport().getNamespaceContext();
/* 469:    */  }
/* 470:    */  
/* 489:    */  public FunctionContext getFunctionContext()
/* 490:    */  {
/* 491:491 */    return getContextSupport().getFunctionContext();
/* 492:    */  }
/* 493:    */  
/* 512:    */  public VariableContext getVariableContext()
/* 513:    */  {
/* 514:514 */    return getContextSupport().getVariableContext();
/* 515:    */  }
/* 516:    */  
/* 532:    */  public Expr getRootExpr()
/* 533:    */  {
/* 534:534 */    return this.xpath.getRootExpr();
/* 535:    */  }
/* 536:    */  
/* 541:    */  public String toString()
/* 542:    */  {
/* 543:543 */    return this.exprText;
/* 544:    */  }
/* 545:    */  
/* 550:    */  public String debug()
/* 551:    */  {
/* 552:552 */    return this.xpath.toString();
/* 553:    */  }
/* 554:    */  
/* 570:    */  protected Context getContext(Object node)
/* 571:    */  {
/* 572:572 */    if ((node instanceof Context))
/* 573:    */    {
/* 574:574 */      return (Context)node;
/* 575:    */    }
/* 576:    */    
/* 577:577 */    Context fullContext = new Context(getContextSupport());
/* 578:    */    
/* 579:579 */    if ((node instanceof List))
/* 580:    */    {
/* 581:581 */      fullContext.setNodeSet((List)node);
/* 582:    */    }
/* 583:    */    else
/* 584:    */    {
/* 585:585 */      List list = new SingletonList(node);
/* 586:586 */      fullContext.setNodeSet(list);
/* 587:    */    }
/* 588:    */    
/* 589:589 */    return fullContext;
/* 590:    */  }
/* 591:    */  
/* 599:    */  protected ContextSupport getContextSupport()
/* 600:    */  {
/* 601:601 */    if (this.support == null)
/* 602:    */    {
/* 603:603 */      this.support = new ContextSupport(createNamespaceContext(), createFunctionContext(), createVariableContext(), getNavigator());
/* 604:    */    }
/* 605:    */    
/* 611:611 */    return this.support;
/* 612:    */  }
/* 613:    */  
/* 619:    */  public Navigator getNavigator()
/* 620:    */  {
/* 621:621 */    return this.navigator;
/* 622:    */  }
/* 623:    */  
/* 636:    */  protected FunctionContext createFunctionContext()
/* 637:    */  {
/* 638:638 */    return XPathFunctionContext.getInstance();
/* 639:    */  }
/* 640:    */  
/* 645:    */  protected NamespaceContext createNamespaceContext()
/* 646:    */  {
/* 647:647 */    return new SimpleNamespaceContext();
/* 648:    */  }
/* 649:    */  
/* 654:    */  protected VariableContext createVariableContext()
/* 655:    */  {
/* 656:656 */    return new SimpleVariableContext();
/* 657:    */  }
/* 658:    */  
/* 674:    */  protected List selectNodesForContext(Context context)
/* 675:    */    throws JaxenException
/* 676:    */  {
/* 677:677 */    List list = this.xpath.asList(context);
/* 678:678 */    return list;
/* 679:    */  }
/* 680:    */  
/* 698:    */  protected Object selectSingleNodeForContext(Context context)
/* 699:    */    throws JaxenException
/* 700:    */  {
/* 701:701 */    List results = selectNodesForContext(context);
/* 702:    */    
/* 703:703 */    if (results.isEmpty())
/* 704:    */    {
/* 705:705 */      return null;
/* 706:    */    }
/* 707:    */    
/* 708:708 */    return results.get(0);
/* 709:    */  }
/* 710:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.BaseXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */