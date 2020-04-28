# SpringMVC-Mobile-App-Web-Services
<img src="images/spring-logo.png"  height="200" >

# A highly secure RESTful API for Web & Mobile Applications

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

