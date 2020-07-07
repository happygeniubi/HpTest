
package com.happyge.empl.controller;

import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.model.HllcFile;
import com.happyge.empl.service.FileService;
import com.happyge.empl.utils.ResultData;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
public class FileController {

    @Autowired
    private FileService fileService;


    @RequestMapping(value="/download")
    @ResponseBody
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("filename") String filename,
                                           Model model)throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath("/image/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }

    //查询
    @RequestMapping("/listdownload")
    @ResponseBody
    public ResultData<Object> listUser(String curPage){
        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;


        PageInfo<HllcFile> pageInfo = fileService.listfile(curPage1, pageNum1);

        return ResultData.success(pageInfo);
    }


    @ResponseBody
    @RequestMapping(value="/addFile")
    public String fileUpload(HttpServletRequest request, @RequestParam(value = "file",required = false) MultipartFile file, HllcFile hllcFile, @RequestParam("title") String title, @RequestParam("creater") String creater, @RequestParam("desription") String desription) throws IOException {

        /**
         * 上传图片
         */
        //图片上传成功后，将图片的地址写到数据库
        String filePath = request.getServletContext().getRealPath("/image/");//保存图片的路径,tomcat中有配置
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
        String newFileName = UUID.randomUUID()+originalFilename;
        System.out.println(newFileName);
        //封装上传文件位置的全路径，就是硬盘路径+文件名
        File targetFile = new File(filePath,newFileName);
        //把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
        file.transferTo(targetFile);

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        hllcFile.setFlTitle(title);
        hllcFile.setFlDescribe(desription);
        hllcFile.setFlPeople(creater);
        hllcFile.setFlDate(s);
        hllcFile.setFlFile(newFileName);
        fileService.savefile(hllcFile);

        return "success"; //重定向到查询
    }

}
