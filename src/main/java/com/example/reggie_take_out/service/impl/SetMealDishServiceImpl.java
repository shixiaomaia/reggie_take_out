package com.example.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_take_out.entity.SetmealDish;
import com.example.reggie_take_out.mapper.SetMealDishMapper;
import com.example.reggie_take_out.service.SetMealDishService;
import org.springframework.stereotype.Service;

@Service
public class SetMealDishServiceImpl extends ServiceImpl<SetMealDishMapper, SetmealDish> implements SetMealDishService {
}
