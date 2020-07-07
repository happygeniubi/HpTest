package com.happyge.empl.controller;

import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.model.HllcPost;
import com.happyge.empl.service.DepartmentService;
import com.happyge.empl.service.PositionService;
import com.happyge.empl.utils.ResultData;
import com.happyge.empl.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("")
public class PositionController {

  @Autowired
  private PositionService positionService;
  @Autowired
  private DepartmentService departmentService;


  //职位回显
  @RequestMapping("classList" )
  @ResponseBody
  public ResultData<Object> classList(String curPage){

    int curPage1 = Integer.parseInt(curPage);
    int pageNum1 = 5;
    Map<String,Object> map = new HashMap<String,Object>();
    PageInfo<HllcPost> pageInfo = positionService.list(curPage1, pageNum1);
    List<HllcDepartment> postList = departmentService.findPositionName();
    map.put("pageInfo",pageInfo);
    map.put("postList",postList);
    return ResultData.success(map);


  }

  //删除职位
  @RequestMapping("deleteClass")
  @ResponseBody
  public ResultData<Object> deleteClass(String poId){
    boolean b =positionService.deleteClass(poId);

    return ResultData.success(b);

  }

  //增加职位
  @RequestMapping("saveClass")
  @ResponseBody
  public ResultData<Object> savePosition(HllcPost hllcPost){

    //获取当前的时间
    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    String s = dateFormat.format(date);

    String id = UuidUtil.getUUID();

    hllcPost.setPodate(s);
    hllcPost.setPoid(id);
    positionService.savaClass(hllcPost);


    return ResultData.success();
  }

  /**
   * 按职位的名称搜索职位
   * @parm:content(搜索的内容)
   * @parm:curPage(当前页)
   *
   * */
  @RequestMapping("findClassByName")
  @ResponseBody
  public ResultData<Object> findClassByName(String content, String curPage){

    int curPage1 = Integer.parseInt(curPage);
    int pageNum1 = 5;
    String s1 = "%"+content+"%";
    Map<String,Object> map1 = new HashMap<String,Object>();
    List<HllcPost> listl=null;
    PageInfo<HllcPost> pageInfo = positionService.findClassByName(s1,curPage1,pageNum1);
    map1.put("pageInfo",pageInfo);
    map1.put("listl",listl);
    return ResultData.success1(map1);
  }

  /**
   * 通过ID得到一个职位的全部信息
   * @parm:poId
   *
   *
   * */
  @RequestMapping("selectPositionById")
  @ResponseBody
  public List<HllcPost> selectPosition(String poId){
    List<HllcPost> poList = positionService.selectPositionById(poId);

    return poList;
  }

  /**
   * 修改职位信息
   * @param :hllcPost
   *
   *
   * */
  @RequestMapping("updatePosition")
  @ResponseBody
  public ResultData<Object> updatePosition(HllcPost hllcPost){
    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    String s = dateFormat.format(date);
    hllcPost.setPodate(s);
    positionService.updatePosition(hllcPost);

    return ResultData.success();
  }

  /**
   *批量删除
   * @param :ids
   *
   * */
  @RequestMapping("deleteAllPosition")
  @ResponseBody
  public boolean deleteAllPosition(String ids){
    String[] array = ids.split(",");
    List<String> idslist = new ArrayList();
    for (String s: array) {
      idslist.add(s);
    }
    boolean b = positionService.deleteManyPosition(idslist);
    return b;
  }

}

