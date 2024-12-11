<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preference Center</title>
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            text-align: center;
            /* Center align the content */
        }

        .form-container {
            max-width: 400px;
            margin: 20px auto;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: left;
            /* Align form contents to the left */
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="checkbox"] {
            margin-right: 5px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>

<script runat="server">
    // Load necessary platform libraries
    Platform.Load('core', '1');
    // Get the HTTP request method
    var method=Platform.Request.Method;
    // Set a SSJS variable with the request method
    Variable.SetValue("@method", method);
  try{
  </script>
  
  %%[
      /* Define variables */
      VAR @display, @objectType
  
      /* Initialize display variable */
      SET @display = "none"
  
      /* Check if the method is "POST" */
      IF @method == "POST" THEN
          /* Retrieve POST parameters */
          SET @subscriberkey = RequestParameter("subscriberkey") 
          SET @email = RequestParameter("email")
          SET @firstName = RequestParameter("firstName")
          SET @lastName = RequestParameter("lastName")
          SET @status = IIF(RequestParameter("status") == "Active", "Active", "Unsubscribed")
          SET @objectType = RequestParameter("objectType")   
          
          /* Check if the object type is SFMC */
          IF @objectType == "SFMC" THEN
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
              SET @display = ""
          ELSE
              /* If object type is not SFMC, update Salesforce object */
              SET @status = IIF(RequestParameter("Status") == "Active", "True", "False")
              SET @updateRecord = UpdateSingleSalesforceObject(
                      @objectType, @subscriberkey,
                      "FirstName", @firstName,
                      "LastName", @lastName,
                      "Email", @email,
                      "HasOptedOutOfEmail", @status
              )
              SET @display = ""
          ENDIF
      ENDIF
  
      /* Check if the method is "GET" */
      IF @method == "GET" THEN
          /* Retrieve GET parameters */
          SET @subscriberkey = AttributeValue("_subscriberkey") 
          SET @email = AttributeValue("emailaddr")
          SET @firstName = AttributeValue("First Name")
          SET @lastName = AttributeValue("Last Name")
          SET @status = Lookup("_Subscribers", "Status", "SubscriberKey", @subscriberkey)
          SET @objectType = "SFMC"
  
          IF Length(@subscriberkey) == 18 THEN
              /* Fetching data from Sales Cloud to show in the form */
              SET @subscriberRows = RetrieveSalesforceObjects(
                  "Lead",
                  "Id,FirstName,LastName,Email,HasOptedOutOfEmail",
                  "Id",
                  "=",
                  @subscriberkey
              )
              SET @objectType = "Lead"
  
              IF RowCount(@subscriberRows) == 0 THEN
                  SET @subscriberRows = RetrieveSalesforceObjects(
                  "Contact",
                  "Id,FirstName,LastName,Email,HasOptedOutOfEmail",
                  "Id",
                  "=",
                  @subscriberkey
                  )
                  SET @objectType = "Contact"
              ENDIF
  
              IF RowCount(@subscriberRows) == 0 THEN
                  SET @subscriberRows = RetrieveSalesforceObjects(
                  "Account",
                  "Id,FirstName,LastName,Email,HasOptedOutOfEmail",
                  "Id",
                  "=",
                  @subscriberkey
                  )
                  SET @objectType = "Account"
              ENDIF
  
              IF RowCount(@subscriberRows) == 0 THEN
                  SET @subscriberRows = RetrieveSalesforceObjects(
                  "Opportunity",
                  "Id,FirstName,LastName,Email,HasOptedOutOfEmail",
                  "Id",
                  "=",
                  @subscriberkey
                  )
                  SET @objectType = "Opportunity"
              ENDIF
  
              /* Check if subscriber rows are retrieved */
              IF RowCount(@subscriberRows) > 0 THEN
                  SET @row = Row(@subscriberRows, 1)
                  SET @firstName = Field(@row, "FirstName")
                  SET @lastName = Field(@row, "LastName")
                  SET @email = Field(@row, "Email")
                  SET @status = Field(@row, "HasOptedOutOfEmail")
  
                  /* Checking if the subscriber has opted out of emails */
                  SET @status = IIF(@status == "True", "Unsubscribed", "Active")
              ENDIF
          ENDIF
  
          SET @chkStatus = IIF(@status == "Active", "checked", "")
      ENDIF
  ]%%
  
  
  <script runat="server">
    }
      catch(ex)
      {
        // Catch and handle any exceptions
        Platform.Load('core', '1');
        Write(Stringify(ex));
      }
  </script>
  
</head>

<body>
    <h1>Preference Center</h1>
    <div style="display:%%=v(@display)=%%">
        <p class="success-message" id="successMessage">Your preferences have been updated successfully.</p>
    </div>
    <!-- Form for Account preferences -->
    <div class="form-container" id="preferencesForm">
        <h2>User Preferences</h2>
        <form action="%%=RequestParameter('PAGEURL')=%%" method="post">
            <input type="hidden" name="subscriberkey" value="%%=v(@subscriberkey)=%%">
            <input type="hidden" name="objectType" value="%%=v(@objectType)=%%">
            <label>
                First Name:
                <input type="text" name="firstName" value="%%=v(@firstName)=%%" required>
            </label>
            <label>
                Last Name:
                <input type="text" name="lastName" value="%%=v(@lastName)=%%" required>
            </label>
            <label>
                Email:
                <input type="email" name="email" value="%%=v(@email)=%%" required>
            </label>
            <label>
                <input type="checkbox" name="status" value="%%=v(@status)=%%" %%=v(@chkStatus)=%%> Subscribe to Newsletter
            </label>
            <button type="submit">Update Preferences</button>
        </form>
    </div>

</body>

</html>