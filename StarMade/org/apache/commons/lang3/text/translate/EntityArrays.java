/*   1:    */package org.apache.commons.lang3.text.translate;
/*   2:    */
/*  32:    */public class EntityArrays
/*  33:    */{
/*  34: 34 */  public static String[][] ISO8859_1_ESCAPE() { return (String[][])ISO8859_1_ESCAPE.clone(); }
/*  35: 35 */  private static final String[][] ISO8859_1_ESCAPE = { { " ", "&nbsp;" }, { "¡", "&iexcl;" }, { "¢", "&cent;" }, { "£", "&pound;" }, { "¤", "&curren;" }, { "¥", "&yen;" }, { "¦", "&brvbar;" }, { "§", "&sect;" }, { "¨", "&uml;" }, { "©", "&copy;" }, { "ª", "&ordf;" }, { "«", "&laquo;" }, { "¬", "&not;" }, { "­", "&shy;" }, { "®", "&reg;" }, { "¯", "&macr;" }, { "°", "&deg;" }, { "±", "&plusmn;" }, { "²", "&sup2;" }, { "³", "&sup3;" }, { "´", "&acute;" }, { "µ", "&micro;" }, { "¶", "&para;" }, { "·", "&middot;" }, { "¸", "&cedil;" }, { "¹", "&sup1;" }, { "º", "&ordm;" }, { "»", "&raquo;" }, { "¼", "&frac14;" }, { "½", "&frac12;" }, { "¾", "&frac34;" }, { "¿", "&iquest;" }, { "À", "&Agrave;" }, { "Á", "&Aacute;" }, { "Â", "&Acirc;" }, { "Ã", "&Atilde;" }, { "Ä", "&Auml;" }, { "Å", "&Aring;" }, { "Æ", "&AElig;" }, { "Ç", "&Ccedil;" }, { "È", "&Egrave;" }, { "É", "&Eacute;" }, { "Ê", "&Ecirc;" }, { "Ë", "&Euml;" }, { "Ì", "&Igrave;" }, { "Í", "&Iacute;" }, { "Î", "&Icirc;" }, { "Ï", "&Iuml;" }, { "Ð", "&ETH;" }, { "Ñ", "&Ntilde;" }, { "Ò", "&Ograve;" }, { "Ó", "&Oacute;" }, { "Ô", "&Ocirc;" }, { "Õ", "&Otilde;" }, { "Ö", "&Ouml;" }, { "×", "&times;" }, { "Ø", "&Oslash;" }, { "Ù", "&Ugrave;" }, { "Ú", "&Uacute;" }, { "Û", "&Ucirc;" }, { "Ü", "&Uuml;" }, { "Ý", "&Yacute;" }, { "Þ", "&THORN;" }, { "ß", "&szlig;" }, { "à", "&agrave;" }, { "á", "&aacute;" }, { "â", "&acirc;" }, { "ã", "&atilde;" }, { "ä", "&auml;" }, { "å", "&aring;" }, { "æ", "&aelig;" }, { "ç", "&ccedil;" }, { "è", "&egrave;" }, { "é", "&eacute;" }, { "ê", "&ecirc;" }, { "ë", "&euml;" }, { "ì", "&igrave;" }, { "í", "&iacute;" }, { "î", "&icirc;" }, { "ï", "&iuml;" }, { "ð", "&eth;" }, { "ñ", "&ntilde;" }, { "ò", "&ograve;" }, { "ó", "&oacute;" }, { "ô", "&ocirc;" }, { "õ", "&otilde;" }, { "ö", "&ouml;" }, { "÷", "&divide;" }, { "ø", "&oslash;" }, { "ù", "&ugrave;" }, { "ú", "&uacute;" }, { "û", "&ucirc;" }, { "ü", "&uuml;" }, { "ý", "&yacute;" }, { "þ", "&thorn;" }, { "ÿ", "&yuml;" } };
/*  36:    */  
/* 138:138 */  public static String[][] ISO8859_1_UNESCAPE() { return (String[][])ISO8859_1_UNESCAPE.clone(); }
/* 139:139 */  private static final String[][] ISO8859_1_UNESCAPE = invert(ISO8859_1_ESCAPE);
/* 140:    */  
/* 147:147 */  public static String[][] HTML40_EXTENDED_ESCAPE() { return (String[][])HTML40_EXTENDED_ESCAPE.clone(); }
/* 148:148 */  private static final String[][] HTML40_EXTENDED_ESCAPE = { { "ƒ", "&fnof;" }, { "Α", "&Alpha;" }, { "Β", "&Beta;" }, { "Γ", "&Gamma;" }, { "Δ", "&Delta;" }, { "Ε", "&Epsilon;" }, { "Ζ", "&Zeta;" }, { "Η", "&Eta;" }, { "Θ", "&Theta;" }, { "Ι", "&Iota;" }, { "Κ", "&Kappa;" }, { "Λ", "&Lambda;" }, { "Μ", "&Mu;" }, { "Ν", "&Nu;" }, { "Ξ", "&Xi;" }, { "Ο", "&Omicron;" }, { "Π", "&Pi;" }, { "Ρ", "&Rho;" }, { "Σ", "&Sigma;" }, { "Τ", "&Tau;" }, { "Υ", "&Upsilon;" }, { "Φ", "&Phi;" }, { "Χ", "&Chi;" }, { "Ψ", "&Psi;" }, { "Ω", "&Omega;" }, { "α", "&alpha;" }, { "β", "&beta;" }, { "γ", "&gamma;" }, { "δ", "&delta;" }, { "ε", "&epsilon;" }, { "ζ", "&zeta;" }, { "η", "&eta;" }, { "θ", "&theta;" }, { "ι", "&iota;" }, { "κ", "&kappa;" }, { "λ", "&lambda;" }, { "μ", "&mu;" }, { "ν", "&nu;" }, { "ξ", "&xi;" }, { "ο", "&omicron;" }, { "π", "&pi;" }, { "ρ", "&rho;" }, { "ς", "&sigmaf;" }, { "σ", "&sigma;" }, { "τ", "&tau;" }, { "υ", "&upsilon;" }, { "φ", "&phi;" }, { "χ", "&chi;" }, { "ψ", "&psi;" }, { "ω", "&omega;" }, { "ϑ", "&thetasym;" }, { "ϒ", "&upsih;" }, { "ϖ", "&piv;" }, { "•", "&bull;" }, { "…", "&hellip;" }, { "′", "&prime;" }, { "″", "&Prime;" }, { "‾", "&oline;" }, { "⁄", "&frasl;" }, { "℘", "&weierp;" }, { "ℑ", "&image;" }, { "ℜ", "&real;" }, { "™", "&trade;" }, { "ℵ", "&alefsym;" }, { "←", "&larr;" }, { "↑", "&uarr;" }, { "→", "&rarr;" }, { "↓", "&darr;" }, { "↔", "&harr;" }, { "↵", "&crarr;" }, { "⇐", "&lArr;" }, { "⇑", "&uArr;" }, { "⇒", "&rArr;" }, { "⇓", "&dArr;" }, { "⇔", "&hArr;" }, { "∀", "&forall;" }, { "∂", "&part;" }, { "∃", "&exist;" }, { "∅", "&empty;" }, { "∇", "&nabla;" }, { "∈", "&isin;" }, { "∉", "&notin;" }, { "∋", "&ni;" }, { "∏", "&prod;" }, { "∑", "&sum;" }, { "−", "&minus;" }, { "∗", "&lowast;" }, { "√", "&radic;" }, { "∝", "&prop;" }, { "∞", "&infin;" }, { "∠", "&ang;" }, { "∧", "&and;" }, { "∨", "&or;" }, { "∩", "&cap;" }, { "∪", "&cup;" }, { "∫", "&int;" }, { "∴", "&there4;" }, { "∼", "&sim;" }, { "≅", "&cong;" }, { "≈", "&asymp;" }, { "≠", "&ne;" }, { "≡", "&equiv;" }, { "≤", "&le;" }, { "≥", "&ge;" }, { "⊂", "&sub;" }, { "⊃", "&sup;" }, { "⊆", "&sube;" }, { "⊇", "&supe;" }, { "⊕", "&oplus;" }, { "⊗", "&otimes;" }, { "⊥", "&perp;" }, { "⋅", "&sdot;" }, { "⌈", "&lceil;" }, { "⌉", "&rceil;" }, { "⌊", "&lfloor;" }, { "⌋", "&rfloor;" }, { "〈", "&lang;" }, { "〉", "&rang;" }, { "◊", "&loz;" }, { "♠", "&spades;" }, { "♣", "&clubs;" }, { "♥", "&hearts;" }, { "♦", "&diams;" }, { "Œ", "&OElig;" }, { "œ", "&oelig;" }, { "Š", "&Scaron;" }, { "š", "&scaron;" }, { "Ÿ", "&Yuml;" }, { "ˆ", "&circ;" }, { "˜", "&tilde;" }, { " ", "&ensp;" }, { " ", "&emsp;" }, { " ", "&thinsp;" }, { "‌", "&zwnj;" }, { "‍", "&zwj;" }, { "‎", "&lrm;" }, { "‏", "&rlm;" }, { "–", "&ndash;" }, { "—", "&mdash;" }, { "‘", "&lsquo;" }, { "’", "&rsquo;" }, { "‚", "&sbquo;" }, { "“", "&ldquo;" }, { "”", "&rdquo;" }, { "„", "&bdquo;" }, { "†", "&dagger;" }, { "‡", "&Dagger;" }, { "‰", "&permil;" }, { "‹", "&lsaquo;" }, { "›", "&rsaquo;" }, { "€", "&euro;" } };
/* 149:    */  
/* 349:349 */  public static String[][] HTML40_EXTENDED_UNESCAPE() { return (String[][])HTML40_EXTENDED_UNESCAPE.clone(); }
/* 350:350 */  private static final String[][] HTML40_EXTENDED_UNESCAPE = invert(HTML40_EXTENDED_ESCAPE);
/* 351:    */  
/* 358:358 */  public static String[][] BASIC_ESCAPE() { return (String[][])BASIC_ESCAPE.clone(); }
/* 359:359 */  private static final String[][] BASIC_ESCAPE = { { "\"", "&quot;" }, { "&", "&amp;" }, { "<", "&lt;" }, { ">", "&gt;" } };
/* 360:    */  
/* 370:370 */  public static String[][] BASIC_UNESCAPE() { return (String[][])BASIC_UNESCAPE.clone(); }
/* 371:371 */  private static final String[][] BASIC_UNESCAPE = invert(BASIC_ESCAPE);
/* 372:    */  
/* 377:377 */  public static String[][] APOS_ESCAPE() { return (String[][])APOS_ESCAPE.clone(); }
/* 378:378 */  private static final String[][] APOS_ESCAPE = { { "'", "&apos;" } };
/* 379:    */  
/* 386:386 */  public static String[][] APOS_UNESCAPE() { return (String[][])APOS_UNESCAPE.clone(); }
/* 387:387 */  private static final String[][] APOS_UNESCAPE = invert(APOS_ESCAPE);
/* 388:    */  
/* 395:395 */  public static String[][] JAVA_CTRL_CHARS_ESCAPE() { return (String[][])JAVA_CTRL_CHARS_ESCAPE.clone(); }
/* 396:396 */  private static final String[][] JAVA_CTRL_CHARS_ESCAPE = { { "\b", "\\b" }, { "\n", "\\n" }, { "\t", "\\t" }, { "\f", "\\f" }, { "\r", "\\r" } };
/* 397:    */  
/* 408:408 */  public static String[][] JAVA_CTRL_CHARS_UNESCAPE() { return (String[][])JAVA_CTRL_CHARS_UNESCAPE.clone(); }
/* 409:409 */  private static final String[][] JAVA_CTRL_CHARS_UNESCAPE = invert(JAVA_CTRL_CHARS_ESCAPE);
/* 410:    */  
/* 415:    */  public static String[][] invert(String[][] array)
/* 416:    */  {
/* 417:417 */    String[][] newarray = new String[array.length][2];
/* 418:418 */    for (int i = 0; i < array.length; i++) {
/* 419:419 */      newarray[i][0] = array[i][1];
/* 420:420 */      newarray[i][1] = array[i][0];
/* 421:    */    }
/* 422:422 */    return newarray;
/* 423:    */  }
/* 424:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.EntityArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */