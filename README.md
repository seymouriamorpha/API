<b>
    <h1 align="center"> API Contracts </h1>
</b>

<b>
    <h2 align="center"> User interactions </h2>
</b>

<details>
    <summary> Create new user </summary>
    <p> Request example (POST to <i>APPLICATION_URL/users</i>):
    <pre>
    {
        "forename":"UserFirstName",
    	"surname":"UserLastName",
    	"email":"user.email@mail.com",
    	"token":"phone_token_for_push"
    }
    </pre>
    <p> Success response example:
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
    <p> Unsuccess response example:
    <pre>
    HTTP Status: 409 (Conflict)
    {
        "message": "Rejected. User with given email address has already been registered"
    }
    </pre>
</details>

<details>
    <summary> Update user's credential </summary>
    <p> <b>WARNING:</b> if one or more field stay the same, need to send it too
    <p> Request example (PATCH to <i>APPLICATION_URL/users/id/{id}</i>):
    <pre>
    {
        "id":"5850038a444482331830ecfe",
        "forename":"UserFirstName",
        "surname":"UserLastName",
        "email":"new.user.email@mail.com",
        "token":"phone_token_for_push"
    }
    </pre>
    <p> Successful request example:
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
    <p> Request example (DELETE to <i>APPLICATION_URL/users/{id}</i>):
    <pre>
    {
        
    }
    </pre>
</details>

<details>
    <summary> Get all users </summary>
    <p> Request: GET to <i>APPLICATION_URL/users</i>
    <p> Response example:
    <pre>
    </pre>
</details>

<details>
    <summary> Get user by ID </summary>
    <p> Request: GET to <i>APPLICATION_URL/users/{id}</i>
    <p> Response example:
    <pre>
    </pre>
</details>

<details>
    <summary> Get user by e-mail address </summary>
    <p> Request: GET to <i>APPLICATION_URL/users/email/{email}</i>
    <p> Response example:
    <pre>
    </pre>
</details>

<b>
    <h2 align="center">Event interactions</h2>
</b>