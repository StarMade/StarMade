/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.Collections;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import org.jaxen.Context;
/*   9:    */import org.jaxen.ContextSupport;
/*  10:    */import org.jaxen.JaxenException;
/*  11:    */import org.jaxen.function.BooleanFunction;
/*  12:    */
/*  80:    */public class PredicateSet
/*  81:    */  implements Serializable
/*  82:    */{
/*  83:    */  private static final long serialVersionUID = -7166491740228977853L;
/*  84:    */  private List predicates;
/*  85:    */  
/*  86:    */  public PredicateSet()
/*  87:    */  {
/*  88: 88 */    this.predicates = Collections.EMPTY_LIST;
/*  89:    */  }
/*  90:    */  
/*  96:    */  public void addPredicate(Predicate predicate)
/*  97:    */  {
/*  98: 98 */    if (this.predicates == Collections.EMPTY_LIST)
/*  99:    */    {
/* 100:100 */      this.predicates = new ArrayList();
/* 101:    */    }
/* 102:    */    
/* 103:103 */    this.predicates.add(predicate);
/* 104:    */  }
/* 105:    */  
/* 112:    */  public List getPredicates()
/* 113:    */  {
/* 114:114 */    return this.predicates;
/* 115:    */  }
/* 116:    */  
/* 120:    */  public void simplify()
/* 121:    */  {
/* 122:122 */    Iterator predIter = this.predicates.iterator();
/* 123:123 */    Predicate eachPred = null;
/* 124:    */    
/* 125:125 */    while (predIter.hasNext())
/* 126:    */    {
/* 127:127 */      eachPred = (Predicate)predIter.next();
/* 128:128 */      eachPred.simplify();
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 137:    */  public String getText()
/* 138:    */  {
/* 139:139 */    StringBuffer buf = new StringBuffer();
/* 140:    */    
/* 141:141 */    Iterator predIter = this.predicates.iterator();
/* 142:142 */    Predicate eachPred = null;
/* 143:    */    
/* 144:144 */    while (predIter.hasNext())
/* 145:    */    {
/* 146:146 */      eachPred = (Predicate)predIter.next();
/* 147:147 */      buf.append(eachPred.getText());
/* 148:    */    }
/* 149:    */    
/* 150:150 */    return buf.toString();
/* 151:    */  }
/* 152:    */  
/* 164:    */  protected boolean evaluateAsBoolean(List contextNodeSet, ContextSupport support)
/* 165:    */    throws JaxenException
/* 166:    */  {
/* 167:167 */    return anyMatchingNode(contextNodeSet, support);
/* 168:    */  }
/* 169:    */  
/* 170:    */  private boolean anyMatchingNode(List contextNodeSet, ContextSupport support)
/* 171:    */    throws JaxenException
/* 172:    */  {
/* 173:173 */    if (this.predicates.size() == 0) {
/* 174:174 */      return false;
/* 175:    */    }
/* 176:176 */    Iterator predIter = this.predicates.iterator();
/* 177:    */    
/* 179:179 */    List nodes2Filter = contextNodeSet;
/* 180:    */    
/* 181:181 */    while (predIter.hasNext()) {
/* 182:182 */      int nodes2FilterSize = nodes2Filter.size();
/* 183:    */      
/* 184:184 */      Context predContext = new Context(support);
/* 185:185 */      List tempList = new ArrayList(1);
/* 186:186 */      predContext.setNodeSet(tempList);
/* 187:    */      
/* 189:189 */      for (int i = 0; i < nodes2FilterSize; i++) {
/* 190:190 */        Object contextNode = nodes2Filter.get(i);
/* 191:191 */        tempList.clear();
/* 192:192 */        tempList.add(contextNode);
/* 193:193 */        predContext.setNodeSet(tempList);
/* 194:    */        
/* 195:195 */        predContext.setPosition(i + 1);
/* 196:196 */        predContext.setSize(nodes2FilterSize);
/* 197:197 */        Object predResult = ((Predicate)predIter.next()).evaluate(predContext);
/* 198:198 */        if ((predResult instanceof Number))
/* 199:    */        {
/* 201:201 */          int proximity = ((Number)predResult).intValue();
/* 202:202 */          if (proximity == i + 1) {
/* 203:203 */            return true;
/* 204:    */          }
/* 205:    */        }
/* 206:    */        else {
/* 207:207 */          Boolean includes = BooleanFunction.evaluate(predResult, predContext.getNavigator());
/* 208:    */          
/* 210:210 */          if (includes.booleanValue()) {
/* 211:211 */            return true;
/* 212:    */          }
/* 213:    */        }
/* 214:    */      }
/* 215:    */    }
/* 216:    */    
/* 217:217 */    return false;
/* 218:    */  }
/* 219:    */  
/* 232:    */  protected List evaluatePredicates(List contextNodeSet, ContextSupport support)
/* 233:    */    throws JaxenException
/* 234:    */  {
/* 235:235 */    if (this.predicates.size() == 0) {
/* 236:236 */      return contextNodeSet;
/* 237:    */    }
/* 238:238 */    Iterator predIter = this.predicates.iterator();
/* 239:    */    
/* 241:241 */    List nodes2Filter = contextNodeSet;
/* 242:    */    
/* 243:243 */    while (predIter.hasNext()) {
/* 244:244 */      nodes2Filter = applyPredicate((Predicate)predIter.next(), nodes2Filter, support);
/* 245:    */    }
/* 246:    */    
/* 248:248 */    return nodes2Filter;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public List applyPredicate(Predicate predicate, List nodes2Filter, ContextSupport support) throws JaxenException
/* 252:    */  {
/* 253:253 */    int nodes2FilterSize = nodes2Filter.size();
/* 254:254 */    List filteredNodes = new ArrayList(nodes2FilterSize);
/* 255:    */    
/* 256:256 */    Context predContext = new Context(support);
/* 257:257 */    List tempList = new ArrayList(1);
/* 258:258 */    predContext.setNodeSet(tempList);
/* 259:    */    
/* 261:261 */    for (int i = 0; i < nodes2FilterSize; i++) {
/* 262:262 */      Object contextNode = nodes2Filter.get(i);
/* 263:263 */      tempList.clear();
/* 264:264 */      tempList.add(contextNode);
/* 265:265 */      predContext.setNodeSet(tempList);
/* 266:    */      
/* 267:267 */      predContext.setPosition(i + 1);
/* 268:268 */      predContext.setSize(nodes2FilterSize);
/* 269:269 */      Object predResult = predicate.evaluate(predContext);
/* 270:270 */      if ((predResult instanceof Number))
/* 271:    */      {
/* 273:273 */        int proximity = ((Number)predResult).intValue();
/* 274:274 */        if (proximity == i + 1) {
/* 275:275 */          filteredNodes.add(contextNode);
/* 276:    */        }
/* 277:    */      }
/* 278:    */      else {
/* 279:279 */        Boolean includes = BooleanFunction.evaluate(predResult, predContext.getNavigator());
/* 280:    */        
/* 282:282 */        if (includes.booleanValue()) {
/* 283:283 */          filteredNodes.add(contextNode);
/* 284:    */        }
/* 285:    */      }
/* 286:    */    }
/* 287:287 */    return filteredNodes;
/* 288:    */  }
/* 289:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.PredicateSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */