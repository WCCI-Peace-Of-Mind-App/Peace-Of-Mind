const newMedication = document.querySelector(".new-medication");

const id = document.querySelector("#id").value;
const drugName = document.querySelector("#name");
const dosage = document.querySelector("#dosage");
const administration = document.querySelector("#administration");
const amount = document.querySelector("#amount");
const picture = document.querySelector("#picture");
const reason = document.querySelector("#reason");

const submitMed = document.querySelector(".submit-btn");

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


submitMed.addEventListener('click', function() {
    if(validateAllData()) {
        let frequencyTime = document.querySelector('input[name="frequencyTime"]:checked').value;
        postMedication(id, drugName.value, dosage.value, administration.value, amount.value, frequencyTime, picture.value, reason.value);
        drugName.value="";
        dosage.value="";
        administration.value="";
        amount.value="";
        reason.value="";
    } else if (!(validateTextData())) {
        alert ("Make sure all fields are filled out before submitting");
    } else {
        alert ("Frequency Amount must be a number");
    }
});