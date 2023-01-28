package de.hsrm.mi.swt02.backend.domain.map;

import org.junit.jupiter.api.Test;

class MapObjectTypeTest {
    MapObjectType mapObjectType = new MapObjectType(0L, 0L, ObjectTypeEnum.STREET, 0, "name", "texture", "model3d");

    @Test
    void testSetObjectTypeId () {
        mapObjectType.setObjectTypeId(0L);
    }

    @Test
    void testSetVersion () {
        mapObjectType.setVersion(0L);
    }

    @Test
    void testSetGroupId () {
        mapObjectType.setGroupId(0L);
    }

    @Test
    void testSetType () {
        mapObjectType.setType(ObjectTypeEnum.STREET);
    }

    @Test
    void testSetRotation () {
        mapObjectType.setRotation(0);
    }

    @Test
    void testSetName () {
        mapObjectType.setName("name");
    }

    @Test
    void testSetTexture () {
        mapObjectType.setTexture("texture");
    }

    @Test
    void testSetModel3d () {
        mapObjectType.setModel3d("model3d");
    }
}

