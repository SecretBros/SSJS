<script runat="server" language="Javascript">
Platform.Load("core","1");

try{
    /*set the name of the Soap Object that you want to retrive*/
    var soapObject="Automation";

  /* Call the function describesoapobject to retrieve the meta information for the SOAP object.*/
   var response=DescribeSoapObject(soapObject);

   /**Extract the Properties Array from the response */
   var property=response.Results[0].Properties;

   Write('Below are the properties of the Soap Objects'+'\n')
    for(var i in property){
            Write(i+'   '+property[i].Name+" \n ");
    }

Write(Stringify(response)); 
}
catch(ex){
    Write(ex.message);
    Write(ex.description);
    Write(ex.jintException);

}

//Create the function to Describe the Soap Objects
function DescribeSoapObject(soapObject){
    var api= new Script.Util.WSProxy();
    var response=api.describe(soapObject);
    return response;
}

</script>