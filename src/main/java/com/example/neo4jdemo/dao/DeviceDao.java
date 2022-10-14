package com.example.neo4jdemo.dao;

import com.example.neo4jdemo.entity.Device;
import com.example.neo4jdemo.entity.Relation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DeviceDao extends Neo4jRepository<Device,Long> {

    List<Device> findByName(String name);
    @Query("match (p:Device{name: $name})-[r:elecApply]->(b) return p, r,b")
    Set<Device> searchNextByName(@Param("name") String name);



    @Query("match (p:Device{name: $name})-[r*0..]->(b) return p, r,b ")
    Set<Relation> searchRelationsByName(@Param("name") String name);

    /**
     * 查询上级供电电源
     * @param name
     * @return
     */
    @Query("match (p:Device)-[r:elecApply*1]->(b:Device{name: $name}) return p, r,b")
    Set<Device> searchUpDevices(@Param("name") String name);
}

