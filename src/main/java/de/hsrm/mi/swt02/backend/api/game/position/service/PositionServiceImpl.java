package de.hsrm.mi.swt02.backend.api.game.position.service;

import de.hsrm.mi.swt02.backend.api.game.position.repository.PositionRepository;
import de.hsrm.mi.swt02.backend.domain.position.ObjectPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<ObjectPosition> findAllPositions () {
        return positionRepository.findAll();
    }

    @Override
    public void deletePosition (long id) {
        positionRepository.deleteById(id);
    }

    @Override
    public ObjectPosition createPosition (long mapObjectId, double x, double y, double[] rotation) {
        var objectPosition = new ObjectPosition(mapObjectId, x, y, rotation);
        return positionRepository
                .save(objectPosition);
    }

    @Override
    public void updatePosition (long objectPositionId, double x, double y, double[] rotation) {
        var optionalObjectPosition = positionRepository.findById(objectPositionId);
        if (optionalObjectPosition.isPresent()) {
            var objectPosition = optionalObjectPosition.get();
            objectPosition.setPosX(x);
            objectPosition.setPosY(y);
            objectPosition.setRotation(rotation);
            positionRepository.save(objectPosition);
        } else {
            log.debug("MapObject not found");
        }
    }
}
