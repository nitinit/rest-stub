REST Stub Simulator
================

Quick start
---------

Option 1 - Running locally without docker support
---------
You can build the simulator application locally with Maven:

```
mvn clean install
```

This will compile and package all resources for you.
After the successful build you are able to run the stub simulator with:

```
mvn spring-boot:run
```

This will compile and package all resources for you.
After the successful build you are able to run the stub simulator with:

```
mvn spring-boot:run
```

Option 2 - Running with docker support
---------

```
mvn docker:build
```

then 

```
mvn docker:start
```

Open your browser and point to [http://localhost:9090](http://localhost:9090).

Access stub API endpoints applications(It also supports taking query inputs so please do refer respective stub mappings)
[http://localhost:9090/schedules/ByEvent](http://localhost:9090/schedules/ByEvent)

For checking list of endpoints/mapping available at stub-server
http://localhost:9090/__admin/

To Create dynamic stub behaviour for an endpoint and related mapping, Use -

```
POST http://localhost:8080/__admin/mappings/save
```

