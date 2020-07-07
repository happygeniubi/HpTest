package com.happyge.empl.service;

import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcNotice;

import java.util.List;

public interface NoticeService {

  //公告回显
  PageInfo<HllcNotice> noList(int curPage, int pageNum);

  //公告总数
  int noListCount();

  //增加公告
  void savaNotice(HllcNotice hllcNotice);

  //通过id获得一个公告的信息
  List<HllcNotice> selectNoticeById(Integer noId);

  //通过id删除公告
  void deleteNotice(Integer noId);

  //通过id修改公告
  void updateNotice(HllcNotice hllcNotice);

  //通过搜素内容搜索公告
  PageInfo<HllcNotice> selectNotice(String content,int curPage,int pageNum);

  Integer deleteMany(List<Integer> list);
}
