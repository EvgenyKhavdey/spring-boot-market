package ru.gb.springbootdemoapp.converter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.gb.springbootdemoapp.model.Order;
import ru.gb.springbootdemoapp.model.OrderView;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public abstract class OrderMapper {

    public abstract OrderView mapDto(Order order);
}
