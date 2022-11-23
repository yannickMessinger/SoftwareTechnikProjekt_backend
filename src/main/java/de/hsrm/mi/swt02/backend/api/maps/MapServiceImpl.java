package de.hsrm.mi.swt02.backend.api.maps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private MapRepository mapRepository;

    @Override
    @Transactional
    public List<Map> findAllMaps() {

        List<Map> maps = mapRepository.findAll();

        if (maps.isEmpty())
            log.warn("DB is empty.. found no Maps´s");

        return maps;
    }

    @Override
    @Transactional
    public Map findMapByID(long id) {

        Optional<Map> optionalMap = mapRepository.findById(id);

        if (optionalMap.isEmpty())
            log.warn("No Map with given ID was found");

        return optionalMap.get();
    }

    @Override
    @Transactional
    public long createMap(Map map) {

        if (map != null)
            return mapRepository.save(map).getId();
        else
            return 0;
    }

    @Override
    @Transactional
    public void deleteMap(long id) {

        mapRepository.deleteById(id);
    }
}
