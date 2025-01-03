/** 

<script runat="server">
Platform.Load("core","1");
var api= new Script.Util.WSProxy();
try{
    var customerKey:"SignUpDataExtension";
    var props = [
            {
                "Name": "MobileNumber",
                "Value": 237237193191
            }
        ];
 var result = api.createItem("DataExtension", { 
            CustomerKey: customerKey,
            Properties:props
        });

   Write(Stringify(results));     
}
catch(ex){
    Write(Stringify(ex));
}
</script>
*/


<script runat="server">
    Platform.Load("core", "1");

    try {
        // Initialize the Data Extension
        var de = DataExtension.Init("SignUpDataExtension");

               // Array of 93 mobile numbers
        var mobileNumbers = [
    "+1000000001", "+1000000002", "+1000000003", "+1000000004", "+1000000005",
    "+1000000006", "+1000000007", "+1000000008", "+1000000009", "+1000000010",
    "+1000000011", "+1000000012", "+1000000013", "+1000000014", "+1000000015",
    "+1000000016", "+1000000017", "+1000000018", "+1000000019", "+1000000020",
    "+1000000021", "+1000000022", "+1000000023", "+1000000024", "+1000000025",
    "+1000000026", "+1000000027", "+1000000028", "+1000000029", "+1000000030",
    "+1000000031", "+1000000032", "+1000000033", "+1000000034", "+1000000035",
    "+1000000036", "+1000000037", "+1000000038", "+1000000039", "+1000000040",
    "+1000000041", "+1000000042", "+1000000043", "+1000000044", "+1000000045",
    "+1000000046", "+1000000047", "+1000000048", "+1000000049", "+1000000050",
    "+1000000051", "+1000000052", "+1000000053", "+1000000054", "+1000000055",
    "+1000000056", "+1000000057", "+1000000058", "+1000000059", "+1000000060",
    "+1000000061", "+1000000062", "+1000000063", "+1000000064", "+1000000065",
    "+1000000066", "+1000000067", "+1000000068", "+1000000069", "+1000000070",
    "+1000000071", "+1000000072", "+1000000073", "+1000000074", "+1000000075",
    "+1000000076", "+1000000077", "+1000000078", "+1000000079", "+1000000080",
    "+1000000081", "+1000000082", "+1000000083", "+1000000084", "+1000000085",
    "+1000000086", "+1000000087", "+1000000088", "+1000000089", "+1000000090",
    "+1000000091", "+1000000092", "+1000000093"
];
    // To count the number of records that were Successfully Inserted.
        var successCount = 0;

        // Loop through mobile numbers and add them to the Data Extension
        for (var i = 0; i < mobileNumbers.length; i++) {
            var payload = {
                MobileNumber: mobileNumbers[i]
               
            };

           var result = dataExt.Rows.Update(payload,["Email"],[9868600, "CA"]);

    Write(Stringify(result));

            // Track successful additions
            if (result) {
                successCount++;
            } else {
                Write("Failed to add mobile number: " + mobileNumbers[i]);
            }
        }

        // Output the result
        Write("Successfully added " + successCount + " mobile numbers to the Data Extension.");
    } catch (error) {
        Write("Error: " + Stringify(error));
    }
</script>


// <script runat="server">

//     Platform.Load("core", "1");

//     try {
//         // Initialize the Data Extension
//         var de = DataExtension.Init("SignUpDataExtension");

 


//         var successCount = 0;

//         // Loop through mobile numbers and add them to the Data Extension
//         for (var i = 0; i < mobileNumbers.length; i++) {
//             var payload = {
//                 MobileNumber: mobileNumbers[i],
//                 // Add other fields like FirstName, LastName, Email if needed
//             };

//             var result = de.Rows.Add(payload);

//             // Track successful additions
//             if (result) {
//                 successCount++;
//             }
//         }

//         // Output the result
//         Write("Successfully added " + successCount + " mobile numbers to the Data Extension.");
//     } catch (error) {
//         Write("Error: " + Stringify(error));
//     }

// </script>
