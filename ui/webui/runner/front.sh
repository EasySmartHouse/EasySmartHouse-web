PROJECT_HOME="$PWD"

if [ -z "$JAVA_HOME" ]; then
	JAVA_EXE = "$JAVA_HOME/jre/sh/java"
elif [ -z "$JRE_HOME" ]; then
	JAVA_EXE = "$JRE_HOME/sh/java"
elif
	JAVA_EXE = "java"
fi

exec "$JAVA_EXE" -jar "$PROJECT_HOME/jetty-runner.jar" --path /webui "$PROJECT_HOME/esh-webui.war" > /dev/null