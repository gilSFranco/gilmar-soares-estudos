
# Using "json for testing"

I created these files to help you test my API. I created some scripts that save the username, password and access token, so that the use is faster and more practical. I hope it helps!


## The "api.json" file

It contains all the test configuration that I used in postman. The access URLs, endpoints and request bodies are ready to be used. And also the scripts which I mentioned previously.

## The "environment.json" file

This file contains the environment variables I mentioned. We have the environment variables and the global variables. Which are:

### Environment

- `base_url`: API access URL
- `username`: Registered username
- `password`: Registered password

### Globals

- `access_token`: Authentication token generated at user login
- `refresh_token`: Authentication refresh token generated at user login