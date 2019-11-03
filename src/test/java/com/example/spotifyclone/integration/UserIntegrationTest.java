package com.example.spotifyclone.integration;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.models.User;
import com.example.spotifyclone.models.UserRole;
import com.example.spotifyclone.repositories.SongRepository;
import com.example.spotifyclone.repositories.UserRepository;
import com.example.spotifyclone.repositories.UserRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIntegrationTest {

   @Autowired
    UserRoleRepository userRoleRepository;

   @Autowired
    UserRepository userRepository;

   @Autowired
    private SongRepository songRepository;

   private UserRole createUserRole() {//we need this set or else will not be able to instantiate new User object in DB
       UserRole userRole = userRoleRepository.findByName("ROLE_ADMIN");
       if(userRole == null){
           userRole = new UserRole();
           userRole.setName("ROLE_ADMIN");
           userRole = userRoleRepository.save(userRole);
       }
       return userRole;
   }

   private User createUser() {
    UserRole userRole = createUserRole();
    User user = new User();

    user.setUsername("chris");
    user.setPassword("pass");
    user.setUserRole(userRole);

    return user;
   }

   private Song createSong() {
       Song song = new Song();
       song.setTitle("Cigarette Daydreams");
       song.setLength((double) 200);

       return song;
   }

   @Test
    public void signup_User_Success() {
       User user = userRepository.findByUsername("chris");
        if(user != null){
            userRepository.delete(user); //delete if user object already exists
        }
        user = createUser(); //creating new user
        user = userRepository.save(user);   //save to db
        User foundUser = userRepository.findByUsername(user.getUsername()); //set this user to the one we just created

        assertNotNull(user);    //user we created - not null
        assertNotNull(foundUser); //repo search for same user - not null
        assertEquals(user.getId(), foundUser.getId());  //created and returned user are equal
   }

   @Test(expected = DataIntegrityViolationException.class)
    public void signup_DuplicateUser_Success() {
       User user = createUser();    //create user
       userRepository.save(user);   //save user to DB
       user.setId(null);    //set id to null
       userRepository.save(user);   //save to DB to throw exception
   }

   @Test
    public void delete_ById_Success() {
       User user = createUser();
       userRepository.save(user);

       assertNotNull(user.getId()); //make sure not null first

       userRepository.deleteById(user.getId()); //delete from DB

       User deletedUser = userRepository.findByUsername("chris");   //search for user by username, even though delted by Id
       assertNull(deletedUser); //boolean - does not exist
   }

   @Test
    public void addSong_ToUser_Success() {
//        User user = userRepository.findByUsername("chris"); //first, find user
//        if(user == null) {  //if no user
//            createUser();   //then, create a user
//            userRepository.save(user); //save user to DB
//        }     //couldn't get this to work searching for user... so just created new user

       User user = createUser();
        Song song = createSong();
        song = songRepository.save(song);  //add song to DB
       user.addSong(song);  //add song to user object
       userRepository.save(user); //save this user w/song to DB

       assertNotNull(user); //user exists
       assertNotNull(user.getSongs());  //song exists
       assertEquals(user.getSongs().get(0).getId(), song.getId());
   }
}
