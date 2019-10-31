package com.example.spotifyclone.controller;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

   @Autowired
    SongService songService;

   @PostMapping
    public Song createSong(@RequestBody Song song){
       return songService.createSong(song);
   }

   @GetMapping("/list")
    public Iterable<Song> listSongs() {
       return songService.listSongs();
   }
   @PostMapping("/{username}")
    public Song createUserSongList(@PathVariable String username, @RequestBody Song song) {
       return songService.createUserSongList(username, song);
   }

}
