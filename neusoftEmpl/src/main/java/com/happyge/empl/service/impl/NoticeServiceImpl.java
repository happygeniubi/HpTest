package com.happyge.empl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcNotice;
import com.happyge.empl.repository.HllcNoticeMapper;
import com.happyge.empl.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

  @Autowired
  private HllcNoticeMapper hllcNoticeMapper;

  //公告回显
  @Override
  public PageInfo<HllcNotice> noList(int curPage, int pageNum) {
    PageHelper.startPage(curPage, pageNum);
    List<HllcNotice> blogs = hllcNoticeMapper.noList();
    PageInfo<HllcNotice> pageInfo = new PageInfo<HllcNotice>(blogs);
    return pageInfo;
  }

  //公告总数
  @Override
  public int noListCount() {
    return hllcNoticeMapper.noListCount();
  }

  //增加公告
  @Override
  public void savaNotice(HllcNotice hllcNotice) {
    hllcNoticeMapper.saveNotice(hllcNotice);
  }

  //通过id获得一个公告的信息
  @Override
  public List<HllcNotice> selectNoticeById(Integer noId) {
    return hllcNoticeMapper.findNoticeById(noId);
  }

  //通过id删除公告的信息
  @Override
  public void deleteNotice(Integer noId) {
    hllcNoticeMapper.deleteNotice(noId);

  }

  //通过id修改公告的信息
  @Override
  public void updateNotice(HllcNotice hllcNotice){
    hllcNoticeMapper.updateNotice(hllcNotice);
  }


  //通过搜索内容搜索公告
  @Override
  public PageInfo<HllcNotice> selectNotice(String content,int curPage,int pageNum) {
    PageHelper.startPage(curPage, pageNum);
    List<HllcNotice> blogs = hllcNoticeMapper.findNotice(content);
    PageInfo<HllcNotice> pageInfo = new PageInfo<HllcNotice>(blogs);
    return pageInfo;
  }

  @Override
  public Integer deleteMany(List<Integer> list) {
    return hllcNoticeMapper.deleteMany(list);
  }
}
