# Dropwizard lambda gateway
This is a simple dropwizard container that can be configured to run either a local lambda jar or invoke a lambda on aws.


## Configure your lambdas
Create a file lambda-wrapper/src/main/resources/GatwayConfig.json that specifies the local and/or deployed versions of your lambda.  Below is an example.

```
[
  {
    "path": "/test",
    "jarLoc": "/Users/.../test-0.0.1-SNAPSHOT.jar",
    "className": "TestMain",
    "lambdaMethod": "handle",
    "deployedFunction": "test",
    "requestType": "application/json",
    "responseType": "application/json",
    "methodType": "POST"
  },
  {
    "path": "/test2",
    "jarLoc": "/Users/.../test-0.0.1-SNAPSHOT.jar",
    "className": "TestMain",
    "lambdaMethod": "handleGet",
    "deployedFunction": "test",
    "responseType": "text/html",
    "methodType": "GET"
  }
]
```

## Building and running lambda-wrapper locally
From within the lambda-wrapper directory:

Build: `mvn install`

Run as prod or dev: `java -jar target/lambda-wrapper-0.0.1-SNAPSHOT.jar server dev.yml`

# Using Convox to manage your application
## Setting up Convox

More information can be found at https://convox.com/docs, but basic instructions are as follows.

Create a new rack: `convox install --stack-name "gateway" --instance-count "2" --region "us-west-2" convox_credentials.csv`

Create your app: `convox apps create`

## Building and releasing new version
Build the lambda-wrapper with `mvn package` and deploy with `convox deploy`

To test your container locally, run `convox start`.  If you are running on OS X, you may need to proxy port 443 in VBox.
