# spotify-clone

- A link to the GitHub repo.
https://github.com/christopher-cook/spotify-clone

- All of your design decision / reasons behind each decision.

  - **User role to User (Many to one relationship)**
  One user can only have one role but one role can belong to many users.

  - **User to Songs (Many to many relationship)**
  Many songs can belong to many users.
  
- What went right.
We were able to collaboratively through issues.

- Challenges you faced.
Testing was a bit difficult with testing void methods. We also ran into a bit of an issue with authorization during testing as our test would fail without mocking the user (@WithMockUser).

Setting up the models and relationship mapping initially was also a bit confusing because we ran into issues with recursive loops which we resolved with @JsonIgnore.

- Which part you enjoyed working on the most.
