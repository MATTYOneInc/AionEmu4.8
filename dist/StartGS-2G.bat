@ECHO off
TITLE Aion-Core - GameServer Console
:START
CLS
SET JAVAVER=1.7
SET NUMAENABLE=false
CLS
IF "%MODE%" == "" (
CALL PanelGS.bat
)

IF "%JAVAVER%" == "1.7" (

JAVA -Xms512m -Xmx2048m -server -ea -javaagent:./libs/ac-commons-1.3.jar -cp ./libs/*;AC-Game.jar com.aionemu.gameserver.GameServer
SET CLASSPATH=%OLDCLASSPATH%
IF ERRORLEVEL 2 GOTO START
IF ERRORLEVEL 1 GOTO ERROR
IF ERRORLEVEL 0 GOTO END
:ERROR
ECHO.
ECHO Aion-Lightning - Game Server has terminated abnormaly!
ECHO.
PAUSE
EXIT
:END
ECHO.
ECHO Aion-Lightning - Game Server is terminated!
ECHO.
PAUSE
EXIT