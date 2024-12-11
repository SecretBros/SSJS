<script runat="server">
// Load the Core library with version 1.1.1
   Platform.Load("Core", "1");
  try{
    // Declare the Name of The Data Extension
    var dname="Dataview_Sent";

    //Calling the function to retrive the Data Extension
    var retriveResult=RetriveDataExtension(dname);
       
    //Validating the Data Extension if the Data Extension exists or Not
    if(!retriveResult){
      var result=CreateDataExtension(dname);
      
    /**Retriving the DataExtension*/
     var retriveResult=RetriveDataExtension(dname);
     }

     // print the Output 
     Write(Stringify(retriveResult));
 
  }
  catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }

                             /*****Data Defination */
  function RetriveDataExtension(dname){
    //Retrive the Data Extension details based on CustomerKey

         var result = DataExtension.Retrieve({
            Property: "Name",
            SimpleOperator: "equals",
            Value: dname /**Name of the DE */
        });
        Write(Stringify(result));     
    
  }

  function CreateDataExtension(){
  //Define the properties of the DataExtension to be Created
    var deObj={
    "CustomerKey":dname,/**External Key of the DE */
      "Name":dname,/**Name of the DE */
      "Fields": [
        { "Name": "AccountID", "FieldType": "Number" },
        { "Name": "OYBAccountID", "FieldType": "Number" },
        { "Name": "JobID", "FieldType": "Number" },
        { "Name": "ListID", "FieldType": "Number" },
        { "Name": "BatchID", "FieldType": "Number" },
        { "Name": "SubscriberID", "FieldType": "Number" },
        { "Name": "SubscriberKey", "FieldType": "Text", "MaxLength": 254 },
        { "Name": "EventDate", "FieldType": "Date" },
        { "Name": "Domain", "FieldType": "Text", "MaxLength": 128 },
        { "Name": "TriggererSendDefinitionObjectID", "FieldType": "Text", "MaxLength": 36 },
        { "Name": "TriggeredSendCustomerKey", "FieldType": "Text", "MaxLength": 36 }
      ]
    };
    // Create the Data Extension
    var myDE = DataExtension.Add(deObj);
    // Output the created Data Extension details
    Write(Stringify(myDE)) 
  } 
</script>
