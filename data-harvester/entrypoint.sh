#!/bin/sh

echo "The application will start in ${APP_SLEEP}s..." && sleep ${APP_SLEEP}
echo ${HOME}
exec java ${JAVA_OPTS} -jar "${HOME}/app.jar" "$@"