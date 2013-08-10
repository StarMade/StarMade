# -*- coding: utf-8 -*-
"""
@author: tambre
@version: v0.2
"""

import os
import sys
import getopt
import shutil
import zipfile
import subprocess
import shlex
import urllib.request
import re

def startProcess(command):
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
        try:
                opts, args = getopt.getopt(argv, "iu", ["ignoreupdates"])
        except getopt.GetoptError:
                #TO-DO: Add usage printout
                print ("Failed to parse arguments")
                sys.exit()
        for opt, arg in opts:
                if opt in ("-iu", "--ignoreupdates"):
                        ignoreupdates = True
        print ("---------------------------")
        print ('-- Welcome to SMCP v%s --' % getVersion(0))
        print ('---------------------------\n')
        print ('Extracting StarMade v%s\n' % getVersion(1))
        if not os.path.exists('instance') and not os.path.isdir('instance'):
                os.makedirs('instance')
        if not os.path.exists('tmp') and not os.path.isdir('tmp'):
                os.makedirs('tmp')
        if not os.path.exists('conf') and not os.path.isdir('conf'):
                os.makedirs('conf')
        workingDir = os.getcwd()
        os.chdir(workingDir + '\install')
        unzip("StarMade.zip", workingDir + '\instance')
        os.chdir(workingDir)
        print ('Decompiling StarMade')
        print ('*   Deobfuscating... (Stage #1)')
        #startProcess("java -Xmx1G -jar runtime/N3Remapper.jar conf/remapper.cfg pre instance/StarMade.jar tmp/deobf.zip")
        print ('*   Decompiling...   (Stage #2)')
        if not os.path.exists('sources') and not os.path.isdir('sources'):
                os.makedirs('sources')
        #tmp/deobf.zip when implemnted SpecialSource
        startProcess("java -Xmx1G -jar runtime/fernflower.jar install/StarMade.zip sources")
        os.chdir(workingDir + '\install')
        print ('Setting up Eclipse workspace\n')
        unzip("EclipseWorkspace.zip", workingDir)
        os.chdir(workingDir)
        if os.path.exists('tmp'):
                print ('Deleting temporary files\n')
                shutil.rmtree('tmp')
        print ('-----------------------------------------')
        print ('- SMCP Is now ready for mod development -')
        print ('-----------------------------------------')
	
def unzip(zipFilePath, destDir):
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
