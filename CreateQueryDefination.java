
<script runat="server">
  // Load the Core library with version 1.1.1
  Platform.Load("Core", "1");

  try {
    // Define the Query Definition properties
    var queryDef = {
      Name: "FetchQueryFrom_Dataview_Sent", 
      CustomerKey: "FetchQueryFrom_Dataview_Sent", 
      TargetUpdateType: "Overwrite",
      TargetType: "DE",
      Target: {
        Name: "DataExtensionForQueryActivity",
        CustomerKey: "DataExtensionForQueryActivity"
      },
      QueryText: "SELECT * FROM [Dataview_Sent]"
    };

    // Add the Query Definition
    var status = QueryDefinition.Add(queryDef);

    // Output the status
    Write("Status: " + status + '\n');

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>

                         