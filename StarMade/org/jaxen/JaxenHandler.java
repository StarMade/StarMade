/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.LinkedList;
/*   5:    */import org.jaxen.expr.DefaultXPathFactory;
/*   6:    */import org.jaxen.expr.Expr;
/*   7:    */import org.jaxen.expr.FilterExpr;
/*   8:    */import org.jaxen.expr.FunctionCallExpr;
/*   9:    */import org.jaxen.expr.LocationPath;
/*  10:    */import org.jaxen.expr.Predicate;
/*  11:    */import org.jaxen.expr.Predicated;
/*  12:    */import org.jaxen.expr.Step;
/*  13:    */import org.jaxen.expr.XPathExpr;
/*  14:    */import org.jaxen.expr.XPathFactory;
/*  15:    */import org.jaxen.saxpath.XPathHandler;
/*  16:    */
/*  86:    */public class JaxenHandler
/*  87:    */  implements XPathHandler
/*  88:    */{
/*  89:    */  private XPathFactory xpathFactory;
/*  90:    */  private XPathExpr xpath;
/*  91:    */  protected boolean simplified;
/*  92:    */  protected LinkedList stack;
/*  93:    */  
/*  94:    */  public JaxenHandler()
/*  95:    */  {
/*  96: 96 */    this.stack = new LinkedList();
/*  97: 97 */    this.xpathFactory = new DefaultXPathFactory();
/*  98:    */  }
/*  99:    */  
/* 105:    */  public void setXPathFactory(XPathFactory xpathFactory)
/* 106:    */  {
/* 107:107 */    this.xpathFactory = xpathFactory;
/* 108:    */  }
/* 109:    */  
/* 115:    */  public XPathFactory getXPathFactory()
/* 116:    */  {
/* 117:117 */    return this.xpathFactory;
/* 118:    */  }
/* 119:    */  
/* 129:    */  public XPathExpr getXPathExpr()
/* 130:    */  {
/* 131:131 */    return getXPathExpr(true);
/* 132:    */  }
/* 133:    */  
/* 146:    */  public XPathExpr getXPathExpr(boolean shouldSimplify)
/* 147:    */  {
/* 148:148 */    if ((shouldSimplify) && (!this.simplified))
/* 149:    */    {
/* 150:150 */      this.xpath.simplify();
/* 151:151 */      this.simplified = true;
/* 152:    */    }
/* 153:    */    
/* 154:154 */    return this.xpath;
/* 155:    */  }
/* 156:    */  
/* 157:    */  public void startXPath()
/* 158:    */  {
/* 159:159 */    this.simplified = false;
/* 160:160 */    pushFrame();
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void endXPath() throws JaxenException
/* 164:    */  {
/* 165:165 */    this.xpath = getXPathFactory().createXPath((Expr)pop());
/* 166:166 */    popFrame();
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void startPathExpr()
/* 170:    */  {
/* 171:171 */    pushFrame();
/* 172:    */  }
/* 173:    */  
/* 178:    */  public void endPathExpr()
/* 179:    */    throws JaxenException
/* 180:    */  {
/* 181:    */    FilterExpr filterExpr;
/* 182:    */    
/* 186:    */    LocationPath locationPath;
/* 187:    */    
/* 191:    */    FilterExpr filterExpr;
/* 192:    */    
/* 195:195 */    if (stackSize() == 2)
/* 196:    */    {
/* 197:197 */      LocationPath locationPath = (LocationPath)pop();
/* 198:198 */      filterExpr = (FilterExpr)pop();
/* 199:    */    }
/* 200:    */    else
/* 201:    */    {
/* 202:202 */      Object popped = pop();
/* 203:    */      FilterExpr filterExpr;
/* 204:204 */      if ((popped instanceof LocationPath))
/* 205:    */      {
/* 206:206 */        LocationPath locationPath = (LocationPath)popped;
/* 207:207 */        filterExpr = null;
/* 208:    */      }
/* 209:    */      else
/* 210:    */      {
/* 211:211 */        locationPath = null;
/* 212:212 */        filterExpr = (FilterExpr)popped;
/* 213:    */      }
/* 214:    */    }
/* 215:215 */    popFrame();
/* 216:    */    
/* 217:217 */    push(getXPathFactory().createPathExpr(filterExpr, locationPath));
/* 218:    */  }
/* 219:    */  
/* 220:    */  public void startAbsoluteLocationPath()
/* 221:    */    throws JaxenException
/* 222:    */  {
/* 223:223 */    pushFrame();
/* 224:    */    
/* 225:225 */    push(getXPathFactory().createAbsoluteLocationPath());
/* 226:    */  }
/* 227:    */  
/* 228:    */  public void endAbsoluteLocationPath() throws JaxenException
/* 229:    */  {
/* 230:230 */    endLocationPath();
/* 231:    */  }
/* 232:    */  
/* 233:    */  public void startRelativeLocationPath() throws JaxenException
/* 234:    */  {
/* 235:235 */    pushFrame();
/* 236:    */    
/* 237:237 */    push(getXPathFactory().createRelativeLocationPath());
/* 238:    */  }
/* 239:    */  
/* 240:    */  public void endRelativeLocationPath() throws JaxenException
/* 241:    */  {
/* 242:242 */    endLocationPath();
/* 243:    */  }
/* 244:    */  
/* 245:    */  protected void endLocationPath() throws JaxenException
/* 246:    */  {
/* 247:247 */    LocationPath path = (LocationPath)peekFrame().removeFirst();
/* 248:    */    
/* 249:249 */    addSteps(path, popFrame().iterator());
/* 250:    */    
/* 252:252 */    push(path);
/* 253:    */  }
/* 254:    */  
/* 256:    */  protected void addSteps(LocationPath locationPath, Iterator stepIter)
/* 257:    */  {
/* 258:258 */    while (stepIter.hasNext())
/* 259:    */    {
/* 260:260 */      locationPath.addStep((Step)stepIter.next());
/* 261:    */    }
/* 262:    */  }
/* 263:    */  
/* 265:    */  public void startNameStep(int axis, String prefix, String localName)
/* 266:    */    throws JaxenException
/* 267:    */  {
/* 268:268 */    pushFrame();
/* 269:    */    
/* 270:270 */    push(getXPathFactory().createNameStep(axis, prefix, localName));
/* 271:    */  }
/* 272:    */  
/* 275:    */  public void endNameStep()
/* 276:    */  {
/* 277:277 */    endStep();
/* 278:    */  }
/* 279:    */  
/* 280:    */  public void startTextNodeStep(int axis)
/* 281:    */    throws JaxenException
/* 282:    */  {
/* 283:283 */    pushFrame();
/* 284:    */    
/* 285:285 */    push(getXPathFactory().createTextNodeStep(axis));
/* 286:    */  }
/* 287:    */  
/* 288:    */  public void endTextNodeStep()
/* 289:    */  {
/* 290:290 */    endStep();
/* 291:    */  }
/* 292:    */  
/* 293:    */  public void startCommentNodeStep(int axis) throws JaxenException
/* 294:    */  {
/* 295:295 */    pushFrame();
/* 296:    */    
/* 297:297 */    push(getXPathFactory().createCommentNodeStep(axis));
/* 298:    */  }
/* 299:    */  
/* 300:    */  public void endCommentNodeStep()
/* 301:    */  {
/* 302:302 */    endStep();
/* 303:    */  }
/* 304:    */  
/* 305:    */  public void startAllNodeStep(int axis) throws JaxenException
/* 306:    */  {
/* 307:307 */    pushFrame();
/* 308:    */    
/* 309:309 */    push(getXPathFactory().createAllNodeStep(axis));
/* 310:    */  }
/* 311:    */  
/* 312:    */  public void endAllNodeStep()
/* 313:    */  {
/* 314:314 */    endStep();
/* 315:    */  }
/* 316:    */  
/* 317:    */  public void startProcessingInstructionNodeStep(int axis, String name)
/* 318:    */    throws JaxenException
/* 319:    */  {
/* 320:320 */    pushFrame();
/* 321:    */    
/* 322:322 */    push(getXPathFactory().createProcessingInstructionNodeStep(axis, name));
/* 323:    */  }
/* 324:    */  
/* 326:    */  public void endProcessingInstructionNodeStep()
/* 327:    */  {
/* 328:328 */    endStep();
/* 329:    */  }
/* 330:    */  
/* 331:    */  protected void endStep()
/* 332:    */  {
/* 333:333 */    Step step = (Step)peekFrame().removeFirst();
/* 334:    */    
/* 335:335 */    addPredicates(step, popFrame().iterator());
/* 336:    */    
/* 338:338 */    push(step);
/* 339:    */  }
/* 340:    */  
/* 341:    */  public void startPredicate()
/* 342:    */  {
/* 343:343 */    pushFrame();
/* 344:    */  }
/* 345:    */  
/* 346:    */  public void endPredicate() throws JaxenException
/* 347:    */  {
/* 348:348 */    Predicate predicate = getXPathFactory().createPredicate((Expr)pop());
/* 349:    */    
/* 350:350 */    popFrame();
/* 351:    */    
/* 352:352 */    push(predicate);
/* 353:    */  }
/* 354:    */  
/* 355:    */  public void startFilterExpr()
/* 356:    */  {
/* 357:357 */    pushFrame();
/* 358:    */  }
/* 359:    */  
/* 360:    */  public void endFilterExpr() throws JaxenException
/* 361:    */  {
/* 362:362 */    Expr expr = (Expr)peekFrame().removeFirst();
/* 363:    */    
/* 364:364 */    FilterExpr filter = getXPathFactory().createFilterExpr(expr);
/* 365:    */    
/* 366:366 */    Iterator predIter = popFrame().iterator();
/* 367:    */    
/* 368:368 */    addPredicates(filter, predIter);
/* 369:    */    
/* 371:371 */    push(filter);
/* 372:    */  }
/* 373:    */  
/* 375:    */  protected void addPredicates(Predicated obj, Iterator predIter)
/* 376:    */  {
/* 377:377 */    while (predIter.hasNext())
/* 378:    */    {
/* 379:379 */      obj.addPredicate((Predicate)predIter.next());
/* 380:    */    }
/* 381:    */  }
/* 382:    */  
/* 383:    */  protected void returnExpr()
/* 384:    */  {
/* 385:385 */    Expr expr = (Expr)pop();
/* 386:386 */    popFrame();
/* 387:387 */    push(expr);
/* 388:    */  }
/* 389:    */  
/* 391:    */  public void startOrExpr() {}
/* 392:    */  
/* 394:    */  public void endOrExpr(boolean create)
/* 395:    */    throws JaxenException
/* 396:    */  {
/* 397:397 */    if (create)
/* 398:    */    {
/* 399:399 */      Expr rhs = (Expr)pop();
/* 400:400 */      Expr lhs = (Expr)pop();
/* 401:    */      
/* 402:402 */      push(getXPathFactory().createOrExpr(lhs, rhs));
/* 403:    */    }
/* 404:    */  }
/* 405:    */  
/* 408:    */  public void startAndExpr() {}
/* 409:    */  
/* 411:    */  public void endAndExpr(boolean create)
/* 412:    */    throws JaxenException
/* 413:    */  {
/* 414:414 */    if (create)
/* 415:    */    {
/* 417:417 */      Expr rhs = (Expr)pop();
/* 418:418 */      Expr lhs = (Expr)pop();
/* 419:    */      
/* 420:420 */      push(getXPathFactory().createAndExpr(lhs, rhs));
/* 421:    */    }
/* 422:    */  }
/* 423:    */  
/* 426:    */  public void startEqualityExpr() {}
/* 427:    */  
/* 429:    */  public void endEqualityExpr(int operator)
/* 430:    */    throws JaxenException
/* 431:    */  {
/* 432:432 */    if (operator != 0)
/* 433:    */    {
/* 435:435 */      Expr rhs = (Expr)pop();
/* 436:436 */      Expr lhs = (Expr)pop();
/* 437:    */      
/* 438:438 */      push(getXPathFactory().createEqualityExpr(lhs, rhs, operator));
/* 439:    */    }
/* 440:    */  }
/* 441:    */  
/* 444:    */  public void startRelationalExpr() {}
/* 445:    */  
/* 448:    */  public void endRelationalExpr(int operator)
/* 449:    */    throws JaxenException
/* 450:    */  {
/* 451:451 */    if (operator != 0)
/* 452:    */    {
/* 454:454 */      Expr rhs = (Expr)pop();
/* 455:455 */      Expr lhs = (Expr)pop();
/* 456:    */      
/* 457:457 */      push(getXPathFactory().createRelationalExpr(lhs, rhs, operator));
/* 458:    */    }
/* 459:    */  }
/* 460:    */  
/* 463:    */  public void startAdditiveExpr() {}
/* 464:    */  
/* 467:    */  public void endAdditiveExpr(int operator)
/* 468:    */    throws JaxenException
/* 469:    */  {
/* 470:470 */    if (operator != 0)
/* 471:    */    {
/* 473:473 */      Expr rhs = (Expr)pop();
/* 474:474 */      Expr lhs = (Expr)pop();
/* 475:    */      
/* 476:476 */      push(getXPathFactory().createAdditiveExpr(lhs, rhs, operator));
/* 477:    */    }
/* 478:    */  }
/* 479:    */  
/* 482:    */  public void startMultiplicativeExpr() {}
/* 483:    */  
/* 486:    */  public void endMultiplicativeExpr(int operator)
/* 487:    */    throws JaxenException
/* 488:    */  {
/* 489:489 */    if (operator != 0)
/* 490:    */    {
/* 492:492 */      Expr rhs = (Expr)pop();
/* 493:493 */      Expr lhs = (Expr)pop();
/* 494:    */      
/* 495:495 */      push(getXPathFactory().createMultiplicativeExpr(lhs, rhs, operator));
/* 496:    */    }
/* 497:    */  }
/* 498:    */  
/* 501:    */  public void startUnaryExpr() {}
/* 502:    */  
/* 505:    */  public void endUnaryExpr(int operator)
/* 506:    */    throws JaxenException
/* 507:    */  {
/* 508:508 */    if (operator != 0)
/* 509:    */    {
/* 510:510 */      push(getXPathFactory().createUnaryExpr((Expr)pop(), operator));
/* 511:    */    }
/* 512:    */  }
/* 513:    */  
/* 516:    */  public void startUnionExpr() {}
/* 517:    */  
/* 519:    */  public void endUnionExpr(boolean create)
/* 520:    */    throws JaxenException
/* 521:    */  {
/* 522:522 */    if (create)
/* 523:    */    {
/* 525:525 */      Expr rhs = (Expr)pop();
/* 526:526 */      Expr lhs = (Expr)pop();
/* 527:    */      
/* 528:528 */      push(getXPathFactory().createUnionExpr(lhs, rhs));
/* 529:    */    }
/* 530:    */  }
/* 531:    */  
/* 532:    */  public void number(int number)
/* 533:    */    throws JaxenException
/* 534:    */  {
/* 535:535 */    push(getXPathFactory().createNumberExpr(number));
/* 536:    */  }
/* 537:    */  
/* 538:    */  public void number(double number) throws JaxenException
/* 539:    */  {
/* 540:540 */    push(getXPathFactory().createNumberExpr(number));
/* 541:    */  }
/* 542:    */  
/* 543:    */  public void literal(String literal) throws JaxenException
/* 544:    */  {
/* 545:545 */    push(getXPathFactory().createLiteralExpr(literal));
/* 546:    */  }
/* 547:    */  
/* 548:    */  public void variableReference(String prefix, String variableName)
/* 549:    */    throws JaxenException
/* 550:    */  {
/* 551:551 */    push(getXPathFactory().createVariableReferenceExpr(prefix, variableName));
/* 552:    */  }
/* 553:    */  
/* 555:    */  public void startFunction(String prefix, String functionName)
/* 556:    */    throws JaxenException
/* 557:    */  {
/* 558:558 */    pushFrame();
/* 559:559 */    push(getXPathFactory().createFunctionCallExpr(prefix, functionName));
/* 560:    */  }
/* 561:    */  
/* 563:    */  public void endFunction()
/* 564:    */  {
/* 565:565 */    FunctionCallExpr function = (FunctionCallExpr)peekFrame().removeFirst();
/* 566:    */    
/* 567:567 */    addParameters(function, popFrame().iterator());
/* 568:    */    
/* 570:570 */    push(function);
/* 571:    */  }
/* 572:    */  
/* 574:    */  protected void addParameters(FunctionCallExpr function, Iterator paramIter)
/* 575:    */  {
/* 576:576 */    while (paramIter.hasNext())
/* 577:    */    {
/* 578:578 */      function.addParameter((Expr)paramIter.next());
/* 579:    */    }
/* 580:    */  }
/* 581:    */  
/* 582:    */  protected int stackSize()
/* 583:    */  {
/* 584:584 */    return peekFrame().size();
/* 585:    */  }
/* 586:    */  
/* 587:    */  protected void push(Object obj)
/* 588:    */  {
/* 589:589 */    peekFrame().addLast(obj);
/* 590:    */  }
/* 591:    */  
/* 592:    */  protected Object pop()
/* 593:    */  {
/* 594:594 */    return peekFrame().removeLast();
/* 595:    */  }
/* 596:    */  
/* 597:    */  protected boolean canPop()
/* 598:    */  {
/* 599:599 */    return peekFrame().size() > 0;
/* 600:    */  }
/* 601:    */  
/* 602:    */  protected void pushFrame()
/* 603:    */  {
/* 604:604 */    this.stack.addLast(new LinkedList());
/* 605:    */  }
/* 606:    */  
/* 607:    */  protected LinkedList popFrame()
/* 608:    */  {
/* 609:609 */    return (LinkedList)this.stack.removeLast();
/* 610:    */  }
/* 611:    */  
/* 612:    */  protected LinkedList peekFrame()
/* 613:    */  {
/* 614:614 */    return (LinkedList)this.stack.getLast();
/* 615:    */  }
/* 616:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.JaxenHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */