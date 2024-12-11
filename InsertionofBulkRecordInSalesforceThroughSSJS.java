<script  runat="server" language="Javascript">
Platform.Load("Core","1");


try{
    var ContentType="dataextension"
    var parentFolderCategoryID=RetriveFolderCategoryID("Data Extensions",ContentType)
    Write(Stringify('parentFolderCategoryID'+parentFolderCategoryID));

}
catch(ex){

}






/**Function to  Retrive the root folder */

function RetriveFolderCategoryID(name,ContentType){

    /**Assign value to operate for left operand for filter */
    var prop1="Name";
    var SimpleOperator="equals";
    var prop1value=name;

    /**Assign varible to filter operation for  right filter  */
    var prop2="ContentType";
    var SimpleOperator2="equals";
    var prop2value=ContentType;


    /**want to filter the records */
    var filter1={Property:prop1,SimpleOperator:SimpleOperator,value:prop1value}

    var filter2={Property:prop2,SimpleOperator:SimpleOperator2,value:prop2value}


    var complexFilter={
        LeftOperand:filter1,
        LogicalOperator: "AND",
        RightOperand:filter2

    }

    var result=Folder.Retrieve(complexFilter);
    return result[0].ID;

}



/**If the root Folder Does not exist create the child  Folder  */ 
function CreateFolder(){

}


/**Retrive the DE  */

/**Create the DE */
function CreatSalesforceDE(){
    try{
    var createDE_Result=DataExtension.Init("");

    }
    catch{

    }

}




}




</script>