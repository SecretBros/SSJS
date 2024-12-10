<script  runat="server" language="Javascript">
Platform.Load("Core","1");


try{
    var contentType="dataextension"
  /**====================================== PART 1 ======================================== */
  /** Start 1.This line tell us that we are trying to fetch the root folder */
    var parentFolderCategoryID = RetriveFolderCategoryID("Data Extensions",contentType);
    Write(parentFolderCategoryID);

  /** End.This line tell us that we are trying to fetch the root folder */

/**===================================== PART 2 =============================================== */
    /**Name of new Folder */
  var newFolderName="SSJS Learning Session 11";

 /**Checking if the folder already exist or not */
  var childFolderID=RetriveFolderCategoryID(newFolderName,contentType);

 /** Create a new folder if it does not already exist */
 if(!childFolderID){
  //var newFolderStatus=CreateFolder(newFolderName,contentType,parentFolderCategoryID);
  childFolderID=RetriveFolderCategoryID(newFolderName,contentType);
}
/**====================================== PART 3 ===================================================== */
 var deSearchName="SignUpDataExtension";
 var deName = SearchDataExtension(deSearchName);  
;

/***=========================================  PART 4 ==================================================== */
if(!deName){
    var newDE=SignUpDataExtension(childFolderID,deSearchName);
    }
/**=============================================PART 5============================================= */

var checkRecords=AddRecordInSignUpDataExtension(deSearchName);
Write(Stringify(checkRecords));


}
catch(ex){
 // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
}

/** Step1 Function to  Retrive the root folder */

function RetriveFolderCategoryID(name,ContentType){

   var filter1={
      Property: "Name",
      SimpleOperator: "equals",
      Value: name
    }
  
  var filter2={
      Property: "ContentType",
      SimpleOperator: "equals",
      Value: ContentType
    }

  var complexFilter = {
         LeftOperand: filter1,
        LogicalOperator: "AND",
        RightOperand: filter2
 }

    var result=Folder.Retrieve(complexFilter);
    return result[0].ID;

}



function CreateFolder(newFolderName,contentType,parentFolderCategoryID){
    var newFolder = {
      "Name" : newFolderName,
      "CustomerKey" : newFolderName,
      "Description" : "Test added",
      "ContentType" : contentType,
      "IsActive" : "true",
      "IsEditable" : "true",
      "AllowChildren" : "true",
      "ParentFolderID" : parentFolderCategoryID // Parent folder ID; adjust as needed
    };

    // Add the new folder
    var status = Folder.Add(newFolder);
    return status;

}


function SearchDataExtension(deName){

var filter={
       Property: "CustomerKey",
      SimpleOperator: "equals",
      Value: deName
    }
    var results = DataExtension.Retrieve(filter);
    return results[0].Name;

}


function SignUpDataExtension(childFolderID,deSearchName) {
    // Define the properties of the Sendable Data Extension to be created
    var deObj = {
      "CustomerKey": deSearchName,
      "Name": deSearchName,
      "CategoryID": childFolderID,
      "Fields": [
        { "Name": "EmailAddress", "FieldType": "EmailAddress" },
        { "Name": "MobileNumber", "FieldType": "Number"}
      ],
      "SendableInfo": {
        "Field":  { "Name": "EmailAddress", "FieldType": "EmailAddress" },
        "RelatesOn": "Subscriber Key"
      },
      "IsTestable": true
    };

    // Create the Sendable Data Extension
    var myDE = DataExtension.Add(deObj);

    // Output the created Sendable Data Extension details
    Write(Stringify(myDE));
}



function AddRecordInSignUpDataExtension(deSearchName){
 // Initialize the existing Sendable Data Extension

  // Define an array of contacts to be added
    var arrContacts = [
      { EmailAddress: "sprasad@mailinator.com", MobileNumber : 8436373828},
      { EmailAddress: "sprasad2@mailinator.com", MobileNumber : 8436373828}
    ];
    var updateDE = DataExtension.Init(deSearchName);

    // Add the rows to the Data Extension
    var status = updateDE.Rows.Add(arrContacts);

    // Output the status of adding the new rows
    Write("Add Rows Status: " + status + '\n');
    return status
}
</script>