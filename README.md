# Service-File
## Comment
A service which can accecpt multiple files in json format，and store them in the mysql database    
The strcuture of the database is：    
id(filename) content                
You can get the file content by its name.
## API
- Upload(MultipartFile):void   POST /file/upload     
- Get(String):String           GET  /file/{id}

## Function
This service can store and provide file data source for other services.  
