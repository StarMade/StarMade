package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CommentHeader
{
  public static final String TITLE = "TITLE";
  public static final String ARTIST = "ARTIST";
  public static final String ALBUM = "ALBUM";
  public static final String TRACKNUMBER = "TRACKNUMBER";
  public static final String VERSION = "VERSION";
  public static final String PERFORMER = "PERFORMER";
  public static final String COPYRIGHT = "COPYRIGHT";
  public static final String LICENSE = "LICENSE";
  public static final String ORGANIZATION = "ORGANIZATION";
  public static final String DESCRIPTION = "DESCRIPTION";
  public static final String GENRE = "GENRE";
  public static final String DATE = "DATE";
  public static final String LOCATION = "LOCATION";
  public static final String CONTACT = "CONTACT";
  public static final String ISRC = "ISRC";
  private String vendor;
  private HashMap comments = new HashMap();
  private boolean framingBit;
  private static final long HEADER = 126896460427126L;
  
  public CommentHeader(BitInputStream source)
    throws VorbisFormatException, IOException
  {
    if (source.getLong(48) != 126896460427126L) {
      throw new VorbisFormatException("The identification header has an illegal leading.");
    }
    this.vendor = getString(source);
    int ucLength = source.getInt(32);
    for (int local_i = 0; local_i < ucLength; local_i++)
    {
      String comment = getString(source);
      int local_ix = comment.indexOf('=');
      String key = comment.substring(0, local_ix);
      String value = comment.substring(local_ix + 1);
      addComment(key, value);
    }
    this.framingBit = (source.getInt(8) != 0);
  }
  
  private void addComment(String key, String value)
  {
    ArrayList local_al = (ArrayList)this.comments.get(key);
    if (local_al == null)
    {
      local_al = new ArrayList();
      this.comments.put(key, local_al);
    }
    local_al.add(value);
  }
  
  public String getVendor()
  {
    return this.vendor;
  }
  
  public String getComment(String key)
  {
    ArrayList local_al = (ArrayList)this.comments.get(key);
    return local_al == null ? (String)null : (String)local_al.get(0);
  }
  
  public String[] getComments(String key)
  {
    ArrayList local_al = (ArrayList)this.comments.get(key);
    return local_al == null ? new String[0] : (String[])local_al.toArray(new String[local_al.size()]);
  }
  
  public String getTitle()
  {
    return getComment("TITLE");
  }
  
  public String[] getTitles()
  {
    return getComments("TITLE");
  }
  
  public String getVersion()
  {
    return getComment("VERSION");
  }
  
  public String[] getVersions()
  {
    return getComments("VERSION");
  }
  
  public String getAlbum()
  {
    return getComment("ALBUM");
  }
  
  public String[] getAlbums()
  {
    return getComments("ALBUM");
  }
  
  public String getTrackNumber()
  {
    return getComment("TRACKNUMBER");
  }
  
  public String[] getTrackNumbers()
  {
    return getComments("TRACKNUMBER");
  }
  
  public String getArtist()
  {
    return getComment("ARTIST");
  }
  
  public String[] getArtists()
  {
    return getComments("ARTIST");
  }
  
  public String getPerformer()
  {
    return getComment("PERFORMER");
  }
  
  public String[] getPerformers()
  {
    return getComments("PERFORMER");
  }
  
  public String getCopyright()
  {
    return getComment("COPYRIGHT");
  }
  
  public String[] getCopyrights()
  {
    return getComments("COPYRIGHT");
  }
  
  public String getLicense()
  {
    return getComment("LICENSE");
  }
  
  public String[] getLicenses()
  {
    return getComments("LICENSE");
  }
  
  public String getOrganization()
  {
    return getComment("ORGANIZATION");
  }
  
  public String[] getOrganizations()
  {
    return getComments("ORGANIZATION");
  }
  
  public String getDescription()
  {
    return getComment("DESCRIPTION");
  }
  
  public String[] getDescriptions()
  {
    return getComments("DESCRIPTION");
  }
  
  public String getGenre()
  {
    return getComment("GENRE");
  }
  
  public String[] getGenres()
  {
    return getComments("GENRE");
  }
  
  public String getDate()
  {
    return getComment("DATE");
  }
  
  public String[] getDates()
  {
    return getComments("DATE");
  }
  
  public String getLocation()
  {
    return getComment("LOCATION");
  }
  
  public String[] getLocations()
  {
    return getComments("LOCATION");
  }
  
  public String getContact()
  {
    return getComment("CONTACT");
  }
  
  public String[] getContacts()
  {
    return getComments("CONTACT");
  }
  
  public String getIsrc()
  {
    return getComment("ISRC");
  }
  
  public String[] getIsrcs()
  {
    return getComments("ISRC");
  }
  
  private String getString(BitInputStream source)
    throws IOException, VorbisFormatException
  {
    int length = source.getInt(32);
    byte[] strArray = new byte[length];
    for (int local_i = 0; local_i < length; local_i++) {
      strArray[local_i] = ((byte)source.getInt(8));
    }
    return new String(strArray, "UTF-8");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.CommentHeader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */