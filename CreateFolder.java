
<script runat="server">
  // Load the Core library with version 1.1.1
  Platform.Load("Core", "1");

  try {
    // Define the properties of the new folder
    var newFolder = {
      "Name" : "Newsletter",
      "CustomerKey" : "Newsletter",
      "Description" : "Test added",
      "ContentType" : "dataextension",//[asset,journey,automations] //adjust as needed
      "IsActive" : "true",
      "IsEditable" : "true",
      "AllowChildren" : "true",
      "ParentFolderID" : 30030 // Parent folder ID; adjust as needed 25233
    };

    // Add the new folder
    var status = DataFolder.Add(newFolder);

    // Output the status of the folder addition
    Write(status);

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>
                         