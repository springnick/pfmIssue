package com.nick.demo.service2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceService implements IRoleService {
    private static final Logger log= LoggerFactory.getLogger(RoleServiceService.class);
    @Override

    public String checkAccess() {
        log.info("checking access");
        int size= (int) (Math.random()*20);
        for(int i=0;i<size;i++){
            try {
                Thread.sleep((long) (50*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
