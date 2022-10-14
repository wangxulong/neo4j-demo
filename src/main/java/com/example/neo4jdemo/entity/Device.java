package com.example.neo4jdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity(label = "Device")
@Data
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private final String name;
//
    @Relationship(type = "elecApply")
    private Set<Device> devices = new HashSet<>();
//
    @Relationship(type = "elecApply")
    private Set<Relation> relations = new HashSet<>();


    @Relationship(type = "elecApply", direction = Relationship.INCOMING)
    private Set<Relation> backRelations = new HashSet<>();



}
