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


<script runat="server" langauge="Javascript">
Platform.Load("Core","1");

try{
    //name of the Soap Object that you want to describe.
    var SoapObject="Automation";

    /* Call the function describesoapobject to retrieve the meta information for the SOAP object.*/
    var GetResponse=DescribeSoapObject(SoapObject);
  
    //Extract the properties Array from the response.
    var property=GetResponse.Results[0].Properties;
      
      //Call  the function 'RetrivableProperties' to  get an array of retrivable propertyname
    var cols=RetrivableProperties(property);


    //Below are the retrive properties of the soap object
    for(var i in cols){
        Write(i+' '+cols[i]+'\n');
    }

}
catch(ex){
    Write(ex.message);
    Write(ex.description);
    Write(ex.jintException);
}

function DescribeSoapObject(SoapObject){
    var api= new Script.Util.WSProxy();
    var response=api.describe(SoapObject);
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

</script>
