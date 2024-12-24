/**************************************************************************************************************
* @Name                         : CheckRetrivalProperties
* @Author                       : Adarsh
* @Created on                   : 20-12-2024
* @Description                  : In this section, we will review the Data Extension retrieval properties to 
                                  determine who has access to retrieve the data.
* @Ticket ID                    :
* @Modification History         :
*
* Developer                 Date                   Description
* *************************************************************************************************************
* Adarsh                    20-12-2024       In this section, we will review the Data Extension retrieval
                                              properties to determine who has access to retrieve the data.
***************************************************************************************************************/

<script runat="server" language="Javascript">
Platform.Load("Core","1");

try{
    var SoapApiObject="DataExtension";
    var response=DescribeSoapObject(SoapApiObject);
    Write(Stringify(response));
    var metaSoapApiObjectResult=response.Results[0].Properties;
    var rows=CheckRetrivalProperties(metaSoapApiObjectResult);
     
     for(var i in rows){
        Write(i+'==>'+rows[i]);

     }
      

}
catch(ex){
    Write(ex.message);
    Write(ex.description);
    Write(ex.jintException);
}



function DescribeSoapObject(){
    var api= new Script.WSProxy();
    var response=api.describe();
    return response;
}

function CheckRetrivalProperties(metaSoapApiObjectResult){
    var propertiesName=[];

    for(var i in metaSoapApiObjectResult ){
        var name = metaSoapApiObjectResult[i].Name;
        var isretrival = metaSoapApiObjectResult[i].IsRetrievable;
        if(isretrival===true){
          propertiesName.push(name);
        }
    }
   return propertiesName;   
}
</script>