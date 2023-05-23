package com.example.Pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TeamController {   

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping("/")
    public ResponseEntity<String> defaultRoute(){
        return new ResponseEntity<>("The service is running", HttpStatus.OK);
    }

    @RequestMapping("/teams")
    public ResponseEntity<List<Team>> allTeams() {
        List<Team> teams = teamService.getAllTeams();
        if (teams.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(teams);
        }
    }

    @RequestMapping("/team/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable String id) {
        Optional<Team> optionalTeam = teamService.getTeam(id);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/team")
    //Fehler war das @RequestBody gefehlt hat
    public ResponseEntity<String> addTeam(@RequestBody Team team) {
        try {
            teamService.saveTeam(team);
            return new ResponseEntity<>("Your team was added.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding the team: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable String id, @RequestBody Team updatedTeam) {
        Optional<Team> optionalTeam = teamService.getTeam(id);
        if (optionalTeam.isPresent()) {
            Team existingTeam = optionalTeam.get();
            existingTeam.setName(updatedTeam.getName());
            existingTeam.setLocation(updatedTeam.getLocation());
            existingTeam.setRegion(updatedTeam.getRegion());
            existingTeam.setFounders(updatedTeam.getFounders());
            existingTeam.setPlayers(updatedTeam.getPlayers());
            existingTeam.setUrl(updatedTeam.getUrl());

            teamService.deleteTeam(id); // Delete the old team
            teamService.saveTeam(existingTeam); // Save the new team

            return new ResponseEntity<>("Team " + existingTeam.getName() + " has been updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Team with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/del/team/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable String id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if(optionalTeam.isPresent()) {
            teamService.deleteTeam(id);
            return new ResponseEntity<>("Team has been deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Team has not been found", HttpStatus.NOT_FOUND);
        }
    }
}