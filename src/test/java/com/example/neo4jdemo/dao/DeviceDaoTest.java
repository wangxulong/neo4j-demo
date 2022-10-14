package com.example.neo4jdemo.dao;

import com.example.neo4jdemo.entity.Device;
import com.example.neo4jdemo.entity.Relation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
class DeviceDaoTest {
    @Autowired
    private DeviceDao deviceDao;
    @Test
    @DisplayName("查询有供电关系的下级设备")
    void findByName(){
        List<Device> devices = deviceDao.findByName("设备Z");
        Set<Relation> relations = devices.get(0).getRelations();
        for (Relation relation : relations) {
            log.info("设备Z -[type:{},weight:{}]->{}", relation.getType(),relation.getWeight(),relation.getEndNode().getName());
        }
        Assertions.assertTrue(devices.size() > 0);
    }
    @Test
    void testNextDevices(){
        Set<Device> devices = deviceDao.searchNextByName("设备J");
        Assertions.assertTrue(devices.size()> 0 );

    }
    @Test
    void searchNextRelations(){
        Set<Relation> relations = deviceDao.searchRelationsByName("设备J");
        Assertions.assertTrue(relations.size()> 0 );

    }

    @Test
    void searchUpRelation() {
        Set<Device> devices = deviceDao.searchUpDevices("设备K");
        Assertions.assertTrue(devices.size() == 4);

    }
}