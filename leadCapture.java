<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Lead Capture Form</title>
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
      max-width: 400px;
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
    }
    h2 {
      text-align: center;
      color: #333;
    }
    form {
      margin-top: 20px;
    }
    label {
      display: block;
      margin-bottom: 5px;
      color: #666;
    }
    input[type="text"],
    input[type="email"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      margin-bottom: 10px;
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
      margin: 0 auto 20px;
      max-width: 200px;
    }
    .success-message {
      text-align: center;
      color: #27ae60;
      margin-top: 20px;
    }
  </style>
 
  <script runat="server">
     Platform.Load('core', '1');
     var method=Platform.Request.Method;

     Variable.SetValue("@method", method);
try{
</script>
%%[
    
    SET @display = "none"
    
    /* Check if the method is "POST" */
    IF @method == "POST" THEN

            /* Assign subscriberkey from the query parameter */
            SET @subscriberKey = GUID()
            SET @firstName = RequestParameter("firstName")
            SET @lastName = RequestParameter("lastName")
            SET @email = RequestParameter("email")
            SET @consent = IIF(RequestParameter("consent") == "True", "True", "False")
            SET @display = "none"

        
            SET @status = IIF(@consent == "True", "Active", "Unsubscribed")
            /* Get the Business Unit (BU) MID (Member ID) */
            SET @BU_MID = AuthenticatedMemberID()

            /* Create a Subscriber object */
            SET @subscriber = CreateObject("Subscriber")
            SetObjectProperty(@subscriber, "SubscriberKey", @subscriberKey) /* Ensure subscriberKey is provided */
            SetObjectProperty(@subscriber, "EmailAddress", @email)
            SetObjectProperty(@subscriber, "Status", @status) /* Ensure status is provided */

            /* Create a ClientID object and associate it with the Subscriber */
            SET @cid = CreateObject("ClientID")
            SetObjectProperty(@cid, "ID", @BU_MID)
            SetObjectProperty(@cid, "IDSpecified", "true")
            SetObjectProperty(@subscriber, "Client", @cid)

            /* Set up options for updating the Subscriber */
            SET @options = CreateObject("UpdateOptions")
            SET @save = CreateObject("SaveOption")
            SetObjectProperty(@save, "SaveAction", "UpdateAdd")
            SetObjectProperty(@save, "PropertyName", "*")
            AddObjectArrayItem(@options, "SaveOptions", @save)

            /* Update the Subscriber object with the new status */
            /* The updated status, @update_sub_status, and any error code, @update_sub_errorcode, are captured */
            SET @subscribersStatus = InvokeUpdate(@subscriber, @update_sub_status, @update_sub_errorcode, @options)

            SET @upsert =UpsertData("Subscribers", 1,
                                    "SubscriberKey", @subscriberKey,
                                    "Email", @email,
                                    "FirstName",@firstName,
                                    "LastName", @lastName,
                                    "EmailConsent", @consent)
            SET @display = " "
            
    ENDIF
]%%


<script runat="server">
  }
    catch(ex)
    {
      Platform.Load('core', '1');
      Write(Stringify(ex));
    }
</script>
  

</head>
<body>
  <div class="container">
    <img src="https://www.salesforce.com/news/wp-content/uploads/sites/3/2021/05/Salesforce-logo.jpg" alt="Your Logo" class="logo">
    <h2>Sign Up for Updates</h2>
    <form id="leadCaptureForm" method="post" action="%%=RequestParameter('PAGEURL')=%%">
      <label for="firstName">First Name</label>
      <input type="text" id="firstName" name="firstName" placeholder="Enter your first name" required>
      <label for="lastName">Last Name</label>
      <input type="text" id="lastName" name="lastName" placeholder="Enter your last name" required>
      <label for="email">Email Address</label>
      <input type="email" id="email" name="email" placeholder="Enter your email address" required>
      <label for="consent">
        <input type="checkbox" id="consent" name="consent" value="True">
        I agree to receive marketing communications.
      </label>

      <button type="submit">Sign Up</button>
    </form>
    <div style="display:%%=v(@display)=%%">
        <p class="success-message" id="successMessage">Thank you for signing up!</p>
    </div>
   
  </div>
</body>
</html>