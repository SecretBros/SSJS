<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subscription Centers</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 90%;
            max-width: 600px;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: inline-block;
            width: 30%;
            margin-bottom: 5px;
            color: #666;
        }

        input[type="text"],
        input[type="email"] {
            width: 65%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 10px;
        }

        .preferences label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }

        .preferences input[type="checkbox"] {
            display: inline-block;
            margin-right: 5px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #3498db;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #2980b9;
        }

        .logo {
            display: block;
            margin: 0 auto 10px;
            max-width: 100px;
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
        Platform.Load("core", "1.1");
        try {
            // Retrieve the HTTP method
            var method = Platform.Request.Method;
            // Handle GET request
            if (method == "GET") {
                // Retrieve attributes from context
                var email = Attribute.GetValue("emailaddr");
                var subscriberKey = Attribute.GetValue("_subscriberkey");
                var firstName = Attribute.GetValue("First Name");
                var lastName = Attribute.GetValue("Last Name");
            }
            // Handle POST request
            if (method == "POST") {
                // Retrieve form field values from the POST request
                var email = Platform.Request.GetFormField("email");
                var subscriberKey = Platform.Request.GetFormField("subscriberKey");
                var firstName = Platform.Request.GetFormField("firstName");
                var lastName = Platform.Request.GetFormField("lastName");
                var newsletter = Platform.Request.GetFormField("newsletter");
                // Determine status based on newsletter subscription
                if (newsletter && newsletter == "True") {
                    var status = "Active";
                    var chkNewsletter="checked";
                } else {
                    var status = "Unsubscribed";
                }
                // Create subscriber object for newsletter subscription
                var subscriberNewsletter = {
                    "EmailAddress": email,
                    "SubscriberKey": subscriberKey,
                    "Lists": [{
                        "ID": 1754,
                        "Status": status,
                        "Action": "Upsert"
                    }]
                };
                // Perform Upsert operation for newsletter subscription
                var subObjNewsletter = Subscriber.Init(subscriberKey);
                var statusNewsletter = subObjNewsletter.Upsert(subscriberNewsletter);
                // Retrieve promotions form field value
                var promotions = Platform.Request.GetFormField("promotions");
                // Determine status based on promotions subscription
                if (promotions && promotions == "True") {
                    var status = "Active";
                    var chkPromotions="checked";
                } else {
                    var status = "Unsubscribed";
                }
                // Create subscriber object for promotions subscription
                var subscriberPromotions = {
                    "EmailAddress": email,
                    "SubscriberKey": subscriberKey,
                    "Lists": [{
                        "ID": 1753,
                        "Status": status,
                        "Action": "Upsert"
                    }]
                };
                // Perform Upsert operation for promotions subscription
                var subObjPromotions = Subscriber.Init(subscriberKey);
                var statusPromotions = subObjPromotions.Upsert(subscriberPromotions);
               Variable.SetValue("@display", "");
            }
            // Set variables for status of newsletter and promotions
              Variable.SetValue("@statusPromotions", statusPromotions);
              Variable.SetValue("@statusNewsletter", statusNewsletter);
              Variable.SetValue("@email", email);
              Variable.SetValue("@subscriberKey", subscriberKey);
              Variable.SetValue("@firstName", firstName);
              Variable.SetValue("@lastName", lastName);
              Variable.SetValue("@chkNewsletter", chkNewsletter);
              Variable.SetValue("@chkPromotions", chkPromotions);
        } catch (e) {
            // Handle exceptions
            Write("Exception: " + e.message + "<br>");
            Write("Description: " + e.description + "<br>");
        }
    </script>
</head>

<body>
    <div class="container">
        <img src="https://www.salesforce.com/news/wp-content/uploads/sites/3/2021/05/Salesforce-logo.jpg"
            alt="Your Logo" class="logo">
        <h1>Subscription Centers</h1>
        <div style="display:%%=v(@display)=%%">
            <p class="success-message" id="successMessage">Your preferences have been updated successfully.</p>
        </div>
        <!-- Subscription Form -->
        <form action="#" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="%%=v(@firstName)=%%"><br>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="%%=v(@lastName)=%%"><br>
            <input type="hidden" id="subscriberKey" name="subscriberKey" value="%%=v(@subscriberKey)=%%">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="%%=v(@email)=%%"><br><br>
            <!-- Preferences -->
            <p>Preferences:</p>
            <div class="preferences">
                <label><input type="checkbox" name="newsletter" value="True" %%=v(@chkNewsletter)=%%> Newsletter</label><br>
                <label><input type="checkbox" name="promotions" value="True" %%=v(@chkPromotions)=%%> Promotions</label><br><br>
            </div>
            <input type="hidden" name="submitted" value="True">
            <button type="submit">Submit</button>
        </form>
    </div>
</body>

</html>