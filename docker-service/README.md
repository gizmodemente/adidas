# Docker Service

## A General purpose docker image for deploy services using spring boot.

You can use this image like a base image for create containers for any services. You can add any .jar files to target folder and build the containers using "docker build" instructions.

<pre>
docker build -t <<b>name_for_your_container</b>> --build-arg filename=<<b>filename_in_target_folder_including_extension</b>>
</pre>
