package com.happyge.empl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcPost;
import com.happyge.empl.repository.HllcEmployeeMapper;
import com.happyge.empl.repository.HllcPostMapper;
import com.happyge.empl.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

  @Autowired
  private HllcPostMapper hllcPostMapper;

  @Autowired
  private HllcEmployeeMapper hllcEmployeeMapper;


  @Override
  public PageInfo<HllcPost> list(int curPage, int pageNum) {
    PageHelper.startPage(curPage, pageNum);
    List<HllcPost> blogs = hllcPostMapper.list();
    PageInfo<HllcPost> pageInfo = new PageInfo<HllcPost>(blogs);
    return pageInfo;
  }

  @Override
  public boolean deleteClass(String poId) {
    int child =hllcEmployeeMapper.selectPositionEmployee(poId) ;
    boolean b = true;
    if(child<=0)
    {
      this.hllcPostMapper.deleteClass(poId);
      return b;
    }
    else
    {
      b = false;
      return b;
    }
  }

  @Override
  public void savaClass(HllcPost hllcPost) {
    hllcPostMapper.savaClass(hllcPost);
  }


  @Override
  public PageInfo<HllcPost> findClassByName(String content,int curPage,int pageNum) {
    PageHelper.startPage(curPage, pageNum);
    List<HllcPost> blogs = hllcPostMapper.findClassByName(content);
    PageInfo<HllcPost> pageInfo = new PageInfo<HllcPost>(blogs);
    return pageInfo;
  }

  //通过id找到一个职位的全部信息
  @Override
  public List<HllcPost> selectPositionById(String poId) {

    return hllcPostMapper.findPositionById(poId);
  }

  //修改职位信息
  @Override
  public void updatePosition(HllcPost hllcPost) {
    hllcPostMapper.updatePosition(hllcPost);
  }

  //批量删除
  @Override
  public boolean deleteManyPosition(List<String> idslist) {
    int countEmployee = hllcEmployeeMapper.findEmployeeBypoId(idslist);
    boolean regord = false;
    if(countEmployee==0){
      regord = true;
      hllcPostMapper.deleteAllPosition(idslist);
      return regord;
    }
    return false;
  }

  @Override
  public List<HllcPost> positionlist() {
    return hllcPostMapper.positionList();
  }
}
