/*   1:    */package org.jaxen.saxpath;
/*   2:    */
/*   3:    */import org.jaxen.JaxenRuntimeException;
/*   4:    */
/* 103:    */public class Axis
/* 104:    */{
/* 105:    */  public static final int INVALID_AXIS = 0;
/* 106:    */  public static final int CHILD = 1;
/* 107:    */  public static final int DESCENDANT = 2;
/* 108:    */  public static final int PARENT = 3;
/* 109:    */  public static final int ANCESTOR = 4;
/* 110:    */  public static final int FOLLOWING_SIBLING = 5;
/* 111:    */  public static final int PRECEDING_SIBLING = 6;
/* 112:    */  public static final int FOLLOWING = 7;
/* 113:    */  public static final int PRECEDING = 8;
/* 114:    */  public static final int ATTRIBUTE = 9;
/* 115:    */  public static final int NAMESPACE = 10;
/* 116:    */  public static final int SELF = 11;
/* 117:    */  public static final int DESCENDANT_OR_SELF = 12;
/* 118:    */  public static final int ANCESTOR_OR_SELF = 13;
/* 119:    */  
/* 120:    */  public static String lookup(int axisNum)
/* 121:    */  {
/* 122:122 */    switch (axisNum)
/* 123:    */    {
/* 124:    */    case 1: 
/* 125:125 */      return "child";
/* 126:    */    
/* 127:    */    case 2: 
/* 128:128 */      return "descendant";
/* 129:    */    
/* 130:    */    case 3: 
/* 131:131 */      return "parent";
/* 132:    */    
/* 133:    */    case 4: 
/* 134:134 */      return "ancestor";
/* 135:    */    
/* 136:    */    case 5: 
/* 137:137 */      return "following-sibling";
/* 138:    */    
/* 139:    */    case 6: 
/* 140:140 */      return "preceding-sibling";
/* 141:    */    
/* 142:    */    case 7: 
/* 143:143 */      return "following";
/* 144:    */    
/* 145:    */    case 8: 
/* 146:146 */      return "preceding";
/* 147:    */    
/* 148:    */    case 9: 
/* 149:149 */      return "attribute";
/* 150:    */    
/* 151:    */    case 10: 
/* 152:152 */      return "namespace";
/* 153:    */    
/* 154:    */    case 11: 
/* 155:155 */      return "self";
/* 156:    */    
/* 157:    */    case 12: 
/* 158:158 */      return "descendant-or-self";
/* 159:    */    
/* 160:    */    case 13: 
/* 161:161 */      return "ancestor-or-self";
/* 162:    */    }
/* 163:    */    
/* 164:164 */    throw new JaxenRuntimeException("Illegal Axis Number");
/* 165:    */  }
/* 166:    */  
/* 178:    */  public static int lookup(String axisName)
/* 179:    */  {
/* 180:180 */    if ("child".equals(axisName))
/* 181:    */    {
/* 182:182 */      return 1;
/* 183:    */    }
/* 184:    */    
/* 185:185 */    if ("descendant".equals(axisName))
/* 186:    */    {
/* 187:187 */      return 2;
/* 188:    */    }
/* 189:    */    
/* 190:190 */    if ("parent".equals(axisName))
/* 191:    */    {
/* 192:192 */      return 3;
/* 193:    */    }
/* 194:    */    
/* 195:195 */    if ("ancestor".equals(axisName))
/* 196:    */    {
/* 197:197 */      return 4;
/* 198:    */    }
/* 199:    */    
/* 200:200 */    if ("following-sibling".equals(axisName))
/* 201:    */    {
/* 202:202 */      return 5;
/* 203:    */    }
/* 204:    */    
/* 205:205 */    if ("preceding-sibling".equals(axisName))
/* 206:    */    {
/* 207:207 */      return 6;
/* 208:    */    }
/* 209:    */    
/* 210:210 */    if ("following".equals(axisName))
/* 211:    */    {
/* 212:212 */      return 7;
/* 213:    */    }
/* 214:    */    
/* 215:215 */    if ("preceding".equals(axisName))
/* 216:    */    {
/* 217:217 */      return 8;
/* 218:    */    }
/* 219:    */    
/* 220:220 */    if ("attribute".equals(axisName))
/* 221:    */    {
/* 222:222 */      return 9;
/* 223:    */    }
/* 224:    */    
/* 225:225 */    if ("namespace".equals(axisName))
/* 226:    */    {
/* 227:227 */      return 10;
/* 228:    */    }
/* 229:    */    
/* 230:230 */    if ("self".equals(axisName))
/* 231:    */    {
/* 232:232 */      return 11;
/* 233:    */    }
/* 234:    */    
/* 235:235 */    if ("descendant-or-self".equals(axisName))
/* 236:    */    {
/* 237:237 */      return 12;
/* 238:    */    }
/* 239:    */    
/* 240:240 */    if ("ancestor-or-self".equals(axisName))
/* 241:    */    {
/* 242:242 */      return 13;
/* 243:    */    }
/* 244:    */    
/* 245:245 */    return 0;
/* 246:    */  }
/* 247:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.Axis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */