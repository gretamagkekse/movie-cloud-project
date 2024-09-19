# Project Team Yellow

## Running Docker Compose

to start the database, you have to run the docker container.

- first, see if the container is already running by typing in the terminal ```docker ps```
- then, use the command ```docker compose up --detach``` to create and run a docker container
- to stop the container use ```docker compose down --volumes```


## Table of Contents

- [Endpoints](#endpoints)
    - [Movie Endpoints](#movie-endpoints)
    - [Favorites Endpoints](#favorites-endpoints)
    - [Comment Endpoints](#comment-endpoints)
- [Authentication](#authentication)
- [Technologies](#technologies)

## Endpoints

### Movie Endpoints

#### Get Movie Details

- **GET** `/api/movie/{id}`
- Retrieves detailed information about a specific movie.

  
#### Get Popular Movies

- **GET** `/api/movies/popular`
- Retrieves a list of popular movies.


#### Search Movies

- **GET** `/api/movies/search`
- Searches for movies based on a query string.



---

### Favorites Endpoints

#### Add Movie to Favorites

- **POST** `/api/favorites/add`
- Adds a movie to the user's list of favorite movies.



#### Remove Movie from Favorites

- **POST** `/api/favorites/remove`
- Removes a movie from the user's list of favorite movies.



#### Get User's Favorite Movies

- **GET** `/api/favorites/list`
- Retrieves a list of the user's favorite movies.



#### Check if Movie is in Favorites

- **GET** `/api/favorites/is-favorite`
- Checks if a specific movie is in the user's favorites list.


---

### Comment Endpoints

#### Add a Comment

- **POST** `/api/comments/{movieId}`
- Adds a comment to a specific movie.



#### Get All Comments for a Movie

- **GET** `/api/comments/{movieId}`
- Retrieves all comments for a specific movie.


#### Get All Comments by a User

- **GET** `/api/comments/user-comments/{username}`
- Retrieves all comments made by a specific user.



#### Delete a Comment

- **DELETE** `/api/comments/user-comments/{id}`
- Deletes a comment made by the current user.



---

## Authentication

The API uses **Basic Authentication** for endpoints that require the user to be logged in. When a user is authenticated, they can add, remove, and retrieve their favorite movies, as well as post comments.

### Secured Endpoints
The following endpoints require authentication:

- `POST /api/favorites/add`
- `POST /api/favorites/remove`
- `GET /api/favorites/list`
- `GET /api/favorites/is-favorite`
- `POST /api/comments/{movieId}`
- `DELETE /api/comments/user-comments/{id}`

---

## Technologies

- **[Spring Boot](https://spring.io/projects/spring-boot)**: Backend framework used to build the REST API.
- **[TMDb API](https://developer.themoviedb.org/docs/getting-started)**: Used to retrieve movie details and popular movies.
- **[Swagger (OpenAPI)](https://swagger.io/)**: Used for API documentation and security requirements.
- **JPA**: Used for database interactions, such as managing users and comments.
