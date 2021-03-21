FROM tomcat:latest
ARG WAR_FILE=target/*.war
ADD ${WAR_FILE} /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]