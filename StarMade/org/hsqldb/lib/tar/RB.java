package org.hsqldb.lib.tar;

import org.hsqldb.lib.RefCapableRBInterface;
import org.hsqldb.lib.ValidatingResourceBundle;

public enum RB
  implements RefCapableRBInterface
{
  DbBackup_syntax,  DbBackup_syntaxerr,  TarGenerator_syntax,  pad_block_write,  cleanup_rmfail,  TarReader_syntax,  unsupported_entry_present,  bpr_write,  stream_buffer_report,  write_queue_report,  file_missing,  modified_property,  file_disappeared,  file_changed,  file_appeared,  pif_malformat,  pif_malformat_size,  zero_write,  pif_toobig,  read_denied,  compression_unknown,  insufficient_read,  decompression_ranout,  move_work_file,  cant_overwrite,  cant_write_dir,  no_parent_dir,  bad_block_write_len,  illegal_block_boundary,  workfile_delete_fail,  unsupported_ext,  dest_exists,  parent_not_dir,  cant_write_parent,  parent_create_fail,  tar_field_toobig,  missing_supp_path,  nonfile_entry,  read_lt_1,  data_changed,  unexpected_header_key,  tarreader_syntaxerr,  unsupported_mode,  dir_x_conflict,  pif_unknown_datasize,  pif_data_toobig,  data_size_unknown,  extraction_exists,  extraction_exists_notfile,  extraction_parent_not_dir,  extraction_parent_not_writable,  extraction_parent_mkfail,  write_count_mismatch,  header_field_missing,  checksum_mismatch,  create_only_normal,  bad_header_value,  bad_numeric_header_value,  listing_format;
  
  private static ValidatingResourceBundle vrb;
  
  private RB() {}
  
  public String getString()
  {
    return vrb.getString(this);
  }
  
  public String toString()
  {
    return ValidatingResourceBundle.resourceKeyFor(this);
  }
  
  public String getExpandedString()
  {
    return vrb.getExpandedString(this);
  }
  
  public String getExpandedString(String... paramVarArgs)
  {
    return vrb.getExpandedString(this, paramVarArgs);
  }
  
  public String getString(String... paramVarArgs)
  {
    return vrb.getString(this, paramVarArgs);
  }
  
  public String getString(int paramInt)
  {
    return vrb.getString(this, paramInt);
  }
  
  public String getString(int paramInt1, int paramInt2)
  {
    return vrb.getString(this, paramInt1, paramInt2);
  }
  
  public String getString(int paramInt1, int paramInt2, int paramInt3)
  {
    return vrb.getString(this, paramInt1, paramInt2, paramInt3);
  }
  
  public String getString(int paramInt, String paramString)
  {
    return vrb.getString(this, paramInt, paramString);
  }
  
  public String getString(String paramString, int paramInt)
  {
    return vrb.getString(this, paramString, paramInt);
  }
  
  public String getString(int paramInt1, int paramInt2, String paramString)
  {
    return vrb.getString(this, paramInt1, paramInt2, paramString);
  }
  
  public String getString(int paramInt1, String paramString, int paramInt2)
  {
    return vrb.getString(this, paramInt1, paramString, paramInt2);
  }
  
  public String getString(String paramString, int paramInt1, int paramInt2)
  {
    return vrb.getString(this, paramString, paramInt1, paramInt2);
  }
  
  public String getString(int paramInt, String paramString1, String paramString2)
  {
    return vrb.getString(this, paramInt, paramString2, paramString2);
  }
  
  public String getString(String paramString1, String paramString2, int paramInt)
  {
    return vrb.getString(this, paramString1, paramString2, paramInt);
  }
  
  public String getString(String paramString1, int paramInt, String paramString2)
  {
    return vrb.getString(this, paramString1, paramInt, paramString2);
  }
  
  static
  {
    vrb = new ValidatingResourceBundle(RB.class.getPackage().getName() + ".rb", RB.class);
    vrb.setMissingPosValueBehavior(2);
    vrb.setMissingPropertyBehavior(2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.tar.RB
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */