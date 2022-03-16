$(function(){
    $("#begin, #end").datepicker({dateFormat: 'yy-mm-dd'});
    $("#today").click(function(){
        if($(this).prop("checked")){
            $("#begin, #end").prop("disabled",true);
            $("#begin, #end").val(makeDateSting(new Date));
        }else{
            $("#begin, #end").prop("disabled",false);
        }
    })
    function makeDateSting(dt){
        return dt.getFullYear() + "-" +leadingZero(dt.getMonth()+1) + "-" + leadingZero(dt.getDate());
    }
    function leadingZero(n){
        return n<10 ? "0"+n : ""+n;
    }

    $("#crawling").click(function(){
        $.ajax({
            url:"/api/news/crawling",
            type:"put",
            success:function(r){
                alert(r)
            }
        })
    })

    $("#makeLog").click(function(){
        let start = $("#begin").val();
        let end = $("#end").val();
        $.ajax({
            url:"/link/click?start="+start+"&end="+end,
            type:"get",
            success:function(msg){
                alert(msg);
            }
        })
    })
})