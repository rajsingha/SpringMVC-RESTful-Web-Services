## SpringMVC-RESTful-Web-Services

<img src="images/spring-logo.png"  height="200" >

## A highly secure RESTful API for Web & Mobile Applications

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/rajsingha/SpringMVC-Mobile-App-Web-Services?include_releases) <br>
[Version 0.0.1](https://github.com/rajsingha/SpringMVC-Mobile-App-Web-Services/releases)

## Documentation

To connect the API with your database you need change the application.properties file.<br>

[application.properties file](https://github.com/rajsingha/SpringMVC-Mobile-App-Web-Services/blob/master/src/main/resources/application.properties)

### application.properties file

1. Change the file according to your neeed

```
spring.datasource.username= <Your database username here>

spring.datasource.password= <Your database password here>

spring.datasource.url= <your databse url here> 

```
### The basic functionalities of the API :

In the UserController.java [POST, GET, UPDATE, DELETE] functions are available.<br>

[UserController.java](https://github.com/rajsingha/SpringMVC-Mobile-App-Web-Services/blob/master/src/main/java/com/webservice/mobile/app/ui/controller/UserController.java)

#### Login Function

```  
    @ApiOperation(value = "The Get User Details Web Service Endpoint",
    notes = "This Web Service Endpoint returns User Details. User public user id in URL path." +
            "For Example: /user/<User Id here>")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    
    // Login function
    public UserRest getUser(@PathVariable String id){
        UserRest returnValue = new UserRest();
        UserDTO userDTO = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDTO,returnValue);
        return returnValue;
    }
    
```

#### CreateUser Function

```
  @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}
            )
            
    // CreateUser Function        
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {


        UserRest returnValue = new UserRest();

        if (userDetails.getFirstName().isEmpty() ||
                userDetails.getLastName().isEmpty() ||
                userDetails.getEmail().isEmpty() ||
                userDetails.getPassword().isEmpty()
        ) throw new
                UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails,userDTO);

        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser,returnValue);

        return returnValue;
    }

```
#### UpdateUser Function

```
@PutMapping(path = "/{id}",consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
      
    //UpdateUser Function 
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails){

        UserRest returnValue = new UserRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails,userDTO);
        UserDTO updateUser = userService.updateUser(id,userDTO);
        BeanUtils.copyProperties(updateUser,returnValue);

        return returnValue;
    }
    
```
#### DeleteUser Function

```
@DeleteMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
            
    //DeleteUser Function        
    public OperationStatusModel deleteUser(@PathVariable String id){
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }

```

#### GetAllUsers Function

```
@ApiImplicitParams({
            @ApiImplicitParam(name ="authorization",
                    value ="${userController.authorizationHeader.description}",
                    paramType = "header")
    })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
            
    //This function will return all the users deatils from database
    
    public List<UserRest> getUsers(@RequestParam(value = "page",defaultValue = "0")int page,
                                   @RequestParam(value = "limit",defaultValue = "25")int limit)
    {

        List<UserRest> returnValue = new ArrayList<>();

        List<UserDTO> users =userService.getUsers(page,limit);

        for (UserDTO userDTO: users){
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties(userDTO,userRest);
            returnValue.add(userRest);
        }

        return returnValue;
    }

```
#### To Run the application you need to install maven and run the given command below:

Run the command where you have downloaded the repository.

```
install mvn
```
Then
```
spring-boot:run
```
#### To test the API use postman.

#### For testing and documentation run the app using docker
```
http://localhost:8080/mobile-app-ws/swagger-ui.html

```


###### Developed with ❤️ by Raj Singha

## LICENSE

MIT License

Copyright (c) 2020 Raj Singha

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

