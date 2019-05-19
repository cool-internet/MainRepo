# MainRepo
## Introduction
### How to deploy
Input locally
```
drone exec [command options] [path/to/.drone.yml]
```
to build the drone project with the procedure defined in .drone.yml.
```
kind: pipeline
name: default
steps:
- name: test
  image: gcc
  commands:
  - make
  - make test
```
Each step starts a new container that includes a clone of your repository, and then runs the contents of your commands section inside it.
### Configuration
Input these statements 
```
export DRONE_SERVER=http://202.120.40.8:30331
export DRONE_TOKEN=dtar788Anxv5D1n7VRmvjKhSB******
```
into the shell which configure the drone.    
You can also input
```
drone info
```
to check the configuration has been set, or 
```
drone repo ls github
```
to check the repo.
![](/pic/ls.JPG "ls sample")

### Sample
Here I attempt a demo in C using my Cache Lab as an example
```
drone exec --pipeline default ./drone.yml
```
## Dependency
- Drone
- Github
