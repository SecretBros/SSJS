<script runat="server" language='javascript'>
  // Load the Core library with version 1
  Platform.Load("Core", "1");
  try {
    // Retrieve the Data Extension details based on CustomerKey
    var results = DataExtension.Retrieve({
      Property: "CustomerKey",
      SimpleOperator: "equals",
      Value: "29DF6785-ED93-4867-8C77-305B415BD69"
    });

    // Output the retrieved Data Extension details
    Write("Name: " + results[0].Name + '\n');
    Write("Description: " + results[0].Description + '\n');
    Write("IsSendable: " + results[0].IsSendable + '\n');
    Write("IsTestable: " + results[0].IsTestable + '\n');
    Write("CategoryID: " + results[0].CategoryID + '\n');
    Write("IsPlatformObject: " + results[0].IsPlatformObject + '\n');
    Write("CustomerKey: " + results[0].CustomerKey + '\n');
    Write("CreatedDate: " + results[0].CreatedDate + '\n');
    Write("ModifiedDate: " + results[0].ModifiedDate + '\n');
    Write("ObjectID: " + results[0].ObjectID + '\n');
    Write("Status: " + results[0].Status + '\n');
    Write("Client Id: " + results[0].Client.ID + '\n');
  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>