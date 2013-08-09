# -*- coding: utf-8 -*-
"""
@author: tambre
@version: v0.1
"""

import os
import sys
import shutil
import zipfile
import subprocess
import shlex

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

def main():
        buildnum = getVersion(2)
	print '---------------------------'
	print '- Welcome to SMCP v%s ' % getVersion(0)
	print '---------------------------\n'
	print 'Extracting StarMade v%(0)s (%(1)s)\n' % {"0" : getVersion(1), "1" : buildnum}
	if not os.path.exists('instance') and not os.path.isdir('instance'):
		os.makedirs('instance')
	if not os.path.exists('tmp') and not os.path.isdir('tmp'):
		os.makedirs('tmp')
	if not os.path.exists('conf') and not os.path.isdir('conf'):
		os.makedirs('conf')
	workingDir = os.getcwd()
	os.chdir(workingDir + '\install')
	unzip("starmade-" + buildnum +".zip", workingDir + '\instance')
	os.chdir(workingDir)
	print 'Decompiling StarMade'
	print '*   Deobfuscating... (Stage #1)'
	startProcess("java -Xmx1G -jar runtime/N3Remapper.jar conf/remapper.cfg pre instance/StarMade.jar tmp/deobf.zip")
	print '*   Decompiling...   (Stage #2)'
	if not os.path.exists('sources') and not os.path.isdir('sources'):
		os.makedirs('sources')
	startProcess("java -Xmx1G -jar runtime/fernflower.jar tmp/deobf.zip sources")
	#subprocess.call(['java', '-Xms2G', '-jar', 'fernflower.jar', workingDir + '/tmp/deobf.zip', workingDir + '/sources'])
	os.chdir(workingDir + '\install')
	print 'Setting up Eclipse workspace\n'
	unzip("EclipseWorkspace.zip", workingDir)
	os.chdir(workingDir)
	if os.path.exists('tmp'):
		print 'Deleting temporary files'
		shutil.rmtree('tmp')
	print '-----------------------------------------'
	print '- SMCP Is now ready for mod development -'
	print '-----------------------------------------'
	
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
	
if __name__ == '__main__':
	main()
