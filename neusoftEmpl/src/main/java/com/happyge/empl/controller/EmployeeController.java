package com.happyge.empl.controller;

import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.model.HllcEmployee;
import com.happyge.empl.model.HllcPost;
import com.happyge.empl.service.DepartmentService;
import com.happyge.empl.service.EmployeeService;
import com.happyge.empl.service.PositionService;
import com.happyge.empl.utils.LocalUtil;
import com.happyge.empl.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private LocalUtil lu = LocalUtil.getInstance();
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    //全国数据,部门的数据
    @RequestMapping("country")
    @ResponseBody
    public ResultData<Object> country(){

        Map<String,Object> mav = new HashMap<String, Object>();

        List<String> countryList=employeeService.CountryList();
        List<HllcDepartment> departmentName = departmentService.findPositionName();

        mav.put("departmentName",departmentName);
        mav.put("countryList",countryList);
        return ResultData.success(mav);
    }

    /**
     * 搜索條件的顯示
     *
     * */
    @RequestMapping("display")
    @ResponseBody
    public Map<String,Object> display(){
        Map<String,Object> map = new HashMap<String, Object>();
        List<HllcDepartment> deList = departmentService.findPositionName();
        List<HllcPost> polist = positionService.positionlist();
        map.put("deList",deList);
        map.put("polist",polist);

        return map;

    }
    /**
     * 获得一个员工在全部信息
     * @param :enId
     *
     * */
    @RequestMapping("selectEmployeeMessage")
    @ResponseBody
    public ResultData<Object> selectEmployeeMessage(String enId){
        List<HllcDepartment> departmentName = departmentService.findPositionName();
        List<HllcEmployee> emList = employeeService.selectEmployeeMessage(enId);
        System.out.println(emList.get(0).getEnHeadphoto());
        List<String> countryList=employeeService.CountryList();
        List<String> provinceList= lu.getProvinces(emList.get(0).getEnCountry());
        List<String> cityList= lu.getCities(emList.get(0).getEnCountry(),emList.get(0).getEnProvince());
        List<String> regionList= lu.getRegion(emList.get(0).getEnCountry(),emList.get(0).getEnProvince(),emList.get(0).getEnCity());
        List<String> countryNowList=employeeService.CountryList();
        List<String> provinceNowList= lu.getProvinces(emList.get(0).getEnCountryNow());
        List<String> cityNowList= lu.getCities(emList.get(0).getEnCountryNow(),emList.get(0).getEnProvinceNow());
        List<String> regionNowList= lu.getRegion(emList.get(0).getEnCountryNow(),emList.get(0).getEnProvinceNow(),emList.get(0).getEnCityNow());

        Map<String,Object> mav = new HashMap<String,Object>();
        mav.put("emList",emList);
        mav.put("departmentName",departmentName);
        mav.put("countryList",countryList);
        mav.put("provinceList",provinceList);
        mav.put("cityList",cityList);
        mav.put("regionList",regionList);
        mav.put("countryNowList",countryNowList);
        mav.put("provinceNowList",provinceNowList);
        mav.put("cityNowList",cityNowList);
        mav.put("regionNowList",regionNowList);

        return ResultData.success(mav);
    }
    /*
     * 通过部门id获取职位
     * parme：deId
     *
     * */
    @RequestMapping("positionBydepartment")
    @ResponseBody
    public List<HllcPost> positionBydepartment(String deId){

        List<HllcPost> polist = departmentService.findPositionByDepartment(deId);

        return polist;
    }

    /*
     * 获得全国的省份
     * countryName:国家的名字
     *
     * */
    @ResponseBody
    @RequestMapping("province")
    public List<String> province(String countryName){


        List<String> provinceList = lu.getProvinces(countryName);

        return provinceList;
    }


   /* 获得各省的市区
    provinceName:省名
    countryName:国家名
    */

    @ResponseBody
    @RequestMapping("city")
    public List<String> city(String countryName,String provinceName){
        List<String> cityList = lu.getCities(countryName,provinceName);

        return cityList;
    }

    /** 获得各省的市区
     provinceName:省名
     countryName:国家名
     */


    @ResponseBody
    @RequestMapping("region")
    public List<String> region(String countryName,String provinceName,String cityName){
        List<String> regionList = lu.getRegion(countryName,provinceName,cityName);

        return regionList;
    }
    /*
     * 获得全国的省份
     * countryName:国家的名字
     *
     * */
    @ResponseBody
    @RequestMapping("provinceNow")
    public List<String> provinceNow(String countryNowName){


        List<String> provinceList = lu.getProvinces(countryNowName);

        return provinceList;
    }


   /* 获得各省的市区
    provinceName:省名
    countryName:国家名
    */

    @ResponseBody
    @RequestMapping("cityNow")
    public List<String> cityNow(String countryNowName,String provinceNowName){
        List<String> cityList = lu.getCities(countryNowName,provinceNowName);

        return cityList;
    }

    /* 获得各省的市区
    provinceName:省名
    countryName:国家名
    */

    @ResponseBody
    @RequestMapping("regionNow")
    public List<String> regionNow(String countryNowName,String provinceNowName,String cityNowName){
        List<String> regionList = lu.getRegion(countryNowName,provinceNowName,cityNowName);

        return regionList;
    }

    /**
     * 增加员工
     * parm:HllcEmployee
     *
     */

    @RequestMapping("addEmployee")
    @ResponseBody
    public String addEmployee(@RequestParam(value = "file",required = false) MultipartFile file, HllcEmployee hllcEmployee) throws IOException {
        System.out.println("......................");
        String path = "D:\\project\\ssm\\src\\main\\webapp\\uploadPhoto";//文件的上传路径

        System.out.println("path" + path);
        String fileName = file.getOriginalFilename();
        //获取文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获得文件后缀名称
        // 生成最新的uuid文件名称
        String newFileName = uuid + ".jpg";

        hllcEmployee.setEnId(uuid);
        hllcEmployee.setEnStatus("实习");
        System.out.println("文件名" + fileName);
        String path1[] = path.split("\\\\");//对于特殊字符的分隔|  ^   $  *   .    (    )   \   /等都是正则表达式的一部分，只能通过前面加上\\进行转义。注意\要用三个\\\，也就是split（“\\\\”）
        String path2 = path1[path1.length - 1];//截取图片所在的文件夹名称
        File dir = new File(path, newFileName);//将指定文件上传到指定的目录下
        /**
         * 如果文件夹不存在，自动创建该文件夹
         */
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.isEmpty()) {
            file.transferTo(dir);
            hllcEmployee.setEnHeadphoto(newFileName);
            employeeService.addEmployee(hllcEmployee);
            String s = "成功";
            return s;
        }else {
            return "失败";
        }

    }

    /*
     * 正式员工显示
     * enstuts="在职"
     *
     * */
    @RequestMapping("fomalEmployee")
    @ResponseBody
    public ResultData<Object> fomalEmployee(String curPage){

        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;

        PageInfo<HllcEmployee> pageInfo = employeeService.formalList(curPage1,pageNum1);

        return ResultData.success(pageInfo);

    }

    /*
     * 实习员工的显示
     * enstuts=“实习”
     *
     *
     * */
    @RequestMapping("internshipEmployee")
    @ResponseBody
    public ResultData<Object> internshipEmployee(String curPage){


        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;

        PageInfo<HllcEmployee> pageInfo = employeeService.internList(curPage1,pageNum1);

        return ResultData.success(pageInfo);
    }


    /**
     *
     * 离职员工
     * enStatus="离职" 为离职员工
     *
     * */

    @RequestMapping("quitEmployee")
    @ResponseBody
    public ResultData<Object> quitEmployee(String curPage){


        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;

        PageInfo<HllcEmployee> pageInfo = employeeService.quitList(curPage1,pageNum1);


        return ResultData.success(pageInfo);


    }

    /*
     * 实习员工淘汰
     *把enstuts设置为离职
     *
     * */
    @RequestMapping("eliminateEmployee")
    @ResponseBody
    public ResultData<Object> eliminateEmployee(String enId){

        employeeService.eliminateEmployee(enId);

        return ResultData.success();
    }

    /*
     * 把离职员工删除
     * parm:enId
     *
     * */
    @RequestMapping("deleteEmployee")
    @ResponseBody
    public ResultData<Object> deleteEmployee(String enId){

        employeeService.deleteEmployee(enId);
        return ResultData.success();
    }

    /*
     * 把离职员工重新录取
     * 把enstuts设置成实习
     *
     * */

    @RequestMapping("ReAdmissionEmployee")
    @ResponseBody
    public ResultData<Object> ReAdmissionEmployee(String enId){

        employeeService.ReAdmissionEmployee(enId);

        return ResultData.success();

    }


    /*
     * 把实习员工正式录取
     * 把enstuats="在职"
     *
     * */
    @RequestMapping("admissionEmployee")
    @ResponseBody
    public ResultData<Object> admissionEmployee(String enId){
        employeeService.admissionEmployee(enId);
        return ResultData.success();
    }

    /**
     * 搜索正式员工
     * parm：department
     * parm：position
     *
     * */
    @RequestMapping("selectFomalEmployee")
    @ResponseBody
    public ResultData<Object> selectFomalEmployee(HllcEmployee hllcEmployee,String curPage){
        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;

        PageInfo<HllcEmployee> pageInfo = employeeService.findFomalEmployee(curPage1,pageNum1,hllcEmployee);


        return ResultData.success1(pageInfo);



    }

    /**
     * 员工修改
     * @param : hllcemployee
     *
     * */

    @RequestMapping("updateEmployee")
    @ResponseBody
    public String updateEmployee(@RequestParam(value = "file",required = false) MultipartFile file,HllcEmployee hllcEmployee) throws Exception{

        System.out.println("......................");
        String path = "D:\\project\\ssm\\src\\main\\webapp\\uploadPhoto";//文件的上传路径

        System.out.println("path" + path);
        String fileName = file.getOriginalFilename();
        //获取文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获得文件后缀名称
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // 生成最新的uuid文件名称
        String newFileName = uuid + "."+ suffixName;

        System.out.println("文件名" + fileName);
        String path1[] = path.split("\\\\");//对于特殊字符的分隔|  ^   $  *   .    (    )   \   /等都是正则表达式的一部分，只能通过前面加上\\进行转义。注意\要用三个\\\，也就是split（“\\\\”）
        String path2 = path1[path1.length - 1];//截取图片所在的文件夹名称
        File dir = new File(path, newFileName);//将指定文件上传到指定的目录下

        /**
         * 如果文件夹不存在，自动创建该文件夹
         */
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.isEmpty()) {
            file.transferTo(dir);
            hllcEmployee.setEnHeadphoto(newFileName);
            employeeService.updateEmployee(hllcEmployee);
            String s = "成功";
            return s;
        }else {
            return "失败";
        }

    }

    /**
     * 员工批量删除
     * @param :ids
     *
     * */
    @RequestMapping("deleteAllEmployee")
    @ResponseBody
    public String deleteAllEmployee(String ids){
        String[] array = ids.split(",");
        List<String> idslist = new ArrayList();
        for (String s: array) {
            idslist.add(s);
        }
        employeeService.deleteAllEmployee(idslist);
        return "成功";
    }

//    /**
//     * 搜索员工
//     * @param :hlloyeeEmployee
//     *
//     * */
//    @RequestMapping("SelectEmployeeByCondition")
//    @ResponseBody
//    public ResultData<Object> selectEmployeeByCondition(HllcEmployee hllcEmployee){
//
//
//    }
//



}
