package com.n.gateway.controller;


import com.n.gateway.VO.City;
import com.n.gateway.VO.WeatherResponse;
import com.n.gateway.service.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/reporter")
@Slf4j
public class WeatherReporterController {

    @Autowired
    private Client client;

    @GetMapping("/index")
    public ModelAndView getReporter(@RequestParam(value = "province", required = false, defaultValue = "广东") String province,
                                    @RequestParam(value = "cityName", required = false) String cityName,
                                    Model model){
        List<City> list = client.getCityList(province);
        if(StringUtils.isEmpty(cityName)){
            cityName = list.get(0).getCityName();
        }
        WeatherResponse response = client.getWeatherDataByCityName(cityName);
        model.addAttribute("title", "据说天气预报");
        model.addAttribute("provinceName", province);
        model.addAttribute("provinceList", client.getProvinceList());
        model.addAttribute("cityName", cityName);
        model.addAttribute("cityList", list);
        model.addAttribute("report", response.getData());

        return new ModelAndView("weather/report", "reportModel", model);
    }

    @GetMapping("/{province}/{cityName}")
    public ModelAndView getReporterByCityName(@PathVariable("province") String province,
                                               @PathVariable("cityName") String cityName, Model model){
        model.addAttribute("title", "据说天气预报");
        model.addAttribute("provinceName", province);
        model.addAttribute("provinceList", client.getProvinceList());
        model.addAttribute("cityName", cityName);
        model.addAttribute("cityList", client.getCityList(province));
        model.addAttribute("report", client.getWeatherDataByCityName(cityName).getData());

        return new ModelAndView("weather/report", "reportModel", model);
    }
}
