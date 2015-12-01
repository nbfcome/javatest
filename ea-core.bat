@echo off
echo [INFO] install ea-core jar

cd %~dp0

call mvn clean install -Dmaven.test.skip=true
echo [INFO] end install
pause
