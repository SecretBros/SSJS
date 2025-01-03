var updateDE = DataExtension.Init('sendableDataExtension');
var status = updateDE.Fields.UpdateSendableField("DifferentSubKey", "Subscriber Key");


/******************************************************************************************************************************************
* @Name                         : Fields.UpdateSendableField
* @Author                       : Cynoteck
* @Created on                   : 02-01-2025
* @Description                  : Updates a data extension field used as part of a send from a current value to a new value
* @Ticket ID                    :
* @Modification History         :
*
* Developer                 Date                   Description
* *****************************************************************************************************************************************
* Cynoteck              02-01-2025                Updates a data extension field used as part of a send from a current value to a new value
*******************************************************************************************************************************************/

<script runat="server">
  // Load the Core library with version 1.1.1
  Platform.Load("Core", "1");

  try {
    // Initialize the existing Sendable Data Extension
    var updateDE = DataExtension.Init('SignUpDataExtension');

    var status = updateDE.Fields.UpdateSendableField("FirstName", "Name");

    // Output the status of adding the new field
    Write((status));
  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>

                         