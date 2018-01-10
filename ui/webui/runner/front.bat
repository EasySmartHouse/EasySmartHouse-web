SET PROJECT_HOME=%CD%

IF EXIST "%JAVA_HOME%" SET JAVA_EXE=%JAVA_HOME%\jre\bin\java.exe
IF NOT "%JAVA_EXE%" == "" GOTO java_found

SET JAVA_EXE=java.exe

:java_found
"%JAVA_EXE%" -jar "%PROJECT_HOME%\jetty-runner.jar" --path /webui "%PROJECT_HOME%\esh-webui.war"