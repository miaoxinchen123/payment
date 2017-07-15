/**
*插件介绍:图片上传本地预览插件
*@author:jianfei.xu
*使用方法:
*界面构造(IMG标签外必须拥有DIV 而且必须给予DIV控件ID)
* <div id="imgdiv"><img id="imgShow" width="120" height="120" /></div>
* <input type="file" id="up_img" />
*调用代码:
* new uploadImgPreview({ uploadFileId: "up_img", divPreviewId: "imgdiv", imgPreviewId: "imgShow" });
*参数说明:
*uploadFileId:选择文件控件ID;
*divPreviewId:DIV控件ID;
*imgPreviewId:图片控件ID;
*imgWidth:预览宽度;
*imgHeight:预览高度;
*imgType:支持文件类型 格式:["jpg","png"];
*imgSize:图片大小,单位kb;
*errTypeMsg:图片格式错误提示;
*errSizeMsg:图片大小错误提示;
*callback:选择文件后回调方法;
*/

function uploadImgPreview(setting) {
    /**
    * this(当前对象)
    */
    var _self = this;
    
    /**
    * 判断为null或者空值
    */
    _self.isNull = function(value) {
        if (typeof (value) == "function") { return false; }
        if (value == undefined || value == null || value == "" || value.length == 0) {
            return true;
        }
        return false;
    }
    
    /**
    * 默认配置
    */
    _self.defautlSetting = {
        uploadFileId: "",
        divPreviewId: "",
        imgPreviewId: "",
        imgWidth: 100,
        imgHeight: 100,
        imgSize: 300,
        imgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        errTypeMsg: "图片格式错误,图片类型必须是(gif,jpeg,jpg,bmp,png)中的一种",
        errSizeMsg: "图片过大,图片大小不超过300Kb",
        callback: function() { }
    };
    
    /**
    * 读取配置
    */
    _self.Setting = {
        uploadFileId: _self.isNull(setting.uploadFileId) ? _self.defautlSetting.uploadFileId : setting.uploadFileId,
        divPreviewId: _self.isNull(setting.divPreviewId) ? _self.defautlSetting.divPreviewId : setting.divPreviewId,
        imgPreviewId: _self.isNull(setting.imgPreviewId) ? _self.defautlSetting.imgPreviewId : setting.imgPreviewId,
        imgWidth: _self.isNull(setting.imgWidth) ? imgWidth.defautlSetting.imgWidth : setting.imgWidth,
        imgHeight: _self.isNull(setting.imgHeight) ? _self.defautlSetting.imgHeight : setting.imgHeight,
        imgType: _self.isNull(setting.imgType) ? _self.defautlSetting.imgType : setting.imgType,
        imgSize: _self.isNull(setting.imgSize) ? _self.defautlSetting.imgSize : setting.imgSize,
        errTypeMsg: _self.isNull(setting.errTypeMsg) ? _self.defautlSetting.errTypeMsg : setting.errTypeMsg,
        errSizeMsg: _self.isNull(setting.errSizeMsg) ? _self.defautlSetting.errSizeMsg : setting.errSizeMsg,
        callback: _self.isNull(setting.callback) ? _self.defautlSetting.callback : setting.callback
    };
    
    /**
    * 获取Img控件URL
    */
    _self.getImgURL = function(imgSrc) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(imgSrc);
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(imgSrc);
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(imgSrc);
        }
        
        return url;
    }
    
    /**
     * 获取Img大小
     */
    _self.getImgSize = function(imgInput) {
    	var imgSize = -1;
    	if(navigator.userAgent.indexOf("MSIE") > -1) {//IE
      	  var image = new Image();        
          image.dynsrc = imgInput.value;        
          imgSize = image.fileSize;
    	} else if(imgInput.files[0] && imgInput.files[0].size) {//firefox、chrome
    		imgSize = imgInput.files[0].size;
    	}
    	
    	return imgSize;
    }
    
    /**
    * Img控件绑定事件
    */
    document.getElementById(_self.Setting.uploadFileId).onchange = function() {
        if (this.value) {
            if (!RegExp("\.(" + _self.Setting.imgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {//图片格式
                $.messager.alert("提示", _self.Setting.errTypeMsg, "info");
                this.value = "";
                return false;
            }
            
            var imgSize = _self.getImgSize(this);
            if(imgSize > 0 && imgSize > _self.Setting.imgSize * 1024) {//图片大小
            	$.messager.alert("提示", _self.Setting.errSizeMsg, "info");
            	this.value = "";
                return false;
            }
            
            var imgFileUrl = _self.getImgURL(this.files[0]);
            if (navigator.userAgent.indexOf("MSIE") > -1) {//IE
                try {
                    document.getElementById(_self.Setting.imgPreviewId).src = imgFileUrl;
                } catch (e) {//使用滤镜显示
                    var div = document.getElementById(_self.Setting.divPreviewId);
                    this.select();
                    top.parent.document.body.focus();
                    var src = document.selection.createRange().text;
                    document.selection.empty();
                    document.getElementById(_self.Setting.imgPreviewId).style.display = "none";
                    div.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    div.style.width = _self.Setting.imgWidth + "px";
                    div.style.height = _self.Setting.imgHeight + "px";
                    div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
               }
            } else {
                document.getElementById(_self.Setting.imgPreviewId).src = imgFileUrl;
            }
            
            _self.Setting.callback();
        }
    }
    
}