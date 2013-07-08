/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import org.jaxen.JaxenException;
/*   4:    */import org.jaxen.expr.iter.IterableAncestorAxis;
/*   5:    */import org.jaxen.expr.iter.IterableAncestorOrSelfAxis;
/*   6:    */import org.jaxen.expr.iter.IterableAttributeAxis;
/*   7:    */import org.jaxen.expr.iter.IterableAxis;
/*   8:    */import org.jaxen.expr.iter.IterableChildAxis;
/*   9:    */import org.jaxen.expr.iter.IterableDescendantAxis;
/*  10:    */import org.jaxen.expr.iter.IterableDescendantOrSelfAxis;
/*  11:    */import org.jaxen.expr.iter.IterableFollowingAxis;
/*  12:    */import org.jaxen.expr.iter.IterableFollowingSiblingAxis;
/*  13:    */import org.jaxen.expr.iter.IterableNamespaceAxis;
/*  14:    */import org.jaxen.expr.iter.IterableParentAxis;
/*  15:    */import org.jaxen.expr.iter.IterablePrecedingAxis;
/*  16:    */import org.jaxen.expr.iter.IterablePrecedingSiblingAxis;
/*  17:    */import org.jaxen.expr.iter.IterableSelfAxis;
/*  18:    */
/*  72:    */public class DefaultXPathFactory
/*  73:    */  implements XPathFactory
/*  74:    */{
/*  75:    */  public XPathExpr createXPath(Expr rootExpr)
/*  76:    */    throws JaxenException
/*  77:    */  {
/*  78: 78 */    return new DefaultXPathExpr(rootExpr);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public PathExpr createPathExpr(FilterExpr filterExpr, LocationPath locationPath)
/*  82:    */    throws JaxenException
/*  83:    */  {
/*  84: 84 */    return new DefaultPathExpr(filterExpr, locationPath);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public LocationPath createRelativeLocationPath()
/*  88:    */    throws JaxenException
/*  89:    */  {
/*  90: 90 */    return new DefaultRelativeLocationPath();
/*  91:    */  }
/*  92:    */  
/*  93:    */  public LocationPath createAbsoluteLocationPath() throws JaxenException
/*  94:    */  {
/*  95: 95 */    return new DefaultAbsoluteLocationPath();
/*  96:    */  }
/*  97:    */  
/*  98:    */  public BinaryExpr createOrExpr(Expr lhs, Expr rhs)
/*  99:    */    throws JaxenException
/* 100:    */  {
/* 101:101 */    return new DefaultOrExpr(lhs, rhs);
/* 102:    */  }
/* 103:    */  
/* 105:    */  public BinaryExpr createAndExpr(Expr lhs, Expr rhs)
/* 106:    */    throws JaxenException
/* 107:    */  {
/* 108:108 */    return new DefaultAndExpr(lhs, rhs);
/* 109:    */  }
/* 110:    */  
/* 113:    */  public BinaryExpr createEqualityExpr(Expr lhs, Expr rhs, int equalityOperator)
/* 114:    */    throws JaxenException
/* 115:    */  {
/* 116:116 */    switch (equalityOperator)
/* 117:    */    {
/* 119:    */    case 1: 
/* 120:120 */      return new DefaultEqualsExpr(lhs, rhs);
/* 121:    */    
/* 124:    */    case 2: 
/* 125:125 */      return new DefaultNotEqualsExpr(lhs, rhs);
/* 126:    */    }
/* 127:    */    
/* 128:    */    
/* 129:129 */    throw new JaxenException("Unhandled operator in createEqualityExpr(): " + equalityOperator);
/* 130:    */  }
/* 131:    */  
/* 133:    */  public BinaryExpr createRelationalExpr(Expr lhs, Expr rhs, int relationalOperator)
/* 134:    */    throws JaxenException
/* 135:    */  {
/* 136:136 */    switch (relationalOperator)
/* 137:    */    {
/* 139:    */    case 3: 
/* 140:140 */      return new DefaultLessThanExpr(lhs, rhs);
/* 141:    */    
/* 144:    */    case 5: 
/* 145:145 */      return new DefaultGreaterThanExpr(lhs, rhs);
/* 146:    */    
/* 149:    */    case 4: 
/* 150:150 */      return new DefaultLessThanEqualExpr(lhs, rhs);
/* 151:    */    
/* 154:    */    case 6: 
/* 155:155 */      return new DefaultGreaterThanEqualExpr(lhs, rhs);
/* 156:    */    }
/* 157:    */    
/* 158:    */    
/* 159:159 */    throw new JaxenException("Unhandled operator in createRelationalExpr(): " + relationalOperator);
/* 160:    */  }
/* 161:    */  
/* 163:    */  public BinaryExpr createAdditiveExpr(Expr lhs, Expr rhs, int additiveOperator)
/* 164:    */    throws JaxenException
/* 165:    */  {
/* 166:166 */    switch (additiveOperator)
/* 167:    */    {
/* 169:    */    case 7: 
/* 170:170 */      return new DefaultPlusExpr(lhs, rhs);
/* 171:    */    
/* 174:    */    case 8: 
/* 175:175 */      return new DefaultMinusExpr(lhs, rhs);
/* 176:    */    }
/* 177:    */    
/* 178:    */    
/* 179:179 */    throw new JaxenException("Unhandled operator in createAdditiveExpr(): " + additiveOperator);
/* 180:    */  }
/* 181:    */  
/* 183:    */  public BinaryExpr createMultiplicativeExpr(Expr lhs, Expr rhs, int multiplicativeOperator)
/* 184:    */    throws JaxenException
/* 185:    */  {
/* 186:186 */    switch (multiplicativeOperator)
/* 187:    */    {
/* 189:    */    case 9: 
/* 190:190 */      return new DefaultMultiplyExpr(lhs, rhs);
/* 191:    */    
/* 194:    */    case 11: 
/* 195:195 */      return new DefaultDivExpr(lhs, rhs);
/* 196:    */    
/* 199:    */    case 10: 
/* 200:200 */      return new DefaultModExpr(lhs, rhs);
/* 201:    */    }
/* 202:    */    
/* 203:    */    
/* 204:204 */    throw new JaxenException("Unhandled operator in createMultiplicativeExpr(): " + multiplicativeOperator);
/* 205:    */  }
/* 206:    */  
/* 207:    */  public Expr createUnaryExpr(Expr expr, int unaryOperator)
/* 208:    */    throws JaxenException
/* 209:    */  {
/* 210:210 */    switch (unaryOperator)
/* 211:    */    {
/* 213:    */    case 12: 
/* 214:214 */      return new DefaultUnaryExpr(expr);
/* 215:    */    }
/* 216:    */    
/* 217:217 */    return expr;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public UnionExpr createUnionExpr(Expr lhs, Expr rhs)
/* 221:    */    throws JaxenException
/* 222:    */  {
/* 223:223 */    return new DefaultUnionExpr(lhs, rhs);
/* 224:    */  }
/* 225:    */  
/* 226:    */  public FilterExpr createFilterExpr(Expr expr)
/* 227:    */    throws JaxenException
/* 228:    */  {
/* 229:229 */    return new DefaultFilterExpr(expr, createPredicateSet());
/* 230:    */  }
/* 231:    */  
/* 232:    */  public FunctionCallExpr createFunctionCallExpr(String prefix, String functionName)
/* 233:    */    throws JaxenException
/* 234:    */  {
/* 235:235 */    return new DefaultFunctionCallExpr(prefix, functionName);
/* 236:    */  }
/* 237:    */  
/* 238:    */  public NumberExpr createNumberExpr(int number)
/* 239:    */    throws JaxenException
/* 240:    */  {
/* 241:241 */    return new DefaultNumberExpr(new Double(number));
/* 242:    */  }
/* 243:    */  
/* 244:    */  public NumberExpr createNumberExpr(double number) throws JaxenException
/* 245:    */  {
/* 246:246 */    return new DefaultNumberExpr(new Double(number));
/* 247:    */  }
/* 248:    */  
/* 249:    */  public LiteralExpr createLiteralExpr(String literal) throws JaxenException
/* 250:    */  {
/* 251:251 */    return new DefaultLiteralExpr(literal);
/* 252:    */  }
/* 253:    */  
/* 254:    */  public VariableReferenceExpr createVariableReferenceExpr(String prefix, String variable)
/* 255:    */    throws JaxenException
/* 256:    */  {
/* 257:257 */    return new DefaultVariableReferenceExpr(prefix, variable);
/* 258:    */  }
/* 259:    */  
/* 262:    */  public Step createNameStep(int axis, String prefix, String localName)
/* 263:    */    throws JaxenException
/* 264:    */  {
/* 265:265 */    IterableAxis iter = getIterableAxis(axis);
/* 266:266 */    return new DefaultNameStep(iter, prefix, localName, createPredicateSet());
/* 267:    */  }
/* 268:    */  
/* 271:    */  public Step createTextNodeStep(int axis)
/* 272:    */    throws JaxenException
/* 273:    */  {
/* 274:274 */    IterableAxis iter = getIterableAxis(axis);
/* 275:275 */    return new DefaultTextNodeStep(iter, createPredicateSet());
/* 276:    */  }
/* 277:    */  
/* 278:    */  public Step createCommentNodeStep(int axis) throws JaxenException
/* 279:    */  {
/* 280:280 */    IterableAxis iter = getIterableAxis(axis);
/* 281:281 */    return new DefaultCommentNodeStep(iter, createPredicateSet());
/* 282:    */  }
/* 283:    */  
/* 284:    */  public Step createAllNodeStep(int axis) throws JaxenException
/* 285:    */  {
/* 286:286 */    IterableAxis iter = getIterableAxis(axis);
/* 287:287 */    return new DefaultAllNodeStep(iter, createPredicateSet());
/* 288:    */  }
/* 289:    */  
/* 290:    */  public Step createProcessingInstructionNodeStep(int axis, String piName)
/* 291:    */    throws JaxenException
/* 292:    */  {
/* 293:293 */    IterableAxis iter = getIterableAxis(axis);
/* 294:294 */    return new DefaultProcessingInstructionNodeStep(iter, piName, createPredicateSet());
/* 295:    */  }
/* 296:    */  
/* 298:    */  public Predicate createPredicate(Expr predicateExpr)
/* 299:    */    throws JaxenException
/* 300:    */  {
/* 301:301 */    return new DefaultPredicate(predicateExpr);
/* 302:    */  }
/* 303:    */  
/* 304:    */  protected IterableAxis getIterableAxis(int axis)
/* 305:    */    throws JaxenException
/* 306:    */  {
/* 307:307 */    switch (axis)
/* 308:    */    {
/* 309:    */    case 1: 
/* 310:310 */      return new IterableChildAxis(axis);
/* 311:    */    case 2: 
/* 312:312 */      return new IterableDescendantAxis(axis);
/* 313:    */    case 3: 
/* 314:314 */      return new IterableParentAxis(axis);
/* 315:    */    case 5: 
/* 316:316 */      return new IterableFollowingSiblingAxis(axis);
/* 317:    */    case 6: 
/* 318:318 */      return new IterablePrecedingSiblingAxis(axis);
/* 319:    */    case 7: 
/* 320:320 */      return new IterableFollowingAxis(axis);
/* 321:    */    case 8: 
/* 322:322 */      return new IterablePrecedingAxis(axis);
/* 323:    */    case 9: 
/* 324:324 */      return new IterableAttributeAxis(axis);
/* 325:    */    case 10: 
/* 326:326 */      return new IterableNamespaceAxis(axis);
/* 327:    */    case 11: 
/* 328:328 */      return new IterableSelfAxis(axis);
/* 329:    */    case 12: 
/* 330:330 */      return new IterableDescendantOrSelfAxis(axis);
/* 331:    */    case 13: 
/* 332:332 */      return new IterableAncestorOrSelfAxis(axis);
/* 333:    */    case 4: 
/* 334:334 */      return new IterableAncestorAxis(axis);
/* 335:    */    }
/* 336:336 */    throw new JaxenException("Unrecognized axis code: " + axis);
/* 337:    */  }
/* 338:    */  
/* 340:    */  public PredicateSet createPredicateSet()
/* 341:    */    throws JaxenException
/* 342:    */  {
/* 343:343 */    return new PredicateSet();
/* 344:    */  }
/* 345:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultXPathFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */