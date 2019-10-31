package com.example.spotifyclone.repositories;

import com.example.spotifyclone.models.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Integer> {

    @Query("FROM Song sp JOIN User u ON u.username = ?1 and sp.id = u.username.id")
    public Song findSongByUsername(String username);

//    @Query("DELETE FROM Song s WHERE s.id =")
    void deleteById(Integer songId);
}
