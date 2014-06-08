# -*- coding: utf-8 -*-
"""
@author: tambre, Anarchon
@version: v0.2
"""

import os
import re
import sys
import getopt
import shutil
import zipfile
import subprocess

def startProcess(command):
        import shlex
        args = shlex.split(command)
        p = subprocess.Popen(args)
        p.communicate()[0]

def getArgument(line):
	cfg = open(os.path.join(os.getcwd(), "conf", "smcp.cfg"), "r")
	lines = cfg.readlines()
	line = lines[line].strip()
	ver = line.split('=', 1)[1]
	return ver

def dlProgress(count, blockSize, totalSize):
        import math
        percent = int(count * blockSize * 100 / totalSize)
        sys.stdout.write("\rGetting new StarMade v" + smver + "... " + "%.1f" % (count * blockSize / 1024 / 1024) + 'MB/' + "%.1f" % (totalSize / 1024 / 1024) + "MB %d%%" % percent)
        sys.stdout.flush()

def main(argv):
        global smver
        ignoreupdates = False
        hasfailed = False
        try:
                opts, args = getopt.getopt(argv, "iu", ["ignoreupdates"])
        except getopt.GetoptError:
                print ("Failed to parse arguments")
                print ("Arguments:")
                print ("ignoreupdates/iu - Disables updating")
                sys.exit()
        for opt, arg in opts:
                if opt in ("-iu", "-ignoreupdates"):
                        ignoreupdates = True
        print ('-----------------------------------------')
        print ('--------- Welcome to SMCP v%s ---------' % getArgument(0))
        print ('-----------------------------------------\n')
        workingDir = os.getcwd()
        smver = getArgument(1)
        smbuild = getArgument(2)
        smbuildsize = getArgument(4)
        if ignoreupdates == False:
                foundUpdates = False
                print ('Checking for updates...')
                if os.path.isfile(os.path.join(workingDir, 'install', 'starmade-build_' + smbuild + '.zip')):
                        if not zipfile.is_zipfile(os.path.join(workingDir, 'install', 'starmade-build_' + smbuild + '.zip')):
                                import urllib.request
                                import urllib.error
                                
                                os.remove(os.path.join(workingDir, 'install', 'starmade-build_' + smbuild + '.zip'))
                                url = r'http://files.star-made.org/build/starmade-build_' + smbuild + '.zip'
                                spath = os.path.join(workingDir, 'install', 'starmade-build_' + smbuild + '.zip')
                                urllib.request.urlretrieve(url, spath, reporthook = dlProgress)
                                foundUpdates = True
                else:
                        import urllib.request
                        import urllib.error
                        
                        url = r'http://files.star-made.org/build/starmade-build_' + smbuild + '.zip'
                        spath = os.path.join(workingDir, 'install', 'starmade-build_' + smbuild + '.zip')
                        urllib.request.urlretrieve(url, spath, reporthook = dlProgress)
                        foundUpdates = True
                if foundUpdates == False:
                        print ('')
        elif not zipfile.is_zipfile(os.path.join(workingDir, 'install', 'starmade-build_' + smbuild + '.zip')):
                print ("Invalid zip file!")
        print ('Extracting StarMade v' + smver + '\n')
        if not os.path.exists('instance') and not os.path.isdir('instance'):
                os.makedirs('instance')
        if not os.path.exists('tmp') and not os.path.isdir('tmp'):
                os.makedirs('tmp')
        if not os.path.exists('conf') and not os.path.isdir('conf'):
                os.makedirs('conf')
        unzip(os.path.join(workingDir, 'install', 'starmade-build_' + smbuild + '.zip'), os.path.join(workingDir, 'instance'))
        print ('Decompiling StarMade')
        print ('*   Deobfuscating... (Stage #1) (DISABLED)')
        #print ('*       Not here yet, skipping')
        #startProcess("java -Xmx1G -jar runtime/N3Remapper.jar conf/remapper.cfg pre instance/StarMade.jar tmp/deobf.zip")
        print ('*   Decompiling...   (Stage #2) (DISABLED)\n')
        if not os.path.exists('src') and not os.path.isdir('src'):
                os.makedirs('src')
        #tmp/deobf.zip when we have implemented SpecialSource
        #startProcess('java -Xmx1G -jar runtime/fernflower.jar' + workingDir + 'install/StarMade.zip sources')
        print ('Setting up Eclipse workspace\n')
        unzip(os.path.join(workingDir, "install", "EclipseWorkspace.zip"), workingDir)
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
        zfile = zipfile.ZipFile(zipFilePath)
        for name in zfile.namelist():
                (dirName, fileName) = os.path.split(name)
                if fileName == '':
                        newDir = os.path.join(destDir, dirName)
                        if not os.path.exists(newDir):
                                os.mkdir(newDir)
                else:
                        fd = open(os.path.join(destDir, name), 'wb')
                        fd.write(zfile.read(name))
                        fd.close()
        zfile.close()
	
if __name__ == "__main__":
	main(sys.argv[1:])
