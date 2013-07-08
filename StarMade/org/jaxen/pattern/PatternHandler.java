/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.LinkedList;
/*   5:    */import org.jaxen.JaxenException;
/*   6:    */import org.jaxen.JaxenHandler;
/*   7:    */import org.jaxen.expr.Expr;
/*   8:    */import org.jaxen.expr.FilterExpr;
/*   9:    */import org.jaxen.expr.XPathFactory;
/*  10:    */
/*  77:    */public class PatternHandler
/*  78:    */  extends JaxenHandler
/*  79:    */{
/*  80:    */  private Pattern pattern;
/*  81:    */  
/*  82:    */  public Pattern getPattern()
/*  83:    */  {
/*  84: 84 */    return getPattern(true);
/*  85:    */  }
/*  86:    */  
/*  99:    */  public Pattern getPattern(boolean shouldSimplify)
/* 100:    */  {
/* 101:101 */    if ((shouldSimplify) && (!this.simplified))
/* 102:    */    {
/* 104:104 */      this.pattern.simplify();
/* 105:105 */      this.simplified = true;
/* 106:    */    }
/* 107:    */    
/* 108:108 */    return this.pattern;
/* 109:    */  }
/* 110:    */  
/* 114:    */  public void endXPath()
/* 115:    */  {
/* 116:116 */    this.pattern = ((Pattern)pop());
/* 117:    */    
/* 118:118 */    System.out.println("stack is: " + this.stack);
/* 119:    */    
/* 120:120 */    popFrame();
/* 121:    */  }
/* 122:    */  
/* 138:    */  public void endPathExpr()
/* 139:    */  {
/* 140:140 */    LinkedList frame = popFrame();
/* 141:    */    
/* 142:142 */    System.out.println("endPathExpr(): " + frame);
/* 143:    */    
/* 144:144 */    push(frame.removeFirst());
/* 145:    */  }
/* 146:    */  
/* 174:    */  public void startAbsoluteLocationPath()
/* 175:    */  {
/* 176:176 */    pushFrame();
/* 177:    */    
/* 178:178 */    push(createAbsoluteLocationPath());
/* 179:    */  }
/* 180:    */  
/* 181:    */  public void endAbsoluteLocationPath()
/* 182:    */    throws JaxenException
/* 183:    */  {
/* 184:184 */    endLocationPath();
/* 185:    */  }
/* 186:    */  
/* 188:    */  public void startRelativeLocationPath()
/* 189:    */  {
/* 190:190 */    pushFrame();
/* 191:    */    
/* 192:192 */    push(createRelativeLocationPath());
/* 193:    */  }
/* 194:    */  
/* 195:    */  public void endRelativeLocationPath()
/* 196:    */    throws JaxenException
/* 197:    */  {
/* 198:198 */    endLocationPath();
/* 199:    */  }
/* 200:    */  
/* 201:    */  protected void endLocationPath()
/* 202:    */    throws JaxenException
/* 203:    */  {
/* 204:204 */    LinkedList list = popFrame();
/* 205:    */    
/* 206:206 */    System.out.println("endLocationPath: " + list);
/* 207:    */    
/* 208:208 */    LocationPathPattern locationPath = (LocationPathPattern)list.removeFirst();
/* 209:209 */    push(locationPath);
/* 210:210 */    boolean doneNodeTest = false;
/* 211:211 */    while (!list.isEmpty())
/* 212:    */    {
/* 213:213 */      Object filter = list.removeFirst();
/* 214:214 */      if ((filter instanceof NodeTest))
/* 215:    */      {
/* 216:216 */        if (doneNodeTest)
/* 217:    */        {
/* 218:218 */          LocationPathPattern parent = new LocationPathPattern((NodeTest)filter);
/* 219:219 */          locationPath.setParentPattern(parent);
/* 220:220 */          locationPath = parent;
/* 221:221 */          doneNodeTest = false;
/* 222:    */        }
/* 223:    */        else
/* 224:    */        {
/* 225:225 */          locationPath.setNodeTest((NodeTest)filter);
/* 226:    */        }
/* 227:    */      }
/* 228:228 */      else if ((filter instanceof FilterExpr))
/* 229:    */      {
/* 230:230 */        locationPath.addFilter((FilterExpr)filter);
/* 231:    */      }
/* 232:232 */      else if ((filter instanceof LocationPathPattern))
/* 233:    */      {
/* 234:234 */        LocationPathPattern parent = (LocationPathPattern)filter;
/* 235:235 */        locationPath.setParentPattern(parent);
/* 236:236 */        locationPath = parent;
/* 237:237 */        doneNodeTest = false;
/* 238:    */      }
/* 239:    */    }
/* 240:    */  }
/* 241:    */  
/* 246:    */  public void startNameStep(int axis, String prefix, String localName)
/* 247:    */  {
/* 248:248 */    pushFrame();
/* 249:    */    
/* 250:250 */    short nodeType = 1;
/* 251:251 */    switch (axis)
/* 252:    */    {
/* 253:    */    case 9: 
/* 254:254 */      nodeType = 2;
/* 255:255 */      break;
/* 256:    */    case 10: 
/* 257:257 */      nodeType = 13;
/* 258:    */    }
/* 259:    */    
/* 260:    */    
/* 261:261 */    if ((prefix != null) && (prefix.length() > 0) && (!prefix.equals("*")))
/* 262:    */    {
/* 263:263 */      push(new NamespaceTest(prefix, nodeType));
/* 264:    */    }
/* 265:265 */    if ((localName != null) && (localName.length() > 0) && (!localName.equals("*")))
/* 266:    */    {
/* 267:267 */      push(new NameTest(localName, nodeType));
/* 268:    */    }
/* 269:    */  }
/* 270:    */  
/* 272:    */  public void startTextNodeStep(int axis)
/* 273:    */  {
/* 274:274 */    pushFrame();
/* 275:    */    
/* 276:276 */    push(new NodeTypeTest((short)3));
/* 277:    */  }
/* 278:    */  
/* 280:    */  public void startCommentNodeStep(int axis)
/* 281:    */  {
/* 282:282 */    pushFrame();
/* 283:    */    
/* 284:284 */    push(new NodeTypeTest((short)8));
/* 285:    */  }
/* 286:    */  
/* 288:    */  public void startAllNodeStep(int axis)
/* 289:    */  {
/* 290:290 */    pushFrame();
/* 291:    */    
/* 292:292 */    push(AnyNodeTest.getInstance());
/* 293:    */  }
/* 294:    */  
/* 297:    */  public void startProcessingInstructionNodeStep(int axis, String name)
/* 298:    */  {
/* 299:299 */    pushFrame();
/* 300:    */    
/* 302:302 */    push(new NodeTypeTest((short)7));
/* 303:    */  }
/* 304:    */  
/* 305:    */  protected void endStep()
/* 306:    */  {
/* 307:307 */    LinkedList list = popFrame();
/* 308:308 */    if (!list.isEmpty())
/* 309:    */    {
/* 310:310 */      push(list.removeFirst());
/* 311:    */      
/* 312:312 */      if (!list.isEmpty())
/* 313:    */      {
/* 314:314 */        System.out.println("List should now be empty!" + list);
/* 315:    */      }
/* 316:    */    }
/* 317:    */  }
/* 318:    */  
/* 322:    */  public void startUnionExpr() {}
/* 323:    */  
/* 326:    */  public void endUnionExpr(boolean create)
/* 327:    */    throws JaxenException
/* 328:    */  {
/* 329:329 */    if (create)
/* 330:    */    {
/* 333:333 */      Expr rhs = (Expr)pop();
/* 334:334 */      Expr lhs = (Expr)pop();
/* 335:    */      
/* 336:336 */      push(getXPathFactory().createUnionExpr(lhs, rhs));
/* 337:    */    }
/* 338:    */  }
/* 339:    */  
/* 341:    */  protected Pattern createAbsoluteLocationPath()
/* 342:    */  {
/* 343:343 */    return new LocationPathPattern(NodeTypeTest.DOCUMENT_TEST);
/* 344:    */  }
/* 345:    */  
/* 346:    */  protected Pattern createRelativeLocationPath()
/* 347:    */  {
/* 348:348 */    return new LocationPathPattern();
/* 349:    */  }
/* 350:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.PatternHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */