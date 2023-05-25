package com.example.Pos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.UUID;

@Document(collection = "Teams")
public class Team {
    //Fehler konnte wegen @Field() keine Objecte löschen
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("location")
    private String location;
    @Field("region")
    private String region;
    @Field("founders")
    private String[] founders;
    @Field("players")
    private String[] players;
    @Field("logo")
    private String url;


    public Team(String name, String location,String region,String[] founders, String[] players, String url){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        this.region = region;
        this.founders = founders;
        this.players = players;
        this.url = url;
    }

    @Override
    public String toString(){
        return String.format(
                "Team[id=%s, name=%s, location=%s, region=%s, founders=%s, players=%s]",
                id,name,location,region, Arrays.toString(founders), Arrays.toString(players));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String[] getFounders() {
        return founders;
    }

    public void setFounders(String[] founders) {
        this.founders = founders;
    }

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}
}