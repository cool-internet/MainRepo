# MainRepo
## Introduction
### How to deploy
Input locally
```
drone exec [command options] [path/to/.drone.yml]
```
to build the drone project with the procedure defined in .drone.yml. .drone.yml often contains some operations like 
```
kind: pipeline
name: default
steps:
- name: test
  image: maven:3-jdk-10
  commands:
  - mvn install
  - mvn test
```
to confirm the property of a program.
### Configuration
Input these statements 
```
export DRONE_SERVER=http://202.120.40.8:30331
export DRONE_TOKEN=dtar788Anxv5D1n7VRmvjKhSB******
```
into the shell which configure the drone.    
You can also input 
```
drone repo ls github
```
to check whether the configuration has been set.
![](/pic/ls.JPG "ls sample")

### Sample

## Dependency
- Drone
- Github
