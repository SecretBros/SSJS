<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Unsubscribe</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #ecf0f3;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .container {
      max-width: 400px;
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    h1 {
      color: #333;
    }
    p {
      color: #666;
      margin-top: 20px;
    }
    form {
      margin-top: 20px;
    }
    button {
      padding: 10px 20px;
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
    .success-message {
      color: #27ae60;
      margin-top: 20px;
      
    }
    .logo {
      max-width: 150px;
      margin-bottom: 20px;
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
        SET @subscriberKey = RequestParameter("SubscriberKey") 
        SET @submitted =  IIF(RequestParameter("Submitted") == "True" , "True", "False")
        SET @display="none"

        IF @submitted=="True" THEN

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
            SET @options = CreateObject("UpdateOptions")
            SET @save = CreateObject("SaveOption")
            SetObjectProperty(@save, "SaveAction", "UpdateAdd")
            SetObjectProperty(@save, "PropertyName", "*")
            AddObjectArrayItem(@options, "SaveOptions", @save)

            /* Update the Subscriber object with the new status */
            /* The updated status, @update_sub_status, and any error code, @update_sub_errorcode, are captured */
            SET @subscribersStatus = InvokeUpdate(@subscriber, @update_sub_status, @update_sub_errorcode, @options)
            SET @display=""


        ENDIF

]%%
</head>
<body>
  <div class="container">
    <img src="https://www.salesforce.com/news/wp-content/uploads/sites/3/2021/05/Salesforce-logo.jpg" alt="Your Logo" class="logo">
    <h1>Unsubscribe</h1>
    <p>Click the button below to unsubscribe from our newsletter.</p>
    <form id="unsubscribeForm" method="post" action="%%=RequestParameter('PAGEURL')=%%">
        <input type="hidden" name="SubscriberKey" id="SubscriberKey" value="%%=v(@SubscriberKey)=%%">
        <input type="hidden" name="Submitted" id="Submitted" value="True">
      <button type="submit">Unsubscribe</button>
    </form>
    <div style="display:%%=v(@display)=%%">
       <p class="success-message" id="successMessage" >You have been successfully unsubscribed.</p>
    </div>
   
  </div>
</body>
</html>