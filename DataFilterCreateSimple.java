
<script runat="server">
  // Load the Core library with version 1.1.1
  Platform.Load("Core", "1");

  try {
    // Define the filter criteria for the filter definition
    var filterObj = {
   

      LeftOperand: {
      Property: "RenewalDate",
      SimpleOperator: "greaterThanAnniversary",
      Value: "5/15/2024"
      },
      LogicalOperator: "AND",
      RightOperand: {
      Property: "RenewalDate",
      SimpleOperator: "lessThanAnniversary",
      Value: "12/15/2024"
      }
    };

    // Define the new filter definition
    var newFD = {
      Name: "AmpScriptde_Filter_Complex",
      CustomerKey: "AmpScriptde_Filter_Complex",
      Filter: filterObj,
      DataSource: {
        Type: "DataExtension",
        CustomerKey: "BAB341B0-6BAC-446E-A1F1-C7571D95CC39"
      }
    };

    // Attempt to add the new filter definition
    var status = FilterDefinition.Add(newFD);

    // Output the status as a string (for debugging purposes)
    Write(Stringify(status));

  } catch (ex) {
    // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
  }
</script>
                         