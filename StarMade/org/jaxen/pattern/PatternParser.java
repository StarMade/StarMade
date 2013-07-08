/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import java.util.ListIterator;
/*   6:    */import org.jaxen.JaxenException;
/*   7:    */import org.jaxen.JaxenHandler;
/*   8:    */import org.jaxen.expr.DefaultAllNodeStep;
/*   9:    */import org.jaxen.expr.DefaultCommentNodeStep;
/*  10:    */import org.jaxen.expr.DefaultFilterExpr;
/*  11:    */import org.jaxen.expr.DefaultNameStep;
/*  12:    */import org.jaxen.expr.DefaultProcessingInstructionNodeStep;
/*  13:    */import org.jaxen.expr.DefaultStep;
/*  14:    */import org.jaxen.expr.DefaultTextNodeStep;
/*  15:    */import org.jaxen.expr.DefaultXPathFactory;
/*  16:    */import org.jaxen.expr.Expr;
/*  17:    */import org.jaxen.expr.FilterExpr;
/*  18:    */import org.jaxen.expr.LocationPath;
/*  19:    */import org.jaxen.expr.Predicate;
/*  20:    */import org.jaxen.expr.PredicateSet;
/*  21:    */import org.jaxen.expr.Step;
/*  22:    */import org.jaxen.expr.UnionExpr;
/*  23:    */import org.jaxen.expr.XPathExpr;
/*  24:    */import org.jaxen.saxpath.SAXPathException;
/*  25:    */import org.jaxen.saxpath.XPathReader;
/*  26:    */import org.jaxen.saxpath.helpers.XPathReaderFactory;
/*  27:    */
/*  93:    */public class PatternParser
/*  94:    */{
/*  95:    */  private static final boolean TRACE = false;
/*  96:    */  private static final boolean USE_HANDLER = false;
/*  97:    */  
/*  98:    */  public static Pattern parse(String text)
/*  99:    */    throws JaxenException, SAXPathException
/* 100:    */  {
/* 101:101 */    XPathReader reader = XPathReaderFactory.createReader();
/* 102:102 */    JaxenHandler handler = new JaxenHandler();
/* 103:    */    
/* 104:104 */    handler.setXPathFactory(new DefaultXPathFactory());
/* 105:105 */    reader.setXPathHandler(handler);
/* 106:106 */    reader.parse(text);
/* 107:    */    
/* 108:108 */    Pattern pattern = convertExpr(handler.getXPathExpr().getRootExpr());
/* 109:109 */    return pattern.simplify();
/* 110:    */  }
/* 111:    */  
/* 117:    */  protected static Pattern convertExpr(Expr expr)
/* 118:    */    throws JaxenException
/* 119:    */  {
/* 120:120 */    if ((expr instanceof LocationPath))
/* 121:    */    {
/* 122:122 */      return convertExpr((LocationPath)expr);
/* 123:    */    }
/* 124:124 */    if ((expr instanceof FilterExpr))
/* 125:    */    {
/* 126:126 */      LocationPathPattern answer = new LocationPathPattern();
/* 127:127 */      answer.addFilter((FilterExpr)expr);
/* 128:128 */      return answer;
/* 129:    */    }
/* 130:130 */    if ((expr instanceof UnionExpr))
/* 131:    */    {
/* 132:132 */      UnionExpr unionExpr = (UnionExpr)expr;
/* 133:133 */      Pattern lhs = convertExpr(unionExpr.getLHS());
/* 134:134 */      Pattern rhs = convertExpr(unionExpr.getRHS());
/* 135:135 */      return new UnionPattern(lhs, rhs);
/* 136:    */    }
/* 137:    */    
/* 139:139 */    LocationPathPattern answer = new LocationPathPattern();
/* 140:140 */    answer.addFilter(new DefaultFilterExpr(expr, new PredicateSet()));
/* 141:    */    
/* 142:142 */    return answer;
/* 143:    */  }
/* 144:    */  
/* 145:    */  protected static LocationPathPattern convertExpr(LocationPath locationPath)
/* 146:    */    throws JaxenException
/* 147:    */  {
/* 148:148 */    LocationPathPattern answer = new LocationPathPattern();
/* 149:    */    
/* 150:150 */    List steps = locationPath.getSteps();
/* 151:    */    
/* 153:153 */    LocationPathPattern path = answer;
/* 154:154 */    boolean first = true;
/* 155:155 */    for (ListIterator iter = steps.listIterator(steps.size()); iter.hasPrevious();)
/* 156:    */    {
/* 157:157 */      Step step = (Step)iter.previous();
/* 158:158 */      if (first)
/* 159:    */      {
/* 160:160 */        first = false;
/* 161:161 */        path = convertStep(path, step);
/* 162:    */      }
/* 163:    */      else
/* 164:    */      {
/* 165:165 */        if (navigationStep(step))
/* 166:    */        {
/* 167:167 */          LocationPathPattern parent = new LocationPathPattern();
/* 168:168 */          int axis = step.getAxis();
/* 169:169 */          if ((axis == 2) || (axis == 12))
/* 170:    */          {
/* 171:171 */            path.setAncestorPattern(parent);
/* 172:    */          }
/* 173:    */          else
/* 174:    */          {
/* 175:175 */            path.setParentPattern(parent);
/* 176:    */          }
/* 177:177 */          path = parent;
/* 178:    */        }
/* 179:179 */        path = convertStep(path, step);
/* 180:    */      }
/* 181:    */    }
/* 182:182 */    if (locationPath.isAbsolute())
/* 183:    */    {
/* 184:184 */      LocationPathPattern parent = new LocationPathPattern(NodeTypeTest.DOCUMENT_TEST);
/* 185:185 */      path.setParentPattern(parent);
/* 186:    */    }
/* 187:187 */    return answer;
/* 188:    */  }
/* 189:    */  
/* 190:    */  protected static LocationPathPattern convertStep(LocationPathPattern path, Step step) throws JaxenException
/* 191:    */  {
/* 192:192 */    if ((step instanceof DefaultAllNodeStep))
/* 193:    */    {
/* 194:194 */      int axis = step.getAxis();
/* 195:195 */      if (axis == 9)
/* 196:    */      {
/* 197:197 */        path.setNodeTest(NodeTypeTest.ATTRIBUTE_TEST);
/* 198:    */      }
/* 199:    */      else
/* 200:    */      {
/* 201:201 */        path.setNodeTest(NodeTypeTest.ELEMENT_TEST);
/* 202:    */      }
/* 203:    */    }
/* 204:204 */    else if ((step instanceof DefaultCommentNodeStep))
/* 205:    */    {
/* 206:206 */      path.setNodeTest(NodeTypeTest.COMMENT_TEST);
/* 207:    */    }
/* 208:208 */    else if ((step instanceof DefaultProcessingInstructionNodeStep))
/* 209:    */    {
/* 210:210 */      path.setNodeTest(NodeTypeTest.PROCESSING_INSTRUCTION_TEST);
/* 211:    */    }
/* 212:212 */    else if ((step instanceof DefaultTextNodeStep))
/* 213:    */    {
/* 214:214 */      path.setNodeTest(TextNodeTest.SINGLETON);
/* 215:    */    }
/* 216:216 */    else if ((step instanceof DefaultCommentNodeStep))
/* 217:    */    {
/* 218:218 */      path.setNodeTest(NodeTypeTest.COMMENT_TEST);
/* 219:    */    } else {
/* 220:220 */      if ((step instanceof DefaultNameStep))
/* 221:    */      {
/* 222:222 */        DefaultNameStep nameStep = (DefaultNameStep)step;
/* 223:223 */        String localName = nameStep.getLocalName();
/* 224:224 */        String prefix = nameStep.getPrefix();
/* 225:225 */        int axis = nameStep.getAxis();
/* 226:226 */        short nodeType = 1;
/* 227:227 */        if (axis == 9)
/* 228:    */        {
/* 229:229 */          nodeType = 2;
/* 230:    */        }
/* 231:231 */        if (nameStep.isMatchesAnyName())
/* 232:    */        {
/* 233:233 */          if ((prefix.length() == 0) || (prefix.equals("*")))
/* 234:    */          {
/* 235:235 */            if (axis == 9)
/* 236:    */            {
/* 237:237 */              path.setNodeTest(NodeTypeTest.ATTRIBUTE_TEST);
/* 238:    */            }
/* 239:    */            else
/* 240:    */            {
/* 241:241 */              path.setNodeTest(NodeTypeTest.ELEMENT_TEST);
/* 242:    */            }
/* 243:    */            
/* 244:    */          }
/* 245:    */          else {
/* 246:246 */            path.setNodeTest(new NamespaceTest(prefix, nodeType));
/* 247:    */          }
/* 248:    */          
/* 249:    */        }
/* 250:    */        else {
/* 251:251 */          path.setNodeTest(new NameTest(localName, nodeType));
/* 252:    */        }
/* 253:    */        
/* 254:254 */        return convertDefaultStep(path, nameStep);
/* 255:    */      }
/* 256:256 */      if ((step instanceof DefaultStep))
/* 257:    */      {
/* 258:258 */        return convertDefaultStep(path, (DefaultStep)step);
/* 259:    */      }
/* 260:    */      
/* 262:262 */      throw new JaxenException("Cannot convert: " + step + " to a Pattern");
/* 263:    */    }
/* 264:264 */    return path;
/* 265:    */  }
/* 266:    */  
/* 267:    */  protected static LocationPathPattern convertDefaultStep(LocationPathPattern path, DefaultStep step) throws JaxenException
/* 268:    */  {
/* 269:269 */    List predicates = step.getPredicates();
/* 270:270 */    if (!predicates.isEmpty())
/* 271:    */    {
/* 272:272 */      FilterExpr filter = new DefaultFilterExpr(new PredicateSet());
/* 273:273 */      for (Iterator iter = predicates.iterator(); iter.hasNext();)
/* 274:    */      {
/* 275:275 */        filter.addPredicate((Predicate)iter.next());
/* 276:    */      }
/* 277:277 */      path.addFilter(filter);
/* 278:    */    }
/* 279:279 */    return path;
/* 280:    */  }
/* 281:    */  
/* 282:    */  protected static boolean navigationStep(Step step)
/* 283:    */  {
/* 284:284 */    if ((step instanceof DefaultNameStep))
/* 285:    */    {
/* 286:286 */      return true;
/* 287:    */    }
/* 288:    */    
/* 289:289 */    if (step.getClass().equals(DefaultStep.class))
/* 290:    */    {
/* 291:291 */      return !step.getPredicates().isEmpty();
/* 292:    */    }
/* 293:    */    
/* 295:295 */    return true;
/* 296:    */  }
/* 297:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.PatternParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */