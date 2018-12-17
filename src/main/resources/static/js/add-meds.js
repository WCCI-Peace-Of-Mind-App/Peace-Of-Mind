const newMedication = document.querySelector(".new-medication");

const id = document.querySelector("#id").value;
const drugName = document.querySelector("#name");
const dosage = document.querySelector("#dosage");
const amount = document.querySelector("#amount");
const picture = document.querySelector("#picture");
const reason = document.querySelector("#reason");

const submitMed = document.querySelector(".submit-btn");

let timing = null;


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

function textIsAlphanumereic(textbox) {
    let regex = /^[A-Za-z0-9]+$/;
    return(textbox.value.match(regex));
}

function textIsNumber(textbox) {
    let regex = /^[0-9]+$/;
    return (textbox.value.match(regex));
}

function validateTextBoxesHaveData () {
    return (textHasValue(drugName) && textHasValue(dosage) && textHasValue(reason) && textHasValue(amount));
}

function validateTextBoxesHaveValidData() {
    return(textIsAlphanumereic(drugName) && textIsAlphanumereic(dosage));
}

function validateAllData() {
    return(validateTextBoxesHaveValidData() && textIsNumber(amount) && textHasValue(reason));
}

function addWarningText(textToPost) {
    newMedication.innerHTML = textToPost;
}

function closeMessageDiv() {
    newMedication.style.opacity = 0;
}

function showThenHideMessage() {
    newMedication.style.opacity = 1;
    timing = window.setTimeout(closeMessageDiv, 5000);
}

function stopExistingTimeout() {
    if (timing !== null) {
        clearTimeout(timing);
    }
}

function displayMessage(textToPost) {
    addWarningText(textToPost);
    showThenHideMessage();
}

function clearEntryFields() {
    drugName.value="";
    dosage.value="";
    amount.value="";
    reason.value="";
}

function changeCaseOfText(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}

function submitMedication(id, drugName, dosage, amount, picture, reason) {
    let frequencyTime = document.querySelector('input[name="frequencyTime"]:checked').value;
    let administration = document.querySelector('input[name="administration"]:checked').value;
    let value2 = changeCaseOfText(drugName.value);
    let value3 = changeCaseOfText(dosage.value);
    let value5 = changeCaseOfText(amount.value);
    let value8 = changeCaseOfText(reason.value);
    postMedication(id, value2, value3, administration, value5, frequencyTime, picture.value, value8);
}

submitMed.addEventListener('click', function() {
    stopExistingTimeout();
    if(validateAllData()) {
        submitMedication(id, drugName, dosage, amount, picture, reason);
        clearEntryFields();
        showThenHideMessage();
    } else if (!(validateTextBoxesHaveData())) {
        displayMessage(missingEntry);
    } else if (!(textIsNumber(amount))) {
        displayMessage(notANumber);
    } else {
        displayMessage("Use only alpha-numeric characters please");
    }
});