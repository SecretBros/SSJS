
<script runat="server">
  // Load the Core library with version 1
  Platform.Load("Core", "1");

  try {
    // Define the properties of the Data Extension to be created
    var deObj = {
      "CustomerKey": "DataExtensionForQueryActivity",
      "Name": "DataExtensionForQueryActivity",
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
  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>
                            