<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: liyuchen
  Date: 2019/7/26
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>实习员工</title>

    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/plugins/headPhoto.css" rel="stylesheet">
    <link href="./js/plugins/kindeditor-4.1.10/themes/default/default.css" rel="stylesheet">
    <link href="./css/common.css" rel="stylesheet">

    <script src="./js/jquery-1.11.1.min.js"></script>
    <script src="./js/plugins/kindeditor-4.1.10/zh_CN.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/employee_info1.js"></script>
    <script src="./js/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="./js/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

</head>

<body>
<form id="employee" >
    <div class="container main-container">
        <div class="emi-box">
            <span class="emi-title">员工信息</span>
            <div class="row">
                <div class="col-md-6">带 <span>*</span> 为必须填</div>
                <div class="col-md-6 text-align-right">
                    <button type="button" onclick="addEmplpyee()"  class="btn btn-info btn-save-or-modify">
                        <span class="glyphicon glyphicon-th"></span>&nbsp;保存或修改员工信息
                    </button>
                </div>
            </div>
            <form id="headphoto"  enctype="multipart/form-data">

                <!-- 基本信息表格 -->
                <table class="table table-bordered margin-top-10 emi-table">
                    <tr>
                        <td class="emi-key fullname-label"><span>*</span>姓名:</td>
                        <td><input type="text" id="enName" name="enName" class="emi-input fullname" placeholder="姓名" required/></td>
                        <td class="emi-key"><span>*</span>性别:</td>
                        <td colspan="3">
                            <label class="emi-label"><input type="radio" name="enSex"  value="男"  checked="true" />男</label>
                            <label class="emi-label"><input type="radio" name="enSex" value="女"  />女</label>
                        </td>
                        <td class="emi-photo" rowspan="5">
                            <div id="imgPreview">
                                <div id="prompt3">
                                    <span id="imgSpan">点击上传</span>
                                    <br/>
                                    <input type="file" id="file" name="file" class="filepath" onchange="changepic(this)" accept="image/jpg,image/jpeg,image/png,image/PNG">
                                </div>
                                <img src="./img/upload.jpg" alt="" title="" id="img3"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="emi-key"><span>*</span>身份证号:</td>
                        <td><input type="text" id="enIdentity" name="enIdentity" class="emi-input" placeholder="身份证号" date/></td>
                        <td class="emi-key"><span>*</span>出生年月:</td>
                        <td><input type="text" id="enBirthday" name="enBirthday" class="emi-input date-before" placeholder="出生年月"/></td>
                        <td class="emi-key">社保卡号:</td>
                        <td><input type="text" id="enSocial" name="enSocial" class="emi-input" placeholder="社保卡号"/></td>
                    </tr>
                    <tr>
                        <td class="emi-key depl-label"><span>*</span>部门:</td>
                        <td>
                            <select id="enDepartment" class="emi-select depl">
                                <option selected="selected" value="0">请选择</option>

                            </select>
                        </td>
                        <td  class="emi-key"><span>*</span>职位</td>
                        <td><select id="enPost" class="emi-select"><option>请选择...</option></select></td>
                        <td class="emi-key"><span>*</span>上岗日期</td>
                        <td><input type="text" id="enDate" name="enDate" class="emi-input date-after" placeholder="上岗日期"/></td>
                    </tr>
                    <tr>
                        <td class="emi-key culture-label"><span>*</span>文化程度:</td>
                        <td colspan="5">
                            <label class="emi-label"><input type="radio" name="enCulture"  class="culture" value="不识字"/>不识字</label>
                            <label class="emi-label"><input type="radio" name="enCulture"  class="culture" value="小学"/>小学</label>
                            <label class="emi-label"><input type="radio" name="enCulture" class="culture" vaule="初中"/>初中</label>
                            <label class="emi-label"><input type="radio" name="enCulture" class="culture" value="技工"/>技工</label>
                            <label class="emi-label"><input type="radio" name="enCulture" class="culture" value="高中"/>高中</label>
                            <label class="emi-label"><input type="radio" name="enCulture" class="culture" value="大学"/>大学</label>
                            <label class="emi-label"><input type="radio" name="enCulture" class="culture" value="硕士"/>硕士</label>
                            <label class="emi-label"><input type="radio" name="enCulture" class="culture" value="博士及以上"/>博士及以上</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="emi-key"><span>*</span>婚姻状况:</td>
                        <td colspan="5">
                            <label class="emi-label"><input type="radio" name="enMarriage" value="未婚"/>未婚</label>
                            <label class="emi-label"><input type="radio" name="enMarriage" value="已婚"/>已婚</label>
                            <label class="emi-label"><input type="radio" name="enMarriage" value="离婚"/>离婚</label>
                            <label class="emi-label"><input type="radio" name="enMarriage" value="丧偶"/>丧偶</label>
                            <label class="emi-label"><input type="radio" name="enMarriage" value="其他"/>其他</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="emi-key"><span>*</span>政治面貌:</td>
                        <td colspan="6">
                            <label class="emi-label"><input type="radio" name="enoPolitic" value="无党派民主人士"/>无党派民主人士</label>
                            <label class="emi-label"><input type="radio" name="enoPolitic" value="中共党员"/>中共党员</label>
                            <label class="emi-label"><input type="radio" name="enoPolitic" value="共青团员"/>共青团员</label>
                            <label class="emi-label"><input type="radio" name="enoPolitic" value="民革党员"/>民革党员</label>
                            <label class="emi-label"><input type="radio" name="enoPolitic" value="民盟盟员"/>民盟盟员</label>
                            <label class="emi-label"><input type="radio" name="enoPolitic" value="农民工党员"/>农民工党员</label>
                            <label class="emi-label"><input type="radio" name="enoPolitic" value="其他"/>其他</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="emi-key"><span>*</span>民族</td>
                        <td colspan="6">
                            <label class="emi-label"><input type="radio" name="enNation" value="汉族"/>汉族</label>
                            <label class="emi-label"><input type="radio" name="enNation" value="藏族"/>藏族</label>
                            <label class="emi-label"><input type="radio" name="enNation" value="彝族"/>彝族</label>
                            <label class="emi-label"><input type="radio" name="enNation" value="回族"/>回族</label>
                            <label class="emi-label"><input type="radio" name="enNation" value="维族"/>维族</label>
                            <label class="emi-label"><input type="radio" name="enNation" value="苗族"/>苗族</label>
                            <label class="emi-label"><input type="radio" name="enNation" value="其他"/>其他</label>
                        </td>
                    </tr>
                </table>


                <!-- 地址信息表格 -->
                <table class="table table-bordered margin-top-10 emi-table">
                    <tr>
                        <td class="emi-key"><span>*</span>户籍地址:</td>
                        <td colspan="6">
                            <select id="country" class="emi-select emi-address">
                                <option selected="selected" value="0">请选择</option>

                            </select>
                            <select id="province" name="provinceid"  class="emi-select emi-address"><option>请选择...</option></select>
                            <select id="city" name="city"  class="emi-select emi-address"><option>请选择...</option></select>
                            <select id="region" class="emi-select emi-address"><option>请选择...</option></select>
                            <input type="text" id="address1" class="emi-input emi-address-input" placeholder="详细信息">
                        </td>
                    </tr>
                    <tr>
                        <td class="emi-key"><span>*</span>现居住地址:</td>
                        <td colspan="6">
                            <select id="countryNow" class="emi-select emi-address">
                                <option selected="selected" value="0">请选择</option>

                            </select>
                            <select id="provinceNow" class="emi-select emi-address"><option>请选择...</option></select>
                            <select id="cityNow" class="emi-select emi-address"><option>请选择...</option></select>
                            <select id="regionNow" class="emi-select emi-address"><option>请选择...</option></select>
                            <input type="text" id="address2" class="emi-input emi-address-input" placeholder="详细信息">
                        </td>
                    </tr>
                </table>

                <!-- 详细信息表格 -->
                <table class="table table-bordered margin-top-10 emi-table">
                    <tr>
                        <td class="emi-key"><span>*</span>手机号码:</td>
                        <td><input type="text" id="enNumber" name="enNumber" class="emi-input" placeholder="手机号码"></td>
                        <td class="emi-key"><span>*</span>其他联系方式:</td>
                        <td colspan="3"><input type="text" id="enElsenumber" name="enElsenumber" class="emi-input" placeholder="其他联系方式"></td>
                    </tr>
                    <tr>
                        <td class="emi-key"><span>*</span>紧急联系人:</td>
                        <td><input type="text" id="enUrgentpeople" name="enUrgentpeople" class="emi-input" placeholder="紧急联系人"></td>
                        <td class="emi-key"><span>*</span>紧急电话:</td>
                        <td colspan="3"><input type="text" id="enUrgentnumber" name="enUrgentnumber" class="emi-input" placeholder="紧急电话"></td>
                    </tr>
                    <tr>
                        <td class="emi-key">毕业院校:</td>
                        <td><input type="text" id="enSchool" name="enSchool" class="emi-input" placeholder="毕业院校"></td>
                        <td class="emi-key">所学专业:</td>
                        <td colspan="3"><input id="enMajor" name="enMajor" type="text" class="emi-input" placeholder="所学专业"></td>
                    </tr>
                    <tr>
                        <td class="emi-key">毕业时间:</td>
                        <td><input type="text" id="enGraduation" name="enGraduation" class="emi-input date-before" placeholder="毕业时间"></td>
                        <td class="emi-key">学历:</td>
                        <td><input type="text" id="enEducation" name="enEducation" class="emi-input" placeholder="学历"></td>
                        <td class="emi-key">获得学位:</td>
                        <td><input type="text" id="enAcademic"  name="enAcademic" class="emi-input" placeholder="获得学位"></td>
                    </tr>
                    <tr>
                        <td class="emi-key"><span>*</span>是否缴纳社保:</td>
                        <td colspan="5">
                            <label class="emi-label"><input type="radio" name="enIfsocial" value="1" />是</label>
                            <label class="emi-label"><input type="radio" name="enIfsocial" value="0" />否</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="emi-key">备注:</td>
                        <td colspan="5"><input type="text" id="enElse" class="emi-input" placeholder="备注"></td>
                    </tr>
                </table>
            </form>
            <!-- 工作经历 -->
            <table class="table table-bordered margin-top-10 emi-table	">
                <tr>
                    <td class="emi-key"><button type="button" class="btn btn-success btn-xs btn-add-job">新增工作经历</button></td>
                    <td>
                        <table id="experience" class="table table-bordered emi-table">
                            <thead>
                            <tr>
                                <th>单位名称</th>
                                <th>入职时间</th>
                                <th>离职时间</th>
                                <th>岗位</th>
                                <th>离职原因</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="experienceData" class="tbody-job-list">
                            <tr class="tr-job">
                                <td><input type="text" name="exName" class="emi-input" placeholder="单位名称"></td>
                                <td><input type="text" name="exDate" class="emi-input date"  placeholder="入职名称"></td>
                                <td><input type="text" name="exOutdate" class="emi-input date"  placeholder="离职时间"></td>
                                <td><input type="text" name="exPost" class="emi-input" placeholder="岗位"></td>
                                <td><input type="text" name="exReason" class="emi-input" placeholder="离职原因"></td>
                                <td><button type="button" class="btn btn-default btn-xs">删除</button></td>
                            </tr>

                            </tbody>
                        </table>
                    </td>
                </tr>

            </table>


            <div class="emi-note">
                1.此表作为公司人力资源记录，将被用于人力资源管理相关事宜，请认真据实填写。<br/>
                2.此表由公司组织定期填写，期间如有重要变更请及时通知人力资源部。
            </div>
        </div>
    </div>
</form>

<div class="container footer-container">开发者-happygod-<a href>http://www.happygeniubibibibi.com</a></div>
</body>

</html>
<script type="text/javascript">

  $(document).ready(function () {

  coutryList();
});

  function coutryList() {

    $.ajax({

      type:"post",
      url:"/hzl/country",
      dataType:"json",
      success:function (result) {
        var countryList = result.pageInfo.countryList;
        var departmentName = result.pageInfo.departmentName;
        var str1="";
        var str2='<option selected="selected" value="0">请选择</option>';
        for(var i = 0;i<countryList.length;i++){
          str1+='<option  value="'+i+'">'+countryList[i]+'</option>'
        }
        $("#country").empty();
        $("#countryNow").empty();
        $("#country").append(str2,str1);
        $("#countryNow").append(str2,str1);

        var str3='';
        for(var i = 0;i<departmentName.length;i++){
          str3+='<option  value="'+departmentName[i].deId+'">'+departmentName[i].deName+'</option>'
        }
        $("#enDepartment").empty();
        $("#enDepartment").append(str2,str3);
      }
    });
  }

  function changepic() {
    $("#prompt3").css("display", "none");
    var reads = new FileReader();
    f = document.getElementById('file').files[0];
    reads.readAsDataURL(f);
    reads.onload = function(e) {
      document.getElementById('img3').src = this.result;
      $("#img3").css("display", "block");
    };
  }

  $(document).on("click","#img3",function() {
    $("#prompt3").css("display", "block");
    $("#img3").css("display", "none");
  });

  $(document).on("click",".btn.btn-success.btn-xs.btn-add-job",function () {

    var str1 = '<tr class="tr-job">\n' +
      '                            <td><input type="text" name="exName" class="emi-input" placeholder="单位名称"></td>\n' +
      '                            <td><input type="text" name="exDate" class="emi-input date"   placeholder="入职名称"></td>\n' +
      '                            <td><input type="text" name="exOutdate" class="emi-input date"   placeholder="离职时间"></td>\n' +
      '                            <td><input type="text" name="exPost" class="emi-input" placeholder="岗位"></td>\n' +
      '                            <td><input type="text" name="exReason" class="emi-input" placeholder="离职原因"></td>\n' +
      '                            <td><button type="button" class="btn btn-default btn-xs" onclick="removeJob(this)">删除</button></td>\n' +
      '                        </tr>';
    $("#experience tbody").append(str1)

  });

  function addEmplpyee() {

    var enName = $("#enName").val();
    var enSex =  $('input[name="enSex"]:checked').val();
    var enIdentity = $("#enIdentity").val();
    var enBirthday = $("#enBirthday").val();
    var enSocial = $("#enSocial").val();
    var enDepartment = $('#enDepartment option:selected').text();
    var enPost = $('#enPost option:selected').text();
    var poid = document.getElementById("enPost").value
    var enDate = $("#enDate").val();
    var enCulture = $('input[name="enCulture"]:checked').val();
    var enMarriage = $('input[name="enMarriage"]:checked').val();
    var enoPolitic =$('input[name="enoPolitic"]:checked').val();
    var enNation =$('input[name="enNation"]:checked').val();
    var enCity=$('#city option:selected').text();
    var enCountry=$('#country option:selected').text();
    var enRegion=$('#region option:selected').text();
    var enProvince=$('#province option:selected').text();
    var enAddress = $("#address1").val();
    var enCityNow=$('#cityNow option:selected').text();
    var enCountryNow=$('#countryNow option:selected').text();
    var enRegionNow=$('#regionNow option:selected').text();
    var enProvinceNow=$('#provinceNow option:selected').text();
    var enAddressNow = $("#address2").val();
    var enNumber= $("#enNumber").val();
    var enElsenumber= $("#enElsenumber").val();
    var enUrgentpeople= $("#enUrgentpeople").val();
    var enUrgentnumber= $("#enUrgentnumber").val();
    var enSchool= $("#enSchool").val();
    var enMajor= $("#enMajor").val();
    var enGraduation= $("#enGraduation").val();
    var enEducation= $("#enEducation").val();
    var enAcademic =  $("#enAcademic").val();
    var enIfsocial= $('input[name="enIfsocial"]:checked').val();
    var enElse= $("#enElse").val();
    var file_obj = document.getElementById('file').files[0];
    var fd = new FormData();
    var hllcExperienceList = [];
    $("#experienceData tr").each(function (i,obj) {
      hllcExperienceList.push(
        {
          exName:$("input[name=exName]",obj).val(),
          exDate:$("input[name=exDate]",obj).val(),
          exOutdate:$("input[name=exOutdate]",obj).val(),
          exPost:$("input[name=exPost]",obj).val(),
          exReason:$("input[name=exReason]",obj).val()
        }
      );
    });
    fd.append('file', file_obj);
    fd.append('enName', enName);
    fd.append('enSex', enSex);
    fd.append('enIdentity', enIdentity);
    fd.append('enBirthday', enBirthday);
    fd.append('enSocial', enSocial);
    fd.append('enDepartment', enDepartment);
    fd.append('enPost', enPost);
    fd.append('poid', poid);
    fd.append('enDate', enDate);
    fd.append('enCulture', enCulture);
    fd.append('enMarriage', enMarriage);
    fd.append('enoPolitic', enoPolitic);
    fd.append('enNation', enNation);
    fd.append('enCity', enCity);
    fd.append('enCountry', enCountry);
    fd.append('enProvince', enProvince);
    fd.append('enRegion', enRegion);
    fd.append('enAddress', enAddress);
    fd.append('enCityNow',enCityNow);
    fd.append('enCountryNow',enCountryNow);
    fd.append('enRegionNow',enRegionNow);
    fd.append('enProvinceNow',enProvinceNow);
    fd.append('enAddressNow', enAddressNow);
    fd.append('enNumber', enNumber);
    fd.append('enElsenumber', enElsenumber);
    fd.append('enUrgentpeople', enUrgentpeople);
    fd.append('enUrgentnumber', enUrgentnumber);
    fd.append('enSchool', enSchool);
    fd.append('enMajor', enMajor);
    fd.append('enGraduation', enGraduation);
    fd.append('enEducation', enEducation);
    fd.append('enAcademic', enAcademic);
    fd.append('enIfsocial', enIfsocial);
    fd.append('enElse', enElse);


    $.ajax({

      type: "POST",
      url: "/hzl/addEmployee",
      data: fd,
      cache: false,//文件不设置缓存
      processData: false,//数据不被转换为字符串
      contentType: false,//上传文件时使用，避免 JQuery 对其操作
      success: function (data) {
        window.opener = null;
        window.open('', '_self');
        window.close();
        window.location.reload(true);
      }


    });
  }


  function removeJob(obj) {
    var tr=obj.parentNode.parentNode;
    var tbody=tr.parentNode;
    tbody.removeChild(tr);
  }

</script>