var defaultTextColor = "#C0C0C0";
var defaultBackgroundColor = "#2C2020";
var errorBackgroundColor = "darkred";
var userName = []; // Will hold the first and last name of the user.
var radioAnswer = []; // Will contain the answers to the survey questions.
var checkboxAnswer = ""; // Will become a string that contains all checkbox answers.
var textareaAnswer = ""; // Will contain any additional comments from the user.
var questionText = []; // Will contain the text of each question.


function submitForm() {
  var fieldset = document.getElementsByTagName("fieldset"); // Contains all fieldsets.
  var formIsValidated = true; // Set to false if a required field is empty.
  var errorText = "The following erors were found:\n\n"; // Optional error text.

  for (i = 0; i  < fieldset.length - 1; i++) { // Skip the last fieldset in the loop since it's a textarea.
    var isFieldsetChecked = []; // Set to true if a checked input is found in a corresponding fieldset.
    var input = fieldset[i].getElementsByTagName("input"); // Contains all inputs in this fieldset.

    // Check if all inputs in this fieldset have at least one check.
    var inputIsChecked = false; // Set to true if an input is checked.
    for (j = 0; j < input.length; j++) {
      // Check this input to see if it's checked.
      if (input[j].checked) {
        // This input is checked.
        inputIsChecked = true; // This fieldset has a checked radio or checkbox.
        if (input[j].type == "radio") {
          // This is a radio type, so add its value to an array of radio values.
          radioAnswer.push(input[j].value);
        }
        if (input[j].type == "checkbox") {
          // This is a checkbox type, so add its value to a string of checkbox values.
          checkboxAnswer += input[j].value + ", ";
        }
      }
    } // End input loop.

    if (! inputIsChecked) {
      // This fieldset did not have a checked radio or checkbox.
      formIsValidated = false; // Do not show survey results.
      errorText += " Question " + (i + 1) + " was not answered!\n";
      fieldset[i].style.background = errorBackgroundColor; // Indicate which fieldset was not answered.
    }
    else {
      // This fieldset did have a checked radio or checkbox.
      fieldset[i].style.background = defaultBackgroundColor; // Set the fieldset color back to default.
    } 
    // Get the text of each question from each legend element.
    questionText.push(fieldset[i].getElementsByTagName("p")[0].innerHTML);
  } // End fieldset loop.

  // An array that contains the inputs fields for first and last name entered.
    var userNameField =[document.getElementById("firstName"),
    document.getElementById("lastName")
  ];

  // Validate the first and last name fields.
  for (i = 0; i < userNameField.length; i++) {
    userName[i] = userNameField[i].value; // Get the first and last names.
    if (userName[i] == "") {
      // This name field is empty.
      formIsValidated = false; // Do not show survey results.
      userNameField[i].style.background = errorBackgroundColor; // Indicate which field is empty.
      if (i == 0) {
        // This is the first name.
        errorText += " First Name field is empty!\n";
      }
      else {
        // This is the last name.
        errorText += " Last Name field is empty!\n";
      }
    }
    else {
      // This name field is not emty, so test for valid characters.
      if (/[^a-zA-Z]/.test(userName[i])) {
        // This name field contains invalid characters.
        formIsValidated = false; // Do not show survey.
        userNameField[i].style.background = errorBackgroundColor; // Indicate which field is empty.
        if (i == 0) {
          // This is the first name.
          errorText += " First Name can only contain letters!\n";
        }
        else {
          // This is the last name.
          errorText += " Last Name can only contain letters!\n";
        }
      }
      else {
        // All characters in this name field are valid.
        userNameField[i].style.background = defaultBackgroundColor; // Set the field color back to default.
      }
    }
  } // End user-name validation loop.

  textareaAnswer = document.getElementsByTagName("textarea")[0].value; // Record any additional comments from the user.
  if (textareaAnswer == "") {
    textareaAnswer = "none"; // Dispaly the word none if no additional comments were entered.
  }

  if (formIsValidated) {
    // Form is validated.
    showCompletedSurvey(); // Display survey results.
  }
  else {
    // Form is not validated.
    errorText += "\nPlease correct the errors and resubmit the survey.";
    alert(errorText); // Display a detailed error message.
    resetGlobalVars(); // Reset global variables.
  }
  return false; // Since I have to create the resulting page with Javascript.
}


// Displays the survey results if the survey was validated.
function showCompletedSurvey() {
  window.open().document.write(
"<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<!-- lab5 by Jonathan Rainwater\n" +
"This is an unofficial customer satisfaction survey for Mobius Motors.\n" +
"The real Mobius Motors website can be located at https://mobiusmotors.com/\n" +
"This lab demonstrates usage of Javascript.\n" +
"-->\n" +
"<head>\n" +
"  <link rel=\"stylesheet/less\" href=\"less/lab5.less\">\n" +
"  <script src=\"less/less.js\" type=\"text/javascript\"></script>\n" +
"  <title>Survey Results</title>\n" +
"  <meta name=\"author\" content=\"Jonathan Rainwater\">\n" +
"</head>\n" +
"<body>\n" +
"<script src=\"js/functions.js\"></script>\n" +
"<p class=\"head\" style=\"font-weight:bold;\">\n" +
"<img src=\"img/logo_name.png\" class=\"logoName\" alt=\"Mobius\"> Survery Results\n" +
"</p>\n" +
"<p class=\"head\">\n" +
"Thank you, " + userName[0] + " " + userName[1] + ", for completing our survey!" +
"</p>\n" +
"Results:<br>\n" +
"<br>\n" +
"Q1  " + questionText[0] + ":   " + radioAnswer[0] + "<br><br>\n" +
"Q2  " + questionText[1] + ":   " + radioAnswer[1] + "<br><br>\n" +
"Q3  " + questionText[2] + ":   " + radioAnswer[2] + "<br><br>\n" +
"Q4  " + questionText[3] + ":   " + radioAnswer[3] + "<br><br>\n" +
"Q5  " + questionText[4] + ":   " + radioAnswer[4] + "<br><br>\n" +
"Q6  " + questionText[5] + ":   " + radioAnswer[5] + "<br><br>\n" +
"Q7  " + questionText[6] + ":   " + checkboxAnswer.slice(0,-2) + "<br><br>\n" +
"Q8  " + "Additional comments:<br> " +
"<textarea rows=\"10\" cols=\"60\" readonly>" + textareaAnswer + "</textarea><br>" +
"<br>\n" +
"<img src=\"img/logo_full.png\" class=\"logoImage\" alt=\"Mobius logo\">\n" +
"<br>\n" +
"</body>\n" +
"</html>"
  );
  resetGlobalVars(); // Reset global variables.
}

// Reset global variables.
function resetGlobalVars() {
  userName = [];
  radioAnswer = [];
  checkboxAnswer = "";
  textareaAnswer = "";
  questionText = [];
}

// EOF
