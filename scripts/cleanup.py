# -*- coding: utf-8 -*-
"""
@author: tambre
@version: v0.2
"""

import os
import shutil
import platform
import subprocess

def getArgument(line):
	cfg = open(os.path.join(os.getcwd() + "conf", "smcp.cfg"), "r")
	lines = cfg.readlines()
	line = lines[line].strip()
	ver = line.split('=', 1)[1]
	return ver

def main():
	hasDeletedAnything = False
	smcpversion = getArgument(0)
	print ('-------------------------')
	print ('- Welcome to SMCP v%s -' % smcpversion
	print ('-------------------------\n')
	answer = input('If you really want to clean up, enter "Yes" ')
	if answer.lower() not in ['yes']:
		print ('You have not entered "Yes", aborting the cleanup process\n')
		sys.exit(1)
	else:
		if platform.system() is 'Windows':
			subprocess.call("cls", shell=True)
		else:
			subprocess.call("clear")
		print ('-------------------------')
		print ('- Welcome to SMCP v%s -' % smcpversion
		print ('-------------------------\n')
	if os.path.exists('sources'):
		print ('Deleting sources')
		shutil.rmtree('src')
		hasDeletedAnything = True
	if os.path.exists('instance'):
		print ('Deleting instance folder')
		shutil.rmtree('instance')
		hasDeletedAnything = True
	if os.path.exists('tmp'):
		print ('Deleting tmp folder')
		if os.path.exists('workspace'):
			print ('')
		shutil.rmtree('tmp')
		hasDeletedAnything = True
	if os.path.exists('workspace'):
		print ('Deleting Eclipse workspace')
		shutil.rmtree('workspace')
		hasDeletedAnything = True
	if hasDeletedAnything:
		print ('')
	print ('-------------------------')
	print ('- SMCP cleanup complete -')
	print ('-------------------------')

if __name__ == '__main__':
	main()
