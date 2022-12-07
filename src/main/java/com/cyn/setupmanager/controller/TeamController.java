package com.cyn.setupmanager.controller;

import com.cyn.setupmanager.controller.dto.NewSetupDto;
import com.cyn.setupmanager.controller.dto.NewTeamDto;
import com.cyn.setupmanager.controller.dto.UpdateSetupDto;
import com.cyn.setupmanager.controller.dto.UpdateTeamDto;
import com.cyn.setupmanager.domain.*;
import com.cyn.setupmanager.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController
{
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final SetupRepository setupRepository;
    private final SetupVariantRepository setupVariantRepository;
    private final CarRepository carRepository;
    private final TrackRepository trackRepository;

    @GetMapping(value="/{id}")
    public Team getTeamById(@PathVariable("id") Integer id) {
        return teamRepository.findById(id).get();
    }

    @GetMapping
    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

    @DeleteMapping(value="/{id}")
    public boolean deleteTeamById(@PathVariable("id") Integer id) {
        try {
            teamRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            log.error(e.toString());
            return false;
        }
    }

    @PostMapping
    public Team createTeam(@RequestBody NewTeamDto newTeam, Authentication authentication) {
        String userName = authentication.getName();
        Optional<User> optU = userRepository.findByUsername(userName);
        if (optU.isPresent()) {
            User u = optU.get();
            if (u.getTeam() != null) {
                return null;
            }
            List<User> memberList = new ArrayList<User>();
            memberList.add(u);
            Team nt = new Team();
            nt.setName(newTeam.getName());
            nt.setDescription(newTeam.getDescription());
            nt.setSetups(new ArrayList<Setup>());
            nt.setMembers(memberList);
            nt.setOwnerId(u.getId());

            Team createdTeam = teamRepository.saveAndFlush(nt);
            u.setTeam(createdTeam);
            userRepository.saveAndFlush(u);

            return createdTeam;
        }
        else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public Team updateTeam(@RequestBody UpdateTeamDto upT, @PathVariable("id") Integer id, Authentication authentication) {
        String userName = authentication.getName();
        Optional<User> optU = userRepository.findByUsername(userName);
        if (optU.isPresent()){
            User u = optU.get();
            Optional<Team> optT = teamRepository.findById(id);
            if (optT.isPresent()) {
                Team t = optT.get();
                if (t.getMembers().contains(u)) {
                    t.setName(upT.getName());
                    t.setDescription(upT.getDescription());
                    t.setOwnerId(upT.getOwnerId());
                    List<User> userList = userRepository.findAllById(upT.getMemberIds());
                    for (User mem : userList) {
                        if (!t.getMembers().contains(mem)) {
                            t.getMembers().add(mem);
                        }
                    }
                    Team savedTeam = teamRepository.saveAndFlush(t);
                    for (User mem: userList) {
                        mem.setTeam(savedTeam);
                        userRepository.saveAndFlush(mem);
                    }
                    return savedTeam;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    @PostMapping("/{id}/setups")
    public Setup createSetup(@PathVariable("id") Integer teamId, @RequestBody NewSetupDto setup, Authentication authentication){
        String userName = authentication.getName();
        Optional<User> optU = userRepository.findByUsername(userName);
        if (optU.isPresent()){
            User u = optU.get();
            Optional<Team> optT = teamRepository.findById(teamId);
            Optional<Car> optC = carRepository.findById(setup.getCarId());
            Optional<Track> optTr = trackRepository.findById(setup.getTrackId());
            if (optT.isPresent() && optC.isPresent() && optTr.isPresent()) {
                Car c = optC.get();
                Track tr = optTr.get();
                Team t = optT.get();

                if (t.getMembers().contains(u)) {
                    Setup s = new Setup();
                    s.setTeam(t);
                    s.setCar(c);
                    s.setTrack(tr);
                    Setup savedSetup = setupRepository.saveAndFlush(s);

                    SetupVariant sv = new SetupVariant();
                    sv.setCarSetup(setup.getCarSetup());
                    sv.setVersion(1);
                    sv.setSetup(savedSetup);
                    SetupVariant savedSv = setupVariantRepository.saveAndFlush(sv);

                    return savedSetup;
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    @PostMapping("/{teamId}/setups/{setupId}")
    public Setup updateSetup(@RequestBody UpdateSetupDto setup, @PathVariable("teamId")Integer teamId, @PathVariable("setupId")Integer setupId, Authentication authentication) {
        String userName = authentication.getName();
        Optional<User> optU = userRepository.findByUsername(userName);
        if (optU.isEmpty()){
            return null;
        }
        User u = optU.get();
        Optional<Team> optT = teamRepository.findById(teamId);
        if (optT.isEmpty()) {
            return null;
        }
        Team t = optT.get();
        if (!t.getMembers().contains(u)) {
            return null;
        }
        Optional<Setup> optS = setupRepository.findById(setupId);
        if (optS.isEmpty()){
            return null;
        }
        Setup s = optS.get();
        SetupVariant sv = new SetupVariant();
        sv.setSetup(s);
        sv.setVersion(s.getSetupVariants().size() + 1);
        sv.setCarSetup(setup.getCarSetup());
        setupVariantRepository.saveAndFlush(sv);
        return s;
    }

    @GetMapping("/{id}/setups")
    public List<Setup> getAllSetupsByTeamId(@PathVariable("id")Integer id, Authentication authentication) {
        String userName = authentication.getName();
        Optional<User> optU = userRepository.findByUsername(userName);
        if (optU.isEmpty()){
            return null;
        }
        User u = optU.get();
        Optional<Team> optT = teamRepository.findById(id);
        if (optT.isEmpty()) {
            return null;
        }
        Team t = optT.get();
        if (!t.getMembers().contains(u)) {
            return null;
        }

        return t.getSetups();
    }

    @GetMapping("/{teamId}/setups/{setupId}")
    public Setup getSetupDetailsByTeamIdAndSetupId(@PathVariable("teamId")Integer teamId, @PathVariable("setupId")Integer setupId, Authentication authentication){
        String userName = authentication.getName();
        Optional<User> optU = userRepository.findByUsername(userName);
        if (optU.isEmpty()){
            return null;
        }
        User u = optU.get();
        Optional<Team> optT = teamRepository.findById(teamId);
        if (optT.isEmpty()) {
            return null;
        }
        Team t = optT.get();
        if (!t.getMembers().contains(u)) {
            return null;
        }
        Optional<Setup> optS = setupRepository.findById(setupId);
        if (optS.isEmpty()) {
            return null;
        }
        Setup s = optS.get();
        if (!t.getSetups().contains(s)) {
            return null;
        }
        return s;
    }
}
