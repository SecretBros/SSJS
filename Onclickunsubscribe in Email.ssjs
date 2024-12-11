<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Unsubscribe Confirmation</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f9f9f9;
      margin: 0;
      padding: 0;
    }
    .container {
      max-width: 600px;
      margin: 50px auto;
      padding: 20px;
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
      color: #333333;
      margin-top: 0;
    }
    p {
      color: #666666;
      line-height: 1.6;
    }
    .logo {
      width: 150px;
      margin: 20px auto;
    }
    .footer {
      margin-top: 30px;
      color: #999999;
      font-size: 0.8em;
    }
  </style>
  %%[

  /* 
  This code snippet is responsible for updating the status of a Subscriber object. 
  It requires the following parameters to be passed:
  - subscriberKey: The unique identifier for the subscriber.
  - status: The status of the subscriber (e.g., Active, Unsubscribed, Held).

  Please ensure that both subscriberKey and status are provided for proper execution.
  */

  /* Assign subscriberkey from the query parameter */
  SET @subscriberKey = AttributeValue("_subscriberkey") 
  SET @emailAddress = AttributeValue("emailaddr")
  SET @status = "Unsubscribed"

  /* Get the Business Unit (BU) MID (Member ID) */
  SET @BU_MID = AuthenticatedMemberID()

  /* Create a Subscriber object */
  SET @subscriber = CreateObject("Subscriber")
  SetObjectProperty(@subscriber, "SubscriberKey", @subscriberKey) /* Ensure subscriberKey is provided */
  SetObjectProperty(@subscriber, "Status", @status) /* Ensure status is provided */

  /* Create a ClientID object and associate it with the Subscriber */
  SET @cid = CreateObject("ClientID")
  SetObjectProperty(@cid, "ID", @BU_MID)
  SetObjectProperty(@cid, "IDSpecified", "true")
  SetObjectProperty(@subscriber, "Client", @cid)

  /* Set up options for updating the Subscriber */
  /*yeh wala part todha smj mai hi aya*/
  Set @options = CreateObject("UpdateOptions")
  Set @save = CreateObject("SaveOption")
  SetObjectProperty(@save, "SaveAction", "UpdateAdd")
  SetObjectProperty(@save, "PropertyName", "*")
  AddObjectArrayItem(@options, "SaveOptions", @save)

  /* Update the Subscriber object with the new status */
  /* The updated status, @update_sub_status, and any error code, @update_sub_errorcode, are captured */
  Set @subscribersStatus = InvokeUpdate(@subscriber, @update_sub_status, @update_sub_errorcode, @options)


]%%
</head>
<body>
  <div class="container"> 
    <img src="https://www.salesforce.com/news/wp-content/uploads/sites/3/2021/05/Salesforce-logo.jpg" alt="Your Company Logo" class="logo">
    <h1>Unsubscribe Successful</h1>
    <p>You have been successfully unsubscribed from our newsletter. You will no longer receive emails from us.</p>
    <p>If you have any questions or concerns, please contact us at <a href="mailto:info@example.com">info@example.com</a>.</p>
  </div>
  <div class="footer">
    &copy; 2024 Your Company. All rights reserved.
  </div>
</body>
</html>