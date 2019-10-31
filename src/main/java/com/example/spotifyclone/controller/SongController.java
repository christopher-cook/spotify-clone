package com.example.spotifyclone.controller;

import com.example.spotifyclone.models.Song;
import com.example.spotifyclone.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @PostMapping
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }


    @GetMapping("/list")
    public Iterable<Song> listSongs(){
        return songService.listSongs();
    }

//    @DeleteMapping("/{songId}")
//    public HttpStatus deleteSong(@PathVariable Integer songId) {
//        songService.deleteSong(songId);
//        return HttpStatus.OK;
//    }
}
