#Use java 8 base image expose port 9494 add war to container and provide entrypoint for docker.
FROM java:8
EXPOSE 9494
ADD target/employee_time_tracking_service_api_consumer_app-0.0.1-SNAPSHOT.war employee_time_tracking_service_api_consumer_app.war
ENTRYPOINT ["java","-jar","employee_time_tracking_service_api_consumer_app.war"]
