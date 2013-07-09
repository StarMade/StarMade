@echo off
:: cd ../
:: java -jar runtime/JRename.jar instance/StarMade.jar tmp/Deobf.zip
:: mkdir source
:: cd source
:: jar xf ../files/source.zip
:: cd ../
runtime\python scripts\setup.py %*
:: jar xf files/EclipseWorkspace.zip
pause