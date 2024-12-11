
<script runat="server">
  // Load the Core library with version 1.1.1
  Platform.Load("Core", "1");

  try {
    // Retrieve the filter definition with CustomerKey equal to "Behavioral"
    var results = FilterDefinition.Retrieve({
      Property: "CustomerKey",
      SimpleOperator: "equals",
      Value: "FilterDate"
    });

    // Output details for each retrieved filter definition
    for (var i = 0; i < results.length; i++) {
      Write("Name: " + results[i].Name + "\n");
      Write("Description: " + results[i].Description + "\n");
      Write("ObjectID: " + results[i].ObjectID + "\n");
      Write("CategoryID: " + results[i].CategoryID + "\n");
      Write("CreatedDate: " + results[i].CreatedDate + "\n");
      Write("ModifiedDate: " + results[i].ModifiedDate + "\n");
      Write("DataFilter: " + results[i].DataFilter + "\n");
      Write("DataSource CustomerKey: " + results[i].DataSource.CustomerKey + "\n");
      Write("DataSource Name: " + results[i].DataSource.Name + "\n");
      Write("============================================================\n");
    }

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>
                         