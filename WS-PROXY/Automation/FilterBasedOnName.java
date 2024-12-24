/**************************************************************************************************************
* @Name                         : FilterAutomationBasedOnName
* @Author                       : Adarsh
* @Created on                   : 20-12-2024
* @Description                  : Code to describe the SOAP Web Service API Objects
* @Ticket ID                    :
* @Modification History         :
*
* Developer                 Date                   Description
* *************************************************************************************************************
* Adarsh                    20-12-2024         Code to describe the SOAP Web Service API Objects
***************************************************************************************************************/


<script runat="server" langauge="Javascript">
Platform.Load("Core","1");

try{
    //name of the Soap Object that you want to describe.
    var soapObjectname="Automation";

    /* Call the function describesoapobject to retrieve the meta information for the SOAP object.*/
    var GetResponse=DescribeSoapObject(soapObjectname);
  
    //Extract the properties Array from the response.
    var property=GetResponse.Results[0].Properties;
      
      //Call  the function 'RetrivableProperties' to  get an array of retrivable propertyname
    var cols=RetrivableProperties(property);

    //Filter Automation on Ready State
    var filter= ApplyFilter("Name","equals","deleteAllContacts");

   //Call the function  from the SOAP object   where the status equal 2
     var response= RetrieveSoapObject(soapObjectname,cols,filter);
     


    var result=performItemSoapObject(soapObjectname,response);
    Write(Stringify(result));

}
catch(ex){
     Write(ex.message);
    Write(ex.description);
    Write(ex.jintException);
}

function DescribeSoapObject(soapObjectname){
    var api= new Script.Util.WSProxy();
    var response=api.describe(soapObjectname);
    return response;   
}

function RetrivableProperties(SoapMetaData){
   
    //create an array to store  the name of retrivable properties.
    var properties=[];

    //Iterate through each object in the SoapMetaData Array
    for(var i in SoapMetaData){
        //Extract the "Name" property from th current object and store it in the propertiesName Array.
        var name  = SoapMetaData[i].Name;
       var isRetrievable=SoapMetaData[i].IsRetrievable;
        if(isRetrievable === true){
          properties.push(name);
        }

    }
    // Return the array the Containing the names of retrivable properties.
    return properties;
}


 function ApplyFilter(prop,operator,value)
  {
    return {
        Property: prop,
        SimpleOperator: operator,
        Value: value
    };
  }

   function RetrieveSoapObject(soapObjectname,cols,filter)
  {
    var api = new Script.Util.WSProxy();
    var response = api.retrieve(soapObjectname,cols,filter);
    return response;
  }


//Function to perform operation on a SOAP object using WS Proxy
/**
 * Parameters:-
 * soapObjectName:-The name of the SOAP objet from which the data will be retrived
 * cols:-An array of column name to retrive from SOAP Object.
 * filter: A filter to apply when querying the SOAP object (optional)
 */

function performItemSoapObject(soapObjectname,payload)
{
  Write('payload'+payload);
  Write('soapObjectname'+soapObjectname);
  // Create a new instance of WSProxy to interact with the SOAP object.
  var api = new Script.Util.WSProxy();
  api.setClientId({
    "ID": Platform.Function.AuthenticatedMemberID(),
    "UserID": Platform.Function.AuthenticatedEmployeeID()
});
  var customerKey = payload.Results[0].CustomerKey;
  var props = {"CustomerKey":customerKey};
  var action = ["start","stop"];
  var opts = {};
  var result = api.performItem(soapObjectname,props, action[1], opts);
  Write('payload'+payload);
  Write('soapObjectname'+soapObjectname);
  Write('result'+result);
  return result;
}

</script>