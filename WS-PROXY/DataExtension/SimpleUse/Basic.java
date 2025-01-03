/**************************************************************************************************************
* @Name                         : BasicDescribeDataExtension
* @Author                       : Adarsh
* @Created on                   : 22-12-2024
* @Description                  : Code to describe the SOAP Web Service API Objects
* @Ticket ID                    :
* @Modification History         :
*
* Developer                 Date                   Description
* *************************************************************************************************************
* Adarsh                    22-12-2024         Code to describe the SOAP Web Service API Objects
***************************************************************************************************************/
<script runat="server">
Platform.Load("Core","1");
try{ 
    var SoapApiObject="DataExtension";
    var response=DescribeSoapObject(SoapApiObject);
    Write(Stringify(response));
}
catch(ex){
    Write(ex.message+'\n');
    Write(ex.description+'\n');
    Write(ex.jintException+'\n');

}
function DescribeSoapObject(SoapApiObject){
    var api= new Script.Util.WSProxy();
    var response=api.describe(SoapApiObject);
    return response;
}
</script>