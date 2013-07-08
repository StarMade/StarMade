package org.w3c.tidy;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public final class EntityTable
{
  private static EntityTable defaultEntityTable;
  private static Entity[] entities = { new Entity("nbsp", 160), new Entity("iexcl", 161), new Entity("cent", 162), new Entity("pound", 163), new Entity("curren", 164), new Entity("yen", 165), new Entity("brvbar", 166), new Entity("sect", 167), new Entity("uml", 168), new Entity("copy", 169), new Entity("ordf", 170), new Entity("laquo", 171), new Entity("not", 172), new Entity("shy", 173), new Entity("reg", 174), new Entity("macr", 175), new Entity("deg", 176), new Entity("plusmn", 177), new Entity("sup2", 178), new Entity("sup3", 179), new Entity("acute", 180), new Entity("micro", 181), new Entity("para", 182), new Entity("middot", 183), new Entity("cedil", 184), new Entity("sup1", 185), new Entity("ordm", 186), new Entity("raquo", 187), new Entity("frac14", 188), new Entity("frac12", 189), new Entity("frac34", 190), new Entity("iquest", 191), new Entity("Agrave", 192), new Entity("Aacute", 193), new Entity("Acirc", 194), new Entity("Atilde", 195), new Entity("Auml", 196), new Entity("Aring", 197), new Entity("AElig", 198), new Entity("Ccedil", 199), new Entity("Egrave", 200), new Entity("Eacute", 201), new Entity("Ecirc", 202), new Entity("Euml", 203), new Entity("Igrave", 204), new Entity("Iacute", 205), new Entity("Icirc", 206), new Entity("Iuml", 207), new Entity("ETH", 208), new Entity("Ntilde", 209), new Entity("Ograve", 210), new Entity("Oacute", 211), new Entity("Ocirc", 212), new Entity("Otilde", 213), new Entity("Ouml", 214), new Entity("times", 215), new Entity("Oslash", 216), new Entity("Ugrave", 217), new Entity("Uacute", 218), new Entity("Ucirc", 219), new Entity("Uuml", 220), new Entity("Yacute", 221), new Entity("THORN", 222), new Entity("szlig", 223), new Entity("agrave", 224), new Entity("aacute", 225), new Entity("acirc", 226), new Entity("atilde", 227), new Entity("auml", 228), new Entity("aring", 229), new Entity("aelig", 230), new Entity("ccedil", 231), new Entity("egrave", 232), new Entity("eacute", 233), new Entity("ecirc", 234), new Entity("euml", 235), new Entity("igrave", 236), new Entity("iacute", 237), new Entity("icirc", 238), new Entity("iuml", 239), new Entity("eth", 240), new Entity("ntilde", 241), new Entity("ograve", 242), new Entity("oacute", 243), new Entity("ocirc", 244), new Entity("otilde", 245), new Entity("ouml", 246), new Entity("divide", 247), new Entity("oslash", 248), new Entity("ugrave", 249), new Entity("uacute", 250), new Entity("ucirc", 251), new Entity("uuml", 252), new Entity("yacute", 253), new Entity("thorn", 254), new Entity("yuml", 255), new Entity("fnof", 402), new Entity("Alpha", 913), new Entity("Beta", 914), new Entity("Gamma", 915), new Entity("Delta", 916), new Entity("Epsilon", 917), new Entity("Zeta", 918), new Entity("Eta", 919), new Entity("Theta", 920), new Entity("Iota", 921), new Entity("Kappa", 922), new Entity("Lambda", 923), new Entity("Mu", 924), new Entity("Nu", 925), new Entity("Xi", 926), new Entity("Omicron", 927), new Entity("Pi", 928), new Entity("Rho", 929), new Entity("Sigma", 931), new Entity("Tau", 932), new Entity("Upsilon", 933), new Entity("Phi", 934), new Entity("Chi", 935), new Entity("Psi", 936), new Entity("Omega", 937), new Entity("alpha", 945), new Entity("beta", 946), new Entity("gamma", 947), new Entity("delta", 948), new Entity("epsilon", 949), new Entity("zeta", 950), new Entity("eta", 951), new Entity("theta", 952), new Entity("iota", 953), new Entity("kappa", 954), new Entity("lambda", 955), new Entity("mu", 956), new Entity("nu", 957), new Entity("xi", 958), new Entity("omicron", 959), new Entity("pi", 960), new Entity("rho", 961), new Entity("sigmaf", 962), new Entity("sigma", 963), new Entity("tau", 964), new Entity("upsilon", 965), new Entity("phi", 966), new Entity("chi", 967), new Entity("psi", 968), new Entity("omega", 969), new Entity("thetasym", 977), new Entity("upsih", 978), new Entity("piv", 982), new Entity("bull", 8226), new Entity("hellip", 8230), new Entity("prime", 8242), new Entity("Prime", 8243), new Entity("oline", 8254), new Entity("frasl", 8260), new Entity("weierp", 8472), new Entity("image", 8465), new Entity("real", 8476), new Entity("trade", 8482), new Entity("alefsym", 8501), new Entity("larr", 8592), new Entity("uarr", 8593), new Entity("rarr", 8594), new Entity("darr", 8595), new Entity("harr", 8596), new Entity("crarr", 8629), new Entity("lArr", 8656), new Entity("uArr", 8657), new Entity("rArr", 8658), new Entity("dArr", 8659), new Entity("hArr", 8660), new Entity("forall", 8704), new Entity("part", 8706), new Entity("exist", 8707), new Entity("empty", 8709), new Entity("nabla", 8711), new Entity("isin", 8712), new Entity("notin", 8713), new Entity("ni", 8715), new Entity("prod", 8719), new Entity("sum", 8721), new Entity("minus", 8722), new Entity("lowast", 8727), new Entity("radic", 8730), new Entity("prop", 8733), new Entity("infin", 8734), new Entity("ang", 8736), new Entity("and", 8743), new Entity("or", 8744), new Entity("cap", 8745), new Entity("cup", 8746), new Entity("int", 8747), new Entity("there4", 8756), new Entity("sim", 8764), new Entity("cong", 8773), new Entity("asymp", 8776), new Entity("ne", 8800), new Entity("equiv", 8801), new Entity("le", 8804), new Entity("ge", 8805), new Entity("sub", 8834), new Entity("sup", 8835), new Entity("nsub", 8836), new Entity("sube", 8838), new Entity("supe", 8839), new Entity("oplus", 8853), new Entity("otimes", 8855), new Entity("perp", 8869), new Entity("sdot", 8901), new Entity("lceil", 8968), new Entity("rceil", 8969), new Entity("lfloor", 8970), new Entity("rfloor", 8971), new Entity("lang", 9001), new Entity("rang", 9002), new Entity("loz", 9674), new Entity("spades", 9824), new Entity("clubs", 9827), new Entity("hearts", 9829), new Entity("diams", 9830), new Entity("quot", 34), new Entity("amp", 38), new Entity("lt", 60), new Entity("gt", 62), new Entity("OElig", 338), new Entity("oelig", 339), new Entity("Scaron", 352), new Entity("scaron", 353), new Entity("Yuml", 376), new Entity("circ", 710), new Entity("tilde", 732), new Entity("ensp", 8194), new Entity("emsp", 8195), new Entity("thinsp", 8201), new Entity("zwnj", 8204), new Entity("zwj", 8205), new Entity("lrm", 8206), new Entity("rlm", 8207), new Entity("ndash", 8211), new Entity("mdash", 8212), new Entity("lsquo", 8216), new Entity("rsquo", 8217), new Entity("sbquo", 8218), new Entity("ldquo", 8220), new Entity("rdquo", 8221), new Entity("bdquo", 8222), new Entity("dagger", 8224), new Entity("Dagger", 8225), new Entity("permil", 8240), new Entity("lsaquo", 8249), new Entity("rsaquo", 8250), new Entity("euro", 8364) };
  private Map entityHashtable = new Hashtable();
  
  private Entity install(Entity paramEntity)
  {
    return (Entity)this.entityHashtable.put(paramEntity.getName(), paramEntity);
  }
  
  public Entity lookup(String paramString)
  {
    return (Entity)this.entityHashtable.get(paramString);
  }
  
  public int entityCode(String paramString)
  {
    if (paramString.length() <= 1) {
      return 0;
    }
    if (paramString.charAt(1) == '#')
    {
      int i = 0;
      try
      {
        if ((paramString.length() >= 4) && (paramString.charAt(2) == 'x')) {
          i = Integer.parseInt(paramString.substring(3), 16);
        } else if (paramString.length() >= 3) {
          i = Integer.parseInt(paramString.substring(2));
        }
      }
      catch (NumberFormatException localNumberFormatException) {}
      return i;
    }
    Entity localEntity = lookup(paramString.substring(1));
    if (localEntity != null) {
      return localEntity.getCode();
    }
    return 0;
  }
  
  public String entityName(short paramShort)
  {
    String str = null;
    Iterator localIterator = this.entityHashtable.values().iterator();
    while (localIterator.hasNext())
    {
      Entity localEntity = (Entity)localIterator.next();
      if (localEntity.getCode() == paramShort) {
        str = localEntity.getName();
      }
    }
    return str;
  }
  
  public static EntityTable getDefaultEntityTable()
  {
    if (defaultEntityTable == null)
    {
      defaultEntityTable = new EntityTable();
      for (int i = 0; i < entities.length; i++) {
        defaultEntityTable.install(entities[i]);
      }
    }
    return defaultEntityTable;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.EntityTable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */