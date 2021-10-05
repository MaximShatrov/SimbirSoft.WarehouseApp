# Warehouse shop Springboot web application

Application provides user access to the DB of warehouse shop.

Database contains 3 tables of entities:
`USERS`        `ITEMS`        `CATEGORIES`

Users can have 1 of 3 roles.

| Role  | Permissions  |
| ------------ | ------------ |
|**ADMIN** |Create, read, update, delete `Users`, `Items` and `Categories`|
|**EMPLOYEE**|Create, read, update, delete `Items` and `Categories` |
|**USER**|Read `items`|

## Application configuration

Configure Postges URL, DB name, user and password in `src/main/resources/application.properties`

    # ---- Postgres ----
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.database=POSTGRESQL
    spring.datasource.name=postgresql
    spring.datasource.url=jdbc:postgresql://localhost:5432/shop_warehouse
    spring.datasource.username=postgres
    spring.datasource.password=postgres

Also you can set server's port

    #Set server port here
    server.port=8080

## Build

Run mvn package from the powershell, as follows:

    .\mvnw package

If done right you'll get message in command line:

    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  01:03 min
    [INFO] Finished at: 2021-10-05T23:04:11+04:00
    [INFO] ------------------------------------------------------------------------

In the /target directory, you should see `WarehouseApp-0.0.1-SNAPSHOT.jar`

## Run the app

From command line run command:

    \target> java -jar .\WarehouseApp-0.0.1-SNAPSHOT.jar
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::  (v2.5.4)
    ....... . . .
    ....... . . . (log output here)
    ....... . . .
    ........ Started WarehouseAppApplication v0.0.1-SNAPSHOT using Java 1.8.0_241 

Now you can access app by `localhost:PORT/api/v1/`

# Authorization

Authorization in application implemented via JWT token. API methods are accessed using a valid JWT token in request's
header:

    curl --location --request GET 'http://localhost:8080/api/v1/users' \
    --header 'Authorization:JWT Token' \
    --header 'Content-Type: application/json'

### Authorization page

### Request `POST /api/v1/auth/login`

Body:

	{
        "login": "string",
        "password": "string",
	}

### Response

#### `Code 200` Authorization success.

Body:

	{
        "login": "admin",
        "token": "string"
	}

# REST API

The REST API to the Warehouse shop app is described below.

## Get list of Users

### Request `GET /api/v1/users`

### Response

#### `Code 200` Get list of users success.

Body:

	[
		{
			"login": "string",
			 "password": "string",
			"role": "ADMIN",
			"status": "ACTIVE"
		}
	]

#### `Code 404` Users not found.

## Edit user's info

### Request `PUT /api/v1/users`

Body:

	{
		"login": "string",
	    "password": "string",
		"role": "ADMIN",
		"status": "ACTIVE"
	}

### Response

#### `Code 200` Edit user's info success.

Body:

	{
		"login": "string",
	    "password": "string",
		"role": "ADMIN",
		"status": "ACTIVE"
	}

#### `Code 400` Edit user's info failed. Wrong request.

#### `Code 404` User not found.

## Create new user.

### Request `POST /api/v1/users`

Body:

	{
	    "login": "string",
	    "password": "string",
	    "role": "ADMIN",
		"status": "ACTIVE"
	}

### Response

#### `Code 201` User create success.

Body:

	{
    	"login": "string",
    	"password": "string",
    	"role": "ADMIN",
    	"status": "ACTIVE"
	}

#### `Code 400` User create failed. Wrong request.

#### `Code 406` User already exists.

## Get user's info.

### Request `GET /api/v1/users/{login}`

### Response

#### `Code 200` Get user's info success.

Body:

	{
		"login": "string",
		"password": "string",
		"role": "ADMIN",
		"status": "ACTIVE"
	}

#### `Code 404` User not found.

## Delete user.

### Request `DELETE /api/v1/users/{login}`

### Response

#### `Code 204` Delete user success.

#### `Code 404` User not found.

## Get list of items

### Request `GET /api/v1/items`

### Response

#### `Code 200` Get list of items success.

Body:

	[
		{
			"id": 0,
			"name": "string",
			"description": "string",
			"amount": 0,
			"price": 0,
			"category": {
				"id": 0,
				"name": "string"
			}
		}
	]

#### `Code 404` Items not found.

## Edit items info

### Request `PUT /api/v1/items`

Body:

	{
		"id": 0,
		"name": "string",
		"description": "string",
		"amount": 0,
		"price": 0,
		"category": {
			"id": 0,
			"name": "string"
		}
	}

### Response

#### `Code 200` Edit item's info success.

Body:

	{
		"id": 0,
		"name": "string",
		"description": "string",
		"amount": 0,
		"price": 0,
		"category": {
			"id": 0,
			"name": "string"
		}
	}

#### `Code 400` Edit item's info failed. Item not found or wrong request.

## Create new item.

### Request `POST /api/v1/items`

Body:

	{
		"id": 0,
		"name": "string",
		"description": "string",
		"amount": 0,
		"price": 0,
		"category": {
			"id": 0,
			"name": "string"
		}
	}

### Response

#### `Code 201` Item create success.

Body:

	{
		"id": 0,
		"name": "string",
		"description": "string",
		"amount": 0,
		"price": 0,
		"category": {
			"id": 0,
			"name": "string"
		}
	}

#### `Code 400` Item create failed. Wrong request.

## Get item's info.

### Request `GET /api/v1/items/{id}`

### Response

#### `Code 200` Get item's info success.

Body:

	{
		"id": 0,
		"name": "string",
		"description": "string",
		"amount": 0,
		"price": 0,
		"category": {
			"id": 0,
			"name": "string"
		}
	}

#### `Code 404` Item not found.

## Delete item.

### Request `DELETE /api/v1/items/{id}`

### Response

#### `Code 204` Delete item success.

#### `Code 404` Item not found.

CATEGORIES

## Get list of categories

### Request `GET /api/v1/categories`

### Response

#### `Code 200` Get list of categories success.

Body:

	[
		{
			"id": 0,
			"name": "string"
		}
	]

#### `Code 404` Categories not found.

## Edit categories info

### Request `PUT /api/v1/categories`

Body:

	{
		"id": 0,
		"name": "string"
	}

### Response

#### `Code 200` Edit category's info success.

Body:

	{
		"id": 0,
		"name": "string"
	}

#### `Code 400` Edit category's info failed. Category not found or wrong request.

## Create new item's category.

### Request `POST /api/v1/categories`

Body:

	{
		"id": 0,
		"name": "string"
	}

### Response

#### `Code 201` Category create success.

Body:

	{
		"id": 0,
		"name": "string"
	}

#### `Code 400` Category create failed. Wrong request.

## Get category's info.

### Request `GET /api/v1/categories/{id}`

### Response

#### `Code 200` Get category's info success.

Body:

	{
		"id": 0,
		"name": "string"
	}

#### `Code 404` Category not found.

## Delete category.

### Request `DELETE /api/v1/categories/{id}`

### Response

#### `Code 204` Delete category success.

#### `Code 404` Category not found.

###### Выполнил Шатров Максим