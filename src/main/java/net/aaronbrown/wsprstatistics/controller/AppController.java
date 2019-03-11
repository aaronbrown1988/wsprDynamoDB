package net.aaronbrown.wsprstatistics.controller;

import net.aaronbrown.wsprstatistics.dao.SpotsDAO;
import net.aaronbrown.wsprstatistics.services.CacheService;
import net.aaronbrown.wsprstatistics.services.SpotLoadingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by aaron on 8/04/17.
 * The purpose of this is to report things about the app/Data
 */

@Controller
@RequestMapping("/api")
public class AppController {

    @Autowired
    private SpotsDAO spotsDAO;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private SpotLoadingService spotLoadingService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/lastSpotTime")
    public
    @ResponseBody
    Date lastSpotTime() {
        Date last = (Date) cacheService.getObject("lastUpdatedDate");
        if (last == null) {
            last = spotsDAO.lastUpdated();
            cacheService.putObjectWithExpiry("lastUpdatedDate", last, 604800);
        }
        return last;
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping("/getLatest")
    public
    @ResponseBody Boolean getLast() {
        try {
            spotLoadingService.getSpots(LocalDate.now().minusMonths(1));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
