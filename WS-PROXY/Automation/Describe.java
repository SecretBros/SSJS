/**************************************************************************************************************
* @Name                         : Describe
* @Author                       : Adarsh
* @Created on                   : 12-19-2024
* @Description                  : Code to describe the SOAP Web Service API Objects
* @Ticket ID                    :
* @Modification History         :
*
* Developer                 Date                   Description
* *************************************************************************************************************
* Adarsh                    12-19-2024         Code to describe the SOAP Web Service API Objects
***************************************************************************************************************/
<script runat="server" langauge="Javasript">

Platform.Load("core","1");

try{
var response=   ("Automation");
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