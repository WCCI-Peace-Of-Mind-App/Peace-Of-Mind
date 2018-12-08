const newMedication = document.querySelector(".new-medication");

const id = document.querySelector("#id").value;
const drugName = document.querySelector("#name");
const dosage = document.querySelector("#dosage");
const administration = document.querySelector("#administration");
const amount = document.querySelector("#amount");
const picture = document.querySelector("#picture");
const reason = document.querySelector("#reason");

const submitMed = document.querySelector(".submit-btn");

const missingEntry = "Make sure all fields are filled out before submitting";
const notANumber = "Frequency Amount must be a number";

const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    if(xhr.readyState === 4 && xhr.status === 200) {
        const res = xhr.responseText;
        newMedication.innerHTML = res;
    }
};

function postMedication(id, drugname, dosage, administration, frequencyAmount, frequencyTime, picture, reason) {
     xhr.open('POST', "/add-medication/add/" + id + "/" + drugname + "/" + dosage + "/" + administration + "/" + frequencyAmount + "/" + frequencyTime + "/" + picture + "/" + reason);
     xhr.send();
}

function textHasValue(textbox) {
    return (textbox.value !== "");
}

function textIsNumber(textbox) {
    const regex = /^[0-9]+$/;
    return (textbox.value.match(regex));
}

function validateTextData() {
    return (textHasValue(drugName) && textHasValue(dosage) && textHasValue(administration) && textHasValue(reason));
}

function validateAllData() {
    return(validateTextData() && textIsNumber(amount));
}

function addWarningText(textToPost) {
    newMedication.innerHTML = textToPost;
}

function closeMessageDiv() {
    newMedication.style.display = "none";
}

function showThenHideMessage() {
    newMedication.style.display = "block";
    window.setTimeout(closeMessageDiv, 5000);
}

function displayMessage(textToPost) {
    addWarningText(textToPost);
    showThenHideMessage();
}

function clearEntryFields() {
    drugName.value="";
    dosage.value="";
    administration.value="";
    amount.value="";
    reason.value="";
}

function changeCaseOfText(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}

function submitMedication(id, drugName, dosage, administration, amount, picture, reason) {
    let frequencyTime = document.querySelector('input[name="frequencyTime"]:checked').value;
    let value1 = changeCaseOfText(drugName.value);
    let value2 = changeCaseOfText(dosage.value);
    let value3 = changeCaseOfText(administration.value);
    let value4 = changeCaseOfText(amount.value);
    let value5 = changeCaseOfText(reason.value);
    postMedication(id, value1, value2, value3, value4, frequencyTime, picture.value, value5);
}

submitMed.addEventListener('click', function() {
    if(validateAllData()) {
        submitMedication(id, drugName, dosage, administration, amount, picture, reason);
        clearEntryFields();
        showThenHideMessage();
    } else if (!(validateTextData())) {
        displayMessage(missingEntry);
    } else {
        displayMessage(notANumber);
    }
});