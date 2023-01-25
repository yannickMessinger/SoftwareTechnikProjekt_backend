package de.hsrm.mi.swt02.backend.api.map.service;

import de.hsrm.mi.swt02.backend.api.map.repository.MapObjectTypeRepository;
import de.hsrm.mi.swt02.backend.domain.map.MapObjectType;
import de.hsrm.mi.swt02.backend.domain.map.ObjectTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration (classes = {MapObjectTypeServiceImpl.class})
@ExtendWith (SpringExtension.class)
class MapObjectTypeServiceImplTest {
    @MockBean
    private MapObjectTypeRepository mapObjectTypeRepository;

    @Autowired
    private MapObjectTypeServiceImpl mapObjectTypeServiceImpl;

    /**
     * Method under test: {@link MapObjectTypeServiceImpl#findAllMapObjectType()}
     */
    @Test
    void testFindAllMapObjectType () {
        ArrayList<MapObjectType> mapObjectTypeList = new ArrayList<>();
        when(mapObjectTypeRepository.findAll()).thenReturn(mapObjectTypeList);
        List<MapObjectType> actualFindAllMapObjectTypeResult = mapObjectTypeServiceImpl.findAllMapObjectType();
        assertSame(mapObjectTypeList, actualFindAllMapObjectTypeResult);
        assertTrue(actualFindAllMapObjectTypeResult.isEmpty());
        verify(mapObjectTypeRepository).findAll();
    }

    /**
     * Method under test: {@link MapObjectTypeServiceImpl#findMapObjectTypeById(long)}
     */
    @Test
    void testFindMapObjectTypeById () {
        MapObjectType mapObjectType = new MapObjectType();
        mapObjectType.setGroupId(123L);
        mapObjectType.setModel3d("Model3d");
        mapObjectType.setName("Name");
        mapObjectType.setObjectTypeId(123L);
        mapObjectType.setRotation(1);
        mapObjectType.setTexture("Texture");
        mapObjectType.setType(ObjectTypeEnum.STREET);
        mapObjectType.setVersion(1L);
        Optional<MapObjectType> ofResult = Optional.of(mapObjectType);
        when(mapObjectTypeRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(mapObjectType, mapObjectTypeServiceImpl.findMapObjectTypeById(123L));
        verify(mapObjectTypeRepository).findById((Long) any());
    }
}

