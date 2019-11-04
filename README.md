# spotify-clone
---

### Design choices:

![ERD](https://github.com/christopher-cook/spotify-clone/blob/master/Spotify%20Clone%20ERD.jpeg)

  - **User to User Role (Many to one relationship)**
    - One user can only have one role but one role can belong to many users.

  - **User to Songs (Many to many relationship)**
    - Many songs can belong to many users.
  
### What went right:
  - We were able to collaborate through issues.
  - After breaking Github repo was able to prevent further merge conflicts.

### Challenges faced:
  - Testing was a bit difficult with regards to void methods. We also ran into a bit of an issue with authorization during   
  testing as our test would fail without mocking the user (@WithMockUser).

  - Setting up the models and relationship mapping initially was also a bit confusing as we ran into a 
  recursive loop issue which we resolved utilizing @JsonIgnore.

### Which part you enjoyed working on the most:
  - We enjoyed having the opportunity to review and reinforce some of the ideas and concepts learned in Spring.
  - Again, despite not resolving all tests the mock/injectmocks are finally clearing up a bit.
  --- 
  
  [Github Repo](https://github.com/christopher-cook/spotify-clone)
