# -*- coding: utf-8 -*-
"""
@author: tambre
@version: v0.2
"""

import os
import sys
import getopt
import shutil
import subprocess
import urllib.request
import urllib.error
import re

def startProcess(command):
        import shlex
        args = shlex.split(command)
        p = subprocess.Popen(args)
        p.communicate()[0]

def getVersion(line):
	cfg = open(os.getcwd() + "\conf\smcp.cfg", "r")
	lines = cfg.readlines()
	line = lines[line].strip()
	ver = line.split('=', 1)[1]
	return ver

def main(argv):
        ignoreupdates = False
        hasfailed = False
        try:
                opts, args = getopt.getopt(argv, "iu", ["ignoreupdates"])
        except getopt.GetoptError:
                #TO-DO: Add usage printout
                print ("Failed to parse arguments")
                sys.exit()
        for opt, arg in opts:
                if opt in ("-iu", "--ignoreupdates"):
                        ignoreupdates = True
        print ('-----------------------------------------')
        print ('--------- Welcome to SMCP v%s ---------' % getVersion(0))
        print ('-----------------------------------------\n')
        workingDir = os.getcwd()
        smbuild = getVersion(2)
        smver = getVersion(1)
        if ignoreupdates == False:
                #try:
                #        #import hashlib
                #        starmademdweb = urllib.request.urlopen("http://smcp.pingu.pw/file.md5");
                #        starmademdraw = starmademdweb.read()
                #        #print (sumfile(open(workingDir + '\install\StarMade.zip')))
                #except urllib.error.HTTPError as exception:
                #        print ('    *   Unable to get latest version info - HTTPError =  ' + str(exception.reason))
                #except urllib.error.URLError as exception:
                #        print ('    *   Unable to get latest version info - URLError = ' + str(exception.reason))
                #        sys.exit(2)
                #except urllib.error.HTTPException as exception:
                #        print ('    *   Unable to get latest version info - HTTPException')
                #       sys.exit(2)
                #except Exception as exception:
                #        import traceback
                #        print ('    *   Unable to get latest version info - Exception = ' + traceback.format_exc())
                #        sys.exit(2)
                #starmademdweb.close()
                #print (starmademdraw)
                print ('Checking for updates... (DISABLED)')
                if not os.path.isfile(workingDir + '\install\starmade-build_' + smbuild + '.zip'):
                        print ('*   Getting new StarMade... (May take a while)\n')
                        url = r'http://files.star-made.org/build/starmade-build_' + smbuild + '.zip'
                        opener = urllib.request.urlopen(url)
                        starmade_out = open(workingDir + '\install\starmade-build_' + smbuild + '.zip', 'wb')
                        starmade_out.write(opener.readall())
                        starmade_out.close()
                else:
                        print ('\n')
        print ('Extracting StarMade v' + smver + '\n')
        if not os.path.exists('instance') and not os.path.isdir('instance'):
                os.makedirs('instance')
        if not os.path.exists('tmp') and not os.path.isdir('tmp'):
                os.makedirs('tmp')
        if not os.path.exists('conf') and not os.path.isdir('conf'):
                os.makedirs('conf')
        unzip(workingDir + '\install\starmade-build_' + smbuild + '.zip', workingDir + '\instance')
        print ('Decompiling StarMade')
        print ('*   Deobfuscating... (Stage #1) (DISABLED)')
        #print ('*       Not here yet, skipping')
        #startProcess("java -Xmx1G -jar runtime/N3Remapper.jar conf/remapper.cfg pre instance/StarMade.jar tmp/deobf.zip")
        print ('*   Decompiling...   (Stage #2) (DISABLED)')
        if not os.path.exists('sources') and not os.path.isdir('sources'):
                os.makedirs('sources')
        #tmp/deobf.zip when we have implemented SpecialSource
        #startProcess('java -Xmx1G -jar runtime/fernflower.jar' + workingDir + 'install/StarMade.zip sources')
        print ('Setting up Eclipse workspace\n')
        unzip(workingDir + "\install\EclipseWorkspace.zip", workingDir)
        if os.path.exists(workingDir + 'tmp'):
                print ('Deleting temporary files\n')
                shutil.rmtree(workingDir + 'tmp')
        endMessage(hasfailed)

def endMessage(failed):
        if failed:
                print ('-----------------------------------------')
                print ('----------- SMCP setup failed -----------')
                print ('-----------------------------------------')
        else:
                print ('-----------------------------------------')
                print ('- SMCP Is now ready for mod development -')
                print ('-----------------------------------------')
	
def unzip(zipFilePath, destDir):
        import zipfile
        zfile = zipfile.ZipFile(zipFilePath)
        for name in zfile.namelist():
                (dirName, fileName) = os.path.split(name)
                if fileName == '':
                        newDir = destDir + '/' + dirName
                        if not os.path.exists(newDir):
                                os.mkdir(newDir)
                else:
                        fd = open(destDir + '/' + name, 'wb')
                        fd.write(zfile.read(name))
                        fd.close()
        zfile.close()
	
if __name__ == "__main__":
	main(sys.argv[1:])
