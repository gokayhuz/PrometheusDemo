/**
 * Copyright 2003-2017 Monitise Group Limited. All Rights Reserved.
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.monitise.prometheus_demo;

import com.monitise.prometheus_demo.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Random;

@RestController
public class DemoController {

    private static Random random = new Random();

    @Autowired
    private RemoteService remoteService;

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    @ResponseBody
    public String sortNumbers(HttpServletRequest request) {
        int upperLimit = (int)(Math.random() * 10_000_000);

        int[] array = new int[upperLimit];
        for (int i = 0; i < upperLimit; i++) {
            array[i] = (int)(10_000 * Math.random());
        }
        Arrays.sort(array);
        System.out.printf("sorted %d integers\n", upperLimit);
        return "sorted " + upperLimit + " integers";
    }

    @RequestMapping(value = "/test")
    public String nowork(HttpServletRequest request) {
        System.out.println("test");
        return "test";
    }

    @RequestMapping(value = "/dowork", method = {RequestMethod.GET})
    @ResponseBody
    public String work(HttpServletRequest request) throws InterruptedException {
        final int MAX_SLEEP_TIME = 1_000;
        int timeToSleep = random.nextInt(MAX_SLEEP_TIME);

        // simulate some processing
        Thread.sleep(timeToSleep);

        // make a remote call
        remoteService.someRemoteCall();

        // simulate some post processing after remote call
        Thread.sleep(timeToSleep);
        System.out.println("dowork");
        return "request";
    }
}

