//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

var XMLHttp=
{
    XMLHttpRequestPool:[],
    getInstance:function(){
       for(var i=0;i<this.XMLHttpRequestPool.length;i++){
           if(this.XMLHttpRequestPool[i].readyState==0||
               this.XMLHttpRequestPool[i].readyState==4){
               return this.XMLHttpRequestPool[i];
           }
       }
        this.XMLHttpRequestPool[this.XMLHttpRequestPool.length]=this.createXMLHttpRequest();
        return this.XMLHttpRequestPool[this.XMLHttpRequestPool.length-1];
    },
    createXMLHttpRequest:function(){
        if(window.XMLHttpRequest){
            var objXMLHttp=new XMLHttpRequest();
        }else
        {
            var MSXML = ['MSXML2.XMLHTTP.5.0', 'MSXML2.XMLHTTP.4.0',
                'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
            for(var n=0;n<MSXML.length;n++){
                try{
                    var objXMLHttp=new ActiveXObject(MSXML[n]);
                    break;
                }catch(e){

                }
            }
        }
        if(objXMLHttp.readState==null){
            objXMLHttp.readyState=0;
            objXMLHttp.addEventListener("load",function(){
              objXMLHttp.readyState=4;
                if(typeof objXMLHttp.onreadystatechange=="function")
                {
                    objXMLHttp.onreadystatechange();
                }

            },false);
        }
        return objXMLHttp;
    },
    sendRequest:function(method,url,data,callback)
    {
        var objXMLHttp=this.getInstance();
        with(objXMLHttp)
        {
            try{
                if(url.indexOf("?")>0)
                {
                  //  url+="&randnum="+Math.random();
                }else
                {
                 //   url+="?randnum="+Math.random();
                }
                open(method,url,true);
                if(method=="POST"){

                    setRequestHeader("Content-Type",'application/x-www-form-urlencoded');
                    send(data);
                }
                if(method=="GET"){
                    send(null);
                }
               onreadystatechange=function()
                {
                    if(objXMLHttp.readyState==4&&(objXMLHttp.status==200 || objXMLHttp.status==304)){
                        callback(objXMLHttp);
                    }
                }
            }catch(e){
                alert(e);
            }
        }

    }
};