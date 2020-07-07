$(function(){





  //当城市改变时省份也会发生变
  $("#country").change(function () {
    var countryName = $('#country option:selected').text();

    $.ajax({

      type:"post",
      url:"/hzl/province",
      data:"countryName="+countryName,
      dataType:"json",
      success:function (data) {

        $("#province").empty();
        if(data.length<=1){

          $("#city").empty();

          $("#region").empty();

        }else {


          var str1 = "<option>请选择...</option>";
          var str2 = "";
          for (var i = 0; i < data.length; i++) {

            str2 += "<option value=" + i + ">" + data[i] + "</option>";


          }
          $("#province").append(str1, str2);
          $("#city").empty();
          $("#city").append(str1);
          $("#region").empty();
          $("#region").append(str1);
        }
      }

    });



  });

  //省改变市也跟着改变
  $("#province").change(function () {

    var countryName = $('#country option:selected').text();
    var provinceName = $("#province option:selected").text();

    $.ajax({

      type:"post",
      url:"/hzl/city",
      data:{countryName:countryName,
        provinceName:provinceName,
      },
      dataType:"json",
      success:function (city) {

        var str3="<option>请选择...</option>";
        var str4="";
        $("#city").empty();

        if(city.length<=1){


          $("#region").empty();

        }else {
          for (var i = 0; i < city.length; i++) {

            str4 += "<option value=" + i + ">" + city[i] + "</option>";

          }

          $("#city").append(str3, str4);
          $("#region").empty();
          $("#region").append(str3);
        }
      }



    });



  });

  $("#city").change(function () {

    var countryName = $('#country option:selected').text();
    var provinceName = $("#province option:selected").text();
    var cityName = $("#city option:selected").text();

    $.ajax({

      type:"post",
      url:"/hzl/region",
      data:{countryName:countryName,
        provinceName:provinceName,
        cityName:cityName,
      },
      dataType:"json",
      success:function (region) {

        var str3="<option>请选择...</option>";
        var str4="";
        $("#region").empty();

        if(region.length>=1) {


          for (var i = 0; i < region.length; i++) {

            str4 += "<option value=" + i + ">" + region[i] + "</option>";

          }

          $("#region").append(str3, str4);
        }

      }



    });


  });

  //现在居住
  $("#countryNow").change(function () {

    var countryNowName = $('#countryNow option:selected').text();
    $.ajax({

      type:"post",
      url:"/hzl/provinceNow",
      data:"countryNowName="+countryNowName,
      dataType:"json",
      success:function (data) {

        $("#provinceNow").empty();
        if(data.length<=1){

          $("#cityNow").empty();

          $("#regionNow").empty();

        }else {


          var str1 = "<option>请选择...</option>";
          var str2 = "";
          for (var i = 0; i < data.length; i++) {

            str2 += "<option value=" + i + ">" + data[i] + "</option>";


          }
          $("#provinceNow").append(str1, str2);
          $("#cityNow").empty();
          $("#cityNow").append(str1);
          $("#regionNow").empty();
          $("#regionNow").append(str1);
        }
      }

    });



  });

  //省改变市也跟着改变
  $("#provinceNow").change(function () {

    var countryNowName = $('#countryNow option:selected').text();
    var provinceNowName = $("#provinceNow option:selected").text();

    $.ajax({

      type:"post",
      url:"/hzl/cityNow",
      data:{countryNowName:countryNowName,
        provinceNowName:provinceNowName,
      },
      dataType:"json",
      success:function (city) {

        var str3="<option>请选择...</option>";
        var str4="";
        $("#cityNow").empty();

        if(city.length<=1){


          $("#regionNow").empty();

        }else {
          for (var i = 0; i < city.length; i++) {

            str4 += "<option value=" + i + ">" + city[i] + "</option>";

          }

          $("#cityNow").append(str3, str4);
          $("#regionNow").empty();
          $("#regionNow").append(str3);

        }
      }



    });



  });

  //市
  $("#cityNow").change(function () {

    var countryNowName = $('#countryNow option:selected').text();
    var provinceNowName = $("#provinceNow option:selected").text();
    var cityNowName = $("#cityNow option:selected").text();

    $.ajax({

      type:"post",
      url:"/hzl/regionNow",
      data:{countryNowName:countryNowName,
        provinceNowName:provinceNowName,
        cityNowName:cityNowName,
      },
      dataType:"json",
      success:function (region) {

        var str3="<option>请选择...</option>";
        var str4="";
        $("#regionNow").empty();

        if(region.length>=1) {
          for (var i = 0; i < region.length; i++) {

            str4 += "<option value=" + i + ">" + region[i] + "</option>";

          }

          $("#regionNow").append(str3, str4);

        }
      }



    });


  });


  //部门改变时职位发生变化
  $("#enDepartment").change(function () {

    var deId  = $("#enDepartment option:selected").val();

    $.ajax({

      type:"post",
      url:"/hzl/positionBydepartment",
      data:"deId="+deId,
      dataType:"json",
      success:function (po) {
        $("#enPost").empty();
        var str2 = "<option>请选择...</option>";
        var str3 = "";
        if(po.length!=0){
          for(var i = 0;i<po.length;i++){
            str3 +="<option value="+po[i].poid+">"+po[i].poname+"</option>";

          };

          $("#enPost").append(str2,str3);
        };

      }


    });


  });




});