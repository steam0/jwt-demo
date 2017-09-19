# JWT Test Repository

## Build and run
`./gradlew clean bootRun`

## Endpoints

`POST <hostname>/sign-up`

    {
        "username": "test",
        "password": "testpassword"
    }
    
`POST <hostname>/login`

    {
        "username": "test",
        "password": "testpassword"
    }
    
`GET <hostname>/userinfo`

`Authorization Bearer eyJhbGciOiJI...d4yoX_64YQTCkR9th9Q`

    Add the auth header from the /login response as auth header in
    this request.
    
    Returns the user from the token