FROM tomcat:8-jre8
MAINTAINER "Matthew Swamy"

ADD docker/settings.xml /usr/local/tomcat/conf/
ADD docker/tomcat-users.xml /usr/local/tomcat/conf/