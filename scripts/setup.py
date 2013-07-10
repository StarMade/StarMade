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

def main():
	print '-------------------'
	print '- Welcome to SMCP -'
	print '-------------------\n'
	print 'Extracting StarMade (build_20130707_170345)\n'
	if not os.path.exists('instance') and not os.path.isdir('instance'):
		os.makedirs('instance')
	if not os.path.exists('tmp') and not os.path.isdir('tmp'):
		os.makedirs('tmp')
	if not os.path.exists('conf') and not os.path.isdir('conf'):
		os.makedirs('conf')
	workingDir = os.getcwd()
	os.chdir(workingDir + '\install')
	unzip("starmade-build_20130707_170345.zip", workingDir + '\instance')
	print 'Decompiling StarMade'
	print '*   Deobfuscating... (Stage #1)'
	os.chdir('..')
        os.chdir(workingDir + "/runtime")
        subprocess.call(['java', '-jar', 'JRename.jar', workingDir + '\instance\StarMade.jar', workingDir + '/tmp\deobf.zip'])
	print '*   Decompiling...   (Stage #2)'
	os.chdir('..')
	if not os.path.exists('sources') and not os.path.isdir('sources'):
		os.makedirs('sources')
	os.chdir(workingDir + '/runtime')
	subprocess.call(['java', '-jar', 'fernflower.jar', workingDir + '/tmp/deobf.zip', workingDir + '/sources'])
	#print '    *   We are going to copy sources as we can not decompile yet!\n'
	#unzip("sources.zip", workingDir + '\sources')
	os.chdir(workingDir + '\install')
	print 'Setting up Eclipse workspace\n'
	unzip("EclipseWorkspace.zip", workingDir)
	os.chdir(workingDir)
	if os.path.exists('tmp'):
		print 'Deleting temporary files'
		#shutil.rmtree('tmp')
	print '-----------------------------------------'
	print '- SMCP Is now ready for mod development -'
	print '-----------------------------------------'
	
def unzip(zipFilePath, destDir):
    zfile = zipfile.ZipFile(zipFilePath)
    for name in zfile.namelist():
        (dirName, fileName) = os.path.split(name)
        if fileName == '':
            # directory
            newDir = destDir + '/' + dirName
            if not os.path.exists(newDir):
                os.mkdir(newDir)
        else:
            # file
            fd = open(destDir + '/' + name, 'wb')
            fd.write(zfile.read(name))
            fd.close()
    zfile.close()
	
if __name__ == '__main__':
	main()
