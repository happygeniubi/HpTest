package com.happyge.empl.controller;

import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.model.HllcNotice;
import com.happyge.empl.service.DepartmentService;
import com.happyge.empl.service.NoticeService;
import com.happyge.empl.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("")
public class NoticeController {

  @Autowired
  private NoticeService noticeService;

  @Autowired
  private DepartmentService departmentService;

  /**
   * 内容回显
   *
   *
   * */
  @RequestMapping("noticeList")
  @ResponseBody
  public ResultData<Object> noticeList(String curPage){

    int curPage1 = Integer.parseInt(curPage);
    int pageNum1 = 5;

    List<HllcDepartment> deList = departmentService.findPositionName();
    PageInfo<HllcNotice> pageInfo = noticeService.noList(curPage1, pageNum1);

    Map<String,Object> map = new HashMap<String,Object>();
    map.put("deList",deList);
    map.put("pageInfo",pageInfo);
    return ResultData.success(map);
  }


  /**
   * 增加公告
   * @param :hllcNotice
   *
   * */

  @RequestMapping("saveNotice")
  @ResponseBody
  public ResultData<Object> savePosition(HllcNotice hllcNotice){

    //获得当前时间
    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    String s = dateFormat.format(date);

    hllcNotice.setNoDate(s);
    noticeService.savaNotice(hllcNotice);


    return ResultData.success();
  }

  /**
   * 通过id获得公告的信息
   * @param :noId
   *
   * */
  @RequestMapping("selectNoticeById")
  @ResponseBody
  public List<HllcNotice> selectNoticeById(Integer noId){

    List<HllcNotice> noList = noticeService.selectNoticeById(noId);

    return noList;
  }

  /**
   * 通过id删除公告
   * @param:noId
   *
   * */
  @RequestMapping("deleteNotice")
  @ResponseBody
  public ResultData<Object> deleteNotice(Integer noId){
    noticeService.deleteNotice(noId);
    return ResultData.success();
  }

  /**
   *通过id修改公告
   * @param:hllcNotic
   */

  @RequestMapping("updateNotice")
  @ResponseBody
  public ResultData<Object> updateNotice(HllcNotice hllcNotice){
    //获得当前时间
    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    String s = dateFormat.format(date);

    hllcNotice.setNoDate(s);
    noticeService.updateNotice(hllcNotice);
    return ResultData.success();
  }

  /**
   * 通过搜素内容搜素公告
   * @param :content  curPage
   *
   *
   * */
  @RequestMapping("selectNotice")
  @ResponseBody
  public ResultData<Object> selectNotice(String content, HttpSession httpSession,int curPage){

    int pageNum = 5;
    Map<String,Object> parm = new HashMap<String,Object>();
    parm.put("curPage",curPage);
    parm.put("pageNum",pageNum);
    Map<String,Object> map = new HashMap<String,Object>();
    if(content!=""){
      String s = "%"+content+"%";

      PageInfo<HllcNotice> pageInfo = noticeService.selectNotice(s,curPage,pageNum);
      httpSession.setAttribute("s",s);
      map.put("pageInfo",pageInfo);

      return ResultData.success1(map);

    }else {
      content ="%"+ (String) httpSession.getAttribute("s")+"%";
      PageInfo<HllcNotice> pageInfo = noticeService.selectNotice(content,curPage,pageNum);
      map.put("pageInfo",pageInfo);
      return ResultData.success1(map);
    }

  }

  /**
   * 批量删除
   * @param :ids   所有选中的id
   */

  @RequestMapping(value="/department/deleteMany")
  public
  @ResponseBody
  String deleteMany(String ids) {
    if (ids == null ) {
      return "FAILURE";
    }
    String[] array = ids.split(",");
    List<Integer> list = new ArrayList();
    for (String s: array) {
      int departmentRecId = Integer.parseInt(s);
      list.add(departmentRecId);
    }
    Integer count = noticeService.deleteMany(list);
    if (count != null && count > 0) {
      return "SUCCESS";
    }
    return "FAILURE";
  }

}

