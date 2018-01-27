docker run -it --rm \
  -e JPDA_ADDRESS=8000 \
  -e JPDA_TRANSPORT=dt_socket \
  -e CATALINA_OPTS="-Dspring.config.location=file:/apps/application.properties" \
  --name tomcatTvDev \
  -p 8888:8080 \
  -p 9000:8000 \
  -v /apps:/apps \
  tomcatdev
