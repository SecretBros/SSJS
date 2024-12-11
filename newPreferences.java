<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preference Center</title>
    <style>
        /* Professional CSS for styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
            line-height: 1.6;
        }
        .container {
            max-width: 500px;
            margin: 20px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input[type="email"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }
        .email-preferences label {
            display: block;
            margin-bottom: 10px;
            font-weight: normal;
            position: relative;
            padding-left: 30px;
            cursor: pointer;
            line-height: 1.6;
        }
        .email-preferences input[type="checkbox"] {
            position: absolute;
            opacity: 0;
            cursor: pointer;
        }
        .checkmark {
            position: absolute;
            top: 0;
            left: 0;
            height: 20px;
            width: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .email-preferences input[type="checkbox"]:checked + .checkmark:after {
            content: '';
            position: absolute;
            display: block;
            left: 5px;
            top: 5px;
            width: 8px;
            height: 8px;
            border: solid #007bff;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 15px 20px;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
       .success-message {
            text-align: center;
            color: #27ae60;
            margin-top: 20px;
        }
    </style>
  %%[
    var @display,@chkNewsletter,@chkPromotions
    SET @display="none"
  ]%%
<script runat="server">
    // Load the required core library
    Platform.Load("core", "1");

    try {
        // Retrieve the HTTP method (GET or POST)
        var method = Platform.Request.Method;
        

        // Handle GET request
        if (method == "GET") {
            // Retrieve attributes from context
            var email = Attribute.GetValue("emailaddr");
            //var subscriberKey = Attribute.GetValue("_subscriberkey");GUID()
            
            Write('<br>')
        }

        // Handle POST request
        if (method == "POST") {
            // Retrieve form field values from the POST request
            var email = Platform.Request.GetFormField("email");
            //var subscriberKey = Platform.Request.GetFormField("subscriberKey");
            var promotional = Platform.Request.GetFormField("promotional");
            var updates = Platform.Request.GetFormField("updates");
            var newsletter = Platform.Request.GetFormField("newsletter");
            var language = Platform.Request.GetFormField("language");
            var timezone = Platform.Request.GetFormField("timezone");
            

            var subscriberKey = GUID();
            Write('subscriberKey'+subscriberKey);
            Write('<br>')
            
            
            // Convert promotional, updates, and newsletter values to "True" or "False" strings
            if (promotional)
                promotional = "True";
            else
                promotional = "False";

            if (updates)
                updates = "True";
            else
                updates = "False";

            if (newsletter)
                newsletter = "True";
            else
                newsletter = "False";    

                Write('email promotional:-'+promotional);Write('<br>') 
                Write('email updates:-'+updates); Write('<br>') 
                Write('email newsletter:-'+newsletter);   
                Write('<br>')  

            // Initialize the Data Extension
            var DE = DataExtension.Init('Dataview_CloudPage');
           
           /**This the Payload data that i want to update */
            var payload ={ 
                "subscriberKey": subscriberKey,
                "email": email,      
                "promotional": promotional,
                "updates": updates,
                "newsletter": newsletter,
                "language": language,
                "timezone": timezone
            }

            


            Write('payload'+Stringify(payload));
            
            // Update the rows in the Data Extension
            var updatestatus = DE.Rows.Update(payload, ["subscriberKey"], [subscriberKey]);

             Write('update status'+updatestatus);

            if(updatestatus==0){
                // Insert the rows in the Data Extension
                var addstatus = DE.Rows.Add(payload);
                 Write(' 1 rows insert status'+addstatus);
            }    
        Variable.SetValue("@display", "");
            
        }

        // Set variables for status of newsletter and promotions
        var test1= Variable.SetValue("@email", email);
        var test2= Variable.SetValue("@subscriberKey", subscriberKey);
        Write('<br>');
        Write('STATUS1');
        Write(test1);
        Write('<br>');
        Write('STATUS2');
        Write(test2);
    } catch(error) {
        Write(Stringify(error));
    }	
</script>


</head>
<body>
    <div class="container">
        <h1>Preference Center</h1>
      <div style="display:%%=v(@display)=%%">
            <p class="success-message" id="successMessage">Your preferences have been updated successfully.</p>
        </div>
      <form action="%%=RequestParameter('PAGEURL')=%%" method="post">
            <label for="email">Email Address:</label>
            <input type="email" id="email" name="email" placeholder="Your email address" value="%%=v(@email)=%%" required>
            <input type="hidden" id="subscriberKey" name="subscriberKey" value="%%=v(@subscriberKey)=%%">
            <div class="email-preferences">
                <label><input type="checkbox" name="promotional" checked> <span class="checkmark"></span> Receive Promotional Offers</label>
                <label><input type="checkbox" name="updates" checked> <span class="checkmark"></span> Receive Product Updates</label>
                <label><input type="checkbox" name="newsletter" checked> <span class="checkmark"></span> Subscribe to Newsletter</label>
            </div>
            <label for="language">Preferred Language:</label>
            <select id="language" name="language">
                <option value="en">English</option>
                <option value="fr">French</option>
                <option value="es">Spanish</option>
                <!-- Add more options as needed -->
            </select>
            <label for="timezone">Preferred Timezone:</label>
            <select id="timezone" name="timezone">
                <option value="gmt-8">GMT-8 (Pacific Time)</option>
                <option value="gmt-5">GMT-5 (Eastern Time)</option>
                <option value="gmt+1">GMT+1 (Central European Time)</option>
                <!-- Add more options as needed -->
            </select>
            <input type="submit" value="Update Preferences">
        </form>
    </div>
</body>
</html>


