package com.example.neo4jdemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

import java.util.Objects;

@RelationshipEntity(type = "elecApply")
@Data
public class Relation {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    @JsonIgnore
    private Device startNode;
    @EndNode
    private Device endNode;

    @Property("weight")
    private Double weight;
    @Property("type")
    private String type;

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", startNode=" + startNode.getName() +
                ", endNode=" + endNode.getName() +
                ", weight=" + weight +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return Objects.equals(id, relation.id) && Objects.equals(startNode.getName(), relation.startNode.getName())
                && Objects.equals(endNode.getName(), relation.endNode.getName()) && Objects.equals(weight, relation.weight) && Objects.equals(type, relation.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startNode.getName(), endNode.getName(), weight, type);
    }
}
