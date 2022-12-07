package com.cyn.setupmanager.repository;

import com.cyn.setupmanager.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Integer>
{
    Optional<Track> findById(Integer id);
    List<Track> findAll();
    Track saveAndFlush(Track track);
    void deleteAllByIdInBatch(Iterable<Integer> ids);
}
