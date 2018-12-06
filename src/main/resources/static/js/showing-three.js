const patientStatus = document.querySelector(".patientStatus");
const patientId = document.querySelector(".id").value;
const showOneButton = document.querySelector(".show-one");
const showAllButton = document.querySelector(".show-all");

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function() {
    if(xhr.readyState === 4 && xhr.status === 200) {
        const res = xhr.responseText;
        patientStatus.innerHTML = res;
    }
}

function postAllStatuses(patientId) {
    xhr.open('GET', '/status-history-all/' + patientId, true);
    xhr.send();
}

showAllButton.addEventListener('click', function() {
    postAllStatuses(patientId);
})

