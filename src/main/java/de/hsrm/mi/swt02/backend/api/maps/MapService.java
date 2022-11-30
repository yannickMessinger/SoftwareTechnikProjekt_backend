package de.hsrm.mi.swt02.backend.api.maps;

import java.util.List;

public interface MapService {

    public List<Map> findAllMaps();
    public Map findMapByID(long id);
    public long createMap(Map map);
    public void deleteMap(long id);
//  public void addMapElements(List<MapElement> elements);
//  public List<MapElement> getMapElements();
//  public MapElement getMapElementByID(long id);
//  public void deleteMapElementByID(long id);

}
