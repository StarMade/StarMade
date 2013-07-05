/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class CommentHeader
/*     */ {
/*     */   public static final String TITLE = "TITLE";
/*     */   public static final String ARTIST = "ARTIST";
/*     */   public static final String ALBUM = "ALBUM";
/*     */   public static final String TRACKNUMBER = "TRACKNUMBER";
/*     */   public static final String VERSION = "VERSION";
/*     */   public static final String PERFORMER = "PERFORMER";
/*     */   public static final String COPYRIGHT = "COPYRIGHT";
/*     */   public static final String LICENSE = "LICENSE";
/*     */   public static final String ORGANIZATION = "ORGANIZATION";
/*     */   public static final String DESCRIPTION = "DESCRIPTION";
/*     */   public static final String GENRE = "GENRE";
/*     */   public static final String DATE = "DATE";
/*     */   public static final String LOCATION = "LOCATION";
/*     */   public static final String CONTACT = "CONTACT";
/*     */   public static final String ISRC = "ISRC";
/*     */   private String vendor;
/*  54 */   private HashMap comments = new HashMap();
/*     */   private boolean framingBit;
/*     */   private static final long HEADER = 126896460427126L;
/*     */ 
/*     */   public CommentHeader(BitInputStream source)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  60 */     if (source.getLong(48) != 126896460427126L) {
/*  61 */       throw new VorbisFormatException("The identification header has an illegal leading.");
/*     */     }
/*     */ 
/*  64 */     this.vendor = getString(source);
/*     */ 
/*  66 */     int ucLength = source.getInt(32);
/*     */ 
/*  68 */     for (int i = 0; i < ucLength; i++) {
/*  69 */       String comment = getString(source);
/*  70 */       int ix = comment.indexOf('=');
/*  71 */       String key = comment.substring(0, ix);
/*  72 */       String value = comment.substring(ix + 1);
/*     */ 
/*  74 */       addComment(key, value);
/*     */     }
/*     */ 
/*  77 */     this.framingBit = (source.getInt(8) != 0);
/*     */   }
/*     */ 
/*     */   private void addComment(String key, String value) {
/*  81 */     ArrayList al = (ArrayList)this.comments.get(key);
/*  82 */     if (al == null) {
/*  83 */       al = new ArrayList();
/*  84 */       this.comments.put(key, al);
/*     */     }
/*  86 */     al.add(value);
/*     */   }
/*     */ 
/*     */   public String getVendor() {
/*  90 */     return this.vendor;
/*     */   }
/*     */ 
/*     */   public String getComment(String key) {
/*  94 */     ArrayList al = (ArrayList)this.comments.get(key);
/*  95 */     return al == null ? (String)null : (String)al.get(0);
/*     */   }
/*     */ 
/*     */   public String[] getComments(String key) {
/*  99 */     ArrayList al = (ArrayList)this.comments.get(key);
/* 100 */     return al == null ? new String[0] : (String[])al.toArray(new String[al.size()]);
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 104 */     return getComment("TITLE");
/*     */   }
/*     */ 
/*     */   public String[] getTitles() {
/* 108 */     return getComments("TITLE");
/*     */   }
/*     */ 
/*     */   public String getVersion() {
/* 112 */     return getComment("VERSION");
/*     */   }
/*     */ 
/*     */   public String[] getVersions() {
/* 116 */     return getComments("VERSION");
/*     */   }
/*     */ 
/*     */   public String getAlbum() {
/* 120 */     return getComment("ALBUM");
/*     */   }
/*     */ 
/*     */   public String[] getAlbums() {
/* 124 */     return getComments("ALBUM");
/*     */   }
/*     */ 
/*     */   public String getTrackNumber() {
/* 128 */     return getComment("TRACKNUMBER");
/*     */   }
/*     */ 
/*     */   public String[] getTrackNumbers() {
/* 132 */     return getComments("TRACKNUMBER");
/*     */   }
/*     */ 
/*     */   public String getArtist() {
/* 136 */     return getComment("ARTIST");
/*     */   }
/*     */ 
/*     */   public String[] getArtists() {
/* 140 */     return getComments("ARTIST");
/*     */   }
/*     */ 
/*     */   public String getPerformer() {
/* 144 */     return getComment("PERFORMER");
/*     */   }
/*     */ 
/*     */   public String[] getPerformers() {
/* 148 */     return getComments("PERFORMER");
/*     */   }
/*     */ 
/*     */   public String getCopyright() {
/* 152 */     return getComment("COPYRIGHT");
/*     */   }
/*     */ 
/*     */   public String[] getCopyrights() {
/* 156 */     return getComments("COPYRIGHT");
/*     */   }
/*     */ 
/*     */   public String getLicense() {
/* 160 */     return getComment("LICENSE");
/*     */   }
/*     */ 
/*     */   public String[] getLicenses() {
/* 164 */     return getComments("LICENSE");
/*     */   }
/*     */ 
/*     */   public String getOrganization() {
/* 168 */     return getComment("ORGANIZATION");
/*     */   }
/*     */ 
/*     */   public String[] getOrganizations() {
/* 172 */     return getComments("ORGANIZATION");
/*     */   }
/*     */ 
/*     */   public String getDescription() {
/* 176 */     return getComment("DESCRIPTION");
/*     */   }
/*     */ 
/*     */   public String[] getDescriptions() {
/* 180 */     return getComments("DESCRIPTION");
/*     */   }
/*     */ 
/*     */   public String getGenre() {
/* 184 */     return getComment("GENRE");
/*     */   }
/*     */ 
/*     */   public String[] getGenres() {
/* 188 */     return getComments("GENRE");
/*     */   }
/*     */ 
/*     */   public String getDate() {
/* 192 */     return getComment("DATE");
/*     */   }
/*     */ 
/*     */   public String[] getDates() {
/* 196 */     return getComments("DATE");
/*     */   }
/*     */ 
/*     */   public String getLocation() {
/* 200 */     return getComment("LOCATION");
/*     */   }
/*     */ 
/*     */   public String[] getLocations() {
/* 204 */     return getComments("LOCATION");
/*     */   }
/*     */ 
/*     */   public String getContact() {
/* 208 */     return getComment("CONTACT");
/*     */   }
/*     */ 
/*     */   public String[] getContacts() {
/* 212 */     return getComments("CONTACT");
/*     */   }
/*     */ 
/*     */   public String getIsrc() {
/* 216 */     return getComment("ISRC");
/*     */   }
/*     */ 
/*     */   public String[] getIsrcs() {
/* 220 */     return getComments("ISRC");
/*     */   }
/*     */ 
/*     */   private String getString(BitInputStream source)
/*     */     throws IOException, VorbisFormatException
/*     */   {
/* 226 */     int length = source.getInt(32);
/*     */ 
/* 228 */     byte[] strArray = new byte[length];
/*     */ 
/* 230 */     for (int i = 0; i < length; i++) {
/* 231 */       strArray[i] = ((byte)source.getInt(8));
/*     */     }
/*     */ 
/* 234 */     return new String(strArray, "UTF-8");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.CommentHeader
 * JD-Core Version:    0.6.2
 */