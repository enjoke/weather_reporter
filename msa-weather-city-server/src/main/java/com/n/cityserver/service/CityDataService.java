package com.n.cityserver.service;

import com.n.cityserver.VO.City;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Map;

public interface CityDataService {
    Map<String, List<City>> getCityList() throws JAXBException;

    List<String> getProvinceList() throws JAXBException;
}
