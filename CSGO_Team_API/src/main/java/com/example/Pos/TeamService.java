package com.example.Pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    public List<Team> getAllTeams(){
        return repository.findAll();
    }

    public Optional<Team> getTeam(String id){return this.repository.findById(id);}

    public void saveTeam(Team team){
        this.repository.save(team);
    }

    public void deleteTeam(String id){this.repository.deleteById(id);}
}