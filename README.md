## CI Tool
### Quick Start
Just click [here](http://202.120.40.8:30331)
### How to deploy
At the root path of repo, input locally
```
drone exec [command options] [path/to/.drone.yml]
```
to build the drone project with the procedure defined in `.drone.yml`.
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
Here I attempt a demo in Java image using simplified form as an example
```
drone exec --pipeline default ./drone.yml
```
![](/pic/demo.JPG "Output")
![](/pic/demores.JPG "Class file remain")
### Dependency
- Docker
- Drone
- Github
### Procedures for building
#### Docker
First, install docker. Since it is universally acknowledged, we just skip this step. And if you have not get docker installed, please refer to blogs.
#### Github OAuth Application
In your account settings, find ` Developer settings`. Click that and create your OAuth Application.
Then it will generate a pair of client id and client secret.
Keep these information and to the next step.
#### Docker Compose
Create a new yml file like `docker-compose.yml` at proper place:
```
version: '3'
services:
   drone:
      image: "drone/drone:1"
      volumes:
       -  /var/run/docker.sock:/var/run/docker.sock
       -  /var/lib/drone:/data
      environment:
       - DRONE_GITHUB_SERVER=https://github.com
       - DRONE_GITHUB_CLIENT_ID=dda0bad6240**
       - DRONE_GITHUB_CLIENT_SECRET=ca13c24**
       - DRONE_RUNNER_CAPACITY=2
       - DRONE_SERVER_HOST=202.120.40.8:30331
       - DRONE_SERVER_PROTO=http
      ports:
       - 30331:80
       - 443:443
      restart: always
```
replace `DRONE_GITHUB_CLIENT_ID` and `DRONE_GITHUB_CLIENT_SECRET` with your own client id and client secret generated.
Besides, replace `DRONE_SERVER_HOST` with your server host.
At where `docker-compose.yml` exists, run `docker-compose up -d`. Done!