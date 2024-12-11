<script runat="server">
  // Load the Core library with version 1.1.1
  Platform.Load("Core", "1.1.1");

  try {
    // Retrieve an Account User based on the CustomerKey
    // Should be parent BU and have required permissions as Administrator
    var accountUser = AccountUser.Retrieve();
    // Uncomment the line below to see the complete AccountUser object as a string
    // Write(Stringify(accountUser));

    // Output information about the retrieved Account User
    Write('Client ID: ' + accountUser[0].Client.ID + '\n');
    Write('UserID: ' + accountUser[0].UserID + '\n');
    Write('Email: ' + Stringify(accountUser[0].Email) + '\n') 
    Write('Name: ' + Stringify(accountUser[0].Name) + '\n') 
    Write('AccountUserID:' + accountUser[0].AccountUserID + '\n') 
    Write('CustomerKey:' + accountUser[0].CustomerKey + '\n') 
    Write('userID:' + accountUser[0].UserID + '\n') 
  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>