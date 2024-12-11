
<script runat="server">
  // Load the Core library with version 1.1.1
  Platform.Load("Core", "1");

  try {
    // Retrieve the folder based on the specified name
    var results = Folder.Retrieve({
      Property: "Name",
      SimpleOperator: "equals",
      Value: "Shashi"
    });

    // Output the details of the retrieved folders
    for (var i = 0; i < results.length; i++) {
      Write("Name: " + results[i].Name + "\n");
      Write("ID: " + results[i].ID + "\n");
      Write("ContentType: " + results[i].ContentType + "\n");
      Write("Description: " + results[i].Description + "\n");
      Write("IsActive: " + results[i].IsActive + "\n");
      Write("IsEditable: " + results[i].IsEditable + "\n");
      Write("AllowChildren: " + results[i].AllowChildren + "\n");
      Write("CustomerKey: " + results[i].CustomerKey + "\n");

      // Check if the folder has a parent folder
      if (results[i].ParentFolder) {
        Write("ParentFolder Name: " + results[i].ParentFolder.Name + "\n");
        Write("ParentFolder ID: " + results[i].ParentFolder.ID + "\n");
        Write("ParentFolder CustomerKey: " + results[i].ParentFolder.CustomerKey + "\n");
      }

      Write("=================================================================== \n");
    }

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>
                         