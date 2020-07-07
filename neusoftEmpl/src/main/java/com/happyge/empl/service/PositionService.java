package com.happyge.empl.service;

import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcPost;

import java.util.List;

public  interface PositionService {

  //所有职位
  PageInfo<HllcPost> list(int curPage, int pageNum);

  //删除职位
  boolean deleteClass(String poId);

  //增加职位
  void savaClass(HllcPost hllcPost);


  //通过名字查找职位
  PageInfo<HllcPost> findClassByName(String content,int curPage,int pageNum);

  //通过id找到一个职位全部信息
  List<HllcPost> selectPositionById(String poId);

  //修改职位信息
  void updatePosition(HllcPost hllcPost);

  //批量删除
  boolean deleteManyPosition(List<String> idslist);

  //所有職位
  List<HllcPost> positionlist();
}
