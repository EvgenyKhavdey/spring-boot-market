package ru.gb.springbootdemoapp.service;

import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.dto.ControllerTime;

@Service
public class AspectService {

    public ControllerTime getAspectTime(){
        ControllerTime controllerTime = new ControllerTime();
        return controllerTime;
    }
}
