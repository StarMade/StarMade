/*   1:    */package org.jasypt.intf.cli;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.Arrays;
/*   5:    */import java.util.HashSet;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.Map.Entry;
/*   8:    */import java.util.Properties;
/*   9:    */import java.util.Set;
/*  10:    */import org.jasypt.commons.CommonUtils;
/*  11:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*  12:    */
/*  41:    */final class CLIUtils
/*  42:    */{
/*  43:    */  static void showEnvironment(boolean verbose)
/*  44:    */  {
/*  45: 45 */    if (verbose) {
/*  46: 46 */      System.out.println("\n----ENVIRONMENT-----------------\n");
/*  47: 47 */      System.out.println("Runtime: " + System.getProperty("java.vm.vendor") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version") + " ");
/*  48:    */      
/*  51: 51 */      System.out.println("\n");
/*  52:    */    }
/*  53:    */  }
/*  54:    */  
/*  60:    */  static void showArgumentDescription(Properties argumentValues, boolean verbose)
/*  61:    */  {
/*  62: 62 */    if (verbose) {
/*  63: 63 */      System.out.println("\n----ARGUMENTS-------------------\n");
/*  64: 64 */      Iterator entriesIter = argumentValues.entrySet().iterator();
/*  65: 65 */      while (entriesIter.hasNext()) {
/*  66: 66 */        Map.Entry entry = (Map.Entry)entriesIter.next();
/*  67: 67 */        System.out.println(entry.getKey() + ": " + entry.getValue());
/*  68:    */      }
/*  69:    */      
/*  70: 70 */      System.out.println("\n");
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  79:    */  static void showOutput(String output, boolean verbose)
/*  80:    */  {
/*  81: 81 */    if (verbose) {
/*  82: 82 */      System.out.println("\n----OUTPUT----------------------\n");
/*  83: 83 */      System.out.println(output);
/*  84: 84 */      System.out.println("\n");
/*  85:    */    } else {
/*  86: 86 */      System.out.println(output);
/*  87:    */    }
/*  88:    */  }
/*  89:    */  
/*  95:    */  static void showError(Throwable t, boolean verbose)
/*  96:    */  {
/*  97: 97 */    if (verbose)
/*  98:    */    {
/*  99: 99 */      System.err.println("\n----ERROR-----------------------\n");
/* 100:100 */      if ((t instanceof EncryptionOperationNotPossibleException)) {
/* 101:101 */        System.err.println("Operation not possible (Bad input or parameters)");
/* 103:    */      }
/* 104:104 */      else if (t.getMessage() != null) {
/* 105:105 */        System.err.println(t.getMessage());
/* 106:    */      } else {
/* 107:107 */        System.err.println(t.getClass().getName());
/* 108:    */      }
/* 109:    */      
/* 110:110 */      System.err.println("\n");
/* 111:    */    }
/* 112:    */    else
/* 113:    */    {
/* 114:114 */      System.err.print("ERROR: ");
/* 115:115 */      if ((t instanceof EncryptionOperationNotPossibleException)) {
/* 116:116 */        System.err.println("Operation not possible (Bad input or parameters)");
/* 118:    */      }
/* 119:119 */      else if (t.getMessage() != null) {
/* 120:120 */        System.err.println(t.getMessage());
/* 121:    */      } else {
/* 122:122 */        System.err.println(t.getClass().getName());
/* 123:    */      }
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 133:    */  static boolean getVerbosity(String[] args)
/* 134:    */  {
/* 135:135 */    for (int i = 0; i < args.length; i++) {
/* 136:136 */      String key = CommonUtils.substringBefore(args[i], "=");
/* 137:137 */      String value = CommonUtils.substringAfter(args[i], "=");
/* 138:138 */      if ((!CommonUtils.isEmpty(key)) && (!CommonUtils.isEmpty(value)))
/* 139:    */      {
/* 141:141 */        if ("verbose".equals(key)) {
/* 142:142 */          Boolean verbosity = CommonUtils.getStandardBooleanValue(value);
/* 143:    */          
/* 144:144 */          return verbosity != null ? verbosity.booleanValue() : false;
/* 145:    */        } }
/* 146:    */    }
/* 147:147 */    return true;
/* 148:    */  }
/* 149:    */  
/* 155:    */  static Properties getArgumentValues(String appName, String[] args, String[][] requiredArgNames, String[][] optionalArgNames)
/* 156:    */  {
/* 157:157 */    Set argNames = new HashSet();
/* 158:158 */    for (int i = 0; i < requiredArgNames.length; i++) {
/* 159:159 */      argNames.addAll(Arrays.asList(requiredArgNames[i]));
/* 160:    */    }
/* 161:161 */    for (int i = 0; i < optionalArgNames.length; i++) {
/* 162:162 */      argNames.addAll(Arrays.asList(optionalArgNames[i]));
/* 163:    */    }
/* 164:    */    
/* 165:165 */    Properties argumentValues = new Properties();
/* 166:166 */    for (int i = 0; i < args.length; i++) {
/* 167:167 */      String key = CommonUtils.substringBefore(args[i], "=");
/* 168:168 */      String value = CommonUtils.substringAfter(args[i], "=");
/* 169:169 */      if ((CommonUtils.isEmpty(key)) || (CommonUtils.isEmpty(value))) {
/* 170:170 */        throw new IllegalArgumentException("Bad argument: " + args[i]);
/* 171:    */      }
/* 172:172 */      if (argNames.contains(key)) {
/* 173:173 */        if ((value.startsWith("\"")) && (value.endsWith("\""))) {
/* 174:174 */          argumentValues.setProperty(key, value.substring(1, value.length() - 1));
/* 175:    */        }
/* 176:    */        else
/* 177:    */        {
/* 178:178 */          argumentValues.setProperty(key, value);
/* 179:    */        }
/* 180:    */      } else {
/* 181:181 */        throw new IllegalArgumentException("Bad argument: " + args[i]);
/* 182:    */      }
/* 183:    */    }
/* 184:    */    
/* 186:186 */    for (int i = 0; i < requiredArgNames.length; i++) {
/* 187:187 */      boolean found = false;
/* 188:188 */      for (int j = 0; j < requiredArgNames[i].length; j++) {
/* 189:189 */        if (argumentValues.containsKey(requiredArgNames[i][j])) {
/* 190:190 */          found = true;
/* 191:    */        }
/* 192:    */      }
/* 193:193 */      if (!found) {
/* 194:194 */        showUsageAndExit(appName, requiredArgNames, optionalArgNames);
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 198:198 */    return argumentValues;
/* 199:    */  }
/* 200:    */  
/* 207:    */  static void showUsageAndExit(String appName, String[][] requiredArgNames, String[][] optionalArgNames)
/* 208:    */  {
/* 209:209 */    System.err.println("\nUSAGE: " + appName + " [ARGUMENTS]\n");
/* 210:210 */    System.err.println("  * Arguments must apply to format:\n");
/* 211:211 */    System.err.println("      \"arg1=value1 arg2=value2 arg3=value3 ...\"");
/* 212:    */    
/* 213:213 */    System.err.println();
/* 214:214 */    System.err.println("  * Required arguments:\n");
/* 215:215 */    for (int i = 0; i < requiredArgNames.length; i++) {
/* 216:216 */      System.err.print("      ");
/* 217:217 */      if (requiredArgNames[i].length == 1) {
/* 218:218 */        System.err.print(requiredArgNames[i][0]);
/* 219:    */      } else {
/* 220:220 */        System.err.print("(");
/* 221:221 */        for (int j = 0; j < requiredArgNames[i].length; j++) {
/* 222:222 */          if (j > 0) {
/* 223:223 */            System.err.print(" | ");
/* 224:    */          }
/* 225:225 */          System.err.print(requiredArgNames[i][j]);
/* 226:    */        }
/* 227:227 */        System.err.print(")");
/* 228:    */      }
/* 229:229 */      System.err.println();
/* 230:    */    }
/* 231:231 */    System.err.println();
/* 232:232 */    System.err.println("  * Optional arguments:\n");
/* 233:233 */    for (int i = 0; i < optionalArgNames.length; i++) {
/* 234:234 */      System.err.print("      ");
/* 235:235 */      if (optionalArgNames[i].length == 1) {
/* 236:236 */        System.err.print(optionalArgNames[i][0]);
/* 237:    */      } else {
/* 238:238 */        System.err.print("(");
/* 239:239 */        for (int j = 0; j < optionalArgNames[i].length; j++) {
/* 240:240 */          if (j > 0) {
/* 241:241 */            System.err.print(" | ");
/* 242:    */          }
/* 243:243 */          System.err.print(optionalArgNames[i][j]);
/* 244:    */        }
/* 245:245 */        System.err.print(")");
/* 246:    */      }
/* 247:247 */      System.err.println();
/* 248:    */    }
/* 249:249 */    System.exit(1);
/* 250:    */  }
/* 251:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.CLIUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */