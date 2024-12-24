/**************************************************************************************************************
* @Name                         : FilterAutomationReady
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
    var filter= ApplyFilter("Status","equals","2");

   //Call the function  from the SOAP object   where the status equal 2
     var response= RetrieveSoapObject(soapObjectname,cols,filter);


    //Convert the response object to a string representation and write it to an  output.
    Write("There are "+response.Results.length+' in the Ready Status');
    Write('\n'+'Below are the name of the Automation');
    
    for(var i in response.Results){
        Write(response.Results[i].Name+'\n');
    }

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

</script>