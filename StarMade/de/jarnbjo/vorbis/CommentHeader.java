/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.HashMap;
/*   7:    */
/*  36:    */public class CommentHeader
/*  37:    */{
/*  38:    */  public static final String TITLE = "TITLE";
/*  39:    */  public static final String ARTIST = "ARTIST";
/*  40:    */  public static final String ALBUM = "ALBUM";
/*  41:    */  public static final String TRACKNUMBER = "TRACKNUMBER";
/*  42:    */  public static final String VERSION = "VERSION";
/*  43:    */  public static final String PERFORMER = "PERFORMER";
/*  44:    */  public static final String COPYRIGHT = "COPYRIGHT";
/*  45:    */  public static final String LICENSE = "LICENSE";
/*  46:    */  public static final String ORGANIZATION = "ORGANIZATION";
/*  47:    */  public static final String DESCRIPTION = "DESCRIPTION";
/*  48:    */  public static final String GENRE = "GENRE";
/*  49:    */  public static final String DATE = "DATE";
/*  50:    */  public static final String LOCATION = "LOCATION";
/*  51:    */  public static final String CONTACT = "CONTACT";
/*  52:    */  public static final String ISRC = "ISRC";
/*  53:    */  private String vendor;
/*  54: 54 */  private HashMap comments = new HashMap();
/*  55:    */  private boolean framingBit;
/*  56:    */  private static final long HEADER = 126896460427126L;
/*  57:    */  
/*  58:    */  public CommentHeader(BitInputStream source) throws VorbisFormatException, IOException
/*  59:    */  {
/*  60: 60 */    if (source.getLong(48) != 126896460427126L) {
/*  61: 61 */      throw new VorbisFormatException("The identification header has an illegal leading.");
/*  62:    */    }
/*  63:    */    
/*  64: 64 */    this.vendor = getString(source);
/*  65:    */    
/*  66: 66 */    int ucLength = source.getInt(32);
/*  67:    */    
/*  68: 68 */    for (int i = 0; i < ucLength; i++) {
/*  69: 69 */      String comment = getString(source);
/*  70: 70 */      int ix = comment.indexOf('=');
/*  71: 71 */      String key = comment.substring(0, ix);
/*  72: 72 */      String value = comment.substring(ix + 1);
/*  73:    */      
/*  74: 74 */      addComment(key, value);
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    this.framingBit = (source.getInt(8) != 0);
/*  78:    */  }
/*  79:    */  
/*  80:    */  private void addComment(String key, String value) {
/*  81: 81 */    ArrayList al = (ArrayList)this.comments.get(key);
/*  82: 82 */    if (al == null) {
/*  83: 83 */      al = new ArrayList();
/*  84: 84 */      this.comments.put(key, al);
/*  85:    */    }
/*  86: 86 */    al.add(value);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getVendor() {
/*  90: 90 */    return this.vendor;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public String getComment(String key) {
/*  94: 94 */    ArrayList al = (ArrayList)this.comments.get(key);
/*  95: 95 */    return al == null ? (String)null : (String)al.get(0);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public String[] getComments(String key) {
/*  99: 99 */    ArrayList al = (ArrayList)this.comments.get(key);
/* 100:100 */    return al == null ? new String[0] : (String[])al.toArray(new String[al.size()]);
/* 101:    */  }
/* 102:    */  
/* 103:    */  public String getTitle() {
/* 104:104 */    return getComment("TITLE");
/* 105:    */  }
/* 106:    */  
/* 107:    */  public String[] getTitles() {
/* 108:108 */    return getComments("TITLE");
/* 109:    */  }
/* 110:    */  
/* 111:    */  public String getVersion() {
/* 112:112 */    return getComment("VERSION");
/* 113:    */  }
/* 114:    */  
/* 115:    */  public String[] getVersions() {
/* 116:116 */    return getComments("VERSION");
/* 117:    */  }
/* 118:    */  
/* 119:    */  public String getAlbum() {
/* 120:120 */    return getComment("ALBUM");
/* 121:    */  }
/* 122:    */  
/* 123:    */  public String[] getAlbums() {
/* 124:124 */    return getComments("ALBUM");
/* 125:    */  }
/* 126:    */  
/* 127:    */  public String getTrackNumber() {
/* 128:128 */    return getComment("TRACKNUMBER");
/* 129:    */  }
/* 130:    */  
/* 131:    */  public String[] getTrackNumbers() {
/* 132:132 */    return getComments("TRACKNUMBER");
/* 133:    */  }
/* 134:    */  
/* 135:    */  public String getArtist() {
/* 136:136 */    return getComment("ARTIST");
/* 137:    */  }
/* 138:    */  
/* 139:    */  public String[] getArtists() {
/* 140:140 */    return getComments("ARTIST");
/* 141:    */  }
/* 142:    */  
/* 143:    */  public String getPerformer() {
/* 144:144 */    return getComment("PERFORMER");
/* 145:    */  }
/* 146:    */  
/* 147:    */  public String[] getPerformers() {
/* 148:148 */    return getComments("PERFORMER");
/* 149:    */  }
/* 150:    */  
/* 151:    */  public String getCopyright() {
/* 152:152 */    return getComment("COPYRIGHT");
/* 153:    */  }
/* 154:    */  
/* 155:    */  public String[] getCopyrights() {
/* 156:156 */    return getComments("COPYRIGHT");
/* 157:    */  }
/* 158:    */  
/* 159:    */  public String getLicense() {
/* 160:160 */    return getComment("LICENSE");
/* 161:    */  }
/* 162:    */  
/* 163:    */  public String[] getLicenses() {
/* 164:164 */    return getComments("LICENSE");
/* 165:    */  }
/* 166:    */  
/* 167:    */  public String getOrganization() {
/* 168:168 */    return getComment("ORGANIZATION");
/* 169:    */  }
/* 170:    */  
/* 171:    */  public String[] getOrganizations() {
/* 172:172 */    return getComments("ORGANIZATION");
/* 173:    */  }
/* 174:    */  
/* 175:    */  public String getDescription() {
/* 176:176 */    return getComment("DESCRIPTION");
/* 177:    */  }
/* 178:    */  
/* 179:    */  public String[] getDescriptions() {
/* 180:180 */    return getComments("DESCRIPTION");
/* 181:    */  }
/* 182:    */  
/* 183:    */  public String getGenre() {
/* 184:184 */    return getComment("GENRE");
/* 185:    */  }
/* 186:    */  
/* 187:    */  public String[] getGenres() {
/* 188:188 */    return getComments("GENRE");
/* 189:    */  }
/* 190:    */  
/* 191:    */  public String getDate() {
/* 192:192 */    return getComment("DATE");
/* 193:    */  }
/* 194:    */  
/* 195:    */  public String[] getDates() {
/* 196:196 */    return getComments("DATE");
/* 197:    */  }
/* 198:    */  
/* 199:    */  public String getLocation() {
/* 200:200 */    return getComment("LOCATION");
/* 201:    */  }
/* 202:    */  
/* 203:    */  public String[] getLocations() {
/* 204:204 */    return getComments("LOCATION");
/* 205:    */  }
/* 206:    */  
/* 207:    */  public String getContact() {
/* 208:208 */    return getComment("CONTACT");
/* 209:    */  }
/* 210:    */  
/* 211:    */  public String[] getContacts() {
/* 212:212 */    return getComments("CONTACT");
/* 213:    */  }
/* 214:    */  
/* 215:    */  public String getIsrc() {
/* 216:216 */    return getComment("ISRC");
/* 217:    */  }
/* 218:    */  
/* 219:    */  public String[] getIsrcs() {
/* 220:220 */    return getComments("ISRC");
/* 221:    */  }
/* 222:    */  
/* 223:    */  private String getString(BitInputStream source)
/* 224:    */    throws IOException, VorbisFormatException
/* 225:    */  {
/* 226:226 */    int length = source.getInt(32);
/* 227:    */    
/* 228:228 */    byte[] strArray = new byte[length];
/* 229:    */    
/* 230:230 */    for (int i = 0; i < length; i++) {
/* 231:231 */      strArray[i] = ((byte)source.getInt(8));
/* 232:    */    }
/* 233:    */    
/* 234:234 */    return new String(strArray, "UTF-8");
/* 235:    */  }
/* 236:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.CommentHeader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */