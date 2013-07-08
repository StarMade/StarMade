@echo off
echo -------------------
echo - Welcome to SMCP -
echo -------------------
echo Extracting StarMade (build_20130707_170345)
mkdir instance
mkdir tmp
mkdir conf
cd instance
jar xf ../files/starmade-build_20130707_170345.zip
cd ../
echo Decompiling StarMade
echo     Deobfuscating... (Stage #1)
java -jar runtime/JRename.jar instance/StarMade.jar tmp/Deobf.zip
echo     Decompiling...
echo         We are going to copy sources as we can not decompile yet!
mkdir source
cd source
jar xf ../files/source.zip
cd ../
echo     Executing Python Setup...
runtime\python scripts\setup.py %*
echo Setting up Eclipse
jar xf files/EclipseWorkspace.zip
echo -----------------------------------------
echo - SMCP Is now ready for mod development -
echo -----------------------------------------
pause