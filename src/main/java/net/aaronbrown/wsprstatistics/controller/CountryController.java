package net.aaronbrown.wsprstatistics.controller;

import net.aaronbrown.wsprstatistics.services.CountryService;
import net.aaronbrown.wsprstatistics.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

/**
 * Created by aaron on 17/04/17.
 */
@Controller
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private CountryService countryService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/{callsign}/{band}")
    public
    @ResponseBody
    Map<String, Double> countryByCallsignAndBand(@PathVariable String callsign, @PathVariable(required = false) Integer band) {
        return statisticsService.countryByCallsignAndBand(callsign, band);
    }

    @RequestMapping("/{callsign}/all")
    public
    @ResponseBody
    Map<String, Double> countryByCallsign(@PathVariable String callsign) {
        return statisticsService.countryByCallsign(callsign);
    }

    @RequestMapping("/list")
    public
    @ResponseBody
    List<String> countryList() {
        return countryService.getCountryList();
    }

    @RequestMapping("/band/{tx}/{rx}")
    public
    @ResponseBody
    Map<String, Double> bandForCountry(@PathVariable String tx, @PathVariable String rx) {
        return null;
    }

    @RequestMapping("/time/{tx}/{rx}")
    public
    @ResponseBody
    Map<String, Double> timeForCountry(@PathVariable String tx, @PathVariable String rx) {
        return null;
    }
}
