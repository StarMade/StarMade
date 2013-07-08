package org.w3c.tidy.ant;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Parameter;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FlatFileNameMapper;
import org.apache.tools.ant.util.IdentityMapper;
import org.w3c.tidy.Tidy;

public class JTidyTask
  extends Task
{
  private List filesets = new ArrayList();
  private File destdir;
  private File destfile;
  private File srcfile;
  private boolean failonerror;
  private boolean flatten;
  private Tidy tidy;
  private Properties props;
  private File properties;
  
  public void setDestdir(File paramFile)
  {
    this.destdir = paramFile;
  }
  
  public void setDestfile(File paramFile)
  {
    this.destfile = paramFile;
  }
  
  public void setSrcfile(File paramFile)
  {
    this.srcfile = paramFile;
  }
  
  public void setFailonerror(boolean paramBoolean)
  {
    this.failonerror = paramBoolean;
  }
  
  public void setFlatten(boolean paramBoolean)
  {
    this.flatten = paramBoolean;
  }
  
  public void setProperties(File paramFile)
  {
    this.properties = paramFile;
  }
  
  public void addFileset(FileSet paramFileSet)
  {
    this.filesets.add(paramFileSet);
  }
  
  public void addConfiguredParameter(Parameter paramParameter)
  {
    this.props.setProperty(paramParameter.getName(), paramParameter.getValue());
  }
  
  public void init()
  {
    super.init();
    this.tidy = new Tidy();
    this.props = new Properties();
  }
  
  protected void validateParameters()
    throws BuildException
  {
    if ((this.srcfile == null) && (this.filesets.size() == 0)) {
      throw new BuildException("Specify at least srcfile or a fileset.");
    }
    if ((this.srcfile != null) && (this.filesets.size() > 0)) {
      throw new BuildException("You can't specify both srcfile and nested filesets.");
    }
    if ((this.destfile == null) && (this.destdir == null)) {
      throw new BuildException("One of destfile or destdir must be set.");
    }
    if ((this.srcfile == null) && (this.destfile != null)) {
      throw new BuildException("You only can use destfile with srcfile.");
    }
    if ((this.srcfile != null) && (this.srcfile.isDirectory())) {
      throw new BuildException("srcfile can't be a directory.");
    }
    if ((this.properties != null) && (this.properties.isDirectory())) {
      throw new BuildException("Invalid properties file specified: " + this.properties.getPath());
    }
  }
  
  public void execute()
    throws BuildException
  {
    validateParameters();
    if (this.properties != null) {
      try
      {
        this.props.load(new FileInputStream(this.properties));
      }
      catch (IOException localIOException)
      {
        throw new BuildException("Unable to load properties file " + this.properties, localIOException);
      }
    }
    this.tidy.setErrout(new PrintWriter(new ByteArrayOutputStream()));
    this.tidy.setConfigurationFromProps(this.props);
    if (this.srcfile != null) {
      executeSingle();
    } else {
      executeSet();
    }
  }
  
  protected void executeSingle()
  {
    if (!this.srcfile.exists()) {
      throw new BuildException("Could not find source file " + this.srcfile.getAbsolutePath() + ".");
    }
    if (this.destfile == null) {
      this.destfile = new File(this.destdir, this.srcfile.getName());
    }
    processFile(this.srcfile, this.destfile);
  }
  
  protected void executeSet()
  {
    Object localObject = null;
    if (this.flatten) {
      localObject = new FlatFileNameMapper();
    } else {
      localObject = new IdentityMapper();
    }
    ((FileNameMapper)localObject).setTo(this.destdir.getAbsolutePath());
    Iterator localIterator = this.filesets.iterator();
    while (localIterator.hasNext())
    {
      FileSet localFileSet = (FileSet)localIterator.next();
      DirectoryScanner localDirectoryScanner = localFileSet.getDirectoryScanner(getProject());
      String[] arrayOfString1 = localDirectoryScanner.getIncludedFiles();
      File localFile = localDirectoryScanner.getBasedir();
      ((FileNameMapper)localObject).setFrom(localFile.getAbsolutePath());
      for (int i = 0; i < arrayOfString1.length; i++)
      {
        String[] arrayOfString2 = ((FileNameMapper)localObject).mapFileName(arrayOfString1[i]);
        processFile(new File(localFile, arrayOfString1[i]), new File(this.destdir, arrayOfString2[0]));
      }
    }
  }
  
  protected void processFile(File paramFile1, File paramFile2)
  {
    log("Processing " + paramFile1.getAbsolutePath(), 4);
    BufferedInputStream localBufferedInputStream;
    try
    {
      localBufferedInputStream = new BufferedInputStream(new FileInputStream(paramFile1));
    }
    catch (IOException localIOException1)
    {
      throw new BuildException("Unable to open file " + paramFile1);
    }
    BufferedOutputStream localBufferedOutputStream;
    try
    {
      paramFile2.getParentFile().mkdirs();
      paramFile2.createNewFile();
      localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(paramFile2));
    }
    catch (IOException localIOException2)
    {
      throw new BuildException("Unable to open destination file " + paramFile2, localIOException2);
    }
    this.tidy.parse(localBufferedInputStream, localBufferedOutputStream);
    try
    {
      localBufferedInputStream.close();
    }
    catch (IOException localIOException3) {}
    try
    {
      localBufferedOutputStream.flush();
      localBufferedOutputStream.close();
    }
    catch (IOException localIOException4) {}
    if ((this.tidy.getParseErrors() > 0) && (!this.tidy.getForceOutput())) {
      paramFile2.delete();
    }
    if ((this.failonerror) && (this.tidy.getParseErrors() > 0)) {
      throw new BuildException("Tidy was unable to process file " + paramFile1 + ", " + this.tidy.getParseErrors() + " returned.");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.ant.JTidyTask
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */