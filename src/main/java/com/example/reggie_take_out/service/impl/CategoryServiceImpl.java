package com.example.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie_take_out.common.CustomException;
import com.example.reggie_take_out.entity.Category;
import com.example.reggie_take_out.entity.Dish;
import com.example.reggie_take_out.entity.Setmeal;
import com.example.reggie_take_out.mapper.CategoryMapper;
import com.example.reggie_take_out.service.CategoryService;
import com.example.reggie_take_out.service.DishService;
import com.example.reggie_take_out.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    /*根据id删除分类，删除之前要进行判断*/
    @Autowired
    DishService dishService;
    @Autowired
    SetMealService setMealService;
    @Override
    public void remove(long id) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        queryWrapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(queryWrapper);
        if (count > 0) {
            //已经关联了菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> queryWrapper2=new LambdaQueryWrapper<>();
        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        queryWrapper2.eq(Setmeal::getCategoryId,id);
        int count2 = setMealService.count(queryWrapper2);
        if(count2>0){
            //已经关联了套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
