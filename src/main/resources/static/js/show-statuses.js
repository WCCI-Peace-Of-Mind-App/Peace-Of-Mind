const patientStatus = document.querySelector(".statusChange");
const patientId = document.querySelector(".id").value;

const showOneButton = document.querySelector(".show-one");
const showThreeButton = document.querySelector(".show-three");
const showAllButton = document.querySelector(".show-all");

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function() {
    if(xhr.readyState === 4 && xhr.status === 200) {
        const res = xhr.responseText;
        patientStatus.innerHTML = res;
    }
}

function postOneStatus(patientId) {
    xhr.open('GET', '/status-history-one/' + patientId, true);
    xhr.send();
}

function postThreeStatuses(patientId) {
    xhr.open('GET', '/status-history-three/' + patientId, true);
    xhr.send();
}

function postAllStatuses(patientId) {
    xhr.open('GET', '/status-history-all/' + patientId, true);
    xhr.send();
}

showOneButton.addEventListener('click', function() {
    postOneStatus(patientId);
    showOneButton.style.display = "none";
    showThreeButton.innerText = "View Status History";
    showThreeButton.style.display = "inline-block";
    showAllButton.style.display = "none";

})

showThreeButton.addEventListener('click', function() {
    postThreeStatuses(patientId);
    showOneButton.style.display = "inline-block";
    showThreeButton.style.display = "none";
    showAllButton.style.display = "inline-block";

})

showAllButton.addEventListener('click', function() {
    postAllStatuses(patientId);
    showOneButton.style.display = "inline-block";
    showThreeButton.innerText = "Show Fewer";
    showThreeButton.style.display = "inline-block";
    showAllButton.style.display = "none";
})

