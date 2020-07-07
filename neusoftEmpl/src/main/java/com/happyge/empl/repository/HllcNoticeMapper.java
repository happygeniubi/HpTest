package com.happyge.empl.repository;

import com.happyge.empl.model.HllcNotice;

import java.util.List;

public interface HllcNoticeMapper {

  //公告回显
  List<HllcNotice> noList();

  //公告总数
  int noListCount();

  //增加公告
  void saveNotice(HllcNotice hllcNotice);

  //通过id获得一个公告的信息
  List<HllcNotice> findNoticeById(Integer noId);

  //通过id删除一个公告
  void deleteNotice(Integer noId);

  //通过id修改公告信息
  void updateNotice(HllcNotice hllcNotice);

  //通过搜索内容搜索公告
  List<HllcNotice> findNotice(String content);

  Integer deleteMany(List<Integer> list);
}
