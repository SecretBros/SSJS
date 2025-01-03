<script runat="server">
   Platform.Load("core","1");
   var api=new Script.Util.WSProxy();
 try{
    var customerKey="SignUpDataExtension";
    var fields=[{
        Name:"MobileNumber",
        FieldType: "Phone",
        IsRequired:false
        } ];

        var result=api.updateItem('DataExtension', {
            "CustomerKey": customerKey,
            "Fields": fields
        })
    Write(Stringify(result));
 }
 catch(ex){
    Write(Stringify(ex));
 }

</script>