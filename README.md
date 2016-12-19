<b>
    <h1 align="center"> API Contracts </h1>
</b>

<b>
    <h2 align="center"> User interactions </h2>
</b>

<details>
    <summary> Get user by ID </summary>
    <p> Request: GET to <i>APPLICATION_URL/users/{id}</i>
    <p> Successful response example:
    <pre>
    {
        "id": "584f063a20379b0b84cd2b1c",
        "forename": "UserFirstName",
        "surname": "UserLastName",
        "email": "user.email@mail.com",
        "token": "phone_token_for_push"
    }
    </pre>
</details>

<details>
    <summary> Get all users </summary>
    <p> Request: GET to <i>APPLICATION_URL/users</i>
    <p> Successful response example:
    <pre>
    [
        {
            "id": "584f061820379b0b84cd2b1b",
            "forename": "UserFirstName1",
            "surname": "UserLastName1",
            "email": "user1.email@mail.com",
            "token": "phone_token_for_push1"
        },
        {
            "id": "584f063a20379b0b84cd2b1c",
            "forename": "UserFirstName2",
            "surname": "UserLastName2",
            "email": "user2.email@mail.com",
            "token": "phone_token_for_push2"
        },
        {
            "id": "584f066a20379b0b84cd2b1d",
            "forename": "UserFirstName3",
            "surname": "UserLastName3",
            "email": "user3.email@mail.com",
            "token": "phone_token_for_push3"
        }
    ]
    </pre>
</details>

<details>
    <summary> Create new user </summary>
    <p> Request : POST to <i>APPLICATION_URL/users</i>:
    <pre>
    {
        "forename":"UserFirstName",
    	"surname":"UserLastName",
    	"email":"user.email@mail.com",
    	"token":"phone_token_for_push"
    }
    </pre>
    <p> Successful response example:
    <pre>
    HTTP Status: 201
    {
        "id": "5850038a444482331830ecfe",
        "forename": "UserFirstName",
        "surname": "UserLastName",
        "email": "user.email@mail.com",
        "token": "phone_token_for_push"
    }
    </pre>
    <p> Unsuccessful response example:
    <pre>
    HTTP Status: 409 (Conflict)
    {
        "message": "Rejected. User with given email address has already been registered"
    }
    </pre>
</details>

<details>
    <summary> Update user's credential </summary>
    <p> Request : PATCH to <i>APPLICATION_URL/users/{id}</i>:
    <pre>
    {
        "id":"5850038a444482331830ecfe",
        "forename":"UserFirstName",
        "surname":"UserLastName",
        "email":"new.user.email@mail.com",
        "token":"phone_token_for_push"
    }
    </pre>
    <p> Successful response example:
    <pre>
    HTTP Status: 200
    {
        "id":"5850038a444482331830ecfe",
        "forename":"UserFirstName",
        "surname":"UserLastName",
        "email":"new.user.email@mail.com",
        "token":"phone_token_for_push"
    }
    </pre>
</details>

<details>
    <summary> Delete user </summary>
    <p> Request: DELETE to <i>APPLICATION_URL/users/{id}</i>
    <p> Successful response example:
    <pre>
    HTTP Status: 200
    </pre>
</details>

<b>
    <h2 align="center">Event interactions</h2>
</b>

<details>
    <summary> Get event by ID </summary>
    <p> Request: GET to <i>APPLICATION_URL/events/{id}</i>
    <p> Successful response example:
    <pre>
    {
        "id": "584f063a20379b0b84cd2b1c",
        "forename": "UserFirstName",
        "surname": "UserLastName",
        "email": "user.email@mail.com",
        "token": "phone_token_for_push"
    }
    </pre>
</details>

<details>
    <summary> Get events by CreatorId </summary>
    <p> Request: GET to <i>APPLICATION_URL/events/creator/{creatorId}</i>
    <p> Successful response example:
    <pre>
    {
    }
    </pre>
</details>

<details>
    <summary> Create new event </summary>
    <p> Request: POST to <i>APPLICATION_URL/events</i>
    <pre>
    {
        "title":"EPAM Smoking Party",
        "creatorId":"584f061820379b0b84cd2b1b",
        "lat":"52.404999",
        "lon":"30.921104",
        "eventTime":
    }
    </pre>
    <p> Successful response example:
    <pre>
    {
    }
    </pre>
</details>