package com.cyn.setupmanager.controller;

import com.cyn.setupmanager.domain.Track;
import com.cyn.setupmanager.repository.TrackRepository;
import com.electronwill.nightconfig.core.conversion.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tracks")
public class TrackController
{
    private final TrackRepository trackRepository;

    @GetMapping(value="/get/{id}")
    public Track getTrackById(@PathVariable("id") Integer id) {
        return trackRepository.findById(id).get();
    }

    @GetMapping(value = "/get")
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @PostMapping()
    public boolean createTrack(@RequestBody Track track)
    {
        Track t = trackRepository.saveAndFlush(track);
        return t != null;
    }

    @PutMapping(value = "/{id}")
    public boolean updateTrack(@RequestBody Track track, @PathVariable Integer id) {
        Optional<Track> optT = trackRepository.findById(id);
        if (optT.isPresent()) {
            Track t = optT.get();
            t.setName(track.getName());
            t.setLength(track.getLength());

            trackRepository.saveAndFlush(t);
            return true;
        }
        else {
            return false;
        }
    }

    @DeleteMapping(value ="/{id}")
    public boolean deleteTrack(@PathVariable Integer id) {
        List<Integer> idsToDelete = new ArrayList<>();
        idsToDelete.add(id);
        trackRepository.deleteAllByIdInBatch(idsToDelete);
        return true;
    }

    @GetMapping(value="/{id}/trackMap")
    public @ResponseBody  ResponseEntity<InputStreamResource> getImageWithMediaType(@PathVariable("id")Integer id) throws IOException
    {
        MediaType contentType = MediaType.IMAGE_PNG;
        InputStream in = (new ClassPathResource("static/track_"+id+".png")).getInputStream();
                //getClass().getResourceAsStream("static/track_"+id);
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }
}
