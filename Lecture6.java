
<script runat="server">
  // Load the Core library with version 1
  Platform.Load("Core", "1");

  

   try {
    var ContainJsonResult=CreateDE();
    var CreateQueryPayload=QueryDE();
    var ContainQueryResult=RunQuery();
    Write('check whether the Query run sucessfully'+ContainQueryResult);
    Write( '\n');

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }




  function RunQuery(){
  try {
    // Initialize a Query Definition with the external key "bouncedataviewQueryDef"
    var qd = QueryDefinition.Init("FetchQueryFrom_Dataview_Sent_v1");
    var status = qd.Perform();
   // var status = qd.Remove();

    // Output the status of the update operation
    //Write("Update Status: " + status + '\n');

    return status;

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
                         
  }


function QueryDE(){
  try {
    // Define the Query Definition properties
    var queryDef = {
      Name: "FetchQueryFrom_Dataview_Sent_v1", 
      CustomerKey: "FetchQueryFrom_Dataview_Sent_v1", 
      TargetUpdateType: "Overwrite",
      TargetType: "DE",
      Target: {
        Name: "DataExtensionForQueryActivity_v1",
        CustomerKey: "DataExtensionForQueryActivity_v1"
      },
      QueryText: "SELECT * FROM [Dataview_Sent]" /** This is the Data Extension where we have take the records in our DE through query */
    };

    // Add the Query Definition
    var status = QueryDefinition.Add(queryDef);

    // Output the status
   // Write("Status: " + status + '\n');

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
                         
}

  function CreateDE()
  {
    try {
    // Define the properties of the Data Extension to be created
    var deObj = {
      "CustomerKey": "DataExtensionForQueryActivity_v1",
      "Name": "DataExtensionForQueryActivity_v1",
      "Fields": [
                { "CustomerKey": "AccountID", "Name": "AccountID", "FieldType": "Number", "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "OYBAccountID", "Name": "OYBAccountID", "FieldType": "Number", "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "JobID", "Name": "JobID", "FieldType": "Number", "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "ListID", "Name": "ListID", "FieldType": "Number", "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "BatchID", "Name": "BatchID", "FieldType": "Number", "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "SubscriberID", "Name": "SubscriberID", "FieldType": "Number", "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "SubscriberKey", "Name": "SubscriberKey", "FieldType": "Text", "MaxLength": 254, "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "EventDate", "Name": "EventDate", "FieldType": "Date", "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "Domain", "Name": "Domain", "FieldType": "Text", "MaxLength": 128, "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "TriggererSendDefinitionObjectID", "Name": "TriggererSendDefinitionObjectID", "FieldType": "Text", "MaxLength": 36, "IsPrimaryKey": false, "IsRequired": false },
                { "CustomerKey": "TriggeredSendCustomerKey", "Name": "TriggeredSendCustomerKey", "FieldType": "Text", "MaxLength": 36, "IsPrimaryKey": false, "IsRequired": false }
            ]
    };

    // Create the Data Extension
    var myDE = DataExtension.Add(deObj);
    // Output the created Data Extension details
    Write(Stringify(myDE));

    return myDE;} catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }

  }

  
</script>
                            