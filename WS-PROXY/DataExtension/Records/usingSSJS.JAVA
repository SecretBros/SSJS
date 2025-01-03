<script runat="server">

    Platform.Load("core", "1");

    try {
        // Initialize the Data Extension
        var de = DataExtension.Init("SignUpDataExtension");

        // Example array of 100 phone numbers
        var phoneNumbers = [];
        for (var i = 1; i <= 100; i++) {
            phoneNumbers.push({ PhoneNumber: "+934563213" + i });
        }

        // Loop through the phone numbers and add to the Data Extension
        var successCount = 0;
        for (var i = 0; i < phoneNumbers.length; i++) {
            var payload = {
                PhoneNumber: phoneNumbers[i].PhoneNumber
            };

            // Add each row to the Data Extension
            var result = de.Rows.Add(payload);

            // Check result and count successful inserts
            if (result) {
                successCount++;
            }
        }

        // Output the result
        Write("Successfully added " + successCount + " phone numbers to the Data Extension.");
    } catch (error) {
        Write(Stringify(error));
    }

</script>
